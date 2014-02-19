package presentation.actions.login;

import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

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
import utils.linkprocessors.CommandActionLinkProcessor;
import utils.userUtils.UserUtils;

import com.justinmind.usermanagement.api.UserManagement;
import com.justinmind.usermanagement.entity.Role;
import com.justinmind.util.siteorg.SiteOrg;
import com.justinmind.util.siteorg.SiteOrgManager;

import bussineslogic.controlers.UseCaseFacade;
import bussineslogic.excepciones.UsuarioNoActivoException;
import presentation.formbeans.objects.*;
import bussineslogic.objects.*;

public class DoLogin extends Action {

    @Override
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {

    	NavigationFunctions.setReturnPoint(request, mapping.getPath());
    	UsuarioForm newUsuarioForm = (UsuarioForm)form;                                                           
                      
    	Usuario current = null;
    	String errorMsg = "errors.usuario-no-valido";
    	try{
    		current = UseCaseFacade.HacerLogin(newUsuarioForm.getUsername(), newUsuarioForm.getPassword(), request.getRemoteHost());
    	}catch(UsuarioNoActivoException e){
    		errorMsg = "errors.usuario-bloqueado";
    	}
        
    	if(current==null){
        	ActionErrors errors=new ActionErrors();
        	ActionMessages messages=new ActionMessages();
        	messages.add(errorMsg, new ActionMessage(errorMsg));
        	errors.add(messages);
        	request.setAttribute(Globals.ERROR_KEY, errors);        	
        	        	
        	return mapping.findForward("error");
        }
        
        
        	UserUtils.setCurrentUsuario(request, current);

//        	Set<Role> roles = UseCaseFacade.ObtenerRolesUsuario(current.getUsuariocode(), current.getUsuariocode());
//        	        	
//        	Object rolename;
//        	if(roles==null || roles.isEmpty()){
//        		rolename= SiteOrgManager.singleton().getRolesManager().getDefaultRoleName();
//        	} else{
//        		String[] rolesNames = new String[roles.size()];
//        		int i=0;
//        		Iterator iter = roles.iterator();
//        		while(iter.hasNext()){
//        			rolesNames[i] = ((Role)iter.next()).getId();
//        			++i;
//
//        		}
//        		rolename = rolesNames;
//        	}
//            
//        	String lang = UserUtils.getCurrentLocale(request).getLanguage();
//                        
//            SiteOrg siteOrg = SiteOrgManager.singleton().siteOrgFactoryUsingCache(rolename, lang, new CommandActionLinkProcessor());            
//                      
//            siteOrg.configAndCheck();
//            siteOrg.configureForThisUser(null);
//            
//            request.getSession().setAttribute("siteorg", siteOrg);
            
            request.getSession().setAttribute(UserUtils.ATT_AUDITMSG_DATE, new Date());
            
//            request.getSession().setAttribute("org.apache.struts.action.LOCALE", new Locale(lang));
            
            uicomponents.scheduler.SchedulerFacadeForCommonControlsScheduler.facadeConfigure(request, "def");
            
        	return mapping.findForward("success");
//        }
//        
//        else {
//        	ActionErrors errors=new ActionErrors();
//        	ActionMessages messages=new ActionMessages();
//        	messages.add("errors.login-incorrecto", new ActionMessage("errors.login-incorrecto"));
//        	errors.add(messages);
//        	request.setAttribute(Globals.ERROR_KEY, errors);        	
//        	        	
//        	return mapping.findForward("error");
//        }
    }
    
}