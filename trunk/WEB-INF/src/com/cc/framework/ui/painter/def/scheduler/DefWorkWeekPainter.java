/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/scheduler/DefWorkWeekPainter.java,v 1.6 2005/09/12 10:33:37 P001002 Exp $
 * $Revision: 1.6 $
 * $Date: 2005/09/12 10:33:37 $
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
import java.util.Calendar;
import java.util.Vector;

import org.apache.ecs.ConcreteElement;

import com.cc.framework.ui.model.SchedulerView;

/**
 * The Working Week View Calendar Painter
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.6 $
 */
public class DefWorkWeekPainter extends DefDayViewPainter {

	/**
	 * @see com.cc.framework.ui.painter.def.scheduler.DefSchedulerViewPainter#getDateFormat(int)
	 */
	protected DateFormat getDateFormat(int formatterId) {
		if (formatterId == DATEFORMAT_DETAIL) {
			return getDateFormat(DATEFORMAT_WEEK);
		} else {
			return super.getDateFormat(formatterId);
		}
	}

	/**
	 * @see com.cc.framework.ui.painter.def.scheduler.DefSchedulerViewPainter#doCreateNavigationButton(boolean)
	 */
	public ConcreteElement doCreateNavigationButton(boolean up) {
		Calendar cal = (Calendar) getCtrl().getDate().clone();

		if (up) {		
			cal.add(Calendar.WEEK_OF_YEAR, 1);
		} else {
			cal.add(Calendar.WEEK_OF_YEAR, -1);
		}

		return createNavigationButton(cal, SchedulerView.WORKWEEK, up);
	}

	/**
	 * Collect all working days of the current week
	 * 
	 * @see com.cc.framework.ui.painter.def.scheduler.DefDayViewPainter#getDays()
	 */
	protected Calendar[] getDays() {
		Vector days = new Vector();
		
		Calendar cal = (Calendar) getCtrl().getDate().clone();

		for (int day = Calendar.SUNDAY; day <= Calendar.SATURDAY; day++) {
			if (getCtrl().isWorkingDay(day)) {
				cal.set(Calendar.DAY_OF_WEEK, day);
				
				days.add(cal.clone());
			}
		}

		return (Calendar[]) days.toArray(new Calendar[days.size()]);
	}
}