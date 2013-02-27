/**
 * $Header: d:/repository/cvs/cc-adapter-struts-1.2/adapter/com/cc/framework/taglib/StrutsSelectTagComparator.java,v 1.2 2005/01/27 09:22:59 P001002 Exp $
 * $Revision: 1.2 $
 * $Date: 2005/01/27 09:22:59 $
 *
 * ====================================================================
 *
 * Copyright (c) 2002 SCC Informationssysteme GmbH. All rights
 * reserved.
 *
 * ====================================================================
 */

package com.cc.framework.taglib;

import org.apache.struts.taglib.html.SelectTag;

import com.cc.framework.ui.painter.html.OptionsComparator;

/**
 * Specialised options Comparator for integration with the
 * Struts Framework
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.2 $
 */
public class StrutsSelectTagComparator implements OptionsComparator {

	/**
	 * Select Tag
	 */
	private SelectTag tag = null;

	/**
	 * Constructor
	 * @param tag	SelectTag
	 */
	public StrutsSelectTagComparator(SelectTag tag) {
		super();

		this.tag = tag;
	}

	/**
	 * Metode match
	 * @param	option	String to macth
	 * @return	boolean
	 */
	public boolean match(Object option) {
		if (option == null) {
			return false;
		} else {
			return tag.isMatched(option.toString());
		}
	}
}
