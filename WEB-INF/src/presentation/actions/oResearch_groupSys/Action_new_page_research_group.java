package presentation.actions.oResearch_groupSys;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.formbeans.oResearch_groupSys.Action_new_page_research_group_Form;
import presentation.formbeans.objects.Personal_IDForm;
import presentation.formbeans.objects.Research_group_Form;
import presentation.formbeans.objects.Unit_Form;
import utils.Pair;
import utils.actions.NavigationFunctions;
import utils.beanUtils.ExtendedBeanUtils;
import utils.filter.ListConfigurator;
import utils.formbeans.FormBeanManager;
import utils.formbeans.ToStringComparator;
import utils.userUtils.UserUtils;
import bussineslogic.controlers.UseCaseFacade;
import bussineslogic.objects.Personal;
import bussineslogic.objects.Research_group;
import bussineslogic.objects.Unit;

/**
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Action_new_page_research_group extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		/** 1.  We obtain the user Information */
		String usercode = UserUtils.getCurrentUsuarioId(request);
		Locale locale = UserUtils.getCurrentLocale(request);

		/** 2.  We set this page as a input point (see NavigationFunctions.setInputPoint function for more info). */
		NavigationFunctions.setInputPoint(request, mapping.getPath());

		/** 3.  We obtain the initial form bean and we put it to a new FormBeanManager. */

		Action_new_page_research_group_Form action_new_page_research_group_Form = (Action_new_page_research_group_Form) form;

		FormBeanManager fbManager = new FormBeanManager(request, action_new_page_research_group_Form);

		/** 4.  We obtain the initial POJO. */

		Research_group research_group = null;

		/** 5.  In case a BOAdder exists in the following page, we reed the info from the BOAdder. */

		/** 6.  We Prepare the values that will be used in the next jsp */
		/** 6.1.  We create a new FormBeanManager (and delete the old one) */

		fbManager.cleanContainer();

		fbManager = new FormBeanManager(request, Action_new_page_research_group_Form.class);

		/** 6.2.  We add the current POJO merged with the nested-pojo-form-bean to the manager. */

		fbManager.setAttribute(research_group, "research_group_Form", Research_group_Form.class, action_new_page_research_group_Form.getResearch_group_Form());

		/** 6.3.  We add the container of the manager to the request. */

		request.setAttribute("oResearch_groupSys__action_new_page_research_group_Form", fbManager.getContainer());

		/** 6.4.  We add the values of all (if any) of the support tables which will be used in the jsp (for selectables) to the request. */

		/** 6.4.1.  We obtain the values form the business logic. */

		//Pair<Integer, List<Personal>> _selec_personal= UseCaseFacade.ObtainAllPersonal(usercode,  new ListConfigurator());
		///** 6.4.2.  We copy the values to a list of formbeans. */
		//
		//List<Personal_Form> _selec_personal_Form = ExtendedBeanUtils.copyPropertiesToFormBean(_selec_personal.getSecond(), locale, Personal_Form.class);
		///** 6.4.3.  We sort the list. */
		//Collections.sort(_selec_personal_Form, new ToStringComparator());
		///** 6.4.4.  We put the values to the request, so the jsp can get them. */
		//
		//request.setAttribute("_selec_personal", _selec_personal_Form);
		/** 6.4.5.  We obtain the values form the business logic. */

		Pair<Integer, List<Unit>> _selec_unit = UseCaseFacade.ObtainAllUnit(usercode, new ListConfigurator());
		/** 6.4.6.  We copy the values to a list of formbeans. */

		List<Unit_Form> _selec_unit_Form = ExtendedBeanUtils.copyPropertiesToFormBean(_selec_unit.getSecond(), locale, Unit_Form.class);
		/** 6.4.7.  We sort the list. */
		Collections.sort(_selec_unit_Form, new ToStringComparator());
		/** 6.4.8.  We put the values to the request, so the jsp can get them. */

		request.setAttribute("_selec_unit", _selec_unit_Form);

		/** 6.5.  We add the values of the the view-lists (if any) to the request. */

		/** 6.6.  We add the values of the BOAdder (if any) to the request. */

		/** 6.7.  We add the values of popup-selectables (if any) to the request: we need to put the value of the to-string method, because the FormBean may only store be the code */

		Personal _personalOfsupervisor = null;
		/** 6.7.1.  In case that the selector has a value, we obtain it form the business logic. */

		if (action_new_page_research_group_Form.getResearch_group_Form().getSupervisor().getPersonalcode() != null && !action_new_page_research_group_Form
				.getResearch_group_Form()
				.getSupervisor()
				.getPersonalcode()
				.equals("")) {

			_personalOfsupervisor =
					UseCaseFacade.ObtainPersonal(usercode, action_new_page_research_group_Form.getResearch_group_Form().getSupervisor().getPersonalcode());
		}
		/** 6.7.2.  We put the value to the form bean manager. */

		fbManager.setAttribute(_personalOfsupervisor, "supervisor", Personal_IDForm.class);

		/** 7.  We navigate to the correct page. */

		return NavigationFunctions.findForward(request, mapping, "success");

	}
}