package presentation.formbeans.oPaymentSys;




import utils.formbeans.FormBeanContainer;
import utils.formbeans.BOAdderFormBean;

import utils.validator.ValidatorFormAndAction;

import presentation.formbeans.objects.*;

/**
 * This class is a FormBeanContainer for the action action_modify_modify_page_payment.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_modify_modify_page_payment_Form extends FormBeanContainer {

	
	//FormBean for the action
			
			/**
			* nested-pojo-form-bean contained in this container
			**/
			private Payment_Form payment_Form = null;
	
	
	

	
	
	
		
		/**
		 * @return returns the code of the current payment_Form (a shortcut of getPayment_Form().getPaymentcode())
		 */
		public String getPaymentcode() {
			
			return getPayment_Form().getPaymentcode();
		}

		/**
		 * Sets the code of the current payment_Form (a shortcut of getPayment_Form().setPaymentcode(String))
		 * @param paymentcode the new code
		 */
		
		public void setPaymentcode(String paymentcode){
			
			getPayment_Form().setPaymentcode(paymentcode);
		}

		/**
		 * @return the current payment_Form
		 */
		
		public Payment_Form getPayment_Form() {
			
			if(payment_Form==null)
				
				payment_Form = new Payment_Form();
			
			return payment_Form;
		}

		/**
		 * set the current payment_Form
		 * @param payment_Form the new payment_Form
		 */
		
		public void setPayment_Form(Payment_Form payment_Form){
			
			this.payment_Form=payment_Form;
		}
		
		
			/* (non-Javadoc)
			 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
			 */
			@Override
			public String getMainFormBeanCode() {
				return getPaymentcode();
			}
			
			
			
	
	
	
	
	
	
}