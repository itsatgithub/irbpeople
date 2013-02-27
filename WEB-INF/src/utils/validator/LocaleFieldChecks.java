package utils.validator;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.validator.DateValidator;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.ValidatorResult;
import org.apache.commons.validator.ValidatorResults;
import org.apache.commons.validator.util.ValidatorUtils;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.validator.Resources;

import utils.beanUtils.converters.NumberConverter;
import utils.userUtils.UserUtils;

/**
 * Static methods to make validations.
 * 
 * @author Automatika - JustInMind SL
 */
public class LocaleFieldChecks implements Serializable {

	/**
	 * Method to validate a nested form bean
     * @param bean The bean validation is being performed on.
     * @param va The <code>ValidatorAction</code> that is currently being performed.
     * @param field The <code>Field</code> object associated with the currentfield being validated.
     * @param errors The <code>ActionMessages</code> object to add errors to if anyvalidation errors occur.
     * @param request Current request object.
	 * @return <code>true</code> if meets stated requirements, <code>false</code> otherwise.
	 */
	public static boolean validateNested(Object bean, ValidatorAction va,
			Field field, ActionMessages errors, Validator validator,
			HttpServletRequest request) {
		try {
			String datePatternCode = field.getVarValue("beanName");

			Object property = new PropertyUtilsBean().getNestedProperty(bean,
					field.getProperty());

			Validator currentValidator = Resources.initValidator(
					datePatternCode, property, request.getSession()
							.getServletContext(), request, errors, /* page */0);

			boolean correct = true;
			// currentValidator=new
			// Validator(Resources.getValidatorResources(application, request),
			// datePatternCode);
			ValidatorResults results = currentValidator.validate();
			Set resultsKeys = results.getPropertyNames();

			for (Iterator i = resultsKeys.iterator(); i.hasNext();) {
				ValidatorResult result = results.getValidatorResult((String) i
						.next());
				if (result.getActionMap().values().contains(new Boolean(false))) {
					correct = false;
					break;
				}
			}

			// for(Iterator i=results.getResultValueMap().values().iterator();
			// i.hasNext();){
			// if(((ValidatorResult)i.next()).getActionMap().values().contains(new
			// Boolean(false))){
			// correct=false;
			// break;
			// }
			// }

			return correct;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Method to validate a Date. It uses the locale for the validation.
     * @param bean The bean validation is being performed on.
     * @param va The <code>ValidatorAction</code> that is currently being performed.
     * @param field The <code>Field</code> object associated with the currentfield being validated.
     * @param errors The <code>ActionMessages</code> object to add errors to if anyvalidation errors occur.
     * @param validator
     * @param request Current request object.
	 * @return <code>true</code> if meets stated requirements, <code>false</code> otherwise.
	 */
	public static boolean validateDate(Object bean, ValidatorAction va,
			Field field, ActionMessages errors, Validator validator,
			HttpServletRequest request) {

		String value = null;
		if (isString(bean)) {
			value = (String) bean;
		} else {
			value = ValidatorUtils.getValueAsString(bean, field.getProperty());
		}

		if (value == null || value.trim().equals("") || value.equals("0"))
			return true;

		String datePatternCode = field.getVarValue("datePattern");
		if (datePatternCode == null)
			datePatternCode = "simpleDate";

		Boolean strict = Boolean.parseBoolean(field.getVarValue("strict"));
		Locale locale = UserUtils.getCurrentLocale(request);

		// if(!value.matches("\\d{1,2}/\\d{1,2}/\\d\\d\\d\\d")) return false;
		boolean valid = validateDate(value, locale, datePatternCode, strict);
		if (!valid) {
			errors.add(field.getKey(), Resources.getActionMessage(request, va,
					field));
		}

		return valid;
	}


	/**
	 * Method to validate a Date. It uses the locale for the validation.
	 * @param value value to validate
	 * @param locale locale to use
	 * @return <code>true</code> if meets stated requirements, <code>false</code> otherwise.
	 */
	public static boolean validateDate(String value, Locale locale) {
		return validateDate(value, locale, "simpleDate", false);
	}

	/**
	 * Returns true if the value is a correct date.
	 * 
	 * @param value the (possible) date in string format
	 * @param locale the locale for the date
	 * @param datePatternCode the key that represents the way this Date is beeing represented, (the key of the property file)
	 * @param strict true if the comparizon is strict
	 * @return true is the value can be parsed to a date
	 */
	public static boolean validateDate(String value, Locale locale,
			String datePatternCode, boolean strict) {
		ResourceBundle patternsBundle = ResourceBundle.getBundle("Patterns",
				locale);
		String datePattern = patternsBundle.getString(datePatternCode);

		value = value.trim().replace('-', '/');

		return DateValidator.getInstance().isValid(value, datePattern, strict);
	}
	
	/**
	 * Method to validate BigDecimals. It uses the locale for the validation.
     * @param bean The bean validation is being performed on.
     * @param va The <code>ValidatorAction</code> that is currently being performed.
     * @param field The <code>Field</code> object associated with the currentfield being validated.
     * @param errors The <code>ActionMessages</code> object to add errors to if anyvalidation errors occur.
     * @param validator
     * @param request Current request object.
	 * @return <code>true</code> if meets stated requirements, <code>false</code> otherwise.
	 */
	public static boolean validateBigDecimal(Object bean, ValidatorAction va,
			Field field, ActionMessages errors, Validator validator,
			HttpServletRequest request) {

		String value = null;
		if (isString(bean)) {
			value = (String) bean;
		} else {
			value = ValidatorUtils.getValueAsString(bean, field.getProperty());
		}

		if (value == null || value.trim().equals("") || value.equals("0"))
			return true;

		Locale locale = UserUtils.getCurrentLocale(request);
		
		boolean valid = validateBigDecimal(value, locale);
		if (!valid) {
			errors.add(field.getKey(), Resources.getActionMessage(request, va,
					field));
		}

		return valid;
	}

	/**
	 * Returns true if the value is a correct bigdecimal
	 * @param value value to test
	 * @param locale locale to use
	 * @return <code>true</code> if meets stated requirements, <code>false</code> otherwise.
	 */
	public static boolean validateBigDecimal(String value, Locale locale) {
		if (value == null || value.trim().equals(""))
			return true;
		else {
			ResourceBundle patternsBundle = ResourceBundle.getBundle(
					"Patterns", locale);
			String ts = patternsBundle.getString("thousandsSeparator");
			String ds = patternsBundle.getString("decimalSeparator");

			String pattern = "[-+]?(?:(?:(?>\\d{1,3})[@](?=\\d\\d\\d))+(?:\\d\\d\\d)(?:[#]\\d*)?|\\d*(?:[#]\\d*)?)";

			pattern = pattern.replaceAll("@", ts);
			pattern = pattern.replaceAll("#", ds);
			
			return value.trim().matches(pattern);
		}
	}
	
	/**
	 * Method to validate Integer. It uses the locale for the validation.
     * @param bean The bean validation is being performed on.
     * @param va The <code>ValidatorAction</code> that is currently being performed.
     * @param field The <code>Field</code> object associated with the currentfield being validated.
     * @param errors The <code>ActionMessages</code> object to add errors to if anyvalidation errors occur.
     * @param validator
     * @param request Current request object.
	 * @return <code>true</code> if meets stated requirements, <code>false</code> otherwise.
	 */
	public static boolean validateInteger(Object bean, ValidatorAction va,
			Field field, ActionMessages errors, Validator validator,
			HttpServletRequest request) {

		String value = null;
		if (isString(bean)) {
			value = (String) bean;
		} else {
			value = ValidatorUtils.getValueAsString(bean, field.getProperty());
		}

		if (value == null || value.trim().equals("") || value.equals("0"))
			return true;

		Locale locale = UserUtils.getCurrentLocale(request);
		
		boolean valid = validateInteger(value, locale);
		if (!valid) {
			errors.add(field.getKey(), Resources.getActionMessage(request, va,
					field));
		}

		return valid;
	}
	
	/**
	 * Returns true if the value is a correct integer
	 * @param value value to test
	 * @param locale locale to use
	 * @return <code>true</code> if meets stated requirements, <code>false</code> otherwise.
	 */
	public static boolean validateInteger(String value, Locale locale) {
		if (value == null || value.equals(""))
			return true;
		else {
			ResourceBundle patternsBundle = ResourceBundle.getBundle(
					"Patterns", locale);
			String ts = patternsBundle.getString("thousandsSeparator");

			String pattern = "[-+]?(?:(?:(?>\\d{1,3})[@](?=\\d\\d\\d))+(?:\\d\\d\\d)?|\\d*?)";

			pattern = pattern.replaceAll("@", ts);
			
			return value.trim().matches(pattern);
		}
	}
	
	/**
	 * Method to validate if a bigDecimal is inside a range. It uses the locale for the validation.
     * @param bean The bean validation is being performed on.
     * @param va The <code>ValidatorAction</code> that is currently being performed.
     * @param field The <code>Field</code> object associated with the currentfield being validated.
     * @param errors The <code>ActionMessages</code> object to add errors to if anyvalidation errors occur.
     * @param validator
     * @param request Current request object.
	 * @return <code>true</code> if meets stated requirements, <code>false</code> otherwise.
	 */
	public static boolean validateBigDecimalRange(Object bean, ValidatorAction va,
			Field field, ActionMessages errors, Validator validator,
			HttpServletRequest request) {
        String value = ValidatorUtils.getValueAsString(bean, field.getProperty());
         if ( value == null || value.trim().equals("")  ) return true;
         else {
             try {
            	 Locale locale = UserUtils.getCurrentLocale(request);
            	 NumberConverter numberConverter=new NumberConverter(locale);
                 BigDecimalConverter bdc = new BigDecimalConverter();
     			 
                 
                 BigDecimal bd = (BigDecimal)numberConverter.convert(BigDecimal.class, value);
                 BigDecimal max = (BigDecimal)bdc.convert(String.class,(field.getVarValue("maximum") == null ? "999999999.99" : field.getVarValue("maximum")));
                 BigDecimal min = (BigDecimal)bdc.convert(String.class,(field.getVarValue("minimum") == null ? "-999999999.99" : field.getVarValue("minimum")));
                  
                 if (bd.compareTo(min) < 0) {
            			errors.add(field.getKey(), Resources.getActionMessage(request, va,
            					field)); 
                	 return false;
                 }
                 if (bd.compareTo(max) > 0) {
            			errors.add(field.getKey(), Resources.getActionMessage(request, va,
            					field));    
                	 return false;
                 }
              
                 return true;
             } catch (ConversionException e) {
       			errors.add(field.getKey(), Resources.getActionMessage(request, va,
        					field));            	 
                 return false;
             }
         }
  }
	
	/**
	 * Method to validate if the lenght of a String.
     * @param bean The bean validation is being performed on.
     * @param va The <code>ValidatorAction</code> that is currently being performed.
     * @param field The <code>Field</code> object associated with the currentfield being validated.
     * @param errors The <code>ActionMessages</code> object to add errors to if anyvalidation errors occur.
     * @param validator
     * @param request Current request object.
	 * @return <code>true</code> if meets stated requirements, <code>false</code> otherwise.
	 */
    public static boolean validateStringSize(Object bean, ValidatorAction va,
			Field field, ActionMessages errors, Validator validator,
			HttpServletRequest request) {
        
    	boolean isValid = false;
    	String value = ValidatorUtils.getValueAsString(bean, field.getProperty());
    	if (value == null ) return true;
           try {
        	   String length = field.getVarValue("size");
        	   isValid =  (value.length() <= new Integer(length)) ? true : false;
           } catch (Exception e) {
        	   e.printStackTrace();
           }
           if(!isValid){       		
    			errors.add(field.getKey(), Resources.getActionMessage(request, va,
    					field));    		
           }
           return isValid;
    }

	/**
	 * Return <code>true</code> if the specified object is a String or a
	 * <code>null</code> value.
	 * 
	 * @param o
	 *            Object to be tested
	 * @return The string value
	 */
	protected static boolean isString(Object o) {
		return (o == null) ? true : String.class.isInstance(o);
	}
}
