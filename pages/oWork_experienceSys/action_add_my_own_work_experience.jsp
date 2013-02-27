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



<nested:nest property="work_experience_Form">
<nested:hidden property="work_experiencecode" />
</nested:nest>



<table cellpadding=0 cellspacing=0>
	<tr>
		<td><jsp:include page="/pages/common/templates/groupHeader.jsp"> <jsp:param name="HeaderKey" value="work_experience.new"/> </jsp:include></td>
	</tr>
	<tr align="center">
		<td class="GroupContent">
			<table class="FormTable" cellpadding=0 cellspacing=0>
			

<tr>
	<td align="center">
	<nested:nest property="work_experience_Form">

		<table cellpadding=0 cellspacing=0>		
			
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="start/spdate" />*
	
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
	
<jim:message key="end/spdate" />*
	
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
	
<jim:message key="type/spof/spinstitution" />*
	
</td>
<td class="FormFieldRight">
	
<nested-jim:TagSelect property="type_of_institution.type_of_institutioncode"  >
	<nested-jim:TagOptionsCollection name="_selec_type_of_institution" label="_descripcion" value="type_of_institutioncode" />
</nested-jim:TagSelect>
	
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="area" />*
	
</td>
<td class="FormFieldRight">
	
<nested-jim:TagSelect property="area.areacode" onchange="positionFunction(this.value);">
	<nested-jim:TagOptionsCollection name="_selec_area" label="_descripcion" value="areacode" />
</nested-jim:TagSelect>
	
</td>
</tr>

<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="name/spof/spinstitution/spor/spcompany" />*
	
</td>
<td class="FormFieldRight">
	
<nested:text property="name_of_institution_or_company" maxlength="100"/>
	
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="department" />
	
</td>
<td class="FormFieldRight">
	
<nested:text property="department" maxlength="45"/>
	
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="position" />*
	
</td>
<td class="FormFieldRight">
	
<nested:text styleId="positionText" property="position" maxlength="45" disabled="false"/>

<nested-jim:TagSelect styleId="positionSelect" property="position"  disabled="true" style="display:none">
	<nested-jim:TagOptionsCollection name="_selec_position" label="_descripcion" value="position" />
</nested-jim:TagSelect>

</td>
</tr>

<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="work/spexperience/spcountry" />*
	
</td>
<td class="FormFieldRight">
	
<nested-jim:TagSelect property="work_experience_country.countrycode"  >
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




</html:form>