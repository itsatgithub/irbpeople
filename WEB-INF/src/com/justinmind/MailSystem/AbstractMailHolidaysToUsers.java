package com.justinmind.MailSystem;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import presentation.formbeans.objects.Irbholiday_Form;

import uicomponents.scheduler.ICalendar;
import utils.dateformat.MultipleDateFormat;


import bussineslogic.controlers.UseCase;
import bussineslogic.controlers.UseCaseUtils;
import bussineslogic.objects.Irbholiday;
import bussineslogic.objects.Irbholidayinfo;
import bussineslogic.objects.Personal;


import com.justinmind.usermanagement.api.UserManagement;
import com.justinmind.usermanagement.entity.Role;


public abstract class AbstractMailHolidaysToUsers extends MailAbstract {

	private static org.apache.log4j.Category log= org.apache.log4j.Logger.getLogger(MailAbstract.class);
	
    public AbstractMailHolidaysToUsers() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    Personal person;
    MailData mailData;
    
    public void process(Object bean){                        
    	mailData=(MailData)bean;
    	person=mailData.getPerson();
    	
    	String emailDestinatario = UseCase.ObtenerEmailActualFromPersonal(person.getPersonalcode());
        
        Locale loc=new Locale(person.getLanguage()!=null ? person.getLanguage().getLanguagecode() : "en");
        String[] destinatarios = new String[]{emailDestinatario};
        
        setLocale(loc);
        Send(getMessage(loc), destinatarios, getSubject());
        createAuditMessage(person, getSubject(), emailDestinatario);
       
    }
    
    public void createAuditMessage(Personal person, String subject, String email){
    	UseCase.CreateMailSentAuditmessage(person, getSubject(), email);
    }
    
    public String getMessage(Locale loc){
    	
    	Irbholiday irbholiday=mailData.getIrbholiday();
    	Irbholidayinfo irbholidayinfo=mailData.getIrbholidayinfo();
    	String observacions=mailData.getObservacions();
    	
    	String parameters[] = null;
    	
    	String initialdate = null, enddate = null, description = null, type = null;
    	String year = null, holidays = null, pendingholidays = null, aps = null, pendingaps = null, previousyearholidays = null;
    	
    	if(irbholiday!=null) {
	    	initialdate = new MultipleDateFormat().parse(irbholiday.getInitialdate(), loc);
	    	enddate = new MultipleDateFormat().parse(irbholiday.getEnddate(), loc);
	    	description = irbholiday.getDescription();
	    	type = Irbholiday_Form.getType(""+irbholiday.getType(), loc);
	    }
    	
    	if(irbholidayinfo!=null) {
    		year = ""+irbholidayinfo.getYear();
	    	holidays = ""+irbholidayinfo.getHolidays();
	    	pendingholidays = ""+irbholidayinfo.getPendingholidays();
	    	aps = ""+irbholidayinfo.getAps();
	    	pendingaps = ""+irbholidayinfo.getPendingaps();
	    	previousyearholidays = ""+irbholidayinfo.getPreviousyearholidays();
	    }
    	
    	if (irbholidayinfo == null) {
			parameters = new String[] {type, initialdate, enddate, description,
					observacions };
		} else if (irbholiday == null) {
			parameters = new String[] { year, previousyearholidays, holidays,
					pendingholidays, aps, pendingaps};
		} else {
			parameters = new String[] { type, initialdate, enddate, description,
					previousyearholidays, holidays, pendingholidays, aps,
					pendingaps, observacions };
		}
    	
    	String generalMessage=getMessage();
    	
		MessageFormat formatter = new MessageFormat("");
		formatter.setLocale(loc);
		
		formatter.applyPattern(generalMessage);
		String message = formatter.format(parameters);

        return message;
    }
    
    public static class MailData{
    	
    	Personal person;
    	Irbholiday irbholiday;
    	Irbholidayinfo irbholidayinfo;
    	String observacions;
    	
    	public MailData(Personal person, Irbholiday irbholiday, Irbholidayinfo irbholidayinfo, String observacions) {
    		this.person = person;
    		this.irbholiday = irbholiday;
    		this.irbholidayinfo = irbholidayinfo;
    		this.observacions = observacions;
    	}
    	
		public String getObservacions() {
			return observacions;
		}

		public void setObservacions(String observacions) {
			this.observacions = observacions;
		}

		public Personal getPerson() {
			return person;
		}
		public void setPerson(Personal person) {
			this.person = person;
		}
		public Irbholiday getIrbholiday() {
			return irbholiday;
		}
		public void setIrbholiday(Irbholiday irbholiday) {
			this.irbholiday = irbholiday;
		}

		public Irbholidayinfo getIrbholidayinfo() {
			return irbholidayinfo;
		}

		public void setIrbholidayinfo(Irbholidayinfo irbholidayinfo) {
			this.irbholidayinfo = irbholidayinfo;
		}
    	
    	
    }

}
