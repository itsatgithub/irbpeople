
//Move the selected item from Select Box 'source'
//to Select Box 'destination
//-------------------------------------------------
function copyToList(source,destination)
{

  for (i=0;i<source.options.length;i++)
  {
    var current = source.options[i];
    if (current.selected)
    {
      txt = current.text;
      val = current.value;
      destination.options[destination.length] = new Option(txt,val);
      source.options[i] = null;
      i--;
    }
  }
}

//Move one position up the selected Item in Select Box 'combo'	
//-----------------------------------------------------------	
function moveUp(combo)
{		
	boxLen = combo.length;
	var count = 0;
	var i;
	for (i = 0; i < combo.length; i++){
		if(combo.options[i].selected == true){
			count++;
		}
	}
	
	if (count>1){
		alert('You cannot order multiple elements');
	}
	else {
		currentItem = combo.selectedIndex;

		if (currentItem > 0) {
		
			selText = combo.options[currentItem].text;
			swpText = combo.options[currentItem - 1].text;
			selValue = combo.options[currentItem].value;
			swpValue = combo.options[currentItem - 1].value;
			
			combo.options[currentItem - 1].text = selText;
			combo.options[currentItem].text     = swpText;
			combo.options[currentItem - 1].value = selValue;
			combo.options[currentItem].value     = swpValue;
			
			combo.selectedIndex = currentItem - 1;
		}
	}
}


//Move one position down the selected Item in Select Box 'combo'	
//---------------------------------------------------------------
function moveDown(combo)
{
	boxLen = combo.length;
	currentItem = combo.selectedIndex;
	if ((currentItem < boxLen - 1) && (currentItem != -1)) {
			
		selText = combo.options[currentItem].text;
		swpText = combo.options[currentItem + 1].text;
		selValue = combo.options[currentItem].value;
		swpValue = combo.options[currentItem + 1].value;
	
		combo.options[currentItem + 1].text = selText;
		combo.options[currentItem].text     = swpText;
		combo.options[currentItem + 1].value = selValue;
		combo.options[currentItem].value     = swpValue;
			
		combo.selectedIndex = currentItem + 1;
	}
}

//Enable or disable a field depending on a Select Box option
//-----------------------------------------------------------
function enableDisable2Fields(combo, field1, field2)
{
	currentItem = combo.selectedIndex;
	var val=combo.options[currentItem].value
	if (val == "")
	{
		field1.disabled=true;
		field2.disabled=true;
	}	
	else if(val == "between")
	{
		field1.disabled=false;
		field2.disabled=false;
	}
	else{
		field1.disabled=false;
		field2.disabled=true;
	}
}

// ------------------------------------
function enableDisableField(combo, field)
{
	currentItem = combo.selectedIndex;
	var val=combo.options[currentItem].value
	if (val == "")
	{
		field.disabled=true;
	}	
	else{
		field.disabled=false;
	}
}

// ------------------------------------
function selectAll(aList1, aList2) {

  for (var i=0; i<aList1.options.length; i++) {
    aList1.options[i].selected = true;
  }
   for (var i=0; i<aList2.options.length; i++) {
    aList2.options[i].selected = true;
  }
}

// -------------------------------------
function urlReplaceAction(strUrl, strOldAction, strNewAction) {
  if (strUrl == null) {
	strUrl = document.URL;
  } 
  var strUrlBase = strUrl;
  var iArgsStart = strUrl.indexOf(strOldAction);
  if (iArgsStart != -1) {
  	strUrlBase = strUrl.substring(0, iArgsStart);
  	strUrl = strUrlBase + strNewAction;
  }
  return strUrl;
}

// -------------------------------------
function validate_execution(url) {

  if(document.forms[0].SELselectedfields.options.length == 0) {
  	alert('You must select at least 1 field to show');
  }
  else {
  	selectAll(document.forms[0].SELselectedfields, document.forms[0].ORDselectedfields)
  	var strUrl = document.URL;
  	strUrl = urlReplaceAction(strUrl, 'new-listing', 'new-listing-ok');
    submitAction(url,document.forms[0],false,null);
  	
  	//document.central.submit();
  }
}
/**
// -------------------------------------
function validateModify() {

  if(document.central.SELselectedfields.options.length == 0) {
  	alert('You must select at least 1 field to show');
  }
  else {
  	selectAll(document.central.SELselectedfields, document.central.ORDselectedfields)
  	var strUrl = document.URL;
  	strUrl = urlReplaceAction(strUrl, 'modify-listing', 'modify-listing-ok');
  	//alert(strUrl);
  	submitAction(strUrl, document.central, false, false);
  	//document.central.submit();
  }
} **/