package com.justinmind.MailSystem;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;


import bussineslogic.controlers.UseCase;
import bussineslogic.objects.Personal;
import bussineslogic.objects.Research_group;
import bussineslogic.objects.Usuario;

import com.justinmind.usermanagement.api.UserManagement;
import com.justinmind.usermanagement.entity.Role;


public class MailSettedToSupervisor extends MailAbstract {

	private static org.apache.log4j.Category log= org.apache.log4j.Logger.getLogger(MailAbstract.class);
	Personal person;
	MailData data;
	
    public MailSettedToSupervisor() {
        super();
        // TODO Auto-generated constructor stub
    }
    


    public void process(Object bean){
    	data=(MailData)bean;
    	person=data.getPerson();
    	
        String emailDestinatario = UseCase.ObtenerEmailActualFromPersonal(person.getPersonalcode());
    	
        Locale loc=new Locale(person.getLanguage()!=null ? person.getLanguage().getLanguagecode() : "en");
        String[] destinatarios = new String[]{emailDestinatario};
        
        setLocale(loc);
        Send(getMessage(loc), destinatarios, getSubject());
        UseCase.CreateMailSentAuditmessage(person, getSubject(), emailDestinatario);
       
    }
    
    public String getMessage(Locale loc){
    	String username=person.getSurname1();
    	String groupName=data.getResearch_group().getDescription();
    	
    	String[] parameters=new String[]{username, groupName};
    	
    	String generalMessage=getMessage();
    	
		MessageFormat formatter = new MessageFormat("");
		formatter.setLocale(loc);
		
		formatter.applyPattern(generalMessage);
		String message = formatter.format(parameters);
		
        return message;
    }
    
    public static class MailData{
    	Personal person;
    	Research_group research_group;
    	public MailData(Personal person, Research_group research_group) {
			this.person = person;
			this.research_group = research_group;
		}
		public Research_group getResearch_group() {
			return research_group;
		}
		public void setResearch_group(Research_group research_group) {
			this.research_group = research_group;
		}
		public Personal getPerson() {
			return person;
		}
		public void setPerson(Personal person) {
			this.person = person;
		}
    }
 
}
