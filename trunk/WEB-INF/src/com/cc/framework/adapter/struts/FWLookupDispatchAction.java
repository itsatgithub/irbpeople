/**
 * $Header: d:/repository/cvs/cc-adapter-struts-1.2/adapter/com/cc/framework/adapter/struts/FWLookupDispatchAction.java,v 1.4 2005/07/04 14:24:16 P001002 Exp $
 * $Revision: 1.4 $
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import com.cc.framework.dialog.DialogContext;
import com.cc.framework.ui.control.Control;
import com.cc.framework.ui.control.ControlMethodInvocation;
import com.cc.framework.ui.control.ControlRequestContext;
import com.cc.framework.ui.control.ControlValuePath;
import com.cc.framework.ui.control.MenuContext;

/**
 * CC-Framework Baseclass for LookupDispatchAction
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.4 $
 */
public abstract class FWLookupDispatchAction extends LookupDispatchAction implements FrameworkAction {

	/**
	 * Commons Logging instance.
	 */
	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * Constructor
	 */
	public FWLookupDispatchAction() {
		super();
	}

	/**
	 * @see com.cc.framework.adapter.struts.FrameworkAction#cleanup(com.cc.framework.adapter.struts.ActionContext)
	 */
	public void cleanup(ActionContext ctx) {
		ActionUtil.removeFormBean(this, ctx);
	}

	/**
	 * @see com.cc.framework.adapter.struts.FrameworkAction#getControlByName(com.cc.framework.adapter.struts.ActionContext, java.lang.String)
	 */
	public Control getControlByName(ActionContext ctx, String name)
		throws Exception {
		return ActionUtil.getControlByName(this, ctx, name);
	}

	/**
	 * @see com.cc.framework.adapter.struts.FrameworkAction#applyControlStates(com.cc.framework.adapter.struts.ActionContext)
	 */
	public void applyControlStates(ActionContext ctx) throws Exception {
		ActionUtil.applyControlStates(this, ctx);
	}

	/**
	 * @see com.cc.framework.adapter.struts.FrameworkAction#handleControlAction(com.cc.framework.adapter.struts.ActionContext, com.cc.framework.ui.control.ControlMethodInvocation)
	 */
	public ActionContext handleControlAction(
		ActionContext ctx,
		ControlMethodInvocation cmi)
		throws Exception {
		return ActionUtil.handleControlAction(this, ctx, cmi);
	}

	/**
	 * @see com.cc.framework.adapter.struts.FrameworkAction#handleControlAction(com.cc.framework.ui.control.ControlRequestContext, java.lang.Object[])
	 */
	public void handleControlAction(ControlRequestContext ctx, Object params[])
		throws Exception {

		try {
			ActionUtil.handleControlAction(this, ctx, params);
		} catch (Exception e) {
			log.error(e);
			throw e;
		}
	}

	/**
	 * @see com.cc.framework.adapter.struts.FrameworkAction#getActionHandler(com.cc.framework.ui.control.ControlRequestContext, java.lang.Object[])
	 */
	public Method getActionHandler(
		ControlRequestContext ctx,
		Object params[]) {
		return ActionUtil.getActionHandler(this, ctx, params);
	}

	/**
	 * @see com.cc.framework.adapter.struts.FrameworkAction#setControlValue(com.cc.framework.adapter.struts.ActionContext, com.cc.framework.ui.control.ControlValuePath, java.lang.String[])
	 */
	public void setControlValue(
		ActionContext ctx,
		ControlValuePath path,
		String[] values)
		throws Exception {

		try {
			ActionUtil.setControlValue(this, ctx, path, values);
		} catch (Exception e) {
			log.error(e);
			throw e;
		}
	}

	/**
	 * @see com.cc.framework.adapter.struts.FrameworkAction#setControlValuesFromRequest(com.cc.framework.adapter.struts.ActionContext)
	 */
	public void setControlValuesFromRequest(ActionContext ctx)
		throws Exception {
		ActionUtil.setControlValuesFromRequest(this, ctx);
	}

	/**
	 * @see com.cc.framework.adapter.struts.FrameworkAction#handleFormAction(com.cc.framework.adapter.struts.ActionContext)
	 */
	public ActionContext handleFormAction(ActionContext ctx) throws Exception {
		try {
			return ActionUtil.handleFormAction(this, ctx);
		} catch (Exception e) {
			log.warn(e);
			throw e;
		}
	}

	/**
	 * Returns or creates a dialog context
	 *
	 * @param	ctx		The ActionContext
	 * @param	create	Indicates if an existing context should be returned (false); otherwise a new context is started (true)
	 * @return	DialogContext
	 */
	public DialogContext getDialogContext(ActionContext ctx, boolean create) {
		return null;
	}

	// ================================
	// Template Methods
	// ================================

	/**
	 * @see com.cc.framework.adapter.struts.FrameworkAction#doPreExecute(com.cc.framework.adapter.struts.ActionContext)
	 */
	public boolean doPreExecute(ActionContext ctx) throws Exception {
		// execute() -> go on
		return true;
	}

	/**
	 * @see com.cc.framework.adapter.struts.FrameworkAction#doPostExecute(com.cc.framework.adapter.struts.ActionContext)
	 */
	public void doPostExecute(ActionContext ctx) throws Exception {
		// no action in the base class
	}

	/**
	 * @see com.cc.framework.adapter.struts.FrameworkAction#doSetMenuContext(com.cc.framework.adapter.struts.ActionContext, com.cc.framework.ui.control.MenuContext)
	 */
	public void doSetMenuContext(ActionContext ctx, MenuContext menuCtx)
		throws Exception {
		// no action in the base class
	}

	/**
	 * @see com.cc.framework.adapter.struts.FrameworkAction#createActionContext(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionContext createActionContext(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {

		return ActionUtil.createActionContext(
			this,
			mapping,
			form,
			request,
			response);
	}

	/**
	 * @see com.cc.framework.adapter.struts.FrameworkAction#doExecute(com.cc.framework.adapter.struts.ActionContext)
	 */
	public void doExecute(ActionContext ctx) throws Exception {
		// call the baseclass implementation of execute
		ctx.forward(
			super.execute(
				ctx.mapping(),
				ctx.form(),
				ctx.request(),
				ctx.response()));
	}

	/**
	 * @see com.cc.framework.adapter.struts.FrameworkAction#execute(com.cc.framework.adapter.struts.ActionContext)
	 */
	public void execute(ActionContext ctx) throws Exception {
		try {
			ActionUtil.execute(this, ctx);

			// for compatiblity with struts message collections
			// Move the struts messages to the action context
			// so they will survive redirects until the next jsp
			// page is displayed
			ctx.consumeStrutsMessages();

			// save an return messages
			saveErrors(ctx.request(), ctx.getErrors());
			saveMessages(ctx.request(), ctx.getMessages());
		} catch (Exception e) {
			log.warn(e);
			throw e;
		}
	}

	/**
	 * This method gets called when no FWRequestProcessor is used!
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionContext ctx = createActionContext(mapping, form, request, response);
		execute(ctx);
		return ctx.getForward();
	}
}
