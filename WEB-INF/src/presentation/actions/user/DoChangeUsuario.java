package presentation.actions.user;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bussineslogic.objects.Usuario;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import utils.actions.NavigationFunctions;
import utils.formbeans.FormBeanManager;
import utils.userUtils.UserUtils;
import bussineslogic.controlers.UseCaseFacade;
import presentation.formbeans.objects.UsuarioForm;
import presentation.formbeans.user.UsuarioContainerForm;

public class DoChangeUsuario extends Action {

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
        
    	//Update the user
    	//Usuario usuario = UseCaseFacade.ModificarUsuario(usercode,(Usuario) formBeanManager.getPOJO("usuarioForm", Usuario.class));
    	Usuario usuario = UseCaseFacade.ObtenerUsuario(usercode,usuarioForm.getUsuariocode());
        //Remove all roles
        UseCaseFacade.QuitarRoles(usercode, (Usuario) formBeanManager.getPOJO("usuarioForm", Usuario.class));
        //Add all roles
        String[] roles = usuarioForm.getUsuarioForm().getRoles();
        for(int i=0; i<roles.length;i++){
        	UseCaseFacade.AddRol(usercode,(Usuario) formBeanManager.getPOJO("usuarioForm", Usuario.class),roles[i]);
        }
        
        
        //Write data
        formBeanManager.cleanContainer();
        formBeanManager=new FormBeanManager(request, UsuarioContainerForm.class);
        formBeanManager.setAttribute(usuario, "usuarioForm", UsuarioForm.class, null);
        request.setAttribute("usuarioContainerForm", formBeanManager.getContainer());
        
        return NavigationFunctions.findForward(request, mapping, "success", "usuariocode="+usuario.getUsuariocode());
    }
    
    
    
    

}