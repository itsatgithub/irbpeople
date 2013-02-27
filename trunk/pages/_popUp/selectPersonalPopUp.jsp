<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested"%>
<%@ taglib uri="/tags/jim" prefix="jim"%>
<%@ taglib uri="/tags/nested-jim" prefix="nested-jim"%>
<%@page import="utils.jsp.JspUtils"%>

<logic:notPresent name="org.apache.struts.action.MESSAGE" scope="application">
	<font color="red">
		ERROR: Application resources not loaded -- check servlet container logs for error messages. 
	</font>
</logic:notPresent>

<html:form action="<%=JspUtils.getCurrentAction(request)%>">
	<html:hidden property="id"/>

<% String objectField=request.getParameter("objectField");%>

<input type="hidden" name="objectField" value="<%=objectField %>"/>

<jsp:include page="/pages/common/lists/filter.jsp" flush="true">
	<jsp:param name="reportfield" value="<%=objectField %>"/>
</jsp:include>

<jsp:include page="/pages/common/lists/viewlist.jsp" flush="true">
	<jsp:param name="selectionType" value="none"/>
	<jsp:param name="objectname" value="personal"/>
	<jsp:param name="objectfield" value="<%=objectField %>"/>
	<jsp:param name="isReadOnly" value="true"/>
	<jsp:param name="formBeanName" value="personal_Form" />
	<jsp:param name="isSelectPopUp" value="true"/>
</jsp:include>


</html:form>