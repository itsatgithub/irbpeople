package presentation.formbeans.oHolidaySys;




import utils.formbeans.FormBeanContainer;
import utils.formbeans.BOAdderFormBean;

import utils.validator.ValidatorFormAndAction;

import presentation.formbeans.objects.*;

/**
 * This class is a FormBeanContainer for the action action_delete_page_holiday.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_delete_page_holiday_Form extends FormBeanContainer {

	
	//FormBean for the action
			
			/**
			* nested-pojo-form-bean contained in this container
			**/
			private Holiday_Form holiday_Form = null;
	
	
	

	
	
	
		
		/**
		 * @return returns the code of the current holiday_Form (a shortcut of getHoliday_Form().getHolidaycode())
		 */
		public String getHolidaycode() {
			
			return getHoliday_Form().getHolidaycode();
		}

		/**
		 * Sets the code of the current holiday_Form (a shortcut of getHoliday_Form().setHolidaycode(String))
		 * @param holidaycode the new code
		 */
		
		public void setHolidaycode(String holidaycode){
			
			getHoliday_Form().setHolidaycode(holidaycode);
		}

		/**
		 * @return the current holiday_Form
		 */
		
		public Holiday_Form getHoliday_Form() {
			
			if(holiday_Form==null)
				
				holiday_Form = new Holiday_Form();
			
			return holiday_Form;
		}

		/**
		 * set the current holiday_Form
		 * @param holiday_Form the new holiday_Form
		 */
		
		public void setHoliday_Form(Holiday_Form holiday_Form){
			
			this.holiday_Form=holiday_Form;
		}
		
		
			/* (non-Javadoc)
			 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
			 */
			@Override
			public String getMainFormBeanCode() {
				return getHolidaycode();
			}
			
			
			
	
	
	
	
	
	
}