package com.justinmind.MailSystem;

import java.io.File;
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


public class MailEndOfContractInstructions extends MailAbstract {

	private static org.apache.log4j.Category log= org.apache.log4j.Logger.getLogger(MailAbstract.class);
	
    public MailEndOfContractInstructions() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    Personal person;
    

    public void process(Object bean){                        
    	MailData data=(MailData)bean;
    	person=data.getPerson();
    	
        
        Locale loc=new Locale(person.getLanguage()!=null ? person.getLanguage().getLanguagecode() : "en");
        String[] destinatarios = new String[]{UseCase.ObtenerEmailActualFromPersonal(person.getPersonalcode())};
        
        setLocale(loc);
       
        try
        {
            File f = new File(data.getFilename());
            this.attachment = f;
        }
        catch(Exception e)
        {
            log.error("Error al abrir el fichero: " + data.getFilename(), e);
        }
        
        
        Send(getMessage(loc), destinatarios, getSubject());
        UseCase.CreateMailSentAuditmessage(person, getSubject(), person.getPersonal_email());
    }
    
    public String getMessage(Locale loc){
    	
    	String[] parameters=new String[]{};
    	
    	String generalMessage=getMessage();
    	
		MessageFormat formatter = new MessageFormat("");
		formatter.setLocale(loc);
		
		formatter.applyPattern(generalMessage);
		String message = formatter.format(parameters);
		
        return message;
    }
    
    
    public static class MailData{
    	Personal person;
    	String filename;
    	
    	public MailData(Personal person, String filename){
    		this.filename=filename;
    		this.person=person;
    	}
    	
		public String getFilename() {
			return filename;
		}
		public void setFilename(String filename) {
			this.filename = filename;
		}
		public Personal getPerson() {
			return person;
		}
		public void setPerson(Personal person) {
			this.person = person;
		}
    }
}
