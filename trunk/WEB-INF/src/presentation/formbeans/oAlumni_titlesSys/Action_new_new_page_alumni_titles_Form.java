package presentation.formbeans.oAlumni_titlesSys;




import utils.formbeans.FormBeanContainer;
import utils.formbeans.BOAdderFormBean;

import utils.validator.ValidatorFormAndAction;

import presentation.formbeans.objects.*;

/**
 * This class is a FormBeanContainer for the action action_new_new_page_alumni_titles.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_new_new_page_alumni_titles_Form extends FormBeanContainer {

	
	//FormBean for the action
			
			/**
			* nested-pojo-form-bean contained in this container
			**/
			private Alumni_titles_Form alumni_titles_Form = null;
	
	
	

	
	
	
		
		/**
		 * @return returns the code of the current alumni_titles_Form (a shortcut of getAlumni_titles_Form().getAlumni_titlescode())
		 */
		public String getAlumni_titlescode() {
			
			return getAlumni_titles_Form().getAlumni_titlescode();
		}

		/**
		 * Sets the code of the current alumni_titles_Form (a shortcut of getAlumni_titles_Form().setAlumni_titlescode(String))
		 * @param alumni_titlescode the new code
		 */
		
		public void setAlumni_titlescode(String alumni_titlescode){
			
			getAlumni_titles_Form().setAlumni_titlescode(alumni_titlescode);
		}

		/**
		 * @return the current alumni_titles_Form
		 */
		
		public Alumni_titles_Form getAlumni_titles_Form() {
			
			if(alumni_titles_Form==null)
				
				alumni_titles_Form = new Alumni_titles_Form();
			
			return alumni_titles_Form;
		}

		/**
		 * set the current alumni_titles_Form
		 * @param alumni_titles_Form the new alumni_titles_Form
		 */
		
		public void setAlumni_titles_Form(Alumni_titles_Form alumni_titles_Form){
			
			this.alumni_titles_Form=alumni_titles_Form;
		}
		
		
			/* (non-Javadoc)
			 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
			 */
			@Override
			public String getMainFormBeanCode() {
				return getAlumni_titlescode();
			}
			
			
			
	
	
	
	
	
	
}