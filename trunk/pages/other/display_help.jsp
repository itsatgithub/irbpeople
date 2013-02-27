<%@ include file="/pages/common/templates/global.jsp" %>
<style type="text/css">
<!--
.Estilo1 {
	font-size: 24px;
	font-weight: bold;
}
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
}
-->
</style>

<table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td height="36" background="<%=JspUtils.getProjectPath(request)%>/images/gui/help_banner.jpg">&nbsp;</td>
  </tr>
</table>
<table width="95%"  border="0" align="center">
  <tr>
    <td><span class="Estilo1">IRB People - Help</span></td>
  </tr>
  <tr><td>
	  <jsp:include flush="true" page="<%="/pages/"+request.getParameter("helpPath") %>"/>
  </td></tr>
</table>



