package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of country
 * 
 * @author Automatika - JustInMind SL
 */
public class CustomList_Form extends ValidatorFormAndAction{

	String version;
	String customListcode;


	private String name=null;
	private String view_name=null;
	private String usercode=null;
	private String update_date=null;
	private String comments=null;
	private String periodic=null;

	
	public CustomList_Form getCustomList() {
		return this;
	}
	
	
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}

	
	@Override
	public String toString(){
		
		if(getCustomListcode()==null || getCustomListcode().equals("")) return "";
			
			String result=getName()+"";
			return (result!=null)?result:"";
		
	}
	
	public String get_descripcion(){
    	return this.toString();
    }



	public String getCustomListcode() {
		return customListcode;
	}



	public void setCustomListcode(String customListcode) {
		this.customListcode = customListcode;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getView_name() {
		return view_name;
	}



	public void setView_name(String view_name) {
		this.view_name = view_name;
	}



	public String getUsercode() {
		return usercode;
	}



	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}



	



	public String getUpdate_date() {
		return update_date;
	}



	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}



	public String getComments() {
		return comments;
	}



	public void setComments(String comments) {
		this.comments = comments;
	}



    public String getPeriodic() {
        return periodic;
    }



    public void setPeriodic(String periodic) {
        this.periodic = periodic;
    }



	
	





}