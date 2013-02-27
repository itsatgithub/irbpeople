/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/scheduler/CalendarIterator.java,v 1.6 2005/08/24 17:50:46 P001002 Exp $
 * $Revision: 1.6 $
 * $Date: 2005/08/24 17:50:46 $
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
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.cc.framework.util.CalendarHelp;

/**
 * Iterator with a resolution of one day
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.6 $
 */
public class CalendarIterator {

	/**
	 * The initial date for this iterator
	 */
	private Calendar initialDate;

	/**
	 * The current Iteration element
	 */
	private Calendar i;

	/**
	 * Constructor
	 * 
	 * @param		calendar The initial date
	 */
	public CalendarIterator(Calendar calendar) {
		super();

		this.initialDate	= calendar;
	}

	/**
	 * Returns the initial Date for this iterator
	 * 
	 * @return		Initial Date
	 */
	public Calendar getInitial() {
		return initialDate;
	}

	/**
	 * Restarts the iterator
	 */
	public void restart() {
		i = (Calendar) initialDate.clone();

		// Start with the first Day
		i.set(Calendar.DAY_OF_MONTH, 1);
	}

	/**
	 * this method checks if a given day should be displyed.
	 * This depends on the WeekEndDay settings of the design
	 * model.
	 * 
	 * @param		dayOfWeekMask A mask containing all the
	 * 				visible days. The iterator will skip any
	 * 				hidden day
	 * @param		dayOfWeek the day
	 * @return		<code>true</code> if the day should be
	 * 				shown
	 */
	private boolean showDay(int dayOfWeekMask, int dayOfWeek) {
		return ((dayOfWeekMask & (1 << dayOfWeek)) > 0);
	}

	/**
	 * Restarts the iterator to the first day of week
	 * 
	 * @param		firstDayOfWeek The first day of the week
	 * @param		dayOfWeekMask A mask containing all the
	 * 				visible days. The iterator will skip any
	 * 				hidden day
	 */
	public void restart(int firstDayOfWeek, int dayOfWeekMask) {
		restart();

		if (!showDay(dayOfWeekMask, getDayOfWeek())) {
			// Move the calendar iterator to the first visible day
			do {
				next();	
			} while (!showDay(dayOfWeekMask, getDayOfWeek()));

		}

		// Move the calendar iterator to the first visible week
		int delta =
			(firstDayOfWeek - (getDayOfWeek() + CalendarHelp.DAYS_IN_WEEK))
				% CalendarHelp.DAYS_IN_WEEK;

		skip(delta);
	}

	/**
	 * Skips the iterator the given number of days
	 * 
	 * @param		days The number of days to skip
	 */
	public void skip(int days) {
		i.add(Calendar.DAY_OF_YEAR, days);
	}

	/**
	 * Positions the iterator on the next day
	 */
	public void next() {
		i.add(Calendar.DAY_OF_YEAR, 1);
	}

	/**
	 * @return		Current day
	 */
	public Calendar current() {
		return i;
	}

	/**
	 * Gets the value for the WEEK_OF_YEAR time field.
	 * 
	 * @return the value for the WEEK_OF_YEAR time field.
	 */
	public int getWeekOfYear() {
		return i.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * Gets the value for the DAY_OF_WEEK time field.
	 * 
	 * @return the value for the DAY_OF_WEEK time field.
	 */
	public int getDayOfWeek() {
		return i.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * Gets the value for the DAY_OF_MONTH time field.
	 * 
	 * @return the value for the DAY_OF_MONTH time field.
	 */
	public int getDayOfMonth() {
		return i.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * This member calculates the month offset of the current
	 * iteration element from the initial Date.
	 * 
	 * @return		Month offset
	 */
	public int getMonthDelta() {
		return (i.get(Calendar.YEAR) * 12 + i.get(Calendar.MONTH))
			- (initialDate.get(Calendar.YEAR) * 12
				+ initialDate.get(Calendar.MONTH));
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		if (i == null) {
			return "null";
		} else {
			DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy");

			return df.format(i.getTime());
		}
	}
}