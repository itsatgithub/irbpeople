<%@ include file="/pages/common/templates/global.jsp" %>

<logic:notPresent name="org.apache.struts.action.MESSAGE"
	scope="application">
	<font color="red"> ERROR: Application resources not loaded --
	check servlet container logs for error messages. </font>
</logic:notPresent>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr><td width="100%">
		<div id="myMenuID" width="100%"></div>
		<script type="text/javascript"><!--
			 cmDraw ('myMenuID', MENU_ITEMS, 'hbr', cmThemeSpinweb);
		--></script>	
	</td></tr>
</table>
