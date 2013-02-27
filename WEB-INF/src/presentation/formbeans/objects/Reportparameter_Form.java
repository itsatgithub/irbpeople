package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of reportparameter
 * 
 * @author Automatika - JustInMind SL
 */
public class Reportparameter_Form extends ValidatorFormAndAction{

	String version;
	String reportparametercode;


	private String class_type=null;

	private String value=null;




	private Report_IDForm report=null;


	
	public Reportparameter_Form getReportparameter() {
		return this;
	}
	
	public String getReportparametercode() {
		return reportparametercode;
	}
	
	public void setReportparametercode(String reportparametercode) {
		this.reportparametercode = reportparametercode;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}

	
	@Override
	public String toString(){
		
		if(getReportparametercode()==null || getReportparametercode().equals("")) return "";
	
		
			String result=getClass_type()+"";
			return (result!=null)?result:"";
		
	}
	
	public String get_descripcion(){
    	return this.toString();
    }



	
	public String getClass_type(){
		return class_type;
	}
	
	public void setClass_type(String class_type){
		this.class_type=class_type;
	}

	
	public String getValue(){
		return value;
	}
	
	public void setValue(String value){
		this.value=value;
	}




	
	public Report_IDForm getReport(){
		if(report == null)
			report = new Report_IDForm();
		return report;
	}
	
	public void setReport(Report_IDForm report){
		this.report = report;
	}
	


}