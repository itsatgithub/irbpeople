package presentation.formbeans.oProfessionalSys;




import utils.formbeans.FormBeanContainer;
import utils.formbeans.BOAdderFormBean;

import utils.validator.ValidatorFormAndAction;

import presentation.formbeans.objects.*;

/**
 * This class is a FormBeanContainer for the action action_delete_page_professional.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_delete_page_professional_Form extends FormBeanContainer {

	
	//FormBean for the action
			
			/**
			* nested-pojo-form-bean contained in this container
			**/
			private Professional_Form professional_Form = null;
	
	
	

	
	
	
		
		/**
		 * @return returns the code of the current professional_Form (a shortcut of getProfessional_Form().getProfessionalcode())
		 */
		public String getProfessionalcode() {
			
			return getProfessional_Form().getProfessionalcode();
		}

		/**
		 * Sets the code of the current professional_Form (a shortcut of getProfessional_Form().setProfessionalcode(String))
		 * @param professionalcode the new code
		 */
		
		public void setProfessionalcode(String professionalcode){
			
			getProfessional_Form().setProfessionalcode(professionalcode);
		}

		/**
		 * @return the current professional_Form
		 */
		
		public Professional_Form getProfessional_Form() {
			
			if(professional_Form==null)
				
				professional_Form = new Professional_Form();
			
			return professional_Form;
		}

		/**
		 * set the current professional_Form
		 * @param professional_Form the new professional_Form
		 */
		
		public void setProfessional_Form(Professional_Form professional_Form){
			
			this.professional_Form=professional_Form;
		}
		
		
			/* (non-Javadoc)
			 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
			 */
			@Override
			public String getMainFormBeanCode() {
				return getProfessionalcode();
			}
			
			
			
	
	
	
	
	
	
}