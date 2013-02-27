/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/DefFramePainter.java,v 1.5 2005/08/24 19:17:00 P001002 Exp $
 * $Revision: 1.5 $
 * $Date: 2005/08/24 19:17:00 $
 *
 * ====================================================================
 *
 * Copyright (c) 2002 SCC Informationssysteme GmbH. All rights
 * reserved.
 *
 * ====================================================================
 */

package com.cc.framework.ui.painter.def;

import java.util.Collection;
import java.util.Vector;

import org.apache.ecs.ConcreteElement;
import org.apache.ecs.html.TD;
import org.apache.ecs.html.TR;
import org.apache.ecs.html.Table;

import com.cc.framework.ui.control.ControlActionDef;
import com.cc.framework.ui.control.ControlButton;
import com.cc.framework.ui.control.FrameControl;
import com.cc.framework.ui.model.FrameTitle;
import com.cc.framework.ui.model.InnerFrame;
import com.cc.framework.ui.painter.ActionPainter;
import com.cc.framework.ui.painter.Frame;
import com.cc.framework.ui.painter.PainterContext;

/**
 * Painter for the Frame Control
 *
 * @author		<a href="mailto:gschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version		$Revision: 1.5 $
 */
public class DefFramePainter extends DefPainterBase {

	/**
	 * Constructor
	 * 
	 * @param	painterContext	The PainterContext
	 * @param	ctrl			The Control to render
	 */
	public DefFramePainter(PainterContext painterContext, FrameControl ctrl) {
		super(painterContext);
	}

	/**
	 * Returns the Control instance
	 * 
	 * @return		Control instance
	 */
	protected FrameControl getCtrl() {
		return (FrameControl) getPainterContext().getControl();
	}

	/**
	 * Checks if the frame should be painted
	 *
	 * @return		<code>true</code> if the frame should
	 * 				be painted
	 */
	protected boolean showFrame() {
		return getCtrl().showFrame();
	}

	// ======================================
	// object painter
	// ======================================

	/**
	 * @see com.cc.framework.ui.painter.ControlPainter#getElementClass(int)
	 */
	public String getElementClass(int type) {

		switch (type) {
			case DefClassType.CLASS_CONTROL :
				if (showFrame()) {
					return DefHtmlClass.FRAMECONTROL;
				} else {
					return DefHtmlClass.FRAMECONTROL_NO_FRAME;
				}

			case DefClassType.CLASS_BODY :
				return "frcb";

			case DefClassType.CLASS_HEADER :
				return "frch";

			case DefClassType.CLASS_FOOTER :
				return "frcf";

			default :
				return super.getElementClass(type);
		}
	}

	/**
	 * Creates the Minimize-Button
	 * @return	ConcreteElement
	 */
	protected ConcreteElement doCreateMinimizeButton() {

		// create button
		ActionPainter ap = null;

		if (getCtrl().isMinimized()) {
			ap = createActionPainter(ControlActionDef.ACTION_RESTORE);
			ap.setImage(getImage(DefResources.BUTTON_RESTORE_1));
			ap.setTooltip(getFrameworkString(DefResources.FW_TOOLTIP_RESTORE));
		} else {
			ap = createActionPainter(ControlActionDef.ACTION_MINIMIZE);
			ap.setImage(getImage(DefResources.BUTTON_MINIMIZE_1));
			ap.setTooltip(getFrameworkString(DefResources.FW_TOOLTIP_MINIMIZE));
		}

		return ap.createElement();
	}

	/**
	 * Creates the Maximize-Button
	 * @return	ConcreteElement
	 */
	protected ConcreteElement doCreateMaximizeButton() {

		// create button
		ActionPainter ap = null;

		if (getCtrl().isMaximized()) {
			ap = createActionPainter(ControlActionDef.ACTION_RESTORE);
			ap.setImage(getImage(DefResources.BUTTON_RESTORE_1));
			ap.setTooltip(getFrameworkString(DefResources.FW_TOOLTIP_RESTORE));
		} else {
			ap = createActionPainter(ControlActionDef.ACTION_MAXIMIZE);
			ap.setImage(getImage(DefResources.BUTTON_MAXIMIZE_1));
			ap.setTooltip(getFrameworkString(DefResources.FW_TOOLTIP_MAXIMIZE));
		}

		return ap.createElement();
	}

	/**
	 * Creates the Close-Button
	 * @return	ConcreteElement
	 */
	protected ConcreteElement doCreateCloseButton() {
		// create button
		ActionPainter ap = createActionPainter(ControlActionDef.ACTION_CLOSE);

		ap.setImage(getImage(DefResources.BUTTON_CLOSE_1));
		ap.setTooltip(getFrameworkString(DefResources.FW_TOOLTIP_CLOSE));

		return ap.createElement();
	}

	/**
	 * Creates the buttons in the title of the frame
	 *
	 * @return		collection of ConcreteElement Items
	 */
	protected Collection doCreateTitleButtons() {
		Vector buttons = new Vector();

		if (getCtrl().showButton(ControlButton.MINIMIZE)) {
			buttons.add(doCreateMinimizeButton());
		}

		if (getCtrl().showButton(ControlButton.MAXIMIZE)) {
			buttons.add(doCreateMaximizeButton());
		}

		if (getCtrl().showButton(ControlButton.CLOSE)) {
			buttons.add(doCreateCloseButton());
		}

		return buttons;
	}

	/**
	 * Creates the froms body.
	 *
	 * @return	ConcreteElement
	 */
	protected ConcreteElement doCreateBody() {

		Table body = (Table) new Table()
			.setCellPadding(0)
			.setCellSpacing(0)
			.setWidth("100%")
			.setClass(getElementClass(DefClassType.CLASS_INNER_BODY));

		InnerFrame[] frames = getCtrl().getInnerFrames(null);
		if (frames != null) {
			for (int i = 0; i < frames.length; i++) {
				body.addElement(
					new TR(new TD(getFramePainter().createInnerFrame(frames[i]))));
			}
		}

		return body;
	}

	/**
	 * Creates the Title for the Frame
	 * 
	 * @return		Frame Title or <code>null</code>
	 */
	protected FrameTitle getFrameTitle() {
		return new FrameTitle(
			getCtrl().getImage(),
			getSmartCaption(getCtrl().getCaption(), getCtrl().getDetail()),
			getSmartDetail(getCtrl().getCaption(), getCtrl().getDetail()),
			getCtrl().getHelp(),
			doCreateTitleButtons());
	}

	/**
	 * @see com.cc.framework.ui.painter.ControlPainter#doCreateElement()
	 */
	protected ConcreteElement doCreateElement() {
		// if no designmodel is specified then terminate
		if ((getCtrl().getDesignModel() == null) || getCtrl().isClosed()) {
			return null;
		}

		// render the form
		Frame control =
			getFramePainter().createFrame(getFrameTitle(), showFrame());
	
		if (getStyleId() != null) {
			control.setID(getStyleId());
		}
	
		if (getCtrl().getStyle() != null) {
			control.setStyle(getCtrl().getStyle());
		}
	
		// set general style class
		if (getCtrl().getStyleClass() == null) {
			control.setClass(getElementClass(DefClassType.CLASS_CONTROL));
		} else {
			control.setClass(getCtrl().getStyleClass());
		}
	
		if (getCtrl().getWidth() != null) {
			control.setWidth(getCtrl().getWidth());
		}
	
		if (getCtrl().getSummary() != null) {
			control.setSummary(getCtrl().getSummary());
		}
		
		// add content elements
		if (!getCtrl().isMinimized()) {
			control.addBodyElement(doCreateBody());
		}

		return control;
	}
}
