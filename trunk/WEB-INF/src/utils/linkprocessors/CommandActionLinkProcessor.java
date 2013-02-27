package utils.linkprocessors;

 

import javax.servlet.*;

 

import com.justinmind.util.linkprocess.LinkPlaces;

import com.justinmind.util.linkprocess.LinkProcessorInterface;

/**
 * Implementation of the LinkProcessorInterface for the current application. This class is used by SiteOrgManager.
 * @author Automatika - JustInMind SL
 */
public class CommandActionLinkProcessor implements LinkProcessorInterface {

            private int linkType = 0;

            private ServletRequest sR = null;

            private String viewId = null;

 

/**
 * Comentario de constructor LinkProcessor.
 */
public CommandActionLinkProcessor() {

            super();

}

/* (non-Javadoc)
 * @see com.justinmind.util.linkprocess.LinkProcessorInterface#processLink(java.lang.String, java.lang.String, java.lang.String)
 */
public String processLink(String command, String action, String params) {

 

    String link = null;

    String postfix = ".do";

    

    // - Puede ocurrir que sR sea nulo en el momento de la pregarga de las

    //   Caches.

        switch (linkType) {

            case LinkPlaces.ZONE :

                link = "../"+command +"/"+action;

                break;

            case LinkPlaces.MENU_BAR_GENERIC :

                link = action + postfix;

                break;

            case LinkPlaces.MENU_BAR_ALLWEBMENUS :

                link = "\"" + action + postfix + "\"";

                break;

            case LinkPlaces.MENU_BAR_EVC_JIMMENU :

                link = action + postfix;

                break;       

            case LinkPlaces.MENU_BAR_EVC_JSCOOKMENU :

                link = "./"+command +"/"+ action + postfix;

                break;      
                
            case LinkPlaces.PATH :

                link = "/"+command +"/"+action+postfix;

                break;                

            default :

                link = "";

        }

    return link;

}

/* (non-Javadoc)
 * @see com.justinmind.util.linkprocess.LinkProcessorInterface#setLinkParameters(int, javax.servlet.ServletRequest, java.lang.String)
 */
public void setLinkParameters(int linkType, ServletRequest sR, String viewId) {

 

    this.linkType = linkType;

    this.sR = sR;

    this.viewId = viewId;   

}

/* (non-Javadoc)
 * @see com.justinmind.util.linkprocess.LinkProcessorInterface#getLinkType()
 */
public int getLinkType() {

    return linkType;

}

 

}

