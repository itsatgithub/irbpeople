package com.justinmind.MailSystem;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;


import bussineslogic.controlers.UseCase;
import bussineslogic.objects.Irbholiday;
import bussineslogic.objects.Personal;
import bussineslogic.objects.Usuario;

import com.justinmind.MailSystem.AbstractMailToSupervisors.MailData;
import com.justinmind.usermanagement.api.UserManagement;
import com.justinmind.usermanagement.entity.Role;


public class MailAdviseSupervisorNewHolidaysFromPersonal extends MailAbstract {

	private static org.apache.log4j.Category log= org.apache.log4j.Logger.getLogger(MailAbstract.class);
		
	    public MailAdviseSupervisorNewHolidaysFromPersonal() {
	        super();
	        // TODO Auto-generated constructor stub
	    }
	    
	    Personal person;
	    MailData mailData;
	    
	    public void process(Object bean){                        
	    	mailData=(MailData)bean;
	    	person=mailData.getSupervisor();
	        
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
	    	String supervisorSurname=mailData.getSupervisor().getSurname1();
	    	Irbholiday irbholiday = mailData.getIrbholiday();
	    	Personal personal = irbholiday.getPersonal();
	    	String personalName=personal.getName()+" "+personal.getSurname1();
	    	
	    	//obtenim la url per a la validacio
	    	String validationUrl = getValidationUrl(irbholiday.getCode(), irbholiday.getValidationcode());
	    	
	    	//afegir el parametre validationUrl als parametres
	    	String[] parameters=new String[]{supervisorSurname, personalName, validationUrl};
	    	
	    	String generalMessage=getMessage();
	    	
			MessageFormat formatter = new MessageFormat("");
			formatter.setLocale(loc);
			
			formatter.applyPattern(generalMessage);
			String message = formatter.format(parameters);

	        return message;
	    }
	    
	  private String getValidationUrl(String irbholidayCode, String validationCode ) {
	    	
	    	String validationAction = "/other/action_validate_holidays.do";
		  	ResourceBundle bundle=ResourceBundle.getBundle("MailConfiguration");
	    	String systemUrl=bundle.getString("systemUrl");
	    	
	    	return systemUrl+validationAction+"?irbholidaycode="+irbholidayCode+"&validationcode="+validationCode;
	    }

	    
	    public static class MailData{
	    	Personal supervisor;
	    	Irbholiday irbholiday;
	    	
			public MailData(Personal supervisor, Irbholiday irbholiday) {
				this.supervisor = supervisor;
				this.irbholiday = irbholiday;
			}
			public Personal getSupervisor() {
				return supervisor;
			}
			public void setSupervisor(Personal supervisor) {
				this.supervisor = supervisor;
			}
			public Irbholiday getIrbholiday() {
				return irbholiday;
			}
			public void setIrbholiday(Irbholiday irbholiday) {
				this.irbholiday = irbholiday;
			}
	    	
	    }
}
