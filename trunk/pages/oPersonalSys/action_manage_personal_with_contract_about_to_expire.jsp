<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested"%>
<%@ taglib uri="/tags/jim" prefix="jim"%>
<%@ taglib uri="/tags/nested-jim" prefix="nested-jim"%>
<%@page import="utils.jsp.JspUtils"%>

<logic:notPresent name="org.apache.struts.action.MESSAGE" scope="application">
	<font color="red">
		ERROR: Application resources not loaded -- check servlet container logs for error messages. 
	</font>
</logic:notPresent>

<html:form action="<%=JspUtils.getCurrentAction(request)%>">
	<html:hidden property="id"/>


	<table>
		<tr>
			<td colspan=2><html:errors/></td>
		</tr>
	</table>







<table border=0 cellpadding=0 cellspacing=0>

	<tr align="center">
		<td >
			<table class="FormTable" cellpadding=0 cellspacing=0 border=0>
				<tr>
					<td><h3><jim:message key="personal.contract_about_to_expire" /></h3></td>
				</tr>
				<tr>
					<td>
						<jsp:include page="/pages/common/lists/viewlist.jsp" flush="true">
							<jsp:param name="selectionType" value="ROWACTION"/>
							<jsp:param name="objectname" value="personal"/>
							<jsp:param name="objectfield" value="oPersonalSys__action_manage_personal_with_contract"/>
							
							
							<jsp:param name="isReadOnly" value="true"/>
							<jsp:param name="rowActionZoneName" value="rowaction0"/>
						</jsp:include>
					</td>
				</tr>
				<tr>
					<td><h3><jim:message key="personal.grant_about_to_expire" /></h3></td>
				</tr>
				<tr>
					<td>
						<jsp:include page="/pages/common/lists/viewlist.jsp" flush="true">
							<jsp:param name="selectionType" value="ROWACTION"/>
							<jsp:param name="objectname" value="personal"/>
							<jsp:param name="objectfield" value="oPersonalSys__action_manage_personal_with_grant"/>
							<jsp:param name="viewlistElementsName" value="viewlistElements2" />
							
							<jsp:param name="isReadOnly" value="true"/>
							<jsp:param name="rowActionZoneName" value="rowaction0"/>
						</jsp:include>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>





</html:form>