package presentation.actions.customList;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import bussineslogic.controlers.UseCaseFacade;

import utils.actions.NavigationFunctions;
import utils.listFilter.ViewListConfiguration;
import utils.userUtils.UserUtils;

public class Action_save_custom_list extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {    

		String usercode = UserUtils.getCurrentUsuarioId(request); 
		Locale locale = UserUtils.getCurrentLocale(request); 

		/** 2.  We set this page as a input point (see NavigationFunctions.setInputPoint function for more info). */
		NavigationFunctions.setReturnPoint(request, mapping.getPath());

		String custom_list_name = (String) request.getSession().getAttribute("CustomListCode");

		if(custom_list_name == null || custom_list_name.equals("")){

			return NavigationFunctions.findForward(request, mapping, "goToSaveAs");
		}
		else{//this is an update
			
			ViewListConfiguration config_filter = (ViewListConfiguration) request.getSession().getAttribute("filterView" );
			String[] columns = (String[]) request.getSession().getAttribute("columnsView" );
			String[] orderBy = (String[])request.getSession().getAttribute("orderByView" );
			String view_name = (String)request.getSession().getAttribute("viewName" );
			List<String[]> filter = config_filter.obtainListConfiguratorForViewsToSave(locale);
			
			UseCaseFacade.UpdateCustomListElements(usercode, custom_list_name, columns, orderBy, filter);
			//Action_save_save_custom_list.SaveCustomList(columns, orderBy, filter, custom_list_name, view_name, usercode);

			return NavigationFunctions.findForward(request, mapping, "success");
		}
	}

}
