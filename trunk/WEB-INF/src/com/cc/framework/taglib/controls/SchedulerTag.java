/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/controls/SchedulerTag.java,v 1.19 2005/11/13 14:03:57 P001002 Exp $
 * $Revision: 1.19 $
 * $Date: 2005/11/13 14:03:57 $
 *
 * ====================================================================
 *
 * Copyright (c) 2002 SCC Informationssysteme GmbH.  All rights
 * reserved.
 *
 * ====================================================================
 */

package com.cc.framework.taglib.controls;

import javax.servlet.jsp.JspException;

import com.cc.framework.common.InvalidEnumType;
import com.cc.framework.taglib.FrameContainerTag;
import com.cc.framework.taglib.TagHelp;
import com.cc.framework.ui.AlignmentType;
import com.cc.framework.ui.control.Control;
import com.cc.framework.ui.control.ControlButton;
import com.cc.framework.ui.control.SchedulerControl;
import com.cc.framework.ui.model.ControlDesignModel;
import com.cc.framework.ui.model.ImageModel;
import com.cc.framework.ui.model.InnerFrame;
import com.cc.framework.ui.model.SchedulerScope;
import com.cc.framework.ui.model.SchedulerDesignModel;
import com.cc.framework.ui.model.SchedulerView;
import com.cc.framework.ui.model.imp.SchedulerDesignModelImp;
import com.cc.framework.util.CalendarHelp;

/**
 * Tag Handler für das &lt;calendar&gt;-Tag
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision: 1.19 $
 */
public class SchedulerTag extends BaseControlTag implements FrameContainerTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 7727554006503689640L;

	/**
	 * The Initial State for this control
	 */
	private SchedulerView view = null;

	/**
	 * Constructor
	 */
	public SchedulerTag() {
		super();
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateDesignModel()
	 */
	protected ControlDesignModel doCreateDesignModel() {
		return new SchedulerDesignModelImp();
	}

	/**
	 * Returns a casted version of the ControlDesignModel
	 * 
	 * @return SchedulerDesignModel
	 */
	protected SchedulerDesignModel getSchedulerDesignModel() {
		return (SchedulerDesignModel) getDesignModel();
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#release()
	 */
	public void release() {
		super.release();

		view = null;
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateControl()
	 */
	protected Control doCreateControl() throws JspException {
		Object value = lookupBean();

		if (!(value instanceof SchedulerControl)) {
			throw new JspException("SchedulerTag: Invalid property class. expected class=SchedulerControl!");
		}

		SchedulerDesignModel designModel = getSchedulerDesignModel();

		// set the control
		SchedulerControl ctrl = (SchedulerControl) value;

		// Assign an action if this doesn't happend by the programmer
		if (designModel.getAction() == null) {
			designModel.setAction(getDefaultAction());
		}

		// Assign the DesignModel if this did not happened
		// in the Application
		if ((ctrl.getDesignModel() == null) || ctrl.getDesignModel().isDynamicDesignModel()) {
			boolean modifyState = (ctrl.getDesignModel() == null);

			ctrl.setDesignModel(designModel);

			// Set the Controls State Attributes
			if (modifyState) {
				if (view != null) {
					ctrl.setView(view);
				}
			}
		} else {
			SchedulerDesignModel dm = (SchedulerDesignModel) ctrl.getDesignModel();

			dm.setAction(designModel.getAction());
			dm.setProperty(designModel.getProperty());
		}

		return ctrl;
	}

	/**
	 * The main title of the control
	 * 
	 * @param title
	 *            The main title of the control
	 */
	public void setTitle(String title) {
		getSchedulerDesignModel().setTitle(title);
	}

	/**
	 * Sets the Flag for the RefreshButton
	 * 
	 * @param value
	 *            Flag, true if the RefreshButton should be painted
	 * @throws JspException
	 *             if the Argument can not be converted to a boolean Value
	 */
	public void setRefreshButton(String value) throws JspException {
		getSchedulerDesignModel()
			.setButtonPermission(ControlButton.REFRESH, TagHelp.toPermission(value));
	}

	/**
	 * Sets the Flag for the CreateButton
	 * 
	 * @param value
	 *            Flag, true if the CreateButton should be painted
	 * @throws JspException
	 *             if the Argument can not be converted to a boolean Value
	 */
	public void setCreateButton(String value) throws JspException {
		getSchedulerDesignModel()
			.setButtonPermission(ControlButton.CREATE, TagHelp.toPermission(value));
	}

	/**
	 * Sets the Flag for the AddButton
	 * 
	 * @param value
	 *            Flag, true if the AddButton should be painted
	 * @throws JspException
	 *             if the Argument can not be converted to a boolean Value
	 */
	public void setAddButton(String value) throws JspException {
		getSchedulerDesignModel()
			.setButtonPermission(ControlButton.ADD, TagHelp.toPermission(value));
	}

	/**
	 * Sets the Flag for the PrintList Button
	 * 
	 * @param value
	 *            Flag, <code>true</code> if the Button should be shown
	 * @throws JspException
	 *             if the Argument can not be converted to a boolean Value
	 */
	public void setPrintListButton(String value) throws JspException {
		getSchedulerDesignModel()
			.setButtonPermission(ControlButton.PRINTLIST, TagHelp.toPermission(value));
	}

	/**
	 * Sets the Flag for the ExportList Button
	 * 
	 * @param value
	 *            Flag, <code>true</code> if the Button should be shown
	 * @throws JspException
	 *             if the Argument can not be converted to a boolean Value
	 */
	public void setExportListButton(String value) throws JspException {
		getSchedulerDesignModel()
			.setButtonPermission(ControlButton.EXPORTLIST, TagHelp.toPermission(value));
	}

	/**
	 * Disables the controls frame
	 * 
	 * @param noframe
	 *            <code>true</code> if the frame should be hidden
	 * @throws JspException
	 *             If the Argument can't be converted to boolean
	 */
	public void setNoframe(String noframe) throws JspException {
		getSchedulerDesignModel().setShowFrame(!TagHelp.toBoolean(noframe));
	}

	/**
	 * @param show
	 *            <code>true</code> to show appointments in year view in a
	 *            popup window
	 * @throws JspException
	 *             If the Argument can't be converted to boolean
	 */
	public void setPopups(String show) throws JspException {
		getSchedulerDesignModel().setShowPopups(TagHelp.toBoolean(show));
	}

	/**
	 * Sets the number of columns in year view
	 * 
	 * @param columns
	 *            number of columns
	 * @throws JspException
	 *             If the Argument can't be converted to int
	 */
	public void setColumns(String columns) throws JspException {
		getSchedulerDesignModel().setColumns(TagHelp.toInt(columns));
	}

	/**
	 * Sets the number of rows in year view
	 * 
	 * @param rows
	 *            number of rows
	 * @throws JspException
	 *             If the Argument can't be converted to int
	 */
	public void setRows(String rows) throws JspException {
		getSchedulerDesignModel().setRows(TagHelp.toInt(rows));
	}

	/**
	 * Sets the number of month to increment in year view when the user clicks
	 * on one of the navigation buttons. A value of 0 resets the the control to
	 * the default increment which is the number of visible months (= rows *
	 * columns)
	 * 
	 * @param increment
	 *            Number of columns to increment
	 * @throws JspException
	 *             Is thrown when the argument can not be converted to int
	 */
	public void setMonthIncrement(String increment) throws JspException {
		getSchedulerDesignModel().setMonthIncrement(TagHelp.toInt(increment));
	}

	/**
	 * Specifies the style used to display dates in a Scheduler control
	 * 
	 * @param view
	 *            The view to set
	 * @throws JspException
	 *             If the argument can not be parsed
	 */
	public void setView(String view) throws JspException {
		try {
			this.view = SchedulerView.parse(view);
		} catch (InvalidEnumType iet) {
			throw new JspException(iet.getMessage());
		}
	}

	/**
	 * Specifies the name of an Imagemap which must be saved in the request. The
	 * values that the Appointments getImageRef() method returns are mapped to
	 * the entries of this Imagemap. The mapping is done with the help of the
	 * regular expression, which is assigned to every entry of the Imagemap.
	 * 
	 * @param mapName
	 *            The ImageMap to assign
	 * @throws JspException
	 *             If the ImageMap can not be found
	 */
	public void setImagemap(String mapName) throws JspException {
		getSchedulerDesignModel().setImageMap(TagHelp.lookupImageMap(pageContext, mapName));
	}

	/**
	 * Sets a value indicating whether the weekends should be compressed in one
	 * day
	 * 
	 * @param comress
	 *            boolean value
	 * @throws JspException
	 *             If the Argument can't be converted to boolean
	 */
	public void setCompressWeekEnds(String comress) throws JspException {
		getSchedulerDesignModel().setCompressWeekEnds(TagHelp.toBoolean(comress));
	}

	/**
	 * Sets a value indicating the start of the hour range displayed by the
	 * Schedule on DayView.
	 * 
	 * @param hour
	 *            the start hour
	 * @throws JspException
	 *             If the Argument can't be converted to int
	 */
	public void setDayStartHour(String hour) throws JspException {
		getSchedulerDesignModel().setDayStartHour(TagHelp.toInt(hour));
	}

	/**
	 * Sets a value indicating the end of the hour range displayed by the
	 * Schedule on DayView.
	 * 
	 * @param hour
	 *            the start hour
	 * @throws JspException
	 *             If the Argument can't be converted to int
	 */
	public void setDayEndHour(String hour) throws JspException {
		getSchedulerDesignModel().setDayEndHour(TagHelp.toInt(hour));
	}

	/**
	 * Sets a value indicating the start time of the day for the working hours.
	 * 
	 * @param hour
	 *            the start hour
	 * @throws JspException
	 *             If the Argument can't be converted to int
	 */
	public void setWorkStartHour(String hour) throws JspException {
		getSchedulerDesignModel().setWorkStartHour(TagHelp.toInt(hour));
	}

	/**
	 * Sets a value indicating the end time of the day for the working hours.
	 * 
	 * @param hour
	 *            the start hour
	 * @throws JspException
	 *             If the Argument can't be converted to int
	 */
	public void setWorkEndHour(String hour) throws JspException {
		getSchedulerDesignModel().setWorkEndHour(TagHelp.toInt(hour));
	}

	/**
	 * Sets a value specifying whether the all-day area is displayed.
	 * 
	 * @param show
	 *            boolean value
	 * @throws JspException
	 *             If the Argument can't be converted to boolean
	 */
	public void setAllDayArea(String show) throws JspException {
		getSchedulerDesignModel().setShowAllDayArea(TagHelp.toBoolean(show));
	}

	/**
	 * Sets a value specifying the interval in which the hour is divided.
	 * 
	 * @param interval
	 *            the interval in minutes (5|6|10|15|30|60)
	 * @throws JspException
	 *             If the Argument can't be converted to int
	 */
	public void setInterval(String interval) throws JspException {
		getSchedulerDesignModel().setInterval(TagHelp.toInt(interval));
	}

	/**
	 * Sets a value specifying whether the week end days are shown in week,
	 * month and navigator view.
	 * 
	 * @param show
	 *            boolean value
	 * @throws JspException
	 *             If the Argument can't be converted to boolean
	 */
	public void setWeekEndDays(String show) throws JspException {
		getSchedulerDesignModel().setShowWeekEndDays(TagHelp.toBoolean(show));
	}

	/**
	 * Sets a Mask with the weeks working days. The mask is a bitwise or
	 * combination of the following integer constants representing each day of
	 * the week:
	 * <ul>
	 * <li>Calendar.SUNDAY^2 = Sunday</li>
	 * <li>Calendar.MONDAY^2 = Monady</li>
	 * <li>...</li>
	 * </ul>
	 * 
	 * @param dayMask
	 *            Mask
	 */
	public void setWorkingDayMask(int dayMask) {
		getSchedulerDesignModel().setWorkingDayMask(dayMask);
	}

	/**
	 * Sets a list with the weeks working days. The mask is a semicolon
	 * seperated list of Day Names (Locale.ENGLISH)
	 * 
	 * @param dayList
	 *            Mask
	 */
	public void setWorkingDays(String dayList) {
		getSchedulerDesignModel().setWorkingDayMask(CalendarHelp.parseDayMask(dayList));
	}

	/**
	 * Sets the first day of the week
	 * 
	 * @param dayName
	 *            The name (Locale.ENGLISH) of the first day of the week
	 */
	public void setFirstDayOfWeek(String dayName) {
		getSchedulerDesignModel().setFirstDayOfWeek(CalendarHelp.parseDay(dayName));
	}

	/**
	 * Sets the maximum visible appointments for one Day
	 * 
	 * @param max
	 *            Maximum visible Appointments for one Day
	 * @throws JspException
	 *             If the Argument can't be converted to int
	 */
	public void setMaxVisible(String max) throws JspException {
		getSchedulerDesignModel().setMaxVisible(TagHelp.toInt(max));
	}

	/**
	 * @see com.cc.framework.taglib.FrameContainerTag#addInnerFrame(com.cc.framework.ui.model.InnerFrame)
	 */
	public void addInnerFrame(InnerFrame frame) {
		if (frame.getLayoutHint() == null) {
			frame.setLayoutHint(AlignmentType.BOTTOM);
		}

		getSchedulerDesignModel().addInnerFrame(frame);
	}

	/**
	 * @see com.cc.framework.taglib.ImageContainerTag#setImage(com.cc.framework.ui.model.ImageModel)
	 */
	public void setImage(ImageModel image) {
		getSchedulerDesignModel().setImage(image);
	}

	/**
	 * Sets the list with the available views for this control
	 * 
	 * @param buttonList
	 *            Semicolon delimited list
	 */
	public void setViewButtons(String buttonList) {
		getSchedulerDesignModel().setViewMask(SchedulerView.parseMask(buttonList));
	}

	/**
	 * The automatic HTML coding of the control can be activated or disabled
	 * with the filter-attribute
	 * 
	 * @param filter
	 *            <code>true</code> if string values should be HTML encoded.
	 * @throws JspException
	 *             If the Argument can't be converted to a boolean
	 */
	public void setFilter(String filter) throws JspException {
		getSchedulerDesignModel().setFilter(TagHelp.toBoolean(filter));
	}

	/**
	 * Indicates whether a checkbox should be displayed before the appointment
	 * entries. The appointment entries must implement the Checkable Interface,
	 * so that the control element can draw the checkboxes.
	 * 
	 * @param viewList
	 *            <code>true</code> if checkboxes should be displayed
	 * @throws JspException
	 *             if the argument can not be converted to a boolean value
	 */
	public void setCheckboxes(String viewList) throws JspException {
		getSchedulerDesignModel().setCheckBoxMask(SchedulerScope.parseMask(viewList));
	}
}