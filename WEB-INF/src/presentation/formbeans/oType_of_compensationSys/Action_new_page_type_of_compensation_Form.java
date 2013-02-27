package presentation.formbeans.oType_of_compensationSys;




import utils.formbeans.FormBeanContainer;
import utils.formbeans.BOAdderFormBean;

import utils.validator.ValidatorFormAndAction;

import presentation.formbeans.objects.*;

/**
 * This class is a FormBeanContainer for the action action_new_page_type_of_compensation.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_new_page_type_of_compensation_Form extends FormBeanContainer {

	
	//FormBean for the action
			
			/**
			* nested-pojo-form-bean contained in this container
			**/
			private Type_of_compensation_Form type_of_compensation_Form = null;
	
	
	

	
	
	
		
		/**
		 * @return returns the code of the current type_of_compensation_Form (a shortcut of getType_of_compensation_Form().getType_of_compensationcode())
		 */
		public String getType_of_compensationcode() {
			
			return getType_of_compensation_Form().getType_of_compensationcode();
		}

		/**
		 * Sets the code of the current type_of_compensation_Form (a shortcut of getType_of_compensation_Form().setType_of_compensationcode(String))
		 * @param type_of_compensationcode the new code
		 */
		
		public void setType_of_compensationcode(String type_of_compensationcode){
			
			getType_of_compensation_Form().setType_of_compensationcode(type_of_compensationcode);
		}

		/**
		 * @return the current type_of_compensation_Form
		 */
		
		public Type_of_compensation_Form getType_of_compensation_Form() {
			
			if(type_of_compensation_Form==null)
				
				type_of_compensation_Form = new Type_of_compensation_Form();
			
			return type_of_compensation_Form;
		}

		/**
		 * set the current type_of_compensation_Form
		 * @param type_of_compensation_Form the new type_of_compensation_Form
		 */
		
		public void setType_of_compensation_Form(Type_of_compensation_Form type_of_compensation_Form){
			
			this.type_of_compensation_Form=type_of_compensation_Form;
		}
		
		
			/* (non-Javadoc)
			 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
			 */
			@Override
			public String getMainFormBeanCode() {
				return getType_of_compensationcode();
			}
			
			
			
	
	
	
	
	
	
}