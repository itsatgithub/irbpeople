package presentation.formbeans.customList;

import utils.formbeans.FormBeanContainer;

public class AjaxAction_view_custom_list_Form extends FormBeanContainer {

    private String selected_view = null;

    private String listdate = null;

    private String[] SELselectedfields = null;
    private String[] ORDselectedfields = null;

    public String[] getORDselectedfields() {
	return ORDselectedfields;
    }

    public void setORDselectedfields(String[] dselectedfields) {
	ORDselectedfields = dselectedfields;
    }

    public String[] getSELselectedfields() {
	return SELselectedfields;
    }

    public void setSELselectedfields(String[] lselectedfields) {
	SELselectedfields = lselectedfields;
    }

    public String getSelected_view() {
	return selected_view;
    }

    public void setSelected_view(String selected_view) {
	this.selected_view = selected_view;
    }

    public String getView_name() {
	return getSelected_view();
    }

    public void setView_name(String view_name) {
	this.selected_view = view_name;
    }

    public String[] getColumns() {
	return SELselectedfields;
    }

    public void setColumns(String[] columns) {
	this.SELselectedfields = columns;
    }

    public String[] getOrderBy() {
	return ORDselectedfields;
    }

    public void setOrderBy(String[] orderBy) {
	this.ORDselectedfields = orderBy;
    }

    public void setListdate(String listdate) {
	this.listdate = listdate;
    }

    public String getListdate() {
	return listdate;
    }

}
