package presentation.formbeans.oFunding_detailSys;




import utils.formbeans.FormBeanContainer;
import utils.formbeans.BOAdderFormBean;

import utils.validator.ValidatorFormAndAction;

import presentation.formbeans.objects.*;

/**
 * This class is a FormBeanContainer for the action action_delete_page_funding_detail.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_delete_page_funding_detail_Form extends FormBeanContainer {

	
	//FormBean for the action
			
			/**
			* nested-pojo-form-bean contained in this container
			**/
			private Funding_detail_Form funding_detail_Form = null;
	
	
	

	
	
	
		
		/**
		 * @return returns the code of the current funding_detail_Form (a shortcut of getFunding_detail_Form().getFunding_detailcode())
		 */
		public String getFunding_detailcode() {
			
			return getFunding_detail_Form().getFunding_detailcode();
		}

		/**
		 * Sets the code of the current funding_detail_Form (a shortcut of getFunding_detail_Form().setFunding_detailcode(String))
		 * @param funding_detailcode the new code
		 */
		
		public void setFunding_detailcode(String funding_detailcode){
			
			getFunding_detail_Form().setFunding_detailcode(funding_detailcode);
		}

		/**
		 * @return the current funding_detail_Form
		 */
		
		public Funding_detail_Form getFunding_detail_Form() {
			
			if(funding_detail_Form==null)
				
				funding_detail_Form = new Funding_detail_Form();
			
			return funding_detail_Form;
		}

		/**
		 * set the current funding_detail_Form
		 * @param funding_detail_Form the new funding_detail_Form
		 */
		
		public void setFunding_detail_Form(Funding_detail_Form funding_detail_Form){
			
			this.funding_detail_Form=funding_detail_Form;
		}
		
		
			/* (non-Javadoc)
			 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
			 */
			@Override
			public String getMainFormBeanCode() {
				return getFunding_detailcode();
			}
			
			
			
	
	
	
	
	
	
}