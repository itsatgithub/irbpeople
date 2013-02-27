/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/frame/DefPanelFramePainter.java,v 1.5 2005/09/12 10:33:37 P001002 Exp $
 * $Revision: 1.5 $
 * $Date: 2005/09/12 10:33:37 $
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
import org.apache.ecs.html.Span;
import org.apache.ecs.html.TD;
import org.apache.ecs.html.TR;
import org.apache.ecs.html.Table;

import com.cc.framework.ui.model.FrameTitle;
import com.cc.framework.ui.painter.Frame;
import com.cc.framework.ui.painter.def.DefClassType;

/**
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.5 $
 */
public class DefPanelFramePainter extends DefFramePainterBase {

	/**
	 * @see com.cc.framework.ui.painter.FramePainter#createFrame(boolean)
	 */
	public Frame createFrame(boolean showFrame) {
		return new Frame();
	}

	/**
	 * @see com.cc.framework.ui.painter.FramePainter#createInnerFrame(boolean)
	 */
	public Frame createInnerFrame(boolean showFrame) {
		return new Frame();
	}

	/**
	 * @see com.cc.framework.ui.painter.FramePainter#createTitle(com.cc.framework.ui.model.FrameTitle, boolean)
	 */
	public ConcreteElement createTitle(FrameTitle title, boolean showFrame) {

		Span caption = new Span();
		
		if (title.getImage() != null) {
			// Add an optional frame image
			caption.addElement(createImage(title.getImage())
				.setVspace(0)
				.setAlign(AlignType.absmiddle)
				.setStyle("margin-right:3px;"));
		}

		if (title.getTooltip() != null) {
			caption.setTitle(html(title.getTooltip(), title.filter()));
		}

		caption.addElement(html(title.getCaption(), title.filter()));

		return (Table) new Table()
			.setWidth("100%")
			.setCellSpacing(0)
			.setCellPadding(0)
			.addElement(new TR().addElement(new TD(caption)))
			.setClass(getElementClass(DefClassType.CLASS_HEADER));
	}

	/**
	 * @see com.cc.framework.ui.painter.FramePainter#createFrame(com.cc.framework.ui.model.FrameTitle, boolean)
	 */
	public Frame createFrame(FrameTitle title, boolean showFrame) {
		Frame frame = new Frame();

		frame.addBodyElement(createTitle(title, showFrame));

		return frame;
	}

	/**
	 * @see com.cc.framework.ui.painter.FramePainter#addSection(com.cc.framework.ui.painter.Frame, com.cc.framework.ui.model.FrameTitle, boolean)
	 */
	public void addSection(Frame frame, FrameTitle title, boolean showFrame) {
		frame.addSection(createTitle(title, showFrame));
	}
}