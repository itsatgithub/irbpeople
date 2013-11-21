package presentation.actions.oAlumni_paramsSys;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.formbeans.oAlumni_paramsSys.Action_modify_page_alumni_params_Form;
import presentation.formbeans.objects.Alumni_params_Form;
import utils.actions.NavigationFunctions;
import utils.formbeans.FormBeanManager;
import utils.userUtils.UserUtils;
import bussineslogic.controlers.UseCaseFacade;
import bussineslogic.objects.Alumni_params;

/**
 * 
 * @author Automatika - JustInMind SL
 * 
 */
public class Action_modify_page_alumni_params extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		/** 1. We obtain the user Information */
		String usercode = UserUtils.getCurrentUsuarioId(request);
		Locale locale = UserUtils.getCurrentLocale(request);

		/**
		 * 2. We set this page as a input point (see
		 * NavigationFunctions.setInputPoint function for more info).
		 */
		NavigationFunctions.setInputPoint(request, mapping.getPath());

		/**
		 * 3. We obtain the initial form bean and we put it to a new
		 * FormBeanManager.
		 */

		Action_modify_page_alumni_params_Form action_modify_page_alumni_params_Form = (Action_modify_page_alumni_params_Form) form;

		//Hardcoded param to edit
		action_modify_page_alumni_params_Form.setAlumni_paramscode("EXPORT_MIN_DAYS");
		
		FormBeanManager fbManager = new FormBeanManager(request, action_modify_page_alumni_params_Form);

		/** 4. We obtain the initial POJO. */

		Alumni_params alumni_params = UseCaseFacade.ObtainAlumni_params(usercode, action_modify_page_alumni_params_Form.getAlumni_paramscode());

		/**
		 * 5. In case a BOAdder exists in the following page, we reed the info
		 * from the BOAdder.
		 */

		/** 6. We Prepare the values that will be used in the next jsp */
		/** 6.1. We create a new FormBeanManager (and delete the old one) */

		fbManager.cleanContainer();

		fbManager = new FormBeanManager(request, Action_modify_page_alumni_params_Form.class);

		/**
		 * 6.2. We add the current POJO merged with the nested-pojo-form-bean to
		 * the manager.
		 */

		fbManager.setAttribute(alumni_params, "alumni_params_Form", Alumni_params_Form.class, action_modify_page_alumni_params_Form.getAlumni_params_Form());

		/** 6.3. We add the container of the manager to the request. */

		request.setAttribute("oAlumni_paramsSys__action_modify_page_alumni_params_Form", fbManager.getContainer());

		/**
		 * 6.4. We add the values of all (if any) of the support tables which
		 * will be used in the jsp (for selectables) to the request.
		 */

		/**
		 * 6.5. We add the values of the the view-lists (if any) to the request.
		 */

		/** 6.6. We add the values of the BOAdder (if any) to the request. */

		/**
		 * 6.7. We add the values of popup-selectables (if any) to the request:
		 * we need to put the value of the to-string method, because the
		 * FormBean may only store be the code
		 */

		/** 7. We navigate to the correct page. */

		return NavigationFunctions.findForward(request, mapping, "success");

	}
}