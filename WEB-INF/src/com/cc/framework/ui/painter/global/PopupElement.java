/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/global/PopupElement.java,v 1.3 2005/09/27 16:12:59 P001002 Exp $
 * $Revision: 1.3 $
 * $Date: 2005/09/27 16:12:59 $
 *
 * ====================================================================
 *
 * Copyright (c) 2002 SCC Informationssysteme GmbH. All rights
 * reserved.
 *
 * ====================================================================
 */

package com.cc.framework.ui.painter.global;

import org.apache.ecs.ConcreteElement;
import org.apache.ecs.html.Div;

import com.cc.framework.ui.painter.def.DefHtmlClass;

/**
 * HTML Popup element
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.3 $
 */
public class PopupElement extends Div {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 2401225552739252915L;

	/**
	 * Constructor
	 */
	public PopupElement() {
		super();

		setOnMouseOver("stopPopupTimer();");
		setOnMouseOut("startPopupTimer();");
		setClass(DefHtmlClass.POPUP);
		setID(getPopupId());
		setStyle("visibility: hidden;");
	}

	/**
	 * Constructor
	 * 
	 * @param		body the popup body
	 */
	public PopupElement(ConcreteElement body) {
		this();
		
		addElement(body);
	}

	/**
	 * @return		unique popup id
	 */
	public String getPopupId() {
		return "p" + this.hashCode();
	}

	/**
	 * Links this popup element to the given anchor
	 * element
	 * 
	 * @param		anchor the anchor for the popup window
	 */
	public void linkTo(ConcreteElement anchor) {
		if (anchor != null) {
			anchor.addAttribute("onMouseOver", "showPopup('" + getPopupId() + "', event);stopPopupTimer();");
			anchor.addAttribute("onMouseOut", "startPopupTimer();");
		}
	}
}
