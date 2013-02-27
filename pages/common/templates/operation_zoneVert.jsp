<%@ include file="/pages/common/templates/global.jsp" %>
<%@page import="utils.jsp.ZoneConstructorUtils"%>
<%@page import="utils.jsp.ZoneConstructorUtils.RefActionButtonData"%>

<table border="0" cellspacing="0" cellpadding="0" >

<%
ZoneConstructorUtils zoneConstructor=new ZoneConstructorUtils(request, pageContext, "operation");

if(zoneConstructor.hasItems())
{
	for(ZoneConstructorUtils.RefActionButtonData actionData: zoneConstructor.getActions())
	{        
	%>  
	<tr>
	    <td align="<%=utils.jsp.Constants.operation_align %>">
	    
		<jsp:include page="/pages/common/templates/operationButton.jsp" flush="true">
			<jsp:param name="CaptionKey" value="<%=actionData.getCaption()%>" />
			<jsp:param name="ConceptKey" value="<%=actionData.getConcept()%>" />
			<jsp:param name="FunctionKey" value="<%=actionData.getSubmitFunctionCall() %>" />
		</jsp:include>

    	</td>
	</tr>
	<%//if (j!=items.length-1){ %>
    	<tr><td class="OperationSeparator"></td></tr>
    <%//}     
	} // for
} // if
%>

</table>
