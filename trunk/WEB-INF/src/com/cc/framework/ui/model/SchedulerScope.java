/**
 * $Header$
 * $Revision$
 * $Date$
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

import com.cc.framework.common.InvalidEnumType;
import com.cc.framework.common.SimpleEnumType;
import com.cc.framework.util.StringHelp;

/**
 * Specifies where to show checkboxes in a scheduler control
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision$
 */
public class SchedulerScope implements SimpleEnumType, Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -6620090864437085364L;

	/**
	 * Mask for all views
	 */
	public static final int MASK_ALL = 0x00FF;

	/**
	 * The control will be displayed in appointment view
	 */
	public static final SchedulerScope APPOINTMENT	= new SchedulerScope("appointment", 0);

	/**
	 * The control will be displayed in day view
	 */
	public static final SchedulerScope DAY			= new SchedulerScope("day", 1);

	/**
	 * The internal view name
	 */
	private String view;

	/**
	 * An ordinal value for the bit position in a scope
	 * mask
	 */
	private int bit = 0;

	/**
	 * Collection with all enumeration elements
	 */
	private static final SchedulerScope[] ALL = { APPOINTMENT, DAY };

	// ----------------------------------
	// methods
	// ----------------------------------

	/**
	 * Constructor for SchedulerCheckRange
	 * 
	 * @param view
	 *            Specifies where to show checkboxes in a scheduler control
	 * @param bit
	 *            Ordinal value for the bit position in a scope mask
	 */
	private SchedulerScope(String view, int bit) {
		super();

		this.view = view;
		this.bit = bit;
	}

	/**
	 * @return returns a Bitmask for this view
	 */
	public int mask() {
		return 1 << bit;
	}

	/**
	 * Returns the SchedulerCheckRange
	 * 
	 * @return The SchedulerCheckRange
	 */
	public String toString() {
		return view;
	}

	/**
	 * Checks if the view is contained in the given mask
	 * 
	 * @param viewMask
	 *            The View Mask to test
	 * @return <code>true</code> if the view is set in the mask
	 */
	public boolean isInMask(int viewMask) {
		return (viewMask & mask()) != 0;
	}

	/**
	 * Compares this type to the specified object.
	 * 
	 * @param obj
	 *            The object to compare this <code>SchedulerCheckRange</code>
	 *            object against.
	 * @return <code>true</code> if the internal types are equal;
	 *         <code>false</code> otherwise.
	 */
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		} else if (obj instanceof String) {
			return view.equals(obj);
		} else if (obj instanceof SchedulerScope) {
			return view.equals(((SchedulerScope) obj).view);
		}

		return false;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return view.hashCode();
	}

	/**
	 * Creates for the argument an object of type SchedulerCheckRange
	 * 
	 * @param code
	 *            String to parse
	 * @return The new Object
	 */
	public static SchedulerScope parse(String code) {

		for (int index = 0; index < ALL.length; index++) {
			if (ALL[index].equals(code)) {
				return ALL[index];
			}
		}

		throw new InvalidEnumType("Invalid SchedulerCheckRange: " + code);
	}

	/**
	 * Converts the semicolon delimited list of views into a view mask
	 * 
	 * @param viewList
	 *            semicolon delimited list of views or <code>"true"</code> for
	 *            all possible views or <code>"false"</code for no view
	 * @return nit mask
	 */
	public static int parseMask(String viewList) {
		String[] views = StringHelp.split(viewList, ";");

		int mask = 0;

		for (int i = 0; i < views.length; i++) {
			if ("true".equals(views[i])) {
				mask = SchedulerScope.MASK_ALL;
				break;
			} else if ("false".equals(views[i])) {
				mask = 0;
				break;
			} else {
				mask |= parse(views[i]).mask();
			}
		}

		return mask;
	}
}