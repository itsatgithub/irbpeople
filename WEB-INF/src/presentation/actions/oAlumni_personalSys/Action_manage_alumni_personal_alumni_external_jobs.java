package presentation.actions.oAlumni_personalSys;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.formbeans.oAlumni_personalSys.Action_manage_alumni_personal_alumni_external_jobs_Form;
import presentation.formbeans.objects.Alumni_external_jobs_Form;
import presentation.formbeans.objects.Alumni_personal_Form;
import utils.Pair;
import utils.actions.NavigationFunctions;
import utils.beanUtils.ExtendedBeanUtils;
import utils.formbeans.FormBeanManager;
import utils.listFilter.ViewListConfiguration;
import utils.userUtils.UserUtils;
import bussineslogic.controlers.UseCaseFacade;
import bussineslogic.objects.Alumni_external_jobs;
import bussineslogic.objects.Alumni_personal;

/**
 * 
 * @author Automatika - JustInMind SL
 * 
 */
public class Action_manage_alumni_personal_alumni_external_jobs extends Action {

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

        Action_manage_alumni_personal_alumni_external_jobs_Form action_manage_alumni_personal_alumni_external_jobs_Form = (Action_manage_alumni_personal_alumni_external_jobs_Form) form;

        FormBeanManager fbManager = new FormBeanManager(request, action_manage_alumni_personal_alumni_external_jobs_Form);

        /** 4. We obtain the initial POJO. */

        Alumni_personal alumni_personal = UseCaseFacade.ObtainAlumni_personal(usercode, action_manage_alumni_personal_alumni_external_jobs_Form
                .getAlumni_personalcode());

        request.setAttribute("alumni_personalcode", alumni_personal.getCode());

        /**
         * 5. In case a BOAdder exists in the following page, we reed the info
         * from the BOAdder.
         */

        /** 6. We Prepare the values that will be used in the next jsp */
        /** 6.1. We create a new FormBeanManager (and delete the old one) */

        fbManager.cleanContainer();

        fbManager = new FormBeanManager(request, Action_manage_alumni_personal_alumni_external_jobs_Form.class);

        /**
         * 6.2. We add the current POJO merged with the nested-pojo-form-bean to
         * the manager.
         */

        fbManager.setAttribute(alumni_personal, "alumni_personal_Form", Alumni_personal_Form.class,
                action_manage_alumni_personal_alumni_external_jobs_Form.getAlumni_personal_Form());

        /** 6.3. We add the container of the manager to the request. */

        request.setAttribute("oAlumni_personalSys__action_manage_alumni_personal_alumni_external_jobs_Form", fbManager.getContainer());

        /**
         * 6.4. We add the values of all (if any) of the support tables which
         * will be used in the jsp (for selectables) to the request.
         */

        /**
         * 6.5. We add the values of the the view-lists (if any) to the request.
         */

        /** 6.5.1. We obtain the current ViewListConfiguration from the request */
        ViewListConfiguration filter = (ViewListConfiguration) request.getAttribute("viewListConfiguration");
        /** 6.5.2. We obtain the list of objects form the Business logic */

        Pair<Integer, List<Alumni_external_jobs>> _list_alumni_external_jobs = UseCaseFacade
                .ObtainAllIalumni_external_jobs_alumni_personalFromPersonal(usercode,
                        action_manage_alumni_personal_alumni_external_jobs_Form.getAlumni_personal_Form().getAlumni_personalcode(), filter
                                .obtainListConfigurator(locale, false));

        /** 6.5.3. We obtain copy the list of pojos to a list of FormBeans */

        List<Alumni_external_jobs_Form> viewlistElements = ExtendedBeanUtils.copyPropertiesToFormBean(
                _list_alumni_external_jobs.getSecond(), locale, Alumni_external_jobs_Form.class);

        request.setAttribute("viewlistElements", viewlistElements);

        
        /**
         * 6.7. We add the values of popup-selectables (if any) to the request:
         * we need to put the value of the to-string method, because the
         * FormBean may only store be the code
         */

        /** 7. We navigate to the correct page. */

        return NavigationFunctions.findForward(request, mapping, "success");

    }
}