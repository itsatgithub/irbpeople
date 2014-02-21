package presentation.formbeans.oParamsSys;

import presentation.formbeans.objects.Params_Form;
import utils.formbeans.FormBeanContainer;

/**
 * This class is a FormBeanContainer for the action
 * action_modify_page_params.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_modify_page_params_Form extends FormBeanContainer {

	// FormBean for the action

	/**
	 * nested-pojo-form-bean contained in this container
	 **/
	private Params_Form params_Form = null;

	/**
	 * @return returns the code of the current params_Form (a shortcut of
	 *         getParams_Form().getParamscode())
	 */
	public String getParamscode() {

		return getParams_Form().getParamscode();
	}

	/**
	 * Sets the code of the current params_Form (a shortcut of
	 * getParams_Form().setParamscode(String))
	 * 
	 * @param paramscode
	 *            the new code
	 */

	public void setParamscode(String paramscode) {

		getParams_Form().setParamscode(paramscode);
	}

	/**
	 * @return the current params_Form
	 */

	public Params_Form getParams_Form() {

		if (params_Form == null)

			params_Form = new Params_Form();

		return params_Form;
	}

	/**
	 * set the current params_Form
	 * 
	 * @param params_Form
	 *            the new params_Form
	 */

	public void setParams_Form(Params_Form params_Form) {

		this.params_Form = params_Form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
	 */
	@Override
	public String getMainFormBeanCode() {
		return getParamscode();
	}

}