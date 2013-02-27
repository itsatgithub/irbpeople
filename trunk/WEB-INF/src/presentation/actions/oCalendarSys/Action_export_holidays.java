package presentation.actions.oCalendarSys;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.formbeans.objects.Unit_Form;
import utils.Pair;
import utils.actions.NavigationFunctions;
import utils.beanUtils.ExtendedBeanUtils;
import utils.filter.ListConfigurator;
import utils.formbeans.ToStringComparator;
import utils.userUtils.UserUtils;
import bussineslogic.controlers.UseCaseFacade;
import bussineslogic.objects.Unit;

/**
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Action_export_holidays extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		/** 1.  We obtain the user Information */
		String usercode = UserUtils.getCurrentUsuarioId(request);
		Locale locale = UserUtils.getCurrentLocale(request);

		/** 2.  We set this page as a input point (see NavigationFunctions.setInputPoint function for more info). */
		NavigationFunctions.setReturnPoint(request, mapping.getPath());

		/** 3.  We Prepare the values that will be used in the next jsp */
		/** 3.1.  We add the values of all (if any) of the support tables which will be used in the jsp (for selectables) to the request. */

		/** 3.2.  We add the values of the the view-lists (if any) to the request. */

		/** 3.3.  We add the values of popup-selectables (if any) to the request: we need to put the value of the to-string method, because the FormBean may only store be the code */

		/** 4.  We navigate to the correct page. */

		Pair<Integer, List<Unit>> _selec_unit = UseCaseFacade.ObtainAllUnit(usercode, new ListConfigurator());
		/** 6.4.18.  We copy the values to a list of formbeans. */

		List<Unit_Form> _selec_unit_Form = ExtendedBeanUtils.copyPropertiesToFormBean(_selec_unit.getSecond(), locale, Unit_Form.class);
		/** 6.4.19.  We sort the list. */
		Collections.sort(_selec_unit_Form, new ToStringComparator());
		/** 6.4.20.  We put the values to the request, so the jsp can get them. */

		request.setAttribute("_selec_unit", _selec_unit_Form);

		return NavigationFunctions.findForward(request, mapping, "success");

	}

}