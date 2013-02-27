package presentation.formbeans.oCountrySys;




import utils.formbeans.FormBeanContainer;
import utils.formbeans.BOAdderFormBean;

import utils.validator.ValidatorFormAndAction;

import presentation.formbeans.objects.*;

/**
 * This class is a FormBeanContainer for the action action_delete_page_country.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_delete_page_country_Form extends FormBeanContainer {

	
	//FormBean for the action
			
			/**
			* nested-pojo-form-bean contained in this container
			**/
			private Country_Form country_Form = null;
	
	
	

	
	
	
		
		/**
		 * @return returns the code of the current country_Form (a shortcut of getCountry_Form().getCountrycode())
		 */
		public String getCountrycode() {
			
			return getCountry_Form().getCountrycode();
		}

		/**
		 * Sets the code of the current country_Form (a shortcut of getCountry_Form().setCountrycode(String))
		 * @param countrycode the new code
		 */
		
		public void setCountrycode(String countrycode){
			
			getCountry_Form().setCountrycode(countrycode);
		}

		/**
		 * @return the current country_Form
		 */
		
		public Country_Form getCountry_Form() {
			
			if(country_Form==null)
				
				country_Form = new Country_Form();
			
			return country_Form;
		}

		/**
		 * set the current country_Form
		 * @param country_Form the new country_Form
		 */
		
		public void setCountry_Form(Country_Form country_Form){
			
			this.country_Form=country_Form;
		}
		
		
			/* (non-Javadoc)
			 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
			 */
			@Override
			public String getMainFormBeanCode() {
				return getCountrycode();
			}
			
			
			
	
	
	
	
	
	
}