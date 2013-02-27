package presentation.formbeans.oType_of_holidaysSys;




import utils.formbeans.FormBeanContainer;
import utils.formbeans.BOAdderFormBean;

import utils.validator.ValidatorFormAndAction;

import presentation.formbeans.objects.*;

/**
 * This class is a FormBeanContainer for the action action_new_new_page_type_of_holidays.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_new_new_page_type_of_holidays_Form extends FormBeanContainer {

	
	//FormBean for the action
			
			/**
			* nested-pojo-form-bean contained in this container
			**/
			private Type_of_holidays_Form type_of_holidays_Form = null;
	
	
	

	
	
	
		
		/**
		 * @return returns the code of the current type_of_holidays_Form (a shortcut of getType_of_holidays_Form().getType_of_holidayscode())
		 */
		public String getType_of_holidayscode() {
			
			return getType_of_holidays_Form().getType_of_holidayscode();
		}

		/**
		 * Sets the code of the current type_of_holidays_Form (a shortcut of getType_of_holidays_Form().setType_of_holidayscode(String))
		 * @param type_of_holidayscode the new code
		 */
		
		public void setType_of_holidayscode(String type_of_holidayscode){
			
			getType_of_holidays_Form().setType_of_holidayscode(type_of_holidayscode);
		}

		/**
		 * @return the current type_of_holidays_Form
		 */
		
		public Type_of_holidays_Form getType_of_holidays_Form() {
			
			if(type_of_holidays_Form==null)
				
				type_of_holidays_Form = new Type_of_holidays_Form();
			
			return type_of_holidays_Form;
		}

		/**
		 * set the current type_of_holidays_Form
		 * @param type_of_holidays_Form the new type_of_holidays_Form
		 */
		
		public void setType_of_holidays_Form(Type_of_holidays_Form type_of_holidays_Form){
			
			this.type_of_holidays_Form=type_of_holidays_Form;
		}
		
		
			/* (non-Javadoc)
			 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
			 */
			@Override
			public String getMainFormBeanCode() {
				return getType_of_holidayscode();
			}
			
			
			
	
	
	
	
	
	
}