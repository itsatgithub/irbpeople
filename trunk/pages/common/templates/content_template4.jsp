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

// No Go ni operation

%>

<table border="0" cellspacing="0" cellpadding="0" width="100%" id="popupanchor" name="popupanchor">
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
	<tr><td><tiles:insert attribute="path" /></td></tr>
	<tr>
		<td>
		<table border="0" cellspacing="0" cellpadding="0" width="100%">
			<tr>
				<td class="TemplateCenter" width="100%" align="<%=utils.jsp.Constants.generalTable_align %>"><jsp:include flush="true" page="/pages/common/templates/showAuditMessages.jsp"></jsp:include><tiles:insert attribute="center" /></td>
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
