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

<script language="javascript">
	var modified=false
</script>

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

<table width=80%>
<tr>
	<td>
<table class="FormTable" cellpadding=0 cellspacing=0>
<tr><td style="border:1px solid black; background:#ffffff; padding:10px">
<center><jim:message key="message.verify_or_complete_data" /></center>
</td></tr></table>
	</td>
</tr>
<tr>
<td>
<br>
<table cellpadding=0 cellspacing=0 width=100%>
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
		</table>

	</nested:nest>
	</td>
</tr>
			</table>
		</td>
	</tr>
</table></td></tr>
<tr>
<td>
<br>



<table cellpadding=0 cellspacing=0 width=100%>
	<tr>
		<td><jsp:include page="/pages/common/templates/groupHeader.jsp"> <jsp:param name="HeaderKey" value="funding/spgeneral"/> </jsp:include></td>
	</tr>
	<tr align="center">
		<td class="GroupContent">
			<table class="FormTable" cellpadding=0 cellspacing=0>
			
			<tr><td style="border:1px solid black; background:#ffffff; padding:10px">
				<jim:message key="funding_general_info"/>
			</td></tr>
			

<tr>
	<td align="center">
	<br>
	<nested:nest property="personal_Form">

		<table cellpadding=0 cellspacing=0>		
			
<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="sponsoring/spagency" />
	
</td>
<td class="FormFieldRight">
	
<nested:text property="sponsoring_agency" maxlength="100" onchange="modified=true;"/>
	
</td>
<td class="FormFieldLeft">
	
<jim:message key="holding/spinstitution" />
	
</td>
<td class="FormFieldRight">
	
<nested:text property="holding_institution" maxlength="100" onchange="modified=true;"/>
	
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="principal/spinvestigator" />
	
</td>
<td class="FormFieldRight">
	
<nested:text property="principal_investigator" maxlength="100" onchange="modified=true;"/>
	
</td>
<td class="FormFieldLeft">
	
<jim:message key="research/spproject" />
	
</td>
<td class="FormFieldRight">
	
<nested:text property="research_project" maxlength="100" onchange="modified=true;"/>
	
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
</td></tr>
<tr>
<td>
<br>



<table cellpadding=0 cellspacing=0 width=100%>
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
	
<jim:message key="research/spgroup" />*
	
</td>
<td class="FormFieldRight">
	
<script type="text/javascript">
var aux;
var changed=false;
function setAux(value){
	if(aux==null){
		aux=value;
	}
}
function changeValueTo(select){
	if(changed) return;
	if(select.value==aux) return;

	if(!confirm('<jim:message key="researchgroup_changed_confirmation" />')){
		select.value=aux;
	}
	else {
		changed=true;
	}
}
</script>

<nested-jim:TagSelect property="research_group.research_groupcode"  onclick="setAux(this.value)" onchange="changeValueTo(this); modified=true">
	<nested-jim:TagOptionsCollection name="_selec_research_group" label="_descripcion" value="research_groupcode" />
</nested-jim:TagSelect>
	
</td>
<td class="FormFieldLeft">
	
<jim:message key="position" />
	
</td>
<td class="FormFieldRight">
	
<nested-jim:TagSelect property="position.positioncode"  onchange="modified=true;" >
	<nested-jim:TagOptionsCollection name="_selec_position" label="_descripcion" value="positioncode" />
</nested-jim:TagSelect>
	
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>

<tr>
	
<td class="FormFieldLeft">
	

<jim:message key="start/spdate" />*

</td>
<td class="FormFieldRight">
	
<nested-jim:TagDate property="start_date"  onchange="modified=true;"/>
	
</td>
<td class="FormFieldLeft">
	
<jim:message key="end/spdate" />

	
</td>
<td class="FormFieldRight">
	
<nested-jim:TagDate property="end_date"  onchange="modified=true;"/>
	
</td>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<tr>

<td class="FormFieldLeft">
	
<jim:message key="payroll/spinstitution" />*
	
</td>
<td class="FormFieldRight">
	
<nested-jim:TagSelect property="payroll_institution.payroll_institutioncode"  onchange="modified=true;" >
	<nested-jim:TagOptionsCollection name="_selec_payroll_institution" label="_descripcion" value="payroll_institutioncode" />
</nested-jim:TagSelect>
	
</td>
</tr>


		</table>
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


</html:form>