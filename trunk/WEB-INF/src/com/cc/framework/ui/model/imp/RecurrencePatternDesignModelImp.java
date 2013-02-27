/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/RecurrencePatternDesignModelImp.java,v 1.2 2005/07/08 15:15:32 P001002 Exp $
 * $Revision: 1.2 $
 * $Date: 2005/07/08 15:15:32 $
 *
 * ====================================================================
 *
 * Copyright (c) 2002 SCC Informationssysteme GmbH. All rights
 * reserved.
 *
 * ====================================================================
 */

package com.cc.framework.ui.model.imp;

import com.cc.framework.ui.model.RecurrencePatternDesignModel;

/**
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.2 $
 */
public class RecurrencePatternDesignModelImp
	extends ControlDesignModelImp
	implements RecurrencePatternDesignModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 6081804566530106018L;

	/**
	 * Specifies if all String should be converted
	 * into there HTML representation
	 */
	private boolean filter				= true;

	/**
	 * @see com.cc.framework.ui.model.RecurrencePatternDesignModel#filter()
	 */
	public boolean filter() {
		return filter;
	}

	/**
	 * @see com.cc.framework.ui.model.RecurrencePatternDesignModel#setFilter(boolean)
	 */
	public void setFilter(boolean filter) {
		this.filter = filter;

	}
}