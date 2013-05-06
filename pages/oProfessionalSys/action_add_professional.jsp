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



<nested:nest property="professional_Form">
<nested:hidden property="professionalcode" />
</nested:nest>


<script type="text/javascript" src="<%=JspUtils.getProjectPath(request) %>/common/jquery-1.2.6.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		  $("#add_research").click(function(){
			  $("#research_group_3").show();
			  $(this).hide();
		  });
		  $("#add_research_2").click(function(){
			  $("#research_group_4").show();
			  $(this).hide();
		  });
		  
		  $("#add_unit").click(function(){
			  $("#unit_3").show();
			  $(this).hide();
		  });
		  $("#add_unit_2").click(function(){
			  $("#unit_4").show();
			  $(this).hide();
		  });
		  
		  $(".hide_on_empty select").each(function(){
			  if($(this).val().length == 0){
				  $(this).parent().parent().hide();
			  }
		  });
	});
</script>


<table cellpadding=0 cellspacing=0>
	<tr>
		<td><jsp:include page="/pages/common/templates/groupHeader.jsp"> <jsp:param name="HeaderKey" value="professional.new"/> </jsp:include></td>
	</tr>
	<tr align="center">
		<td class="GroupContent">
			<table class="FormTable" cellpadding=0 cellspacing=0>
			

<tr>
	<td align="center">
	<nested:nest property="professional_Form">

	<nested:hidden property="professional_personal.personalcode" />

		<table cellpadding=0 cellspacing=0>			
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
	
<jim:message key="type/spof/spcontract" />
	
</td>
<td class="FormFieldRight">
	
<nested-jim:TagSelect property="type_of_contract.type_of_contractcode"  >
	<nested-jim:TagOptionsCollection name="_selec_type_of_contract" label="_descripcion" value="type_of_contractcode" />
</nested-jim:TagSelect>
	
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
	
<nested-jim:TagSelect property="location.locationcode"  >
	<nested-jim:TagOptionsCollection name="_selec_location" label="_descripcion" value="locationcode" />
</nested-jim:TagSelect>
	
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
	
<nested:text property="email" maxlength="100" size="35" disabled="true"/>
	
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
	
<nested:text property="phone" maxlength="100" disabled="true"/>
	
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
	
<nested:text property="mobile_long" maxlength="100" disabled="true"/>
	
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
	
<nested:text property="mobile_short" maxlength="100" disabled="true"/>
	
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
	
<nested:text property="lab_phone_number" maxlength="100" disabled="true"/>
	
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
	
<nested:text property="fax" maxlength="100" disabled="true"/>
	
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
	
<nested-jim:TagSelect property="payroll_institution.payroll_institutioncode"  >
	<nested-jim:TagOptionsCollection name="_selec_payroll_institution" label="_descripcion" value="payroll_institutioncode" />
</nested-jim:TagSelect>
	
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="payroll/spinstitution_2" />
	
</td>
<td class="FormFieldRight">
	
<nested-jim:TagSelect property="payroll_institution_2.payroll_institutioncode"  >
	<nested-jim:TagOptionsCollection name="_selec_payroll_institution" label="_descripcion" value="payroll_institutioncode" excludeLabel="IRB"/>
</nested-jim:TagSelect>
	
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
	
<nested-jim:TagSelect property="professional_unit.unitcode"  >
	<nested-jim:TagOptionsCollection name="_selec_unit" label="_descripcion" value="unitcode" />
</nested-jim:TagSelect>
	
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
	
<nested-jim:TagSelect property="professional_unit_2.unitcode"  >
	<nested-jim:TagOptionsCollection name="_selec_unit" label="_descripcion" value="unitcode" />
</nested-jim:TagSelect>
	<input type="button" value="+" id="add_unit" class="add_unit"/>
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>

<tr id="unit_3" class="hide_on_empty">
	
<td class="FormFieldLeft">
	
<jim:message key="unit_3" />
	
</td>
<td class="FormFieldRight">
	
<nested-jim:TagSelect property="professional_unit_3.unitcode">
	<nested-jim:TagOptionsCollection name="_selec_unit" label="_descripcion" value="unitcode" />
</nested-jim:TagSelect>
<input type="button" value="+" id="add_unit_2" class="add_unit"/>	
</td>
</tr>

<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>

<tr id="unit_4" class="hide_on_empty">
	
<td class="FormFieldLeft">
	
<jim:message key="unit_4" />
	
</td>
<td class="FormFieldRight">
	
<nested-jim:TagSelect property="professional_unit_4.unitcode">
	<nested-jim:TagOptionsCollection name="_selec_unit" label="_descripcion" value="unitcode" />
</nested-jim:TagSelect>
	
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
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
	
<nested-jim:TagSelect property="research_group.research_groupcode">
	<nested-jim:TagOptionsCollection name="_selec_research_group" label="_descripcion" value="research_groupcode" />
</nested-jim:TagSelect>
	
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
	
<nested-jim:TagSelect property="research_group_2.research_groupcode">
	<nested-jim:TagOptionsCollection name="_selec_research_group" label="_descripcion" value="research_groupcode" />
</nested-jim:TagSelect>
	<input type="button" value="+" id="add_research" class="add_research"/>	
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>


<tr id="research_group_3" class="hide_on_empty">
	
<td class="FormFieldLeft">
	
<jim:message key="research/spgroup_3" />
	
</td>
<td class="FormFieldRight">
	
<nested-jim:TagSelect property="research_group_3.research_groupcode">
	<nested-jim:TagOptionsCollection name="_selec_research_group" label="_descripcion" value="research_groupcode" />
</nested-jim:TagSelect>
<input type="button" value="+" id="add_research_2" class="add_research"/>	
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>

<tr id="research_group_4" class="hide_on_empty">
	
<td class="FormFieldLeft">
	
<jim:message key="research/spgroup_4" />
	
</td>
<td class="FormFieldRight">
	
<nested-jim:TagSelect property="research_group_4.research_groupcode">
	<nested-jim:TagOptionsCollection name="_selec_research_group" label="_descripcion" value="research_groupcode" />
</nested-jim:TagSelect>
	
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
	
<nested-jim:TagSelect property="position.positioncode"  >
	<nested-jim:TagOptionsCollection name="_selec_position" label="_descripcion" value="positioncode" />
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