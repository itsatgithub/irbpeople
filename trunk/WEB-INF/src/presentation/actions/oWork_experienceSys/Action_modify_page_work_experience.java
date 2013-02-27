package presentation.actions.oWork_experienceSys;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.formbeans.oWork_experienceSys.Action_modify_page_work_experience_Form;
import presentation.formbeans.objects.Area_Form;
import presentation.formbeans.objects.Country_Form;
import presentation.formbeans.objects.Position_Form;
import presentation.formbeans.objects.Type_of_institution_Form;
import presentation.formbeans.objects.Work_experience_Form;
import utils.Pair;
import utils.actions.NavigationFunctions;
import utils.beanUtils.ExtendedBeanUtils;
import utils.filter.ListConfigurator;
import utils.formbeans.FormBeanManager;
import utils.formbeans.ToStringComparator;
import utils.userUtils.UserUtils;
import bussineslogic.controlers.UseCaseFacade;
import bussineslogic.objects.Area;
import bussineslogic.objects.Country;
import bussineslogic.objects.Position;
import bussineslogic.objects.Type_of_institution;
import bussineslogic.objects.Work_experience;

/**
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Action_modify_page_work_experience extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		/** 1.  We obtain the user Information */
		String usercode = UserUtils.getCurrentUsuarioId(request);
		Locale locale = UserUtils.getCurrentLocale(request);

		/** 2.  We set this page as a input point (see NavigationFunctions.setInputPoint function for more info). */
		NavigationFunctions.setInputPoint(request, mapping.getPath());

		/** 3.  We obtain the initial form bean and we put it to a new FormBeanManager. */

		Action_modify_page_work_experience_Form action_modify_page_work_experience_Form = (Action_modify_page_work_experience_Form) form;

		FormBeanManager fbManager = new FormBeanManager(request, action_modify_page_work_experience_Form);

		/** 4.  We obtain the initial POJO. */

		Work_experience work_experience = UseCaseFacade.ObtainWork_experience(usercode, action_modify_page_work_experience_Form.getWork_experiencecode());

		/** 5.  In case a BOAdder exists in the following page, we reed the info from the BOAdder. */

		/** 6.  We Prepare the values that will be used in the next jsp */
		/** 6.1.  We create a new FormBeanManager (and delete the old one) */

		fbManager.cleanContainer();

		fbManager = new FormBeanManager(request, Action_modify_page_work_experience_Form.class);

		/** 6.2.  We add the current POJO merged with the nested-pojo-form-bean to the manager. */

		fbManager.setAttribute(
				work_experience,
				"work_experience_Form",
				Work_experience_Form.class,
				action_modify_page_work_experience_Form.getWork_experience_Form());

		/** 6.3.  We add the container of the manager to the request. */

		request.setAttribute("oWork_experienceSys__action_modify_page_work_experience_Form", fbManager.getContainer());

		/** 6.4.  We add the values of all (if any) of the support tables which will be used in the jsp (for selectables) to the request. */

		/** 6.4.1.  We obtain the values form the business logic. */

		//Pair<Integer, List<Personal>> _selec_personal= UseCaseFacade.ObtainAllPersonal(usercode,  new ListConfigurator());
		///** 6.4.2.  We copy the values to a list of formbeans. */
		//
		//List<Personal_Form> _selec_personal_Form = ExtendedBeanUtils.copyPropertiesToFormBean(_selec_personal.getSecond(), locale, Personal_Form.class);
		///** 6.4.3.  We sort the list. */
		//Collections.sort(_selec_personal_Form, new ToStringComparator());
		///** 6.4.4.  We put the values to the request, so the jsp can get them. */
		//
		//request.setAttribute("_selec_personal", _selec_personal_Form);
		/** 6.4.5.  We obtain the values form the business logic. */

		Pair<Integer, List<Type_of_institution>> _selec_type_of_institution = UseCaseFacade.ObtainAllType_of_institution(usercode, new ListConfigurator());
		/** 6.4.6.  We copy the values to a list of formbeans. */

		List<Type_of_institution_Form> _selec_type_of_institution_Form =
				ExtendedBeanUtils.copyPropertiesToFormBean(_selec_type_of_institution.getSecond(), locale, Type_of_institution_Form.class);
		/** 6.4.7.  We sort the list. */
		Collections.sort(_selec_type_of_institution_Form, new ToStringComparator());
		/** 6.4.8.  We put the values to the request, so the jsp can get them. */

		request.setAttribute("_selec_type_of_institution", _selec_type_of_institution_Form);
		/** 6.4.9.  We obtain the values form the business logic. */

		Pair<Integer, List<Area>> _selec_area = UseCaseFacade.ObtainAllArea(usercode, new ListConfigurator());
		/** 6.4.10.  We copy the values to a list of formbeans. */

		List<Area_Form> _selec_area_Form = ExtendedBeanUtils.copyPropertiesToFormBean(_selec_area.getSecond(), locale, Area_Form.class);
		/** 6.4.11.  We sort the list. */
		Collections.sort(_selec_area_Form, new ToStringComparator());
		/** 6.4.12.  We put the values to the request, so the jsp can get them. */

		request.setAttribute("_selec_area", _selec_area_Form);
		/** 6.4.13.  We obtain the values form the business logic. */

		Pair<Integer, List<Position>> _selec_position = UseCaseFacade.ObtainAllPosition(usercode, new ListConfigurator());
		/** 6.4.14.  We copy the values to a list of formbeans. */

		List<Position_Form> _selec_position_Form = ExtendedBeanUtils.copyPropertiesToFormBean(_selec_position.getSecond(), locale, Position_Form.class);
		/** 6.4.15.  We sort the list. */
		Collections.sort(_selec_position_Form, new ToStringComparator());
		/** 6.4.16.  We put the values to the request, so the jsp can get them. */

		request.setAttribute("_selec_position", _selec_position_Form);
		/** 6.4.17.  We obtain the values form the business logic. */

		Pair<Integer, List<Country>> _selec_country = UseCaseFacade.ObtainAllCountry(usercode, new ListConfigurator());
		/** 6.4.18.  We copy the values to a list of formbeans. */

		List<Country_Form> _selec_country_Form = ExtendedBeanUtils.copyPropertiesToFormBean(_selec_country.getSecond(), locale, Country_Form.class);
		/** 6.4.19.  We sort the list. */
		Collections.sort(_selec_country_Form, new ToStringComparator());
		Country_Form countryForm = new Country_Form();
		ExtendedBeanUtils.copyPropertiesToFormBean(countryForm, UseCaseFacade.ObtainDefaultCountry(usercode), locale);
		_selec_country_Form.add(countryForm);

		/** 6.4.20.  We put the values to the request, so the jsp can get them. */

		request.setAttribute("_selec_country", _selec_country_Form);

		/** 6.5.  We add the values of the the view-lists (if any) to the request. */

		/** 6.6.  We add the values of the BOAdder (if any) to the request. */

		/** 6.7.  We add the values of popup-selectables (if any) to the request: we need to put the value of the to-string method, because the FormBean may only store be the code */

		/** 7.  We navigate to the correct page. */

		return NavigationFunctions.findForward(request, mapping, "success");

	}
}