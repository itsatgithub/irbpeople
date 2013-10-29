package presentation.actions.oPersonalSys;


import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.formbeans.oPersonalSys.Action_manage_my_own_personal_academic_info_Form;
import presentation.formbeans.objects.Academic_info_Form;
import presentation.formbeans.objects.Personal_Form;
import utils.Pair;
import utils.actions.NavigationFunctions;
import utils.beanUtils.ExtendedBeanUtils;
import utils.formbeans.FormBeanManager;
import utils.listFilter.ViewListConfiguration;
import utils.userUtils.UserUtils;
import bussineslogic.controlers.UseCaseFacade;
import bussineslogic.objects.Academic_info;
import bussineslogic.objects.Personal;


/**
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Action_manage_my_own_personal_academic_info extends Action {
    
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
	
Action_manage_my_own_personal_academic_info_Form action_manage_my_own_personal_academic_info_Form=(Action_manage_my_own_personal_academic_info_Form) form;

FormBeanManager fbManager=new FormBeanManager(request, action_manage_my_own_personal_academic_info_Form);
	
	/** 4.  We obtain the initial POJO. */
	
Personal personal = UseCaseFacade.ObtainPersonalFromUser(usercode, UserUtils.getCurrentUsuario(request));
	
	/** 5.  In case a BOAdder exists in the following page, we reed the info from the BOAdder. */
	
	
	
	/** 6.  We Prepare the values that will be used in the next jsp */
	/** 6.1.  We create a new FormBeanManager (and delete the old one) */
	
	fbManager.cleanContainer();

fbManager=new FormBeanManager(request, Action_manage_my_own_personal_academic_info_Form.class);




	/** 6.2.  We add the current POJO merged with the nested-pojo-form-bean to the manager. */
	
	fbManager.setAttribute(personal, "personal_Form", Personal_Form.class, action_manage_my_own_personal_academic_info_Form.getPersonal_Form());
	
	/** 6.3.  We add the container of the manager to the request. */
	
	request.setAttribute("oPersonalSys__action_manage_my_own_personal_academic_info_Form", fbManager.getContainer());

	/** 6.4.  We add the values of all (if any) of the support tables which will be used in the jsp (for selectables) to the request. */	
	
	

	/** 6.5.  We add the values of the the view-lists (if any) to the request. */	
	
	
	/** 6.5.1.  We obtain the current ViewListConfiguration from the request */
	ViewListConfiguration filter = (ViewListConfiguration) request.getAttribute("viewListConfiguration");
	/** 6.5.2.  We obtain the list of objects form the Business logic */
	
			
			Pair<Integer,List<Academic_info>> _list_academic_info=UseCaseFacade.ObtainAllIacademic_info_personalFromPersonal(usercode,action_manage_my_own_personal_academic_info_Form.getPersonal_Form().getPersonalcode(),filter.obtainListConfigurator(locale, false));
	
	/** 6.5.3.  We obtain copy the list of pojos to a list of FormBeans */
	
	List<Academic_info_Form> viewlistElements =ExtendedBeanUtils.copyPropertiesToFormBean(_list_academic_info.getSecond(), locale, Academic_info_Form.class);
	
	request.setAttribute("viewlistElements", viewlistElements);
	

	/** 6.6.  We add the values of the BOAdder (if any) to the request. */	
	
	
    	
	
	/** 6.7.  We add the values of popup-selectables (if any) to the request: we need to put the value of the to-string method, because the FormBean may only store be the code */
	
	
	
	/** 7.  We navigate to the correct page. */
	
	return NavigationFunctions.findForward(request, mapping, "success");
	
	
		}
}