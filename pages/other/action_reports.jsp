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
	String onchange="document.forms[0].action='" + currentAction + "';document.forms[0].submit()";
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
		<td><jsp:include page="/pages/common/templates/groupHeader.jsp"> <jsp:param name="HeaderKey" value="report.title"/> </jsp:include></td>
	</tr>
	<tr align="center">
		<td class="GroupContent">
			<table class="FormTable" cellpadding=0 cellspacing=0>
			
<tr>
	<td align="center">

		<table cellpadding=0 cellspacing=0>		
			
<tr>
	
<td class="FormFieldLeft"><jim:message key="report.name" /></td>
<td class="FormFieldRight">
	
<html:select property="selReport" onchange="<%= onchange %>" >
	<html:options labelProperty="reports" property="reportValues" />
</html:select>
	
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>			



<%
	List parameters = (List)request.getAttribute("parameters");
	if(parameters != null)
	{
	    for(Object o: parameters)
	    {
	        Object[] par = (Object[]) o;
	        String inputName = "param_" + (String)par[0];
%>
<tr>

<td class="FormFieldLeft">
		<%= (String)par[1] %>
</td>
<td class="FormFieldRight">
<input type="text" name="<%= inputName %>" value="<%= request.getParameter(inputName)!=null ? request.getParameter(inputName):"" %>" />
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<%
	    }
	}
%>

</table>

	</td>
</tr>
			</table>
		</td>
	</tr>
</table>

</html:form>
