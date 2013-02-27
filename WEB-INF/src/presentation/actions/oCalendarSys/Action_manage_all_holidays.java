package presentation.actions.oCalendarSys;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.formbeans.oCalendarSys.Action_manage_all_holidays_Form;
import presentation.formbeans.objects.Irbholidayinfo_Form;
import utils.Pair;
import utils.actions.NavigationFunctions;
import utils.beanUtils.ExtendedBeanUtils;
import utils.listFilter.ViewListConfiguration;
import utils.userUtils.UserUtils;
import bussineslogic.controlers.UseCase;
import bussineslogic.controlers.UseCaseFacade;
import bussineslogic.objects.Irbholidayinfo;
import bussineslogic.objects.Personal;
import bussineslogic.objects.Professional;

/**
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Action_manage_all_holidays extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		/** 1.  We obtain the user Information */
		String usercode = UserUtils.getCurrentUsuarioId(request);
		Locale locale = UserUtils.getCurrentLocale(request);

		/** 2.  We set this page as a input point (see NavigationFunctions.setInputPoint function for more info). */
		NavigationFunctions.setReturnPoint(request, mapping.getPath());

		/** 3.  We Prepare the values that will be used in the next jsp */
		/** 3.1.  We add the values of all (if any) of the support tables which will be used in the jsp (for selectables) to the request. */

		Action_manage_all_holidays_Form action_manage_all_holidays_Form = (Action_manage_all_holidays_Form) form;

		if (action_manage_all_holidays_Form.getYear() == null || action_manage_all_holidays_Form.getYear().equals("")) {
			String s = (String) request.getSession().getAttribute("year");
			if (s == null || s.equals(""))
				action_manage_all_holidays_Form.setYear("" + Calendar.getInstance().get(Calendar.YEAR));
			else
				action_manage_all_holidays_Form.setYear(s);
		}

		String irbCodeString = ResourceBundle.getBundle("MainConfiguration").getString("irbPayrollInstitutionCode");

		String yearPage = action_manage_all_holidays_Form.getYear();
		int currentYearForHolidays = UseCase.getCurrentYearForHolidays();

		request.getSession().setAttribute("year", action_manage_all_holidays_Form.getYear());

		/** 3.2.  We add the values of the the view-lists (if any) to the request. */

		/** 3.2.1.  We obtain the current ViewListConfiguration from the request */
		ViewListConfiguration filter = (ViewListConfiguration) request.getAttribute("viewListConfiguration");
		/** 3.2.2.  We obtain the list of objects form the Business logic */

		/*		
		Pair<Integer, List<Irbholidayinfo>> _list_irbholidayinfo = UseCaseFacade.ObtainAllIrbholidayinfoFromYear(usercode, Integer.parseInt(yearPage), filter.obtainListConfigurator(locale, false));

		 *//** 3.2.3.  We obtain copy the list of pojos to a list of FormBeans */
		/*

		List<Irbholidayinfo_Form> viewlistElements =ExtendedBeanUtils.copyPropertiesToFormBean(_list_irbholidayinfo.getSecond(), locale, Irbholidayinfo_Form.class);
		*/

		String orderProperty = filter.getList_config_orderby().getAttribute();

		String order = "personal.surname1";
		String orderSense = "asc";
		if (orderProperty != null) {
			order = filter.getList_config_orderby().getAttribute();
			orderSense = filter.getList_config_orderby().getAsc();

		}

		ViewListConfiguration.OrderBy oldOrderBy = filter.getList_config_orderby();
		filter.setList_config_orderby(filter.new OrderBy());

		List<Irbholidayinfo> irbholidayinfo = new ArrayList<Irbholidayinfo>();

		if (Integer.valueOf(yearPage) <= currentYearForHolidays) {
			Pair<Integer, List<Personal>> _list_personal = UseCaseFacade.ObtainPersonalWithContract(usercode, filter.obtainListConfigurator(locale, false));

			filter.setList_config_orderby(oldOrderBy);

			List<Personal> personals = _list_personal.getSecond();

			for (Personal personal : personals) {
				Irbholidayinfo holInfo =
						UseCaseFacade.ObtainIrbholidayinfoFromPersonal(usercode, personal.getCode(), new GregorianCalendar(Integer.parseInt(yearPage), 0, 1));

				//Si el holInfo no existe lo creo con la informaci�n del current professional
				//  pero esto s�lo funciona en el current year for holidays.
				if (holInfo == null) {
					Set<Professional> pros = personal.getIprofessional_personal();

					for (Professional pro : pros) {
						if (pro.isCurrent() && (pro.getPayroll_institution() != null && pro.getPayroll_institution().getCode().equals(irbCodeString))) {
							holInfo = UseCase.CreateOrModifyIrbholidayinfo(UserUtils.getCurrentUsuario(request), pro, Integer.valueOf(yearPage), -1);
							if (holInfo != null) {
								UseCase.RecalculateHolidaysFromPersonal(holInfo);
							} else {
								Logger log = org.apache.log4j.Logger.getLogger(Action_manage_all_holidays.class);
								log
										.debug("Personal: " + personal.getPersonalcode() + " has IRB contract but not holiday information for year " + yearPage + " and it could not be created.");
							}
							break;
						}
					}

				}
				if (holInfo != null) {
					irbholidayinfo.add(holInfo);
				}
			}
		}
		/** 3.2.3.  We obtain copy the list of pojos to a list of FormBeans */

		List<Irbholidayinfo_Form> viewlistElements = ExtendedBeanUtils.copyPropertiesToFormBean(irbholidayinfo, locale, Irbholidayinfo_Form.class);

		Collections.sort(viewlistElements, new BeansComparator(order, orderSense));

		request.setAttribute("viewlistElements", viewlistElements);

		request.setAttribute("currentYear", currentYearForHolidays);

		/** 3.3.  We add the values of popup-selectables (if any) to the request: we need to put the value of the to-string method, because the FormBean may only store be the code */

		/** 4.  We navigate to the correct page. */

		return NavigationFunctions.findForward(request, mapping, "success");

	}

	class BeansComparator implements Comparator<Object> {

		private String order;
		private String orderSense;

		public BeansComparator(String order, String orderSense) {
			this.order = order;
			this.orderSense = orderSense;
		}

		public int compare(Object arg0, Object arg1) {

			try {
				String value1 = BeanUtils.getProperty(arg0, order);
				String value2 = BeanUtils.getProperty(arg1, order);

				try {
					int v1 = Integer.parseInt(value1);
					int v2 = Integer.parseInt(value2);

					if (orderSense.equalsIgnoreCase("desc"))
						return v2 - v1;
					else
						return v1 - v2;
				}
				catch (NumberFormatException e) {}

				if (orderSense.equalsIgnoreCase("desc"))
					return value2.compareToIgnoreCase(value1);
				else
					return value1.compareToIgnoreCase(value2);
			}
			catch (Exception e) {
				e.printStackTrace();
			}

			return 0;
		}

	}
}