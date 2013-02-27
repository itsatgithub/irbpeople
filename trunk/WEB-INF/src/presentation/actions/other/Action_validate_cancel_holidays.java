package presentation.actions.other;


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
import bussineslogic.excepciones.NoPermisosException;
import bussineslogic.objects.*;
import presentation.actions.personalphoto.GetImageAction;
import presentation.formbeans.objects.*;
import presentation.formbeans.other.Action_validate_holidays_Form;
import presentation.formbeans.other.Action_validate_personal_Form;

import presentation.formbeans.oPersonalSys.*;


/**
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Action_validate_cancel_holidays extends Action {
    
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
	
Action_validate_holidays_Form action_validate_holidays_Form=(Action_validate_holidays_Form) form;

FormBeanManager fbManager=new FormBeanManager(request, action_validate_holidays_Form);

	/** 4.  We obtain the initial POJO. */

Irbholiday irbholiday = null;
try {
	irbholiday = UseCaseFacade.ObtainIrbholiday(usercode, ((Irbholiday)fbManager.getPOJO("irbholiday_Form", Irbholiday.class)).getCode());
} catch (Exception e) {
	UserUtils.addSessionSavedAuditMessage(request, "errors.no-validation");
	return NavigationFunctions.findForward(request, mapping, "error");
}
	
	UseCaseFacade.cancelValidateHolidays(usercode, irbholiday, action_validate_holidays_Form.getObservacions());
	
	UserUtils.addSessionSavedAuditMessage(request, "info.validationHolidaysFail");
	
	/** 5.  In case a BOAdder exists in the following page, we reed the info from the BOAdder. */
	
	
	/** 6.  We Prepare the values that will be used in the next jsp */
	/** 6.1.  We create a new FormBeanManager (and delete the old one) */
	
	fbManager.cleanContainer();

fbManager=new FormBeanManager(request, Action_validate_personal_Form.class);




	/** 6.2.  We add the current POJO merged with the nested-pojo-form-bean to the manager. */
	
	
	/** 6.3.  We add the container of the manager to the request. */
	

	/** 6.4.  We add the values of all (if any) of the support tables which will be used in the jsp (for selectables) to the request. */
	

	/** 6.5.  We add the values of the the view-lists (if any) to the request. */	

	

	/** 6.6.  We add the values of the BOAdder (if any) to the request. */	
	
	
    	
	
	/** 6.7.  We add the values of popup-selectables (if any) to the request: we need to put the value of the to-string method, because the FormBean may only store be the code */
	
	
	
	/** 7.  We navigate to the correct page. */
	
	return NavigationFunctions.findForward(request, mapping, "success");
	
	
		}
}