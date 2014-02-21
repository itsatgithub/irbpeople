package bussineslogic.objects;

import utils.Persistent;

/**
 * This class contains business object 'params'.
 * 
 * @author Automatika - JustInMind SL
 * 
 */
public class Params implements Persistent {

	// Code (primary key) of this params
	private String paramscode;
	
	private String title;
	
	private String value;
	
	/**
	 * Default Constructor which creates an empty params
	 */
	public Params() {
	}

	/**
	 * Returns the code (primary key) of this params. Convenience method
	 * which calls
	 * {@link bussineslogic.objects.params#getparamscode()
	 * getparamscode()}
	 * 
	 * @return a String with the code
	 */
	public String getCode() {
		return getParamscode();
	}

	/**
	 * Set the code (primary key) of this params. Convenience method
	 * which calls
	 * {@link bussineslogic.objects.params#setparamscode(String)
	 * setparamscode(String)}
	 * 
	 * @param paramscode
	 *            the String with the code
	 */
	public void setCode(String paramscode) {
		setParamscode(paramscode);
	}

	/**
	 * Returns the code (primary key) of this params.
	 * 
	 * @return a String with the code
	 */
	public String getParamscode() {
		return paramscode;
	}

	/**
	 * Set the code (primary key) of this params
	 * 
	 * @param paramscode
	 *            the String with the code
	 */
	public void setParamscode(String paramscode) {
		this.paramscode = paramscode;
	}

	

	@Override
	public String toString() {
		if (getCode() == null || getCode().equals(""))
			return "";

		return getCode();
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}