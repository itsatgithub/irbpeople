package presentation.actions.personalphoto;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Collections;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

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
import presentation.actions.personalphoto.GetImageAction;
import presentation.formbeans.objects.*;
import presentation.formbeans.personalphoto.*;




/**
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Action_Modify_PersonalPhoto extends Action {
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {    	    	    

    	/** 1.  We obtain the user Information */
    	String usercode = UserUtils.getCurrentUsuarioId(request); 
    	Locale locale = UserUtils.getCurrentLocale(request); 
    	



	/** 2.  We set this page as a input point (see NavigationFunctions.setInputPoint function for more info). */
	NavigationFunctions.setInputPoint(request, mapping.getPath());
	
	/** 3.  We obtain the initial form bean and we put it to a new FormBeanManager. */
	
	PersonalPhotoContainer_Form actionForm=(PersonalPhotoContainer_Form) form;

	FormBeanManager fbManager=new FormBeanManager(request, actionForm);
	
	Personal personal=UseCaseFacade.ObtainPersonal(usercode, actionForm.getPersonalcode());
	
	String photocode=GetImageAction.NO_PHOTO;
	if(personal.getPhoto()!=null){
		photocode=personal.getPhoto().getPersonalPhotocode();
	}
	request.setAttribute("_personalPhoto_img", photocode);
	
	return NavigationFunctions.findForward(request, mapping, "success");
	
	
		}
}