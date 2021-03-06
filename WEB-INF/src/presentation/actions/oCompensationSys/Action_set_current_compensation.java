package presentation.actions.oCompensationSys;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.formbeans.oCompensationSys.Action_delete_page_compensation_Form;
import presentation.formbeans.oProfessionalSys.Action_delete_page_professional_Form;
import utils.actions.NavigationFunctions;
import utils.formbeans.FormBeanManager;
import utils.userUtils.UserUtils;
import bussineslogic.controlers.UseCaseFacade;


/**
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Action_set_current_compensation extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
    throws Exception {                  

        /** 1.  We obtain the user Information */
        String usercode = UserUtils.getCurrentUsuarioId(request); 
        




        /** 2.  We set this page as a input point (see NavigationFunctions.setInputPoint function for more info). */
        NavigationFunctions.setInputPoint(request, mapping.getPath());

        /** 3.  We obtain the initial form bean and we put it to a new FormBeanManager. */

        Action_delete_page_compensation_Form action_delete_page_compensation_Form=(Action_delete_page_compensation_Form) form;

        FormBeanManager fbManager=new FormBeanManager(request, action_delete_page_compensation_Form);

        /** 4.  We obtain the initial POJO. */

        String personalcode = UseCaseFacade.SetCurrentCompensation(usercode, action_delete_page_compensation_Form.getCompensationcode());

        /** 5.  In case a BOAdder exists in the following page, we reed the info from the BOAdder. */



        /** 4.  We clean the current container */
        fbManager.cleanContainer();

        /** 5.  We return to the correct page (introducing some values to the request if necessary) */


        
        /** 6.1.  We look if the current page is beeing opened in a popup window. */
        boolean isPopUp = NavigationFunctions.isPopUpWindow(request);
        /** 6.2.  We look for the forward to execute (we put the current object in case it is used in the next page) */

        return NavigationFunctions.findForward(request, mapping, "success", "personalcode=" + personalcode, isPopUp, personalcode, personalcode);
            
        }
}