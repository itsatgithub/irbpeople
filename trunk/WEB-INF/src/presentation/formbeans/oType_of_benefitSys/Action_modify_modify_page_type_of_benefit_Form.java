package presentation.formbeans.oType_of_benefitSys;




import utils.formbeans.FormBeanContainer;
import utils.formbeans.BOAdderFormBean;

import utils.validator.ValidatorFormAndAction;

import presentation.formbeans.objects.*;

/**
 * This class is a FormBeanContainer for the action action_modify_modify_page_type_of_benefit.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_modify_modify_page_type_of_benefit_Form extends FormBeanContainer {

	
	//FormBean for the action
			
			/**
			* nested-pojo-form-bean contained in this container
			**/
			private Type_of_benefit_Form type_of_benefit_Form = null;
	
	
	

	
	
	
		
		/**
		 * @return returns the code of the current type_of_benefit_Form (a shortcut of getType_of_benefit_Form().getType_of_benefitcode())
		 */
		public String getType_of_benefitcode() {
			
			return getType_of_benefit_Form().getType_of_benefitcode();
		}

		/**
		 * Sets the code of the current type_of_benefit_Form (a shortcut of getType_of_benefit_Form().setType_of_benefitcode(String))
		 * @param type_of_benefitcode the new code
		 */
		
		public void setType_of_benefitcode(String type_of_benefitcode){
			
			getType_of_benefit_Form().setType_of_benefitcode(type_of_benefitcode);
		}

		/**
		 * @return the current type_of_benefit_Form
		 */
		
		public Type_of_benefit_Form getType_of_benefit_Form() {
			
			if(type_of_benefit_Form==null)
				
				type_of_benefit_Form = new Type_of_benefit_Form();
			
			return type_of_benefit_Form;
		}

		/**
		 * set the current type_of_benefit_Form
		 * @param type_of_benefit_Form the new type_of_benefit_Form
		 */
		
		public void setType_of_benefit_Form(Type_of_benefit_Form type_of_benefit_Form){
			
			this.type_of_benefit_Form=type_of_benefit_Form;
		}
		
		
			/* (non-Javadoc)
			 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
			 */
			@Override
			public String getMainFormBeanCode() {
				return getType_of_benefitcode();
			}
			
			
			
	
	
	
	
	
	
}