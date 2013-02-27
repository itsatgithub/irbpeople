/**
 * $Header: d:/repository/cvs/cc-samples-ext-2/source/com/cc/sample/presentation/painter/AppPainterFactory.java,v 1.2 2005/07/18 16:13:06 P001002 Exp $
 * $Revision: 1.2 $
 * $Date: 2005/07/18 16:13:06 $
 *
 * ====================================================================
 *
 * Copyright (c) 2002 SCC Informationssysteme GmbH. All rights
 * reserved.
 *
 * ====================================================================
 */

package com.cc.sample.presentation.painter;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;

import com.cc.framework.ui.control.Control;
import com.cc.framework.ui.painter.ColorPalette;
import com.cc.framework.ui.painter.ControlPainter;
import com.cc.framework.ui.painter.PainterContext;
import com.cc.framework.ui.painter.PainterFactory;
import com.cc.framework.ui.painter.ResourceMap;

/**
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.2 $
 */
public class AppPainterFactory extends PainterFactory {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 246440759866926329L;

	/**
	 * Constructor
	 * 
	 * @param		palette The color palette for our
	 * 				application frame
	 */
	public AppPainterFactory(ColorPalette palette) {
		super();
		
		getResources().setColorPalette(palette);
	}

	/**
	 * @see com.cc.framework.ui.painter.PainterFactory#createResourceMap()
	 */
	protected ResourceMap createResourceMap() {
		return new AppResourceMap();
	}

	/**
	 * @see com.cc.framework.ui.painter.PainterFactory#doCreateHeaderIncludes(javax.servlet.jsp.JspWriter)
	 */
	protected void doCreateHeaderIncludes(JspWriter writer)
		throws IOException {

		ResourceMap map = getResources();

		// CSS resources
		writer.print("\t<link rel='stylesheet' href='");
		writer.print(map.getString(AppResources.STRING_FILE_CSS_DEFAULT, false));
		writer.println("' charset='ISO-8859-1' type='text/css'>");
	}

	/**
	 * @see com.cc.framework.ui.painter.PainterFactory#doCreatePainter(com.cc.framework.ui.painter.PainterContext, com.cc.framework.ui.control.Control)
	 */
	protected ControlPainter doCreatePainter(
		PainterContext painterContext,
		Control ctrl) {

		// This factory has no custom painters;
		return null;
	}

	/**
	 * @see com.cc.framework.ui.painter.PainterFactory#getFactoryId()
	 */
	public String getFactoryId() {
		return "app";
	}
}
