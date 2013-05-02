<%@ include file="/pages/common/templates/global.jsp"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested"%>
<%@ taglib uri="/tags/jim" prefix="jim"%>
<%@ taglib uri="/tags/nested-jim" prefix="nested-jim"%>
<%@page import="utils.jsp.JspUtils"%>
<%@page import="java.util.*"%>
<%@page import="presentation.formbeans.objects.*"%>

<logic:notPresent name="org.apache.struts.action.MESSAGE"
	scope="application">
	<font color="red"> ERROR: Application resources not loaded --
	check servlet container logs for error messages. </font>
</logic:notPresent>


<%
	List<Views_Form> views_form = (List<Views_Form>) request
			.getAttribute("views");

	Locale locale = (Locale) request.getAttribute("locale");
	org.apache.log4j.Category log_for_filter = org.apache.log4j.Logger
			.getLogger(HttpServletRequest.class);

	ReportCompositionFactory rcf = (ReportCompositionFactory) pageContext
			.getServletContext().getAttribute("rcf");
	ReportComposition rc = null;
	try {
		rc = rcf.getReportComposition("irb_rrhh");
	} catch (Exception e) {
		log_for_filter.warn(e);
	}

	List<String[]> views_labels = new ArrayList();

	try {
		Iterator<Views_Form> it_vf = views_form.iterator();

		while (it_vf.hasNext()) {

			Views_Form temp = it_vf.next();
			ReportComObject rco = rc.getReport(temp.getName());

			if (rco.getLabel(locale) != null
					&& !rco.getLabel(locale).equals("")) {
				
				String tt = rco.getLabel(locale);
				views_labels.add(new String[]{tt, temp.getName()});

			} else {
				views_labels.add(new String[]{temp.getName(),temp.getName()});
				
			}
		}
	} catch (Exception e) {
		log_for_filter.warn(e);
	}
	
	/*
	Collections.sort(views_labels, new Comparator<String[]>()
		{		
			public int compare(String[] s1, String[] s2)
			{
			    if(s1 != null && s1[0] !=null && s2!=null && s2[0]!=null)
			    {
			    	return s1[0].compareTo(s2[0]);
			    }
			    else   
			    {
			        return 0;
			    }
			}
		}
	);
	*/
%>


<script type="text/javascript" src="<%=JspUtils.getProjectPath(request) %>/common/jquery-1.2.6.js"></script>
<script type="text/javascript">
$(document).ready(function(){

<%--
<% if (views_form.get(0) != null){%>
$.ajax({
		url: encodeURI("../customList/ajaxAction_view_custom_list"+".do"),
		data:{ selected_view: "<%=views_form.get(0).getName()%>"},
 		success: function(html){
 				$("#list_creation_content").html(html);
 				return false;
 }
 });

<%}%>
--%>

$("select[@name=selected_view]").change(function () {
         
          
          $("select option:selected").each(function () {
              
                $.ajax({
					url: encodeURI("../customList/ajaxAction_view_custom_list"+".do"),
					data:{ selected_view: $(this).val()},
 					success: function(html){
 						$("#list_creation_content").html(html);
 						if($("select[@name=selected_view]").val()=='view_personal_professional_date'){
 							$("#listdate").show();
 	 					} else {
 							$("#listdate").hide();
 	 					}
 						return false;
 					}
 					});
 			    return false;
 					
              });
              return false;
        }).change();

});

</script>



<html:form action="<%=JspUtils.getCurrentAction(request)%>">
	<html:hidden property="id" />


	<table>
		<tr>
			<td colspan=2><html:errors /></td>
		</tr>
	</table>

<div>
<table cellpadding=0 cellspacing=0 width="85%">
<tr>
<td align="center"> 
<table cellpadding=0 cellspacing=0 width="100%"> 
	<tr>
		<td><jsp:include page="/pages/common/templates/groupHeader.jsp">
			<jsp:param name="HeaderKey" value="customlist.infoCustomList" />
		</jsp:include></td>
	</tr>
	<tr align="center">
		<td class="GroupContent">
		<table class="FormTable" cellpadding=0 cellspacing=0 >


			<tr >
				<td align="center" >


				<table cellpadding=0 cellspacing=0 width="100%">

					<tr>

						<td class="FormFieldLeft" ><jim:message key="customlist.viewname" />

						</td>
						<td class="FormFieldRight">
						<select name="selected_view"  >
<%
		
		boolean first = true;
		for(String[] pair: views_labels)
		{
			if (first == true) {
				first = false;
%>
			<option value="<%=pair[1]%>" selected="selected"><%=pair[0]%></option>
			<%
				} else {
			%>
		
		<option value="<%=pair[1]%>" ><%=pair[0]%></option>
		<%
			}
				}
		%>

</select>
</td>
					</tr>
					<tr id="listdate">
						<td class="FormFieldLeft" >
							<jim:message key="customlist.date" />
						</td>
						<td class="FormFieldRight">
							<jim:TagDate  property="listdate" />&nbsp;<jim:message key="text.leaveBlankIfToday" />
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</td>
</tr>
</table> 
</div>
<div id="list_creation_content"> <p>&nbsp;</p><p>&nbsp;</p> <center>Loading .....</center></div>

</html:form>

<table height="23" border=0 cellpadding=0 cellspacing=0
	style="position:relative;top:0;left:0;"
	onmousedown="this.style.top=1;this.style.left=1;"
	onmouseup="this.style.top=0;this.style.left=0;"
	onmouseout="this.style.top=0;this.style.left=0;">
	<tr>
		<td width=6px
			style="background-image:url(<%=JspUtils.getProjectPath(request)%>/images/gui/button-left.gif)">&nbsp;</td>
		<td valign="top"><input
			style="border:0px;height:23;background-image:url(<%=JspUtils.getProjectPath(request)%>/images/gui/button-center.gif);background-repeat:repeat-x"
			type="button" id="Button_0" name="Button_0"
			value="<jim:message key="execute" />" onClick="validate_execution('../customList/action_execute_list');">

		</td>
		<td width=6px
			style="background-image:url(<%=JspUtils.getProjectPath(request)%>/images/gui/button-right.gif)">&nbsp;</td>
	</tr>
</table>
