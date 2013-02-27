/**
 * $Header: d:/repository/cvs/cc-adapter-struts-1.2/adapter/com/cc/framework/adapter/struts/FrameworkAction.java,v 1.3 2005/07/04 14:24:16 P001002 Exp $
 * $Revision: 1.3 $
 * $Date: 2005/07/04 14:24:16 $
 *
 * ====================================================================
 *
 * Copyright (c) 2002 SCC Informationssysteme GmbH. All rights
 * reserved.
 *
 * ====================================================================
 */

package com.cc.framework.adapter.struts;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.cc.framework.dialog.DialogContext;
import com.cc.framework.ui.control.Control;
import com.cc.framework.ui.control.ControlMethodInvocation;
import com.cc.framework.ui.control.ControlRequestContext;
import com.cc.framework.ui.control.ControlValuePath;
import com.cc.framework.ui.control.MenuContext;

/**
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.3 $
 */
public interface FrameworkAction {

	/**
	 * This method do some cleanup after the action finished.
	 * The default implementation removes the form bean.
	 *
	 * @param	ctx		ActionContext
	 */
	public void cleanup(ActionContext ctx);

	/**
	 * Returns or creates a dialog context
	 *
	 * @param	ctx		The ActionContext
	 * @param	create	Indicates if an existing context should be returned (false); otherwise a new context is started (true)
	 * @return	DialogContext
	 */
	public DialogContext getDialogContext(ActionContext ctx, boolean create);

	/**
	 * This method is called to create the ActionContext wrapper. Overwrite
	 * this method to use your own implementation
	 *  
	 * @param		mapping Struts Action Mapping
	 * @param		form Struts Action Form
	 * @param		request HTTPServletRequest
	 * @param		response HTTPServletResponse
	 * @return		ActionContext whichis passed to the doExecute() method
	 */
	public ActionContext createActionContext(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response);

	/**
	 * The framework has a default pattern where to search a control instance.
	 * If the control can not be found by the implemented pattern, this
	 * methode can be overwritten to implement an additional or own processing.
	 * First the control will be searched in the session, then the control will
	 * be searched in the FormBean which is associated with the action.
	 *
	 * @param	ctx		The ActionContext
	 * @param	name	The name of the bean
	 * @return	Control
	 * @throws	Exception	If an exception occurred
	 */
	public Control getControlByName(ActionContext ctx, String name)
		throws Exception;

	/**
	 * Applies the serialized control state from the HTTPRequest to
	 * the corresponding control instances.
	 *
	 * @param	ctx	ActionContext
	 * @throws	Exception if an exception occurs
	 */
	public void applyControlStates(ActionContext ctx) throws Exception;

	/**
	 * Searches the action class for a handler method.
	 * The search order ist
	 * <ul>
	 *   <li>[control]_on[action]</li>
	 *   <li>on[action]</li>
	 * </ul>
	 * 
	 * @param		ctx the action context
	 * @param		params actual parameters
	 * @return		returns a handler method or <code>null</code>
	 * 				when no such method is defined
	 */
	public Method getActionHandler(ControlRequestContext ctx, Object params[]);

	/**
	 * Handles an action which comes from a control on the form
	 * an delegates the request to the control.
	 *
	 * @param	ctx	ActionContext
	 * @param	cmi	ControlMethodInvocation
	 * @return	ActionContext
	 * @throws	Exception if an exception occurs
	 */
	public ActionContext handleControlAction(
		ActionContext ctx,
		ControlMethodInvocation cmi)
		throws Exception;

	/**
	 * Handles an action which comes from a control on the form.
	 * If the subclass does not implement a special processing
	 * for this event, the event will be forwarded to the control.
	 * So the control can do some default processing.
	 * This default behavoir can be canged if an eventhandler in
	 * the subclass is implemented. This handler must follow the naming
	 * convention "controlname_onEventname(ControlRequestContext ctx, parameters ...)
	 * For example: users_onDrilldown(ControlRequestContext ctx, String key)
	 *
	 * Note: An other way to change the default behavoir of an existing control
	 * is to subclass the control an write an new control.
	 *
	 * @param	ctx		ControlRequestContext
	 * @param	params	Parameters
	 * @throws	Exception if an exception occurs
	 */
	public void handleControlAction(ControlRequestContext ctx, Object params[])
		throws Exception;

	/**
	 * This method is called to set a control element value
	 * 
	 * @param		ctx The ActionContext
	 * @param		path The Pathname of the value
	 * @param		values The value itselfe
	 * @throws		Exception is thrown when a value
	 * 				could not be set
	 */
	public void setControlValue(
		ActionContext ctx,
		ControlValuePath path,
		String[] values)
		throws Exception;

	/**
	 * This method is called to updates any control element data
	 * from request values. All nested control element values
	 * follow the naming convention documented in
	 * {@link com.cc.framework.ui.control.ControlValuePath}
	 * 
	 * @param		ctx The ActionContext
	 * @throws		Exception is thrown when a value
	 * 				could not be set
	 */
	public void setControlValuesFromRequest(ActionContext ctx)
		throws Exception;

	/**
	 * Checks if the request comes from a form button (save, cancel, ...)
	 * and delegates the request to the corresponding handler for the
	 * button which must be implemented in the (action) subclass.
	 * This handler must follow the naming convention "buttonname_onEventname(FormActionContext ctx)
	 * For example save_onClick(FormActionContext ctx)
	 *
	 * @param	ctx		The ActionContext
	 * @return	ActionContext
	 * @throws	Exception If an exception occurred
	 */
	public ActionContext handleFormAction(ActionContext ctx) throws Exception;

	/**
	 * This method is called instead the struts execute.
	 *
	 * @param	ctx	ActionContext
	 * @throws	Exception if an exception occurs
	 */
	public void execute(ActionContext ctx) throws Exception;

	// ===================
	// Template methods
	// ===================

	/**
	 * A template method. Can be overriden by a subclass
	 * and is executed bevor the doExecute method is called.
	 * The methode must return true if the doExecute method
	 * should be called.
	 *
	 * @param	ctx		The ActionContext
	 * @return	true if the doExecute() should be processed; false otherwise
	 * @throws	Exception If an exception occurred
	 */
	public boolean doPreExecute(ActionContext ctx) throws Exception;

	/**
	 * A template method. Can be overriden by a subclass
	 * and is executed after the doExecute method was called.
	 *
	 * @param	ctx		The ActionContext
	 * @throws	Exception	If an exception occurred
	 */
	public void doPostExecute(ActionContext ctx) throws Exception;

	/**
	 * A template method which must be overriden by the subclass.
	 * This method is called instead the struts execute method
	 * and if the event comes not from a form or control
	 *
	 * @param	ctx	ActionContext
	 * @throws	Exception if an exception occurs
	 */
	public void doExecute(ActionContext ctx) throws Exception;

	/**
	 * Sets the menu context. If used, the processing must
	 * be implemented in the subclass
	 *
	 * @param	ctx			The ActionContext
	 * @param	menuCtx		The MenuContext
	 * @throws	Exception If an exception occurred
	 */
	public void doSetMenuContext(ActionContext ctx, MenuContext menuCtx)
		throws Exception;
}