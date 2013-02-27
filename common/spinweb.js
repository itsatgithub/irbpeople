	function repositioningSectionMenu() {} // dummy, to avoid multiple javascript errors
	// this function is located in: pages/facade-protected/protected_menu.jsp

	function getSelectedRadio(buttonGroup) {
	   // returns the array number of the selected radio button or -1 if no button is selected
	   if (buttonGroup[0]) { // if the button group is an array (one button is not an array)
	      for (var i=0; i<buttonGroup.length; i++) {
	         if (buttonGroup[i].checked) {	         	
	            return i
	         }
	      }
	   } else {
	      if (buttonGroup.checked) { return 0; } // if the one button is checked, return zero
	   }
	   // if we get to this point, no radio button is selected
	   return null;
	} // Ends the "getSelectedRadio" function

	function getSelectedRadioValue(buttonGroup) {
	   // returns the value of the selected radio button or "" if no button is selected
	   var i = getSelectedRadio(buttonGroup);
	   if (i == -1) {
	      return "";
	   } else {
	      if (buttonGroup[i]) { // Make sure the button group is an array (not just one button)
	         return buttonGroup[i].value;
	      } else { // The button group is just the one button, and it is checked
	         return buttonGroup.value;
	      }
	   }
	} // Ends the "getSelectedRadioValue" function
	
	
	function debug()
	{
		//alert('document.forms.length='+document.forms.length);
		//alert('document.forms[0].name=' + document.forms[0].name);
		//alert('document.forms["central"].name=' + document.forms["central"].name);
	}
	
	function submitAction(url,form,existcode,codevalue) {
		submitAction(url,form,existcode,codevalue, null);				
	}
	
	function submitAction(url,form,existcode,codevalue, objectname) {
		
		var oldAction=form.action;
		var newAction = encodeURI(url + '.do');
		
		if(typeof thisisapopup != 'undefined') 
		{		
			newAction=urlAddParam(newAction, 'isPopUpWindow','true');	
		}
				
		form.action=newAction;
		
		var codigo = null;
		var valorcodigo = null;
		valorcodigo = codevalue;
		if (existcode){
			// el siguiente código supone que los radiobuttons o checkboxes del viewlist
			//	se llaman igual que el formulario(sin el form) + 'code'
			if (valorcodigo != null || numberSelectedItems(form[objectname + 'code']) == 1)
			{				
				form.submit();
			}
			else
			{
				alertOneItem();
				form.action=oldAction;
				return
			}	
		}
		
		form.submit();
		
		
	}
	
	function alertOneItem()
	{
		alert("You must select one item");
	}
	


	
	function numberSelectedItems(buttonGroup)
	{
		var numSelected = 0
		
		if (buttonGroup == null)
		{
			return 0;
		}
		
		if(isArray(buttonGroup)){
		
			if (buttonGroup[0]) { 
		      for (var i=0; i<buttonGroup.length; i++)
		         if (buttonGroup[i].checked) numSelected++;
		   } else {
		      if (buttonGroup.checked) { numSelected++; } // if the one button is checked, return zero
		   }
		   // if we get to this point, no radio button is selected
		   //alert(numSelected);
	   return numSelected;
	   }
	   else {
		//return 1
			return (buttonGroup.checked)?1:0;

	   }
	
	}
	
	function isArray(obj) {
		if (isNaN(obj.length))
			return false;
		else
			return true;
	}
	
	function submitMultipleAction(url, form, existcode, codevalue) {
		submitMultipleAction(url, form, existcode, codevalue, null);
	}
	
	function submitMultipleAction(url, form, existcode, codevalue, objectName) {
		
		if( numberSelectedItems(form.item) == 0)
		{
			alertOneItem();
		}
		else
		{
			submitAction(url, form, false, false, objectName);
		}
	}		
	
	function pagination(accion){
		document.pager.straction.value = accion;
		//document.pager.action= encodeURI(strUrl);
		document.pager.submit();
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
	
	function numItems(form){	
		var url = "";
		url = window.location.href;
		url = urlRemoveParam(url,"itemsperpage");
		url = urlRemoveParam(url,"hasback");
		url = urlRemoveParam(url,"hasnext");
		url = urlRemoveParam(url,"numpage");
		url = urlRemoveParam(url,"min");
		url = urlRemoveParam(url,"max");
		form.itemsperpage.value = form.itemspage[form.itemspage.selectedIndex].value;
		form.action=url;
		form.submit();
	}

	// Deletes pagination parameters, changes sortby parameter according to the
	//   column header clicked by the user and submits the form in order to reload
	//   the list. If the user clicks a second time on a column header the 
	//   sorting is changed to descendent (ej: "name asc" -> "name desc")
	// Parameters:
	//    form: the list's parent form
	//    order: the name of the column clicked by the user.
	
	function sort(form,order){
		var url = "";
		url = window.location.href;
		url = urlRemoveParam(url,"itemsperpage");
		url = urlRemoveParam(url,"hasback");
		url = urlRemoveParam(url,"hasnext");
		url = urlRemoveParam(url,"numpage");
		url = urlRemoveParam(url,"min");
		url = urlRemoveParam(url,"max");
		url = urlRemoveParam(url,"sortby");
		
		currentOrderby = form.sortby.value;
		
		var tmp = currentOrderby.split(' ');
	
		//tmp[0] = previous ordering column name
		//tmp[1] = previous ordering asc or desc
		
		// if order == tmp[0] then the user clicked on the same column again
		//   so we must change from asc to desc or the way around.
		
		if(tmp[0] == order) 
			tmp[1] = tmp[1] == "asc" ? "desc" : "asc";
		else
			tmp[1] = "asc"
				
		form.sortby.value = order + " " + tmp[1];
		
		var existcode = false;
		submitAction(url,form,existcode,null);
	}

	function actionURL(command, action)
	{
		var url = document.URL;
		
		// addparam no trata bien los anchors '#'
		//  arreglo para los popups en mozilla (urls acaban en #)
		if(url.substr(url.length-1,1) == '#')
			url = url.substring(0,url.length -1);
		
		//alert(url);
		url = urlRemoveParam(url, "command");
		url = urlRemoveParam(url, "action");
		//alert(url);
		url = urlAddParam(url, "command", command);
		url = urlAddParam(url, "action", action);
		
		//alert(url);
		return url;
	}   
	
	function actionURL3(command, action, param)
	{
		
		var url = actionURL(command, action);
				
		//alert(url);
		url = urlRemoveParam(url, "param");
		//alert(url);
		url = urlAddParam(url, "param", param);
		
		//alert(url);
		return url;
	}
	
	function actionURL4(command, action, param1, param2)
	{
		
		var url = actionURL3(command, action, param1);
				
		//alert(url);
		url = urlRemoveParam(url, "param2");
		//alert(url);
		url = urlAddParam(url, "param2", param2);
		
		//alert(url);
		return url;
	}  
	
	function selectAll(buttonGroup)
	{
		var numSelected = 0
		
		if (buttonGroup == null)
		{
			return 
		}
		
		if (buttonGroup[0])
		{ 
	    	for (var i=0; i<buttonGroup.length; i++)
	      	{
	      		if(document.central.select_all.checked != buttonGroup[i].checked) buttonGroup[i].click();
	      	}
	   	}
	   	else
	   	{
			if(document.central.select_all.checked != buttonGroup.checked) buttonGroup.click();
	   	}
		   
	}

	function maxLength(event,maxchars)
	{
		var e= event ? event : window.event;
	
		//alert(e.target.value.length)
	
		if( e.which==0 || e.which==8 || e.ctrlKey==true)
		{
		// backspace or ctrl key pressed in Mozilla
			return true;
		}
		else
		{
			if(e.target)
				return ( e.target.value.length < maxchars );
			else
				return ( e.srcElement.value.length < maxchars);
		}
	}
	
	function reloadAndDeleteFile(){
		var doc = document.central.docid.value;
		var url = window.opener.location.href;
		url = urlRemoveParam(url,"documentid");
		window.opener.location.href = url+'&documentid='+doc;
	}
	
	function changeValue(field, value, trueString, falseString) {
		if(value){
			field.value=trueString;
		}
		else{
			field.value=falseString;
		}
		
	}
	
	function positionFunction(value)
	{
		var oText
		var oSelect
		if (document.getElementById) {
			oText = document.getElementById("positionText");
			oSelect = document.getElementById("positionSelect");
		}
		else if (document.all) {
			oText = document.all("positionText");
			oSelect = document.all("positionSelect");
		}
		
		if(value=='99999')
		{
			oText.disabled=true;
			oText.style.display='none';
			
			
			
			oSelect.disabled=false;
			oSelect.style.display='inline';
		}
		else
		{
			oText.disabled=false;
			oText.style.display='inline';

			oSelect.disabled=true;
			oSelect.style.display='none';
		}

	}
	
	function changesWarning(message)
	{
		
		if(typeof modified != 'undefined')
		{

			if(modified==true) if(!confirm(message)) return false;
		}
		return true;
	}