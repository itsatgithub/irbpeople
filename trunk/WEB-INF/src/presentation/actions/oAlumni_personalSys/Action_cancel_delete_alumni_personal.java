package presentation.actions.oAlumni_personalSys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.formbeans.oAlumni_personalSys.Action_cancel_delete_alumni_personal_Form;
import utils.actions.NavigationFunctions;
import utils.formbeans.FormBeanManager;

/**
 * 
 * @author Automatika - JustInMind SL
 * 
 */
public class Action_cancel_delete_alumni_personal extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FormBeanManager fbManager = new FormBeanManager(request, (Action_cancel_delete_alumni_personal_Form) form);
		fbManager.cleanContainer();
		boolean isPopUp = NavigationFunctions.isPopUpWindow(request);
		return NavigationFunctions.findForward(request, mapping, "success", isPopUp);

	}
}