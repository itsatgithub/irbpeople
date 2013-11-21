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
		<td><jsp:include page="/pages/common/templates/groupHeader.jsp"> <jsp:param name="HeaderKey" value="alumni_external_job_positions.new"/> </jsp:include></td>
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
	
<jim:message key="alumni_external_job_positions.job_position_types" />
	
</td>
<td class="FormFieldRight">
	
<nested-jim:TagSelect property="job_position_types.alumni_job_position_typescode"  >
	<nested-jim:TagOptionsCollection name="_selec_alumni_job_position_types" label="_descripcion" value="alumni_job_position_typescode" />
</nested-jim:TagSelect>
	
</td>
</tr>	
<tr>
	
	
<td class="FormFieldLeft">
	

<jim:message key="alumni_external_job_positions.description" />
	*
</td>
<td class="FormFieldRight">
	
<nested:text property="description" maxlength="255"/>
	
</td>
</tr>
<tr>
	
<td class="FormFieldLeft">
	

<jim:message key="alumni_external_job_positions.short_description" />	
</td>
<td class="FormFieldRight">
	
<nested:text property="short_description" maxlength="100"/>
	
</td>
</tr>
<tr>
	
<td class="FormFieldLeft">
	

<jim:message key="alumni_external_job_positions.order_number" />	
</td>
<td class="FormFieldRight">	
	<nested:text property="order_number" maxlength="3"/>	
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