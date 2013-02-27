package presentation.actions.user;

import java.util.ArrayList;
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

import bussineslogic.controlers.UseCaseFacade;
import presentation.formbeans.objects.*;
import bussineslogic.objects.*;
import presentation.formbeans.user.UsuarioContainerForm;

public class DisplayAddUser extends Action {

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
         
        //TODO change
        List<Language> idiomas = UseCaseFacade.ObtenerIdiomas();
        List<LanguageForm> idiomasForm=new ArrayList();
        for(int i=0; i<idiomas.size(); ++i){
        	idiomasForm.add(new LanguageForm());
        }
        ExtendedBeanUtils.copyPropertiesToFormBean(idiomasForm, idiomas, locale);
        request.setAttribute("idiomas", idiomasForm);
        
        /** Código generado para preparar los atributos del usuario */
		
		
        /** ENDOF: Código generado para preparar los atributos del usuario */        
        
		return NavigationFunctions.findForward(request, mapping, "success");
    }
    
    
    
    

}