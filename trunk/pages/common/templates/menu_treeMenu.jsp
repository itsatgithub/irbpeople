<%@ include file="/pages/common/templates/global.jsp" %>

<logic:notPresent name="org.apache.struts.action.MESSAGE"
	scope="application">
	<font color="red"> ERROR: Application resources not loaded --
	check servlet container logs for error messages. </font>
</logic:notPresent>

<table  height="100%">
	<tr valign="top"><td>
		<div id="myMenuID" ></div>
		<script type="text/javascript"><!--
			ctDraw ('myMenuID', MENU_ITEMS, ctThemeXP1, 'menuPrincipal', 0, 0);
			ctExpandTree('myMenuID',1);
		--></script>	
	</td></tr>
</table>