<%@page import="bussineslogic.controlers.UseCase"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested"%>
<%@ taglib uri="/tags/jim" prefix="jim"%>

<%@page import="utils.userUtils.UserUtils"%>

<% if(!UserUtils.checkRole(request, UseCase.IRBPEOPLE_GRANT_ROLE_NAME) && !UserUtils.checkRole(request, UseCase.IRBPEOPLE_INNOVATION_ROLE_NAME)) { %>

<table cellpadding="0" cellspacing="0" width="900">
	<tr>
		<td><jsp:include page="/pages/common/templates/groupHeader.jsp"> <jsp:param name="HeaderKey" value="personal_comment"/> </jsp:include></td>
	</tr>
	<tr align="center">
		<td class="GroupContent">
			<table class="FormTable" cellpadding="0" cellspacing="0">
	<tr>
		<td align="center">
		<% if(!UserUtils.isRRHH(request)) { %>
	<jsp:include
		page="/pages/common/lists/BOAdderStart_.jsp" flush="true">
		<jsp:param name="BOAdderName" value="personal_comments" />
	</jsp:include> 
	<nested:nest property="personal_comments">
		<nested:hidden property="operation" value="" />
		<nested:nest property="newFormBean">
			<nested:hidden property="personal.personalcode" />
			<table align="center" class="filtro">
				<tr>
					<td class="FormFieldLeft">
					<jim:message key="text" />
					</td>
					<td class="FormFieldRight">
					<nested:textarea property="text" tabindex="30" cols="80" rows="3"/>
					</td>		
					<td>
					<input type="button"
						value="<jim:message key="BOAdderButton"/>"
						onclick="if(this.form.elements['personal_comments.newFormBean.text'].value.length>990){alert('El texto es demasiado largo.')}else{submitAddToBOAdderButton(this.form,'personal_comments')} return false;" 
						/>
					</td>
				</tr>
			</table>
		</nested:nest>
	</nested:nest> 
	<jsp:include page="/pages/common/lists/viewlist_personal_comment.jsp"
		flush="true">
		<jsp:param name="selectionType" value="none" />
		<jsp:param name="objectname" value="personal_comment" />
		<jsp:param name="objectfield"
			value="oPersonalSys__action_manage_personal_comments" />
		<jsp:param name="isReadOnly" value="true" />
		<jsp:param name="formBeanName" value="personal_comment_Form" />
		<jsp:param name="isBOAdder" value="true" />
		<jsp:param name="BOAdderName" value="personal_comments" />
		<jsp:param name="viewlistElementsName" value="viewlistElements2" />
	</jsp:include>
	<%} else { %>
	<jsp:include page="/pages/common/lists/viewlist.jsp"
		flush="true">
		<jsp:param name="selectionType" value="none" />
		<jsp:param name="objectname" value="personal_comment" />
		<jsp:param name="objectfield"
			value="oPersonalSys__action_manage_personal_comments" />
		<jsp:param name="isReadOnly" value="true" />
		<jsp:param name="formBeanName" value="personal_comment_Form" />
		<jsp:param name="viewlistElementsName" value="viewlistElements2" />
	</jsp:include>
	<%} %>
</td>	
</tr>
			</table>
		</td>
	</tr>
</table>
<%} %>