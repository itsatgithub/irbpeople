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



<nested:nest property="alumni_external_job_positions_Form">
<nested:hidden property="alumni_external_job_positionscode" />
</nested:nest>



<table cellpadding=0 cellspacing=0>
	<tr>
		<td><jsp:include page="/pages/common/templates/groupHeader.jsp"> <jsp:param name="HeaderKey" value="alumni_external_job_positions.view"/> </jsp:include></td>
	</tr>
	<tr align="center">
		<td class="GroupContent">
			<table class="FormTable" cellpadding=0 cellspacing=0>
			

<tr>
	<td align="center">
	<nested:nest property="alumni_external_job_positions_Form">

		<table cellpadding=0 cellspacing=0>		
			
<tr>
	
<td class="FormFieldLeft">
	

<jim:message key="alumni_external_job_positions.description" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="description"/>
	
</td>
</tr>
<tr>
	
<td class="FormFieldLeft">
	

<jim:message key="alumni_external_job_positions.short_description" />	
</td>
<td class="FormFieldRight">
	
<nested:write property="short_description"/>
	
</td>
</tr>
<tr>
	
<td class="FormFieldLeft">
	

<jim:message key="alumni_external_job_positions.order_number" />	
</td>
<td class="FormFieldRight">	
	<nested:write property="order_number"/>	
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
		</table>

	</nested:nest>
	</td>
</tr>
			</table>
		</td>
	</tr>
</table>
<br>




</html:form>