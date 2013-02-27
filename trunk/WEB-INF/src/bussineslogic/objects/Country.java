package bussineslogic.objects;




import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import utils.Persistent;

/**
 * This class contains business object 'country'.
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Country implements Persistent{

	
	//Code (primary key) of this country
	private String countrycode;
	//Version of the current instance. This attribute is used by hibernate to control concurrent modifications.
	private int version;
	//Indicates whereas this country is deleted
	private boolean deleted=false;
	
	//definition of the specific attributes
	
String description;
Set<Work_experience> iwork_experience_country=new HashSet<Work_experience>();
Set<Education> ieducation_country=new HashSet<Education>();
Set<Personal> ibirth_country=new HashSet<Personal>();
Set<Personal> icountry=new HashSet<Personal>();
Set<Personal> iend_of_contract_country=new HashSet<Personal>();

	/**
	 * Default Constructor which creates an empty country
	 */
	public Country(){
	}

	
	/**
	 * Returns the code (primary key) of this country. 
	 * @return a String with the code
	 */
	public String getCountrycode() {
		return countrycode;
	}

	/**
	 * Set the code (primary key) of this country
	 * @param countrycode the String with the code
	 */
	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}
	
	/**
	 * Returns the code (primary key) of this country. Convenience method which calls {@link bussineslogic.objects.Country#getCountrycode() getCountrycode()}
	 * @return a String with the code
	 */
	public String getCode() {
		return getCountrycode();
	}

	/**
	 * Set the code (primary key) of this country. Convenience method which calls {@link bussineslogic.objects.Country#setCountrycode(String) getCountrycode(String)}
	 * @param countrycode the String with the code
	 */
	public void setCode(String countrycode) {
		setCountrycode(countrycode);
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
	 * Tests if this country has been deleted
	 * @return true if this country has been deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Sets the delete state of this country
	 * @param deleted true if the object is deleted.
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	/**
 * @return the description
 */
public String getDescription(){
	return description;
}

/**
* @param description the description to set.
*/
public void setDescription(String description){
	this.description=description;
}

/**
 * @return the iwork_experience_country
 */
public Set<Work_experience> getIwork_experience_country(){
	return iwork_experience_country;
}

/**
* @param iwork_experience_country the iwork_experience_country to set.
*/
public void setIwork_experience_country(Set<Work_experience> iwork_experience_country){
	this.iwork_experience_country=iwork_experience_country;
}
/**
* Adds a Work_experience to the iwork_experience_country set.
* @param iwork_experience_country Work_experience to be added
*/
public void addIwork_experience_country(Work_experience iwork_experience_country){
	this.iwork_experience_country.add(iwork_experience_country);
}

/**
* Removes a Work_experience to the iwork_experience_country set.
* @param iwork_experience_country Work_experience to be removed
*/
public void removeIwork_experience_country(Work_experience iwork_experience_country){
	this.iwork_experience_country.remove(iwork_experience_country);
}


/**
 * @return the ieducation_country
 */
public Set<Education> getIeducation_country(){
	return ieducation_country;
}

/**
* @param ieducation_country the ieducation_country to set.
*/
public void setIeducation_country(Set<Education> ieducation_country){
	this.ieducation_country=ieducation_country;
}
/**
* Adds a Education to the ieducation_country set.
* @param ieducation_country Education to be added
*/
public void addIeducation_country(Education ieducation_country){
	this.ieducation_country.add(ieducation_country);
}

/**
* Removes a Education to the ieducation_country set.
* @param ieducation_country Education to be removed
*/
public void removeIeducation_country(Education ieducation_country){
	this.ieducation_country.remove(ieducation_country);
}


/**
 * @return the ibirth_country
 */
public Set<Personal> getIbirth_country(){
	return ibirth_country;
}

/**
* @param ibirth_country the ibirth_country to set.
*/
public void setIbirth_country(Set<Personal> ibirth_country){
	this.ibirth_country=ibirth_country;
}
/**
* Adds a Personal to the ibirth_country set.
* @param ibirth_country Personal to be added
*/
public void addIbirth_country(Personal ibirth_country){
	this.ibirth_country.add(ibirth_country);
}

/**
* Removes a Personal to the ibirth_country set.
* @param ibirth_country Personal to be removed
*/
public void removeIbirth_country(Personal ibirth_country){
	this.ibirth_country.remove(ibirth_country);
}


/**
 * @return the icountry
 */
public Set<Personal> getIcountry(){
	return icountry;
}

/**
* @param icountry the icountry to set.
*/
public void setIcountry(Set<Personal> icountry){
	this.icountry=icountry;
}
/**
* Adds a Personal to the icountry set.
* @param icountry Personal to be added
*/
public void addIcountry(Personal icountry){
	this.icountry.add(icountry);
}

/**
* Removes a Personal to the icountry set.
* @param icountry Personal to be removed
*/
public void removeIcountry(Personal icountry){
	this.icountry.remove(icountry);
}


/**
 * @return the iend_of_contract_country
 */
public Set<Personal> getIend_of_contract_country(){
	return iend_of_contract_country;
}

/**
* @param iend_of_contract_country the iend_of_contract_country to set.
*/
public void setIend_of_contract_country(Set<Personal> iend_of_contract_country){
	this.iend_of_contract_country=iend_of_contract_country;
}
/**
* Adds a Personal to the iend_of_contract_country set.
* @param iend_of_contract_country Personal to be added
*/
public void addIend_of_contract_country(Personal iend_of_contract_country){
	this.iend_of_contract_country.add(iend_of_contract_country);
}

/**
* Removes a Personal to the iend_of_contract_country set.
* @param iend_of_contract_country Personal to be removed
*/
public void removeIend_of_contract_country(Personal iend_of_contract_country){
	this.iend_of_contract_country.remove(iend_of_contract_country);
}



	
	
	@Override
	public String toString(){
		if(getCode()==null || getCode().equals("")) return "";
		
		
		return getCode();
	}

}