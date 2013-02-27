package presentation.actions.customList;

import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import utils.Pair;
import utils.actions.NavigationFunctions;
import utils.excel.XLSManager;
import utils.filter.ListConfigurator;
import utils.listFilter.ViewListConfiguration;
import utils.userUtils.UserUtils;
import bussineslogic.controlers.UseCase;
import bussineslogic.objects.Usuario;

import com.justinmind.helper.composition.report.ReportComObject;
import com.justinmind.helper.composition.report.ReportComposition;
import com.justinmind.helper.composition.report.ReportCompositionFactory;

public class Action_export_excel_custom_list extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {

	/**
	 * PRE: At least 1 column is selected by the user. This is checked in
	 * the jsp, in the Js function validate_execution (formUtils.js)
	 **/

	/** 1. We obtain the user Information */
	String usercode = UserUtils.getCurrentUsuarioId(request);
	Locale locale = UserUtils.getCurrentLocale(request);

	/**
	 * 2. We set this page as an input point (see
	 * NavigationFunctions.setInputPoint function for more info).
	 */
	NavigationFunctions.setReturnPoint(request, mapping.getPath());

	ViewListConfiguration filter = (ViewListConfiguration) request
		.getSession().getAttribute("filterView");

	String customListCode = (String) request.getSession().getAttribute(
		"CustomListCode");
	String fromModifyCustomList = (String) request.getSession()
		.getAttribute("FromModifyCustomList");

	List<String[]> tableContent = null;
	String viewName = null;
	ListConfigurator list_config = (ListConfigurator) request.getSession()
		.getAttribute("listconfigfilter");
	String[] column_list = null;
	String[] orderby_list = null;

	viewName = (String) request.getSession().getAttribute("viewName");

	if (list_config == null) {
	    list_config = filter.obtainListConfiguratorForViews(locale);
	}
	column_list = (String[]) request.getSession().getAttribute(
		"columnsView");
	orderby_list = (String[]) request.getSession().getAttribute(
		"orderByView");
	String listdate = (String) request.getSession()
		.getAttribute("listdate");

	/** Obtain data from the FormBean **/
	/** Obtain special ListConfigurator **/
	/** Execute UseCase to obtain the list of objects from the BL **/

	Pair<Integer, List<Object[]>> listView = UseCase.ObtainCustomListData(
		UseCase.getUsuario(usercode), viewName, list_config,
		column_list, orderby_list, listdate);

	/** convert the objects list in a List<string[]> **/

	XLSManager xlsm = new XLSManager();

	Usuario user = UserUtils.getCurrentUsuario(request);

	String userLang = user.getLanguage().getLanguagecode();
	org.apache.log4j.Category log_for_filter = org.apache.log4j.Logger
		.getLogger(HttpServletRequest.class);

	ReportCompositionFactory rcf = (ReportCompositionFactory) request
		.getSession().getServletContext().getAttribute("rcf");
	ReportComposition rc = null;

	try {
	    rc = rcf.getReportComposition("irb_rrhh");
	} catch (Exception e) {
	    log_for_filter.warn(e);
	}

	// look up which view must be shown depending on which action
	// calls the actionNewListing action

	// if its called by itself because we want to change the view:

	ReportComObject rco = rc.getReport(viewName);

	// ******************** SELECT SECTION ***********************
	// ***********************************************************

	// we fill a hashtable with the (fieldname, label) pair
	// so we can easyly find which label corresponds to the name used
	// in the beans (the fieldname)
	Vector defaultSelectFieldNames = rco.getSelect().getVisibleFieldNames();
	Vector defaultSelectLabels = rco.getSelect().getVisibleLabels(userLang);
	Hashtable selectFields = new Hashtable();
	Iterator itNames = defaultSelectFieldNames.iterator();
	Iterator itLabels = defaultSelectLabels.iterator();
	while (itNames.hasNext()) {
	    selectFields.put((String) itNames.next(), (String) itLabels.next());
	}

	// Vector column_names = new Vector();
	String[] column_labels = new String[column_list.length];
	String auxname, auxlabel;

	// we lookup which fields must be shown in the right <select> (the
	// ones which are chosen to use in the saved report)
	String[] rightFields = column_list;

	// and we fill the Vectors containing the fieldnames and labels
	// of the right side <select> using the hashtable to find the labels
	itNames = defaultSelectFieldNames.iterator();
	itLabels = defaultSelectLabels.iterator();
	for (int i = 0; i < rightFields.length; i++) {

	    column_labels[i] = (String) selectFields.get(rightFields[i]);
	}

	response.setContentType("application/vnd.ms-excel");
	response.setHeader("Content-Disposition", "attachment; filename="
		+ viewName + ".xls");
	OutputStream out = response.getOutputStream();

	if (listView.getSecond() != null) {

	    xlsm.createXLSNoBeans(listView.getSecond().toArray(), viewName,
		    column_list, column_labels, out);
	}

	/** Insert filter data into the session **/

	/** 4. We navigate to the correct page. */

	return null;

    }
}