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

import bussineslogic.controlers.UseCaseFacade;
import bussineslogic.excepciones.ValidationFailedException;

import bussineslogic.objects.*;
import presentation.formbeans.objects.*;

import presentation.formbeans.oPersonalSys.*;


/**
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Action_change_state_from_validating_to_validated extends Action {
    
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
	
Action_change_state_from_validating_to_validated_Form action_change_state_from_validating_to_progress_Form=(Action_change_state_from_validating_to_validated_Form) form;

FormBeanManager fbManager=new FormBeanManager(request, action_change_state_from_validating_to_progress_Form);
	
	/** 4.  We obtain the initial POJO. */
	
	Personal personal = UseCaseFacade.ObtainPersonal(usercode, action_change_state_from_validating_to_progress_Form.getPersonalcode());
	
	/** 5.  In case a BOAdder exists in the following page, we reed the info from the BOAdder. */
	
	
	
	/** 6.  We Prepare the values that will be used in the next jsp */
	/** 6.1.  We create a new FormBeanManager (and delete the old one) */
	
	fbManager.cleanContainer();

fbManager=new FormBeanManager(request, Action_change_state_from_validating_to_validated_Form.class);




	/** 6.2.  We add the current POJO merged with the nested-pojo-form-bean to the manager. */
	
	fbManager.setAttribute(personal, "personal_Form", Personal_Form.class, action_change_state_from_validating_to_progress_Form.getPersonal_Form());
	
	/** We add the values of the first professional, if it exists to the formBean **/
	Action_change_state_from_validating_to_validated_Form currentContainer=(Action_change_state_from_validating_to_validated_Form)fbManager.getContainer();
	currentContainer.getProfessional_Form().getPosition().setPositioncode(action_change_state_from_validating_to_progress_Form.getProfessional_Form().getPosition().getPositioncode());
	currentContainer.getProfessional_Form().getResearch_group().setResearch_groupcode(action_change_state_from_validating_to_progress_Form.getProfessional_Form().getResearch_group().getResearch_groupcode());
	ListConfigurator professionalsListConf=new ListConfigurator();
	professionalsListConf.setOrderBy("professionalcode");
	professionalsListConf.setAsc("");
	List<Professional> professionals=UseCaseFacade.ObtainAllIprofessional_personalFromPersonal(usercode, personal.getCode(), professionalsListConf).second;
	if(professionals!=null && !professionals.isEmpty()){
		ExtendedBeanUtils.copyPropertiesToFormBean(currentContainer.getProfessional_Form(), professionals.iterator().next(), locale);
	}
	
	/** 6.3.  We add the container of the manager to the request. */
	
	request.setAttribute("oPersonalSys__action_change_state_from_validating_to_progress_Form", fbManager.getContainer());

	/** 6.4.  We add the values of all (if any) of the support tables which will be used in the jsp (for selectables) to the request. */	
	
	

	/** 6.5.  We add the values of the the view-lists (if any) to the request. */	
	
	
	/** 6.5.1.  We obtain the current ViewListConfiguration from the request */
	ViewListConfiguration filter = (ViewListConfiguration) request.getAttribute("viewListConfiguration");
	/** 6.5.2.  We obtain the list of objects form the Business logic */
	
			
			Pair<Integer,List<Funding_detail>> _list_funding_detail=UseCaseFacade.ObtainAllIfunding_detail_personalFromPersonal(usercode,action_change_state_from_validating_to_progress_Form.getPersonal_Form().getPersonalcode(),filter.obtainListConfigurator(locale, false));
	
	/** 6.5.3.  We obtain copy the list of pojos to a list of FormBeans */
	
	List<Funding_detail_Form> viewlistElements =ExtendedBeanUtils.copyPropertiesToFormBean(_list_funding_detail.getSecond(), locale, Funding_detail_Form.class);
	
	request.setAttribute("viewlistElements", viewlistElements);
	

	/** 6.6.  We add the values of the BOAdder (if any) to the request. */	
	
	
    	
	
	/** 6.7.  We add the values of popup-selectables (if any) to the request: we need to put the value of the to-string method, because the FormBean may only store be the code */
	
	/** 6.8 we add the possible validation errors **/
	try{
		UseCaseFacade.checkToProgressRequiredData(usercode, personal.getCode());
	} catch(ValidationFailedException e){
		/**This personal can not be sent to validate**/
		NavigationFunctions.putActionError(request, mapping, e.getParameters());
	}

	/** 7.  We navigate to the correct page. */
	
	return NavigationFunctions.findForward(request, mapping, "success");
	
	
		}
}