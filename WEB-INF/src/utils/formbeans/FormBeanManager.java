package utils.formbeans;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.struts.action.ActionForm;

import utils.beanUtils.ExtendedBeanUtils;
import utils.session.SessionManager;
import utils.userUtils.UserUtils;

/**
 * This class manages the formBeans; filling its contents, storing and deleting information from the session. FormBeans managers are created inside each action.
 *  
 * @author Automatika - JustInMind SL
 *
 */
public class FormBeanManager {
	/**
	 * Current http request
	 */
	HttpServletRequest request;
	/**
	 * FormBeanContainer of the manager
	 */
	FormBeanContainer container;
	
	/**
	 * Creates a new FormBeanManager with an empty formBeanContainer. In case a formBeanContainer exists FormBeanManager(HttpServletRequest , FormBeanContainer) should be used.
	 * @param request the current request 
	 * @param formBeanClazz the class of the form bean that the manager has to manage (the FormBeanContainer)
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public FormBeanManager(HttpServletRequest request, Class formBeanClazz) throws InstantiationException, IllegalAccessException{
		this(request, (FormBeanContainer) formBeanClazz.newInstance());
	}
	
	/**
	 * Creates a new FormBeanManager with a given formBeanContainer. This call should be used when a form bean is obtained in an Action and has to be readed.
	 * @param request the current request 
	 * @param container the formBeanContainer to manage
	 */
	public FormBeanManager(HttpServletRequest request, FormBeanContainer container) {
		this.request=request;
		this.container=container;
	}
	
	/**
	 * Sets the attribute "new form bean" of the BOAdder FormBean {@link utils.formbeans.BOAdderFormBean}.
	 * @param boAdderName name of the BOAdder
	 * @param formBean formBean (nested-pojo-form-bean) to be set as the "new form bean"
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public void setBOAdderNew(String boAdderName, ActionForm formBean) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		BOAdderFormBean boAdderFormBean=(BOAdderFormBean) getFormBean(boAdderName);
		boAdderFormBean.setNewFormBean(formBean);
	}
	
	/**
	 * Sets a list of pojos as the list of nested-pojo-form-bean of a BOAdder.
	 * @param pojoList list of pojos to store
	 * @param boAdderName name of the BOAdder
	 * @param formBeanClass class of the nested-pojo-form-bean where the info of the pojoList will be stored.
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 */
	public void setBOAdderList(List pojoList, String boAdderName, Class formBeanClass) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException{

		BOAdderFormBean boAdderFormBean=(BOAdderFormBean) getFormBean(boAdderName);
		
		List formBeans=new Vector();
		for(int i=0; i<pojoList.size(); ++i){
			formBeans.add(formBeanClass.newInstance());
		}
		
		ExtendedBeanUtils.copyPropertiesToFormBean(formBeans, pojoList, UserUtils.getCurrentLocale(request));
		
		boAdderFormBean.setFormBeanList(formBeans.toArray((Object[])Array.newInstance(boAdderFormBean.getBOAdderClass(), 0)));
		
		SessionManager.addSessionizedObject(request, container.getId(), boAdderName+".List", pojoList);
	}
	
	/**
	 * Returns a list of pojos (DTOs) obtained from the nested-pojo-form-bean of a BOAdder formbean. In case that the nested-pojo-form-bean is an old pojo (not created in the BOAdder), the unfilled data of the nested-pojo-form-bean is filled from the original pojo.
	 * @param boAdderName Name of the BOAdder
	 * @return the list of pojos (DTOs) obtained form the BOAdder
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 */
	public List getPOJOBOAdderList(String boAdderName) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException{
		
		List pojo=(List) SessionManager.getSessionizedObject(request, container.getId(), boAdderName+".List");
		
		BOAdderFormBean boAdderFormBean=(BOAdderFormBean) getFormBean(boAdderName);
		Object[] formBean=boAdderFormBean.getFormBeanList();
		List aux=Arrays.asList(formBean);
		
		if(aux.isEmpty()){
			return pojo;
		}
		ExtendedBeanUtils.copyPropertiesFromFormBean(pojo, aux, UserUtils.getCurrentLocale(request));
		
		return pojo;
	}
	
	/**
	 * Obtains a pojo (DTO) from the attribute "new form bean" of the BOAdder FormBean {@link utils.formbeans.BOAdderFormBean}.
	 * @param pojoClazz class of the pojo to obtain
	 * @return the pojo (DTO) obtained form the nested-pojo-form-bean
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 */
	public Object getPOJOBOAdderNew(String boAdderName, Class pojoClazz) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException{
		Object pojo=pojoClazz.newInstance();

		BOAdderFormBean boAdderFormBean=(BOAdderFormBean) getFormBean(boAdderName);
		ActionForm formBean=(ActionForm) boAdderFormBean.getNewFormBean();
		
		ExtendedBeanUtils.copyPropertiesFromFormBean(pojo, formBean, UserUtils.getCurrentLocale(request));
		
		return pojo;
	}
	
	/**
	 * Sets a list of pojos as an attribute (nested-pojo-form-bean) of the container form bean.
	 * @param pojo list of pojos
	 * @param name name of the attribute of the container
	 * @param formBeanClass Class of the nested-pojo-form-bean to which the pojos are converted
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 */
	public void setAttribute(List pojo, String name, Class formBeanClass) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException{
//		Class formBeanClass=BeanUtilsBean.getInstance().getPropertyUtils().getPropertyDescriptor(container, name).getReadMethod().getReturnType();
		List formBeans=new Vector();
		for(int i=0; i<pojo.size(); ++i){
			formBeans.add(formBeanClass.newInstance());
		}
		
		ExtendedBeanUtils.copyPropertiesToFormBean(formBeans, pojo, UserUtils.getCurrentLocale(request));
//		formBeanClass
		
		PropertyDescriptor desc=BeanUtilsBean.getInstance().getPropertyUtils().getPropertyDescriptor(container, name);
		Class arrayClass=desc.getWriteMethod().getParameterTypes()[0].getComponentType();
		
		BeanUtils.setProperty(container,name, formBeans.toArray((Object[])Array.newInstance(arrayClass, 0)));
		
		SessionManager.addSessionizedObject(request, container.getId(), name, pojo);
	}
	
	/**
	 * Sets a pojo as an attribute (nested-pojo-form-bean) of the container form bean
	 * @param pojo pojo to insert
	 * @param name name of the attribute of the container
	 * @param formBeanClass Class of the nested-pojo-form-bean to which the pojos are converted
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 */
	public void setAttribute(Object pojo, String name, Class formBeanClass) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException{
		setAttribute(pojo, name, formBeanClass, null);
	}

	/**
	 * Sets a nested-pojo-form-bean obtained from both a pojo and a nested-pojo-form-bean, as an attribute of the container form bean. It merges the pojo and the nested-pojo-form-bean with the method {@link utils.beanUtils.ExtendedBeanUtils#copyPropertiesToFormBean(ActionForm,Object,Locale)} 
	 * @param pojo pojo to insert
	 * @param name name of the attribute of the container
	 * @param formBeanClass Class of the nested-pojo-form-bean to which the pojos are converted
	 * @param formBean nested-pojo-form-bean to insert
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 */
	public void setAttribute(Object pojo, String name, Class formBeanClass, ActionForm formBean) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException{
//		Class formBeanClass=BeanUtilsBean.getInstance().getPropertyUtils().getPropertyDescriptor(container, name).getReadMethod().getReturnType();
		

		if(formBean==null){
			formBean=(ActionForm) formBeanClass.newInstance();
		}
		
		if(pojo!=null){
			ExtendedBeanUtils.copyPropertiesToFormBean(formBean, pojo, UserUtils.getCurrentLocale(request));
		}
		
		BeanUtils.setProperty(container,name, formBean);
		
		SessionManager.addSessionizedObject(request, container.getId(), name, pojo);
	}
	
	/**
	 * Obtains a pojo (DTO) from an attribute (nested-pojo-form-bean) of the container.
	 * @param name name of the attribute of the container
	 * @param pojoClazz class of the pojo to obtain
	 * @return returns the pojo DTO
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 */
	public Object getPOJO(String name, Class pojoClazz) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException{
		Object pojo=SessionManager.getSessionizedObject(request, container.getId(), name);
		
		if(pojo==null){
			pojo=pojoClazz.newInstance();
		}/* else {
			PropertyDescriptor[] descriptors=BeanUtilsBean.getInstance().getPropertyUtils().getPropertyDescriptors(pojo);
			for(int i=0; i<descriptors.length; ++i){
				PropertyDescriptor desc=descriptors[i];
				Object obj=BeanUtils.getProperty(pojo, desc.getName());
				if(obj instanceof PersistentCollection){
					BeanUtils.setProperty(pojo, desc.getName(), new HashSet());
				}
			}	
		}*/

		ActionForm formBean=getFormBean(name);
		
		ExtendedBeanUtils.copyPropertiesFromFormBean(pojo, formBean, UserUtils.getCurrentLocale(request));
		
		return pojo;
	}
	
	/**
	 * Obtains a list of pojos (DTO) from an attribute (list of nested-pojo-form-bean) of the container.
	 * @param name name of the attribute of the container
	 * @param pojoClazz class of the pojo to obtain
	 * @return returns the list of pojo DTO
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 */
	public List getPOJOs(String name, Class pojoClazz) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException{
		//TODO increase list
		
		List pojo=(List) SessionManager.getSessionizedObject(request, container.getId(), name);
		Object[] formBean=(Object[]) BeanUtilsBean.getInstance().getPropertyUtils().getProperty(container, name);
		List aux=Arrays.asList(formBean);
		
		if(aux.isEmpty()){
			return pojo;
		}
		ExtendedBeanUtils.copyPropertiesFromFormBean(pojo, aux, UserUtils.getCurrentLocale(request));
		
		return pojo;
	}

	/**
	 * @return the container form bean
	 */
	public FormBeanContainer getContainer() {
		return container;
	}
	
	/**
	 * Cleans the information related to the formbean which is stored in the sesion. This method must be called in order to clean the sesion 
	 */
	public void cleanContainer(){
		if(container!=null)
			SessionManager.removeContainer(request,container.getId());
	}
	
	/**
	 * Obtains a nested form bean from the container
	 * @param name name of the attribute of the container
	 * @return the nested form bean
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	private ActionForm getFormBean(String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		ActionForm formBean=(ActionForm) BeanUtilsBean.getInstance().getPropertyUtils().getProperty(container, name);
		return formBean;
	}
	
	/*public <T> List<T> actualizarBOAdder(String usercode, BOAdderFormBean<T> boAdderBean, String iniBOCode, String initBOName, Method method){
	ActionForm initBOForm=getFormBean(initBOName);
	
	List<T> resultList=null;
	if(boAdderBean.getOperation().equals(BOAdderFormBean.LOAD)){
		Pair<Integer,List> reservas = SistemaReservasFacade.ListarReservasPendientesUsuario(usercode,iniBOCode,new ListConfigurator());
		resultList=reservas.getSecond();
		boAdderBean.getNewFormBean().getUsuario().setUsuariocode(iniBOCode);
	}
	else if(boAdderBean.getOperation().equals(BOAdderFormBean.ADD)){
		resultList=getPOJOs("reservaForm", Reserva.class);
		Reserva newReservaTO= (Reserva) getPOJO("newreserva",Reserva.class);
		newReservaTO.setCode(BOAdderFunctions.generateCode());
		newReservaTO.setSala(SistemaReservasFacade.ObtenerSala(usercode, boAdderBean.getNewFormBean().getSala().getSalacode()));
		resultList.add(newReservaTO);
		boAdderBean.setNewFormBean(new ReservaForm());
		boAdderBean.getNewFormBean().getUsuario().setUsuariocode(iniBOCode);
		
	} else if(boAdderBean.getOperation().equals(BOAdderFormBean.DELETE)){
		resultList=getPOJOs("reservaForm", Reserva.class);
		
		for(T to_delete : resultList){
			if(to_delete.getReservacode().equals(boAdderBean.getRemoveCode())){
				break;
			}
		}
		resultList.remove(to_delete);
	}
}*/
	
}
