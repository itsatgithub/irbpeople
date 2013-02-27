/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/PainterFactory.java,v 1.36 2005/07/12 06:36:48 P001002 Exp $
 * $Revision: 1.36 $
 * $Date: 2005/07/12 06:36:48 $
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

package com.cc.framework.ui.painter;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import com.cc.framework.Globals;
import com.cc.framework.adapter.FrameworkAdapterFactory;
import com.cc.framework.common.Factory;
import com.cc.framework.security.SecurityUtil;
import com.cc.framework.ui.Color;
import com.cc.framework.ui.control.Control;
import com.cc.framework.ui.control.ControlAction;
import com.cc.framework.ui.model.ImageModel;
import com.cc.framework.ui.painter.global.GlobalPainterFactory;
import com.cc.framework.ui.painter.html.HtmlPainterFactory;

/**
 * A painter factory is responsible for the rendering of html control
 * elements like lists, trees or tabsets. A painter creates a special
 * gui layout (skin). For example the default painter from the
 * painter.def package generates the gui which is shown in the trial
 * version. New user interfaces can be provided by customizing the
 * stylesheet which comes with the default painter or by creating
 * new painters. Customizing the stylesheets can be done in future
 * with the ResourceFactory Tool.
 *
 * The application must register a painter at startup. This can be done
 * in the application or the session scope. If a painter is registered
 * in the application scope the gui (skin) will be offerd to all users.
 * If a user should select between different gui layouts (skins) the
 * painters must be registered in the users session.
 *
 * <p>Example: register a painter in the session scope. This can be done
 * in the logon action of the application
 * <pre>
 *   // select a special painter for the user
 *   session.setAttribute(PainterFactory.SESSION_PAINTER, new MyPainterFactory());
 * </pre>
 *
 * <p>Example: register the painter in the application scope. This can be done in
 * the {@link javax.servlet.http.HttpServlet#init()} methode of the controller servlet.
 * <pre>
 *   // register a painter for the application
 *   getServletContext().setAttribute(PainterFactory.APPLICATION_PAINTER, new MyPainterFactory());
 * </pre>
 *
 * <p>The frameworke selects a painter in the following way.
 *
 * <ol>
 *  <li>Session scope</li>
 *  <li>Application scope</li>
 *  <li>If no painter will be found the the Default Painter Factory will be used {@link com.cc.framework.ui.painter.def.DefPainterFactory}</li>
 * </ol>

 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.36 $
 * @since       1.0
 */
public abstract class PainterFactory implements Factory, Serializable {

	/**
	 * The Resource Map.
	 * It contains the local Image definitions for this painter
	 */
	private ResourceMap resources = null;

	/**
	 * Constructor
	 */
	public PainterFactory() {
		super();

		this.resources = createResourceMap();
	}

	/**
	 * Returns an unique String which identifies the Painterfactory.
	 *
	 * @return	The unique Id of the Painterfactory.
	 */
	public abstract String getFactoryId();

	/**
	 * Gets the painters Resource Map
	 *
	 * @return		Resource Map
	 */
	public ResourceMap getResources() {
		return resources;
	}

	/**
	 * Creates the Painters Resource Map
	 *
	 * @return		The Resource Map
	 */
	protected abstract ResourceMap createResourceMap();

	/**
	 * Creates a painter for the control spezified in the argument.
	 * This methode must be implemented by a concrete sub class.
	 * <p><b>Note:</b> This methode will be called from the PainterFactory!
	 *
	 * @param	painterContext	The context of the jsp page where the control is
	 * 							defined. So it is possible for the painter to
	 * 							get the  ActionMappings if required. The ActionMappings
	 * 							are neccessary for the generation of hyperlinks.
	 * @param	ctrl			The instance of the control for which a painter should be created
	 * @return	The painter for the specified control
	 */
	protected abstract ControlPainter doCreatePainter(PainterContext painterContext, Control ctrl);

	/**
	 * Creates a fram painter for the control spezified in the argument.
	 * This methode must be implemented by a concrete sub class.
	 * <p><b>Note:</b> This methode will be called from the PainterFactory!
	 *
	 * @param	painterContext	The context of the jsp page where the control is
	 * 							defined. So it is possible for the painter to
	 * 							get the  ActionMappings if required. The ActionMappings
	 * 							are neccessary for the generation of hyperlinks.
	 * @param	ctrl			The instance of the control for which a frame painter
	 * 							should be created
	 * @return	The frame painter for the specified control
	 */
	protected FramePainter doCreateFramePainter(PainterContext painterContext, Control ctrl) {
		return null;
	}

	/**
	 * Creates all Include-Tags in the HTML-Pages wich are necessary for the
	 * Framework to work well (css, javascripts, ...).
	 * This Methode must be overriden by a concret Painter Class.
	 *
	 * @param	writer The JspWriter
	 * @throws	IOException	If an input or output exception occurred
	 */
	protected abstract void doCreateHeaderIncludes(JspWriter writer) throws IOException;

	/**
	 * Creates all Include-Tags in the HTML-Pages wich are necessary for the
	 * Framework to work well (css, javascripts, ...).
	 *
	 * @param	pageContext	The PageContext
	 */
	public static void createHeaderIncludes(PageContext pageContext) {

		JspWriter writer = pageContext.getOut();

		try {
			PainterFactory[] factories = getFactoyStack(pageContext);

			// Every Painterfactory (in reverse order!) has now the
			// chance to write additional includes into the JSP-Page
			for (int i = (factories.length - 1); i >= 0; i--) {
				writer.println();
				writer.println("\t<!-- painter '" + factories[i].getFactoryId() + "' -->");
				factories[i].doCreateHeaderIncludes(writer);
				writer.println("\t<!-- end -->");
			}
		} catch (java.io.IOException ioe) {
			// No Action
		}
	}

	/**
	 * Helper Function. Creates a Painter for the specified Control.
	 * Therefor the registerd painter will be used.
	 *
	 * @param	pageContext	PageContext
	 * @param	ctrl	Control
	 * @return	ControlPainter
	 */
	public static ControlPainter createPainter(
		PageContext pageContext,
		Control ctrl) {

		ControlPainter painter = null;
		PainterContext painterContext = new PainterContext(pageContext, ctrl);

		// assign the principal objekt to the control
		// so the control can decide what to do (which elements should be painted)
		// if a user has a special right or not
		ctrl.setPrincipal(SecurityUtil.getPrincipal((HttpServletRequest) pageContext.getRequest()));

		PainterFactory[] factories = getFactoyStack(pageContext);

		// get the first registered painter
		for (int i = 0; (painter == null) && (i < factories.length); i++) {

			painter = factories[i].doCreatePainter(painterContext, ctrl);

			if (painter != null) {
				// Register this factory in the painter context
				painterContext.setFactory(factories[i], factories);
			}
		}

		return painter;
	}

	/**
	 * Helper Function. Creates a Painter for the specified Control.
	 * Therefor the registerd painter will be used.
	 *
	 * @param		controlPainter The ControlPainter
	 * @return		FramePainter or <code>null</code>
	 */
	public static FramePainter createFramePainter(ControlPainter controlPainter) {
		FramePainter painter = null;

		PainterFactory[] factories = getFactoyStack(controlPainter.getPageContext());

		// get the first registered painter
		for (int i = 0; (painter == null) && (i < factories.length); i++) {

			painter =
				factories[i].doCreateFramePainter(
					controlPainter.getPainterContext(),
					controlPainter.getPainterContext().getControl());

			if (painter != null) {
				// Connect the Portion Painter to it's parent
				// control painter
				painter.setControlPainter(controlPainter);
			}
		}

		return painter;
	}

	/**
	 * Creates an ActionPainter. The Painter is used by Controls to
	 * render Actions into the user interface
	 *
	 * @param	ctx The Painter Context
	 * @param	action	ControlAction
	 * @return	ActionPainter
	 */
	public ActionPainter createActionPainter(PainterContext ctx, ControlAction action) {
		return FrameworkAdapterFactory.getAdapter().createActionPainter(ctx, action);
	}

	/**
	 * Checks if the specified Factory is registered
	 *
	 * @param	pageContext	The	PageContext
	 * @param	factoryId	The unique Id of the factory
	 * @return	boolean
	 */
	public static boolean isFactorySelected(PageContext pageContext, String factoryId) {
		PainterFactory[] factories = getFactoyStack(pageContext);

		for (int i = 0; i < factories.length; i++) {
			if (factories[i].equals(factoryId)) {
				return true;
			}
		}

		// Factory not found
		return false;
	}

	/**
	 * Returns the specified painter factory by its factory id
	 *
	 * @param	pageContext	The	PageContext
	 * @param	factoryId	The unique id of the factory
	 * @return	painter factoryinstance or <code>null</code>
	 */
	public static PainterFactory lookupFactory(PageContext pageContext, String factoryId) {
		PainterFactory[] factories = getFactoyStack(pageContext);

		for (int i = 0; i < factories.length; i++) {
			if (factories[i].equals(factoryId)) {
				return factories[i];
			}
		}

		// Factory not found
		return null;
	}

	/**
	 * Retrieves the value for the given resource key
	 *
	 * @param		pageContext Jsp Page Context
	 * @param		resourceKey the resource key
	 * @return		value or <code>null</code>
	 */
	public static String getStringResource(PageContext pageContext, String resourceKey) {

		if (resourceKey == null) {
			return null;
		}

		String value = null;

		PainterFactory[] factories = getFactoyStack(pageContext);

		// Search for the resource in all the painter factories
		for (int i = 0; (value == null) && (i < factories.length); i++) {
			value = factories[i].getResources().getString(resourceKey, true);
		}

		return value;
	}

	/**
	 * Retrieves the value for the given color key
	 *
	 * @param		pageContext Jsp Page Context
	 * @param		resourceKey the resource key
	 * @return		value or <code>null</code>
	 */
	public static Color getColorResource(PageContext pageContext, String resourceKey) {

		if (resourceKey == null) {
			return null;
		}

		Color color = null;

		PainterFactory[] factories = getFactoyStack(pageContext);

		// Search for the resource in all the painter factories
		for (int i = 0; (color == null) && (i < factories.length); i++) {
			color = factories[i].getResources().getColor(resourceKey);
		}

		return color;
	}

	/**
	 * Retrieves the value for the given resource key
	 *
	 * @param		pageContext Jsp Page Context
	 * @param		resourceKey the resource key
	 * @return		value or <code>null</code>
	 */
	public static ImageModel getImageResource(PageContext pageContext, String resourceKey) {

		if (resourceKey == null) {
			return null;
		}

		ImageModel image = null;

		PainterFactory[] factories = getFactoyStack(pageContext);

		// Search for the resource in all the painter factories
		for (int i = 0; (image == null) && (i < factories.length); i++) {
			image = factories[i].getResources().getImage(resourceKey);
		}

		return image;
	}

	/**
	 * Returns the registerd painterfacory from the
	 * session or application scope.
	 *
	 * @param	pageContext	PageContext
	 * @return	PainterFactory
	 */
	public static PainterFactory[] getFactoyStack(PageContext pageContext) {
		ArrayList painterList = new ArrayList();

		// get the painter from session scope
		if (pageContext.getSession() != null) {
			ArrayList sessionPainters = (ArrayList) pageContext.getSession().getAttribute(Globals.PAINTER_KEY);

			// Die Vereinigungsmenge bilden
			if (sessionPainters != null) {
				painterList.addAll(sessionPainters);
			}
		}

		// get the painter from application scope
		ArrayList applicationPainters = (ArrayList) pageContext.getServletContext().getAttribute(Globals.PAINTER_KEY);

		if (applicationPainters != null) {
			painterList.addAll(applicationPainters);
		}

		PainterFactory[] list = new PainterFactory[painterList.size()];
		return (PainterFactory[]) painterList.toArray(list);
	}

	/**
	 * Resets the SessionPainter
	 * @param session	HttpSession
	 */
	public static void resetSessionPainter(HttpSession session) {
		if (session == null) {
			return;
		}

		session.removeAttribute(Globals.PAINTER_KEY);
	}

	/**
	 * Restes the ApplicationPainter
	 * @param ctx	ServletContext
	 */
	public static void resetApplicationPainter(ServletContext ctx) {
		ctx.removeAttribute(Globals.PAINTER_KEY);
	}

	/**
	 * Registers a Painter in the Session Scope.
	 * SessionPainter will be used first
	 * @param	session	HttpSession
	 * @param	painter	PainterFactory
	 */
	public static void registerSessionPainter(HttpSession session, PainterFactory painter) {

		if (session == null) {
			return;
		}

		ArrayList painterList = (ArrayList) session.getAttribute(Globals.PAINTER_KEY);

		if (painterList == null) {
			painterList = new ArrayList();

			session.setAttribute(Globals.PAINTER_KEY, painterList);
		}

		if ((painter != null) && !isRegistered(painter, painterList)) {
			painterList.add(0, painter);
		}
	}

	/**
	 * Unregisters a Painter from the Session Scope
	 *
	 * @param	session	HttpSession
	 * @param	painter	PainterFactory to unregister
	 */
	public static void unregisterSessionPainter(HttpSession session, PainterFactory painter) {

		if (session == null) {
			return;
		}

		ArrayList painterList = (ArrayList) session.getAttribute(Globals.PAINTER_KEY);

		if (painterList != null) {
			Iterator iter = painterList.iterator();

			while (iter.hasNext()) {
				PainterFactory p = (PainterFactory) iter.next();

				if (p.equals(painter)) {
					// Unregister the factory
					iter.remove();
					break;
				}
			}
		}
	}

	/**
	 * Registers the global painters in the Appliction Scope
	 * @param	ctx	ServletContext
	 */
	public static void registerApplicationPainters(ServletContext ctx) {
		registerApplicationPainter(ctx, null);
	}

	/**
	 * Registers a Painter in the Appliction Scope
	 * @param	ctx	ServletContext
	 * @param	painter	PainterFactory to register
	 */
	public static void registerApplicationPainter(ServletContext ctx, PainterFactory painter) {

		ArrayList painterList = (ArrayList) ctx.getAttribute(Globals.PAINTER_KEY);

		if (painterList == null) {
			painterList = new ArrayList();

			ctx.setAttribute(Globals.PAINTER_KEY, painterList);

			// register the global painterfactories
			painterList.add(0, GlobalPainterFactory.instance());
			painterList.add(0, HtmlPainterFactory.instance());
		}

		if ((painter != null) && !isRegistered(painter, painterList)) {
			painterList.add(0, painter);
		}
	}

	/**
	 * Unregisters a Painter from the Appliction Scope
	 *
	 * @param	ctx	ServletContext
	 * @param	painter	PainterFactory to unregister
	 */
	public static void unregisterApplicationPainter(ServletContext ctx, PainterFactory painter) {

		ArrayList painterList = (ArrayList) ctx.getAttribute(Globals.PAINTER_KEY);

		if (painterList != null) {
			Iterator iter = painterList.iterator();

			while (iter.hasNext()) {
				PainterFactory p = (PainterFactory) iter.next();

				if (p.equals(painter)) {
					// Unregister the factory
					iter.remove();
					break;
				}
			}
		}
	}

	/**
	 * Checks if a painter is already registered in the painter list
	 *
	 * @param		painter the painter to check
	 * @param		painterList the painter list to search
	 * @return		returns <code>true</code> if the painter
	 * 				is already registered in the list
	 */
	protected static boolean isRegistered(PainterFactory painter, ArrayList painterList) {
		// Is this painter already registered
		Iterator iter = painterList.iterator();
		while (iter.hasNext()) {
			PainterFactory p = (PainterFactory) iter.next();

			if (p.equals(painter)) {
				// the factory is already registered
				return true;
			}
		}

		return false;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {

		if (obj instanceof String) {
			return obj.equals(getFactoryId());
		} else {
			return super.equals(obj);
		}
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		if (getFactoryId() == null) {
			return super.hashCode();
		} else {
			return getFactoryId().hashCode();
		}
	}

	/**
	 * Returns the unique id of the painter factory
	 * @return The unique id
	 */
	public String toString() {
		return getFactoryId();
	}
}