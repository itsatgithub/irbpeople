package presentation.formbeans.oPersonalSys;




import utils.formbeans.FormBeanContainer;
import utils.formbeans.BOAdderFormBean;

import utils.validator.ValidatorFormAndAction;

import presentation.formbeans.objects.*;

/**
 * This class is a FormBeanContainer for the action action_new_new_page_personal.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_new_new_page_personal_Form extends FormBeanContainer {

	
	//FormBean for the action
			
			/**
			* nested-pojo-form-bean contained in this container
			**/
			private Personal_Form personal_Form = null;
	
	
			private PersonalPhoto_Form personalPhoto_Form;
			
			private BOAdderFormBean<Child_Form> personal_child = null;
			
			public PersonalPhoto_Form getPersonalPhoto_Form() {
				if(personalPhoto_Form==null) personalPhoto_Form=new PersonalPhoto_Form();
				return personalPhoto_Form;
			}

			public void setPersonalPhoto_Form(PersonalPhoto_Form personalPhoto_Form) {
				this.personalPhoto_Form = personalPhoto_Form;
			}

	
	
	
		
		/**
		 * @return returns the code of the current personal_Form (a shortcut of getPersonal_Form().getPersonalcode())
		 */
		public String getPersonalcode() {
			
			return getPersonal_Form().getPersonalcode();
		}

		/**
		 * Sets the code of the current personal_Form (a shortcut of getPersonal_Form().setPersonalcode(String))
		 * @param personalcode the new code
		 */
		
		public void setPersonalcode(String personalcode){
			
			getPersonal_Form().setPersonalcode(personalcode);
		}

		/**
		 * @return the current personal_Form
		 */
		
		public Personal_Form getPersonal_Form() {
			
			if(personal_Form==null)
				
				personal_Form = new Personal_Form();
			
			return personal_Form;
		}

		/**
		 * set the current personal_Form
		 * @param personal_Form the new personal_Form
		 */
		
		public void setPersonal_Form(Personal_Form personal_Form){
			
			this.personal_Form=personal_Form;
		}
		
		
			/* (non-Javadoc)
			 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
			 */
			@Override
			public String getMainFormBeanCode() {
				return getPersonalcode();
			}
			
			
			public BOAdderFormBean<Child_Form> getPersonal_child() {
				if(personal_child == null) {
					personal_child = new BOAdderFormBean<Child_Form>(Child_Form.class);
				}
				return personal_child;
			}

			public void setPersonal_child(BOAdderFormBean<Child_Form> personal_child) {
				this.personal_child = personal_child;
			}
				
			
	
	
	
	
	
	
}