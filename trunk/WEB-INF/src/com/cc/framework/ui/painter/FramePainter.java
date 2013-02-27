/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/FramePainter.java,v 1.3 2005/06/02 14:09:00 P001002 Exp $
 * $Revision: 1.3 $
 * $Date: 2005/06/02 14:09:00 $
 *
 * ====================================================================
 *
 * Copyright (c) 2002 SCC Informationssysteme GmbH. All rights
 * reserved.
 *
 * ====================================================================
 */

package com.cc.framework.ui.painter;

import org.apache.ecs.ConcreteElement;

import com.cc.framework.ui.model.FrameTitle;
import com.cc.framework.ui.model.InnerFrame;

/**
 * A FramePainter is responsible for painting the Frame of a
 * Control element
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.3 $
 */
public interface FramePainter {

	/**
	 * Sets the ControlPainter. The Control Painter gives access
	 * to the PainterContext 
	 * 
	 * @param		ctrlPainter The Control Painter
	 */
	public void setControlPainter(ControlPainter ctrlPainter);

	/**
	 * Creates a inner frame
	 * 
	 * @param		showFrame This flag indicates if the painter
	 * 				should create a visible or invisible frame 
	 * @return		Frame element
	 */
	public Frame createInnerFrame(boolean showFrame);

	/**
	 * Creates a inner frame
	 * 
	 * @param		innerFrame Attributes of the inner frame 
	 * @return		Frame element
	 */
	public Frame createInnerFrame(InnerFrame innerFrame);

	/**
	 * Creates a frame
	 * 
	 * @return		Frame element
	 * @param		showFrame This flag indicates if the painter
	 * 				should create a visible or invisible frame 
	 */
	public Frame createFrame(boolean showFrame);

	/**
	 * Creates a frame with a title bar
	 *
	 * @param		title The frames title
	 * @param		showFrame This flag indicates if the painter
	 * 				should create a visible or invisible frame 
	 * @return		Frame element
	 */
	public Frame createFrame(FrameTitle title, boolean showFrame);

	/**
	 * Adds a new titeled section to the given frame
	 *
	 * @param		frame the frame where to add a new section		
	 * @param		title The frames title
	 * @param		showFrame This flag indicates if the painter
	 * 				should create a visible or invisible frame 
	 */
	public void addSection(Frame frame, FrameTitle title, boolean showFrame);

	/**
	 * Creates the title of the frame.
	 *
	 * @param		title The frames title
	 * @param		showFrame This flag indicates if the painter
	 * 				should create a visible or invisible frame 
	 * @return		ConcreteElement
	 */
	public ConcreteElement createTitle(FrameTitle title, boolean showFrame);
}