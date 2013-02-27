//--------------------------------------------------------------------------------------------------//
//---------------------Functions used to navigate through the application --------------------------//
//--------------------------------------------------------------------------------------------------//

//
// This function is used when a link is pressed
//
// @param url url obtained from ZoneConstructor.
// @param popup boolean that indicates whereas it has to be opened in a popup
//
function linkPressed(url, popup){
	var path=getPathFromUrl(url);
	navigateTo(path, popup);
}

//
// This function is used when a link is pressed
//
// @param url url obtained from ZoneConstructor.
// @param popup boolean that indicates whereas it has to be opened in a popup
// @param codeName name of the code
// @param codeValue value of the code
//
function linkPressedWithCode(url, popup, codeName, codeValue){
	var path=getPathFromUrl(url);
	navigateToWithCode(path, popup, codeName, codeValue);
}

//
// This function is used when a row action is pressed
//
// @param url url obtained from ZoneConstructor.
// @param popup boolean that indicates whereas it has to be opened in a popup
// @param codeName name of the code
// @param codeValue value of the code
//
function viewListRowActionPressed(url, popup, codeName, codeValue){
	var path=getPathFromUrl(url);
	navigateToWithCode(path, popup, codeName, codeValue);
}



// This function is used when a button of a viewlist with multiple checkbox is pressed 
//
// @param url url obtained from ZoneConstructor.
// @param popup boolean that indicates whereas it has to be opened in a popup
// @param codeName name of the code to set.
//
function viewListButtonPressedCobrarFactura(url, popup, codeName){
	
	var path=getPathFromUrl(url);
	var form=document.forms[0];
	var atLeastOneCheck = false;

	if(form.elements['bo_facturacode'][0])
	{
		for(i=0; i < form.elements['bo_facturacode'].length; i++)
		{
			var oCode = form.elements['bo_facturacode'][i];
			if(oCode.checked)
			{
				path = urlAddParamAllowRepeat(path, 'bo_facturacode',oCode.value)
				atLeastOneCheck = true;
			}	
		}
	}
	else
	{
		if(form.elements['bo_facturacode'].checked)
		{
			path = urlAddParamAllowRepeat(path, 'bo_facturacode',form.elements['bo_facturacode'].value)
			atLeastOneCheck = true;
		}
	}
	
	if(!atLeastOneCheck)
	{
		alertAtLeastOneItem();
		return;
	}
	
	navigateTo(path,true);

}

function viewListButtonPressedCheck(url, popup, codeValue){

	var path=getPathFromUrl(url);
	var form=document.forms[0];
	var OneCheck = false;
	
	for(i=0; i < form.elements[codeValue].length; i++)
	{
		var oCode = form.elements[codeValue][i];
		if(oCode.checked)
		{
			if(OneCheck)
			{

			}
			else
			{
				OneCheck = true;
			}
		}	
	}
	
	if(!OneCheck)
	{
		alertAtLeastOneItem();
		return;
	}
	form.action=path;
	form.submit();
}
// This function is used when a button of a viewlist with multiple checkbox is pressed 
// and we want to allow only a single check, only one checkbox selected 
// (radio button behaviour implemented in a check box)
//
// @param url url obtained from ZoneConstructor.
// @param popup boolean that indicates whereas it has to be opened in a popup
// @param codeName name of the code to set.
//
function viewListButtonPressedCheckOne(url, popup,codeValue){
	
	var path=getPathFromUrl(url);
	var form=document.forms[0];
	var OneCheck = false;
	
	if(form.elements[codeValue][0])
	{
		for(i=0; i < form.elements[codeValue].length; i++)
		{
			var oCode = form.elements[codeValue][i];
			if(oCode.checked)
			{
				if(OneCheck)
				{
					alertOneItem();
					return;
				}
				else
				{
					path = urlAddParamAllowRepeat(path, codeValue,oCode.value)
					OneCheck = true;
				}
			}	
		}
	}
	else
	{
		if(form.elements[codeValue].checked)
		{
			path = urlAddParamAllowRepeat(path, codeValue, form.elements[codeValue].value)
			OneCheck = true;
		}
	}
	
	if(!OneCheck)
	{
		alertOneItem();
		return;
	}
	navigateTo(path, popup);
}


//
// This function is used when a button of a viewlist is pressed (row with selectable)
//
// @param url url obtained from ZoneConstructor.
// @param popup boolean that indicates whereas it has to be opened in a popup
// @param codeName name of the code to set.
//
function viewListButtonPressed(url, popup, codeName){
	
	var codeValue=getCheckedValue(document.forms[0]._view_list_selected_item);
	
	if(codeValue==null){
		alertOneItem();
		return;
	}

	var path=getPathFromUrl(url);	
	
	navigateToWithCode(path, popup, codeName, codeValue);
}

//
//This function is used when a button of the submit zone is pressed.
//
// @param url url obtained from ZoneConstructor.
//
function submitButtonPressed(url){
	var path=getPathFromUrl(url);
	submitTo(path);
}

// 
// Private Method Used By viewListButtonPressed(url, codeName).
//
// Returns the value of a radio.
//
// @param radioObj radio to check
// @returns a string with the value of the checked item, or null in any other case.
//
function getCheckedValue(radioObj) {
	if(!radioObj)
		return null;
	var radioLength = radioObj.length;
	if(radioLength == undefined)
		if(radioObj.checked)
			return radioObj.value;
		else
			return null;
	for(var i = 0; i < radioLength; i++) {
		if(radioObj[i].checked) {
			return radioObj[i].value;
		}
	}
	return null;
}

//
// Private Method Used By viewListButtonPressed(url, codeName).
//
// This method is used to warn the user that an item must be selected.
//
function alertOneItem()
{
	alert("You must select one item");
}

function alertAtLeastOneItem()
{
	alert("You must select at least one item");
}

//
// Private Method
//
//The navigator goes to the given url, with the code given
//
// @param path of the url
// @param popup boolean that indicates whereas it has to be opened in a popup
// @param codeName name of the code
// @param codeValue value of the code
//
function navigateToWithCode(path, popup, codeName, codeValue){
	path=urlAddParam(path, codeName,codeValue);
	navigateTo(path, popup);
}

//
// Private Method
//
//The navigator goes to the given url
//
// @param path of the url
// @param popup boolean that indicates whereas it has to be opened in a popup
//
function navigateTo(path, popup){
	if(popup){
		openPopupWindow(path);
	} else {
		window.location.assign(path);
	}
}


function dobleSubmitPrevent(){
	if(alreadySubmitted) {
		return false;
   	}
	if(!alreadySubmitted) {
		alreadySubmitted = true;
		return true;
	}

}


function submitWithParam(form,paramater,value){
	
	if(typeof thisisapopup != 'undefined') 
		{		
			form.action=encodeURI(form.action+'?'+paramater+'='+value+'&isPopUpWindow=true');	
		}else{
			form.action=encodeURI(form.action+'?'+paramater+'='+value);
		}
	if(dobleSubmitPrevent()){
		form.submit();
	}	
}


function updatePosition(textarea)
{
	if(document.selection)
	{
		oCursorPosition = document.selection.createRange();
	}
	else
	{
		oCursorPosition = textarea;		
	} 
}


function insFoto(name){
	
	var insercion = " ("+name+") "

	if(!oCursorPosition)
	{
		alert('Debe situar el cursor en punto del texto donde desee insertar la referencia a una imagen.');
	}

	if(document.selection)
	{
		oCursorPosition.text = insercion;
	}
	else
	{
		var element = oCursorPosition;
		var texto = element.value.substring(0, element.selectionStart) + insercion + element.value.substring(element.selectionEnd);
		element.value = texto;
	}

}


function insPlantilla(form, selectName, textareaName, array)
{

	var oSelect = form.elements[selectName]

	for (var i = oSelect.length - 1; i>=0; i--) {
    	if (oSelect.options[i].selected) {
      		form.elements[textareaName].value += array[oSelect.options[i].value];
      		form.elements[textareaName].focus()
      		form.elements[textareaName].scrollTop = form.elements[textareaName].scrollHeight
			return      		
    	}
    }
	
}


//
// Private Method
//
// The navigator goes to the given url, by submiting a form
//
// @param url url to navigate to
//
function submitTo(path){
	var form=document.forms[0];
	var oldAction=form.action;
	if(typeof thisisapopup != 'undefined') 
		{		
			path=urlAddParam(path, 'isPopUpWindow','true');	
		}
	form.action=path;
	if(dobleSubmitPrevent()){
		form.submit();
	}	
}

function submitToWithPopup(path, popup){
	var form=document.forms[0];
	var oldAction=form.action;
	if(popup) 
		{		
			path=urlAddParam(path, 'isPopUpWindow','true');
		}
	form.action=path;
	if(dobleSubmitPrevent()){
		form.submit();
	}	
	

}

function refresh(){

	if(document.forms.length==0){
		navigateTo(window.location, false);
	}
	else{
	
	/*if(document.forms[0].elements['tecnica_en_ficha.operation'])
	{
		document.forms[0].elements['tecnica_en_ficha.operation'].value='load'
	}
	if(document.forms[0].elements['assoc_otra_tarifa.operation'])
	{
		document.forms[0].elements['assoc_otra_tarifa.operation'].value='load'
	}*/
	
	submitTo(window.location);
	}
	
}

//
// Private Method
//
// Returns a correct path form a given url (obtained from zoneConstructor)
//
// @param url url obtained from ZoneConstructor.
//
function getPathFromUrl(url){
	var path = encodeURI(url + '.do');
	if(typeof thisisapopup != 'undefined') 
	{		
		path=urlAddParam(path, 'isPopUpWindow','true');	
	}
	return path;
}

function focusFirstInput(){
	if(this.document.forms.length>0) {
		for(j=0; j < this.document.forms.length; j++) {	
			for(i=0; i<this.document.forms[j].elements.length;i++){
				if (this.document.forms[j].elements[i].type == 'text' || this.document.forms[j].elements[i].type == 'textarea'){
					if(this.document.forms[j].elements[i].disabled) 
						continue; 
					this.document.forms[j].elements[i].select(); 
					this.document.forms[j].elements[i].focus(); 
					break
				}
			}
		}
	}
}
