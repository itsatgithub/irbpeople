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

<html:form action="<%=JspUtils.getCurrentAction(request)%>" 
>
	<html:hidden property="id"/>


	<table>
		<tr>
			<td colspan=2><html:errors/></td>
		</tr>
	</table>


<!-- <table cellpadding=0 cellspacing=10 width=100% > -->
	

<table class="itemGroupTable">
	<tr>
		<td>
			<table class="itemGroupStyle_5429820" >
				
					


<tr>

	<td class="itemGroupStyle_5429820">
		<table class="itemGroupStyle_5429820" >
			
<tr>
	<nested:nest property="irbholiday_Form">
		<nested:hidden property="irbholidaycode"></nested:hidden>
	</nested:nest>

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
<table id="calendaranchor" border="0" cellpadding="0" cellspacing="0" width=100% >
<tr><td width="800" height="0" /></tr>
<tr><td>   
	<ctrl:scheduler
		action="../oCalendarSys/popUp_action_add_event"
		locale="<%= UserUtils.getCurrentLocale(request).toString() %>" 
		name="<%= schedulerName %>"
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
		weekEndDays="true"
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
<table cellpadding=0 cellspacing=0>
	<tr>
		<td><jsp:include page="/pages/common/templates/groupHeader.jsp"> <jsp:param name="HeaderKey" value="days"/> </jsp:include></td>
	</tr>
	<tr align="center">
		<td class="GroupContent">
			<table class="FormTable" cellpadding=0 cellspacing=0>
			
<tr><td>


<jsp:include page="/pages/common/lists/viewlist.jsp" flush="true">
	<jsp:param name="selectionType" value="ROWACTION"/>
	<jsp:param name="objectname" value="irbholiday"/>
	<jsp:param name="objectfield" value="oCalendarSys__action_manage_calendar"/>
	<jsp:param name="isReadOnly" value="true"/>
	<jsp:param name="rowActionZoneName" value="rowaction"/>
</jsp:include>

</tr></td>

			</table>
		</td>
	</tr>
</table>
<br>
<table cellpadding=0 cellspacing=0>
	<tr>
		<td><jsp:include page="/pages/common/templates/groupHeader.jsp"> <jsp:param name="HeaderKey" value="limit"/> </jsp:include></td>
	</tr>
	<tr align="center">
		<td class="GroupContent">
			<table class="FormTable" cellpadding=0 cellspacing=0>
<tr>
	<td class="FormSeparator"></td>
	<td class="BlankSeparator"></td>
</tr>
<tr>
	
<td class="FormFieldLeft">
	
<jim:message key="calendar.limit" />
	
</td>
<td class="FormFieldRight">

<nested:nest property="irbholiday_Form">
	<nested-jim:TagDate property="initialdate"></nested-jim:TagDate>
	<nested:hidden property="enddate"></nested:hidden>
</nested:nest>
	
</td>
</tr>

			</table>
		</td>
	</tr>
</table>

<br>

</html:form>