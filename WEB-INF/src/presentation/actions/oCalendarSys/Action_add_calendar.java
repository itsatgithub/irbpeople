package presentation.actions.oCalendarSys;


import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.formbeans.oCalendarSys.Action_add_calendar_Form;
import presentation.formbeans.objects.Irbholiday_Form;
import presentation.formbeans.objects.Irbholidayinfo_Form;
import presentation.formbeans.objects.Personal_IDForm;
import presentation.formbeans.objects.TypeIrbholiday_Form;
import uicomponents.scheduler.SchedulerFacadeForCommonControlsScheduler;
import utils.actions.NavigationFunctions;
import utils.beanUtils.ExtendedBeanUtils;
import utils.filter.ListConfigurator;
import utils.formbeans.FormBeanManager;
import utils.userUtils.UserUtils;
import bussineslogic.controlers.UseCaseFacade;
import bussineslogic.objects.Irbholiday;
import bussineslogic.objects.Irbholidayinfo;
import bussineslogic.objects.Personal;
import bussineslogic.objects.Professional;


/**
 * 
 * @author Factory - JustInMind SL
 *
 */
public class Action_add_calendar extends Action {
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {    	    	    

    	/** 1.  We obtain the user Information */
    	String usercode = UserUtils.getCurrentUsuarioId(request); 
    	Locale locale = UserUtils.getCurrentLocale(request); 
    	
    	
    	/** 2.  We set this page as a input point (see NavigationFunctions.setInputPoint function for more info). */
    	NavigationFunctions.setInputPoint(request, mapping.getPath());
    	

    	/** 3.  We obtain the initial form bean and we put it to a new FormBeanManager. */
    	
    Action_add_calendar_Form action_add_calendar_Form=(Action_add_calendar_Form) form;

    FormBeanManager fbManager=new FormBeanManager(request, action_add_calendar_Form);
    
	int yearCal = SchedulerFacadeForCommonControlsScheduler.getViewYear(request);
	
	Personal personal = UseCaseFacade.ObtainPersonal(usercode, action_add_calendar_Form.getPersonalcode());
	
	Irbholiday irbholiday = new Irbholiday();
	irbholiday.setPersonal(personal);
    
    Irbholidayinfo irbholidayinfo = UseCaseFacade.ObtainIrbholidayinfoFromPersonal(usercode, 
    		action_add_calendar_Form.getPersonalcode(), new GregorianCalendar(yearCal, 0, 1));
    
    List<Professional> professionals = UseCaseFacade.ObtainAllIprofessional_personalFromPersonal(usercode, 
    		action_add_calendar_Form.getPersonalcode(), new ListConfigurator()).getSecond();
    
    Personal supervisor = new Personal();
    
    for(Professional p : professionals) {
    	if(p.getEnd_date()==null || p.getEnd_date().after(new Date())) {
    		if(p.getResearch_group()!=null && p.getResearch_group().getSupervisor()!=null) {
    			supervisor = p.getResearch_group().getSupervisor();
    			break;
    		}
    	}
    }
    
    Personal_IDForm supervisor_form = action_add_calendar_Form.getSupervisor();
    
	ExtendedBeanUtils.copyPropertiesToFormBean(supervisor_form, supervisor, locale);
	
	Irbholiday_Form irbholiday_Form = action_add_calendar_Form.getIrbholiday_Form();
	
	ExtendedBeanUtils.copyPropertiesToFormBean(irbholiday_Form, irbholiday, locale);
	
	irbholiday_Form.setEnddate(irbholiday_Form.getInitialdate());
    
	fbManager.cleanContainer();
    
    fbManager=new FormBeanManager(request, Action_add_calendar_Form.class);
	
    fbManager.setAttribute(null, "irbholiday_Form", Irbholiday_Form.class, irbholiday_Form);
    fbManager.setAttribute(null, "supervisor", Personal_IDForm.class, action_add_calendar_Form.getSupervisor());
    fbManager.setAttribute(irbholidayinfo, "irbholidayinfo_Form", Irbholidayinfo_Form.class);
	
	
	request.setAttribute("oCalendarSys__action_add_calendar_Form", fbManager.getContainer());
	
	
	List<TypeIrbholiday_Form> _selec_type = Irbholiday_Form.getTypes(locale);
	
	request.setAttribute("_selec_type", _selec_type);
		

		/** 4.  We navigate to the correct page. */		
		
		return NavigationFunctions.findForward(request, mapping, "success");
		
		
			}

}