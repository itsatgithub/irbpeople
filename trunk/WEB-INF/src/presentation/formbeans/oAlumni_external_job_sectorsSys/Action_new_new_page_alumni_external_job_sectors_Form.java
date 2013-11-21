package presentation.formbeans.oAlumni_external_job_sectorsSys;




import utils.formbeans.FormBeanContainer;
import utils.formbeans.BOAdderFormBean;

import utils.validator.ValidatorFormAndAction;

import presentation.formbeans.objects.*;

/**
 * This class is a FormBeanContainer for the action action_new_new_page_alumni_external_job_sectors.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_new_new_page_alumni_external_job_sectors_Form extends FormBeanContainer {

	
	//FormBean for the action
			
			/**
			* nested-pojo-form-bean contained in this container
			**/
			private Alumni_external_job_sectors_Form alumni_external_job_sectors_Form = null;
	
	
	

	
	
	
		
		/**
		 * @return returns the code of the current alumni_external_job_sectors_Form (a shortcut of getAlumni_external_job_sectors_Form().getAlumni_external_job_sectorscode())
		 */
		public String getAlumni_external_job_sectorscode() {
			
			return getAlumni_external_job_sectors_Form().getAlumni_external_job_sectorscode();
		}

		/**
		 * Sets the code of the current alumni_external_job_sectors_Form (a shortcut of getAlumni_external_job_sectors_Form().setAlumni_external_job_sectorscode(String))
		 * @param alumni_external_job_sectorscode the new code
		 */
		
		public void setAlumni_external_job_sectorscode(String alumni_external_job_sectorscode){
			
			getAlumni_external_job_sectors_Form().setAlumni_external_job_sectorscode(alumni_external_job_sectorscode);
		}

		/**
		 * @return the current alumni_external_job_sectors_Form
		 */
		
		public Alumni_external_job_sectors_Form getAlumni_external_job_sectors_Form() {
			
			if(alumni_external_job_sectors_Form==null)
				
				alumni_external_job_sectors_Form = new Alumni_external_job_sectors_Form();
			
			return alumni_external_job_sectors_Form;
		}

		/**
		 * set the current alumni_external_job_sectors_Form
		 * @param alumni_external_job_sectors_Form the new alumni_external_job_sectors_Form
		 */
		
		public void setAlumni_external_job_sectors_Form(Alumni_external_job_sectors_Form alumni_external_job_sectors_Form){
			
			this.alumni_external_job_sectors_Form=alumni_external_job_sectors_Form;
		}
		
		
			/* (non-Javadoc)
			 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
			 */
			@Override
			public String getMainFormBeanCode() {
				return getAlumni_external_job_sectorscode();
			}
			
			
			
	
	
	
	
	
	
}