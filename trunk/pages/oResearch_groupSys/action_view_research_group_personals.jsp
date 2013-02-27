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



<nested:nest property="research_group_Form">
<nested:hidden property="research_groupcode" />
</nested:nest>



<table cellpadding=0 cellspacing=0>
	<tr>
		<td><jsp:include page="/pages/common/templates/groupHeader.jsp"> <jsp:param name="HeaderKey" value="research/spgroup/spdata"/> </jsp:include></td>
	</tr>
	<tr align="center">
		<td class="GroupContent">
			<table class="FormTable" cellpadding=0 cellspacing=0>
			

<tr>
	<td align="center">
	<nested:nest property="research_group_Form">

		<table cellpadding=0 cellspacing=0>		
			
<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="supervisor" />
	
</td>
<td class="FormFieldRight">
	<nested:write property="supervisor"/>
	
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
	<nested:write property="unit"/>
	
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
	<nested:textarea property="description" readonly="true"/>
	
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



<table cellspacing="0" cellpadding="0" border="0">
<tbody><tr><td><table cellspacing="0" cellpadding="0" border="0">
<tbody><tr><td><img src="<%=JspUtils.getProjectPath(request)%>/images/gui/headertab-left.gif"/></td><td background="<%=JspUtils.getProjectPath(request)%>/images/gui/headertab-center.gif" class="headertitle">

<jim:message key="supervisor" />

</td><td><img src="<%=JspUtils.getProjectPath(request)%>/images/gui/headertab-right.gif"/></td></tr>
</tbody></table></td><td> </td></tr><tr><td colspan="2">

<table cellspacing="0" cellpadding="0" style="border: 1px solid rgb(153, 153, 153); border-collapse: collapse;">

			

<tr>
	<td align="center">
	<nested:nest property="research_group_Form.supervisor">

		<table cellpadding=0 cellspacing=0>		
			
<tr>
	
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
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<tr>
	
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
</tr>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
		</table>

	</nested:nest>
	</td>
</tr>
			
			
</table></td></tr></tbody></table>
<br>



<table cellpadding=0 cellspacing=0>
	<tr>
		<td><jsp:include page="/pages/common/templates/groupHeader.jsp"> <jsp:param name="HeaderKey" value="personal/spin/spthe/spresearch/spgroup"/> </jsp:include></td>
	</tr>
	<tr align="center">
		<td class="GroupContent">
			<table class="FormTable" cellpadding=0 cellspacing=0>
			
<tr><td>


<jsp:include page="/pages/common/lists/filter.jsp" flush="true">
	<jsp:param name="reportfield" value="oResearch_groupSys__action_view_research_group_personals"/>
</jsp:include>


<jsp:include page="/pages/common/lists/viewlist.jsp" flush="true">
	<jsp:param name="selectionType" value="NONE"/>
	<jsp:param name="objectname" value="professional"/>
	<jsp:param name="objectfield" value="oResearch_groupSys__action_view_research_group_personals"/>
	
	
	<jsp:param name="isReadOnly" value="true"/>
	
</jsp:include>

</tr></td>

			</table>
		</td>
	</tr>
</table>
<br>




</html:form>