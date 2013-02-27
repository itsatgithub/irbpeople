package presentation.formbeans.oType_of_contractSys;




import utils.formbeans.FormBeanContainer;
import utils.formbeans.BOAdderFormBean;

import utils.validator.ValidatorFormAndAction;

import presentation.formbeans.objects.*;

/**
 * This class is a FormBeanContainer for the action action_new_page_type_of_contract.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_new_page_type_of_contract_Form extends FormBeanContainer {

	
	//FormBean for the action
			
			/**
			* nested-pojo-form-bean contained in this container
			**/
			private Type_of_contract_Form type_of_contract_Form = null;
	
	
	

	
	
	
		
		/**
		 * @return returns the code of the current type_of_contract_Form (a shortcut of getType_of_contract_Form().getType_of_contractcode())
		 */
		public String getType_of_contractcode() {
			
			return getType_of_contract_Form().getType_of_contractcode();
		}

		/**
		 * Sets the code of the current type_of_contract_Form (a shortcut of getType_of_contract_Form().setType_of_contractcode(String))
		 * @param type_of_contractcode the new code
		 */
		
		public void setType_of_contractcode(String type_of_contractcode){
			
			getType_of_contract_Form().setType_of_contractcode(type_of_contractcode);
		}

		/**
		 * @return the current type_of_contract_Form
		 */
		
		public Type_of_contract_Form getType_of_contract_Form() {
			
			if(type_of_contract_Form==null)
				
				type_of_contract_Form = new Type_of_contract_Form();
			
			return type_of_contract_Form;
		}

		/**
		 * set the current type_of_contract_Form
		 * @param type_of_contract_Form the new type_of_contract_Form
		 */
		
		public void setType_of_contract_Form(Type_of_contract_Form type_of_contract_Form){
			
			this.type_of_contract_Form=type_of_contract_Form;
		}
		
		
			/* (non-Javadoc)
			 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
			 */
			@Override
			public String getMainFormBeanCode() {
				return getType_of_contractcode();
			}
			
			
			
	
	
	
	
	
	
}