package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of type_of_contract
 * 
 * @author Automatika - JustInMind SL
 */
public class Type_of_contract_Form extends ValidatorFormAndAction{

	String version;
	String type_of_contractcode;


	private String description=null;

	private String is_irbs=null;




	
	public Type_of_contract_Form getType_of_contract() {
		return this;
	}
	
	public String getType_of_contractcode() {
		return type_of_contractcode;
	}
	
	public void setType_of_contractcode(String type_of_contractcode) {
		this.type_of_contractcode = type_of_contractcode;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}

	
	@Override
	public String toString(){
		
		if(getType_of_contractcode()==null || getType_of_contractcode().equals("")) return "";
	
		
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

    public String getIs_irbs() {
        return is_irbs;
    }

    public void setIs_irbs(String is_irbs) {
        this.is_irbs = is_irbs;
    }





}