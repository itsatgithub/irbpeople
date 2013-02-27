package presentation.formbeans.oPositionSys;




import utils.formbeans.FormBeanContainer;
import utils.formbeans.BOAdderFormBean;

import utils.validator.ValidatorFormAndAction;

import presentation.formbeans.objects.*;

/**
 * This class is a FormBeanContainer for the action action_delete_page_position.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_delete_page_position_Form extends FormBeanContainer {

	
	//FormBean for the action
			
			/**
			* nested-pojo-form-bean contained in this container
			**/
			private Position_Form position_Form = null;
	
	
	

	
	
	
		
		/**
		 * @return returns the code of the current position_Form (a shortcut of getPosition_Form().getPositioncode())
		 */
		public String getPositioncode() {
			
			return getPosition_Form().getPositioncode();
		}

		/**
		 * Sets the code of the current position_Form (a shortcut of getPosition_Form().setPositioncode(String))
		 * @param positioncode the new code
		 */
		
		public void setPositioncode(String positioncode){
			
			getPosition_Form().setPositioncode(positioncode);
		}

		/**
		 * @return the current position_Form
		 */
		
		public Position_Form getPosition_Form() {
			
			if(position_Form==null)
				
				position_Form = new Position_Form();
			
			return position_Form;
		}

		/**
		 * set the current position_Form
		 * @param position_Form the new position_Form
		 */
		
		public void setPosition_Form(Position_Form position_Form){
			
			this.position_Form=position_Form;
		}
		
		
			/* (non-Javadoc)
			 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
			 */
			@Override
			public String getMainFormBeanCode() {
				return getPositioncode();
			}
			
			
			
	
	
	
	
	
	
}