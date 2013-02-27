/**
 * $Header: d:/repository/cvs/cc-adapter-struts-1.2/adapter/com/cc/framework/ui/model/imp/StrutsMessageDataModelImp.java,v 1.5 2005/05/01 09:01:27 P001002 Exp $
 * $Revision: 1.5 $
 * $Date: 2005/05/01 09:01:27 $
 *
 * ====================================================================
 *
 * Copyright (c) 2000 - 2005 SCC Informationssysteme GmbH. All rights
 * reserved.
 * Vendor Site : http://www.scc-gmbh.com
 * Product Site: http://www.common-controls.com
 *
 * ====================================================================
 */

package com.cc.framework.ui.model.imp;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.taglib.TagUtils;

import com.cc.framework.common.Severity;
import com.cc.framework.message.Message;
import com.cc.framework.ui.model.MessageDataModel;
import com.cc.framework.util.StringHelp;

/**
 * Wrapper class for struts messages
 *
 * @author	   <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version	   $Revision: 1.5 $
 * @since      1.0
 */
public class StrutsMessageDataModelImp implements MessageDataModel {

	/**
	 * Inner class
	 */
	private static class StrutsMessage implements Message, Comparable {

		/**
		 * Severity
		 */
		private Severity severity = Severity.NONE;

		/**
		 * Key
		 */
		private String key;

		/**
		 * Message
		 */
		private String message;

		/**
		 * Constructor
		 * @param	severity	Severity
		 * @param	key			MessageKey
		 * @param	message		Message
		 */
		public StrutsMessage(Severity severity, String key, String message) {
			super();

			this.severity	= severity;
			this.key		= key;
			this.message	= message;
		}

		/**
		 * @see com.cc.framework.message.Message#getKey()
		 */
		public String getKey() {
			return key;
		}

		/**
		 * @see com.cc.framework.message.Message#getSeverity()
		 */
		public Severity getSeverity() {
			return severity;
		}

		/**
		 * @see com.cc.framework.message.Message#getMessage(String)
		 */
		public String getMessage(String locale) {
			return message;
		}

		/**
		 * @see com.cc.framework.message.Message#getMessage()
		 */
		public String getMessage() {
			return message;
		}

		/**
		 * @see java.lang.Comparable#compareTo(java.lang.Object)
		 */
		public int compareTo(Object o) {
			if (o instanceof Message) {
				return getSeverity().compareTo(((Message) o).getSeverity()) * -1;
			}

			return 0;
		}
	}

	/**
	 * Collection with all Messages
	 */
	private Message messages[] = new StrutsMessage[0];


	// ------------------------------------------------
    //                methods
    // ------------------------------------------------

	/**
	 * Constructor
	 */
	public StrutsMessageDataModelImp() {
		super();
	}

	/**
	 * Loads the Massages
	 *
	 * @param		severity Severity
	 * @param		pageContext PageContext
	 * @throws		JspException Wird im Falle eines Fehlers geworfen
	 */
	public void load(Severity severity, PageContext pageContext) throws JspException {

		String collectionKey =
			Severity.ERROR.equals(severity)
				? org.apache.struts.Globals.ERROR_KEY
				: org.apache.struts.Globals.MESSAGE_KEY;

		ActionMessages strutsMessages =
			TagUtils.getInstance().getActionMessages(
				pageContext,
				collectionKey);

		// Acquire the collection we are going to iterate over
		Iterator strutsMessageIter = strutsMessages.get();

		if (!strutsMessageIter.hasNext()) {
			return;
		}

		ArrayList list = new ArrayList();

		while (strutsMessageIter.hasNext()) {
			ActionMessage strutsMessage = (ActionMessage) strutsMessageIter.next();

			// default bundel to look for a message resource
			String bundel = org.apache.struts.Globals.MESSAGES_KEY;

			String key = strutsMessage.getKey();
			Object[] args = strutsMessage.getValues();

			// was a key/bundel spezified in the way key@bundel#arg1#arg2#arg3....?
			if (key.indexOf("@") != -1) {
				String[] arr = StringHelp.split(key, "@");
				key = arr[0];

				if (arr[1].indexOf("#") != -1) {
					int pos = arr[1].indexOf("#");
					bundel = arr[1].substring(0, pos);
					args = StringHelp.split(arr[1].substring(pos + 1, arr[1].length()), "#");
				} else {
					bundel = arr[1];
				}
			}

			// Create the message
			String msg =
				TagUtils.getInstance().message(
					pageContext,
					bundel,
					org.apache.struts.Globals.LOCALE_KEY,
					key,
					args);

			list.add(new StrutsMessage(severity, strutsMessage.getKey(), msg));
		}

		messages = new StrutsMessage[list.size()];
		list.toArray(messages);
	}

	/**
	 * @see com.cc.framework.ui.model.MessageDataModel#getMessages()
	 */
	public Message[] getMessages() {
		return messages;
	}

	/**
	 * @see com.cc.framework.ui.model.MessageDataModel#size()
	 */
	public int size() {
		return messages.length;
	}
}