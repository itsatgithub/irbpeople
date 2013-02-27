package presentation.actions.customList;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import utils.actions.NavigationFunctions;
import utils.listFilter.ViewListConfiguration;
import utils.userUtils.UserUtils;

public class Action_saveAs_custom_list extends Action {
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			
    	    	
    	String usercode = UserUtils.getCurrentUsuarioId(request); 
    	Locale locale = UserUtils.getCurrentLocale(request); 
    	
		/** 2.  We set this page as a input point (see NavigationFunctions.setInputPoint function for more info). */
		NavigationFunctions.setReturnPoint(request, mapping.getPath());
    	
    	
		return NavigationFunctions.findForward(request, mapping, "success");
    	
    }

}
