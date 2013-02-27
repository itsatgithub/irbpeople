package presentation.actions.customList;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.formbeans.customList.Action_execute_list_Form;
import utils.Pair;
import utils.actions.NavigationFunctions;
import utils.filter.ListConfigurator;
import utils.listFilter.ViewListConfiguration;
import utils.userUtils.UserUtils;
import bussineslogic.controlers.UseCase;
import bussineslogic.controlers.UseCaseFacade;
import bussineslogic.objects.Column;
import bussineslogic.objects.CustomList;
import bussineslogic.objects.Filter;
import bussineslogic.objects.OrderBy;

/**
 * 
 * @author Automatika - JustInMind SL
 * 
 */
public class Action_execute_list extends Action {

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
		.getAttribute("viewListConfiguration");

	Action_execute_list_Form action_execute_List_Form = (Action_execute_list_Form) form;

	String customListCode = (String) request.getSession().getAttribute(
		"CustomListCode");
	String fromModifyCustomList = (String) request.getSession()
		.getAttribute("FromModifyCustomList");

	List<String[]> tableContent = null;
	String viewName = null;
	ListConfigurator list_config = null;
	String[] column_list = null;
	String[] orderby_list = null;

	if (customListCode != null
		&& !customListCode.equals("")
		&& (fromModifyCustomList == null || !fromModifyCustomList
			.equals("true"))) {
	    // we are executing previously saved and not modified list

	    // obtenir vista

	    CustomList customlist = UseCaseFacade.ObtainCustomList(usercode,
		    customListCode);

	    // obtenir els filtres
	    // use case obtainallfrom per a filter, column i orderby i les seves
	    // facades
	    // obtenir les columnes i orderbys inserir-les a List<String>

	    List<Filter> filter_object_list = UseCaseFacade
		    .ObtainAllFiltersFrom(usercode, customListCode);
	    List<Column> column_object_list = UseCaseFacade
		    .ObtainAllColumnsFrom(usercode, customListCode);
	    List<OrderBy> orderby_object_list = UseCaseFacade
		    .ObtainAllOrderBysFrom(usercode, customListCode);

	    viewName = customlist.getView_name();
	    list_config = UseCase.CreateListConfiguratorFromFilterList(
		    filter_object_list, locale);
	    column_list = UseCase.CreateListFromColumnList(column_object_list);
	    orderby_list = UseCase
		    .CreateListFromOrderByList(orderby_object_list);

	    request.getSession().setAttribute("listconfigfilter", list_config);

	} else { // we are executing a new list or a modified list from a
		 // previously saved one

	    viewName = action_execute_List_Form.getView_name();

	    if (viewName.equals("view_personal_professional_date")) {

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date now = new Date();
		String nowString = df.format(now);

		if (action_execute_List_Form.getListdate() != null
			&& !action_execute_List_Form.getListdate().equals("")) {
		    filter.addFilter("listdate_ini_date",
			    action_execute_List_Form.getListdate());
		} else {
		    filter.addFilter("listdate_ini_date", nowString);
		}

		filter.addFilter("listdate_compareType_date", "equal");
	    }

	    list_config = filter.obtainListConfiguratorForViews(locale);
	    column_list = action_execute_List_Form.getColumns();
	    orderby_list = action_execute_List_Form.getOrderBy();
	    request.getSession().setAttribute("listconfigfilter", null);
	}

	/** Obtain data from the FormBean **/
	/** Obtain special ListConfigurator **/
	/** Execute UseCase to obtain the list of objects from the BL **/

	Pair<Integer, List<Object[]>> listView = UseCase.ObtainCustomListData(
		UseCase.getUsuario(usercode), viewName, list_config,
		column_list, orderby_list, null);

	/** convert the objects list in a List<string[]> **/

	tableContent = UseCase.ProcessListData(listView.getSecond(),
		column_list, locale);

	/** Insert filter data into the session **/

	request.getSession().setAttribute("filterView", filter);
	request.getSession().setAttribute("columnsView", column_list);
	request.getSession().setAttribute("orderByView", orderby_list);
	request.getSession().setAttribute("viewName", viewName);
	request.getSession().setAttribute("listdate",
		action_execute_List_Form.getListdate());

	/** Insert the data into the request **/

	request.setAttribute("customList__action_execute_List_Form",
		action_execute_List_Form);
	request.setAttribute("tableContent", tableContent);

	request.setAttribute("usercode", usercode);

	/** 4. We navigate to the correct page. */

	return NavigationFunctions.findForward(request, mapping, "success");

    }
}