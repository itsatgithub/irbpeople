package presentation.formbeans.oAlumni_communicationsSys;




import utils.formbeans.FormBeanContainer;
import utils.formbeans.BOAdderFormBean;

import utils.validator.ValidatorFormAndAction;

import presentation.formbeans.objects.*;

/**
 * This class is a FormBeanContainer for the action action_new_page_alumni_communications.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_new_page_alumni_communications_Form extends FormBeanContainer {

	
	//FormBean for the action
			
			/**
			* nested-pojo-form-bean contained in this container
			**/
			private Alumni_communications_Form alumni_communications_Form = null;
	
	
	

	
	
	
		
		/**
		 * @return returns the code of the current alumni_communications_Form (a shortcut of getAlumni_communications_Form().getAlumni_communicationscode())
		 */
		public String getAlumni_communicationscode() {
			
			return getAlumni_communications_Form().getAlumni_communicationscode();
		}

		/**
		 * Sets the code of the current alumni_communications_Form (a shortcut of getAlumni_communications_Form().setAlumni_communicationscode(String))
		 * @param alumni_communicationscode the new code
		 */
		
		public void setAlumni_communicationscode(String alumni_communicationscode){
			
			getAlumni_communications_Form().setAlumni_communicationscode(alumni_communicationscode);
		}

		/**
		 * @return the current alumni_communications_Form
		 */
		
		public Alumni_communications_Form getAlumni_communications_Form() {
			
			if(alumni_communications_Form==null)
				
				alumni_communications_Form = new Alumni_communications_Form();
			
			return alumni_communications_Form;
		}

		/**
		 * set the current alumni_communications_Form
		 * @param alumni_communications_Form the new alumni_communications_Form
		 */
		
		public void setAlumni_communications_Form(Alumni_communications_Form alumni_communications_Form){
			
			this.alumni_communications_Form=alumni_communications_Form;
		}
		
		
			/* (non-Javadoc)
			 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
			 */
			@Override
			public String getMainFormBeanCode() {
				return getAlumni_communicationscode();
			}
			
			
			
	
	
	
	
	
	
}