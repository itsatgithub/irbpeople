package presentation.actions.user;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import utils.actions.NavigationFunctions;
import utils.userUtils.UserUtils;
import presentation.formbeans.user.ChangePasswordForm;

public class DisplayChangePassword extends Action {

    @Override
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
       
    	//load data
    	String usercode = UserUtils.getCurrentUsuarioId(request); 
    	Locale locale = UserUtils.getCurrentLocale(request);
    	
    	ChangePasswordForm passwordForm = (ChangePasswordForm)form;
    	passwordForm.setPassword("");
    	passwordForm.setPassword2("");
    	    	    	
		return NavigationFunctions.findForward(request, mapping, "success");
    }
    
    
    
    

}