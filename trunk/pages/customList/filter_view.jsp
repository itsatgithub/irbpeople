<%@ include file="/pages/common/templates/global.jsp"%>

<%@page import="java.lang.reflect.Method"%>
<%@page import="utils.hibernate.HibernateUtil"%>
<%@page import="org.hibernate.SQLQuery" %>
<%@page import="java.util.regex.*" %>

<script type="text/javascript" src="<%=JspUtils.getProjectPath(request)%>/common/formUtils.js"></script>

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
	
	String filterName = (String) request.getAttribute("view_name");
	List<String[]> savedfilter = (List<String[]>) request.getAttribute("savedFilters");
	
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


<html:hidden property="list_config.submitConfigurator" value="true" />
<center>
<table width=100% border=0 cellspacing=0 cellpadding=0>
	<tr>
		<td>
		<div id="proving">
		</div>
		<table width=100% border=0 cellspacing=0 cellpadding=0>
			<tr>
				<td width=5px height=5px><img width=5px height=5px
					src="<%=JspUtils.getProjectPath(request)%>/images/gui/top-left-corner.gif" /></td>
				<td bgcolor="#ffffff" style="border-top: 1px solid #898989"
					height="5px" style="font-size:0px"><img width=1px border=0
					src="<%=JspUtils.getProjectPath(request)%>/images/gui/transparent.gif" /></td>
				<td bgcolor="#ffffff" style="border-top: 1px solid #898989"
					height="5px" style="font-size:0px"><img width=1px border=0
					src="<%=JspUtils.getProjectPath(request)%>/images/gui/transparent.gif" /></td>
				<td bgcolor="#ffffff" style="border-top: 1px solid #898989"
					height="5px" style="font-size:0px"><img width=1px border=0
					src="<%=JspUtils.getProjectPath(request)%>/images/gui/transparent.gif" /></td>
				<td width=5px height=5px><img width=5px height=5px
					src="<%=JspUtils.getProjectPath(request)%>/images/gui/top-right-corner.gif" /></td>
			</tr>
			<tr bgcolor="#ffffff">
				<td style="border-left: 1px solid #898989" width="4px"
					style="font-size:0px"><img width=1px border=0
					src="<%=JspUtils.getProjectPath(request)%>/images/gui/transparent.gif" /></td>
				<td>
				<table width=100% border=0 cellspacing=8 cellpadding=0
					style="padding-left: 3px; padding-right: 3px">

					<%
						for (int i = 0; i < visibleFieldNames.size(); i++) {
								String nameAux = ((String) visibleFieldNames.get(i))
										.replace('.', '/');
								//if nameAux està contingut en la llista de filtres guardats.. value = value guardat, else value = null

								String filtervalue = "";
								String filtertype = "";
								String filtervalue2 = "";
								String disabled = "disabled = \"\" ";

								if (savedfilter != null) {
									Iterator<String[]> itfilter = savedfilter.iterator();
									while (itfilter.hasNext()) {
										String[] temp = itfilter.next();
										if (temp[0].equals(nameAux)) {
											filtervalue = temp[1];
											filtertype = temp[2];
											if(filtertype.equals("between")){
												filtervalue2 = temp[3];
											}
											disabled = "";
											break;
										}
									}
								}
					%>
					<tr>
						<td align="right" style="padding-right: 20px;"><%=visibleLabels.get(i)%></td>
						<%
							if (visibleTypes.get(i).equals("integer")) {
										// Numérico: Input - Comparador - Input
						%>
						<td align="left">
								
						
						<table>
							<tr>
								<td>
								<script type="text/javascript">
								$(document).ready(function(){
									$('select[@name=<%="list_config_filter."+nameAux+"_compareType_integer"%>]').change(function () {
																	
										enableDisable2Fields(document.forms[0].elements['<%="list_config_filter."+nameAux+"_compareType_integer"%>'],document.forms[0].elements['<%="list_config_filter."+nameAux+"_ini_integer"%>'], document.forms[0].elements['<%="list_config_filter."+nameAux+"_end_integer"%>'])
									});
																
																	
								});
								
								</script>
								
								<html:select									
									name="viewListConfiguration"
									property="<%="list_config_filter."+nameAux+"_compareType_integer"%>" value="<%=filtertype %>"
									 >
									<html:option value="">	</html:option>
									<html:option value="equal">=</html:option>
									<html:option value="notEqual">!=</html:option>
									<html:option value="greater">&gt;</html:option>
									<html:option value="greaterEqual">&gt;=</html:option>
									<html:option value="lower">&lt;</html:option>
									<html:option value="lowerEqual">&lt;=</html:option>
									<html:option value="between">between</html:option>
								</html:select>
								
								
								</td>
								
								<td>
								<%if(filtervalue.equals("")){ %>
									<html:text name="viewListConfiguration"
										property="<%="list_config_filter."+nameAux+"_ini_integer"%>" value="<%=filtervalue %>" disabled="true"/>
								<%}else{%>
								<html:text name="viewListConfiguration"
										property="<%="list_config_filter."+nameAux+"_ini_integer"%>" value="<%=filtervalue %>" />
								<%} %>
								</td>

								<td>
								<%if(filtertype.equals("between")){ %>
								<html:text name="viewListConfiguration"
									property="<%="list_config_filter."+nameAux+"_end_integer"%>" value="<%=filtervalue2 %>"/>
									<%}else{ %>
								<html:text name="viewListConfiguration"
									property="<%="list_config_filter."+nameAux+"_end_integer"%>" disabled="true"/>
								
								<%} %>
								</td>
							</tr>
						</table>
						</td>
						<%
							}

									else if (visibleTypes.get(i).equals("bigdecimal")) {
										// Numérico: Input - Comparador - Input
						%>
							<td align="left">
						<table>
							<tr>
								<td>
								
								<script type="text/javascript">
								$(document).ready(function(){
									$('select[@name=<%="list_config_filter."+nameAux+"_compareType_bigdecimal"%>]').change(function () {
																	
										enableDisable2Fields(document.forms[0].elements['<%="list_config_filter."+nameAux+"_compareType_bigdecimal"%>'],document.forms[0].elements['<%="list_config_filter."+nameAux+"_ini_bigdecimal"%>'], document.forms[0].elements['<%="list_config_filter."+nameAux+"_end_bigdecimal"%>'])
									});
																
																	
								});
								
								</script>
								
								<html:select name="viewListConfiguration"
									property="<%="list_config_filter."+nameAux+"_compareType_bigdecimal"%>" value="<%=filtertype %>">
									<html:option value="">	</html:option>
									<html:option value="equal">=</html:option>
									<html:option value="notEqual">!=</html:option>
									<html:option value="greater">&gt;</html:option>
									<html:option value="greaterEqual">&gt;=</html:option>
									<html:option value="lower">&lt;</html:option>
									<html:option value="lowerEqual">&lt;=</html:option>
									<html:option value="between">between</html:option>
								</html:select></td>
								<td>
								
								<%if(filtervalue.equals("")){ %>
								<html:text name="viewListConfiguration"
									property="<%="list_config_filter."+nameAux+"_ini_bigdecimal"%>" value="<%=filtervalue %>" disabled="true"/>
								<%}else{%>
								<html:text name="viewListConfiguration"
									property="<%="list_config_filter."+nameAux+"_ini_bigdecimal"%>" value="<%=filtervalue %>"/>
								<%} %>
								
								</td>

								<td>
								<%if(filtertype.equals("between")){ %>
								<html:text name="viewListConfiguration"
									property="<%="list_config_filter."+nameAux+"_end_bigdecimal"%>" value="<%=filtervalue2 %>"/>
								
								<%}else{ %>
								<html:text name="viewListConfiguration"
									property="<%="list_config_filter."+nameAux+"_end_bigdecimal"%>" disabled="true"/>
								
								<%} %>
								</td>
							</tr>
						</table>
						</td>
						<%
							} else if (visibleTypes.get(i).equals("boolean")) {
										Locale loc = UserUtils.getCurrentLocale(request);
										ResourceBundle patternsBundle = ResourceBundle
												.getBundle("Patterns", loc);
										String trueEtiq = patternsBundle.getString("trueEtiq");
										String falseEtiq = patternsBundle
												.getString("falseEtiq");
						%>
							<td align="left">
						<table>
							<tr>
								<td>
								
							<html:select name="viewListConfiguration"
							property="<%="list_config_filter."+nameAux+"_boolean"%>" value="<%=filtervalue %>">
							<html:option value="<%="" %>"></html:option>
							<%-- OJO: nuestro utils.beanUtils.converters.BooleanConverter solo devuelve true si value=trueEtiq que depende del locale --%>
							<html:option value="<%=trueEtiq %>"><%=trueEtiq%></html:option>
							<html:option value="<%=falseEtiq %>"><%=falseEtiq%></html:option>
						</html:select></td></tr></table></td>

						<%
							} else if (visibleTypes.get(i).equals("date")) {
										// Fechas: Input - Input
						%>
							<td align="left">
						<table>
							<tr>
								<td>
								
								<script type="text/javascript">
								$(document).ready(function(){
									$('select[@name=<%="list_config_filter."+nameAux+"_compareType_date"%>]').change(function () {
																	
										enableDisable2Fields(document.forms[0].elements['<%="list_config_filter."+nameAux+"_compareType_date"%>'],document.forms[0].elements['<%="list_config_filter."+nameAux+"_ini_date"%>'], document.forms[0].elements['<%="list_config_filter."+nameAux+"_end_date"%>'])
									});
																
																	
								});
								
								</script>
								<html:select name="viewListConfiguration"
									property="<%="list_config_filter."+nameAux+"_compareType_date"%>" value="<%=filtertype %>">
									<html:option value="">
									</html:option>
									<html:option value="equal">equal</html:option>
									<html:option value="lowerEqual">before</html:option>
									<html:option value="greaterEqual">after</html:option>
									<html:option value="between">between</html:option>

								</html:select></td>
								<td>
								<%if(filtertype.equals("")){ %>
								  <jim:TagDate name="viewListConfiguration"
									 property="<%="list_config_filter."+nameAux+"_ini_date"%>" value="<%=filtervalue %>" disabled="true"/>
								<%}else{ %>
								  <jim:TagDate name="viewListConfiguration"
									 property="<%="list_config_filter."+nameAux+"_ini_date"%>" value="<%=filtervalue %>" />
								<%} %>
								</td>
								<td>
								
								<%if(filtertype.equals("between")){ %>
								<jim:TagDate name="viewListConfiguration"
									property="<%="list_config_filter."+nameAux+"_end_date"%>" value="<%=filtervalue2 %>"/>
								
								<%}else{ %>
						<jim:TagDate name="viewListConfiguration"
									property="<%="list_config_filter."+nameAux+"_end_date"%>" disabled="true" />
								
								<%} %>
								
								
									
								</td>
							</tr>
						</table>
						</td>
						<%
							} else if (visibleTypes.get(i).toString().startsWith("select")) {
										// Select
						%>
							<td align="left">
						<table>
							<tr>
								<td><html:hidden property="<%="list_config_filter."+nameAux+"_compareType_string"%>"
												value="equal" /> 
												
								 
									
									<html:select name="viewListConfiguration"
												property="<%="list_config_filter."+nameAux+"_string"%>" value="<%=filtervalue %>">
									<html:option value="">
									</html:option>
									<%
										//filterName

									String queryString = visibleTypes.get(i).toString().toLowerCase();
									String deletedCondition = " where deleted=0 ";
									
									if(queryString.contains(" where "))
									{
										deletedCondition = " and deleted=0 ";    
									}
									
									int index = queryString.indexOf("group by");
									
									if(index == -1)
									{
									    index = queryString.indexOf("order by");
									}
									
									Pattern p = Pattern.compile("\\bdeleted\\b");
									Matcher m = p.matcher(queryString);
									
									if(!m.find())
									{
									    if(index==-1)
									    {
									        queryString += deletedCondition;
									    }
									    else
									    {
									        queryString = queryString.substring(0,index) + deletedCondition + queryString.substring(index);
									    }
									}
										
									SQLQuery query = HibernateUtil.getSession().createSQLQuery(queryString);
									
									List<String> select_items = query.list(); 
									    
									Iterator<String> it = select_items.iterator();

									while (it.hasNext()) {
											String temp = it.next();
									%>
									<html:option value="<%=temp%>"><%=temp%></html:option>
									<%
										}
									%>


								</html:select></td>

							</tr>
						</table>
						</td>
						<%
							}

									else/*if(visibleTypes.get(i).equals("text"))*/{
										// Texto: Input
						%>
						<td align="left">

						<table>
							<tr>
								<td>
								<script type="text/javascript">
									$(document).ready(function(){
									$('select[@name=<%="list_config_filter."+nameAux+"_compareType_string"%>]').change(function () {
																	
										enableDisableField(document.forms[0].elements['<%="list_config_filter."+nameAux+"_compareType_string"%>'],document.forms[0].elements['<%="list_config_filter."+nameAux+"_string"%>'])
									});
																
																	
									});
								
								</script>
								
								<html:select name="viewListConfiguration"
									property="<%="list_config_filter."+nameAux+"_compareType_string"%>" value="<%=filtertype %>">
									<html:option value="">
									</html:option>
									<html:option value="equal">equal</html:option>
									<html:option value="like">similar</html:option>

								</html:select></td>
								<td>
								<%if(filtertype.equals("")){ %>
								<html:text name="viewListConfiguration"
									property="<%="list_config_filter."+nameAux+"_string"%>" value="<%=filtervalue %>" disabled="true"/>
								<%}else{ %>
								<html:text name="viewListConfiguration"
									property="<%="list_config_filter."+nameAux+"_string"%>" value="<%=filtervalue %>"/>
								<%} %>	
								</td>
							</tr>
						</table>

						</td>
						<%
							}
						%>
					</tr>

					<%
						}
					%>
				</table>
				</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>

				<td style="border-right: 1px solid #898989" height="4px" width="5px"
					style="font-size:0px"><img width=1px border=0
					src="<%=JspUtils.getProjectPath(request)%>/images/gui/transparent.gif" /></td>
			</tr>
			<tr height="5px">
				<td width=5px height=5px><img width=5px height=5px
					src="<%=JspUtils.getProjectPath(request)%>/images/gui/bottom-left-corner.gif" /></td>
				<td bgcolor="#ffffff" style="border-bottom: 1px solid #898989"
					height="5px" style="font-size:0px"><img width=1px border=0
					src="<%=JspUtils.getProjectPath(request)%>/images/gui/transparent.gif" /></td>
				<td bgcolor="#ffffff" style="border-bottom: 1px solid #898989"
					height="5px" style="font-size:0px"><img width=1px border=0
					src="<%=JspUtils.getProjectPath(request)%>/images/gui/transparent.gif" /></td>
				<td bgcolor="#ffffff" style="border-bottom: 1px solid #898989"
					height="5px" style="font-size:0px"><img width=1px border=0
					src="<%=JspUtils.getProjectPath(request)%>/images/gui/transparent.gif" /></td>
				<td width=5px height=5px><img width=5px height=5px
					src="<%=JspUtils.getProjectPath(request)%>/images/gui/bottom-right-corner.gif" /></td>
			</tr>
		</table>

		</td>
	</tr>
</table>
</center>
&nbsp;
<%
	} catch (Exception e) {
		log_for_filter.warn(e);
	}
%>
