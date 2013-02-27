<%@ include file="/pages/common/templates/global.jsp" %>


<%

Menu menu = null;
Menu operationMenu = null;

SiteOrg siteOrg = (SiteOrg)request.getSession().getAttribute("siteorg");

ActionMapping am = (ActionMapping)request.getAttribute("org.apache.struts.action.mapping.instance");

String commandName = NavigationFunctions.getCommandName(am);
String actionName = NavigationFunctions.getActionName(am);
String viewId      	= "eim";

String sectorName = null;
boolean considerOtherSectors = true;      

// Go y operation en la misma columna a la derecha

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
	<tr>		
		<td class="TemplateCenter" width="33%" align="<%=utils.jsp.Constants.generalTable_align %>">
			
			<jsp:include page="/pages/common/templates/linksGroup.jsp" flush="true">
				<jsp:param name="GroupName" value="group1" />
			</jsp:include>
								
		</td>
		<td class="TemplateCenter" width="33%" align="<%=utils.jsp.Constants.generalTable_align %>">
			
			<jsp:include page="/pages/common/templates/linksGroup.jsp" flush="true">
				<jsp:param name="GroupName" value="group2" />
			</jsp:include>
								
		</td>
		<td class="TemplateCenter" width="33%" align="<%=utils.jsp.Constants.generalTable_align %>">
			<jsp:include page="/pages/common/templates/linksGroup.jsp" flush="true">
				<jsp:param name="GroupName" value="group3" />
			</jsp:include>
		</td>	
	</tr>
	<tr><td class="linkGroupSeparator"></td></tr>
	<tr>		
		<td class="TemplateCenter" width="33%" align="<%=utils.jsp.Constants.generalTable_align %>">
			
			<jsp:include page="/pages/common/templates/linksGroup.jsp" flush="true">
				<jsp:param name="GroupName" value="group4" />
			</jsp:include>
								
		</td>
		<td class="TemplateCenter" width="33%" align="<%=utils.jsp.Constants.generalTable_align %>">
			
			<jsp:include page="/pages/common/templates/linksGroup.jsp" flush="true">
				<jsp:param name="GroupName" value="group5" />
			</jsp:include>
								
		</td>
		<td class="TemplateCenter" width="33%" align="<%=utils.jsp.Constants.generalTable_align %>">
			<jsp:include page="/pages/common/templates/linksGroup.jsp" flush="true">
				<jsp:param name="GroupName" value="group6" />
			</jsp:include>
		</td>	
	</tr>
</table>
<form action="<%=JspUtils.getCurrentAction(request)%>"></form>