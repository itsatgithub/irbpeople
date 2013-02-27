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
	


	<table>
		<tr>
			<td colspan=2><html:errors/></td>
		</tr>
	</table>

<table>
<tr>
	<td colspan=2><strong><jim:message key="closeYearWarning" /></strong></td>
</tr>
<tr>
	<td colspan=2>&nbsp;</td>
</tr>
<tr>
	<td><jim:message key="holidaysCurrentYear"/></td><td><%= request.getAttribute("currentYear") %></td>
</tr>
<tr>
	<td><jim:message key="holidaysNextYear"/></td><td><%= request.getAttribute("nextYear") %></td>
</tr>
</table>

</html:form>