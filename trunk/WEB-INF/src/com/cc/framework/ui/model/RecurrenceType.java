/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/RecurrenceType.java,v 1.6 2005/07/08 14:19:30 P001002 Exp $
 * $Revision: 1.6 $
 * $Date: 2005/07/08 14:19:30 $
 *
 * ====================================================================
 *
 * Copyright (c) 2002 SCC Informationssysteme GmbH. All rights
 * reserved.
 *
 * ====================================================================
 */

package com.cc.framework.ui.model;

import java.io.Serializable;

import com.cc.framework.common.SimpleEnumType;

/**
 * Enumeration for valid Recurrence Types
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.6 $
 */
public class RecurrenceType implements SimpleEnumType, Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 9053986269790477924L;

	/** Recurrence Type: none */
	public static final RecurrenceType NONE			= new RecurrenceType("none");

	/** Recurrence Type: daily */
	public static final RecurrenceType DAILY		= new RecurrenceType("daily");

	/** Recurrence Type: weekly */
	public static final RecurrenceType WEEKLY		= new RecurrenceType("weekly");

	/** Recurrence Type: monthly */
	public static final RecurrenceType MONTHLY		= new RecurrenceType("monthly");

	/** Recurrence Type: monthlyNth */
	public static final RecurrenceType MONTHLY_NTH	= new RecurrenceType("monthlyNth");

	/** Recurrence Type: yearly */
	public static final RecurrenceType YEARLY		= new RecurrenceType("yearly");

	/** Recurrence Type: yearlyNth */
	public static final RecurrenceType YEARLY_NTH	= new RecurrenceType("yearlyNth");

	/**
	 * Field type
	 */
	private String type;

	/**
	 * List with all Elements
	 */
	private static final RecurrenceType ALL[] =
		{ NONE, DAILY, WEEKLY, MONTHLY, MONTHLY_NTH, YEARLY, YEARLY_NTH };

	/**
	 * Constructor
	 * 
	 * @param		type Wiederholungstyp
	 */
	private RecurrenceType(String type) {
		this.type = type;
	}

	/**
	 * Method toString
	 * 
	 * @return		String
	 */
	public String toString() {
		return type;
	}

	/**
	 * Compares this type to the specified object.
	 *
	 * @param	obj The object to compare this <code>RecurType</code>
	 * 			object against.
	 * @return  <code>true</code> if the internal types are equal;
	 *          <code>false</code> otherwise.
	 */
	public boolean equals(Object obj) {

		if (obj instanceof String) {
			return type.equals(obj);
		} else if (obj instanceof RecurrenceType) {
			return type.equals(((RecurrenceType) obj).type);
		}

		return false;
	}

	/**
	 * Creates for the argument an Object of Type RecurType
	 * @param	code	String to parse
	 * @return	RecurType
	 * @throws	IllegalArgumentException	if the argument can not be pased 
	 */
	public static RecurrenceType parse(String code)
		throws IllegalArgumentException {

		if (code == null) {
			return null;
		}

		for (int index = 0; index < ALL.length; index++) {
			if (ALL[index].equals(code)) {
				return ALL[index];
			}
		}

		throw new IllegalArgumentException("Invalid RecurrenceType: " + code);
	}
}
