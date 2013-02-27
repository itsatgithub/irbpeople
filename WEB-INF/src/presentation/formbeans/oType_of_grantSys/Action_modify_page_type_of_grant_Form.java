package presentation.formbeans.oType_of_grantSys;




import utils.formbeans.FormBeanContainer;
import utils.formbeans.BOAdderFormBean;

import utils.validator.ValidatorFormAndAction;

import presentation.formbeans.objects.*;

/**
 * This class is a FormBeanContainer for the action action_modify_page_type_of_grant.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_modify_page_type_of_grant_Form extends FormBeanContainer {

	
	//FormBean for the action
			
			/**
			* nested-pojo-form-bean contained in this container
			**/
			private Type_of_grant_Form type_of_grant_Form = null;
	
	
	

	
	
	
		
		/**
		 * @return returns the code of the current type_of_grant_Form (a shortcut of getType_of_grant_Form().getType_of_grantcode())
		 */
		public String getType_of_grantcode() {
			
			return getType_of_grant_Form().getType_of_grantcode();
		}

		/**
		 * Sets the code of the current type_of_grant_Form (a shortcut of getType_of_grant_Form().setType_of_grantcode(String))
		 * @param type_of_grantcode the new code
		 */
		
		public void setType_of_grantcode(String type_of_grantcode){
			
			getType_of_grant_Form().setType_of_grantcode(type_of_grantcode);
		}

		/**
		 * @return the current type_of_grant_Form
		 */
		
		public Type_of_grant_Form getType_of_grant_Form() {
			
			if(type_of_grant_Form==null)
				
				type_of_grant_Form = new Type_of_grant_Form();
			
			return type_of_grant_Form;
		}

		/**
		 * set the current type_of_grant_Form
		 * @param type_of_grant_Form the new type_of_grant_Form
		 */
		
		public void setType_of_grant_Form(Type_of_grant_Form type_of_grant_Form){
			
			this.type_of_grant_Form=type_of_grant_Form;
		}
		
		
			/* (non-Javadoc)
			 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
			 */
			@Override
			public String getMainFormBeanCode() {
				return getType_of_grantcode();
			}
			
			
			
	
	
	
	
	
	
}