package presentation.actions.oUser_accessSys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.formbeans.oUser_accessSys.Action_unlock_modify_modify_page_user_access_Form;
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
public class Action_unlock_modify_modify_page_user_access extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		/** 1. We obtain the user Information */
		String usercode = UserUtils.getCurrentUsuarioId(request);
		
		/**
		 * 2. We obtain the initial form bean and we put it to a new
		 * FormBeanManager.
		 */

		Action_unlock_modify_modify_page_user_access_Form action_unlock_modify_modify_page_user_access_Form = (Action_unlock_modify_modify_page_user_access_Form) form;
		FormBeanManager fbManager = new FormBeanManager(request, action_unlock_modify_modify_page_user_access_Form);

		/** 3. We use the business logic to modify the new item */
		User_access user_access = (User_access) fbManager.getPOJO("user_access_Form", User_access.class);
		String useraccess_code = user_access.getUsercode();
		user_access = UseCaseFacade.ObtainUser_access(usercode, useraccess_code);

		// useraccess_code = ((User_access)
		// fbManager.getPOJO("user_access_Form", User_access.class)).getCode();
		UseCaseFacade.UnlockUser_access(usercode, user_access.getUsercode());
		fbManager.cleanContainer();

		boolean isPopUp = NavigationFunctions.isPopUpWindow(request);

		return NavigationFunctions.findForward(request, mapping, "success", "user_accesscode=" + user_access.getUsercode(), isPopUp, user_access.getCode(), user_access.toString());

	}
}