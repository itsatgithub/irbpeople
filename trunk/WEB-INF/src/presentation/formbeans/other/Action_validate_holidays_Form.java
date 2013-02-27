package presentation.formbeans.other;




import utils.formbeans.FormBeanContainer;
import utils.formbeans.BOAdderFormBean;

import utils.validator.ValidatorFormAndAction;

import presentation.formbeans.objects.*;

/**
 * This class is a FormBeanContainer for the action action_view_details.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_validate_holidays_Form extends FormBeanContainer {

	
	//FormBean for the action
			
			/**
			* nested-pojo-form-bean contained in this container
			**/
			private Irbholiday_Form irbholiday_Form = null;
			private String observacions = null;
			
			
		public String getIrbholidaycode() {
			return getIrbholiday_Form().getIrbholidaycode();
		}
		
		public void setIrbholidaycode(String irbholidaycode) {
			getIrbholiday_Form().setIrbholidaycode(irbholidaycode);
		}


		/**
		 * @return the current personal_Form
		 */
		
		public Irbholiday_Form getIrbholiday_Form() {
			
			if(irbholiday_Form==null)
				
				irbholiday_Form = new Irbholiday_Form();
			
			return irbholiday_Form;
		}

		/**
		 * set the current personal_Form
		 * @param irbholiday_Form the new personal_Form
		 */
		
		public void setIrbholiday_Form(Irbholiday_Form irbholiday_Form){
			
			this.irbholiday_Form=irbholiday_Form;
		}
		
		
			/* (non-Javadoc)
			 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
			 */
			@Override
			public String getMainFormBeanCode() {
				return getIrbholidaycode();
			}
			
			/**
			 * @return returns the code of the current personal_Form (a shortcut of getPersonal_Form().getPersonalcode())
			 */
			public String getValidationcode() {
				
				return getIrbholiday_Form().getValidationcode();
			}

			/**
			 * Sets the code of the current personal_Form (a shortcut of getPersonal_Form().setPersonalcode(String))
			 * @param personalcode the new code
			 */
			
			public void setValidationcode(String validationcode){
				
				getIrbholiday_Form().setValidationcode(validationcode);
			}

			public String getObservacions() {
				return observacions;
			}

			public void setObservacions(String observacions) {
				this.observacions = observacions;
			}
			
		
}