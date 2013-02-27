package presentation.actions.personalphoto;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Collections;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import utils.formbeans.ToStringComparator;
import utils.actions.NavigationFunctions;
import utils.actions.BOAdderFunctions;
import utils.formbeans.FormBeanManager;
import utils.formbeans.BOAdderFormBean;
import utils.userUtils.UserUtils;
import utils.Pair;
import utils.listFilter.ViewListConfiguration;
import utils.beanUtils.ExtendedBeanUtils;
import utils.filter.ListConfigurator;

import bussineslogic.controlers.UseCaseFacade;

import bussineslogic.objects.*;
import presentation.formbeans.objects.*;
import presentation.formbeans.personalphoto.PersonalPhotoContainer_Form;


/**
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Action_Modify_personalPhoto_Modify extends Action {
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {    	    	    

    	/** 1.  We obtain the user Information */
    	String usercode = UserUtils.getCurrentUsuarioId(request); 
    	Locale locale = UserUtils.getCurrentLocale(request); 
    	


	
	/** 2.  We obtain the initial form bean and we put it to a new FormBeanManager. */
    PersonalPhotoContainer_Form actionForm=(PersonalPhotoContainer_Form) form;
    FormBeanManager fbManager=new FormBeanManager(request, actionForm);	
    PersonalPhoto_Form photoForm=actionForm.getPersonalPhoto_Form();
	
	/** 5.  We clean the current container */
	fbManager.cleanContainer();
	
	/**Image**/
	FormFile imageFile=photoForm.getPhoto();
	
	if(imageFile.getFileSize()>1024*1024){
		NavigationFunctions.putActionError(request, mapping, "errors.imageToBig");
        return NavigationFunctions.findInput(request, mapping);        
	}
    
    if(!imageFile.getFileName().toLowerCase().endsWith(".jpg") && !imageFile.getFileName().toLowerCase().endsWith(".jpeg") && !imageFile.getFileName().toLowerCase().endsWith(".gif"))
    {
        NavigationFunctions.putActionError(request, mapping, "errors.imageFormatIncorrect");
        return NavigationFunctions.findInput(request, mapping);        
    }
	
	if(imageFile.getFileSize()>0){
		InputStream inputStream=imageFile.getInputStream();
		byte[] image=new byte[imageFile.getFileSize()];
		inputStream.read(image);
		PersonalPhoto photo=new PersonalPhoto();
		photo.setPhoto(image);
		UseCaseFacade.setPersonalPhotoToPersonal(usercode, photo, actionForm.getPersonalcode());
		photoForm.setPersonalPhotocode(photo.getCode());
	}
	
	

	/** 6.  We return to the correct page (introducing some values to the request if necessary) */
	
	
/** 6.1.  We look if the current page is beeing opened in a popup window. */
boolean isPopUp = NavigationFunctions.isPopUpWindow(request);
/** 6.2.  We look for the forward to execute (we put the current object in case it is used in the next page) */

return NavigationFunctions.findForward(request, mapping, "success", "personalPhotocode="+photoForm.getPersonalPhotocode(), isPopUp, photoForm.getPersonalPhotocode(), "");
	
	
		}
}