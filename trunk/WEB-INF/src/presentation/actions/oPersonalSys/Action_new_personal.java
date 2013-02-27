package presentation.actions.oPersonalSys;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Collections;
import java.util.Map;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.justinmind.usermanagement.entity.Language;

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
import presentation.actions.personalphoto.GetImageAction;
import presentation.formbeans.objects.*;

import presentation.formbeans.oPersonalSys.*;


/**
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Action_new_personal extends Action {

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

		Action_new_personal_Form action_new_personal_Form=(Action_new_personal_Form) form;

		FormBeanManager fbManager=new FormBeanManager(request, action_new_personal_Form);

		/** 4.  We obtain the initial POJO. */

		Personal personal = null;

		/** 5.  In case a BOAdder exists in the following page, we reed the info from the BOAdder. */

		BOAdderFormBean<Child_Form> boAdderFormBean = action_new_personal_Form.getPersonal_child();

		List<Child> boAdderList = null;
		try {
			boAdderList = fbManager.getPOJOBOAdderList("personal_child");

		} catch (NullPointerException e) {}

		if (boAdderFormBean.getOperation().equals(boAdderFormBean.LOAD)) {

			boAdderList = new ArrayList<Child>();

			boAdderFormBean.getNewFormBean().getChild().getChild_personal().setPersonalcode(action_new_personal_Form.getPersonalcode());

		} else if(boAdderFormBean.getOperation().equals(boAdderFormBean.ADD) && boAdderList!=null) {

			boAdderList = fbManager.getPOJOBOAdderList("personal_child");

			Child newChildTO = (Child) fbManager.getPOJOBOAdderNew("personal_child", Child.class);

			newChildTO.setCode(BOAdderFunctions.generateCode());

			newChildTO.setChild_personal(UseCaseFacade.ObtainPersonal(usercode, boAdderFormBean.getNewFormBean().getChild().getChild_personal().getPersonalcode()));

			if(newChildTO.getBirth_date() == null || newChildTO.getBirth_date().equals("")) {

				Map<String, List<String>> parameter = new HashMap<String, List<String>>();
				List<String> l = new ArrayList<String>();l.add("child.birth_date");parameter.put("propertyError.required_attribute", l);
				NavigationFunctions.putActionError(request, mapping, parameter);

			} else {

				boAdderList.add(newChildTO);
			}

			boAdderFormBean.setNewFormBean(new Child_Form());

			boAdderFormBean.getNewFormBean().getChild().getChild_personal().setPersonalcode(action_new_personal_Form.getPersonalcode());

		} else if(boAdderFormBean.getOperation().equals(boAdderFormBean.DELETE) && boAdderList!=null){

			boAdderList = fbManager.getPOJOBOAdderList("personal_child");

			for (Child to_delete : boAdderList) {
				if(to_delete.getCode().equals(boAdderFormBean.getRemoveCode())) {
					boAdderList.remove(to_delete);
					break;
				}
			}

		} else {

			Pair<Integer,List<Child>> listChild = 
				UseCaseFacade.ObtainAllIchild_personalFromPersonal(usercode, 
						action_new_personal_Form.getPersonalcode(), new ListConfigurator());

			boAdderList = listChild.getSecond();

			boAdderFormBean.getNewFormBean().getChild().getChild_personal().setPersonalcode(action_new_personal_Form.getPersonalcode());
		}

		/** 6.  We Prepare the values that will be used in the next jsp */
		/** 6.1.  We create a new FormBeanManager (and delete the old one) */

		fbManager.cleanContainer();

		fbManager=new FormBeanManager(request, Action_new_personal_Form.class);




		/** 6.2.  We add the current POJO merged with the nested-pojo-form-bean to the manager. */

		fbManager.setAttribute(personal, "personal_Form", Personal_Form.class, action_new_personal_Form.getPersonal_Form());

		/** 6.3.  We add the container of the manager to the request. */

		request.setAttribute("oPersonalSys__action_new_personal_Form", fbManager.getContainer());

		String photocode=GetImageAction.NO_PHOTO;
		request.setAttribute("_personalPhoto_img", photocode);

		/** 6.4.  We add the values of all (if any) of the support tables which will be used in the jsp (for selectables) to the request. */

		List<Language> _selec_language= UseCaseFacade.ObtenerIdiomas();
		/** 6.4.2.  We copy the values to a list of formbeans. */

		List<LanguageForm> _selec_language_Form = ExtendedBeanUtils.copyPropertiesToFormBean(_selec_language, locale, LanguageForm.class);
		/** 6.4.3.  We sort the list. */
		Collections.sort(_selec_language_Form, new ToStringComparator());
		/** 6.4.4.  We put the values to the request, so the jsp can get them. */

		request.setAttribute("_selec_language", _selec_language_Form);

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
		Country_Form countryForm=new Country_Form();
		ExtendedBeanUtils.copyPropertiesToFormBean(countryForm, UseCaseFacade.ObtainDefaultCountry(usercode), locale);
		_selec_country_Form.add(countryForm);

		/** 6.4.8.  We put the values to the request, so the jsp can get them. */

		request.setAttribute("_selec_country", _selec_country_Form);
		/** 6.4.9.  We obtain the values form the business logic. */

		Pair<Integer, List<Nationality>> _selec_nationality= UseCaseFacade.ObtainAllNationality(usercode,  new ListConfigurator());
		/** 6.4.10.  We copy the values to a list of formbeans. */

		List<Nationality_Form> _selec_nationality_Form = ExtendedBeanUtils.copyPropertiesToFormBean(_selec_nationality.getSecond(), locale, Nationality_Form.class);
		/** 6.4.11.  We sort the list. */
		Collections.sort(_selec_nationality_Form, new ToStringComparator());
		Nationality_Form nationallityForm=new Nationality_Form();
		ExtendedBeanUtils.copyPropertiesToFormBean(nationallityForm, UseCaseFacade.ObtainDefaultNationallity(usercode), locale);
		_selec_nationality_Form.add(nationallityForm);


		/** 6.4.12.  We put the values to the request, so the jsp can get them. */

		request.setAttribute("_selec_nationality", _selec_nationality_Form);
		/** 6.4.13.  We obtain the values form the business logic. */

		Pair<Integer, List<Payment>> _selec_payment= UseCaseFacade.ObtainAllPayment(usercode,  new ListConfigurator());
		/** 6.4.14.  We copy the values to a list of formbeans. */

		List<Payment_Form> _selec_payment_Form = ExtendedBeanUtils.copyPropertiesToFormBean(_selec_payment.getSecond(), locale, Payment_Form.class);
		/** 6.4.15.  We sort the list. */
		Collections.sort(_selec_payment_Form, new ToStringComparator());
		/** 6.4.16.  We put the values to the request, so the jsp can get them. */

		request.setAttribute("_selec_payment", _selec_payment_Form);
		/** 6.4.17.  We obtain the values form the business logic. */

		Pair<Integer, List<Category>> _selec_category= UseCaseFacade.ObtainAllCategory(usercode,  new ListConfigurator());
		/** 6.4.18.  We copy the values to a list of formbeans. */

		List<Category_Form> _selec_category_Form = ExtendedBeanUtils.copyPropertiesToFormBean(_selec_category.getSecond(), locale, Category_Form.class);
		/** 6.4.19.  We sort the list. */
		Collections.sort(_selec_category_Form, new ToStringComparator());
		/** 6.4.20.  We put the values to the request, so the jsp can get them. */

		request.setAttribute("_selec_category", _selec_category_Form);
		/** 6.4.21.  We obtain the values form the business logic. */

		Pair<Integer, List<Working_hours>> _selec_working_hours= UseCaseFacade.ObtainAllWorking_hours(usercode,  new ListConfigurator());
		/** 6.4.22.  We copy the values to a list of formbeans. */

		List<Working_hours_Form> _selec_working_hours_Form = ExtendedBeanUtils.copyPropertiesToFormBean(_selec_working_hours.getSecond(), locale, Working_hours_Form.class);
		/** 6.4.23.  We sort the list. */
		Collections.sort(_selec_working_hours_Form, new ToStringComparator());
		/** 6.4.24.  We put the values to the request, so the jsp can get them. */

		request.setAttribute("_selec_working_hours", _selec_working_hours_Form);
		/** 6.4.25.  We obtain the values form the business logic. */

		Pair<Integer, List<Bank>> _selec_bank= UseCaseFacade.ObtainAllBank(usercode,  new ListConfigurator());
		/** 6.4.26.  We copy the values to a list of formbeans. */

		List<Bank_Form> _selec_bank_Form = ExtendedBeanUtils.copyPropertiesToFormBean(_selec_bank.getSecond(), locale, Bank_Form.class);
		/** 6.4.27.  We sort the list. */
		Collections.sort(_selec_bank_Form, new ToStringComparator());
		/** 6.4.28.  We put the values to the request, so the jsp can get them. */

		request.setAttribute("_selec_bank", _selec_bank_Form);
		/** 6.4.29.  We obtain the values form the business logic. */

		Pair<Integer, List<Marital_status>> _selec_marital_status= UseCaseFacade.ObtainAllMarital_status(usercode,  new ListConfigurator());
		/** 6.4.30.  We copy the values to a list of formbeans. */

		List<Marital_status_Form> _selec_marital_status_Form = ExtendedBeanUtils.copyPropertiesToFormBean(_selec_marital_status.getSecond(), locale, Marital_status_Form.class);
		/** 6.4.31.  We sort the list. */
		Collections.sort(_selec_marital_status_Form, new ToStringComparator());
		/** 6.4.32.  We put the values to the request, so the jsp can get them. */

		request.setAttribute("_selec_marital_status", _selec_marital_status_Form);



		/** 6.5.  We add the values of the the view-lists (if any) to the request. */



		/** 6.6.  We add the values of the BOAdder (if any) to the request. */

		fbManager.setBOAdderList(boAdderList, "personal_child",
				Child_Form.class);

		fbManager.setBOAdderNew("personal_child", boAdderFormBean
				.getNewFormBean());

		request.setAttribute("viewlistElements",
				((Action_new_personal_Form) fbManager.getContainer())
				.getPersonal_child().getFormBeanList());


		/** 6.7.  We add the values of popup-selectables (if any) to the request: we need to put the value of the to-string method, because the FormBean may only store be the code */



		/** 7.  We navigate to the correct page. */

		return NavigationFunctions.findForward(request, mapping, "success");


	}
}