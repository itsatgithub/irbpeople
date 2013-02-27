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

	<table cellpadding=0 cellspacing=0>
		<tr align="center">
			<td>
			<table class="FormTable" cellpadding=0 cellspacing=0>
				<tr>

					<jsp:include page="/pages/common/lists/filter.jsp" flush="true">
						<jsp:param name="reportfield"
							value="oPersonalSys__action_manage_active_personal" />
					</jsp:include>

					<jsp:include page="/pages/common/lists/viewlist.jsp" flush="true">
						<jsp:param name="selectionType" value="ROWACTION" />
						<jsp:param name="objectname" value="personal" />
						<jsp:param name="objectfield"
							value="oPersonalSys__action_manage_active_personal" />
						<jsp:param name="isReadOnly" value="true" />
					</jsp:include>
				</tr>
			</table>
			</td>
		</tr>
	</table>

</html:form>
