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



<nested:nest property="alumni_external_jobs_Form">
<nested:hidden property="alumni_external_jobscode" />
</nested:nest>



<table cellpadding=0 cellspacing=0>
	<tr>
		<td><jsp:include page="/pages/common/templates/groupHeader.jsp"> <jsp:param name="HeaderKey" value="alumni_external_jobs.modify"/> </jsp:include></td>
	</tr>
	<tr align="center">
		<td class="GroupContent">
			<table class="FormTable" cellpadding=0 cellspacing=0>
			

<tr>
	<td align="center">
	<nested:nest property="alumni_external_jobs_Form">	
		<nested:hidden property="personal.alumni_personalcode"/>
	
<table cellpadding=0 cellspacing=0>		
			
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<tr>
	<td class="FormFieldLeft">
	<jim:message key="alumni_external_jobs.start_date" />
	</td>
	<td class="FormFieldRight">
		<nested-jim:TagDate property="start_date" />
	</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<tr>
	<td class="FormFieldLeft">
	<jim:message key="alumni_external_jobs.end_date" />
	</td>
	<td class="FormFieldRight">
		<nested-jim:TagDate property="end_date" />
	</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="alumni_external_jobs.external_job_positions" />
	
</td>
<td class="FormFieldRight">
	
<nested-jim:TagSelect property="external_job_positions.alumni_external_job_positionscode"  >
	<nested-jim:TagOptionsCollection name="_selec_alumni_external_job_positions" label="_descripcion" value="alumni_external_job_positionscode" />
</nested-jim:TagSelect>
	
</td>
</tr>


<tr>
	<td class="FormFieldLeft">
	<jim:message key="alumni_external_jobs.comments" />
	</td>
	<td class="FormFieldRight">
		<nested:textarea property="comments" />
	</td>
</tr>
<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="alumni_external_jobs.external_job_sectors" />
	
</td>
<td class="FormFieldRight">
	
<nested-jim:TagSelect property="external_job_sectors.alumni_external_job_sectorscode"  >
	<nested-jim:TagOptionsCollection name="_selec_alumni_external_job_sectors" label="_descripcion" value="alumni_external_job_sectorscode" />
</nested-jim:TagSelect>
	
</td>
</tr>
<tr>
	<td class="FormFieldLeft">
		<jim:message key="alumni_external_jobs.institution" />
	</td>
	<td class="FormFieldRight">
		<nested:text property="institution" maxlength="100"/>
	</td>
</tr>
<tr>
	<td class="FormFieldLeft">
		<jim:message key="alumni_external_jobs.address" />
	</td>
	<td class="FormFieldRight">
		<nested:text property="address" maxlength="255"/>
	</td>
</tr>
<tr>
	<td class="FormFieldLeft">
		<jim:message key="alumni_external_jobs.postcode" />
	</td>
	<td class="FormFieldRight">
		<nested:text property="postcode" maxlength="45"/>
	</td>
</tr>
<tr>
	<td class="FormFieldLeft">
		<jim:message key="alumni_external_jobs.city" />
	</td>
	<td class="FormFieldRight">
		<nested:text property="city" maxlength="45"/>
	</td>
</tr>
<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="alumni_external_jobs.country" />
	
</td>
<td class="FormFieldRight">
	
<nested-jim:TagSelect property="country.countrycode" >
	<nested-jim:TagOptionsCollection name="_selec_country" label="_descripcion" value="countrycode" />
</nested-jim:TagSelect>	
</td>
</tr>

<tr>
	<td class="FormFieldLeft">
		<jim:message key="alumni_external_jobs.telephone" />
	</td>
	<td class="FormFieldRight">
		<nested:text property="telephone" maxlength="45" />
										</td>
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