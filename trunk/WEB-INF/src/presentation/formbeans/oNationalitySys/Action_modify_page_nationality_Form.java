package presentation.formbeans.oNationalitySys;




import utils.formbeans.FormBeanContainer;
import utils.formbeans.BOAdderFormBean;

import utils.validator.ValidatorFormAndAction;

import presentation.formbeans.objects.*;

/**
 * This class is a FormBeanContainer for the action action_modify_page_nationality.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_modify_page_nationality_Form extends FormBeanContainer {

	
	//FormBean for the action
			
			/**
			* nested-pojo-form-bean contained in this container
			**/
			private Nationality_Form nationality_Form = null;
	
	
	

	
	
	
		
		/**
		 * @return returns the code of the current nationality_Form (a shortcut of getNationality_Form().getNationalitycode())
		 */
		public String getNationalitycode() {
			
			return getNationality_Form().getNationalitycode();
		}

		/**
		 * Sets the code of the current nationality_Form (a shortcut of getNationality_Form().setNationalitycode(String))
		 * @param nationalitycode the new code
		 */
		
		public void setNationalitycode(String nationalitycode){
			
			getNationality_Form().setNationalitycode(nationalitycode);
		}

		/**
		 * @return the current nationality_Form
		 */
		
		public Nationality_Form getNationality_Form() {
			
			if(nationality_Form==null)
				
				nationality_Form = new Nationality_Form();
			
			return nationality_Form;
		}

		/**
		 * set the current nationality_Form
		 * @param nationality_Form the new nationality_Form
		 */
		
		public void setNationality_Form(Nationality_Form nationality_Form){
			
			this.nationality_Form=nationality_Form;
		}
		
		
			/* (non-Javadoc)
			 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
			 */
			@Override
			public String getMainFormBeanCode() {
				return getNationalitycode();
			}
			
			
			
	
	
	
	
	
	
}