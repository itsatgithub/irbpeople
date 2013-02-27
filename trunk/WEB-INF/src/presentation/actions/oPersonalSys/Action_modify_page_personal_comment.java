package presentation.actions.oPersonalSys;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.formbeans.oPersonalSys.Action_modify_page_personal_comment_Form;
import presentation.formbeans.objects.Personal_comment_Form;
import utils.actions.NavigationFunctions;
import utils.formbeans.FormBeanManager;
import utils.userUtils.UserUtils;
import bussineslogic.controlers.UseCaseFacade;
import bussineslogic.objects.Personal_comment;

/**
 * 
 * @author Automatika - JustInMind SL
 * 
 */
public class Action_modify_page_personal_comment extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

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

        Action_modify_page_personal_comment_Form action_modify_page_personal_comment_Form = (Action_modify_page_personal_comment_Form) form;

        FormBeanManager fbManager = new FormBeanManager(request, action_modify_page_personal_comment_Form);

        /** 4. We obtain the initial POJO. */

        Personal_comment personal_comment = UseCaseFacade.ObtainPersonal_comment(usercode,
                action_modify_page_personal_comment_Form.getPersonal_commentcode());

        /**
         * 5. In case a BOAdder exists in the following page, we reed the info
         * from the BOAdder.
         */

        /** 6. We Prepare the values that will be used in the next jsp */
        /** 6.1. We create a new FormBeanManager (and delete the old one) */

        fbManager.cleanContainer();

        fbManager = new FormBeanManager(request, Action_modify_page_personal_comment_Form.class);

        /**
         * 6.2. We add the current POJO merged with the nested-pojo-form-bean to
         * the manager.
         */

        fbManager.setAttribute(personal_comment, "personal_comment_Form", Personal_comment_Form.class,
                action_modify_page_personal_comment_Form.getPersonal_comment_Form());

        /** 6.3. We add the container of the manager to the request. */

        request
                .setAttribute("opersonal_commentSys__action_modify_page_personal_comment_Form", fbManager
                        .getContainer());

        

        /** 7. We navigate to the correct page. */

        return NavigationFunctions.findForward(request, mapping, "success");

    }
}