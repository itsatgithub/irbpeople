/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/util/CalendarHelp.java,v 1.11 2005/09/22 06:20:18 P001002 Exp $
 * $Revision: 1.11 $
 * $Date: 2005/09/22 06:20:18 $
 *
 * ====================================================================
 *
 * Copyright (c) 2002 SCC Informationssysteme GmbH. All rights
 * reserved.
 *
 * ====================================================================
 */

package com.cc.framework.util;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Calendar utility class
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.11 $
 */
public abstract class CalendarHelp {

	/**
	 * The number of days in a week 
	 */
	public static final int DAYS_IN_WEEK = 7;

	/**
	 * Bitmask for Sunday
	 */
	public static final int DOWM_SUNDAY = (1 << Calendar.SUNDAY);

	/**
	 * Bitmask for Monday
	 */
	public static final int DOWM_MONDAY = (1 << Calendar.MONDAY);

	/**
	 * Bitmask for Tuesday
	 */
	public static final int DOWM_TUESDAY = (1 << Calendar.TUESDAY);

	/**
	 * Bitmask for Wednesday
	 */
	public static final int DOWM_WEDNESDAY = (1 << Calendar.WEDNESDAY);

	/**
	 * Bitmask for Thursday
	 */
	public static final int DOWM_THURSDAY = (1 << Calendar.THURSDAY);

	/**
	 * Bitmask for Friday
	 */
	public static final int DOWM_FRIDAY = (1 << Calendar.FRIDAY);

	/**
	 * Bitmask for Saturday
	 */
	public static final int DOWM_SATURDAY = (1 << Calendar.SATURDAY);

	/**
	 * Bitmask for Working Days
	 */
	public static final int DOWM_WEEKDAYS =
		DOWM_MONDAY
			| DOWM_TUESDAY
			| DOWM_WEDNESDAY
			| DOWM_THURSDAY
			| DOWM_FRIDAY;

	/**
	 * Bitmask for Weekend Days
	 */
	public static final int DOWM_WEEKENDDAYS =
		DOWM_SUNDAY
			| DOWM_SATURDAY;

	/**
	 * Bitmask for Working Days
	 */
	public static final int DOWM_ALLDAYS =
		DOWM_WEEKDAYS
			| DOWM_WEEKENDDAYS;

	/**
	 * Converts a calendar into a long value
	 * <b>for compatibility with JDK 1.3</b>
	 * 
	 * @param		cal Calendar to convert
	 * @return		long value
	 */
	public static long toLong(Calendar cal) {
		// JDK 1.4: return cal.getTimeInMillis()	
		return cal.getTime().getTime();
	}

	/**
	 * Initializes the calendar with the given UTC timestamp
	 * <b>for compatibility with JDK 1.3</b>
	 * 
	 * @param		cal the calendar
	 * @param		timeInMillis UTC timestamp
	 */
	public static void setFromLong(Calendar cal, long timeInMillis) {
		// JDK 1.4: cal.setTimeInMillis(timeInMillis)	
		cal.setTime(new Date(timeInMillis));
	}

	/**
	 * This method calcualtes the number of days between the two
	 * dates.
	 *  
	 * @param		date1 the first day
	 * @param		date2 the last day
	 * @return		Number of days
	 */
	public static int getDays(Calendar date1, Calendar date2) {
		int startYear	= date1.get(Calendar.YEAR);
		int startDay	= date1.get(Calendar.DAY_OF_YEAR);
	
		int year		= date2.get(Calendar.YEAR);
		int day			= date2.get(Calendar.DAY_OF_YEAR);


// TODO Schaltjahre berücksichtigen
		int delta		= ((year - startYear) * 365) + (day - startDay);

		return delta;
	}

	/**
	 * Checks if both dates ar on the same day
	 * 
	 * @param		a First date
	 * @param		b Secound date
	 * @return		<code>true</code> if the days cover the same day
	 */
	public static boolean isSameDay(Calendar a, Calendar b) {
		if ((a == null) || (b == null)) {
			return false;
		} else {
			return (a.get(Calendar.YEAR) == b.get(Calendar.YEAR))
				&& (a.get(Calendar.DAY_OF_YEAR) == b.get(Calendar.DAY_OF_YEAR));
		}
	}

	/**
	 * Parses a semicolon seperated Day list and creates
	 * a day mask
	 * 
	 * @param		dayList Semicolon separated list with day
	 * 				names (Locale.ENGLISH)
	 * @return		Day Bitmask
	 */
	public static int parseDayMask(String dayList) {
		int dayMask = 0;
 
		String[] dayNames	= new DateFormatSymbols(Locale.ENGLISH).getWeekdays();
		String[] tokens		= StringHelp.split(dayList, ";");

		for (int i = 0; i < tokens.length; i++) {
			int day = Calendar.SUNDAY;
			
			while (day <= Calendar.SATURDAY) {
				if (dayNames[day].toLowerCase().startsWith(tokens[i].toLowerCase())) {
					dayMask |= (1 << day);
					break;
				}

				++day;
			}

			if (day > Calendar.SATURDAY) {
				throw new IllegalArgumentException("Illegal day mask item: " + tokens[i]);
			}
		}

		return dayMask;
	}

	/**
	 * Converts a Day Name into a integer value
	 * 
	 * @param		dayName The Day Name (Locale.ENGLISH)
	 * @return		Day index (as defined in Calendar class)
	 */
	public static int parseDay(String dayName) {
		if ("def".equalsIgnoreCase(dayName)) {
			return 0;
		}

		String[] dayNames	= new DateFormatSymbols(Locale.ENGLISH).getWeekdays();

		for (int day = Calendar.SUNDAY; day <= Calendar.SATURDAY; day++) {
			if (dayNames[day].toLowerCase().startsWith(dayName.toLowerCase())) {
				return day;
			}
		}

		throw new IllegalArgumentException("Illegal day name: " + dayName);
	}

	/**
	 * Rools the dayy index by the given offset
	 * 
	 * @param		dayOfWeek Starting day index
	 * @param		offset the offset to roll
	 * @return		resulting day index
	 */
	public static int rollDay(int dayOfWeek, int offset) {
		return ((dayOfWeek + offset - 1) % DAYS_IN_WEEK) + 1;
	}

	/**
	 * This method checks if the given date falls between the
	 * dates t1 and t2. The resolution for this check is the
	 * day - the time will be ignored
	 * 
	 * @param		date Zu prüfendes Datum
	 * @param		t1 Unteres intervallende
	 * @param		t2 Oberes Intervallende
	 * @return		Liefert <code>true</code> wenn das Datum
	 * 				in dem angegebenen Intervall liegt
	 */
	public static boolean between(Calendar date, Calendar t1, Calendar t2) {
		int d = date.get(Calendar.YEAR) * 1000 + date.get(Calendar.DAY_OF_YEAR);
		int d1 =
			(t1 == null)
				? 0
				: t1.get(Calendar.YEAR) * 1000 + t1.get(Calendar.DAY_OF_YEAR);
		int d2 =
			(t2 == null)
				? Integer.MAX_VALUE
				: t2.get(Calendar.YEAR) * 1000 + t2.get(Calendar.DAY_OF_YEAR);

		return (d >= d1) && (d <= d2);
	}

	/**
	 * Checks if the given day is a weekend day
	 * 
	 * @param		dayOfWeek the day to check
	 * @return		<code>true</code> if this is a weekend day
	 */
	public static boolean isWeekEndDay(int dayOfWeek) {
		return ((DOWM_WEEKENDDAYS & (1 << dayOfWeek)) > 0);
	}
}
