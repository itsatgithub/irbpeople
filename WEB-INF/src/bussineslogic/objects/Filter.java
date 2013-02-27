package bussineslogic.objects;

import utils.Persistent;

public class Filter implements Persistent{

	
	//Code (primary key) of this nationality
	private String filtercode;
	//Version of the current instance. This attribute is used by hibernate to control concurrent modifications.
	private int version;
	//Indicates whereas this nationality is deleted
	private boolean deleted=false;
	
	//definition of the specific attributes
	
	String name;
	String value;
	String type;
	CustomList assocCustomList;
	FilterType assocFilterType;
	
	
	
	
	public Filter(){};
	
	public String getFiltercode() {
		return filtercode;
	}
	public void setFiltercode(String filtercode) {
		this.filtercode = filtercode;
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
	public CustomList getAssocCustomList() {
		return assocCustomList;
	}
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setAssocCustomList(CustomList assocCustomList) {
		this.assocCustomList = assocCustomList;
	}
	public FilterType getAssocFilterType() {
		return assocFilterType;
	}
	public void setAssocFilterType(FilterType assocfilterType) {
		this.assocFilterType = assocfilterType;
	}


	public String getCode() {
		 
		return  getFiltercode();
	}

	public void setCode(String code) {
		setFiltercode(code);
		
	}
	
	@Override
	public String toString(){
		if(getCode()==null || getCode().equals("")) return "";
		
		
		return getCode();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
	

}
