/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/RecurrencePatternDesignModel.java,v 1.1 2005/05/18 11:22:13 P001002 Exp $
 * $Revision: 1.1 $
 * $Date: 2005/05/18 11:22:13 $
 *
 * ====================================================================
 *
 * Copyright (c) 2002 SCC Informationssysteme GmbH. All rights
 * reserved.
 *
 * ====================================================================
 */

package com.cc.framework.ui.model;

/**
 * Designmodel for the recurrencepattern control.<br>
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.1 $
 */
public interface RecurrencePatternDesignModel extends ControlDesignModel {

	/**
	 * Sets the optional tooltip text
	 * 
	 * @param	tooltip	Tooltip
	 */
	public void setTooltip(String tooltip);

	/**
	 * Retrieves the optional tooltip text
	 * 
	 * @return	tooltip text or <code>null</code>
	 */
	public String getTooltip();

	/**
	 * Returns if the filter is activated (default=true). This means
	 * that all Strings which should be displayed in the HTML page
	 * are html encoded
	 *
	 * @return	<code>true</code> if string will be html encoded;
	 * 			<code>false</code> otherwise
	 */
	public boolean filter();

	/**
	 * Activates the html encoding (filter). Default is true. This
	 * means that all Strings which should be displayed in the HTML page
	 * will be html encoded.
	 *
	 * @param		filter <code>true</code> if strings should be html encoded; false otherwise
	 */
	public void setFilter(boolean filter);
}