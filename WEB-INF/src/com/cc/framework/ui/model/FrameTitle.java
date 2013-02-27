/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/FrameTitle.java,v 1.2 2005/05/29 09:50:57 P001002 Exp $
 * $Revision: 1.2 $
 * $Date: 2005/05/29 09:50:57 $
 *
 * ====================================================================
 *
 * Copyright (c) 2002 SCC Informationssysteme GmbH. All rights
 * reserved.
 *
 * ====================================================================
 */

package com.cc.framework.ui.model;

import java.util.Collection;
import java.util.Iterator;

/**
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.2 $
 */
public class FrameTitle implements Cloneable {

	/**
	 * Title Tooltip
	 */
	private String tooltip = null;

	/**
	 * The localized caption Text
	 */
	private String caption = null;

	/**
	 * The localized detail Text
	 */
	private String detail = null;

	/**
	 * The Help Id for this Frame
	 */
	private String helpId = null;

	/**
	 * Indicates if String resources should be
	 * converted to HTML
	 */
	private boolean filter = true;
	
	/**
	 * Collection of ConcreteElements for the Frames button
	 * bar
	 */
	private Collection buttons = null;

	/**
	 * The optional title image
	 */
	private ImageModel image = null;

	/**
	 * Constructor
	 */
	public FrameTitle() {
		super();
	}

	/**
	 * Constructor
	 *
	 * @param		image The optional frame image
	 * @param		caption The localized caption Text
	 * @param		detail The localized detail Text
	 * @param		helpId The Help Id for this Frame
	 * @param		buttons Collection with button elements 
	 */
	public FrameTitle(
		ImageModel image,
		String caption,
		String detail,
		String helpId,
		Collection buttons) {
		super();

		this.image		= image;
		this.caption	= caption;
		this.detail		= detail;
		this.helpId		= helpId;
		this.buttons	= buttons;
	}

	/**
	 * Checks if this Title has any Button Elements assigned
	 * 
	 * @return		<code>true</code> if button elements are assigned
	 */

	public boolean hasButtons() {
		return (buttons != null) && (buttons.size() > 0);
	}

	/**
	 * @return		returns an iterator for the button (Concreteelement)
	 * 				elements
	 */
	public Iterator getButtons() {
		return buttons.iterator();
	}

	/**
	 * @return		returns the caption text
	 */
	public String getCaption() {
		return caption;
	}

	/**
	 * @return		returns the detail text
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * Checks if the title has a detail text
	 * 
	 * @return		returns <code>true</code> if the title
	 * 				has a detail text
	 */
	public boolean hasDetail() {
		return (detail != null) && !"".equals(detail);
	}

	/**
	 * Indicates if String resources should be
	 * converted to HTML
	 * 
	 * @return		<code>true</code> if HTML conversion should
	 * 				be made
	 */
	public boolean filter() {
		return filter;
	}

	/**
	 * @return		Help Id of the Title Element
	 */
	public String getHelpId() {
		return helpId;
	}

	/**
	 * @return		Title Tooltip Text
	 */
	public String getTooltip() {
		return tooltip;
	}

	/**
	 * Returns the image for the frames title
	 * 
	 * @return		ImageModel
	 */
	public ImageModel getImage() {
		return image;
	}

	/**
	 * Sets the ConcreteElement collection of button
	 * elements for the title
	 * 
	 * @param		collection Concreteelement Collection
	 */
	public void setButtons(Collection collection) {
		this.buttons = collection;
	}

	/**
	 * @param		string Caption Text
	 */
	public void setCaption(String string) {
		this.caption = string;
	}

	/**
	 * @param		string Detail Text
	 */
	public void setDetail(String string) {
		this.detail = string;
	}

	/**
	 * @param		b <code>true</code> to enable HTML
	 * 				Conversion. The default is <code>true</code>
	 */
	public void setFilter(boolean b) {
		this.filter = b;
	}

	/**
	 * @param		string Help Id
	 */
	public void setHelpId(String string) {
		this.helpId = string;
	}

	/**
	 * @param		string Tooltip Text
	 */
	public void setTooltip(String string) {
		this.tooltip = string;
	}

	/**
	 * Sets an image for the frames title
	 * 
	 * @param		img	ImageModel
	 */
	public void setImage(ImageModel img) {
		this.image = img;
	}
}
