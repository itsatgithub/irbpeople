<%@ include file="/pages/common/templates/global.jsp" %>
<% String boAdderName = (String)request.getParameter("BOAdderName");	
%><script type="text/javascript">
<%-- 
Execución del botón de añadir al BOAdder
--%>
function submitAddToBOAdderButton(form){
	form.action=encodeURI(form.action+'?<%=boAdderName%>.operation=add');
	form.submit;
}
</script>
	<!-- TABLA CONTENEDORA -->
		<table class="BOAdder" border=0 cellspacing=0 cellpadding=0 align="center">
		<tr>
			<td width=5px height=5px><img width=5px height=5px src="<%=JspUtils.getProjectPath(request)%>/images/gui/boadder-corner-top-left.gif" /></td>			

			<td class="BOAdderElement" style="border-top:1px solid #898989" height="5px" style="font-size:0px"><img width=1px border=0 src="<%=JspUtils.getProjectPath(request)%>/images/gui/transparent.gif" /></td>			

			<td width=5px height=5px><img width=5px height=5px src="<%=JspUtils.getProjectPath(request)%>/images/gui/boadder-corner-top-right.gif" /></td>
		</tr>
		<tr>
			<td class="BOAdderElement" style="border-left:1px solid #898989" width="4px" style="font-size:0px"><img width=1px border=0 src="<%=JspUtils.getProjectPath(request)%>/images/gui/transparent.gif" /></td>

			<td class="BOAdderElement" align="left">	
			<!-- Tabla de añadir -->