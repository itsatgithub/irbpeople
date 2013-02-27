/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/scheduler/DefDayViewPainter.java,v 1.23 2005/11/26 13:52:58 P001002 Exp $
 * $Revision: 1.23 $
 * $Date: 2005/11/26 13:52:58 $
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
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Vector;

import org.apache.ecs.ConcreteElement;
import org.apache.ecs.ElementContainer;
import org.apache.ecs.Entities;
import org.apache.ecs.StringElement;
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
import com.cc.framework.ui.model.imp.AppointmentComparator;
import com.cc.framework.ui.model.imp.ClientHandlerImp;
import com.cc.framework.ui.painter.ActionPainter;
import com.cc.framework.ui.painter.def.DefResources;
import com.cc.framework.util.CalendarHelp;

/**
 * The Day View Calendar Painter
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.23 $
 */
public class DefDayViewPainter extends DefSchedulerViewPainter {

	/**
	 * The width of the "hour" section
	 */
	private static final int METRIC_HOUR_WIDTH		= 20;

	/**
	 * The width of the "minute" section
	 */
	private static final int METRIC_INTERVAL_WIDTH	= 15;

	/**
	 * The hight of one row
	 */
	private static final int METRIC_LINE_HIGHT		= 22;

	/**
	 * Relative to the top of the time table
	 */
	private static final int POS_REL_TOP			= 1;

	/**
	 * Relative to the bottom of the time table
	 */
	private static final int POS_REL_BOTTOM			= 2;

	/**
	 * Column Layout Information
	 */
	protected class DayInfo {

		/**
		 * The Days date
		 */
		private Calendar date;

		/**
		 * The Day of the week
		 */
		private int dayOfWeek = 0;

		/**
		 * Collection of all appointments
		 */
		private Appointment[] appointments;

		/**
		 * Collection of "allday" events
		 */
		private Vector allDayEvents = new Vector();

		/**
		 * Collection of normal appointments
		 */
		private Vector events = new Vector();

		/**
		 * The number of columns required to layout
		 * all appointments for this day
		 */
		private int columns = 1;

		/**
		 * Creats and initializes the Information about the given day
		 * 
		 * @param date
		 *            The days date
		 * @param appointments
		 *            The appointment list for that day
		 */
		public DayInfo(Calendar date, Appointment[] appointments) {
			this.date			= date;
			this.dayOfWeek		= date.get(Calendar.DAY_OF_WEEK);
			this.appointments	= appointments;

			if ((appointments != null) && (appointments.length > 0)) {
				Arrays.sort(appointments, new AppointmentComparator());

				// This array is used to assign each appointment a collumn where
				// it is not overlapping another appointment. We initialize the
				// array to the possible maximum number of columns
				// -> that is all appointments are in the same column!
				int[] columnCoverage				= new int[appointments.length];
				AppointmentInfo[] columnAssigment	= new AppointmentInfo[appointments.length];

				// process all appointments of the day
				for (int i = 0; i < appointments.length; i++) {

					if (appointments[i].isAllDayEvent() || appointments[i].isMultiDayEvent()) {
						// Add to "allday" events
						allDayEvents.add(appointments[i]);
					} else {
						// Add to normal events
						AppointmentInfo appointment =
							new AppointmentInfo(
								appointments[i],
								getCtrl().getDayStartHour(),
								getCtrl().getDayEndHour(),
								getCtrl().getInterval());

						events.add(appointment);

						if (appointment.isVisible()) {
							// Search the next free column for the appointment ai[i]
							for (int j = 0; j <= i; j++) {
								if (columnCoverage[j] <= appointment.getStartRow()) {
									// Assign the column
									appointment.setColumn(j);

									// Remember how long this column will be
									// occupied by this appointment
									columnAssigment[j]	= appointment;
									columnCoverage[j]	= appointment.getEndRow();

									if (j > 0) {
										// The previous appointment can only
										// span one column!
										columnAssigment[j - 1].setColSpan(1);
									}

									// Check if there is any restrictions for the
									// column width of the appointment
									// (we do not know the final number of columns
									//  at this point)
									int k = j + 1;
									while ((k < columns) && (columnCoverage[k] <= appointment.getStartRow())) {
										k++;
									}

									// only constraint the number of columns when
									// necessary!
									if (k < columns) {
										columnAssigment[j].setColSpan(k - j);

										for (int l = j + 1; l < k; l++) {
											columnAssigment[l]	= columnAssigment[j];
											columnCoverage[l]	= columnCoverage[j];
										}
									}

									// update the column count for this day
									columns = Math.max(columns, j + 1);
									break;
								}
							}
						}
					}
				}

				// Adjust the column spans
				Iterator i = events.iterator();
				while (i.hasNext()) {
					AppointmentInfo app = (AppointmentInfo) i.next();

					if (app.isVisible() && (app.getColSpan() == -1)) {
						app.setColSpan(columns - app.getColumn());
					}
				}
			}
		}

		/**
		 * Retrieves the raw apppointment list for this day
		 * 
		 * @return Appointment list
		 */
		public Appointment[] getAppointments() {
			return appointments;
		}

		/**
		 * @return The total number of appointments
		 */
		public int getCount() {
			if (appointments == null) {
				return 0;
			} else {
				return appointments.length;
			}
		}

		/**
		 * Counts the number of visible appointments
		 * 
		 * @return Numer of appointments
		 */
		public int getVisibleCount() {
			int out = 0;

			Iterator i = events.iterator();
			while (i.hasNext()) {
				AppointmentInfo app = (AppointmentInfo) i.next();

				if (app.isVisible()) {
					++out;
				}
			}

			return out;
		}

		/**
		 * Counts the number of appointments that are out of range
		 * 
		 * @param pos
		 *            The position of the appointment relative to the bounds of
		 *            the time table (POS_REL_xxx)
		 * @return Numer of appointments
		 */
		public int getAppointmentsOutOfBoundsCount(int pos) {
			int out = 0;

			Iterator i = events.iterator();
			while (i.hasNext()) {
				AppointmentInfo app = (AppointmentInfo) i.next();

				if (!app.isVisible() && (app.getPosition() == pos)) {
					++out;
				}
			}

			return out;
		}

		/**
		 * Retrieves a list of appointments which are out of bounds
		 * 
		 * @param pos
		 *            The position of the appointment relative to the bounds of
		 *            the time table (POS_REL_xxx)
		 * @return Appointment List
		 */
		public Appointment[] getAppointmentsOutOfBounds(int pos) {
			Vector out = new Vector();

			Iterator i = events.iterator();
			while (i.hasNext()) {
				AppointmentInfo app = (AppointmentInfo) i.next();

				if (!app.isVisible() && (app.getPosition() == pos)) {
					out.add(app.getAppointment());
				}
			}

			return (Appointment[]) out.toArray(new Appointment[out.size()]);
		}

		/**
		 * Returns only the "allday" appointments for this day
		 * 
		 * @return Appointment collection
		 */
		public Appointment[] getAllDayEvents() {
			return (Appointment[]) allDayEvents.toArray(
				new Appointment[allDayEvents.size()]);
		}

		/**
		 * @return The days date
		 */
		public Calendar getDate() {
			return date;
		}

		/**
		 * @return The number of columns required to layout all appointments for
		 *         the day
		 */
		public int getColumns() {
			return columns;
		}

		/**
		 * This method checks if the given hour is a working hour
		 * 
		 * @param hour
		 *            The hour to check
		 * @return <code>true</code> if the hour is a working hour
		 */
		public boolean isWorkingHour(int hour) {
			return getCtrl().isWorkingHour(dayOfWeek, hour);
		}

		/**
		 * Searches for the appointment that starts in the given column at the
		 * given time and advances the internal iterator
		 * 
		 * @param column
		 *            Column
		 * @param rowIndex
		 *            the row that the appointment should span
		 * @return returns anappointment or <code>null</code>
		 */
		public AppointmentInfo find(
			int column,
			int rowIndex) {
	
			Iterator i = events.iterator();
			while (i.hasNext()) {
				AppointmentInfo app = (AppointmentInfo) i.next();
				
				if (app.showInCell(rowIndex, column)) {
					return app;
				}
			}

			// No matching element found
			return null;
		}
	}

	/**
	 * This class adds some layout information to an
	 * appointment
	 */
	protected class AppointmentInfo {

		/**
		 * The Appointment
		 */
		private Appointment appointment;

		/**
		 * The hour where the appointment starts
		 */
		private int startHour = 0;

		/**
		 * The interval where the appointment starts
		 */
		private int startInterval = 0;

		/**
		 * The row index where the appointment starts
		 */
		private int startRow = 0;

		/**
		 * The number of rows spanned by this appointment
		 */
		private int rowCount = 0;

		/**
		 * The visible start row
		 */
		private int visibleStartRow = 0;

		/**
		 * The number of visible rows
		 */
		private int visibleRows = 0;

		/**
		 * Indicates the "out of bounds" position of the
		 * appointment
		 */
		private int position = 0;

		/**
		 * The column index for this appointment
		 */
		private int column = -1;

		/**
		 * The number of columns spanned by this appointment
		 */
		private int colspan = -1;

		/**
		 * Constructor. Creates a new Appointment Wrapper and calculates the
		 * correct row index
		 * 
		 * @param appointment
		 *            the appointment
		 * @param dayStartHour
		 *            The current day start hour
		 * @param dayEndHour
		 *            The current day end hour
		 * @param interval
		 *            The current interval setting
		 */
		public AppointmentInfo(Appointment appointment, int dayStartHour, int dayEndHour, int interval) {
			super();

			this.appointment	= appointment;

			// This is a one Day event.
			// So we can ignore the date at this point

			// Index calculation
			int sh				= appointment.getStartTime().get(Calendar.HOUR_OF_DAY);
			int sm				= appointment.getStartTime().get(Calendar.MINUTE);

			int eh				= appointment.getEndTime().get(Calendar.HOUR_OF_DAY);
			int em				= appointment.getEndTime().get(Calendar.MINUTE);

			// Calculate the start row of the appointment
			startHour			= sh;
			startInterval		= sm / interval;

			startRow			= (sh * 60 / interval) + (sm / interval);

			// Calculate the last row of the appointment
			int endRow			= (eh * 60 / interval) + ((em + interval - 1) / interval);
			rowCount			= endRow - startRow;

			// An appointment should at least span one row
			rowCount			= Math.max(1, rowCount);

			// Calculate the visible portion of the appointment
			// -> Adjust for the day offset
			visibleStartRow		= startRow - (dayStartHour * 60 / interval);

			// Calculate the number of visible rows
			if (visibleStartRow >= 0) {
				visibleRows		= rowCount;
			} else {
				// Adust the number of visible rows
				visibleRows		= Math.max(0, rowCount + visibleStartRow);
				position		= POS_REL_TOP;
			}

			int maxRowCount		= ((dayEndHour - dayStartHour + 1) * 60 / interval);
			if (visibleStartRow + visibleRows > maxRowCount) {
				visibleRows		= Math.max(0, maxRowCount - visibleStartRow);
				position		= POS_REL_BOTTOM;
			}
		}

		/**
		 * Returns the starting row of the appointment (absolute)
		 * 
		 * @return Starting row
		 */
		public int getStartRow() {
			return startRow;
		}

		/**
		 * Checks if the appointment starts at the given time coordinates
		 * 
		 * @param hour
		 *            the start hour to check
		 * @param interval
		 *            the Minutes to check
		 * @return <code>true</code> if the appointment starts at the
		 *         specified time coordinates
		 */
		public boolean startsAt(int hour, int interval) {
			return ((hour > startHour) || ((hour == startHour) && (interval >= startInterval)));
		}

		/**
		 * Checks if the appointment is shown in the given row of the time table
		 * 
		 * @param row
		 *            The row index
		 * @param col
		 *            The column
		 * @return <code>true</code> if the appointment overlays the specified
		 *         row
		 */
		public boolean showInCell(int row, int col) {
			return isVisible() && (column == col) && (startRow <= row)
					&& (startRow + rowCount > row);
		}

		/**
		 * Retrieves the last row that is covered by this appointment(absolute)
		 * 
		 * @return Index of the last covered row
		 */
		public int getEndRow() {
			return startRow + rowCount;
		}

		/**
		 * @return returns the column where this appointment can be rendered
		 *         without overlapping another appointment
		 */
		public int getColumn() {
			return column;
		}

		/**
		 * Assigns a free column to the appointment
		 * 
		 * @param index
		 *            the columnindex
		 */
		public void setColumn(int index) {
			this.column = index;
		}

		/**
		 * Returns the starting row relativ to the first visible hour of the
		 * time table
		 * 
		 * @return Starting row
		 */
		public int getVisibleStartRow() {
			return startRow;
		}

		/**
		 * This Attribute indicates the number of rows to span for this
		 * appointment.
		 * 
		 * @return The Rows to span
		 */
		public int getVisibleRows() {
			return visibleRows;
		}

		/**
		 * Checks if this appointment is visible
		 * 
		 * @return <code>true</code> if the appointment is visible
		 */
		public boolean isVisible() {
			return visibleRows > 0;
		}

		/**
		 * @return The Appointment itself
		 */
		public Appointment getAppointment() {
			return appointment;
		}

		/**
		 * @return The position of the appointment relative to the bounds of the
		 *         time table
		 */
		public int getPosition() {
			return position;
		}

		/**
		 * @return The number of columns spanned by this appointment
		 */
		public int getColSpan() {
			return colspan;
		}

		/**
		 * @param colspan
		 *            The number of columns spanned by this appointment
		 */
		public void setColSpan(int colspan) {
			this.colspan = colspan;
		}
		
		/**
		 * @see java.lang.Object#toString()
		 */
		public String toString() {
			return appointment.getSubject();
		}
	}

	/**
	 * Creates the information objects for all visible days
	 * 
	 * @param dates
	 *            Days
	 * @return Information Objects
	 */
	private DayInfo[] createDayInfo(Calendar[] dates) {
		DayInfo[] columns = new DayInfo[dates.length];

		for (int i = 0; i < columns.length; i++) {
			columns[i] =
				new DayInfo(dates[i], getCtrl().getAppointments(dates[i]));
		}

		return columns;
	}

	/**
	 * @see com.cc.framework.ui.painter.def.scheduler.DefSchedulerViewPainter#getDateFormat(int)
	 */
	protected DateFormat getDateFormat(int formatterId) {
		if (formatterId == DATEFORMAT_DETAIL) {
			return getDateFormat(DATEFORMAT_WEEKDAY);
		} else {
			return super.getDateFormat(formatterId);
		}
	}

	/**
	 * This method is called to retrieve a list of visible Days for the Day view
	 * 
	 * @return Array with Date Objects
	 */
	protected Calendar[] getDays() {
		return new Calendar[] { getCtrl().getDate() };
	}

	/**
	 * Rtrieves a Tooltip Text for the given Day
	 * 
	 * @param date
	 *            the Date
	 * @return Tooltip Text
	 */
	protected String getDayTooltip(Calendar date) {
		DateFormat df = getDateFormat(DATEFORMAT_WEEKDAY);

		return df.format(date.getTime());
	}

	/**
	 * Rtrieves a Label Text for the given Day
	 * 
	 * @param date
	 *            the Date
	 * @return Label Text
	 */
	protected String getDayLabel(Calendar date) {
		DateFormat df = getDateFormat(DATEFORMAT_WEEKDAY_SHORT);

		return df.format(date.getTime());
	}

	/**
	 * This method is called to create the labels for the hour time bar
	 * 
	 * @param date
	 *            the date and time
	 * @return hour string
	 */
	protected String getHourLabel(Calendar date) {
		DateFormat df = getDateFormat(DATEFORMAT_HOUR);
		
		return df.format(date.getTime());
	}

	/**
	 * Creates the days label element
	 * 
	 * @param dayInfo
	 *            the information about the day
	 * @return Label element
	 */
	protected ConcreteElement doCreateDayLabel(DayInfo dayInfo) {
		ElementContainer labelContainer = new ElementContainer();

		// get the tooltip
		String tooltip = getDayTooltip(dayInfo.getDate());

		// create button
		ActionPainter ap = createActionPainter(ControlActionDef.ACTION_SELECTDATE);

		ap.addParameter(CalendarHelp.toLong(dayInfo.getDate()));
		ap.addParameter(SchedulerView.DAY);
		ap.setTooltip(tooltip);
		ap.setLabel(getDayLabel(dayInfo.getDate()));

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
				dayInfo.getAppointments(),
				dayInfo.getDate(),
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
				dayInfo.getDate(),
				getCtrl().getTransaction()));
		}
        */
		
		return labelContainer;
	}

	/**
	 * Creates the header with the day labels for a multi day view
	 * 
	 * @param days
	 *            Days
	 * @return Header Row
	 */
	protected ConcreteElement doCreateDayHeader(DayInfo[] days) {
		TR dayHeader = new TR();

		// Add the static columns for the time bar
		dayHeader
			.addElement(new TD(Entities.NBSP)
				.setColSpan(2))
			.setClass("days");

		// Create a day header for each day column
		for (int i = 0; i < days.length; i++) {
			dayHeader
				.addElement(new TD(doCreateDayLabel(days[i]))
					.setColSpan(days[i].getColumns()));
		}

		return dayHeader;
	}

	/**
	 * Creates the row with metric information that givs the table its column
	 * layout
	 * 
	 * @param days
	 *            the visible days list
	 * @return the metric row
	 */
	protected ConcreteElement doCreateMetricRow(DayInfo[] days) {
		TR metricRow = new TR();

		int totalRows = getTotalRows(days);

		// Add the static columns for the time bar
		metricRow
			.addElement(new TD()
				.setHeight(0)
				.setWidth(METRIC_HOUR_WIDTH))
			.addElement(new TD()
				.setWidth(METRIC_INTERVAL_WIDTH))
			.addElement(new TD(createSpacer(-1, 1))
				.setRowSpan(totalRows))
			.setClass("metric");

		// Add the required colums for each day
		// -> the number of columns depends on the
		//    appointments for this day

		for (int day = 0; day < days.length; day++) {
			if (day > 0) {
				// Add a separator column
				metricRow
					.addElement(new TD(createSpacer(-1, 1))
						.setRowSpan(totalRows));
			}

			for (int col = 0; col < days[day].getColumns(); col++) {
				metricRow
					.addElement(new TD()
						.setWidth((100 / (days.length * days[day].getColumns())) + "%"));
			}
		}

		return metricRow;
	}

	/**
	 * Creates the row with the "allday" areas for all visible days
	 * 
	 * @param days
	 *            the visible days list
	 * @return the allday row
	 */
	protected ConcreteElement doCreateAllDayArea(DayInfo[] days) {

		TR allDay = new TR();
		allDay.setClass("allday");

		// Add the static columns for the time bar
		allDay
			.addElement(new TD(Entities.NBSP)
				.setColSpan(2));

		// Create an "allday" cell for each day column
		for (int i = 0; i < days.length; i++) {
			allDay
				.addElement(new TD(doCreateAppointmentList(
						days[i].getDate(),
						days[i].getAllDayEvents(),
						LABELFORMAT_SHORT))
					.setColSpan(days[i].getColumns()));
		}

		return allDay;
	}

	/**
	 * Counts the number of appointments that are out of range
	 * 
	 * @param days
	 *            the visible days list
	 * @param pos
	 *            The position of the appointment relative to the bounds of the
	 *            time table (POS_REL_xxx)
	 * @return Numer of appointments
	 */
	protected int getAppointmentsOutOfBoundsCount(DayInfo[] days, int pos) {
		int out = 0;

		for (int i = 0; i < days.length; i++) {
			out += days[i].getAppointmentsOutOfBoundsCount(pos);
		}

		return out;
	}

	/**
	 * Calculates the total number of html table rows for the day view table
	 * 
	 * @param days
	 *            the visible days list
	 * @return number of table rows
	 */
	protected int getTotalRows(DayInfo[] days) {
		int rows		= 0;
		int hours		= getCtrl().getDayEndHour() - getCtrl().getDayStartHour() + 1;
		int rowsPerHour	= 60 / getCtrl().getInterval();

		// add all rows for the time table
		rows += hours * rowsPerHour;

		// add one row for the metric row
		++rows;

		// one row for the day header
		if (days.length > 1) {
			++rows;
		}

		// add one row for the "allday" area
		if (getCtrl().showAllDayArea()) {
			++rows;
		}

		// add on row for the "more" indicators
		if (getAppointmentsOutOfBoundsCount(days, POS_REL_TOP) > 0) {
			++rows;
		}

		if (getAppointmentsOutOfBoundsCount(days, POS_REL_BOTTOM) > 0) {
			++rows;
		}

		return rows;
	}

	/**
	 * Calculates the total number of html table columns (TD's) for the day view
	 * table
	 * 
	 * @param days
	 *            the visible days list
	 * @return number of table columns
	 */
	protected int getTotalColumns(DayInfo[] days) {
		int cols = 0;

		// add fixed columns
		cols += 3;

		// add appointment columns
		cols += getAppointmentColumns(days);

		// add separator columns
		cols += days.length - 1;

		return cols;
	}

	/**
	 * Calculates the number of html table columns (TD's) that are necessary to
	 * display the appointments
	 * 
	 * @param days
	 *            the visible days list
	 * @return number of table columns
	 */
	protected int getAppointmentColumns(DayInfo[] days) {
		int cols = 0;

		for (int i = 0; i < days.length; i++) {
			cols += days[i].getColumns();
		}

		return cols;
	}

	/**
	 * Creates the row with the "allday" areas for all visible days
	 * 
	 * @param days
	 *            the visible days list
	 * @param pos
	 *            The position of the appointment relative to the bounds of the
	 *            time table (POS_REL_xxx)
	 * @return the allday row
	 */
	protected TR doCreateOverflowIndicatorRow(DayInfo[] days, int pos) {
		if (getAppointmentsOutOfBoundsCount(days, pos) == 0) {
			return null;
		} else {
			TR moreRow = new TR();
			moreRow.setClass("overflow");

			// Add the static columns for the time bar
			moreRow
				.addElement(new TD(Entities.NBSP)
					.setColSpan(2));

			// Create an "allday" cell for each day column
			for (int i = 0; i < days.length; i++) {
				Appointment[] out = days[i].getAppointmentsOutOfBounds(pos);

				ConcreteElement moreButton = null;

				if (out.length > 0) {
					String tooltip =
						getFrameworkString(
							DefResources.FW_TOOLTIP_SCHEDULER_RANGE,
							new Object[] { 
									new Integer(days[i].getCount()),
									new Integer(days[i].getVisibleCount()),
									new Integer(out.length)});

					// create button
					moreButton =
						createImage(
							(pos == POS_REL_TOP)
								? DefResources.SCHEDULER_MORE_UP
								: DefResources.SCHEDULER_MORE_DOWN);
					
					if (getCtrl().showPopups()) {
						// Use a complex popup window
						addPopupWindow(tooltip, days[i].getDate(), out, moreButton);
					} else {
						// Use a simple tooltip text
						moreButton.setTitle(tooltip);
					}
				} else {
					moreButton = new StringElement(Entities.NBSP);
				}

				moreRow
					.addElement(new TD(moreButton)
						.setColSpan(days[i].getColumns()));
			}

			return moreRow;
		}
	}

	/**
	 * @see com.cc.framework.ui.painter.def.scheduler.DefSchedulerViewPainter#doCreateBody()
	 */
	public ConcreteElement doCreateBody() {
		Table table = (Table) new Table()
			.setCellPadding(0)
			.setCellSpacing(0)
			.setBorder(0)
			.setWidth("100%")
			.setClass("dayview");

		DayInfo[] days = createDayInfo(getDays());

		if ((days == null) || (days.length == 0)) {
			return null;
		}
		
		// Create the navigation header
		ConcreteElement header = doCreateHeader();
		if (header != null) {
			table
				.addElement(new TR()
					.addElement(new TD(header)
						.setColSpan(getTotalColumns(days))));
		}

		// Create the metric Row
		table.addElement(doCreateMetricRow(days));

		// Create the Column Header Row
		if (days.length > 1) {
			table.addElement(doCreateDayHeader(days));
		}

		// Paint the "all day" Area
		if (getCtrl().showAllDayArea()) {
			table.addElement(doCreateAllDayArea(days));
		}

		TR rangeRow = doCreateOverflowIndicatorRow(days, POS_REL_TOP);
		if (rangeRow != null) {
			table.addElement(rangeRow);
		}

		// Paint the Time Table for the selected Day
		int rowsPerHour	= 60 / getCtrl().getInterval();
		int rowIndex = getCtrl().getDayStartHour() * rowsPerHour;
		int[] colUsage	= new int[getAppointmentColumns(days)];
		Calendar cal = (Calendar) days[0].getDate().clone();
		for (int hour = getCtrl().getDayStartHour(); hour <= getCtrl().getDayEndHour(); hour++) {

			// Set the current hour
			cal.set(Calendar.HOUR_OF_DAY, hour);
			
			for (int interval = 0; interval < rowsPerHour; interval++) {
				TR row = new TR();

				// Create the columns for the time table
				if (interval == 0) {
					row
						.addElement(new TD(getHourLabel(cal))
							.setRowSpan(rowsPerHour)
							.setHeight(METRIC_LINE_HIGHT)
							.setNoWrap(true)
							.setClass("hour"))
						.addElement(new TD("00")
							.setClass("interval"));
				} else {
					row
						.addElement(new TD(Entities.NBSP)
							.setHeight(METRIC_LINE_HIGHT)
							.setClass("interval"));
				}

				// Create the appointment columns
				int colOffset = 0;

				for (int day = 0; day < days.length; day++) {
					int col = 0;
					while (col < days[day].getColumns()) {
						if (colUsage[colOffset + col] > 0) {
							// we are currently rendering an appointment
							// in this column -> so do not create a <td> element

							// advance the current collumn one row
							--colUsage[colOffset + col];

							// continue with the next column
							++col;
						} else {
							// Search for the next appointment that occupies the
							// current column and row
							AppointmentInfo app = days[day].find(col, rowIndex);

							if (app == null) {
								// There is no appointment for the current cell.
								// We now try to combine as many empty cells as
								// possible in one TD element - to reduce the
								// amount of generated HTML code.
								
								// So we calculate the nuber of columns this empty
								// cell spans.
								int colstart = col;

								do {
									// continue with the next column
									++col;
								} while ((col < days[day].getColumns()) && (days[day].find(col, rowIndex) == null) && (colUsage[colOffset + col] <= 0));

								// This cell is not overlapped by an appointment
								// -> use the row to indicate working time or spare time
								row.addElement(new TD(Entities.NBSP)
									.setColSpan(col - colstart)
									.setClass(days[day].isWorkingHour(hour) ? "wt" : "st"));
							} else {
								row.addElement(new TD(doCreateAppointment(getCtrl().getDate(), app.getAppointment(), LABELFORMAT_SHORT))
									.setColSpan(app.getColSpan())
									.setRowSpan(app.getVisibleRows())
									.setClass("apps"));

								// register the columns and rows the appointment will
								// span in the day table
								for (int i = 0; i < app.getColSpan(); i++) {
									colUsage[colOffset + col] = app.getVisibleRows() - 1;

									// continue with the next column
									++col;
								}
							}
						}
					}

					// advance to the next columnset for the next day
					colOffset += days[day].getColumns();
				}

				table.addElement(row);

				// Continue with the next row
				++rowIndex;
			}
		}

		rangeRow = doCreateOverflowIndicatorRow(days, POS_REL_BOTTOM);
		if (rangeRow != null) {
			table.addElement(rangeRow);
		}

		return table;
	}

	/**
	 * @see com.cc.framework.ui.painter.def.scheduler.DefSchedulerViewPainter#doCreateNavigationButton(boolean)
	 */
	public ConcreteElement doCreateNavigationButton(boolean up) {
		Calendar cal = (Calendar) getCtrl().getDate().clone();

		if (up) {
			cal.add(Calendar.DAY_OF_YEAR, 1);
		} else {
			cal.add(Calendar.DAY_OF_YEAR, -1);
		}

		return createNavigationButton(cal, SchedulerView.DAY, up);
	}
}