package utils.session;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import presentation.actions.login.DoLogout;

import utils.userUtils.UserUtils;

import bussineslogic.controlers.UseCaseFacade;
import bussineslogic.objects.Usuario;

public class IRBSessionListener implements HttpSessionListener {

    private static org.apache.log4j.Category log = org.apache.log4j.Logger.getLogger(HttpSessionListener.class);
    

    public void sessionDestroyed(HttpSessionEvent event) {
        
        if(event.getSession().getAttribute("currentUser") != null)
        {
            log.info("Session destroyed: " + ((Usuario)event.getSession().getAttribute("currentUser")).getPersonal().getUsername());
        
            UseCaseFacade.HacerLogout((Usuario)event.getSession().getAttribute("currentUser"), "", "Logout - Fin de sesión");
        }

    }

    public void sessionCreated(HttpSessionEvent arg0) { 
    }

}
