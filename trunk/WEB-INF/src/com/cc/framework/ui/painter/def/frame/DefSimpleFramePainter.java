/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/frame/DefSimpleFramePainter.java,v 1.4 2005/09/12 10:33:37 P001002 Exp $
 * $Revision: 1.4 $
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

import org.apache.ecs.ConcreteElement;

import com.cc.framework.ui.model.FrameTitle;
import com.cc.framework.ui.painter.Frame;

/**
 * Concrete Frame painter
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.4 $
 */
public class DefSimpleFramePainter extends DefFramePainterBase {

	/**
	 * Creates a new FramePainter
	 */
	public DefSimpleFramePainter() {
		super();
	}

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
	 * @see com.cc.framework.ui.painter.FramePainter#createFrame(com.cc.framework.ui.model.FrameTitle, boolean)
	 */
	public Frame createFrame(FrameTitle title, boolean showFrame) {
		// This frame will show no title!
		return createFrame(showFrame);
	}

	/**
	 * @see com.cc.framework.ui.painter.FramePainter#createTitle(com.cc.framework.ui.model.FrameTitle, boolean)
	 */
	public ConcreteElement createTitle(FrameTitle title, boolean showFrame) {
		return null;
	}

	/**
	 * @see com.cc.framework.ui.painter.FramePainter#addSection(com.cc.framework.ui.painter.Frame, com.cc.framework.ui.model.FrameTitle, boolean)
	 */
	public void addSection(Frame frame, FrameTitle title, boolean showFrame) {
		frame.addSection(createTitle(title, showFrame));
	}
}