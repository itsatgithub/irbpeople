package presentation.actions.oAlumni_personalSys;

import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.formbeans.oAlumni_personalSys.Action_validate_external_modify_modify_page_alumni_personal_Form;
import utils.actions.NavigationFunctions;
import utils.formbeans.FormBeanManager;
import utils.userUtils.UserUtils;
import bussineslogic.controlers.UseCaseFacade;
import bussineslogic.objects.Alumni_personal;

/**
 * 
 * @author Automatika - JustInMind SL
 * 
 */
public class Action_validate_external_modify_modify_page_alumni_personal extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		/** 1. We obtain the user Information */
		String usercode = UserUtils.getCurrentUsuarioId(request);
		Locale locale = UserUtils.getCurrentLocale(request);

		/**
		 * 2. We obtain the initial form bean and we put it to a new
		 * FormBeanManager.
		 */

		Action_validate_external_modify_modify_page_alumni_personal_Form action_validate_external_modify_modify_page_alumni_personal_Form = (Action_validate_external_modify_modify_page_alumni_personal_Form) form;

		FormBeanManager fbManager = new FormBeanManager(request, action_validate_external_modify_modify_page_alumni_personal_Form);

		/** 3. We use the business logic to modify the new item */
		String personalcode = ((Alumni_personal) fbManager.getPOJO("external_alumni_personal_Form", Alumni_personal.class)).getCode();
		Alumni_personal external_alumni = UseCaseFacade.ObtainAlumni_personal(usercode, personalcode);

		personalcode = ((Alumni_personal) fbManager.getPOJO("alumni_personal_Form", Alumni_personal.class)).getCode();
		
		if (personalcode.length() > 0) {
			Alumni_personal alumni = UseCaseFacade.ObtainAlumni_personal(usercode, personalcode);
			UseCaseFacade.ValidateAlumniPersonal(usercode, external_alumni, alumni);
		}

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

		return NavigationFunctions.findForward(request, mapping, "success", "alumni_personalcode=" + external_alumni.getAlumni_personalcode(), isPopUp, external_alumni.getCode(), external_alumni.toString());

	}
}