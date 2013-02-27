package presentation.formbeans.oGrant_concessionSys;




import utils.formbeans.FormBeanContainer;
import utils.formbeans.BOAdderFormBean;

import utils.validator.ValidatorFormAndAction;

import presentation.formbeans.objects.*;

/**
 * This class is a FormBeanContainer for the action action_modify_page_grant_concession.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_modify_page_grant_concession_Form extends FormBeanContainer {

	
	//FormBean for the action
			
			/**
			* nested-pojo-form-bean contained in this container
			**/
			private Grant_concession_Form grant_concession_Form = null;
	
	
	

	
	
	
		
		/**
		 * @return returns the code of the current grant_concession_Form (a shortcut of getGrant_concession_Form().getGrant_concessioncode())
		 */
		public String getGrant_concessioncode() {
			
			return getGrant_concession_Form().getGrant_concessioncode();
		}

		/**
		 * Sets the code of the current grant_concession_Form (a shortcut of getGrant_concession_Form().setGrant_concessioncode(String))
		 * @param grant_concessioncode the new code
		 */
		
		public void setGrant_concessioncode(String grant_concessioncode){
			
			getGrant_concession_Form().setGrant_concessioncode(grant_concessioncode);
		}

		/**
		 * @return the current grant_concession_Form
		 */
		
		public Grant_concession_Form getGrant_concession_Form() {
			
			if(grant_concession_Form==null)
				
				grant_concession_Form = new Grant_concession_Form();
			
			return grant_concession_Form;
		}

		/**
		 * set the current grant_concession_Form
		 * @param grant_concession_Form the new grant_concession_Form
		 */
		
		public void setGrant_concession_Form(Grant_concession_Form grant_concession_Form){
			
			this.grant_concession_Form=grant_concession_Form;
		}
		
		
			/* (non-Javadoc)
			 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
			 */
			@Override
			public String getMainFormBeanCode() {
				return getGrant_concessioncode();
			}
			
			
			
	
	
	
	
	
	
}