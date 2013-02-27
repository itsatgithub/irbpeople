package bussineslogic.objects;

import utils.Persistent;

public class FilterType implements Persistent{

	
	//Code (primary key) of this nationality
	private String filterTypecode;
	//Version of the current instance. This attribute is used by hibernate to control concurrent modifications.
	private int version;
	//Indicates whereas this nationality is deleted
	private boolean deleted=false;
	
	//definition of the specific attributes
	
	String name;

	public FilterType(){}
	
	public String getFilterTypecode() {
		return filterTypecode;
	}

	public void setFilterTypecode(String filterTypecode) {
		this.filterTypecode = filterTypecode;
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
		 
		return getFilterTypecode();
	}

	public void setCode(String code) {
		setFilterTypecode(code);
		
	}
	@Override
	public String toString(){
		if(getCode()==null || getCode().equals("")) return "";
		
		
		return getCode();
	}

}
