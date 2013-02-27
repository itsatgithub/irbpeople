package bussineslogic.objects;




import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import utils.Persistent;

/**
 * This class contains business object 'report'.
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Report implements Persistent{

	
	//Code (primary key) of this report
	private String reportcode;
	//Version of the current instance. This attribute is used by hibernate to control concurrent modifications.
	private int version;
	//Indicates whereas this report is deleted
	private boolean deleted=false;
	
	//definition of the specific attributes
	
String name;
Date date;
String filename;
String type;
boolean is_public;
String observations;
Usuario author;
Set<Reportparameter> ireport=new HashSet<Reportparameter>();

	/**
	 * Default Constructor which creates an empty report
	 */
	public Report(){
	}

	
	/**
	 * Returns the code (primary key) of this report. 
	 * @return a String with the code
	 */
	public String getReportcode() {
		return reportcode;
	}

	/**
	 * Set the code (primary key) of this report
	 * @param reportcode the String with the code
	 */
	public void setReportcode(String reportcode) {
		this.reportcode = reportcode;
	}
	
	/**
	 * Returns the code (primary key) of this report. Convenience method which calls {@link bussineslogic.objects.Report#getReportcode() getReportcode()}
	 * @return a String with the code
	 */
	public String getCode() {
		return getReportcode();
	}

	/**
	 * Set the code (primary key) of this report. Convenience method which calls {@link bussineslogic.objects.Report#setReportcode(String) getReportcode(String)}
	 * @param reportcode the String with the code
	 */
	public void setCode(String reportcode) {
		setReportcode(reportcode);
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
	 * Tests if this report has been deleted
	 * @return true if this report has been deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Sets the delete state of this report
	 * @param deleted true if the object is deleted.
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	/**
 * @return the name
 */
public String getName(){
	return name;
}

/**
* @param name the name to set.
*/
public void setName(String name){
	this.name=name;
}

/**
 * @return the date
 */
public Date getDate(){
	return date;
}

/**
* @param date the date to set.
*/
public void setDate(Date date){
	this.date=date;
}

/**
 * @return the filename
 */
public String getFilename(){
	return filename;
}

/**
* @param filename the filename to set.
*/
public void setFilename(String filename){
	this.filename=filename;
}

/**
 * @return the type
 */
public String getType(){
	return type;
}

/**
* @param type the type to set.
*/
public void setType(String type){
	this.type=type;
}

/**
 * @return the is_public
 */
public boolean getIs_public(){
	return is_public;
}

/**
* @param is_public the is_public to set.
*/
public void setIs_public(boolean is_public){
	this.is_public=is_public;
}

/**
 * @return the observations
 */
public String getObservations(){
	return observations;
}

/**
* @param observations the observations to set.
*/
public void setObservations(String observations){
	this.observations=observations;
}

/**
 * @return the Author
 */
public Usuario getAuthor(){
	return author;
}

/**
* @param Author the Author to set.
*/
public void setAuthor(Usuario author){
	this.author=author;
}

/**
 * @return the ireport
 */
public Set<Reportparameter> getIreport(){
	return ireport;
}

/**
* @param ireport the ireport to set.
*/
public void setIreport(Set<Reportparameter> ireport){
	this.ireport=ireport;
}
/**
* Adds a Reportparameter to the ireport set.
* @param ireport Reportparameter to be added
*/
public void addIreport(Reportparameter ireport){
	this.ireport.add(ireport);
}

/**
* Removes a Reportparameter to the ireport set.
* @param ireport Reportparameter to be removed
*/
public void removeIreport(Reportparameter ireport){
	this.ireport.remove(ireport);
}



	
	
	@Override
	public String toString(){
		if(getCode()==null || getCode().equals("")) return "";
		
		
		return getCode();
	}

}