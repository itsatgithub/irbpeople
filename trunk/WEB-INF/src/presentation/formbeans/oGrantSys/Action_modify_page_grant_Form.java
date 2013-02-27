package presentation.formbeans.oGrantSys;




import utils.formbeans.FormBeanContainer;
import utils.formbeans.BOAdderFormBean;

import utils.validator.ValidatorFormAndAction;

import presentation.formbeans.objects.*;

/**
 * This class is a FormBeanContainer for the action action_modify_page_grant.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_modify_page_grant_Form extends FormBeanContainer {

	
	//FormBean for the action
			
			/**
			* nested-pojo-form-bean contained in this container
			**/
			private Grant_Form grant_Form = null;
	
	
	

	
	
	
		
		/**
		 * @return returns the code of the current grant_Form (a shortcut of getGrant_Form().getGrantcode())
		 */
		public String getGrantcode() {
			
			return getGrant_Form().getGrantcode();
		}

		/**
		 * Sets the code of the current grant_Form (a shortcut of getGrant_Form().setGrantcode(String))
		 * @param grantcode the new code
		 */
		
		public void setGrantcode(String grantcode){
			
			getGrant_Form().setGrantcode(grantcode);
		}

		/**
		 * @return the current grant_Form
		 */
		
		public Grant_Form getGrant_Form() {
			
			if(grant_Form==null)
				
				grant_Form = new Grant_Form();
			
			return grant_Form;
		}

		/**
		 * set the current grant_Form
		 * @param grant_Form the new grant_Form
		 */
		
		public void setGrant_Form(Grant_Form grant_Form){
			
			this.grant_Form=grant_Form;
		}
		
		
			/* (non-Javadoc)
			 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
			 */
			@Override
			public String getMainFormBeanCode() {
				return getGrantcode();
			}
			
			
			
	
	
	
	
	
	
}