package presentation.actions.oWork_experienceSys;


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

	import presentation.formbeans.oWork_experienceSys.*;


/**
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Action_add_my_own_work_experience_new_new_page_work_experience extends Action {
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {    	    	    

    	/** 1.  We obtain the user Information */
    	String usercode = UserUtils.getCurrentUsuarioId(request); 
    	Locale locale = UserUtils.getCurrentLocale(request); 
    	


	
	/** 2.  We obtain the initial form bean and we put it to a new FormBeanManager. */
	
Action_add_my_own_work_experience_new_new_page_work_experience_Form action_add_my_own_work_experience_new_new_page_work_experience_Form=(Action_add_my_own_work_experience_new_new_page_work_experience_Form) form;

FormBeanManager fbManager=new FormBeanManager(request, action_add_my_own_work_experience_new_new_page_work_experience_Form);	
	
	/** 3.  We use the business logic to create the new item */
	Work_experience work_experienceDTO=(Work_experience) fbManager.getPOJO("work_experience_Form",Work_experience.class);
	Personal currentPersonal=UseCaseFacade.ObtainPersonalFromUser(usercode, UserUtils.getCurrentUsuario(request));
	work_experienceDTO.setWork_experience_personal(currentPersonal);
	Work_experience work_experience = UseCaseFacade.CreateWork_experience(usercode,work_experienceDTO);	
	
	/** 4.  In case that a BOAdder exists in the previous jsp, we update the list of values */
	
	

	/** 5.  We clean the current container */
	fbManager.cleanContainer();

	/** 6.  We return to the correct page (introducing some values to the request if necessary) */
	
	
/** 6.1.  We look if the current page is beeing opened in a popup window. */
boolean isPopUp = NavigationFunctions.isPopUpWindow(request);
/** 6.2.  We look for the forward to execute (we put the current object in case it is used in the next page) */

return NavigationFunctions.findForward(request, mapping, "success", "work_experiencecode="+work_experience.getWork_experiencecode(), isPopUp, work_experience.getCode(), work_experience.toString());
	
	
		}
}