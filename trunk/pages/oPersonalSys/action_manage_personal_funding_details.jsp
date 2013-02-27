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

	<% if(request.getAttribute("tainted")!=null && request.getAttribute("tainted").equals("true")) { %>
    <input type="hidden" name="tainted" value="true" />
    <%} else { %>
    <input type="hidden" name="tainted" value="false" />
	<%} %>

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
	&nbsp;
	<nested:write property="surname1"/>
	&nbsp;
	<nested:write property="surname2"/>
</td>
<td class="FormFieldLeft">
	<jim:message key="state" />
</td>
<td class="FormFieldRight" >
	<nested-jim:state property="state"/>
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
		<td><jsp:include page="/pages/common/templates/groupHeader.jsp"> <jsp:param name="HeaderKey" value="funding/spdetails"/> </jsp:include></td>
	</tr>
	<tr align="center">
		<td class="GroupContent">
			<table class="FormTable" cellpadding=0 cellspacing=0>
		


<tr><td>



<jsp:include page="/pages/common/lists/viewlist.jsp" flush="true">
	<jsp:param name="selectionType" value="ROWACTION"/>
	<jsp:param name="objectname" value="funding_detail"/>
	<jsp:param name="objectfield" value="oPersonalSys__action_manage_personal_funding_details"/>
	
	
	<jsp:param name="isReadOnly" value="true"/>
	<jsp:param name="rowActionZoneName" value="rowaction0"/>
</jsp:include>

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
	
<jim:message key="sponsoring/spagency" />
	
</td>
<td class="FormFieldRight">
	
<nested:text property="sponsoring_agency" maxlength="100" onchange="this.form.elements['tainted'].value=true"/>
	
</td>
<td class="FormFieldLeft">
	
<jim:message key="holding/spinstitution" />
	
</td>
<td class="FormFieldRight">
	
<nested:text property="holding_institution" maxlength="100" onchange="this.form.elements['tainted'].value=true"/>
	
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
	
<nested:text property="principal_investigator" maxlength="100" onchange="this.form.elements['tainted'].value=true"/>
	
</td>
<td class="FormFieldLeft">
	
<jim:message key="research/spproject" />
	
</td>
<td class="FormFieldRight">
	
<nested:text property="research_project" maxlength="100" onchange="this.form.elements['tainted'].value=true"/>
	
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
<jsp:include page="/pages/common/lists/personal_comments_include.jsp" />

</html:form>