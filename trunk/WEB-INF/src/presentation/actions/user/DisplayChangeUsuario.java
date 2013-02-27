package presentation.actions.user;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bussineslogic.objects.Usuario;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import utils.formbeans.ToStringComparator;
import utils.Pair;
import utils.filter.ListConfigurator;
import utils.actions.NavigationFunctions;
import utils.beanUtils.ExtendedBeanUtils;
import utils.formbeans.FormBeanManager;
import utils.userUtils.UserUtils;

import com.justinmind.usermanagement.entity.Language;
import com.justinmind.usermanagement.entity.Role;
import com.justinmind.util.siteorg.SiteOrgManager;

import bussineslogic.controlers.UseCaseFacade;
import presentation.formbeans.objects.*;
import bussineslogic.objects.*;
import presentation.formbeans.user.UsuarioContainerForm;

public class DisplayChangeUsuario extends Action {

    @Override
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
       
    	//load data
    	String usercode = UserUtils.getCurrentUsuarioId(request); 
    	Locale locale = UserUtils.getCurrentLocale(request);
    	
    	UsuarioContainerForm usuarioForm = (UsuarioContainerForm)form;
    	FormBeanManager formBeanManager=new FormBeanManager(request, usuarioForm);
    	//use case execution
        Usuario usuario = UseCaseFacade.ObtenerUsuario(usercode, usuarioForm.getUsuariocode());         	        	                
        
        Set<Role> currentRoles = UseCaseFacade.ObtenerRolesUsuario(usercode, usuarioForm.getUsuariocode());
                                
        //clear data
        formBeanManager.cleanContainer();
        
        //write data
        formBeanManager=new FormBeanManager(request, UsuarioContainerForm.class);
        formBeanManager.setAttribute(usuario, "usuarioForm", UsuarioForm.class,usuarioForm.getUsuarioForm());

        request.setAttribute("usuarioContainerForm", formBeanManager.getContainer());
        
        Iterator<Role> it = currentRoles.iterator();      
        String[] rolesId = new String[currentRoles.size()];
        int i=0;
        while(it.hasNext()){
        	rolesId[i] = it.next().getEntitycode();
        	i++;
        }
        usuarioForm.getUsuarioForm().setRoles(rolesId);
        
        List<Role> roles = UseCaseFacade.ObtenerRoles();
                
        // Quitamos el rol por defecto, ya que lo tendrá siempre.
        
        
        List<RoleForm> rolesForm=ExtendedBeanUtils.copyPropertiesToFormBean(roles, locale, RoleForm.class);      
        request.setAttribute("roles", rolesForm);
        
        List<Language> idiomas = UseCaseFacade.ObtenerIdiomas();
        List<LanguageForm> idiomasForm=ExtendedBeanUtils.copyPropertiesToFormBean(idiomas, locale, LanguageForm.class);      
        request.setAttribute("idiomas", idiomasForm);
        
        /** Código generado para preparar los atributos del usuario */
		
		
        /** ENDOF: Código generado para preparar los atributos del usuario */             
        
		return NavigationFunctions.findForward(request, mapping, "success");
    }
    
    
    
    

}