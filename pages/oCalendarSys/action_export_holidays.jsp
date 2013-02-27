<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested"%>
<%@ taglib uri="/tags/jim" prefix="jim"%>
<%@ taglib uri="/tags/nested-jim" prefix="nested-jim"%>
<%@page import="utils.jsp.JspUtils"%>
<%@page import="java.util.List" %>

<logic:notPresent name="org.apache.struts.action.MESSAGE" scope="application">
	<font color="red">
		ERROR: Application resources not loaded -- check servlet container logs for error messages. 
	</font>
</logic:notPresent>

<%
	String currentAction = JspUtils.getProjectPath(request) + JspUtils.getCurrentAction(request);
	
%>

<html:form action="<%= JspUtils.getCurrentAction(request) %>">
	<html:hidden property="id"/>


	<table>
		<tr>
			<td colspan=2><html:errors/></td>
		</tr>
	</table>


<table cellpadding=0 cellspacing=0>
	<tr>
		<td><jsp:include page="/pages/common/templates/groupHeader.jsp"> <jsp:param name="HeaderKey" value="export_holidays.title"/> </jsp:include></td>
	</tr>
	<tr align="center">
		<td class="GroupContent">
			<table class="FormTable" cellpadding=0 cellspacing=0>
			
<tr>
	<td align="center">

		<table cellpadding=0 cellspacing=0>		
			
<tr>
	
<td class="FormFieldLeft"><jim:message key="start/spdate" /></td>
<td class="FormFieldRight">
	
<jim:TagDate  property="start_date" />
	
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>			




<tr>

<td class="FormFieldLeft">
		<jim:message key="end/spdate" />
</td>
<td class="FormFieldRight">
<jim:TagDate  property="end_date" />
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="unit" />
	
</td>
<td class="FormFieldRight">
	
<jim:TagSelect property="unit.unitcode"  >
	<jim:TagOptionsCollection name="_selec_unit" label="_descripcion" value="unitcode" />
</jim:TagSelect>

	
</td>
</tr>

</table>

	</td>
</tr>
			</table>
		</td>
	</tr>
</table>

</html:form>
