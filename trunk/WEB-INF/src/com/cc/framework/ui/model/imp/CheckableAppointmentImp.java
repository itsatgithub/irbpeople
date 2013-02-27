/*
 * $Header$
 * $Revision$
 * $Date$
 *
 * ====================================================================
 *
 * Copyright (c) 2000 - 2005 SCC Informationssysteme GmbH. All rights
 * reserved.
 * Vendor URL : http://www.scc-gmbh.com
 * Product URL: http://www.common-controls.com
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL SCC INFORMATIONSSYSTEME GMBH OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 */

package com.cc.framework.ui.model.imp;

import java.util.Calendar;
import java.util.Hashtable;
import java.util.Map;

import com.cc.framework.common.CheckState;
import com.cc.framework.ui.model.AppointmentPriority;
import com.cc.framework.ui.model.CheckableAppointment;

/**
 * This Class adds checkability to simple appointments
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision$
 */
public class CheckableAppointmentImp extends AppointmentImp implements CheckableAppointment {

	/**
	 * Checked Appointments
	 */
	private Map checkState = null;
	
	/**
	 * Creates a simple appointment
	 */
	public CheckableAppointmentImp() {
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
	public CheckableAppointmentImp(
		String id,
		String subject,
		Calendar startTime,
		Calendar endTime,
		AppointmentPriority priority) {

		super(id, subject, startTime, endTime, priority);
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
	public CheckableAppointmentImp(
		String id,
		String subject,
		Calendar startTime,
		int duration,
		AppointmentPriority priority) {

		super(id, subject, startTime, duration, priority);
	}

	/**
	 * Creates a simple allday event
	 * 
	 * @param		id The unique Id of this appointment
	 * @param		subject The Appointments Subject 
	 * @param		startTime Start Time
	 * @param		priority Priority
	 */
	public CheckableAppointmentImp(
		String id,
		String subject,
		Calendar startTime,
		AppointmentPriority priority) {

		super(id, subject, startTime, priority);
	}

	/**
	 * @see com.cc.framework.ui.model.CheckableAppointment#getCheckState(long)
	 */
	public int getCheckState(long timeInMillis) {
		if (checkState != null) {
			Long key = new Long(timeInMillis);
		
			if (checkState.containsKey(key)) {
				return ((Integer) checkState.get(key)).intValue();
			} else {
				return CheckState.UNCHECKED.toInt();
			}
		} else {
			return CheckState.UNCHECKED.toInt();
		}
	}

	/**
	 * @see com.cc.framework.ui.model.CheckableAppointment#setCheckState(long, int)
	 */
	public void setCheckState(long timeInMillis, int newState) {
		if (checkState == null) {
			checkState = new Hashtable();
		}

		Long key = new Long(timeInMillis);

		checkState.remove(key);
		
		if (key.intValue() != 0) {
			checkState.put(key, new Integer(newState));
		}
	}
}