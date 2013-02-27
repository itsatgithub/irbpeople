<%@ include file="/pages/common/templates/global.jsp" %>
<%@page import="utils.jsp.ZoneConstructorUtils"%>
<%@page import="utils.jsp.ZoneConstructorUtils.RefActionButtonData"%>

<script type="text/javascript">

<%-- 

Control de la ordenación:
En función del atributo a ordenar, comprueba el sentido (ascendente/descendente) y
modifica los valores del ListConfigurator pertenecientes a la ordenación

--%>

function sort(form,order){
					
		var lastAttribure = form['list_config_orderby.attribute'].value;
		var lastAsc = form['list_config_orderby.asc'].value
		
		form['list_config_orderby.attribute'].value = order;

		if(lastAttribure == order) 
			form['list_config_orderby.asc'].value = lastAsc == "asc" ? "desc" : "asc";
		else			
			form['list_config_orderby.asc'].value = "asc";
		form.action=encodeURI(form.action+'?list_config.submitConfigurator=true');
		form.submit();
		
	}

<%-- 

Control de la paginación:
En función de si se ha apretado el botón bw (backward=atrás) o fw (forward=adelante)
modifica los valores del ListConfigurator incrementando o decrementando la primera linea
a mostrar y actualizando el máximo de resultados que quiere visualizar el usuario.

--%>
function paginate(form,go,count){
			
		var lastIndex = parseInt(form['list_config_pagination.firstEntry'].value);
		var maxEntries = parseInt(form['list_config_pagination.maxResults'].value);

		if(go=='fw'){
			if(lastIndex+maxEntries < count) {
				form['list_config_pagination.firstEntry'].value = lastIndex+maxEntries+'';							
				}		
		}
			
		else if(go=='bw'){
			if(lastIndex - maxEntries > 0){ 						
				form['list_config_pagination.firstEntry'].value = lastIndex-maxEntries+'';
				}
			else{
				form['list_config_pagination.firstEntry'].value = 0;
				}
		}
		form.action=encodeURI(form.action+'?list_config.submitConfigurator=true');
		form.submit();
		
	}	
	
function copySelectedBean(code, text){
	window.opener.textTarget.value=text;
	window.opener.textTarget.size=text.length;
	
	window.opener.hiddenTarget.value=code;
	window.close();
}
	
function submitRowAction(url,form,name,value){	
	newUrl=encodeURI(url + '.do'+'?'+name+'='+value);
	window.location.assign(newUrl);
//	form.action=encodeURI(url + '.do'+'?'+name+'='+value);
//	form.submit;

}

function submitRowActionInPopUp(url,form,name,value){
	//openViewdetailsPopup(url + '.do'+'?'+name+'='+value);
	var destinationUrl = url+ '.do'+'?'+name+'='+value+"&isPopUpWindow=true";

	popupWindow.setUrl(destinationUrl);
	popupWindow.autoHide();
	popupWindow.setSize(750,600);
	popupWindow.windowProperties = 'toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,alwaysRaised,dependent,titlebar=no,resizable=no';
	popupWindow.showPopup("rowActionanchor");
	return false;
}

function submitDeleteNoneRowAction(form,boAdderName,name,value){	
	form.action=encodeURI(form.action+'?'+boAdderName+'.operation=delete&'+name+'='+value);
	form.submit;
}
	

</script>

<%
	ListComObject lco;

	ListComposition lc;

	try {
		// Get the ListCompositionFactory with the same name it was created.
		ListCompositionFactory lcf = (ListCompositionFactory) pageContext
		.getServletContext().getAttribute("lcf");
		lc = lcf.getListComposition("lc1");
	} catch (Exception e) {
		throw new JspException(e.getMessage());
	}

	//	Recuperamos los parámetros
	// Tipo de selección (opcional, por defecto none)
	String selectionType=(String)request.getParameter("selectionType");
	// Nombre del objeto (Parámetro obligatorio)
	// Sirve para:
	//		- cojer el objectfiled si no esta especificado
	//		- se usa para determinar cual es el nombre del codigo (el xxxcode) el objeto a 
	//			seleccionar (para radio, selects,...) en el caso que selectObjectName no esté definido.
	String objectname = (String)request.getParameter("objectname");
	
	/* En el caso que el listado muestre un objeto (ex: a) y el queremos que las rowactions/select/etc del listado hagan referencia a un
	associado de este objeto */
	String selectObjectName = (String) request.getParameter("selectObjectName");
	String selectObjectProperty = (String) request.getParameter("selectObjectProperty");

	if(selectObjectName==null) selectObjectName=objectname;
	
	//nombre del attibuto de donde estan por valores (por defecto es).
	String viewlistElementsName=(String)request.getParameter("viewlistElementsName");
	if(viewlistElementsName==null || viewlistElementsName.equalsIgnoreCase("")) viewlistElementsName="viewlistElements";
	
	// Nombre del formBean (Parámetro obligatorio)
	String formBeanName = (String)request.getParameter("formBeanName");
	
	// Objectfield (opcional)
	String objectfieldName = (String)request.getParameter("objectfield");	
	
	// Listado sólo de lectura (opcional, por defecto true)
	String isReadOnly = (String)request.getParameter("isReadOnly");	
	
	// Listado de BOAdder (aparece un botón de borrar un elemento de la lista que no es Ref-Action)
	String isBOAdder = (String)request.getParameter("isBOAdder");	
	String boAdderName = (String)request.getParameter("BOAdderName");	
	// indica cual es el nombre de la zona que contiene las rowActions (por defecto es "rowaction0")
	String rowActionZoneName=(String)request.getParameter("rowActionZoneName");	
 	if(rowActionZoneName==null) rowActionZoneName="rowaction0";
	
	if(isBOAdder==null){
		isBOAdder="false";
	}
	String isSelectPopUp = (String) request.getParameter("isSelectPopUp");
	if (isSelectPopUp == null){
		isSelectPopUp="false";
	}
	// Total de elementos de la tabla (necesario para la paginación)
	Integer count = (Integer)request.getAttribute("viewListTotalElements");
	// Si se ha introducido el nombre de un objectfield lo usamos, sino, usamos el "por defecto".
	if(request.getParameter("objectfield")==null) lco = lc.getObject(objectname);
	else lco = lc.getObject(objectfieldName);
	// code contiene el nombre del atributo clave, para el sortby por defecto y los nombres de checkboxes, radios, etc.
	String code=selectObjectName+"code";
	String codeProperty=(selectObjectProperty==null)? code :  selectObjectProperty+"."+code;
	
	Vector visibleNames = lco.getVisibleNames();
	Vector visibleLabels = lco.getVisibleLabels(UserUtils.getCurrentLocale(request).getLanguage());	
	Vector visibleSizes = lco.getVisibleAtts("size");
	Vector typeOfFields= lco.getVisibleTypeNames();

	
	%>
	
	<table cellpadding="0" cellspacing="0">
	<!-- Separación con cualquier cosa que haya antes -->
	<tr><td>&nbsp;</td></tr>
		<tr>
		<td>


	<%-- END OF Paginación --%>
	
	<table border="0" cellpadding="0" cellspacing="0" id="rowActionanchor" name="rowActionanchor">
		<tr height="24px">
		<td><!-- Rowactions --></td>
	<%	
	// Pintar Cabeceras
		if(selectionType.equals("single") || selectionType.equals("multiple")){
			%>
			<td></td>
			<%
		}	
	%>
		<td width=5px ><img src="<%=JspUtils.getProjectPath(request)%>/images/gui/columns-left.gif"></td>
	<%
	for (int i = 0; i < visibleNames.size(); i++) {		
		%>	<td class="ucheader" width="<%=(Integer.parseInt( (String)visibleSizes.get(i)) * 10)%>" 
			<%
					if(i== 0)
					{
			%>        
			        	style="border-left: none;"
			<%
					}
			%>  
			>
				<input type="button" width="100%" class="orderbutton" value="<%=visibleLabels.get(i)%>" onClick="sort(this.form,'<%= (String) visibleNames.get(i) %>');"/>
			</td>
		<%
	}	
	%>
			<td width=5px ><img src="<%=JspUtils.getProjectPath(request)%>/images/gui/columns-right.gif"></td>
		</tr>
		
		<%
		int index=0;
		%>
		
		<logic:iterate id="i_item" name="<%=viewlistElementsName%>" >		
		<tr>
		<%
	    String rowColor = index % 2 != 0 ? "#EEEEEE" : "#FFFFFF";
		index++;
		
		if(isReadOnly.equals("false")){
			// Arreglo necesario para que el nombre de los inputs sea "dinámico"
			pageContext.setAttribute(formBeanName, pageContext.getAttribute("i_item"));
		}

    %>
    <td>
    <%--RowActions --%>
		<table border=0 cellspacing=0 cellpadding=0>
		<tr>
		<%--Actions of BOAdder --%>
		<% if(isBOAdder.equalsIgnoreCase("true")){
			%>
			
			<td height="23" width="19" style="padding-right: 2px;"><input type="image" onclick="<%="submitDeleteNoneRowAction(this.form,'"+boAdderName+"', '"+boAdderName+".removeCode','"+BeanUtils.getProperty(pageContext.getAttribute("i_item"), codeProperty)%>')" src="<%=JspUtils.getProjectPath(request)%>/images/CATEGORY/action-type/del.gif" width="16" height="16" title="delete" style="position:relative;top:0;left:0;border:0;" onmousedown="this.style.top=1;this.style.left=1;" onmouseup="this.style.top=0;this.style.left=0;"/></td>
			<td height="23" width="19" style="padding-right: 2px;"><input type="image" onclick="submitRowActionInPopUp('../oPersonalSys/popUp_action_modify_page_personal_comment',this.form,'personal_commentcode','<%=BeanUtils.getProperty(pageContext.getAttribute("i_item"), codeProperty)%>');return false;" src="/irbpeople/images/CATEGORY/action-type/mod.gif" width="16" height="16" title="Modify comment" style="position:relative;top:0;left:0;border:0;" onmousedown="this.style.top=1;this.style.left=1;" onmouseup="this.style.top=0;this.style.left=0;"/></td>
			<%
		}
		%>
		
		<%-- Action of selectionPopup--%>
		<% if(isSelectPopUp.equalsIgnoreCase("true")){
			%>
			<input type="image" onclick="copySelectedBean('<%=BeanUtils.getProperty(pageContext.getAttribute("i_item"), codeProperty)%>', '<%=pageContext.getAttribute("i_item")%>'); return false;" src="<%=JspUtils.getProjectPath(request)%>/images/CATEGORY/action-type/add_exe.gif" width="16" height="16" title="select" style="position:relative;top:0;left:0;border:0;" onmousedown="this.style.top=1;this.style.left=1;" onmouseup="this.style.top=0;this.style.left=0;"/>	
		<% }%>

		
<%

// Recuperamos del site toda la información necesaria para construir las rowActions
ZoneConstructorUtils zoneConstructor=new ZoneConstructorUtils(request, pageContext, rowActionZoneName);

Menu menu = null; 

SiteOrg siteOrg = (SiteOrg)request.getSession().getAttribute("siteorg");
boolean noGo = false;
boolean noOperation = false;


ActionMapping am = (ActionMapping)request.getAttribute("org.apache.struts.action.mapping.instance");

String[] actionType 	= am.getType().split("\\.");
String commandName = NavigationFunctions.getCommandName(am);
String actionName = NavigationFunctions.getActionName(am);
String viewId      	= "eim";

String sectorName = null;
boolean considerOtherSectors = true;  
String objType=null;

if(!commandName.equals("_popUp")){
	menu = siteOrg.factoryZone(rowActionZoneName, commandName, actionName, sectorName, considerOtherSectors, pageContext.getRequest(), viewId);
	objType = siteOrg.actionGetParam("objType", commandName, actionName, sectorName, considerOtherSectors);
	if(objType==null || objType.equalsIgnoreCase("")) objType = siteOrg.getParameter("genericObjectName");

}


if(objType==null || objType.equalsIgnoreCase("")) objType = siteOrg.getParameter("genericObjectName");
if(menu != null && selectionType.equalsIgnoreCase("rowaction"))
{
MenuItem[] items = menu.getItems();

String concept = null;


%>


		
		 <%

		for(int j=0; j < items.length; j++)
		{
				MenuItem item=items[j];
				concept = item.getCon();
				boolean popup = item.containsParameter("isPopUp");

				String actionIcon = JspUtils.getProjectPath(request)+"/images/CATEGORY/action-type/" + concept + ".gif"; 
				String toSubmitAction=null;
				String returnCode="return false;";
				String destinationAction = "";
			
				if(popup){
					toSubmitAction="submitRowActionInPopUp";
					destinationAction = NavigationFunctions.getPopUpActionName(item.getLink());
					returnCode="return false;";

				}else{
					toSubmitAction="submitRowAction";
					destinationAction = item.getLink();
				}
					
		          
		%>  
		<td height=23 width=19 style="padding-right:2px" >
			<%--  <input type="image" onclick="alert('click');return false;" src="<%= actionIcon %>" width="16" height="16" title="<%=items[j].getCaptionButton() %>" style="position:relative;top:0;left:0;border:0;" onmousedown="this.style.top=1;this.style.left=1;" onmouseup="this.style.top=0;this.style.left=0;"/>	 --%>

			<input type="image" onclick="<%=toSubmitAction+"('"+destinationAction+"',this.form,'"+code+"','"+BeanUtils.getProperty(pageContext.getAttribute("i_item"), codeProperty)+"');"+returnCode%>" src="<%= actionIcon %>" width="16" height="16" title="<%=items[j].getCaptionButton() %>" style="position:relative;top:0;left:0;border:0;" onmousedown="this.style.top=1;this.style.left=1;" onmouseup="this.style.top=0;this.style.left=0;"/>

	</td>
				
		<%     } // for		
		        } // if
		%>

		</tr>
		<tr>
		<%
if(am != null)
{

//out.write(commandName + ":" + actionName);
}
		 %>

		</tr>
		</table>
	</td>	    
	<%
		if(selectionType.equals("single")){
			%>
			<td><jim:radio style="background : transparent;border : 0px;" property="<%=code%>" idName="i_item" value="<%=codeProperty%>"  checked="false"/></td>
			<%
		}else if(selectionType.equals("multiple")){
			%>
			<td><jim:multibox style="background : transparent;border : 0px;" property="<%=code%>">
					<bean:write name="i_item" property="<%=codeProperty%>"/>
			</jim:multibox></td>			
			<%			
		}
		else{
			if(isReadOnly.equals("false")){
			%>
				<%-- <html:hidden name="<%=formBeanName %>" property="<%=code%>" indexed="true"/>
				<html:hidden name="<%=formBeanName %>" property="version" indexed="true"/--> --%>
			<%
			}
		}
		%>
		<td style="border-left:1px solid #CCCCCC" bgcolor="<%=rowColor%>" width=4px><img src="<%=JspUtils.getProjectPath(request)%>/images/gui/transparent.gif" /></td>
		<%
		// Pintar todos los atributos visibles
		for(int i=0;i<visibleNames.size();i++) {				
	        String css_class = i == 0 ? "listColumns0 bgcolor=\""+rowColor+"\"" : "listColumns bgcolor=\""+rowColor+"\"";
			if(typeOfFields.get(i).equals("input") && isReadOnly.equals("false")){
				
				%>
					<td class=<%=css_class%>><html:text name="<%=formBeanName %>"  property="<%=(String)visibleNames.get(i) %>" indexed="true"/></td>
				<%		
			}
			else/*(typeOfFields.get(i).equals("text"))*/{
				%><%-- En el resultado se pone simepre un espacio al final. Así, en el caso que sea vacio, se pintan los bordes--%>
					<td class=<%=css_class%> title="<bean:write name="i_item" property="<%=(String)visibleNames.get(i) %>"/>"><jim:write_crlf name="i_item" property="<%=(String)visibleNames.get(i) %>"/>&nbsp;</td>
				<%	
			}
		} %>
			<td width=2px style="border-right:2px solid #898989" bgcolor="<%=rowColor%>"><img src="<%=JspUtils.getProjectPath(request)%>/images/gui/transparent.gif"></td>
		</tr>
		</logic:iterate>
		<tr>
			<td><img src="<%=JspUtils.getProjectPath(request)%>/images/gui/transparent.gif" /></td>
			<%
		if(selectionType.equals("single") || selectionType.equals("multiple")){
				%>
			<td></td>
				<%
			}	
			%>
			<td width=5px height=4px><img src="<%=JspUtils.getProjectPath(request)%>/images/gui/columns-left-corner.gif" /></td>
			<td colspan=<%= visibleNames.size() %> background="<%=JspUtils.getProjectPath(request)%>/images/gui/columns-bottom.gif" height=4px></td>
			<td width=5px height=4px><img src="<%=JspUtils.getProjectPath(request)%>/images/gui/columns-right-corner.gif" /></td>
		</tr>
	</table>
</td></tr>
<tr><td>&nbsp;</td></tr>
<tr><td align="right">
	
	<%-- Propagadores de la ordenación --%>

	<html:hidden name="viewListConfiguration" property="list_config_orderby.attribute" />
	<html:hidden name="viewListConfiguration" property="list_config_orderby.asc" />
	
	<%
	// Si no se encuentra el atributo count, no se paginarán los resultados
 	if(count!=null){ 		
	%>
	<%-- Paginación --%>
	
	<%-- Propagadores de la paginación --%>
	<html:hidden name="viewListConfiguration" property="list_config_pagination.firstEntry"/>
 	<html:hidden name="viewListConfiguration" property="list_config_pagination.maxResults"/>
 	<table>
 		<tr>
 			<td>
		 	<input type="image" src="<%=JspUtils.getProjectPath(request)%>/images/gui/backward_nav.gif" name="pagination-bw" onClick="paginate(this.form, 'bw',<%=count %>);" border="0"/>
		 	</td>
		<td>
 	<%
	 	ViewListConfiguration conf = (ViewListConfiguration)request.getAttribute("viewListConfiguration");
	
	 	int	firstEntry = new Integer(conf.getList_config_pagination().getFirstEntry());
	 	int	maxResults = new Integer(conf.getList_config_pagination().getMaxResults());
	
		int pages = count / maxResults + (count % maxResults > 0 ? 1 : 0);
		int currentPage = firstEntry / maxResults + 1; 	
	 	
	 	if(count==0)
		{
	%>	  
		  <jim:message key="pagination.emptylist"/>
	<%
		}
		else if(count==1)
		{
	%>	  
		<%= count %>&nbsp;<jim:message key="pagination.unitsmessage-one"/>
	<%
		}
		else
		{
	%>	
		<jim:message key="pagination.pages"/>&nbsp;<%= currentPage %>
		<jim:message key="pagination.pagesseparator"/>&nbsp;<%= pages %>&nbsp;
		(<%= count %>&nbsp;<jim:message key="pagination.unitsmessage"/>)
	<%
		}%>
	 		</td>		 		
 			<td>
		 	<input type="image" src="<%=JspUtils.getProjectPath(request)%>/images/gui/forward_nav.gif" name="pagination-fw" onClick="paginate(this.form, 'fw',<%=count %>);" border="0"/> 	 	
		 	</td> 			
		</tr>
	</table>
	 <%
 	}
	%> 	
		
		</td></tr>
</table>