package presentation.formbeans.oAreaSys;




import utils.formbeans.FormBeanContainer;
import utils.formbeans.BOAdderFormBean;

import utils.validator.ValidatorFormAndAction;

import presentation.formbeans.objects.*;

/**
 * This class is a FormBeanContainer for the action action_new_new_page_area.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_new_new_page_area_Form extends FormBeanContainer {

	
	//FormBean for the action
			
			/**
			* nested-pojo-form-bean contained in this container
			**/
			private Area_Form area_Form = null;
	
	
	

	
	
	
		
		/**
		 * @return returns the code of the current area_Form (a shortcut of getArea_Form().getAreacode())
		 */
		public String getAreacode() {
			
			return getArea_Form().getAreacode();
		}

		/**
		 * Sets the code of the current area_Form (a shortcut of getArea_Form().setAreacode(String))
		 * @param areacode the new code
		 */
		
		public void setAreacode(String areacode){
			
			getArea_Form().setAreacode(areacode);
		}

		/**
		 * @return the current area_Form
		 */
		
		public Area_Form getArea_Form() {
			
			if(area_Form==null)
				
				area_Form = new Area_Form();
			
			return area_Form;
		}

		/**
		 * set the current area_Form
		 * @param area_Form the new area_Form
		 */
		
		public void setArea_Form(Area_Form area_Form){
			
			this.area_Form=area_Form;
		}
		
		
			/* (non-Javadoc)
			 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
			 */
			@Override
			public String getMainFormBeanCode() {
				return getAreacode();
			}
			
			
			
	
	
	
	
	
	
}