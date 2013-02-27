package com.justinmind.MailSystem;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import bussineslogic.controlers.UseCase;
import bussineslogic.objects.Grant_concession;
import bussineslogic.objects.Personal;
import bussineslogic.objects.Professional;
import bussineslogic.objects.Usuario;

import com.justinmind.MailSystem.AbstractMailToSupervisors.MailData;

public class MailContractsAboutToExpire extends MailAbstract {

    private static org.apache.log4j.Category log= org.apache.log4j.Logger.getLogger(MailAbstract.class);

    private static ResourceBundle MAINCONFIG = ResourceBundle.getBundle("MainConfiguration");

    public MailContractsAboutToExpire() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    MailData mailData;
    
    
    public void process(Object bean){                        
    	mailData=(MailData)bean;
    
    	String emailDestinatario = mailData.getRrhh();
    	
        Locale loc=new Locale(MAINCONFIG.getString("rrhhLanguage"));
//        String[] destinatarios = new String[]{emailDestinatario};
        
        setLocale(loc);
        Send(getMessage(loc), emailDestinatario.split(";"), getSubject());
//        createAuditMessage(user, getSubject(), emailDestinatario);
       
    }
    
    public void createAuditMessage(Personal user, String subject, String email){
    	UseCase.CreateMailSentAuditmessage(user, getSubject(), email);
    }
    
    public String getMessage(Locale loc) {
    	        
        String listContracts="";
    	for(Personal p: mailData.getPersonalsWithContractAboutToExpire())
    	{
    	    for(Professional pr: p.getIprofessional_personal())
    	    {
    	        if(pr.isCurrent())
    	        {
    	            listContracts += p.toString() + " - " + pr.getType_of_contract().getDescription() + " - " + (new SimpleDateFormat("dd/MM/yyyy")).format(pr.getEnd_date());
    	        }
    	    }
    	}
        
    	String listGrants="";
    	for(Personal p: mailData.getPersonalsWithGrantAboutToExpire())
    	{
    	    for(Grant_concession gc: p.getIgrant_concession_personal())
    	    {
    	        if(gc.isCurrent())
    	        {
    	            listGrants += p.toString() + " - " + gc.getGrant().getDescription() + " - " + (new SimpleDateFormat("dd/MM/yyyy")).format(gc.getEnd_date());
    	        }
    	    }
    	}
        
    	String[] parameters=new String[]{MAINCONFIG.getString("interval"), listContracts, listGrants};
    	
    	String generalMessage=getMessage();
    	
		MessageFormat formatter = new MessageFormat("");
		formatter.setLocale(loc);
		
		formatter.applyPattern(generalMessage);
		String message = formatter.format(parameters);

        return message;
    }
    
    public static class MailData{
    	String rrhh;
    	List<Personal> personalsWithContractAboutToExpire;
    	List<Personal> personalsWithGrantAboutToExpire;
    	String observacions;
    	
		public MailData(String rrhh, List<Personal> personalsWithContractAboutToExpire, List<Personal> personalsWithGrantAboutToExpire, String observacions) {
		    this.personalsWithContractAboutToExpire = personalsWithContractAboutToExpire;
		    this.personalsWithGrantAboutToExpire = personalsWithGrantAboutToExpire;
		    this.rrhh = rrhh;
			this.observacions = observacions;
		}

		public String getRrhh() {
			return rrhh;
		}

		public void setRrhh(String rrhh) {
			this.rrhh = rrhh;
		}

		public String getObservacions() {
			return observacions;
		}

		public void setObservacions(String observacions) {
			this.observacions = observacions;
		}

        public List<Personal> getPersonalsWithContractAboutToExpire() {
            return personalsWithContractAboutToExpire;
        }

        public void setPersonalsWithContractAboutToExpire(List<Personal> personalsWithContractAboutToExpire) {
            this.personalsWithContractAboutToExpire = personalsWithContractAboutToExpire;
        }

        public List<Personal> getPersonalsWithGrantAboutToExpire() {
            return personalsWithGrantAboutToExpire;
        }

        public void setPersonalsWithGrantAboutToExpire(List<Personal> personalsWithGrantAboutToExpire) {
            this.personalsWithGrantAboutToExpire = personalsWithGrantAboutToExpire;
        }
    }
}
