/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/AppointmentImp.java,v 1.12 2005/10/11 13:53:03 P001002 Exp $
 * $Revision: 1.12 $
 * $Date: 2005/10/11 13:53:03 $
 *
 * ====================================================================
 *
 * Copyright (c) 2002 SCC Informationssysteme GmbH. All rights
 * reserved.
 *
 * ====================================================================
 */

package com.cc.framework.ui.model.imp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import uicomponents.scheduler.ICalendar;

import com.cc.framework.ui.model.Appointment;
import com.cc.framework.ui.model.AppointmentPriority;
import com.cc.framework.ui.model.RecurrencePattern;
import com.cc.framework.util.CalendarHelp;

/**
 * Ein konkreter Termin
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.12 $
 */
public class AppointmentImp implements Appointment {

	/**
	 * The Unique Id for this Appointment
	 */
	private String id;

    /*
     * Modification to integrate with ZoneConstructorUtils
     */
 //   private IEvent event = null;
	private String type;
    
	/**
	 * This Flag indicates that this is an all day event.
	 */
	private boolean allDayEvent = false;

	/**
	 * The Start Time of the Appointment. A <code>null</code>
	 * value indicates an open beginning
	 */
	private Calendar startTime = null;

	/**
	 * The End Time. A <code>null</code> value indicates an
	 * open end
	 */
	private Calendar endTime = null;

	/**
	 * Priority
	 */
	private AppointmentPriority priority = AppointmentPriority.NORMAL;

	/**
	 * The Reccurence pattern for recurring appointments
	 */
	private RecurrencePattern pattern = null;

	/**
	 * Schort description for this Appointment Item
	 */
	private String subject;

	/**
	 * Description Text
	 */
	private String text;

	/**
	 * Optional Hyperlink
	 */
	private String link;

	/**
	 * Reference to an image for this Appointment Item
	 */
	private String imageRef = null;

	/**
	 * Creates a simple appointment
	 */
	public AppointmentImp() {
		super();
	}

	/**
	 * Creates a simple appointment
	 *
	 * @param		id The unique Id of this appointment
	 * @param		subject The Appointments Subject 
	 * @param		startTime Start Time
	 * @param		endTime End Time or <code>null</code>
	 * @param		priority Priority
	 */
	public AppointmentImp(
		String id,
		String subject,
		Calendar startTime,
		Calendar endTime,
		AppointmentPriority priority) {
		super();

		this.id				= id;
		this.startTime		= startTime;
		this.endTime		= endTime;
		this.allDayEvent	= false;
		this.subject		= subject;
		this.priority		= priority;
		
		if (endTime == null) {
			// This is an all day event
			setAllDayEvent(true);
		}
	}

	/**
	 * Creates a simple appointment
	 * 
	 * @param		id The unique Id of this appointment
	 * @param		subject The Appointments Subject 
	 * @param		startTime Start Time
	 * @param		duration Duration
	 * @param		priority Priority
	 */
	public AppointmentImp(
		String id,
		String subject,
		Calendar startTime,
		int duration,
		AppointmentPriority priority) {
		super();

		this.id				= id;
		this.startTime		= startTime;
		this.endTime		= (Calendar) startTime.clone();
		this.allDayEvent	= false;
		endTime.add(Calendar.MINUTE, duration);
		this.subject		= subject;
		this.priority		= priority;
	}

	/**
	 * Creates a simple allday event
	 * 
	 * @param		id The unique Id of this appointment
	 * @param		subject The Appointments Subject 
	 * @param		startTime Start Time
	 * @param		priority Priority
	 */
	public AppointmentImp(
		String id,
		String subject,
		Calendar startTime,
		AppointmentPriority priority) {
		super();

		this.id				= id;
		this.startTime		= startTime;
		this.endTime		= null;
		this.subject		= subject;
		this.priority		= priority;

		// Make this appointment an all day event
		setAllDayEvent(true);
	}

	/**
	 * @see com.cc.framework.ui.model.Appointment#getPriority()
	 */
	public AppointmentPriority getPriority() {
		return priority;
	}

	/**
	 * @see com.cc.framework.ui.model.Appointment#getRecurrencePattern()
	 */
	public RecurrencePattern getRecurrencePattern() {
		return pattern;
	}

	/**
	 * @see com.cc.framework.ui.model.Appointment#getStartTime()
	 */
	public Calendar getStartTime() {
		return startTime;
	}

	/**
	 * @see com.cc.framework.ui.model.Appointment#getEndTime()
	 */
	public Calendar getEndTime() {
		return endTime;
	}

	/**
	 * @see com.cc.framework.ui.model.Appointment#isRecurring()
	 */
	public boolean isRecurring() {
		return (pattern != null);
	}

	/**
	 * @see com.cc.framework.ui.model.Appointment#getLink()
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @see com.cc.framework.ui.model.Appointment#getText()
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the recurrence pattern for recurring appointments
	 * 
	 * @param		pattern Recurrence pattern
	 * @return		the appointment
	 */
	public AppointmentImp setRecurrencePattern(RecurrencePattern pattern) {
		this.pattern = pattern;

		if (pattern != null) {
			pattern.setAppointment(this);
		}

		return this;
	}

	/**
	 * @see com.cc.framework.ui.model.Appointment#getSubject()
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @see com.cc.framework.ui.model.Appointment#isAllDayEvent()
	 */
	public boolean isAllDayEvent() {
		return allDayEvent;
	}

	/**
	 * @see com.cc.framework.ui.model.Appointment#isMultiDayEvent()
	 */
	public boolean isMultiDayEvent() {
		if (isAllDayEvent() && (getEndTime() == null)) {
			return false;
		} else if ((getStartTime() == null) || (getEndTime() == null)) {
			// Appointment has an open beginning/ending
			return true;
		} else {
			return !CalendarHelp.isSameDay(getStartTime(), getEndTime());
		}
	}

	/**
	 * @see com.cc.framework.ui.model.Appointment#getImageRef()
	 */
	public String getImageRef() {
		return imageRef;
	}

	/**
	 * @see com.cc.framework.ui.model.Appointment#getUniqueId()
	 */
	public String getUniqueId() {
		return id;
	}

	/**
	 * Sets the unique identifier for this appointment
	 * 
	 * @param		id unique identifier
	 */
	public void setUniqueId(String id) {
		this.id = id;
	}

	/**
	 * Makes this appointment an all day event
	 * 
	 * @param		allDayEvent <code>true</code> if this is an all
	 * 				day event
	 * @return		the appointment object
	 */
	public AppointmentImp setAllDayEvent(boolean allDayEvent) {
		this.allDayEvent = allDayEvent;

		if (allDayEvent) {
			// Reset the time fields - they are not needed
			if (this.startTime != null) {
				this.startTime.set(Calendar.HOUR_OF_DAY, 0);
				this.startTime.set(Calendar.MINUTE, 0);
				this.startTime.set(Calendar.SECOND, 0);
			}
			
			if (this.endTime != null) {
				this.endTime.set(Calendar.HOUR_OF_DAY, 0);
				this.endTime.set(Calendar.MINUTE, 0);
				this.endTime.set(Calendar.SECOND, 0);
			}
		}

		return this;
	}

	/**
	 * Sets the image reference for this appointment. The image
	 * reference will be mapped against the imagemap of the
	 * scheduler control.
	 * 
	 * @param		imageRef image for this appointment or
	 * 				<code>null</code> 
	 * @return		the appointment object
	 */
	public AppointmentImp setImageRef(String imageRef) {
		this.imageRef = imageRef;
		
		return this;
	}

	/**
	 * Sets the hyperlink that schould be associated with this
	 * appointment.
	 * 
	 * @param		string Hyperlink
	 * @return		the appointment object
	 */
	public AppointmentImp setLink(String string) {
		link = string;
		
		return this;
	}

	/**
	 * Sets the appointments subject text. The subject will be
	 * shown in the scheduler control
	 * 
	 * @param		string Subject text
	 * @return		the appointment object
	 */
	public AppointmentImp setSubject(String string) {
		subject = string;
		
		return this;
	}

	/**
	 * Sets the long descriptive text for this appointment
	 * 
	 * @param		string Text
	 * @return		the appointment object
	 */
	public AppointmentImp setText(String string) {
		text = string;
		
		return this;
	}

	/**
	 * Sets the appointments priority
	 * 
	 * @param		priority the priority
	 */
	public void setPriority(AppointmentPriority priority) {
		this.priority = priority;
	}

	/**
	 * Sets the appointments end time
	 * 
	 * @param		calendar end time
	 */
	public void setEndTime(Calendar calendar) {
		endTime = calendar;
	}

	/**
	 * Sets the appointments start time
	 * 
	 * @param		calendar Start Time
	 */
	public void setStartTime(Calendar calendar) {
		startTime = calendar;
	}

	/**
	 * @see com.cc.framework.ui.model.Appointment#match(java.util.Calendar)
	 */
	public boolean match(Calendar date) {
		if (isRecurring()) {
			return getRecurrencePattern().match(date);
		} else {
			if (isAllDayEvent() && (getEndTime() == null)) {
				return CalendarHelp.between(date, getStartTime(), getStartTime());
			} else {
				return CalendarHelp.between(date, getStartTime(), getEndTime());
			}
		}
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		DateFormat df = new SimpleDateFormat("d MMM yyyy HH:mm:ss", Locale.US);

		StringBuffer buf = new StringBuffer();

		if (startTime != null) {
			buf.append(df.format(startTime.getTime()));
		} else {
			buf.append("null");
		}

		buf.append(" - ");

		if (endTime != null) {
			buf.append(df.format(endTime.getTime()));
		} else {
			buf.append("null");
		}
		
		buf.append(": ").append(subject);

		return buf.toString();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

//    public IEvent getEvent() {
//        return event;
//    }
//
//    public void setEvent(IEvent event) {
//        this.event = event;
//    }
}