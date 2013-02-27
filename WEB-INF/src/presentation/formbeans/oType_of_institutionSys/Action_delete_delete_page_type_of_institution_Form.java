package presentation.formbeans.oType_of_institutionSys;




import utils.formbeans.FormBeanContainer;
import utils.formbeans.BOAdderFormBean;

import utils.validator.ValidatorFormAndAction;

import presentation.formbeans.objects.*;

/**
 * This class is a FormBeanContainer for the action action_delete_delete_page_type_of_institution.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_delete_delete_page_type_of_institution_Form extends FormBeanContainer {

	
	//FormBean for the action
			
			/**
			* nested-pojo-form-bean contained in this container
			**/
			private Type_of_institution_Form type_of_institution_Form = null;
	
	
	

	
	
	
		
		/**
		 * @return returns the code of the current type_of_institution_Form (a shortcut of getType_of_institution_Form().getType_of_institutioncode())
		 */
		public String getType_of_institutioncode() {
			
			return getType_of_institution_Form().getType_of_institutioncode();
		}

		/**
		 * Sets the code of the current type_of_institution_Form (a shortcut of getType_of_institution_Form().setType_of_institutioncode(String))
		 * @param type_of_institutioncode the new code
		 */
		
		public void setType_of_institutioncode(String type_of_institutioncode){
			
			getType_of_institution_Form().setType_of_institutioncode(type_of_institutioncode);
		}

		/**
		 * @return the current type_of_institution_Form
		 */
		
		public Type_of_institution_Form getType_of_institution_Form() {
			
			if(type_of_institution_Form==null)
				
				type_of_institution_Form = new Type_of_institution_Form();
			
			return type_of_institution_Form;
		}

		/**
		 * set the current type_of_institution_Form
		 * @param type_of_institution_Form the new type_of_institution_Form
		 */
		
		public void setType_of_institution_Form(Type_of_institution_Form type_of_institution_Form){
			
			this.type_of_institution_Form=type_of_institution_Form;
		}
		
		
			/* (non-Javadoc)
			 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
			 */
			@Override
			public String getMainFormBeanCode() {
				return getType_of_institutioncode();
			}
			
			
			
	
	
	
	
	
	
}