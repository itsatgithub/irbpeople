package presentation.formbeans.objects;

import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of type_of_study
 * 
 * @author Automatika - JustInMind SL
 */
public class Alumni_irb_job_positions_Form extends ValidatorFormAndAction {

	String version;
	String alumni_irb_job_positionscode;

	private String description = null;
	private String short_description = null;
	private String order_number;

	private Alumni_job_position_types_IDForm job_position_types = null;
	
	public void setAlumni_irb_job_positionscode(String alumni_irb_job_positionscode) {
		this.alumni_irb_job_positionscode = alumni_irb_job_positionscode;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {

		if (getAlumni_irb_job_positionscode() == null || getAlumni_irb_job_positionscode().equals(""))
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

	public Alumni_job_position_types_IDForm getJob_position_types() {
		if(job_position_types==null){
			job_position_types = new Alumni_job_position_types_IDForm();
		}
		return job_position_types;
	}

	public void setJob_position_types(Alumni_job_position_types_IDForm job_position_types) {
		this.job_position_types = job_position_types;
	}
	
	public Alumni_irb_job_positions_Form getAlumni_irb_job_positions() {
		return this;
	}

	public String getAlumni_irb_job_positionscode() {
		return alumni_irb_job_positionscode;
	}

}