package presentation.actions.oCalendarSys;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ecs.xhtml.li;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.formbeans.oCalendarSys.Action_add_event_Form;
import presentation.formbeans.oCalendarSys.Action_manage_calendar_Form;
import presentation.formbeans.objects.Irbholiday_Form;
import uicomponents.scheduler.ICalendar;
import uicomponents.scheduler.SchedulerFacadeForCommonControlsScheduler;
import utils.Pair;
import utils.actions.NavigationFunctions;
import utils.beanUtils.ExtendedBeanUtils;
import utils.dateformat.MultipleDateFormat;
import utils.formbeans.FormBeanManager;
import utils.listFilter.ViewListConfiguration;
import utils.userUtils.UserUtils;
import bussineslogic.controlers.UseCaseFacade;
import bussineslogic.objects.Irbholiday;


/**
 * 
 * @author Factory - JustInMind SL
 *
 */
public class Action_manage_calendar extends Action {
	
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {    	    	    

    	/** 1.  We obtain the user Information */
    	String usercode = UserUtils.getCurrentUsuarioId(request); 
    	Locale locale = UserUtils.getCurrentLocale(request); 
    	


			
		/** 2.  We set this page as a input point (see NavigationFunctions.setInputPoint function for more info). */
		NavigationFunctions.setReturnPoint(request, mapping.getPath());
		
		
		Action_manage_calendar_Form action_manage_calendar_Form = (Action_manage_calendar_Form) form;
		
		FormBeanManager fbManager = new FormBeanManager(request, action_manage_calendar_Form);
		
		/** 3.  We Prepare the values that will be used in the next jsp */
		/** 3.1.  We add the values of all (if any) of the support tables which will be used in the jsp (for selectables) to the request. */
		
		String s = (String)request.getSession().getAttribute("year");
		SchedulerFacadeForCommonControlsScheduler.facadePrepareScheduler(request, "sched_" + mapping.getPath(), null, s!=null&&!s.equals("")?Integer.parseInt(s):Calendar.getInstance().get(Calendar.YEAR));
		
		/** 3.2.  We add the values of the the view-lists (if any) to the request. */			
		
		
	/** 3.2.1.  We obtain the current ViewListConfiguration from the request */
	ViewListConfiguration filter = (ViewListConfiguration) request.getAttribute("viewListConfiguration");
	
	
	filter.getList_config_orderby().setAttribute("initialdate");
	filter.getList_config_orderby().setAsc("asc");
	
		
	/** 3.2.2.  We obtain the list of objects form the Business logic */
	
	int year = SchedulerFacadeForCommonControlsScheduler.getViewYear(request);
	
	// obtenir els dies marcats
	
		Pair<Integer,List<Irbholiday>> _list_irbholiday=UseCaseFacade.obtainIrbholidayFestiu(usercode, 
				year, filter.obtainListConfigurator(locale, false));
		
		Irbholiday limitVacances = UseCaseFacade.ObtainIrbholidayLimitVacances(usercode, new GregorianCalendar(year, 0, 1));

		Irbholiday_Form irbholiday_Form = null;
		
		if(limitVacances!=null) {
			
			irbholiday_Form = new Irbholiday_Form();
			ExtendedBeanUtils.copyPropertiesToFormBean(irbholiday_Form, limitVacances, locale);
		}
		
	
	/** 3.2.3.  We obtain copy the list of pojos to a list of FormBeans */
	
	List<Irbholiday_Form> viewlistElements =ExtendedBeanUtils.copyPropertiesToFormBean(_list_irbholiday.getSecond(), locale, Irbholiday_Form.class);
	
	List<Irbholiday_Form> list = null;
	
	if(irbholiday_Form != null) {
		
		list = new ArrayList<Irbholiday_Form>();
		
		for(Irbholiday_Form i : viewlistElements) list.add(i);
		
		list.add(irbholiday_Form);
	}
	
	/** 3.2.4.  Load data in the calendar. */
    SchedulerFacadeForCommonControlsScheduler.facadeLoadAppointments(request, (list!=null)?list:viewlistElements, "sched_" + mapping.getPath(), null, request.getLocale());
    
	
	
	request.setAttribute("viewlistElements", viewlistElements);
	
		fbManager.cleanContainer();
    
		fbManager=new FormBeanManager(request, Action_manage_calendar_Form.class);
	
		fbManager.setAttribute(limitVacances, "irbholiday_Form", Irbholiday_Form.class);
	
		
		request.setAttribute("oCalendarSys__action_manage_calendar_Form", fbManager.getContainer());
		
		
		
		/** 3.3.  We add the values of popup-selectables (if any) to the request: we need to put the value of the to-string method, because the FormBean may only store be the code */
		
		

		/** 4.  We navigate to the correct page. */		
		
		return NavigationFunctions.findForward(request, mapping, "success");
		
		
			}
 
	}