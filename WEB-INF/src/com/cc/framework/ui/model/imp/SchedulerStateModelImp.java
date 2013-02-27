/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/SchedulerStateModelImp.java,v 1.8 2005/09/12 10:33:37 P001002 Exp $
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

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.cc.framework.ui.model.SchedulerStateModel;
import com.cc.framework.ui.model.SchedulerView;
import com.cc.framework.util.PropertyMap;

/**
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.8 $
 */
public class SchedulerStateModelImp implements SchedulerStateModel, Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1467099945406536251L;

	/**
	 * Specifies the style used to display dates in a Scheduler
	 * control
	 */
	private SchedulerView view = SchedulerView.MONTH;

	/**
	 * The first visible Date in the Scheduler Control
	 */
	private Calendar date = GregorianCalendar.getInstance();

	/**
	 * @see com.cc.framework.ui.model.SchedulerStateModel#getView()
	 */
	public SchedulerView getView() {
		return view;
	}

	/**
	 * @see com.cc.framework.ui.model.SchedulerStateModel#setView(com.cc.framework.ui.model.SchedulerView)
	 */
	public void setView(SchedulerView view) {
		this.view = view;
	}

	/**
	 * @see com.cc.framework.ui.model.SchedulerStateModel#getDate()
	 */
	public Calendar getDate() {
		return date;
	}

	/**
	 * @see com.cc.framework.ui.model.SchedulerStateModel#setDate(java.util.Calendar)
	 */
	public void setDate(Calendar date) {
		this.date = date;
	}

	/**
	 * @see com.cc.framework.ui.model.StateModel#reset()
	 */
	public void reset() {
		view = SchedulerView.MONTH;
	}

	/**
	 * @see com.cc.framework.ui.model.StateModel#synchronizeState(com.cc.framework.util.PropertyMap)
	 */
	public void synchronizeState(PropertyMap properties) throws Exception {
		if (properties.hasProperty(PROP_VIEW)) {
			// Set the current view
			view = SchedulerView.parse(properties.getProperty(PROP_VIEW));
		}
	}
}
