package presentation.actions.customList;

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

import presentation.formbeans.customList.Action_load_customList_Form;
import presentation.formbeans.objects.CustomList_Form;
import utils.actions.NavigationFunctions;
import utils.beanUtils.ExtendedBeanUtils;
import utils.reportFilter.ReportFilter;
import utils.userUtils.UserUtils;
import bussineslogic.controlers.UseCase;
import bussineslogic.controlers.UseCaseFacade;
import bussineslogic.objects.Column;
import bussineslogic.objects.CustomList;
import bussineslogic.objects.Filter;
import bussineslogic.objects.OrderBy;
import bussineslogic.objects.Usuario;

import com.justinmind.helper.composition.report.ReportComObject;
import com.justinmind.helper.composition.report.ReportComposition;
import com.justinmind.helper.composition.report.ReportCompositionFactory;

public class Action_modify_custom_list extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {

	/** 1. We obtain the user Information */
	String usercode = UserUtils.getCurrentUsuarioId(request);
	Locale locale = UserUtils.getCurrentLocale(request);

	/**
	 * 2. We set this page as a input point (see
	 * NavigationFunctions.setInputPoint function for more info).
	 */
	NavigationFunctions.setReturnPoint(request, mapping.getPath());
	Action_load_customList_Form action_load_customList_Form = (Action_load_customList_Form) form;
	String customlistcode = action_load_customList_Form
		.getCustomList_Form().getCustomListcode();

	/**
	 * Set the code of the custom list for obtaining information in the next
	 * action (execute)
	 **/
	request.getSession().setAttribute("CustomListCode", customlistcode);

	/**
	 * This is needed to differentiate a load action with a modify action in
	 * the list execution
	 **/

	request.getSession().setAttribute("FromModifyCustomList", "true");

	CustomList customlist = UseCaseFacade.ObtainCustomList(usercode,
		customlistcode);
	List<Column> column = UseCaseFacade.ObtainAllColumnsFrom(usercode,
		customlistcode);
	List<OrderBy> orderby = UseCaseFacade.ObtainAllOrderBysFrom(usercode,
		customlistcode);
	List<Filter> filter = UseCaseFacade.ObtainAllFiltersFrom(usercode,
		customlistcode);

	// Vector columnvector = UseCase.CreateVectorFromColumnList(column);
	// Vector orderbyvector = UseCase.CreateVectorFromOrderByList(orderby);

	for (Filter f : filter) {
	    if (f.getName().equals("listdate")) {
		// request.setAttribute("listdate", f.getValue());
		action_load_customList_Form.setListdate(f.getValue());
	    }
	}

	/** we prepare information to create the fields ***/

	/**
	 * code from gescresa && (IRBpeople) filter.jsp
	 * 
	 * This code is similar to the code of view_custom_list
	 * 
	 ***/

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
	String vistaName = customlist.getView_name();
	ReportComObject rco = rc.getReport(vistaName);

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

	Vector SELleftNames = new Vector();
	Vector SELleftLabels = new Vector();
	Vector SELrightNames = new Vector();
	Vector SELrightLabels = new Vector();
	String auxname, auxlabel;

	// we lookup which fields must be shown in the right <select> (the
	// ones which are chosen to use in the saved report)
	String[] rightFields = UseCase.CreateListFromColumnList(column);

	// and we fill the Vectors containing the fieldnames and labels
	// of the right side <select> using the hashtable to find the labels
	itNames = defaultSelectFieldNames.iterator();
	itLabels = defaultSelectLabels.iterator();
	for (int i = 0; i < rightFields.length; i++) {
	    SELrightNames.add(rightFields[i]);
	    SELrightLabels.add((String) selectFields.get(rightFields[i]));
	}

	// now we fill the left side select Vectors with the fields which are
	// not
	// contained in the right side
	itNames = defaultSelectFieldNames.iterator();
	itLabels = defaultSelectLabels.iterator();
	while (itNames.hasNext()) {
	    auxname = (String) itNames.next();
	    auxlabel = (String) itLabels.next();
	    boolean found = false;
	    for (int i = 0; i < rightFields.length; i++) {
		if (auxname.equals(rightFields[i])) {
		    found = true;
		}
	    }
	    if (!found) {
		SELleftNames.add(auxname);
		SELleftLabels.add(auxlabel);
	    }

	}

	// ******************** ORDER BY SECTION ***********************
	// ***********************************************************

	// we fill a hashtable with the (fieldname, label) pair
	// so we can easyly find which label corresponds to the name used
	// in the beans (the fieldname)
	Vector defaultOrderbyFieldNames = rco.getOrderBy()
		.getVisibleFieldNames();
	Vector defaultOrderbyLabels = rco.getOrderBy().getVisibleLabels(
		userLang);
	Hashtable orderbyFields = new Hashtable();
	itNames = defaultOrderbyFieldNames.iterator();
	itLabels = defaultOrderbyLabels.iterator();
	while (itNames.hasNext()) {
	    orderbyFields
		    .put((String) itNames.next(), (String) itLabels.next());
	}
	Vector ORDleftNames = new Vector();
	Vector ORDleftLabels = new Vector();
	Vector ORDrightNames = new Vector();
	Vector ORDrightLabels = new Vector();

	// we lookup which fields must be shown in the right <select> (the
	// ones which are chosen to use in the saved report)
	rightFields = UseCase.CreateListFromOrderByList(orderby);

	// and we fill the Vectors containing the fieldnames and labels
	// of the right side <select> using the hashtable to find the labels
	itNames = defaultOrderbyFieldNames.iterator();
	itLabels = defaultOrderbyLabels.iterator();
	for (int i = 0; i < rightFields.length; i++) {
	    ORDrightNames.add(rightFields[i]);
	    ORDrightLabels.add((String) orderbyFields.get(rightFields[i]));
	}

	// now we fill the left side select Vectors with the fields which are
	// not
	// contained in the right side
	itNames = defaultOrderbyFieldNames.iterator();
	itLabels = defaultOrderbyLabels.iterator();
	while (itNames.hasNext()) {
	    auxname = (String) itNames.next();
	    auxlabel = (String) itLabels.next();
	    boolean found = false;
	    for (int i = 0; i < rightFields.length; i++) {
		if (auxname.equals(rightFields[i])) {
		    found = true;
		}
	    }
	    if (!found) {
		ORDleftNames.add(auxname);
		ORDleftLabels.add(auxlabel);
	    }

	}

	// ********************* IMPORTANT *************************
	// NOTE THAT: the SEL and ORD prefixes must be the same which
	// were used in the jsp where showCombos is used.

	// finally we set the request attributes needed by the showCombos tag
	
	ReportFilter.filterFieldsByProfile(user, SELleftNames, SELleftLabels);
	ReportFilter.filterFieldsByProfile(user, SELrightNames, SELrightLabels);
	ReportFilter.filterFieldsByProfile(user, ORDleftNames, ORDleftLabels);
	ReportFilter.filterFieldsByProfile(user, ORDrightNames, ORDrightLabels);
	
	request.setAttribute("SELleftFieldNames", (Vector) SELleftNames);
	request.setAttribute("SELleftLabels", (Vector) SELleftLabels);
	request.setAttribute("SELrightFieldNames", (Vector) SELrightNames);
	request.setAttribute("SELrightLabels", (Vector) SELrightLabels);

	request.setAttribute("ORDleftFieldNames", (Vector) ORDleftNames);
	request.setAttribute("ORDleftLabels", (Vector) ORDleftLabels);
	request.setAttribute("ORDrightFieldNames", (Vector) ORDrightNames);
	request.setAttribute("ORDrightLabels", (Vector) ORDrightLabels);

	Boolean fillWhere = new Boolean(true);
	request.setAttribute("fillWhere", (Boolean) fillWhere);

	request.setAttribute("view_name", vistaName);

	CustomList_Form cl_form = new CustomList_Form();
	ExtendedBeanUtils.copyPropertiesToFormBean(cl_form, customlist, locale);
	request.setAttribute("customlist", cl_form);
	request.setAttribute("savedFilters",
		UseCase.CreateArrayFromfilterList(filter, locale));
	/***/

	return NavigationFunctions.findForward(request, mapping, "success");

    }

}
