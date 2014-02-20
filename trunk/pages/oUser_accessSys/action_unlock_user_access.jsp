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
	<nested:nest property="user_access_Form">
		<nested:hidden property="user_accesscode" />		
	</nested:nest>
	<nested:nest property="user_access_Form">	
	<table cellpadding=0 cellspacing=0>
		<tr>
			<td>
				<jsp:include page="/pages/common/templates/groupHeader.jsp">
					<jsp:param name="HeaderKey" value="user_access" />
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
											<jim:message key="user_access.user_accesscode" />
										</td>
										<td class="FormFieldRight">
											<nested:write property="user_accesscode" />
										</td>
										<td class="FormFieldLeft">
											<jim:message key="user_access.name" />
										</td>
										<td class="FormFieldRight">
											<nested:write property="name" />
										</td>
									</tr>									
									
									<tr>
										<td class="FormSeparator"></td>
										<td class="BlankSeparator"></td>
									</tr>
									
									<tr>
										<td class="FormFieldLeft">
											<jim:message key="user_access.last_access" />
										</td>
										<td class="FormFieldRight">
											<nested:write property="last_access" />
										</td>
										<td class="FormFieldLeft">
											<jim:message key="user_access.tries" />
										</td>
										<td class="FormFieldRight">
											<nested:write property="tries" />
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