<%@ include file="/pages/common/templates/global.jsp" %>
<%
String text;
%>
<table cellpadding=0 cellspacing=0>
	<tr>
		<th class="GroupHeader" width="100%">
		<%if(request.getParameter("HeaderKey")!=null){%>
			<jim:message key="<%=request.getParameter("HeaderKey") %>" />
		<%} else { %>
			<%= request.getParameter("HeaderString")%>
		<%} %>
		</th>
		<td width="5px" class="GroupCorner"><img
			src="<%=JspUtils.getProjectPath(request)%>/images/gui/header-corner-top-right.gif"></td>
	</tr>
</table>
