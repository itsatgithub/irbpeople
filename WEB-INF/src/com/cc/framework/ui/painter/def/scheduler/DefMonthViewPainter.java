/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/scheduler/DefMonthViewPainter.java,v 1.21 2005/10/11 11:11:24 P001002 Exp $
 * $Revision: 1.21 $
 * $Date: 2005/10/11 11:11:24 $
 *
 * ====================================================================
 *
 * Copyright (c) 2002 SCC Informationssysteme GmbH. All rights
 * reserved.
 *
 * ====================================================================
 */

package com.cc.framework.ui.painter.def.scheduler;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.util.Calendar;

import org.apache.ecs.ConcreteElement;
import org.apache.ecs.ElementContainer;
import org.apache.ecs.html.A;
import org.apache.ecs.html.TBody;
import org.apache.ecs.html.TD;
import org.apache.ecs.html.TR;
import org.apache.ecs.html.Table;

import uicomponents.scheduler.SchedulerFacadeForCommonControlsScheduler;

import com.cc.framework.ui.control.ControlActionDef;
import com.cc.framework.ui.control.ControlButton;
import com.cc.framework.ui.model.Appointment;
import com.cc.framework.ui.model.ClientHandler;
import com.cc.framework.ui.model.SchedulerScope;
import com.cc.framework.ui.model.SchedulerView;
import com.cc.framework.ui.model.imp.ClientHandlerImp;
import com.cc.framework.ui.painter.ActionPainter;
import com.cc.framework.util.CalendarHelp;

/**
 * Scheduler Painter for month view
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.21 $
 */
public class DefMonthViewPainter extends DefSchedulerViewPainter {

	/**
	 * The minimal row height
	 */
	private static final int METRIC_MIN_DAY_HEIGHT = 80;

	/**
	 * Constructor
	 */
	public DefMonthViewPainter() {
		super();
	}

	/**
	 * @see com.cc.framework.ui.painter.def.scheduler.DefSchedulerViewPainter#getDateFormat(int)
	 */
	protected DateFormat getDateFormat(int formatterId) {
		if (formatterId == DATEFORMAT_DETAIL) {
			return getDateFormat(DATEFORMAT_MONTH_OF_YEAR);
		} else {
			return super.getDateFormat(formatterId);
		}
	}

	/**
	 * Rtrieves a Tooltip Text for the given Day
	 *
	 * @param		date the Date
	 * @return		Tooltip Text
	 */
	protected String getDayTooltip(Calendar date) {
		DateFormat df = getDateFormat(DATEFORMAT_WEEKDAY);

		return df.format(date.getTime());
	}

	/**
	 * Rtrieves a Label Text for the given Day
	 *
	 * @param		date The Date
	 * @param		state The days state
	 * @return		Label Text
	 */
	protected String getDayLabel(Calendar date, int state) {
		int day = date.get(Calendar.DAY_OF_MONTH);

		if ((state & STATE_FIRST_DAY) != 0) {
			DateFormat df = getDateFormat(DATEFORMAT_DAY);
			return df.format(date.getTime());
		} else {
			return Integer.toString(day);
		}
	}

	/**
	 * This method paints one single Header Cell of the Scheduler
	 *
	 * @param		headerCell The Header cell to paint
	 * @param		dayOfWeek Tha Day for this column
	 */
	protected void doPaintDayHeader(TD headerCell, int dayOfWeek) {
		DateFormatSymbols dfs = new DateFormatSymbols(getSafeLocale());

		headerCell
			.addElement(html(dfs.getShortWeekdays()[dayOfWeek]))
			.setTitle(html(dfs.getWeekdays()[dayOfWeek]));
	}

	/**
	 * @see com.cc.framework.ui.painter.def.scheduler.DefSchedulerViewPainter#doCreateNavigationButton(boolean)
	 */
	public ConcreteElement doCreateNavigationButton(boolean up) {
		Calendar cal = (Calendar) getCtrl().getDate().clone();
		cal.set(Calendar.DAY_OF_MONTH, 1);

		if (up) {
			cal.add(Calendar.MONTH, 1);
		} else {
			cal.add(Calendar.MONTH, -1);
		}

		return createNavigationButton(cal, SchedulerView.MONTH, up);
	}

	/**
	 * Creates the Days column header row
	 *
	 * @param		firstDayOfWeek the weeks starting offset
	 * @return		Header row
	 */
	protected TR doCreateDayHeader(int firstDayOfWeek) {

		TR dayHeader = new TR();
		dayHeader.setClass("days");

		int colWidth = (int) ((100.0d / calcDayColumns()) + 0.5d);

		// Iterate all Week Days
		for (int i = 0; i < CalendarHelp.DAYS_IN_WEEK; i++) {
			int dayOfWeek = CalendarHelp.rollDay(firstDayOfWeek, i);

			if (showDay(dayOfWeek)) {
				TD headerCell = new TD();
				headerCell.setWidth(colWidth + "%");
				headerCell.setClass(getCtrl().isWorkingDay(dayOfWeek) ? "wd" : "wed");

				doPaintDayHeader(headerCell, dayOfWeek);

				dayHeader.addElement(headerCell);
			}
		}

		return dayHeader;
	}

	/**
	 * Creates the days label element
	 *
	 * @param		date the date
	 * @param		appointments List of appointments for the day
	 * @param		state the days state
	 * @return		Label element
	 */
	protected ConcreteElement doCreateDayLabel(Calendar date, Appointment[] appointments, int state) {
		ElementContainer labelContainer = new ElementContainer();

		// get the tooltip
		String tooltip = getDayTooltip(date);

		// create button
		ActionPainter ap = createActionPainter(ControlActionDef.ACTION_SELECTDATE);

		ap.addParameter(CalendarHelp.toLong(date));
		ap.addParameter(SchedulerView.DAY);
		ap.setTooltip(tooltip);
		ap.setLabel(getDayLabel(date, state));

        // old - inicio
        // old - inicio
        // old - inicio
		// labelContainer.addElement(ap.createElement());
        // old - fin
        // old - fin
        // old - fin
        
        // new - inicio        
        // new - inicio        
        // new - inicio        
        
        /*
        ap.getAction().toString(): ?ctrl=scheduler_Action_todas_las_reuniones&action=SelectDate&param=1180366336999&param=day
        ap.getTransaction(): false
        ap.getLabel(): 28. may
        ap.getStyle(): null
        ap.getTooltip(): lun, 28 may 2007
        ap.getStyleId(): null
        ap.getTarget(): null
        ap.getStyleClass(): null
        */
        
        // Creates a link to all day view, and an onclik submit operation to send all other form parameters.
        ConcreteElement link = SchedulerFacadeForCommonControlsScheduler.insideTagRefactorLink(
                ap.createElement(), 
                ap.getAction().toString());
        
        labelContainer.addElement(link);
        // new - fin
        // new - fin
        // new - fin

		if (SchedulerScope.DAY.isInMask(getCtrl().getCheckBoxMask())) {
			ClientHandler handler = new ClientHandlerImp();

			labelContainer.addElement(SchedulerViewHelp.createCheckBox(
				getPainterContext(),
				appointments,
				date,
				SchedulerScope.DAY,
				handler,
				false,
				getCtrl().getTransaction()));
		}

        /*
		// The create appointment button is external.
        if (getCtrl().showButton(ControlButton.ADDAPPOINTMENT)) {
			labelContainer.addElement(SchedulerViewHelp.createAddButton(
				getPainterContext(),
				date,
				getCtrl().getTransaction()));
		}
        */

		return labelContainer;
	}

	/**
	 * Paints the cell for the given day
	 *
	 * @param		date date
	 * @param		state days state
	 * @return		the day cell
	 */
	protected TD doCreateDayCell(Calendar date, int state) {
		Appointment[] appointments = getCtrl().getAppointments(date);

		TD dayCell = new TD();
		dayCell.setHeight(METRIC_MIN_DAY_HEIGHT);
		dayCell.setClass(getDayClass(state));

		if ((state & STATE_TODAY) != 0) {
			dayCell.setID("today");
		}

		Table t = (Table) new Table()
			.setCellSpacing(0)
			.setCellPadding(0)
			.setWidth("100%")
			.addElement(new TR()
				.addElement(new TD(doCreateDayLabel(date, appointments, state))
					.setClass("label")))
			.addElement(new TR()
				.addElement(new TD(doCreateAppointmentList(date, appointments, LABELFORMAT_SHORT))
					.setClass("apps")))
			.setStyle(getCtrl().isWorkingDay(date.get(Calendar.DAY_OF_WEEK)) ? "wd" : "wed");

		dayCell.addElement(t);

		return dayCell;
	}

	/**
	 * Calculates the number of day columns
	 *
	 * @return		The number of day columns
	 */
	protected int calcDayColumns() {
		int visible = 0;
		for (int dayOfWeek = Calendar.SUNDAY; dayOfWeek <= Calendar.SATURDAY; dayOfWeek++) {
			if (showDay(dayOfWeek)) {
				++visible;
			}
		}

		return visible;
	}

	/**
	 * @see com.cc.framework.ui.painter.def.scheduler.DefSchedulerViewPainter#doCreateBody()
	 */
	public ConcreteElement doCreateBody() {

		Table table = (Table) new Table()
			.setCellPadding(0)
			.setCellSpacing(1)
			.setBorder(0)
			.setWidth("100%")
			.setClass("monthview");

		// Create the navigation header
		ConcreteElement header = doCreateHeader();
		if (header != null) {
			table.addElement(
				new TR().addElement(new TD(header).setColSpan(calcDayColumns())));
		}

		// Create the Column Header Row
		TR headerRow = doCreateDayHeader(getFirstDayOfWeek());
		if (headerRow != null) {
			table.addElement(headerRow);
		}

		// Create the Month Table
		CalendarIterator calIter = new CalendarIterator(getCtrl().getDate());
		calIter.restart(getFirstDayOfWeek(), getDayOfWeekMask());

		// iterate all days of the current month
		TBody body = new TBody();
		table.addElement(body);

		int monthDelta	= -1;
		boolean first	= true;

		while (calIter.getMonthDelta() <= 0) {
			TR row = new TR();

			for (int i = 0; i < CalendarHelp.DAYS_IN_WEEK; i++) {
				if (showDay(calIter.getDayOfWeek())) {
					if (calIter.getMonthDelta() != monthDelta) {
						monthDelta	= calIter.getMonthDelta();
						first		= true;
					}

					// calculate the days state
					int state = getDayState(calIter, first, STATE_FIRST_MONTH | STATE_LAST_MONTH);

					TD dayCell = new TD();
					dayCell.setHeight(METRIC_MIN_DAY_HEIGHT);
					dayCell.setClass(getDayClass(state));

					if ((state & STATE_TODAY) == STATE_TODAY) {
						dayCell.setID("today");
					}

					row.addElement(doCreateDayCell(calIter.current(), state));

					first = false;
				}

				calIter.next();
			}

			// Skip hidden days, so we don't have to create a new
			// row when there is no visible day left in this month
			while (!showDay(calIter.getDayOfWeek())) {
				calIter.next();
			}
			
			body.addElement(row);
		}

		return table;
	}
}