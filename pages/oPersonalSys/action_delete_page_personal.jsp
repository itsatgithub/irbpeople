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



<nested:nest property="personal_Form">
<nested:hidden property="personalcode" />
</nested:nest>



<table cellpadding=0 cellspacing=0>
	<tr>
		<td><jsp:include page="/pages/common/templates/groupHeader.jsp"> <jsp:param name="HeaderKey" value="personal"/> </jsp:include></td>
	</tr>
	<tr align="center">
		<td class="GroupContent">
			<table class="FormTable" cellpadding=0 cellspacing=0>
			

<tr>
	<td align="center">
	<nested:nest property="personal_Form">

		<table cellpadding=0 cellspacing=0>		
			
<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="usercode" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="usercode"/>
	
</td>
<td class="FormFieldLeft">
	
<jim:message key="name" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="name"/>
	
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="surname1" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="surname1"/>
	
</td>

<td class="FormFieldLeft">
	
<jim:message key="surname2" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="surname2"/>
	
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<tr>
<td class="FormFieldLeft">
	
<jim:message key="dni" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="dni"/>
	
</td>

	
<td class="FormFieldLeft">
	
<jim:message key="gender" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="gender"/>
	
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<tr>
<td class="FormFieldLeft">
	
<jim:message key="birth/spdate" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="birth_date"/>
	
</td>

	
<td class="FormFieldLeft">
	
<jim:message key="birth/spcity" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="birth_city"/>
	
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<tr>
<td class="FormFieldLeft">
	
<jim:message key="birth/spcountry" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="birth_country"/>
	
</td>

	
<td class="FormFieldLeft">
	
<jim:message key="nationality" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="nationality"/>
	
</td>

</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<tr>

<td class="FormFieldLeft">
	
<jim:message key="nationality/sp2" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="nationality_2"/>
	
</td>

	
<td class="FormFieldLeft">
	
<jim:message key="street" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="street"/>
	
</td>

</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<tr>

<td class="FormFieldLeft">
	
<jim:message key="city" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="city"/>
	
</td>

	
<td class="FormFieldLeft">
	
<jim:message key="zip/spcode" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="zip_code"/>
	
</td>

</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<tr>

<td class="FormFieldLeft">
	
<jim:message key="country" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="country"/>
	
</td>

	
<td class="FormFieldLeft">
	
<jim:message key="phone" />
	
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
	
<jim:message key="phone2" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="phone2"/>
	
</td>

	
<td class="FormFieldLeft">
	
<jim:message key="ice/spphone" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="ice_phone"/>
	
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



<table cellpadding=0 cellspacing=0>
	<tr>
		<td><jsp:include page="/pages/common/templates/groupHeader.jsp"> <jsp:param name="HeaderKey" value="employee"/> </jsp:include></td>
	</tr>
	<tr align="center">
		<td class="GroupContent">
			<table class="FormTable" cellpadding=0 cellspacing=0>
			

<tr>
	<td align="center">
	<nested:nest property="personal_Form">

		<table cellpadding=0 cellspacing=0>		
			
<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="payments" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="payments"/>
	
</td>
<td class="FormFieldLeft">
	
<jim:message key="ss/spnumber" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="ss_number"/>
	
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="category" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="category"/>
	
</td>
<td class="FormFieldLeft">
	
<jim:message key="working/ushours" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="working_hours"/>
	
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="bank" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="bank"/>
	
</td>
<td class="FormFieldLeft">
	
<jim:message key="bank/spaccount" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="bank_account"/>
	
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="marital/usstatus" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="marital_status"/>
	
</td>
<td class="FormFieldLeft">
<jim:message key="bic" />
</td>
<td class="FormFieldRight">	
	<nested:write property="bic"/>	
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>

<tr>
	<td class="FormFieldLeft">	
		<jim:message key="personal.mostAdvancedEducation" />
	</td>
	<td class="FormFieldRight">	
		<nested:write property="mostAdvancedEducation"/>	
	</td>
	<td class="FormFieldLeft">	
		<jim:message key="personal.access_scientific_publications" />
	</td>
	<td class="FormFieldRight">	
		<nested:write property="access_scientific_publications"/>	
	</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>



<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="personal.second_affiliation" />
	
</td>
<td class="FormFieldRight"  colspan="3">
	<nested:write property="second_affiliation"/>
	
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



<table cellpadding=0 cellspacing=0>
	<tr>
		<td><jsp:include page="/pages/common/templates/groupHeader.jsp"> <jsp:param name="HeaderKey" value="funding/spgeneral"/> </jsp:include></td>
	</tr>
	<tr align="center">
		<td class="GroupContent">
			<table class="FormTable" cellpadding=0 cellspacing=0>
			

<tr>
	<td align="center">
	<nested:nest property="personal_Form">

		<table cellpadding=0 cellspacing=0>		
			
<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="principal/spinvestigator" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="principal_investigator"/>
	
</td>
<td class="FormFieldLeft">
	
<jim:message key="holding/spinstitution" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="holding_institution"/>
	
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="sponsoring/spagency" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="sponsoring_agency"/>
	
</td>
<td class="FormFieldLeft">
	
<jim:message key="research/spproject" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="research_project"/>
	
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



<table cellpadding=0 cellspacing=0>
	<tr>
		<td><jsp:include page="/pages/common/templates/groupHeader.jsp"> <jsp:param name="HeaderKey" value="end/spof/spcontract"/> </jsp:include></td>
	</tr>
	<tr align="center">
		<td class="GroupContent">
			<table class="FormTable" cellpadding=0 cellspacing=0>
			

<tr>
	<td align="center">
	<nested:nest property="personal_Form">

		<table cellpadding=0 cellspacing=0>		
			
<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="end/spof/spcontract/spemail" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="end_of_contract_email"/>
	
</td>
<td class="FormFieldLeft">
	
<jim:message key="end/spof/spcontract/spphone" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="end_of_contract_phone"/>
	
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="end/spof/spcontract/spzip/spcode" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="end_of_contract_zip_code"/>
	
</td>
<td class="FormFieldLeft">
	
<jim:message key="end/spof/spcontract/spcity" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="end_of_contract_city"/>
	
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="end/spof/spcontract/spcountry" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="end_of_contract_country"/>
	
</td>
<td class="FormFieldLeft">
	
<jim:message key="end/spof/spcontract/spaddress" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="end_of_contract_address"/>
	
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="end/spof/spcontract/spreason" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="end_of_contract_reason"/>
	
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



<table cellpadding=0 cellspacing=0>
	<tr>
		<td><jsp:include page="/pages/common/templates/groupHeader.jsp"> <jsp:param name="HeaderKey" value="current/spprofessional"/> </jsp:include></td>
	</tr>
	<tr align="center">
		<td class="GroupContent">
			<table class="FormTable" cellpadding=0 cellspacing=0>
			
			</table>
		</td>
	</tr>
</table>
<br>



<table cellpadding=0 cellspacing=0>
	<tr>
		<td><jsp:include page="/pages/common/templates/groupHeader.jsp"> <jsp:param name="HeaderKey" value="current/spcompensation"/> </jsp:include></td>
	</tr>
	<tr align="center">
		<td class="GroupContent">
			<table class="FormTable" cellpadding=0 cellspacing=0>
			
			</table>
		</td>
	</tr>
</table>
<br>




</html:form>