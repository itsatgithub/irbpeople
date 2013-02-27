package presentation.formbeans.oLocationSys;




import utils.formbeans.FormBeanContainer;
import utils.formbeans.BOAdderFormBean;

import utils.validator.ValidatorFormAndAction;

import presentation.formbeans.objects.*;

/**
 * This class is a FormBeanContainer for the action action_view_location.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_view_location_Form extends FormBeanContainer {

	
	//FormBean for the action
			
			/**
			* nested-pojo-form-bean contained in this container
			**/
			private Location_Form location_Form = null;
	
	
	

	
	
	
		
		/**
		 * @return returns the code of the current location_Form (a shortcut of getLocation_Form().getLocationcode())
		 */
		public String getLocationcode() {
			
			return getLocation_Form().getLocationcode();
		}

		/**
		 * Sets the code of the current location_Form (a shortcut of getLocation_Form().setLocationcode(String))
		 * @param locationcode the new code
		 */
		
		public void setLocationcode(String locationcode){
			
			getLocation_Form().setLocationcode(locationcode);
		}

		/**
		 * @return the current location_Form
		 */
		
		public Location_Form getLocation_Form() {
			
			if(location_Form==null)
				
				location_Form = new Location_Form();
			
			return location_Form;
		}

		/**
		 * set the current location_Form
		 * @param location_Form the new location_Form
		 */
		
		public void setLocation_Form(Location_Form location_Form){
			
			this.location_Form=location_Form;
		}
		
		
			/* (non-Javadoc)
			 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
			 */
			@Override
			public String getMainFormBeanCode() {
				return getLocationcode();
			}
			
			
			
	
	
	
	
	
	
}