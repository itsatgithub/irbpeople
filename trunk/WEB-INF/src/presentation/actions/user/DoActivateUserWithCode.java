package presentation.actions.user;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bussineslogic.objects.Auditmessage;
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
import bussineslogic.excepciones.NoPermisosException;
import bussineslogic.excepciones.UsuarioExisteException;
import presentation.formbeans.objects.UsuarioForm;
import presentation.formbeans.user.UsuarioContainerForm;

public class DoActivateUserWithCode extends Action {

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
    	Usuario user;
    	try{
    		user = UseCaseFacade.AltaUsuario(usercode, usuarioForm.getUsuariocode(), usuarioForm.getUsuarioForm().getActivationCode());
    	}catch(NoPermisosException e){
    		return NavigationFunctions.putActionError(request, mapping, "errors.activation-code-incorrecto");
    	}
             
    	
    	UserUtils.addSessionSavedAuditMessage(request, "info.usuario-activado");
    	
        //Write data
        formBeanManager.cleanContainer();
        formBeanManager=new FormBeanManager(request, UsuarioContainerForm.class);
        formBeanManager.setAttribute(user, "usuarioForm", UsuarioForm.class, null);
        request.setAttribute("usuarioContainerForm", formBeanManager.getContainer());
        
		return NavigationFunctions.findForward(request, mapping, "success");
    }
    
    
    
    

}