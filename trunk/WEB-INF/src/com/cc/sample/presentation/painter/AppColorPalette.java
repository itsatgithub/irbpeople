/**
 * $Header: d:/repository/cvs/cc-samples-ext-2/source/com/cc/sample/presentation/painter/AppColorPalette.java,v 1.1 2005/03/16 08:27:25 P001002 Exp $
 * $Revision: 1.1 $
 * $Date: 2005/03/16 08:27:25 $
 *
 * ====================================================================
 *
 * Copyright (c) 2002 SCC Informationssysteme GmbH. All rights
 * reserved.
 *
 * ====================================================================
 */

package com.cc.sample.presentation.painter;

import java.util.Hashtable;

import com.cc.framework.ui.Color;
import com.cc.framework.ui.painter.ColorPalette;

/**
 * The Color palette for the applications fram
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.1 $
 */
public abstract class AppColorPalette implements ColorPalette {

	/**
	 * Colors
	 */
	private Hashtable colors = new Hashtable();

	/**
	 * Constructor
	 */
	public AppColorPalette() {
		super();
	}

	/**
	 * Registers one single color
	 * 
	 * @param		resourceId Color resource id
	 * @param		color The color value
	 */
	public void registerColor(String resourceId, Color color) {
		colors.put(resourceId, color);
	}

	/**
	 * @see com.cc.framework.ui.painter.ColorPalette#getColor(java.lang.String)
	 */
	public Color getColor(String resourceId) {
		return (Color) colors.get(resourceId);
	}
}
