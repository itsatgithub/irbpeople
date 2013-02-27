package presentation.formbeans.oReportSys;




import utils.formbeans.FormBeanContainer;
import utils.formbeans.BOAdderFormBean;

import utils.validator.ValidatorFormAndAction;

import presentation.formbeans.objects.*;

/**
 * This class is a FormBeanContainer for the action action_new_new_page_report.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_new_new_page_report_Form extends FormBeanContainer {

	
	//FormBean for the action
			
			/**
			* nested-pojo-form-bean contained in this container
			**/
			private Report_Form report_Form = null;
	
	
	

	
	
	
		
		/**
		 * @return returns the code of the current report_Form (a shortcut of getReport_Form().getReportcode())
		 */
		public String getReportcode() {
			
			return getReport_Form().getReportcode();
		}

		/**
		 * Sets the code of the current report_Form (a shortcut of getReport_Form().setReportcode(String))
		 * @param reportcode the new code
		 */
		
		public void setReportcode(String reportcode){
			
			getReport_Form().setReportcode(reportcode);
		}

		/**
		 * @return the current report_Form
		 */
		
		public Report_Form getReport_Form() {
			
			if(report_Form==null)
				
				report_Form = new Report_Form();
			
			return report_Form;
		}

		/**
		 * set the current report_Form
		 * @param report_Form the new report_Form
		 */
		
		public void setReport_Form(Report_Form report_Form){
			
			this.report_Form=report_Form;
		}
		
		
			/* (non-Javadoc)
			 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
			 */
			@Override
			public String getMainFormBeanCode() {
				return getReportcode();
			}
			
			
			
	
	
	
	
	
	
}