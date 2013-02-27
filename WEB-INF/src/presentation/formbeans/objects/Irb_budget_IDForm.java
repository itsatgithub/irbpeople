package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-ID-form-bean of irb_budget. ID-formBeans contain all the attributes of irb_budget but it does not contain the associations.
 * 
 * @author Automatika - JustInMind SL
 */
public class Irb_budget_IDForm extends ValidatorFormAndAction{


String irb_budgetcode;


	private String description=null;






public Irb_budget_IDForm getIrb_budget() {
	return this;
}

public String getIrb_budgetcode() {
	return irb_budgetcode;
}

public void setIrb_budgetcode(String irb_budgetcode) {
	this.irb_budgetcode = irb_budgetcode;
}

	
	@Override
	public String toString(){
		
		if(getIrb_budgetcode()==null || getIrb_budgetcode().equals("")) return "";
	
		
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