package presentation.actions.user;

import java.util.List;
import java.util.Locale;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

public class DisplayAddNewUser extends Action {

    @Override
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
    	
    	NavigationFunctions.setInputPoint(request, mapping.getPath());
    	
        //load data
    	String usercode = UserUtils.getCurrentUsuarioId(request); 
    	Locale locale = UserUtils.getCurrentLocale(request); 
    	if(locale==null){locale=new Locale("en");}
    	
    	UsuarioContainerForm newUserForm = (UsuarioContainerForm) form;
    	FormBeanManager formBeanManager=new FormBeanManager(request, newUserForm);
    	//use case execution
    	
    	//clear data
    	formBeanManager.cleanContainer();
    	
    	//write data
    	formBeanManager=new FormBeanManager(request, UsuarioContainerForm.class);
        formBeanManager.setAttribute(null, "usuarioForm", UsuarioForm.class,newUserForm.getUsuarioForm());
        request.setAttribute("usuarioContainerForm", formBeanManager.getContainer());
         
        List<Role> roles = UseCaseFacade.ObtenerRoles();
        
        // Quitamos el rol por defecto, ya que lo tendrá siempre.
        for(int j=0;j<roles.size();j++){
        	if(roles.get(j).getDescription().equalsIgnoreCase(SiteOrgManager.singleton().getRolesManager().getDefaultRoleName())) roles.remove(j);
        }
        
        List<RoleForm> rolesForm=ExtendedBeanUtils.copyPropertiesToFormBean(roles, locale, RoleForm.class);      
        request.setAttribute("roles", rolesForm);
        
        List<Language> idiomas = UseCaseFacade.ObtenerIdiomas();
        List<LanguageForm> idiomasForm=ExtendedBeanUtils.copyPropertiesToFormBean(idiomas, locale, LanguageForm.class);      
        request.setAttribute("idiomas", idiomasForm);
        

        /** Código generado para preparar los atributos del usuario */
        /** 6.4.  We add the values of all (if any) of the support tables which will be used in the jsp (for selectables) to the request. */
        /** 6.4.1.  We obtain the values form the business logic. */
        Pair<Integer, List<Personal>> _selec_gender= UseCaseFacade.ObtainAllPersonalWithoutUser(usercode,  new ListConfigurator());
        /** 6.4.2.  We copy the values to a list of formbeans. */
        Personal_Form aux=new Personal_Form();
        
        aux.setName("--create a new personal--");
        
        List<Personal_Form> _selec_personal_Form = ExtendedBeanUtils.copyPropertiesToFormBean(_selec_gender.getSecond(), locale, Personal_Form.class);
        _selec_personal_Form.add(aux);
        
        /** 6.4.3.  We sort the list. */
        Collections.sort(_selec_personal_Form, new ToStringComparator());
        /** 6.4.4.  We put the values to the request, so the jsp can get them. */
        request.setAttribute("_selec_personal", _selec_personal_Form);
		
        /** ENDOF: Código generado para preparar los atributos del usuario */        
        
		return NavigationFunctions.findForward(request, mapping, "success");
    }
    
    
    
    

}