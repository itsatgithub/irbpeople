package presentation.actions.oCalendarSys;


import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.formbeans.oCalendarSys.Action_cancel_add_event_Form;
import presentation.formbeans.oCalendarSys.Action_cancel_send_calendar_info_Form;
import utils.actions.NavigationFunctions;
import utils.formbeans.FormBeanManager;
import utils.userUtils.UserUtils;


/**
 * 
 * @author Factory - JustInMind SL
 *
 */
public class Action_cancel_new_year extends Action {
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {    	    	    

    	
    	
	
	
    /** 4.1.  We look if the current page is beeing opened in a popup window. */
    boolean isPopUp = NavigationFunctions.isPopUpWindow(request);
    /** 4.2.  We look for the forward to execute. */
    return NavigationFunctions.findForward(request, mapping, "success",isPopUp);
		
		
			}
}