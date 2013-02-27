package utils.beanUtils.converters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.beanutils.Converter;

/**
 * This class is a converter to local Date (it needs a Locale).
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class DateConverter implements Converter{

	public static SimpleDateFormat dateFormat;
	public static Locale locale = new Locale("es");
	
	public DateConverter(Locale l){
		locale = l;

		ResourceBundle patternsBundle = ResourceBundle.getBundle("Patterns",locale);
//		String datePattern = patternsBundle.getString("simpleDateAndTime");
		String datePattern = patternsBundle.getString("simpleDate");

		dateFormat=new SimpleDateFormat(datePattern);
		dateFormat.setLenient(false);
	}
	
	public Object convert(Class type, Object value) {
		try{
			return dateFormat.parse(((String) value).trim());
		}
		catch (ParseException e){
			return null;
		}
	}

}
