package presentation.formbeans.other;




import java.util.ArrayList;
import java.util.List;

import utils.formbeans.FormBeanContainer;
import utils.formbeans.BOAdderFormBean;

import utils.validator.ValidatorFormAndAction;

import presentation.formbeans.objects.*;

/**
 * This class is a FormBeanContainer for the action action_reports.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_reports_Form extends FormBeanContainer {

	
	//FormBean for the action
	
	private String selReport="";
    private List reports = new ArrayList();
    private List reportValues = new ArrayList();
    
    public List getReports() {
        return reports;
    }
    public void setReports(List reports) {
        this.reports = reports;
    }
    public String getSelReport() {
        return selReport;
    }
    public void setSelReport(String selReport) {
        this.selReport = selReport;
    }
    public List getReportValues() {
        return reportValues;
    }
    public void setReportValues(List reportValues) {
        this.reportValues = reportValues;
    }

	
	
	
	
	
	
	
	
	
}