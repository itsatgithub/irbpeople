package bussineslogic.objects;




import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import utils.Persistent;

/**
 * This class contains business object 'work_experience'.
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Work_experience implements Persistent{

	
	//Code (primary key) of this work_experience
	private String work_experiencecode;
	//Version of the current instance. This attribute is used by hibernate to control concurrent modifications.
	private int version;
	//Indicates whereas this work_experience is deleted
	private boolean deleted=false;
	
	//definition of the specific attributes
	
Date start_date;
Date end_date;
String name_of_institution_or_company;
String department;
String position;
Personal work_experience_personal;
Type_of_institution type_of_institution;
Area area;
Position work_experience_position;
Country work_experience_country;

	/**
	 * Default Constructor which creates an empty work_experience
	 */
	public Work_experience(){
	}

	
	/**
	 * Returns the code (primary key) of this work_experience. 
	 * @return a String with the code
	 */
	public String getWork_experiencecode() {
		return work_experiencecode;
	}

	/**
	 * Set the code (primary key) of this work_experience
	 * @param work_experiencecode the String with the code
	 */
	public void setWork_experiencecode(String work_experiencecode) {
		this.work_experiencecode = work_experiencecode;
	}
	
	/**
	 * Returns the code (primary key) of this work_experience. Convenience method which calls {@link bussineslogic.objects.Work_experience#getWork_experiencecode() getWork_experiencecode()}
	 * @return a String with the code
	 */
	public String getCode() {
		return getWork_experiencecode();
	}

	/**
	 * Set the code (primary key) of this work_experience. Convenience method which calls {@link bussineslogic.objects.Work_experience#setWork_experiencecode(String) getWork_experiencecode(String)}
	 * @param work_experiencecode the String with the code
	 */
	public void setCode(String work_experiencecode) {
		setWork_experiencecode(work_experiencecode);
	}

	/**
	 * Returns the version of the current instance. This attribute is used by hibernate to control concurrent modifications.
	 * @return int with the version number.
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * Set the version of the current instance. This attribute is used by hibernate to control concurrent modifications.
	 * @param version int with the version number.
	 */
	public void setVersion(int version) {
		this.version = version;
	}
	
	/**
	 * Tests if this work_experience has been deleted
	 * @return true if this work_experience has been deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Sets the delete state of this work_experience
	 * @param deleted true if the object is deleted.
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	/**
 * @return the start_date
 */
public Date getStart_date(){
	return start_date;
}

/**
* @param start_date the start_date to set.
*/
public void setStart_date(Date start_date){
	this.start_date=start_date;
}

/**
 * @return the end_date
 */
public Date getEnd_date(){
	return end_date;
}

/**
* @param end_date the end_date to set.
*/
public void setEnd_date(Date end_date){
	this.end_date=end_date;
}

/**
 * @return the name_of_institution_or_company
 */
public String getName_of_institution_or_company(){
	return name_of_institution_or_company;
}

/**
* @param name_of_institution_or_company the name_of_institution_or_company to set.
*/
public void setName_of_institution_or_company(String name_of_institution_or_company){
	this.name_of_institution_or_company=name_of_institution_or_company;
}

/**
 * @return the work_experience_personal
 */
public Personal getWork_experience_personal(){
	return work_experience_personal;
}

/**
* @param work_experience_personal the work_experience_personal to set.
*/
public void setWork_experience_personal(Personal work_experience_personal){
	this.work_experience_personal=work_experience_personal;
}

/**
 * @return the type_of_institution
 */
public Type_of_institution getType_of_institution(){
	return type_of_institution;
}

/**
* @param type_of_institution the type_of_institution to set.
*/
public void setType_of_institution(Type_of_institution type_of_institution){
	this.type_of_institution=type_of_institution;
}

/**
 * @return the area
 */
public Area getArea(){
	return area;
}

/**
* @param area the area to set.
*/
public void setArea(Area area){
	this.area=area;
}

/**
 * @return the work_experience_position
 */
public Position getWork_experience_position(){
	return work_experience_position;
}

/**
* @param work_experience_position the work_experience_position to set.
*/
public void setWork_experience_position(Position work_experience_position){
	this.work_experience_position=work_experience_position;
}

/**
 * @return the work_experience_country
 */
public Country getWork_experience_country(){
	return work_experience_country;
}

/**
* @param work_experience_country the work_experience_country to set.
*/
public void setWork_experience_country(Country work_experience_country){
	this.work_experience_country=work_experience_country;
}


	
	
	@Override
	public String toString(){
		if(getCode()==null || getCode().equals("")) return "";
		
		
		return getCode();
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