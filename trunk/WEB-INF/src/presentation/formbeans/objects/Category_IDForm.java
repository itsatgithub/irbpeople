package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-ID-form-bean of category. ID-formBeans contain all the attributes of category but it does not contain the associations.
 * 
 * @author Automatika - JustInMind SL
 */
public class Category_IDForm extends ValidatorFormAndAction{


String categorycode;


	private String description=null;






public Category_IDForm getCategory() {
	return this;
}

public String getCategorycode() {
	return categorycode;
}

public void setCategorycode(String categorycode) {
	this.categorycode = categorycode;
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