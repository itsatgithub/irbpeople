package com.justinmind.MailSystem;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import org.hibernate.Session;


import com.justinmind.hibernate.util.HibernateManagementSession;
import com.justinmind.hibernate.util.HibernateManagementSessionFactory;
import com.justinmind.usermanagement.entity.Umuser;


public abstract class MailAbstract {
	
	private static org.apache.log4j.Category log= org.apache.log4j.Logger.getLogger(MailAbstract.class);
	private static ResourceBundle apps = ResourceBundle.getBundle("MailConfiguration"); 
    
    public MailAbstract() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected Locale local = new Locale("es");
    protected String urlMail = "";
    protected Session hs;
    protected Object attachment = null;
    ArrayList<Umuser> auditoryUsers = new ArrayList<Umuser>();
        
    public void process(Object bean){  
    /** Método que se debe implementar en la clase concreta para obtener
     *  la lista de destinatarios y la información necesaria del bean 
     *  para rellenar la plantilla del mail (si es necesario)*/ 
    }
    
    /** Asigna el locale, es necesario para que los mensajes que se 
     *  obtengan se puedan internacionalizar */
    public void setLocale(Locale l){
        local = l;
    }
    
    /** Asigna un link para añadirlo al final del cuerpo del mail */
    public void setUrlMail(String auxUrl){
        urlMail = auxUrl;        
    }
    
    /** Obtiene el mensaje que hay en el bundle usando introspección */
    public String getMessage(){        
        /* Obtenemos el nombre de la clase */
        String actionCase = this.getClass().getName();
        /* Obtenemos el bundle */
        ResourceBundle apps = ResourceBundle.getBundle(MailConstants.BUNDLE_NAME,local);
        /* Calculamos la key quitando el prefijo de la clase (package) */
        actionCase = utils.Utiljim.replace(actionCase, MailConstants.CLASS_PREFIX,"");
        /* Obtenemos el mensaje usando la key obtenida*/                                
        return apps.getString(actionCase);       
    }
    
    /** Obtiene el subject que hay en el bundle usando introspección */
    public String getSubject(){        
        /* Obtenemos el nombre de la clase */
        String actionCase = this.getClass().getName();
        /* Obtenemos el bundle */
        ResourceBundle apps = ResourceBundle.getBundle(MailConstants.BUNDLE_NAME,local);
        /* Calculamos la key quitando el prefijo de la clase (package) */
        actionCase = utils.Utiljim.replace(actionCase, MailConstants.CLASS_PREFIX,"");
                               
        /* Obtenemos el mensaje usando la key obtenida y el sufijo de subject*/
        return apps.getString(actionCase+MailConstants.SUBJECT_SUFFIX);
    }
    
    /** Envía correo electrónico con el mensaje a los destinatarios */       
    public void Send(String message, String recipients[ ], String subject){
        try{
            if(attachment==null){            
            MailSender.SendMail(recipients, subject, message,
                    apps.getString("systemaccount"));
            }
            else{
                MailSender.SendMailWithAttachment(recipients, subject, message,
                        apps.getString("systemaccount"), (File)this.getAttachment());                
            }
        }catch(Exception e){
            log.warn(e);            
        }              
    }

//    public void doAudit()
//    {
//        Mensajeusuario mensaje = new Mensajeusuario();
//        hms = HibernateManagementSessionFactory.getInstance().openHibernateManagementSession();
//        hms.beginTransaction();
//        String actionCase = Utiljim.replace(this.getClass().getName(), MailConstants.ACTION_CASE_PREFIX,"");        
//        for(int i=0;i<auditoryUsers.size();i++){
//            ResourceBundle apps = ResourceBundle.getBundle(MailConstants.BUNDLE_NAME,new Locale(auditoryUsers.get(i).getLanguage().getLanguagecode()));
//            mensaje.setUsercode(auditoryUsers.get(i).getUsername());
//            mensaje.setCommand("MailSystem");
//            mensaje.setAction(actionCase);
//            mensaje.setTimestamp(new Timestamp(System.currentTimeMillis()));            
//            mensaje.setMensaje(actionCase+"-"+apps.getString(MailConstants.AUDIT_MESSAGE)+" "+ mensaje.getUsercode()+".");           
//            try {                
//                MensajeusuariosHome.singleton().create(hms, mensaje);                
//            } catch (Exception e) {
//                log.warn(e);
//            } 
//        }     
//        hms.commitTransaction();
//        hms.close();
//    }
    
    public Session getHms() {
        return hs;
    }

    public void setHms(Session hs) {
        this.hs = hs;
    }

    public Object getAttachment() {
        return attachment;
    }

    public void setAttachment(Object attachment) {
        this.attachment = attachment;
    }    
}
