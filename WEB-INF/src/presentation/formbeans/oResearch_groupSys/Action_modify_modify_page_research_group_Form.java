package presentation.formbeans.oResearch_groupSys;




import utils.formbeans.FormBeanContainer;
import utils.formbeans.BOAdderFormBean;

import utils.validator.ValidatorFormAndAction;

import presentation.formbeans.objects.*;

/**
 * This class is a FormBeanContainer for the action action_modify_modify_page_research_group.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_modify_modify_page_research_group_Form extends FormBeanContainer {

	
	//FormBean for the action
			
			/**
			* nested-pojo-form-bean contained in this container
			**/
			private Research_group_Form research_group_Form = null;
	
	
	

	
	
	
		
		/**
		 * @return returns the code of the current research_group_Form (a shortcut of getResearch_group_Form().getResearch_groupcode())
		 */
		public String getResearch_groupcode() {
			
			return getResearch_group_Form().getResearch_groupcode();
		}

		/**
		 * Sets the code of the current research_group_Form (a shortcut of getResearch_group_Form().setResearch_groupcode(String))
		 * @param research_groupcode the new code
		 */
		
		public void setResearch_groupcode(String research_groupcode){
			
			getResearch_group_Form().setResearch_groupcode(research_groupcode);
		}

		/**
		 * @return the current research_group_Form
		 */
		
		public Research_group_Form getResearch_group_Form() {
			
			if(research_group_Form==null)
				
				research_group_Form = new Research_group_Form();
			
			return research_group_Form;
		}

		/**
		 * set the current research_group_Form
		 * @param research_group_Form the new research_group_Form
		 */
		
		public void setResearch_group_Form(Research_group_Form research_group_Form){
			
			this.research_group_Form=research_group_Form;
		}
		
		
			/* (non-Javadoc)
			 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
			 */
			@Override
			public String getMainFormBeanCode() {
				return getResearch_groupcode();
			}
			
			
			
	
	
	
	
	
	
}