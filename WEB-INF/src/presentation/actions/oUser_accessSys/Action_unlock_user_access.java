package presentation.actions.oUser_accessSys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.formbeans.oUser_accessSys.Action_unlock_user_access_Form;
import presentation.formbeans.objects.User_access_Form;
import utils.actions.NavigationFunctions;
import utils.formbeans.FormBeanManager;
import utils.userUtils.UserUtils;
import bussineslogic.controlers.UseCaseFacade;
import bussineslogic.objects.User_access;

/**
 * 
 * @author Automatika - JustInMind SL
 * 
 */
public class Action_unlock_user_access extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String usercode = UserUtils.getCurrentUsuarioId(request);
		NavigationFunctions.setInputPoint(request, mapping.getPath());

		Action_unlock_user_access_Form action_unlock_user_access_Form = (Action_unlock_user_access_Form) form;
		FormBeanManager fbManager = new FormBeanManager(request, action_unlock_user_access_Form);

		String user_accesscode = action_unlock_user_access_Form.getUser_accesscode();
		User_access user_access = UseCaseFacade.ObtainUser_access(usercode, user_accesscode);

		fbManager.cleanContainer();

		fbManager = new FormBeanManager(request, Action_unlock_user_access_Form.class);
		
		User_access_Form user_access_form = action_unlock_user_access_Form.getUser_access_Form();		
		fbManager.setAttribute(user_access, "user_access_Form", User_access_Form.class, user_access_form);
		request.setAttribute("oUser_accessSys__action_unlock_user_access_Form", fbManager.getContainer());		

		return NavigationFunctions.findForward(request, mapping, "success");

	}
}