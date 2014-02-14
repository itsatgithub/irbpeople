<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested"%>
<%@ taglib uri="/tags/jim" prefix="jim"%>
<%@ taglib uri="/tags/nested-jim" prefix="nested-jim"%>
<%@page import="utils.jsp.JspUtils"%>

<logic:notPresent name="org.apache.struts.action.MESSAGE"
	scope="application">
	<font color="red"> ERROR: Application resources not loaded --
	check servlet container logs for error messages. </font>
</logic:notPresent>

<html:form action="<%=JspUtils.getCurrentAction(request)%>">
	<html:hidden property="id" />


	<table>
		<tr>
			<td colspan=2><html:errors /></td>
		</tr>
	</table>



	<table cellpadding=0 cellspacing=0 width=900>
		<tr>
			<td><jsp:include page="/pages/common/templates/groupHeader.jsp">
				<jsp:param name="HeaderKey" value="personal" />
			</jsp:include></td>
		</tr>
		<tr align="center">
			<td class="GroupContent">
			<table class="FormTable" cellpadding=0 cellspacing=0>


				<tr>
					<td align="center"><nested:nest property="personal_Form">

						<table cellpadding=0 cellspacing=0>

							<tr>
								<td class="FormFieldLeft"><jim:message key="name" /></td>
								<td class="FormFieldRight"><nested:text property="name"	maxlength="100" tabindex="1"/></td>
								
								<td class="FormFieldLeft"><jim:message key="street" /></td>
								<td class="FormFieldRight"><nested:text property="street" maxlength="100" tabindex="11" size="40"/></td>
								
							</tr>
							<tr>
								<td class="FormSeparator"></td>
								<td class="BlankSeparator"></td>
								<td class="FormSeparator"></td>
								<td class="BlankSeparator"></td>
							</tr>
							<tr>
								<td class="FormFieldLeft"><jim:message key="surname1" /></td>
								<td class="FormFieldRight"><nested:text property="surname1"	maxlength="100" tabindex="2"/></td>

								<td class="FormFieldLeft"><jim:message key="city" /></td>
								<td class="FormFieldRight"><nested:text property="city"	maxlength="100" tabindex="12"/></td>
							</tr>
							<tr>
								<td class="FormSeparator"></td>
								<td class="BlankSeparator"></td>
								<td class="FormSeparator"></td>
								<td class="BlankSeparator"></td>
							</tr>
							<tr>
								<td class="FormFieldLeft"><jim:message key="surname2" /></td>
								<td class="FormFieldRight"><nested:text property="surname2"	maxlength="100" tabindex="3"/></td>

								<td class="FormFieldLeft"><jim:message key="zip/spcode" /></td>
								<td class="FormFieldRight"><nested:text property="zip_code"	maxlength="100" tabindex="13"/></td>

							</tr>
							<tr>
								<td class="FormSeparator"></td>
								<td class="BlankSeparator"></td>
								<td class="FormSeparator"></td>
								<td class="BlankSeparator"></td>
							</tr>
							<tr>
								<td class="FormFieldLeft"><jim:message key="dni" /></td>
								<td class="FormFieldRight"><nested:text property="dni" maxlength="100" tabindex="4"/></td>

								<td class="FormFieldLeft"><jim:message key="country" /></td>
								<td class="FormFieldRight">
									<nested-jim:TagSelect property="country.countrycode" tabindex="14">
										<nested-jim:TagOptionsCollection name="_selec_country" label="_descripcion" value="countrycode" />
									</nested-jim:TagSelect>
								</td>
							</tr>
							<tr>
								<td class="BlankSeparator">&nbsp;</td>
								<td class="BlankSeparator"></td>
								<td class="BlankSeparator"></td>
								<td class="BlankSeparator"></td>
							</tr>
							<tr>
								<td class="FormFieldLeft"><jim:message key="gender" /></td>
								<td class="FormFieldRight">
									<nested-jim:TagSelect property="gender.gendercode" tabindex="5">
										<nested-jim:TagOptionsCollection name="_selec_gender" label="_descripcion" value="gendercode" />
									</nested-jim:TagSelect>
								</td>

								<td class="FormFieldLeft"><jim:message key="phone" /></td>
								<td class="FormFieldRight"><nested:text property="phone" maxlength="100" tabindex="15"/></td>
							</tr>
							<tr>
								<td class="FormSeparator"></td>
								<td class="BlankSeparator"></td>
								<td class="FormSeparator"></td>
								<td class="BlankSeparator"></td>
							</tr>
							<tr>
								<td class="FormFieldLeft"><jim:message key="birth/spdate" /></td>
								<td class="FormFieldRight"><nested-jim:TagDate property="birth_date" tabindex="6"/></td>
								
								<td class="FormFieldLeft"><jim:message key="phone2" /></td>
								<td class="FormFieldRight"><nested:text property="phone2" maxlength="100" tabindex="16"/></td>
							</tr>
							<tr>
								<td class="FormSeparator"></td>
								<td class="BlankSeparator"></td>
								<td class="FormSeparator"></td>
								<td class="BlankSeparator"></td>
							</tr>
							<tr>
								<td class="FormFieldLeft"><jim:message key="birth/spcity" /></td>
								<td class="FormFieldRight"><nested:text property="birth_city" maxlength="100" tabindex="7"/></td>

								<td class="FormFieldLeft"><jim:message key="ice/spphone" /></td>
								<td class="FormFieldRight"><nested:text property="ice_phone" maxlength="100" tabindex="17"/></td>
							</tr>
							<tr>
								<td class="FormSeparator"></td>
								<td class="BlankSeparator"></td>
								<td class="FormSeparator"></td>
								<td class="BlankSeparator"></td>
							</tr>
							<tr>
								<td class="FormFieldLeft"><jim:message key="birth/spcountry" /></td>
								<td class="FormFieldRight">
									<nested-jim:TagSelect property="birth_country.countrycode" tabindex="8">
										<nested-jim:TagOptionsCollection name="_selec_country" label="_descripcion" value="countrycode" />
									</nested-jim:TagSelect>
								</td>

								<td class="FormFieldLeft"><jim:message key="personal_email" /></td>
								<td class="FormFieldRight"><nested:text property="personal_email" tabindex="18" size="35"/></td>
							</tr>
							<tr>
								<td class="FormSeparator"></td>
								<td class="BlankSeparator"></td>
								<td class="FormSeparator"></td>
								<td class="BlankSeparator"></td>
							</tr>
							<tr>
								<td class="FormFieldLeft"><jim:message key="nationality" /></td>
								<td class="FormFieldRight">
									<nested-jim:TagSelect property="nationality.nationalitycode" tabindex="9">
										<nested-jim:TagOptionsCollection name="_selec_nationality" label="_descripcion" value="nationalitycode" />
									</nested-jim:TagSelect>
								</td>
							</tr>
							<tr>
								<td class="FormSeparator"></td>
								<td class="BlankSeparator"></td>
								<td class="BlankSeparator"></td>
								<td class="BlankSeparator"></td>
							</tr>
							<tr>
								<td class="FormFieldLeft"><jim:message key="nationality/sp2" /></td>
								<td class="FormFieldRight">
									<nested-jim:TagSelect property="nationality_2.nationalitycode" tabindex="10">
										<nested-jim:TagOptionsCollection name="_selec_nationality" label="_descripcion" value="nationalitycode" />
									</nested-jim:TagSelect>
								</td>
							</tr>
						</table>
					</nested:nest></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<br>



	<table cellpadding=0 cellspacing=0  width=900>
		<tr>
			<td><jsp:include page="/pages/common/templates/groupHeader.jsp">
				<jsp:param name="HeaderKey" value="employee" />
			</jsp:include></td>
		</tr>
		<tr align="center">
			<td class="GroupContent">
			<table class="FormTable" cellpadding=0 cellspacing=0>


				<tr>
					<td align="center"><nested:nest property="personal_Form">

						<table cellpadding=0 cellspacing=0>
							<tr>
								<td class="FormFieldLeft"><jim:message key="personal.username" /></td>
								<td class="FormFieldRight"><nested:text property="username" maxlength="20" tabindex="19"/></td>
	
								<td class="FormFieldLeft"><jim:message key="payments" /></td>
								<td class="FormFieldRight">
									<nested-jim:TagSelect property="payments.paymentcode" tabindex="25">
										<nested-jim:TagOptionsCollection name="_selec_payment" label="_descripcion" value="paymentcode" />
									</nested-jim:TagSelect>
								</td>
							</tr>
							<tr>
								<td class="FormSeparator"></td>
								<td class="BlankSeparator"></td>
							</tr>
							<tr>
								<td class="FormFieldLeft"><jim:message key="personal.language" /></td>
								<td class="FormFieldRight">	
									<nested-jim:TagSelect property="language.languagecode"  required="true" tabindex="20">
										<nested-jim:TagOptionsCollection name="_selec_language" label="language" value="languagecode" />
									</nested-jim:TagSelect>
								</td>

								<td class="FormFieldLeft"><jim:message key="ss/spnumber" /></td>
								<td class="FormFieldRight"><nested:text	property="ss_number" maxlength="100" tabindex="26"/></td>
							</tr>
							<tr>
								<td class="FormSeparator"></td>
								<td class="BlankSeparator"></td>
							</tr>
							<tr>
								<td class="FormFieldLeft"><jim:message key="marital/spstatus" /></td>
								<td class="FormFieldRight">
									<nested-jim:TagSelect property="marital_status.marital_statuscode" tabindex="21">
										<nested-jim:TagOptionsCollection name="_selec_marital_status" label="_descripcion" value="marital_statuscode" />
									</nested-jim:TagSelect>
								</td>
								
								<td class="FormFieldLeft"><jim:message key="bank" /></td>
								<td class="FormFieldRight">
									<nested-jim:TagSelect property="bank.bankcode" tabindex="27">
										<nested-jim:TagOptionsCollection name="_selec_bank" label="_descripcion" value="bankcode" />
									</nested-jim:TagSelect>
								</td>
							</tr>
							<tr>
								<td class="FormSeparator"></td>
								<td class="BlankSeparator"></td>
							</tr>
							<tr>
								<td class="FormFieldLeft"><jim:message key="working/ushours" /></td>
								<td class="FormFieldRight">
									<nested-jim:TagSelect property="working_hours.working_hourscode" tabindex="22">
										<nested-jim:TagOptionsCollection name="_selec_working_hours" label="_descripcion" value="working_hourscode" />
									</nested-jim:TagSelect>
								</td>

								<td class="FormFieldLeft"><jim:message key="bank/spaccount" /></td>
								<td class="FormFieldRight"><nested:text	property="bank_account" maxlength="120" size="50" tabindex="28"/></td>
							</tr>
							<tr>
								<td class="FormSeparator"></td>
								<td class="BlankSeparator"></td>
							</tr>
							<tr>
								<td class="FormFieldLeft"><jim:message key="category" /></td>
								<td class="FormFieldRight">
									<nested-jim:TagSelect property="category.categorycode" tabindex="23">
										<nested-jim:TagOptionsCollection name="_selec_category"	label="_descripcion" value="categorycode" />
									</nested-jim:TagSelect>
								</td>
								<td class="FormFieldLeft">
									<jim:message key="bic" />
								</td>
								<td class="FormFieldRight">	
									<nested:text property="bic" maxlength="20" tabindex="29" size="20" onchange="this.form.elements['tainted'].value=true"/>	
								</td>
							</tr>
							<tr>
								<td class="FormSeparator"></td>
								<td class="BlankSeparator"></td>
							</tr>
							<tr>
								<td class="FormFieldLeft">&nbsp;</td>
								<td class="FormFieldRight">&nbsp;</td>
								<td class="FormFieldLeft">
									<jim:message key="swift" />	
								</td>
								<td class="FormFieldRight">	
									<nested:text property="swift" maxlength="20" tabindex="30" size="20" onchange="this.form.elements['tainted'].value=true"/>	
								</td>
							</tr>
							<tr>
								<td class="FormSeparator"></td>
								<td class="BlankSeparator"></td>
							</tr>
														
							
							<tr>
								<td class="FormSeparator"></td>
								<td class="BlankSeparator"></td>
							</tr>
							<tr>
								<td class="FormFieldLeft"><jim:message key="personal.second_affiliation" /></td>
								<td class="FormFieldRight" colspan="3">
									<nested:text property="second_affiliation" maxlength="255" tabindex="24" size="80" />
								</td>
							</tr>
							<tr>
								<td class="FormSeparator"></td>
								<td class="BlankSeparator"></td>
							</tr>
						</table>

					</nested:nest></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<br>

	<table cellpadding=0 cellspacing=0  width=900>
		<tr>
			<td><jsp:include page="/pages/common/templates/groupHeader.jsp">
				<jsp:param name="HeaderKey" value="children" />
			</jsp:include></td>
		</tr>
		<tr align="center">
			<td class="GroupContent">
			<table class="FormTable" cellpadding=0 cellspacing=0>
				<tr>
					<td align="center"><jsp:include
						page="/pages/common/lists/BOAdderStart_.jsp" flush="true">
						<jsp:param name="BOAdderName" value="personal_child" />
					</jsp:include> <nested:nest property="personal_child">
						<nested:hidden property="operation" value="" />
						<nested:nest property="newFormBean">
							<nested:hidden property="child_personal.personalcode" />
							<table align="center" class="filtro">
								<tr>
									<td class="FormFieldLeft"><jim:message key="birth/spdate" />
									</td>
									<td class="FormFieldRight"><nested-jim:TagDate
										property="birth_date" tabindex="28"/></td>
									<td class="FormFieldLeft"><jim:message key="observations" />
									</td>
									<td class="FormFieldRight"><nested:text
										property="observations" maxlength="100" tabindex="30"/></td>
									<td><input type="submit"
										value="<jim:message key="BOAdderButton"/>"
										onclick="submitAddToBOAdderButton(this.form,'personal_child'); return false;" />
									</td>
								</tr>
							</table>
						</nested:nest>
					</nested:nest> <jsp:include page="/pages/common/lists/viewlist.jsp" flush="true">
						<jsp:param name="selectionType" value="none" />
						<jsp:param name="objectname" value="child" />
						<jsp:param name="objectfield"
							value="oPersonalSys__action_manage_personal_children" />
						<jsp:param name="isReadOnly" value="true" />
						<jsp:param name="formBeanName" value="child_Form" />
						<jsp:param name="isBOAdder" value="true" />
						<jsp:param name="BOAdderName" value="personal_child" />
					</jsp:include></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<br>

</html:form>