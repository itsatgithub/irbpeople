package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of category
 * 
 * @author Automatika - JustInMind SL
 */
public class Category_Form extends ValidatorFormAndAction{

	String version;
	String categorycode;


	private String description=null;





	
	public Category_Form getCategory() {
		return this;
	}
	
	public String getCategorycode() {
		return categorycode;
	}
	
	public void setCategorycode(String categorycode) {
		this.categorycode = categorycode;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}

	
	@Override
	public String toString(){
		
		if(getCategorycode()==null || getCategorycode().equals("")) return "";
	
		
			String result=getDescription()+"";
			return (result!=null)?result:"";
		
	}
	
	public String get_descripcion(){
    	return this.toString();
    }



	
	public String getDescription(){
		return description;
	}
	
	public void setDescription(String description){
		this.description=description;
	}





}