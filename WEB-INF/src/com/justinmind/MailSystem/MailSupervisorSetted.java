package com.justinmind.MailSystem;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;


import bussineslogic.controlers.UseCase;
import bussineslogic.objects.Usuario;

import com.justinmind.usermanagement.api.UserManagement;
import com.justinmind.usermanagement.entity.Role;


public class MailSupervisorSetted extends MailAbstract {

	private static org.apache.log4j.Category log= org.apache.log4j.Logger.getLogger(MailAbstract.class);
	
    public MailSupervisorSetted() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    Usuario user;
    String password;

    public void process(Object bean){                        
    	MailData data=(MailData)bean;
    	user=data.getUser();
    	password=data.getPassword();
        
        Locale loc=new Locale(user.getLanguage()!=null ? user.getLanguage().getLanguagecode() : "en");
        String[] destinatarios = new String[]{user.getEmail()};
        
        setLocale(loc);
        Send(getMessage(loc), destinatarios, getSubject());
        UseCase.CreateMailSentAuditmessage(user.getPersonal(), getSubject(), user.getEmail());
       
    }
    
    public String getMessage(Locale loc){
    	String username=user.getUsername();
    	
    	
    	String[] parameters=new String[]{username, password};
    	
    	String generalMessage=getMessage();
    	
		MessageFormat formatter = new MessageFormat("");
		formatter.setLocale(loc);
		
		formatter.applyPattern(generalMessage);
		String message = formatter.format(parameters);
		
        return message;
    }
    
    
    public static class MailData{
    	Usuario user;
    	String password;
    	
    	public MailData(Usuario user, String password){
    		this.password=password;
    		this.user=user;
    	}
    	
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public Usuario getUser() {
			return user;
		}
		public void setUser(Usuario user) {
			this.user = user;
		}
    }
}
