/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/frame/DefFramePainterBase.java,v 1.4 2005/09/12 10:33:37 P001002 Exp $
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

import java.util.Iterator;

import org.apache.ecs.ConcreteElement;
import org.apache.ecs.ElementContainer;
import org.apache.ecs.StringElement;
import org.apache.ecs.html.TD;

import com.cc.framework.ui.AlignmentType;
import com.cc.framework.ui.control.ControlButton;
import com.cc.framework.ui.model.FrameTitle;
import com.cc.framework.ui.model.InnerFrame;
import com.cc.framework.ui.painter.ControlPortionPainter;
import com.cc.framework.ui.painter.Frame;
import com.cc.framework.ui.painter.FramePainter;
import com.cc.framework.ui.painter.def.DefClassType;
import com.cc.framework.ui.painter.def.DefPainterBase;
import com.cc.framework.ui.painter.def.DefResources;

/**
 * Concrete Frame painter
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.4 $
 */
public abstract class DefFramePainterBase_200705201850 extends ControlPortionPainter implements FramePainter {

	/**
	 * Creates a new FramePainter
	 */
	public DefFramePainterBase_200705201850() {
		super();
	}		

	/**
	 * This method checks if a help button is visible
	 * 
	 * @param		title The Frame Title
	 * @return		<code>true</code> if the help button
	 * 				is visible
	 */
	protected boolean showHelpButton(FrameTitle title) {
		return (title.getHelpId() != null)
			&& (getPainterContext().getControl().showButton(ControlButton.HELP));
	}

	/**
	 * This method checks if any title button is visible
	 * 
	 * @param		title The Frame Title
	 * @return		<code>true</code> if any title button
	 * 				is visible
	 */
	protected boolean showButtons(FrameTitle title) {
		return (title != null) && (title.hasButtons() || showHelpButton(title));
	}

	/**
	 * Helper for rendering buttons
	 *
	 * @param	title The Frames Title
	 * @return	ElementContainer
	 */
	protected ElementContainer createButtonList(FrameTitle title) {
		
		if (!showButtons(title)) {
			return null;
		} else {
			ElementContainer container = new ElementContainer();
	
			if (title.hasButtons()) {
				Iterator iter = title.getButtons();
				while (iter.hasNext()) {
					ConcreteElement button = (ConcreteElement) iter.next();
	
					if (button != null) {
						container.addElement(new TD(button));
					}
				}
			}
	
			if (showHelpButton(title)) {
				ConcreteElement button = createHelpButton(title.getHelpId());
	
				if (button != null) {
					container.addElement(new TD(button));
				}
			}
	
			return container;
		}
	}

	/**
	 * Creates a Help-Button with the given Help Id
	 *
	 * @param		helpId the help id
	 * @return		ConcreteElement or <code>null</code> when
	 * 				no help button should be painted
	 */
	protected ConcreteElement createHelpButton(String helpId) {
		return DefPainterBase.createHelpElement(
			getPainterContext(),
			helpId,
			DefResources.BUTTON_HELP_1,
			AlignmentType.CENTER);
	}

	/**
	 * @see com.cc.framework.ui.painter.FramePainter#createInnerFrame(com.cc.framework.ui.model.InnerFrame)
	 */
	public Frame createInnerFrame(InnerFrame innerFrame) {
		Frame frame = createInnerFrame(innerFrame.showBorder());
	
		if (innerFrame.getStyleId() != null) {
			frame.setID(innerFrame.getStyleId());
		}
		
		if (innerFrame.getStyle() != null) {
			frame.setStyle(innerFrame.getStyle());
		}
		
		// set general style class
		if (innerFrame.getStyleClass() == null) {
			if (innerFrame.showBorder()) {
				frame.setClass(getElementClass(DefClassType.CLASS_INNER_FRAME));
			} else {
				frame.setClass(getElementClass(DefClassType.CLASS_INNER_FRAME_NO_BORDER));
			}
		} else {
			frame.setClass(innerFrame.getStyleClass());
		}
		
		if (innerFrame.getWidth() != null) {
			frame.setWidth(innerFrame.getWidth());
		} else {
			frame.setWidth("100%");
		}
		
		if (innerFrame.getSummary() != null) {
			frame.setSummary(innerFrame.getSummary());
		}
		
		if (innerFrame.getContent() != null) {
			frame.addBodyElement(new StringElement(innerFrame.getContent()));
		}
		
		return frame;
	}
}
