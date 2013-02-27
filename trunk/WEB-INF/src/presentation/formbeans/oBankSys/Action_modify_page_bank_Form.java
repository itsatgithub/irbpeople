package presentation.formbeans.oBankSys;




import utils.formbeans.FormBeanContainer;
import utils.formbeans.BOAdderFormBean;

import utils.validator.ValidatorFormAndAction;

import presentation.formbeans.objects.*;

/**
 * This class is a FormBeanContainer for the action action_modify_page_bank.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_modify_page_bank_Form extends FormBeanContainer {

	
	//FormBean for the action
			
			/**
			* nested-pojo-form-bean contained in this container
			**/
			private Bank_Form bank_Form = null;
	
	
	

	
	
	
		
		/**
		 * @return returns the code of the current bank_Form (a shortcut of getBank_Form().getBankcode())
		 */
		public String getBankcode() {
			
			return getBank_Form().getBankcode();
		}

		/**
		 * Sets the code of the current bank_Form (a shortcut of getBank_Form().setBankcode(String))
		 * @param bankcode the new code
		 */
		
		public void setBankcode(String bankcode){
			
			getBank_Form().setBankcode(bankcode);
		}

		/**
		 * @return the current bank_Form
		 */
		
		public Bank_Form getBank_Form() {
			
			if(bank_Form==null)
				
				bank_Form = new Bank_Form();
			
			return bank_Form;
		}

		/**
		 * set the current bank_Form
		 * @param bank_Form the new bank_Form
		 */
		
		public void setBank_Form(Bank_Form bank_Form){
			
			this.bank_Form=bank_Form;
		}
		
		
			/* (non-Javadoc)
			 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
			 */
			@Override
			public String getMainFormBeanCode() {
				return getBankcode();
			}
			
			
			
	
	
	
	
	
	
}