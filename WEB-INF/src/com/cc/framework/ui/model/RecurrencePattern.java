/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/RecurrencePattern.java,v 1.6 2005/06/06 17:44:41 P001002 Exp $
 * $Revision: 1.6 $
 * $Date: 2005/06/06 17:44:41 $
 *
 * ====================================================================
 *
 * Copyright (c) 2002 SCC Informationssysteme GmbH. All rights
 * reserved.
 *
 * ====================================================================
 */

package com.cc.framework.ui.model;

import java.util.Calendar;

/**
 * Pattern for recurring appointments
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.6 $
 */
public interface RecurrencePattern extends DataModel {

	/**
	 * sets the reference to the appointment item
	 * 
	 * @param		appointment appointment item
	 */
	public void setAppointment(Appointment appointment);

	/**
	 * Sets the recurrence pattern fields
	 * 
	 * @param		recurType Recurrence Type
	 * @param		dayOfMonth Day of Month
	 * @param		dayOfWeekMask Day of Week mask
	 * @param		instance instance
	 * @param		interval interval
	 * @param		monthOfYear month of the year
	 */
	public void setPattern(
		RecurrenceType recurType,
		int dayOfMonth,
		int dayOfWeekMask,
		int instance,
		int interval,
		int monthOfYear);

	/**
	 * @return		Returns the parent AppointmentItem object 
	 */
	public Appointment getAppointment();

	/**
	 * @return		Returns the Recurrence type
	 */
	public RecurrenceType getRecurrenceType();

	/**
	 * The DayOfMonth property contains the calendar date of each month on which
	 * the AppointmentItem is to recur, for example the value 1 to indicate the first
	 * day of every month. The last day of every month can be represented by the
	 * value 31 in monthly recurrences. For yearly recurrences, DayOfMonth cannot
	 * be set past the last possible day in the selected month.
	 * 
	 * If an appointment is viewed and its DayOfMonth property has a value greater
	 * than the number of days in the specified month, it does not generate a
	 * recurrence for that month. This applies, for example, if DayOfMonth is 29
	 * and MonthOfYear is 2. For such an appointment, it only generates a recurrence
	 * in leap years, whereas generate recurrences on February 28 for years
	 * other than leap years.
	 * 
	 * DayOfMonth is only valid if the value of the RecurrenceType property is MONTHLY
	 * or YEARLY. When DayOfMonth is valid on a newly created RecurrencePattern object,
	 * it defaults to the current day of the month.
	 * 
	 * @return		Day of month
	 */ 
	public int getDayOfMonth();

	/**
	 * The maximum value for the DayOfWeekMask property is 127, which is the logical
	 * inclusive OR of all seven days. An attempt to set DayOfWeekMask to any value less
	 * than 0 or greater than 127 results in an OutOfBounds Exception.
	 *
	 * DayOfWeekMask is only valid if the value of the RecurrenceType property is WEEKLY,
	 * MONTHLY_NTH, or YEARLY_NTH. When DayOfWeekMask is valid on a newly created
	 * RecurrencePattern object, it defaults to the current day of the week.
	 * 
	 * Setting DayOfWeekMask to multiple days per week is only valid if the value of the
	 * RecurrenceType property is WEEKLY. Recurrences of type MONTHLY_NTH or YEARLY_NTH
	 * can only use a single day per week. 
	 * 
	 * @return		Day of Week
	 */
	public int getDayOfWeekMask();

	/**
	 * The NoEndDate property contains True if the AppointmentItem object is to recur
	 * indefinitely and False if the recurrence has a terminal date. NoEndDate is always
	 * valid on a newly created RecurrencePattern object and defaults to True.
	 * 
	 * @return 		<code>true</code> if the AppointmentItem object is to recur
	 * 				indefinitely
	 */
	public boolean hasNoEndDate();

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
	 * 
	 * @return		Instance 
	 */
	public int getInstance();

	/**
	 * The Interval property is used when the AppointmentItem is to recur less often than
	 * every recurrence unit, such as once every three days, once every two weeks, or once
	 * every six months. Interval contains a value representing the frequency of recurrence
	 * in terms of recurrence units.
	 * 
	 * @return		Interval
	 */ 
	public int getInterval();

	/**
	 * The MonthOfYear property contains the calendar number of the month in which the
	 * AppointmentItem is to recur, for example the value 2 to indicate February.
	 * 
	 * MonthOfYear is only valid if the value of the RecurrenceType property is YEARLY or
	 * YEARLY_NTH. When MonthOfYear is valid on a newly created RecurrencePattern object,
	 * it defaults to the current month.
	 * 
	 * @return		MonthOfYear
	 */ 
	public int getMonthOfYear();  

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
	 * 
	 * @return		Numer of occurrences
	 */ 
	public int getOccurrences();

	/**
	 * The PatternEndDate property contains the latest possible date of the last occurrence
	 * of the appointment. This qualification is necessary because PatternEndDate is not
	 * required to be included in the pattern specified by the DayOfMonth, DayOfWeekMask,
	 * Instance, Interval, and MonthOfYear properties. A recurrence is generated on PatternEndDate
	 * only if it matches the pattern.
	 * 
	 * @return		PatternEndDate
	 */ 
	public Calendar getPatternEndDate();

	/**
	 * The PatternStartDate property contains the earliest possible date of the first occurrence
	 * of the appointment. This qualification is necessary because PatternStartDate is not required
	 * to be included in the pattern specified by the DayOfMonth, DayOfWeekMask, Instance, Interval,
	 * and MonthOfYear properties. A recurrence is generated on PatternStartDate only if it matches
	 * the pattern.
	 * 
	 * @return		PatternStartDate
	 */
	public Calendar getPatternStartDate();  

	/**
	 * @return		Returns an collection of exceptions for a specified series
	 * 				of recurring appointments
	 */
	public RecurrenceException[] getExceptions();

	/**
	 * This method checks if the recurrence pattern matches the
	 * given date
	 * 
	 * @param		date The date to check
	 * @return		<code>true</code> if the pattern matches the
	 * 				Date
	 */
	public boolean match(Calendar date);
}