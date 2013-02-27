package com.justinmind.MailSystem;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;


import bussineslogic.controlers.UseCase;
import bussineslogic.objects.Personal;
import bussineslogic.objects.Usuario;

import com.justinmind.usermanagement.api.UserManagement;
import com.justinmind.usermanagement.entity.Role;


public abstract class AbstractMailToSupervisors extends MailAbstract {

	private static org.apache.log4j.Category log= org.apache.log4j.Logger.getLogger(MailAbstract.class);
	
    public AbstractMailToSupervisors() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    Personal supervisor;
    MailData mailData;
    
    public void process(Object bean){                        
    	mailData=(MailData)bean;
    	supervisor=mailData.getSupervisor();
        
    	String emailDestinatario = UseCase.ObtenerEmailActualFromPersonal(supervisor.getCode());
    	
        Locale loc=new Locale(supervisor.getLanguage()!=null ? supervisor.getLanguage().getLanguagecode() : "en");
        String[] destinatarios = new String[]{emailDestinatario};
        
        setLocale(loc);
        Send(getMessage(loc), destinatarios, getSubject());
        createAuditMessage(supervisor, getSubject(), emailDestinatario);
       
    }
    
    public void createAuditMessage(Personal supervisor, String subject, String email){
    	UseCase.CreateMailSentAuditmessage(supervisor, getSubject(), email);
    }
    
    public String getMessage(Locale loc){
    	String supervisorSurname=mailData.getSupervisor().getSurname1();
    	Personal personal=mailData.getValitadionPersonal();
    	String personalName=personal.getName()+" "+personal.getSurname1();
    	
    	//obtenim la url per a la validacio
    	String validationUrl = getValidationUrl(personal.getCode(), personal.getValidationCode());
    	
    	//afegir el parametre validationUrl als parametres
    	String[] parameters=new String[]{supervisorSurname, personalName, validationUrl};
    	
    	String generalMessage=getMessage();
    	
		MessageFormat formatter = new MessageFormat("");
		formatter.setLocale(loc);
		
		formatter.applyPattern(generalMessage);
		String message = formatter.format(parameters);

        return message;
    }
    
  private String getValidationUrl(String personalCode, String validationCode ) {
    	
    	String validationAction = "/other/action_validate_personal.do";
	  	ResourceBundle bundle=ResourceBundle.getBundle("MailConfiguration");
    	String systemUrl=bundle.getString("systemUrl");
    	
    	return systemUrl+validationAction+"?personalcode="+personalCode+"&validationcode="+validationCode;
    }

    
    public static class MailData{
    	Personal supervisor;
    	Personal valitadionPersonal;
    	
		public MailData(Personal supervisor, Personal valitadionPersonal) {
			this.supervisor = supervisor;
			this.valitadionPersonal = valitadionPersonal;
		}
		public Personal getSupervisor() {
			return supervisor;
		}
		public void setSupervisor(Personal supervisor) {
			this.supervisor = supervisor;
		}
		public Personal getValitadionPersonal() {
			return valitadionPersonal;
		}
		public void setValitadionPersonal(Personal valitadionPersonal) {
			this.valitadionPersonal = valitadionPersonal;
		}
    	
    }

}
