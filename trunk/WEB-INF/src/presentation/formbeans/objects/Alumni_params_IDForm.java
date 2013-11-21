package presentation.formbeans.objects;

import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-ID-form-bean of alumni_params. ID-formBeans contain all the
 * attributes of alumni_params but it does not contain the associations.
 * 
 * @author Automatika - JustInMind SL
 */
public class Alumni_params_IDForm extends ValidatorFormAndAction {

	String alumni_paramscode;

	private String title = null;

	private String value = null;
	
	public Alumni_params_IDForm getAlumni_params() {
		return this;
	}

	public String getAlumni_paramscode() {
		return alumni_paramscode;
	}

	public void setAlumni_paramscode(String alumni_paramscode) {
		this.alumni_paramscode = alumni_paramscode;
	}

	public String get_descripcion() {
		return this.toString();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {

		if (getAlumni_paramscode() == null || getAlumni_paramscode().equals(""))
			return "";

		String result = getValue() + "";
		return (result != null) ? result : "";

	}

}