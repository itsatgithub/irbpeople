<%@ include file="/pages/common/templates/global.jsp" %>
<%@page import="utils.jsp.ZoneConstructorUtils"%>

<table border="0" cellspacing="0" cellpadding="0">
<tr>
<%

ZoneConstructorUtils zoneConstructor=new ZoneConstructorUtils(request, pageContext, "go");

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
    		<td class="GoSeparator"></td>
    	<%//}
    
	} // for
} // if
%>
</tr>
</table>
