function openViewdetailsPopup(url){//, form, codevalue) {
	openViewdetailsPopup(url, 600,600);
	return false;
}

function openViewdetailsPopup(url, height, width){//, form, codevalue) {
	popupWindow.setUrl(url);
	popupWindow.autoHide();
	popupWindow.setSize(height, width);
	popupWindow.windowProperties = 'toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,alwaysRaised,dependent,titlebar=no,resizable=no';
	popupWindow.showPopup("popupanchor");
	return false;
}

function openViewdetailsPopupOperation(url, form, existcode,codevalue) {
	return openViewdetailsPopupOperation(url, form, existcode,codevalue,null);
}

function openViewdetailsPopupOperation(url, form, existcode,codevalue,objectname) {
							
	var codigo = null;
	var valorcodigo = null;
	var itemName = objectname + 'code';
	
	valorcodigo = codevalue;
	
	var newAction = encodeURI(url + '.do');
				
	if(window.opener && window.opener.location.href!=window.location.href)
	{		
		newAction=urlAddParam(newAction, 'isPopUpWindow','true');	
	}
			
	if (existcode){
		
		if (valorcodigo == null && numberSelectedItems(form[itemName]) == 1)
		{			
			var lineIndex = getSelectedRadioValue(form[itemName]);
			codigo = lineIndex;
		}
		else
		{
			codigo = valorcodigo;
		}

		if (codigo == null){
			alertOneItem();
			return false;
		}
    }

	if(codigo!=null){
		newAction = urlAddParam(newAction, itemName, codigo);
	}
	popupWindow.setUrl(newAction);
	popupWindow.autoHide();
	popupWindow.setSize(700,525);
	popupWindow.windowProperties = 'toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,alwaysRaised,dependent,titlebar=no,resizable=yes';

	popupWindow.showPopup("popupanchor");

}

//-------------------------------------------------------------------------------------------------
//
// urlRemoveParam(strUrl, strParamName)
//
// Takes a parameter name and removes parameter as encoded in the document URL.
//
//-------------------------------------------------------------------------------------------------
function urlRemoveParam(strUrl, strParamName)
{
   if (strUrl == null)
   {
      strUrl = document.URL;
   }
   
   var strUrlBase = strUrl;
   var strArgs = "";
   var iArgsStart = strUrl.indexOf("?");
   if (iArgsStart != -1)
   {
      strUrlBase = strUrl.substring(0, iArgsStart +1);
      strArgs = strUrl.substring(iArgsStart +1);
   }

   var iArgStart = strArgs.indexOf(strParamName);
   while (iArgStart != -1)
   {
      // Ensure we have found an arg name
      if (iArgStart == 0 || strArgs.charAt(iArgStart -1) == '&')
      {
         // Locate the end of the arg name and value
         var iArgEnd = strArgs.indexOf('&', iArgStart);
         if (iArgEnd == -1)
         {
            iArgEnd = strArgs.length;
         }
         else if (iArgStart == 0)
         {
            // NOTE :Remove seperator as well, if first arg
            iArgEnd++;
         }

         // Ensure start of argument includes the prefixed seperator, if one exists
         if (iArgStart > 0 && strArgs.charAt(iArgStart -1) == '&')
         {
            iArgStart--;
         }
               
         // Remove the argument
         var strStrippedArgs = strArgs.substring(0, iArgStart);
         strArgs = strStrippedArgs + strArgs.substring(iArgEnd);
      }
      else
      {
         // This is not an argument name, look for next occurence
         iArgStart += strParamName.length;
      }

      // Locate next argument of same name
      iArgStart = strArgs.indexOf(strParamName, iArgStart);
   } // end while

   return strUrlBase + strArgs;
} // urlRemoveParam

//-------------------------------------------------------------------------------------------------
//
// urlAddParam(strKey, strValue, strUrl)
//
//-------------------------------------------------------------------------------------------------
function urlAddParam(strUrl, strKey, strValue)
{
   if (strUrl.indexOf("?" + strKey) == -1 && strUrl.indexOf("&" + strKey) == -1)
   {
      if (strUrl.indexOf("?") == -1)
      {
         strUrl = strUrl + "?";
      }
      else if (strUrl.lastIndexOf("&") != strUrl.length - 1 && strUrl.indexOf("?") != strUrl.length - 1)
      {
         strUrl = strUrl + "&";
      }
      strUrl = strUrl + strKey + "=" + strValue;
   }
   return strUrl;
}

function addOption(code, caption){

	try{
	var selectOO = window.addOptionForm[window.addOptionTargetName];
			
	if(window.addOptionTargetHiddenName==null && selectOO.options['options']!=null){
		//IE	
		var oOption = document.createElement("OPTION");
		selectOO.options.add(oOption);
		oOption.innerText = caption;
		oOption.value = code;
		oOption.selected = 'selected';
				
	} else if(window.addOptionTargetHiddenName == null && window.opener.textTarget.options){
		//Firefox
		var newOption = document.createElement('option');
		newOption.text = caption;
        newOption.value = code;
        newOption.selected ='selected';
        var currentOption = selectOO.options[selectOO.selectedIndex];  
        selectOO.options[selectOO.selectedIndex].selected='';  
		selectOO.add(newOption, currentOption);
	} 
	else {		
			var hidenOO = window.addOptionForm[window.addOptionTargetHiddenName];		
			selectOO.value=caption;
			hidenOO.value=code;
			}
	}				        
	catch(ex){
		// Is not a selectable popup
		}
}

// *****************************
// 	FieldPopup object
// *****************************
function FieldPopup()
{
    var pw = new PopupWindow();
    window.targetInput = null;
    pw.selectTarget = FP_selectTarget;
    pw.goToActionPopUp = FP_goToActionPopUp;	
    return pw;
}

function FP_selectTarget(url, textTarget, hiddenTarget, anchor, objectField)
{
    window.textTarget = textTarget;
    window.hiddenTarget = hiddenTarget;
	var urlWithParams=urlAddParam(url, 'objectField', objectField);
    this.setUrl(urlWithParams);
	this.autoHide();
	//this.setSize(800,600);
	this.windowProperties = 'toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,alwaysRaised,dependent,titlebar=no,resizable=yes';
    this.showPopup(anchor);
}

function FP_goToActionPopUp(url, textTarget, hiddenTarget, anchor)
{
    window.textTarget = textTarget;
    window.hiddenTarget = hiddenTarget;
    this.setUrl(url);
	this.autoHide();
	this.windowProperties = 'toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,alwaysRaised,dependent,titlebar=no,resizable=yes';
    this.showPopup(anchor);
    window.addOption=addOption;
    window.addOptionTarget=textTarget;
    window.addOptionTargetName=textTarget.name;
    if(hiddenTarget!=null){
	    window.addOptionTargetHiddenName=hiddenTarget.name;
	} else{
		window.addOptionTargetHiddenName=null;
	}
    window.addOptionForm = window.textTarget.form;    
}
// ******************************
// END FieldPopup object
// ******************************