package utils.session;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * This class manages contains static information stored in the sesion.
 * It stores information for a given formBean in the session.
 * 
 * @author Automatika - JustInMind SL
 */
public class SessionManager {
	private static final String containerID="_container_Id";
	
	/**
	 * It deletes the information form the sesion of a formIdentifier
	 * @param request current request
	 * @param formIdentifier identifier of the current formBean. See {@link utils.formbeans.FormBeanContainer#getId()}
	 */
	public static void removeContainer(HttpServletRequest request, Object formIdentifier){
		Map valuesMap=(Map)request.getSession().getAttribute(containerID);
		if(valuesMap!=null)
			valuesMap.remove(formIdentifier);
		
	}
	
	/**
	 * It stores a value in the sesion for a property (name), given a formBeanIdentifier. If this method is invoqued, {@link SessionManager#removeContainer(HttpServletRequest, Object)} should be called in order to clean the session. 
	 * @param request current request
	 * @param formIdentifier identifier of the current formBean. See {@link utils.formbeans.FormBeanContainer#getId()}
	 * @param name name of the value to store
	 * @param value value to store
	 */
	public static void addSessionizedObject(HttpServletRequest request, Object formIdentifier, String name, Object value){
		Map valuesMap=(Map) request.getSession().getAttribute(containerID);
		if(valuesMap==null){
			valuesMap=new HashMap();
			request.getSession().setAttribute(containerID,valuesMap);
		}
		
		Map formMap=(Map) valuesMap.get(formIdentifier);
		if(formMap==null){
			formMap=new HashMap();
			valuesMap.put(formIdentifier, formMap);
		}
		formMap.put(name, value);		
	}
	
	/**
	 * It obtains a value in the sesion for a property (name), given a formBeanIdentifier; previously the value was added with {@link SessionManager#addSessionizedObject(HttpServletRequest, Object, String, Object)}
	 * @param request current request
	 * @param formIdentifier identifier of the current formBean. See {@link utils.formbeans.FormBeanContainer#getId()}
	 * @param name name of the value to obtain
	 * @return value of the property
	 */
	public static Object getSessionizedObject(HttpServletRequest request, Object formIdentifier, String name){
		Map valuesMap=(Map) request.getSession().getAttribute(containerID);
		if(valuesMap==null){
			return null;
		}
		
		Map formMap=(Map) valuesMap.get(formIdentifier);
		if(formMap==null){
			return null;
		}
		return formMap.get(name);
	}
	
}
