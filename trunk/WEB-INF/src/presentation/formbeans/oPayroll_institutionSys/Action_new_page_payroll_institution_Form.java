package presentation.formbeans.oPayroll_institutionSys;




import utils.formbeans.FormBeanContainer;
import utils.formbeans.BOAdderFormBean;

import utils.validator.ValidatorFormAndAction;

import presentation.formbeans.objects.*;

/**
 * This class is a FormBeanContainer for the action action_new_page_payroll_institution.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_new_page_payroll_institution_Form extends FormBeanContainer {

	
	//FormBean for the action
			
			/**
			* nested-pojo-form-bean contained in this container
			**/
			private Payroll_institution_Form payroll_institution_Form = null;
	
	
	

	
	
	
		
		/**
		 * @return returns the code of the current payroll_institution_Form (a shortcut of getPayroll_institution_Form().getPayroll_institutioncode())
		 */
		public String getPayroll_institutioncode() {
			
			return getPayroll_institution_Form().getPayroll_institutioncode();
		}

		/**
		 * Sets the code of the current payroll_institution_Form (a shortcut of getPayroll_institution_Form().setPayroll_institutioncode(String))
		 * @param payroll_institutioncode the new code
		 */
		
		public void setPayroll_institutioncode(String payroll_institutioncode){
			
			getPayroll_institution_Form().setPayroll_institutioncode(payroll_institutioncode);
		}

		/**
		 * @return the current payroll_institution_Form
		 */
		
		public Payroll_institution_Form getPayroll_institution_Form() {
			
			if(payroll_institution_Form==null)
				
				payroll_institution_Form = new Payroll_institution_Form();
			
			return payroll_institution_Form;
		}

		/**
		 * set the current payroll_institution_Form
		 * @param payroll_institution_Form the new payroll_institution_Form
		 */
		
		public void setPayroll_institution_Form(Payroll_institution_Form payroll_institution_Form){
			
			this.payroll_institution_Form=payroll_institution_Form;
		}
		
		
			/* (non-Javadoc)
			 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
			 */
			@Override
			public String getMainFormBeanCode() {
				return getPayroll_institutioncode();
			}
			
			
			
	
	
	
	
	
	
}