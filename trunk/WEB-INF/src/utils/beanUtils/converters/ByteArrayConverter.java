package utils.beanUtils.converters;

import org.apache.commons.beanutils.Converter;

/**
 * This class is a converter to ByteArray
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class ByteArrayConverter implements Converter{
		
	public ByteArrayConverter(){
	}
	
	public Object convert(Class type, Object value) {
		if(String.class.isInstance(value)){
			return ((String)value).getBytes();
		}
		//TODO
		else return null;
	}

}
