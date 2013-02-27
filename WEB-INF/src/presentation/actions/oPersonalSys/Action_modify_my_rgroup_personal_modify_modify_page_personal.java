package presentation.actions.oPersonalSys;


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

import bussineslogic.controlers.UseCase;
import bussineslogic.controlers.UseCaseFacade;

import bussineslogic.objects.*;
import presentation.formbeans.objects.*;

import presentation.formbeans.oPersonalSys.*;


/**
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Action_modify_my_rgroup_personal_modify_modify_page_personal extends Action {
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {    	    	    

    	/** 1.  We obtain the user Information */
    	String usercode = UserUtils.getCurrentUsuarioId(request); 
    	Locale locale = UserUtils.getCurrentLocale(request); 
    	



			/** 2.  We obtain the initial form bean and we put it to a new FormBeanManager. */
		
Action_modify_my_rgroup_personal_modify_modify_page_personal_Form action_modify_my_rgroup_personal_modify_modify_page_personal_Form=(Action_modify_my_rgroup_personal_modify_modify_page_personal_Form) form;

FormBeanManager fbManager=new FormBeanManager(request, action_modify_my_rgroup_personal_modify_modify_page_personal_Form);			
		
	/** 3.  We use the business logic to modify the new item */
	
	Personal personal = UseCaseFacade.UpdatePersonal(usercode,(Personal) fbManager.getPOJO("personal_Form",Personal.class));
	
	/** add the data of Professional **/
	Professional professionalDTO=(Professional)fbManager.getPOJO("professional_Form",Professional.class);
	if(professionalDTO.getCode().equals("") && (!professionalDTO.getPosition().getCode().equals("") || !professionalDTO.getResearch_group().getCode().equals(""))){
		professionalDTO.setProfessional_personal(personal);
		UseCaseFacade.CreateProfessional(usercode, professionalDTO);
	}
	else {
		//We put the verison to the DTO. Simplification: the data of professional will be overwritten if somebody has changed it while it was on the screen (concurrent modification).
		//TODO test concurrent modifications correctly
		professionalDTO.setVersion(UseCaseFacade.ObtainProfessional(usercode, professionalDTO.getCode()).getVersion());
		
		UseCaseFacade.UpdateProfessional(usercode, professionalDTO);
	}
	
	/** 4.  In case that a BOAdder exists in the previous jsp, we update the list of values using the business logic. */
	
	/** we test if the current personal is still a supervisor of the personal **/
	Usuario user=UseCaseFacade.ObtenerUsuario(usercode, usercode);
	if(!UseCaseFacade.isSupervisorOf(usercode, user.getPersonal().getCode(), personal.getCode())){
		return new ActionForward(NavigationFunctions.getReturnPoint(request));
	}

	/** 5.  We clean the current container */
	fbManager.cleanContainer();

	/** 6.  We return to the correct page (introducing some values to the request if necessary) */
	
	
/** 6.1.  We look if the current page is beeing opened in a popup window. */
boolean isPopUp = NavigationFunctions.isPopUpWindow(request);
/** 6.2.  We look for the forward to execute (we put the current object in case it is used in the next page) */

return NavigationFunctions.findForward(request, mapping, "success", "personalcode="+personal.getPersonalcode(), isPopUp, personal.getCode(), personal.toString());
	
	
		}
}