package bussineslogic.objects;

import utils.Persistent;

public class PersonalPhoto implements Persistent{

	//Code (primary key) of this payment
	private String personalPhotocode;
	//Version of the current instance. This attribute is used by hibernate to control concurrent modifications.
	private int version;
	//Indicates whereas this payment is deleted
	private boolean deleted=false;
	
	private byte[] photo;

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public String getPersonalPhotocode() {
		return personalPhotocode;
	}

	public void setPersonalPhotocode(String personalPhotocode) {
		this.personalPhotocode = personalPhotocode;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getCode() {
		return getPersonalPhotocode();
	}

	public void setCode(String code) {
		setPersonalPhotocode(code);
	}
}
