package presentation.actions.oPersonalSys;


import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.formbeans.oPersonalSys.Action_send_my_own_data_to_validate_Form;
import presentation.formbeans.objects.Academic_info_Form;
import presentation.formbeans.objects.Country_Form;
import presentation.formbeans.objects.Education_Form;
import presentation.formbeans.objects.Gender_Form;
import presentation.formbeans.objects.Grant_concession_Form;
import presentation.formbeans.objects.Nationality_Form;
import presentation.formbeans.objects.Personal_Form;
import presentation.formbeans.objects.Work_experience_Form;
import utils.Pair;
import utils.actions.NavigationFunctions;
import utils.beanUtils.ExtendedBeanUtils;
import utils.filter.ListConfigurator;
import utils.formbeans.FormBeanManager;
import utils.formbeans.ToStringComparator;
import utils.listFilter.ViewListConfiguration;
import utils.userUtils.UserUtils;
import bussineslogic.controlers.UseCaseFacade;
import bussineslogic.excepciones.ValidationFailedException;
import bussineslogic.objects.Academic_info;
import bussineslogic.objects.Country;
import bussineslogic.objects.Education;
import bussineslogic.objects.Gender;
import bussineslogic.objects.Grant_concession;
import bussineslogic.objects.Nationality;
import bussineslogic.objects.Personal;
import bussineslogic.objects.Professional;
import bussineslogic.objects.Work_experience;


/**
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Action_send_my_own_data_to_validate extends Action {
    
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
	
Action_send_my_own_data_to_validate_Form action_send_my_own_data_to_validate_Form=(Action_send_my_own_data_to_validate_Form) form;

FormBeanManager fbManager=new FormBeanManager(request, action_send_my_own_data_to_validate_Form);
	
	/** 4.  We obtain the initial POJO. */
	
Personal personal = UseCaseFacade.ObtainPersonalFromUser(usercode, UserUtils.getCurrentUsuario(request));
	
	/** 5.  In case a BOAdder exists in the following page, we reed the info from the BOAdder. */
	
	try{
		UseCaseFacade.checkToValidateRequiredData(usercode, personal.getCode());
	
	} catch(ValidationFailedException e){
		/**This personal can not be sent to validate**/
		return NavigationFunctions.putActionError(request, mapping, e.getParameters());
	}
	
	/** 6.  We Prepare the values that will be used in the next jsp */
	/** 6.1.  We create a new FormBeanManager (and delete the old one) */
	
	fbManager.cleanContainer();

fbManager=new FormBeanManager(request, Action_send_my_own_data_to_validate_Form.class);




	/** 6.2.  We add the current POJO merged with the nested-pojo-form-bean to the manager. */
	
	fbManager.setAttribute(personal, "personal_Form", Personal_Form.class, action_send_my_own_data_to_validate_Form.getPersonal_Form());
	
	/** We add the values of the first professional, if it exists to the formBean **/
	Action_send_my_own_data_to_validate_Form currentContainer=(Action_send_my_own_data_to_validate_Form)fbManager.getContainer();
	currentContainer.getProfessional_Form().getPosition().setPositioncode(action_send_my_own_data_to_validate_Form.getProfessional_Form().getPosition().getPositioncode());
	currentContainer.getProfessional_Form().getResearch_group().setResearch_groupcode(action_send_my_own_data_to_validate_Form.getProfessional_Form().getResearch_group().getResearch_groupcode());
	ListConfigurator professionalsListConf=new ListConfigurator();
	professionalsListConf.setOrderBy("professionalcode");
	professionalsListConf.setAsc("");
	List<Professional> professionals=UseCaseFacade.ObtainAllIprofessional_personalFromPersonal(usercode, personal.getCode(), professionalsListConf).second;
	if(professionals!=null && !professionals.isEmpty()){
		ExtendedBeanUtils.copyPropertiesToFormBean(currentContainer.getProfessional_Form(), professionals.iterator().next(), locale);
	}
	
	/** 6.3.  We add the container of the manager to the request. */
	
	request.setAttribute("oPersonalSys__action_send_my_own_data_to_validate_Form", fbManager.getContainer());

	/** 6.4.  We add the values of all (if any) of the support tables which will be used in the jsp (for selectables) to the request. */	
	
	
/** 6.4.1.  We obtain the values form the business logic. */

Pair<Integer, List<Gender>> _selec_gender= UseCaseFacade.ObtainAllGender(usercode,  new ListConfigurator());
/** 6.4.2.  We copy the values to a list of formbeans. */

List<Gender_Form> _selec_gender_Form = ExtendedBeanUtils.copyPropertiesToFormBean(_selec_gender.getSecond(), locale, Gender_Form.class);
/** 6.4.3.  We sort the list. */
Collections.sort(_selec_gender_Form, new ToStringComparator());
/** 6.4.4.  We put the values to the request, so the jsp can get them. */

request.setAttribute("_selec_gender", _selec_gender_Form);
/** 6.4.5.  We obtain the values form the business logic. */

Pair<Integer, List<Country>> _selec_country= UseCaseFacade.ObtainAllCountry(usercode,  new ListConfigurator());
/** 6.4.6.  We copy the values to a list of formbeans. */

List<Country_Form> _selec_country_Form = ExtendedBeanUtils.copyPropertiesToFormBean(_selec_country.getSecond(), locale, Country_Form.class);
/** 6.4.7.  We sort the list. */
Collections.sort(_selec_country_Form, new ToStringComparator());
/** 6.4.8.  We put the values to the request, so the jsp can get them. */

request.setAttribute("_selec_country", _selec_country_Form);
/** 6.4.9.  We obtain the values form the business logic. */

Pair<Integer, List<Nationality>> _selec_nationality= UseCaseFacade.ObtainAllNationality(usercode,  new ListConfigurator());
/** 6.4.10.  We copy the values to a list of formbeans. */

List<Nationality_Form> _selec_nationality_Form = ExtendedBeanUtils.copyPropertiesToFormBean(_selec_nationality.getSecond(), locale, Nationality_Form.class);
/** 6.4.11.  We sort the list. */
Collections.sort(_selec_nationality_Form, new ToStringComparator());
/** 6.4.12.  We put the values to the request, so the jsp can get them. */

request.setAttribute("_selec_nationality", _selec_nationality_Form);

	/** 6.5.  We add the values of the the view-lists (if any) to the request. */	
	
	
	/** 6.5.1.  We obtain the current ViewListConfiguration from the request */
	ViewListConfiguration filter = new ViewListConfiguration();
	/** 6.5.2.  We obtain the list of objects form the Business logic */
	Pair<Integer,List<Work_experience>> _list_work_experience=UseCaseFacade.ObtainAllIwork_experience_personalFromPersonal(usercode,personal.getCode(),filter.obtainListConfigurator(locale, false));
	/** 6.5.3.  We obtain copy the list of pojos to a list of FormBeans */
	List<Work_experience_Form> work_experience_viewlistElements =ExtendedBeanUtils.copyPropertiesToFormBean(_list_work_experience.getSecond(), locale, Work_experience_Form.class);
	request.setAttribute("work_experience_viewlistElements", work_experience_viewlistElements);
	
	
	/** 6.5.2.  We obtain the list of objects form the Business logic */
	Pair<Integer,List<Grant_concession>> _list_grant_concesion=UseCaseFacade.ObtainAllIgrant_concession_personalFromPersonal(usercode,personal.getCode(),filter.obtainListConfigurator(locale, false));
	/** 6.5.3.  We obtain copy the list of pojos to a list of FormBeans */
	List<Grant_concession_Form> grantConcesion_viewlistElements =ExtendedBeanUtils.copyPropertiesToFormBean(_list_grant_concesion.getSecond(), locale, Grant_concession_Form.class);
	request.setAttribute("grant_concession_viewlistElements", grantConcesion_viewlistElements);
	
	/** 6.5.2.  We obtain the list of objects form the Business logic */
	Pair<Integer,List<Academic_info>> _list_academic_info=UseCaseFacade.ObtainAllIacademic_info_personalFromPersonal(usercode,personal.getCode(),filter.obtainListConfigurator(locale, false));
	/** 6.5.3.  We obtain copy the list of pojos to a list of FormBeans */
	List<Academic_info_Form> academicInfo_viewlistElements =ExtendedBeanUtils.copyPropertiesToFormBean(_list_academic_info.getSecond(), locale, Academic_info_Form.class);
	request.setAttribute("academic_info_viewlistElements", academicInfo_viewlistElements);
	

	/** 6.5.2.  We obtain the list of objects form the Business logic */
	Pair<Integer,List<Education>> _list_education=UseCaseFacade.ObtainAllIeducation_personalFromPersonal(usercode,personal.getCode(),filter.obtainListConfigurator(locale, false));
	/** 6.5.3.  We obtain copy the list of pojos to a list of FormBeans */
	List<Education_Form> education_viewlistElements =ExtendedBeanUtils.copyPropertiesToFormBean(_list_education.getSecond(), locale, Education_Form.class);
	request.setAttribute("education_viewlistElements", education_viewlistElements);
	

	/** 6.6.  We add the values of the BOAdder (if any) to the request. */	
	
	
    	
	
	/** 6.7.  We add the values of popup-selectables (if any) to the request: we need to put the value of the to-string method, because the FormBean may only store be the code */
	
	
	
	/** 7.  We navigate to the correct page. */
	
	return NavigationFunctions.findForward(request, mapping, "success");
	
	
		}
}