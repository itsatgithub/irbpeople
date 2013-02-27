package presentation.actions.oPersonalSys;

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
public class Action_cancel_modify_modify_page_personal_comment extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        /**
         * 4. We return to the correct page (introducing some values to the
         * request if necessary)
         */

        /** 4.1. We look if the current page is beeing opened in a popup window. */
        boolean isPopUp = NavigationFunctions.isPopUpWindow(request);
        /** 4.2. We look for the forward to execute. */
        return NavigationFunctions.findForward(request, mapping, "success", isPopUp);

    }
}