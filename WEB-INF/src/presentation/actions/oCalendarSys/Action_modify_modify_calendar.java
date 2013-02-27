package presentation.actions.oCalendarSys;


import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.formbeans.oCalendarSys.Action_modify_modify_calendar_Form;
import presentation.formbeans.oCalendarSys.Action_modify_modify_event_Form;
import utils.actions.NavigationFunctions;
import utils.dateformat.MultipleDateFormat;
import utils.formbeans.FormBeanManager;
import utils.userUtils.UserUtils;
import bussineslogic.controlers.UseCaseFacade;
import bussineslogic.excepciones.HolidaysException;
import bussineslogic.objects.Irbholiday;


/**
 * 
 * @author Factory - JustInMind SL
 *
 */
public class Action_modify_modify_calendar extends Action {
    
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
    	
    Action_modify_modify_calendar_Form action_modify_modify_calendar_Form=(Action_modify_modify_calendar_Form) form;

    FormBeanManager fbManager=new FormBeanManager(request, action_modify_modify_calendar_Form);
    
    MultipleDateFormat mdf = new MultipleDateFormat();
    
    if(mdf.parse(action_modify_modify_calendar_Form.getInitialdate(), locale).
    		compareTo(mdf.parse(action_modify_modify_calendar_Form.getEnddate(), locale)) > 0 ) {
    	return NavigationFunctions.putActionError(request, mapping, "error.dates");
    	
    }
    
    try {
    	Irbholiday irbholiday = UseCaseFacade.UpdateIrbholiday(usercode, (Irbholiday)fbManager.getPOJO("irbholiday_Form", Irbholiday.class));
    } catch(HolidaysException he) {
    	return NavigationFunctions.putActionError(request, mapping, he.getMessage());
    }
	
    fbManager.cleanContainer();
    
	
    boolean isPopUp = NavigationFunctions.isPopUpWindow(request);


    return NavigationFunctions.findForward(request, mapping, "success", isPopUp);
		
		
			}
}