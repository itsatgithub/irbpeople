package presentation.formbeans.oIrb_budgetSys;




import utils.formbeans.FormBeanContainer;
import utils.formbeans.BOAdderFormBean;

import utils.validator.ValidatorFormAndAction;

import presentation.formbeans.objects.*;

/**
 * This class is a FormBeanContainer for the action action_modify_page_irb_budget.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_modify_page_irb_budget_Form extends FormBeanContainer {

	
	//FormBean for the action
			
			/**
			* nested-pojo-form-bean contained in this container
			**/
			private Irb_budget_Form irb_budget_Form = null;
	
	
	

	
	
	
		
		/**
		 * @return returns the code of the current irb_budget_Form (a shortcut of getIrb_budget_Form().getIrb_budgetcode())
		 */
		public String getIrb_budgetcode() {
			
			return getIrb_budget_Form().getIrb_budgetcode();
		}

		/**
		 * Sets the code of the current irb_budget_Form (a shortcut of getIrb_budget_Form().setIrb_budgetcode(String))
		 * @param irb_budgetcode the new code
		 */
		
		public void setIrb_budgetcode(String irb_budgetcode){
			
			getIrb_budget_Form().setIrb_budgetcode(irb_budgetcode);
		}

		/**
		 * @return the current irb_budget_Form
		 */
		
		public Irb_budget_Form getIrb_budget_Form() {
			
			if(irb_budget_Form==null)
				
				irb_budget_Form = new Irb_budget_Form();
			
			return irb_budget_Form;
		}

		/**
		 * set the current irb_budget_Form
		 * @param irb_budget_Form the new irb_budget_Form
		 */
		
		public void setIrb_budget_Form(Irb_budget_Form irb_budget_Form){
			
			this.irb_budget_Form=irb_budget_Form;
		}
		
		
			/* (non-Javadoc)
			 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
			 */
			@Override
			public String getMainFormBeanCode() {
				return getIrb_budgetcode();
			}
			
			
			
	
	
	
	
	
	
}