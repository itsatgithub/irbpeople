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



<nested:nest property="alumni_personal_Form">
<nested:hidden property="alumni_personalcode" />
</nested:nest>

<table cellpadding=0 cellspacing=0>
	<tr>
		<td><jsp:include page="/pages/common/templates/groupHeader.jsp"> <jsp:param name="HeaderKey" value="alumni_personal"/> </jsp:include></td>
	</tr>
	<tr align="center">
		<td class="GroupContent">
			<table class="FormTable" cellpadding=0 cellspacing=0>
			

<tr>
	<td align="center">
	<nested:nest property="alumni_personal_Form">

		<table cellpadding=0 cellspacing=0>		
			
<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="alumni_personal.alumni_personalcode" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="alumni_personalcode"/>
	
</td>
<td class="FormFieldLeft">
	<jim:message key="alumni_personal.firstname" />
</td>
<td class="FormFieldRight">
	<nested:write property="firstname"/>	
</td>
<td class="FormFieldLeft">
	<jim:message key="alumni_personal.surname" />
</td>
<td class="FormFieldRight" >
	<nested:write property="surname"/>	
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
		<td><jsp:include page="/pages/common/templates/groupHeader.jsp"> <jsp:param name="HeaderKey" value="alumni_directory_data"/> </jsp:include></td>
	</tr>
	<tr align="center">
		<td class="GroupContent">
			<table class="FormTable" cellpadding=0 cellspacing=0>
			
<tr><td>



<jsp:include page="/pages/common/lists/viewlist.jsp" flush="true">
	<jsp:param name="selectionType" value="ROWACTION"/>
	<jsp:param name="objectname" value="alumni_directory_data"/>
	<jsp:param name="objectfield" value="oAlumni_personalSys__action_manage_alumni_personal_alumni_directory_data"/>
	
	
	<jsp:param name="isReadOnly" value="true"/>
	<jsp:param name="rowActionZoneName" value="rowaction0"/>
</jsp:include>

</tr></td>

			</table>
		</td>
	</tr>
</table>
<br>
</html:form>