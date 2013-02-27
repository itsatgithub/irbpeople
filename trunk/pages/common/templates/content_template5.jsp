<%@ include file="/pages/common/templates/global.jsp" %>

<%
Menu menu = null; 

SiteOrg siteOrg = (SiteOrg)request.getSession().getAttribute("siteorg");

ActionMapping am = (ActionMapping)request.getAttribute("org.apache.struts.action.mapping.instance");

String commandName = NavigationFunctions.getCommandName(am);
String actionName = NavigationFunctions.getActionName(am);
String viewId      	= "eim";

String sectorName = null;
boolean considerOtherSectors = true;      

// Go y operations arriba en dos rows diferentes y con orientacion horizontal

%>

<table border="0" cellspacing="0" cellpadding="0" width="100%" id="popupanchor" name="popupanchor">
	<tr><td><tiles:insert attribute="path" /></td></tr>
	<% menu = siteOrg.factoryZone("folder", commandName, actionName, sectorName, considerOtherSectors, pageContext.getRequest(), viewId);	
	if (menu != null && !menu.isEmpty()){%>
	<tr>
		<td class="TemplateFolder1" ><tiles:insert attribute="folder1" /></td>
	</tr>
	<%} %>
	<% menu = siteOrg.factoryZone("folder2", commandName, actionName, sectorName, considerOtherSectors, pageContext.getRequest(), viewId);	
	if (menu != null && !menu.isEmpty()){%>
	<tr>
		<td class="TemplateFolder2"><tiles:insert attribute="folder2" /></td>
	</tr>
	<%} %>
	<% menu = siteOrg.factoryZone("folder3", commandName, actionName, sectorName, considerOtherSectors, pageContext.getRequest(), viewId);	
	if (menu != null && !menu.isEmpty()){%>
	<tr>
		<td class="TemplateFolder3" ><tiles:insert attribute="folder3" /></td>
	</tr>
	<%} %>
<tr>
	<td width=100%>
	<table width=100% border=0 cellpadding=0 cellspacing=0>
	<tr>
	<td width=3px height=3px><img src="../images/gui/folders-corner-left.gif" /></td>
	<td style="border-top:1px solid #898989; background:#eeeeee" height=3px><img src="../images/gui/pixel.gif" /></td>
	<td width=3px height=3px><img src="../images/gui/folders-corner-right.gif" /></td>
	</tr>
	</table>
	</td>
</tr>	
<tr height=100% bgcolor="#EEEEEE" >
	<td>
	<table width=100% height=100% border=0 cellspacing=0 cellpadding=0>
		<tr>
			<td width=15px style="border-left:1px solid #898989"><img src="../images/gui/pixel.gif" /></td>
    		<td valign=top>
	<table width=100% border=0 cellspacing=0 cellpadding=0 valign=top >    		
	<% menu = siteOrg.factoryZone("go", commandName, actionName, sectorName, considerOtherSectors, pageContext.getRequest(), viewId);	
	if (menu != null && !menu.isEmpty()){%>
	<tr>
		<td class="TemplateGo" align="<%=utils.jsp.Constants.go_align %>"><tiles:insert attribute="go" /></td>
	</tr>
	<%} %>
	<% menu = siteOrg.factoryZone("operation", commandName, actionName, sectorName, considerOtherSectors, pageContext.getRequest(), viewId);	
	if (menu != null && !menu.isEmpty()){%>
	<tr>
		<td class="TemplateOperation" align="<%=utils.jsp.Constants.operation_align %>"><tiles:insert attribute="operation" /></td>
	</tr>
	<% } %>
	<tr>
		<td>
		<table border="0" cellspacing="0" cellpadding="0" width="100%">
			<tr>
				<td class="TemplateCenter" align="<%=utils.jsp.Constants.generalTable_align %>"><jsp:include flush="true" page="/pages/common/templates/showAuditMessages.jsp"></jsp:include><tiles:insert attribute="center" /></td>
			</tr>
		</table>
		</td>
	</tr>
	<% menu = siteOrg.factoryZone("submit", commandName, actionName, sectorName, considerOtherSectors, pageContext.getRequest(), viewId);	
	if (menu != null && !menu.isEmpty()){%>
	<tr>
		<td class="TemplateSubmit" align="<%=utils.jsp.Constants.submit_align %>"><tiles:insert attribute="submit" /></td>
	</tr>
	<% } %>
    </table>
    		</td>
    		<td width=3px background="../images/gui/folders-shadow-right.gif"><img src="../images/gui/pixel.gif" /></td>
</tr>
</table>
</td>
</tr>
<tr>
	<td width=100%>
	<table width=100% border=0 cellpadding=0 cellspacing=0>
	<tr>
	<td width=5px height=4px><img src="../images/gui/columns-left-corner.gif" /></td>
	<td background="../images/gui/columns-bottom.gif" height=4px><img src="../images/gui/pixel.gif" /></td>
	<td width=5px height=4px><img src="../images/gui/columns-right-corner.gif" /></td>
	</tr>
	</table>
	</td>
</tr>
</table>	

