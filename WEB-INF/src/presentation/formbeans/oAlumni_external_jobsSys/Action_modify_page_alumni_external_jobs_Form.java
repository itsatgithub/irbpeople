package presentation.formbeans.oAlumni_external_jobsSys;

import presentation.formbeans.objects.Alumni_external_jobs_Form;
import utils.formbeans.FormBeanContainer;

/**
 * This class is a FormBeanContainer for the action
 * action_modify_page_alumni_external_jobs.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_modify_page_alumni_external_jobs_Form extends FormBeanContainer {

	// FormBean for the action

	/**
	 * nested-pojo-form-bean contained in this container
	 **/
	private Alumni_external_jobs_Form alumni_external_jobs_Form = null;

	/**
	 * @return returns the code of the current alumni_external_jobs_Form (a shortcut of
	 *         getAlumni_external_jobs_Form().getAlumni_external_jobscode())
	 */
	public String getAlumni_external_jobscode() {

		return getAlumni_external_jobs_Form().getAlumni_external_jobscode();
	}

	/**
	 * Sets the code of the current alumni_external_jobs_Form (a shortcut of
	 * getAlumni_external_jobs_Form().setAlumni_external_jobscode(String))
	 * 
	 * @param alumni_external_jobscode
	 *            the new code
	 */

	public void setAlumni_external_jobscode(String alumni_external_jobscode) {

		getAlumni_external_jobs_Form().setAlumni_external_jobscode(alumni_external_jobscode);
	}

	/**
	 * @return the current alumni_external_jobs_Form
	 */

	public Alumni_external_jobs_Form getAlumni_external_jobs_Form() {

		if (alumni_external_jobs_Form == null)

			alumni_external_jobs_Form = new Alumni_external_jobs_Form();

		return alumni_external_jobs_Form;
	}

	/**
	 * set the current alumni_external_jobs_Form
	 * 
	 * @param alumni_external_jobs_Form
	 *            the new alumni_external_jobs_Form
	 */

	public void setAlumni_external_jobs_Form(Alumni_external_jobs_Form alumni_external_jobs_Form) {

		this.alumni_external_jobs_Form = alumni_external_jobs_Form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
	 */
	@Override
	public String getMainFormBeanCode() {
		return getAlumni_external_jobscode();
	}

}