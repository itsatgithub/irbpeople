<%@ include file="/pages/common/templates/global.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String param_caption = request.getParameter("CaptionKey");
	String param_id = request.getParameter("IDKey");
	String param_function = request.getParameter("FunctionKey");
%>
<table height="23" border=0 cellpadding=0 cellspacing=0
	style="position:relative;top:0;left:0;"
	onmousedown="this.style.top=1;this.style.left=1;"
	onmouseup="this.style.top=0;this.style.left=0;"
	onmouseout="this.style.top=0;this.style.left=0;">
	<tr>
		<td width=6px
			style="background-image:url(<%=JspUtils.getProjectPath(request)%>/images/gui/button-left.gif)">&nbsp;</td>
		<td valign="top"><input
			style="border:0px;height:23;background-image:url(<%=JspUtils.getProjectPath(request)%>/images/gui/button-center.gif);background-repeat:repeat-x"
			type="button" id="Button_<%=param_id %>" name="Button_<%=param_id %>"
			value="<%=param_caption%>" onClick="<%=param_function%>">
		</td>
		<td width=6px
			style="background-image:url(<%=JspUtils.getProjectPath(request)%>/images/gui/button-right.gif)">&nbsp;</td>
	</tr>
</table>


