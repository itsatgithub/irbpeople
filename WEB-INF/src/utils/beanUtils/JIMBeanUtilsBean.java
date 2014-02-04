/*
 * Copyright 2001-2004 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 


package utils.beanUtils;


import java.beans.IndexedPropertyDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ContextClassLoaderLocal;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaClass;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.MappedPropertyDescriptor;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;

import utils.NonPersistent;
import utils.Persistent;
import utils.beanUtils.converters.BooleanConverter;
import utils.beanUtils.converters.ByteArrayConverter;
import utils.beanUtils.converters.DateConverter;
import utils.beanUtils.converters.NumberConverter;
import utils.beanUtils.converters.StringConverter;


/**
 * This class is a modification of the class BeanUtilsBean.
 * 
 * @author Automatika - JustInMind SL
 *
 */

public class JIMBeanUtilsBean {
	private boolean fromFormBeanCopy=false;
	private boolean fromPojoCopy=false;
	private boolean firstTime=true;

    // ------------------------------------------------------ Private Class Variables

    /** 
     * Contains <code>BeanUtilsBean</code> instances indexed by context classloader.
     */
	private static final ContextClassLoaderLocal 
	beansByClassLoader = new ContextClassLoaderLocal() {
	    // Creates the default instance used when the context classloader is unavailable
	    protected Object initialValue() {
	        return new HashMap<Locale, JIMBeanUtilsBean>();
	    }
	};
    
    /** 
     * Gets the instance which provides the functionality for {@link BeanUtils}.
     * This is a pseudo-singleton - an single instance is provided per (thread) context classloader.
     * This mechanism provides isolation for web apps deployed in the same container. 
     */
	protected synchronized static JIMBeanUtilsBean getInstance(Locale locale) {
	    Map<Locale, JIMBeanUtilsBean> temp = (Map<Locale, JIMBeanUtilsBean>) beansByClassLoader.get();

	    if(temp.containsKey(locale))
	    {
	        return temp.get(locale);
	    }
	    else
	    {
	        JIMBeanUtilsBean utilsBean = new JIMBeanUtilsBean();
	        utilsBean.getConvertUtils().register(new DateConverter(locale), Date.class);
	        utilsBean.getConvertUtils().register(new StringConverter(locale), String.class);
	        utilsBean.getConvertUtils().register(new NumberConverter(locale), BigDecimal.class);
	        utilsBean.getConvertUtils().register(new NumberConverter(locale), Integer.class);
	        utilsBean.getConvertUtils().register(new NumberConverter(locale), int.class);
	        utilsBean.getConvertUtils().register(new BooleanConverter(locale), Boolean.class);
	        utilsBean.getConvertUtils().register(new BooleanConverter(locale), boolean.class);

	        byte byteArray[] = new byte[0];
	        utilsBean.getConvertUtils().register(new ByteArrayConverter(), byteArray.getClass());

	        //this is set in order to enable null results
	        BeanUtilsBean.getInstance().getConvertUtils().register(new BigDecimalConverter(null), BigDecimal.class);

	        temp.put(locale, utilsBean);

	        return utilsBean;
	    }
	}

    /** 
     * Sets the instance which provides the functionality for {@link BeanUtils}.
     * This is a pseudo-singleton - an single instance is provided per (thread) context classloader.
     * This mechanism provides isolation for web apps deployed in the same container. 
     */
    protected synchronized static void setInstance(JIMBeanUtilsBean newInstance) {
        beansByClassLoader.set(newInstance);
    }

    // --------------------------------------------------------- Attributes

    /**
     * Logging for this instance
     */
    private Log log = LogFactory.getLog(JIMBeanUtilsBean.class);
    
    /** Used to perform conversions between object types when setting properties */
    private ConvertUtilsBean convertUtilsBean;
    
    /** Used to access properties*/
    private JIMPropertyUtilsBean JIMPropertyUtilsBean;

    // --------------------------------------------------------- Constuctors

    /** 
     * <p>Constructs an instance using new property 
     * and conversion instances.</p>
     */
    protected JIMBeanUtilsBean() {
        this(new ConvertUtilsBean(), new JIMPropertyUtilsBean());
    }

    /** 
     * <p>Constructs an instance using given property and conversion instances.</p>
     *
     * @param convertUtilsBean use this <code>ConvertUtilsBean</code> 
     * to perform conversions from one object to another
     * @param JIMPropertyUtilsBean use this <code>JIMPropertyUtilsBean</code>
     * to access properties
     */
    protected JIMBeanUtilsBean(		
                            ConvertUtilsBean convertUtilsBean, 
                            JIMPropertyUtilsBean JIMPropertyUtilsBean) {
                            
        this.convertUtilsBean = convertUtilsBean;
        this.JIMPropertyUtilsBean = JIMPropertyUtilsBean;
    }

    // --------------------------------------------------------- protected Methods

    /**
     * <p>Clone a bean based on the available property getters and setters,
     * even if the bean class itself does not implement Cloneable.</p>
     *
     * <p>
     * <strong>Note:</strong> this method creates a <strong>shallow</strong> clone.
     * In other words, any objects referred to by the bean are shared with the clone
     * rather than being cloned in turn.
     * </p>
     *
     * @param bean Bean to be cloned
     *
     * @exception IllegalAccessException if the caller does not have
     *  access to the property accessor method
     * @exception InstantiationException if a new instance of the bean's
     *  class cannot be instantiated
     * @exception InvocationTargetException if the property accessor method
     *  throws an exception
     * @exception NoSuchMethodException if an accessor method for this
     *  property cannot be found
     */
    protected Object cloneBean(Object bean)
            throws IllegalAccessException, InstantiationException,
            InvocationTargetException, NoSuchMethodException {

        if (log.isDebugEnabled()) {
            log.debug("Cloning bean: " + bean.getClass().getName());
        }
        Class clazz = bean.getClass();
        Object newBean = null;
        if (bean instanceof DynaBean) {
            newBean = ((DynaBean) bean).getDynaClass().newInstance();
        } else {
            newBean = bean.getClass().newInstance();
        }
        getPropertyUtils().copyProperties(newBean, bean);
        return (newBean);

    }


    /**
     * ------
     * MODIFIED METHOD, THIS JAVADOC IS NOT COMPLETE
     * ------
     * 
     * <p>Copy property values from the origin bean to the destination bean
     * for all cases where the property names are the same.  For each
     * property, a conversion is attempted as necessary.  All combinations of
     * standard JavaBeans and DynaBeans as origin and destination are
     * supported.  Properties that exist in the origin bean, but do not exist
     * in the destination bean (or are read-only in the destination bean) are
     * silently ignored.</p>
     *
     * <p>If the origin "bean" is actually a <code>Map</code>, it is assumed
     * to contain String-valued <strong>simple</strong> property names as the keys, pointing at
     * the corresponding property values that will be converted (if necessary)
     * and set in the destination bean. <strong>Note</strong> that this method
     * is intended to perform a "shallow copy" of the properties and so complex
     * properties (for example, nested ones) will not be copied.</p>
     *
     * <p>This method differs from <code>populate()</code>, which
     * was primarily designed for populating JavaBeans from the map of request
     * parameters retrieved on an HTTP request, is that no scalar->indexed
     * or indexed->scalar manipulations are performed.  If the origin property
     * is indexed, the destination property must be also.</p>
     *
     * <p>If you know that no type conversions are required, the
     * <code>copyProperties()</code> method in {@link PropertyUtils} will
     * execute faster than this method.</p>
     *
     * <p><strong>FIXME</strong> - Indexed and mapped properties that do not
     * have getter and setter methods for the underlying array or Map are not
     * copied by this method.</p>
     *
     * @param dest Destination bean whose properties are modified
     * @param orig Origin bean whose properties are retrieved
     *
     * @exception IllegalAccessException if the caller does not have
     *  access to the property accessor method
     * @exception IllegalArgumentException if the <code>dest</code> or
     *  <code>orig</code> argument is null
     * @exception InvocationTargetException if the property accessor method
     *  throws an exception
     */
    protected void copyProperties(Object dest, Object orig)
        throws IllegalAccessException, InvocationTargetException {
    	
        // Validate existence of the specified beans
        if (dest == null) {
            throw new IllegalArgumentException
                    ("No destination bean specified");
        }
        if (orig == null) {
        	if(isFromFormBeanCopy()){
        		//nothing should be done
        		return;
        	}
            throw new IllegalArgumentException("No origin bean specified");
        }
        if (log.isDebugEnabled()) {
            log.debug("BeanUtils.copyProperties(" + dest + ", " +
                      orig + ")");
        }

        // Copy the properties, converting as necessary
        if (orig instanceof DynaBean) {
            throw new IllegalArgumentException("this method can not work with DynaBean");
        } else if (orig instanceof Map) {
        	throw new IllegalArgumentException("this method can not work with Map");
        } else /* if (orig is a standard JavaBean) */ {
        	
        	if(isFromFormBeanCopy()){
        		if (Persistent.class.isInstance(dest) && !firstTime) {
					// in this case only the code should be copied
					Persistent persistent = (Persistent) dest;
					try{
					Object formBeanCode = getFormBeanCodePropertyValue(orig,
							persistent);
					if(formBeanCode!=null){
						//PropertyDescriptor[] descirptors=getPropertyUtils().getPropertyDescriptors(persistent.getClass());
						
						if(!formBeanCode.equals(persistent.getCode())){
							
							try {
//							    log.error("****** (1) comprobar si hay que quitar estas líneas de código, ver diagnet *****");
								Object blankCopy=dest.getClass().newInstance();
//								 erases all the old values
//								BeanUtils.copyProperties(persistent, blankCopy);								
								persistent.setCode((String) formBeanCode);
								
							} catch (Exception e) {
								throw new IllegalArgumentException(e);
							}
							
						}
						return;
					}
					}catch (Exception e){
						throw new IllegalArgumentException("No se puede obtener el codigo del objeto"+orig+"."+" Estas seguro que el nombre del codigo es el nombre de la clase + code?");
					}
				}
        		firstTime=false;
			}
        	
        	
        	
            PropertyDescriptor origDescriptors[] =
                getPropertyUtils().getPropertyDescriptors(orig);
            for (int i = 0; i < origDescriptors.length; i++) {
                String name = origDescriptors[i].getName();
                if ("class".equals(name)) {
                    continue; // No point in trying to set an object's class
                }
                if (getPropertyUtils().isReadable(orig, name) &&
                    getPropertyUtils().isWriteable(dest, name)) {
                    try {
                        Object value = getPropertyUtils().getSimpleProperty(orig, name);
                        if(!isFromFormBeanCopy() || value!=null)
                        copyProperty(dest, name, value);
                    } catch (NoSuchMethodException e) {
                        ; // Should not happen
                    }
                }
            }
        }

    }


    /**
     * 
     * ------
     * MODIFIED METHOD, THIS JAVADOC IS NOT COMPLETE
     * ------
     * 
     * <p>Copy the specified property value to the specified destination bean,
     * performing any type conversion that is required.  If the specified
     * bean does not have a property of the specified name, or the property
     * is read only on the destination bean, return without
     * doing anything.  If you have custom destination property types, register
     * {@link Converter}s for them by calling the <code>register()</code>
     * method of {@link ConvertUtils}.</p>
     *
     * <p><strong>IMPLEMENTATION RESTRICTIONS</strong>:</p>
     * <ul>
     * <li>Does not support destination properties that are indexed,
     *     but only an indexed setter (as opposed to an array setter)
     *     is available.</li>
     * <li>Does not support destination properties that are mapped,
     *     but only a keyed setter (as opposed to a Map setter)
     *     is available.</li>
     * <li>The desired property type of a mapped setter cannot be
     *     determined (since Maps support any data type), so no conversion
     *     will be performed.</li>
     * </ul>
     *
     * @param bean Bean on which setting is to be performed
     * @param name Property name (can be nested/indexed/mapped/combo)
     * @param value Value to be set
     *
     * @exception IllegalAccessException if the caller does not have
     *  access to the property accessor method
     * @exception InvocationTargetException if the property accessor method
     *  throws an exception
     */
    protected void copyProperty(Object bean, String name, Object value)
        throws IllegalAccessException, InvocationTargetException {

        // Trace logging (if enabled)
        if (log.isTraceEnabled()) {
            StringBuffer sb = new StringBuffer("  copyProperty(");
            sb.append(bean);
            sb.append(", ");
            sb.append(name);
            sb.append(", ");
            if (value == null) {
                sb.append("<NULL>");
            } else if (value instanceof String) {
                sb.append((String) value);
            } else if (value instanceof String[]) {
                String values[] = (String[]) value;
                sb.append('[');
                for (int i = 0; i < values.length; i++) {
                    if (i > 0) {
                        sb.append(',');
                    }
                    sb.append(values[i]);
                }
                sb.append(']');
            } else {
                sb.append(value.toString());
            }
            sb.append(')');
            log.trace(sb.toString());
        }

        //TODO: form here to next 'TODO' the code has not been revised
        
        // Resolve any nested expression to get the actual target bean
        Object target = bean;
        int delim = name.lastIndexOf(PropertyUtils.NESTED_DELIM);
        if (delim >= 0) {
            try {
                target =
                    getPropertyUtils().getProperty(bean, name.substring(0, delim));
            } catch (NoSuchMethodException e) {
                return; // Skip this property setter
            }
            name = name.substring(delim + 1);
            if (log.isTraceEnabled()) {
                log.trace("    Target bean = " + target);
                log.trace("    Target name = " + name);
            }
        }

        // Declare local variables we will require
        String propName = null;          // Simple name of target property
        Class type = null;               // Java type of target property
        int index = -1;                  // Indexed subscript value (if any)
        String key = null;               // Mapped key value (if any)

        // Calculate the target property name, index, and key values
        propName = name;
        int i = propName.indexOf(PropertyUtils.INDEXED_DELIM);
        if (i >= 0) {
            int k = propName.indexOf(PropertyUtils.INDEXED_DELIM2);
            try {
                index =
                    Integer.parseInt(propName.substring(i + 1, k));
            } catch (NumberFormatException e) {
                ;
            }
            propName = propName.substring(0, i);
        }
        int j = propName.indexOf(PropertyUtils.MAPPED_DELIM);
        if (j >= 0) {
            int k = propName.indexOf(PropertyUtils.MAPPED_DELIM2);
            try {
                key = propName.substring(j + 1, k);
            } catch (IndexOutOfBoundsException e) {
                ;
            }
            propName = propName.substring(0, j);
        }

        //TODO: until here (from last 'TODO') the code has not been revised
        
        // Calculate the target property type
        if (target instanceof DynaBean) {
            throw new IllegalArgumentException("this method can not work with DynaBean");
        } else {
            PropertyDescriptor descriptor = null;
            try {
                descriptor =
                    getPropertyUtils().getPropertyDescriptor(target, name);
                if (descriptor == null) {
                    return; // Skip this property setter
                }
            } catch (NoSuchMethodException e) {
                return; // Skip this property setter
            }
            type = descriptor.getPropertyType();
            if (type == null) {
                // Most likely an indexed setter on a POJB only
                if (log.isTraceEnabled()) {
                    log.trace("    target type for property '" +
                              propName + "' is null, so skipping ths setter");
                }
                return;
            }
        }
        if (log.isTraceEnabled()) {
            log.trace("    target propName=" + propName + ", type=" +
                      type + ", index=" + index + ", key=" + key);
        }

        //TODO: form here to next 'TODO' the code has not been revised
        // Convert the specified value to the required type and store it
        if (index >= 0) {                    // Destination must be indexed
            Converter converter = getConvertUtils().lookup(type.getComponentType());
            if (converter != null) {
                log.trace("        USING CONVERTER " + converter);
                value = converter.convert(type, value);
            }
            try {
                getPropertyUtils().setIndexedProperty(target, propName,
                                                 index, value);
            } catch (NoSuchMethodException e) {
                throw new InvocationTargetException
                    (e, "Cannot set " + propName);
            }
        } else if (key != null) {            // Destination must be mapped
            // Maps do not know what the preferred data type is,
            // so perform no conversions at all
            // FIXME - should we create or support a TypedMap?
            try {
                getPropertyUtils().setMappedProperty(target, propName,
                                                key, value);
            } catch (NoSuchMethodException e) {
                throw new InvocationTargetException
                    (e, "Cannot set " + propName);
            }
			// TODO: until here (from last 'TODO') the code has not been revised
        } else {
        	
        	if (isFromPojoCopy() && !Persistent.class.isInstance(value) && !NonPersistent.class.isInstance(value)) {
				try {
					Object oldValue = getPropertyUtils().getProperty(target,
							propName);
					if(!(oldValue==null || (String.class.isInstance(oldValue) && ((String)oldValue).equalsIgnoreCase(""))))
					{
//					    log.error("****** (1) comprobar si hay que quitar estas líneas de código, ver diagnet *****");
					    return;
					}
				} catch (NoSuchMethodException e2) {

				}
			}
        	// Destination must be simple
            Converter converter = getConvertUtils().lookup(type);
            if (converter != null ) { 
                log.trace("        USING CONVERTER " + converter);
                if(propName.equals("year"))
                {
                  //chapuza para que los años no salgan formateados
                    value=value.toString();
                }
                else
                {
                    value = converter.convert(type, value);
                }
            }
            try {
            	
                getPropertyUtils().setSimpleProperty(target, propName, value);
            } catch (Exception e) {
                try{
                    //it is not a simple property, so it should be recursivelly copied
                    Object originalValue=getPropertyUtils().getProperty(target, propName);
                    if(originalValue==null){
                        //if it is null it should be created
                        Class clazz=getPropertyUtils().getPropertyType(target, propName);
                        originalValue=clazz.newInstance();
                        getPropertyUtils().setProperty(target, propName, originalValue);
                    }
                    
                    if(target instanceof bussineslogic.objects.Personal)
                    {
                        if(name.equals("country")
                                && (originalValue instanceof bussineslogic.objects.Country)
                                && (value instanceof presentation.formbeans.objects.Country_IDForm))
                        {
                            bussineslogic.objects.Country newCountry = new bussineslogic.objects.Country();
                            newCountry.setCode( ((presentation.formbeans.objects.Country_IDForm)value).getCountrycode());
                            ((bussineslogic.objects.Personal)target).setCountry(newCountry);
                        }
                        else if(name.equals("birth_country")
                                && (originalValue instanceof bussineslogic.objects.Country)
                                && (value instanceof presentation.formbeans.objects.Country_IDForm))
                        {
                            bussineslogic.objects.Country newCountry = new bussineslogic.objects.Country();
                            newCountry.setCode( ((presentation.formbeans.objects.Country_IDForm)value).getCountrycode());
                            ((bussineslogic.objects.Personal)target).setBirth_country(newCountry);
                        }
                        else if(name.equals("end_of_contract_country")
                                && (originalValue instanceof bussineslogic.objects.Country)
                                && (value instanceof presentation.formbeans.objects.Country_IDForm))
                        {
                            bussineslogic.objects.Country newCountry = new bussineslogic.objects.Country();
                            newCountry.setCode( ((presentation.formbeans.objects.Country_IDForm)value).getCountrycode());
                            ((bussineslogic.objects.Personal)target).setEnd_of_contract_country(newCountry);
                        }
                        else if(name.equals("nationality")
                                && (originalValue instanceof bussineslogic.objects.Nationality)
                                && (value instanceof presentation.formbeans.objects.Nationality_IDForm))
                        {
                            bussineslogic.objects.Nationality newNationality = new bussineslogic.objects.Nationality();
                            newNationality.setCode( ((presentation.formbeans.objects.Nationality_IDForm)value).getNationalitycode());
                            ((bussineslogic.objects.Personal)target).setNationality(newNationality);
                        }
                        else if(name.equals("nationality_2")
                                && (originalValue instanceof bussineslogic.objects.Nationality)
                                && (value instanceof presentation.formbeans.objects.Nationality_IDForm))
                        {
                            bussineslogic.objects.Nationality newNationality = new bussineslogic.objects.Nationality();
                            newNationality.setCode( ((presentation.formbeans.objects.Nationality_IDForm)value).getNationalitycode());
                            ((bussineslogic.objects.Personal)target).setNationality_2(newNationality);
                        }
                        else
                        {
                            copyProperties(originalValue, value);
                        }
                    }
                    else if(target instanceof bussineslogic.objects.Alumni_personal)
                    {
                        if(name.equals("nationality")
                                && (originalValue instanceof bussineslogic.objects.Nationality)
                                && (value instanceof presentation.formbeans.objects.Nationality_IDForm))
                        {
                            bussineslogic.objects.Nationality newNationality = new bussineslogic.objects.Nationality();
                            newNationality.setCode( ((presentation.formbeans.objects.Nationality_IDForm)value).getNationalitycode());
                            ((bussineslogic.objects.Alumni_personal)target).setNationality(newNationality);
                        }
                        else if(name.equals("nationality_2")
                                && (originalValue instanceof bussineslogic.objects.Nationality)
                                && (value instanceof presentation.formbeans.objects.Nationality_IDForm))
                        {
                            bussineslogic.objects.Nationality newNationality = new bussineslogic.objects.Nationality();
                            newNationality.setCode( ((presentation.formbeans.objects.Nationality_IDForm)value).getNationalitycode());
                            ((bussineslogic.objects.Alumni_personal)target).setNationality_2(newNationality);
                        }
                        else
                        {
                            copyProperties(originalValue, value);
                        }
                    }
                    else if (target instanceof bussineslogic.objects.Professional)
                    {
                        if(name.equals("payroll_institution")
                                && (originalValue instanceof bussineslogic.objects.Payroll_institution)
                                && (value instanceof presentation.formbeans.objects.Payroll_institution_IDForm))
                        {
                            bussineslogic.objects.Payroll_institution newInstitution = new bussineslogic.objects.Payroll_institution();
                            newInstitution.setCode(((presentation.formbeans.objects.Payroll_institution_IDForm)value).getPayroll_institutioncode());
                            ((bussineslogic.objects.Professional)target).setPayroll_institution(newInstitution);
                        }
                        else if(name.equals("payroll_institution_2")
                                && (originalValue instanceof bussineslogic.objects.Payroll_institution)
                                && (value instanceof presentation.formbeans.objects.Payroll_institution_IDForm))
                        {
                            bussineslogic.objects.Payroll_institution newInstitution = new bussineslogic.objects.Payroll_institution();
                            newInstitution.setCode(((presentation.formbeans.objects.Payroll_institution_IDForm)value).getPayroll_institutioncode());
                            ((bussineslogic.objects.Professional)target).setPayroll_institution_2(newInstitution);
                        }
                        else if(name.equals("professional_unit")
                        		&& (originalValue instanceof bussineslogic.objects.Unit)
                        		&& (value instanceof presentation.formbeans.objects.Unit_IDForm))
                        {
                        	bussineslogic.objects.Unit newUnit = new bussineslogic.objects.Unit();
                        	newUnit.setCode(((presentation.formbeans.objects.Unit_IDForm)value).getUnitcode());
                        	((bussineslogic.objects.Professional)target).setProfessional_unit(newUnit);
                        }
                        else if(name.equals("professional_unit_3")
                        		&& (originalValue instanceof bussineslogic.objects.Unit)
                        		&& (value instanceof presentation.formbeans.objects.Unit_IDForm))
                        {
                        	bussineslogic.objects.Unit newUnit = new bussineslogic.objects.Unit();
                        	newUnit.setCode(((presentation.formbeans.objects.Unit_IDForm)value).getUnitcode());
                        	((bussineslogic.objects.Professional)target).setProfessional_unit_3(newUnit);
                        }
                        else if(name.equals("professional_unit_4")
                        		&& (originalValue instanceof bussineslogic.objects.Unit)
                        		&& (value instanceof presentation.formbeans.objects.Unit_IDForm))
                        {
                        	bussineslogic.objects.Unit newUnit = new bussineslogic.objects.Unit();
                        	newUnit.setCode(((presentation.formbeans.objects.Unit_IDForm)value).getUnitcode());
                        	((bussineslogic.objects.Professional)target).setProfessional_unit_4(newUnit);
                        }
                        else if(name.equals("research_group")
                        		&& (originalValue instanceof bussineslogic.objects.Research_group)
                        		&& (value instanceof presentation.formbeans.objects.Research_group_IDForm))
                        {
                        	bussineslogic.objects.Research_group newResearchGroup = new bussineslogic.objects.Research_group();
                        	newResearchGroup.setCode(((presentation.formbeans.objects.Research_group_IDForm)value).getResearch_groupcode());
                        	((bussineslogic.objects.Professional)target).setResearch_group(newResearchGroup);
                        }
                        else if(name.equals("research_group_2")
                        		&& (originalValue instanceof bussineslogic.objects.Research_group)
                        		&& (value instanceof presentation.formbeans.objects.Research_group_IDForm))
                        {
                        	bussineslogic.objects.Research_group newResearchGroup = new bussineslogic.objects.Research_group();
                        	newResearchGroup.setCode(((presentation.formbeans.objects.Research_group_IDForm)value).getResearch_groupcode());
                        	((bussineslogic.objects.Professional)target).setResearch_group_2(newResearchGroup);
                        }
                        else if(name.equals("research_group_3")
                        		&& (originalValue instanceof bussineslogic.objects.Research_group)
                        		&& (value instanceof presentation.formbeans.objects.Research_group_IDForm))
                        {
                        	bussineslogic.objects.Research_group newResearchGroup = new bussineslogic.objects.Research_group();
                        	newResearchGroup.setCode(((presentation.formbeans.objects.Research_group_IDForm)value).getResearch_groupcode());
                        	((bussineslogic.objects.Professional)target).setResearch_group_3(newResearchGroup);
                        }
                        else if(name.equals("research_group_4")
                        		&& (originalValue instanceof bussineslogic.objects.Research_group)
                        		&& (value instanceof presentation.formbeans.objects.Research_group_IDForm))
                        {
                        	bussineslogic.objects.Research_group newResearchGroup = new bussineslogic.objects.Research_group();
                        	newResearchGroup.setCode(((presentation.formbeans.objects.Research_group_IDForm)value).getResearch_groupcode());
                        	((bussineslogic.objects.Professional)target).setResearch_group_4(newResearchGroup);
                        }
                        else
                        {
                            copyProperties(originalValue, value);
                        }
                    }
                    else
                    {
                        copyProperties(originalValue, value);
                    }
                } catch(Exception e1){
            		throw new InvocationTargetException(e, "Cannot get " + propName +" of "+target );
            	}
            }
        }

    }


    /**
     * <p>Return the entire set of properties for which the specified bean
     * provides a read method. This map contains the to <code>String</code>
     * converted property values for all properties for which a read method
     * is provided (i.e. where the getReadMethod() returns non-null).</p>
     *
     * <p>This map can be fed back to a call to
     * <code>BeanUtils.populate()</code> to reconsitute the same set of
     * properties, modulo differences for read-only and write-only
     * properties, but only if there are no indexed properties.</p>
     *
     * @param bean Bean whose properties are to be extracted
     *
     * @exception IllegalAccessException if the caller does not have
     *  access to the property accessor method
     * @exception InvocationTargetException if the property accessor method
     *  throws an exception
     * @exception NoSuchMethodException if an accessor method for this
     *  property cannot be found
     */
    protected Map describe(Object bean)
            throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {

        if (bean == null) {
        //            return (Collections.EMPTY_MAP);
            return (new java.util.HashMap());
        }
        
        if (log.isDebugEnabled()) {
            log.debug("Describing bean: " + bean.getClass().getName());
        }
            
        Map description = new HashMap();
        if (bean instanceof DynaBean) {
            DynaProperty descriptors[] =
                ((DynaBean) bean).getDynaClass().getDynaProperties();
            for (int i = 0; i < descriptors.length; i++) {
                String name = descriptors[i].getName();
                description.put(name, getProperty(bean, name));
            }
        } else {
            PropertyDescriptor descriptors[] =
                getPropertyUtils().getPropertyDescriptors(bean);
            for (int i = 0; i < descriptors.length; i++) {
                String name = descriptors[i].getName();
                if (descriptors[i].getReadMethod() != null)
                    description.put(name, getProperty(bean, name));
            }
        }
        return (description);

    }


    /**
     * Return the value of the specified array property of the specified
     * bean, as a String array.
     *
     * @param bean Bean whose property is to be extracted
     * @param name Name of the property to be extracted
     *
     * @exception IllegalAccessException if the caller does not have
     *  access to the property accessor method
     * @exception InvocationTargetException if the property accessor method
     *  throws an exception
     * @exception NoSuchMethodException if an accessor method for this
     *  property cannot be found
     */
    protected String[] getArrayProperty(Object bean, String name)
            throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {

        Object value = getPropertyUtils().getProperty(bean, name);
        if (value == null) {
            return (null);
        } else if (value instanceof Collection) {
            ArrayList values = new ArrayList();
            Iterator items = ((Collection) value).iterator();
            while (items.hasNext()) {
                Object item = items.next();
                if (item == null) {
                    values.add((String) null);
                } else {
                    // convert to string using convert utils
                    values.add(getConvertUtils().convert(item));
                }
            }
            return ((String[]) values.toArray(new String[values.size()]));
        } else if (value.getClass().isArray()) {
            int n = Array.getLength(value);
            String results[] = new String[n];
            for (int i = 0; i < n; i++) {
                Object item = Array.get(value, i);
                if (item == null) {
                    results[i] = null;
                } else {
                    // convert to string using convert utils
                    results[i] = getConvertUtils().convert(item);
                }
            }
            return (results);
        } else {
            String results[] = new String[1];
            results[0] = value.toString();
            return (results);
        }

    }


    /**
     * Return the value of the specified indexed property of the specified
     * bean, as a String.  The zero-relative index of the
     * required value must be included (in square brackets) as a suffix to
     * the property name, or <code>IllegalArgumentException</code> will be
     * thrown.
     *
     * @param bean Bean whose property is to be extracted
     * @param name <code>propertyname[index]</code> of the property value
     *  to be extracted
     *
     * @exception IllegalAccessException if the caller does not have
     *  access to the property accessor method
     * @exception InvocationTargetException if the property accessor method
     *  throws an exception
     * @exception NoSuchMethodException if an accessor method for this
     *  property cannot be found
     */
    protected String getIndexedProperty(Object bean, String name)
            throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {

        Object value = getPropertyUtils().getIndexedProperty(bean, name);
        return (getConvertUtils().convert(value));

    }


    /**
     * Return the value of the specified indexed property of the specified
     * bean, as a String.  The index is specified as a method parameter and
     * must *not* be included in the property name expression
     *
     * @param bean Bean whose property is to be extracted
     * @param name Simple property name of the property value to be extracted
     * @param index Index of the property value to be extracted
     *
     * @exception IllegalAccessException if the caller does not have
     *  access to the property accessor method
     * @exception InvocationTargetException if the property accessor method
     *  throws an exception
     * @exception NoSuchMethodException if an accessor method for this
     *  property cannot be found
     */
    protected String getIndexedProperty(Object bean,
                                            String name, int index)
            throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {

        Object value = getPropertyUtils().getIndexedProperty(bean, name, index);
        return (getConvertUtils().convert(value));

    }


    /**
     * Return the value of the specified indexed property of the specified
     * bean, as a String.  The String-valued key of the required value
     * must be included (in parentheses) as a suffix to
     * the property name, or <code>IllegalArgumentException</code> will be
     * thrown.
     *
     * @param bean Bean whose property is to be extracted
     * @param name <code>propertyname(index)</code> of the property value
     *  to be extracted
     *
     * @exception IllegalAccessException if the caller does not have
     *  access to the property accessor method
     * @exception InvocationTargetException if the property accessor method
     *  throws an exception
     * @exception NoSuchMethodException if an accessor method for this
     *  property cannot be found
     */
    protected String getMappedProperty(Object bean, String name)
            throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {

        Object value = getPropertyUtils().getMappedProperty(bean, name);
        return (getConvertUtils().convert(value));

    }


    /**
     * Return the value of the specified mapped property of the specified
     * bean, as a String.  The key is specified as a method parameter and
     * must *not* be included in the property name expression
     *
     * @param bean Bean whose property is to be extracted
     * @param name Simple property name of the property value to be extracted
     * @param key Lookup key of the property value to be extracted
     *
     * @exception IllegalAccessException if the caller does not have
     *  access to the property accessor method
     * @exception InvocationTargetException if the property accessor method
     *  throws an exception
     * @exception NoSuchMethodException if an accessor method for this
     *  property cannot be found
     */
    protected String getMappedProperty(Object bean,
                                           String name, String key)
            throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {

        Object value = getPropertyUtils().getMappedProperty(bean, name, key);
        return (getConvertUtils().convert(value));

    }


    /**
     * Return the value of the (possibly nested) property of the specified
     * name, for the specified bean, as a String.
     *
     * @param bean Bean whose property is to be extracted
     * @param name Possibly nested name of the property to be extracted
     *
     * @exception IllegalAccessException if the caller does not have
     *  access to the property accessor method
     * @exception IllegalArgumentException if a nested reference to a
     *  property returns null
     * @exception InvocationTargetException if the property accessor method
     *  throws an exception
     * @exception NoSuchMethodException if an accessor method for this
     *  property cannot be found
     */
    protected String getNestedProperty(Object bean, String name)
            throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {

        Object value = getPropertyUtils().getNestedProperty(bean, name);
        return (getConvertUtils().convert(value));

    }


    /**
     * Return the value of the specified property of the specified bean,
     * no matter which property reference format is used, as a String.
     *
     * @param bean Bean whose property is to be extracted
     * @param name Possibly indexed and/or nested name of the property
     *  to be extracted
     *
     * @exception IllegalAccessException if the caller does not have
     *  access to the property accessor method
     * @exception InvocationTargetException if the property accessor method
     *  throws an exception
     * @exception NoSuchMethodException if an accessor method for this
     *  property cannot be found
     */
    protected String getProperty(Object bean, String name)
            throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {

        return (getNestedProperty(bean, name));

    }


    /**
     * Return the value of the specified simple property of the specified
     * bean, converted to a String.
     *
     * @param bean Bean whose property is to be extracted
     * @param name Name of the property to be extracted
     *
     * @exception IllegalAccessException if the caller does not have
     *  access to the property accessor method
     * @exception InvocationTargetException if the property accessor method
     *  throws an exception
     * @exception NoSuchMethodException if an accessor method for this
     *  property cannot be found
     */
    protected String getSimpleProperty(Object bean, String name)
            throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {

        Object value = getPropertyUtils().getSimpleProperty(bean, name);
        return (getConvertUtils().convert(value));

    }


    /**
     * <p>Populate the JavaBeans properties of the specified bean, based on
     * the specified name/value pairs.  This method uses Java reflection APIs
     * to identify corresponding "property setter" method names, and deals
     * with setter arguments of type <code>String</code>, <code>boolean</code>,
     * <code>int</code>, <code>long</code>, <code>float</code>, and
     * <code>double</code>.  In addition, array setters for these types (or the
     * corresponding primitive types) can also be identified.</p>
     * 
     * <p>The particular setter method to be called for each property is
     * determined using the usual JavaBeans introspection mechanisms.  Thus,
     * you may identify custom setter methods using a BeanInfo class that is
     * associated with the class of the bean itself.  If no such BeanInfo
     * class is available, the standard method name conversion ("set" plus
     * the capitalized name of the property in question) is used.</p>
     * 
     * <p><strong>NOTE</strong>:  It is contrary to the JavaBeans Specification
     * to have more than one setter method (with different argument
     * signatures) for the same property.</p>
     *
     * <p><strong>WARNING</strong> - The logic of this method is customized
     * for extracting String-based request parameters from an HTTP request.
     * It is probably not what you want for general property copying with
     * type conversion.  For that purpose, check out the
     * <code>copyProperties()</code> method instead.</p>
     *
     * @param bean JavaBean whose properties are being populated
     * @param properties Map keyed by property name, with the
     *  corresponding (String or String[]) value(s) to be set
     *
     * @exception IllegalAccessException if the caller does not have
     *  access to the property accessor method
     * @exception InvocationTargetException if the property accessor method
     *  throws an exception
     */
    protected void populate(Object bean, Map properties)
        throws IllegalAccessException, InvocationTargetException {

        // Do nothing unless both arguments have been specified
        if ((bean == null) || (properties == null)) {
            return;
        }
        if (log.isDebugEnabled()) {
            log.debug("BeanUtils.populate(" + bean + ", " +
                    properties + ")");
        }

        // Loop through the property name/value pairs to be set
        Iterator names = properties.keySet().iterator();
        while (names.hasNext()) {

            // Identify the property name and value(s) to be assigned
            String name = (String) names.next();
            if (name == null) {
                continue;
            }
            Object value = properties.get(name);

            // Perform the assignment for this property
            setProperty(bean, name, value);

        }

    }


    /**
     * <p>Set the specified property value, performing type conversions as
     * required to conform to the type of the destination property.</p>
     *
     * <p>If the property is read only then the method returns 
     * without throwing an exception.</p>
     *
     * <p>If <code>null</code> is passed into a property expecting a primitive value,
     * then this will be converted as if it were a <code>null</code> string.</p>
     *
     * <p><strong>WARNING</strong> - The logic of this method is customized
     * to meet the needs of <code>populate()</code>, and is probably not what
     * you want for general property copying with type conversion.  For that
     * purpose, check out the <code>copyProperty()</code> method instead.</p>
     *
     * <p><strong>WARNING</strong> - PLEASE do not modify the behavior of this
     * method without consulting with the Struts developer community.  There
     * are some subtleties to its functionality that are not documented in the
     * Javadoc description above, yet are vital to the way that Struts utilizes
     * this method.</p>
     *
     * @param bean Bean on which setting is to be performed
     * @param name Property name (can be nested/indexed/mapped/combo)
     * @param value Value to be set
     *
     * @exception IllegalAccessException if the caller does not have
     *  access to the property accessor method
     * @exception InvocationTargetException if the property accessor method
     *  throws an exception
     */
    protected void setProperty(Object bean, String name, Object value)
        throws IllegalAccessException, InvocationTargetException {

        // Trace logging (if enabled)
        if (log.isTraceEnabled()) {
            StringBuffer sb = new StringBuffer("  setProperty(");
            sb.append(bean);
            sb.append(", ");
            sb.append(name);
            sb.append(", ");
            if (value == null) {
                sb.append("<NULL>");
            } else if (value instanceof String) {
                sb.append((String) value);
            } else if (value instanceof String[]) {
                String values[] = (String[]) value;
                sb.append('[');
                for (int i = 0; i < values.length; i++) {
                    if (i > 0) {
                        sb.append(',');
                    }
                    sb.append(values[i]);
                }
                sb.append(']');
            } else {
                sb.append(value.toString());
            }
            sb.append(')');
            log.trace(sb.toString());
        }

        // Resolve any nested expression to get the actual target bean
        Object target = bean;
        int delim = findLastNestedIndex(name);
        if (delim >= 0) {
            try {
                target =
                    getPropertyUtils().getProperty(bean, name.substring(0, delim));
            } catch (NoSuchMethodException e) {
                return; // Skip this property setter
            }
            name = name.substring(delim + 1);
            if (log.isTraceEnabled()) {
                log.trace("    Target bean = " + target);
                log.trace("    Target name = " + name);
            }
        }

        // Declare local variables we will require
        String propName = null;          // Simple name of target property
        Class type = null;               // Java type of target property
        int index = -1;                  // Indexed subscript value (if any)
        String key = null;               // Mapped key value (if any)

        // Calculate the property name, index, and key values
        propName = name;
        int i = propName.indexOf(PropertyUtils.INDEXED_DELIM);
        if (i >= 0) {
            int k = propName.indexOf(PropertyUtils.INDEXED_DELIM2);
            try {
                index =
                    Integer.parseInt(propName.substring(i + 1, k));
            } catch (NumberFormatException e) {
                ;
            }
            propName = propName.substring(0, i);
        }
        int j = propName.indexOf(PropertyUtils.MAPPED_DELIM);
        if (j >= 0) {
            int k = propName.indexOf(PropertyUtils.MAPPED_DELIM2);
            try {
                key = propName.substring(j + 1, k);
            } catch (IndexOutOfBoundsException e) {
                ;
            }
            propName = propName.substring(0, j);
        }

        // Calculate the property type
        if (target instanceof DynaBean) {
            DynaClass dynaClass = ((DynaBean) target).getDynaClass();
            DynaProperty dynaProperty = dynaClass.getDynaProperty(propName);
            if (dynaProperty == null) {
                return; // Skip this property setter
            }
            type = dynaProperty.getType();
        } else {
            PropertyDescriptor descriptor = null;
            try {
                descriptor =
                    getPropertyUtils().getPropertyDescriptor(target, name);
                if (descriptor == null) {
                    return; // Skip this property setter
                }
            } catch (NoSuchMethodException e) {
                return; // Skip this property setter
            }
            if (descriptor instanceof MappedPropertyDescriptor) {
                if (((MappedPropertyDescriptor) descriptor).getMappedWriteMethod() == null) {
                    if (log.isDebugEnabled()) {
                        log.debug("Skipping read-only property");
                    }
                    return; // Read-only, skip this property setter
                }
                type = ((MappedPropertyDescriptor) descriptor).
                    getMappedPropertyType();
            } else if (descriptor instanceof IndexedPropertyDescriptor) {
                if (((IndexedPropertyDescriptor) descriptor).getIndexedWriteMethod() == null) {
                    if (log.isDebugEnabled()) {
                        log.debug("Skipping read-only property");
                    }
                    return; // Read-only, skip this property setter
                }
                type = ((IndexedPropertyDescriptor) descriptor).
                    getIndexedPropertyType();
            } else {
                if (descriptor.getWriteMethod() == null) {
                    if (log.isDebugEnabled()) {
                        log.debug("Skipping read-only property");
                    }
                    return; // Read-only, skip this property setter
                }
                type = descriptor.getPropertyType();
            }
        }

        // Convert the specified value to the required type
        Object newValue = null;
        if (type.isArray() && (index < 0)) { // Scalar value into array
            if (value == null) {
                String values[] = new String[1];
                values[0] = (String) value;
                newValue = getConvertUtils().convert((String[]) values, type);
            } else if (value instanceof String) {
                String values[] = new String[1];
                values[0] = (String) value;
                newValue = getConvertUtils().convert((String[]) values, type);
            } else if (value instanceof String[]) {
                newValue = getConvertUtils().convert((String[]) value, type);
            } else {
                newValue = value;
            }
        } else if (type.isArray()) {         // Indexed value into array
            if (value instanceof String) {
                newValue = getConvertUtils().convert((String) value,
                                                type.getComponentType());
            } else if (value instanceof String[]) {
                newValue = getConvertUtils().convert(((String[]) value)[0],
                                                type.getComponentType());
            } else {
                newValue = value;
            }
        } else {                             // Value into scalar
            if ((value instanceof String) || (value == null)) {
                newValue = getConvertUtils().convert((String) value, type);
            } else if (value instanceof String[]) {
                newValue = getConvertUtils().convert(((String[]) value)[0],
                                                type);
            } else if (getConvertUtils().lookup(value.getClass()) != null) {
                newValue = getConvertUtils().convert(value.toString(), type);
            } else {
                newValue = value;
            }
        }

        // Invoke the setter method
        try {
            if (index >= 0) {
                getPropertyUtils().setIndexedProperty(target, propName,
                                                 index, newValue);
            } else if (key != null) {
                getPropertyUtils().setMappedProperty(target, propName,
                                                key, newValue);
            } else {
                getPropertyUtils().setProperty(target, propName, newValue);
            }
        } catch (NoSuchMethodException e) {
            throw new InvocationTargetException
                (e, "Cannot set " + propName);
        }

    }
    
    private int findLastNestedIndex(String expression)
    {
        // walk back from the end to the start 
        // and find the first index that 
        int bracketCount = 0;
        for (int i = expression.length() - 1; i>=0 ; i--) {
            char at = expression.charAt(i);
            switch (at) {
                case PropertyUtils.NESTED_DELIM:
                    if (bracketCount < 1) {
                        return i;
                    }
                    break;
                    
                case PropertyUtils.MAPPED_DELIM:
                case PropertyUtils.INDEXED_DELIM:
                    // not bothered which
                    --bracketCount;
                    break;
                
                case PropertyUtils.MAPPED_DELIM2:
                case PropertyUtils.INDEXED_DELIM2:
                    // not bothered which
                    ++bracketCount;
                    break;            
            }
        }
        // can't find any
        return -1;
    }
    
    /** 
     * Gets the <code>ConvertUtilsBean</code> instance used to perform the conversions.
     */
    protected ConvertUtilsBean getConvertUtils() {
        return convertUtilsBean;
    }
    
    /**
     * Gets the <code>JIMPropertyUtilsBean</code> instance used to access properties.
     */
    protected JIMPropertyUtilsBean getPropertyUtils() {
        return JIMPropertyUtilsBean;
    }

    private boolean isFromFormBeanCopy() {
		return fromFormBeanCopy;
	}

	private void setFromFormBeanCopy(boolean fromFormBeanCopy) {
		this.fromFormBeanCopy = fromFormBeanCopy;
		firstTime=true;
	}

	protected void copyPropertiesFromFormBean(Object dest, ActionForm orig) throws IllegalAccessException, InvocationTargetException {
		setFromFormBeanCopy(true);
		copyProperties(dest, orig);
		setFromFormBeanCopy(false);
	}
	
	protected void copyPropertiesToFormBean(ActionForm dest, Object orig) throws IllegalAccessException, InvocationTargetException {
		setFromPojoCopy(true);
		copyProperties(dest, orig);
		setFromPojoCopy(false);
	}
	
	
	protected String getPojoName(Persistent persistent) {
		String complete = persistent.getClass().getCanonicalName();
		
		int lastIndex = complete.indexOf('$');
		if(lastIndex!=-1){
			complete=complete.substring(0, lastIndex);
		}
			
		int index = complete.lastIndexOf(".");
		if(index!=-1)
			complete = complete.substring(index + 1);

		return complete;
	}
	
	public PropertyDescriptor getFormBeanCodePropertyDescriptor(Object formBean, Persistent persistent) {
		PropertyDescriptor origDescriptors[] = getPropertyUtils()
				.getPropertyDescriptors(formBean);

		for (int i = 0; i < origDescriptors.length; ++i) {
			String name = origDescriptors[i].getName();
			if (name.equalsIgnoreCase(getPojoName(persistent) + "code")) {
				return origDescriptors[i];
			}
		}
		return null;
	}
	
	protected Object getFormBeanCodePropertyValue(Object formBean, Persistent persistent) throws IllegalAccessException, InvocationTargetException {
		return getFormBeanCodePropertyDescriptor(formBean, persistent).getReadMethod().invoke(formBean, new Object[0]);
	}

	protected boolean isFromPojoCopy() {
		return fromPojoCopy;
	}

	protected void setFromPojoCopy(boolean fromPojoCopy) {
		this.fromPojoCopy = fromPojoCopy;
	}
}
