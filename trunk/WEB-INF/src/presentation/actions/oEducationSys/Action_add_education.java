package presentation.actions.oEducationSys;


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

import presentation.formbeans.oEducationSys.*;


/**
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Action_add_education extends Action {
    
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
	
Action_add_education_Form action_add_education_Form=(Action_add_education_Form) form;

FormBeanManager fbManager=new FormBeanManager(request, action_add_education_Form);
	
	/** 4.  We obtain the initial POJO. */
	
	Education education = null;
	
	/** 5.  In case a BOAdder exists in the following page, we reed the info from the BOAdder. */
	
	
	
	/** 6.  We Prepare the values that will be used in the next jsp */
	/** 6.1.  We create a new FormBeanManager (and delete the old one) */
	
	fbManager.cleanContainer();

fbManager=new FormBeanManager(request, Action_add_education_Form.class);




	/** 6.2.  We add the current POJO merged with the nested-pojo-form-bean to the manager. */
	
	fbManager.setAttribute(education, "education_Form", Education_Form.class, action_add_education_Form.getEducation_Form());
	
	/** 6.3.  We add the container of the manager to the request. */
	
	request.setAttribute("oEducationSys__action_add_education_Form", fbManager.getContainer());
	
	/** 6.4.  We add the values of all (if any) of the support tables which will be used in the jsp (for selectables) to the request. */
	
	

/** 6.4.5.  We obtain the values form the business logic. */

Pair<Integer, List<Type_of_education>> _selec_type_of_education= UseCaseFacade.ObtainAllType_of_education(usercode,  new ListConfigurator());
/** 6.4.6.  We copy the values to a list of formbeans. */

List<Type_of_education_Form> _selec_type_of_education_Form = ExtendedBeanUtils.copyPropertiesToFormBean(_selec_type_of_education.getSecond(), locale, Type_of_education_Form.class);
/** 6.4.7.  We sort the list. */
Collections.sort(_selec_type_of_education_Form, new ToStringComparator());
/** 6.4.8.  We put the values to the request, so the jsp can get them. */

request.setAttribute("_selec_type_of_education", _selec_type_of_education_Form);
/** 6.4.9.  We obtain the values form the business logic. */

Pair<Integer, List<Country>> _selec_country= UseCaseFacade.ObtainAllCountry(usercode,  new ListConfigurator());
/** 6.4.10.  We copy the values to a list of formbeans. */

List<Country_Form> _selec_country_Form = ExtendedBeanUtils.copyPropertiesToFormBean(_selec_country.getSecond(), locale, Country_Form.class);
/** 6.4.11.  We sort the list. */
Collections.sort(_selec_country_Form, new ToStringComparator());
Country_Form countryForm=new Country_Form();
ExtendedBeanUtils.copyPropertiesToFormBean(countryForm, UseCaseFacade.ObtainDefaultCountry(usercode), locale);
_selec_country_Form.add(countryForm);

/** 6.4.12.  We put the values to the request, so the jsp can get them. */

request.setAttribute("_selec_country", _selec_country_Form);
	
	
	
	/** 6.5.  We add the values of the the view-lists (if any) to the request. */
	
	
	
	/** 6.6.  We add the values of the BOAdder (if any) to the request. */
	
	
    	

	/** 6.7.  We add the values of popup-selectables (if any) to the request: we need to put the value of the to-string method, because the FormBean may only store be the code */
	
	

	/** 7.  We navigate to the correct page. */
	
	return NavigationFunctions.findForward(request, mapping, "success");
	
	
		}
}