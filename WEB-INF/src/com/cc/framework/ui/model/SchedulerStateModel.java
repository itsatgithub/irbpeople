/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/SchedulerStateModel.java,v 1.4 2005/07/04 14:22:43 P001002 Exp $
 * $Revision: 1.4 $
 * $Date: 2005/07/04 14:22:43 $
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



/**
 * Scheduler StateModel 
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.4 $
 */
public interface SchedulerStateModel extends StateModel {

	/**
	 * State property for the current view
	 */
	public String PROP_VIEW = "view";

	/**
	 * Retrieves the first visible Date in the Scheduler
	 * Control
	 * 
	 * @return		First visible Date
	 */
	public Calendar getDate();

	/**
	 * Sets the first visible Date in the Scheduler Control
	 *
	 * @param		date The first visible Date
	 */
	public void setDate(Calendar date);

	/**
	 * @return	Returns the style used to display dates in a
	 * 			Scheduler control
	 */
	public SchedulerView getView();

	/**
	 * Specifies the style used to display dates in a Scheduler
	 * control
	 * @param		view The View
	 */
	public void setView (SchedulerView view);
}
