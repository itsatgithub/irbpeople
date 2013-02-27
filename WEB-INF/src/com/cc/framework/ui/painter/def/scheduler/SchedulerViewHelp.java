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

package com.cc.framework.ui.painter.def.scheduler;

import java.util.Calendar;

import org.apache.ecs.AlignType;
import org.apache.ecs.ConcreteElement;

import com.cc.framework.common.CheckState;
import com.cc.framework.ui.control.ControlActionDef;
import com.cc.framework.ui.model.Appointment;
import com.cc.framework.ui.model.CheckableAppointment;
import com.cc.framework.ui.model.ClientEvent;
import com.cc.framework.ui.model.ClientHandler;
import com.cc.framework.ui.model.ImageModel;
import com.cc.framework.ui.model.SchedulerScope;
import com.cc.framework.ui.model.imp.ImageModelImp;
import com.cc.framework.ui.painter.ActionPainter;
import com.cc.framework.ui.painter.PainterContext;
import com.cc.framework.ui.painter.def.DefResources;
import com.cc.framework.util.CalendarHelp;

/**
 * Helper Class for Scheduler view painters
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision$
 */
public class SchedulerViewHelp {

	/**
	 * Retrieves the cumulated Check state for the given Appointment list
	 * 
	 * @param date
	 *            The Date of the day
	 * @param appointments
	 *            The appointments to check
	 * @return one of the CheckState constants
	 */
	protected static int getCheckState(Calendar date, Appointment[] appointments) {
		int checkState = -1;

		int checked			= 0;
		int intermediate	= 0;
		int invalid			= 0;

		for (int i = 0; i < appointments.length; i++) {
			switch (getCheckState(date, appointments[i])) {
				case -1 :
					++invalid;
					break;

				case 1 :
					++checked;
					break;

				case 2 :
					++intermediate;
					break;

				default :
					;
			}
		}

		if (invalid == appointments.length) {
			// Die Gruppe verfügt über keine selektierbaren Kinder.
			// Sie ist damit selbst nicht selektierbar.
			checkState = CheckState.UNSELECTABLE.toInt();
		} else if ((checked == 0) && (intermediate == 0)) {
			// Keines der Kinder ist markiert
			checkState = CheckState.UNCHECKED.toInt();
		} else {
			// Einige Kinder sind markiert, aber nicht alle
			checkState = (checked == appointments.length)
				? CheckState.CHECKED.toInt()
				: CheckState.UNDEFINED.toInt();
		}

		return checkState;
	}

	/**
	 * Retrieves the Check state for the given Appointment
	 * 
	 * @param date
	 *            The Date of the day
	 * @param appointment
	 *            The appointment to check
	 * @return one of the CheckState constants
	 */
	protected static int getCheckState(Calendar date, Appointment appointment) {
		if (appointment instanceof CheckableAppointment) {
			return ((CheckableAppointment) appointment).getCheckState(CalendarHelp.toLong(date));
		} else {
			return CheckState.UNSELECTABLE.toInt();
		}
	}

	/**
	 * renders a CheckBox
	 * 
	 * @param ctx
	 *            Painter context
	 * @param appointment
	 *            the appointment
	 * @param date
	 *            the concrete date
	 * @param handler
	 *            Java Script Handlers
	 * @param displayOnly
	 *            Indicates that this checkbox should be only for display
	 * @param transaction
	 *            Generates the Transaction Token in Hyperlinks
	 * @return ConcreteElement Checkbox HTML Element
	 */
	public static ConcreteElement createCheckBox(
		PainterContext ctx,
		Appointment appointment,
		Calendar date,
		ClientHandler handler,
		boolean displayOnly,
		boolean transaction) {

		int checkState	= getCheckState(date, appointment);
		int size		= 15;
		
		ConcreteElement cb	= null;

		switch (checkState) {
			case -2 :
				// Der Knoten bietet keine Checkbox an
				cb = ctx.createImage(size, DefResources.CHECKBOX_NONE).setAlign(AlignType.absmiddle);
				break;

			case -1 :
				// Der Knoten verfügt nicht über die notwendigen Informationen
				cb = ctx.createImage(size, DefResources.CHECKBOX_INVALID).setAlign(AlignType.absmiddle);
				break;

			case 0 :
				// Der Knoten ist nicht markiert
				if (displayOnly) {
					cb = ctx.createImage(size, DefResources.CHECKBOX_UNCHECKED).setAlign(AlignType.absmiddle);
				} else {
					ActionPainter ap = ctx.createActionPainter(ControlActionDef.ACTION_CHECKAPPOINTMENT);

					ap.setTransaction(transaction);
					ap.addParameter(appointment.getUniqueId());
					ap.addParameter(CalendarHelp.toLong(date));
					ap.addParameter(true);
					ap.setImage(ctx.getImage(size, DefResources.CHECKBOX_UNCHECKED));
					ap.addEventHandler(ClientEvent.ONCLICK, handler.getHandler(ClientEvent.EXT_ONCHECK));

					cb = ap.createElement();
				}
				break;

			case 1 :
				// Der Knoten ist vollständig markiert
				if (displayOnly) {
					cb = ctx.createImage(size, DefResources.CHECKBOX_CHECKED).setAlign(AlignType.absmiddle);
				} else {
					ActionPainter ap = ctx.createActionPainter(ControlActionDef.ACTION_CHECKAPPOINTMENT);

					ap.setTransaction(transaction);
					ap.addParameter(appointment.getUniqueId());
					ap.addParameter(CalendarHelp.toLong(date));
					ap.addParameter(false);
					ap.setImage(ctx.getImage(size, DefResources.CHECKBOX_CHECKED));
					ap.addEventHandler(ClientEvent.ONCLICK, handler.getHandler(ClientEvent.EXT_ONUNCHECK));

					cb = ap.createElement();
				}
				break;

			case 2 :
				// Der Knoten ist nur teilweise markiert
				if (displayOnly) {
					cb = ctx.createImage(size, DefResources.CHECKBOX_INDETERMINATE).setAlign(AlignType.absmiddle);
				} else {
					ActionPainter ap = ctx.createActionPainter(ControlActionDef.ACTION_CHECKAPPOINTMENT);

					ap.setTransaction(transaction);
					ap.addParameter(appointment.getUniqueId());
					ap.addParameter(CalendarHelp.toLong(date));
					ap.addParameter(false);
					ap.setImage(ctx.getImage(size, DefResources.CHECKBOX_INDETERMINATE));
					ap.addEventHandler(ClientEvent.ONCLICK, handler.getHandler(ClientEvent.EXT_ONUNCHECK));

					cb = ap.createElement();
				}
				break;

			default :
				;
		}

		return cb;
	}

	/**
	 * renders a CheckBox
	 * 
	 * @param ctx
	 *            Painter context
	 * @param appointments
	 *            the appointment List
	 * @param date
	 *            the concrete date
	 * @param scope
	 *            View scope (day,week,month,...)
	 * @param handler
	 *            Java Script Handlers
	 * @param displayOnly
	 *            Indicates that this checkbox should be only for display
	 * @param transaction
	 *            Generates the Transaction Token in Hyperlinks
	 * @return ConcreteElement Checkbox HTML Element
	 */
	public static ConcreteElement createCheckBox(
		PainterContext ctx,
		Appointment[] appointments,
		Calendar date,
		SchedulerScope scope,
		ClientHandler handler,
		boolean displayOnly,
		boolean transaction) {

		int checkState	= getCheckState(date, appointments);
		int size		= 15;
		
		ConcreteElement cb	= null;

		switch (checkState) {
			case -2 :
				// Der Knoten bietet keine Checkbox an
				cb = ctx.createImage(size, DefResources.CHECKBOX_NONE).setAlign(AlignType.absmiddle);
				cb.setStyle("padding-left: 5px");
				break;

			case -1 :
				// Der Knoten verfügt nicht über die notwendigen Informationen
				cb = ctx.createImage(size, DefResources.CHECKBOX_INVALID).setAlign(AlignType.absmiddle);
				cb.setStyle("padding-left: 5px");
				break;

			case 0 :
				// Der Knoten ist nicht markiert
				if (displayOnly) {
					cb = ctx.createImage(size, DefResources.CHECKBOX_UNCHECKED).setAlign(AlignType.absmiddle);
					cb.setStyle("padding-left: 5px");
				} else {
					ActionPainter ap = ctx.createActionPainter(ControlActionDef.ACTION_CHECKDATE);

					ap.setTransaction(transaction);
					ap.addParameter(CalendarHelp.toLong(date));
					ap.addParameter(scope);
					ap.addParameter(true);
					ap.setImage(ctx.getImage(size, DefResources.CHECKBOX_UNCHECKED));
					ap.addEventHandler(ClientEvent.ONCLICK, handler.getHandler(ClientEvent.EXT_ONCHECK));
					ap.setStyle("padding-left: 5px");

					cb = ap.createElement();
				}
				break;

			case 1 :
				// Der Knoten ist vollständig markiert
				if (displayOnly) {
					cb = ctx.createImage(size, DefResources.CHECKBOX_CHECKED).setAlign(AlignType.absmiddle);
					cb.setStyle("padding-left: 5px");
				} else {
					ActionPainter ap = ctx.createActionPainter(ControlActionDef.ACTION_CHECKDATE);

					ap.setTransaction(transaction);
					ap.addParameter(CalendarHelp.toLong(date));
					ap.addParameter(scope);
					ap.addParameter(false);
					ap.setImage(ctx.getImage(size, DefResources.CHECKBOX_CHECKED));
					ap.addEventHandler(ClientEvent.ONCLICK, handler.getHandler(ClientEvent.EXT_ONUNCHECK));
					ap.setStyle("padding-left: 5px");

					cb = ap.createElement();
				}
				break;

			case 2 :
				// Der Knoten ist nur teilweise markiert
				if (displayOnly) {
					cb = ctx.createImage(size, DefResources.CHECKBOX_INDETERMINATE).setAlign(AlignType.absmiddle);
					cb.setStyle("padding-left: 5px");
				} else {
					ActionPainter ap = ctx.createActionPainter(ControlActionDef.ACTION_CHECKDATE);

					ap.setTransaction(transaction);
					ap.addParameter(CalendarHelp.toLong(date));
					ap.addParameter(scope);
					ap.addParameter(false);
					ap.setImage(ctx.getImage(size, DefResources.CHECKBOX_INDETERMINATE));
					ap.addEventHandler(ClientEvent.ONCLICK, handler.getHandler(ClientEvent.EXT_ONUNCHECK));
					ap.setStyle("padding-left: 5px");

					cb = ap.createElement();
				}
				break;

			default :
				;
		}

		return cb;
	}

	/**
	 * renders a add appointment button
	 * 
	 * @param ctx
	 *            Painter context
	 * @param date
	 *            The Date of the day
	 * @param transaction
	 *            Generates the Transaction Token in Hyperlinks
	 * @return ConcreteElement
	 */
	public static ConcreteElement createAddButton(
		PainterContext ctx,
		Calendar date,
		boolean transaction) {

		// create buttons
		ActionPainter ap = ctx.createActionPainter(ControlActionDef.ACTION_ADDAPPOINTMENT);

		ap.setTransaction(transaction);
		ap.addParameter(CalendarHelp.toLong(date));
		ap.setTooltip(ctx.getFrameworkString(DefResources.FW_TOOLTIP_SCHEDULER_ADDAPPOINTMENT));
		ap.setImage(ctx.getImage(DefResources.SCHEDULER_ADD_APPOINTMENT));
		ap.setStyle("padding-left: 5px");
		
		return ap.createElement();
	}
    /**
     * renders a row-action appointment button to delete, modify or view the event.
     * 
     * @param ctx
     *            Painter context
     * @param date
     *            The Date of the day
     * @param transaction
     *            Generates the Transaction Token in Hyperlinks
     * @return ConcreteElement
     */
    public static ConcreteElement createRowActionButton(
        PainterContext ctx,
        Appointment appointment,
        Calendar date,
        String actionImageName,
        boolean transaction) {

        // create buttons
        
        // Indicamos la misma accion que cuando le damos al link, es decir la de editar.
        ActionPainter ap = ctx.createActionPainter(ControlActionDef.ACTION_APPOINTMENTCLICK);
        
        // Esto no funciona... (intento de crear la accion de borrar directamente)
        // ActionPainter ap = ctx.createActionPainter(ControlActionDef.ACTION_DELETE);

        ap.setTransaction(transaction);
        
        // Hemos tenido que agregar esta linea que pasa el identificador del appointment.
        ap.addParameter(appointment.getUniqueId());
        ap.addParameter(CalendarHelp.toLong(date));
        ap.setTooltip(ctx.getFrameworkString(DefResources.FW_TOOLTIP_SCHEDULER_ADDAPPOINTMENT));
        
        // ImageModel im = null;
        // im = ctx.getImage(DefResources.SCHEDULER_ADD_APPOINTMENT);
        // im = new ImageModelImp("../fw/def/image/severity/imgWarning13x13.gif", 13, 13);  
        // ap.setImage(im);
        
        ap.setImage(ctx.getImage(actionImageName));
        
        ap.setStyle("padding-left: 5px");
        
        return ap.createElement();
    }    
}