/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/SchedulerDesignModelImp.java,v 1.23 2005/11/13 14:03:57 P001002 Exp $
 * $Revision: 1.23 $
 * $Date: 2005/11/13 14:03:57 $
 *
 * ====================================================================
 * 
 * Copyright (c) 2002 SCC Informationssysteme GmbH.  All rights 
 * reserved.
 *
 * ====================================================================
 */

package com.cc.framework.ui.model.imp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Map;

import com.cc.framework.security.Permission;
import com.cc.framework.security.Principal;
import com.cc.framework.security.StaticPermission;
import com.cc.framework.ui.ImageMap;
import com.cc.framework.ui.control.ControlButton;
import com.cc.framework.ui.model.FrameUtil;
import com.cc.framework.ui.model.ImageModel;
import com.cc.framework.ui.model.InnerFrame;
import com.cc.framework.ui.model.SchedulerDesignModel;
import com.cc.framework.ui.model.SchedulerView;
import com.cc.framework.util.CalendarHelp;

/**
 * DesignModel for the Appointment Scheduler
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.23 $
 */
public class SchedulerDesignModelImp
	extends ControlDesignModelImp
	implements SchedulerDesignModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 3079377516667935628L;

	/**
	 * The Title of the control
	 */
	private String title = null;

	/**
	 * Specifies if all String should be converted into valid HTML Strings
	 */
	private boolean filter = true;

	/**
	 * Enables or disables the controls frame
	 */
	private boolean showFrame = true;

	/**
	 * The users permission which is necessary to see
	 * any buttons (Map&lt;ControlButton, Permission&gt;)
	 */
	private Map buttonPermissions = new Hashtable();

	/**
	 * The Image Map
	 */
	private ImageMap imageMap = null;

	/**
	 * Compress Weekends in Month View
	 */
	private boolean compressWeekEnds = true;

	/**
	 * Schow Weekends in month and navigator view
	 */
	private boolean showWeekEndDays = true;

	/**
	 * Schow or hide the all day area in day view
	 */
	private boolean showAllDayArea = true;

	/**
	 * The starting hour of the day
	 */
	private int dayStartHour = 6;

	/**
	 * The ending hour of the day
	 */
	private int dayEndHour = 19;

	/**
	 * The working start hour of the day
	 */
	private int workStartHour = 8;

	/**
	 * The working end hour of the day
	 */
	private int workEndHour = 17;

	/**
	 * The time interval for day view. Valid values are 5, 6, 15, 30 or 60
	 */
	private int interval = 30;

	/**
	 * The maximum number of visible Appointments for one day
	 */
	private int maxVisible = 4;

	/**
	 * A Mask indicating the working days of the week. Default is (Mo - Fr)
	 */
	private int dayMask = CalendarHelp.DOWM_WEEKDAYS;

	/**
	 * The index of the first day of the week. A value of 0 indicates that the
	 * Locales default should be used
	 */
	private int firstDayOfWeek = 0;

	/**
	 * the number of columns in year view
	 */
	private int columns = 1;

	/**
	 * the number of rows in year view
	 */
	private int rows = 1;

	/**
	 * The number of month to increment in year view when the user clicks on one
	 * of the navigation buttons. A value of 0 resets the the control to the
	 * default increment which is the number of visible months (= rows *
	 * columns)
	 */
	private int increment = 0;

	/**
	 * Indicates if appointments in year view should be displayed in popup
	 * windows
	 */
	private boolean popups = true;

	/**
	 * Indicates where to displayed checkboxes<br>
	 * The appointment entries must implement the
	 * <code>AppointmentCheckable</code> Interface, so that the control
	 * element can draw the checkboxes.
	 */
	private int checkBoxMask = 0;

	/**
	 * Bit combination of the visible view buttons for this scheduler control.
	 */
	private int viewMask = SchedulerView.MASK_ALL;

	/**
	 * The optional title image
	 */
	private ImageModel image = null;

	/**
	 * Inner Frames
	 */
	private ArrayList frames = new ArrayList();

	/**
	 * Constructor
	 */
	public SchedulerDesignModelImp() {
		super();

		buttonPermissions.put(ControlButton.REFRESH, StaticPermission.NONE);
		buttonPermissions.put(ControlButton.CREATE, StaticPermission.NONE);
		buttonPermissions.put(ControlButton.ADD, StaticPermission.NONE);
		buttonPermissions.put(ControlButton.PRINTLIST, StaticPermission.NONE);
		buttonPermissions.put(ControlButton.EXPORTLIST, StaticPermission.NONE);
	}

	/**
	 * @see com.cc.framework.ui.model.SchedulerDesignModel#getTitle()
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @see com.cc.framework.ui.model.SchedulerDesignModel#setShowFrame(boolean)
	 */
	public void setShowFrame(boolean show) {
		this.showFrame = show;
	}

	/**
	 * @see com.cc.framework.ui.model.SchedulerDesignModel#setTitle(java.lang.String)
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @see com.cc.framework.ui.model.SchedulerDesignModel#showFrame()
	 */
	public boolean showFrame() {
		return showFrame;
	}

	/**
	 * @see com.cc.framework.ui.model.SchedulerDesignModel#compressWeekEnds()
	 */
	public boolean compressWeekEnds() {
		return compressWeekEnds;
	}

	/**
	 * @see com.cc.framework.ui.model.SchedulerDesignModel#setImageMap(com.cc.framework.ui.ImageMap)
	 */
	public void setImageMap(ImageMap map) {
		this.imageMap = map;
	}

	/**
	 * @see com.cc.framework.ui.model.SchedulerDesignModel#getImageMap()
	 */
	public ImageMap getImageMap() {
		return imageMap;
	}

	/**
	 * @see com.cc.framework.ui.model.SchedulerDesignModel#getDayEndHour()
	 */
	public int getDayEndHour() {
		return dayEndHour;
	}

	/**
	 * @see com.cc.framework.ui.model.SchedulerDesignModel#getDayStartHour()
	 */
	public int getDayStartHour() {
		return dayStartHour;
	}

	/**
	 * @see com.cc.framework.ui.model.SchedulerDesignModel#getWorkEndHour()
	 */
	public int getWorkEndHour() {
		return workEndHour;
	}

	/**
	 * @see com.cc.framework.ui.model.SchedulerDesignModel#getWorkStartHour()
	 */
	public int getWorkStartHour() {
		return workStartHour;
	}

	/**
	 * @see com.cc.framework.ui.model.SchedulerDesignModel#setCompressWeekEnds(boolean)
	 */
	public void setCompressWeekEnds(boolean comress) {
		this.compressWeekEnds = comress;
	}

	/**
	 * @see com.cc.framework.ui.model.SchedulerDesignModel#setDayEndHour(int)
	 */
	public void setDayEndHour(int hour) {
		if ((hour < 0) || (hour > 23)) {
			throw new IllegalArgumentException("hour must be in range from 0 to 23!");
		}

		this.dayEndHour = hour;
	}

	/**
	 * @see com.cc.framework.ui.model.SchedulerDesignModel#setDayStartHour(int)
	 */
	public void setDayStartHour(int hour) {
		if ((hour < 0) || (hour > 23)) {
			throw new IllegalArgumentException("hour must be in range from 0 to 23!");
		}

		this.dayStartHour = hour;
	}

	/**
	 * @see com.cc.framework.ui.model.SchedulerDesignModel#setShowAllDayArea(boolean)
	 */
	public void setShowAllDayArea(boolean show) {
		this.showAllDayArea = show;
	}

	/**
	 * @see com.cc.framework.ui.model.SchedulerDesignModel#setWorkEndHour(int)
	 */
	public void setWorkEndHour(int hour) {
		if ((hour < 0) || (hour > 23)) {
			throw new IllegalArgumentException("hour must be in range from 0 to 23!");
		}

		this.workEndHour = hour;
	}

	/**
	 * @see com.cc.framework.ui.model.SchedulerDesignModel#setWorkStartHour(int)
	 */
	public void setWorkStartHour(int hour) {
		if ((hour < 0) || (hour > 23)) {
			throw new IllegalArgumentException("hour must be in range from 0 to 23!");
		}

		this.workStartHour = hour;
	}

	/**
	 * @see com.cc.framework.ui.model.SchedulerDesignModel#showAllDayArea()
	 */
	public boolean showAllDayArea() {
		return showAllDayArea;
	}

	/**
	 * @see com.cc.framework.ui.model.SchedulerDesignModel#setShowWeekEndDays(boolean)
	 */
	public void setShowWeekEndDays(boolean show) {
		this.showWeekEndDays = show;
	}

	/**
	 * @see com.cc.framework.ui.model.SchedulerDesignModel#showWeekEndDays()
	 */
	public boolean showWeekEndDays() {
		return showWeekEndDays;
	}

	/**
	 * @see com.cc.framework.ui.model.SchedulerDesignModel#getInterval()
	 */
	public int getInterval() {
		return interval;
	}

	/**
	 * @see com.cc.framework.ui.model.SchedulerDesignModel#setInterval(int)
	 */
	public void setInterval(int interval) {
		if (!((interval == 5)
			|| (interval == 6)
			|| (interval == 15)
			|| (interval == 30)
			|| (interval == 60))) {
			throw new IllegalArgumentException("valid values for interval are 5;6;15;30;60!");
		}

		this.interval = interval;
	}

	/**
	 * @see com.cc.framework.ui.model.SchedulerDesignModel#getWorkingDayMask()
	 */
	public int getWorkingDayMask() {
		return dayMask;
	}

	/**
	 * An attempt to set DayOfWeekMask to any value less
	 * than 0 or greater than 127 (0x7F) results in an OutOfBounds
	 * Exception.
	 * 
	 * @see com.cc.framework.ui.model.SchedulerDesignModel#setWorkingDayMask(int)
	 */
	public void setWorkingDayMask(int dayMask) {
		if ((dayMask & ~CalendarHelp.DOWM_ALLDAYS) > 0) {
			throw new IndexOutOfBoundsException("invalid day mask!");
		}

		this.dayMask = dayMask;
	}

	/**
	 * @see com.cc.framework.ui.model.SchedulerDesignModel#getFirstDayOfWeek()
	 */
	public int getFirstDayOfWeek() {
		return firstDayOfWeek;
	}

	/**
	 * @see com.cc.framework.ui.model.SchedulerDesignModel#setFirstDayOfWeek(int)
	 */
	public void setFirstDayOfWeek(int dayIndex) {
		if ((dayIndex < 0) || (dayIndex > Calendar.SATURDAY)) {
			throw new IndexOutOfBoundsException("Invalid Day Index: " + dayIndex);
		}

		this.firstDayOfWeek = dayIndex;
	}
	/**
	 * @see com.cc.framework.ui.model.SchedulerDesignModel#getMaxVisible()
	 */
	public int getMaxVisible() {
		return maxVisible;
	}

	/**
	 * @see com.cc.framework.ui.model.SchedulerDesignModel#setMaxVisible(int)
	 */
	public void setMaxVisible(int max) {
		this.maxVisible = max;
	}

	/**
	 * @see com.cc.framework.ui.model.FrameContainer#addInnerFrame(com.cc.framework.ui.model.InnerFrame)
	 */
	public void addInnerFrame(InnerFrame frame) {
		frames.add(frame);
	}

	/**
	 * @see com.cc.framework.ui.model.FrameContainer#getInnerFrames(com.cc.framework.security.Principal, java.lang.Object)
	 */
	public InnerFrame[] getInnerFrames(Principal principal, Object layoutHint) {
		return FrameUtil.filter(frames, principal, layoutHint);
	}

	/**
	 * @see com.cc.framework.ui.model.FrameContainer#getImage()
	 */
	public ImageModel getImage() {
		return image;
	}

	/**
	 * @see com.cc.framework.ui.model.FrameContainer#setImage(com.cc.framework.ui.model.ImageModel)
	 */
	public void setImage(ImageModel img) {
		this.image = img;
	}

	/**
	 * @see com.cc.framework.ui.model.SchedulerDesignModel#getColumns()
	 */
	public int getColumns() {
		return columns;
	}

	/**
	 * @see com.cc.framework.ui.model.SchedulerDesignModel#getRows()
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * @see com.cc.framework.ui.model.SchedulerDesignModel#setColumns(int)
	 */
	public void setColumns(int columns) {
		this.columns = columns;
	}

	/**
	 * @see com.cc.framework.ui.model.SchedulerDesignModel#setRows(int)
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}

	/**
	 * @see com.cc.framework.ui.model.SchedulerDesignModel#setShowPopups(boolean)
	 */
	public void setShowPopups(boolean show) {
		this.popups = show;
	}

	/**
	 * @see com.cc.framework.ui.model.SchedulerDesignModel#showPopups()
	 */
	public boolean showPopups() {
		return popups;
	}

	/**
	 * @see com.cc.framework.ui.model.SchedulerDesignModel#getViewMask()
	 */
	public int getViewMask() {
		return viewMask;
	}

	/**
	 * @see com.cc.framework.ui.model.SchedulerDesignModel#setViewMask(int)
	 */
	public void setViewMask(int mask) {
		this.viewMask = mask;
	}

	/**
	 * @see com.cc.framework.ui.model.SchedulerDesignModel#getFilter()
	 */
	public boolean getFilter() {
		return filter;
	}

	/**
	 * @see com.cc.framework.ui.model.SchedulerDesignModel#setFilter(boolean)
	 */
	public void setFilter(boolean filter) {
		this.filter = filter;
	}

	/**
	 * @see com.cc.framework.ui.model.SchedulerDesignModel#getMonthIncrement()
	 */
	public int getMonthIncrement() {
		return increment;
	}

	/**
	 * @see com.cc.framework.ui.model.SchedulerDesignModel#setMonthIncrement(int)
	 */
	public void setMonthIncrement(int increment) {
		this.increment = increment;
	}

	/**
	 * @see com.cc.framework.ui.model.SchedulerDesignModel#getCheckBoxMask()
	 */
	public int getCheckBoxMask() {
		return checkBoxMask;
	}

	/**
	 * @see com.cc.framework.ui.model.SchedulerDesignModel#setCheckBoxMask(int)
	 */
	public void setCheckBoxMask(int checkBoxMask) {
		this.checkBoxMask = checkBoxMask;
	}

	/**
	 * Retrieves the permission for the given button
	 * 
	 * @param button Button constant
	 * @return Permission or <code>null</code>
	 */
	public Permission getButtonPermission(ControlButton button) {
		return (Permission) buttonPermissions.get(button);
	}
	
	/**
	 * Checks if the button can be displayed.
	 * 
	 * @param  button The Button to query
	 * @param	principal	The principal object
	 * @return	boolean		<code>true</code> if the button should be displayed
	 */
	public boolean showButton(ControlButton button, Principal principal) {
		Permission permission = getButtonPermission(button);
	
		if (permission == null) {
			return false;
		} else {
			return permission.isGranted(principal);
		}
	}

	/**
	 * Sets whether a command button should be displayed by specifying an Access Control List.
	 *  
	 * @param  button The Button to query
	 * @param	permission	Permission
	 */
	public void setButtonPermission(ControlButton button, Permission permission) {
		buttonPermissions.put(button, permission);
	}
}