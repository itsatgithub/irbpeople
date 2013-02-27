/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/control/Control.java,v 1.51 2005/09/27 14:06:22 P001002 Exp $
 * $Revision: 1.51 $
 * $Date: 2005/09/27 14:06:22 $
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

package com.cc.framework.ui.control;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cc.framework.adapter.RequestContext;
import com.cc.framework.security.Permission;
import com.cc.framework.security.Principal;
import com.cc.framework.ui.RunAt;
import com.cc.framework.ui.model.AccessControlled;
import com.cc.framework.ui.model.ClientEvent;
import com.cc.framework.ui.model.ClientHandler;
import com.cc.framework.ui.model.ControlDesignModel;
import com.cc.framework.ui.model.DataModel;
import com.cc.framework.ui.model.DesignRule;
import com.cc.framework.ui.model.StateModel;
import com.cc.framework.util.PropertyMap;
import com.cc.framework.util.StringHelp;

/**
 * Base class for controls
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision: 1.51 $
 * @since 1.0
 */
public abstract class Control implements AccessControlled, ClientHandler,
		Serializable {

	/** Error message */
	private static final String ERR_ACTION = "Error calling control action handler [{0}] reason [{1}]";

	/** Error Message */
	private static final String ERR_MISSING_CTX = "the first parameter must be the ControlRequestContext!";

	/**
	 * Commons Logging instance.
	 */
	protected transient Log log = LogFactory.getLog(this.getClass());

	/**
	 * The principal objekt
	 */
	private transient Principal principal = null;

	/**
	 * Collection of optional Buttons that should be displayed next to the
	 * Control
	 * <p>
	 * Collection&lt;Control&gt;
	 */
	private Collection buttons = null;

	// ------------------
	// methods
	// ------------------

	/**
	 * Constructor
	 */
	public Control() {
		super();
	}

	/**
	 * Read the non-static and non-transient fields of the current class from
	 * this stream. All transient members are initialized.
	 * 
	 * @param in
	 *            the input stream
	 * @throws IOException
	 *             if an I/O error occurs.
	 * @throws ClassNotFoundException
	 *             if the class of a serialized object could not be found.
	 */
	private void readObject(ObjectInputStream in) throws IOException,
			ClassNotFoundException {
		in.defaultReadObject();

		// Create transient logging instance
		log = LogFactory.getLog(this.getClass());
	}

	/**
	 * Returns the Control Id
	 * 
	 * @return Control Id
	 */
	public String getId() {
		return getDesignModel().getId();
	}

	/**
	 * Returns the Control Name
	 * 
	 * @return Control Name
	 */
	public String getName() {
		return getDesignModel().getName();
	}

	/**
	 * Checks if the framework should include a transaction token (if any) in
	 * all generated hyperlinks. The Transaction token is used to track form
	 * re-submissions.
	 * 
	 * @return <code>true</code> if the transaction token should be generated
	 */
	public boolean getTransaction() {
		return getDesignModel().getTransaction();
	}

	/**
	 * Returns the design model of the control
	 * 
	 * @return The ControlDesignModel
	 */
	public abstract ControlDesignModel getDesignModel();

	/**
	 * Returns the data model of the Control
	 * 
	 * @return The data model
	 */
	public abstract DataModel getDataModel();

	/**
	 * Returns the state model of the control
	 * 
	 * @return The state model
	 */
	public abstract StateModel getStateModel();

	/**
	 * Checks if the control should act like a form element. In this case all
	 * action on the control must result in a submition of the enclosing form.
	 * This means that the data which is required to detected the actual action
	 * on the control (drilldown, edit, ...) must be sumit as form data and can
	 * not attached to the url.
	 * 
	 * @return <code>true</code> if the control acts like a form element.
	 */
	public boolean isFormElement() {
		return getDesignModel().isFormElement();
	}

	/**
	 * Returns the action which is assigned to the control
	 * 
	 * @return String
	 */
	public String getAction() {
		return getDesignModel().getAction();
	}

	/**
	 * Returns the width of the control
	 * 
	 * @return String
	 */
	public String getWidth() {
		return getDesignModel().getWidth();
	}

	/**
	 * Returns the height of the control
	 * 
	 * @return String
	 */
	public String getHeight() {
		return getDesignModel().getHeight();
	}

	/**
	 * Returns the Propety Attribute
	 * 
	 * @return String
	 */
	public String getProperty() {
		return getDesignModel().getProperty();
	}

	/**
	 * Returns the name of the control
	 * 
	 * @return String
	 */
	public String getControlName() {
		String name = getDesignModel().getName();
		String prop = getDesignModel().getProperty();

		if (name == null) {
			return prop;
		} else if (prop == null) {
			return name;
		} else {
			return StringHelp.join(name, prop, '.');
		}
	}

	/**
	 * Returns if the control works with or without serverroundtrips.
	 * 
	 * @return RunAt
	 */
	public RunAt getRunAt() {
		return getDesignModel().getRunAt();
	}

	/**
	 * Returns the Style
	 * 
	 * @return String
	 */
	public String getStyle() {
		return getDesignModel().getStyle();
	}

	/**
	 * Returns the Help id that is associated with this control element. If a
	 * control has a help id a little icon with a link to the help system will
	 * be created
	 * 
	 * @return String returns the Help id of the element. The semantic of this
	 *         id is completely in the responsibility of the applications help
	 *         system
	 */
	public String getHelp() {
		return getDesignModel().getHelp();
	}

	/**
	 * Retrieves the optional tooltip text
	 * 
	 * @return tooltip text or <code>null</code>
	 */
	public String getTooltip() {
		return getDesignModel().getTooltip();
	}

	/**
	 * Retrieves the property that can be used for rendering to non-visual media
	 * such as speech or Braille
	 * 
	 * @return receives a description and/or structure of the object or
	 *         <code>null</code>
	 */
	public String getSummary() {
		return getDesignModel().getSummary();
	}

	/**
	 * Retrieves the optional tooltip text
	 * 
	 * @return tooltip text or <code>null</code>
	 */
	public int getTabIndex() {
		return getDesignModel().getTabIndex();
	}

	/**
	 * returns the StyleId
	 * 
	 * @return string
	 */
	public String getStyleId() {
		return getDesignModel().getStyleId();
	}

	/**
	 * Returns the StyleClass
	 * 
	 * @return String
	 */
	public String getStyleClass() {
		return getDesignModel().getStyleClass();
	}

	/**
	 * Returns the disabled Flag
	 * 
	 * @return boolean
	 */
	public boolean isDisabled() {
		return getDesignModel().isDisabled();
	}

	/**
	 * @see com.cc.framework.ui.model.ClientHandler#setHandler(com.cc.framework.ui.model.ClientEvent,
	 *      java.lang.String)
	 */
	public void setHandler(ClientEvent event, String handler) {
		getDesignModel().setHandler(event, handler);
	}

	/**
	 * Returns the script handler which was associated for a client event
	 * 
	 * @param event
	 *            The event (like onClick, onMouseOver, ...)
	 * @return String The script handler oder <code>null</code> if no handler
	 *         was associated.
	 */
	public String getHandler(ClientEvent event) {
		return getDesignModel().getHandler(event);
	}

	/**
	 * Returns the list of events for which client handler have been registered.
	 * 
	 * @return the liste of events
	 */
	public ClientEvent[] getHandlers() {
		return getDesignModel().getHandlers();
	}

	/**
	 * This Methode is called by the Painter before rendering a Button. By
	 * overwriting this method a derived class can show or hide buttons.
	 * 
	 * @param button
	 *            ControlButton
	 * @return boolean
	 */
	public boolean showButton(ControlButton button) {
		// The Base Class allows the rendering of all Buttons
		return true;
	}

	/**
	 * This method gets called whenever a string resource identifier needs to be
	 * resolved. The base class implementation will return <code>null</code>.
	 * 
	 * @param resourceId
	 *            the resource identifier
	 * @param arguments
	 *            array with arguments for text markups
	 * @param locale
	 *            the locale
	 * @return localized string resource or <code>null</code> to let the
	 *         framework resolve the resource identifier
	 */
	public String getFrameworkString(String resourceId, Object[] arguments,
			Locale locale) {
		// Let the framework handle the resource id
		return null;
	}

	/**
	 * Returns the principal object
	 * 
	 * @return The principal object
	 */
	public Principal getPrincipal() {
		return principal;
	}

	/**
	 * Sets the principal object
	 * 
	 * @param principal
	 *            The principal object
	 */
	public void setPrincipal(Principal principal) {
		this.principal = principal;
	}

	/**
	 * Gets the Local Setting for this Control
	 * 
	 * @return Locale Setting
	 */
	public String getLocaleName() {
		return getDesignModel().getLocaleName();
	}

	/**
	 * Returns the design rules for this control
	 * 
	 * @return Array of design rules
	 */
	public DesignRule[] getDesignRules() {
		return getDesignModel().getDesignRules();
	}

	/**
	 * @see com.cc.framework.ui.model.AccessControlled#getPermission()
	 */
	public Permission getPermission() {
		return getDesignModel().getPermission();
	}

	/**
	 * @see com.cc.framework.ui.model.AccessControlled#show(com.cc.framework.security.Principal)
	 */
	public boolean show(Principal principal) {
		return getDesignModel().show(principal);
	}

	/**
	 * Adds a tool button to the Text Element
	 * 
	 * @param button
	 *            The Button zo add
	 */
	public void addButton(Control button) {
		if (button == null) {
			return;
		}

		if (buttons == null) {
			buttons = new Vector();
		}

		buttons.add(button);
	}

	/**
	 * Retrieves a list of button that are assigned to this Filed
	 * 
	 * @return Collection&lt;ButtonControl&gt; or <code>null</code>
	 */
	public Collection getButtons() {
		return buttons;
	}

	// ----------------------------
	// State Synchronization
	// ----------------------------

	/**
	 * This method applies the given state properties to the controls state
	 * model
	 * 
	 * @param properties
	 *            State properties
	 * @throws Exception
	 *             is thrown when an error occurs during execution
	 */
	public void synchronizeState(PropertyMap properties) throws Exception {
		if (getStateModel() != this) {
			getStateModel().synchronizeState(properties);
		}
	}

	/**
	 * This method applies the given state properties to the controls state
	 * model
	 * 
	 * @param ctx
	 *            RequestContext
	 * @param properties
	 *            State properties
	 * @throws Exception
	 *             is thrown when an error occurs during execution
	 */
	public void synchronizeState(RequestContext ctx, PropertyMap properties)
			throws Exception {
		synchronizeState(properties);
	}

	/**
	 * This method is called to set the value of the datamodel
	 * 
	 * @param ctx
	 *            The Action Context
	 * @param path
	 *            Path Name of the input element
	 * @param values
	 *            the values to set
	 * @throws Exception
	 *             a derived class can throw an exception if the value could not
	 *             be set
	 */
	public void setValue(RequestContext ctx, ControlValuePath path,
			String[] values) throws Exception {
		// No functionality in the base class
		StringBuffer buf = new StringBuffer().append("setValue({").append(path)
				.append("}, [");

		// Value array
		for (int i = 0; i < values.length; i++) {
			if (i > 0) {
				buf.append(", ");
			}

			buf.append("\"").append(values[i]).append("\"");
		}

		buf.append("])");

		log.debug(buf.toString());
	}

	// ----------------------------
	// Action Handler
	// ----------------------------

	/**
	 * Delegates all incoming action to the action handler
	 * 
	 * @param ctx
	 *            ControlRequestContext
	 * @throws Exception
	 *             is thrown when an error occurs during execution
	 */
	public void execute(ControlRequestContext ctx) throws Exception {

		Object[] params = new Object[1];

		params[0] = ctx;

		execute(params);
	}

	/**
	 * Delegates all incoming action to the action handler
	 * 
	 * @param ctx
	 *            ControlRequestContext
	 * @param p1
	 *            Optional parameter 1
	 * @throws Exception
	 *             is thrown when an error occurs during execution
	 */
	public void execute(ControlRequestContext ctx, Object p1) throws Exception {

		Object[] params = new Object[2];

		params[0] = ctx;
		params[1] = p1;

		execute(params);
	}

	/**
	 * Delegates all incoming action to the action handler
	 * 
	 * @param ctx
	 *            ControlRequestContext
	 * @param p1
	 *            Optional parameter 1
	 * @param p2
	 *            Optional parameter 2
	 * @throws Exception
	 *             is thrown when an error occurs during execution
	 */
	public void execute(ControlRequestContext ctx, Object p1, Object p2)
			throws Exception {

		Object[] params = new Object[3];

		params[0] = ctx;
		params[1] = p1;
		params[2] = p2;

		execute(params);
	}

	/**
	 * Delegates all incoming action to the action handler
	 * 
	 * @param ctx
	 *            ControlRequestContext
	 * @param p1
	 *            Optional parameter 1
	 * @param p2
	 *            Optional parameter 2
	 * @param p3
	 *            Optional parameter 3
	 * @throws Exception
	 *             is thrown when an error occurs during execution
	 */
	public void execute(ControlRequestContext ctx, Object p1, Object p2,
			Object p3) throws Exception {

		Object[] params = new Object[4];

		params[0] = ctx;
		params[1] = p1;
		params[2] = p2;
		params[3] = p3;

		execute(params);
	}

	/**
	 * Delegates all incoming action to the action handler
	 * 
	 * @param ctx
	 *            ControlRequestContext
	 * @param params
	 *            Parameter
	 * @throws Exception
	 *             is thrown when an error occurs during execution
	 */
	public void execute(ControlRequestContext ctx, Object[] params)
			throws Exception {

		ArrayList p = new ArrayList();

		p.add(ctx);
		for (int i = 0; i < params.length; i++) {
			p.add(params[i]);
		}

		execute(p.toArray(new Object[p.size()]));
	}

	/**
	 * Delegates all incoming action to the action handler
	 * 
	 * @param ctx
	 *            ControlRequestContext
	 * @param params
	 *            Parameter
	 * @throws Exception
	 *             is thrown when an error occurs during execution
	 */
	public void execute(ControlRequestContext ctx, Collection params)
			throws Exception {

		ArrayList p = new ArrayList();

		p.add(ctx);
		Iterator i = params.iterator();
		while (i.hasNext()) {
			p.add(i.next());
		}

		execute(p.toArray(new Object[p.size()]));
	}

	/**
	 * Delegates all incoming action to the action handler. The first parameter
	 * must be the ControlRequestContext
	 * 
	 * @param params
	 *            Parameter
	 * @throws Exception
	 *             is thrown when an error occurs during execution
	 */
	public void execute(Object[] params) throws Exception {

		if ((params == null) || (params.length == 0)
				|| !(params[0] instanceof ControlRequestContext)) {
			throw new IllegalArgumentException(ERR_MISSING_CTX);
		}

		ControlRequestContext ctx = (ControlRequestContext) params[0];

		if (ctx.action() == null) {
			// No action to execute!
			return;
		}

		String method = StringHelp.strcat("on", ctx.action().getName());
		Class[] types = ctx.action().getFormalParameterTypes();

		try {
			// get the action handler for this control
			Method actionHandler = getClass().getMethod(method, types);
			actionHandler.invoke(this, params);
		} catch (InvocationTargetException ite) {
			Throwable t = ite.getTargetException();

			log.error(MessageFormat.format(ERR_ACTION,
					new Object[] { method, t }));

			if (t instanceof Exception) {
				throw (Exception) t;
			} else {
				throw ite;
			}
		} catch (Exception e) {
			log.error(MessageFormat.format(ERR_ACTION,
					new Object[] { method, e }));

			throw e;
		}
	}

	// --------------------------------
	// event handler
	// --------------------------------

	/**
	 * Default Handler for the <b>Help</b> Event
	 * 
	 * @param ctx
	 *            ControlRequestContext
	 * @param helpId
	 *            The Help Id of the Control
	 * @throws Exception
	 *             is thrown when an error occurs
	 */
	public void onHelp(ControlRequestContext ctx, String helpId)
			throws Exception {

		log.debug("onHelp(" + helpId + ")");
	}
}