<%@ include file="/pages/common/templates/global.jsp" %>

<script type="text/javascript">

function filtersubmit(form){

		try{
			form['list_config_pagination.firstEntry'].value=0;
			form.action=encodeURI(form.action+'?list_config.submitConfigurator=true');
			form.submit();
		} catch(ex){
			form.action=encodeURI(form.action+'?list_config.submitConfigurator=true');
			form.submit();		
		}
	}	
		
function filterclear(form){
		
		if(form)
		{
			for(var i=0; i < form.elements.length; i++)
			{
				var el=form.elements[i];		
				if( el.name.indexOf('list_config_filter.')==0 )
				{
					el.value='';
				}
				else if(el.name=='month' || el.name=='year')
				{
					el.value='';
				}
			}	
		}
	}

function enterKey(aEvent)
{
	var myEvent = window.event ? window.event : aEvent;
	
	if(myEvent.keyCode==13)
	{
		//filtersubmit(document.forms[0]);
	}
}

</script>
 
<%
	// Recuperamos el reportfield donde se describe el contenido del filtro (atributos, tipo de inputs, etc.)
	org.apache.log4j.Category log_for_filter = org.apache.log4j.Logger.getLogger(HttpServletRequest.class);
	
	ReportCompositionFactory rcf = (ReportCompositionFactory) pageContext.getServletContext().getAttribute("rcf");
	ReportComposition rc = null;
	try {
		rc = rcf.getReportComposition("irb_rrhh");
	}
	catch (Exception e) {log_for_filter.warn(e);}
	
	String filterName = (String) request.getParameter("reportfield");
	
	if(filterName == null || filterName.equals(""))
	{
	    filterName = (String) request.getAttribute("objectname");
	}
	
	try
	{
	
	ReportComObject rco = rc.getReport(filterName);

	Vector visibleFieldNames = rco.getWhere().getVisibleFieldNames();
	Vector visibleLabels = rco.getWhere().getVisibleLabels(UserUtils.getCurrentLocale(request).getLanguage());
	Vector visibleTypes =  rco.getWhere().getVisibleTypeNames();
%>
<center>
<table width=100% border=0 cellspacing=0 cellpadding=0 onkeypress="enterKey(event)">
<tr>
<td>
	<table width=100% border=0 cellspacing=0 cellpadding=0 >
		<tr>
			<td width=5px height=5px><img width=5px height=5px src="<%=JspUtils.getProjectPath(request)%>/images/gui/top-left-corner.gif" /></td>			
			<td bgcolor="#ffffff" style="border-top:1px solid #898989" height="5px" style="font-size:0px"><img width=1px border=0 src="<%=JspUtils.getProjectPath(request)%>/images/gui/transparent.gif" /></td>			
			<td bgcolor="#ffffff" style="border-top:1px solid #898989" height="5px" style="font-size:0px"><img width=1px border=0 src="<%=JspUtils.getProjectPath(request)%>/images/gui/transparent.gif" /></td>			
			<td bgcolor="#ffffff" style="border-top:1px solid #898989" height="5px" style="font-size:0px"><img width=1px border=0 src="<%=JspUtils.getProjectPath(request)%>/images/gui/transparent.gif" /></td>						
			<td width=5px height=5px><img width=5px height=5px src="<%=JspUtils.getProjectPath(request)%>/images/gui/top-right-corner.gif" /></td>
		</tr>
		<tr bgcolor="#ffffff">
			<td style="border-left:1px solid #898989" width="4px" style="font-size:0px"><img width=1px border=0 src="<%=JspUtils.getProjectPath(request)%>/images/gui/transparent.gif" /></td>
			<td>
				<table width=100% border=0 cellspacing=0 cellpadding=0 style="padding-left:3px; padding-right:3px">
				<tr>
<%	
		for(int i=0; i < visibleFieldNames.size(); i++)
		{			
			String nameAux = ((String)visibleFieldNames.get(i)).replace('.', '/');
	%>
			<td align="right"><%= visibleLabels.get(i) %>&nbsp;</td>
	<%		
			if(visibleTypes.get(i).equals("integer")){
			// Numérico: Input - Comparador - Input				
				%>
				<td>			
					<table border=0 cellspacing=0 cellpadding=0>
						<tr>
							<td>
								<html:text name="viewListConfiguration" property="<%="list_config_filter."+nameAux+"_ini_integer"%>"/>
							</td>
							<td>
								<html:select name="viewListConfiguration" property="<%="list_config_filter."+nameAux+"_compareType_integer"%>">
								      <html:option value="equal">=</html:option>
								      <html:option value="notEqual">!=</html:option>
								      <html:option value="greater">&gt;</html:option>
								      <html:option value="greaterEqual">&gt;=</html:option>
								      <html:option value="lower">&lt;</html:option>
								      <html:option value="lowerEqual">&lt;=</html:option>
								      <html:option value="between">[]</html:option>
								</html:select>						
							</td>
							<td>
								<html:text name="viewListConfiguration" property="<%="list_config_filter."+nameAux+"_end_integer"%>"/>
							</td>								
						</tr>						
					</table>							
				</td>
				<%				
			}		

			else if(visibleTypes.get(i).equals("bigdecimal")){
			// Numérico: Input - Comparador - Input
				%>
				<td>				
					<table border=0 cellspacing=0 cellpadding=0>
						<tr>
							<td>
								<html:text name="viewListConfiguration" property="<%="list_config_filter."+nameAux+"_ini_bigdecimal"%>"/>
							</td>
							<td>					
								<html:select name="viewListConfiguration" property="<%="list_config_filter."+nameAux+"_compareType_bigdecimal"%>">
								      <html:option value="equal">=</html:option>
								      <html:option value="notEqual">!=</html:option>
								      <html:option value="greater">&gt;</html:option>
								      <html:option value="greaterEqual">&gt;=</html:option>
								      <html:option value="lower">&lt;</html:option>
								      <html:option value="lowerEqual">&lt;=</html:option>
								      <html:option value="between">[]</html:option>
								</html:select>					
							</td>
							<td>					
								<html:text name="viewListConfiguration" property="<%="list_config_filter."+nameAux+"_end_bigdecimal"%>"/>
							</td>
						</tr>
					</table>						
				</td>
				<%				
			}		
			else if(visibleTypes.get(i).equals("boolean")){
				Locale loc = UserUtils.getCurrentLocale(request);
				ResourceBundle patternsBundle = ResourceBundle.getBundle("Patterns",loc);
				String trueEtiq = patternsBundle.getString("trueEtiq");
				String falseEtiq = patternsBundle.getString("falseEtiq");
			%>
				<td>
				<html:select name="viewListConfiguration" property="<%="list_config_filter."+nameAux+"_boolean"%>">
					<html:option value="<%="" %>"></html:option>
					<html:option value="<%=trueEtiq%>"><%=trueEtiq%></html:option>
					<html:option value="<%=falseEtiq%>"><%=falseEtiq%></html:option>
				</html:select>
				</td>
				
				<%
				}	
			else if(visibleTypes.get(i).equals("date")){
			// Fechas: Input - Input				
				%>
				<td>
					<table border=0 cellspacing=0 cellpadding=0>
						<tr>
							<td>
								<jim:TagDate name="viewListConfiguration" property="<%="list_config_filter."+nameAux+"_ini_date"%>"/>
							</td>
							<td>
								<jim:TagDate name="viewListConfiguration" property="<%="list_config_filter."+nameAux+"_end_date"%>"/>				
							</td>
						</tr>					
					</table>					
				</td>					
				<%				
			}
	
			else/*if(visibleTypes.get(i).equals("text"))*/{
			// Texto: Input
				%>
				<td>
				<html:text name="viewListConfiguration" property="<%="list_config_filter."+nameAux+"_string"%>"/>
				</td>
				<%				
			}
	
		    if( i % 2 == 1)
		    {
	%>
			</tr><%
			if(i < visibleFieldNames.size()-1){
				// Equilibramos la tabla (las filas tienen dos columnas de atributos)
			    %>		    
			<tr>
	<%
			}	 
		}
	}
%>
				</table>
			</td>
			<td>&nbsp;</td>						
			
			<td>
				<html:submit onclick="filtersubmit(this.form);return false;" >Filtrar</html:submit>
			</td>						
			<td style="border-right:1px solid #898989" height="4px" width="5px" style="font-size:0px"><img width=1px border=0 src="<%=JspUtils.getProjectPath(request)%>/images/gui/transparent.gif" /></td>			
		</tr>
		<tr height="5px">
			<td width=5px height=5px><img width=5px height=5px src="<%=JspUtils.getProjectPath(request)%>/images/gui/bottom-left-corner.gif" /></td>			    
			<td bgcolor="#ffffff" style="border-bottom:1px solid #898989" height="5px" style="font-size:0px"><img width=1px border=0 src="<%=JspUtils.getProjectPath(request)%>/images/gui/transparent.gif" /></td>
			<td bgcolor="#ffffff" style="border-bottom:1px solid #898989" height="5px" style="font-size:0px"><img width=1px border=0 src="<%=JspUtils.getProjectPath(request)%>/images/gui/transparent.gif" /></td>			
			<td bgcolor="#ffffff" style="border-bottom:1px solid #898989" height="5px" style="font-size:0px"><img width=1px border=0 src="<%=JspUtils.getProjectPath(request)%>/images/gui/transparent.gif" /></td>						
			<td width=5px height=5px><img width=5px height=5px src="<%=JspUtils.getProjectPath(request)%>/images/gui/bottom-right-corner.gif" /></td>
		</tr>
</table>
	
</td>
</tr>
</table>
</center>
&nbsp;
<%
	}
	catch(Exception e)
	{log_for_filter.warn(e);}
%>
