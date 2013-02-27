package presentation.actions.customList;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.formbeans.objects.CustomList_Form;
import presentation.formbeans.objects.Education_Form;
import bussineslogic.controlers.UseCaseFacade;
import bussineslogic.objects.CustomList;
import bussineslogic.objects.Education;

import utils.Pair;
import utils.actions.NavigationFunctions;
import utils.beanUtils.ExtendedBeanUtils;
import utils.listFilter.ViewListConfiguration;
import utils.userUtils.UserUtils;

public class Action_viewlist_custom_list extends Action {
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {    
    	
    	/** 1.  We obtain the user Information */
    	String usercode = UserUtils.getCurrentUsuarioId(request); 
    	Locale locale = UserUtils.getCurrentLocale(request); 
    	
    	/**
    	 * Delete the different session values used in this listing module  
    	 * */
    	request.getSession().setAttribute("CustomListCode", null);
		request.getSession().setAttribute("FromModifyCustomList", null);
		request.getSession().setAttribute("listconfigfilter",null);
		request.getSession().setAttribute("filterView", null);
		request.getSession().setAttribute("columnsView",null);
		request.getSession().setAttribute("orderByView",null); 
		request.getSession().setAttribute("viewName",null);
    	
		/** 2.  We set this page as a input point (see NavigationFunctions.setInputPoint function for more info). */
		NavigationFunctions.setReturnPoint(request, mapping.getPath());
		
		ViewListConfiguration filter = (ViewListConfiguration) request.getAttribute("viewListConfiguration");
		/** 3.2.2.  We obtain the list of objects form the Business logic */
		
			
		List<CustomList> _list_customlist=UseCaseFacade.ObtainAllCustomList(UserUtils.getCurrentUsuario(request));
		
		/** 3.2.3.  We obtain copy the list of pojos to a list of FormBeans */
		
		List<CustomList_Form> viewlistElements =ExtendedBeanUtils.copyPropertiesToFormBean(_list_customlist, locale, CustomList_Form.class);
		
		request.setAttribute("viewlistElements", viewlistElements);
    	
		return NavigationFunctions.findForward(request, mapping, "success");
    }

}
