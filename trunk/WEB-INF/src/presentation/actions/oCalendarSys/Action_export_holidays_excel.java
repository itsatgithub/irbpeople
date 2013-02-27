package presentation.actions.oCalendarSys;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.formbeans.oCalendarSys.Action_export_holidays_Form;
import utils.Pair;
import utils.actions.NavigationFunctions;
import utils.dateformat.MultipleDateFormat;
import utils.excel.XLSManager;
import utils.userUtils.UserUtils;
import bussineslogic.controlers.UseCaseFacade;
import bussineslogic.objects.Irbholiday;
import bussineslogic.objects.Personal;

public class Action_export_holidays_excel extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		/**PRE: At least 1 column is selected by the user. 
		 * 		This is checked in the jsp, in the Js function validate_execution (formUtils.js) **/

		/** 1.  We obtain the user Information */
		String usercode = UserUtils.getCurrentUsuarioId(request);
		Locale locale = UserUtils.getCurrentLocale(request);

		/** 2.  We set this page as an input point (see NavigationFunctions.setInputPoint function for more info). */
		NavigationFunctions.setReturnPoint(request, mapping.getPath());

		Action_export_holidays_Form ehf = (Action_export_holidays_Form) form;

		String startDateString = ehf.getStart_date();
		String endDateString = ehf.getEnd_date();

		MultipleDateFormat mdf = new MultipleDateFormat();

		Date startDate = mdf.parse(startDateString, locale);
		Date endDate = mdf.parse(endDateString, locale);

		if (startDate.compareTo(endDate) > 0) {
			return NavigationFunctions.putActionError(request, mapping, "error.dates");

		}

		List<Irbholiday> holidays = UseCaseFacade.ObtainHolidaysBetweenDates(startDate, endDate);

		Pair<Integer, List<Personal>> _list_personal;

		if (ehf.getUnit() != null && ehf.getUnit().getUnitcode() != null) {
			_list_personal = UseCaseFacade.ObtainPersonalWithContractByUnit(usercode, ehf.getUnit().getUnitcode());
		} else {
			_list_personal = UseCaseFacade.ObtainPersonalWithContract(usercode, null);
		}

		//		Pair<Integer,List<Object[]>> listView = UseCase.ObtainCustomListData(UseCase.getUsuario(usercode), 
		//				viewName, 
		//				list_config,
		//				column_list,
		//				orderby_list
		//		);

		XLSManager xlsm = new XLSManager();

		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=holidays.xls");
		OutputStream out = response.getOutputStream();

		xlsm.createHolidaysXLS(startDate, endDate, _list_personal, holidays, out);

		//		if (listView.getSecond() != null){
		//
		//			xlsm.createXLSNoBeans(listView.getSecond().toArray(),viewName, column_list, column_labels, out);
		//		}

		return null;

	}
}