<%@ include file="/pages/common/templates/global.jsp" %>

<html>
<head>

<link rel=stylesheet HREF="<%=JspUtils.getProjectPath(request)%>/common/eimStyles.css" type="text/css">
<link rel="Shortcut Icon" href="../images/gui/favicon.ico">
<title><jim:message key="welcome.title" /></title>
<SCRIPT LANGUAGE="Javascript" SRC="../common/js/PopupWindow.js"></SCRIPT>
<SCRIPT LANGUAGE="Javascript" SRC="../common/spinweb.js"></SCRIPT>
<SCRIPT LANGUAGE="Javascript" SRC="../common/js/CalendarPopup.js"></SCRIPT>
<SCRIPT LANGUAGE="Javascript" SRC="../common/js/popups.js"></SCRIPT>
<SCRIPT LANGUAGE="Javascript" SRC="../common/js/jim_navigation.js"></SCRIPT>

<script type='text/javascript' language='JavaScript' src='../fw/global/jscript/common.js'></script>
<script type='text/javascript' language='JavaScript' src='../fw/global/jscript/environment.js'></script>
<script type='text/javascript' language='JavaScript' src='../fw/global/jscript/utility.js'></script>
<script type='text/javascript' language='JavaScript' src='../fw/global/jscript/formatter.js'></script>
<link rel='stylesheet' href='../fw/def/style/default.css' charset='ISO-8859-1' type='text/css'>
<link rel='stylesheet' href='../common/calendar.css' type='text/css'>
<script type='text/javascript' language='JavaScript' src='../fw/def/jscript/functions.js'></script>
<script type='text/javascript' language='JavaScript' src='../fw/def/jscript/resourcemap.js'></script>
<script type='text/javascript' language='JavaScript' src='../fw/def/jscript/controls.js'></script>
<script type='text/javascript' language='JavaScript' src='../fw/def/jscript/tabset.js'></script>
<script type='text/javascript' language='JavaScript' src='../fw/def/jscript/tree.js'></script>
<script type='text/javascript' language='JavaScript' src='../fw/def/jscript/list.js'></script>
<script type='text/javascript' language='JavaScript' src='../common/js/calendar.js'></script>

<script type="text/javascript"><!--
var myThemeSpinwebBase = '../common/JSCookMenu/ThemeSpinweb/';

--></script>
<script type="text/javascript" src="../common/JSCookMenu/JSCookMenu.js"></script>
<link rel="stylesheet"
	href="../common/JSCookMenu/ThemeSpinweb/theme.css" type="text/css">
<script type="text/javascript"
	src="../common/JSCookMenu/ThemeSpinweb/theme.js"></script>

<%@include file="./menuInHeader.jsp"%>
</head>
<body>
<SCRIPT>var popupWindow = new PopupWindow();</SCRIPT>

<table border="0" cellspacing="0" cellpadding="0" width="100%">
	<tr>
		<td><tiles:insert attribute="loginBar" /></td>
	</tr>
	<%
    // Comprobacion de si hay menu y numero de items.
    if(hayMenu){
    %>
	<tr>
		<td class="TemplateMenuHorizontal" ><tiles:insert attribute="menu" /></td>
	</tr>
	<%} %>
	<tr>
		<td valign="top">
			<tiles:useAttribute id="center" classname="String" name="center" />
			<tiles:insert attribute="content">
				<tiles:put name="center" value="<%=center %>"/>
			</tiles:insert>
		</td>
	</tr>
</table>
</body>
</html>
