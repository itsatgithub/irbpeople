package bussineslogic.objects;

import utils.Persistent;

/**
 * This class contains business object 'alumni_params'.
 * 
 * @author Automatika - JustInMind SL
 * 
 */
public class Alumni_params implements Persistent {

	// Code (primary key) of this alumni_params
	private String alumni_paramscode;
	
	private String title;
	
	private String value;
	
	/**
	 * Default Constructor which creates an empty alumni_params
	 */
	public Alumni_params() {
	}

	/**
	 * Returns the code (primary key) of this alumni_params. Convenience method
	 * which calls
	 * {@link bussineslogic.objects.alumni_params#getalumni_paramscode()
	 * getalumni_paramscode()}
	 * 
	 * @return a String with the code
	 */
	public String getCode() {
		return getAlumni_paramscode();
	}

	/**
	 * Set the code (primary key) of this alumni_params. Convenience method
	 * which calls
	 * {@link bussineslogic.objects.alumni_params#setalumni_paramscode(String)
	 * setalumni_paramscode(String)}
	 * 
	 * @param alumni_paramscode
	 *            the String with the code
	 */
	public void setCode(String alumni_paramscode) {
		setAlumni_paramscode(alumni_paramscode);
	}

	/**
	 * Returns the code (primary key) of this alumni_params.
	 * 
	 * @return a String with the code
	 */
	public String getAlumni_paramscode() {
		return alumni_paramscode;
	}

	/**
	 * Set the code (primary key) of this alumni_params
	 * 
	 * @param alumni_paramscode
	 *            the String with the code
	 */
	public void setAlumni_paramscode(String alumni_paramscode) {
		this.alumni_paramscode = alumni_paramscode;
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