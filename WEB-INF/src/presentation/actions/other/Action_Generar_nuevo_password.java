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

import bussineslogic.controlers.UseCase;
import bussineslogic.controlers.UseCaseFacade;

import bussineslogic.objects.*;
import presentation.formbeans.objects.*;

import presentation.formbeans.other.*;


/**
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Action_Generar_nuevo_password extends Action {
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {    	    	    

    	/** 1.  We obtain the user Information */
    	String usercode = UserUtils.getCurrentUsuarioId(request); 
    	Locale locale = UserUtils.getCurrentLocale(request); 
    	



		/** 2.  We obtain the initial form bean and we put it to a new FormBeanManager. */
		
Action_Generar_nuevo_password_Form action_Generar_nuevo_password_Form=(Action_Generar_nuevo_password_Form) form;

FormBeanManager fbManager=new FormBeanManager(request, action_Generar_nuevo_password_Form);			

		/** 3.  We clean the current container */		
		fbManager.cleanContainer();
		
		/** We obtain the user to be changed**/
		Usuario usuarioToChange=UseCase.getUsuario(action_Generar_nuevo_password_Form.getUsuario_form().getUsuariocode());
		
		/** In case that the mail is incorrect we return an error**/
		if(usuarioToChange==null || !usuarioToChange.getEmail().equalsIgnoreCase(action_Generar_nuevo_password_Form.getUsuario_form().getEmail())){
			ActionErrors errors=new ActionErrors();
        	ActionMessages messages=new ActionMessages();
        	messages.add("errors.mail-and-user-not-correspond", new ActionMessage("errors.mail-and-user-not-correspond"));
        	errors.add(messages);
        	request.setAttribute(Globals.ERROR_KEY, errors);        	
        	        	
        	return NavigationFunctions.findInput(request, mapping);
		}
		
		/**We generate a new password **/
		UseCaseFacade.requestToGeneratePassword(usuarioToChange.getUsuariocode(), usuarioToChange.getUsuariocode());

		/** 4.  We return to the correct page (introducing some values to the request if necessary) */
		UserUtils.addSessionSavedAuditMessage(request, "info.requestForPasswordGenereted");
		
		
/** 4.1.  We look if the current page is beeing opened in a popup window. */
boolean isPopUp = NavigationFunctions.isPopUpWindow(request);
/** 4.2.  We look for the forward to execute. */
return NavigationFunctions.findForward(request, mapping, "success",isPopUp);
		
		
			}
}