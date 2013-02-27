package presentation.actions.other;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Collections;

import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.StaleObjectStateException;

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

import bussineslogic.controlers.UseCase;
import bussineslogic.controlers.UseCaseFacade;

import bussineslogic.objects.*;
import presentation.formbeans.objects.*;

import presentation.formbeans.other.*;


/**
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class ErrorPage extends Action {
	private static org.apache.log4j.Category log = org.apache.log4j.Logger.getLogger(ErrorPage.class);
	
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {    	    	    

    	Exception ex=(Exception)request.getAttribute(Globals.EXCEPTION_KEY);
    	if(ex.getCause() instanceof StaleObjectStateException){
    		NavigationFunctions.putActionError(request, mapping, "error.staleobjecterror");
    		return new ActionForward(NavigationFunctions.getReturnPoint(request));
    	}
    	
    	UserUtils.removeCurrentUsuario(request);
    		
    	
    	
		
		return NavigationFunctions.findForward(request, mapping, "success");
		
		
			}
}