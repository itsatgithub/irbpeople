/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/scheduler/DefYearViewPainter.java,v 1.18 2005/10/11 13:53:03 P001002 Exp $
 * $Revision: 1.18 $
 * $Date: 2005/10/11 13:53:03 $
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

import org.apache.ecs.AlignType;
import org.apache.ecs.ConcreteElement;
import org.apache.ecs.Entities;
import org.apache.ecs.html.TBody;
import org.apache.ecs.html.TD;
import org.apache.ecs.html.TR;
import org.apache.ecs.html.Table;

import uicomponents.scheduler.ICalendar;
import uicomponents.scheduler.SchedulerFacadeForCommonControlsScheduler;

import com.cc.framework.ui.control.ControlActionDef;
import com.cc.framework.ui.model.Appointment;
import com.cc.framework.ui.model.ImageModel;
import com.cc.framework.ui.model.SchedulerView;
import com.cc.framework.ui.painter.ActionPainter;
import com.cc.framework.ui.painter.def.DefResources;
import com.cc.framework.util.CalendarHelp;

/**
 * Scheduler Painter for year view
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.18 $
 */
public class DefYearViewPainter extends DefSchedulerViewPainter {

	/**
	 * Constructor
	 */
	public DefYearViewPainter() {
		super();
	}

	/**
	 * retrieves the number of month to increment in year view when the user
	 * clicks on one of the navigation buttons. A value of 0 resets the the
	 * control to the default increment which is the number of visible months (=
	 * rows * columns)
	 * 
	 * @return the number of month to increment
	 */
	protected int getMonthIncrement() {
		int increment = getCtrl().getMonthIncrement();
		
		if (increment <= 0) {
			return getCtrl().getColumns() * getCtrl().getRows();
		} else {
			return increment;
		}
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
	 * This method paints one single Header Cell of the Scheduler
	 *
	 * @param		headerCell The Header cell to paint
	 * @param		dayOfWeek Tha Day for this column
	 */
	protected void doPaintDayHeader(TD headerCell, int dayOfWeek) {

		DateFormatSymbols dfs = new DateFormatSymbols(getSafeLocale());

		headerCell
			.addElement(html(dfs.getShortWeekdays()[dayOfWeek]).substring(0, 1))
			.setTitle(html(dfs.getWeekdays()[dayOfWeek]));
	}

	/**
	 * @see com.cc.framework.ui.painter.def.scheduler.DefSchedulerViewPainter#doCreateNavigationButton(boolean)
	 */
	public ConcreteElement doCreateNavigationButton(boolean up) {
		Calendar cal = (Calendar) getCtrl().getDate().clone();
		cal.set(Calendar.DAY_OF_MONTH, 1);

		if (up) {
			cal.add(Calendar.MONTH, getMonthIncrement());
		} else {
			cal.add(Calendar.MONTH, -getMonthIncrement());
		}

		return createNavigationButton(cal, SchedulerView.YEAR, up);
	}

	/**
	 * Creates the navigation button for the given month state
	 *
	 * @param		monthState Month state
	 * @return		Navigation button or spacer element
	 */
	public ConcreteElement doCreateNavigationButton(int monthState) {
		if ((monthState & STATE_LEFTMOST_MONTH) != 0) {
			return doCreateNavigationButton(false);
		} else if ((monthState & STATE_RIGHTMOST_MONTH) != 0) {
			return doCreateNavigationButton(true);
		} else {
			ImageModel btn = getImage(DefResources.BUTTON_SCHEDULER_NEXT_1);

			return createSpacer(btn.getHeight(), btn.getWidth());
		}
	}

	/**
	 * @see com.cc.framework.ui.painter.def.scheduler.DefSchedulerViewPainter#doCreateHeader()
	 */
	protected ConcreteElement doCreateHeader() {
		// This view has no single header. It shows
		// one header for every month
		return null;
	}

	/**
	 * Creates a navigation header element
	 *
	 * @param		date The date of the month
	 * @param		monthState The month state
	 * @return		Header element for navigation or <code>null</code>
	 */
	protected ConcreteElement doCreateMonthHeader(Calendar date, int monthState) {
		DateFormat df = getDateFormat(DATEFORMAT_MONTH_OF_YEAR);

		ActionPainter ap = createActionPainter(ControlActionDef.ACTION_SELECTDATE);
		ap.addParameter(CalendarHelp.toLong(date));
		ap.addParameter(SchedulerView.MONTH);
		ap.setLabel(df.format(date.getTime()));
		ap.setTooltip(getFrameworkString(DefResources.FW_TOOLTIP_SCHEDULER_MONTHVIEW));
        
        // old - inicio
        // old - inicio
        // old - inicio
        // ConcreteElement link = ap.createElement();
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
                ap.createElement(), ap.getAction().toString());
        
        // new - fin
        // new - fin
        // new - fin   
        
		if (!showFrame() && (monthState & (STATE_LEFTMOST_MONTH | STATE_RIGHTMOST_MONTH)) != 0) {
			// Additional navigation buttons are required
			return new Table()
				.setCellPadding(0)
				.setCellSpacing(0)
				.setBorder(0)
				.setWidth("100%")
				.addElement(new TR()
					.addElement(new TD(doCreateNavigationButton(monthState & STATE_LEFTMOST_MONTH))
						.setAlign(AlignType.left))
					.addElement(new TD(link)
						.setNoWrap(true)
						.setAlign(AlignType.center))
					.addElement(new TD(doCreateNavigationButton(monthState & STATE_RIGHTMOST_MONTH))
						.setAlign(AlignType.right))
				.setClass("nav"));
		} else {
			return link;
		}
	}

	/**
	 * Creates the Days column header row
	 *
	 * @return		Header row
	 */
	protected TR doCreateDayHeader() {

		TR headerRow = new TR();
		headerRow.setClass("days");

		int firstDayOfWeek	= getCtrl().getFirstDayOfWeek();
		int colWidth		= (int) ((100.0d / calcDayColumns()) + 0.5d);

		// Iterate all Week Days
		for (int i = 0; i < CalendarHelp.DAYS_IN_WEEK; i++) {
			int dayOfWeek = CalendarHelp.rollDay(firstDayOfWeek, i);

			if (showDay(dayOfWeek)) {
				TD headerCell = new TD();
				headerCell.setWidth(colWidth + "%");
				headerCell.setClass(getCtrl().isWorkingDay(dayOfWeek) ? "wd" : "wed");

				doPaintDayHeader(headerCell, dayOfWeek);

				headerRow.addElement(headerCell);
			}
		}

		return headerRow;
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
		return Integer.toString(date.get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * Paints the cell for the given day
	 *
	 * @param		date date
	 * @param		state days state
	 * @param		appointments List with appointments
	 * @return		the day cell
	 */
	protected TD doCreateDayCell(Calendar date, int state, Appointment[] appointments) {

		TD dayCell = new TD();
		
		String dayClass = getDayClass(state);
		
		//no pintem els dies que estan fora del més (fora de l'any).
		if(dayClass == "dom") return dayCell;
		
		dayCell.setClass(dayClass);

		if ((state & STATE_HIDDEN) == STATE_HIDDEN) {
			dayCell.addElement(Entities.NBSP);
		} else {
			// create button
			ActionPainter ap = createActionPainter(ControlActionDef.ACTION_SELECTDATE);

			ap.addParameter(CalendarHelp.toLong(date));
			ap.addParameter(SchedulerView.DAY);
			ap.setLabel(getDayLabel(date, state));

			if (getCtrl().showPopups() && (appointments.length > 0)) {
				addPopupWindow(getDayTooltip(date), date, appointments, dayCell);
			} else {
				dayCell.setTitle(getDayTooltip(date));
			}

			if ((state & STATE_TODAY) != 0) {
				dayCell.setID("today");
			}

            // old - inicio
            // old - inicio
            // old - inicio
            // dayCell.addElement(ap.createElement());
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
			ConcreteElement concreteElement = ap.createElement();
			// no creem un boto si hi ha apointment
            if(appointments.length == 0) {
            // Creates a link to all day view, and an onclik submit operation to send all other form parameters.
            	ConcreteElement link = SchedulerFacadeForCommonControlsScheduler.insideCreateButtonLink(
                    concreteElement, date.getTime(), getCtrl().getAction(), getLocale());
            	dayCell.addElement(link);
            } else {
            	concreteElement.removeAttribute("href");
            	dayCell.addElement(concreteElement);
            }
            // set style class 
            if(appointments.length == 1)
            	dayCell.setClass(appointments[0].getType());
            else if(appointments.length == 2) {
            	if(appointments[0].getType().equals(SchedulerFacadeForCommonControlsScheduler.CLASS_FESTIU) ||
            			appointments[0].getType().equals(SchedulerFacadeForCommonControlsScheduler.CLASS_LIMIT_VANCANCES))
                	dayCell.setClass(appointments[0].getType());
            	else
            		dayCell.setClass(appointments[1].getType());
            }
            	
            // new - fin
            // new - fin
            // new - fin               
            
		}

		return dayCell;
	}

	/**
	 * Creates a cell for hidden days
	 *
	 * @param		colspan Number of spanned columns
	 * @return		cell
	 */
	protected TD doCreateHiddenCell(int colspan) {
		TD cell = new TD(Entities.NBSP);

		if (colspan > 1) {
			cell.setColSpan(colspan);
		}

		cell.setClass(getDayClass(STATE_HIDDEN));

		return cell;
	}

	/**
	 * Creates the table for the given month
	 *
	 * @param		calIter the iterator to iterate all months days
	 * @param		monthState state flags of the current month
	 * @return		Month table
	 */
	protected Table doCreateMonthTable(CalendarIterator calIter, int monthState) {

		Table table = new Table()
			.setCellPadding(0)
			.setCellSpacing(1)
			.setBorder(0)
			.setWidth("100%");

		// Create the navigation header
		ConcreteElement header = doCreateMonthHeader(calIter.getInitial(), monthState);
		if (header != null) {
			table.addElement(new TR()
				.addElement(new TD(header)
					.setColSpan(calcDayColumns())
					.setClass("nav")));
		}

		// Create the Column Header Row
		TR headerRow = doCreateDayHeader();
		if (headerRow != null) {
			table.addElement(headerRow);
		}

		// iterate all days of the current month
		TBody body = new TBody();
		table.addElement(body);

		int monthDelta	= -1;
		boolean first	= true;

		while (calIter.getMonthDelta() <= 0) {
			TR row = new TR();

			int hidden = 0;

			for (int i = 0; i < CalendarHelp.DAYS_IN_WEEK; i++) {
				if (showDay(calIter.getDayOfWeek())) {
					if (calIter.getMonthDelta() != monthDelta) {
						monthDelta	= calIter.getMonthDelta();
						first		= true;
					}

					// calculate the days state
					int state = getDayState(calIter, first, monthState);

					if ((state & STATE_HIDDEN) != 0) {
						++hidden;
					} else {
						Appointment[] appointments = getCtrl().getAppointments(calIter.current());
						if ((appointments != null) && (appointments.length > 0)) {
							state |= STATE_APPOINTMENTS;
						}

						// Add preceding hidden cells to the row
						if (hidden > 0) {
							row.addElement(doCreateHiddenCell(hidden));
							hidden = 0;
						}

						row.addElement(doCreateDayCell(calIter.current(), state, appointments));
					}

					first = false;
				}

				calIter.next();
			}

			// Add trailing hidden cells to the row
			if (hidden > 0) {
				row.addElement(doCreateHiddenCell(hidden));
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

	/**
	 * @see com.cc.framework.ui.painter.def.scheduler.DefSchedulerViewPainter#doCreateBody()
	 */
	public ConcreteElement doCreateBody() {
		Table table = (Table) new Table()
			.setCellPadding(0)
			.setCellSpacing(0)
			.setBorder(0)
			.setWidth("100%")
			.setClass("yearview");

		Calendar cal	= getCtrl().getDate();
		int columns		= getCtrl().getColumns();
		int rows		= getCtrl().getRows();

		for (int row = 0; row < rows; row++) {

			TR tableRow = new TR();
			table.addElement(tableRow);

			for (int column = 0; column < columns; column++) {
				int month	= (cal.get(Calendar.MONTH) + column + (row * columns)) % 12;
				int year	= cal.get(Calendar.YEAR) + ((cal.get(Calendar.MONTH) + column + (row * columns)) / 12);

				Calendar monthCal = (Calendar) cal.clone();
				monthCal.set(year, month, 1);

				CalendarIterator calIter = new CalendarIterator(monthCal);
				calIter.restart(getCtrl().getFirstDayOfWeek(), getDayOfWeekMask());

				int monthState	= 0;
				if (row == 0) {
					if (column == 0) {
						monthState |= STATE_FIRST_MONTH | STATE_LEFTMOST_MONTH;
					}

					if (column + 1 == columns) {
						monthState |= STATE_RIGHTMOST_MONTH;
					}
				}

				if ((row + 1 == rows) && (column + 1 == columns)) {
					monthState |= STATE_LAST_MONTH;
				}

				tableRow.addElement(new TD()
					.addElement(doCreateMonthTable(calIter, monthState))
					.setWidth(Integer.toString(100 / columns) + "%")
					.setVAlign(AlignType.top));
			}
		}

		return table;
	}
}