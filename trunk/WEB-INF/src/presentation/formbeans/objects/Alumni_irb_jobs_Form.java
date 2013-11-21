package presentation.formbeans.objects;

import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of type_of_study
 * 
 * @author Automatika - JustInMind SL
 */
public class Alumni_irb_jobs_Form extends ValidatorFormAndAction {

	String version;
	String alumni_irb_jobscode;

	private Alumni_personal_IDForm personal;
	private String start_date;
	private String end_date;
	private Unit_IDForm unit;
	private Unit_IDForm unit_2;
	private Alumni_irb_job_positions_IDForm irb_job_positions;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Alumni_irb_jobs_Form getAlumni_irb_jobs() {
		return this;
	}

	@Override
	public String toString() {

		if (getAlumni_irb_jobscode() == null || getAlumni_irb_jobscode().equals(""))
			return "";

		String result = getStart_date() + " " + getEnd_date();
		return (result != null) ? result : "";

	}

	public String get_descripcion() {
		return this.toString();
	}

	public String getAlumni_irb_jobscode() {
		return alumni_irb_jobscode;
	}

	public void setAlumni_irb_jobscode(String alumni_irb_jobscode) {
		this.alumni_irb_jobscode = alumni_irb_jobscode;
	}

	public Alumni_personal_IDForm getPersonal() {
		if (personal == null) {
			personal = new Alumni_personal_IDForm();
		}
		return personal;
	}

	public void setPersonal(Alumni_personal_IDForm personal) {
		this.personal = personal;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public Unit_IDForm getUnit() {
		if (unit == null)
			unit = new Unit_IDForm();
		return unit;
	}

	public void setUnit(Unit_IDForm unit) {
		this.unit = unit;
	}

	public Unit_IDForm getUnit_2() {
		if (unit_2 == null)
			unit_2 = new Unit_IDForm();
		return unit_2;
	}

	public void setUnit_2(Unit_IDForm unit_2) {
		this.unit_2 = unit_2;
	}

	public Alumni_irb_job_positions_IDForm getIrb_job_positions() {
		if (irb_job_positions == null)
			irb_job_positions = new Alumni_irb_job_positions_IDForm();
		return irb_job_positions;
	}

	public void setIrb_job_positions(Alumni_irb_job_positions_IDForm irb_job_positions) {
		this.irb_job_positions = irb_job_positions;
	}

}