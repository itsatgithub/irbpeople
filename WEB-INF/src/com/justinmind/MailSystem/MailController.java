package com.justinmind.MailSystem;

import java.util.Locale;
import java.util.ResourceBundle;

import org.hibernate.Session;


import com.justinmind.util.siteorg.util.JavaNameFormatter;

public class MailController {

	private static org.apache.log4j.Category log= org.apache.log4j.Logger.getLogger(MailController.class);
	
    /** Singleton object */
    private static MailController theController = null;
    
    private static Locale locale = new Locale("es"); 
    
     /**
      * Singleton accessor.
      */
    public synchronized static MailController singleton() {
    
          if( theController == null ) {
              theController = new MailController();
          }
          return theController;
    }
    
    public MailController() {
        super();     
    }
    
    public void PrepareMail(String actionCase, Object bean, Session hs){
        Class auxClass=null;
        try{
            /* Comprobamos si el sistema de mails esta activo*/
            ResourceBundle apps = ResourceBundle.getBundle("MailConfiguration");
            String active = apps.getString(MailConstants.ACTIVATE_SYSTEM);
            if(!active.equals("yes")) {
                log.info("Sistema de alertas desactivado (ver fichero properties -> MailConfiguration)");
                return;
            }   
            /* Obtenemos el nombre de la clase que debe procesar y enviar
             * el mail*/
            actionCase = JavaNameFormatter.toJavaName(actionCase, true);
            auxClass = Class.forName(MailConstants.ACTION_CASE_PREFIX+actionCase);
            /* Instanciamos la clase */
            MailAbstract mailer = (MailAbstract)auxClass.newInstance();
            mailer.setHms(hs);
            /* Procesamos y enviamos el mail */
            mailer.process(bean);
//            mailer.doAudit();
        }catch(Exception e){
            log.warn(e);
        }
    }
    
    public void PrepareMail(String actionCase, Object bean, Object attachment, Session hs){
        Class auxClass=null;
        try{
            /* Comprobamos si el sistema de mails esta activo*/
            ResourceBundle apps = ResourceBundle.getBundle("MailConfiguration");
            String active = apps.getString(MailConstants.ACTIVATE_SYSTEM);
            if(!active.equals("yes")) {
                log.info("Sistema de alertas desactivado (ver fichero properties -> MailConfiguration)");
                return;
            }   
            /* Obtenemos el nombre de la clase que debe procesar y enviar
             * el mail*/
            actionCase = JavaNameFormatter.toJavaName(actionCase, true);
            auxClass = Class.forName(MailConstants.ACTION_CASE_PREFIX+actionCase);
            /* Instanciamos la clase */
            MailAbstract mailer = (MailAbstract)auxClass.newInstance();
            mailer.setHms(hs);
            mailer.setAttachment(attachment);
            mailer.setLocale(locale);
            /* Procesamos y enviamos el mail */
            mailer.process(bean);
//            mailer.doAudit();
        }catch(Exception e){
            log.warn(e);
        }
    }
    
    public void PrepareMail(String actionCase, Object bean, String urlMail, Session hs){
        Class auxClass=null;
        try{
            /* Comprobamos si el sistema de mails esta activo*/
            ResourceBundle apps = ResourceBundle.getBundle("MailConfiguration");
            String active = apps.getString(MailConstants.ACTIVATE_SYSTEM);
            if(!active.equals("yes")) {
                log.info("Sistema de alertas desactivado (ver fichero properties -> MailConfiguration)");
                return;
            }   
            /* Obtenemos el nombre de la clase que debe procesar y enviar
             * el mail*/
            actionCase = JavaNameFormatter.toJavaName(actionCase, true);
            auxClass = Class.forName(MailConstants.ACTION_CASE_PREFIX+actionCase);
            /* Instanciamos la clase */
            MailAbstract mailer = (MailAbstract)auxClass.newInstance();
            mailer.setHms(hs);
            /* Añadimos el link*/
            mailer.setUrlMail(urlMail);
            /* Procesamos y enviamos el mail */
            mailer.process(bean);
//            mailer.doAudit();
        }catch(Exception e){
            log.warn(e);
        }
    }
    
    public void PrepareMail(String actionCase, Object bean){
        Class auxClass=null;
        try{
            /* Comprobamos si el sistema de mails esta activo*/
            ResourceBundle apps = ResourceBundle.getBundle("MailConfiguration");
            String active = apps.getString(MailConstants.ACTIVATE_SYSTEM);
            if(!active.equals("yes")) {
                log.info("Sistema de alertas desactivado (ver fichero properties -> MailConfiguration)");
                return;
            }   
            /* Obtenemos el nombre de la clase que debe procesar y enviar
             * el mail*/
            actionCase = JavaNameFormatter.toJavaName(actionCase, true);
            auxClass = Class.forName(MailConstants.ACTION_CASE_PREFIX+actionCase);
            /* Instanciamos la clase */
            MailAbstract mailer = (MailAbstract)auxClass.newInstance();
            /* Procesamos y enviamos el mail */
            mailer.process(bean); 
//            mailer.doAudit();
        }catch(Exception e){
            log.warn(e);
        }
    }

    public static Locale getLocale() {
        return locale;
    }

    public static void setLocale(Locale locale) {
        MailController.locale = locale;
    }
    

}
