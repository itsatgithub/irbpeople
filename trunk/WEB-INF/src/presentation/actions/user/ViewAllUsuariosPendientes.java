package presentation.actions.user;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bussineslogic.objects.Usuario;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import utils.Pair;
import utils.actions.NavigationFunctions;
import utils.beanUtils.ExtendedBeanUtils;
import utils.listFilter.ViewListConfiguration;
import utils.userUtils.UserUtils;
import bussineslogic.controlers.UseCaseFacade;
import presentation.formbeans.objects.UsuarioForm;

public class ViewAllUsuariosPendientes extends Action {

    @Override
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
    	NavigationFunctions.setReturnPoint(request, mapping.getPath());
    	//load data
    	String usercode = UserUtils.getCurrentUsuarioId(request); 
    	Locale locale = UserUtils.getCurrentLocale(request); 	 	
       
	    ViewListConfiguration filter = (ViewListConfiguration) request.getAttribute("viewListConfiguration");
	   
	    //use case execution
    	Pair<Integer,List<Usuario>> usuarios = UseCaseFacade.ObtenerUsuariosPendientes(usercode, filter.obtainListConfigurator(locale, true));         	        	
    	
    	//write data
    	List usuariosForm = ExtendedBeanUtils.copyPropertiesToFormBean(usuarios.getSecond(), locale, UsuarioForm.class);
    	
    	request.setAttribute("viewlistElements", usuariosForm);
    	request.setAttribute("viewListTotalElements", usuarios.getFirst());    	

		return NavigationFunctions.findForward(request, mapping, "success");
    }
}