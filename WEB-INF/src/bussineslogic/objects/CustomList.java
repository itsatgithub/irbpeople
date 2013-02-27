package bussineslogic.objects;

import java.util.Date;

import utils.Persistent;

public class CustomList implements Persistent{

	
	//Code (primary key) of this nationality
	private String customListcode;
	//Version of the current instance. This attribute is used by hibernate to control concurrent modifications.
	private int version;
	//Indicates whereas this nationality is deleted
	private boolean deleted=false;
	
	//definition of the specific attributes
	
	String name;
	String view_name;
	String usercode;
	Date update_date;
	String comments;
	boolean periodic;

	public CustomList(){}
	
	public String getCustomListcode() {
		return customListcode;
	}

	public void setCustomListcode(String customListcode) {
		this.customListcode = customListcode;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getCode() {
		
		return getCustomListcode();
	}

	public void setCode(String code) {
		setCustomListcode(code);
		
	}
	
	
	@Override
	public String toString(){
		if(getCode()==null || getCode().equals("")) return "";
		
		
		return getCode();
	}

	public String getView_name() {
		return view_name;
	}

	public void setView_name(String view_name) {
		this.view_name = view_name;
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

    public boolean isPeriodic() {
        return periodic;
    }

    public void setPeriodic(boolean periodic) {
        this.periodic = periodic;
    }
    
    public boolean getPeriodic() {
        return periodic;
    }
    

}
