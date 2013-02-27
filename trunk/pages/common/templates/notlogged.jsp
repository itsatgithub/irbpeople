<%@ include file="/pages/common/templates/global.jsp" %>

<html>
<head>
<link rel=stylesheet HREF="<%=JspUtils.getProjectPath(request)%>/common/eimStyles.css" type="text/css">
	<title><jim:message key="welcome.title" /></title>
	<html:base />

	<link rel="shortcut icon" href="../images/gui/favicon.ico">
</head>
<body bgcolor="white" onload="if(document.forms[0]['username']) document.forms[0]['username'].focus()">
<table align="center">
	<tr>
		<td><jsp:include flush="true" page="/pages/common/templates/showAuditMessages.jsp"></jsp:include><tiles:insert attribute="center" /></td>
	</tr>
</table>
</body>
</html>