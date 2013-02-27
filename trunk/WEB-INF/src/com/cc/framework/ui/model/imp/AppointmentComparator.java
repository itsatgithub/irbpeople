/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/AppointmentComparator.java,v 1.2 2005/06/16 19:38:58 P001002 Exp $
 * $Revision: 1.2 $
 * $Date: 2005/06/16 19:38:58 $
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
import java.util.Comparator;

import com.cc.framework.ui.model.Appointment;

/**
 * Comparator for Appointment items. This comparator compares
 * the appointment start times.
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.2 $
 */
public class AppointmentComparator implements Comparator {

	/**
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Object o1, Object o2) {

		Appointment a1	= (Appointment) o1;
		Appointment a2	= (Appointment) o2;

		// Move all day or multiple day events to the top
		if (a1.isAllDayEvent() || a1.isMultiDayEvent()) {
			if (!(a2.isAllDayEvent() || a2.isMultiDayEvent())) {
				// Allday events a1 first
				return -1;
			}
		} else if (a2.isAllDayEvent() || a2.isMultiDayEvent()) {
			return 1;
		}

		// Compare the start dates
		Calendar s1 = a1.getStartTime();
		Calendar s2 = a2.getStartTime();

		if (s1 == null) {
			// Appointment 1 has no starting date
			if (s2 == null) {
				// Both appointments have no starting date
				return 0;
			} else {
				return -1;
			}
		} else if (s2 == null) {
			// Appointment 2 has no starting date
			return 1;
		} else {
			if (s1.before(s2)) {
				return -1;
			} else if (s1.after(s2)) {
				return 1;
			} else {
				return 0;
			}
		}
	}
}
