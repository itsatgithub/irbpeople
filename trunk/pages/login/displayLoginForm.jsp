<%@ include file="/pages/common/templates/global.jsp" %>



<script type="text/javascript">

	function enterKey(aEvent)
	{
		var myEvent = window.event ? window.event : aEvent;
		
		if(myEvent.keyCode==13)
		{
			document.forms[0].action='<%= JspUtils.getProjectPath(request) %>/login/doLogin.do';
			document.forms[0].submit();
		}
		
	}

</script>

<logic:notPresent name="org.apache.struts.action.MESSAGE"
	scope="application">
	<font color="red"> ERROR: Application resources not loaded --
	check servlet container logs for error messages. </font>
</logic:notPresent>

<html:form action="/login/displayLoginForm.do">

<div style="height:150px;">&nbsp;</div>

<table border=0 cellspacing=0 cellpadding=0 onkeypress="enterKey(event)" align=center>
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
		<table border=0 cellspacing=0 cellpadding=0>
			<tr>
				<td>&nbsp;</td>
				<%
					Object old_id = request.getSession().getAttribute("old_id");
					if(old_id != null)
					{
					    request.getSession().removeAttribute("old_id");
				%>
					<td align="right"><jim:message key="usuario.username"/>:&nbsp;</td><td><html:text property="username" styleClass="Form" value="<%= (String)old_id %>" /></td>
				<%
					} else {
  				%>
					<td align="right"><jim:message key="usuario.username"/>:&nbsp;</td><td><html:text property="username" styleClass="Form"/></td>
				<%
					}
				%>
  				<td>&nbsp;</td>
	      	</tr>
	      	<tr>
	      		<td colspan=4>&nbsp;</td>
	     	</tr>        	
	      	<tr>
	      		<td>&nbsp;</td>
	      		<td colspan=2 align="right"><jim:message key="usuario.password"/>:&nbsp;<html:password property="password" styleClass="Form"/></td>
	      		<td>&nbsp;</td>
	      	</tr>
	      	<tr>
	      		<td colspan=4>&nbsp;</td>
	     	</tr>
	      	<tr>
	      		<td>&nbsp;</td>
	      		<td>
				<%
					String actionCall = "document.forms[0].action='" + JspUtils.getProjectPath(request) + "/login/doLogin.do'; document.forms[0].submit();";
					String actionCall2 = "document.forms[0].action='" + JspUtils.getProjectPath(request) + "/user/displayAddUser.do'; document.forms[0].submit();";
				%>
				<jsp:include page="/pages/common/templates/goButton.jsp" flush="true">
					<jsp:param name="CaptionKey" value="Log in" />
					<jsp:param name="ConceptKey" value="login" />
					<jsp:param name="FunctionKey" value="<%= actionCall %>" />
				</jsp:include>	
				</td>
				<%if (JspUtils.isObtentionPhaseActive(request)){ %>
				<td style="padding-left:10px">
				<jsp:include page="/pages/common/templates/goButton.jsp" flush="true">
					<jsp:param name="CaptionKey" value="New user" />
					<jsp:param name="ConceptKey" value="new" />
					<jsp:param name="FunctionKey" value="<%= actionCall2 %>" />
				</jsp:include>          			
	          	</td>
	          	<%}%>
	          	<td>&nbsp;</td>
	      	</tr>
	      	<%--    		
      		<tr>
      			<td>&nbsp;</td>
				<td colspan=2 style="font-size:10" ><a href="#" onclick="javascript:window.open('<%=JspUtils.getProjectPath(request)%>/other/action_generate_new_password.do',null,'height=250,width=400,status=no,toolbar=no,menubar=no,location=no');return false;"><jim:message key="login.generatePassword"/></a></td>
				<td>&nbsp;</td>
      		</tr>
      		--%>
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

</html:form>

