/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/SchedulerView.java,v 1.7 2005/08/24 17:51:06 P001002 Exp $
 * $Revision: 1.7 $
 * $Date: 2005/08/24 17:51:06 $
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
 * Specifies the style used to display dates in a Scheduler control
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision: 1.7 $
 */
public class SchedulerView implements SimpleEnumType, Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 6380064267219079740L;

	/**
	 * Mask for all views
	 */
	public static final int MASK_ALL = 0x001F;

	/**
	 * The control will be displayed in day view
	 */
	public static final SchedulerView DAY			= new SchedulerView("day", 0);

	/**
	 * The control will be displayed in week view
	 */
	public static final SchedulerView WEEK			= new SchedulerView("week", 1);

	/**
	 * The control will display only work days in a week using day view
	 */	
	public static final SchedulerView WORKWEEK		= new SchedulerView("workweek", 2);

	/**
	 * The control will be displayed in month view
	 */
	public static final SchedulerView MONTH			= new SchedulerView("month", 3);

	/**
	 * The control will be displayed in month short view
	 */
	public static final SchedulerView YEAR			= new SchedulerView("year", 4);

	/**
	 * The internal view name
	 */
	private String view;

	/**
	 * An Bit position in the View Mask
	 */
	private int bit = 0;

	/**
	 * Collection with all enumeration elements
	 */
	private static final SchedulerView[] ALL = { DAY, WEEK, WORKWEEK, MONTH, YEAR };

	// ----------------------------------
	// methods
	// ----------------------------------

	/**
	 * Constructor for ScheduleView
	 * 
	 * @param view
	 *            Specifies the style used to display dates in a Scheduler
	 *            control
	 * @param bit
	 *            Ordinal value for the bit position in the view mask
	 */
	private SchedulerView(String view, int bit) {
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
	 * Returns the ScheduleView
	 * 
	 * @return The ScheduleView
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
	 *            The object to compare this <code>ScheduleView</code> object
	 *            against.
	 * @return <code>true</code> if the internal types are equal;
	 *         <code>false</code> otherwise.
	 */
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		} else if (obj instanceof String) {
			return view.equals(obj);
		} else if (obj instanceof SchedulerView) {
			return view.equals(((SchedulerView) obj).view);
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
	 * Creates for the argument an object of type AlignmentType
	 * 
	 * @param code
	 *            String to parse
	 * @return The new Object
	 */
	public static SchedulerView parse(String code) {

		for (int index = 0; index < ALL.length; index++) {
			if (ALL[index].equals(code)) {
				return ALL[index];
			}
		}

		throw new InvalidEnumType("Invalid SchedulerView: " + code);
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
				mask = SchedulerView.MASK_ALL;
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