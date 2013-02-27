<%@ include file="/pages/common/templates/global.jsp" %>

<html>
<head>
<link rel=stylesheet HREF="<%=JspUtils.getProjectPath(request)%>/common/eimStyles.css" type="text/css">
	<title><jim:message key="welcome.title" /></title>
	<%@include file="./menuInHeader.jsp"%>
	
	<link rel="Shortcut Icon" href="../images/gui/favicon.ico">
	
</head>
<body>
<table>
	<tr>
		<td valign="top" width="200px" >
			<table style="border:1px solid black"><tr><td><tiles:insert attribute="menu" /></td></tr></table>
		</td>
		<td width="10px">&nbsp;</td>
		<td valign="top"><tiles:insert attribute="center" /></td>
	</tr>
</table>
</body>
</html>