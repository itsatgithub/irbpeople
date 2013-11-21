package presentation.formbeans.oAlumni_external_job_positionsSys;




import utils.formbeans.FormBeanContainer;
import utils.formbeans.BOAdderFormBean;

import utils.validator.ValidatorFormAndAction;

import presentation.formbeans.objects.*;

/**
 * This class is a FormBeanContainer for the action action_modify_modify_page_alumni_external_job_positions.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_modify_modify_page_alumni_external_job_positions_Form extends FormBeanContainer {

	
	//FormBean for the action
			
			/**
			* nested-pojo-form-bean contained in this container
			**/
			private Alumni_external_job_positions_Form alumni_external_job_positions_Form = null;
	
	
	

	
	
	
		
		/**
		 * @return returns the code of the current alumni_external_job_positions_Form (a shortcut of getAlumni_external_job_positions_Form().getAlumni_external_job_positionscode())
		 */
		public String getAlumni_external_job_positionscode() {
			
			return getAlumni_external_job_positions_Form().getAlumni_external_job_positionscode();
		}

		/**
		 * Sets the code of the current alumni_external_job_positions_Form (a shortcut of getAlumni_external_job_positions_Form().setAlumni_external_job_positionscode(String))
		 * @param alumni_alumni_external_job_positionscode the new code
		 */
		
		public void setAlumni_external_job_positionscode(String alumni_alumni_external_job_positionscode){
			
			getAlumni_external_job_positions_Form().setAlumni_external_job_positionscode(alumni_alumni_external_job_positionscode);
		}

		/**
		 * @return the current alumni_external_job_positions_Form
		 */
		
		public Alumni_external_job_positions_Form getAlumni_external_job_positions_Form() {
			
			if(alumni_external_job_positions_Form==null)
				
				alumni_external_job_positions_Form = new Alumni_external_job_positions_Form();
			
			return alumni_external_job_positions_Form;
		}

		/**
		 * set the current alumni_external_job_positions_Form
		 * @param alumni_external_job_positions_Form the new alumni_external_job_positions_Form
		 */
		
		public void setAlumni_external_job_positions_Form(Alumni_external_job_positions_Form alumni_external_job_positions_Form){
			
			this.alumni_external_job_positions_Form=alumni_external_job_positions_Form;
		}
		
		
			/* (non-Javadoc)
			 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
			 */
			@Override
			public String getMainFormBeanCode() {
				return getAlumni_external_job_positionscode();
			}
			
			
			
	
	
	
	
	
	
}