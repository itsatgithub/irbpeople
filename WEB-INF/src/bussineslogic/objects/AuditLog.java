package bussineslogic.objects;




import java.util.Date;

import utils.Persistent;

/**
 * This class contains business object 'auditmessage'.
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class AuditLog implements Persistent{

	
	//Code (primary key) of this auditmessage
	private String audit_logcode;
	
	//definition of the specific attributes
	
	Date date;
	String user;
	String role;
	String type;
    String remoteHost;

	/**
	 * Default Constructor which creates an empty auditmessage
	 */
	public AuditLog(){
	}

	
	/**
	 * Returns the code (primary key) of this auditmessage. 
	 * @return a String with the code
	 */
	public String getAudit_logcode() {
		return audit_logcode;
	}

	/**
	 * Set the code (primary key) of this auditmessage
	 * @param auditmessagecode the String with the code
	 */
	public void setAudit_logcode(String audit_logcode) {
		this.audit_logcode = audit_logcode;
	}
	
	/**
	 * Returns the code (primary key) of this auditmessage. Convenience method which calls {@link AuditLog#getAuditmessagecode() getAuditmessagecode()}
	 * @return a String with the code
	 */
	public String getCode() {
		return getAudit_logcode();
	}

	/**
	 * Set the code (primary key) of this auditmessage. Convenience method which calls {@link AuditLog#setAuditmessagecode(String) getAuditmessagecode(String)}
	 * @param auditmessagecode the String with the code
	 */
	public void setCode(String audit_logcode) {
		setAudit_logcode(audit_logcode);
	}




	
	
	@Override
	public String toString(){
		if(getCode()==null || getCode().equals("")) return "";
		
		
		return getCode();
	}


    public Date getDate() {
        return date;
    }


    public void setDate(Date date) {
        this.date = date;
    }


    public String getUser() {
        return user;
    }


    public void setUser(String user) {
        this.user = user;
    }


    public String getRole() {
        return role;
    }


    public void setRole(String role) {
        this.role = role;
    }


    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }


    public void setRemoteHost(String remoteHost) {
        this.remoteHost = remoteHost;
        
    }


    public String getRemoteHost() {
        return remoteHost;
    }

}