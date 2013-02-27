/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/control/SchedulerControl.java,v 1.29 2005/11/13 14:03:58 P001002 Exp $
 * $Revision: 1.29 $
 * $Date: 2005/11/13 14:03:58 $
 *
 * ====================================================================
 *
 * Copyright (c) 2002 SCC Informationssysteme GmbH.  All rights
 * reserved.
 *
 * ====================================================================
 */

package com.cc.framework.ui.control;

import java.util.Calendar;
import java.util.TimeZone;

import com.cc.framework.common.CheckState;
import com.cc.framework.security.Permission;
import com.cc.framework.ui.ImageMap;
import com.cc.framework.ui.model.Appointment;
import com.cc.framework.ui.model.CheckableAppointment;
import com.cc.framework.ui.model.ControlDesignModel;
import com.cc.framework.ui.model.DataModel;
import com.cc.framework.ui.model.ImageModel;
import com.cc.framework.ui.model.InnerFrame;
import com.cc.framework.ui.model.SchedulerDataModel;
import com.cc.framework.ui.model.SchedulerDesignModel;
import com.cc.framework.ui.model.SchedulerScope;
import com.cc.framework.ui.model.SchedulerStateModel;
import com.cc.framework.ui.model.SchedulerView;
import com.cc.framework.ui.model.StateModel;
import com.cc.framework.ui.model.imp.SchedulerStateModelImp;
import com.cc.framework.util.CalendarHelp;

/**
 * SchedulerControl
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.29 $
 */
public class SchedulerControl extends Control implements SchedulerStateModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 6822083764427059054L;

	// ===============
	// Members
	// ===============

	/**
	 * DataModel
	 */
	private SchedulerDataModel dataModel = null;

	/**
	 * DesignModell
	 */
	private SchedulerDesignModel designModel = null;

	/**
	 * StateModel
	 */
	private SchedulerStateModel stateModel = null;

	/**
	 * A Scheduler control can be used to control the state
	 * of a dependent child Scheduler control. It will
	 * dispatch all navigation commands to the child control
	 */
	private SchedulerControl childCtrl = null;

	// ========
	// Methods
	// ========

	/**
	 * Konstruktor
	 */
	public SchedulerControl() {
		super();

		stateModel = doCreateStateModel();
	}

	/**
	 * Konstruktor
	 *
	 * @param		designModel Das Design Model
	 */
	public SchedulerControl(SchedulerDesignModel designModel) {
		this();

		this.designModel = designModel;
	}

	/**
	 * @see com.cc.framework.ui.model.StateModel#reset()
	 */
	public void reset() {
		stateModel.reset();

		if (childCtrl != null) {
			childCtrl.reset();
		}
	}

	/**
	 * Sets the StateModel
	 * @param	stateModel	TreeStateModel
	 */
	public void setStateModel(SchedulerStateModel stateModel) {
		this.stateModel	= stateModel;
	}

	/**
	 * Creates the state model for this control instance
	 *
	 * @return		State model instance
	 */
	protected SchedulerStateModel doCreateStateModel() {
		return new SchedulerStateModelImp();
	}

	/**
	 * @see com.cc.framework.ui.control.Control#getDataModel()
	 */
	public DataModel getDataModel() {
		return dataModel;
	}

	/**
	 * @see com.cc.framework.ui.control.Control#getDesignModel()
	 */
	public ControlDesignModel getDesignModel() {
		return designModel;
	}

	/**
	 * Sets the designModel
	 * @param designModel The designModel to set
	 */
	public void setDesignModel(SchedulerDesignModel designModel) {
		this.designModel = designModel;
	}

	/**
	 * Sets the data model
	 * @param dataModel	TopsAndFlopsDataModel
	 */
	public void setDataModel(SchedulerDataModel dataModel) {
		this.dataModel = dataModel;

		if (childCtrl != null) {
			childCtrl.setDataModel(dataModel);
		}
	}

	/**
	 * @see com.cc.framework.ui.control.Control#getStateModel()
	 */
	public StateModel getStateModel() {
		return stateModel;
	}

	/**
	 * Liefert alle Termine für ein bestimmtes Datum
	 *
	 * @param		date Das Datum
	 * @return		Terminliste
	 */
	public Appointment[] getAppointments(Calendar date) {
		if (dataModel == null) {
			return new Appointment[0];
		} else {
			return dataModel.getAppointments(date);
		}
	}

	/**
	 * Retrieves the appointment with the given key
	 *
	 * @param		uniqueId the unique identifier of the appointment
	 * @return		Appointment or <code>null</code>
	 */
	public Appointment getAppointmentFromId(String uniqueId) {
		if (dataModel == null) {
			return null;
		} else {
			return dataModel.getAppointmentFromId(uniqueId);
		}
	}
	
	/**
	 * Liefert die Zeitzone
	 *
	 * @return		Zeitzone
	 */
	public TimeZone getTimeZone() {
		if (dataModel == null) {
			return TimeZone.getDefault();
		} else {
			return dataModel.getTimeZone();
		}
	}

	/**
	 * @see com.cc.framework.ui.model.SchedulerStateModel#getDate()
	 */
	public Calendar getDate() {
		return stateModel.getDate();
	}

	/**
	 * @see com.cc.framework.ui.model.SchedulerStateModel#setDate(java.util.Calendar)
	 */
	public void setDate(Calendar date) {
		stateModel.setDate(date);

		if (childCtrl != null) {
			childCtrl.setDate(date);
		}
	}

	/**
	 * Checks if the given day is configured as a working day
	 *
	 * @param		dayOfWeek Day to check
	 * @return		<code>true</code> if this is a working day
	 */
	public boolean isWorkingDay(int dayOfWeek) {
		return ((1 << dayOfWeek) & getWorkingDayMask()) > 0;
	}

	/**
	 * This method checks if the given hour is a working hour
	 *
	 * @param		dayOfWeek Day to check
	 * @param		hour The hour to check
	 * @return		<code>true</code> if the hour is a
	 * 				working hour
	 */
	public boolean isWorkingHour(int dayOfWeek, int hour) {
		return isWorkingDay(dayOfWeek)
			&& (hour >= getWorkStartHour())
			&& (hour <= getWorkEndHour());
	}

	/**
	 * Returns the Title
	 *
	 * @return		Titel
	 */
	public String getTitle() {
		return designModel.getTitle();
	}

	/**
	 * @return		<code>true</code> to show appointments in
	 * 				year view in a popup window
	 */
	public boolean showPopups() {
		return designModel.showPopups();
	}

	/**
	 * Retrieves a bit mask that indicates on what view levels a checkbox should
	 * be shown for this control
	 * 
	 * @return Bit combination of Scheduler views
	 * @see com.cc.framework.ui.model.SchedulerScope
	 */
	public int getCheckBoxMask() {
		return designModel.getCheckBoxMask();
	}

	/**
	 * Retrieves the number of columns in year view
	 *
	 * @return		number of columns
	 */
	public int getColumns() {
		return designModel.getColumns();
	}

	/**
	 * Retrieves the number of rows in year view
	 *
	 * @return		number of rows
	 */
	public int getRows() {
		return designModel.getRows();
	}

	/**
	 * Returns the Filter setting
	 *
	 * @return		boolean
	 */
	public boolean getFilter() {
		return designModel.getFilter();
	}

	/**
	 * Returns the image for the frames title
	 *
	 * @return		ImageModel
	 */
	public ImageModel getImage() {
		return designModel.getImage();
	}

	/**
	 * Retrieves a list of inner frames
	 * that are matching the given layout hint
	 *
	 * @param		layoutHint The layout hint that specifies
	 * 				what frames should be selected:
	 * 				<code>AlignmentType.TOP</code> - header frames
	 * 				<code>AlignmentType.BOTTOM</code> - footer frames
	 * @return		Frame list
	 */
	public InnerFrame[] getInnerFrames(Object layoutHint) {
		return designModel.getInnerFrames(getPrincipal(), layoutHint);
	}

	/**
	 * Returns <code>true</code> if the frame should be painted
	 *
	 * @return	<code>true</code> if the frame should be painted
	 */
	public boolean showFrame() {
		return designModel.showFrame();
	}

	/**
	 * @return	Gets a value indicating whether the weekends should be
	 * 			compressed in one day
	 */
	public boolean compressWeekEnds() {
		return designModel.compressWeekEnds();
	}

	/**
	 * @return	Gets a value indicating the start of the hour range
	 * 			displayed by the Schedule on DayView.
	 */
	public int getDayStartHour() {
		return designModel.getDayStartHour();
	}

	/**
	 * @return	Gets a value indicating the end of the hour range
	 * 			displayed by the Schedule on DayView.
	 */
	public int getDayEndHour() {
		return designModel.getDayEndHour();
	}

	/**
	 * @return	Gets a value indicating the start time of the day for
	 * 			the working hours.
	 */
	public int getWorkStartHour() {
		return designModel.getWorkStartHour();
	}

	/**
	 * @return	Gets a value indicating the end time of the day for
	 * 			the working hours.
	 */
	public int getWorkEndHour() {
		return designModel.getWorkEndHour();
	}

	/**
	 * Retrieves a Mask with the weeks working days. The
	 * mask is a bitwise or combination of the following
	 * integer constants representing each day of the week:
	 * <ul>
	 *   <li>Calendar.SUNDAY^2 = Sunday</li>
	 *   <li>Calendar.MONDAY^2 = Monady</li>
	 *   <li>...</li>
	 * </ul>
	 *
	 * @return		Mask
	 */
	public int getWorkingDayMask() {
		return designModel.getWorkingDayMask();
	}

	/**
	 * @return	Gets a value specifying whether the all-day area is
	 * 			displayed.
	 */
	public boolean showAllDayArea() {
		return designModel.showAllDayArea();
	}

	/**
	 * @return		Gets a value specifying whether the week end days
	 * 				are shown in week, month and navigator view.
	 */
	public boolean showWeekEndDays() {
		return designModel.showWeekEndDays();
	}

	/**
	 * Gets the first day of the week
	 *
	 * @return		Day index of the first Day of the week
	 * 				or <code>0</code> for the locales default
	 */
	public int getFirstDayOfWeek() {
		if (designModel.getFirstDayOfWeek() == 0) {
			return getDate().getFirstDayOfWeek();
		} else {
			return designModel.getFirstDayOfWeek();
		}
	}

	/**
	 * @return		Gets a value specifying the interval in which the hour
	 * 				is divided.
	 */
	public int getInterval() {
		return designModel.getInterval();
	}

	/**
	 * @return	Returns the style used to display dates in a
	 * 			Scheduler control
	 */
	public SchedulerView getView() {
		return stateModel.getView();
	}

	/**
	 * Returns the associated ImageMap
	 *
	 * @return		ImageMap or <code>null</code>
	 */
	public ImageMap getImageMap() {
		return designModel.getImageMap();
	}

	/**
	 * Retrieves the maximum visible appointments for one
	 * Day
	 *
	 * @return		Maximum visible Appointments for one Day
	 */
	public int getMaxVisible() {
		return designModel.getMaxVisible();
	}

	/**
	 * @see com.cc.framework.ui.model.SchedulerStateModel#setView(com.cc.framework.ui.model.SchedulerView)
	 */
	public void setView(SchedulerView view) {
		stateModel.setView(view);
	}

	/**
	 * Retrieves a bit mask with all the possible view
	 * buttons for this control
	 *
	 * @return		Bit combination of Scheduler views
	 * @see			com.cc.framework.ui.model.SchedulerView
	 */
	public int getViewMask() {
		return designModel.getViewMask();
	}

	/**
	 * retrieves the number of month to increment in year view when the user
	 * clicks on one of the navigation buttons. A value of 0 resets the the
	 * control to the default increment which is the number of visible months (=
	 * rows * columns)
	 *
	 * @return the number of month to increment
	 */
	public int getMonthIncrement() {
		return designModel.getMonthIncrement();
	}

	/**
	 * @see com.cc.framework.ui.control.Control#showButton(com.cc.framework.ui.control.ControlButton)
	 */
	public boolean showButton(ControlButton button) {

		Permission permission = designModel.getButtonPermission(button);
		if (permission != null) {
			return permission.isGranted(getPrincipal());
		} else {
			return super.showButton(button);
		}
	}

	/**
	 * A Scheduler control can be used to control the state
	 * of a dependent child Scheduler control. It will dispatch
	 * all navigation commands to the child control. In addition the
	 * control will retrieve its selection state from the child control.
	 * (master -> detail relationship)
	 *
	 * @param		child The dependent scheduler control
	 */
	public void setDetail(SchedulerControl child) {
		this.childCtrl = child;
	}

	/**
	 * Retrives the scheduler control that is controlled by this
	 * scheduler.
	 *
	 * @return		the controlled scheduler or <code>null</code>
	 */
	public SchedulerControl getDetail() {
		return childCtrl;
	}

	// --------------------------------
	//          event handler
	//	--------------------------------

	/**
	 * Defaulthandler for the <b>Create</b> Event
	 *
	 * @param		ctx	ControlRequestContext
	 * @throws		Exception is thrown when an error occurs
	 */
	public void onCreate(ControlRequestContext ctx) throws Exception {
		log.debug("onCreate()");
	}

	/**
	 * Default Handler for the <b>Refresh</b> Event
	 *
	 * @param		ctx	ControlRequestContext
	 * @throws		Exception is thrown when an error occurs
	 */
	public void onRefresh(ControlRequestContext ctx)
		throws Exception {

		log.debug("onRefresh()");
	}

	/**
	 * Default Handler for the <b>View</b> Event
	 *
	 * @param		ctx	ControlRequestContext
	 * @param		view The view to switch to
	 * @throws		Exception is thrown when an error occurs
	 */
	public void onView(ControlRequestContext ctx, String view)
		throws Exception {

		log.debug("onView(" + view + ")");

		setView(SchedulerView.parse(view));
	}

	/**
	 * Rolls the specified Date field by the given delta value.
	 * This positions the Scheduler on another date.
	 *
	 * @param		ctx	ControlRequestContext
	 * @param		timeInMillis The new Time in milli secounds in UTC
	 * @param		view the View (SchedulerView)
	 * @throws		Exception is thrown when an error occurs
	 */
	public void onChangeDate(
		ControlRequestContext ctx,
		long timeInMillis,
		String view)
		throws Exception {

		log.debug("onChangeDate(" + timeInMillis + ", " + view + ")");

		// Roll the current date
		Calendar cal = (Calendar) getDate().clone();

		CalendarHelp.setFromLong(cal, timeInMillis);

		setDate(cal);
		setView(SchedulerView.parse(view));
	}

	/**
	 * This method gets called when the user clicks on a date
	 * element.
	 *
	 * @param		ctx	ControlRequestContext
	 * @param		timeInMillis The new Time in milli secounds in UTC
	 * @param		view the View (SchedulerView)
	 * @throws		Exception is thrown when an error occurs
	 */
	public void onSelectDate(
		ControlRequestContext ctx,
		long timeInMillis,
		String view)
		throws Exception {

		log.debug("onSelectDate(" + timeInMillis + ", " + view + ")");

		if (childCtrl == null) {
			// Roll the current date
			Calendar cal = (Calendar) getDate().clone();

			CalendarHelp.setFromLong(cal, timeInMillis);

			setDate(cal);
			setView(SchedulerView.parse(view));
		} else {
			// Change the state of the child control
			childCtrl.onChangeDate(ctx, timeInMillis, view);
		}
	}

	/**
	 * Defaulthandler for the <b>AppointmentCLick</b> Event
	 *
	 * @param		ctx ControlRequestContext
	 * @param		key Unique Id as generated by the Datamodel to identify the Row
	 * @param		timeInMillis Timestamp in UTC
	 * @throws		Exception is thrown when an error occurs
	 */
	public void onAppointmentClick(ControlRequestContext ctx, String key, long timeInMillis)
		throws Exception {

		log.debug("onAppointmentClick(" + key + ", " + timeInMillis + ")");
	}

	/**
	 * Defaulthandler for the <b>AddAppointment</b> Event
	 *
	 * @param		ctx ControlRequestContext
	 * @param		timeInMillis Timestamp in UTC
	 * @throws		Exception is thrown when an error occurs
	 */
	public void onAddAppointment(ControlRequestContext ctx, long timeInMillis)
		throws Exception {

		log.debug("onAddAppointment(" + timeInMillis + ")");
	}

	/**
	 * Defaulthandler for the <b>CheckAppointment</b> Event
	 *
	 * @param		ctx ControlRequestContext
	 * @param		uniqueId Unique Id as generated by the Datamodel to identify the Row
	 * @param		timeInMillis Timestamp in UTC
	 * @param		check indicates if the appointment waschecked or unchecked
	 * @throws		Exception is thrown when an error occurs
	 */
	public void onCheckAppointment(ControlRequestContext ctx, String uniqueId, long timeInMillis, boolean check)
		throws Exception {

		log.debug("onCheckAppointment(" + uniqueId + ", " + timeInMillis + ", " + check + ")");

		Appointment appointment = getAppointmentFromId(uniqueId);
		
		if (appointment instanceof CheckableAppointment) {
			((CheckableAppointment) appointment)
				.setCheckState(
					timeInMillis,
					check ? CheckState.CHECKED.toInt() : CheckState.UNCHECKED.toInt());
		}
	}

	/**
	 * Defaulthandler for the <b>CheckAppointment</b> Event
	 *
	 * @param		ctx ControlRequestContext
	 * @param		timeInMillis Timestamp in UTC
	 * @param		scope The scope (range of appointments) thet is affected by
	 * 				this event
	 * @param		check indicates if the appointment waschecked or unchecked
	 * @throws		Exception is thrown when an error occurs
	 */
	public void onCheckDate(ControlRequestContext ctx, long timeInMillis, SchedulerScope scope, boolean check)
		throws Exception {

		log.debug("onCheckAppointment(" + timeInMillis + ", " + scope + ", " + check + ")");

		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(timeInMillis);

		Appointment[] appointments = getAppointments(cal);
		if (appointments != null) {
			for (int i = 0; i < appointments.length; i++) {
				if (appointments[i] instanceof CheckableAppointment) {
					((CheckableAppointment) appointments[i])
						.setCheckState(
							timeInMillis,
							check ? CheckState.CHECKED.toInt() : CheckState.UNCHECKED.toInt());
				}
			}
		}
	}

	/**
	 * Defaulthandler for the <b>ExportList</b> Event
	 * 
	 * @param ctx
	 *            ControlRequestContext
	 * @throws Exception
	 *             is thrown when an error occurs
	 */
	public void onExportList(ControlRequestContext ctx) throws Exception {
		log.debug("onExportList()");
	}

	/**
	 * Defaulthandler for the <b>PrintList</b> Event
	 * 
	 * @param ctx
	 *            ControlRequestContext
	 * @throws Exception
	 *             is thrown when an error occurs
	 */
	public void onPrintList(ControlRequestContext ctx) throws Exception {
		log.debug("onPrintList()");
	}
}