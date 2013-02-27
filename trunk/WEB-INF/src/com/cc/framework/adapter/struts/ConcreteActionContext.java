/*
 * $Header: d:/repository/cvs/cc-adapter-struts-1.2/adapter/com/cc/framework/adapter/struts/ConcreteActionContext.java,v 1.8 2005/07/31 11:58:52 P001002 Exp $
 * $Revision: 1.8 $
 * $Date: 2005/07/31 11:58:52 $
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

package com.cc.framework.adapter.struts;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.cc.framework.Globals;
import com.cc.framework.adapter.FrameworkAdapterFactory;
import com.cc.framework.adapter.RequestContext;
import com.cc.framework.security.Principal;
import com.cc.framework.security.SecurityUtil;

/**
 * This class encapsulate all informations needed when calling a
 * control action method.
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.8 $
 * @since      1.0
 */
public class ConcreteActionContext implements ActionContext {

	/**
	 * The ActionForward which should be
	 * activate by the action
	 */
	private ActionForward forward			= null;

	/**
	 * Collection of error messages which was filled
	 * during the execution of an action.
	 */
	private ActionMessages errors			= null;

	/**
	 * Collection of messages which was filled
	 * during the execution of an action.
	 */
	private ActionMessages messages			= null;

	/**
	 * The ActionMapping
	 */
	private ActionMapping mapping			= null;

	/**
	 * The ActionForm form
	 */
	private ActionForm form					= null;

	/**
	 * The HttpServletRequest
	 */
	private HttpServletRequest request		= null;

	/**
	 * The HttpServletResponse
	 */
	private HttpServletResponse response	= null;

	/**
	 * This flag can be set if the ActionForward
	 * should be ignored.
	 */
	private boolean noForward				= false;

	/**
	 * Constructor for ConcreteActionContext
	 *
	 * @param	mapping		The ActionMapping
	 * @param	form		The ActionForm
	 * @param	request		The HttpServletRequest
	 * @param	response	The HttpServletResponse
	 */
	public ConcreteActionContext(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		this.mapping	= mapping;
		this.form		= form;
		this.request	= request;
		this.response	= response;
	}

	/**
	 * @see com.cc.framework.adapter.RequestContext#getNestedContext()
	 */
	public RequestContext getNestedContext() {
		return null;
	}

	/**
	 * Retrieves or creates the error collection. The Error
	 * collection is stored in the users session
	 *
	 * @return		Error Collection
	 */
	protected ActionMessages errorCollection() {

		if (errors == null) {
			if (session() == null) {
				// create a new error collection
				errors = new ActionMessages();
			} else {
				// search for an existing error collection in the session
				errors = (ActionMessages) session().getAttribute(Globals.ERRORS_SAVE_KEY);

				if (errors == null) {
					errors = new ActionMessages();
					session().setAttribute(Globals.ERRORS_SAVE_KEY, errors);
				}
			}
		}

		return errors;
	}

	/**
	 * Retrieves or creates the message collection. The Message
	 * collection is stored in the users session
	 *
	 * @return		Message Collection
	 */
	protected ActionMessages messageCollection() {

		if (messages == null) {
			if (session() == null) {
				// create a new error collection
				messages = new ActionMessages();
			} else {
				// search for an existing error collection in the session
				messages = (ActionMessages) session().getAttribute(Globals.MESSAGES_SAVE_KEY);

				if (messages == null) {
					messages = new ActionMessages();
					session().setAttribute(Globals.MESSAGES_SAVE_KEY, messages);
				}
			}
		}

		return messages;
	}


	/**
	 * Gets the ActionMapping
	 *
	 * @return Returns the ActionMapping
	 */
	public ActionMapping mapping() {
		return mapping;
	}

	/**
	 * Gets the ActionForm
	 *
	 * @return Returns the ActionForm
	 */
	public ActionForm form() {
		return form;
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#validateForm()
	 */
	public void validateForm() {
		addErrors(form.validate(mapping, request));
	}

	/**
	 * Gets the HttpServletRequest
	 *
	 * @return Returns the HttpServletRequest
	 */
	public HttpServletRequest request() {
		return request;
	}

	/**
	 * Gets the HttpServletResponse
	 *
	 * @return Returns the HttpServletResponse
	 */
	public HttpServletResponse response() {
		return response;
	}

	/**
	 * Returns the HttpSession
	 * @return	The HttpSession
	 */
	public HttpSession session() {
		return request.getSession(false);
	}

	/**
	 * Returns if the request was handeled
	 *
	 * @return Returns a boolean
	 */
	public boolean isHandeled() {
		return noForward || (forward != null);
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#getForward()
	 */
	public ActionForward getForward() {
		return noForward ? null : forward;
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#forward(ActionForward)
	 */
	public void forward(ActionForward forward) {
		this.forward	= forward;
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#forwardToResponse()
	 */
	public void forwardToResponse() {
		noForward = true;
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#forwardToInput()
	 */
	public void forwardToInput() {
		String input = mapping.getInput();

		forward((input == null) ? null : new ActionForward(input));
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#forwardToAction(String)
	 */
	public void forwardToAction(String actionName) {
		ActionForward fwd =
			new ActionForward(
				FrameworkAdapterFactory.getAdapter().applyServletMapping("/" + actionName),
				true);
		forward(fwd);
	}


	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#forwardByName(String)
	 */
	public void forwardByName(String forwardName) {

		ActionForward forward	= mapping.findForward(forwardName);

		forward((forward == null) ? null : new FWActionForward(forward));
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#forwardByName(String, Object)
	 */
	public void forwardByName(String forwardName, Object p1) {

		ActionForward forward	= mapping.findForward(forwardName);

		forward((forward == null) ? null : new FWActionForward(forward, p1));
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#forwardByName(String, Object, Object)
	 */
	public void forwardByName(String forwardName, Object p1, Object p2) {

		ActionForward forward	= mapping.findForward(forwardName);

		forward((forward == null) ? null : new FWActionForward(forward, p1, p2));
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#forwardByName(String, Object, Object, Object)
	 */
	public void forwardByName(String forwardName, Object p1, Object p2, Object p3) {

		ActionForward forward	= mapping.findForward(forwardName);

		forward((forward == null) ? null : new FWActionForward(forward, p1, p2, p3));
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#forwardByName(java.lang.String, java.lang.Object[])
	 */
	public void forwardByName(String forwardName, Object[] params) {

		ActionForward forward	= mapping.findForward(forwardName);

		forward((forward == null) ? null : new FWActionForward(forward, params));
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#getErrors()
	 */
	public ActionMessages getErrors() {
		HttpSession session = session();

		if (session == null) {
			return null;
		} else {
			return (ActionMessages) session().getAttribute(Globals.ERRORS_SAVE_KEY);
		}
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#invalidDialogContext(String)
	 */
	public void invalidDialogContext(String messageKey) {
		addGlobalError(messageKey);

		forwardToInput();
	}

	/**
	 * This method transfers all existing Struts errors and messages
	 * into the frameworks message store. The Struts collections will
	 * be cleared.
	 *
	 * @see com.cc.framework.adapter.struts.ActionContext#consumeStrutsMessages()
	 */
	public void consumeStrutsMessages() {

		// Messages first
		ActionMessages m =
			(ActionMessages) request().getAttribute(
				org.apache.struts.Globals.ERROR_KEY);

		if ((m != null) && !m.isEmpty()) {
			ActionMessages errors = errorCollection();

			// chek if this is already our own message
			// collection instance
			if (errors != m) {
				errors.add(m);

				// remove the messages from the struts error
				// collection. Otherwise the messages will be
				// duplicated by a forward request
				m.clear();
			}
		}

		m =
			(ActionMessages) request().getAttribute(
				org.apache.struts.Globals.MESSAGE_KEY);

		if ((m != null) && !m.isEmpty()) {
			ActionMessages messages = messageCollection();

			// chek if this is already our own message
			// collection instance
			if (messages != m) {
				messages.add(m);
				m.clear();
			}
		}
	}

	/**
	 * Add an new Action error to the Error Collection.
	 *
	 * @param		property Property Name
	 * @param		error The Error
	 */
	public void addError(String property, ActionMessage error) {
		errorCollection().add(property, error);
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#addErrors(ActionMessages)
	 */
	public void addErrors(ActionMessages errorList) {
		errorCollection().add(errorList);
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#addError(String, Throwable)
	 */
	public void addError(String key, java.lang.Throwable t) {
		addError(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(key, t.getMessage()));
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#addGlobalError(String)
	 */
	public void addGlobalError(String key) {
		addError(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(key));
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#addGlobalError(String, Object)
	 */
	public void addGlobalError(String key, Object p1) {
		addError(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(key, p1));
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#addGlobalError(String, Object, Object)
	 */
	public void addGlobalError(String key, Object p1, Object p2) {
		addError(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(key, p1, p2));
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#addGlobalError(String, Object, Object, Object)
	 */
	public void addGlobalError(String key, Object p1, Object p2, Object p3) {
		addError(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(key, p1, p2, p3));
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#addGlobalError(String, Object, Object, Object, Object)
	 */
	public void addGlobalError(String key, Object p1, Object p2, Object p3, Object p4) {
		addError(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(key, p1, p2, p3, p4));
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#addPropertyError(String, String)
	 */
	public void addPropertyError(String property, String key) {
		addError(property, new ActionMessage(key));
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#addPropertyError(String, String, Object)
	 */
	public void addPropertyError(String property, String key, Object p1) {
		addError(property, new ActionMessage(key, p1));
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#addPropertyError(String, String, Object, Object)
	 */
	public void addPropertyError(String property, String key, Object p1, Object p2) {
		addError(property, new ActionMessage(key, p1, p2));
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#addPropertyError(String, String, Object, Object, Object)
	 */
	public void addPropertyError(String property, String key, Object p1, Object p2, Object p3) {
		addError(property, new ActionMessage(key, p1, p2, p3));
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#addPropertyError(String, String, Object, Object, Object, Object)
	 */
	public void addPropertyError(String property, String key, Object p1, Object p2, Object p3, Object p4) {
		addError(property, new ActionMessage(key, p1, p2, p3, p4));
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#hasErrors()
	 */
	public boolean hasErrors() {
		return !((errors == null) || errors.isEmpty());
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#getMessages()
	 */
	public ActionMessages getMessages() {
		HttpSession session = session();

		if (session == null) {
			return null;
		} else {
			return (ActionMessages) session.getAttribute(Globals.MESSAGES_SAVE_KEY);
		}
	}

	/**
	 * Method addMessage
	 * @param	property	Property
	 * @param	message		ActionMessage
	 */
	public void addMessage(String property, ActionMessage message) {
		messageCollection().add(property, message);
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#addMessages(org.apache.struts.action.ActionMessages)
	 */
	public void addMessages(ActionMessages messageList) {
		messageCollection().add(messageList);
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#addGlobalMessage(String)
	 */
	public void addGlobalMessage(String key) {
		addMessage(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(key));
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#addGlobalMessage(String, Object)
	 */
	public void addGlobalMessage(String key, Object p1) {
		addMessage(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(key, p1));
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#addGlobalMessage(String, Object, Object)
	 */
	public void addGlobalMessage(String key, Object p1, Object p2) {
		addMessage(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(key, p1, p2));
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#addGlobalMessage(String, Object, Object, Object)
	 */
	public void addGlobalMessage(String key, Object p1, Object p2, Object p3) {
		addMessage(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(key, p1, p2, p3));
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#addGlobalMessage(String, Object, Object, Object, Object)
	 */
	public void addGlobalMessage(String key, Object p1, Object p2, Object p3, Object p4) {
		addMessage(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(key, p1, p2, p3, p4));
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#addPropertyMessage(String, String)
	 */
	public void addPropertyMessage(String property, String key) {
		addMessage(property, new ActionMessage(key));
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#addPropertyMessage(String, String, Object)
	 */
	public void addPropertyMessage(String property, String key, Object p1) {
		addMessage(property, new ActionMessage(key, p1));
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#addPropertyMessage(String, String, Object, Object)
	 */
	public void addPropertyMessage(String property, String key, Object p1, Object p2) {
		addMessage(property, new ActionMessage(key, p1, p2));
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#addPropertyMessage(String, String, Object, Object, Object)
	 */
	public void addPropertyMessage(String property, String key, Object p1, Object p2, Object p3) {
		addMessage(property, new ActionMessage(key, p1, p2, p3));
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#addPropertyMessage(String, String, Object, Object, Object, Object)
	 */
	public void addPropertyMessage(String property, String key, Object p1, Object p2, Object p3, Object p4) {
		addMessage(property, new ActionMessage(key, p1, p2, p3, p4));
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#hasMessages()
	 */
	public boolean hasMessages() {
		return hasErrors() || (!((messages == null) || messages.isEmpty()));
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#getPrincipal()
	 */
	public Principal getPrincipal() {
		return SecurityUtil.getPrincipal(request);
	}

	/**
	 * @see com.cc.framework.adapter.RequestContext#getLocale()
	 */
	public Locale getLocale() {
		Locale locale = (Locale) session().getAttribute(org.apache.struts.Globals.LOCALE_KEY);

		if (locale == null) {
			locale	= Locale.getDefault();
		}

		return locale;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "ctx";
	}
}