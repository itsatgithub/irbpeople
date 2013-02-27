package utils.actions;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.ForwardAction;

import bussineslogic.objects.Auditmessage;

import utils.session.SessionManager;
import utils.userUtils.UserUtils;

/**
 * This class contains static functions that are used in actions or jsps, related to the navigation of the application. 
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class NavigationFunctions {
	
	private static final String POPUP_PREFIX = "popUp_";

	/**
	 * Sets a path as the current return point of the current session. The return point is the place where the application navigates to, in case that no forward is defined in the struts config.
	 * @param request the current request
	 * @param path the path to be set as the return point (the path can be optained from an ActionMapping with mapping.getPath()).
	 */
	public static void setReturnPoint(HttpServletRequest request, String path) {
		SessionManager.addSessionizedObject(request, "all", "returnPoint", path
				+ ".do");
	}

	/**
	 * Obtains the path that represents the current return point of the current session. The return point is the place where the application navigates to, in case that no forward in defined in the struts config.
	 * @param request the current request
	 * @return the path (including the final .do) which is the current return point.
	 */
	public static String getReturnPoint(HttpServletRequest request) {
		return (String) SessionManager.getSessionizedObject(request, "all",
				"returnPoint");
	}

	/**
	 * Sets a path as the current input point of the current session. The return input is the place where the application navigates to, in case that no input is defined in the struts config and a validation error is produced.
	 * @param request the current request
	 * @param path the path to be set as the input point (the path can be optained from an ActionMapping with mapping.getPath()).
	 */
	public static void setInputPoint(HttpServletRequest request, String path) {
		SessionManager.addSessionizedObject(request, "all", "inputPoint", path
				+ ".do");
	}

	/**
	 * Obtains the path that represents the current input point of the current session. The return point is the place where the application navigates to, in case that no forward in defined in the struts config.
	 * @param request the current request
	 * @return the path (including the final .do) which is the current input point.
	 */
	public static String getInputPoint(HttpServletRequest request) {
		return (String) SessionManager.getSessionizedObject(request, "all",
				"inputPoint");
	}

	/**
	 * Finds the forward for an action
	 * @param request the current request
	 * @param mapping the ActionMapping
	 * @param name Logical name of the forwarding instance to be returned
	 * @param isPopUp indicates whereas the current window is a popup window
	 * @return returns the forward to be done by the action
	 */
	public static ActionForward findForward(HttpServletRequest request, ActionMapping mapping, String name, boolean isPopUp) {
		if(isPopUp==true){
			return new ActionForward("/_popUp/pop_Up_Closer.do");
		}
		return findForward(request,mapping,name);
	}
	
	/**
	 * Finds the forward for an action which is not opened in a popup window.
	 * @param request the current request
	 * @param mapping the ActionMapping
	 * @param name Logical name of the forwarding instance to be returned
	 * @return returns the forward to be done by the action
	 */
	public static ActionForward findForward(HttpServletRequest request,
			ActionMapping mapping, String name) {
		return findForward(request, mapping, name, null);
	}
	
	/**
	 * Finds the forward for an action
	 * @param request the current request
	 * @param mapping the ActionMapping
	 * @param name Logical name of the forwarding instance to be returned
	 * @param config
	 * @param isPopUp indicates whereas the current window is a popup window
	 * @param createdCode indicates which is the code of the object that has been created/modified/viewed in the current action
	 * @param createdDescription indicates which is the description (toString method) of the object that has been created/modified/viewed in the current action
	 * @return returns the forward to be done by the action
	 */
	public static ActionForward findForward(HttpServletRequest request,
			ActionMapping mapping, String name, String config, boolean isPopUp, String createdCode, String createdDescription) {		
		if(isPopUp==true){
			request.setAttribute("newCode", createdCode);
			request.setAttribute("newTitle", createdDescription);			
			
			return new ActionForward("/_popUp/pop_Up_Closer.do");
		}
		return findForward(request,mapping,name,config);
	}

	/**
	 * Finds the forward for an action
	 * @param request the current request
	 * @param mapping the ActionMapping
	 * @param name Logical name of the forwarding instance to be returned
	 * @param config
	 * @return returns the forward to be done by the action
	 */
	public static ActionForward findForward(HttpServletRequest request,
			ActionMapping mapping, String name, String config) {
		ActionForward path = mapping.findForward(name);

		if (path == null) {
			path = new ActionForward(getReturnPoint(request));
			path.setRedirect(true);
			path.freeze();
		}

		else if (config != null) {
			path = new ActionForward(path);
			path.setPath(path.getPath() + "?" + config);
			path.freeze();
		}

		return path;
	}

	/**
	 * returns the popup action name for an action; it returns the name of an action when it is viewed as a popup
	 * @param actionName name of the initial action
	 * @return name of the popup action
	 */
	public static String getPopUpActionName(String actionName) {
		int slashPoint = actionName.lastIndexOf("/") + 1;
		String result = actionName.substring(0, slashPoint);
		String pageName=actionName.substring(slashPoint);
		if(!pageName.startsWith(POPUP_PREFIX))
			pageName = POPUP_PREFIX + pageName;
		result += pageName;
		return result;
	}
	
	/**
	 * Returns the name of the current action
	 * @param am current action mapping
	 * @return the name of the action. If it is a popup aciton, the 'real' action is returned.
	 */
	public static String getActionName(ActionMapping am){
		
		String[] names=am.getPath().split("/");
		
		String actionName=names[2];
		
		if(actionName.contains(POPUP_PREFIX)){
			actionName = actionName.replaceFirst(POPUP_PREFIX, "");
		}
		return actionName;
	}
	
	/**
	 * Returns the name of the current command (the group in the site, of the folder in the path)
	 * @param am current action mapping
	 * @return the name of the command 
	 */
	public static String getCommandName(ActionMapping am){
		
		String[] names=am.getPath().split("/");

		String commandName=names[1];
				
		return commandName;
	}
	
    /**
     * Returns the name of the current action
     * @param request current request
     * @return the name of the action. If it is a popup aciton, the 'real' action is returned.
     */
    public static String getActionName(PageContext context){
        String actionName=(String) context.getRequest().getAttribute("_jim_site_action_name");
        return actionName;
    }
    
    /**
     * Returns the name of the current command (the group in the site, of the folder in the path)
     * @param request current request
     * @return the name of the command 
     */
    public static String getCommandName(PageContext context){
        String commandName=(String) context.getRequest().getAttribute("_jim_site_group_name");
        return commandName;
    }

	/**
	 * returns true if the action is a popup window
	 * @param request current request
	 * @return returns true if the current action is a popup window
	 */
	public static boolean isPopUpWindow(HttpServletRequest request){
		return request.getParameter("isPopUpWindow")!=null;
	}
	
	public static ActionForward findInput(HttpServletRequest request, ActionMapping mapping){
		String input = mapping.getInput();

		// beggining of the added code
		if (input == null)
			input = NavigationFunctions.getInputPoint(request);
		
		return new ActionForward(input);
	}
	
	public static ActionForward putActionError(HttpServletRequest request, ActionMapping mapping, String message){
		ActionErrors errors=new ActionErrors();
    	ActionMessages messages=new ActionMessages();
    	messages.add(message, new ActionMessage(message));
    	errors.add(messages);
    	request.setAttribute(Globals.ERROR_KEY, errors);        	
    	return mapping.findForward("error");
	}
	
	public static ActionForward putActionError(HttpServletRequest request, ActionMapping mapping, Map<String, List<String>> message){
		ActionErrors errors=new ActionErrors();
    	ActionMessages messages=new ActionMessages();
    	
    	Locale loc=UserUtils.getCurrentLocale(request);
		ResourceBundle bundle = ResourceBundle.getBundle("MessageResources", loc);
    	
    	for(Entry<String, List<String> > entry:message.entrySet()){
    		String property=entry.getKey();
    		for(String att : entry.getValue()){
    			//convertir att a string
                
                String attName;
                
                try
                {
                    attName=bundle.getString(att);
                } catch(MissingResourceException e)
                {
                    attName=att;
                }
    			ActionMessage msg=new ActionMessage(property, attName);
    			messages.add(property, msg);
    		}
    	}
    	
    	errors.add(messages);
    	request.setAttribute(Globals.ERROR_KEY, errors);
    	return mapping.findForward("error");
	}
}
