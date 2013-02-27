package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-ID-form-bean of report. ID-formBeans contain all the attributes of report but it does not contain the associations.
 * 
 * @author Automatika - JustInMind SL
 */
public class Report_IDForm extends ValidatorFormAndAction{


String reportcode;


	private String name=null;

	private String date=null;

	private String filename=null;

	private String type=null;

	private String is_public=null;

	private String observations=null;






public Report_IDForm getReport() {
	return this;
}

public String getReportcode() {
	return reportcode;
}

public void setReportcode(String reportcode) {
	this.reportcode = reportcode;
}

	
	@Override
	public String toString(){
		
		if(getReportcode()==null || getReportcode().equals("")) return "";
	
		
			String result=getName()+"";
			return (result!=null)?result:"";
		
	}

public String get_descripcion(){
	return this.toString();
}



	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name=name;
	}

	
	public String getDate(){
		return date;
	}
	
	public void setDate(String date){
		this.date=date;
	}

	
	public String getFilename(){
		return filename;
	}
	
	public void setFilename(String filename){
		this.filename=filename;
	}

	
	public String getType(){
		return type;
	}
	
	public void setType(String type){
		this.type=type;
	}

	
	public String getIs_public(){
		return is_public;
	}
	
	public void setIs_public(String is_public){
		this.is_public=is_public;
	}

	
	public String getObservations(){
		return observations;
	}
	
	public void setObservations(String observations){
		this.observations=observations;
	}





}