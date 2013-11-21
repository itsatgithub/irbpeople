package presentation.formbeans.objects;

import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-ID-form-bean of alumni_titles. ID-formBeans contain all the
 * attributes of alumni_titles but it does not contain the associations.
 * 
 * @author Automatika - JustInMind SL
 */
public class Alumni_titles_IDForm extends ValidatorFormAndAction {

	String alumni_titlescode;

	private String description = null;

	private String short_description = null;
	
	private String order_number = null;

	public Alumni_titles_IDForm getAlumni_titles() {
		return this;
	}

	public String getAlumni_titlescode() {
		return alumni_titlescode;
	}

	public void setAlumni_titlescode(String alumni_titlescode) {
		this.alumni_titlescode = alumni_titlescode;
	}

	public String get_descripcion() {
		return this.toString();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShort_description() {
		return short_description;
	}

	public void setShort_description(String short_description) {
		this.short_description = short_description;
	}

	public String getOrder_number() {
		return order_number;
	}

	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}

	@Override
	public String toString() {

		if (getAlumni_titlescode() == null || getAlumni_titlescode().equals(""))
			return "";

		String result = getDescription() + "";
		return (result != null) ? result : "";

	}

}