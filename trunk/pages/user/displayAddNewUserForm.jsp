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
					<jsp:param name="HeaderKey" value="groupheaders.addusuario" />
				</jsp:include></td>
			</tr>
			<tr align="center">
				<td class="GroupContent">
				<table class="FormTable" cellpadding=0 cellspacing=0>
					<tr>
						<td class="FormFieldLeft"><jim:message
							key="usuario.email" /> *</td>
						<td class="FormFieldRight"><nested:text property="email"
							styleClass="Form" /></td>
					</tr>
					<tr>
						<td class="FormSeparator"></td>
						<td class="BlankSeparator"></td>
					</tr>
					<tr>
						<td class="FormFieldLeft"><jim:message
							key="usuario.password" /> *</td>
						<td class="FormFieldRight"><nested:password
							property="password" styleClass="Form" /></td>
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
					
					<tr>
						<td class="FormFieldLeft"><nested:message key="personal" /></td>
						<td class="FormFieldRight">
						<nested-jim:TagSelect property="usuariocode"  >
							<nested-jim:TagOptionsCollection name="_selec_personal" label="_descripcion" value="personalcode" />
						</nested-jim:TagSelect>
						</td>
					</tr>
		
					<tr>
						<td class="FormSeparator"></td>
						<td class="BlankSeparator"></td>
					</tr>

					<!-- tr>
						<td class="FormFieldLeft"><jim:message key="usuario.idioma" /></td>
						<td class="FormFieldRight"><nested:select
							property="language.languagecode" styleClass="Form">
							<html:options collection="idiomas" property="languagecode"
								labelProperty="language" />
						</nested:select></td>
					</tr-->
				</table>
				</td>
			</tr>
		</table>
	</nested:nest>
</html:form>