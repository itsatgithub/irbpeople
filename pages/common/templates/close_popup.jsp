<%@ include file="/pages/common/templates/global.jsp" %>

<html>
<head>
<link rel=stylesheet HREF="<%=JspUtils.getProjectPath(request)%>/common/eimStyles.css" type="text/css">
</head>
<%

String code=(String) request.getAttribute("newCode");
String newTitle=(String) request.getAttribute("newTitle");
String onLoadString="";
if(code!=null && newTitle!=null) {
	onLoadString = "window.opener.addOption('"+code+"','"+newTitle+"');";
}
onLoadString+="window.opener.document.forms[0].submit();";
onLoadString+="window.close();";

%>

<body onload="<%=onLoadString%>">



Procesando....





</body>
</html>