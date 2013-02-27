package presentation.actions.customList;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.formbeans.objects.Views_Form;

import bussineslogic.controlers.UseCase;
import bussineslogic.controlers.UseCaseFacade;
import bussineslogic.objects.Views;

import utils.actions.NavigationFunctions;
import utils.beanUtils.ExtendedBeanUtils;
import utils.userUtils.UserUtils;

public class Action_view_custom_list extends Action {
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {    	    	    

    	/** 1.  We obtain the user Information */
    	String usercode = UserUtils.getCurrentUsuarioId(request); 
    	Locale locale = UserUtils.getCurrentLocale(request); 
    	
    	request.getSession().setAttribute("CustomListCode",null);
    	
		/** 2.  We set this page as an input point (see NavigationFunctions.setInputPoint function for more info). */
		NavigationFunctions.setReturnPoint(request, mapping.getPath());
		
		List<Views> views = UseCaseFacade.ObtainAllViews(UserUtils.getCurrentUsuario(request));
		
		List<Views_Form> views_form = ExtendedBeanUtils.copyPropertiesToFormBean(views, locale, Views_Form.class);
		
		request.setAttribute("views", views_form);
		request.setAttribute("locale", locale);
		
		return NavigationFunctions.findForward(request, mapping, "success");
    
    }

}
