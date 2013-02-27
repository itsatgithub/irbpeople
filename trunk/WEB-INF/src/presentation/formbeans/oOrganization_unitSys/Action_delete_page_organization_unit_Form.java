package presentation.formbeans.oOrganization_unitSys;




import utils.formbeans.FormBeanContainer;
import utils.formbeans.BOAdderFormBean;

import utils.validator.ValidatorFormAndAction;

import presentation.formbeans.objects.*;

/**
 * This class is a FormBeanContainer for the action action_delete_page_organization_unit.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_delete_page_organization_unit_Form extends FormBeanContainer {

	
	//FormBean for the action
			
			/**
			* nested-pojo-form-bean contained in this container
			**/
			private Organization_unit_Form organization_unit_Form = null;
	
	
	

	
	
	
		
		/**
		 * @return returns the code of the current organization_unit_Form (a shortcut of getOrganization_unit_Form().getOrganization_unitcode())
		 */
		public String getOrganization_unitcode() {
			
			return getOrganization_unit_Form().getOrganization_unitcode();
		}

		/**
		 * Sets the code of the current organization_unit_Form (a shortcut of getOrganization_unit_Form().setOrganization_unitcode(String))
		 * @param organization_unitcode the new code
		 */
		
		public void setOrganization_unitcode(String organization_unitcode){
			
			getOrganization_unit_Form().setOrganization_unitcode(organization_unitcode);
		}

		/**
		 * @return the current organization_unit_Form
		 */
		
		public Organization_unit_Form getOrganization_unit_Form() {
			
			if(organization_unit_Form==null)
				
				organization_unit_Form = new Organization_unit_Form();
			
			return organization_unit_Form;
		}

		/**
		 * set the current organization_unit_Form
		 * @param organization_unit_Form the new organization_unit_Form
		 */
		
		public void setOrganization_unit_Form(Organization_unit_Form organization_unit_Form){
			
			this.organization_unit_Form=organization_unit_Form;
		}
		
		
			/* (non-Javadoc)
			 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
			 */
			@Override
			public String getMainFormBeanCode() {
				return getOrganization_unitcode();
			}
			
			
			
	
	
	
	
	
	
}