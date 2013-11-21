package presentation.formbeans.objects;

import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of type_of_study
 * 
 * @author Automatika - JustInMind SL
 */
public class Alumni_job_position_types_Form extends ValidatorFormAndAction {

	String version;
	String alumni_job_position_typescode;

	private String description = null;
	private String short_description = null;
	private String order_number;

	public Alumni_job_position_types_Form getAlumni_job_position_types() {
		return this;
	}

	public String getAlumni_job_position_typescode() {
		return alumni_job_position_typescode;
	}

	public void setAlumni_job_position_typescode(String alumni_job_position_typescode) {
		this.alumni_job_position_typescode = alumni_job_position_typescode;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {

		if (getAlumni_job_position_typescode() == null || getAlumni_job_position_typescode().equals(""))
			return "";

		String result = getDescription() + "";
		return (result != null) ? result : "";

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

}