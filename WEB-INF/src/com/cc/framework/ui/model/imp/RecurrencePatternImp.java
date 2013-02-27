/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/RecurrencePatternImp.java,v 1.8 2005/09/12 10:33:37 P001002 Exp $
 * $Revision: 1.8 $
 * $Date: 2005/09/12 10:33:37 $
 *
 * ====================================================================
 *
 * Copyright (c) 2002 SCC Informationssysteme GmbH. All rights
 * reserved.
 *
 * ====================================================================
 */

package com.cc.framework.ui.model.imp;

import java.text.MessageFormat;
import java.util.Calendar;

import com.cc.framework.ui.model.Appointment;
import com.cc.framework.ui.model.RecurrenceException;
import com.cc.framework.ui.model.RecurrencePattern;
import com.cc.framework.ui.model.RecurrenceType;
import com.cc.framework.util.CalendarHelp;

/**
 * Recurrence pattern for appointments
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.8 $
 */
public class RecurrencePatternImp implements RecurrencePattern {

	/** Exception text */
	protected static final String ERR_MASK =
		"invalid dayOfWeekMask mask!";

	/** Exception text */
	protected static final String ERR_ATTR =
		"attribute \"{0}\" is not allowed for a {1} pattern!";

	/** Exception text */
	protected static final String ERR_ATTR_ZERO =
		"attribute \"{0}\" most not be zero for a {1} pattern!";

	/** Exception text */
	protected static final String ERR_ATTR_VALUE =
		"invalid value for attribute \"{0}\" for a {1} pattern!";

	/**
	 * The appointment this recurrence pattern is attached to
	 */
	private Appointment appointment = null;

	/**
	 * Recurrence Type
	 */
	private RecurrenceType recurType = RecurrenceType.NONE;

	/**
	 * The Day of the month
	 */
	private int dayOfMonth = 0;

	/**
	 * Bit mask for a day of the week
	 * (use the CalendarHelp.DOWM_xxx constants)
	 * 
	 * Sunday  = CalendarHelp.DOWM_SUNDAY 
	 * Monday  = CalendarHelp.DOWM_MONDAY
	 * Tuesday = CalendarHelp.DOWM_TUESDAY
	 * ... 
	 * @see CalendarHelp
	 */
	private int dayOfWeekMask = 0;

	/**
	 * The Instance property is used when the AppointmentItem is to recur only once during
	 * each recurrence unit, such as the second Wednesday of every month or the first Tuesday
	 * of every January. The DayOfWeekMask property must specify exactly one day of the week,
	 * and Instance selects the occurrence of that day within the month on which recurrence
	 * is enabled. The last occurrence of the day within the month can be represented by
	 * the value 5.
	 * 
	 * Instance is only valid if the value of the RecurrenceType property is MONTHLY_NTH or
	 * YEARLY_NTH. When Instance is valid on a newly created RecurrencePattern object, it
	 * defaults to the current instance of the current day of the week. For example, if the
	 * RecurrencePattern object is created on Wednesday 9 December and RecurrenceType is set
	 * to YEARLY_NTH, then DayOfWeekMask defaults to 8 (Wednesday), Instance defaults to 2
	 * (the second Wednesday of the month), and MonthOfYear defaults to 12 (December).
	 */
	private int instance = 0;

	/**
	 * The Interval property is used when the AppointmentItem is to recur less often than
	 * every recurrence unit, such as once every three days, once every two weeks, or once
	 * every six months. Interval contains a value representing the frequency of recurrence
	 * in terms of recurrence units.
	 */
	private int interval = 0;

	/**
	 * The MonthOfYear property contains the calendar number of the month in which the
	 * AppointmentItem is to recur, for example the value 2 to indicate February.
	 * 
	 * MonthOfYear is only valid if the value of the RecurrenceType property is YEARLY or
	 * YEARLY_NTH. When MonthOfYear is valid on a newly created RecurrencePattern object,
	 * it defaults to the current month.
	 */ 
	private int monthOfYear = 0;

	/**
	 * The Occurrences property is used when the appointment is to recur a specific number
	 * of times, such as the next ten Thursdays. Occurrences has a minimum value of 1 and
	 * represents the total number of occurrences of the AppointmentItem object fitting the
	 * recurrence pattern. This qualification is necessary because the days of the original
	 * appointment, the PatternStartDate, and the PatternEndDate are not required to be
	 * included in the pattern specified by the DayOfMonth, DayOfWeekMask, Instance, Interval,
	 * and MonthOfYear properties. The original appointment is counted in Occurrences only if
	 * it matches the pattern.
	 * 
	 * Occurrences is always valid on a newly created RecurrencePattern object and defaults
	 * to its maximum value of 1,490,000. If the NoEndDate property is subsequently set to
	 * False, Occurrences defaults to 10.
	 */
	private int occurrences = 10;

	/**
	 * The PatternEndDate property contains the latest possible date of the last occurrence
	 * of the appointment. This qualification is necessary because PatternEndDate is not
	 * required to be included in the pattern specified by the DayOfMonth, DayOfWeekMask,
	 * Instance, Interval, and MonthOfYear properties. A recurrence is generated on PatternEndDate
	 * only if it matches the pattern.
	 */
	private Calendar patternEndDate = null;

	/**
	 * The PatternStartDate property contains the earliest possible date of the first occurrence
	 * of the appointment. This qualification is necessary because PatternStartDate is not required
	 * to be included in the pattern specified by the DayOfMonth, DayOfWeekMask, Instance, Interval,
	 * and MonthOfYear properties. A recurrence is generated on PatternStartDate only if it matches
	 * the pattern.
	 */
	private Calendar patternStartDate = null;

	/**
	 * Constructor
	 */
	public RecurrencePatternImp() {
		super();
	}

	/**
	 * @see com.cc.framework.ui.model.RecurrencePattern#setPattern(com.cc.framework.ui.model.RecurrenceType, int, int, int, int, int)
	 */
	public void setPattern(
		RecurrenceType recurType,
		int dayOfMonth,
		int dayOfWeekMask,
		int instance,
		int interval,
		int monthOfYear) {

		this.recurType		= recurType;
		this.dayOfMonth		= dayOfMonth;
		this.dayOfWeekMask	= dayOfWeekMask;
		this.instance		= instance;
		this.interval		= interval;
		this.monthOfYear	= monthOfYear;

		// validate all settings
		validate();
	}

	/**
	 * This method validates the current recurrence settings.
	 * It throws IndexOutOfBoundsException or IllegalArgumentException
	 * if the recurrence settings are invalid.
	 */
	public void validate() {

		if ((dayOfWeekMask & ~CalendarHelp.DOWM_ALLDAYS) > 0) {
			throw new IndexOutOfBoundsException(ERR_MASK);
		}

		// Check for non zero dayOfWeekMask
		if (RecurrenceType.DAILY.equals(recurType)
			|| RecurrenceType.WEEKLY.equals(recurType)
			|| RecurrenceType.MONTHLY_NTH.equals(recurType)
			|| RecurrenceType.YEARLY_NTH.equals(recurType)) {
			if (dayOfWeekMask == 0) {
				throw new IllegalArgumentException(
					MessageFormat.format(
						ERR_ATTR_ZERO,
						new Object[] { "dayOfWeekMask", recurType }));
			}
		}

		// Check for non zero interval
		if (RecurrenceType.DAILY.equals(recurType)
			|| RecurrenceType.WEEKLY.equals(recurType)
			|| RecurrenceType.MONTHLY.equals(recurType)
			|| RecurrenceType.MONTHLY_NTH.equals(recurType)) {
			if (interval == 0) {
				throw new IllegalArgumentException(
					MessageFormat.format(
						ERR_ATTR_ZERO,
						new Object[] { "interval", recurType }));
			}
		}

		// Check for dayOfMonth
		if (RecurrenceType.MONTHLY.equals(recurType)
			|| RecurrenceType.YEARLY.equals(recurType)) {
			if (dayOfMonth > 31) {
				throw new IndexOutOfBoundsException(
					MessageFormat.format(
						ERR_ATTR_VALUE,
						new Object[] { "dayOfMonth", recurType }));
			}
		} else if (dayOfMonth != 0) {
			throw new IllegalArgumentException(
				MessageFormat.format(
					ERR_ATTR,
					new Object[] { "dayOfMonth", recurType }));
		}

		// Check for instance
		if ((instance > 0)
			&& !(RecurrenceType.MONTHLY_NTH.equals(recurType)
				|| RecurrenceType.YEARLY_NTH.equals(recurType))) {
			throw new IllegalArgumentException(
				MessageFormat.format(
					ERR_ATTR,
					new Object[] { "instance", recurType }));
		}

		// Check for monthOfYear
		if (RecurrenceType.YEARLY.equals(recurType)
			|| RecurrenceType.YEARLY_NTH.equals(recurType)) {
			if (monthOfYear > Calendar.DECEMBER) {
				throw new IndexOutOfBoundsException(
					MessageFormat.format(
						ERR_ATTR_VALUE,
						new Object[] { "monthOfYear", recurType }));
			}
		} else if (monthOfYear != 0) {
			throw new IllegalArgumentException(
				MessageFormat.format(
					ERR_ATTR,
					new Object[] { "monthOfYear", recurType }));
		}
	}

	/**
	 * Constructor for a daily recurrence
	 * pattern
	 * 
	 * @param		interval Every N days 
	 * @param		dayOfWeekMask Every Tuesday, Wednesday, and Thursday
	 * @return		Recurrence Pattern 
	 */
	public static RecurrencePatternImp createDailyPattern(
		int interval,
		int dayOfWeekMask) {
	
		RecurrencePatternImp pattern = new RecurrencePatternImp();
		
		pattern.setPattern(
			RecurrenceType.DAILY,
			0,
			dayOfWeekMask,
			0,
			interval,
			0);

		return pattern;
	}

	/**
	 * Constructor for a weekly recurrence
	 * pattern
	 * 
	 * @param		interval Every N weeks 
	 * @param		dayOfWeekMask Every Tuesday, Wednesday, and Thursday
	 * @return		Recurrence Pattern 
	 */
	public static RecurrencePatternImp createWeeklyPattern(
		int interval,
		int dayOfWeekMask) {

		RecurrencePatternImp pattern = new RecurrencePatternImp();

		pattern.setPattern(
			RecurrenceType.WEEKLY,
			0,
			dayOfWeekMask,
			0,
			interval,
			0);

		return pattern;
	}

	/**
	 * Constructor for a monthly recurrence
	 * pattern
	 * 
	 * @param		interval Every N months 
	 * @param		dayOfMonth The Nth day of the month
	 * @return		Recurrence Pattern 
	 */
	public static RecurrencePatternImp createMonthlyPattern(
		int interval,
		int dayOfMonth) {

		RecurrencePatternImp pattern = new RecurrencePatternImp();

		pattern.setPattern(
			RecurrenceType.MONTHLY,
			dayOfMonth,
			0,
			0,
			interval,
			0);

		return pattern;
	}

	/**
	 * Constructor for a monthly (Nth) recurrence
	 * pattern
	 * 
	 * @param		interval Every N months 
	 * @param		instance The Nth Tuesday
	 * @param		dayOfWeekMask Every Tuesday and Wednesday
	 * @return		Recurrence Pattern 
	 */
	public static RecurrencePatternImp createMonthlyNthPattern(
		int interval,
		int instance,
		int dayOfWeekMask) {

			RecurrencePatternImp pattern = new RecurrencePatternImp();

			pattern.setPattern(
				RecurrenceType.MONTHLY_NTH,
				0,
				dayOfWeekMask,
				instance,
				interval,
				0);

			return pattern;
	}

	/**
	 * Constructor for a yearly recurrence
	 * pattern
	 * 
	 * @param		dayOfMonth The Nth day of the month
	 * @param		monthOfYear February
	 * @return		Recurrence Pattern 
	 */
	public static RecurrencePatternImp createYearlyPattern(
		int dayOfMonth,
		int monthOfYear) {

		RecurrencePatternImp pattern = new RecurrencePatternImp();

		pattern.setPattern(
			RecurrenceType.YEARLY,
			dayOfMonth,
			0,
			0,
			0,
			monthOfYear);

		return pattern;
	}

	/**
	 * Constructor for a yearly (Nth) recurrence
	 * pattern
	 * 
	 * @param		instance The Nth Tuesday
	 * @param		dayOfWeekMask Tuesday, Wednesday, Thursday
	 * @param		monthOfYear February
	 * @return		Recurrence Pattern 
	 */
	public static RecurrencePatternImp createYearlyNthPattern(
		int instance,
		int dayOfWeekMask,
		int monthOfYear) {

		RecurrencePatternImp pattern = new RecurrencePatternImp();

		pattern.setPattern(
			RecurrenceType.YEARLY,
			0,
			dayOfWeekMask,
			instance,
			0,
			monthOfYear);

		return pattern;
	}

	/**
	 * @see com.cc.framework.ui.model.RecurrencePattern#getDayOfMonth()
	 */
	public int getDayOfMonth() {
		return dayOfMonth;
	}

	/**
	 * @see com.cc.framework.ui.model.RecurrencePattern#getDayOfWeekMask()
	 */
	public int getDayOfWeekMask() {
		return dayOfWeekMask;
	}

	/**
	 * @see com.cc.framework.ui.model.RecurrencePattern#getInstance()
	 */
	public int getInstance() {
		return instance;
	}

	/**
	 * @see com.cc.framework.ui.model.RecurrencePattern#getInterval()
	 */
	public int getInterval() {
		return interval;
	}

	/**
	 * @see com.cc.framework.ui.model.RecurrencePattern#getMonthOfYear()
	 */
	public int getMonthOfYear() {
		return monthOfYear;
	}

	/**
	 * @see com.cc.framework.ui.model.RecurrencePattern#getOccurrences()
	 */
	public int getOccurrences() {
		return occurrences;
	}

	/**
	 * @see com.cc.framework.ui.model.RecurrencePattern#getAppointment()
	 */
	public Appointment getAppointment() {
		return appointment;
	}

	/**
	 * @see com.cc.framework.ui.model.RecurrencePattern#setAppointment(com.cc.framework.ui.model.Appointment)
	 */
	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	/**
	 * @see com.cc.framework.ui.model.RecurrencePattern#getPatternEndDate()
	 */
	public Calendar getPatternEndDate() {
		return patternEndDate;
	}

	/**
	 * @see com.cc.framework.ui.model.RecurrencePattern#getPatternStartDate()
	 */
	public Calendar getPatternStartDate() {
		return patternStartDate;
	}

	/**
	 * @see com.cc.framework.ui.model.RecurrencePattern#getRecurrenceType()
	 */
	public RecurrenceType getRecurrenceType() {
		return recurType;
	}

	/**
	 * @see com.cc.framework.ui.model.RecurrencePattern#hasNoEndDate()
	 */
	public boolean hasNoEndDate() {
		return patternEndDate == null;
	}

	/**
	 * @see com.cc.framework.ui.model.RecurrencePattern#getExceptions()
	 */
	public RecurrenceException[] getExceptions() {
		return new RecurrenceException[0];
	}

	/**
	 * Sets the patterns range
	 * 
	 * @param		patternStartDate the start date for this pattern
	 * @return		the pattern
	 */
	public RecurrencePatternImp setRange(Calendar patternStartDate) {

		this.patternStartDate	= patternStartDate;
		this.patternEndDate		= null;
		this.occurrences		= 0;

		return this;
	}

	/**
	 * Sets the patterns range
	 * 
	 * @param		patternStartDate the start date for this pattern
	 * @param		patternEndDate the end date for this pattern
	 * 				or <code>null</code>
	 * @return		the pattern
	 */
	public RecurrencePatternImp setRange(
		Calendar patternStartDate,
		Calendar patternEndDate) {

		this.patternStartDate	= patternStartDate;
		this.patternEndDate		= patternEndDate;
		this.occurrences		= 0;

		return this;
	}

	/**
	 * Sets the patterns range
	 * 
	 * @param		patternStartDate the start date for this pattern
	 * @param		occurrences the number of occurences for this
	 * 				pattern or <code>null</code>
	 * @return		the pattern
	 */
	public RecurrencePatternImp setRange(
		Calendar patternStartDate,
		int occurrences) {

		this.patternStartDate	= patternStartDate;
		this.occurrences		= occurrences;

		// calculate the patterns end date
		this.patternEndDate		= calcPatternEndDate();

		return this;
	}

	/**
	 * @see com.cc.framework.ui.model.RecurrencePattern#match(java.util.Calendar)
	 */
	public boolean match(Calendar date) {

		if ((recurType == null) || RecurrenceType.NONE.equals(recurType)) {
			return false;
		}

		// First check if the date falls between our
		// pattern bounds
		if (!CalendarHelp.between(date, patternStartDate, patternEndDate)) {
			return false;
		}

		// Check the recurrence pattern
		if (RecurrenceType.DAILY.equals(recurType)) {
			return matchDaily(date);
		} else if (RecurrenceType.WEEKLY.equals(recurType)) {
			return matchWeekly(date);
		} else if (RecurrenceType.MONTHLY.equals(recurType)) {
			return matchMonthly(date);
		} else if (RecurrenceType.MONTHLY_NTH.equals(recurType)) {
			return matchMonthlyNth(date);
		} else if (RecurrenceType.YEARLY.equals(recurType)) {
			return matchYearly(date);
		} else if (RecurrenceType.YEARLY_NTH.equals(recurType)) {
			return matchYearlyNth(date);
		} else {
			throw new IllegalArgumentException("Illegal RecurrenceType!");
		}
	}

	/**
	 * Checks if this daily Recurrence pattern matches a certain
	 * date
	 * 
	 * @param		date The date to check
	 * @return		returns <code>true</code> if this recurrence
	 * 				pattern is relevant for the given date
	 */
	protected boolean matchDaily(Calendar date) {
		if ((interval == 0) || (dayOfWeekMask == 0)) {
			return false;
		}
		
		// (1) Check interval -> Every N days
		if (patternStartDate != null) {
			int delta		= CalendarHelp.getDays(patternStartDate, date);

			if ((delta % interval) != 0) {
				return false;
			}
		}
	
		// (2) DayOfWeekMask -> Every Tuesday, Wednesday, and Thursday
		int dayFlag = 1 << date.get(Calendar.DAY_OF_WEEK);

		return ((dayOfWeekMask & dayFlag) == dayFlag);
	}

	/**
	 * Checks if this weekly Recurrence pattern matches a certain
	 * date
	 * 
	 * @param		date The date to check
	 * @return		returns <code>true</code> if this recurrence
	 * 				pattern is relevant for the given date
	 */
	protected boolean matchWeekly(Calendar date) {
		if ((interval == 0) || (dayOfWeekMask == 0)) {
			return false;
		}

		// (1) Check interval -> Every N weeks
		if (patternStartDate != null) {
			int startYear	= patternStartDate.get(Calendar.YEAR);
			int startWeek	= patternStartDate.get(Calendar.WEEK_OF_YEAR);
	
			int year		= date.get(Calendar.YEAR);
			int week		= date.get(Calendar.WEEK_OF_YEAR);
			int delta		= ((year - startYear) * 52) + (week - startWeek);
	
			if ((delta % interval) != 0) {
				return false;
			}
		}

		// (2) DayOfWeekMask -> Every Tuesday, Wednesday, and Thursday
		int dayFlag = 1 << date.get(Calendar.DAY_OF_WEEK);

		return ((dayOfWeekMask & dayFlag) == dayFlag);
	}

	/**
	 * Checks if this monthly Recurrence pattern matches
	 * a certain date
	 * 
	 * @param		date The date to check
	 * @return		returns <code>true</code> if this recurrence
	 * 				pattern is relevant for the given date
	 */
	protected boolean matchMonthly(Calendar date) {
		if ((interval == 0) || (dayOfMonth == 0)) {
			return false;
		}

		// (1) Check interval -> Every N months
		if (patternStartDate != null) {
			int startYear	= patternStartDate.get(Calendar.YEAR);
			int startMonth	= patternStartDate.get(Calendar.MONTH);
	
			int year		= date.get(Calendar.YEAR);
			int month		= date.get(Calendar.MONTH);
			int delta		= ((year - startYear) * 12) + (month - startMonth);
	
			if ((delta % interval) != 0) {
				return false;
			}
		}

		// (2) Check DayOfMonth -> The Nth day of the month
		int day = date.get(Calendar.DAY_OF_MONTH);

		int max = date.getActualMaximum(Calendar.DAY_OF_MONTH);

		if (dayOfMonth >= max) {
			return (day == max);
		} else {
			return (dayOfMonth == day);
		}
	}

	/**
	 * Checks if this monthly Recurrence pattern matches
	 * a certain date
	 * 
	 * @param		date The date to check
	 * @return		returns <code>true</code> if this recurrence
	 * 				pattern is relevant for the given date
	 */
	protected boolean matchMonthlyNth(Calendar date) {
		if ((interval == 0) || (dayOfWeekMask == 0) || (instance == 0)) {
			return false;
		}

		// (1) Check interval -> Every N months
		if (patternStartDate != null) {
			int startYear	= getAppointment().getStartTime().get(Calendar.YEAR);
			int startMonth	= getAppointment().getStartTime().get(Calendar.MONTH);
	
			int year		= date.get(Calendar.YEAR);
			int month		= date.get(Calendar.MONTH);
			int delta		= ((year - startYear) * 12) + (month - startMonth);
	
			if ((delta % interval) != 0) {
				return false;
			}
		}

		// (2) DayOfWeekMask -> Every Tuesday and Wednesday
		// (3) Instance -> The Nth Tuesday
		return checkDayInstance(
			date.get(Calendar.DAY_OF_MONTH),
			date.getActualMaximum(Calendar.DAY_OF_MONTH),
			date.get(Calendar.DAY_OF_WEEK),
			dayOfWeekMask,
			instance);
	}

	/**
	 * Checks if this yearly Recurrence pattern matches a certain
	 * date
	 * 
	 * @param		date The date to check
	 * @return		returns <code>true</code> if this recurrence
	 * 				pattern is relevant for the given date
	 */
	protected boolean matchYearly(Calendar date) {
		if ((monthOfYear == 0) || (dayOfMonth == 0)) {
			return false;
		}

		// (1) DayOfMonth -> The Nth day of the month
		// (2) MonthOfYear -> February
		int month	= date.get(Calendar.MONTH);
		int day		= date.get(Calendar.DAY_OF_MONTH);

		return (monthOfYear == month) && (dayOfMonth == day);
	}

	/**
	 * Checks if this yearly Recurrence pattern matches a certain
	 * date
	 * 
	 * @param		date The date to check
	 * @return		returns <code>true</code> if this recurrence
	 * 				pattern is relevant for the given date
	 */
	protected boolean matchYearlyNth(Calendar date) {
		if ((instance == 0) || (dayOfWeekMask == 0)) {
			return false;
		}

		// (1) MonthOfYear -> February
		int month	= date.get(Calendar.MONTH);

		if (monthOfYear != month) {
			return false;
		}

		// (2) DayOfWeekMask -> Every Tuesday and Wednesday
		// (3) Instance -> The Nth Tuesday
		return checkDayInstance(
			date.get(Calendar.DAY_OF_MONTH),
			date.getActualMaximum(Calendar.DAY_OF_MONTH),
			date.get(Calendar.DAY_OF_WEEK),
			dayOfWeekMask,
			instance);
	}


	/**
	 * Checks if the given day matches the instance
	 * 
	 * @param		dayOfMonth The day of month to check
	 * @param		dayOfMonthMax The maximum days of the current month
	 * @param		dayOfWeek Day of week to check
	 * @param		dayOfWeekMask The mask with the allowed days
	 * @param		instance the instance to check
	 * @return		<code>true</code> if the given day matches the
	 * 				instance
	 */
	protected static boolean checkDayInstance(
		int dayOfMonth,
		int dayOfMonthMax,
		int dayOfWeek,
		int dayOfWeekMask,
		int instance) {

		// Simple plausi check
		if ((instance == 0) || (dayOfWeekMask == 0)) {
			return false;
		}

		// Check if the given day matches one of
		// the allowed days
		int dayOfWeekFlag = 1 << dayOfWeek;

		if ((dayOfWeekMask & dayOfWeekFlag) != dayOfWeekFlag) {
			return false;
		}

		if (instance >= 0) {
			// check instance from beginning of month

			if (dayOfWeekMask == dayOfWeekFlag) {
				// performance optimized check for one bit masks
				return instance == ((dayOfMonth - 1) / 7) + 1;
			} else {
				while ((dayOfMonth > 0) && (instance >= 0)) {

					if ((dayOfWeekMask & dayOfWeekFlag) == dayOfWeekFlag) {
						--instance;
					}

					--dayOfMonth;
					dayOfWeekFlag >>= 1;

					if (dayOfWeekFlag < CalendarHelp.DOWM_SUNDAY) {
						dayOfWeekFlag = CalendarHelp.DOWM_SATURDAY;
					}
				}

				return (instance == 0);
			}
		} else {
			// check instance from end of month

			if (dayOfWeekMask == dayOfWeekFlag) {
				// performance optimized check for one bit masks
				return instance == ((dayOfMonth - dayOfMonthMax) / 7) - 1;
			} else {
				while ((dayOfMonth <= dayOfMonthMax) && (instance <= 0)) {

					if ((dayOfWeekMask & dayOfWeekFlag) == dayOfWeekFlag) {
						++instance;
					}

					++dayOfMonth;
					dayOfWeekFlag <<= 1;

					if (dayOfWeekFlag > CalendarHelp.DOWM_SATURDAY) {
						dayOfWeekFlag = CalendarHelp.DOWM_SUNDAY;
					}
				}

				return (instance == 0);
			}
		}
	}

	/**
	 * This method calculates the patterns end date from the pattern
	 * start date and the number of occurences
	 *  
	 * @return		calculated pattern end date
	 */
	protected Calendar calcPatternEndDate() {
		if (occurrences <= 0) {
			// This recurrence pattern has an open end
			return null;
		} else {
			// TODO: calculate the patterns end date
			return null;
		}
	}
}