package presentation.actions.customList;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ecs.xhtml.a;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.formbeans.customList.Action_load_customList_Form;
import presentation.formbeans.objects.CustomList_Form;
import utils.actions.NavigationFunctions;
import utils.beanUtils.ExtendedBeanUtils;
import utils.userUtils.UserUtils;
import bussineslogic.controlers.UseCaseFacade;
import bussineslogic.objects.CustomList;

public class Action_delete_delete_custom_list  extends Action {
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {  
    	
    	
    	/** 1.  We obtain the user Information */
    	String usercode = UserUtils.getCurrentUsuarioId(request); 
    	Locale locale = UserUtils.getCurrentLocale(request); 
    	
		/** 2.  We set this page as a input point (see NavigationFunctions.setInputPoint function for more info). */
		NavigationFunctions.setReturnPoint(request, mapping.getPath());
		Action_load_customList_Form action_load_customList_Form = (Action_load_customList_Form) form;
		
		UseCaseFacade.DeleteCustomListElements(usercode, action_load_customList_Form.getCustomListcode());
		
		
		boolean isPopUp = NavigationFunctions.isPopUpWindow(request);
		/** 4.2.  We look for the forward to execute. */
		return NavigationFunctions.findForward(request, mapping, "success", isPopUp);
    }

}