package presentation.actions.oPersonalSys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Collections;
import java.util.Map;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

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
import bussineslogic.excepciones.ValidationFailedException;

import bussineslogic.objects.*;
import presentation.formbeans.objects.*;

import presentation.formbeans.oPersonalSys.*;

/**
 * 
 * @author Automatika - JustInMind SL
 * 
 */
public class Action_modify_modify_page_personal extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        /** 1. We obtain the user Information */
        String usercode = UserUtils.getCurrentUsuarioId(request);
        Locale locale = UserUtils.getCurrentLocale(request);

        /**
         * 2. We obtain the initial form bean and we put it to a new
         * FormBeanManager.
         */

        Action_modify_modify_page_personal_Form action_modify_modify_page_personal_Form = (Action_modify_modify_page_personal_Form) form;

        FormBeanManager fbManager = new FormBeanManager(request, action_modify_modify_page_personal_Form);

        /** 3. We use the business logic to modify the new item */

        Personal personal = null;

        try {
            personal = UseCaseFacade.UpdatePersonal(usercode, (Personal) fbManager.getPOJO("personal_Form",
                    Personal.class));
        } catch (ValidationFailedException vfe) {
            return NavigationFunctions.putActionError(request, mapping, vfe.getParameters());
        }

//        /**
//         * 4. In case that a BOAdder exists in the previous jsp, we update the
//         * list of values using the business logic.
//         */
//
//        /**
//         * 4.1. We obtain the new list of items of the BOAdder (the one recieved
//         * from the formBean
//         */
//
//        List<Child> listFromBOAdder = fbManager.getPOJOBOAdderList("personal_child");
//        /**
//         * 4.2. We obtain the list of current items of the BOAdder (the list
//         * form the Business Logic)
//         */
//
//        List<Child> currentList = UseCaseFacade.ObtainAllIchild_personalFromPersonal(usercode,
//                action_modify_modify_page_personal_Form.getPersonalcode(), new ListConfigurator()).getSecond();
//
//        /** 4.3. Using both lists we obtain the new objects) */
//
//        List<Child> newPersonal_child = BOAdderFunctions.obtainNewObjects(listFromBOAdder);
//        /** 4.4. Using both lists we obtain the deleted objects) */
//
//        List<Child> deletedPersonal_child = BOAdderFunctions.obtainRemovedObjects(listFromBOAdder, currentList);
//
//        /** 4.5. We create the new objects) */
//
//        for (Child currentNew : newPersonal_child) {
//            /** 4.5.1. We erase the fake code */
//            currentNew.setCode(null);
//            /**
//             * 4.5.2. We set the code of the associated object to the correct
//             * one (it is only needed in case that this is a action of type new;
//             * therefore this code has been created now)
//             */
//
//            currentNew.setChild_personal(personal);
//            /** 4.5.3. We create the new instance using the business logic) */
//
//            UseCaseFacade.CreateChild(usercode, currentNew);
//        }
//
//        /** 4.6. We remove the deleted objects) */
//
//        for (Child currentDelete : deletedPersonal_child) {
//            /** 4.6.1. we remove the object using th business logic) */
//            UseCaseFacade.RemoveChild(usercode, currentDelete.getChildcode());
//        }

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

        return NavigationFunctions.findForward(request, mapping, "success", "personalcode="
                + personal.getPersonalcode(), isPopUp, personal.getCode(), personal.toString());

    }
}