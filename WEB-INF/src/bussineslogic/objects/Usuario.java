package bussineslogic.objects;




import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.justinmind.usermanagement.entity.Umuser;

import utils.Persistent;

/**
 * This class contains business object which is a User in the application.
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Usuario extends Umuser implements Persistent{

	
	Personal personal;
	String activationCode;
	String changePasswordCode;
	String email;
	

Set<Report> iauthor=new HashSet<Report>();
Set<Auditmessage> iuser=new HashSet<Auditmessage>();


	public Personal getPersonal() {
		return personal;
	}



	public void setPersonal(Personal personal) {
		this.personal = personal;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getActivationCode() {
		return activationCode;
	}


	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	public String getChangePasswordCode() {
		return changePasswordCode;
	}


	public void setChangePasswordCode(String changePasswordCode) {
		this.changePasswordCode = changePasswordCode;
	}

	/**
	 * Default Constructor which creates an empty usuario
	 */
	public Usuario(){
	}

	
	/**
	 * Returns the code (primary key) of this usuario. 
	 * @return a String with the code
	 */
    public String getUsuariocode() {
        return getEntitycode();
    }
    
    /**
	 * Set the code (primary key) of this usuario
	 * @param usuariocode the String with the code
	 */
    public void setUsuariocode(String usuariocode) {
        setEntitycode(usuariocode);
    }
	
	
	/**
	 * Returns the code (primary key) of this usuario. Convenience method which calls {@link bussineslogic.objects.Usuario#getUsuariocode() getUsuariocode()}
	 * @return a String with the code
	 */
	public String getCode() {
		return getUsuariocode();
	}

	/**
	 * Set the code (primary key) of this usuario. Convenience method which calls {@link bussineslogic.objects.Usuario#setUsuariocode(String) getUsuariocode(String)}
	 * @param usuariocode the String with the code
	 */
	public void setCode(String usuariocode) {
		setUsuariocode(usuariocode);
	}
	
	/**
 * @return the iAuthor
 */
public Set<Report> getIauthor(){
	return iauthor;
}

/**
* @param iAuthor the iAuthor to set.
*/
public void setIauthor(Set<Report> iAuthor){
	this.iauthor=iAuthor;
}
/**
* Adds a Report to the iAuthor set.
* @param iAuthor Report to be added
*/
public void addIauthor(Report iAuthor){
	this.iauthor.add(iAuthor);
}

/**
* Removes a Report to the iAuthor set.
* @param iAuthor Report to be removed
*/
public void removeIauthor(Report iAuthor){
	this.iauthor.remove(iAuthor);
}


/**
 * @return the iuser
 */
public Set<Auditmessage> getIuser(){
	return iuser;
}

/**
* @param iuser the iuser to set.
*/
public void setIuser(Set<Auditmessage> iuser){
	this.iuser=iuser;
}
/**
* Adds a Auditmessage to the iuser set.
* @param iuser Auditmessage to be added
*/
public void addIuser(Auditmessage iuser){
	this.iuser.add(iuser);
}

/**
* Removes a Auditmessage to the iuser set.
* @param iuser Auditmessage to be removed
*/
public void removeIuser(Auditmessage iuser){
	this.iuser.remove(iuser);
}



	
	
	@Override
	public String toString(){
		if(getCode()==null || getCode().equals("")) return "";
		
		
		return getCode();
	}









}