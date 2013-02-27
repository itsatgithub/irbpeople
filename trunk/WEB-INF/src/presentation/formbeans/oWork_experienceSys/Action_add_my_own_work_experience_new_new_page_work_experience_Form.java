package presentation.formbeans.oWork_experienceSys;




import utils.formbeans.FormBeanContainer;
import utils.formbeans.BOAdderFormBean;

import utils.validator.ValidatorFormAndAction;

import presentation.formbeans.objects.*;

/**
 * This class is a FormBeanContainer for the action action_add_my_own_work_experience_new_new_page_work_experience.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_add_my_own_work_experience_new_new_page_work_experience_Form extends FormBeanContainer {

	
	//FormBean for the action
			
			/**
			* nested-pojo-form-bean contained in this container
			**/
			private Work_experience_Form work_experience_Form = null;
	
	
	

	
	
	
		
		/**
		 * @return returns the code of the current work_experience_Form (a shortcut of getWork_experience_Form().getWork_experiencecode())
		 */
		public String getWork_experiencecode() {
			
			return getWork_experience_Form().getWork_experiencecode();
		}

		/**
		 * Sets the code of the current work_experience_Form (a shortcut of getWork_experience_Form().setWork_experiencecode(String))
		 * @param work_experiencecode the new code
		 */
		
		public void setWork_experiencecode(String work_experiencecode){
			
			getWork_experience_Form().setWork_experiencecode(work_experiencecode);
		}

		/**
		 * @return the current work_experience_Form
		 */
		
		public Work_experience_Form getWork_experience_Form() {
			
			if(work_experience_Form==null)
				
				work_experience_Form = new Work_experience_Form();
			
			return work_experience_Form;
		}

		/**
		 * set the current work_experience_Form
		 * @param work_experience_Form the new work_experience_Form
		 */
		
		public void setWork_experience_Form(Work_experience_Form work_experience_Form){
			
			this.work_experience_Form=work_experience_Form;
		}
		
		
			/* (non-Javadoc)
			 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
			 */
			@Override
			public String getMainFormBeanCode() {
				return getWork_experiencecode();
			}
			
			
			
	
	
	
	
	
	
}