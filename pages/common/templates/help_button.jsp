<%@page import="utils.jsp.JspUtils"%>
<%@ include file="/pages/common/templates/global.jsp" %>
<%

String helpAction=JspUtils.getProjectPath(request)+"/other/display_help.do";
String helpJspPath=JspUtils.getCurrentHelp(request);

String helpPath=helpAction+"?helpPath="+helpJspPath;

String helpCall="openViewdetailsPopup('"+helpPath+"', 750, 600);";


if(helpJspPath!=null && !helpJspPath.equals("")){%>
	<td>
	<A href="" onclick="<%=helpCall%>; return false;" style="position:relative;top:0;left:0;border:0;">
	<img TITLE="<jim:message key="help.helpButton" />" src="../images/CATEGORY/action-type/help.gif" style="position:relative;top:0;left:0;border:0;" />
	</href></td>
	<td>&nbsp;</td>
<%}%>

