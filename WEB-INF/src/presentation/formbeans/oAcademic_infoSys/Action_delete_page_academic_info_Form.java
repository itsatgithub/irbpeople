package presentation.formbeans.oAcademic_infoSys;

import presentation.formbeans.objects.Academic_info_Form;
import utils.formbeans.FormBeanContainer;

/**
 * This class is a FormBeanContainer for the action
 * action_delete_page_academic_info.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_delete_page_academic_info_Form extends FormBeanContainer {

	// FormBean for the action

	/**
	 * nested-pojo-form-bean contained in this container
	 **/
	private Academic_info_Form academic_info_Form = null;

	/**
	 * @return returns the code of the current academic_info_Form (a shortcut of
	 *         getAcademic_info_Form().getAcademic_infocode())
	 */
	public String getAcademic_infocode() {

		return getAcademic_info_Form().getAcademic_infocode();
	}

	/**
	 * Sets the code of the current academic_info_Form (a shortcut of
	 * getAcademic_info_Form().setAcademic_infocode(String))
	 * 
	 * @param academic_infocode
	 *            the new code
	 */

	public void setAcademic_infocode(String academic_infocode) {

		getAcademic_info_Form().setAcademic_infocode(academic_infocode);
	}

	/**
	 * @return the current academic_info_Form
	 */

	public Academic_info_Form getAcademic_info_Form() {

		if (academic_info_Form == null)

			academic_info_Form = new Academic_info_Form();

		return academic_info_Form;
	}

	/**
	 * set the current academic_info_Form
	 * 
	 * @param academic_info_Form
	 *            the new academic_info_Form
	 */

	public void setAcademic_info_Form(Academic_info_Form academic_info_Form) {

		this.academic_info_Form = academic_info_Form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
	 */
	@Override
	public String getMainFormBeanCode() {
		return getAcademic_infocode();
	}

}