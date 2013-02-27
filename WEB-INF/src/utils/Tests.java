package utils;

import java.util.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import bussineslogic.controlers.UseCaseUtils;
import bussineslogic.objects.Irbholiday;

public class Tests {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Irbholiday diaFestivo = new Irbholiday();
        
        diaFestivo.setInitialdate((new GregorianCalendar(2009,7,25)).getTime());
        diaFestivo.setEnddate((new GregorianCalendar(2009,7,25)).getTime());
        
        Irbholiday vacaciones = new Irbholiday();
        vacaciones.setInitialdate( (new GregorianCalendar(2009,7,1)).getTime());
        vacaciones.setEnddate((new GregorianCalendar(2009,7,31)).getTime());
        
        System.out.println(UseCaseUtils.getDiffDays(vacaciones.getInitialdate(), vacaciones.getEnddate()));
        System.out.println(UseCaseUtils.getNumDaysWeekends(vacaciones.getInitialdate(), vacaciones.getEnddate()));
        
        List<Irbholiday> festius = new ArrayList<Irbholiday>();
        festius.add(diaFestivo);
        int totalFestivos=0;
        for(Irbholiday h: festius)
        {
            int diaSemana = h.getInitialdate().getDay();
            if(diaSemana!=0 && diaSemana!=6)
            {
                totalFestivos++;
            }
        }
        
        System.out.println(totalFestivos);
        System.out.println(diasLaborables(vacaciones.getInitialdate(), vacaciones.getEnddate(), festius));
        
        prueba( (new GregorianCalendar(2009,7,25)).getTime(), (new GregorianCalendar(2009,7,25)).getTime(), 2009);
        prueba( (new GregorianCalendar(2008,7,25)).getTime(), (new GregorianCalendar(2009,7,25)).getTime(), 2009);
        prueba( (new GregorianCalendar(2008,7,25)).getTime(), (new GregorianCalendar(2008,7,25)).getTime(), 2009);
        prueba( (new GregorianCalendar(2009,7,25)).getTime(), (new GregorianCalendar(2010,7,25)).getTime(), 2009);
        prueba( (new GregorianCalendar(2010,7,25)).getTime(), (new GregorianCalendar(2010,7,25)).getTime(), 2009);

        prueba2( (new GregorianCalendar(2009,7,25)).getTime(), (new GregorianCalendar(2009,7,25)).getTime(), 2009);
        prueba2( (new GregorianCalendar(2008,7,25)).getTime(), (new GregorianCalendar(2009,7,25)).getTime(), 2009);
        prueba2( (new GregorianCalendar(2008,7,25)).getTime(), (new GregorianCalendar(2008,7,25)).getTime(), 2009);
        prueba2( (new GregorianCalendar(2009,7,25)).getTime(), (new GregorianCalendar(2010,7,25)).getTime(), 2009);
        prueba2( (new GregorianCalendar(2010,7,25)).getTime(), (new GregorianCalendar(2010,7,25)).getTime(), 2009);

    }
    
    public static int diasLaborables(Date start, Date end, List<Irbholiday> festivos)
    {
        int totalDias = UseCaseUtils.getDiffDays( start, end);
        int totalFinesDeSemana = UseCaseUtils.getNumDaysWeekends( start, end);
        int totalFestivos = UseCaseUtils.getFestivosEntreFechas( start, end, festivos);
        
        return totalDias - totalFinesDeSemana - totalFestivos;
    }
    
    public static int diasLaborablesAnno(Date start, Date end, List<Irbholiday> festivos, int anno)
    {
        Date[] newDates = limitDateRangeToYear(new Date[]{start, end}, anno);
        
        Date startDate = newDates[0];
        Date endDate = newDates[1];
        
        return diasLaborables( startDate, endDate, festivos);
    }
    
    public static Date[] limitDateRangeToYear(Date[] range, int anno)
    {
        Date start = range[0];
        Date end = range[1];
        
        int startAnno = start.getYear() + 1900;
        int endAnno = end.getYear() + 1900;
        
        if( (startAnno < anno && endAnno < anno) || (startAnno > anno && endAnno > anno))
        {
            return (new Date[]{null, null});
        };
        
        if(startAnno < anno && endAnno >= anno)
            start = (new GregorianCalendar(anno,0,1)).getTime();
        if(endAnno > anno && startAnno <= anno)
            end = (new GregorianCalendar(anno,11,31)).getTime();
        
        return new Date[]{start, end};
         
    }
    
    static void prueba(Date start, Date end, int anno)
    {
        int startAnno = start.getYear() + 1900;
        int endAnno = end.getYear() + 1900;
        
        if( (startAnno < anno && endAnno < anno) || (startAnno > anno && endAnno > anno)) {System.out.println( 0);return;};
        
        Date startDate = null;
        Date endDate = null;
        if(startAnno < anno && endAnno >= anno) startDate = (new GregorianCalendar(anno,0,1)).getTime();
        if(endAnno > anno && startAnno <= anno) endDate = (new GregorianCalendar(anno,11,31)).getTime();
         
        if(startDate==null) startDate=start;
        if(endDate==null) endDate=end;
        
        System.out.println(startDate);
        System.out.println(endDate);
    }

    static void prueba2(Date start, Date end, int anno)
    {
        Date[] newDates = limitDateRangeToYear( new Date[]{start, end}, anno);
        
        if(newDates[0]==null && newDates[1]==null)
        {
            System.out.println(0);
            return;
        }
        
        System.out.println(newDates[0]);
        System.out.println(newDates[1]);
    }
}
