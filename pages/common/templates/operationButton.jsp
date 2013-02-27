<%@ include file="/pages/common/templates/global.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String param_caption = request.getParameter("CaptionKey");
	String param_concept = request.getParameter("ConceptKey");
	String param_function = request.getParameter("FunctionKey");
	String icon = JspUtils.getProjectPath(request)+"/images/CATEGORY/action-type/";

	icon +=param_concept+".gif";
%>
<table height="35" border=0 cellpadding=0 cellspacing=0
	class="actiontrayout" onmouseover="this.className='actiontrayover';"
	onmouseout="this.className='actiontrayout';this.style.top=0;this.style.left=0;"
	style="position:relative;top:0;left:0;"
	onmousedown="this.style.top=1;this.style.left=1;"
	onmouseup="this.style.top=0;this.style.left=0;">
	<tr height=35px>
		<td width=5px><img src="<%=JspUtils.getProjectPath(request)%>/images/gui/action-button-left.gif"
			height=35px width=5px /></td>
		<td background="<%=JspUtils.getProjectPath(request)%>/images/gui/action-button-background.gif"><img
			src="<%= icon %>" width="16" height="16" align="absmiddle"
			style="position:relative;top:0;left:0;"
			onmousedown="this.style.top=1;this.style.left=1;"
			onmouseup="this.style.top=0;this.style.left=0;" /></td>
		<td style="{white-space: nowrap;}"
			background="<%=JspUtils.getProjectPath(request)%>/images/gui/action-button-background.gif"
			onclick="<%=param_function %>">&nbsp;<%=param_caption%></td>
		<td width=5px><img
			src="<%=JspUtils.getProjectPath(request)%>/images/gui/action-button-right.gif" height=35px width=5px /></TD>
	</TR>
</TABLE>
