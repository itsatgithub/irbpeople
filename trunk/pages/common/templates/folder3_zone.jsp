<%@ include file="/pages/common/templates/global.jsp" %>
<%@page import="utils.jsp.ZoneConstructorUtils"%>
<%@page import="utils.jsp.ZoneConstructorUtils.RefActionButtonData"%>

<table STYLE="table-layout:fixed" width="100%" border="0" cellspacing="0" cellpadding="0" align="left">
<tr>
<td>
<table class="folder1" border="0" cellspacing="0" cellpadding="0" align="left">
<tr>
<script type="text/javascript">
	function checkChanged()
	{
		if( (typeof document.forms[0].elements['tainted'] != 'undefined' ) && document.forms[0].elements['tainted'].value=='true')
			if( confirm('<bean:message key="changes_warning" />') )
			{
				document.forms[0].elements['tainted'].value = 'false';
				return true;
			}
			else
			{
				return false;
			}
		else
			return true;
	}
</script>
<%
ZoneConstructorUtils zoneConstructor=new ZoneConstructorUtils(request, pageContext, "folder3");

if(zoneConstructor.hasItems())
{
	for(ZoneConstructorUtils.RefActionButtonData actionData: zoneConstructor.getActions())
	{      
		String folder= actionData.isCurrent() ? "folder-active-" : "folder-";
        %>
    <td> 
		<jsp:include page="/pages/common/templates/folder1Button.jsp" flush="true">
			<jsp:param name="CaptionKey" value="<%=actionData.getCaption()%>" />
			<jsp:param name="PrefixKey" value="<%=folder%>" />
			<jsp:param name="FunctionKey" value="<%=actionData.getSubmitFunctionCall()%>" />
		</jsp:include>
		
    </td>
<%      
	} // for
} // if

%>


</tr>


</table>
</td>
</tr>
</table>

