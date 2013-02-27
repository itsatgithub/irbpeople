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


public class MailUserActivated extends MailAbstract {

	private static org.apache.log4j.Category log= org.apache.log4j.Logger.getLogger(MailAbstract.class);
	
    public MailUserActivated() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    Usuario user;
    
    public void process(Object bean){                        
        user=(Usuario)bean;
        
        Locale loc=new Locale(user.getLanguage()!=null ? user.getLanguage().getLanguagecode() : "en");
        String[] destinatarios = new String[]{user.getEmail()};
        
        setLocale(loc);
        Send(getMessage(loc), destinatarios, getSubject());
        UseCase.CreateMailSentAuditmessage(user.getPersonal(), getSubject(), user.getEmail());
       
    }
    
    public String getMessage(Locale loc){
    	String userName=user.getUsername();
    	String[] parameters=new String[]{userName};
    	
    	String generalMessage=getMessage();
    	
		MessageFormat formatter = new MessageFormat("");
		formatter.setLocale(loc);
		
		formatter.applyPattern(generalMessage);
		String message = formatter.format(parameters);
		
        return message;
    }
    

}
