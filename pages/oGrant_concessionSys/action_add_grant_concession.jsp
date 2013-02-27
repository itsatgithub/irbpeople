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



<nested:nest property="grant_concession_Form">
<nested:hidden property="grant_concessioncode" />
</nested:nest>



<table cellpadding=0 cellspacing=0>
	<tr>
		<td><jsp:include page="/pages/common/templates/groupHeader.jsp"> <jsp:param name="HeaderKey" value="grant_concession.new"/> </jsp:include></td>
	</tr>
	<tr align="center">
		<td class="GroupContent">
			<table class="FormTable" cellpadding=0 cellspacing=0>
			

<tr>
	<td align="center">
	<nested:nest property="grant_concession_Form">
	<nested:hidden property="grant_concession_personal.personalcode"/>

		<table cellpadding=0 cellspacing=0>					

<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<tr>
	<td class="FormFieldLeft">
	<jim:message key="start_date_msc_or_phd" />
	</td>
	<td class="FormFieldRight">
		<nested-jim:TagDate property="start_date_msc_or_phd" />
	</td>
</tr>

<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<tr>
	<td class="FormFieldLeft">
		<jim:message key="thesis_director" />
	</td>
	<td class="FormFieldRight">
		<nested:text property="thesis_director" maxlength="100"/>
	</td>
</tr>

<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<tr>
	<td class="FormFieldLeft">
		<jim:message key="name_master_or_programme" />
	</td>
	<td class="FormFieldRight">
		<nested:text property="name_master_or_programme" maxlength="100"/>
	</td>
</tr>

<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<tr>
	<td class="FormFieldLeft">
		<jim:message key="university_enrolled" />
	</td>
	<td class="FormFieldRight">
		<nested:text property="university_enrolled" maxlength="100"/>
	</td>
</tr>

<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="start/spdate" />
	
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
	
<jim:message key="end/spdate" />
	
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
	
<jim:message key="grant" />
	
</td>
<td class="FormFieldRight">
	
<nested-jim:TagSelect property="grant.grantcode"  >
	<nested-jim:TagOptionsCollection name="_selec_grant" label="_descripcion" value="grantcode" />
</nested-jim:TagSelect>
	
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="call/spcode" />
	
</td>
<td class="FormFieldRight">
	
<nested:text property="call_code" maxlength="100"/>
	
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