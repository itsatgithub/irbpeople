package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-ID-form-bean of research_group. ID-formBeans contain all the attributes of research_group but it does not contain the associations.
 * 
 * @author Automatika - JustInMind SL
 */
public class Research_group_IDForm extends ValidatorFormAndAction{


String research_groupcode;


	private String description=null;






public Research_group_IDForm getResearch_group() {
	return this;
}

public String getResearch_groupcode() {
	return research_groupcode;
}

public void setResearch_groupcode(String research_groupcode) {
	this.research_groupcode = research_groupcode;
}

	
	@Override
	public String toString(){
		
		if(getResearch_groupcode()==null || getResearch_groupcode().equals("")) return "";
	
		
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