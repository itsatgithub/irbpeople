/*
 * $Header$
 * $Revision$
 * $Date$
 *
 * ====================================================================
 *
 * Copyright (c) 2000 - 2005 SCC Informationssysteme GmbH. All rights
 * reserved.
 * Vendor URL : http://www.scc-gmbh.com
 * Product URL: http://www.common-controls.com
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL SCC INFORMATIONSSYSTEME GMBH OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 */

package com.cc.framework.convert;

import java.lang.reflect.Array;

import com.cc.framework.adapter.RequestContext;

/**
 * Helper class for Datatype conversions
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision$
 */
public class ConverterHelp {

	/**
	 * Retrieves a Converter instance for the given Converter
	 * class name
	 *  
	 * @param		converterId Converter class name
	 * 				(full qualified Java class name)
	 * @return		Converter instance
	 * @throws		ConverterException Is thrown when the instance
	 * 				could not be created
	 */
	public static Converter getInstance(String converterId) throws ConverterException {
		return ConverterRegistry.instance().getCustomConverter(converterId);
	}
	
	/**
	 * Uses a Bean Converter to convert the given object
	 * to a String representation.
	 *
	 * @param		ctx Request context
	 * @param		converter The Converter to use
	 * @param		value The Bean to convert
	 * @return		String or <code>null</code>
	 * @throws		ConverterException Is thrown when the instance
	 * 				could not be created
	 */
	public static String getAsString(RequestContext ctx, Converter converter, Object value) 
		throws ConverterException {

		if (ctx == null) {
			throw new NullPointerException();
		}

		if ((converter == null) && (value != null)) {
			converter = ConverterRegistry.instance().lookup(value.getClass());
		}
		
		if (converter == null) {
			if (value == null) {
				return "";
			} else {
				return value.toString();
			}
		} else {
			return converter.getAsString(ctx, value);
		}
	}

	/**
	 * Uses a Bean Converter to convert the given String into
	 * an Object.
	 *
	 * @param		ctx Request context
	 * @param		dataType the target datatype
	 * @param		newValue The String to convert
	 * @return		Object of type dataType or <code>null</code>
	 * @throws		ConverterException Is thrown when the instance
	 * 				could not be created
	 */
	public static Object getAsObject(RequestContext ctx, Class dataType, String newValue) 
		throws ConverterException {

		if ((ctx == null) || (dataType == null)) {
			throw new NullPointerException();
		}

		if (newValue == null) {
			return null;
		}
		
		Converter converter = ConverterRegistry.instance().lookup(dataType);

		if (converter != null) {
			return converter.getAsObject(ctx, newValue);
		} else {
			throw new ConverterException("no converter found for type " + dataType);
		}
	}
	
	/**
	 * Uses a Bean Converter to convert the given String Array into
	 * an Object Array.
	 *
	 * @param		ctx Request context
	 * @param		dataType the target datatype
	 * @param		newValues The Strings to convert
	 * @return		Object Array of type dataType or <code>null</code>
	 * @throws		ConverterException Is thrown when the instance
	 * 				could not be created
	 */
	public static Object getAsObject(RequestContext ctx, Class dataType, String[] newValues) 
		throws ConverterException {
	
		if ((ctx == null) || (dataType == null)) {
			throw new NullPointerException();
		}

		if ((newValues == null) || (newValues.length == 0)) {
			return null;
		}

        if (dataType.isArray()) {
        	Class componentType = dataType.getComponentType();

        	Converter converter = ConverterRegistry.instance().lookup(componentType);
    		
    		if (converter != null) {
   				Object array = Array.newInstance(componentType, newValues.length);
   				for (int i = 0; i < newValues.length; i++) {
   					Array.set(array, i, converter.getAsObject(ctx, newValues[i]));
   				}
   				return array;
    		} else {
    			throw new ConverterException("no converter found for type " + componentType);
    		}
        } else {
        	return getAsObject(ctx, dataType, newValues[0]);
        }
	}
}
