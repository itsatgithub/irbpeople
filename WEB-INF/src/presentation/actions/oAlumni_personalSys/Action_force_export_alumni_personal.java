package presentation.actions.oAlumni_personalSys;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import utils.actions.NavigationFunctions;
import utils.userUtils.UserUtils;
import bussineslogic.controlers.UseCaseFacade;

/**
 * 
 * @author Automatika - JustInMind SL
 * 
 */
public class Action_force_export_alumni_personal extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		/** 1. We obtain the user Information */
		String usercode = UserUtils.getCurrentUsuarioId(request);
		UseCaseFacade.ForceExportAlumni(usercode);

		// Delete session audit messages
		request.getSession().setAttribute(UserUtils.ATT_AUDITMSG_DATE, new Date());
		request.getSession().setAttribute(UserUtils.ATT_SESSION_SAVED_AUDITMSG, null);

		boolean isPopUp = NavigationFunctions.isPopUpWindow(request);

		// return new ActionForward("/other/action_appHome.do");
		return NavigationFunctions.findForward(request, mapping, "success", isPopUp);

	}
}