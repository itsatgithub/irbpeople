package utils.beanUtils;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.struts.action.ActionForm;

import utils.beanUtils.JIMBeanUtilsBean;
import utils.beanUtils.converters.BooleanConverter;
import utils.beanUtils.converters.ByteArrayConverter;
import utils.beanUtils.converters.DateConverter;
import utils.beanUtils.converters.NumberConverter;
import utils.beanUtils.converters.StringConverter;

public class ExtendedBeanUtils {

	/**
	 * Initializes the conversions with a given locale
	 * @param locale locale used in the conversions
	 */
//	public static void initializeConverters(Locale locale){	
//		JIMBeanUtilsBean.getInstance().getConvertUtils().register(new DateConverter(locale), Date.class);
//		JIMBeanUtilsBean.getInstance().getConvertUtils().register(new StringConverter(locale), String.class);
//		JIMBeanUtilsBean.getInstance().getConvertUtils().register(new NumberConverter(locale), BigDecimal.class);
//		JIMBeanUtilsBean.getInstance().getConvertUtils().register(new NumberConverter(locale), Integer.class);
//		JIMBeanUtilsBean.getInstance().getConvertUtils().register(new NumberConverter(locale), int.class);
//		JIMBeanUtilsBean.getInstance().getConvertUtils().register(new BooleanConverter(locale), Boolean.class);
//		JIMBeanUtilsBean.getInstance().getConvertUtils().register(new BooleanConverter(locale), boolean.class);
//		
//		byte byteArray[] = new byte[0];
//		JIMBeanUtilsBean.getInstance().getConvertUtils().register(new ByteArrayConverter(), byteArray.getClass());
//		
//		//this is set in order to enable null results
//		BeanUtilsBean.getInstance().getConvertUtils().register(new BigDecimalConverter(null), BigDecimal.class);
//	}
	
	/**
	 * Copies the unsetted properties of a list of formbeans from a list of pojos, using copyPropertiesToFormBean for each pair. The two lists must be equal size
	 * 
	 * @param dest list of form beans to which the info of the pojos should be inserted
	 * @param orig list of pojos
	 * @param locale locale to use for the conversions
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 */
	public static void copyPropertiesToFormBean(List dest, List orig, Locale locale) throws IllegalAccessException, InvocationTargetException{
		if(dest.size()!=orig.size()){
			throw new InvocationTargetException(new Throwable("canot copy lists of differnt sizes"));
		}
		Iterator i_orig=orig.iterator();
		for(Iterator i_dest=dest.iterator(); i_dest.hasNext(); ){
			copyPropertiesToFormBean((ActionForm)i_dest.next(), i_orig.next(), locale);
		}
	}
	
	/**
	 * Copies the properties of a list of pojos to a new list of form beans, using copyPropertiesToFormBean for each pair. The two lists must be equal size
	 * 
	 * @param orig list of pojos
	 * @param locale locale to use for the conversions
	 * @param clazz class of the form beans to be created
	 * @return returns a list of form beans of type clazz, with the properties of the pojos
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 */
	public static List copyPropertiesToFormBean(List orig, Locale locale, Class clazz) throws IllegalAccessException, InvocationTargetException, InstantiationException{
		List dest=new ArrayList();
		
		for(int i=0; i<orig.size(); ++i){
			dest.add(clazz.newInstance());
		}
		copyPropertiesToFormBean(dest, orig, locale);
		return dest;
	}
	

	/**
	 * Copies the setted properties of a list of formbeans to a list of pojos, using copyPropertiesFromFormBean for each pair. The two lists must be equal size
	 * @param dest list of pojos to be modified
	 * @param orig list of form beans
	 * @param locale locale to be used in the conversion
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 */
	public static void copyPropertiesFromFormBean(List dest, List orig, Locale locale) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException{
		if(dest.size()!=orig.size()){
			throw new InvocationTargetException(new Throwable("canot copy lists of differnt sizes"));
		}
		Iterator i_orig=orig.iterator();
		for(Iterator i_dest=dest.iterator(); i_dest.hasNext(); ){
			copyPropertiesFromFormBean(i_dest.next(), (ActionForm)i_orig.next(), locale);
		}
	}
	
	/**
	 * Inserts information form a POJO to a form bean, obtaining all the data which is not set in the form bean (dest) form the POJO (orig):
	 * <p>Copy property values from the origin Pojo to the destination bean
     * for all cases where the property names are the same and where the form
     * bean has no value setted.  For each
     * property, a conversion is attempted as necessary.  All combinations of
     * standard JavaBeans and DynaBeans as origin and destination are
     * supported.  Properties that exist in the origin bean, but do not exist
     * in the destination bean (or are read-only in the destination bean) are
     * silently ignored.</p>
	 *   
	 * @param dest form bean to be modified
	 * @param orig pojo with the information
	 * @param locale locale to be used in conversions
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static void copyPropertiesToFormBean(ActionForm dest, Object orig, Locale locale) throws IllegalAccessException, InvocationTargetException {
//		initializeConverters(locale);
		//Object result=dest.getClass().newInstance();
		//Object result=convert(dest, dest.getClass(),locale);
		
		JIMBeanUtilsBean.getInstance(locale).copyPropertiesToFormBean(dest, orig);
	}
	
	/**
	 * Inserts information form a formBean to a Pojo, inserting all the data which is set in the form bean (dest) to the POJO (orig):
	 * <p>Copy property values from the origin Pojo to the destination bean
     * for all cases where the property names are the same and where the form
     * bean has no value setted.  For each
     * property, a conversion is attempted as necessary.  All combinations of
     * standard JavaBeans and DynaBeans as origin and destination are
     * supported.  Properties that exist in the origin bean, but do not exist
     * in the destination bean (or are read-only in the destination bean) are
     * silently ignored.</p>
     * 
	 * @param dest pojo to be modified
	 * @param orig form bean with the information
	 * @param locale locale to be used in conversions
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 */
	public static void copyPropertiesFromFormBean(Object dest, ActionForm orig, Locale locale) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException{
//		initializeConverters(locale);
		

		JIMBeanUtilsBean.getInstance(locale).copyPropertiesFromFormBean(dest, orig);

	}
	
/*	public static List copyCollectionProperties(Collection dest, Collection orig, Class clazz, Locale locale) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException{
		List<Object> result=new ArrayList<Object>();
		Iterator<Object> j=dest.iterator();
		for(Iterator<Object> i=orig.iterator(); i.hasNext();){
			if(j.hasNext())
				result.add(copyProperties(j.next(),i.next(),locale));
			else {
				result.add(convert(i.next(), clazz,locale));
			}
		}
		return result;
	}
	
	public static Object convert(Object origin, Class desti, Locale locale) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		Object result= desti.newInstance();
		result=copyProperties(result, origin, locale);
		
		return result;
	}
	
	public static List convertCollection(Collection origin, Class desti, Locale locale) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		List<Object> result=new ArrayList<Object>();
		for(Iterator<Object> i=origin.iterator(); i.hasNext();){
			result.add(convert(i.next(), desti,locale));
		}
		return result;
	}*/
	
	/**
	 * Converts a value to a class using the current converters, given a locale.
	 */
	public static Object convertSimpleValueTo(Object value, Class clazz, Locale locale){
//		initializeConverters(locale);
		Converter converter = JIMBeanUtilsBean.getInstance(locale).getConvertUtils().lookup(clazz);
		if (converter != null) {
			value = converter.convert(clazz, value);
			return value;
		}
		return null;
	}
}
