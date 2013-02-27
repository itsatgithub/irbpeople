/*
 * $Header: d:/repository/cvs/cc-adapter-struts-1.2/adapter/com/cc/framework/adapter/struts/ActionContext.java,v 1.5 2005/02/16 20:00:05 P001001 Exp $
 * $Revision: 1.5 $
 * $Date: 2005/02/16 20:00:05 $
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

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.cc.framework.adapter.RequestContext;

/**
 * If an action is called, all relevant informations (like the
 * session, request, response object) are passed by a context object
 * which implements this interface.
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.5 $
 * @since      1.0
 */
public interface ActionContext extends RequestContext {

	/**
	 * Generates a standardized error message if no
	 * DialogContext exists.
	 *
	 * @param	messageKey	Message Key
	 */
	public void invalidDialogContext(String messageKey);

	/**
	 * Returns the action mapping object
	 *
	 * @return the action mapping
	 */
	public ActionMapping mapping();

	/**
	 * Returns the action form
	 *
	 * @return the action form
	 */
	public ActionForm form();

	/**
	 * Validates the action form and adds the error messages
	 * to the internal error collection
	 */
	public void validateForm();

	/**
	 * If an element (control, action class) has handled the request
	 * it can set this flag, so that the request is not proccessed a
	 * second time by an other element.
	 *
	 * @return boolean
	 */
	public boolean isHandeled();

	/**
	 * Gets the forward
	 * @return Returns a ActionForward
	 */
	public ActionForward getForward();

	/**
	 * Sets the forward
	 * @param forward The forward to set
	 */
	public void forward(ActionForward forward);

	/**
	 * The next Forward should be directly directed
	 * to the Response
	 */
	public void forwardToResponse();

	/**
	 * Forwards the Request to the Input,
	 * which is specified for the Action
	 * in the struts config.
	 */
	public void forwardToInput();

	/**
	 * Fowards to the specified Action
	 * @param	actionName	Action name
	 */
	public void forwardToAction(String actionName);

	/**
	 * Fowards to the Action specified by the Name
	 * @param	forwardName	Action name
	 */
	public void forwardByName(String forwardName);

	/**
	 * Fowards to the Action specified by the Name
	 * @param	forwardName	Action name
	 * @param	p1	Additional Parameter
	 */
	public void forwardByName(String forwardName, Object p1);

	/**
	 * Fowards to the Action specified by the Name
	 * @param	forwardName	Action name
	 * @param	p1	Additional Parameter
	 * @param	p2	Additional Parameter
	 */
	public void forwardByName(String forwardName, Object p1, Object p2);

	/**
	 * Fowards to the Action specified by the Name
	 * @param	forwardName	Action name
	 * @param	p1	Additional Parameter
	 * @param	p2	Additional Parameter
	 * @param	p3	Additional Parameter
	 */
	public void forwardByName(String forwardName, Object p1, Object p2, Object p3);


	/**
	 * Fowards to the Action specified by the Name
	 * @param	forwardName	Action name
	 * @param	params	Object array with additional parameter
	 */
	public void forwardByName(String forwardName, Object[] params);

	/**
	 * This method transfers all existing Struts errors and messages
	 * into the frameworks message store. The Struts messages and error
	 * collections will be cleared. Otherwise the messages will be
	 * duplicated by a forward request.
	 */
	public void consumeStrutsMessages();

	/**
	 * Gets the error collection
	 *
	 * @return	ActionErrors
	 */
	public ActionMessages getErrors();

	/**
	 * Adds an error collection
	 * @param	errorList	ActionErrors
	 */
	public void addErrors(ActionMessages errorList);

	/**
	 * Adds an message collection
	 * @param	messageList	ActionMessages
	 */
	public void addMessages(ActionMessages messageList);

	/**
	 * Serves to include an exception in the global error collection.
	 * Remark: The Framework, as against Struts, rescues the error and
	 * message text collection across several redirects till the next
	 * JSP page is called.
	 *
	 * @param	key	Key
	 * @param	t	Throwable
	 */
	public void addError(String key, java.lang.Throwable t);

	/**
	 * Saves a general error (without reference to an input field) and
	 * is used to present error messages upon returning from a
	 * sub-dialog within the calling dialog.
	 *
	 * @param	key		Key
	 */
	public void addGlobalError(String key);

	/**
	 * Saves a general error (without reference to an input field)
	 *
	 * @param	key		Key
	 * @param	p1		Object1
	 */
	public void addGlobalError(String key, Object p1);

	/**
	 * Saves a general error (without reference to an input field)
	 *
	 * @param	key		Key
	 * @param	p1		Object1
	 * @param	p2		Object2
	 */
	public void addGlobalError(String key, Object p1, Object p2);

	/**
	 * Saves a general error (without reference to an input field)
	 *
	 * @param	key		Key
	 * @param	p1		Object1
	 * @param	p2		Object2
	 * @param	p3		Object3
	 */
	public void addGlobalError(String key, Object p1, Object p2, Object p3);

	/**
	 * Saves a general error (without reference to an input field)
	 *
	 * @param	key		Key
	 * @param	p1		Object1
	 * @param	p2		Object2
	 * @param	p3		Object3
	 * @param	p4		Object4
	 */
	public void addGlobalError(String key, Object p1, Object p2, Object p3, Object p4);

	/**
	 * Saves an error to a Property in the error collection
	 *
	 * @param	property	The property
	 * @param	key			The key
	 */
	public void addPropertyError(String property, String key);

	/**
	 * Saves an error to a Property in the error collection
	 *
	 * @param	property	The property
	 * @param	key			The key
	 * @param	p1		Additional Paramter
	 */
	public void addPropertyError(String property, String key, Object p1);

	/**
	 * Saves an error to a Property in the error collection
	 *
	 * @param	property	The property
	 * @param	key			The key
	 * @param	p1		Additional Paramter
	 * @param	p2		Additional Paramter
	 */
	public void addPropertyError(String property, String key, Object p1, Object p2);

	/**
	 * Saves an error to a Property in the error collection
	 *
	 * @param	property	The property
	 * @param	key			The key
	 * @param	p1		Additional Paramter
	 * @param	p2		Additional Paramter
	 * @param	p3		Additional Paramter
	 */
	public void addPropertyError(String property, String key, Object p1, Object p2, Object p3);

	/**
	 * Saves an error to a Property in the error collection
	 *
	 * @param	property	The property
	 * @param	key		The resource key
	 * @param	p1		Additional Paramter
	 * @param	p2		Additional Paramter
	 * @param	p3		Additional Paramter
	 * @param	p4		Additional Paramter
	 */
	public void addPropertyError(String property, String key, Object p1, Object p2, Object p3, Object p4);

	/**
	 * Checks whether there are error messages.
	 *
	 * @return	true if there are error messages, false otherwise
	 */
	public boolean hasErrors();

	/**
	 * Returns the message collection
	 * @return	ActionMessages
	 */
	public ActionMessages getMessages();

	/**
	 * Saves a message without reference to a Property.
	 * Is used to present messages upon returning from
	 * a sub-dialog within the calling dialog.
	 *
	 * @param	key		Message resource Key
	 */
	public void addGlobalMessage(String key);

	/**
	 * Saves a message without reference to a Property.
	 *
	 * @param	key		Message resource Key
	 * @param	p1		Additional Paramter
	 */
	public void addGlobalMessage(String key, Object p1);

	/**
	 * Saves a message without reference to a Property.
	 *
	 * @param	key		Message resource Key
	 * @param	p1		Additional Paramter
	 * @param	p2		Additional Paramter
	 */
	public void addGlobalMessage(String key, Object p1, Object p2);

	/**
	 * Saves a message without reference to a Property.
	 *
	 * @param	key		Message resource Key
	 * @param	p1		Additional Paramter
	 * @param	p2		Additional Paramter
	 * @param	p3		Additional Paramter
	 */
	public void addGlobalMessage(String key, Object p1, Object p2, Object p3);

	/**
	 * Saves a message without reference to a Property.
	 *
	 * @param	key		Message resource Key
	 * @param	p1		Additional Paramter
	 * @param	p2		Additional Paramter
	 * @param	p3		Additional Paramter
	 * @param	p4		Additional Paramter
	 */
	public void addGlobalMessage(String key, Object p1, Object p2, Object p3, Object p4);

	/**
	 * Saves a message for a Property in the Message collection
	 *
	 * @param	property	The property
	 * @param	key			Message resource Key
	 */
	public void addPropertyMessage(String property, String key);

	/**
	 * Saves a message for a Property in the Message collection
	 *
	 * @param	property	The property
	 * @param	key			Message resource Key
	 * @param	p1		Additional Paramter
	 */
	public void addPropertyMessage(String property, String key, Object p1);

	/**
	 * Saves a message for a Property in the Message collection
	 *
	 * @param	property	The property
	 * @param	key			Message resource Key
	 * @param	p1		Additional Paramter
	 * @param	p2		Additional Paramter
	 */
	public void addPropertyMessage(String property, String key, Object p1, Object p2);

	/**
	 * Saves a message for a Property in the Message collection
	 *
	 * @param	property	The property
	 * @param	key		Message resource Key
	 * @param	p1		Additional Paramter
	 * @param	p2		Additional Paramter
	 * @param	p3		Additional Paramter
	 */
	public void addPropertyMessage(String property, String key, Object p1, Object p2, Object p3);

	/**
	 * Saves a message for a Property in the Message collection
	 *
	 * @param	property	The property
	 * @param	key		Message resource Key
	 * @param	p1		Additional Paramter
	 * @param	p2		Additional Paramter
	 * @param	p3		Additional Paramter
	 * @param	p4		Additional Paramter
	 */
	public void addPropertyMessage(String property, String key, Object p1, Object p2, Object p3, Object p4);

	/**
	 * Checks whether there are messages present.
	 *
	 * @return	true if there are messages, false otherwise
	 */
	public boolean hasMessages();
}
