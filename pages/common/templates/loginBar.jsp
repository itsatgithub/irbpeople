<%@ include file="/pages/common/templates/global.jsp" %>

<table width="100%" cellpadding=0 cellspacing=0 border=0>
	<tr>
		<td width=160px><img src="../images/gui/app-name.gif" height=50px/></td>
		<%
		if (UserUtils.isLogged(request)) {
		%>
		<td align="left">
		<table border=0 cellpadding=0 cellspacing=0>
			<tr height=23px>
				<td width=5px
					style="background-image:url(../images/gui/username-left.gif)">&nbsp;</td>
				<td height=23px background="../images/gui/username-center.gif"
					style="padding-right:6px;"><b><jim:message
					key="login.currentUser" /></b></td>
<%
	String nombre=UserUtils.getCurrentUsuario(request).getPersonal().toString();
	if(nombre==null || nombre.trim().equals("")) nombre=UserUtils.getCurrentUsername(request);

%>					
				<td height=23px background="../images/gui/username-center.gif"><%= nombre %></td>
				<td width=6px
					style="background-image:url(../images/gui/username-right.gif)">&nbsp;</td>
			</tr>
		</table>
		</td>
		<td align="right"><table border=0 cellpadding=0 cellspacing=0>
			<tr>
				<jsp:include flush="true" page="help_button.jsp"></jsp:include>
				
				<td>
					<html:link forward="homePage"
						style="position:relative;top:0;left:0;border:0;">
					<img TITLE="<jim:message key="login.loginbutton" />"
						src="../images/CATEGORY/action-type/home.gif"
						style="position:relative;top:0;left:0;border:0;" />
					</html:link>
				</td>
				<td>&nbsp;</td>
				<td>
		<html:link page="/login/doLogout.do"
			style="position:relative;top:0;left:0;border:0;">
			<img TITLE="<jim:message key="login.logout" />"
				src="../images/CATEGORY/action-type/logout.gif"
				style="position:relative;top:0;left:0;border:0;" />
		</html:link>
		</td>
			</tr>		
		</table></td>
		<%
		} else {
		%>
		<td>
		<table border=0 cellpadding=0 cellspacing=0>
			<tr height=23px>
				<td width=5px
					style="background-image:url(../images/gui/username-left.gif)">&nbsp;</td>
				<td height=23px background="../images/gui/username-center.gif"
					style="padding-right:6px;"><b><jim:message
					key="login.currentUser" /></b></td>
				<td height=23px background="../images/gui/username-center.gif"><%="-"%></td>
				<td width=6px
					style="background-image:url(../images/gui/username-right.gif)">&nbsp;</td>
			</tr>
		</table>
		</td>
		<td align="right"><html:link page="/login/displayLoginForm.do"
			style="position:relative;top:0;left:0;border:0;">
			<img TITLE="<jim:message key="login.loginbutton" />"
				src="../images/CATEGORY/action-type/login.gif"
				style="position:relative;top:0;left:0;border:0;" />
		</html:link></td>
		<%
		}
		%>
	</tr>
	<tr height=5px><td></td></tr>
</table>
