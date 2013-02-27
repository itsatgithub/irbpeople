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

import bussineslogic.objects.*;
import presentation.formbeans.objects.*;

import presentation.formbeans.oPersonalSys.*;


/**
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Action_modify_my_rgroup_personal extends Action {
    
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
	
Action_modify_my_rgroup_personal_Form action_modify_my_rgroup_personal_Form=(Action_modify_my_rgroup_personal_Form) form;

FormBeanManager fbManager=new FormBeanManager(request, action_modify_my_rgroup_personal_Form);
	
	/** 4.  We obtain the initial POJO. */
	
	Personal personal = UseCaseFacade.ObtainPersonal(usercode, action_modify_my_rgroup_personal_Form.getPersonalcode());
	
	/** 5.  In case a BOAdder exists in the following page, we reed the info from the BOAdder. */
	
	
	
	/** 6.  We Prepare the values that will be used in the next jsp */
	/** 6.1.  We create a new FormBeanManager (and delete the old one) */
	
	fbManager.cleanContainer();

fbManager=new FormBeanManager(request, Action_modify_my_rgroup_personal_Form.class);




	/** 6.2.  We add the current POJO merged with the nested-pojo-form-bean to the manager. */
	
	fbManager.setAttribute(personal, "personal_Form", Personal_Form.class, action_modify_my_rgroup_personal_Form.getPersonal_Form());
	
	/** We add the values of the first professional, if it exists to the formBean **/
	Action_modify_my_rgroup_personal_Form currentContainer=(Action_modify_my_rgroup_personal_Form)fbManager.getContainer();
	currentContainer.getProfessional_Form().getPosition().setPositioncode(action_modify_my_rgroup_personal_Form.getProfessional_Form().getPosition().getPositioncode());
	currentContainer.getProfessional_Form().getResearch_group().setResearch_groupcode(action_modify_my_rgroup_personal_Form.getProfessional_Form().getResearch_group().getResearch_groupcode());
	ListConfigurator professionalsListConf=new ListConfigurator();
	professionalsListConf.setOrderBy("professionalcode");
	professionalsListConf.setAsc("");
	List<Professional> professionals=UseCaseFacade.ObtainAllIprofessional_personalFromPersonal(usercode, personal.getCode(), professionalsListConf).second;
	if(professionals!=null && !professionals.isEmpty()){
		ExtendedBeanUtils.copyPropertiesToFormBean(currentContainer.getProfessional_Form(), professionals.iterator().next(), locale);
	}
	
	/** 6.3.  We add the container of the manager to the request. */
	
	request.setAttribute("oPersonalSys__action_modify_my_rgroup_personal_Form", fbManager.getContainer());

	/** 6.4.  We add the values of all (if any) of the support tables which will be used in the jsp (for selectables) to the request. */	
	
	/**6.4.1We add the values to the select tables of the professional fields**/
	/** 6.4.1.1  We obtain the values form the business logic. */

	Pair<Integer, List<Personal>> _selec_personal= UseCaseFacade.ObtainAllPersonal(usercode,  new ListConfigurator());
	/** 6.4.1.2.  We copy the values to a list of formbeans. */

	List<Personal_Form> _selec_personal_Form = ExtendedBeanUtils.copyPropertiesToFormBean(_selec_personal.getSecond(), locale, Personal_Form.class);
	/** 6.4.1.3.  We sort the list. */
	Collections.sort(_selec_personal_Form, new ToStringComparator());
	/** 6.4.1.4.  We put the values to the request, so the jsp can get them. */

	request.setAttribute("_selec_personal", _selec_personal_Form);
	/** 6.4.1.5.  We obtain the values form the business logic. */

	Pair<Integer, List<Type_of_contract>> _selec_type_of_contract= UseCaseFacade.ObtainAllType_of_contract(usercode,  new ListConfigurator());
	/** 6.4.1.6.  We copy the values to a list of formbeans. */

	List<Type_of_contract_Form> _selec_type_of_contract_Form = ExtendedBeanUtils.copyPropertiesToFormBean(_selec_type_of_contract.getSecond(), locale, Type_of_contract_Form.class);
	/** 6.4.1.7.  We sort the list. */
	Collections.sort(_selec_type_of_contract_Form, new ToStringComparator());
	/** 6.4.1.8.  We put the values to the request, so the jsp can get them. */

	request.setAttribute("_selec_type_of_contract", _selec_type_of_contract_Form);
	
	/** 6.4.1.9.  We obtain the values form the business logic. */
	Pair<Integer, List<Location>> _selec_location= UseCaseFacade.ObtainAllLocation(usercode,  new ListConfigurator());
	/** 6.4.1.10.  We copy the values to a list of formbeans. */
	List<Location_Form> _selec_location_Form = ExtendedBeanUtils.copyPropertiesToFormBean(_selec_location.getSecond(), locale, Location_Form.class);
	/** 6.4.1.11.  We sort the list. */
	Collections.sort(_selec_location_Form, new ToStringComparator());
	/** 6.4.1.12.  We put the values to the request, so the jsp can get them. */
	request.setAttribute("_selec_location", _selec_location_Form);
	
	/** 6.4.1.13.  We obtain the values form the business logic. */

	Pair<Integer, List<Payroll_institution>> _selec_payroll_institution= UseCaseFacade.ObtainAllPayroll_institution(usercode,  new ListConfigurator());
	/** 6.4.1.14.  We copy the values to a list of formbeans. */

	List<Payroll_institution_Form> _selec_payroll_institution_Form = ExtendedBeanUtils.copyPropertiesToFormBean(_selec_payroll_institution.getSecond(), locale, Payroll_institution_Form.class);
	/** 6.4.1.15.  We sort the list. */
	Collections.sort(_selec_payroll_institution_Form, new ToStringComparator());
	/** 6.4.1.16.  We put the values to the request, so the jsp can get them. */

	request.setAttribute("_selec_payroll_institution", _selec_payroll_institution_Form);
	/** 6.4.1.17.  We obtain the values form the business logic. */

	
	/** 6.4.1.21.  We obtain the values form the business logic. */

	Pair<Integer, List<Research_group>> _selec_research_group= UseCaseFacade.ObtainAllResearch_group(usercode,  new ListConfigurator());
	/** 6.4.1.22.  We copy the values to a list of formbeans. */

	List<Research_group_Form> _selec_research_group_Form = ExtendedBeanUtils.copyPropertiesToFormBean(_selec_research_group.getSecond(), locale, Research_group_Form.class);
	/** 6.4.1.23.  We sort the list. */
	Collections.sort(_selec_research_group_Form, new ToStringComparator());
	/** 6.4.1.24.  We put the values to the request, so the jsp can get them. */

	request.setAttribute("_selec_research_group", _selec_research_group_Form);
	/** 6.4.1.25.  We obtain the values form the business logic. */

	Pair<Integer, List<Position>> _selec_position= UseCaseFacade.ObtainAllPosition(usercode,  new ListConfigurator());
	/** 6.4.1.26.  We copy the values to a list of formbeans. */

	List<Position_Form> _selec_position_Form = ExtendedBeanUtils.copyPropertiesToFormBean(_selec_position.getSecond(), locale, Position_Form.class);
	/** 6.4.1.27.  We sort the list. */
	Collections.sort(_selec_position_Form, new ToStringComparator());
	/** 6.4.1.28.  We put the values to the request, so the jsp can get them. */

	request.setAttribute("_selec_position", _selec_position_Form);
	

	/** 6.5.  We add the values of the the view-lists (if any) to the request. */	
	
	

	/** 6.6.  We add the values of the BOAdder (if any) to the request. */	
	
	
    	
	
	/** 6.7.  We add the values of popup-selectables (if any) to the request: we need to put the value of the to-string method, because the FormBean may only store be the code */
	
	
	
	/** 7.  We navigate to the correct page. */
	
	return NavigationFunctions.findForward(request, mapping, "success");
	
	
		}
}