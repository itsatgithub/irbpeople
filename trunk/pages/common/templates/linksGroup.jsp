<%@ include file="/pages/common/templates/global.jsp" %>
<%@page import="utils.jsp.ZoneConstructorUtils"%>
<%@page import="utils.jsp.ZoneConstructorUtils.RefActionButtonData"%>

<%

String param_groupname = request.getParameter("GroupName");

ZoneConstructorUtils zoneConstructor=new ZoneConstructorUtils(request, pageContext, param_groupname);
// Grupos de links

		
		

		if(zoneConstructor.hasItems()) {
			%>
			<table border="0" cellspacing="0" cellpadding="0" width="100%" >
				<tr>
					<td>
					<table border="0" cellspacing="0" cellpadding="0">
					<tr>
					<td width=30><img src="<%= request.getParameter("icon_src") %>" width=30px height=30px/></td>
					<TD width=100% valign=bottom>
					<TABLE width=100% BORDER=0 cellspacing=0 cellpadding=0>
						<tr>
							<td width=5px><img src="../images/gui/headertab-left.gif"/></td>
							<td background="../images/gui/headertab-center.gif" class="headertitle" align="center"><%=zoneConstructor.getCaption() %></td>
							<td width=5px><img src="../images/gui/headertab-right.gif"/></td>
						</tr>
					</table>
					</TD>
					</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td class="GroupContent">
					<table height=200px>
			<%
			
			for(ZoneConstructorUtils.RefActionButtonData actionData: zoneConstructor.getActions())
			{
			
		    /*    String aux = item.getLink();
		        String link = aux.replaceFirst("..", "")+".do";*/
				%>  
				<tr>
					<td><a href="#" onclick="<%=actionData.getSubmitFunctionCall()+"return false;" %>"><%=actionData.getCaption()%></a></td>
				</tr>
		    	<tr><td class="linksGRoupSeparator"></td></tr>
				
			<%} // for
		%>
			</table>
					
				
		
</td>
</tr>
</table>
<%
} // if
%>