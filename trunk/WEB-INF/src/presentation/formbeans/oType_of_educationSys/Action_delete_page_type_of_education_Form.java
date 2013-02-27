package presentation.formbeans.oType_of_educationSys;




import utils.formbeans.FormBeanContainer;
import utils.formbeans.BOAdderFormBean;

import utils.validator.ValidatorFormAndAction;

import presentation.formbeans.objects.*;

/**
 * This class is a FormBeanContainer for the action action_delete_page_type_of_education.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_delete_page_type_of_education_Form extends FormBeanContainer {

	
	//FormBean for the action
			
			/**
			* nested-pojo-form-bean contained in this container
			**/
			private Type_of_education_Form type_of_education_Form = null;
	
	
	

	
	
	
		
		/**
		 * @return returns the code of the current type_of_education_Form (a shortcut of getType_of_education_Form().getType_of_educationcode())
		 */
		public String getType_of_educationcode() {
			
			return getType_of_education_Form().getType_of_educationcode();
		}

		/**
		 * Sets the code of the current type_of_education_Form (a shortcut of getType_of_education_Form().setType_of_educationcode(String))
		 * @param type_of_educationcode the new code
		 */
		
		public void setType_of_educationcode(String type_of_educationcode){
			
			getType_of_education_Form().setType_of_educationcode(type_of_educationcode);
		}

		/**
		 * @return the current type_of_education_Form
		 */
		
		public Type_of_education_Form getType_of_education_Form() {
			
			if(type_of_education_Form==null)
				
				type_of_education_Form = new Type_of_education_Form();
			
			return type_of_education_Form;
		}

		/**
		 * set the current type_of_education_Form
		 * @param type_of_education_Form the new type_of_education_Form
		 */
		
		public void setType_of_education_Form(Type_of_education_Form type_of_education_Form){
			
			this.type_of_education_Form=type_of_education_Form;
		}
		
		
			/* (non-Javadoc)
			 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
			 */
			@Override
			public String getMainFormBeanCode() {
				return getType_of_educationcode();
			}
			
			
			
	
	
	
	
	
	
}