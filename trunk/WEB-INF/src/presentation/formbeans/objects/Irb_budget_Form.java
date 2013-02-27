package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of irb_budget
 * 
 * @author Automatika - JustInMind SL
 */
public class Irb_budget_Form extends ValidatorFormAndAction{

	String version;
	String irb_budgetcode;
	String newIrb_budgetcode;


	private String description=null;


	


	
	public Irb_budget_Form getIrb_budget() {
		return this;
	}
	
	public String getIrb_budgetcode() {
		return irb_budgetcode;
	}
	
	public void setIrb_budgetcode(String irb_budgetcode) {
		this.irb_budgetcode = irb_budgetcode;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
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

	public String getNewIrb_budgetcode() {
		if(newIrb_budgetcode==null || newIrb_budgetcode.equals(""))
			newIrb_budgetcode=getIrb_budgetcode();
		return newIrb_budgetcode;
	}

	public void setNewIrb_budgetcode(String newIrb_budgetcode) {
		this.newIrb_budgetcode = newIrb_budgetcode;
	}





}