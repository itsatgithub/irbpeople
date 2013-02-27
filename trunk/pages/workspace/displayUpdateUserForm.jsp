<%@ include file="../common/templates/global.jsp"%>

<logic:notPresent name="org.apache.struts.action.MESSAGE"
	scope="application">
	<font color="red"> ERROR: Application resources not loaded --
	check servlet container logs for error messages. </font>
</logic:notPresent>

<html:form action="<%=JspUtils.getCurrentAction(request)%>">
	<html:hidden property="id" />

	<nested:nest property="usuarioForm">
		<table>
			<tr>
				<td colspan=2><html:errors /></td>
			</tr>
		</table>
		<table cellpadding=0 cellspacing=0>
			<tr>
				<td><jsp:include page="/pages/common/templates/groupHeader.jsp">
					<jsp:param name="HeaderKey" value="groupheaders.updateusuario" />
				</jsp:include></td>
			</tr>
			<tr>
				<td class="GroupContent">
				<table class="FormTable" cellpadding=0 cellspacing=0>
					<tr>
						<td class="FormFieldLeft"><jim:message
							key="usuario.username" /></td>
						<td class="FormFieldRight"><nested:write property="username" />
						<nested:hidden property="usuariocode" /></td>
					</tr>
					<tr>
						<td class="FormSeparator"></td>
						<td class="BlankSeparator"></td>
					</tr>

					
					

					</nested:nest>
					<nested:nest property="changePasswordForm">
					<tr>
					<td class="FormFieldLeft"><jim:message
						key="usuario.currentpassword" /> *</td>
					<td class="FormFieldRight"><nested:password
						property="current" styleClass="Form" /></td>
				</tr>
				<tr>
					<td class="FormSeparator"></td>
					<td class="BlankSeparator"></td>
				</tr>
				<tr>
					<td class="FormFieldLeft"><jim:message
						key="usuario.password1" /></td>
					<td class="FormFieldRight"><nested:password
						property="password" styleClass="Form" /></td>
				</tr>
				<tr>
					<td class="FormSeparator"></td>
					<td class="BlankSeparator"></td>
				</tr>
				<tr>
					<td class="FormFieldLeft"><nested:message
						key="usuario.password2" /></td>
					<td class="FormFieldRight"><nested:password
						property="password2" styleClass="Form" /></td>
				</tr>
				
				<tr>
					<td class="FormSeparator"></td>
					<td class="BlankSeparator"></td>
				</tr>
				
				</nested:nest>
				<nested:nest property="usuarioForm">
				<tr>
						<td class="FormFieldLeft"><jim:message key="usuario.idioma" /></td>
						<td class="FormFieldRight"><nested:select
							property="language.languagecode">
							<html:options collection="idiomas" property="languagecode"
								labelProperty="language" />

						</nested:select></td>
					</tr>
				
				</table>
				</td>
			</tr>
		</table>
	</nested:nest>
</html:form>