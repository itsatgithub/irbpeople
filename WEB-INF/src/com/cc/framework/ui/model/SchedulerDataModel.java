/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/SchedulerDataModel.java,v 1.2 2005/08/17 19:43:30 P001002 Exp $
 * $Revision: 1.2 $
 * $Date: 2005/08/17 19:43:30 $
 *
 * ====================================================================
 * 
 * Copyright (c) 2002 SCC Informationssysteme GmbH.  All rights 
 * reserved.
 *
 * ====================================================================
 */

package com.cc.framework.ui.model;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Scheduler DataModel
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.2 $
 */
public interface SchedulerDataModel extends DataModel {

	/**
	 * Liefert eine Liste mit Terminen für den angegebenen Tag
	 * 
	 * @param		date Das Tagesdatum
	 * @return		Terminlistes
	 */
	public Appointment[] getAppointments(Calendar date);

	/**
	 * Retrieves the appointment with the given key
	 *
	 * @param		uniqueId the unique Identifier of the appointment
	 * @return		Appointment or <code>null</code>
	 */
	public Appointment getAppointmentFromId(String uniqueId);

	/**
	 * Liefert die Zeitzone zu welcher alle Datumsangaben berechnet
	 * werden sollen
	 * 
	 * @return		Zeitzone
	 */
	public TimeZone getTimeZone();
}
