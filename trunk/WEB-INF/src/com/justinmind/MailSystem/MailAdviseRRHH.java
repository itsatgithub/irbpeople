package com.justinmind.MailSystem;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import bussineslogic.controlers.UseCase;
import bussineslogic.objects.Personal;
import bussineslogic.objects.Usuario;

import com.justinmind.MailSystem.AbstractMailToSupervisors.MailData;

public class MailAdviseRRHH extends MailAbstract {

private static org.apache.log4j.Category log= org.apache.log4j.Logger.getLogger(MailAbstract.class);
	
    private static ResourceBundle MAINCONFIG = ResourceBundle.getBundle("MainConfiguration");

    public MailAdviseRRHH() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    Personal user;
    MailData mailData;
    
    public void process(Object bean){                        
    	mailData=(MailData)bean;
    	user=mailData.getRRHH();
        
    	String emailDestinatarios = MAINCONFIG.getString("rrhhRecipients");
    	
        Locale loc=new Locale(MAINCONFIG.getString("rrhhLanguage"));
        
        String[] destinatarios=emailDestinatarios.split(";");
        
        setLocale(loc);
        Send(getMessage(loc), destinatarios, getSubject());
        createAuditMessage(user, getSubject(), destinatarios[0]);
       
    }
    
    public void createAuditMessage(Personal user, String subject, String email){
    	UseCase.CreateMailSentAuditmessage(user, getSubject(), email);
    }
    
    public String getMessage(Locale loc) {
    	Personal personal=mailData.getValitadionPersonal();
    	String personalName=personal.getName()+" "+personal.getSurname1();
    	String obs = mailData.getObservacions();
    	if(obs==null) obs="";
    	//afegir el parametre validationUrl als parametres
    	String[] parameters=new String[]{personalName, obs};
    	
    	String generalMessage=getMessage();
    	
		MessageFormat formatter = new MessageFormat("");
		formatter.setLocale(loc);
		
		formatter.applyPattern(generalMessage);
		String message = formatter.format(parameters);

        return message;
    }
    
    public static class MailData{
    	Personal rrhh;
    	Personal valitadionPersonal;
    	String observacions;
    	
		public MailData(Personal rrhh, Personal valitadionPersonal, String observacions) {
			this.rrhh = rrhh;
			this.valitadionPersonal = valitadionPersonal;
			this.observacions = observacions;
		}
		public Personal getRRHH() {
			return rrhh;
		}
		public void setRHH(Personal rrhh) {
			this.rrhh = rrhh;
		}
		public Personal getValitadionPersonal() {
			return valitadionPersonal;
		}
		public void setValitadionPersonal(Personal valitadionPersonal) {
			this.valitadionPersonal = valitadionPersonal;
		}
		public String getObservacions() {
			return observacions;
		}
		public void setObservacions(String observacions) {
			this.observacions = observacions;
		}
    }
}
