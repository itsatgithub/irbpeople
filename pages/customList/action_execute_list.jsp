<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested"%>
<%@ taglib uri="/tags/jim" prefix="jim"%>
<%@ taglib uri="/tags/nested-jim" prefix="nested-jim"%>
<%@page import="utils.jsp.JspUtils"%>
<%@page import="java.util.*"%>
<%@page import="utils.userUtils.*"%>
<%@page import="com.justinmind.helper.composition.report.*"%>
<%@page import="bussineslogic.objects.Usuario;"%>

<logic:notPresent name="org.apache.struts.action.MESSAGE"
	scope="application">
	<font color="red"> ERROR: Application resources not loaded --
	check servlet container logs for error messages. </font>
</logic:notPresent>

<link href="<%=JspUtils.getProjectPath(request) %>/common/tablesorter/themes/blue/style.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="<%=JspUtils.getProjectPath(request) %>/common/jquery-1.2.6.js"></script>
<script type="text/javascript" src="<%=JspUtils.getProjectPath(request) %>/common/tablesorter/jquery.tablesorter.js"></script>

<script type="text/javascript">
$(document).ready(function(){
$("table").tablesorter({ 
    widgets: ['zebra'] 
});  

});

</script>

<html:form action="<%=JspUtils.getCurrentAction(request)%>">
	<html:hidden property="id" />


	<table>
		<tr>
			<td colspan="2"><html:errors /></td>
		</tr>
	</table>



	<%
		String[] columns_labels = (String[]) request.getSession().getAttribute("columnsView");
		List<String[]> tablecontent = (List<String[]>) request.getAttribute("tableContent");
		Iterator<String[]> it = tablecontent.iterator();
		
		

		Usuario user = UserUtils.getCurrentUsuario(request);
		
		String userLang= user.getLanguage().getLanguagecode();
		org.apache.log4j.Category log_for_filter = org.apache.log4j.Logger.getLogger(HttpServletRequest.class);
		
		ReportCompositionFactory rcf = (ReportCompositionFactory) request.getSession().getServletContext().getAttribute("rcf");
		ReportComposition rc = null;
		
		try {
			rc = rcf.getReportComposition("irb_rrhh");
		}
		catch (Exception e) {log_for_filter.warn(e);}

		//look up which view must be shown depending on which action 
		//calls the actionNewListing action
		
		//if its called by itself because we want to change the view:
		String vistaName = (String) request.getSession().getAttribute("viewName");
		ReportComObject rco = rc.getReport(vistaName);	
		
		
		// ******************** SELECT SECTION ***********************
		// ***********************************************************
		
		//we fill a hashtable with the (fieldname, label) pair
		//so we can easyly find which label corresponds to the name used
		//in the beans (the fieldname)
		Vector defaultSelectFieldNames = rco.getSelect().getVisibleFieldNames();
		Vector defaultSelectLabels = rco.getSelect().getVisibleLabels(userLang);
		Hashtable selectFields = new Hashtable();
		Iterator itNames = defaultSelectFieldNames.iterator();
		Iterator itLabels = defaultSelectLabels.iterator();
		while (itNames.hasNext()) {
			selectFields.put((String)itNames.next(), (String) itLabels.next()); 
		}
	
		String auxname, auxlabel;
		String[] columns = new String[columns_labels.length];
		
		//we lookup which fields must be shown in the right <select> (the
		//ones which are chosen to use in the saved report)
		
		
		//and we fill the Vectors containing the fieldnames and labels
		// of the right side <select> using the hashtable to find the labels
		itNames = defaultSelectFieldNames.iterator();
		itLabels = defaultSelectLabels.iterator();
		for (int i=0; i<columns_labels.length; i++) {
			
			columns[i] = ((String) selectFields.get(columns_labels[i]));
		}
		
		
		
		Integer width = columns.length*150;  
		if(tablecontent.isEmpty()){
			%>
			
			No results found
			
			<%
			
		}
		else{
	%>

	<table cellpadding="0" cellspacing="0" class="tablesorter" width="<%=width %>px">

		<thead>
			<tr>
				<%
					for (int i = 0; i < columns.length; i++) {
				%>
				<th  width="150px" ><%=columns[i]%></th>

				<%
					}
				%>
			</tr>
		</thead>
		<tbody>
			<%
				while (it.hasNext()) {
						String[] temp = it.next();
			%>
			<tr>
				<%
					for (int i = 0; i < temp.length; i++) {
				%>
				<td><%=temp[i] == null ? "" : temp[i] %></td>
				<%
					}
				%>
			</tr>
			<%
				}
			%>

		</tbody>

	</table>
	
	<%} %>



</html:form>