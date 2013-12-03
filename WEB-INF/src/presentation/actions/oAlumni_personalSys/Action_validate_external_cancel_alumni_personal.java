package presentation.actions.oAlumni_personalSys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import utils.actions.NavigationFunctions;

/**
 * 
 * @author Automatika - JustInMind SL
 * 
 */
public class Action_validate_external_cancel_alumni_personal extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		boolean isPopUp = NavigationFunctions.isPopUpWindow(request);
		return NavigationFunctions.findForward(request, mapping, "success", isPopUp);

	}
}