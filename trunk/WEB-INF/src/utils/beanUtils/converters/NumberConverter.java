package utils.beanUtils.converters;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.beanutils.Converter;

import bussineslogic.controlers.UseCase;

/**
 * This class is a converter to local Number (it needs a Locale).
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class NumberConverter implements Converter{
	private static org.apache.log4j.Category log = org.apache.log4j.Logger.getLogger(NumberConverter.class);
	
	public static Locale locale = new Locale("es");
	
	public NumberConverter(Locale l){
		locale = l;
	}
	
	public Object convert(Class type, Object value) {
		if(value instanceof BigDecimal) {
			return ((BigDecimal)value).add(BigDecimal.ZERO);
		}
		if(value instanceof Integer){
			return new Integer((Integer)value);
		}
		ResourceBundle patternsBundle = ResourceBundle.getBundle("Patterns",locale);
		String thousandsSeparator = patternsBundle.getString("thousandsSeparator");
        String decimalSeparator = patternsBundle.getString("decimalSeparator");
        int decimals = Integer.parseInt(patternsBundle.getString("decimals"));
        int groupingSize = Integer.parseInt(patternsBundle.getString("groupingSize"));
        
        if(value == null || value.toString().trim().equals("")) value = "0";
        
        Object aux = null;
        DecimalFormat pattern = new DecimalFormat();
        DecimalFormatSymbols dec = new DecimalFormatSymbols();
        dec.setDecimalSeparator(decimalSeparator.charAt(0));
        dec.setGroupingSeparator(thousandsSeparator.charAt(0));
        
        pattern.setMaximumFractionDigits(decimals);
        pattern.setMinimumFractionDigits(decimals);
        pattern.setDecimalFormatSymbols(dec);        
        pattern.setGroupingSize(groupingSize);
        pattern.setGroupingUsed(true);
        try{ 
        	if(type.getName().equals(BigDecimal.class.getName()))aux = new BigDecimal(pattern.parse(value.toString().trim()).toString());
        	else if(type.getName().equals(Integer.class.getName()) || type.getName().equals(int.class.getName()))aux = new Integer(pattern.parse(value.toString().trim()).toString());        	
        }catch(Exception e){
        	log.warn("Tring to convert to an number a class different from BigDecimal, Integer or int");
            return null;
        }
        return aux;
	}

}
