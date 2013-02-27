package com.justinmind.MailSystem;

import java.io.File;
import java.util.HashSet;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailSender {

	private static org.apache.log4j.Category log= org.apache.log4j.Logger.getLogger(MailSender.class);
    
    private static ResourceBundle apps = ResourceBundle.getBundle("MailConfiguration");
	
    public static void SendMail( String recipients[ ], String subject, String message , String from) throws MessagingException
    {       
        SendMailWithAttachment(recipients, subject, message, from, null);
//        try{
//            boolean debug = false;
//    
//             //Set the host smtp address
//            Properties props = new Properties();
//            props.put("mail.smtp.host", com.justinmind.mvc.utils.ConfigurationManager.singleton().getParameterStr("mailsystem","host")); 
//             
//    
//            // create some properties and get the default Session
//            Session session = Session.getInstance(props, new ForcedAuthenticator());
//            //Session session = Session.getInstance(props);
//            session.setDebug(debug);
//    
//            // create a message
//            final Message msg = new MimeMessage(session);
//    
//            // set the from and to address
//            InternetAddress addressFrom = new InternetAddress(from);
//            msg.setFrom(addressFrom);
//    
//            String developmentFlag = com.justinmind.mvc.utils.ConfigurationManager.singleton().getParameterStr("mailsystem","development");
//            if(developmentFlag==null)
//            {
//                throw new Exception("Flag 'development' is mandatory in framework.xml and mailconfiguration.properties.");
//            }
//            String recipientPattern = null;
//            if(developmentFlag.equals("true"))
//            {
//                recipientPattern = com.justinmind.mvc.utils.ConfigurationManager.singleton().getParameterStr("mailsystem","patternDevRecipient");
//            }
//            
//            Set<InternetAddress> emails = new HashSet<InternetAddress>();
//            for (int i = 0; i < recipients.length; i++)
//            {
//                if(recipientPattern ==null || recipients[i].matches(recipientPattern))
//                {
//                    emails.add( new InternetAddress(recipients[i]) );
//                }
//            }
//            
//            if(emails.size()==0)
//            {
//                throw new Exception("Tried to send an email without recipients.");
//            }
//
//            InternetAddress[] toAddress = null;
//            msg.setRecipients(Message.RecipientType.TO, emails.toArray(toAddress));
//           
//    
//            // Optional : You can also set your custom headers in the Email if you Want
//            msg.addHeader("MyHeaderName", "myHeaderValue");
//    
//            // Setting the Subject and Content Type
//            msg.setSubject(subject);
//            msg.setContent(message, "text/plain");
//    
//            new Thread( ) {
//    
//                public void run( ) {
//    
//                    try {
//    
//                        Transport.send(msg);
//    
//                    } catch (MessagingException e) {
//    
//                        throw new IllegalArgumentException(
//    
//                        "Transport.send( ) threw: " + e.toString( ));
//    
//                    }
//    
//                }
//    
//            }.start( );
//        } catch (Exception e) {
//            log.warn(e);
//        }

    }
    
    public static void SendMailWithAttachment( String recipients[ ], String subject, String message , String from, File file) throws MessagingException
    {       
        try{
            boolean debug = true;
    
             //Set the host smtp address
            Properties props = new Properties();
            props.put("mail.smtp.host", apps.getString("host")); 
            
            String port=apps.getString("port");
            if(port !=null)
            {
                props.setProperty("mail.smtp.port", port);
            }
            
            String sslActivated=apps.getString("ssl_activated");
            
            if(sslActivated!=null && sslActivated.equalsIgnoreCase("true")){
                if(port == null) port="465";
                props.setProperty("mail.smtp.port", port);
            	props.setProperty("mail.smtp.auth", Boolean.TRUE.toString());
            	props.setProperty("mail.smtp.starttls.enable", Boolean.TRUE.toString());
            	props.put("mail.smtp.socketFactory.port", port);
            	props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            	props.put("mail.smtp.socketFactory.fallback", "false");
            }
            	    
            
            System.setProperty("mail.mime.charset","iso-8859-1");
            
            // create some properties and get the default Session
            Session session = Session.getInstance(props, new ForcedAuthenticator());
            //Session session = Session.getInstance(props);
            session.setDebug(debug);
    
            // create a message
            final Message msg = new MimeMessage(session);
    
            // set the from and to address
            InternetAddress addressFrom = new InternetAddress(from);
            msg.setFrom(addressFrom);
    
            String developmentFlag = apps.getString("development");
            if(developmentFlag==null)
            {
                throw new Exception("Flag 'development' is mandatory in framework.xml and mailconfiguration.properties.");
            }
            String recipientPattern = null;
            if(developmentFlag.equals("true") || developmentFlag.equals("yes"))
            {
                recipientPattern = apps.getString("patternDevRecipients");
            }
            
            Set<InternetAddress> emails = new HashSet<InternetAddress>();
            for (int i = 0; i < recipients.length; i++)
            {
                if(recipientPattern==null ||  recipients[i].matches(recipientPattern))
                {
                    emails.add( new InternetAddress(recipients[i]) );
                }
                else
                {
                    log.debug("Removed recipient: " + recipients[i]);
                }
            }
            
            if(emails.size()==0)
            {
                throw new Exception("Tried to send an email without recipients.");
            }

            InternetAddress[] toAddress = new InternetAddress[emails.size()];
            msg.setRecipients(Message.RecipientType.TO, emails.toArray(toAddress));
                   
            // Setting the Subject and Content Type
            msg.setSubject(subject);
            
            if(file != null)
            {
    //          Create the message part 
                
                BodyPart messageBodyPart = new MimeBodyPart();
    
    //          Fill the message
                messageBodyPart.setContent(message, "text/plain");
                
    
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(messageBodyPart);
    
    //             Part two is attachment
                messageBodyPart = new MimeBodyPart();
    //          filename must be a full path formatted filename, for example:
    //          filename = "C:\\Errores.doc";
                DataSource source = new FileDataSource(file);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(file.getName());            
                multipart.addBodyPart(messageBodyPart);
    
    //             Put parts in message
                msg.setContent(multipart);
            }
            else
            {
                msg.setContent(message, "text/plain");
            }
            
            new Thread( ) {
    
                public void run( ) {
    
                    try {
                        
                        Transport.send(msg);
    
                    } catch (Exception e) {
                        throw new IllegalArgumentException(
                        "Transport.send( ) threw: " + e.toString( ));
    
                    }
    
                }
    
            }.start( );
        } catch (Exception e) {
            log.warn(e);
        }

    }
    
    
    static class ForcedAuthenticator extends Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
          return new PasswordAuthentication(
                  apps.getString("systemaccount"),
                  apps.getString("password"));
        }
      } 
}
