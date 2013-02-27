<%@ include file="/pages/common/templates/global.jsp" %>
<%@page import="utils.jsp.ZoneConstructorUtils"%>
<%@page import="utils.jsp.ZoneConstructorUtils.RefActionButtonData"%>

<table border="0" cellspacing="0" cellpadding="0" width=100%>
<tr>
<%
ZoneConstructorUtils zoneConstructor=new ZoneConstructorUtils(request, pageContext, "rowaction0");

if(zoneConstructor.hasItems())
{
	for(ZoneConstructorUtils.RefActionButtonData actionData: zoneConstructor.getActions())
	{      
	%>  
	    <td> 
		<jsp:include page="/pages/common/templates/goButton.jsp" flush="true">
			<jsp:param name="CaptionKey" value="<%=actionData.getCaption()%>" />
			<jsp:param name="ConceptKey" value="<%=actionData.getConcept()%>" />
			<jsp:param name="FunctionKey" value="<%=actionData.getSubmitFunctionCall() %>" />
		</jsp:include>
		</td>

    	<%//if (j!=items.length-1){ %>
    		<td class="GoSeparator">&nbsp;</td>
    	<%//}
    
	} // for
} // if
%>
<td width="100%">&nbsp;</td>
</tr>
</table>
