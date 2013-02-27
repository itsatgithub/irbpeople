<%@ include file="../common/templates/global.jsp"%>

<script type="text/javascript">

	function enterKey(aEvent)
	{
		var myEvent = window.event ? window.event : aEvent;
		
		if(myEvent.keyCode==13)
		{
			document.forms[0].submit();
		}
		
	}

</script>

<logic:notPresent name="org.apache.struts.action.MESSAGE"
	scope="application">
	<font color="red"> ERROR: Application resources not loaded --
	check servlet container logs for error messages. </font>
</logic:notPresent>

<html:form action="<%=JspUtils.getCurrentAction(request)%>">
	<html:hidden property="id" />

	<nested:nest property="usuarioForm">
		
<div style="height:150px;">&nbsp;</div>

<table border=0 cellspacing=0 cellpadding=0 onkeypress="enterKey(event)">
<tr>
<td>
<table border=0 cellspacing=0 cellpadding=0 align=center style="border-bottom:1px solid #898989; border-left:1px solid #898989;" bgcolor=#ededed>
	<tr height=4px>
		<td colspan=2 style="border-top:1px solid #898989;" width=100%><img src="<%=JspUtils.getProjectPath(request)%>/images/gui/pixel.gif"></td>
		<td style="background-color:#ffffff"><img src="<%=JspUtils.getProjectPath(request)%>/images/gui/login-corner.gif"></td>
	</tr>
   	<tr>
   		<td colspan=3 style="border-right:1px solid #898989;">&nbsp;</td>
   	</tr>
	<tr>
		<td>&nbsp;&nbsp;&nbsp;<img src="<%=JspUtils.getProjectPath(request)%>/images/gui/IRBCN_XX_ANG.gif"/>&nbsp;&nbsp;&nbsp;</td>
		<td>
			<table cellpadding=0 cellspacing=0>
				<tr>
					<td>&nbsp;</td>
					<td align="right"><jim:message key="usuario.email"/>:</td>
					<td class="FormFieldRight"><nested:text property="email" /></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan=4>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td align="right"><jim:message key="usuario.confirmationEmail"/>:</td>
					<td class="FormFieldRight"><nested:text property="email2" /></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan=4>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td align="right"><jim:message key="usuario.password"/>:</td>
					<td class="FormFieldRight"><nested:password property="password" /></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan=4>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td align="right"><jim:message key="usuario.confirmationPassword"/>:</td>
					<td class="FormFieldRight"><nested:password property="password2" /></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan=4>&nbsp;</td>
				</tr>
				<tr>
<%
	String actionCall = "document.forms[0].action='" + JspUtils.getProjectPath(request) + "/user/doAddUser.do'; document.forms[0].submit(); return false";
	String actionCall2= "document.forms[0].action='" + JspUtils.getProjectPath(request) + "/user/cancelAddUser.do'; document.forms[0].submit(); return false";
%>				
					<td>&nbsp;</td>
					<td align="right">
						<jsp:include page="/pages/common/templates/goButton.jsp" flush="true">
							<jsp:param name="CaptionKey" value="Create user" />
							<jsp:param name="ConceptKey" value="new" />
							<jsp:param name="FunctionKey" value="<%= actionCall %>" />
						</jsp:include>
					</td>
					<td style="padding-left:10px">
						<jsp:include page="/pages/common/templates/goButton.jsp" flush="true">
							<jsp:param name="CaptionKey" value="Cancel" />
							<jsp:param name="ConceptKey" value="cancel" />
							<jsp:param name="FunctionKey" value="<%= actionCall2 %>" />
						</jsp:include>
					</td>
					<td>&nbsp;</td>
				</tr>
			</table>
    	</td>
    	<td style="border-right:1px solid #898989;">&nbsp;</td>
    </tr>
    <tr>
    	<td colspan=3 style="border-right:1px solid #898989;">&nbsp;</td>
   	</tr>
</table>
</td>
</tr>
<tr>
<td align=right style="font-family:verdana; font-weight:bold; font-size:10;">
by JustInMind
</td>
</tr>
</table> 		
<html:errors/>		
	
	</nested:nest>
</html:form>