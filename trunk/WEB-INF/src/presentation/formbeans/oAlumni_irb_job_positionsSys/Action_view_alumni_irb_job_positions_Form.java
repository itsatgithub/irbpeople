package presentation.formbeans.oAlumni_irb_job_positionsSys;




import utils.formbeans.FormBeanContainer;
import utils.formbeans.BOAdderFormBean;

import utils.validator.ValidatorFormAndAction;

import presentation.formbeans.objects.*;

/**
 * This class is a FormBeanContainer for the action action_view_alumni_irb_job_positions.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_view_alumni_irb_job_positions_Form extends FormBeanContainer {

	
	//FormBean for the action
			
			/**
			* nested-pojo-form-bean contained in this container
			**/
			private Alumni_irb_job_positions_Form alumni_irb_job_positions_Form = null;
	
	
	

	
	
	
		
		/**
		 * @return returns the code of the current alumni_irb_job_positions_Form (a shortcut of getAlumni_irb_job_positions_Form().getAlumni_irb_job_positionscode())
		 */
		public String getAlumni_irb_job_positionscode() {
			
			return getAlumni_irb_job_positions_Form().getAlumni_irb_job_positionscode();
		}

		/**
		 * Sets the code of the current alumni_irb_job_positions_Form (a shortcut of getAlumni_irb_job_positions_Form().setAlumni_irb_job_positionscode(String))
		 * @param alumni_irb_job_positionscode the new code
		 */
		
		public void setAlumni_irb_job_positionscode(String alumni_irb_job_positionscode){
			
			getAlumni_irb_job_positions_Form().setAlumni_irb_job_positionscode(alumni_irb_job_positionscode);
		}

		/**
		 * @return the current alumni_irb_job_positions_Form
		 */
		
		public Alumni_irb_job_positions_Form getAlumni_irb_job_positions_Form() {
			
			if(alumni_irb_job_positions_Form==null)
				
				alumni_irb_job_positions_Form = new Alumni_irb_job_positions_Form();
			
			return alumni_irb_job_positions_Form;
		}

		/**
		 * set the current alumni_irb_job_positions_Form
		 * @param alumni_irb_job_positions_Form the new alumni_irb_job_positions_Form
		 */
		
		public void setAlumni_irb_job_positions_Form(Alumni_irb_job_positions_Form alumni_irb_job_positions_Form){
			
			this.alumni_irb_job_positions_Form=alumni_irb_job_positions_Form;
		}
		
		
			/* (non-Javadoc)
			 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
			 */
			@Override
			public String getMainFormBeanCode() {
				return getAlumni_irb_job_positionscode();
			}
			
			
			
	
	
	
	
	
	
}