/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/ControlPortionPainter.java,v 1.7 2005/06/21 07:09:49 P001002 Exp $
 * $Revision: 1.7 $
 * $Date: 2005/06/21 07:09:49 $
 *
 * ====================================================================
 *
 * Copyright (c) 2002 SCC Informationssysteme GmbH. All rights
 * reserved.
 *
 * ====================================================================
 */

package com.cc.framework.ui.painter;

import java.util.Locale;

import javax.servlet.jsp.PageContext;

import org.apache.ecs.ConcreteElement;
import org.apache.ecs.html.IMG;

import com.cc.framework.security.Principal;
import com.cc.framework.ui.Color;
import com.cc.framework.ui.control.ControlActionDef;
import com.cc.framework.ui.model.ImageModel;

/**
 * This is the abstract Base Class for all Portion Painter. A Portion
 * Painter has no own Painter Context. It uses the Painter Context of
 * the Parent Control
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.7 $
 */
public abstract class ControlPortionPainter {

	/**
	 * The Control Painter
	 */
	private ControlPainter ctrlPainter = null;

	/**
	 * Creates a new ViewPainter
	 */
	public ControlPortionPainter() {
		super();
	}

	/**
	 * Sets the ControlPainter
	 *
	 * @param		ctrlPainter The Control Painter
	 */
	public void setControlPainter(ControlPainter ctrlPainter) {
		this.ctrlPainter = ctrlPainter;
	}

	/**
	 * @return		PainterContext
	 */
	protected PainterContext getPainterContext() {
		return ctrlPainter.getPainterContext();
	}

	/**
	 * @return		ControlPainter
	 */
	protected ControlPainter getControlPainter() {
		return ctrlPainter;
	}

	/**
	 * Retrieves the current Locale
	 *
	 * @return		Locale
	 */
	protected Locale getLocale() {
		return ctrlPainter.getLocale();
	}

	/**
	 * Retrieves the current Locale or the system locale
	 * when localization is not turned on
	 *
	 * @return		Locale
	 */
	protected Locale getSafeLocale() {
		Locale locale = getLocale();

		if (locale == null) {
			// Use
			return Locale.getDefault();
		} else {
			return locale;
		}
	}

	/**
	 * Localizes a string depending on the localization settings
	 * of the Painter context
	 *
	 * @param	keyOrString String Literal or Resource Key
	 * @return	localized String
	 */
	protected String localize(String keyOrString) {
		return ctrlPainter.localize(keyOrString);
	}

	/**
	 * @return		Principal Object
	 */
	protected Principal getPrincipal() {
		return ctrlPainter.getPrincipal();
	}

	/**
	 * Creates an ActionPainter. The Painter is used by Controls to
	 * render Actions into the user interface
	 *
	 * @param	action	ControlAction
	 * @param	actionName Additional Action Name
	 * @return	ActionPainter
	 */
	protected ActionPainter createActionPainter(
		ControlActionDef action,
		String actionName) {

		return ctrlPainter.createActionPainter(action, actionName);
	}

	/**
	 * Retrieves an color for the specified Id
	 *
	 * @param		resourceId Color Code
	 * @return		The Color or <code>null</code>
	 */
	protected Color getColor(String resourceId) {
		return ctrlPainter.getColor(resourceId);
	}

	/**
	 * Retrieves an image for the specified Id
	 *
	 * @param		resourceId Image Code
	 * @return		The Image or <code>null</code>
	 */
	protected IMG createImage(String resourceId) {
		return ctrlPainter.createImage(resourceId);
	}

	/**
	 * Creates an HTML image Tag for the specified
	 * image model
	 *
	 * @param		image Image
	 * @return		The Image or <code>null</code>
	 */
	protected IMG createImage(ImageModel image) {
		return ctrlPainter.createImage(image);
	}

	/**
	 * Retrieves an image for the specified Id
	 *
	 * @param		resourceId Image Code
	 * @param		param Markup Parameter
	 * @return		The Image or <code>null</code>
	 */
	protected IMG createImage(String resourceId, Object param) {
		return ctrlPainter.createImage(resourceId, param);
	}

	/**
	 * Retrieves an image for the specified Id
	 *
	 * @param		resourceId Image Code
	 * @return		The Image Source
	 */
	protected String getImageSrc(String resourceId) {
		return ctrlPainter.getImageSrc(resourceId);
	}

	/**
	 * Retrieves an image for the specified Id
	 *
	 * @param		resourceId Image Code
	 * @param		param Markup Parameter
	 * @return		The Image Source
	 */
	protected String getImageSrc(String resourceId, Object param) {
		return ctrlPainter.getImageSrc(resourceId, param);
	}

	/**
	 * Retrieves an image for the specified Id
	 *
	 * @param		resourceId Image Code
	 * @return		The Image or <code>null</code>
	 */
	protected ImageModel getImage(String resourceId) {
		return ctrlPainter.getImage(resourceId);
	}

	/**
	 * Retrieves an image for the specified Id
	 *
	 * @param		resourceId Image Code
	 * @param		param Markup Parameter
	 * @return		The Image Source
	 */
	protected ImageModel getImage(String resourceId, Object param) {
		return ctrlPainter.getImage(resourceId, param);
	}

	/**
	 * Creates a spacer image with the given dimension
	 *
	 * @param		height spacer height
	 * @param		width spacer width
	 * @return		Spacer Image
	 */
	protected ConcreteElement createSpacer(int height, int width) {
		return ctrlPainter.createSpacer(height, width);
	}

	/**
	 * Converts a String to an equivalent HTML-String
	 * @param	raw	Object
	 * @return String
	 */
	protected String html(Object raw) {
		return ctrlPainter.html(raw);
	}

	/**
	 * Converts a String to an equivalent HTML-String
	 * @param	raw		Object
	 * @param	filter	Filter
	 * @return	String
	 */
	protected String html(Object raw, boolean filter) {
		return ctrlPainter.html(raw, filter);
	}

	/**
	 * Converts a String to an equivalent HTML-String
	 * @param	raw		Object
	 * @param	filter	Filter
	 * @param	maxlength The maximum number of visible characters
	 * @return	String
	 */
	protected String html(Object raw, boolean filter, int maxlength) {
		return ctrlPainter.html(raw, filter, maxlength);
	}

	/**
	 * Returns the JSP page context
	 *
	 * @return		The jsp page context
	 */
	protected PageContext getPageContext() {
		return ctrlPainter.getPageContext();
	}

	/**
	 * Returns a framework string resource
	 *
	 * @param	resourceId	Id of the resource/key
	 * @return The value for the resource id
	 */
	protected String getFrameworkString(String resourceId) {
		return ctrlPainter.getFrameworkString(resourceId);
	}

	/**
	 * Retrieves an framework String for the specified Id
	 *
	 * @param		resourceId	The resource id
	 * @param		arguments MessageFormat arguments
	 * @return		The String Source
	 */
	protected String getFrameworkString(String resourceId, Object[] arguments) {
		return ctrlPainter.getFrameworkString(resourceId, arguments);
	}

	/**
	 * Creates an ActionPainter. The Painter is used by Controls to
	 * render Actions into the user interface
	 *
	 * @param	action	ControlAction
	 * @return	ActionPainter
	 */
	protected ActionPainter createActionPainter(ControlActionDef action) {
		return ctrlPainter.createActionPainter(action);
	}

	/**
	 * The method returns the html style class required for
	 * a list or treelist control.
	 *
	 * @param	type The required style class
	 * @return	The style class
	 */
	protected String getElementClass(int type) {
		return getControlPainter().getElementClass(type);
	}
}
