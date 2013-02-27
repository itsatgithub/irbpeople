<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested"%>
<%@ taglib uri="/tags/jim" prefix="jim"%>
<%@ taglib uri="/tags/nested-jim" prefix="nested-jim"%>
<%@page import="utils.jsp.JspUtils"%>
<%@page import="java.util.*"%>
<%@page import="presentation.formbeans.objects.*"%>


<table cellpadding=0 cellspacing=0 width="85%">
<tr>
<td align="center">
	
<table cellpadding=0 cellspacing=0 width="100%">
	<tr>
		<td><jsp:include page="/pages/common/templates/groupHeader.jsp">
			<jsp:param name="HeaderKey" value="customlist.columns" />
		</jsp:include></td>
	</tr>
	<tr align="center">
		<td class="GroupContent">
		<table class="FormTable" cellpadding=0 cellspacing=0 width="100%">


			<tr>
				<td align="center">
	<table cellpadding=0 cellspacing=0 width="85%">
		<tr>

			<td><jim:showCombos prefix="SEL" /></td>
		</tr>
		
	</table>
</td></tr></table></td></tr></table>
</td>
</tr>
<tr>
<td align="center">
<table cellpadding=0 cellspacing=0 width="100%">
	<tr>
		<td><jsp:include page="/pages/common/templates/groupHeader.jsp">
			<jsp:param name="HeaderKey" value="customlist.filters" />
		</jsp:include></td>
	</tr>
	<tr align="center">
		<td class="GroupContent">
		<table class="FormTable" cellpadding=0 cellspacing=0 width="100%">


			<tr>
				<td align="center">
	<table cellpadding=0 cellspacing=0 width="85%">
		
		<tr>
			<td><jsp:include page="/pages/customList/filter_view.jsp"
				flush="true" /></td>
		</tr>
		
	</table>
</td></tr></table></td></tr></table>
</td>
</tr>
<tr>
<td align="center">
<table cellpadding=0 cellspacing=0 width="100%">
	<tr>
		<td><jsp:include page="/pages/common/templates/groupHeader.jsp">
			<jsp:param name="HeaderKey" value="customlist.orderBys" />
		</jsp:include></td>
	</tr>
	<tr align="center">
		<td class="GroupContent">
		<table class="FormTable" cellpadding=0 cellspacing=0 width="100%">


			<tr>
				<td align="center">
	<table cellpadding=0 cellspacing=0 width="85%">
		
		<tr>

			<td><jim:showCombos prefix="ORD" /></td>
		</tr>
	</table>
</td></tr></table></td></tr></table>	 
	</td>
</tr>
</table>

