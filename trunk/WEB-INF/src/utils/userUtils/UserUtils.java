package utils.userUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import presentation.formbeans.objects.Auditmessage_Form;
import presentation.formbeans.objects.Auditmessagetype_Form;

import utils.beanUtils.ExtendedBeanUtils;
import utils.filter.ListConfigurator;

import bussineslogic.controlers.UseCase;
import bussineslogic.controlers.UseCaseFacade;
import bussineslogic.excepciones.InternalException;
import bussineslogic.objects.Auditmessage;
import bussineslogic.objects.Usuario;

/**
 * Static utils to work with users
 * 
 * @author Automatika - JustInMind SL
 */
public class UserUtils {
	
	public static final String LOGIN_PATH="/login/displayLoginForm";
	public static final String LOGIN_ACTION_PATH="/login/displayLoginForm.do";
	public static final String DO_LOGIN_PATH="/login/doLogin";
	public static final String DO_LOGOUT_PATH="/login/doLogout";	
	public static final String ADD_USER_PATH="/user/displayAddUser";
	public static final String DO_ADD_USER_PATH="/user/doAddUser";
	
	public static final String ATT_AUDITMSG_DATE = "att_auditmsg_date";
	public static final String ATT_SESSION_SAVED_AUDITMSG = "att_session_saved_auditmsg";

	/**
	 * Returns the current local fo the current user.
	 * @param request current request
	 * @return locale of the current user
	 */
	public static Locale getCurrentLocale(HttpServletRequest request){
		Usuario user = (Usuario)request.getSession().getAttribute("currentUser");
		if(user==null)
			return new Locale("en");
		
		String loc=user.getLanguage().getLanguagecode();
		if(loc==null) {
			return new Locale("en");
		}
		else {
			return new Locale(loc);
		}
	}
	
	/**
	 * Returns the current user.
	 * @param request current request
	 * @return current user
	 */
	public static Usuario getCurrentUsuario(HttpServletRequest request){
		Usuario user = (Usuario)request.getSession().getAttribute("currentUser");
		return user;
	}
	

	public static void setCurrentUsuario(HttpServletRequest request, Usuario user){
		request.getSession().setAttribute("currentUser", user);
	}
	
	/**
	 * Logs out the current user.
	 * @param request current request
	 */
	public static void removeCurrentUsuario(HttpServletRequest request){
		request.getSession().removeAttribute("currentUser");		
	}
	
	/**
	 * Returns the username of the current user.
	 * @param request current request
	 * @return the username (not the usercode)
	 */
	public static String getCurrentUsername(HttpServletRequest request){
		Usuario user = (Usuario)request.getSession().getAttribute("currentUser");
		if(user==null) return "";
		return user.getUsername();
	}	

	/**
	 * Returns the usercode of the current user.
	 * @param request current request
	 * @return usercode (not username)
	 */
	public static String getCurrentUsuarioId(HttpServletRequest request){
		Usuario user =	getCurrentUsuario(request);
		if(user==null) return "";
		return user.getUsuariocode();
	}
	
	/**
	 * Returns true if a user has been logged
	 * @param request current request
	 * @return true if a user has been logged
	 */
	public static boolean isLogged(HttpServletRequest request){
		Usuario user =	getCurrentUsuario(request);
		if(user==null) return false;
		return true;
	}
	
	public static List<Auditmessage_Form> obtainUnseenAuditMessagesFromCurrentUser(HttpServletRequest request) throws IllegalAccessException, InvocationTargetException, InstantiationException, InternalException {
		List<Auditmessage_Form> result;
		if(getCurrentUsuarioId(request)==null || getCurrentUsuarioId(request).equals("")){
			result=new ArrayList<Auditmessage_Form>();
		}
		else {
			ListConfigurator configurator=new ListConfigurator();
			configurator.setFilter("timestamp", request.getSession().getAttribute(ATT_AUDITMSG_DATE), ListConfigurator.GREATER);
			request.getSession().setAttribute(ATT_AUDITMSG_DATE, new Date());
			List<Auditmessage> auditMessages= UseCaseFacade.ObtainAuditmessageFromUser(getCurrentUsuarioId(request), getCurrentUsuarioId(request), configurator).second;
			result= ExtendedBeanUtils.copyPropertiesToFormBean(auditMessages, getCurrentLocale(request), Auditmessage_Form.class);
		}
		List<Auditmessage_Form> sessionAuditMessages=(List<Auditmessage_Form>) request.getSession().getAttribute(ATT_SESSION_SAVED_AUDITMSG);
		if(sessionAuditMessages!=null){
			result.addAll(sessionAuditMessages);
			request.getSession().setAttribute(ATT_SESSION_SAVED_AUDITMSG, null);
		}
		
		return result;
	}
	
	public static void addSessionSavedAuditMessage(HttpServletRequest request, String key) throws IllegalAccessException, InvocationTargetException, InstantiationException{
		List<Auditmessage_Form> oldValues=(List<Auditmessage_Form>) request.getSession().getAttribute(ATT_SESSION_SAVED_AUDITMSG);
		if(oldValues==null){
			oldValues=new ArrayList<Auditmessage_Form>();
			request.getSession().setAttribute(ATT_SESSION_SAVED_AUDITMSG, oldValues);
		}
		
		Auditmessage_Form msg=new Auditmessage_Form();
		
		Locale locale = UserUtils.getCurrentLocale(request);
		ResourceBundle bundle=ResourceBundle.getBundle("MessageResources", locale);
		msg.setMessage(bundle.getString(key));
		
		oldValues.add(msg);
	}
	
	// return true if current user is RRHH
	public static boolean isRRHH(HttpServletRequest request) {
		Usuario u = getCurrentUsuario(request);
		if(u!=null)
			return UseCase.isHHRR(u);
		// es un usuari no logejat 
		else return true;
	}
	
	// return true if current user is RRHH
	public static boolean checkRole(HttpServletRequest request, String rolename) {
		Usuario u = getCurrentUsuario(request);
		if(u!=null)
			return UseCase.checkRole(u, rolename);
		// es un usuari no logejat 
		else return true;
	}
		
}