package presentation.actions.user;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import utils.actions.NavigationFunctions;
import utils.formbeans.FormBeanManager;
import utils.userUtils.UserUtils;
import bussineslogic.controlers.UseCaseFacade;
import presentation.formbeans.user.UsuarioContainerForm;

public class DoDeleteAllUsersDefinitely extends Action {

    @Override
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {

    	//Load data
    	String usercode = UserUtils.getCurrentUsuarioId(request); 
    	Locale locale = UserUtils.getCurrentLocale(request);   	
    	
    	UsuarioContainerForm usuarioForm = (UsuarioContainerForm) form;
    	FormBeanManager formBeanManager=new FormBeanManager(request, usuarioForm);
                                           
    	//Use case execution
        UseCaseFacade.BorrarTODOSUsuariosDefinitivamente(usercode);
                        
    	ActionErrors errors=new ActionErrors();
    	ActionMessages messages=new ActionMessages();
    	
    	messages.add("info.usuario-borrar-todos", new ActionMessage("info.usuario-borrar-todos"));
    	errors.add(messages);
    	request.setAttribute(Globals.ERROR_KEY, errors);        	    	        	
        
        return NavigationFunctions.findForward(request, mapping, "success", "usuariocode="+usercode);    	              	        
        
    }
    
}