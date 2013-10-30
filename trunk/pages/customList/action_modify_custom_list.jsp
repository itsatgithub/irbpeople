<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested"%>
<%@ taglib uri="/tags/jim" prefix="jim"%>
<%@ taglib uri="/tags/nested-jim" prefix="nested-jim"%>
<%@page import="utils.jsp.JspUtils"%>
<%@page import="java.util.*"%>
<%@page import="presentation.formbeans.objects.*"%>
 
<logic:notPresent name="org.apache.struts.action.MESSAGE"
	scope="application">
	<font color="red"> ERROR: Application resources not loaded --
	check servlet container logs for error messages. </font>
</logic:notPresent>
<script type="text/javascript" src="<%=JspUtils.getProjectPath(request) %>/common/jquery-1.2.6.js"></script>

<%
	List<Views_Form> views_form = (List<Views_Form>) request
			.getAttribute("views");
	CustomList_Form customlist = (CustomList_Form) request
			.getAttribute("customlist");
%>



<div class="selected_view">


</div>
	<table>
		<tr>
			<td colspan=2><html:errors /></td>
		</tr>
	</table>

<html:form action="<%=JspUtils.getCurrentAction(request)%>">
	

	
	<html:hidden property="id" />
	<%
		String view_name = (String) request.getAttribute("view_name");
	%>
	
	<html:hidden property="selected_view" value="<%=view_name %>"/>
	
<table cellpadding=0 cellspacing=0 width="85%">
<tr>
<td align="center"> 
<table cellpadding=0 cellspacing=0 width="100%"> 
	<tr>
		<td><jsp:include page="/pages/common/templates/groupHeader.jsp">
			<jsp:param name="HeaderKey" value="customlist.infoCustomList" />
		</jsp:include></td>
	</tr>
	<tr align="center">
		<td class="GroupContent">
		<table class="FormTable" cellpadding=0 cellspacing=0 >


			<tr >
				<td align="center" >


				<table cellpadding=0 cellspacing=0 width="100%">

					<tr>

						<td class="FormFieldLeft" ><jim:message key="customlist.name" />

						</td>
						<td class="FormFieldRight"><b><%=customlist.getName()%></b></td>
					</tr>
					<tr>
						<td class="FormSeparator"></td>
						<td class="BlankSeparator"></td>
					</tr>
					<tr>

						<td class="FormFieldLeft"><jim:message
							key="customlist.comments" /></td>
						<td class="FormFieldRight"><%=customlist.getComments()%></td>
					</tr>
					<tr>
						<td class="FormSeparator"></td>
						<td class="BlankSeparator"></td>
					</tr>
					<tr>

						<td class="FormFieldLeft"><jim:message
							key="customlist.usercode" /></td>
						<td class="FormFieldRight"><%=customlist.getUsercode()%></td>
					</tr>
					<tr>
						<td class="FormSeparator"></td>
						<td class="BlankSeparator"></td>
					</tr>
					<tr>

						<td class="FormFieldLeft"><jim:message
							key="customlist.updateDate" /></td>
						<td class="FormFieldRight"><%=customlist.getUpdate_date()%></td>
					</tr>
					<tr>
						<td class="FormSeparator"></td>
						<td class="BlankSeparator"></td>
					</tr>
					<tr id="listdate">
                        <td class="FormFieldLeft" >
                            <jim:message key="customlist.date" />
                        </td>
                        <td class="FormFieldRight">
                            <jim:TagDate  property="listdate" />
                        </td>
                    </tr>
				</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</td>
</tr>
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

</html:form>

<table height="23" border=0 cellpadding=0 cellspacing=0
	style="position: relative; top: 0; left: 0;"
	onmousedown="this.style.top=1;this.style.left=1;"
	onmouseup="this.style.top=0;this.style.left=0;"
	onmouseout="this.style.top=0;this.style.left=0;">
	<tr>
		<td width=6px
			style="background-image: url(<%=JspUtils.getProjectPath(request) %>/images/gui/button-left.gif )">&nbsp;</td>
		<td valign="top"><input
			style="border: 0px; height: 23; background-image: url(<%= JspUtils.getProjectPath(request) %>/images/gui/button-center.gif ); background-repeat: repeat-x"
			type="button" id="Button_0" name="Button_0" value="Execute"
			onClick="validate_execution('../customList/action_execute_list');">

		</td>
		<td width=6px
			style="background-image: url(<%=JspUtils.getProjectPath(request) %>/images/gui/button-right.gif )">&nbsp;</td>
	</tr>
</table>