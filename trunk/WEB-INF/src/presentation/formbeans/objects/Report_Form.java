package presentation.formbeans.objects;





import org.apache.struts.upload.FormFile;

import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of report
 * 
 * @author Automatika - JustInMind SL
 */
public class Report_Form extends ValidatorFormAndAction{

	String version;
	String reportcode;


	private String name=null;

	private String date=null;

	private FormFile filename=null;

	private String type=null;

	private String is_public=null;

	private String observations=null;




	private UsuarioIDForm author=null;


	
	public Report_Form getReport() {
		return this;
	}
	
	public String getReportcode() {
		return reportcode;
	}
	
	public void setReportcode(String reportcode) {
		this.reportcode = reportcode;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
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

	
	public FormFile getFilename(){
		return filename;
	}
	
	public void setFilename(FormFile filename){
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




	
	public UsuarioIDForm getAuthor(){
		if(author == null)
			author = new UsuarioIDForm();
		return author;
	}
	
	public void setAuthor(UsuarioIDForm Author){
		this.author = Author;
	}
	


}