/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/RecurrenceException.java,v 1.1 2005/05/06 12:43:19 P001002 Exp $
 * $Revision: 1.1 $
 * $Date: 2005/05/06 12:43:19 $
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
 * Represents an exception in a recurrence pattern.
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.1 $
 */
public interface RecurrenceException {

	/**
	 * Gets a value specifying whether the occurrence is deleted.
	 * 
	 * @return		<code>true</code> if the occurence is deleted
	 */
	public boolean isDeleted();

	/**
	 * Gets value specifying when this exception was suppose to occur.
	 * 
	 * @return		Date
	 */
	public Calendar getOriginalDate();

	/**
	 * Gets the RecurrencePattern object associated with this
	 * exception.
	 * 
	 * @return		Recurrence Pattern
	 */
	public RecurrencePattern getRecurrencePattern();
}
