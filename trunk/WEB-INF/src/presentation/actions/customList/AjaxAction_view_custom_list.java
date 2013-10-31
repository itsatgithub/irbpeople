package presentation.actions.customList;

import java.util.Locale;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.formbeans.customList.AjaxAction_view_custom_list_Form;
import utils.actions.NavigationFunctions;
import utils.reportFilter.ReportFilter;
import utils.userUtils.UserUtils;
import bussineslogic.objects.Usuario;

import com.justinmind.helper.composition.report.ReportComObject;
import com.justinmind.helper.composition.report.ReportComposition;
import com.justinmind.helper.composition.report.ReportCompositionFactory;

public class AjaxAction_view_custom_list extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		/** 1. We obtain the user Information */
		String usercode = UserUtils.getCurrentUsuarioId(request);
		Locale locale = UserUtils.getCurrentLocale(request);

		/**
		 * 2. We set this page as a input point (see
		 * NavigationFunctions.setInputPoint function for more info).
		 */
		NavigationFunctions.setReturnPoint(request, mapping.getPath());

		/** we obtain selected view form formbean **/
		AjaxAction_view_custom_list_Form ajaxAction_view_custom_list_Form = (AjaxAction_view_custom_list_Form) form;

		/** PRE: a view has been selected by the user or by default */

		String selected_view = ajaxAction_view_custom_list_Form.getSelected_view();

		/** we prepare information to create the fields ***/

		/** code from gescresa && (IRBpeople) filter.jsp **/

		Usuario user = UserUtils.getCurrentUsuario(request);

		String userLang = user.getLanguage().getLanguagecode();
		org.apache.log4j.Category log_for_filter = org.apache.log4j.Logger.getLogger(HttpServletRequest.class);

		ReportCompositionFactory rcf = (ReportCompositionFactory) request.getSession().getServletContext().getAttribute("rcf");
		ReportComposition rc = null;

		try {
			rc = rcf.getReportComposition("irb_rrhh");
		} catch (Exception e) {
			log_for_filter.warn(e);
		}

		// look up which view must be shown depending on which action
		// calls the actionNewListing action

		// if its called by itself because we want to change the view:
		String vistaName = selected_view;

		/**
		 * //if we are not changing the view, we take the view which we are
		 * working with: if(vistaName == null) vistaName = (String)
		 * request.getSession().getSessionValue("vistaName");
		 **/

		/**
		 * //if none of the previous, we take a default view if (vistaName ==
		 * null) { vistaName = "colaboradorbecaview"; }
		 **/

		// finally we get the reportComObject of the choosen view and set the
		// view in the session
		ReportComObject rco = rc.getReport(vistaName);

		/**
		 * request.setSessionValue("vistaName", (String) vistaName); if
		 * (request.getSessionValue("reportCategory") == null)
		 * request.setSessionValue("reportCategory", "rrhh");
		 **/

		// we prepare the parameters which will be needed by the
		// com.justinmind.jimtags.showCombos customTag
		Vector noFields = new Vector();

		// we look up for the SELECT section visible fields and labels
		Vector<String> SELvisibleFieldNames = rco.getSelect().getVisibleFieldNames();
		Vector<String> SELvisibleLabels = rco.getSelect().getVisibleLabels(userLang);

		ReportFilter.filterFieldsByProfile(user, SELvisibleFieldNames, SELvisibleLabels);

		request.setAttribute("SELleftFieldNames", (Vector) SELvisibleFieldNames);
		request.setAttribute("SELleftLabels", (Vector) SELvisibleLabels);

		// we insert empty Vectors in the right side <select> objects
		request.setAttribute("SELrightFieldNames", (Vector) noFields);
		request.setAttribute("SELrightLabels", (Vector) noFields);

		// WHERE params
		Boolean fillWhere = new Boolean(false);
		request.setAttribute("fillWhere", (Boolean) fillWhere);

		// ORDERBY params
		Vector ORDvisibleFieldNames = rco.getOrderBy().getVisibleFieldNames();
		Vector ORDvisibleLabels = rco.getOrderBy().getVisibleLabels(userLang);

		ReportFilter.filterFieldsByProfile(user, ORDvisibleFieldNames, ORDvisibleLabels);
		request.setAttribute("ORDleftFieldNames", (Vector) ORDvisibleFieldNames);
		request.setAttribute("ORDleftLabels", (Vector) ORDvisibleLabels);

		// we insert empty Vectors in the right side <select> objects
		request.setAttribute("ORDrightFieldNames", (Vector) noFields);
		request.setAttribute("ORDrightLabels", (Vector) noFields);

		request.setAttribute("view_name", vistaName);

		return NavigationFunctions.findForward(request, mapping, "success");

	}

}