package uicomponents.scheduler;

import java.util.Calendar;
import java.util.Locale;

public interface ICalendar {
	
	public static String TYPE_VACANCES = "0";
	public static String TYPE_ASUMPTES_PROPIS = "1";
	public static String TYPE_FESTIU = "2";
	public static String TYPE_LIMIT_VANCANCES_ANY_ANTERIOR = "3";
	
	public static String STATUS_DEMANAT = "0";
	public static String STATUS_APROVAT = "1";
	public static String STATUS_DISFRUTAT = "2";
	
	public static String DESCRIPTION_VACANCES = "holidays";
	public static String DESCRIPTION_ASUMPTES_PROPIS = "aps";
	public static String DESCRIPTION_FESTIU = "";
	public static String DESCRIPTION_LIMIT_VACANCES_ANY_ANTERIOR = "";
	public static String DESCRIPTION_DEMANAT = "pending";
	public static String DESCRIPTION_APROVAT = "aproved";
	public static String DESCRIPTION_DISFRUTAT = "";

	public String getCalendarId();
	
	public Calendar getCalendarIntialdate(Object date, Locale l);
	
	public Calendar getCalendarEnddate(Object date, Locale l);
	
	public String getCalendarSubject();
	
	public String getType();

	public String getStatus();
}
