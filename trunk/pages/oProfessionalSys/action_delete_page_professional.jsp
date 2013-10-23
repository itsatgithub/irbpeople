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
		<td><jsp:include page="/pages/common/templates/groupHeader.jsp"> <jsp:param name="HeaderKey" value="professional.delete"/> </jsp:include></td>
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
<% if(!UserUtils.checkRole(request, UseCase.IRBPEOPLE_GRANT_ROLE_NAME) && !UserUtils.checkRole(request, UseCase.IRBPEOPLE_INNOVATION_ROLE_NAME)) { %>
<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="end/spdate" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="end_date"/>
	
</td>
</tr>
<% } %>
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