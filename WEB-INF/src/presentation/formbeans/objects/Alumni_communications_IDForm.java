package presentation.formbeans.objects;

import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-ID-form-bean of alumni_communications. ID-formBeans contain all the
 * attributes of alumni_communications but it does not contain the associations.
 * 
 * @author Automatika - JustInMind SL
 */
public class Alumni_communications_IDForm extends ValidatorFormAndAction {

	String alumni_communicationscode;

	private String description = null;

	private String short_description = null;
	
	private String order_number = null;

	public Alumni_communications_IDForm getAlumni_communications() {
		return this;
	}

	public String getAlumni_communicationscode() {
		return alumni_communicationscode;
	}

	public void setAlumni_communicationscode(String alumni_communicationscode) {
		this.alumni_communicationscode = alumni_communicationscode;
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

		if (getAlumni_communicationscode() == null || getAlumni_communicationscode().equals(""))
			return "";

		String result = getDescription() + "";
		return (result != null) ? result : "";

	}

}