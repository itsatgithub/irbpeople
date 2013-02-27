package presentation.actions.oCalendarSys;

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
 * @author Factory - JustInMind SL
 * 
 */
public class Action_new_new_year extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        /** 1. We obtain the user Information */
        String usercode = UserUtils.getCurrentUsuarioId(request);

        UseCaseFacade.createNextYearHolidays(usercode);

        boolean isPopUp = NavigationFunctions.isPopUpWindow(request);

        return NavigationFunctions.findForward(request, mapping, "success", isPopUp);

    }
}