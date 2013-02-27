<%@ include file="/pages/common/templates/global.jsp" %>
<%@page import="utils.jsp.JspUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%
	String param_caption= request.getParameter("CaptionKey");
	String param_folder= request.getParameter("PrefixKey");
	String param_function= request.getParameter("FunctionKey");
	String param_folder_style= param_folder+"item";
	String icon = JspUtils.getProjectPath(request)+"/images/gui/"+param_folder+".gif";
	
%>

<table border="0" cellpadding="0" cellspacing="0" class="actiontrayout"
	style="position:relative;top:0;left:0;">
	<tr height="25px">
		<td width="5px"><img
			src="<%=JspUtils.getProjectPath(request)%>/images/gui/<%=param_folder%>border-left.gif" height="25px"
			width="5px" /></td>
		<td background="<%=JspUtils.getProjectPath(request)%>/images/gui/<%=param_folder%>bg.gif"><img
			src="<%= icon %>" width="16" height="16" align="absmiddle"
			onclick="<%=param_function %>" />&nbsp;</td>			
		<td style="{white-space: nowrap}" class="<%=param_folder_style%>"
			background="<%=JspUtils.getProjectPath(request)%>/images/gui/<%=param_folder%>bg.gif"
			onmouseout="this.className='actiontrayout <%=param_folder_style %>';"
			onmouseover="this.className='actiontrayover <%=param_folder_style %>';"
			onclick="if(checkChanged()) <%=param_function %>"><%=param_caption%></td>
		<td width="6px"><img
			src="<%=JspUtils.getProjectPath(request)%>/images/gui/<%=param_folder%>border-right.gif" height="25px"
			width="6px" /></TD>
	</tr>

</table>

