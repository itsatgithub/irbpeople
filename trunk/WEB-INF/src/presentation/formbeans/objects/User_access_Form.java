package presentation.formbeans.objects;

import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of personal
 * 
 * @author Automatika - JustInMind SL
 */
public class User_access_Form extends ValidatorFormAndAction {

	String version;
	String usercode;

	private String locked;
	private String last_access;
	private String tries;

	public User_access_Form getUser_access() {
		return this;
	}

	@Override
	public String toString() {

		if (getUsercode() == null || getUsercode().equals(""))
			return "";

		String result = getName();
		return (result != null) ? result : "";

	}

	// definition of the specific attributes
	private String name;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getUsercode() {
		return usercode;
	}
	
	public String getUser_accesscode(){
		return getUsercode();
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	
	public void setUseraccess_code(String usercode){
		setUsercode(usercode);
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocked() {
		return locked;
	}

	public void setLocked(String locked) {
		this.locked = locked;
	}

	public String getLast_access() {
		return last_access;
	}

	public void setLast_access(String last_access) {
		this.last_access = last_access;
	}

	public String getTries() {
		return tries;
	}

	public void setTries(String tries) {
		this.tries = tries;
	}

}