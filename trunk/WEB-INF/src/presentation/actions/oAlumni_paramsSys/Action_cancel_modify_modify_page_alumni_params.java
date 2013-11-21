package presentation.actions.oAlumni_paramsSys;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.formbeans.oAlumni_paramsSys.Action_cancel_modify_modify_page_alumni_params_Form;
import utils.actions.NavigationFunctions;
import utils.formbeans.FormBeanManager;
import utils.userUtils.UserUtils;

/**
 * 
 * @author Automatika - JustInMind SL
 * 
 */
public class Action_cancel_modify_modify_page_alumni_params extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		/** 1. We obtain the user Information */
		String usercode = UserUtils.getCurrentUsuarioId(request);
		Locale locale = UserUtils.getCurrentLocale(request);

		/**
		 * 2. We obtain the initial form bean and we put it to a new
		 * FormBeanManager.
		 */

		Action_cancel_modify_modify_page_alumni_params_Form action_cancel_modify_modify_page_alumni_params_Form = (Action_cancel_modify_modify_page_alumni_params_Form) form;

		FormBeanManager fbManager = new FormBeanManager(request, action_cancel_modify_modify_page_alumni_params_Form);

		/** 3. We clean the current container */
		fbManager.cleanContainer();

		/**
		 * 4. We return to the correct page (introducing some values to the
		 * request if necessary)
		 */

		/** 4.1. We look if the current page is beeing opened in a popup window. */
		boolean isPopUp = NavigationFunctions.isPopUpWindow(request);
		/** 4.2. We look for the forward to execute. */
		return new ActionForward("/other/action_appHome.do");
		// return NavigationFunctions.findForward(request, mapping,
		// "success",isPopUp);

	}
}