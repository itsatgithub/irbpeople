package presentation.formbeans.objects;

import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of type_of_study
 * 
 * @author Automatika - JustInMind SL
 */
public class Alumni_params_Form extends ValidatorFormAndAction {

	String alumni_paramscode;

	private String title = null;
	private String value = null;	

	public Alumni_params_Form getAlumni_params() {
		return this;
	}

	public String getAlumni_paramscode() {
		return alumni_paramscode;
	}

	public void setAlumni_paramscode(String alumni_paramscode) {
		this.alumni_paramscode = alumni_paramscode;
	}

	@Override
	public String toString() {

		if (getAlumni_paramscode() == null || getAlumni_paramscode().equals(""))
			return "";

		String result = getTitle() + "";
		return (result != null) ? result : "";

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
	
	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}


}