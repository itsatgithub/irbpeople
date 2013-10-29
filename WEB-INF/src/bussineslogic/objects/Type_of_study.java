package bussineslogic.objects;




import java.util.HashSet;
import java.util.Set;

import utils.Persistent;

/**
 * This class contains business object 'type_of_study'.
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Type_of_study implements Persistent{

	
	//Code (primary key) of this type_of_study
	private String type_of_studycode;
	//Version of the current instance. This attribute is used by hibernate to control concurrent modifications.
	private int version;
	//Indicates whereas this type_of_study is deleted
	private boolean deleted=false;
	
	//definition of the specific attributes
	
String description;
Set<Academic_info> iacademic_info=new HashSet<Academic_info>();

	/**
	 * Default Constructor which creates an empty type_of_study
	 */
	public Type_of_study(){
	}

	
	/**
	 * Returns the code (primary key) of this type_of_study. 
	 * @return a String with the code
	 */
	public String getType_of_studycode() {
		return type_of_studycode;
	}

	/**
	 * Set the code (primary key) of this type_of_study
	 * @param type_of_studycode the String with the code
	 */
	public void setType_of_studycode(String type_of_studycode) {
		this.type_of_studycode = type_of_studycode;
	}
	
	/**
	 * Returns the code (primary key) of this type_of_study. Convenience method which calls {@link bussineslogic.objects.Type_of_study#getType_of_studycode() getType_of_studycode()}
	 * @return a String with the code
	 */
	public String getCode() {
		return getType_of_studycode();
	}

	/**
	 * Set the code (primary key) of this type_of_study. Convenience method which calls {@link bussineslogic.objects.Type_of_study#setType_of_studycode(String) getType_of_studycode(String)}
	 * @param type_of_studycode the String with the code
	 */
	public void setCode(String type_of_studycode) {
		setType_of_studycode(type_of_studycode);
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
	 * Tests if this type_of_study has been deleted
	 * @return true if this type_of_study has been deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Sets the delete state of this type_of_study
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
 * @return the itype_of_study
 */
public Set<Academic_info> getIacademic_info(){
	return iacademic_info;
}

/**
* @param itype_of_study the itype_of_study to set.
*/
public void setIacademic_info(Set<Academic_info> iacademic_info){
	this.iacademic_info=iacademic_info;
}
/**
* Adds a Work_experience to the itype_of_study set.
* @param itype_of_study Work_experience to be added
*/
public void addIacademic_info(Academic_info iacademic_info){
	this.iacademic_info.add(iacademic_info);
}

/**
* Removes a Work_experience to the itype_of_study set.
* @param itype_of_study Work_experience to be removed
*/
public void removeIacademic_info(Academic_info iacademic_info){
	this.iacademic_info.remove(iacademic_info);
}



	
	
	@Override
	public String toString(){
		if(getCode()==null || getCode().equals("")) return "";
		
		
		return getCode();
	}

}