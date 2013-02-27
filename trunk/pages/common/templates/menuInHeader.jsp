<%@ include file="/pages/common/templates/global.jsp" %>

<script type="text/javascript"><!--
<%    
       SiteOrg siteOrg = (SiteOrg)request.getSession().getAttribute("siteorg");
       String viewId           = null; // "eim";
       String menuName = "default";                       

       boolean hayMenu = false;
       
        // //////////////////////////////////////////////////
        // - Parametro de DEBUG del MENU.
        boolean DEBUG_EVCMENU = false;        
        // Prefijos, sufijos y valores por defecto de los 
        // parametros de condiguracion: 
        // ['icon', 'title', 'url', 'target', 'description'],  // a menu item
        // Configuracion del Menu.

        String[][] MENU_CONFIG = {
                {"<img src=\\\'images/icons/", "null", "\\\'/>", "icon"},
                {"", "", "", "title"},
                {JspUtils.getProjectPath(request)+"/", "null", "", "url"},
                {"", "null", "", "target"},
                {"", "null", "", "description"}};          
        // - Obtencion del menu.
        boolean todoBien = true;
        String menuStr = null;
        MenuJSCook menu = null;

        try {
            // - Obtenemos el menu.
            menu = new MenuJSCook(menuName, siteOrg, pageContext.getRequest(), viewId);
            // - Obtencion de la funcion de javascript en un String.
            menuStr = "\nvar MENU_ITEMS = \n" + menu.jsFunction(MENU_CONFIG) + ";\n";
            // Comprobacion de si hay menu y numero de items.
            hayMenu = menu.hasItems() && (menu.getItemsNumberInFirstLevel()>0);
        } catch (Exception e) {
            if(DEBUG_EVCMENU) org.apache.log4j.Logger.getRootLogger().warn(e);
            todoBien = false;
        }
        // - Si no han habido problemas lo sacamos por pantalla.
        if (todoBien && menuStr != null) {
            %><%= menuStr %>
            <%
        }
        // - Para que el garbage pase rapido ponemos a null el menu.   
        menuStr = null;
        menu = null;        
        // //////////////////////////////////////////////////
 %>
--></script> 
