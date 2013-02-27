package presentation.actions.oCalendarSys;


import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.formbeans.oCalendarSys.Action_delete_calendar_Form;
import presentation.formbeans.oCalendarSys.Action_delete_event_Form;
import presentation.formbeans.objects.Irbholiday_Form;
import utils.actions.NavigationFunctions;
import utils.beanUtils.ExtendedBeanUtils;
import utils.formbeans.FormBeanManager;
import utils.userUtils.UserUtils;
import bussineslogic.controlers.UseCase;
import bussineslogic.controlers.UseCaseFacade;
import bussineslogic.objects.Irbholiday;


/**
 * 
 * @author Factory - JustInMind SL
 *
 */
public class Action_delete_calendar extends Action {
    
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
    	
    Action_delete_calendar_Form action_delete_event_Form=(Action_delete_calendar_Form) form;

    FormBeanManager fbManager=new FormBeanManager(request, action_delete_event_Form);
    
    Irbholiday irbholiday = UseCaseFacade.ObtainIrbholiday(usercode, action_delete_event_Form.getIrbholidaycode());
    
    
    
	Irbholiday_Form irbholiday_Form = action_delete_event_Form.getIrbholiday_Form();
	ExtendedBeanUtils.copyPropertiesToFormBean(irbholiday_Form, irbholiday, locale);
	irbholiday_Form.setType(Irbholiday_Form.getType(irbholiday_Form.getType(), locale));
    
	fbManager.cleanContainer();
    
    fbManager=new FormBeanManager(request, Action_delete_event_Form.class);
	
    fbManager.setAttribute(irbholiday, "irbholiday_Form", Irbholiday_Form.class, irbholiday_Form);
	
	
	request.setAttribute("oCalendarSys__action_delete_event_Form", fbManager.getContainer());
		

		/** 4.  We navigate to the correct page. */		
		
		return NavigationFunctions.findForward(request, mapping, "success");
		
		
			}
}