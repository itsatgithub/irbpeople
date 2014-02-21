package presentation.formbeans.objects;

import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of type_of_study
 * 
 * @author Automatika - JustInMind SL
 */
public class Params_Form extends ValidatorFormAndAction {

	String paramscode;

	private String title = null;
	private String value = null;	

	public Params_Form getParams() {
		return this;
	}

	public String getParamscode() {
		return paramscode;
	}

	public void setParamscode(String paramscode) {
		this.paramscode = paramscode;
	}

	@Override
	public String toString() {

		if (getParamscode() == null || getParamscode().equals(""))
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