/*
 * Created on 21/05/2007
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package uicomponents.scheduler;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.apache.ecs.ConcreteElement;
import org.apache.ecs.html.Div;
import org.apache.ecs.html.Input;
import org.apache.struts.util.MessageResources;

import utils.dateformat.MultipleDateFormat;
import utils.jsp.JspUtils;
import utils.jsp.ZoneConstructorUtils;
import utils.jsp.ZoneConstructorUtils.RefActionButtonData;
import utils.userUtils.UserUtils;

import com.cc.framework.ui.control.ControlRequestContext;
import com.cc.framework.ui.control.SchedulerControl;
import com.cc.framework.ui.model.Appointment;
import com.cc.framework.ui.model.AppointmentPriority;
import com.cc.framework.ui.model.imp.AppointmentImp;
import com.cc.framework.ui.model.imp.SchedulerDataModelImp;
import com.cc.framework.ui.painter.ColorPalette;
import com.cc.framework.ui.painter.PainterFactory;
import com.cc.framework.util.Formatter;
import com.cc.sample.common.UserInterfaceOptions;
import com.cc.sample.presentation.painter.AppPainterFactory;

public class SchedulerFacadeForCommonControlsScheduler {

    /*
     * Timezone.
     */
    public static TimeZone timeZone = TimeZone.getDefault();

    /*
     * Longitud de la etiqueta máxima.
     */
    public static int LABEL_MAX_LENGTH_DAY =  200;
    public static int LABEL_MAX_LENGTH_WORKWEEK =  40;
    public static int LABEL_MAX_LENGTH_WEEK =  30;
    public static int LABEL_MAX_LENGTH_MONTH =  30;

    /*
     * Control label length.
     */
    public static boolean controlLabelLength = false;  // FALSE == DISABLED.
    
    /**
     * View types.
     */
    public static String VIEW_DAY = "day";  
    public static String VIEW_WORKWEEK = "workweek";  
    public static String VIEW_WEEK = "week";     
    public static String VIEW_MONTH = "month";      
    public static String VIEW_YEAR = "year";

    /**
     * Image Style in zone insertion into scheduler.
     */
    private static String IMAGE_STYLE_0 = "";      
    private static String IMAGE_STYLE_1 = "style=\"position:relative;top:0;left:0;border:0;\" onmousedown=\"this.style.top=1;this.style.left=1;\" onmouseup=\"this.style.top=0;this.style.left=0;\"";  
    private static String IMAGE_STYLE_2 = "onmousedown=\"this.style.top=1;this.style.left=1;\" onmouseup=\"this.style.top=0;this.style.left=0;\"";

    /*
     * Request Navigation parameter inserted by scheduler component in top actions to change
     * the calendar view (month, year, day, ...)
     */
    private static String NAV_PARAMETER = "nav";
    
    /*
     * Parameter names.
     */
    static public final String PARAMNAME_ACTION = "action";
    static public final String PARAMNAME_PARAM = "param";
    
    /*
     * Parameter values.
     */
    // For 'ACTION' parameter.
    static public final String PARAMVALUE_ACTION_REFRESH = "Refresh";
    static public final String PARAMVALUE_ACTION_VIEW = "View";
    static public final String PARAMVALUE_ACTION_CHANGEDATE = "ChangeDate";
    static public final String PARAMVALUE_ACTION_SELECTDATE = "SelectDate";
    
    /*
     * Parameter name to store in the request the name of the calendar.
     */
    private static final String PARAMNAME_SCHEDULERNAME = "theScheduler";     
    /*
     * Default rowaction.
     */
    static public final String ZONE_NAME_DEFAULT = "rowaction";    
    /*
     * Parameter name prefix for zoneName.
     */
    static public final String PARAMNAME_ZONENAMEPREFIX = "zoneFor_";
    /*
     * Parameter name prefix for zoneName.
     */
    static public final String PARAMNAME_DATEFORMATPREFIX = "dateFormatFor_";    
    /*
     * Default hardcoded title.
     */
    static public final String DEFAULTHARDCODED_TITLE ="";
    /*
     * Default hardcoded appointment name.
     */
    static public final String DEFAULTHARDCODED_DEFAULTNAME ="***";    
    /*
     * Message value for TITLE.
     */
    static public final String MESSAGE_TITLE = "scheduler.title";
    /*
     * Message value for DEFAULT_NAME.
     */
    static public final String MESSAGE_DEFAULTNAME = "scheduler.defaultname";

    private static final String SCHEDULER_PREFIX = "sched";

	private static final String SCHEDULER_VIEW_YEAR = "sched_view_year";
	
	public static final String CLASS_FESTIU = "festiu";
	public static final String CLASS_VACANCES = "vacances";
	public static final String CLASS_AP = "ap";
	public static final String CLASS_AP_APROVAT = "aap";
	public static final String CLASS_VACANCES_APROVAT = "avacances";
	public static final String CLASS_LIMIT_VANCANCES = "limvac";
	public static final String CLASS_VACANCES_DISFRUTAT = "dvancances";
	public static final String CLASS_AP_DISFRUTAT = "dap";
	
    
	static public void facadePrepareScheduler(HttpServletRequest request, String schedulerName, String zoneName, int year) throws Exception {
	   	 // Scheculer loading or identification and preparing.
        prepareScheduler(request, schedulerName, zoneName, year);
        
        // Put the scheduler name in the request to be obteined lates in JSP (in the tag).
        setSchedulerNameInTheRequest(request, schedulerName);
	}
	
	
    /**
     * Call this method to load information in the scheduler from presentation struts actions.
     * @param request HttpServletRequest object.
     * @param viewlistElements Objects of this list have to implement 'IEvent' interface,
     * this is the only requeriment.
     * @param schedulerName Name of scheduler bean.
     * @param zoneName Are used the actions of this zone.
     * @throws Exception
     */
    static public void facadeLoadAppointments(HttpServletRequest request, List viewlistElements, String schedulerName, String zoneName, Locale l) throws Exception {
    	
        // One 'MultipleDateFormat' instance for every calendar from session.
        MultipleDateFormat mdf = getMultipleDateFormatFromSession(request, schedulerName);      
        
        // Appointment load.
        loadAppointments(mdf, (SchedulerControl)request.getSession().getAttribute(schedulerName), viewlistElements, l, getViewYear(request));
    }

    /**
     * The name of the zone is saved in the session.
     * @param request HttpServletRequest object.
     * @param schedulerName Name of scheduler bean.
     * @param zoneName Are used the actions of this zone.
     */
    private static void saveZoneNameForThisScheduler(HttpServletRequest request, String schedulerName, String zoneName) {
        request.getSession().setAttribute(PARAMNAME_ZONENAMEPREFIX + schedulerName, zoneName);
    }

    /**
     * To use internally to obtain the zone name from session.
     * @param request HttpServletRequest object.
     * @param schedulerName Name of scheduler bean.
     * @return
     */
    private static String obtainZoneNameFromRequestForThisScheduler(HttpServletRequest request, String schedulerName) {
        String zoneName = (String) request.getSession().getAttribute(PARAMNAME_ZONENAMEPREFIX + schedulerName);
        if(zoneName==null) 
            return ZONE_NAME_DEFAULT;
        else
            return zoneName;
    }    

    /**
     * Prepare the session bean that is the scheduler in one specific page.
     * @param request HttpServletRequest object.
     * @param schedulerName
     * @param zoneName
     * @param l 
     * @return
     * @throws Exception
     */
    static private SchedulerControl prepareScheduler(HttpServletRequest request, String schedulerName, String zoneName, int year) throws Exception {   
        
//      ...?ctrl=scheduler_Action_viewlist_evento&action=Refresh         
        
//      ...?ctrl=scheduler_Action_viewlist_evento&action=View&param=day                          
//      ...?ctrl=scheduler_Action_viewlist_evento&action=View&param=workweek                     
//      ...?ctrl=scheduler_Action_viewlist_evento&action=View&param=week                         
//      ...?ctrl=scheduler_Action_viewlist_evento&action=View&param=month                        
//      ...?ctrl=scheduler_Action_viewlist_evento&action=View&param=year         
        
//      ...?ctrl=scheduler_Action_viewlist_evento&action=ChangeDate&param=1175444346222&param=mon
//      ...?ctrl=scheduler_Action_viewlist_evento&action=ChangeDate&param=1180714746222&param=mon
        
//      ...?ctrl=scheduler_Action_viewlist_evento&action=SelectDate&param=1177949946222&param=day
        
        
        // What action?
        String action = request.getParameter(PARAMNAME_ACTION);
        
        // What id? What viewPoint?
        String id = null;
        String viewPoint = null;
        String[] params = request.getParameterValues(PARAMNAME_PARAM);  
        if(params!=null) {
            if(params.length == 1) 
                viewPoint = params[0];
            else if(params.length>1) {
                id = params[0];
                viewPoint = params[1];
            }
        }
        
        // 'nav' parameter.
        // Makes scheduler view stateful.
        String sessionNav = null;
        if(viewPoint !=null && action!=null) {
        	sessionNav = viewPoint;
        	request.getSession().setAttribute(schedulerName + NAV_PARAMETER, sessionNav);
        } else 
            sessionNav = (String) request.getSession().getAttribute(schedulerName + NAV_PARAMETER);
            
        // Controls Reset the scheduler
        boolean reset = (null == sessionNav);
        
        // posem a la sessio la vista anual
        if(sessionNav == null){
        	request.getSession().setAttribute(schedulerName + NAV_PARAMETER, "year");
        }
        
        // create the SchedulerControl and populate it
        // with the Data to display
        SchedulerControl scheduler = (SchedulerControl) request.getSession().getAttribute(schedulerName);
        
        if (scheduler == null) {
            scheduler = new SchedulerControl();
            
            // put the SchedulerControl into the Session-Object.
            // Our SchedulerControl is a statefull Object.
            request.getSession().setAttribute(schedulerName, scheduler);
            
            // Put in session the name of the zone used in scheduler rowaction per appointment.
            if(zoneName!=null && !zoneName.equalsIgnoreCase(""))
                saveZoneNameForThisScheduler(request, schedulerName, zoneName);            
        }
    
        if (reset) {
            scheduler.reset();
        } else  {
            
            // Seleccion de vista.
            ControlRequestContext ctx = null;
            if(action!=null) {
                if(action.equalsIgnoreCase(PARAMVALUE_ACTION_REFRESH)) {
                    // ...?ctrl=scheduler_Action_viewlist_evento&action=Refresh
                    scheduler.onRefresh(ctx);
                    
/*                 }  else if(action.equalsIgnoreCase(PARAMVALUE_ACTION_VIEW)) {
                    // ...?ctrl=scheduler_Action_viewlist_evento&action=View&param=day                          
                    // ...?ctrl=scheduler_Action_viewlist_evento&action=View&param=workweek                     
                    // ...?ctrl=scheduler_Action_viewlist_evento&action=View&param=week                         
                    // ...?ctrl=scheduler_Action_viewlist_evento&action=View&param=month                        
                    // ...?ctrl=scheduler_Action_viewlist_evento&action=View&param=year                     
                    scheduler.onView(ctx, sessionNav);
*/
                } else if(action.equalsIgnoreCase(PARAMVALUE_ACTION_CHANGEDATE) && id!=null) {
                    // ...?ctrl=scheduler_Action_viewlist_evento&action=ChangeDate&param=1175444346222&param=mon
                    // ...?ctrl=scheduler_Action_viewlist_evento&action=ChangeDate&param=1180714746222&param=mon
                    scheduler.onChangeDate(ctx, Long.valueOf(id).longValue(), sessionNav);
                    year = -1;
                    request.getSession().setAttribute("year", ""+scheduler.getDate().get(Calendar.YEAR));
                } /*else if(action.equalsIgnoreCase(PARAMVALUE_ACTION_SELECTDATE) && id!=null) {
                    // ...?ctrl=scheduler_Action_viewlist_evento&action=SelectDate&param=1177949946222&param=day
                    //scheduler.onSelectDate(ctx, Long.valueOf(id).longValue(), sessionNav);
                	Calendar c = getCalendarFromLong(Long.valueOf(id).longValue());
                	if(!hasAppoiment(scheduler, c)) {
                		UseCaseFacade.CreateCalendar(c.getTime(), "");
                	} else {
                		UseCaseFacade.DeleteCalendar(getAppoimentId(scheduler, c));
                	}
                } */
            }
        }
        
        
        
        // forcem a mostrar l'any sencer de Gener a Desembre
        if(year>0) {
        	Calendar cal = new GregorianCalendar(year, 0, 1);
        	scheduler.setDate(cal);
        } else {
        	scheduler.setDate(new GregorianCalendar(scheduler.getDate().get(Calendar.YEAR), 0, 1));
        }
        
        request.getSession().setAttribute(SCHEDULER_VIEW_YEAR, scheduler.getDate().get(Calendar.YEAR));
        
        return scheduler;
        
    }
    
/*    private static Calendar getCalendarFromLong(long date) {
    	Calendar c = new GregorianCalendar(timeZone);
    	c.setTime(new Date(date));
    	return c;
	}

	private static boolean hasAppoiment(SchedulerControl scheduler, Calendar date) {
		SchedulerDataModelImp dm = (SchedulerDataModelImp) scheduler.getDataModel();
		Appointment[] ap = dm.getAppointments(date);
		return ap!=null && ap.length > 0;
	}
	
	private static String getAppoimentId(SchedulerControl scheduler, Calendar date) {
		SchedulerDataModelImp dm = (SchedulerDataModelImp) scheduler.getDataModel();
		return dm.getAppointments(date)[0].getUniqueId();
	} */

	/**
     * Load of all objets (appointments, meetings, events...) in one calendar.
     * @param scheduler The calendar.
     * @param viewlistElements List of appointments.
	 * @param year 
     * @throws Exception
     */
    static private void loadAppointments(MultipleDateFormat mdf, SchedulerControl scheduler, List viewlistElements, Locale l, int year) throws Exception {
        
        // Select max lenght in visible appointment title, it depends on
        // view type.
      //  int maxLength = LABEL_MAX_LENGTH_MONTH;      
        
        // Control long text in the apppointment subject.
       /* if(controlLabelLength) {
            SchedulerView sv = scheduler.getView();
            // sessionNav is not null...
            if(sv == SchedulerView.DAY) 
                maxLength = LABEL_MAX_LENGTH_DAY;
            else if(sv == SchedulerView.WORKWEEK )
                maxLength = LABEL_MAX_LENGTH_WORKWEEK;
            else if(sv == SchedulerView.WEEK)
                maxLength = LABEL_MAX_LENGTH_WEEK;
            else if(sv == SchedulerView.MONTH)
                maxLength = LABEL_MAX_LENGTH_MONTH;
        }       */ 
        
        // Vector to load appointments.
        Vector appointments = new Vector();
        
        // Loading process.
        Iterator iter = viewlistElements.iterator();
        ICalendar event = null;
        String subject = null;
        String id = null;
        AppointmentImp appointment = null;
        Calendar startdate, enddate;
        while (iter.hasNext()) {
            event = (ICalendar) iter.next();
        
            // ... day.
            startdate = event.getCalendarIntialdate(mdf, l);
            enddate = event.getCalendarEnddate(mdf, l);
            
            // no volem veure marcats els dies que estan fora de l'any
            // per tant els traurem...
            
            if(startdate.get(Calendar.YEAR) != year) {
            	startdate = new GregorianCalendar(year, 0, 1);
            }
            
            if(enddate.get(Calendar.YEAR) != year) {
            	enddate = new GregorianCalendar(year, 11, 31);
            }
            
            // ...Id.
            id = event.getCalendarId();
                
            // Init time and id must be NOT NULL!!.
            // to handle this appointment.
            if(startdate!=null && id!=null && !id.equalsIgnoreCase("")) {
                
                // ... subject.
                subject = event.getCalendarSubject().trim(); 
                
				// -- Appointment creation.
                appointment = new AppointmentImp(
                        id.trim(),
                        subject.trim(),
                        startdate,
                        enddate,
                        AppointmentPriority.NONE);
                
                appointment.setAllDayEvent(true);
                appointment.setType(getClassType(event.getType(), event.getStatus()));
                
                // -- Add the appointment!.
                appointments.add(appointment);
            }
        }
        
        // List insertion in Calendar.
        SchedulerDataModelImp list = new SchedulerDataModelImp();
        list.addAppointments(appointments);
        scheduler.setDataModel(list);            
        
    }    
    
    public static String getClassType(String type, String status) {
    	if(type.equals(ICalendar.TYPE_VACANCES)) {
    		if(status.equals(ICalendar.STATUS_DEMANAT)) return CLASS_VACANCES;
    		else if(status.equals(ICalendar.STATUS_APROVAT))return CLASS_VACANCES_APROVAT;
    		else return CLASS_VACANCES_DISFRUTAT;
    	} else if(type.equals(ICalendar.TYPE_ASUMPTES_PROPIS)) {
    		if(status.equals(ICalendar.STATUS_DEMANAT)) return CLASS_AP;
    		else if(status.equals(ICalendar.STATUS_APROVAT))return CLASS_AP_APROVAT;
    		else return CLASS_AP_DISFRUTAT;
    	} else if(type.equals(ICalendar.TYPE_FESTIU)) return CLASS_FESTIU;
    	  else if(type.equals(ICalendar.TYPE_LIMIT_VANCANCES_ANY_ANTERIOR)) 
    		  return CLASS_LIMIT_VANCANCES;
    	return "";
	}


	/**
     * 
     * @param request
     * @param schedulerName
     * @return
     */
    private static MultipleDateFormat getMultipleDateFormatFromSession(HttpServletRequest request, String schedulerName) {
        MultipleDateFormat mdf = (MultipleDateFormat) request.getSession().getAttribute(PARAMNAME_DATEFORMATPREFIX);
        if(mdf==null) {
            mdf = new MultipleDateFormat();
            request.getSession().setAttribute(PARAMNAME_DATEFORMATPREFIX, mdf);
        }
        return mdf;
    }

    /**
     * This method generates de html to insert all zone code necessary to
     * visualize 'row actions' in every cell. (date, hour, etc.)
     * @param element All the generated HTML is inserted in this Div.
     * @param pageContext to obtain HttpServletRequest object.
     * @param appointmentId Appointment identifier.
     */
   static public void insideTagRowActionsZone(
            Div element,
            PageContext pageContext,
            Appointment appointment,
            String schedulerName,
            String rowactionZone) { 

        // Get the rowactionZone.
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        String zoneName = rowactionZone;
        if(zoneName==null || zoneName.equalsIgnoreCase("")) 
            zoneName = obtainZoneNameFromRequestForThisScheduler(request, schedulerName);
        if(zoneName==null || zoneName.equalsIgnoreCase(""))
            return;
        
        // Build the zonaction.
        ZoneConstructorUtils zcu = ZoneConstructorUtils.getViewListRowActionZone(
                request, 
                pageContext, 
                zoneName, 
                null);
        
        Iterable<RefActionButtonData> buttons =  zcu.getActions();
        Iterator<RefActionButtonData> itB = buttons.iterator();
        RefActionButtonData ra = null;
        
        // String imageStyle = IMAGE_STYLE_2;
        String imageStyle = IMAGE_STYLE_1;
        
        // Add all actions in HTML format.
        while (itB.hasNext()) {
            ra = itB.next();
            element.addElement("<input class=\"calzone\" type=\"image\" " 
                    + "onclick=\"" + ra.getSubmitFunctionCall() + "return false;\" "
                    + "src=\"" + JspUtils.getProjectPath(request) + "/images/CATEGORY/action-type/" + ra.getConcept() + ".gif\" "
                    + "width=\"16\" height=\"16\" "
                    + "title=\"" + ra.getCaption() + "\" "
                    + imageStyle
                    + "/>"
            );
            ra.getSubmitFunctionCall();
        }
        
        //
        // - Code Builded example:
        //
        
        /*
        //element.addElement("<table border=0 cellspacing=0 cellpadding=0>");
        //element.addElement("<tr>");
        //element.addElement("<td height=23 width=19 style=\"padding-right:2px\" >");
        element.addElement("<input type=\"image\" onclick=\"submitRowAction('../obo_EventoSys/action_modify_page_evento',this.form,'bo_eventocode','0000000000011');return false;\" src=\"/generationTestCalendarInsertion/images/CATEGORY/action-type/mod.gif\" width=\"16\" height=\"16\" title=\"Modify evento\" style=\"position:relative;top:0;left:0;border:0;\" onmousedown=\"this.style.top=1;this.style.left=1;\" onmouseup=\"this.style.top=0;this.style.left=0;\"/>");
        //element.addElement("</td>");
        //element.addElement("<td height=23 width=19 style=\"padding-right:2px\" >");
        element.addElement("<input type=\"image\" onclick=\"submitRowAction('../obo_EventoSys/action_view_evento',this.form,'bo_eventocode','0000000000011');return false;\" src=\"/generationTestCalendarInsertion/images/CATEGORY/action-type/vone.gif\" width=\"16\" height=\"16\" title=\"View evento\" style=\"position:relative;top:0;left:0;border:0;\" onmousedown=\"this.style.top=1;this.style.left=1;\" onmouseup=\"this.style.top=0;this.style.left=0;\"/>");
        //element.addElement("</td>");
        //element.addElement("<td height=23 width=19 style=\"padding-right:2px\" >");
        element.addElement("<input type=\"image\" onclick=\"submitRowAction('../obo_EventoSys/action_delete_page_evento',this.form,'bo_eventocode','0000000000011');return false;\" src=\"/generationTestCalendarInsertion/images/CATEGORY/action-type/del.gif\" width=\"16\" height=\"16\" title=\"Delete evento\" style=\"position:relative;top:0;left:0;border:0;\" onmousedown=\"this.style.top=1;this.style.left=1;\" onmouseup=\"this.style.top=0;this.style.left=0;\"/>");
        //element.addElement("</td>");
        //element.addElement("</tr>");
        //element.addElement("<tr>");
        //element.addElement("</tr>");
        //element.addElement("</table>");
        */
    }

    
    /**
     * Adapts time to this scheduler component.
     * @param ingoingTime Input time.
     * @return The output and adapted time.
     * @throws ParseException
     */
    public static Calendar facadeTimeAdapter(String ingoingTime, MultipleDateFormat mdf, Locale l) {

        Date date = mdf.parse(ingoingTime, l);
        if(date==null) return null;
        
        // For this calendar we need the date in Calendar format. We need a Calendar instance.
        Calendar outgoingTime  = new GregorianCalendar(SchedulerFacadeForCommonControlsScheduler.timeZone);
        outgoingTime.setTime(date);
        
        return outgoingTime;
    }

    /**
     * Configure the user interface for the component in one session (for each user).
     * Initialization of scheduler component, done usually in login operation.
     * @param request HttpServletRequest object.
     */
    public static void facadeConfigure(HttpServletRequest request, String uiType) {

        //
        //
        //
        // - Parametros que configuran los componenetes CC.
        //
        
        // .set the locale
//        Locale locale = UserUtils.getCurrentLocale(request);
//        request.getSession().setAttribute(org.apache.struts.Globals.LOCALE_KEY, locale);
//        
        // .register the painter as a session painter, so each
        // user can use a different user interface
        PainterFactory factory  = UserInterfaceOptions.getFactory(uiType);
        ColorPalette palette    = UserInterfaceOptions.getPalette(uiType);            
        PainterFactory.resetSessionPainter(request.getSession());
        PainterFactory.registerSessionPainter(request.getSession(), new AppPainterFactory(palette));
        PainterFactory.registerSessionPainter(request.getSession(), factory);    
        
        //
        //
        //        
    }
    
    /**
     * Obtains scheduler name from request object.
     * @param request HttpServletRequest object.
     */
    public static String prepareTagGetSchedulerNameFromRequest(HttpServletRequest request) {
        return (String) request.getAttribute(PARAMNAME_SCHEDULERNAME);
    }
    
    /**
     * Puts scheduler name in request object.
     * @param request HttpServletRequest object.
     * @param schedulerName Scheduler name.
     */
    private static void setSchedulerNameInTheRequest(HttpServletRequest request, String schedulerName) {
        request.setAttribute(PARAMNAME_SCHEDULERNAME, schedulerName);
    }    
    
    /**
     * Obtains scheduler name from request object.
     * @param request HttpServletRequest object.
     */
    public static String prepareTagGetSchedulerTitle(String schedulerName, String messagesSource, Locale locale) {

        String title = MessageResources.getMessageResources(messagesSource).getMessage(locale, MESSAGE_TITLE + "." + schedulerName);
        if(title==null || title.equalsIgnoreCase("")) {
            title = MessageResources.getMessageResources(messagesSource).getMessage(locale, MESSAGE_TITLE);
            if(title==null) {
                title = DEFAULTHARDCODED_TITLE;
            }            
        }
        
        return title;
    }

    /**
     * Button (href) to Input transformation.
     * @param button starts with an href
     * @return returns an input
     */
    public static ConcreteElement insideTagHrefToInputHTMLTagTransformer(ConcreteElement href) {
        
        // NOTE: The button is really an href with an image, we can
        // see this in the bottom examples:
        
        /*

        ---------------------------------------------------------
        <a 
          href='/generationTest/obo_FurgonetaSys/action_modify_page_furgoneta.do?
            bo_furgonetacode=0000000000002&
              ctrl=scheduler_Action_modify_page_furgoneta&
                action=Refresh' 
          title='Refresh scheduler'>
            
                <img 
                  width='15' 
                  src='../fw/def/image/buttons/btnRefresh1.gif' 
                  height='15' 
                  border='0' 
                  align='absmiddle' 
                  id='btn' 
                  vspace='0'>
                  
                                </a>
        
        ---------------------------------------------------------
        <a 
          href='/generationTest/obo_FurgonetaSys/action_modify_page_furgoneta.do?
            bo_furgonetacode=0000000000002&
              ctrl=scheduler_Action_modify_page_furgoneta&
                action=View&
                  param=day' 
          title='switch to day view'>
            
              <img 
                width='16' 
                src='../fw/def/image/scheduler/btnDay1.gif' 
                height='15' 
                border='0' 
                align='absmiddle' 
                id='btn' 
                vspace='0'>
                
                                </a>         
                                
        ---------------------------------------------------------

        */
        
        // And want to transform the top to this type of input...
        
        /*
        
        ---------------------------------------------------------
        <input 
          width="16" 
          type="image" 
          height="16" 
          onmouseup="this.style.top=0;this.style.left=0;" 
          onmousedown="this.style.top=1;this.style.left=1;" 
          style="border: 0pt none ; position: relative; top: 0pt; left: 0pt;" 
              title="Modify camion" 
              src="../fw/def/image/scheduler/btnDay1.gif" 
              onclick="document.forms[0].action=document.forms[0].action+'?ctrl=scheduler_Action_mis_reservas&action=View&param=day';"/>        
        
        ---------------------------------------------------------
        
        */
        
        //
        //
        // Get parameters from href.
        //
        //
        
        // Scheduler parameters ('ctrl', 'action', and 'param' parameters).
        String schedulerParams = null;
        try {
            int questionMarkIndex = href.getAttribute("href").indexOf("?");
            schedulerParams = href.getAttribute("href").substring(questionMarkIndex+1);
        } catch (Exception e) {
            schedulerParams = null;
        }
        
        // Get title.
        String title = null;
        try {
            title = href.getAttribute("title");
        } catch (Exception e) {
            title = null;
        }
        
        //
        //
        // Get parameters from image.
        //
        //
        
        // src (image source)
        String src = null;
        try {
            src = ((ConcreteElement)href.elements().nextElement()).getAttribute("src");
        } catch (Exception e) {
            src = null;
        }
        
        //
        //
        // Set parameters in input tag!!!.
        //
        //
        
        Input input = new Input();
        
        // Variable info.
        if(schedulerParams!=null)
            input.setOnClick("document.forms[0].action=document.forms[0].action+'?" + schedulerParams + "';");
        if(title!=null)
            input.setTitle(title);
        if(src!=null) 
            input.setSrc(src);
        // Fixed info.
        input.addAttribute("width", "15");
        input.setType("image");
        input.addAttribute("height", "15");
        input.setOnMouseUp("this.style.top=0;this.style.left=0;");
        input.setOnMouseDown("this.style.top=1;this.style.left=1;");
        input.setStyle("border: 0pt none ; position: relative; top: 0pt; left: 0pt;");
        
        return input;
    }

    /**
     * Creates a link to all day view, and an onclik submit operation to send all other form parameters.
     * @param date
     * @param tooltip
     * @param schedulerParams
     * @return
     */
    public static ConcreteElement insideTagRefactorLink(ConcreteElement link, String schedulerParams) {
        
        link.addAttribute("href", "");
        
        // Where:
        /*
            // Calendar internal change-view actions.
            function submitCalendarAction(form,schedulerParams) {
                  form.action=encodeURI(form.action+schedulerParams);
                  form.submit();
            }         
         */
        
        link.addAttribute("onclick", "submitCalendarAction(document.forms[0],'" + schedulerParams +"');return false;");
        
        return link;
    }
    

    public static ConcreteElement insideCreateButtonLink(ConcreteElement link, Date date, String action, Locale locale) { 
        
    	// eliminem el link directe
    	link.addAttribute("href", "");
    	
        String day = new MultipleDateFormat().parse(date, locale);
    	
        // posem el link cap a l'accio que afegeix l'event.
        link.addAttribute("onclick", "submitInPopUp('"+ action +"', forms[0], 'initialdate', '"+ day +"');return false;");
        
        return link;
        
    }

	public static int getViewYear(HttpServletRequest request) {
		Integer y = (Integer)request.getSession().getAttribute(SCHEDULER_VIEW_YEAR);
		if(y != null) {
			return y.intValue();
		} else return Calendar.getInstance().get(Calendar.YEAR);
	}
}