package presentation.actions.oAlumni_personalSys;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.formbeans.oAlumni_personalSys.Action_modify_modify_page_alumni_personal_Form;
import utils.actions.NavigationFunctions;
import utils.formbeans.FormBeanManager;
import utils.userUtils.UserUtils;
import bussineslogic.controlers.UseCaseFacade;
import bussineslogic.excepciones.ValidationFailedException;
import bussineslogic.objects.Alumni_personal;

/**
 * 
 * @author Automatika - JustInMind SL
 * 
 */
public class Action_modify_modify_page_alumni_personal extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		/** 1. We obtain the user Information */
		String usercode = UserUtils.getCurrentUsuarioId(request);
		Locale locale = UserUtils.getCurrentLocale(request);

		/**
		 * 2. We obtain the initial form bean and we put it to a new
		 * FormBeanManager.
		 */

		Action_modify_modify_page_alumni_personal_Form action_modify_modify_page_alumni_personal_Form = (Action_modify_modify_page_alumni_personal_Form) form;

		FormBeanManager fbManager = new FormBeanManager(request, action_modify_modify_page_alumni_personal_Form);

		/** 3. We use the business logic to modify the new item */

		Alumni_personal alumni_personal = null;

		try {
			Alumni_personal alumni_personal_pojo = (Alumni_personal) fbManager.getPOJO("alumni_personal_Form", Alumni_personal.class);
			String[] ialumni_communications_array = action_modify_modify_page_alumni_personal_Form.getAlumni_personal_Form().getAlumni_communications();
			alumni_personal_pojo.clearIalumni_communications();
			for (int i = 0; i < ialumni_communications_array.length; i++) {
				String alumni_communicationscode = ialumni_communications_array[i];
				if (alumni_communicationscode.length() > 0) {
					alumni_personal_pojo.addIalumni_communications(UseCaseFacade.ObtainAlumni_communications(usercode, alumni_communicationscode));
				}
			}
			alumni_personal = UseCaseFacade.UpdateAlumni_personal(usercode, alumni_personal_pojo);
		} catch (ValidationFailedException vfe) {
			return NavigationFunctions.putActionError(request, mapping, vfe.getParameters());
		}

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

		return NavigationFunctions.findForward(request, mapping, "success", "alumni_personalcode=" + alumni_personal.getAlumni_personalcode(), isPopUp, alumni_personal.getCode(), alumni_personal.toString());

	}
}