package presentation.formbeans.oCompensationSys;




import utils.formbeans.FormBeanContainer;
import utils.formbeans.BOAdderFormBean;

import utils.validator.ValidatorFormAndAction;

import presentation.formbeans.objects.*;

/**
 * This class is a FormBeanContainer for the action action_add_compensation.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_add_compensation_Form extends FormBeanContainer {

	
	//FormBean for the action
			
			/**
			* nested-pojo-form-bean contained in this container
			**/
			private Compensation_Form compensation_Form = null;
	
	
	

	
	
	
		
		/**
		 * @return returns the code of the current compensation_Form (a shortcut of getCompensation_Form().getCompensationcode())
		 */
		public String getCompensationcode() {
			
			return getCompensation_Form().getCompensationcode();
		}

		/**
		 * Sets the code of the current compensation_Form (a shortcut of getCompensation_Form().setCompensationcode(String))
		 * @param compensationcode the new code
		 */
		
		public void setCompensationcode(String compensationcode){
			
			getCompensation_Form().setCompensationcode(compensationcode);
		}

		/**
		 * @return the current compensation_Form
		 */
		
		public Compensation_Form getCompensation_Form() {
			
			if(compensation_Form==null)
				
				compensation_Form = new Compensation_Form();
			
			return compensation_Form;
		}

		/**
		 * set the current compensation_Form
		 * @param compensation_Form the new compensation_Form
		 */
		
		public void setCompensation_Form(Compensation_Form compensation_Form){
			
			this.compensation_Form=compensation_Form;
		}
		
		
			/* (non-Javadoc)
			 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
			 */
			@Override
			public String getMainFormBeanCode() {
				return getCompensationcode();
			}
			
			
			
	
			/**
			 * Shortcut to the personal code of the Compensation
			 * @return the code of the personal of the Compensation
			 */
			public String getPersonalcode(){
				return getCompensation_Form().getCompensation_personal().getPersonalcode();
			}
			
			/**
			 * Shortcut to the personal code of the Compensation
			 * @param personalcode code of the personal
			 */
			public void setPersonalcode(String personalcode){
				getCompensation_Form().getCompensation_personal().setPersonalcode(personalcode);
			}
	
	
	
	
	
}