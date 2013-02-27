//javascript per als calendaris

function submitCalendarAction(form,schedulerParams) {
  form.action=encodeURI(form.action+schedulerParams);
  form.submit();
}

function submitInPopUp(url,form,name,value){

	var destinationUrl = url+ '.do'+'?'+name+'='+value+"&isPopUpWindow=true";

	if(form['irbholidayinfo_Form.personal.personalcode'] != null){
		destinationUrl += "&personalcode=" + form['irbholidayinfo_Form.personal.personalcode'].value;
	}

	popupWindow.setUrl(destinationUrl);
	popupWindow.autoHide();
	popupWindow.setSize(750,600);
	popupWindow.windowProperties = 'toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,alwaysRaised,dependent,titlebar=no,resizable=no';
	popupWindow.showPopup("calendaranchor");
	
	return false;
}