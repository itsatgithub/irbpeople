package presentation.formbeans.oAlumni_irb_jobsSys;

import presentation.formbeans.objects.Alumni_irb_jobs_Form;
import utils.formbeans.FormBeanContainer;

/**
 * This class is a FormBeanContainer for the action
 * action_new_new_page_alumni_irb_jobs.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_new_new_page_alumni_irb_jobs_Form extends FormBeanContainer {

	// FormBean for the action

	/**
	 * nested-pojo-form-bean contained in this container
	 **/
	private Alumni_irb_jobs_Form alumni_irb_jobs_Form = null;

	/**
	 * @return returns the code of the current alumni_irb_jobs_Form (a shortcut of
	 *         getAlumni_irb_jobs_Form().getAlumni_irb_jobscode())
	 */
	public String getAlumni_irb_jobscode() {

		return getAlumni_irb_jobs_Form().getAlumni_irb_jobscode();
	}

	/**
	 * Sets the code of the current alumni_irb_jobs_Form (a shortcut of
	 * getAlumni_irb_jobs_Form().setAlumni_irb_jobscode(String))
	 * 
	 * @param alumni_irb_jobscode
	 *            the new code
	 */

	public void setAlumni_irb_jobscode(String alumni_irb_jobscode) {

		getAlumni_irb_jobs_Form().setAlumni_irb_jobscode(alumni_irb_jobscode);
	}

	/**
	 * @return the current alumni_irb_jobs_Form
	 */

	public Alumni_irb_jobs_Form getAlumni_irb_jobs_Form() {

		if (alumni_irb_jobs_Form == null)

			alumni_irb_jobs_Form = new Alumni_irb_jobs_Form();

		return alumni_irb_jobs_Form;
	}

	/**
	 * set the current alumni_irb_jobs_Form
	 * 
	 * @param alumni_irb_jobs_Form
	 *            the new alumni_irb_jobs_Form
	 */

	public void setAlumni_irb_jobs_Form(Alumni_irb_jobs_Form alumni_irb_jobs_Form) {

		this.alumni_irb_jobs_Form = alumni_irb_jobs_Form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
	 */
	@Override
	public String getMainFormBeanCode() {
		return getAlumni_irb_jobscode();
	}

}