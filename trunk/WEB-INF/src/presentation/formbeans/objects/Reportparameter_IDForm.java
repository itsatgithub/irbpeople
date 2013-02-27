package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-ID-form-bean of reportparameter. ID-formBeans contain all the attributes of reportparameter but it does not contain the associations.
 * 
 * @author Automatika - JustInMind SL
 */
public class Reportparameter_IDForm extends ValidatorFormAndAction{


String reportparametercode;


	private String class_type=null;

	private String value=null;






public Reportparameter_IDForm getReportparameter() {
	return this;
}

public String getReportparametercode() {
	return reportparametercode;
}

public void setReportparametercode(String reportparametercode) {
	this.reportparametercode = reportparametercode;
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





}