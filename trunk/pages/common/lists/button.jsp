<%@ include file="/pages/common/templates/global.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% String type = (String)request.getParameter("type");
   String caption = (String)request.getParameter("type");

   String actionIcon = JspUtils.getProjectPath(request)+"/images/CATEGORY/action-type/" + "go" + ".gif";
   
	if (type.equals("action")){
%>
		<table border=0 cellpadding=0 cellspacing=0 class=actiontrayout onmouseout="this.className='actiontrayout';" onmouseover="this.className='actiontrayover';">
		<tr height=33px>
		<td width=5px><img src="<%=JspUtils.getProjectPath(request)%>/images/gui/action-button-left.gif" height=33px width=5px /></td>
    	<td background="<%=JspUtils.getProjectPath(request)%>/images/gui/action-button-background.gif"><img src="<%= actionIcon %>" width="13" height="13" align="absmiddle" style="position:relative;top:0;left:0;" onmousedown="this.style.top=1;this.style.left=1;" onmouseup="this.style.top=0;this.style.left=0;"/></td>
	  	<td background="<%=JspUtils.getProjectPath(request)%>/images/gui/action-button-background.gif" >&nbsp;<%=caption%></td>
	  	<td width=5px><img src="<%=JspUtils.getProjectPath(request)%>/images/gui/action-button-right.gif" height=33px width=5px /></TD>
		</TR>
		</TABLE>
		<%
	} else if (type.equals("normal")) {
	%>

	<%
	}
	%>

