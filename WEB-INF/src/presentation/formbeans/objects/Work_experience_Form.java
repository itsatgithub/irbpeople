package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of work_experience
 * 
 * @author Automatika - JustInMind SL
 */
public class Work_experience_Form extends ValidatorFormAndAction{

	String version;
	String work_experiencecode;


	private String start_date=null;

	private String end_date=null;

	private String name_of_institution_or_company=null;

    private String department=null;
    
    private String position=null;



	private Personal_IDForm work_experience_personal=null;

	private Type_of_institution_IDForm type_of_institution=null;

	private Area_IDForm area=null;

	private Position_IDForm work_experience_position=null;

	private Country_IDForm work_experience_country=null;


	
	public Work_experience_Form getWork_experience() {
		return this;
	}
	
	public String getWork_experiencecode() {
		return work_experiencecode;
	}
	
	public void setWork_experiencecode(String work_experiencecode) {
		this.work_experiencecode = work_experiencecode;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}

	
	@Override
	public String toString(){
		
		if(getWork_experiencecode()==null || getWork_experiencecode().equals("")) return "";
	
		
			String result=getStart_date()+" - "+getEnd_date()+" - "+getName_of_institution_or_company()+"";
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

	
	public String getName_of_institution_or_company(){
		return name_of_institution_or_company;
	}
	
	public void setName_of_institution_or_company(String name_of_institution_or_company){
		this.name_of_institution_or_company=name_of_institution_or_company;
	}




	
	public Personal_IDForm getWork_experience_personal(){
		if(work_experience_personal == null)
			work_experience_personal = new Personal_IDForm();
		return work_experience_personal;
	}
	
	public void setWork_experience_personal(Personal_IDForm work_experience_personal){
		this.work_experience_personal = work_experience_personal;
	}
	

	
	public Type_of_institution_IDForm getType_of_institution(){
		if(type_of_institution == null)
			type_of_institution = new Type_of_institution_IDForm();
		return type_of_institution;
	}
	
	public void setType_of_institution(Type_of_institution_IDForm type_of_institution){
		this.type_of_institution = type_of_institution;
	}
	

	
	public Area_IDForm getArea(){
		if(area == null)
			area = new Area_IDForm();
		return area;
	}
	
	public void setArea(Area_IDForm area){
		this.area = area;
	}
	

	
	public Position_IDForm getWork_experience_position(){
		if(work_experience_position == null)
			work_experience_position = new Position_IDForm();
		return work_experience_position;
	}
	
	public void setWork_experience_position(Position_IDForm work_experience_position){
		this.work_experience_position = work_experience_position;
	}
	

	
	public Country_IDForm getWork_experience_country(){
		if(work_experience_country == null)
			work_experience_country = new Country_IDForm();
		return work_experience_country;
	}
	
	public void setWork_experience_country(Country_IDForm work_experience_country){
		this.work_experience_country = work_experience_country;
	}

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
	


}