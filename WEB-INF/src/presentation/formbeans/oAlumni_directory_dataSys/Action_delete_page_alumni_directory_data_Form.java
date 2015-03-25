package presentation.formbeans.oAlumni_directory_dataSys;

import presentation.formbeans.objects.Alumni_directory_data_Form;
import utils.formbeans.FormBeanContainer;

/**
 * This class is a FormBeanContainer for the action
 * action_delete_page_alumni_directory_data.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_delete_page_alumni_directory_data_Form extends FormBeanContainer {

	// FormBean for the action

	/**
	 * nested-pojo-form-bean contained in this container
	 **/
	private Alumni_directory_data_Form alumni_directory_data_Form = null;

	/**
	 * @return returns the code of the current alumni_directory_data_Form (a shortcut of
	 *         getAlumni_directory_data_Form().getAlumni_directory_datacode())
	 */
	public String getAlumni_directory_datacode() {

		return getAlumni_directory_data_Form().getAlumni_directory_datacode();
	}

	/**
	 * Sets the code of the current alumni_directory_data_Form (a shortcut of
	 * getAlumni_directory_data_Form().setAlumni_directory_datacode(String))
	 * 
	 * @param alumni_directory_datacode
	 *            the new code
	 */

	public void setAlumni_directory_datacode(String alumni_directory_datacode) {

		getAlumni_directory_data_Form().setAlumni_directory_datacode(alumni_directory_datacode);
	}

	/**
	 * @return the current alumni_directory_data_Form
	 */

	public Alumni_directory_data_Form getAlumni_directory_data_Form() {

		if (alumni_directory_data_Form == null)

			alumni_directory_data_Form = new Alumni_directory_data_Form();

		return alumni_directory_data_Form;
	}

	/**
	 * set the current alumni_directory_data_Form
	 * 
	 * @param alumni_directory_data_Form
	 *            the new alumni_directory_data_Form
	 */

	public void setAlumni_directory_data_Form(Alumni_directory_data_Form alumni_directory_data_Form) {

		this.alumni_directory_data_Form = alumni_directory_data_Form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
	 */
	@Override
	public String getMainFormBeanCode() {
		return getAlumni_directory_datacode();
	}

}