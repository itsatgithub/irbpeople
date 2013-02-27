package utils.validator;

import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.ValidatorResult;
import org.apache.commons.validator.ValidatorResults;
import org.apache.commons.validator.util.ValidatorUtils;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.validator.Resources;

public class CategoryCodeValidators {

	
	/**
	 * Method to validate 
     * @param bean The bean validation is being performed on.
     * @param va The <code>ValidatorAction</code> that is currently being performed.
     * @param field The <code>Field</code> object associated with the currentfield being validated.
     * @param errors The <code>ActionMessages</code> object to add errors to if anyvalidation errors occur.
     * @param request Current request object.
	 * @return <code>true</code> if meets stated requirements, <code>false</code> otherwise.
	 */
	public static boolean validateBankcode(Object bean, ValidatorAction va,
			Field field, ActionMessages errors, Validator validator,
			HttpServletRequest request) {
		
		String value = null;
		if (LocaleFieldChecks.isString(bean)) {
			value = (String) bean;
		} else {
			value = ValidatorUtils.getValueAsString(bean, field.getProperty());
		}
		
		if (value == null || value.trim().equals("") || value.equals("0"))
			return true;
		
		String pattern="\\p{Upper}\\p{Upper}\\d\\d\\d\\d";

		boolean valid=value.matches(pattern);
		if (!valid) {
			errors.add(field.getKey(), Resources.getActionMessage(request, va,
					field));
		}
		return valid;
	}
	
	
	
	/**
	 * Method to validate 
     * @param bean The bean validation is being performed on.
     * @param va The <code>ValidatorAction</code> that is currently being performed.
     * @param field The <code>Field</code> object associated with the currentfield being validated.
     * @param errors The <code>ActionMessages</code> object to add errors to if anyvalidation errors occur.
     * @param request Current request object.
	 * @return <code>true</code> if meets stated requirements, <code>false</code> otherwise.
	 */
	public static boolean validateLocation(Object bean, ValidatorAction va,
			Field field, ActionMessages errors, Validator validator,
			HttpServletRequest request) {
		
		String value = null;
		if (LocaleFieldChecks.isString(bean)) {
			value = (String) bean;
		} else {
			value = ValidatorUtils.getValueAsString(bean, field.getProperty());
		}
		
		if (value == null || value.trim().equals("") || value.equals("0"))
			return true;
		
		String pattern=".*";

		boolean valid=value.matches(pattern);
		if (!valid) {
			errors.add(field.getKey(), Resources.getActionMessage(request, va,
					field));
		}
		return valid;
	}
	
	/**
	 * Method to validate 
     * @param bean The bean validation is being performed on.
     * @param va The <code>ValidatorAction</code> that is currently being performed.
     * @param field The <code>Field</code> object associated with the currentfield being validated.
     * @param errors The <code>ActionMessages</code> object to add errors to if anyvalidation errors occur.
     * @param request Current request object.
	 * @return <code>true</code> if meets stated requirements, <code>false</code> otherwise.
	 */
	public static boolean validateNationallity(Object bean, ValidatorAction va,
			Field field, ActionMessages errors, Validator validator,
			HttpServletRequest request) {
		
		String value = null;
		if (LocaleFieldChecks.isString(bean)) {
			value = (String) bean;
		} else {
			value = ValidatorUtils.getValueAsString(bean, field.getProperty());
		}
		
		if (value == null || value.trim().equals("") || value.equals("0"))
			return true;
		
		String pattern="\\p{Upper}\\p{Upper}\\p{Upper}";

		boolean valid=value.matches(pattern);
		if (!valid) {
			errors.add(field.getKey(), Resources.getActionMessage(request, va,
					field));
		}
		return valid;
	}
	
	/**
	 * Method to validate 
     * @param bean The bean validation is being performed on.
     * @param va The <code>ValidatorAction</code> that is currently being performed.
     * @param field The <code>Field</code> object associated with the currentfield being validated.
     * @param errors The <code>ActionMessages</code> object to add errors to if anyvalidation errors occur.
     * @param request Current request object.
	 * @return <code>true</code> if meets stated requirements, <code>false</code> otherwise.
	 */
	public static boolean validateCountry(Object bean, ValidatorAction va,
			Field field, ActionMessages errors, Validator validator,
			HttpServletRequest request) {
		
		String value = null;
		if (LocaleFieldChecks.isString(bean)) {
			value = (String) bean;
		} else {
			value = ValidatorUtils.getValueAsString(bean, field.getProperty());
		}
		
		if (value == null || value.trim().equals("") || value.equals("0"))
			return true;
		
		String pattern="\\p{Upper}\\p{Upper}";

		boolean valid=value.matches(pattern);
		if (!valid) {
			errors.add(field.getKey(), Resources.getActionMessage(request, va,
					field));
		}
		return valid;
	}
	
}
