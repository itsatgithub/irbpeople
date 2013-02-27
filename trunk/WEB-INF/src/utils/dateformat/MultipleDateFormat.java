/*
 * Created on 06/06/2007
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package utils.dateformat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class MultipleDateFormat {
    
    /**
     * Parse several date formats to obtain the correct date.
     * @param ingoingTime
     * @return null when parsing have been IMPOSSIBLE.
     */
    public Date parse(String ingoingTime, Locale l) {
    	ResourceBundle apps = ResourceBundle.getBundle("Patterns", l);
    	
    	SimpleDateFormat dateFormatterDate = new SimpleDateFormat(apps.getString("simpleDate"));
    	SimpleDateFormat dateFormatterDateAndTime = new SimpleDateFormat(apps.getString("simpleDateAndTime"));;
    	
        // Read UTC Times
        /*
        Timestamp sd        = rs.getTimestamp("StartDate");
        Calendar startTime  = new GregorianCalendar(timezone);
        startTime.setTime(new java.util.Date(sd.getTime()));        
        */        
        
        Date date = null;
        try {
            // "dd/MM/yyyy"
            date = dateFormatterDateAndTime.parse(ingoingTime);
        } catch (ParseException e) {
            try {
                // "dd/MM/yyyy hh:mm"
                date = dateFormatterDate.parse(ingoingTime);
            } catch (ParseException e1) {
                return null;
            }
        } catch (Exception ee) {
            return null;
        }
        return date;
    }
    
    /**
     * Parse date to localized string
     */
    public String parse(Date date, Locale l) {
    	ResourceBundle apps = ResourceBundle.getBundle("Patterns", l);
    	
    	SimpleDateFormat dateFormatterDate = new SimpleDateFormat(apps.getString("simpleDate"));
    	
    	String strDate = null;
    	try {
    		strDate = dateFormatterDate.format(date);
    	} catch (Exception e) {
			return null;
		}
    	return strDate;
    }

}
