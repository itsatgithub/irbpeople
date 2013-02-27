/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/Appointment.java,v 1.6 2005/06/15 19:21:33 P001002 Exp $
 * $Revision: 1.6 $
 * $Date: 2005/06/15 19:21:33 $
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

import uicomponents.scheduler.ICalendar;

/**
 * Appointment Definition
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.6 $
 */
public interface Appointment {

	/**
	 * Retruns the unique Id or this appointment. This is the
	 * value the application must use to identify an appointment.
	 * 
	 * @return		unique Id
	 */
	public String getUniqueId();

	/**
	 * Retruns the starting time of the appointment
	 * 
	 * @return		starting time
	 */
	public Calendar getStartTime();

	/**
	 * Checks if this is an all Day Event
	 * 
	 * @return		<code>true</code> if this is an all day event
	 */
	public boolean isAllDayEvent();

	/**
	 * Checks if the appointment spans multiple days
	 * 
	 * @return		<code>true</code> if the appointment spans
	 * 				multiple days
	 */
	public boolean isMultiDayEvent();
	
	/**
	 * The EndTime property contains the time at which the
	 * appointment is to terminate every time it recurs.
	 * EndTime is always valid on a newly created RecurrencePattern
	 * object and defaults to the EndTime property of the
	 * AppointmentItem object that  created this recurrence pattern.
	 * 
	 * The EndTime property ignores seconds and truncates the
	 * time component to the minute.
	 * 
	 * @return		End Time 
	 */
	public Calendar getEndTime();

	/**
	 * The isRecurring property indicates whether this
	 * appointment is specified as recurring
	 * 
	 * @return		The isRecurring property contains <code>true</code>
	 * 				if the appointment is recurring and <code>false</code>
	 * 				if it is not. isRecurring defaults to <code>false</code>
	 * 				in a newly created AppointmentItem object
	 */
	public boolean isRecurring();

	/**
	 * Returns the Recurrence Pattern for recurring appointments
	 * 
	 * @return		RecurrencePattern
	 */
	public RecurrencePattern getRecurrencePattern();

	/**
	 * Retrieves a short description for this Appointment
	 * 
	 * @return		Short description
	 */
	public String getSubject();

	/**
	 * Retrieves a long description for this Appointment
	 * 
	 * @return		Long Description
	 */
	public String getText();

	/**
	 * Retrieves a optional Hyperlink that can be associated with
	 * this appointment.
	 * 
	 * @return		Hyperlink or <code>null</code>
	 */
	public String getLink();

	/**
	 * Retruns the Priority of this apointment
	 * 
	 * @return		Priority
	 */
	public AppointmentPriority getPriority();

	/**
	 * This method returns the image reference that is assigned
	 * to this appointment. The concrete image will be resolved
	 * with the help of the SchedulerControl's image map
	 *  
	 * @return		Image reference
	 */
	public String getImageRef();

	/**
	 * This method checks if the recurrence pattern matches the
	 * given date
	 * 
	 * @param		date The date to check
	 * @return		<code>true</code> if the pattern matches the
	 * 				Date
	 */
	public boolean match(Calendar date);
	
	
	public String getType();
    
//	======================================
//    /*
//     * Modification to integrate with ZoneConstructorUtils
//     */
//    public void setEvent(ICalendar event);
//    
//    /*
//     * Modification to integrate with ZoneConstructorUtils
//     */
//    public ICalendar getEvent();
//  ==================================
    
}