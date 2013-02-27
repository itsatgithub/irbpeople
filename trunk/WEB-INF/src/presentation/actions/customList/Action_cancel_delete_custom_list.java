package presentation.actions.customList;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.formbeans.oEducationSys.Action_cancel_delete_delete_page_education_Form;
import utils.actions.NavigationFunctions;
import utils.formbeans.FormBeanManager;
import utils.userUtils.UserUtils;

public class Action_cancel_delete_custom_list extends Action {
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {    	    	    

    	/** 1.  We obtain the user Information */
    	String usercode = UserUtils.getCurrentUsuarioId(request); 
    	Locale locale = UserUtils.getCurrentLocale(request); 
    	
	
		
/** 4.1.  We look if the current page is beeing opened in a popup window. */
boolean isPopUp = NavigationFunctions.isPopUpWindow(request);
/** 4.2.  We look for the forward to execute. */
return NavigationFunctions.findForward(request, mapping, "success", isPopUp);
		
		
			}
}