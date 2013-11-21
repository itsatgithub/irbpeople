package presentation.formbeans.oAlumni_job_position_typesSys;




import utils.formbeans.FormBeanContainer;
import utils.formbeans.BOAdderFormBean;

import utils.validator.ValidatorFormAndAction;

import presentation.formbeans.objects.*;

/**
 * This class is a FormBeanContainer for the action action_delete_page_alumni_job_position_types.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_delete_page_alumni_job_position_types_Form extends FormBeanContainer {

	
	//FormBean for the action
			
			/**
			* nested-pojo-form-bean contained in this container
			**/
			private Alumni_job_position_types_Form alumni_job_position_types_Form = null;
	
	
	

	
	
	
		
		/**
		 * @return returns the code of the current alumni_job_position_types_Form (a shortcut of getAlumni_job_position_types_Form().getAlumni_job_position_typescode())
		 */
		public String getAlumni_job_position_typescode() {
			
			return getAlumni_job_position_types_Form().getAlumni_job_position_typescode();
		}

		/**
		 * Sets the code of the current alumni_job_position_types_Form (a shortcut of getAlumni_job_position_types_Form().setAlumni_job_position_typescode(String))
		 * @param alumni_job_position_typescode the new code
		 */
		
		public void setAlumni_job_position_typescode(String alumni_job_position_typescode){
			
			getAlumni_job_position_types_Form().setAlumni_job_position_typescode(alumni_job_position_typescode);
		}

		/**
		 * @return the current alumni_job_position_types_Form
		 */
		
		public Alumni_job_position_types_Form getAlumni_job_position_types_Form() {
			
			if(alumni_job_position_types_Form==null)
				
				alumni_job_position_types_Form = new Alumni_job_position_types_Form();
			
			return alumni_job_position_types_Form;
		}

		/**
		 * set the current alumni_job_position_types_Form
		 * @param alumni_job_position_types_Form the new alumni_job_position_types_Form
		 */
		
		public void setAlumni_job_position_types_Form(Alumni_job_position_types_Form alumni_job_position_types_Form){
			
			this.alumni_job_position_types_Form=alumni_job_position_types_Form;
		}
		
		
			/* (non-Javadoc)
			 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
			 */
			@Override
			public String getMainFormBeanCode() {
				return getAlumni_job_position_typescode();
			}
			
			
			
	
	
	
	
	
	
}