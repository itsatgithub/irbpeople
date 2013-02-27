package utils.formbeans;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import utils.validator.ValidatorFormAndAction;

/**
 * FormBean (which should be used as a nested form bean) which contains the information of a BOAdder (the component to add items to a list).
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class BOAdderFormBean<T> extends ValidatorFormAndAction{
	/**
	 * Possible value of <i>operation</i>. Indicates that the values shold be loaded from the database
	 */
	public static final String LOAD="load";
	/**
	 * Possible value of <i>operation</i>. Indicates that a new value has been added
	 */
	public static final String ADD="add";
	/**
	 * Possible value of <i>operation</i>. Indicates that a value has been deleted
	 */
	public static final String DELETE="delete";
	
	/**
	 * Class of the nested-pojo-form-bean
	 */
	private Class<T> clazz;
	/**
	 * Operation that the action should execute
	 */
	private String operation=LOAD;
	/**
	 * It stores the code of the element that should be removed (form the list) in case that the operation is DELETE
	 */
	private String removeCode="";
	/**
	 * It stores the nested-pojo-form-bean of the element that should be added (to the list) in case that the operation is ADD
	 */
	private T newFormBean=null;
	/**
	 * It stores the list of nested-pojo-form-bean items
	 */
	private List<T> formBeans = new ArrayList<T>();
	
	/**
	 * Creates a new BOadder Form Bean
	 * @param clazz Class of the nested-pojo-form-bean that will be stored/created/deleted
	 */
	public BOAdderFormBean(Class<T> clazz){
		this.clazz=clazz;
	}
	
	/**
	 * Returns the current operation (Operation that the action should execute).
	 * @return the current opetaion: (LOAD, ADD or DELETE).
	 */
	public String getOperation() {
		return operation;
	}

	/**
	 * Sets the current operation (Operation that the action should execute).
	 * @param operation the operation to be setted (LOAD, ADD or DELETE)
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}

	/**
	 * Returns an array of nested-pojo-form-bean of the BOAdder.
	 * @return array list of the formBeans
	 */
	public T[] getFormBeanList() {
		return formBeans.toArray((T[])Array.newInstance(clazz, 0));
	}

	/**
	 * Returns the i-th element of the list of the nested-pojo-form-bean of the BOAdder. In case that the list does not have enough elements, a number of neessary elements are added to the list.
	 * @param i the position to load
	 * @return nested-pojo-form-bean in the i-th position
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public T getFormBean(int i) throws InstantiationException, IllegalAccessException {
		while (i >= formBeans.size()) {
			formBeans.add(clazz.newInstance());
		}
		return formBeans.get(i);
	}

	/**
	 * Setts an array list to the list of the nested-pojo-form-bean of the BOAdder
	 * @param formBean array of nested-pojo-form-bean to be setted
	 */
	public void setFormBeanList(T[] formBean) {
		formBeans.clear();
		Collections.addAll(formBeans, formBean);
	}

	/**
	 * Setts an array list to the list of the nested-pojo-form-bean of the BOAdder.
	 * @param index of the element to replace
	 * @param formBean nested-pojo-form-bean to be stored at the specified position
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public void setFormBean(int index, T formBean) throws IllegalAccessException, InvocationTargetException {
		formBeans.set(index, formBean);
	}

	/**
	 * Returns the nested-pojo-form-bean of the new instance. This variable stores the form bean of the element that should be added (to the list) in case that the operation is ADD
	 * @return the nested-pojo-form-bean
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public T getNewFormBean() throws InstantiationException, IllegalAccessException {
		if(newFormBean==null)
			newFormBean=clazz.newInstance();
		return newFormBean;
	}

	/**
	 * Setts the nested-pojo-form-bean of the new instance. This variable stores the form bean of the element that should be added (to the list) in case that the operation is ADD
	 * @param newFormBean the nested-pojo-form-bean to set.
	 */
	public void setNewFormBean(T newFormBean) {
		this.newFormBean = newFormBean;
	}
	
	/**
	 * Returns the code of the nested-pojo-form-bean to be removed in case that the operation is DELETE
	 * @return the code of the nested-pojo-form-bean to be removed
	 */
	public String getRemoveCode() {
		return removeCode;
	}

	/**
	 * Setts the code of the nested-pojo-form-bean to be removed in case that the operation is DELETE
	 * @param remove_reservacode the code of the nested-pojo-form-bean to be removed
	 */
	public void setRemoveCode(String remove_reservacode) {
		this.removeCode = remove_reservacode;
	}
	
	/**
	 * Returns the class of nested-pojo-form-bean of the BOAdder
	 * @return the class of the nested-pojo-form-bean
	 */
	public Class<T> getBOAdderClass(){
		return clazz;
	}
}
