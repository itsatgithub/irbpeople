/*
 * $Header: d:/repository/cvs/cc-adapter-struts-1.2/adapter/com/cc/framework/adapter/struts/ActionUtil.java,v 1.16 2005/07/30 15:19:01 P001002 Exp $
 * $Revision: 1.16 $
 * $Date: 2005/07/30 15:19:01 $
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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.cc.framework.Globals;
import com.cc.framework.http.HttpScope;
import com.cc.framework.http.HttpUtil;
import com.cc.framework.http.RequestDecorator;
import com.cc.framework.ui.control.Control;
import com.cc.framework.ui.control.ControlActionContext;
import com.cc.framework.ui.control.ControlActionDef;
import com.cc.framework.ui.control.ControlMethodInvocation;
import com.cc.framework.ui.control.ControlRequestContext;
import com.cc.framework.ui.control.ControlValuePath;
import com.cc.framework.ui.control.MenuContext;
import com.cc.framework.ui.model.StateModel;
import com.cc.framework.util.PropertyMap;
import com.cc.framework.util.StringHelp;
import com.cc.framework.util.Util;

/**
 * Utility methods for Action derived classes
 *
 * @author     <a href="mailto:gschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version    $Revision: 1.16 $
 * @since      1.0
 * @see org.apache.struts.action.Action
 */
public abstract class ActionUtil {

	/** Error message */
	private static final String ERR_ACTION =
		"Error calling generic action handler reason [{0}]";

	/** Error message */
	private static final String ERR_ACTION_CTRL =
		"Error calling generic action handler for control [{0}] reason [{1}]";

	/** Error message */
	private static final String ERR_ACTION_HANDLER =
		"Error calling action handler [{0}] reason [{1}]";

	/** Error message */
	private static final String ERR_FORMACTION =
		"Error calling FormActionhandler [{0}] reason [{1}]";

	/** Error message */
	private static final String ERR_FIND_CONTROL =
		"Error while retrieving a control instance for identifier [{0}] "
		+ "from form bean [{1}]. The error was: [{2}]";

	/**
	 * Private Logger instance
	 */
	private static Log log = LogFactory.getLog(ActionUtil.class);

	/**
	 * Checks the given form for a valid property
	 * 
	 * @param		bean form bean
	 * @param		name property name
	 * @return		returns <code>true</code> if the proiperty
	 * 				is valid
	 */
	public static boolean isValidFormProperty(Object bean, String name) {
		if (bean instanceof DynaBean) {
			DynaBean dynaBean = (DynaBean) bean;

			int nameSplit = name.indexOf('.');
			
			if (nameSplit == -1) {
				// The controls name is a simple property
				return dynaBean.getDynaClass().getDynaProperty(name) != null;
			} else {
				String propertyName	= name.substring(nameSplit + 1);
				String beanName		= name.substring(0, nameSplit);

				if (dynaBean.getDynaClass().getDynaProperty(beanName) == null) {
					// The DynaForm has no such property
					return false;
				} else {
					bean = dynaBean.get(beanName);
					
					if (bean == null) {
						return false;
					} else {
						return Util.isValidProperty(bean, propertyName);
					}
				}
			}
		} else {
			return Util.isValidProperty(bean, name);
		}
	}

	/**
	 * Helper function that removes the form bean which
	 * can either be located in the user's session or the request scope.
	 *
	 * @param	action The Action instance
	 * @param	ctx		The ActionContext
	 */
	public static void removeFormBean(
		FrameworkAction action,
		ActionContext ctx) {

		// destroy the form which is assigned to the action
		ActionMapping mapping = ctx.mapping();

		if (mapping.getAttribute() != null) {
			if (HttpScope.REQUEST.equals(mapping.getScope())) {
				ctx.request().removeAttribute(mapping.getAttribute());
			} else {
				ctx.session().removeAttribute(mapping.getAttribute());
			}
		}
	}

	/**
	 * The framework has a default pattern where to search a control instance.
	 * If the control can not be found by the implemented pattern, this
	 * methode can be overwritten to implement an additional or own processing.
	 * First the control will be searched in the session, then the control will
	 * be searched in the FormBean which is associated with the action.
	 *
	 * @param	action The Action instance
	 * @param	ctx		The ActionContext
	 * @param	name	The name of the bean
	 * @return	Control
	 * @throws	Exception	If an exception occurred
	 */
	public static Control getControlByName(
		FrameworkAction action,
		ActionContext ctx,
		String name)
		throws Exception {

		if ((name == null) || "".equals(name)) {
			// A generic (unnamed) control action without
			// a control instance was triggered
			return null;
		} else {
			Control control = null;

			// Try to get the control out of the session
			String[] tokens = StringHelp.split(name, ".");
			if (tokens.length > 0) {
				Object bean = ctx.session().getAttribute(tokens[0]);

				for (int i = 1; (bean != null) && (i < tokens.length); i++) {
					if (Util.isValidProperty(bean, tokens[i])) {
						bean = Util.getProperty(bean, tokens[i]);
					}
				}

				if (bean instanceof Control) {
					control = (Control) bean;
				}
			}

			// If the control cannot be found in the session,
			// we search in the FormBean.
			if ((control == null) && (ctx.form() != null)) {
				try {
					if (isValidFormProperty(ctx.form(), name)) {
						Object bean = Util.getProperty(ctx.form(), name);
	
						if (bean instanceof Control) {
							control = (Control) bean;
						}
					}
				} catch (Exception e) {
					throw new Exception(
						MessageFormat.format(
							ERR_FIND_CONTROL,
							new Object[] { name, ctx.form(), e.getMessage()}));
				}
			}

			return control;
		}
	}

	/**
	 * Handles an action which comes from a control on the form
	 * an delegates the request to the control.
	 *
	 * @param	action The Action instance
	 * @param	ctx	ActionContext
	 * @param	cmi	ControlMethodInvocation
	 * @return	ActionContext
	 * @throws	Exception if an exception occurs
	 */
	public static ActionContext handleControlAction(
		FrameworkAction action,
		ActionContext ctx,
		ControlMethodInvocation cmi)
		throws Exception {

		if (cmi != null) {
			if (cmi.getAction() != null) {
				ControlActionDef cad = ControlActionDef.parse(cmi.getAction());

				if (cad != null) {
					Control control = action.getControlByName(ctx, cmi.getControlName());

					// create an action wrapper
					ctx =
						new ConcreteControlActionContext(
							ctx,
							control,
							cmi.getControlName(),
							cad);

					Object param[] =
						cad.mapActualParameter((ControlRequestContext) ctx, cmi.getParams());

					action.handleControlAction((ControlRequestContext) ctx, param);
				}
			}

			if (ctx.isHandeled()) {
				return ctx;
			}

			ctx.forwardToInput();
		}

		return ctx;
	}

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
	 * @param	action The Action instance
	 * @param	ctx		ControlRequestContext
	 * @param	params	Parameters
	 * @throws	Exception if an exception occurs
	 */
	public static void handleControlAction(
		FrameworkAction action,
		ControlRequestContext ctx,
		Object params[])
		throws Exception {

		// register the principal object
		if (ctx.control() != null) {
			ctx.control().setPrincipal(ctx.getPrincipal());
		}

		Method handler = null;

		try {
			// get the action handler for <<control>>_on<<Action>>
			handler = action.getActionHandler(ctx, params);

			if (handler == null) {
				// If the sublcass does not handle the controls event
				// it is handled by default by the control.
				// So delegate the processing to the control
				if (ctx.control() != null) {
					ctx.control().execute(params);
				}
			} else {
				// call the handler method
				handler.invoke(action, params);
			}
		} catch (InvocationTargetException ite) {
			Throwable t = ite.getTargetException();

			logControlAction(ctx.control(), handler, t);

			// rethrow the exception
			if (t instanceof Exception) {
				throw (Exception) t;
			} else {
				throw ite;
			}
		} catch (Exception e) {
			logControlAction(ctx.control(), handler, e);

			// rethrow the exception
			throw e;
		}
	}

	/**
	 * This method logs any exeptions that have occured during
	 * the execution of a control event handler
	 *
	 * @param		ctrl The control instance
	 * @param		handler The Action handler
	 * @param		t The exception
	 */
	protected static void logControlAction(Control ctrl, Method handler, Throwable t) {
		String msg;

		if (handler == null) {
			if (ctrl == null) {
				msg =
					MessageFormat.format(
						ERR_ACTION,
						new Object[] { t.getMessage()});
			} else {
				msg =
					MessageFormat.format(
						ERR_ACTION_CTRL,
						new Object[] { ctrl.getControlName(), t.getMessage()});
			}
		} else {
			msg =
				MessageFormat.format(
					ERR_ACTION_HANDLER,
					new Object[] { handler.getName(), t.getMessage()});
		}

		log.error(msg, t);
	}

	/**
	 * Searches the action class for a handler method.
	 * The search order ist
	 * <ul>
	 *   <li>[control]_on[action]</li>
	 *   <li>on[action]</li>
	 * </ul>
	 *
	 * @param	action The Action instance
	 * @param	ctx the action context
	 * @param	params actual parameters
	 * @return	returns a handler method or <code>null</code>
	 * 			when no such method is defined
	 */
	public static Method getActionHandler(
		FrameworkAction action,
		ControlRequestContext ctx,
		Object params[]) {
		Method handler = null;

		String method = ctx.getActionMethod();
		Class types[] = ctx.action().getFormalParameterTypes();

		// Pass in a ControlActionContext as first parameter!
		types[0] = ControlActionContext.class;

		// 1. get the named action handler for [control]_on[action]
		try {
			handler = action.getClass().getMethod(method, types);
		} catch (NoSuchMethodException nsme) {
			// ignore
		}

		// 2. get the generic action handler for on[action]
		if (handler == null) {
			String generic = StringHelp.strcat("on", ctx.action().getName());

			if (!generic.equals(method)) {
				try {
					handler = action.getClass().getMethod(generic, types);
				} catch (NoSuchMethodException nsme) {
					// ignore
				}
			}
		}

		return handler;
	}

	/**
	 * This method is called to set a control element value
	 *
	 * @param	action The Action instance
	 * @param	ctx The ActionContext
	 * @param	path The Pathname of the value
	 * @param	value The value itselfe
	 * @throws	Exception is thrown when a value
	 * 			could not be set
	 */
	public static void setControlValue(
		FrameworkAction action,
		ActionContext ctx,
		ControlValuePath path,
		String[] value)
		throws Exception {

		try {
			Control control = action.getControlByName(ctx, path.getControl());

			if (control != null) {
				control.setValue(ctx, path, value);
			}
		} catch (Exception e) {
			StringBuffer msg = new StringBuffer()
				.append("Error while setting control value. reason: ")
				.append(e.getMessage());

			log.error(msg);

			// rethrow the exception
			throw e;
		}
	}

	/**
	 * This method is called to updates any control element data
	 * from request values. All nested control element values
	 * follow the naming convention documented in
	 * {@link com.cc.framework.ui.control.ControlValuePath}
	 *
	 * @param	action The Action instance
	 * @param		ctx The ActionContext
	 * @throws		Exception is thrown when a value
	 * 				could not be set
	 */
	public static void setControlValuesFromRequest(
		FrameworkAction action,
		ActionContext ctx)
		throws Exception {

		Enumeration e = ctx.request().getParameterNames();

		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();

			ControlValuePath path = ControlValuePath.parse(key);

			if (path != null) {
				// This request parameter belongs to a control element.
				String[] value = ctx.request().getParameterValues(key);

				action.setControlValue(ctx, path, value);
			}
		}
	}

	/**
	 * Checks if the request comes from a form button (save, cancel, ...)
	 * and delegates the request to the corresponding handler for the
	 * button which must be implemented in the (action) subclass.
	 * This handler must follow the naming convention "buttonname_onEventname(FormActionContext ctx)
	 * For example save_onClick(FormActionContext ctx)
	 *
	 * @param	action The Action instance
	 * @param	ctx The ActionContext
	 * @return	ActionContext
	 * @throws	Exception If an exception occurred
	 */
	public static ActionContext handleFormAction(
		FrameworkAction action,
		ActionContext ctx)
		throws Exception {
		// First update any control values from the request
		action.setControlValuesFromRequest(ctx);

		// Perform control actions of form elements
		ctx = action.handleControlAction(ctx, ControlMethodInvocation.getFromForm(ctx));

		// get the button which submited the form
		String formActionCmd = FormActionCommand.parseFormAction(ctx);

		if (formActionCmd != null) {
			// create a form action wrapper
			ctx = new ConcreteFormActionContext(ctx, formActionCmd);

			Class[] formalparam = { FormActionContext.class };
			Object[] actualparam = {(FormActionContext) ctx };

			// call the method for the handler of the button in the subclass
			try {
				Method method = action.getClass().getMethod(formActionCmd, formalparam);

				method.invoke(action, actualparam);
			} catch (NoSuchMethodException nsme) {
				// There is no handler in the action class
			} catch (InvocationTargetException ite) {
				Throwable t = ite.getTargetException();

				log.error(
					MessageFormat.format(
						ERR_FORMACTION,
						new Object[] { formActionCmd, t.getMessage()}), t);

				if (t instanceof Exception) {
					throw (Exception) t;
				} else {
					throw ite;
				}
			} catch (Exception e) {
				log.error(
					MessageFormat.format(
						ERR_FORMACTION,
						new Object[] { formActionCmd, e.getMessage()}), e);

				throw e;
			}
		}

		return ctx;
	}

	/**
	 * This method is called to create the ActionContext wrapper. Overwrite
	 * this method to use your own implementation
	 *
	 * @param	action The Action instance
	 * @param	mapping Struts Action Mapping
	 * @param	form Struts Action Form
	 * @param	request HTTPServletRequest
	 * @param	response HTTPServletResponse
	 * @return	ActionContext whichis passed to the doExecute() method
	 */
	public static ActionContext createActionContext(
		FrameworkAction action,
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {

		return new ConcreteActionContext(mapping, form, request, response);
	}

	/**
	 * Applies the serialized control state from the HTTPRequest to
	 * the corresponding control instances.
	 *
	 * @param	action The Action instance
	 * @param	ctx	ActionContext
	 * @throws	Exception if an exception occurs
	 */
	public static void applyControlStates(FrameworkAction action, ActionContext ctx)
		throws Exception {

		String[] states = ctx.request().getParameterValues(Globals.STATE_PARAM);

		if (states != null) {
			// Synchronize all control states
			for (int i = 0; i < states.length; i++) {
				PropertyMap props = PropertyMap.parse(states[i]);

				Control control = action.getControlByName(ctx, props.getProperty(StateModel.PROP_CONTROL));

				if (control != null) {
					// Apply the controls state
					control.synchronizeState(ctx, props);
				}
			}
		}
	}

	/**
	 * Process the specified HTTP request, and create the corresponding HTTP
	 * response (or forward to another web component that will create it).
	 * Return an <code>ActionForward</code> instance describing where and how
	 * control should be forwarded, or <code>null</code> if the response has
	 * already been completed.
	 *
	 * @param	action The Action instance
	 * @param	ctx The ActionContext
	 * @throws	Exception if an exception occurred
	 */
	public static void execute(
		FrameworkAction action,
		ActionContext ctx)
		throws Exception {

		// Prüfen ob dieser Request bereits von einer vorangegangenen
		// Action mit einem Ticket versehen worden ist. In diesem Fall darf
		// die Kontrollelement Aktion nicht nochmals durchgeführt werden
		boolean requestSeen = HttpUtil.markRequest(ctx.request(), Globals.REQUEST_SEEN, null) != null;

		if (action.doPreExecute(ctx)) {
			// Parse hidden Fields
			RequestDecorator.parseRequest(ctx.request());

			// apply any control states from the request
			action.applyControlStates(ctx);

			if (!requestSeen) {
				// first controls can handle the request
				ctx = action.handleControlAction(ctx, ControlMethodInvocation.getFromHyperlink(ctx));
			}

			if (!ctx.isHandeled()) {

				// handle events which comes from the form
				if (!requestSeen) {
					ctx = action.handleFormAction(ctx);
				}

				if (!ctx.isHandeled()) {
					// if no event was processed at this time we
					// call the doExecute method in the subclass.
					// to handle the event.
					action.doExecute(ctx);
				}
			}

			// set the menu context
			action.doSetMenuContext(ctx, MenuContext.getContext(ctx.request()));

			// Post execute Hook for subclasses
			action.doPostExecute(ctx);
		}
	}
}