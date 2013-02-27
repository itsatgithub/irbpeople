package bussineslogic.objects;




import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import utils.Persistent;

/**
 * This class contains business object 'reportparameter'.
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Reportparameter implements Persistent{

	
	//Code (primary key) of this reportparameter
	private String reportparametercode;
	//Version of the current instance. This attribute is used by hibernate to control concurrent modifications.
	private int version;
	//Indicates whereas this reportparameter is deleted
	private boolean deleted=false;
	
	//definition of the specific attributes
	
String class_type;
String value;
Report report;

	/**
	 * Default Constructor which creates an empty reportparameter
	 */
	public Reportparameter(){
	}

	
	/**
	 * Returns the code (primary key) of this reportparameter. 
	 * @return a String with the code
	 */
	public String getReportparametercode() {
		return reportparametercode;
	}

	/**
	 * Set the code (primary key) of this reportparameter
	 * @param reportparametercode the String with the code
	 */
	public void setReportparametercode(String reportparametercode) {
		this.reportparametercode = reportparametercode;
	}
	
	/**
	 * Returns the code (primary key) of this reportparameter. Convenience method which calls {@link bussineslogic.objects.Reportparameter#getReportparametercode() getReportparametercode()}
	 * @return a String with the code
	 */
	public String getCode() {
		return getReportparametercode();
	}

	/**
	 * Set the code (primary key) of this reportparameter. Convenience method which calls {@link bussineslogic.objects.Reportparameter#setReportparametercode(String) getReportparametercode(String)}
	 * @param reportparametercode the String with the code
	 */
	public void setCode(String reportparametercode) {
		setReportparametercode(reportparametercode);
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
	 * Tests if this reportparameter has been deleted
	 * @return true if this reportparameter has been deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Sets the delete state of this reportparameter
	 * @param deleted true if the object is deleted.
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	/**
 * @return the class_type
 */
public String getClass_type(){
	return class_type;
}

/**
* @param class_type the class_type to set.
*/
public void setClass_type(String class_type){
	this.class_type=class_type;
}

/**
 * @return the value
 */
public String getValue(){
	return value;
}

/**
* @param value the value to set.
*/
public void setValue(String value){
	this.value=value;
}

/**
 * @return the report
 */
public Report getReport(){
	return report;
}

/**
* @param report the report to set.
*/
public void setReport(Report report){
	this.report=report;
}


	
	
	@Override
	public String toString(){
		if(getCode()==null || getCode().equals("")) return "";
		
		
		return getCode();
	}

}