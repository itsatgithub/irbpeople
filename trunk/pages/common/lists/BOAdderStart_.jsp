<%@ include file="/pages/common/templates/global.jsp" %>
<% String boAdderName = (String)request.getParameter("BOAdderName");	
%><script type="text/javascript">
<%-- 
Execuci�n del bot�n de a�adir al BOAdder
--%>
var alreadySubmitted = false;
function submitAddToBOAdderButton(form, boAdderName){
	form.action=encodeURI(form.action+'?'+boAdderName+'.operation=add');
	if(dobleSubmitPrevent()){
		form.submit();
	}	
}
</script>