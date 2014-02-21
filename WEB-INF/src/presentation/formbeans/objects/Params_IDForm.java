package presentation.formbeans.objects;

import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-ID-form-bean of params. ID-formBeans contain all the
 * attributes of params but it does not contain the associations.
 * 
 * @author Automatika - JustInMind SL
 */
public class Params_IDForm extends ValidatorFormAndAction {

	String paramscode;

	private String title = null;

	private String value = null;
	
	public Params_IDForm getParams() {
		return this;
	}

	public String getParamscode() {
		return paramscode;
	}

	public void setParamscode(String paramscode) {
		this.paramscode = paramscode;
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

		if (getParamscode() == null || getParamscode().equals(""))
			return "";

		String result = getValue() + "";
		return (result != null) ? result : "";

	}

}