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
import bussineslogic.controlers.UseCase;
import bussineslogic.controlers.UseCaseFacade;
import bussineslogic.excepciones.UsuarioExisteException;
import presentation.formbeans.objects.UsuarioForm;
import presentation.formbeans.user.UsuarioContainerForm;

public class DoAddUser extends Action {

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
        
    	//Use case Execution
    	Usuario user = null;
    	Usuario userTO=(Usuario) formBeanManager.getPOJO("usuarioForm", Usuario.class);
    	userTO.setActiveboolean(0);
    	try{
    		user = UseCaseFacade.CrearUsuario(userTO);
    	}catch(UsuarioExisteException e){
        	ActionErrors errors=new ActionErrors();
        	ActionMessages messages=new ActionMessages();
        	messages.add("errors.usuario-ya-existe", new ActionMessage("errors.usuario-ya-existe"));
        	errors.add(messages);
        	request.setAttribute(Globals.ERROR_KEY, errors);        	
        	        	
        	return mapping.findForward("error");
    	}
    	UseCaseFacade.AddRol(user.getCode(), user, UseCase.BASIC_ROLE_NAME);
    	
    	UserUtils.addSessionSavedAuditMessage(request, "info.usuario-creado");
             	
        //Write data
        formBeanManager.cleanContainer();
        formBeanManager=new FormBeanManager(request, UsuarioContainerForm.class);
        formBeanManager.setAttribute(user, "usuarioForm", UsuarioForm.class, null);
        request.setAttribute("usuarioContainerForm", formBeanManager.getContainer());
        
		return NavigationFunctions.findForward(request, mapping, "success");
    }
    
    
    
    

}