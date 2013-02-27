<%@ include file="/pages/common/templates/global.jsp" %>

<logic:notPresent name="org.apache.struts.action.MESSAGE"
	scope="application">
	<font color="red"> ERROR: Application resources not loaded --
	check servlet container logs for error messages. </font>
</logic:notPresent>

<table border=0 cellspacing=0 cellpadding=0>
	<tr><td>
		<div id="myMenuID" align="center"></div>
		<script type="text/javascript"><!--
			cmDraw ('myMenuID', MENU_ITEMS, 'hbr', cmThemeSpinweb);
		--></script>	
	</td></tr>
</table>
