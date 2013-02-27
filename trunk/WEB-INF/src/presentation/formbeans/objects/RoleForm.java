package presentation.formbeans.objects;

import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean for a Role.
 * 
 * @author Automatika - JustInMind SL
 */
public class RoleForm extends ValidatorFormAndAction {
    private String entitycode=null;
    private String description=null;
    private String rolename=null;
    
    public RoleForm RoleForm() {
		return this;
	}
    	
	public String toString() {
		return getRolename();
	}
	
	public String get_descripcion(){
    	return this.toString();
    }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEntitycode() {
		return entitycode;
	}

	public void setEntitycode(String entitycode) {
		this.entitycode = entitycode;
	}
	
	/**
	 * @return the rolename
	 */
	public String getRolename() {
		return rolename;
	}

	/**
	 * @param rolename the rolename to set
	 */
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

}