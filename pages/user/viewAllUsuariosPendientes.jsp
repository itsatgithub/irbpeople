<%@ include file="/pages/common/templates/global.jsp" %>

<logic:notPresent name="org.apache.struts.action.MESSAGE"
	scope="application">
	<font color="red"> ERROR: Application resources not loaded --
	check servlet container logs for error messages. </font>
</logic:notPresent>

<html:errors />

<table cellpadding=0 cellspacing=0>
	<tr>
		<td><jsp:include page="/pages/common/templates/groupHeader.jsp">
			<jsp:param name="HeaderKey"
				value="groupheaders.gestionarusuariospendientes" />
		</jsp:include></td>
	</tr>
	<tr align="center">
		<td class="GroupContent"><html:form
			action="<%=JspUtils.getCurrentAction(request)%>">

			<jsp:include page="/pages/common/lists/filter.jsp" flush="true">
				<jsp:param name="reportfield" value="usuario" />
			</jsp:include>

			<jsp:include page="/pages/common/lists/viewlist.jsp" flush="true">
				<jsp:param name="selectionType" value="single" />
				<jsp:param name="objectname" value="usuario" />
				<jsp:param name="formBeanName" value="usuarioForm" />
				<jsp:param name="isReadOnly" value="true" />
			</jsp:include>
		</html:form></td>
	</tr>
</table>





