package presentation.actions.workspace;

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
import presentation.formbeans.user.ChangePasswordForm;
import presentation.formbeans.user.UsuarioContainerForm;

public class DoUpdateUser extends Action {

    @Override
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
       
    	//read data
    	String usercode = UserUtils.getCurrentUsuarioId(request); 
    	Locale locale = UserUtils.getCurrentLocale(request); 
    	
    	UsuarioContainerForm userForm = (UsuarioContainerForm)form; 
        FormBeanManager fbManager=new FormBeanManager(request, userForm);
        Usuario userTO=(Usuario) fbManager.getPOJO("usuarioForm", Usuario.class);
        
        //test correctness of the password
        ChangePasswordForm passwordForm = userForm.getChangePasswordForm();
        Usuario current = UseCaseFacade.ObtenerUsuario(usercode, usercode);
        boolean correct = current.isMyPassword(passwordForm.getCurrent());
        if(!correct){
        	ActionErrors errors=new ActionErrors();
        	ActionMessages messages=new ActionMessages();
        	messages.add("errors.password-incorrecto", new ActionMessage("errors.password-incorrecto"));
        	errors.add(messages);
        	request.setAttribute(Globals.ERROR_KEY, errors);        	
        	return mapping.getInputForward();     	
        }
        
    	//use case execution
        Usuario usuario = UseCaseFacade.ModificarUsuario(usercode,userTO);  

        

        if(passwordForm.getPassword()!=null && !passwordForm.getPassword().equals("")){
	    	//Use case execution	
	   		UseCaseFacade.ModificarPassword(usercode,usercode,passwordForm.getPassword());
        	ActionErrors errors=new ActionErrors();
        	ActionMessages messages=new ActionMessages();
        	messages.add("info.password-cambiado-correctamente", new ActionMessage("info.password-cambiado-correctamente"));
        	errors.add(messages);
        	request.setAttribute(Globals.ERROR_KEY, errors);
    	}
        
        request.getSession().setAttribute("org.apache.struts.action.LOCALE", new Locale(usuario.getLanguage().getLanguagecode()));
        
        //write data
        fbManager.cleanContainer();
        fbManager=new FormBeanManager(request, UsuarioContainerForm.class);
        fbManager.setAttribute(usuario, "usuarioForm", UsuarioForm.class, null);
                       
        request.setAttribute("usuarioContainerForm", fbManager.getContainer());
        return NavigationFunctions.findForward(request, mapping, "success");
    }
    
    
    
    

}