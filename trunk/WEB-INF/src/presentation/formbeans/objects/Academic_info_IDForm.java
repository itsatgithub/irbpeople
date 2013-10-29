package presentation.formbeans.objects;

import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-ID-form-bean of academic_info. ID-formBeans contain all the
 * attributes of academic_info but it does not contain the associations.
 * 
 * @author Automatika - JustInMind SL
 */
public class Academic_info_IDForm extends ValidatorFormAndAction {

	String academic_infocode;

	private String start_date = null;

	private String end_date = null;

	public Academic_info_IDForm getAcademic_info() {
		return this;
	}

	public String getAcademic_infocode() {
		return academic_infocode;
	}

	public void setAcademic_infocode(String academic_infocode) {
		this.academic_infocode = academic_infocode;
	}

	@Override
	public String toString() {

		if (getAcademic_infocode() == null || getAcademic_infocode().equals(""))
			return "";

		String result = getStart_date() + " - " + getEnd_date();
		return (result != null) ? result : "";

	}

	public String get_descripcion() {
		return this.toString();
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

}