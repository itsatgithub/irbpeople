/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/AppointmentPriority.java,v 1.4 2005/07/08 14:19:30 P001002 Exp $
 * $Revision: 1.4 $
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
 * Enumeration Type for Appointment Priorities
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.4 $
 */
public class AppointmentPriority implements SimpleEnumType, Serializable, Comparable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -4693329694653984747L;

	/** importance: none */
	public static final AppointmentPriority NONE	= new AppointmentPriority("none", 0);

	/** importance: low */
	public static final AppointmentPriority LOW		= new AppointmentPriority("low", 1);

	/** importance: normal */
	public static final AppointmentPriority NORMAL	= new AppointmentPriority("normal", 2);

	/** importance: high */
	public static final AppointmentPriority HIGH	= new AppointmentPriority("high", 3);

	/**
	 * The internal type
	 */
	private String type;

	/**
	 * Dringlichkeit
	 */
	private int importance = 0;

	/**
	 * Collection with all kinds of importance types
	 */
	private static final AppointmentPriority ALL[]	= { NONE, LOW, NORMAL, HIGH };

	// ----------------------------------
	//           methods
	// ----------------------------------

	/**
	 * Constructor for AppointmentImportance
	 *
	 * @param	type The importance type to initialize the Object
	 * @param	importance Dringlichkeit
	 */
	private AppointmentPriority(String type, int importance) {
		super();

		this.type		= type;
		this.importance	= importance;
	}

	/**
	 * Returns the importance type
	 *
	 * @return	The importance type
	 */
	public String toString() {
		return type;
	}

	/**
	 * Compares this type to the specified object.
	 *
	 * @param	obj The object to compare this <code>AppointmentImportance</code>
	 * 			object against.
	 * @return  <code>true</code> if the internal types are equal;
	 *          <code>false</code> otherwise.
	 */
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		} else if (obj instanceof String) {
			return type.equals(obj);
		} else if (obj instanceof AppointmentPriority) {
			return type.equals(((AppointmentPriority) obj).type);
		}

		return false;
	}

	/**
	 * Creates for the argument an object of type AppointmentImportance
	 *
	 * @param	code	String to parse
	 * @return	The new Object
	 * @throws	IllegalArgumentException if the argument is not matched
	 */
	public static AppointmentPriority parse(String code) throws IllegalArgumentException {

		if ((code == null) || "".equals(code)) {
			return AppointmentPriority.NONE;
		}

		for (int index = 0; index < ALL.length; index++) {
			if (ALL[index].equals(code)) {
				return ALL[index];
			}
		}

		throw new IllegalArgumentException("Invalid AppointmentImportance: " + code);
	}

	/**
	 * @return		Integer
	 */
	public int toInt() {
		return importance;
	}

	/**
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		return importance - ((AppointmentPriority) o).importance;
	}
}