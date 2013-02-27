package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-ID-form-bean of funding_detail. ID-formBeans contain all the attributes of funding_detail but it does not contain the associations.
 * 
 * @author Automatika - JustInMind SL
 */
public class Funding_detail_IDForm extends ValidatorFormAndAction{


String funding_detailcode;


	private String institution=null;



	private String percent=null;






public Funding_detail_IDForm getFunding_detail() {
	return this;
}

public String getFunding_detailcode() {
	return funding_detailcode;
}

public void setFunding_detailcode(String funding_detailcode) {
	this.funding_detailcode = funding_detailcode;
}

	
	@Override
	public String toString(){
		
		if(getFunding_detailcode()==null || getFunding_detailcode().equals("")) return "";
	
		
			String result=getInstitution()+" - "+getPercent()+"";
			return (result!=null)?result:"";
		
	}

public String get_descripcion(){
	return this.toString();
}



	
	public String getInstitution(){
		return institution;
	}
	
	public void setInstitution(String institution){
		this.institution=institution;
	}

	


	
	public String getPercent(){
		return percent;
	}
	
	public void setPercent(String percent){
		this.percent=percent;
	}





}