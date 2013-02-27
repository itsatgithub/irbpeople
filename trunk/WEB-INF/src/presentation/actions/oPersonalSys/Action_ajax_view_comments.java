package presentation.actions.oPersonalSys;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Collections;

import org.apache.commons.beanutils.LazyDynaBean;
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
import utils.listFilter.ViewListConfiguration.OrderBy;
import utils.beanUtils.ExtendedBeanUtils;
import utils.filter.ListConfigurator;
import utils.filter.SessionControllerFilter;
import utils.customguardation.controller.*;
import bussineslogic.controlers.UseCaseFacade;
import bussineslogic.objects.*;
import presentation.formbeans.oProfessionalSys.Action_view_professional_Form;
import presentation.formbeans.objects.*;




public class Action_ajax_view_comments extends Action{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)

	throws Exception {

	    


		
			
			
			response.setHeader("Cache-Control", "no-cache");
			response.setContentType("text/x-json");
			response.setCharacterEncoding( "UTF-8" );
			response.getWriter().write("{\"rows\":[{\"id\":\"1\",\"cell\":[\"2007-10-06\",\"Author 3\",\"Esto es un comentario.\"]},{\"id\":\"2\",\"cell\":[\"2007-10-06\",\"Author 2\",\"Este es un comentario algo más largo y con un acento\"]},{\"id\":\"3\",\"cell\":[\"2007-10-06\",\"Author 1\",null]},{\"id\":\"4\",\"cell\":[\"2007-10-06\",\"Author 2\",\"añkdfjañdkfja dsfjañsdklfjañskdfj añsdkfjañskdfjañsldkjf asd falsdkjfalskdjf asdjfalsdjfajfjal  jfl aksdjflaskdjflaskdjflakjsd fasdjfl aksdjfa sflkajsdflkajsdflkajsdlfkajsd fa sdf adsf asdf a sdflaksdjflakdjsflaksjdf   adf adf asldkfjalkdsjflaksdjflasdkjflasdkfj\"]},{\"id\":\"5\",\"cell\":[\"2007-10-06\",\"Author 1\",\"200.00\"]}]}");
		
		/** 4.  We navigate to we give the response direct to the inputPage */
		return null;

	}



}


























