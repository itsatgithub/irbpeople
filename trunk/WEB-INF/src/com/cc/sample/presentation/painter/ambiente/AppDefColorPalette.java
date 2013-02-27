/**
 * $Header: d:/repository/cvs/cc-samples-ext-2/source/com/cc/sample/presentation/painter/ambiente/AppDefColorPalette.java,v 1.1 2005/04/27 12:41:14 P001002 Exp $
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
public class AppDefColorPalette extends AppColorPalette {

	/**
	 * Constructor
	 */
	public AppDefColorPalette() {
		super();

		// Default color schema
		registerColor(AppResources.COLOR_BG_HEADER,			new Color("#97BCC3")); 
		registerColor(AppResources.COLOR_BG_HEADER_INFO,	new Color("#B4CED4")); 
		registerColor(AppResources.COLOR_BG_MAINMENU,		new Color("#305A6C"));
		registerColor(AppResources.COLOR_BG_TOOLMENU,		new Color("#307987"));
		registerColor(AppResources.COLOR_BG_SIDEBAR,		new Color("#C1D6DB"));
	}
}
