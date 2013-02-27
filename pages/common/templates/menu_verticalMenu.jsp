<%@ include file="/pages/common/templates/global.jsp" %>

<logic:notPresent name="org.apache.struts.action.MESSAGE"
	scope="application">
	<font color="red"> ERROR: Application resources not loaded --
	check servlet container logs for error messages. </font>
</logic:notPresent>

<table  border=0 cellspacing=0 cellpadding=0 align="center" >
	<tr height=5px>
		<td width=5px height=5px><img width=5px height=5px src="<%=JspUtils.getProjectPath(request)%>/images/gui/menu-corner-top-left.gif" /></td>			
		<td class="MenuContainerElement" style="border-top:1px solid #787F8A" height="5px" style="font-size:0px"><img width=1px border=0 src="<%=JspUtils.getProjectPath(request)%>/images/gui/transparent.gif" /></td>			
		<td width=5px height=5px><img width=5px height=5px src="<%=JspUtils.getProjectPath(request)%>/images/gui/menu-corner-top-right.gif" /></td>
	</tr>
	<tr height=100%>
		<td class="MenuContainerElement" style="border-left:1px solid #787F8A" width="4px" style="font-size:0px"><img width=1px border=0 src="<%=JspUtils.getProjectPath(request)%>/images/gui/transparent.gif" /></td>
		<!-- Tabla de añadir -->
<!--<table border="0" cellpadding="0" cellspacing="0" height="100%">
	<tr valign="top">-->
		<td class="menuVertContainer" valign="top">
			<div id="myMenuID" align="center"></div>
			<script type="text/javascript"><!--
				cmDraw ('myMenuID', MENU_ITEMS, 'vbr', cmThemeSpinweb);
			--></script>	
		</td>
		<td class="MenuContainerElement" style="border-right:1px solid #787F8A" height="4px" width="5px" style="font-size:0px"><img width=1px border=0 src="<%=JspUtils.getProjectPath(request)%>/images/gui/transparent.gif" /></td>
	<tr height=5px>
		<td  width=5px height=5px><img width=5px height=5px src="<%=JspUtils.getProjectPath(request)%>/images/gui/menu-corner-bottom-left.gif" /></td>			    
		<td class="MenuContainerElement" style="border-bottom:1px solid #787F8A" height="5px" style="font-size:0px"><img width=1px border=0 src="<%=JspUtils.getProjectPath(request)%>/images/gui/transparent.gif" /></td>	
		<td width=5px height=5px><img width=5px height=5px src="<%=JspUtils.getProjectPath(request)%>/images/gui/menu-corner-bottom-right.gif" /></td>
	</tr>			
</table>
