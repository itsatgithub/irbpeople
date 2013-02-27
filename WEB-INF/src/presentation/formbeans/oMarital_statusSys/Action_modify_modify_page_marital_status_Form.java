package presentation.formbeans.oMarital_statusSys;




import utils.formbeans.FormBeanContainer;
import utils.formbeans.BOAdderFormBean;

import utils.validator.ValidatorFormAndAction;

import presentation.formbeans.objects.*;

/**
 * This class is a FormBeanContainer for the action action_modify_modify_page_marital_status.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_modify_modify_page_marital_status_Form extends FormBeanContainer {

	
	//FormBean for the action
			
			/**
			* nested-pojo-form-bean contained in this container
			**/
			private Marital_status_Form marital_status_Form = null;
	
	
	

	
	
	
		
		/**
		 * @return returns the code of the current marital_status_Form (a shortcut of getMarital_status_Form().getMarital_statuscode())
		 */
		public String getMarital_statuscode() {
			
			return getMarital_status_Form().getMarital_statuscode();
		}

		/**
		 * Sets the code of the current marital_status_Form (a shortcut of getMarital_status_Form().setMarital_statuscode(String))
		 * @param marital_statuscode the new code
		 */
		
		public void setMarital_statuscode(String marital_statuscode){
			
			getMarital_status_Form().setMarital_statuscode(marital_statuscode);
		}

		/**
		 * @return the current marital_status_Form
		 */
		
		public Marital_status_Form getMarital_status_Form() {
			
			if(marital_status_Form==null)
				
				marital_status_Form = new Marital_status_Form();
			
			return marital_status_Form;
		}

		/**
		 * set the current marital_status_Form
		 * @param marital_status_Form the new marital_status_Form
		 */
		
		public void setMarital_status_Form(Marital_status_Form marital_status_Form){
			
			this.marital_status_Form=marital_status_Form;
		}
		
		
			/* (non-Javadoc)
			 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
			 */
			@Override
			public String getMainFormBeanCode() {
				return getMarital_statuscode();
			}
			
			
			
	
	
	
	
	
	
}