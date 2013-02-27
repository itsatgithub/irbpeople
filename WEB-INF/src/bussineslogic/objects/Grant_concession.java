package bussineslogic.objects;




import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import utils.Persistent;

/**
 * This class contains business object 'grant_concession'.
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Grant_concession implements Persistent{

	
	//Code (primary key) of this grant_concession
	private String grant_concessioncode;
	//Version of the current instance. This attribute is used by hibernate to control concurrent modifications.
	private int version;
	//Indicates whereas this grant_concession is deleted
	private boolean deleted=false;
	
	//definition of the specific attributes
	
Date start_date;
Date end_date;
String call_code;
Personal grant_concession_personal;
Grant grant;
private boolean current = false;
Date start_date_msc_or_phd;
String thesis_director;
String name_master_or_programme;
String university_enrolled;

	/**
	 * Default Constructor which creates an empty grant_concession
	 */
	public Grant_concession(){
	}

	
	/**
	 * Returns the code (primary key) of this grant_concession. 
	 * @return a String with the code
	 */
	public String getGrant_concessioncode() {
		return grant_concessioncode;
	}

	/**
	 * Set the code (primary key) of this grant_concession
	 * @param grant_concessioncode the String with the code
	 */
	public void setGrant_concessioncode(String grant_concessioncode) {
		this.grant_concessioncode = grant_concessioncode;
	}
	
	/**
	 * Returns the code (primary key) of this grant_concession. Convenience method which calls {@link bussineslogic.objects.Grant_concession#getGrant_concessioncode() getGrant_concessioncode()}
	 * @return a String with the code
	 */
	public String getCode() {
		return getGrant_concessioncode();
	}

	/**
	 * Set the code (primary key) of this grant_concession. Convenience method which calls {@link bussineslogic.objects.Grant_concession#setGrant_concessioncode(String) getGrant_concessioncode(String)}
	 * @param grant_concessioncode the String with the code
	 */
	public void setCode(String grant_concessioncode) {
		setGrant_concessioncode(grant_concessioncode);
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
	 * Tests if this grant_concession has been deleted
	 * @return true if this grant_concession has been deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Sets the delete state of this grant_concession
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
 * @return the call_code
 */
public String getCall_code(){
	return call_code;
}

/**
* @param call_code the call_code to set.
*/
public void setCall_code(String call_code){
	this.call_code=call_code;
}

/**
 * @return the grant_concession_personal
 */
public Personal getGrant_concession_personal(){
	return grant_concession_personal;
}

/**
* @param grant_concession_personal the grant_concession_personal to set.
*/
public void setGrant_concession_personal(Personal grant_concession_personal){
	this.grant_concession_personal=grant_concession_personal;
}

/**
 * @return the grant
 */
public Grant getGrant(){
	return grant;
}

/**
* @param grant the grant to set.
*/
public void setGrant(Grant grant){
	this.grant=grant;
}


	
	
	@Override
	public String toString(){
		if(getCode()==null || getCode().equals("")) return "";
		
		
		return getCode();
	}


    public void setCurrent(boolean current) {
        
        this.current =current;
        
    }


    public boolean isCurrent() {
        return current;
    }


    public Date getStart_date_msc_or_phd() {
        return start_date_msc_or_phd;
    }


    public void setStart_date_msc_or_phd(Date start_date_msc_or_phd) {
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