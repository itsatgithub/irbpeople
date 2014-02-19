package bussineslogic.objects;

import java.util.Date;

import utils.Persistent;

public class User_access implements Persistent {

	private String usercode;
	private String name;
	private boolean locked;
	private Date last_access;

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public Date getLast_access() {
		return last_access;
	}

	public void setLast_access(Date last_access) {
		this.last_access = last_access;
	}

	public int getTries() {
		return tries;
	}

	public void setTries(int tries) {
		this.tries = tries;
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

	private int tries;

	private int version;
	private boolean deleted = false;

	public User_access() {
	}

	public String getCode() {
		return usercode;
	}

	public void setCode(String code) {
		setUsercode(code);
	}

	@Override
	public String toString() {
		if (getCode() == null || getCode().equals(""))
			return "";

		return getCode();
	}

}