package presentation.formbeans.oReportparameterSys;




import utils.formbeans.FormBeanContainer;
import utils.formbeans.BOAdderFormBean;

import utils.validator.ValidatorFormAndAction;

import presentation.formbeans.objects.*;

/**
 * This class is a FormBeanContainer for the action action_new_page_reportparameter.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_new_page_reportparameter_Form extends FormBeanContainer {

	
	//FormBean for the action
			
			/**
			* nested-pojo-form-bean contained in this container
			**/
			private Reportparameter_Form reportparameter_Form = null;
	
	
	

	
	
	
		
		/**
		 * @return returns the code of the current reportparameter_Form (a shortcut of getReportparameter_Form().getReportparametercode())
		 */
		public String getReportparametercode() {
			
			return getReportparameter_Form().getReportparametercode();
		}

		/**
		 * Sets the code of the current reportparameter_Form (a shortcut of getReportparameter_Form().setReportparametercode(String))
		 * @param reportparametercode the new code
		 */
		
		public void setReportparametercode(String reportparametercode){
			
			getReportparameter_Form().setReportparametercode(reportparametercode);
		}

		/**
		 * @return the current reportparameter_Form
		 */
		
		public Reportparameter_Form getReportparameter_Form() {
			
			if(reportparameter_Form==null)
				
				reportparameter_Form = new Reportparameter_Form();
			
			return reportparameter_Form;
		}

		/**
		 * set the current reportparameter_Form
		 * @param reportparameter_Form the new reportparameter_Form
		 */
		
		public void setReportparameter_Form(Reportparameter_Form reportparameter_Form){
			
			this.reportparameter_Form=reportparameter_Form;
		}
		
		
			/* (non-Javadoc)
			 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
			 */
			@Override
			public String getMainFormBeanCode() {
				return getReportparametercode();
			}
			
			
			
	
	
	
	
	
	
}