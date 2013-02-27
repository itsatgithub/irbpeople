/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/DefClassType.java,v 1.4 2005/08/23 07:14:44 P001002 Exp $
 * $Revision: 1.4 $
 * $Date: 2005/08/23 07:14:44 $
 *
 * ====================================================================
 *
 * Copyright (c) 2002 SCC Informationssysteme GmbH. All rights
 * reserved.
 *
 * ====================================================================
 */

package com.cc.framework.ui.painter.def;

/**
 * HTML Class types. Class Types are translated by the 
 * getElementClass(int type) method in concrete HTML Classes
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.4 $
 */
public interface DefClassType {

	/** control class */
	int CLASS_CONTROL					= 1;

	/** body class */
	int CLASS_BODY						= 2;

	/** caption class */
	int CLASS_CAPTION					= 3;

	/** header class */
	int CLASS_HEADER					= 4;

	/** footer class */
	int CLASS_FOOTER					= 5;

	/** body frame left */
	int CLASS_BODY_LEFT					= 6;

	/** body frame right */
	int CLASS_BODY_RIGHT				= 7;

	/** section class */
	int CLASS_SECTION					= 8;

	/** detail class */
	int CLASS_DETAIL					= 9;

	/** form element label class */
	int CLASS_LABEL						= 10;

	/** form element data class */
	int CLASS_DATA						= 11;

	/** button container class */
	int CLASS_BUTTONS					= 12;

	/** mesage indicator class */
	int CLASS_INDICATOR					= 13;

	/** inner frame class */
	int CLASS_INNER_FRAME				= 14;
	
	/** inner frame without border class */
	int CLASS_INNER_FRAME_NO_BORDER		= 15;
	
	/** inner body */
	int CLASS_INNER_BODY				= 16;
	
	/** Hidden Button */
	int CLASS_HIDENBUTTON				= 17;
}