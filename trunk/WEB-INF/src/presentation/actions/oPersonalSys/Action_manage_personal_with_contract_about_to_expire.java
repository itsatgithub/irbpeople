package presentation.actions.oPersonalSys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Collections;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import utils.formbeans.ToStringComparator;
import utils.actions.NavigationFunctions;
import utils.actions.BOAdderFunctions;
import utils.formbeans.FormBeanManager;
import utils.formbeans.BOAdderFormBean;
import utils.userUtils.UserUtils;
import utils.Pair;
import utils.listFilter.ViewListConfiguration;
import utils.beanUtils.ExtendedBeanUtils;
import utils.filter.ListConfigurator;

import bussineslogic.controlers.UseCaseFacade;

import bussineslogic.objects.*;
import presentation.formbeans.objects.*;

import presentation.formbeans.oPersonalSys.*;

/**
 * 
 * @author Automatika - JustInMind SL
 * 
 */
public class Action_manage_personal_with_contract_about_to_expire extends Action {

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
        NavigationFunctions.setReturnPoint(request, mapping.getPath());

        request.getSession().removeAttribute("personals_order");

        Pair<Integer, List<Personal>> _list_personal = UseCaseFacade.ObtainPersonalWithContractAboutToExpire(usercode,
                new ListConfigurator());

        /** 3.2.3. We obtain copy the list of pojos to a list of FormBeans */

        List<Personal_Form> viewlistElements = ExtendedBeanUtils.copyPropertiesToFormBean(_list_personal.getSecond(),
                locale, Personal_Form.class);

        request.setAttribute("viewlistElements", viewlistElements);
        /** 3.2.4. We put the 'real' number of elements (without pagination) */

        // request.setAttribute("viewListTotalElements",
        // _list_personal.getFirst());

        Pair<Integer, List<Personal>> _list_personal_2 = UseCaseFacade.ObtainPersonalWithGrantAboutToExpire(usercode,
                new ListConfigurator());

        /** 3.2.3. We obtain copy the list of pojos to a list of FormBeans */

        List<Personal_Form> viewlistElements_2 = ExtendedBeanUtils.copyPropertiesToFormBean(_list_personal_2
                .getSecond(), locale, Personal_Form.class);

        request.setAttribute("viewlistElements2", viewlistElements_2);
        /** 3.2.4. We put the 'real' number of elements (without pagination) */

        // request.setAttribute("viewListTotalElements2",
        // _list_personal_2.getFirst());

        /**
         * 3.3. We add the values of popup-selectables (if any) to the request:
         * we need to put the value of the to-string method, because the
         * FormBean may only store be the code
         */

        /** 4. We navigate to the correct page. */

        return NavigationFunctions.findForward(request, mapping, "success");

    }
}