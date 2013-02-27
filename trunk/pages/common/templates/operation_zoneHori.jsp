<%@ include file="/pages/common/templates/global.jsp" %>
<%@page import="utils.jsp.ZoneConstructorUtils"%>

<table border="0" cellspacing="0" cellpadding="0" >

<%

ZoneConstructorUtils zoneConstructor=new ZoneConstructorUtils(request, pageContext, "operation");

if(zoneConstructor.hasItems())
{
	for(ZoneConstructorUtils.RefActionButtonData actionData: zoneConstructor.getActions())
	{         
	%>  
	    <td >

		<jsp:include page="/pages/common/templates/operationButton.jsp" flush="true">
			<jsp:param name="CaptionKey" value="<%=actionData.getCaption()%>" />
			<jsp:param name="ConceptKey" value="<%=actionData.getConcept()%>" />
			<jsp:param name="FunctionKey" value="<%=actionData.getSubmitFunctionCall() %>" />
		</jsp:include>

    	</td>

	<%//if (j!=items.length-1){ %>
    	<td class="OperationSeparator"></td>
    <%//}     
	} // for
} // if

%>

</table>
