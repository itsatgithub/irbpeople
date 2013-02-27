<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/jim" prefix="jim"%>
<%@ taglib uri="/tags/nested-jim" prefix="nested-jim"%>
<%@page import="utils.jsp.JspUtils"%>

<%@page import="utils.userUtils.UserUtils"%>
<logic:notPresent name="org.apache.struts.action.MESSAGE" scope="application">
	<font color="red">
		ERROR: Application resources not loaded -- check servlet container logs for error messages. 
	</font>
</logic:notPresent>

<html:form action="<%=JspUtils.getCurrentAction(request)%>" >
	<html:hidden property="id"/>


	<table>
		<tr>
			<td colspan="2"><html:errors/></td>
		</tr>
	</table>
	

<table cellpadding="0" cellspacing="0">
	<tr>
		<td><jsp:include page="/pages/common/templates/groupHeader.jsp"> <jsp:param name="HeaderKey" value="personal/spdata"/> </jsp:include></td>
	</tr>
	<tr align="center">
		<td class="GroupContent">
			<table class="FormTable" cellpadding="0" cellspacing="0">
			

<tr>
	<td align="center">
	<nested:nest property="irbholidayinfo_Form.personal">
	<nested:hidden property="personalcode"></nested:hidden>

		<table cellpadding="0" cellspacing="0">		
			
<tr>
	
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
</table>
<br>
<table cellpadding="0" cellspacing="0">
	<tr>
		<td><jsp:include page="/pages/common/templates/groupHeader.jsp"> <jsp:param name="HeaderKey" value="holidaysinfo"/> </jsp:include></td>
	</tr>
	<tr align="center">
		<td class="GroupContent">
			<table class="FormTable" cellpadding="0" cellspacing="0">
			

<tr>
	<td align="center">
	<nested:nest property="irbholidayinfo_Form">

		<table cellpadding="0" cellspacing="0">
		
<tr>
	<td class="FormFieldLeft" style="font-weight: bold">
		<jim:message key="previousyearholidaysforyear" />
	</td>
	<td class="FormFieldRight" style="font-weight: bold">
		<nested:write property="previousyearholidaysforyear"/>	
	</td>
</tr>

<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>

<tr>
	<td class="FormFieldLeft" style="font-weight: bold">
		<jim:message key="holidaysforyear" />
	</td>
	<td class="FormFieldRight" style="font-weight: bold">
		<nested:write property="holidaysforyear"/>	
	</td>
</tr>

<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>

<tr>
	<td class="FormFieldLeft" style="font-weight: bold">
		<jim:message key="apsforyear" />
	</td>
	<td class="FormFieldRight" style="font-weight: bold">
		<nested:write property="apsforyear"/>	
	</td>
</tr>

<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>

<tr>
	<td class="FormFieldLeft">
		<jim:message key="previous/holidays" /> (<jim:message key="pending" />)
	</td>
	<td class="FormFieldRight">
		<nested:write property="previousyearholidays"/> (<nested:write property="pendingpreviousyearholidays"/>)
	</td>
</tr>

<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>

<tr>
	<td class="FormFieldLeft">
		<jim:message key="spent_holidays" /> (<jim:message key="pending" />)	
	</td>
	<td class="FormFieldRight">
		<nested:write property="holidays"/> (<nested:write property="pendingholidays"/>)
	</td>
</tr>

<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>

<tr>
	<td class="FormFieldLeft">	
		<jim:message key="spent_aps" /> (<jim:message key="pending" />)
	</td>
	<td class="FormFieldRight">
		<nested:write property="aps"/> (<nested:write property="pendingaps"/>)
	</td>
</tr>

<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>

<tr>
	<td class="FormFieldLeft">	
		<jim:message key="remainingpreviousyearholidays" />
	</td>
	<%if(request.getAttribute("pastLimit")!=null && ((String)request.getAttribute("pastLimit")).equals("true")){ %>
	<td class="FormFieldRight" style="color:gray; font-weight: lighter">
	<%} else { %>
	<td class="FormFieldRight" style="color:green; font-weight: bold">
	<%} %>
		<nested:write property="remainingpreviousyearholidays" />
	</td>
</tr>

<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>

<tr>
	<td class="FormFieldLeft">	
		<jim:message key="remainingholidays" />
	</td>
	<td class="FormFieldRight" style="color:green; font-weight: bold">
		<nested:write property="remainingholidays"/>
	</td>
</tr>

<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>

<tr>
	<td class="FormFieldLeft">	
		<jim:message key="remainingaps" />
	</td>
	<td class="FormFieldRight" style="color:green; font-weight: bold">
		<nested:write property="remainingaps"/>
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



<!-- <table cellpadding=0 cellspacing=10 width=100% > -->
	

<table class="itemGroupTable">
	<tr>
		<td>
			<table class="itemGroupStyle_5429820" >
				
					


<tr>

	<td class="itemGroupStyle_5429820">
		<table class="itemGroupStyle_5429820" >
			
<tr>
	

<td colspan="1" 
	rowspan="1"
	class="cellStyle_32760443">
	
</td>
</tr>

	
<tr>
	

<td colspan="1" 
	rowspan="1"
	class="cellStyle_5378774">
	

<table class="itemGroupTable">
	<tr>
		<td>
			<table class="itemGroupStyle_25331350" >
				
					

<tr>

	<td class="iItemContainerStyle_10091594">
		<div class="iItemStyle_10091594">
<%@page import="uicomponents.scheduler.SchedulerFacadeForCommonControlsScheduler"%>
<%@taglib uri="/tags/calendar" prefix="ctrl" %>
<% String schedulerName = SchedulerFacadeForCommonControlsScheduler.prepareTagGetSchedulerNameFromRequest(request); %>
<table id="calendaranchor" border="0" cellpadding="0" cellspacing="0" width="100%" >
<tr><td width="800" height="0" /></tr>
<tr><td>   
	<ctrl:scheduler
		action="../oCalendarSys/popUp_action_add_calendar"
		name="<%= schedulerName %>"
		locale="<%= UserUtils.getCurrentLocale(request).toString() %>"
		title="scheduler.title"
		refreshButton="true"
		width="100%"
		view="year"
		viewButtons=""
		interval="30"
		dayStartHour="1"
		dayEndHour="23"
		maxVisible="2"
		rows="3"
		columns="4"
		weekEndDays="false"
		firstDayOfWeek="mo">
	</ctrl:scheduler>
</td></tr>	
</table>
		</div>
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

					
			</table>
		</td>
	</tr>
</table>
<!-- </table> -->
<br>
<table cellpadding="0" cellspacing="0">
	<tr>
		<td><jsp:include page="/pages/common/templates/groupHeader.jsp"> <jsp:param name="HeaderKey" value="days"/> </jsp:include></td>
	</tr>
	<tr align="center">
		<td class="GroupContent">
			<table class="FormTable" cellpadding="0" cellspacing="0">
			
<tr><td>


<jsp:include page="/pages/common/lists/viewlist.jsp" flush="true">
	<jsp:param name="selectionType" value="ROWACTION"/>
	<jsp:param name="objectname" value="irbholiday"/>
	<jsp:param name="objectfield" value="oCalendarSys__action_manage_personal_calendar"/>
	<jsp:param name="isReadOnly" value="true"/>
	<jsp:param name="rowActionZoneName" value="rowaction"/>
</jsp:include>

</td>
</tr>
			</table>
		</td>
	</tr>
</table>
<br>
</html:form>