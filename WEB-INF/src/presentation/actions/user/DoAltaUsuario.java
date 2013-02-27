package presentation.actions.user;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bussineslogic.objects.Usuario;

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
import presentation.formbeans.objects.UsuarioForm;
import presentation.formbeans.user.UsuarioContainerForm;

public class DoAltaUsuario extends Action {

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
        Usuario usuario = UseCaseFacade.AltaUsuario(usercode, usuarioForm.getUsuariocode());
        
        //Write data
        formBeanManager.cleanContainer();
        formBeanManager=new FormBeanManager(request, UsuarioContainerForm.class);
        formBeanManager.setAttribute(usuario, "usuarioForm", UsuarioForm.class, null);
        request.setAttribute("usuarioContainerForm", formBeanManager.getContainer());
        
    	ActionErrors errors=new ActionErrors();
    	ActionMessages messages=new ActionMessages();
    	
    	messages.add("info.usuario-activado", new ActionMessage("info.usuario-activado"));
    	errors.add(messages);
    	request.setAttribute(Globals.ERROR_KEY, errors);        	    	        	
        
        return NavigationFunctions.findForward(request, mapping, "success", "usuariocode="+usuario.getUsuariocode());    	              	        
        
    }
    
}