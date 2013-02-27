/**
 * $Header: d:/repository/cvs/cc-samples-ext-2/source/com/cc/sample/presentation/painter/ambiente/AppDef2ColorPalette.java,v 1.1 2005/04/27 12:41:14 P001002 Exp $
 * $Revision: 1.1 $
 * $Date: 2005/04/27 12:41:14 $
 *
 * ====================================================================
 *
 * Copyright (c) 2002 SCC Informationssysteme GmbH. All rights
 * reserved.
 *
 * ====================================================================
 */

package com.cc.sample.presentation.painter.ambiente;

import com.cc.framework.ui.Color;
import com.cc.sample.presentation.painter.AppColorPalette;
import com.cc.sample.presentation.painter.AppResources;

/**
 * Color Palette for the Applications frame elements
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.1 $
 */
public class AppDef2ColorPalette extends AppColorPalette {

	/**
	 * Constructor
	 */
	public AppDef2ColorPalette() {
		super();

		// Default2 color schema
		registerColor(AppResources.COLOR_BG_HEADER,			new Color("#96B8D3")); 
		registerColor(AppResources.COLOR_BG_HEADER_INFO,	new Color("#B8CBE1")); 
		registerColor(AppResources.COLOR_BG_MAINMENU,		new Color("#1E5C99"));
		registerColor(AppResources.COLOR_BG_TOOLMENU,		new Color("#5A84B8"));
		registerColor(AppResources.COLOR_BG_SIDEBAR,		new Color("#CCDAEA"));
	}
}
