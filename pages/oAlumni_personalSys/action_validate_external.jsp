<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested"%>
<%@ taglib uri="/tags/jim" prefix="jim"%>
<%@ taglib uri="/tags/nested-jim" prefix="nested-jim"%>
<%@page import="utils.jsp.JspUtils"%>

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
	<nested:nest property="external_alumni_personal_Form">
		<nested:hidden property="alumni_personalcode" />
	</nested:nest>
	<nested:nest property="external_alumni_personal_Form">	
	<table cellpadding=0 cellspacing=0>
		<tr>
			<td>
				<jsp:include page="/pages/common/templates/groupHeader.jsp">
					<jsp:param name="HeaderKey" value="alumni_personal" />
				</jsp:include>
			</td>
		</tr>
		<tr align="center">
			<td class="GroupContent">
				<table class="FormTable" cellpadding=0 cellspacing=0>
					<tr>
						<td align="center">
								<table cellpadding=0 cellspacing=0>
									<tr>
										<td class="FormFieldLeft">
											<jim:message key="alumni_personal.alumni_personalcode" />
										</td>
										<td class="FormFieldRight">
											<nested:write property="alumni_personalcode" />
										</td>
										<td class="FormFieldLeft">
											<jim:message key="alumni_personal.external" />
										</td>
										<td class="FormFieldRight">
											<nested:write property="external" />
										</td>
									</tr>
									<tr>
										<td class="FormFieldLeft">
											<jim:message key="alumni_personal.titles" />
										</td>
										<td class="FormFieldRight" colspan="3">
											<nested:write property="titles" />											
										</td>		
									</tr>
									<tr>
										<td class="FormSeparator"></td>
										<td class="BlankSeparator"></td>
									</tr>
									<tr>
										<td class="FormFieldLeft">
											<jim:message key="alumni_personal.firstname" />
										</td>
										<td class="FormFieldRight">
											<nested:write property="firstname" />
										</td>
										<td class="FormFieldLeft">
											<jim:message key="alumni_personal.surname" />
										</td>
										<td class="FormFieldRight">
											<nested:write property="surname" />
										</td>
									</tr>
									<tr>
										<td class="FormFieldLeft">
											<jim:message key="alumni_personal.irb_surname" />
										</td>
										<td class="FormFieldRight">
											<nested:write property="irb_surname" />
										</td>
										<td class="FormFieldLeft">
											<jim:message key="alumni_personal.gender" />
										</td>
										<td class="FormFieldRight">
											<nested:write property="gender" />
										</td>
									</tr>
									<tr>
										<td class="FormSeparator"></td>
										<td class="BlankSeparator"></td>
									</tr>
									<tr>
										<td class="FormFieldLeft">
											<jim:message key="alumni_personal.nationality" />
										</td>
										<td class="FormFieldRight">
											<nested:write property="nationality" />
										</td>
										<td class="FormFieldLeft">
											<jim:message key="alumni_personal.nationality_2" />
										</td>
										<td class="FormFieldRight">
											<nested:write property="nationality_2" />
										</td>
									</tr>
									<tr>
										<td class="FormSeparator"></td>
										<td class="BlankSeparator"></td>
									</tr>
									<tr>
										<td class="FormFieldLeft">
											<jim:message key="alumni_personal.birth" />
										</td>
										<td class="FormFieldRight">
											<nested:write property="birth" />
										</td>
										<td class="FormFieldLeft">
											<jim:message key="alumni_personal.email" />
										</td>
										<td class="FormFieldRight">
											<nested:write property="email" />
										</td>
									</tr>
									<tr>
										<td class="FormSeparator"></td>
										<td class="BlankSeparator"></td>
									</tr>
									<tr>
										<td class="FormFieldLeft">
											<jim:message key="alumni_personal.url" />
										</td>
										<td class="FormFieldRight">
											<nested:write property="url" />
										</td>
										<td class="FormFieldLeft">
											<jim:message key="alumni_personal.facebook" />
										</td>
										<td class="FormFieldRight">
											<nested:write property="facebook" />
										</td>
									</tr>
									<tr>
										<td class="FormFieldLeft">
											<jim:message key="alumni_personal.linkedin" />
										</td>
										<td class="FormFieldRight">
											<nested:write property="linkedin" />
										</td>
										<td class="FormFieldLeft">
											<jim:message key="alumni_personal.twitter" />
										</td>
										<td class="FormFieldRight">
											<nested:write property="twitter" />
										</td>
									</tr>
									<tr>
										<td class="FormFieldLeft">
											<jim:message key="alumni_personal.keywords" />
										</td>
										<td class="FormFieldRight">
											<nested:write property="keywords" />
										</td>
										<td class="FormFieldLeft">
											<jim:message key="alumni_personal.biography" />
										</td>
										<td class="FormFieldRight">
											<nested:write property="biography" />
										</td>
									</tr>
									<tr>
										<td class="FormFieldLeft">
											<jim:message key="alumni_personal.awards" />
										</td>
										<td class="FormFieldRight">
											<nested:write property="awards" />
										</td>
										<td class="FormFieldLeft">
											<jim:message key="alumni_personal.ORCIDID" />
										</td>
										<td class="FormFieldRight">
											<nested:write property="ORCIDID" />
										</td>
									</tr>
									<tr>
										<td class="FormFieldLeft">
											<jim:message key="alumni_personal.researcherid" />
										</td>
										<td class="FormFieldRight">
											<nested:write property="researcherid" />
										</td>
										<td class="FormFieldLeft">
											<jim:message key="alumni_personal.pubmedid" />
										</td>
										<td class="FormFieldRight">
											<nested:write property="pubmedid" />
										</td>
									</tr>
									<tr>
										<td class="FormSeparator"></td>
										<td class="BlankSeparator"></td>
									</tr>
									<tr>
										<td class="FormFieldLeft">
											<jim:message key="alumni_personal.verified" />
										</td>
										<td class="FormFieldRight">
											<nested:write property="verified" />
										</td>
										<td class="FormFieldLeft">
											<jim:message key="alumni_personal.show_data" />
										</td>
										<td class="FormFieldRight">
											<nested:write property="show_data" />
										</td>
									</tr>
									<tr>
										<td class="FormSeparator"></td>
										<td class="BlankSeparator"></td>
									</tr>
									<tr>
										<td class="FormFieldLeft"><jim:message
												key="alumni_personal.communications" /></td>
										<td class="FormFieldRight" colspan="3">
											<nested:write property="alumni_communications_string" />												
										</td>										
									</tr>
									<tr>									
										<td class="FormSeparator"></td>
										<td class="BlankSeparator"></td>
									</tr>
								</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	</nested:nest>
	<nested:nest property="alumni_personal_Form">
	<br/>
	<br/>
	<br/>
	<table cellpadding=0 cellspacing=0>
		<tr>
			<td>
				<jsp:include page="/pages/common/templates/groupHeader.jsp">
					<jsp:param name="HeaderKey" value="alumni_personal.target" />
				</jsp:include>
			</td>
		</tr>
		<tr align="center">
			<td class="GroupContent">
				<table class="FormTable" cellpadding=0 cellspacing=0>
					<tr>
						<td align="center">
								<table cellpadding=0 cellspacing=0>
								<tr>
									<td class="FormFieldLeft"><jim:message
											key="alumni_personal.target" /></td>
										<td class="FormFieldRight">
											<nested-jim:TagSelect property="alumni_personal.alumni_personalcode">
												<nested-jim:TagOptionsCollection name="_selec_alumni_personal" label="_descripcion" value="alumni_personalcode" />
											</nested-jim:TagSelect>
										</td>									
									</tr>
								</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	</nested:nest>	
	<br>	
</html:form>