<%@page import="java.util.Map" %>
<%@ taglib uri="/tags/jim" prefix="jim"%>

<%

	Map<String, String[]> nextAndPreviousMap = (Map<String, String[]>)request.getSession().getAttribute("personals_order");
	String personalcode = (String)request.getAttribute("personalcode");
	String pageName = request.getParameter("pageName");
	if(nextAndPreviousMap.get(personalcode)!=null)
	{
%>

<table cellpadding="0" cellspacing="0" border="0" align="right">
<tr>
<% if( nextAndPreviousMap.get(personalcode)[0] == null ) {%>
<td height="23" width="19" style="padding-right: 2px;">
<input height="16" width="16" type="image" style="border: 0pt none ; position: relative; top: 0pt; left: 0pt;" title="No previous" src="/irbpeople/images/CATEGORY/action-type/previous_disabled.gif" onclick="return false;"/>
</td><% } else { %>
<td height="23" width="19" style="padding-right: 2px;">
<input height="16" width="16" type="image" onmouseup="this.style.top=0;this.style.left=0;" onmousedown="this.style.top=1;this.style.left=1;" style="border: 0pt none ; position: relative; top: 0pt; left: 0pt;" title="Previous" src="/irbpeople/images/CATEGORY/action-type/previous.gif" onclick="if(checkChanged()){submitRowAction('..<%= pageName %>',this.form,'personalcode','<%= nextAndPreviousMap.get(personalcode)[0] %>');}return false;"/>
</td>
<% }

	if( nextAndPreviousMap.get(personalcode)[1] == null) {
%>
<td height="23" width="19" style="padding-right: 2px;">
<input height="16" width="16" type="image" style="border: 0pt none ; position: relative; top: 0pt; left: 0pt;" title="No next" src="/irbpeople/images/CATEGORY/action-type/next_disabled.gif" onclick="return false;"/>
</td>
<% } else { %>
<td height="23" width="19" style="padding-right: 2px;">
<input height="16" width="16" type="image" onmouseup="this.style.top=0;this.style.left=0;" onmousedown="this.style.top=1;this.style.left=1;" style="border: 0pt none ; position: relative; top: 0pt; left: 0pt;" title="Next" src="/irbpeople/images/CATEGORY/action-type/next.gif" onclick="if(checkChanged()){submitRowAction('..<%= pageName %>',this.form,'personalcode','<%= nextAndPreviousMap.get(personalcode)[1] %>');}return false;"/>
</td>
<% }%>

</tr>
</table>
<%
	}
%>