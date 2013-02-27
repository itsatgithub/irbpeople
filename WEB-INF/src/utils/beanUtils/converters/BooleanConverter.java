package utils.beanUtils.converters;

import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.beanutils.Converter;

/**
 * This class is a converter to Boolean
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class BooleanConverter implements Converter{

	public static Locale locale = new Locale("es");
	
	public BooleanConverter(Locale l){
		locale = l;
	}
	
	public Object convert(Class arg0, Object value) {
		if(value instanceof Boolean){
			return value;
		}
		
		//Este método creo que no está bien ya que exige que value=<trueEtiq> que depende del locale
		//    lleva a error a la hora de hacer las jsps
		//Es mejor el BooleanConverter de org.apache.commons.beanutils.converters que
		// define como true: y, yes, true, on, 1 (en mayúsculas o minúsculas)
		// y false: n, no, false, off, 0
		// el problema con este otro converter es que, por defecto, lanza una excepción si value
		// tiene cualquier valor diferente de los indicados.
		
		ResourceBundle patternsBundle = ResourceBundle.getBundle("Patterns",locale);
		
		String trueEtiq = patternsBundle.getString("trueEtiq");
		String falseEtiq = patternsBundle.getString("falseEtiq");
		
		 if(value == null || value.equals("")) return Boolean.FALSE;
		 
		 if(value.equals(trueEtiq)) return Boolean.TRUE;
		 
		 else /*value.equals(falseEtiq)*/ return Boolean.FALSE;
		 
		 
	}

}
