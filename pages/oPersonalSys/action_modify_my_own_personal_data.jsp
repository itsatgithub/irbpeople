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

<script language="javascript">
	var modified=false
</script>

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
<jim:message key="personalcode" />
</td> 
<td class="FormFieldRight">
	<nested:write property="personalcode" />
</td>

<td class="FormFieldLeft" rowspan="19"><jim:message key="photo" /></td>
<td class="FormFieldRight" rowspan="19">

		<a onclick="openViewdetailsPopupOperation('<%=JspUtils.getProjectPath(request) %>/personalPhoto/action_Modify_PersonalPhoto',null,true,'<nested:write property="personalcode"/>','personal'); return false;" href="">
		<html:img action="/personalPhoto/getImage"  height="150" border="1" paramName="_personalPhoto_img" paramId="personalPhotocode"/>
		</a>

</td>

</tr>
<tr><td class="FormSeparator"></td>
<td class="BlankSeparator"></td>
</tr>
<tr>


<td class="FormFieldLeft">
<jim:message key="name" />*</td>
<td class="FormFieldRight">
<nested:text property="name" maxlength="100" onchange="modified=true;"/>
</td>

</tr>
<tr><td class="FormSeparator"></td>
<td class="BlankSeparator"></td>
</tr>
<tr>

	
<td class="FormFieldLeft">
<jim:message key="surname1" />*
</td><td class="FormFieldRight">
<nested:text property="surname1" maxlength="100" onchange="modified=true;"/>
</td>

</tr>
<tr><td class="FormSeparator"></td>
<td class="BlankSeparator"></td>
</tr>
<tr>

	
<td class="FormFieldLeft">
<jim:message key="surname2" />
</td><td class="FormFieldRight">
<nested:text property="surname2" maxlength="100" onchange="modified=true;"/>
</td>

</tr>
<tr><td class="FormSeparator"></td>
<td class="BlankSeparator"></td>
</tr>
<tr>

<td class="FormFieldLeft">
<jim:message key="dni" />*
</td><td class="FormFieldRight">
<nested:text property="dni" maxlength="100" onchange="modified=true;"/>
</td>

</tr>
<tr>
<td class="FormSeparator"></td>
<td class="BlankSeparator"></td>
</tr>
<tr>
	
<td class="FormFieldLeft">
<jim:message key="gender" />*
</td>
<td class="FormFieldRight">
<nested-jim:TagSelect property="gender.gendercode" onchange="modified=true;" >
	<nested-jim:TagOptionsCollection name="_selec_gender" label="_descripcion" value="gendercode" />
</nested-jim:TagSelect>
</td>

</tr>
<tr><td class="FormSeparator"></td>
<td class="BlankSeparator"></td>
</tr>
<tr>

<td class="FormFieldLeft">
<jim:message key="birth/spdate" />*
</td>
<td class="FormFieldRight">
<nested-jim:TagDate property="birth_date" onchange="modified=true;"/>
</td>

</tr>
<tr><td class="FormSeparator"></td>
<td class="BlankSeparator"></td></tr>
<tr>
	
<td class="FormFieldLeft">
<jim:message key="birth/spcity" />*
</td>
<td class="FormFieldRight">
<nested:text property="birth_city" maxlength="100" onchange="modified=true;"/>
</td>


</tr>
<tr><td class="FormSeparator"></td>
<td class="BlankSeparator"></td></tr>
<tr>


<td class="FormFieldLeft">
<jim:message key="birth/spcountry" />*
</td>
<td class="FormFieldRight">
<nested-jim:TagSelect property="birth_country.countrycode" onchange="modified=true;" >
	<nested-jim:TagOptionsCollection name="_selec_country" label="_descripcion" value="countrycode" />
</nested-jim:TagSelect></td>

</tr>
<tr><td class="FormSeparator"></td>
<td class="BlankSeparator"></td>
</tr>
<tr>
	
	
<td class="FormFieldLeft">
<jim:message key="nationality" />*
</td>
<td class="FormFieldRight">
<nested-jim:TagSelect property="nationality.nationalitycode" onchange="modified=true;" >
	<nested-jim:TagOptionsCollection name="_selec_nationality" label="_descripcion" value="nationalitycode" />
</nested-jim:TagSelect>
</td>


</tr>
<tr><td class="FormSeparator"></td>
<td class="BlankSeparator"></td></tr>
<tr>

<td class="FormFieldLeft">
<jim:message key="nationality/sp2" />
</td>
<td class="FormFieldRight">
<nested-jim:TagSelect property="nationality_2.nationalitycode" onchange="modified=true;" >
	<nested-jim:TagOptionsCollection name="_selec_nationality" label="_descripcion" value="nationalitycode" />
</nested-jim:TagSelect>


</td></tr>
<tr><td class="FormSeparator"></td>
<td class="BlankSeparator"></td>
<td class="FormSeparator"></td>
<td class="BlankSeparator"></td>
</tr><tr>


<td class="FormFieldLeft">
<jim:message key="street" />*
</td>
<td class="FormFieldRight">
<nested:text property="street" maxlength="100" onchange="modified=true;"/>
</td>


<td class="FormFieldLeft">
<jim:message key="phone" />*
</td>
<td class="FormFieldRight">
<nested:text property="phone" maxlength="100" onchange="modified=true;"/>
</td>


</tr><tr>
<td class="FormSeparator"></td>
<td class="BlankSeparator"></td>
<td class="FormSeparator"></td>
<td class="BlankSeparator"></td>
</tr><tr>


<td class="FormFieldLeft">
<jim:message key="city" />*
</td>
<td class="FormFieldRight">
<nested:text property="city" maxlength="100" onchange="modified=true;"/>
</td>

<td class="FormFieldLeft">
<jim:message key="phone2" />
</td>
<td class="FormFieldRight">
<nested:text property="phone2" maxlength="100" onchange="modified=true;"/>
</td>


</tr><tr>
<td class="FormSeparator"></td>
<td class="BlankSeparator"></td>
<td class="FormSeparator"></td>
<td class="BlankSeparator"></td>
</tr><tr>


<td class="FormFieldLeft">
<jim:message key="zip/spcode" />*
</td>
<td class="FormFieldRight">
<nested:text property="zip_code" maxlength="100" onchange="modified=true;"/>
</td>


<td class="FormFieldLeft">
<jim:message key="ice/spphone" />
</td>
<td class="FormFieldRight">
<nested:text property="ice_phone" maxlength="100" onchange="modified=true;"/>
</td>


</tr><tr>
<td class="FormSeparator"></td>
<td class="BlankSeparator"></td>
<td class="FormSeparator"></td>
<td class="BlankSeparator"></td>
</tr><tr>



<td class="FormFieldLeft">
<jim:message key="country" />*
</td>
<td class="FormFieldRight">
<nested-jim:TagSelect property="country.countrycode" onchange="modified=true;" >
	<nested-jim:TagOptionsCollection name="_selec_country" label="_descripcion" value="countrycode" />
</nested-jim:TagSelect>
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
	<nested:hidden property="professionalcode"></nested:hidden>

		<table cellpadding=0 cellspacing=0>		
<tr>
	
<td class="FormFieldLeft">
	

<jim:message key="start/spdate" />*

</td>
<td class="FormFieldRight">
	
<nested-jim:TagDate property="start_date" onchange="modified=true;"/>
	
</td>
<td class="FormFieldLeft">
	
<jim:message key="end/spdate" />

	
</td>
<td class="FormFieldRight">
	
<nested-jim:TagDate property="end_date" onchange="modified=true;"/>
	
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
<jim:message key="unit" />*
</td>
<td class="FormFieldRight">
<nested-jim:TagSelect property="professional_unit.unitcode" onchange="modified=true;" >
	<nested-jim:TagOptionsCollection name="_selec_unit" label="_descripcion" value="unitcode" />
</nested-jim:TagSelect>
</td>
<td class="FormFieldLeft">
	
<jim:message key="payroll/spinstitution" />
	
</td>
<td class="FormFieldRight">
	
<nested-jim:TagSelect property="payroll_institution.payroll_institutioncode" onchange="modified=true;" >
	<nested-jim:TagOptionsCollection name="_selec_payroll_institution" label="_descripcion" value="payroll_institutioncode" />
</nested-jim:TagSelect>
	
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
	
<jim:message key="research/spgroup" />*
	
</td>
<td class="FormFieldRight">
	
<nested-jim:TagSelect property="research_group.research_groupcode" onchange="modified=true;" >
	<nested-jim:TagOptionsCollection name="_selec_research_group" label="_descripcion" value="research_groupcode" emptyValueLabel="no_Research_group"/>
</nested-jim:TagSelect>

	
</td>
<td class="FormFieldLeft">
	
<jim:message key="position" />
	
</td>
<td class="FormFieldRight">
	
<nested-jim:TagSelect property="position.positioncode" onchange="modified=true;" >
	<nested-jim:TagOptionsCollection name="_selec_position" label="_descripcion" value="positioncode" />
</nested-jim:TagSelect>
	
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
<jim:message key="email" />*
</td>
<td class="FormFieldRight">	
<nested:text property="email" maxlength="100" onchange="modified=true;"/>	
</td>
<td class="FormFieldLeft">
<jim:message key="location" />*
</td><td class="FormFieldRight">
<nested-jim:TagSelect property="location.locationcode" onchange="modified=true;" >
	<nested-jim:TagOptionsCollection name="_selec_location" label="_descripcion" value="locationcode" />
</nested-jim:TagSelect>	
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
	
<jim:message key="professional/spphone" />*

	
</td>
<td class="FormFieldRight">
	
<nested:text property="phone" maxlength="100" onchange="modified=true;"/>
	
</td>
<td class="FormFieldLeft">
	
<jim:message key="professional/spmobile" />

	
</td>
<td class="FormFieldRight">
	
<nested:text property="mobile" maxlength="100" onchange="modified=true;"/>
	
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

<b><jim:message key="confidentialDataInfoTitle"/></b>

<p><font size="1"><textarea cols="140" rows="5" readonly="true" wrap="virtual">
<jim:message key="confidentialDataInfoText" translateBrs="false"/>
</textarea></font></p>


</html:form>