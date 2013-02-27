package presentation.actions.oCalendarSys;


import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.formbeans.oCalendarSys.Action_delete_event_Form;
import presentation.formbeans.oCalendarSys.Action_modify_calendar_info_Form;
import presentation.formbeans.oCalendarSys.Action_send_calendar_info_Form;
import presentation.formbeans.objects.Irbholidayinfo_Form;
import utils.actions.NavigationFunctions;
import utils.formbeans.FormBeanManager;
import utils.userUtils.UserUtils;
import bussineslogic.controlers.UseCaseFacade;
import bussineslogic.objects.Irbholidayinfo;


/**
 * 
 * @author Factory - JustInMind SL
 *
 */
public class Action_modify_calendar_info extends Action {
    
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
    	
    Action_modify_calendar_info_Form action_modify_calendar_info_Form=(Action_modify_calendar_info_Form) form;

    FormBeanManager fbManager=new FormBeanManager(request, action_modify_calendar_info_Form);
    
    Irbholidayinfo irbholidayinfo = UseCaseFacade.ObtainIrbholidayinfo(usercode, action_modify_calendar_info_Form.getIrbholidayinfocode());
    
	fbManager.cleanContainer();
    
    fbManager=new FormBeanManager(request, Action_send_calendar_info_Form.class);
	
    fbManager.setAttribute(irbholidayinfo, "irbholidayinfo_Form", Irbholidayinfo_Form.class);
	
	
	request.setAttribute("oCalendarSys__action_modify_calendar_info_Form", fbManager.getContainer());
		

		/** 4.  We navigate to the correct page. */		
		
		return NavigationFunctions.findForward(request, mapping, "success");
		
		
			}
}