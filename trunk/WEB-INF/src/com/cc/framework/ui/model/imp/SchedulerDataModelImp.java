/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/SchedulerDataModelImp.java,v 1.8 2005/09/12 10:33:37 P001002 Exp $
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

import java.util.Calendar;
import java.util.Collection;
import java.util.Enumeration;
import java.util.TimeZone;
import java.util.Vector;

import com.cc.framework.ui.model.Appointment;
import com.cc.framework.ui.model.SchedulerDataModel;

/**
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.8 $
 */
public class SchedulerDataModelImp implements SchedulerDataModel {

	/**
	 * List with simple none recurring appointments
	 */
	private Vector appointments = new Vector();

	/**
	 * The Timezone of the Scheduler
	 */
	private TimeZone timezone = TimeZone.getDefault();

	/**
	 * Constructor
	 */
	public SchedulerDataModelImp() {
		super();
	}

	/**
	 * Constructor
	 *
	 * @param		timezone The Timezone of the Scheduler
	 */
	public SchedulerDataModelImp(TimeZone timezone) {
		super();

		this.timezone = timezone;
	}

	/**
	 * @see com.cc.framework.ui.model.SchedulerDataModel#getTimeZone()
	 */
	public TimeZone getTimeZone() {
		return timezone;
	}

	/**
	 * Adds a new appointment to the list
	 *
	 * @param		appointment The new Appointment
	 */
	public void addAppointment(Appointment appointment) {
		appointments.add(appointment);
	}

	/**
	 * Adds a collection of new appointments to the list
	 *
	 * @param		appointments The new Appointments
	 */
	public void addAppointments(Collection appointments) {
		this.appointments.addAll(appointments);
	}

	/**
	 * Liefert eine Liste mit Terminen für den angegebenen Tag
	 *
	 * @param		date Das Tagesdatum
	 * @return		Terminlistes
	 */
	public Appointment[] getAppointments(Calendar date) {

		Vector matches = new Vector();

		// Die Termine des Anwenders prüfen
		Enumeration e = appointments.elements();
		while (e.hasMoreElements()) {
			 Appointment item = (Appointment) e.nextElement();

			 if (match(item, date)) {
			 	matches.add(item);
			 }
		}

		return (Appointment[]) matches.toArray(new Appointment[matches.size()]);
	}

	/**
	 * @see com.cc.framework.ui.model.SchedulerDataModel#getAppointmentFromId(java.lang.String)
	 */
	public Appointment getAppointmentFromId(String uniqueId) {

		Enumeration e = appointments.elements();
		while (e.hasMoreElements()) {
			 Appointment item = (Appointment) e.nextElement();

			 if (uniqueId.equals(item.getUniqueId())) {
			 	return item;
			 }
		}
		
		// No appoinmtent found with given id
		return null;
	}

	/**
	 * Checks if this appointment matches a certain Day
	 *
	 * @param		appointment The appointment
	 * @param		date The Day the appointment must match
	 * @return		Returns <code>true</code> when the appointment
	 * 				matches the date
	 */
	protected boolean match(Appointment appointment, Calendar date) {
		// Delegate to Appointment implementation
		return appointment.match(date);
	}
}