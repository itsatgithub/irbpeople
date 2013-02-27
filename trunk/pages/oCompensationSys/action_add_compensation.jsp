<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested"%>
<%@ taglib uri="/tags/jim" prefix="jim"%>
<%@ taglib uri="/tags/nested-jim" prefix="nested-jim"%>
<%@page import="utils.jsp.JspUtils"%>

<%@page import="presentation.formbeans.objects.Compensation_Form"%>

<%
boolean showAmountPossibilities=request.getAttribute("showAmountPossibilities")!=null;


%>
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



<nested:nest property="compensation_Form">
<nested:hidden property="compensationcode" />
</nested:nest>



<table cellpadding=0 cellspacing=0>
	<tr>
		<td><jsp:include page="/pages/common/templates/groupHeader.jsp"> <jsp:param name="HeaderKey" value="compensation.new"/> </jsp:include></td>
	</tr>
	<tr align="center">
		<td class="GroupContent">
			<table class="FormTable" cellpadding=0 cellspacing=0>
			

<tr>
	<td align="center">
	<nested:nest property="compensation_Form">
	<nested:hidden property="compensation_personal.personalcode"/>

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
	
<jim:message key="type/spof/spcompensation" />
	
</td>
<td class="FormFieldRight">
	
<nested-jim:TagSelect property="type_of_compensation.type_of_compensationcode"  >
	<nested-jim:TagOptionsCollection name="_selec_type_of_compensation" label="_descripcion" value="type_of_compensationcode" />
</nested-jim:TagSelect>
	
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="description" />
	
</td>
<td class="FormFieldRight">
	
<nested:textarea property="description"/>
	
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="amount" />
	
</td>
<td class="FormFieldRight">
	
<%if(showAmountPossibilities){%><nested:radio property="amountType" value="<%=Compensation_Form.FINAL_AMOUNT%>"/> <%} %><nested-jim:TagMoney property="amount" />
	
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
		
<%if(showAmountPossibilities){%>

<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="percentAugment" />
	
</td>
<td class="FormFieldRight">
	
<nested:radio property="amountType" value="<%=Compensation_Form.PERCENT_AMOUNT%>"/><nested-jim:TagUnit property="percentAugment" />
	
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
		


<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="amountAugment" />
	
</td>
<td class="FormFieldRight">
	
<nested:radio property="amountType" value="<%=Compensation_Form.INCREASE_AMOUNT%>"/> <nested-jim:TagMoney property="amountAugment" />
	
</td>
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<%} %>
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