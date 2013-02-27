/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/value/DeferredValue.java,v 1.5 2005/07/08 14:19:21 P001002 Exp $
 * $Revision: 1.5 $
 * $Date: 2005/07/08 14:19:21 $
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

package com.cc.framework.ui.model.value;

import java.io.Serializable;

import com.cc.framework.security.Permission;
import com.cc.framework.security.PermissionException;
import com.cc.framework.ui.AlignmentType;
import com.cc.framework.ui.model.ImageModel;
import com.cc.framework.ui.model.imp.ImageModelImp;

/**
 * This is the value wrapper class for deferred EL expressions
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.5 $
 */
public class DeferredValue implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1238883032739096137L;

	/** Boolean literal TRUE */
	public static final DeferredValue FALSE = new DeferredValue(false);

	/** Boolean literal FALSE */
	public static final DeferredValue TRUE = new DeferredValue(true);

	/** Integer literal for -1 */
	public static final DeferredValue NEG = new DeferredValue(-1);

	/**
	 * This is the indicator the framework uses to distinguish
	 * a defered EL expression
	 */
	private static final String EL_DEFERRED_INDICATOR = "@{";

	/**
	 * The standard EL Markup
	 */
	private static final String EL_INDICATOR = "${";

	/**
	 * Literal value (-> <code>expression</code> is <code>null</code>)
	 */
	private Object literal = null;

	/**
	 * Expression (-> <code>literal</code> is <code>null</code>)
	 */
	private String expression = null;

	// ------------------------------
	// Static Methods 
	// ------------------------------

	/**
	 * This method converts an expression in the frameworks deferred syntax
	 * (with "@{") to a valid EL expression that can be executed by an
	 * EL expression Parser.
	 * 
	 * @param		deferredExpression The expression in deferred syntax
	 * @return		the expression in valid EL syntax
	 */
	private static String convertEL(String deferredExpression) {
		byte[] b = deferredExpression.getBytes();
			
		int i = 0;
		while (i < b.length) {
			if ((b[i] == '@') && ((i + 1) < b.length) && (b[i + 1] == '{')) {
				b[i] = '$';
				++i;
			}
				
			++i;
		}

		return new String(b);
	}

	/**
	 * Checks if the given string is a deferred expression.
	 * This is the case when the character sequence "@{" or
	 * "${" is part of the string 
	 * 
	 * @param		value string to test
	 * @return		returns <code>true</code> if the string is
	 * 				an expression
	 */
	private static boolean isExpression(String value) {
		return (value != null)
			&& ((value.indexOf(EL_DEFERRED_INDICATOR) != -1)
				|| (value.indexOf(EL_INDICATOR) != -1));
	}

	/**
	 * Pares a boolean expression
	 * 
	 * @param		value string to parse
	 * @return		Deferred value
	 */
	public static DeferredValue parseBoolean(String value) {
		if (isExpression(value)) {
			return new DeferredValue(value);
		} else {
			return new DeferredValue(Boolean.valueOf(value));
		}
	}

	/**
	 * Pares a integer expression
	 * 
	 * @param		value string to parse
	 * @return		Deferred value
	 */
	public static DeferredValue parseInt(String value) {
		if (isExpression(value)) {
			return new DeferredValue(value);
		} else {
			return new DeferredValue(Integer.parseInt(value));
		}
	}

	/**
	 * Pares a Permission expression
	 * 
	 * @param		value string to parse
	 * @return		Deferred value
	 */
	public static DeferredValue parsePermission(String value) {
		if (isExpression(value)) {
			return new DeferredValue(value);
		} else {
			try {
				return new DeferredValue(Permission.parse(value));
			} catch (PermissionException pe) {
					throw new DeferredException(pe.getMessage());
			} 
		}
	}

	/**
	 * Pares a Image expression
	 * 
	 * @param		value string to parse
	 * @return		Deferred value
	 */
	public static DeferredValue parseImage(String value) {
		if (isExpression(value)) {
			return new DeferredValue(value);
		} else {
			return new DeferredValue(new ImageModelImp(value));
		}
	}

	/**
	 * Pares a Alignment expression
	 * 
	 * @param		value string to parse
	 * @return		Deferred value
	 */
	public static DeferredValue parseAlignment(String value) {
		if (isExpression(value)) {
			return new DeferredValue(value);
		} else {
			return new DeferredValue(AlignmentType.parse(value));
		}
	}

	/**
	 * Converts the given deferred value to a Permission object
	 * 
	 * @param		value The deferred value to convert
	 * 				or <code>null</code>
	 * @param		env The expression environment that gives
	 * 				access to the EL Parser an the (row-)bean
	 * @return		Evaluated expression or <code>null</code>
	 */
	public static Permission toPermission(DeferredValue value, DeferredEnvironment env) {
		if (value == null) {
			return null;
		} else {
			return value.toPermission(env);
		}
	}

	/**
	 * Converts the given deferred value to a ImageModel object
	 * 
	 * @param		value The deferred value to convert
	 * 				or <code>null</code>
	 * @param		env The expression environment that gives
	 * 				access to the EL Parser an the (row-)bean
	 * @return		Evaluated expression or <code>null</code>
	 */
	public static ImageModel toImage(DeferredValue value, DeferredEnvironment env) {
		if (value == null) {
			return null;
		} else {
			return value.toImage(env);
		}
	}

	/**
	 * Converts the given deferred value to a AlignmentType object
	 * 
	 * @param		value The deferred value to convert
	 * 				or <code>null</code>
	 * @param		env The expression environment that gives
	 * 				access to the EL Parser an the (row-)bean
	 * @return		Evaluated expression or <code>null</code>
	 */
	public static AlignmentType toAlignment(DeferredValue value, DeferredEnvironment env) {
		if (value == null) {
			return null;
		} else {
			return value.toAlignment(env);
		}
	}

	/**
	 * Converts the given deferred value to a String object
	 * 
	 * @param		value The deferred value to convert
	 * 				or <code>null</code>
	 * @param		env The expression environment that gives
	 * 				access to the EL Parser an the (row-)bean
	 * @return		Evaluated expression or <code>null</code>
	 */
	public static String toString(DeferredValue value, DeferredEnvironment env) {
		if (value == null) {
			return null;
		} else {
			return value.toString(env);
		}
	}

	/**
	 * Evaluates the given String value to a String object
	 * 
	 * @param		value The Expression sourcecode
	 * 				or <code>null</code>
	 * @param		env The expression environment that gives
	 * 				access to the EL Parser an the (row-)bean
	 * @return		Evaluated expression or <code>null</code>
	 */
	public static String evaluateToString(String value, DeferredEnvironment env) {
		if (value == null) {
			return null;
		}

		Object result = evaluate(value, env);
				
		if (result == null) {
			return null;
		} else {
			return result.toString();
		}
	}

	/**
	 * Evaluates the given String value to an object
	 * 
	 * @param		value The Expression sourcecode
	 * 				or <code>null</code>
	 * @param		env The expression environment that gives
	 * 				access to the EL Parser an the (row-)bean
	 * @return		Evaluated expression or <code>null</code>
	 */
	public static Object evaluate(String value, DeferredEnvironment env) {
		if (value == null) {
			return null;
		} else if (isExpression(value)) {
			if (env == null) {
				return value;
			} else {
				return env.evaluate(convertEL(value));
			}
		} else {
			return value;
		}
	}

	// ------------------------------
	// Methods 
	// ------------------------------

	/**
	 * Construktor
	 */
	public DeferredValue() {
	}

	/**
	 * Construktor for an literal <code>boolean</code> value
	 * 
	 * @param		value literal <code>boolean</code> value
	 */
	public DeferredValue(boolean value) {
		literal = new Boolean(value);
	}

	/**
	 * Construktor for an literal <code>int</code> value
	 * 
	 * @param		value literal <code>int</code> value
	 */
	public DeferredValue(int value) {
		literal = new Integer(value);
	}

	/**
	 * Construktor for an literal <code>String</code> value
	 * 
	 * @param		value literal <code>String</code> value
	 */
	public DeferredValue(Object value) {
		if ((value instanceof String) && isExpression((String) value)) {
			// The value is a deferred EL expression
			expression = convertEL((String) value);
		} else {
			literal = value;
		}
	}

	/**
	 * Converts the deferred value to a String object
	 * 
	 * @param		env The expression environment that gives
	 * 				access to the EL Parser an the (row-)bean
	 * @return		Evaluated expression or <code>null</code>
	 */
	public String toString(DeferredEnvironment env) {
		Object v = evaluate(env);

		if (v == null) {
			return null;
		} else {
			return v.toString();
		} 
	}

	/**
	 * Converts the deferred value to a ImageModel object
	 * 
	 * @param		env The expression environment that gives
	 * 				access to the EL Parser an the (row-)bean
	 * @return		Evaluated expression or <code>null</code>
	 */
	public ImageModel toImage(DeferredEnvironment env) {
		Object v = evaluate(env);

		if (v == null) {
			return null;
		} else if (v instanceof ImageModel) {
			return (ImageModel) v;
		} else if (v instanceof String) {
			return new ImageModelImp((String) v);
		} else {
			StringBuffer buf = new StringBuffer()
				.append("could not cast from [")
				.append(v.getClass().getName())
				.append("] to ImageModel!");

			throw new DeferredException(buf.toString());
		}
	}

	/**
	 * Converts the deferred value to a AlignmentType object
	 * 
	 * @param		env The expression environment that gives
	 * 				access to the EL Parser an the (row-)bean
	 * @return		Evaluated expression or <code>null</code>
	 */
	public AlignmentType toAlignment(DeferredEnvironment env) {
		Object v = evaluate(env);

		if (v == null) {
			return AlignmentType.LEFT;
		} else if (v instanceof AlignmentType) {
			return (AlignmentType) v;
		} else if (v instanceof String) {
			return AlignmentType.parse((String) v);
		} else {
			StringBuffer buf = new StringBuffer()
				.append("could not cast from [")
				.append(v.getClass().getName())
				.append("] to AlignmentType!");

			throw new DeferredException(buf.toString());
		}
	}

	/**
	 * Converts the deferred value to a Permission object
	 * 
	 * @param		env The expression environment that gives
	 * 				access to the EL Parser an the (row-)bean
	 * @return		Evaluated expression or <code>null</code>
	 */
	public Permission toPermission(DeferredEnvironment env) {
		Object v = evaluate(env);

		if (v == null) {
			return null;
		} else if (v instanceof Permission) {
			return (Permission) v;
		} else {
			StringBuffer buf = new StringBuffer()
				.append("could not cast from [")
				.append(v.getClass().getName())
				.append("] to Permission!");

			throw new DeferredException(buf.toString());
		}
	}

	/**
	 * Converts the deferred value to a boolean value
	 * 
	 * @param		env The expression environment that gives
	 * 				access to the EL Parser an the (row-)bean
	 * @return		Evaluated expression
	 */
	public boolean toBoolean(DeferredEnvironment env) {
		Object v = evaluate(env);

		if (v instanceof Boolean) {
			return ((Boolean) v).booleanValue();
		} else {
			StringBuffer buf = new StringBuffer()
				.append("could not cast from [")
				.append((literal == null) ? "null" : literal.getClass().getName())
				.append("] to boolean!");

			throw new DeferredException(buf.toString());
		}
	}

	/**
	 * Converts the deferred value to a integer value
	 * 
	 * @param		env The expression environment that gives
	 * 				access to the EL Parser an the (row-)bean
	 * @return		Evaluated expression
	 */
	public int toInt(DeferredEnvironment env) {
		Object v = evaluate(env);

		if (v instanceof Number) {
			return ((Number) v).intValue();
		} else {
			StringBuffer buf = new StringBuffer()
				.append("could not cast from [")
				.append((literal == null) ? "null" : literal.getClass().getName())
				.append("] to int!");

			throw new DeferredException(buf.toString());
		}
	}

	/**
	 * Method to evaluate a EL expression
	 * 
	 * @param		env The expression environment that gives
	 * 				access to the EL Parser an the (row-)bean
	 * @return		The Result of the EL evaluation
	 */
	private Object evaluate(DeferredEnvironment env) {
		if (literal != null) {
			return literal;
		} else if (expression != null) {
			if (env == null) {
				return null;
			} else {
				return env.evaluate(expression);
			}
		} else {
			return null;
		}
	}
}
