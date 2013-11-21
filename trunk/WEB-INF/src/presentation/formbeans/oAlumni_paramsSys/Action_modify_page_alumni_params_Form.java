package presentation.formbeans.oAlumni_paramsSys;

import presentation.formbeans.objects.Alumni_params_Form;
import utils.formbeans.FormBeanContainer;

/**
 * This class is a FormBeanContainer for the action
 * action_modify_page_alumni_params.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_modify_page_alumni_params_Form extends FormBeanContainer {

	// FormBean for the action

	/**
	 * nested-pojo-form-bean contained in this container
	 **/
	private Alumni_params_Form alumni_params_Form = null;

	/**
	 * @return returns the code of the current alumni_params_Form (a shortcut of
	 *         getAlumni_params_Form().getAlumni_paramscode())
	 */
	public String getAlumni_paramscode() {

		return getAlumni_params_Form().getAlumni_paramscode();
	}

	/**
	 * Sets the code of the current alumni_params_Form (a shortcut of
	 * getAlumni_params_Form().setAlumni_paramscode(String))
	 * 
	 * @param alumni_paramscode
	 *            the new code
	 */

	public void setAlumni_paramscode(String alumni_paramscode) {

		getAlumni_params_Form().setAlumni_paramscode(alumni_paramscode);
	}

	/**
	 * @return the current alumni_params_Form
	 */

	public Alumni_params_Form getAlumni_params_Form() {

		if (alumni_params_Form == null)

			alumni_params_Form = new Alumni_params_Form();

		return alumni_params_Form;
	}

	/**
	 * set the current alumni_params_Form
	 * 
	 * @param alumni_params_Form
	 *            the new alumni_params_Form
	 */

	public void setAlumni_params_Form(Alumni_params_Form alumni_params_Form) {

		this.alumni_params_Form = alumni_params_Form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
	 */
	@Override
	public String getMainFormBeanCode() {
		return getAlumni_paramscode();
	}

}