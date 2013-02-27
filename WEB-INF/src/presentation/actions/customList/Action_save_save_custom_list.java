package presentation.actions.customList;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import bussineslogic.controlers.UseCaseFacade;
import bussineslogic.excepciones.InternalException;
import bussineslogic.excepciones.NoPermisosException;
import bussineslogic.objects.Column;
import bussineslogic.objects.CustomList;
import bussineslogic.objects.Filter;
import bussineslogic.objects.FilterType;
import bussineslogic.objects.OrderBy;

import presentation.formbeans.customList.Action_save_custom_list_Form;

import utils.actions.NavigationFunctions;
import utils.listFilter.ViewListConfiguration;
import utils.userUtils.UserUtils;

public class Action_save_save_custom_list  extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {    

		String usercode = UserUtils.getCurrentUsuarioId(request); 
		Locale locale = UserUtils.getCurrentLocale(request); 

		/** 2.  We set this page as a input point (see NavigationFunctions.setInputPoint function for more info). */
	
		/**Obtain all the list information for saving*/
		
		ViewListConfiguration config_filter = (ViewListConfiguration) request.getSession().getAttribute("filterView" );
		String[] columns = (String[]) request.getSession().getAttribute("columnsView" );
		String[] orderBy = (String[])request.getSession().getAttribute("orderByView" );
		String view_name = (String)request.getSession().getAttribute("viewName" );
		Action_save_custom_list_Form action_save_custom_list = (Action_save_custom_list_Form) form;
		
		/** Convert filter**/
		List<String[]> filter = config_filter.obtainListConfiguratorForViewsToSave(locale);

		/**Obtain saving info, name and comments from the form bean**/
		
		String custom_list_name = action_save_custom_list.getCustom_list_name();
		String custom_list_comments = action_save_custom_list.getComments();


		if (custom_list_name != null && !custom_list_name.equals("")){

			SaveCustomList(columns, orderBy, filter, custom_list_name, view_name, usercode, custom_list_comments);


			return NavigationFunctions.findForward(request, mapping, "success");
		}

		return NavigationFunctions.findForward(request, mapping, "fail");
	}

	public static void SaveCustomList (String[] columns, String[] orderBy, List<String[]> filter, 
			String custom_list_name, String view_name,String usercode, String custom_list_comments ) 
	throws InternalException, NoPermisosException{

		CustomList dto = new CustomList();
		dto.setName(custom_list_name);
		dto.setView_name(view_name);
		dto.setComments(custom_list_comments);
		dto.setUsercode(usercode);
		dto.setUpdate_date(new Date());

		dto = UseCaseFacade.CreateCustomList(usercode, dto);


		if(orderBy != null){
			for(int i=0; i<orderBy.length; i++){
				OrderBy dto_ord = new OrderBy();

				dto_ord.setAssocCustomList(dto);
				dto_ord.setName(orderBy[i]);
				UseCaseFacade.CreateOrderBy(usercode, dto_ord);

			}
		}
		Iterator<String[]> it = filter.iterator();

		while(it.hasNext()){

			String[] temp = it.next();
			/**   [name, value, type] **/

			Filter dto_filt = new Filter();
			FilterType type = UseCaseFacade.ObtainFilterType(usercode, temp[2]);
			dto_filt.setAssocCustomList(dto);
			dto_filt.setAssocFilterType(type);
			dto_filt.setName(temp[0]);
			dto_filt.setValue(temp[1]);
			dto_filt.setType(temp[3]);
			UseCaseFacade.CreateFilter(usercode, dto_filt);

		}
		for(int i=0; i<columns.length ;i++){

			Column dto_col = new Column();
			dto_col.setAssocCustomList(dto);
			dto_col.setName(columns[i]);
			UseCaseFacade.CreateColumn(usercode, dto_col);
		}

	}
}
