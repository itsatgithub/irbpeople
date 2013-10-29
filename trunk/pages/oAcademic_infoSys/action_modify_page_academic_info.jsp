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



<nested:nest property="academic_info_Form">
<nested:hidden property="academic_infocode" />
</nested:nest>



<table cellpadding=0 cellspacing=0>
	<tr>
		<td><jsp:include page="/pages/common/templates/groupHeader.jsp"> <jsp:param name="HeaderKey" value="academic_info.modify"/> </jsp:include></td>
	</tr>
	<tr align="center">
		<td class="GroupContent">
			<table class="FormTable" cellpadding=0 cellspacing=0>
			

<tr>
	<td align="center">
	<nested:nest property="academic_info_Form">

<table cellpadding=0 cellspacing=0>		
			
<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="spperson" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="academic_info_personal"/>
	
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
	
<jim:message key="academic_info.type_of_study" />
	
</td>
<td class="FormFieldRight">
	
<nested-jim:TagSelect property="type_of_study.type_of_studycode"  >
	<nested-jim:TagOptionsCollection name="_selec_type_of_study" label="_descripcion" value="type_of_studycode" />
</nested-jim:TagSelect>
	
</td>
</tr>
<tr>
	<td class="FormFieldLeft">
	<jim:message key="academic_info.start_date" />
	</td>
	<td class="FormFieldRight">
		<nested-jim:TagDate property="start_date" />
	</td>
</tr>
<tr>
	<td class="FormFieldLeft">
	<jim:message key="academic_info.end_date" />
	</td>
	<td class="FormFieldRight">
		<nested-jim:TagDate property="end_date" />
	</td>
</tr>
<tr>
	<td class="FormFieldLeft">
	<jim:message key="academic_info.thesis_defense_date" />
	</td>
	<td class="FormFieldRight">
		<nested-jim:TagDate property="thesis_defense_date" />
	</td>
</tr>
<tr>
	<td class="FormFieldLeft">
		<jim:message key="academic_info.university_enrolled" />
	</td>
	<td class="FormFieldRight">
		<nested:text property="university_enrolled" maxlength="100"/>
	</td>
</tr>
<tr>
	<td class="FormFieldLeft">
		<jim:message key="academic_info.studies_name" />
	</td>
	<td class="FormFieldRight">
		<nested:text property="studies_name" maxlength="100"/>
	</td>
</tr>
<tr>
	<td class="FormFieldLeft">
		<jim:message key="academic_info.thesis_director" />
	</td>
	<td class="FormFieldRight">
		<nested:text property="thesis_director" maxlength="100"/>
	</td>
</tr>
<tr>
	<td class="FormFieldLeft">
		<jim:message key="academic_info.thesis_codirector" />
	</td>
	<td class="FormFieldRight">
		<nested:text property="thesis_codirector" maxlength="100"/>
	</td>
</tr>
<tr>
	<td class="FormFieldLeft">
	<jim:message key="academic_info.tac0" />
	</td>
	<td class="FormFieldRight">
		<nested-jim:booleanSelector property="tac0" />
	</td>
</tr>
<tr>
	<td class="FormFieldLeft">
	<jim:message key="academic_info.lab_rotation_date" />
	</td>
	<td class="FormFieldRight">
		<nested-jim:TagDate property="lab_rotation_date" />
	</td>
</tr>
<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="academic_info.lab_rotation_lab" />
	
</td>
<td class="FormFieldRight">
	
<nested-jim:TagSelect property="lab_rotation_lab.research_groupcode">
	<nested-jim:TagOptionsCollection name="_selec_research_group" label="_descripcion" value="research_groupcode" />
</nested-jim:TagSelect>
	
</td>
</tr>
<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="academic_info.lab_rotation_lab2" />
	
</td>
<td class="FormFieldRight">
	
<nested-jim:TagSelect property="lab_rotation_lab2.research_groupcode">
	<nested-jim:TagOptionsCollection name="_selec_research_group" label="_descripcion" value="research_groupcode" />
</nested-jim:TagSelect>
	
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
<br>




</html:form>