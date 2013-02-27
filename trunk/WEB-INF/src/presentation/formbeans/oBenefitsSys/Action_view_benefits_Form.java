package presentation.formbeans.oBenefitsSys;




import utils.formbeans.FormBeanContainer;
import utils.formbeans.BOAdderFormBean;

import utils.validator.ValidatorFormAndAction;

import presentation.formbeans.objects.*;

/**
 * This class is a FormBeanContainer for the action action_view_benefits.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_view_benefits_Form extends FormBeanContainer {

	
	//FormBean for the action
			
			/**
			* nested-pojo-form-bean contained in this container
			**/
			private Benefits_Form benefits_Form = null;
	
	
	

	
	
	
		
		/**
		 * @return returns the code of the current benefits_Form (a shortcut of getBenefits_Form().getBenefitscode())
		 */
		public String getBenefitscode() {
			
			return getBenefits_Form().getBenefitscode();
		}

		/**
		 * Sets the code of the current benefits_Form (a shortcut of getBenefits_Form().setBenefitscode(String))
		 * @param benefitscode the new code
		 */
		
		public void setBenefitscode(String benefitscode){
			
			getBenefits_Form().setBenefitscode(benefitscode);
		}

		/**
		 * @return the current benefits_Form
		 */
		
		public Benefits_Form getBenefits_Form() {
			
			if(benefits_Form==null)
				
				benefits_Form = new Benefits_Form();
			
			return benefits_Form;
		}

		/**
		 * set the current benefits_Form
		 * @param benefits_Form the new benefits_Form
		 */
		
		public void setBenefits_Form(Benefits_Form benefits_Form){
			
			this.benefits_Form=benefits_Form;
		}
		
		
			/* (non-Javadoc)
			 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
			 */
			@Override
			public String getMainFormBeanCode() {
				return getBenefitscode();
			}
			
			
			
	
	
	
	
	
	
}