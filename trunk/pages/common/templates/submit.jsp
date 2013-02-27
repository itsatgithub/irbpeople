<%@ include file="/pages/common/templates/global.jsp" %>

<%  if(UserUtils.isRRHH(request)) {  
	// Variables para el include del botón
	String param_id;
	String param_caption;
	String param_function;
	
    Menu menu=null;
    ActionMapping am = (ActionMapping)request.getAttribute("org.apache.struts.action.mapping.instance");
    
	String commandName = NavigationFunctions.getCommandName(am);
	String actionName = NavigationFunctions.getActionName(am);
	
    String viewId      	= "eim";
    
    SiteOrg siteOrg = (SiteOrg)request.getSession().getAttribute("siteorg");
    String sectorName = null;
    boolean considerOtherSectors = true;
    if(!commandName.equalsIgnoreCase("_popup"))
    	menu = siteOrg.factoryZone("submit", commandName, actionName, sectorName, considerOtherSectors, pageContext.getRequest(), viewId);
    
if(menu!=null && !menu.isEmpty()) {
    MenuItem[] items = menu.getItems();
    MenuItem item = null;
    if(items.length>0)
    {
%>


<table border="0" cellspacing="0" cellpadding="2">
<tr>
<%   
    }
    for(int i=0; i<items.length; i++) {
      item = items[i];
      // Los botones aparecen uno al lado del otro, tal como està hecho ahora.       
      String multi = item.getParameter("multi");
      if(multi == null) multi = "false";
      String submitFunctionCall = (multi.equals("true") ? "submitMultipleAction" : "submitAction") + "('" + item.getLink() +"',document.forms[0],false,null);"; 


      String popup = siteOrg.actionGetParam("popup", commandName, actionName, sectorName, considerOtherSectors);
      if(popup == null) popup = "false";
      
      submitFunctionCall += (popup.equals("true") ? "window.close();" : ""); 

%>		
        <td height="25">  
        
        <%
        	param_id = String.valueOf(i);
        	param_caption = item.getCaptionButton();
        	param_function = submitFunctionCall;
        %>
        
        <jsp:include page="/pages/common/templates/submitButton.jsp" flush="true">
			<jsp:param name="CaptionKey" value="<%=item.getCaptionButton()%>" />
			<jsp:param name="IDKey" value="<%=i%>" />
			<jsp:param name="FunctionKey" value="<%=submitFunctionCall %>" />
		</jsp:include>
		
        </td>
    
<%  } 
    if(items.length>0)
    {
%>
</tr>
</table> 
 
<%
    }
  }
}
%>
