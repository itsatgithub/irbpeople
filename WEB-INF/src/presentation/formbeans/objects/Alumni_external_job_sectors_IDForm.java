package presentation.formbeans.objects;

import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-ID-form-bean of alumni_external_job_sectors. ID-formBeans contain all the
 * attributes of alumni_external_job_sectors but it does not contain the associations.
 * 
 * @author Automatika - JustInMind SL
 */
public class Alumni_external_job_sectors_IDForm extends ValidatorFormAndAction {

	String alumni_external_job_sectorscode;

	private String description = null;

	private String short_description = null;
	
	private String order_number = null;

	public Alumni_external_job_sectors_IDForm getAlumni_external_job_sectors() {
		return this;
	}

	public String getAlumni_external_job_sectorscode() {
		return alumni_external_job_sectorscode;
	}

	public void setAlumni_external_job_sectorscode(String alumni_external_job_sectorscode) {
		this.alumni_external_job_sectorscode = alumni_external_job_sectorscode;
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

		if (getAlumni_external_job_sectorscode() == null || getAlumni_external_job_sectorscode().equals(""))
			return "";

		String result = getDescription() + "";
		return (result != null) ? result : "";

	}

}