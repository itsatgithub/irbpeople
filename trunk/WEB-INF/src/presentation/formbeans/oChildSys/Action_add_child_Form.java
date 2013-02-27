package presentation.formbeans.oChildSys;




import utils.formbeans.FormBeanContainer;
import utils.formbeans.BOAdderFormBean;

import utils.validator.ValidatorFormAndAction;

import presentation.formbeans.objects.*;

/**
 * This class is a FormBeanContainer for the action action_add_child.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_add_child_Form extends FormBeanContainer {

	
	//FormBean for the action
			
			/**
			* nested-pojo-form-bean contained in this container
			**/
			private Child_Form child_Form = null;
	
	
	

	
	
	
		
		/**
		 * @return returns the code of the current child_Form (a shortcut of getChild_Form().getChildcode())
		 */
		public String getChildcode() {
			
			return getChild_Form().getChildcode();
		}

		/**
		 * Sets the code of the current child_Form (a shortcut of getChild_Form().setChildcode(String))
		 * @param childcode the new code
		 */
		
		public void setChildcode(String childcode){
			
			getChild_Form().setChildcode(childcode);
		}

		/**
		 * @return the current child_Form
		 */
		
		public Child_Form getChild_Form() {
			
			if(child_Form==null)
				
				child_Form = new Child_Form();
			
			return child_Form;
		}

		/**
		 * set the current child_Form
		 * @param child_Form the new child_Form
		 */
		
		public void setChild_Form(Child_Form child_Form){
			
			this.child_Form=child_Form;
		}
		
		
			/* (non-Javadoc)
			 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
			 */
			@Override
			public String getMainFormBeanCode() {
				return getChildcode();
			}
			
			
			
			
			
			/**
			 * Shortcut to the personal code of the child (the parent)
			 * @return the code of the personal of the work experience
			 */
			public String getPersonalcode(){
				return getChild_Form().getChild_personal().getPersonalcode();
			}
			
			/**
			 * Shortcut to the personal code of the child (the parent)
			 * @param personalcode code of the personal
			 */
			public void setPersonalcode(String personalcode){
				getChild_Form().getChild_personal().setPersonalcode(personalcode);
			}
	
	
	
	
	
}