package presentation.formbeans.oUnitSys;




import utils.formbeans.FormBeanContainer;
import utils.formbeans.BOAdderFormBean;

import utils.validator.ValidatorFormAndAction;

import presentation.formbeans.objects.*;

/**
 * This class is a FormBeanContainer for the action action_modify_modify_page_unit.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_modify_modify_page_unit_Form extends FormBeanContainer {

	
	//FormBean for the action
			
			/**
			* nested-pojo-form-bean contained in this container
			**/
			private Unit_Form unit_Form = null;
	
	
	

	
	
	
		
		/**
		 * @return returns the code of the current unit_Form (a shortcut of getUnit_Form().getUnitcode())
		 */
		public String getUnitcode() {
			
			return getUnit_Form().getUnitcode();
		}

		/**
		 * Sets the code of the current unit_Form (a shortcut of getUnit_Form().setUnitcode(String))
		 * @param unitcode the new code
		 */
		
		public void setUnitcode(String unitcode){
			
			getUnit_Form().setUnitcode(unitcode);
		}

		/**
		 * @return the current unit_Form
		 */
		
		public Unit_Form getUnit_Form() {
			
			if(unit_Form==null)
				
				unit_Form = new Unit_Form();
			
			return unit_Form;
		}

		/**
		 * set the current unit_Form
		 * @param unit_Form the new unit_Form
		 */
		
		public void setUnit_Form(Unit_Form unit_Form){
			
			this.unit_Form=unit_Form;
		}
		
		
			/* (non-Javadoc)
			 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
			 */
			@Override
			public String getMainFormBeanCode() {
				return getUnitcode();
			}
			
			
			
	
	
	
	
	
	
}