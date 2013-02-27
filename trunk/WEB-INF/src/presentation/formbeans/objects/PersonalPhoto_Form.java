package presentation.formbeans.objects;

import org.apache.struts.upload.FormFile;

import utils.validator.ValidatorFormAndAction;

public class PersonalPhoto_Form extends ValidatorFormAndAction{
	private String personalPhotocode;
	private String version;
	private String deleted;
	private FormFile photo;
	
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	public String getPersonalPhotocode() {
		return personalPhotocode;
	}
	public void setPersonalPhotocode(String personalPhotocode) {
		this.personalPhotocode = personalPhotocode;
	}
	public FormFile getPhoto() {
		return photo;
	}
	public void setPhoto(FormFile photo) {
		this.photo = photo;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
}
