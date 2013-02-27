package presentation.formbeans.oAuditmessageSys;




import utils.formbeans.FormBeanContainer;
import utils.formbeans.BOAdderFormBean;

import utils.validator.ValidatorFormAndAction;

import presentation.formbeans.objects.*;

/**
 * This class is a FormBeanContainer for the action action_todelete_new_page_auditmessage.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_todelete_new_page_auditmessage_Form extends FormBeanContainer {

	
	//FormBean for the action
			
			/**
			* nested-pojo-form-bean contained in this container
			**/
			private Auditmessage_Form auditmessage_Form = null;
	
	
	

	
	
	
		
		/**
		 * @return returns the code of the current auditmessage_Form (a shortcut of getAuditmessage_Form().getAuditmessagecode())
		 */
		public String getAuditmessagecode() {
			
			return getAuditmessage_Form().getAuditmessagecode();
		}

		/**
		 * Sets the code of the current auditmessage_Form (a shortcut of getAuditmessage_Form().setAuditmessagecode(String))
		 * @param auditmessagecode the new code
		 */
		
		public void setAuditmessagecode(String auditmessagecode){
			
			getAuditmessage_Form().setAuditmessagecode(auditmessagecode);
		}

		/**
		 * @return the current auditmessage_Form
		 */
		
		public Auditmessage_Form getAuditmessage_Form() {
			
			if(auditmessage_Form==null)
				
				auditmessage_Form = new Auditmessage_Form();
			
			return auditmessage_Form;
		}

		/**
		 * set the current auditmessage_Form
		 * @param auditmessage_Form the new auditmessage_Form
		 */
		
		public void setAuditmessage_Form(Auditmessage_Form auditmessage_Form){
			
			this.auditmessage_Form=auditmessage_Form;
		}
		
		
			/* (non-Javadoc)
			 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
			 */
			@Override
			public String getMainFormBeanCode() {
				return getAuditmessagecode();
			}
			
			
			
	
	
	
	
	
	
}