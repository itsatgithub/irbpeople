/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/frame/DefInfoFramePainter.java,v 1.2 2005/09/27 14:06:23 P001002 Exp $
 * $Revision: 1.2 $
 * $Date: 2005/09/27 14:06:23 $
 *
 * ====================================================================
 *
 * Copyright (c) 2002 SCC Informationssysteme GmbH. All rights
 * reserved.
 *
 * ====================================================================
 */

package com.cc.framework.ui.painter.def.frame;

import org.apache.ecs.ConcreteElement;
import org.apache.ecs.Entities;
import org.apache.ecs.html.TD;
import org.apache.ecs.html.TR;
import org.apache.ecs.html.Table;

import com.cc.framework.ui.model.FrameTitle;
import com.cc.framework.ui.painter.def.DefColorPalette;
import com.cc.framework.ui.painter.def.DefHtmlClass;
import com.cc.framework.ui.painter.def.DefResources;

/**
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.2 $
 */
public class DefInfoFramePainter extends DefFormFramePainter {

	/**
	 * @see com.cc.framework.ui.painter.FramePainter#createTitle(com.cc.framework.ui.model.FrameTitle, boolean)
	 */
	public ConcreteElement createTitle(FrameTitle title, boolean showFrame) {

		return new Table()
			.setCellPadding(0)
			.setCellSpacing(0)
			.setWidth("100%")
			.addElement(new TR()
				.addElement(new TD()
					.setHeight(4))
				.addElement(new TD()
					.setRowSpan(3)
					.addElement(createImage(DefResources.IMAGE_INFORMATION)))
				.addElement(new TD()
					.setHeight(4))
				.addElement(new TD()
					.setHeight(4)))
			.addElement(new TR()
				.addElement(new TD()
					.addElement(createImage(DefResources.CORNER_FORM_LEFT_COLOR, getColor(DefColorPalette.INFO_COLOR_BG_HEADER))))
				.addElement(new TD(Entities.NBSP + html(title.getCaption(), title.filter()))
					.setClass(DefHtmlClass.MESSAGE_HEADER_LABEL))
				.addElement(new TD()
					.addElement(createImage(DefResources.CORNER_FORM_RIGHT_COLOR, getColor(DefColorPalette.INFO_COLOR_BG_HEADER)))))
			.addElement(new TR()
				.addElement(new TD()
					.addElement(createImage(DefResources.IMAGE_SPACER))
					.setHeight(2)
					.setStyle("border-left: 1px solid " + getColor(DefColorPalette.INFO_COLOR_BORDER).toHtml() + ";"))
				.addElement(new TD()
					.setHeight(2))
				.addElement(new TD()
					.addElement(createImage(DefResources.IMAGE_SPACER))
					.setHeight(2)
					.setStyle("border-right: 1px solid " + getColor(DefColorPalette.INFO_COLOR_BORDER).toHtml() + ";"))
				.setStyle("background-color: " + getColor(DefColorPalette.INFO_COLOR_BG_BODY).toHtml() + ";"));
	}
}
