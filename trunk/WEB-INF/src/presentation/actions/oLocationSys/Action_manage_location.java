package presentation.actions.oLocationSys;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Collections;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import utils.formbeans.ToStringComparator;
import utils.actions.NavigationFunctions;
import utils.actions.BOAdderFunctions;
import utils.formbeans.FormBeanManager;
import utils.formbeans.BOAdderFormBean;
import utils.userUtils.UserUtils;
import utils.Pair;
import utils.listFilter.ViewListConfiguration;
import utils.beanUtils.ExtendedBeanUtils;
import utils.filter.ListConfigurator;

import bussineslogic.controlers.UseCaseFacade;

import bussineslogic.objects.*;
import presentation.formbeans.objects.*;

	import presentation.formbeans.oLocationSys.*;


/**
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Action_manage_location extends Action {
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {    	    	    

    	/** 1.  We obtain the user Information */
    	String usercode = UserUtils.getCurrentUsuarioId(request); 
    	Locale locale = UserUtils.getCurrentLocale(request); 
    	


			
		/** 2.  We set this page as a input point (see NavigationFunctions.setInputPoint function for more info). */
		NavigationFunctions.setReturnPoint(request, mapping.getPath());
		
		/** 3.  We Prepare the values that will be used in the next jsp */
		/** 3.1.  We add the values of all (if any) of the support tables which will be used in the jsp (for selectables) to the request. */
		
		
		
		/** 3.2.  We add the values of the the view-lists (if any) to the request. */			
		
		
	/** 3.2.1.  We obtain the current ViewListConfiguration from the request */
	ViewListConfiguration filter = (ViewListConfiguration) request.getAttribute("viewListConfiguration");
	/** 3.2.2.  We obtain the list of objects form the Business logic */
	
		
		Pair<Integer,List<Location>> _list_location=UseCaseFacade.ObtainAllLocation(usercode,filter.obtainListConfigurator(locale, false));
	
	/** 3.2.3.  We obtain copy the list of pojos to a list of FormBeans */
	
	List<Location_Form> viewlistElements =ExtendedBeanUtils.copyPropertiesToFormBean(_list_location.getSecond(), locale, Location_Form.class);
	
	request.setAttribute("viewlistElements", viewlistElements);
	
		
		
		
		
		/** 3.3.  We add the values of popup-selectables (if any) to the request: we need to put the value of the to-string method, because the FormBean may only store be the code */
		
		

		/** 4.  We navigate to the correct page. */		
		
		return NavigationFunctions.findForward(request, mapping, "success");
		
		
			}
}