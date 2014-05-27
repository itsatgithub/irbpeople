<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested"%>
<%@ taglib uri="/tags/jim" prefix="jim"%>
<%@ taglib uri="/tags/nested-jim" prefix="nested-jim"%>
<%@page import="utils.jsp.JspUtils"%>

<%@page import="bussineslogic.controlers.UseCase"%>
<%@page import="utils.userUtils.UserUtils"%>

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



<nested:nest property="professional_Form">
<nested:hidden property="professionalcode" />
</nested:nest>



<table cellpadding=0 cellspacing=0>
	<tr>
		<td><jsp:include page="/pages/common/templates/groupHeader.jsp"> <jsp:param name="HeaderKey" value="professional.view"/> </jsp:include></td>
	</tr>
	<tr align="center">
		<td class="GroupContent">
			<table class="FormTable" cellpadding=0 cellspacing=0>
			

<tr>
	<td align="center">
	<nested:nest property="professional_Form">

		<table cellpadding=0 cellspacing=0>		
			
<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="personal/spof/spprofessional" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="professional_personal"/>
	
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
	<nested:write property="start_date"/>
	
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
	<nested:write property="end_date"/>
	
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="type/spof/spcontract" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="type_of_contract"/>
	
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="location" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="location"/>
	
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="email" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="email"/>
	
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="professional/spphone" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="phone"/>
	
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="mobile_long" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="mobile_long"/>
	
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="mobile_short" />
	
</td>
<td class="FormFieldRight">
	
<nested:write property="mobile_short"/>
	
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="lab_phone_number" />
	
</td>
<td class="FormFieldRight">
	
<nested:write property="lab_phone_number"/>
	
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="fax" />
	
</td>
<td class="FormFieldRight">
	
<nested:write property="fax"/>
	
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="payroll/spinstitution" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="payroll_institution"/>
	
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<tr>

<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="payroll/spinstitution_2" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="payroll_institution_2"/>
	
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
	<nested:write property="professional_unit"/>
	
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>

<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="unit_2" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="professional_unit_2"/>
	
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>

<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="unit_3" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="professional_unit_3"/>
	
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>

<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="unit_4" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="professional_unit_4"/>
	
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="research/spgroup" />
	
</td>
<td class="FormFieldRight">
	<nested:textarea property="research_group" readonly="true"/>
	
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="research/spgroup_2" />
	
</td>
<td class="FormFieldRight">
	<nested:textarea property="research_group_2" readonly="true"/>
	
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>

<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="research/spgroup_3" />
	
</td>
<td class="FormFieldRight">
	<nested:textarea property="research_group_3" readonly="true"/>
	
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>

<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="research/spgroup_4" />
	
</td>
<td class="FormFieldRight">
	<nested:textarea property="research_group_4" readonly="true"/>
	
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>

<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="position" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="position"/>
	
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