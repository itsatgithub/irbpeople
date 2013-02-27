<%@ include file="/pages/common/templates/global.jsp" %>

<logic:notPresent name="org.apache.struts.action.MESSAGE"
	scope="application">
	<font color="red"> ERROR: Application resources not loaded --
	check servlet container logs for error messages. </font>
</logic:notPresent>

<html:form action="<%=JspUtils.getCurrentAction(request)%>">
	<table>
		<tr>
			<td colspan=2><html:errors /></td>
		</tr>
	</table>
	<table cellpadding=0 cellspacing=0>
		<tr>
			<td><jsp:include page="/pages/common/templates/groupHeader.jsp">
				<jsp:param name="HeaderKey" value="groupheaders.changepassword" />
			</jsp:include></td>
		</tr>
		<tr align="center">
			<td class="GroupContent">
			<table class="FormTable" cellpadding=0 cellspacing=0>

				<tr>
					<td class="FormFieldLeft"><jim:message
						key="usuario.currentpassword" /> *</td>
					<td class="FormFieldRight"><html:password
						name="changePasswordForm" property="current" styleClass="Form" /></td>
				</tr>
				<tr>
					<td class="FormSeparator"></td>
					<td class="BlankSeparator"></td>
				</tr>
				<tr>
					<td class="FormFieldLeft"><jim:message
						key="usuario.password1" /> *</td>
					<td class="FormFieldRight"><html:password
						name="changePasswordForm" property="password" styleClass="Form" /></td>
				</tr>
				<tr>
					<td class="FormSeparator"></td>
					<td class="BlankSeparator"></td>
				</tr>
				<tr>
					<td class="FormFieldLeft"><nested:message
						key="usuario.password2" /> *</td>
					<td class="FormFieldRight"><html:password
						name="changePasswordForm" property="password2" styleClass="Form" /></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</html:form>
