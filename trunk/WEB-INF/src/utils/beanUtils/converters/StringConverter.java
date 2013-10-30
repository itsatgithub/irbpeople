package utils.beanUtils.converters;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;

/**
 * This class is a standard converter to local String (it needs a Locale). It creates 'locale' strings from Date, Bigdecimals, Integer and Boolean. Other types are converted with the toString method.
 * 
 * @author Automatika - JustInMind SL
 *
 */

public final class StringConverter implements Converter {

	public static Locale locale = new Locale("es");

	public StringConverter(Locale l){
		locale = l;
	}
	
    /**
     * Convert the specified input object into an output object of the
     * specified type.
     *
     * @param type Data type to which this value should be converted
     * @param value The input value to be converted
     *
     * @exception ConversionException if conversion cannot be performed
     *  successfully
     */
    public Object convert(Class type, Object value) {

		ResourceBundle patternsBundle = ResourceBundle.getBundle("Patterns",locale);		
    	if(Date.class.isInstance(value)){
//    		String datePattern = patternsBundle.getString("simpleDateAndTime");
    		String datePattern = patternsBundle.getString("simpleDate");
    		Date date=(Date)value;
    		DateFormat format=new SimpleDateFormat(datePattern);
    		String result=format.format(date);
    		return result;
    	}
    	
    	if(BigDecimal.class.isInstance(value)){
    		String thousandsSeparator = patternsBundle.getString("thousandsSeparator");
            String decimalSeparator = patternsBundle.getString("decimalSeparator");
            int decimals = Integer.parseInt(patternsBundle.getString("decimals"));
            int groupingSize = Integer.parseInt(patternsBundle.getString("groupingSize"));
            
    		DecimalFormat pattern = new DecimalFormat();
    		DecimalFormatSymbols dec = new DecimalFormatSymbols();
    		dec.setDecimalSeparator(decimalSeparator.charAt(0));
    		dec.setGroupingSeparator(thousandsSeparator.charAt(0));
    		pattern.setDecimalFormatSymbols(dec);
    		pattern.setMaximumFractionDigits((new Integer(decimals)).intValue());
    		pattern.setMinimumFractionDigits((new Integer(decimals)).intValue());
    		pattern.setGroupingSize((new Integer(groupingSize)).intValue());
    		pattern.setGroupingUsed(true);
    		return pattern.format(value);
    	}
    	
    	if(Integer.class.isInstance(value) || int.class.isInstance(value) ){
    		String thousandsSeparator = patternsBundle.getString("thousandsSeparator");
    		int groupingSize = Integer.parseInt(patternsBundle.getString("groupingSize"));
    		DecimalFormat pattern = new DecimalFormat();
    		DecimalFormatSymbols dec = new DecimalFormatSymbols();
    		//dec.setDecimalSeparator(decimalSeparator.charAt(0));
    		dec.setGroupingSeparator(thousandsSeparator.charAt(0));
    		pattern.setDecimalFormatSymbols(dec);
    		pattern.setMaximumFractionDigits(0);
    		pattern.setMinimumFractionDigits(0);
    		pattern.setDecimalSeparatorAlwaysShown(false);
    		pattern.setGroupingSize((new Integer(groupingSize)).intValue());
    		pattern.setGroupingUsed(true);
    		return pattern.format(value);
    	}
    	if(byte.class.isInstance(value) || Byte.class.isInstance(value)){
    		if(value.toString().equals("1")){
    			return patternsBundle.getString("trueEtiq");
    		}
    		return null;
    	}
    	if(Boolean.class.isInstance(value)){
    		if((Boolean)value==true) return patternsBundle.getString("trueEtiq");
    		else return patternsBundle.getString("falseEtiq");
    	}
    	
        if (value == null) {
            return ((String) null);
        } else {
            return (value.toString());
        }

    }


}
