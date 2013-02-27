package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-ID-form-bean of Usuario. ID-formBeans contain all the attributes of Usuario but it does not contain the associations.
 * 
 * @author Automatika - JustInMind SL
 */
public class UsuarioIDForm extends ValidatorFormAndAction{
	
	String email;

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
    private String usuariocode; 
    private String username; 
    
	public UsuarioIDForm getUserForm() {
		return this;
	}
    
	public String getUsuariocode() {
		return usuariocode;
	}

	public void setUsuariocode(String usercode) {
		this.usuariocode = usercode;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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