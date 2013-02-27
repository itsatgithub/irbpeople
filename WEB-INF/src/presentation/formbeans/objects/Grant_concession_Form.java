package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of grant_concession
 * 
 * @author Automatika - JustInMind SL
 */
public class Grant_concession_Form extends ValidatorFormAndAction{

	String version;
	String grant_concessioncode;


	private String start_date=null;

	private String end_date=null;

	private String call_code=null;

	private String current = null;

	private String start_date_msc_or_phd = null;
	
	private String thesis_director = null;
	
	private String name_master_or_programme = null;
	
	private String university_enrolled = null;
	

	private Personal_IDForm grant_concession_personal=null;

	private Grant_IDForm grant=null;


	
	public Grant_concession_Form getGrant_concession() {
		return this;
	}
	
	public String getGrant_concessioncode() {
		return grant_concessioncode;
	}
	
	public void setGrant_concessioncode(String grant_concessioncode) {
		this.grant_concessioncode = grant_concessioncode;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}

	
	@Override
	public String toString(){
		
		if(getGrant_concessioncode()==null || getGrant_concessioncode().equals("")) return "";
	
		
			String result=getStart_date()+" - "+getEnd_date()+" - "+getCall_code()+"";
			return (result!=null)?result:"";
		
	}
	
	public String get_descripcion(){
    	return this.toString();
    }



	
	public String getStart_date(){
		return start_date;
	}
	
	public void setStart_date(String start_date){
		this.start_date=start_date;
	}

	
	public String getEnd_date(){
		return end_date;
	}
	
	public void setEnd_date(String end_date){
		this.end_date=end_date;
	}

	
	public String getCall_code(){
		return call_code;
	}
	
	public void setCall_code(String call_code){
		this.call_code=call_code;
	}




	
	public Personal_IDForm getGrant_concession_personal(){
		if(grant_concession_personal == null)
			grant_concession_personal = new Personal_IDForm();
		return grant_concession_personal;
	}
	
	public void setGrant_concession_personal(Personal_IDForm grant_concession_personal){
		this.grant_concession_personal = grant_concession_personal;
	}
	

	
	public Grant_IDForm getGrant(){
		if(grant == null)
			grant = new Grant_IDForm();
		return grant;
	}
	
	public void setGrant(Grant_IDForm grant){
		this.grant = grant;
	}

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getStart_date_msc_or_phd() {
        return start_date_msc_or_phd;
    }

    public void setStart_date_msc_or_phd(String start_date_msc_or_phd) {
        this.start_date_msc_or_phd = start_date_msc_or_phd;
    }

    public String getThesis_director() {
        return thesis_director;
    }

    public void setThesis_director(String thesis_director) {
        this.thesis_director = thesis_director;
    }

    public String getName_master_or_programme() {
        return name_master_or_programme;
    }

    public void setName_master_or_programme(String name_master_or_programme) {
        this.name_master_or_programme = name_master_or_programme;
    }

    public String getUniversity_enrolled() {
        return university_enrolled;
    }

    public void setUniversity_enrolled(String university_enrolled) {
        this.university_enrolled = university_enrolled;
    }
	


}