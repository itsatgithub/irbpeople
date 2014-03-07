<%@ include file="/pages/common/templates/global.jsp" %>
<% 
SiteOrg site=(SiteOrg) request.getSession().getAttribute("siteorg");
ActionMapping am=(ActionMapping) request.getAttribute("org.apache.struts.action.mapping.instance");
Menu menu=site.factoryBreadCrumb(NavigationFunctions.getCommandName(am), NavigationFunctions.getActionName(am),request, null);
%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	
    <tr>
    	<td width=25px><img src="../images/gui/pathIcon.gif" height=15px width=15px/></td>
        <td class=title height="25" align=left>
<%
if(menu!=null){
MenuItem[] items=menu.getItems();
	for(int i=0; i<items.length; ++i){
		MenuItem item=items[i];
		if(i!=0){%><%=" > "%><%}
		
		if(i+1 >= items.length){%><b><%=item.getCaptionTitle()%></b><%}
		if(i+1 < items.length){%><html:link page="<%=item.getLink()%>"><%=item.getCaptionTitle()%></html:link><%}
	}
}
%>
</td></tr></table> 