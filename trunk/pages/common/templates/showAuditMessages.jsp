<%@page import="presentation.formbeans.objects.Auditmessage_Form"%>
<%@page import="java.util.List"%>
<%@page import="utils.userUtils.UserUtils"%>
<%@ include file="/pages/common/templates/global.jsp" %>
<%

List<Auditmessage_Form> messages=UserUtils.obtainUnseenAuditMessagesFromCurrentUser(request);
if(messages!=null && !messages.isEmpty()){
%>

<center>
<table width=100% border=0 cellspacing=0 cellpadding=0>
<tr>
<td>
	<table width=100% border=0 cellspacing=0 cellpadding=0 >
		<tr>
			<td width=5px height=5px><img width=5px height=5px src="<%=JspUtils.getProjectPath(request)%>/images/gui/top-left-corner.gif" /></td>			
			<td bgcolor="#ffffff" style="border-top:1px solid #898989" height="5px" style="font-size:0px"><img width=1px border=0 src="<%=JspUtils.getProjectPath(request)%>/images/gui/transparent.gif" /></td>			
			<td bgcolor="#ffffff" style="border-top:1px solid #898989" height="5px" style="font-size:0px"><img width=1px border=0 src="<%=JspUtils.getProjectPath(request)%>/images/gui/transparent.gif" /></td>			
			<td bgcolor="#ffffff" style="border-top:1px solid #898989" height="5px" style="font-size:0px"><img width=1px border=0 src="<%=JspUtils.getProjectPath(request)%>/images/gui/transparent.gif" /></td>						
			<td width=5px height=5px><img width=5px height=5px src="<%=JspUtils.getProjectPath(request)%>/images/gui/top-right-corner.gif" /></td>
		</tr>
		<tr bgcolor="#ffffff">
			<td style="border-left:1px solid #898989" width="4px" style="font-size:0px"><img width=1px border=0 src="<%=JspUtils.getProjectPath(request)%>/images/gui/transparent.gif" /></td>
			<td>&nbsp;</td>
			<td align="center">
				<table border=0 cellspacing=0 cellpadding=0 style="padding-left:3px; padding-right:3px">
				

<%


for(Auditmessage_Form msg:messages){%>
	<tr><td><LI><%=msg.getMessage() %></LI></td></tr>
<%}
%>
</table>
			</td>
			<td>&nbsp;</td>						
			
					
			<td style="border-right:1px solid #898989" height="4px" width="5px" style="font-size:0px"><img width=1px border=0 src="<%=JspUtils.getProjectPath(request)%>/images/gui/transparent.gif" /></td>			
		</tr>
		<tr height="5px">
			<td width=5px height=5px><img width=5px height=5px src="<%=JspUtils.getProjectPath(request)%>/images/gui/bottom-left-corner.gif" /></td>			    
			<td bgcolor="#ffffff" style="border-bottom:1px solid #898989" height="5px" style="font-size:0px"><img width=1px border=0 src="<%=JspUtils.getProjectPath(request)%>/images/gui/transparent.gif" /></td>
			<td bgcolor="#ffffff" style="border-bottom:1px solid #898989" height="5px" style="font-size:0px"><img width=1px border=0 src="<%=JspUtils.getProjectPath(request)%>/images/gui/transparent.gif" /></td>			
			<td bgcolor="#ffffff" style="border-bottom:1px solid #898989" height="5px" style="font-size:0px"><img width=1px border=0 src="<%=JspUtils.getProjectPath(request)%>/images/gui/transparent.gif" /></td>						
			<td width=5px height=5px><img width=5px height=5px src="<%=JspUtils.getProjectPath(request)%>/images/gui/bottom-right-corner.gif" /></td>
		</tr>
</table>
	
</td>
</tr>
</table>
</center>
<%}%>