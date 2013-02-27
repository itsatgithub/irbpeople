/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/message/MessageResources.java,v 1.3 2005/02/16 18:03:21 P001001 Exp $
 * $Revision: 1.3 $
 * $Date: 2005/02/16 18:03:21 $
 *
 * ====================================================================
 *
 * Copyright (c) 2000 - 2005 SCC Informationssysteme GmbH. All rights
 * reserved.
 * Vendor URL : http://www.scc-gmbh.com
 * Product URL: http://www.common-controls.com
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL SCC INFORMATIONSSYSTEME GMBH OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 */

package com.cc.framework.message;

import java.util.Enumeration;
import java.util.Hashtable;

import com.cc.framework.common.Severity;

/**
 * This class defines a message (message resource) in different languages.
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.3 $
 * @since      1.0
 */
public class MessageResources {

	/**
	 * The buffer to store the messages
	 */
	private Hashtable map = new Hashtable();


	/**
	 * Constructor for MessageMap
	 */
	public MessageResources() {
		super();
	}

	/**
	 * Returns the number of Messages in list
	 *
	 * @return int
	 */
	public int getCount() {
		return map.size();
	}

	/**
	 * Returns a message for the specified code.
	 *
	 * @param	code	the message code
	 * @return	MessageResource
	 */
	public MessageResource getMessage(int code) {

		MessageResource message	= (MessageResource) map.get(new Integer(code));

		if (null == message) {
			message	= new MessageResource(9999, "UNKNOWN_MESSAGE", Severity.ERROR);

			message.addText(MessageResource.DEF_LANGUAGE, "unknown message: " + code);
		}

		return message;
	}

	/**
	 * Loads the messages into the internal list.
	 *
	 * @param 	loader	the message loader
	 */
	public void load(MessageResourceLoader loader) {

		// clear the list
		map.clear();

		if (null != loader) {

			// read the messages
			for (loader.restart(); !loader.done(); loader.next()) {
				MessageResource newMessage	= new MessageResource(
					loader.getCode(),
					loader.getName(),
					Severity.parse(loader.getSeverity()));

				int count = loader.getLanguageCount();

				for (int i = 0; i < count; i++) {
					newMessage.addText(loader.getLanguage(i), loader.getText(i));
				}

				map.put(new Integer(newMessage.getCode()), newMessage);
			}
		}
	}

	/**
	 * Returns an enumeration of all messages in this list.
	 *
     * @return  an enumeration of the values in this list.
     * @see     java.util.Enumeration
	 */
	public Enumeration messages() {
		return map.elements();
	}

	/**
	 * Returns a String representation for the size of the message dictionary
	 *
	 * @return String
	 */
	public String toString() {
		return "Message Dictionary size=" + getCount();
	}
}
