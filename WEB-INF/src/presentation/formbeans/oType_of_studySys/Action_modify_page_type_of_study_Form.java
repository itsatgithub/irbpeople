package presentation.formbeans.oType_of_studySys;




import utils.formbeans.FormBeanContainer;
import utils.formbeans.BOAdderFormBean;

import utils.validator.ValidatorFormAndAction;

import presentation.formbeans.objects.*;

/**
 * This class is a FormBeanContainer for the action action_modify_page_type_of_study.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_modify_page_type_of_study_Form extends FormBeanContainer {

	
	//FormBean for the action
			
			/**
			* nested-pojo-form-bean contained in this container
			**/
			private Type_of_study_Form type_of_study_Form = null;
	
	
	

	
	
	
		
		/**
		 * @return returns the code of the current type_of_study_Form (a shortcut of getType_of_study_Form().getType_of_studycode())
		 */
		public String getType_of_studycode() {
			
			return getType_of_study_Form().getType_of_studycode();
		}

		/**
		 * Sets the code of the current type_of_study_Form (a shortcut of getType_of_study_Form().setType_of_studycode(String))
		 * @param type_of_studycode the new code
		 */
		
		public void setType_of_studycode(String type_of_studycode){
			
			getType_of_study_Form().setType_of_studycode(type_of_studycode);
		}

		/**
		 * @return the current type_of_study_Form
		 */
		
		public Type_of_study_Form getType_of_study_Form() {
			
			if(type_of_study_Form==null)
				
				type_of_study_Form = new Type_of_study_Form();
			
			return type_of_study_Form;
		}

		/**
		 * set the current type_of_study_Form
		 * @param type_of_study_Form the new type_of_study_Form
		 */
		
		public void setType_of_study_Form(Type_of_study_Form type_of_study_Form){
			
			this.type_of_study_Form=type_of_study_Form;
		}
		
		
			/* (non-Javadoc)
			 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
			 */
			@Override
			public String getMainFormBeanCode() {
				return getType_of_studycode();
			}
			
			
			
	
	
	
	
	
	
}