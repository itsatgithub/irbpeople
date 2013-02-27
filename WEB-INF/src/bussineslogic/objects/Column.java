package bussineslogic.objects;

import utils.Persistent;

public class Column implements Persistent{

		
		//Code (primary key) of this nationality
		private String columncode;
		//Version of the current instance. This attribute is used by hibernate to control concurrent modifications.
		private int version;
		//Indicates whereas this nationality is deleted
		private boolean deleted=false;
		
		//definition of the specific attributes
		
		String name;
		CustomList assocCustomList;
		
		
		public Column(){}


		public String getColumncode() {
			return columncode;
		}


		public void setColumncode(String columncode) {
			this.columncode = columncode;
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


		public void setAssocCustomList(CustomList assocCustomList) {
			this.assocCustomList = assocCustomList;
		}


		public String getCode() {
		
			return getColumncode();
		}

		public void setCode(String code) {
			setColumncode(code);
			
		}
		
		@Override
		public String toString(){
			if(getCode()==null || getCode().equals("")) return "";
			
			
			return getCode();
		}
		

}
