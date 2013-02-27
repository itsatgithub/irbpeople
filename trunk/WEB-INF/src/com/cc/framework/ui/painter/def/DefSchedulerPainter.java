/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/DefSchedulerPainter.java,v 1.17 2005/11/13 14:12:45 P001002 Exp $
 * $Revision: 1.17 $
 * $Date: 2005/11/13 14:12:45 $
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
import org.apache.ecs.ElementContainer;
import org.apache.ecs.html.Span;

import com.cc.framework.ui.AlignmentType;
import com.cc.framework.ui.control.ControlActionDef;
import com.cc.framework.ui.control.ControlButton;
import com.cc.framework.ui.control.SchedulerControl;
import com.cc.framework.ui.javascript.JavaScript;
import com.cc.framework.ui.model.FrameTitle;
import com.cc.framework.ui.model.InnerFrame;
import com.cc.framework.ui.model.SchedulerView;
import com.cc.framework.ui.painter.ActionPainter;
import com.cc.framework.ui.painter.Frame;
import com.cc.framework.ui.painter.PainterContext;
import com.cc.framework.ui.painter.PainterContextAtributes;
import com.cc.framework.ui.painter.def.scheduler.DefDayViewPainter;
import com.cc.framework.ui.painter.def.scheduler.DefMonthViewPainter;
import com.cc.framework.ui.painter.def.scheduler.DefSchedulerViewPainter;
import com.cc.framework.ui.painter.def.scheduler.DefWeekViewPainter;
import com.cc.framework.ui.painter.def.scheduler.DefWorkWeekPainter;
import com.cc.framework.ui.painter.def.scheduler.DefYearViewPainter;

/**
 * Painter für das Calendar Kontrollelement
 *
 * @author		<a href="mailto:gschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version		$Revision: 1.17 $
 */
public class DefSchedulerPainter extends DefPainterBase {

	/**
	 * The internal used portion painter to paint the actual view
	 * of the scheduler
	 */
	private DefSchedulerViewPainter viewPainter = null;

	/**
	 * Construktor
	 *
	 * @param	painterContext	The PainterContext
	 * @param	ctrl			The Control to render
	 */
	public DefSchedulerPainter(PainterContext painterContext, SchedulerControl ctrl) {
		super(painterContext);
	}

	/**
	 * Returns the Control instance
	 *
	 * @return		Control
	 */
	protected SchedulerControl getCtrl() {
		return (SchedulerControl) getPainterContext().getControl();
	}

	/**
	 * This methods creates the painter for the Scheduler View
	 *
	 * @param		view The current view
	 * @return		View Painter
	 */
	protected DefSchedulerViewPainter doCreateViewPainter(SchedulerView view) {
		if (SchedulerView.DAY.equals(view)) {
			return new DefDayViewPainter();
		} else if (SchedulerView.WEEK.equals(view)) {
			return new DefWeekViewPainter();
		} else if (SchedulerView.WORKWEEK.equals(view)) {
			return new DefWorkWeekPainter();
		} else if (SchedulerView.MONTH.equals(view)) {
			return new DefMonthViewPainter();
		} else if (SchedulerView.YEAR.equals(view)) {
			return new DefYearViewPainter();
		} else {
			throw new IllegalArgumentException("Illegal ScheduleView: " + view);
		}
	}

	/**
	 * Retrieves the cached View Painter
	 *
	 * @return		SchedulerViewPainter
	 */
	protected DefSchedulerViewPainter getViewPainter() {
		if (viewPainter == null) {
			viewPainter = doCreateViewPainter(getCtrl().getView());

			// Connect the Portion Painter to it's parent control painter
			viewPainter.setControlPainter(this);
		}

		return viewPainter;
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

	/**
	 * Creates a View-Button
	 *
	 * @return	ConcreteElement
	 */
	protected ConcreteElement doCreateViewDayButton() {
		// get the tooltip
		String tooltip = getFrameworkString(DefResources.FW_TOOLTIP_SCHEDULER_DAYVIEW);

		// create button
		ActionPainter ap = createActionPainter(ControlActionDef.ACTION_VIEW);
		ap.addParameter(SchedulerView.DAY.toString());
		ap.setImage(getImage(DefResources.BUTTON_SCHEDULER_DAY1));
		ap.setTooltip(tooltip);

		return ap.createElement();
	}

	/**
	 * Creates a View-Button
	 *
	 * @return	ConcreteElement
	 */
	protected ConcreteElement doCreateViewWorkWeekButton() {
		// get the tooltip
		String tooltip = getFrameworkString(DefResources.FW_TOOLTIP_SCHEDULER_WORKWEEKVIEW);

		// create button
		ActionPainter ap = createActionPainter(ControlActionDef.ACTION_VIEW);
		ap.addParameter(SchedulerView.WORKWEEK.toString());
		ap.setImage(getImage(DefResources.BUTTON_SCHEDULER_WORKWEEK1));
		ap.setTooltip(tooltip);

		return ap.createElement();
	}

	/**
	 * Creates a View-Button
	 *
	 * @return	ConcreteElement
	 */
	protected ConcreteElement doCreateViewWeekButton() {
		// get the tooltip
		String tooltip = getFrameworkString(DefResources.FW_TOOLTIP_SCHEDULER_WEEKVIEW);

		// create button
		ActionPainter ap = createActionPainter(ControlActionDef.ACTION_VIEW);
		ap.addParameter(SchedulerView.WEEK.toString());
		ap.setImage(getImage(DefResources.BUTTON_SCHEDULER_WEEK1));
		ap.setTooltip(tooltip);

		return ap.createElement();
	}

	/**
	 * Creates a View-Button
	 *
	 * @return	ConcreteElement
	 */
	protected ConcreteElement doCreateViewMonthButton() {
		// get the tooltip
		String tooltip = getFrameworkString(DefResources.FW_TOOLTIP_SCHEDULER_MONTHVIEW);

		// create button
		ActionPainter ap = createActionPainter(ControlActionDef.ACTION_VIEW);
		ap.addParameter(SchedulerView.MONTH.toString());
		ap.setImage(getImage(DefResources.BUTTON_SCHEDULER_MONTH1));
		ap.setTooltip(tooltip);

		return ap.createElement();
	}

	/**
	 * Creates a View-Button
	 *
	 * @return	ConcreteElement
	 */
	protected ConcreteElement doCreateViewYearButton() {
		// get the tooltip
		String tooltip = getFrameworkString(DefResources.FW_TOOLTIP_SCHEDULER_YEARVIEW);

		// create button
		ActionPainter ap = createActionPainter(ControlActionDef.ACTION_VIEW);
		ap.addParameter(SchedulerView.YEAR.toString());
		ap.setImage(getImage(DefResources.BUTTON_SCHEDULER_YEAR1));
		ap.setTooltip(tooltip);

		return ap.createElement();
	}

	/**
	 * Creates the New-Button
	 *
	 * @return	ConcreteElement
	 */
	protected ConcreteElement doCreateCreateButton() {
		// get the tooltip
		String tooltip = getFrameworkString(DefResources.FW_TOOLTIP_CREATE_APPOINTMENT);

		// create button
		ActionPainter ap = createActionPainter(ControlActionDef.ACTION_CREATE);

		ap.setImage(getImage(DefResources.BUTTON_SCHEDULER_CREATE1));
		ap.setTooltip(tooltip);

		return ap.createElement();
	}

	/**
	 * Creates the Refresh-Button
	 *
	 * @return	ConcreteElement
	 */
	protected ConcreteElement doCreateRefreshButton() {
		// get the tooltip
		String tooltip = getFrameworkString(DefResources.FW_TOOLTIP_REFRESH_SCHEDULER);

		// create button
		ActionPainter ap = createActionPainter(ControlActionDef.ACTION_REFRESH);

		ap.setImage(getImage(DefResources.BUTTON_REFRESH_1));
		ap.setTooltip(tooltip);

		return ap.createElement();
	}

	/**
	 * Creates the Export-Button
	 * 
	 * @param empty
	 *            Indicates an empty list
	 * @return ConcreteElement
	 */
	protected ConcreteElement doCreateExportButton(boolean empty) {
		if (empty) {
			return createImage(DefResources.BUTTON_EXPORT_2);
		} else {
			// get the tooltip
			String tooltip = getFrameworkString(DefResources.FW_TOOLTIP_EXPORT_LIST);
	
			// create button
			ActionPainter ap = createActionPainter(ControlActionDef.ACTION_EXPORTLIST);
	
			ap.setImage(getImage(DefResources.BUTTON_EXPORT_1));
			ap.setTooltip(tooltip);
	
			return ap.createElement();
		}
	}

	/**
	 * Creates the Print-Button
	 * 
	 * @param empty
	 *            Indicates an empty list
	 * @return ConcreteElement
	 */
	protected ConcreteElement doCreatePrintButton(boolean empty) {
		if (empty) {
			return createImage(DefResources.BUTTON_PRINT_2);
		} else {
			// get the tooltip
			String tooltip = getFrameworkString(DefResources.FW_TOOLTIP_PRINT_LIST);
	
			// create button
			ActionPainter ap = createActionPainter(ControlActionDef.ACTION_PRINTLIST);
	
			ap.setImage(getImage(DefResources.BUTTON_PRINT_1));
			ap.setTooltip(tooltip);
	
			return ap.createElement();
		}
	}

	/**
	 * Creates the buttons in the frame header
	 *
	 * @param		viewPainter The current view painter
	 * @return		collection of ConcreteElement Items
	 */
	protected Collection doCreateTitleButtons(DefSchedulerViewPainter viewPainter) {
		Vector buttons = new Vector();

        /*
		if (getCtrl().showButton(ControlButton.CREATE)) {
			buttons.add(doCreateCreateButton());
		}
        */

		if (getCtrl().showButton(ControlButton.REFRESH)) {
			buttons.add(doCreateRefreshButton());
		}

		if (getCtrl().showButton(ControlButton.VIEW)) {
			int viewMask = getCtrl().getViewMask();

			if (SchedulerView.DAY.isInMask(viewMask)) {
				buttons.add(doCreateViewDayButton());
			}

			if (SchedulerView.WORKWEEK.isInMask(viewMask)) {
				buttons.add(doCreateViewWorkWeekButton());
			}

			if (SchedulerView.WEEK.isInMask(viewMask)) {
				buttons.add(doCreateViewWeekButton());
			}

			if (SchedulerView.MONTH.isInMask(viewMask)) {
				buttons.add(doCreateViewMonthButton());
			}

			if (SchedulerView.YEAR.isInMask(viewMask)) {
				buttons.add(doCreateViewYearButton());
			}
		}

		if (showFrame() && getCtrl().showButton(ControlButton.ROLL)) {
			buttons.add(viewPainter.doCreateNavigationButton(false));
			buttons.add(viewPainter.doCreateNavigationButton(true));
		}

		// Additional Buttons
		boolean empty = false;

		if (getCtrl().showButton(ControlButton.EXPORTLIST)) {
			buttons.add(doCreateExportButton(empty));
		}

		// Additional "Print" Button
		if (getCtrl().showButton(ControlButton.PRINTLIST)) {
			buttons.add(doCreatePrintButton(empty));
		}

		return buttons;
	}

	/**
	 * This method creates the body of the scheduler control
	 *
	 * @return		ConcreteElement
	 */
	protected ConcreteElement doCreateBody() {
		return getViewPainter().doCreateView();
	}

	/**
	 * Creates an additional header.
	 *
	 * @return		ConcreteElement
	 */
	protected ConcreteElement doCreateHeader() {
		return doCreateFrames(AlignmentType.TOP);
	}

	/**
	 * Creates an additional footer.
	 *
	 * @return		ConcreteElement
	 */
	protected ConcreteElement doCreateFooter() {
		return doCreateFrames(AlignmentType.BOTTOM);
	}

	/**
	 * Creates a frame container with all frames that match
	 * the given filter
	 *
	 * @param		alignmentFilter the filter
	 * @return		Frame container or <code>null</code>
	 */
	protected ConcreteElement doCreateFrames(AlignmentType alignmentFilter) {
		InnerFrame[] frames = getCtrl().getInnerFrames(alignmentFilter);

		if ((frames == null) || (frames.length == 0)) {
			return null;
		} else {
			ElementContainer ec = new ElementContainer();
			for (int i = 0; i < frames.length; i++) {
				ec.addElement(getFramePainter().createInnerFrame(frames[i]));
			}

			return ec;
		}
	}

	/**
	 * Creates the Title for the Frame
	 *
	 * @return		Frame Title or <code>null</code>
	 */
	protected FrameTitle getFrameTitle() {
		return new FrameTitle(
			getCtrl().getImage(),
			localize(getCtrl().getTitle()),
			getViewPainter().getDetail(),
			getCtrl().getHelp(),
			doCreateTitleButtons(getViewPainter()));
	}

	/**
	 * @see com.cc.framework.ui.painter.ControlPainter#doCreateElement()
	 */
	protected ConcreteElement doCreateElement() {

		// if no designmodel is specified then terminate
		if (getCtrl().getDesignModel() == null) {
			return null;
		}

		// ensure that a style id will be generated
		getPainterContext().setAttribute(
			PainterContextAtributes.FORCE_STYLEID, Boolean.TRUE);

		// render the control
		Frame control =
			getFramePainter().createFrame(getFrameTitle(), showFrame());

		if (getCtrl().getStyle() != null) {
			control.setStyle(getCtrl().getStyle());
		}

		if (getStyleId() != null) {
			control.setID(getStyleId());
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

		if (getCtrl().getHeight() != null) {
			control.setHeight(getCtrl().getHeight());
		}

		if (getCtrl().getSummary() != null) {
			control.setSummary(getCtrl().getSummary());
		}

		// Add body Elements
		control
			.addBodyElement(doCreateHeader())
			.addBodyElement(doCreateBody())
			.addBodyElement(doCreateFooter());

		// embed the control into an html span element
		Span span = new Span();
		span.addElement(control);
		span.setID("sh_" + getStyleId());

		// optional script
		ConcreteElement script = doCreateScript();
		if (script != null) {
			span.addElement(script);
		}

		return span;
	}

	/**
	 * @see com.cc.framework.ui.painter.ControlPainter#getElementClass(int)
	 */
	public String getElementClass(int type) {

		switch (type) {
			case DefClassType.CLASS_CONTROL :
				return showFrame() ? DefHtmlClass.SCHEDULERCONTROL : DefHtmlClass.SCHEDULERCONTROL_NO_FRAME;
			default :
				return super.getElementClass(type);
		}
	}

	/**
	 * Creates the Java Script Code wihich is needed by
	 * the control
	 *
	 * @return		Java Script Code
	 */
	protected JavaScript doCreateScript() {
		return null;
	}
}
