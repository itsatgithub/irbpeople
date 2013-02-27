<%@ include file="../common/templates/global.jsp"%>

<logic:notPresent name="org.apache.struts.action.MESSAGE"
	scope="application">
	<font color="red"> ERROR: Application resources not loaded --
	check servlet container logs for error messages. </font>
</logic:notPresent>

<html:form action="<%=JspUtils.getCurrentAction(request)%>">
	<html:hidden property="id" />
	<table>
		<tr>
			<td colspan=2><html:errors /></td>
		</tr>
	</table>
	<table cellpadding=0 cellspacing=0>
		<nested:nest property="usuarioForm">
			<tr>
				<td><jsp:include page="/pages/common/templates/groupHeader.jsp">
					<jsp:param name="HeaderKey" value="groupheaders.changeusuario" />
				</jsp:include></td>
			</tr>
			<tr align="center">
				<td class="GroupContent">
				<table class="FormTable" cellpadding=0 cellspacing=0>
					<tr>
						<td class="FormFieldLeft"><nested:message
							key="usuario.username" /></td>
						<td class="FormFieldRight"><nested:write property="username"/><nested:hidden property="usuariocode" /></td>
					</tr>
					<tr>
						<td class="FormSeparator"></td>
						<td class="BlankSeparator"></td>
					</tr>
					<tr>
						<td class="FormFieldLeft"><nested:message key="usuario.role" /></td>
						<td class="FormFieldRight"><nested:select property="roles"
							multiple="true" styleClass="Form">
							<html:options collection="roles" property="entitycode"
								labelProperty="_descripcion" />
						</nested:select></td>
					</tr>
					<tr>
						<td class="FormSeparator"></td>
						<td class="BlankSeparator"></td>
					</tr>
					
					
					<!-- tr>
						<td class="FormFieldLeft"><nested:message
							key="usuario.idioma" /></td>
						<td class="FormFieldRight"><nested:select
							property="language.languagecode" styleClass="Form">
							<html:options collection="idiomas" property="languagecode"
								labelProperty="language" />
						</nested:select></td>
					</tr-->
				</table>
				</td>
			</tr>
		</nested:nest>
	</table>
</html:form>