/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/scheduler/DefWeekViewPainter.java,v 1.21 2005/09/27 16:12:59 P001002 Exp $
 * $Revision: 1.21 $
 * $Date: 2005/09/27 16:12:59 $
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
import java.util.Calendar;

import org.apache.ecs.AlignType;
import org.apache.ecs.ConcreteElement;
import org.apache.ecs.ElementContainer;
import org.apache.ecs.Entities;
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
 * Scheduler Painter for Week View
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.21 $
 */
public class DefWeekViewPainter extends DefSchedulerViewPainter {

	/**
	 * The minimal row height
	 */
	private static final int METRIC_MIN_DAY_HEIGHT = 80;

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
	 * @param		date the Date
	 * @return		Label Text
	 */
	protected String getDayLabel(Calendar date) {
		DateFormat df = getDateFormat(DATEFORMAT_WEEKDAY);

		return df.format(date.getTime());
	}

	/**
	 * @see com.cc.framework.ui.painter.def.scheduler.DefSchedulerViewPainter#getDateFormat(int)
	 */
	protected DateFormat getDateFormat(int formatterId) {
		if (formatterId == DATEFORMAT_DETAIL) {
			return getDateFormat(DATEFORMAT_WEEK);
		} else {
			return super.getDateFormat(formatterId);
		}
	}

	/**
	 * Creates the day label
	 *
	 * @param		date Date
	 * @param		appointments List of appointments for the day
	 * @return		Day Lable
	 */
	protected ConcreteElement doCreateDayLabel(Calendar date, Appointment[] appointments) {
		ElementContainer labelContainer = new ElementContainer();

		// get the tooltip
		String tooltip = getDayTooltip(date);

		// create button
		ActionPainter ap = createActionPainter(ControlActionDef.ACTION_SELECTDATE);

		ap.addParameter(CalendarHelp.toLong(date));
		ap.addParameter(SchedulerView.DAY);
		ap.setTooltip(tooltip);
		ap.setLabel(getDayLabel(date));

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
	 * Creates the element for the diven day
	 *
	 * @param		date Date
	 * @return		Day Element
	 */
	protected ConcreteElement doCreateDay(Calendar date) {

		Appointment[] appointments = getCtrl().getAppointments(date);
		
		Table t = new Table()
			.setCellSpacing(0)
			.setCellPadding(0)
			.setWidth("100%")
			.addElement(new TR()
				.addElement(new TD(doCreateDayLabel(date, appointments))
					.setClass("label")))
			.addElement(new TR()
				.addElement(new TD()
					.addElement(doCreateAppointmentList(date, appointments, LABELFORMAT_SHORT))
					.setClass("apps")));

		return t;
	}

	/**
	 * Creates the cells for the days of the week
	 *
	 * @return		7 Day cells
	 */
	protected TD[] doCreateDayCells() {

		Calendar cal = (Calendar) getCtrl().getDate().clone();
		cal.set(Calendar.DAY_OF_WEEK, getCtrl().getFirstDayOfWeek());
		
		// Pre-render the 7 days of the week
		TD[] days = new TD[Calendar.SATURDAY + 1];

		for (int i = 0; i < 7; i++) {
			int day = cal.get(Calendar.DAY_OF_WEEK);

			days[day] = new TD(doCreateDay(cal));
			days[day].setClass("day");
			days[day].setWidth("50%");

			if (markAsToday(cal)) {
				days[day].setID("today");
			}

			if (CalendarHelp.isWeekEndDay(day)) {
				days[day].setHeight(METRIC_MIN_DAY_HEIGHT / 2);
			} else {
				days[day].setHeight(METRIC_MIN_DAY_HEIGHT);
			}
			
			cal.add(Calendar.DAY_OF_YEAR, 1);
		}

		return days;
	}

	/**
	 * @see com.cc.framework.ui.painter.def.scheduler.DefSchedulerViewPainter#doCreateBody()
	 */
	public ConcreteElement doCreateBody() {
		Table body = (Table) new Table()
			.setCellPadding(0)
			.setCellSpacing(1)
			.setBorder(0)
			.setWidth("100%")
			.setClass("weekview");

		TD[] days = doCreateDayCells();

		// Create the navigation header
		ConcreteElement header = doCreateHeader();
		if (header != null) {
			body.addElement(
				new TR().addElement(new TD(header).setColSpan(2)));
		}

		int firstDayOfWeek = getCtrl().getFirstDayOfWeek();
		boolean compressWeekends = getCtrl().compressWeekEnds()
				&& (getCtrl().getFirstDayOfWeek() != Calendar.SUNDAY);

		if (compressWeekends) {
			body
				.addElement(new TR()
					.setVAlign(AlignType.top)
					.addElement(days[Calendar.MONDAY])
					.addElement(days[Calendar.THURSDAY]))
				.addElement(new TR()
					.setVAlign(AlignType.top)
					.addElement(days[Calendar.TUESDAY])
					.addElement(days[Calendar.FRIDAY]))
				.addElement(new TR()
					.setVAlign(AlignType.top)
					.addElement(days[Calendar.WEDNESDAY]
						.setRowSpan(2))
					.addElement(days[Calendar.SATURDAY]))
				.addElement(new TR()
					.setVAlign(AlignType.top)
					.addElement(days[Calendar.SUNDAY]));
		} else {
			body
				.addElement(new TR()
					.setVAlign(AlignType.top)
					.addElement(days[adjustDayIndex(firstDayOfWeek + 0)])
					.addElement(days[adjustDayIndex(firstDayOfWeek + 4)]))
				.addElement(new TR()
					.setVAlign(AlignType.top)
					.addElement(days[adjustDayIndex(firstDayOfWeek + 1)])
					.addElement(days[adjustDayIndex(firstDayOfWeek + 5)]))
				.addElement(new TR()
					.setVAlign(AlignType.top)
					.addElement(days[adjustDayIndex(firstDayOfWeek + 2)])
					.addElement(days[adjustDayIndex(firstDayOfWeek + 6)]))
				.addElement(new TR()
					.setVAlign(AlignType.top)
					.addElement(days[adjustDayIndex(firstDayOfWeek + 3)])
					.addElement(new TD(Entities.NBSP).setClass("hidden")));
		}

		return body;
	}

	protected int adjustDayIndex(int index) {
		if (index > Calendar.SATURDAY) {
			return index - Calendar.SATURDAY;
		} else {
			return index;
		}
	}
	
	/**
	 * @see com.cc.framework.ui.painter.def.scheduler.DefSchedulerViewPainter#doCreateNavigationButton(boolean)
	 */
	public ConcreteElement doCreateNavigationButton(boolean up) {
		Calendar cal = (Calendar) getCtrl().getDate().clone();

		if (up) {
			cal.add(Calendar.WEEK_OF_YEAR, 1);
		} else {
			cal.add(Calendar.WEEK_OF_YEAR, -1);
		}

		return createNavigationButton(cal, SchedulerView.WEEK, up);
	}
}