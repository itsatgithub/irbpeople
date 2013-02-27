package presentation.formbeans.objects;




import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of Usuario
 * 
 * @author Automatika - JustInMind SL
 */
public class UsuarioForm extends ValidatorFormAndAction{

	private Personal_IDForm personal;
	private String email;
	private String email2;
	private String password2;
	private String activationCode;
	
	public Personal_IDForm getPersonal() {
		return personal;
	}
	
	public void setPersonal(Personal_IDForm personal) {
		this.personal = personal;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	
	public String getActivationCode() {
		return activationCode;
	}


	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}
	

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	
	
    private String usuariocode;
	private String username;
    private String password;
    private List<RoleForm> roles = new ArrayList<RoleForm>();
    private LanguageForm language = new LanguageForm();	
    private String version;

    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsuariocode() {
		return usuariocode;
	}

	public void setUsuariocode(String usercode) {
		this.usuariocode = usercode;
	}
	
    public UsuarioForm getUserForm() {
		return this;
	}
    
    public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LanguageForm getLanguage() {
		return language;
	}

	public void setLanguage(LanguageForm language) {
		this.language = language;
	}	

	public String[] getRoles() {
		String[] result=new String[roles.size()];
		int i=0;
		for(Iterator<RoleForm> it=roles.iterator(); it.hasNext(); ++i){
			result[i]=it.next().getEntitycode();
		}
		return result;
	}

	public void setRoles(String[] roleForm) {
		roles.clear();
		for(int i=0; i<roleForm.length; ++i){
			RoleForm newRole=new RoleForm();
			newRole.setEntitycode(roleForm[i]);
			roles.add(newRole);
		}
	}
	
    public String get_descripcion() {
        return this.toString();
    }

	
	@Override
	public String toString(){
		
		if(getUsuariocode()==null || getUsuariocode().equals("")) return "";
	
		return getUsername();
	}







}