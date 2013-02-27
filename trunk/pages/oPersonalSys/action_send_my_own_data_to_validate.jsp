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
		<td><jsp:include page="/pages/common/templates/groupHeader.jsp"> <jsp:param name="HeaderKey" value="personal/spdata"/> </jsp:include></td>
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
		<td><jsp:include page="/pages/common/templates/groupHeader.jsp"> <jsp:param name="HeaderKey" value="professional"/> </jsp:include></td>
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
<jim:message key="unit"/>	
</td>
<td class="FormFieldRight">
<nested:write property="professional_unit.unitcode" />
</td>

<td class="FormFieldLeft">	
<jim:message key="email"/>
</td>
<td class="FormFieldRight">
<nested:write property="email"/>
</td>
</tr>
	
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
	
<td class="FormFieldLeft">
	
<jim:message key="research/spgroup" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="research_group" />
	
</td>
<td class="FormFieldLeft">
	
<jim:message key="position" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="position" />
	
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>

<tr>
<td class="FormFieldLeft">	
<jim:message key="start/spdate"/>
</td>
<td class="FormFieldRight">
<nested:write property="start_date" />
</td>
<td class="FormFieldLeft">
<jim:message key="end/spdate"/>
</td>
<td class="FormFieldRight">
<nested:write property="end_date" />
</td>
</tr>

<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>

<tr>
<td class="FormFieldLeft">	
<jim:message key="professional/spphone"/>	
</td>
<td class="FormFieldRight">
<nested:write property="phone" />
</td>
<td class="FormFieldLeft">
<jim:message key="professional/spmobile"/>
</td>
<td class="FormFieldRight">
<nested:write property="mobile" />
</td>
</tr>

<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>

<tr>
<td class="FormFieldLeft">
<jim:message key="payroll/spinstitution"/>	
</td>
<td class="FormFieldRight">	
<nested:write property="payroll_institution.payroll_institutioncode" />
</td>

<td class="FormFieldLeft">	
<jim:message key="location"/>	
</td>
<td class="FormFieldRight">
<nested:write property="location.locationcode"/>
</td>
</tr>

<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
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
		<td><jsp:include page="/pages/common/templates/groupHeader.jsp"> <jsp:param name="HeaderKey" value="work/spexperience"/> </jsp:include></td>
	</tr>
	<tr align="center">
		<td class="GroupContent">
			<table class="FormTable" cellpadding=0 cellspacing=0>
			
<tr><td>



<jsp:include page="/pages/common/lists/viewlist.jsp" flush="true">
	<jsp:param name="selectionType" value="none"/>
	<jsp:param name="objectname" value="work_experience"/>
	<jsp:param name="objectfield" value="oPersonalSys__action_send_my_own_data_to_validate"/>
	<jsp:param name="isReadOnly" value="true"/>
	<jsp:param name="viewlistElementsName" value="work_experience_viewlistElements"/>
</jsp:include>

</tr></td>

			</table>
		</td>
	</tr>
</table>
<br>



<table cellpadding=0 cellspacing=0>
	<tr>
		<td><jsp:include page="/pages/common/templates/groupHeader.jsp"> <jsp:param name="HeaderKey" value="grant/spconcessions"/> </jsp:include></td>
	</tr>
	<tr align="center">
		<td class="GroupContent">
			<table class="FormTable" cellpadding=0 cellspacing=0>
			<tr><td>



<jsp:include page="/pages/common/lists/viewlist.jsp" flush="true">
	<jsp:param name="selectionType" value="none"/>
	<jsp:param name="objectname" value="grant_concession"/>
	<jsp:param name="objectfield" value="oPersonalSys__action_send_my_own_data_to_validate_grant_concession"/>
	<jsp:param name="isReadOnly" value="true"/>
	<jsp:param name="viewlistElementsName" value="grant_concession_viewlistElements"/>
</jsp:include>

</tr></td>
			</table>
		</td>
	</tr>
</table>
<br>



<table cellpadding=0 cellspacing=0>
	<tr>
		<td><jsp:include page="/pages/common/templates/groupHeader.jsp"> <jsp:param name="HeaderKey" value="education"/> </jsp:include></td>
	</tr>
	<tr align="center">
		<td class="GroupContent">
			<table class="FormTable" cellpadding=0 cellspacing=0>
<tr><td>



<jsp:include page="/pages/common/lists/viewlist.jsp" flush="true">
	<jsp:param name="selectionType" value="none"/>
	<jsp:param name="objectname" value="education"/>
	<jsp:param name="objectfield" value="oPersonalSys__action_send_my_own_data_to_validate_education"/>
	<jsp:param name="isReadOnly" value="true"/>
	<jsp:param name="viewlistElementsName" value="education_viewlistElements"/>
</jsp:include>

</tr></td>			
			</table>
		</td>
	</tr>
</table>
<br>




</html:form>