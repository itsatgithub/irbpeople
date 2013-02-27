/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/SchedulerDesignModel.java,v 1.13 2005/11/09 14:43:25 P001002 Exp $
 * $Revision: 1.13 $
 * $Date: 2005/11/09 14:43:25 $
 *
 * ====================================================================
 * 
 * Copyright (c) 2002 SCC Informationssysteme GmbH.  All rights 
 * reserved.
 *
 * ====================================================================
 */

package com.cc.framework.ui.model;

import com.cc.framework.security.Permission;
import com.cc.framework.security.Principal;
import com.cc.framework.ui.ImageMap;
import com.cc.framework.ui.control.ControlButton;

/**
 * Scheduler DesignModel
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision: 1.13 $
 */
public interface SchedulerDesignModel extends ControlDesignModel,
		FrameContainer {

	/**
	 * Returns the Title
	 * 
	 * @return Titel
	 */
	public String getTitle();

	/**
	 * Sets the titel of the ListControl which is display in the Header
	 * 
	 * @param title
	 *            The main title of the control
	 */
	public void setTitle(String title);

	/**
	 * Specifies the name of an Imagemap.
	 * 
	 * @param map
	 *            ImageMap
	 */
	public void setImageMap(ImageMap map);

	/**
	 * Returns the associated ImageMap
	 * 
	 * @return ImageMap
	 */
	public ImageMap getImageMap();

	/**
	 * Disables the frame
	 * 
	 * @param show
	 *            <code>true</code> if the lists frame should be shown
	 */
	public void setShowFrame(boolean show);

	/**
	 * @return <code>true</code> if the frame should be shown
	 */
	public boolean showFrame();

	/**
	 * @return Gets a value indicating whether the weekends should be compressed
	 *         in one day
	 */
	public boolean compressWeekEnds();

	/**
	 * Sets a value indicating whether the weekends should be compressed in one
	 * day
	 * 
	 * @param comress
	 *            boolean value
	 */
	public void setCompressWeekEnds(boolean comress);

	/**
	 * @return Gets a value indicating the start of the hour range displayed by
	 *         the Schedule on DayView.
	 */
	public int getDayStartHour();

	/**
	 * Sets a value indicating the start of the hour range displayed by the
	 * Schedule on DayView.
	 * 
	 * @param hour
	 *            the start hour
	 */
	public void setDayStartHour(int hour);

	/**
	 * @return Gets a value indicating the end of the hour range displayed by
	 *         the Schedule on DayView.
	 */
	public int getDayEndHour();

	/**
	 * Sets a value indicating the end of the hour range displayed by the
	 * Schedule on DayView.
	 * 
	 * @param hour
	 *            the start hour
	 */
	public void setDayEndHour(int hour);

	/**
	 * @return Gets a value indicating the start time of the day for the working
	 *         hours.
	 */
	public int getWorkStartHour();

	/**
	 * Sets a value indicating the start time of the day for the working hours.
	 * 
	 * @param hour
	 *            the start hour
	 */
	public void setWorkStartHour(int hour);

	/**
	 * @return Gets a value indicating the end time of the day for the working
	 *         hours.
	 */
	public int getWorkEndHour();

	/**
	 * Sets a value indicating the end time of the day for the working hours.
	 * 
	 * @param hour
	 *            the start hour
	 */
	public void setWorkEndHour(int hour);

	/**
	 * @return Gets a value specifying whether the all-day area is displayed.
	 */
	public boolean showAllDayArea();

	/**
	 * Sets a value specifying whether the all-day area is displayed.
	 * 
	 * @param show
	 *            boolean value
	 */
	public void setShowAllDayArea(boolean show);

	/**
	 * @return Gets a value specifying whether the week end days are shown in
	 *         week, month and navigator view.
	 */
	public boolean showWeekEndDays();

	/**
	 * Sets a value specifying whether the week end days are shown in week,
	 * month and navigator view.
	 * 
	 * @param show
	 *            boolean value
	 */
	public void setShowWeekEndDays(boolean show);

	/**
	 * Sets a value specifying the interval in which the hour is divided.
	 * 
	 * @param interval
	 *            the interval in minutes (5|6|10|15|30|60)
	 */
	public void setInterval(int interval);

	/**
	 * @return Gets a value specifying the interval in which the hour is
	 *         divided.
	 */
	public int getInterval();

	/**
	 * Retrieves a Mask with the weeks working days. The mask is a bitwise or
	 * combination of the following integer constants representing each day of
	 * the week:
	 * <ul>
	 * <li>Calendar.SUNDAY^2 = Sunday</li>
	 * <li>Calendar.MONDAY^2 = Monady</li>
	 * <li>...</li>
	 * </ul>
	 * 
	 * @return Mask
	 */
	public int getWorkingDayMask();

	/**
	 * Sets a Mask with the weeks working days. The mask is a bitwise or
	 * combination of the following integer constants representing each day of
	 * the week:
	 * <ul>
	 * <li>Calendar.SUNDAY^2 = Sunday</li>
	 * <li>Calendar.MONDAY^2 = Monady</li>
	 * <li>...</li>
	 * </ul>
	 * An attempt to set DayOfWeekMask to any value less than 1 or greater than
	 * 127 results in an OutOfBounds Exception.
	 * 
	 * @param dayMask
	 *            Mask
	 */
	public void setWorkingDayMask(int dayMask);

	/**
	 * Gets the first day of the week
	 * 
	 * @return Day index of the first Day of the week or <code>0</code> for
	 *         the locales default
	 */
	public int getFirstDayOfWeek();

	/**
	 * Sets the first day of the week
	 * 
	 * @param dayIndex
	 *            Day index of the first Day of the week or <code>0</code> for
	 *            the locales default
	 */
	public void setFirstDayOfWeek(int dayIndex);

	/**
	 * Retrieves the maximum visible appointments for one Day
	 * 
	 * @return Maximum visible Appointments for one Day
	 */
	public int getMaxVisible();

	/**
	 * Sets the maximum visible appointments for one Day
	 * 
	 * @param max
	 *            Maximum visible Appointments for one Day
	 */
	public void setMaxVisible(int max);

	/**
	 * @return <code>true</code> to show appointments in year view in a popup
	 *         window
	 */
	public boolean showPopups();

	/**
	 * @param show
	 *            <code>true</code> to show appointments in year view in a
	 *            popup window
	 */
	public void setShowPopups(boolean show);

	/**
	 * Retrieves the number of columns in year view
	 * 
	 * @return number of columns
	 */
	public int getColumns();

	/**
	 * Sets the number of columns in year view
	 * 
	 * @param columns
	 *            number of columns
	 */
	public void setColumns(int columns);

	/**
	 * Retrieves the number of rows in year view
	 * 
	 * @return number of rows
	 */
	public int getRows();

	/**
	 * Sets the number of rows in year view
	 * 
	 * @param rows
	 *            number of rows
	 */
	public void setRows(int rows);

	/**
	 * Sets the visible view buttons for this scheduler control.
	 * 
	 * @param mask
	 *            Bit combination of Scheduler views
	 * @see com.cc.framework.ui.model.SchedulerView
	 */
	public void setViewMask(int mask);

	/**
	 * Retrieves a bit mask with all the possible view buttons for this control
	 * 
	 * @return Bit combination of Scheduler views
	 * @see com.cc.framework.ui.model.SchedulerView
	 */
	public int getViewMask();

	/**
	 * Returns the Filter setting
	 * 
	 * @return boolean
	 */
	public boolean getFilter();

	/**
	 * Sets the Filter. If set to true, all String will be converted into valid
	 * HTML-strings
	 * 
	 * @param filter
	 *            <code>true</code> if Strings should be html encoded
	 */
	public void setFilter(boolean filter);

	/**
	 * Sets the number of month to increment in year view when the user clicks
	 * on one of the navigation buttons. A value of 0 resets the the control to
	 * the default increment which is the number of visible months (= rows *
	 * columns)
	 * 
	 * @param increment
	 *            Number of columns to increment
	 */
	public void setMonthIncrement(int increment);

	/**
	 * retrieves the number of month to increment in year view when the user
	 * clicks on one of the navigation buttons. A value of 0 resets the the
	 * control to the default increment which is the number of visible months (=
	 * rows * columns)
	 * 
	 * @return the number of month to increment
	 */
	public int getMonthIncrement();

	/**
	 * Retrieves a bit mask that indicates on what view levels a checkbox should
	 * be shown for this control
	 * 
	 * @return Bit combination of Scheduler views
	 * @see com.cc.framework.ui.model.SchedulerScope
	 */
	public int getCheckBoxMask();

	/**
	 * Sets a bit mask that indicates on what view levels checkboxes should be
	 * shown for this control
	 * 
	 * @param checkBoxMask
	 *            Bit combination of Scheduler views
	 * @see com.cc.framework.ui.model.SchedulerScope
	 */
	public void setCheckBoxMask(int checkBoxMask);

	/**
	 * Retrieves the permission for the given button
	 * 
	 * @param button Button constant
	 * @return Permission or <code>null</code>
	 */
	public Permission getButtonPermission(ControlButton button);

	/**
	 * Checks if the button can be displayed.
	 * 
	 * @param  button The Button to query
	 * @param	principal	The principal object
	 * @return	boolean		<code>true</code> if the button should be displayed
	 */
	public boolean showButton(ControlButton button, Principal principal);

	/**
	 * Sets whether a command button should be displayed by specifying an Access Control List.
	 *  
	 * @param  button The Button to query
	 * @param	permission	Permission
	 */
	public void setButtonPermission(ControlButton button, Permission permission);
}