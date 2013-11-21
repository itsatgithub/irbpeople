package presentation.actions.oAlumni_job_position_typesSys;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Collections;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import utils.formbeans.ToStringComparator;
import utils.actions.NavigationFunctions;
import utils.actions.BOAdderFunctions;
import utils.formbeans.FormBeanManager;
import utils.formbeans.BOAdderFormBean;
import utils.userUtils.UserUtils;
import utils.Pair;
import utils.listFilter.ViewListConfiguration;
import utils.beanUtils.ExtendedBeanUtils;
import utils.filter.ListConfigurator;

import bussineslogic.controlers.UseCaseFacade;

import bussineslogic.objects.*;
import presentation.formbeans.objects.*;

	import presentation.formbeans.oAlumni_job_position_typesSys.*;


/**
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Action_modify_modify_page_alumni_job_position_types extends Action {
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {    	    	    

    	/** 1.  We obtain the user Information */
    	String usercode = UserUtils.getCurrentUsuarioId(request); 
    	Locale locale = UserUtils.getCurrentLocale(request); 
    	



			/** 2.  We obtain the initial form bean and we put it to a new FormBeanManager. */
		
Action_modify_modify_page_alumni_job_position_types_Form action_modify_modify_page_alumni_job_position_types_Form=(Action_modify_modify_page_alumni_job_position_types_Form) form;

FormBeanManager fbManager=new FormBeanManager(request, action_modify_modify_page_alumni_job_position_types_Form);			
		
	/** 3.  We use the business logic to modify the new item */
	
	Alumni_job_position_types alumni_job_position_types = UseCaseFacade.UpdateAlumni_job_position_types(usercode,(Alumni_job_position_types) fbManager.getPOJO("alumni_job_position_types_Form",Alumni_job_position_types.class));	
	
	/** 4.  In case that a BOAdder exists in the previous jsp, we update the list of values using the business logic. */
	
	

	/** 5.  We clean the current container */
	fbManager.cleanContainer();

	/** 6.  We return to the correct page (introducing some values to the request if necessary) */
	
	
/** 6.1.  We look if the current page is beeing opened in a popup window. */
boolean isPopUp = NavigationFunctions.isPopUpWindow(request);
/** 6.2.  We look for the forward to execute (we put the current object in case it is used in the next page) */

return NavigationFunctions.findForward(request, mapping, "success", "alumni_job_position_typescode="+alumni_job_position_types.getAlumni_job_position_typescode(), isPopUp, alumni_job_position_types.getCode(), alumni_job_position_types.toString());
	
	
		}
}