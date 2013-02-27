/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/frame/DefSeverityFramePainter.java,v 1.2 2005/09/27 14:06:23 P001002 Exp $
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

import org.apache.ecs.AlignType;
import org.apache.ecs.ConcreteElement;
import org.apache.ecs.Entities;
import org.apache.ecs.html.TD;
import org.apache.ecs.html.TR;
import org.apache.ecs.html.Table;

import com.cc.framework.ui.model.FrameTitle;
import com.cc.framework.ui.painter.def.DefHtmlClass;

/**
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.2 $
 */
public class DefSeverityFramePainter extends DefFormFramePainter {

	/**
	 * @see com.cc.framework.ui.painter.FramePainter#createTitle(com.cc.framework.ui.model.FrameTitle, boolean)
	 */
	public ConcreteElement createTitle(FrameTitle title, boolean showFrame) {

		return new Table()
			.setCellPadding(0)
			.setCellSpacing(0)
			.setWidth("100%")
			.addElement(new TR()
				.addElement(new TD(Entities.NBSP + html(title.getCaption(), title.filter()))
					.setAlign(AlignType.ABSMIDDLE)
					.setColSpan(4)
					.setHeight(20)
					.setClass(DefHtmlClass.MESSAGE_HEADER_LABEL)));
	}
}