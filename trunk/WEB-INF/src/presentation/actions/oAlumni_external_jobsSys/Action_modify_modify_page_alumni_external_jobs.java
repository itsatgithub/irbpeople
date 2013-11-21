package presentation.actions.oAlumni_external_jobsSys;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.formbeans.oAlumni_external_jobsSys.Action_modify_modify_page_alumni_external_jobs_Form;
import utils.actions.NavigationFunctions;
import utils.formbeans.FormBeanManager;
import utils.userUtils.UserUtils;
import bussineslogic.controlers.UseCaseFacade;
import bussineslogic.objects.Alumni_external_jobs;

/**
 * 
 * @author Automatika - JustInMind SL
 * 
 */
public class Action_modify_modify_page_alumni_external_jobs extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		/** 1. We obtain the user Information */
		String usercode = UserUtils.getCurrentUsuarioId(request);
		Locale locale = UserUtils.getCurrentLocale(request);

		/**
		 * 2. We obtain the initial form bean and we put it to a new
		 * FormBeanManager.
		 */

		Action_modify_modify_page_alumni_external_jobs_Form action_modify_modify_page_alumni_external_jobs_Form = (Action_modify_modify_page_alumni_external_jobs_Form) form;

		FormBeanManager fbManager = new FormBeanManager(request,
				action_modify_modify_page_alumni_external_jobs_Form);

		/** 3. We use the business logic to modify the new item */

		Alumni_external_jobs alumni_external_jobs = UseCaseFacade.UpdateAlumni_external_jobs(
				usercode, (Alumni_external_jobs) fbManager.getPOJO(
						"alumni_external_jobs_Form", Alumni_external_jobs.class));

		/**
		 * 4. In case that a BOAdder exists in the previous jsp, we update the
		 * list of values using the business logic.
		 */

		/** 5. We clean the current container */
		fbManager.cleanContainer();

		/**
		 * 6. We return to the correct page (introducing some values to the
		 * request if necessary)
		 */

		/** 6.1. We look if the current page is beeing opened in a popup window. */
		boolean isPopUp = NavigationFunctions.isPopUpWindow(request);
		/**
		 * 6.2. We look for the forward to execute (we put the current object in
		 * case it is used in the next page)
		 */

		return NavigationFunctions.findForward(request, mapping, "success",
				"alumni_external_jobscode=" + alumni_external_jobs.getAlumni_external_jobscode(),
				isPopUp, alumni_external_jobs.getCode(), alumni_external_jobs.toString());

	}
}