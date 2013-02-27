package bussineslogic.objects;




import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import utils.Persistent;

/**
 * This class contains business object 'education'.
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Education implements Persistent{

	
	//Code (primary key) of this education
	private String educationcode;
	//Version of the current instance. This attribute is used by hibernate to control concurrent modifications.
	private int version;
	//Indicates whereas this education is deleted
	private boolean deleted=false;
	
	//definition of the specific attributes
	
Date start_date;
Date end_date;
Date graduation_date;
String title;
String speciality;
String center;
Personal education_personal;
Type_of_education type;
Country education_country;

	/**
	 * Default Constructor which creates an empty education
	 */
	public Education(){
	}

	
	/**
	 * Returns the code (primary key) of this education. 
	 * @return a String with the code
	 */
	public String getEducationcode() {
		return educationcode;
	}

	/**
	 * Set the code (primary key) of this education
	 * @param educationcode the String with the code
	 */
	public void setEducationcode(String educationcode) {
		this.educationcode = educationcode;
	}
	
	/**
	 * Returns the code (primary key) of this education. Convenience method which calls {@link bussineslogic.objects.Education#getEducationcode() getEducationcode()}
	 * @return a String with the code
	 */
	public String getCode() {
		return getEducationcode();
	}

	/**
	 * Set the code (primary key) of this education. Convenience method which calls {@link bussineslogic.objects.Education#setEducationcode(String) getEducationcode(String)}
	 * @param educationcode the String with the code
	 */
	public void setCode(String educationcode) {
		setEducationcode(educationcode);
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
	 * Tests if this education has been deleted
	 * @return true if this education has been deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Sets the delete state of this education
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
 * @return the graduation_date
 */
public Date getGraduation_date(){
	return graduation_date;
}

/**
* @param graduation_date the graduation_date to set.
*/
public void setGraduation_date(Date graduation_date){
	this.graduation_date=graduation_date;
}

/**
 * @return the title
 */
public String getTitle(){
	return title;
}

/**
* @param title the title to set.
*/
public void setTitle(String title){
	this.title=title;
}

/**
 * @return the speciality
 */
public String getSpeciality(){
	return speciality;
}

/**
* @param speciality the speciality to set.
*/
public void setSpeciality(String speciality){
	this.speciality=speciality;
}

/**
 * @return the center
 */
public String getCenter(){
	return center;
}

/**
* @param center the center to set.
*/
public void setCenter(String center){
	this.center=center;
}

/**
 * @return the education_personal
 */
public Personal getEducation_personal(){
	return education_personal;
}

/**
* @param education_personal the education_personal to set.
*/
public void setEducation_personal(Personal education_personal){
	this.education_personal=education_personal;
}

/**
 * @return the type
 */
public Type_of_education getType(){
	return type;
}

/**
* @param type the type to set.
*/
public void setType(Type_of_education type){
	this.type=type;
}

/**
 * @return the education_country
 */
public Country getEducation_country(){
	return education_country;
}

/**
* @param education_country the education_country to set.
*/
public void setEducation_country(Country education_country){
	this.education_country=education_country;
}


	
	
	@Override
	public String toString(){
		if(getCode()==null || getCode().equals("")) return "";
		
		
		return getCode();
	}

}