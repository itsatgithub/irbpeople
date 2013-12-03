package presentation.formbeans.oAlumni_personalSys;

import presentation.formbeans.objects.Alumni_personal_Form;
import utils.formbeans.FormBeanContainer;

/**
 * This class is a FormBeanContainer for the action
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_validate_external_Form extends FormBeanContainer {


	// FormBean for the action

	/**
	 * nested-pojo-form-bean contained in this container
	 **/
	private Alumni_personal_Form alumni_personal_Form = null;

	private Alumni_personal_Form external_alumni_personal_Form = null;

	/**
	 * @return returns the code of the current alumni_personal_Form (a shortcut
	 *         of getAlumni_personal_Form().getAlumni_personalcode())
	 */
	public String getAlumni_personalcode() {

		return getAlumni_personal_Form().getAlumni_personalcode();
	}

	/**
	 * @return returns the code of the external alumni_personal_Form (a shortcut
	 *         of getExternal_alumni_personal_Form().getAlumni_personalcode())
	 */
	public String getExternal_alumni_personalcode() {

		return getExternal_alumni_personal_Form().getAlumni_personalcode();
	}

	/**
	 * Sets the code of the current alumni_personal_Form (a shortcut of
	 * getAlumni_personal_Form().setAlumni_personalcode(String))
	 * 
	 * @param alumni_personalcode
	 *            the new code
	 */

	public void setAlumni_personalcode(String alumni_personalcode) {

		getAlumni_personal_Form().setAlumni_personalcode(alumni_personalcode);
	}

	/**
	 * Sets the code of the external alumni_personal_Form (a shortcut of
	 * getExternal_alumni_personal_Form().setAlumni_personalcode(String))
	 * 
	 * @param alumni_personalcode
	 *            the new code
	 */

	public void setExternal_alumni_personalcode(String alumni_personalcode) {

		getExternal_alumni_personal_Form().setAlumni_personalcode(alumni_personalcode);
	}

	/**
	 * @return the current alumni_personal_Form
	 */

	public Alumni_personal_Form getAlumni_personal_Form() {

		if (alumni_personal_Form == null)

			alumni_personal_Form = new Alumni_personal_Form();

		return alumni_personal_Form;
	}

	/**
	 * @return the external alumni_personal_Form
	 */

	public Alumni_personal_Form getExternal_alumni_personal_Form() {

		if (external_alumni_personal_Form == null)

			external_alumni_personal_Form = new Alumni_personal_Form();

		return external_alumni_personal_Form;
	}

	/**
	 * set the current alumni_personal_Form
	 * 
	 * @param alumni_personal_Form
	 *            the new alumni_personal_Form
	 */

	public void setAlumni_personal_Form(Alumni_personal_Form alumni_personal_Form) {

		this.alumni_personal_Form = alumni_personal_Form;
	}

	/**
	 * set the external alumni_personal_Form
	 * 
	 * @param alumni_personal_Form
	 *            the new alumni_personal_Form
	 */

	public void setExternal_alumni_personal_Form(Alumni_personal_Form alumni_personal_Form) {

		this.external_alumni_personal_Form = alumni_personal_Form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
	 */
	@Override
	public String getMainFormBeanCode() {
		return getExternal_alumni_personalcode();
	}

}