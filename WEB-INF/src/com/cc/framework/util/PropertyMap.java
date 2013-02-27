/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/util/PropertyMap.java,v 1.6 2005/07/08 15:15:34 P001002 Exp $
 * $Revision: 1.6 $
 * $Date: 2005/07/08 15:15:34 $
 *
 * ====================================================================
 *
 * Copyright (c) 2002 SCC Informationssysteme GmbH. All rights
 * reserved.
 *
 * ====================================================================
 */

package com.cc.framework.util;

import java.util.HashMap;
import java.util.Iterator;

/**
 * This class is responsible for converting property maps
 * to and from String
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.6 $
 */
public class PropertyMap {

	/**
	 * Properties collection. The value objects
	 * are of type String or Array of String
	 */
	private HashMap properties = new HashMap();

	/**
	 * Adds a key value pair to the property map
	 * 
	 * @param		key Property Key
	 * @param		value Value
	 */
	private void addInternal(String key, Object value) {
		if (properties.containsKey(key)) {
			throw new IllegalArgumentException("duplicate key");
		} else {
			properties.put(key, value);
		}
	}

	/**
	 * Adds a property of type String to the map
	 * 
	 * @param		key Property Key
	 * @param		value Value
	 */
	public void addProperty(String key, String value) {
		addInternal(key, value);
	}

	/**
	 * Adds an Array of Strings to the map
	 * 
	 * @param		key Property Key
	 * @param		values Value Array
	 */
	public void addProperty(String key, String[] values) {
		addInternal(key, values);
	}

	/**
	 * Checks if the map contains the given key
	 * 
	 * @param		key Property Key
	 * @return		<code>true</code> if the key is a
	 * 				member pf the property map
	 */
	public boolean hasProperty(String key) {
		return properties.containsKey(key);
	}

	/**
	 * Retrieves the property for the given key
	 * 
	 * @param		key Property Key
	 * @return		Value or <code>null</code>
	 */
	public String getProperty(String key) {
		Object prop = properties.get(key);

		if (prop == null) {
			return null;
		} else if (prop.getClass().isArray()) {
			return (String) ((Object[]) prop)[0];
		} else {
			return (String) prop;
		}  
	}

	/**
	 * Retrieves the property for the given key
	 * in array form
	 * 
	 * @param		key Property Key
	 * @return		Array of values or <code>null</code>
	 */
	public String[] getProperties(String key) {
		Object prop = properties.get(key);

		if (prop == null) {
			return null;
		} else if (prop.getClass().isArray()) {
			return (String[]) prop;
		} else {
			return new String[] {(String) prop};
		}  
	}

	/**
	 * translate all escaped characters into the raw characters
	 * 
	 * @param		encodedStr the encoded string
	 * @return		raw string
	 */
	private static String decode(String encodedStr) {
// TODO:
		return encodedStr;
	}

	/**
	 * replace all occurences of '=', ',' and ';' with escaped
	 * characters
	 * 
	 * @param		rawStr the raw string to encode
	 * @return		encoded string
	 */
	private static String encode(String rawStr) {
// TODO: replace all occurences of '=', ',' and ';' with escaped characters
		return rawStr;
	}

	/**
	 * Converts a String into a PropertyMap Object
	 * 
	 * @param		str The String to convert
	 * @return		PropertyMap or <code>null</code>
	 */
	public static PropertyMap parse(String str) {
		if (str == null) {
			return null;
		}

		PropertyMap map = new PropertyMap();

		String[] props = StringHelp.split(str, ";");
		for (int i = 0; i < props.length; i++) {
			String[] tokens = StringHelp.split(props[i], "=");

			if (tokens.length >= 1) {
				String key = decode(tokens[0]);

				if (tokens.length == 1) {
					map.addInternal(key, null);
				} else {
					String[] values	= StringHelp.split(tokens[1], ",");
	
					if ((values != null) && (values.length > 0)) {
						for (int j = 0; j < values.length; j++) {
							values[j] = decode(values[j]);
						}
	
						if (values.length == 1) {
							map.addInternal(key, values[0]);
						} else {
							map.addInternal(key, values);
						}
					}
				}
			} else {
				throw new IllegalArgumentException("Illegal property map format: " + props[i]);
			}
		}

		return map;
	}

	/**
	 * Converts the PropertyMap into a String
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer buf = new StringBuffer();

		boolean first = true;

		Iterator i = properties.keySet().iterator();
		while (i.hasNext()) {
			if (!first) {
				buf.append(";");
			}

			String key		= (String) i.next();
			Object value	= properties.get(key);
			
			buf
				.append(encode(key))
				.append("=");

			if (value != null) {
				if (value.getClass().isArray()) {
					String[] values = (String[]) value;
					for (int j = 0; j < values.length; j++) {
						if (j > 0) {
							buf.append(",");
						}
	
						buf.append(encode(values[j]));
					}
				} else {
					buf.append(encode((String) value));
				}
			}

			first = false;
		}
		
		return buf.toString();
	}
}
