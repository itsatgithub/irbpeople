package presentation.formbeans.oGenderSys;




import utils.formbeans.FormBeanContainer;
import utils.formbeans.BOAdderFormBean;

import utils.validator.ValidatorFormAndAction;

import presentation.formbeans.objects.*;

/**
 * This class is a FormBeanContainer for the action action_modify_modify_page_gender.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_modify_modify_page_gender_Form extends FormBeanContainer {

	
	//FormBean for the action
			
			/**
			* nested-pojo-form-bean contained in this container
			**/
			private Gender_Form gender_Form = null;
	
	
	

	
	
	
		
		/**
		 * @return returns the code of the current gender_Form (a shortcut of getGender_Form().getGendercode())
		 */
		public String getGendercode() {
			
			return getGender_Form().getGendercode();
		}

		/**
		 * Sets the code of the current gender_Form (a shortcut of getGender_Form().setGendercode(String))
		 * @param gendercode the new code
		 */
		
		public void setGendercode(String gendercode){
			
			getGender_Form().setGendercode(gendercode);
		}

		/**
		 * @return the current gender_Form
		 */
		
		public Gender_Form getGender_Form() {
			
			if(gender_Form==null)
				
				gender_Form = new Gender_Form();
			
			return gender_Form;
		}

		/**
		 * set the current gender_Form
		 * @param gender_Form the new gender_Form
		 */
		
		public void setGender_Form(Gender_Form gender_Form){
			
			this.gender_Form=gender_Form;
		}
		
		
			/* (non-Javadoc)
			 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
			 */
			@Override
			public String getMainFormBeanCode() {
				return getGendercode();
			}
			
			
			
	
	
	
	
	
	
}