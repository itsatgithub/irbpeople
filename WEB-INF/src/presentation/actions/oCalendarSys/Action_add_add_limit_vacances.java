package presentation.actions.oCalendarSys;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.formbeans.oCalendarSys.Action_add_add_limit_vacances_Form;
import uicomponents.scheduler.SchedulerFacadeForCommonControlsScheduler;
import utils.actions.NavigationFunctions;
import utils.dateformat.MultipleDateFormat;
import utils.formbeans.FormBeanManager;
import utils.userUtils.UserUtils;
import bussineslogic.controlers.UseCaseFacade;
import bussineslogic.excepciones.HolidaysException;
import bussineslogic.objects.Irbholiday;

/**
 * 
 * @author Factory - JustInMind SL
 * 
 */
public class Action_add_add_limit_vacances extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		/** 1. We obtain the user Information */
		String usercode = UserUtils.getCurrentUsuarioId(request);
		Locale locale = UserUtils.getCurrentLocale(request);

		/**
		 * 2. We set this page as a input point (see
		 * NavigationFunctions.setInputPoint function for more info).
		 */

		/**
		 * 3. We obtain the initial form bean and we put it to a new
		 * FormBeanManager.
		 */

		Action_add_add_limit_vacances_Form action_add_add_limit_vacances_Form = (Action_add_add_limit_vacances_Form) form;

		int year = SchedulerFacadeForCommonControlsScheduler.getViewYear(request);

		if (!validDate(action_add_add_limit_vacances_Form.getInitialdate(), year, locale)) {

			return NavigationFunctions.putActionError(request, mapping, "error.date");
		}

		FormBeanManager fbManager = new FormBeanManager(request, action_add_add_limit_vacances_Form);

		try {
			Irbholiday ih = (Irbholiday) fbManager.getPOJO("irbholiday_Form", Irbholiday.class);

			if (action_add_add_limit_vacances_Form.getIrbholidaycode() == null || action_add_add_limit_vacances_Form.getIrbholidaycode().equals("")) {

				ih.setEnddate(ih.getInitialdate());
				ih.setDescription("Limit vacances");
				ih.setType(Irbholiday.TYPE_LIMIT_VANCANCES_ANY_ANTERIOR);

				UseCaseFacade.CreateIrbholiday(usercode, ih);

			} else {

				ih.setEnddate(ih.getInitialdate());

				UseCaseFacade.UpdateIrbholiday(usercode, ih);
			}
		}
		catch (HolidaysException he) {

			return NavigationFunctions.putActionError(request, mapping, he.getMessage());
		}

		fbManager.cleanContainer();

		return NavigationFunctions.findForward(request, mapping, "success");

	}

	private boolean validDate(String date, int year, Locale l) {

		try {
			Date in = new MultipleDateFormat().parse(date, l);
			Date min = new GregorianCalendar(year, 0, 1).getTime();
			Date max = new GregorianCalendar(year, 11, 31).getTime();

			return (in.compareTo(min) >= 0 && in.compareTo(max) <= 0);

		}
		catch (Exception e) {
			return true;
		}
	}
}