package presentation.formbeans.oEducationSys;




import utils.formbeans.FormBeanContainer;
import utils.formbeans.BOAdderFormBean;

import utils.validator.ValidatorFormAndAction;

import presentation.formbeans.objects.*;

/**
 * This class is a FormBeanContainer for the action action_delete_delete_page_education.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_delete_delete_page_education_Form extends FormBeanContainer {

	
	//FormBean for the action
			
			/**
			* nested-pojo-form-bean contained in this container
			**/
			private Education_Form education_Form = null;
	
	
	

	
	
	
		
		/**
		 * @return returns the code of the current education_Form (a shortcut of getEducation_Form().getEducationcode())
		 */
		public String getEducationcode() {
			
			return getEducation_Form().getEducationcode();
		}

		/**
		 * Sets the code of the current education_Form (a shortcut of getEducation_Form().setEducationcode(String))
		 * @param educationcode the new code
		 */
		
		public void setEducationcode(String educationcode){
			
			getEducation_Form().setEducationcode(educationcode);
		}

		/**
		 * @return the current education_Form
		 */
		
		public Education_Form getEducation_Form() {
			
			if(education_Form==null)
				
				education_Form = new Education_Form();
			
			return education_Form;
		}

		/**
		 * set the current education_Form
		 * @param education_Form the new education_Form
		 */
		
		public void setEducation_Form(Education_Form education_Form){
			
			this.education_Form=education_Form;
		}
		
		
			/* (non-Javadoc)
			 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
			 */
			@Override
			public String getMainFormBeanCode() {
				return getEducationcode();
			}
			
			
			
	
	
	
	
	
	
}