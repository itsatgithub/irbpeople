/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/scheduler/DefSchedulerViewPainter.java,v 1.18 2005/11/26 13:52:58 P001002 Exp $
 * $Revision: 1.18 $
 * $Date: 2005/11/26 13:52:58 $
 *
 * ====================================================================
 *
 * Copyright (c) 2002 SCC Informationssysteme GmbH. All rights
 * reserved.
 *
 * ====================================================================
 */

package com.cc.framework.ui.painter.def.scheduler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.ecs.AlignType;
import org.apache.ecs.ConcreteElement;
import org.apache.ecs.ElementContainer;
import org.apache.ecs.Entities;
import org.apache.ecs.html.Div;
import org.apache.ecs.html.IMG;
import org.apache.ecs.html.Span;
import org.apache.ecs.html.TD;
import org.apache.ecs.html.TR;
import org.apache.ecs.html.Table;

import utils.jsp.ZoneConstructorUtils;
import utils.jsp.JspUtils;

import com.cc.framework.FrameworkResources;
import com.cc.framework.ui.ImageMap;
import com.cc.framework.ui.control.ControlActionDef;
import com.cc.framework.ui.control.SchedulerControl;
import com.cc.framework.ui.model.Appointment;
import com.cc.framework.ui.model.AppointmentPriority;
import com.cc.framework.ui.model.ClientHandler;
import com.cc.framework.ui.model.ImageModel;
import com.cc.framework.ui.model.SchedulerScope;
import com.cc.framework.ui.model.SchedulerView;
import com.cc.framework.ui.model.imp.AppointmentComparator;
import com.cc.framework.ui.model.imp.ClientHandlerImp;
import com.cc.framework.ui.painter.ActionPainter;
import com.cc.framework.ui.painter.ControlPainter;
import com.cc.framework.ui.painter.ControlPortionPainter;
import com.cc.framework.ui.painter.def.DefHtmlClass;
import com.cc.framework.ui.painter.def.DefResources;
import com.cc.framework.ui.painter.global.PopupElement;
import com.cc.framework.util.CalendarHelp;
import com.cc.framework.util.StringHelp;
import uicomponents.scheduler.SchedulerFacadeForCommonControlsScheduler;

/**
 * Scheduler View painter. This Painter creates the View Part of the Scheduling
 * Calendar Control
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision: 1.18 $
 */
public abstract class DefSchedulerViewPainter extends ControlPortionPainter {

	/**
	 * Composite HTML element
	 */
	private class CompositeElement extends ElementContainer {

		/**
		 * Serial Version UID
		 */
		private static final long serialVersionUID = 3930043926986538301L;
	}

	/** Dateformat: variable */
	protected static final int DATEFORMAT_DETAIL			= 1;

	/** Dateformat: d. MMM */
	protected static final int DATEFORMAT_DAY				= 2;

	/** Dateformat: EEE, d MMM yyyy */
	protected static final int DATEFORMAT_WEEKDAY			= 3;

	/** Dateformat: EEE, d MMM */
	protected static final int DATEFORMAT_WEEKDAY_SHORT		= 4;

	/** Dateformat: 'Week' w 'in' yyyy */
	protected static final int DATEFORMAT_WEEK				= 5;

	/** Dateformat: MMMMM yyyy */
	protected static final int DATEFORMAT_MONTH_OF_YEAR		= 6;

	/** Dateformat: h:mm a */
	protected static final int DATEFORMAT_TIME				= 7;

	/** Dateformat: yyyy.MM.dd 'at' h:mm a */
	protected static final int DATEFORMAT_DATETIME			= 8;

	/** Dateformat: variable */
	protected static final int DATEFORMAT_HOUR				= 9;


	/** Flag for the first visible Day of the month */
	protected static final int STATE_FIRST_DAY				= 0x0001;

	/** Flag for a day that belongs to the selected month */
	protected static final int STATE_DAY_IN_MONTH			= 0x0002;

	/** Flag for the day that is the current day */
	protected static final int STATE_TODAY					= 0x0004;

	/** This flag is set when the day should be hidden */
	protected static final int STATE_HIDDEN					= 0x0008;

	/** This flag is set when the day has appointments assigned */
	protected static final int STATE_APPOINTMENTS			= 0x1000;

	/** Flag for the first visible month */
	protected static final int STATE_FIRST_MONTH			= 0x0010;

	/** Flag for the last visible month */
	protected static final int STATE_LAST_MONTH				= 0x0020;

	/** Flag for the leftmost month*/
	protected static final int STATE_LEFTMOST_MONTH			= 0x0040;

	/** Flag for the rightmost month */
	protected static final int STATE_RIGHTMOST_MONTH		= 0x0080;

	
	/** Short Label Format */
	protected static final int LABELFORMAT_SHORT			= 1;

	/** Long Label Format */
	protected static final int LABELFORMAT_LONG				= 2;

	/**
	 * The "today" date
	 */
	private Calendar today = null;

	/**
	 * This is a cache for DateFormat objects
	 */
	private Map formatterCache = new Hashtable();

	/**
	 * Popup Window List
	 */
	private Collection popups = new Vector();

	/**
	 * Creates a new ViewPainter
	 */
	public DefSchedulerViewPainter() {
		super();
	}

	/**
	 * @see com.cc.framework.ui.painter.ControlPortionPainter#setControlPainter(com.cc.framework.ui.painter.ControlPainter)
	 */
	public void setControlPainter(ControlPainter ctrlPainter) {
		super.setControlPainter(ctrlPainter);

		today = GregorianCalendar.getInstance(getCtrl().getTimeZone());
	}

	/**
	 * Liefert das Kontrollelement
	 * 
	 * @return Kontrollelement
	 */
	protected SchedulerControl getCtrl() {
		return (SchedulerControl) getPainterContext().getControl();
	}

	/**
	 * Retrieves the index of the first day of the week
	 * 
	 * @return index of the first day of the week
	 */
	protected final int getFirstDayOfWeek() {
		return getCtrl().getFirstDayOfWeek();
	}

	/**
	 * Retrieves the Detail Text for the Scheduler Control. The detail text
	 * shows the current date
	 * 
	 * @return Detail Text
	 */
	public String getDetail() {
		DateFormat df = getDateFormat(DATEFORMAT_DETAIL);

		return df.format(getCtrl().getDate().getTime());
	}

	/**
	 * Checks if the frame should be painted
	 * 
	 * @return <code>true</code> if the frame should be painted
	 */
	protected boolean showFrame() {
		return getCtrl().showFrame();
	}

	/**
	 * Checks if the given Date schould be marked as today
	 * 
	 * @param date
	 *            The date to check
	 * @return <code>true</code> if the day should be marked as today
	 */
	protected boolean markAsToday(Calendar date) {
		return CalendarHelp.isSameDay(today, date);
	}

	/**
	 * this method calcualtes a mask that contains all the visible days.
	 * 
	 * @return Day mask
	 */
	protected int getDayOfWeekMask() {
		if (getCtrl().showWeekEndDays()) {
			// All 7 days
			return CalendarHelp.DOWM_ALLDAYS;
		} else {
			// Only the 5 working days
			return CalendarHelp.DOWM_WEEKDAYS;
		}
	}

	/**
	 * this method checks if a given day should be displyed. This depends on the
	 * WeekEndDay settings of the design model.
	 * 
	 * @param dayOfWeek
	 *            the day
	 * @return <code>true</code> if the day should be shown
	 */
	protected boolean showDay(int dayOfWeek) {
		return ((getDayOfWeekMask() & (1 << dayOfWeek)) > 0);
	}

	/**
	 * Retrieves the formatter for the given format id
	 * 
	 * @param formatterId
	 *            the id of the formatter (DATEFORMAT_xxxx constant)
	 * @return formatter object
	 */
	protected DateFormat getDateFormat(int formatterId) {
		Integer key = new Integer(formatterId);

		if (formatterCache.containsKey(key)) {
			return (DateFormat) formatterCache.get(key);
		} else {
			DateFormat formatter = createDateFormatter(formatterId, getSafeLocale());

			if (formatter == null) {
				throw new IllegalArgumentException("Illegal Formatter Id: " + formatterId);
			}

			formatterCache.put(key, formatter);

			return formatter;
		}
	}

	/**
	 * Creates a formatter object for the given format id
	 * 
	 * @param formatterId
	 *            the id of the formatter (DATEFORMAT_xxxx constant)
	 * @param locale
	 *            the locale
	 * @return formatter object
	 */
	protected DateFormat createDateFormatter(int formatterId, Locale locale) {
		switch (formatterId) {
			case DATEFORMAT_DETAIL :
				return new SimpleDateFormat(getFrameworkString(FrameworkResources.FW_SCHEDULER_DATEFORMAT_DETAIL), locale);
			case DATEFORMAT_DAY :
				return new SimpleDateFormat(getFrameworkString(FrameworkResources.FW_SCHEDULER_DATEFORMAT_DAY), locale);
			case DATEFORMAT_WEEK :
				return new SimpleDateFormat(getFrameworkString(FrameworkResources.FW_SCHEDULER_DATEFORMAT_WEEK), locale);
			case DATEFORMAT_WEEKDAY :
				return new SimpleDateFormat(getFrameworkString(FrameworkResources.FW_SCHEDULER_DATEFORMAT_WEEKDAY), locale);
			case DATEFORMAT_WEEKDAY_SHORT :
				return new SimpleDateFormat(getFrameworkString(FrameworkResources.FW_SCHEDULER_DATEFORMAT_WEEKDAY_SHORT), locale);
			case DATEFORMAT_MONTH_OF_YEAR :
				return new SimpleDateFormat(getFrameworkString(FrameworkResources.FW_SCHEDULER_DATEFORMAT_MONTHOFYEAR), locale);
			case DATEFORMAT_TIME :
				return new SimpleDateFormat(getFrameworkString(FrameworkResources.FW_SCHEDULER_DATEFORMAT_TIME), locale);
			case DATEFORMAT_DATETIME :
				return new SimpleDateFormat(getFrameworkString(FrameworkResources.FW_SCHEDULER_DATEFORMAT_DATETIME), locale);
			case DATEFORMAT_HOUR :
				return new SimpleDateFormat(getFrameworkString(FrameworkResources.FW_SCHEDULER_DATEFORMAT_HOUR), locale);
			default :
				return null;
		}
	}

	/**
	 * Retrieves the Image for the given Appointment Priority
	 * 
	 * @param priority
	 *            Priority
	 * @return Image
	 */
	protected ImageModel getPriorityImage(AppointmentPriority priority) {
		if (priority == null) {
			return null;
		} else if (priority.equals(AppointmentPriority.HIGH)) {
			return getImage(DefResources.SCHEDULER_PRIO_HIGH);
		} else if (priority.equals(AppointmentPriority.NORMAL)) {
			// return getImage(DefResources.SCHEDULER_PRIO_NORMAL);
			return null;
		} else if (priority.equals(AppointmentPriority.LOW)) {
			return getImage(DefResources.SCHEDULER_PRIO_LOW);
		} else {
			return null;
		}
	}

	/**
	 * Retrieves the state for the given Day
	 * 
	 * @param iter
	 *            iterator
	 * @param first
	 *            indicates if this is the first visible day of the month
	 * @param inherit
	 *            the state that this day should inherit
	 * @return State
	 */
	protected int getDayState(CalendarIterator iter, boolean first, int inherit) {
		int state		= inherit;
		int monthDelta	= iter.getMonthDelta();

		if (first) {
			state |= STATE_FIRST_DAY;
		}

		if (markAsToday(iter.current())) {
			state |= STATE_TODAY;
		}

		if (monthDelta == 0) {
			state |= STATE_DAY_IN_MONTH;
		} else if ((monthDelta < 0) && ((inherit & STATE_FIRST_MONTH) == 0)) {
			 state |= STATE_HIDDEN;
		} else if ((monthDelta > 0) && ((inherit & STATE_LAST_MONTH) == 0)) {
			 state |= STATE_HIDDEN;
		}

		return state;
	}

	/**
	 * Returns the HTML class for a given days state
	 * 
	 * @param state
	 *            days state
	 * @return HTML class
	 */
	protected String getDayClass(int state) {
		if ((state & STATE_HIDDEN) == STATE_HIDDEN) {
			return "hidden";
		} else if ((state & STATE_DAY_IN_MONTH) != 0) {
			// dim = day in month
			if ((state & STATE_APPOINTMENTS) != 0) {
				return "dima";
			} else {
				return "dim";
			}
		} else {
			// dom = day outside month
			if ((state & STATE_APPOINTMENTS) != 0) {
				return "doma";
			} else {
				return "dom";
			}
		}
	}

	// =====================================
	// HTML Creation
	// =====================================

	/**
	 * This method creates an appointment popup window witch is then added to
	 * the internal popup window list of the control.
	 * 
	 * @param date
	 *            The date
	 * @param appointments
	 *            The list of all available appointments for the day
	 * @param shown
	 *            the number of shown appointments
	 * @param popupAnchor
	 *            The anchor element for the popup window
	 * @return Button element
	 */
	protected PopupElement addPopupWindow(
		Calendar date,
		Appointment[] appointments,
		int shown,
		ConcreteElement popupAnchor) {

		// Calculate the number of hidden appointments
		int hidden = appointments.length - shown;

		Appointment[] hiddenAppointments = new Appointment[hidden];
		for (int i = shown; i < appointments.length; i++) {
			hiddenAppointments[i - shown] = appointments[i];
		}

		String title =
			getFrameworkString(
				DefResources.FW_TOOLTIP_SCHEDULER_RANGE,
				new Object[] { 
					new Integer(appointments.length),
					new Integer(shown),
					new Integer(hidden)});

		return addPopupWindow(title, date, hiddenAppointments, popupAnchor);
	}

	/**
	 * This method creates an appointment popup window witch is then
	 * added to the internal popup window list of the control.
	 * 
	 * @param title
	 *            the popupwindows title
	 * @param date
	 *            the date
	 * @param appointments
	 *            appointments list
	 * @param popupAnchor
	 *            the anchor element for the popup window
	 * @return Popup element
	 */
	protected PopupElement addPopupWindow(
		String title,
		Calendar date,
		Appointment[] appointments,
		ConcreteElement popupAnchor) {

		Arrays.sort(appointments, new AppointmentComparator());

		Table t = new Table();
		t.setWidth("280");
		t.setCellPadding(1);
		t.setCellSpacing(0);

		// Header
		if (title != null) {
			t.addElement(new TR()
				.addElement(new TD(html(title))
					.setNoWrap(true)
					.setClass(DefHtmlClass.HEADER)));
		}

//		// Appointment List
//		for (int i = 0; i < appointments.length; i++) {
//			t.addElement(new TR()
//				.addElement(new TD(doCreateAppointment(date, appointments[i], LABELFORMAT_LONG))));
//		}
		
		t.addElement(new TR().addElement(new TD(new Div((
				!appointments[0].getSubject().equals(""))? appointments[0].getSubject() : Entities.NBSP ))));

		PopupElement popup = new PopupElement(t);
		popup.linkTo(popupAnchor);

		// Add the popup window to the internal popup list
		addPopup(popup);
		
		return popup;
	}

	/**
	 * Creates a appointment list. If the maximum number of visible appointments
	 * gets exceeded a "more..." button will be shown.
	 * 
	 * @param date
	 *            the day for wich to create the appointment list
	 * @param appointments
	 *            the appointments for that day
	 * @param labelFormat
	 *            the Label format (LABELFORMAT_xxx) be generated
	 * @return HTML Elements
	 */
	protected ConcreteElement doCreateAppointmentList(
		Calendar date,
		Appointment[] appointments,
		int labelFormat) {

		ElementContainer container = new ElementContainer();

		if ((appointments == null) || (appointments.length == 0)) {
			container.addElement(new Div(Entities.NBSP));
		} else {
			Arrays.sort(appointments, new AppointmentComparator());

			int shown = Math.min(appointments.length, getCtrl().getMaxVisible());

			for (int i = 0; i < shown; i++) {
				container.addElement(doCreateAppointment(date, appointments[i], labelFormat));
			}

			// optional "more..." Button
			if (appointments.length > shown) {
				container
					.addElement(new Div()
						.addElement(doCreateMoreButton(date, appointments, shown))
						.setClass("more"));
			}
		}

		return container;
	}

	/**
	 * Creates a image with a clock symbol for the given time
	 * 
	 * @param date
	 *            The current date and time
	 * @return Image
	 */
	protected IMG createClockImage(Calendar date) {
		if (date == null) {
			return createImage(DefResources.SCHEDULER_CLOCK, "00");
		} else {
			int hour	= date.get(Calendar.HOUR_OF_DAY);
			int min		= date.get(Calendar.MINUTE);

			String res	= Integer.toString(100 + hour).substring(1) + ((min >= 30) ? "_" : "");

			return createImage(DefResources.SCHEDULER_CLOCK, res);
		}
	}

	/**
	 * Creates the Appointment Label
	 * 
	 * @param date
	 *            The concrete day date
	 * @param appointment
	 *            The appointment
	 * @param labelFormat
	 *            the Label format (LABELFORMAT_xxx) be generated
	 * @return The Label Text
	 */
	protected String getAppointmentLabel(Calendar date,
			Appointment appointment, int labelFormat) {
		StringBuffer label = new StringBuffer();

		if (labelFormat == LABELFORMAT_LONG) {
			label
				.append(getAppointmentTimeStr(date, appointment))
				.append(" ");
		}
		
		String subject = appointment.getSubject();

		if ((subject == null) || (subject.trim().length() == 0)) {
			// Use a generic appointment text
			subject = getFrameworkString(DefResources.FW_SCHEDULER_APPOINTMENT);
		}

		label.append(html(subject, getCtrl().getFilter()));

		return label.toString();
	}

	/**
	 * Creates a tooltip for the given appointment
	 * 
	 * @param date
	 *            The concrete day date
	 * @param appointment
	 *            The appointment
	 * @param labelFormat
	 *            the Label format (LABELFORMAT_xxx) be generated
	 * @return Tooltip text
	 */
	protected String getAppointmentTooltip(Calendar date,
			Appointment appointment, int labelFormat) {

		// Omit the Tooltip for long labels
		if (labelFormat == LABELFORMAT_LONG) {
			return null;
		} else {
			return StringHelp.join(getAppointmentTimeStr(date, appointment),
					appointment.getSubject(), ' ');
		}
	}

	/**
	 * Creates a time string for the given appointment
	 * 
	 * @param date
	 *            The concrete day date
	 * @param appointment
	 *            The appointment
	 * @return Time String
	 */
	protected String getAppointmentTimeStr(Calendar date, Appointment appointment)  {
		StringBuffer time = new StringBuffer();

		DateFormat df;

		if (appointment.isAllDayEvent()) {
			df = getDateFormat(DATEFORMAT_WEEKDAY);
			time
				.append(df.format(date.getTime()));
		} else if (appointment.isMultiDayEvent()) {
			df = getDateFormat(DATEFORMAT_DATETIME);
			time
				.append(df.format(appointment.getStartTime().getTime()))
				.append(" - ")
				.append(df.format(appointment.getEndTime().getTime()));
		} else {
			df = getDateFormat(DATEFORMAT_TIME);
			time
				.append(df.format(appointment.getStartTime().getTime()))
				.append(" - ")
				.append(df.format(appointment.getEndTime().getTime()));
		}

		return time.toString();
	}
	
	/**
	 * Creates the HTML element for an simple appointment
	 * 
	 * @param date
	 *            The date
	 * @param appointment
	 *            The appointment
	 * @param labelFormat
	 *            the Label format (LABELFORMAT_xxx) be generated
	 * @return HTML Elements
	 */
	protected ConcreteElement doCreateAppointment(Calendar date,
			Appointment appointment, int labelFormat) {

		Div element = new Div();

        /*
        // At the end we do not include zoneactions in this way, so comment all.
        element.addElement(SchedulerViewHelp.createRowActionButton(
                getPainterContext(),
                appointment,
                date,
                "mod",
                getCtrl().getTransaction()));
        element.addElement(SchedulerViewHelp.createRowActionButton(
                getPainterContext(),
                appointment,
                date,
                "vone",
                getCtrl().getTransaction()));
        element.addElement(SchedulerViewHelp.createRowActionButton(
                getPainterContext(),
                appointment,
                date,
                "del",
                getCtrl().getTransaction()));   
        */
        
        //
        //
        // - Rowactions Insertion.
        //        
        SchedulerFacadeForCommonControlsScheduler.insideTagRowActionsZone(
                element, 
                getPageContext(), 
                appointment,
                getCtrl().getName(),
                getCtrl().getAction()); 
        //
        //
        //        
        
		boolean addMargin		= false;
		boolean allDayEvent		= appointment.isAllDayEvent();
		boolean multiDayEvent	= appointment.isMultiDayEvent();

		if (multiDayEvent
			&& !allDayEvent
			&& CalendarHelp.isSameDay(date, appointment.getStartTime())) {

            element.addElement(createClockImage(appointment.getStartTime())
                    .setVspace(0)
                    .setAlign(AlignType.absmiddle) 
                    .setStyle("margin-right:1px;margin-left:5px;"));
			addMargin = true;
		}

		if (SchedulerScope.APPOINTMENT.isInMask(getCtrl().getCheckBoxMask())) {
			ClientHandler handler = new ClientHandlerImp();

			element.addElement(SchedulerViewHelp.createCheckBox(
				getPainterContext(),
				appointment,
				date,
				handler,
				false,
				getCtrl().getTransaction()));
            
			addMargin = true;
		}
		
		if (appointment.getImageRef() != null) {
			ImageMap imageMap = getCtrl().getImageMap();

			if (imageMap != null) {
				IMG img = createImage(imageMap.mapValueToImage(appointment.getImageRef()));

				if (img != null) {
					element.addElement(img
						.setVspace(0)
						.setAlign(AlignType.absmiddle));
					addMargin = true;
				}
			}
		}

		ImageModel image = getPriorityImage(appointment.getPriority());
		if (image != null) {
			element.addElement(createImage(image)
				.setVspace(0)
				.setAlign(AlignType.absmiddle));
			addMargin = true;
		}

		if (appointment.isRecurring()) {
			element.addElement(createImage(DefResources.SCHEDULER_RECURRING)
				.setVspace(0)
				.setAlign(AlignType.absmiddle));
			addMargin = true;
		}

        // create a drilldwon command for this appointment
        ActionPainter ap = createActionPainter(ControlActionDef.ACTION_APPOINTMENTCLICK);
        ap.addParameter(appointment.getUniqueId());
        ap.addParameter(CalendarHelp.toLong(date));

        ap.setLabel(getAppointmentLabel(date, appointment, labelFormat));
        
        // HEMOS TENIDO QUE AGREGAR ESTO PARA QUE EL LINK NO SEA CLICABLE.
        // LO QUE IMPLICA QUE SE VUELVE DE COLOR BLANCO, POR LO QUE...
        ap.setActive(false);
        
        if (addMargin) {
            // Some space between the image and the text
            // ...HEMOS TENIDO QUE AGREGAR 'color:#000000' EN EL ESTILO.
            ap.setStyle("margin-left:3px;color:#000000");
        } else {
            // ...HEMOS TENIDO QUE AGREGAR 'color:#000000' EN EL ESTILO.
            ap.setStyle("color:#000000");
        }
        element.addElement(ap.createElement());
        
        
        //
        //
        //
        //
        // OTRA MANERA DE INTRODUCIR EL LINK PARA QUE NO SEA CLICABLE.
        // element.addElement(new Span(getAppointmentLabel(date, appointment, labelFormat)).setStyle("color:#000000"));
        //
        //
        //
        //

		if (multiDayEvent
			&& !allDayEvent
			&& CalendarHelp.isSameDay(date, appointment.getEndTime())) {

			element.addElement(createClockImage(appointment.getEndTime())
				.setVspace(0)
				.setAlign(AlignType.absmiddle)
				.setStyle("margin-left:5px;"));
			addMargin = true;
		}

		if (multiDayEvent) {
			element.setClass("appm");
		} else if (allDayEvent) {
			element.setClass("appa");
		} else {
			element.setClass("app");
		}

		String tooltip = getAppointmentTooltip(date, appointment, labelFormat);
		if (tooltip != null) {
			element.setTitle(html(tooltip, getCtrl().getFilter()));
		}

		return element;
	}

	/**
	 * Creates a navigation header element
	 * 
	 * @return Heder element for navigation or <code>null</code>
	 */
	protected ConcreteElement doCreateHeader() {

		if (showFrame()) {
			// Do not create the navigation header when the controls
			// frame is visible.
			return null;
		} else {
			return (Table) new Table()
				.setCellPadding(1)
				.setCellSpacing(0)
				.setBorder(0)
				.setWidth("100%")
				.addElement(new TR()
					.addElement(new TD(doCreateNavigationButton(false))
						.setAlign(AlignType.left))
					.addElement(new TD(html(getDetail()))
						.setNoWrap(true)
						.setAlign(AlignType.center))
					.addElement(new TD(doCreateNavigationButton(true))
						.setAlign(AlignType.right)))
				.setClass("nav");
		}
	}

	/**
	 * Creates the "more..." Button
	 * 
	 * @param date
	 *            The date
	 * @param appointments
	 *            The list of all available appointments for the day
	 * @param shown
	 *            the number of shown appointments
	 * @return Button element
	 */
	protected ConcreteElement doCreateMoreButton(
		Calendar date,
		Appointment[] appointments,
		int shown) {

		// Calculate the number of hidden appointments
		int hidden = appointments.length - shown;
		
		// create button
		ActionPainter ap = createActionPainter(ControlActionDef.ACTION_CHANGEDATE);
		ap.addParameter(CalendarHelp.toLong(date));
		ap.addParameter(SchedulerView.DAY);
		ap.setImage(getImage(DefResources.SCHEDULER_MORE_DOWN));

		if (!getCtrl().showPopups()) {
			// Do not create a popup window but a simple tooltip
			String tooltipText =
				getFrameworkString(
					DefResources.FW_TOOLTIP_SCHEDULER_MORE,
					new Object[] { 
						new Integer(appointments.length),
						new Integer(shown),
						new Integer(hidden)});
	
			ap.setTooltip(tooltipText);
		}

        // old - inicio
        // old - inicio
        // old - inicio
        // ConcreteElement moreButton = ap.createElement();
        // old - fin
        // old - fin
        // old - fin        
        
        // new - inicio
        // new - inicio
        // new - inicio
        
        // Creates a link to all day view, and an onclik submit operation to send all other form parameters.
        ConcreteElement moreButton = SchedulerFacadeForCommonControlsScheduler.insideTagRefactorLink(
                ap.createElement(), 
                ap.getAction().toString());
        
        // new - fin
        // new - fin
        // new - fin         
		
		if (getCtrl().showPopups()) {
			addPopupWindow(date, appointments, shown, moreButton);
		}

		return moreButton;
	}

	/**
	 * Creates the Prev-Button
	 * 
	 * @param cal
	 *            The Date to change to
	 * @param view
	 *            The View
	 * @param up
	 *            Indicates if this a up or down button
	 * @return ConcreteElement
	 */
	protected ConcreteElement createNavigationButton(
		Calendar cal,
		SchedulerView view,
		boolean up) {

		// get the tooltip
		String tooltip =
			getFrameworkString(
				up
					? DefResources.FW_TOOLTIP_SCHEDULER_NEXTDATE
					: DefResources.FW_TOOLTIP_SCHEDULER_PREVDATE);

		// create button
		ActionPainter ap = createActionPainter(ControlActionDef.ACTION_CHANGEDATE);
		ap.addParameter(CalendarHelp.toLong(cal));
		ap.addParameter(view);

		String imageId;

		if (showFrame()) {
			imageId = up ? DefResources.BUTTON_SCHEDULER_NEXT_1 : DefResources.BUTTON_SCHEDULER_PREV_1;
		} else {
			imageId = up ? DefResources.SCHEDULER_NEXT : DefResources.SCHEDULER_PREV;
		}

		ap.setImage(getImage(imageId));
		ap.setTooltip(tooltip);

		return ap.createElement();
	}

	/**
	 * Creates the Roll-Button
	 * 
	 * @param up
	 *            <code>true</code> if the button rolls up
	 * @return ConcreteElement
	 */
	public abstract ConcreteElement doCreateNavigationButton(boolean up);

	/**
	 * This method creates the Body of the Scheduling Calendar Control
	 * 
	 * @return HTML Element
	 */
	protected abstract ConcreteElement doCreateBody();

	/**
	 * This method creates the View Part of the Scheduling Calendar Control
	 * 
	 * @return View Element
	 */
	public final ConcreteElement doCreateView() {
		ConcreteElement view = doCreateBody();

		// Return scheduler and all popup windows
		if (hasPopups()) {
			CompositeElement composite = new CompositeElement();
			composite.addElement(view);

			Iterator i = getPopups();
			while (i.hasNext()) {
				composite.addElement((PopupElement) i.next());
			}

			return composite;
		} else {
			return view;
		}
	}

	// ========================
	// Popup Windows
	// ========================

	/**
	 * Adds a popup Window to te popup Window List for this control
	 * 
	 * @param popup
	 *            The Popup window to add
	 */
	protected void addPopup(PopupElement popup) {
		popups.add(popup);
	}

	/**
	 * @return returns <code>true</code> if the control has any popup windows
	 */
	protected boolean hasPopups() {
		return !popups.isEmpty();
	}

	/**
	 * @return Returns a Iterator for PopupElement Objects
	 */
	protected Iterator getPopups() {
		return popups.iterator();
	}
}