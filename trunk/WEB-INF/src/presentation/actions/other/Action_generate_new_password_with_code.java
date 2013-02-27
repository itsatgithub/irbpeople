package presentation.actions.other;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Collections;

import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

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
import bussineslogic.excepciones.*;

import bussineslogic.objects.*;
import presentation.formbeans.objects.*;

import presentation.formbeans.other.*;


/**
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Action_generate_new_password_with_code extends Action {
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {    	    	    

    	/** 1.  We obtain the user Information */
    	String usercode = UserUtils.getCurrentUsuarioId(request); 
    	Locale locale = UserUtils.getCurrentLocale(request); 
    	



		/** 2.  We obtain the initial form bean and we put it to a new FormBeanManager. */
		
    	Action_generate_new_password_with_code_Form action_generate_new_password_with_code_Form=(Action_generate_new_password_with_code_Form) form;

FormBeanManager fbManager=new FormBeanManager(request, action_generate_new_password_with_code_Form);			

		/** 3.  We clean the current container */		
		fbManager.cleanContainer();
		
		/** We execute the use case**/
		try{
			UseCaseFacade.generatePassword(usercode, action_generate_new_password_with_code_Form.getUsuariocode(), action_generate_new_password_with_code_Form.getPasswordGenerationActivationCode());
		} catch (NoPermisosException e){
			ActionErrors errors=new ActionErrors();
        	ActionMessages messages=new ActionMessages();
        	messages.add("errors.incorrect-password-generation-code", new ActionMessage("errors.incorrect-password-generation-code"));
        	errors.add(messages);
        	request.setAttribute(Globals.ERROR_KEY, errors);        	
        	        	
        	return mapping.findForward("error");
		}
		
		UserUtils.addSessionSavedAuditMessage(request,"info.passwordGenereted");
		
		
		/** 4.  We return to the correct page (introducing some values to the request if necessary) */
		
		
/** 4.1.  We look if the current page is beeing opened in a popup window. */
boolean isPopUp = NavigationFunctions.isPopUpWindow(request);
/** 4.2.  We look for the forward to execute. */
return NavigationFunctions.findForward(request, mapping, "success",isPopUp);
		
		
			}
}