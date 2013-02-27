/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/ELSupport.java,v 1.6 2005/02/25 12:12:45 P001002 Exp $
 * $Revision: 1.6 $
 * $Date: 2005/02/25 12:12:45 $
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

package com.cc.framework.ui;

import java.lang.reflect.Method;

import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.el.ELException;
import javax.servlet.jsp.el.FunctionMapper;

import org.apache.commons.el.Expression;
import org.apache.commons.el.ExpressionString;
import org.apache.commons.el.Logger;
import org.apache.commons.el.VariableResolverImpl;
import org.apache.commons.el.parser.ELParser;
import org.apache.commons.el.parser.ParseException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cc.framework.ui.model.imp.ClientHandlerImp;
import com.cc.framework.util.StringInputStream;

/**
 * Common Controls Expression Language (EL) Support class
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.6 $
 */
public abstract class ELSupport {

	/**
	 * Logging instance
	 */
	private static Log log = LogFactory.getLog(ELSupport.class);

	// ==========================
	// Inner classes
	// ==========================

	/**
	 * Variable Resolver
	 */
	private static class BeanVariableResolver extends VariableResolverImpl {

		/** The Bean */
		private Object bean;

		/**
		 * The identifier for the bean
		 * (<code>null</code> defaults to <b>"bean"</b>)
		 */
		private String beanName = null; 

		/**
		 * Constructor
		 * 
		 * @param	ctx JSP Page Context
		 * @param	bean The Bean
		 * @param		beanName The identifier under which the bean
		 * 				is made visible (default is "bean")
		 */
		public BeanVariableResolver(PageContext ctx, Object bean, String beanName) {
			super(ctx);

			this.bean		= bean;
			this.beanName	= beanName;
		}

		/**
		 * Resolves the specified variable within the given context.
		 * Returns null if the variable is not found.
		 * 
		 * @param		name The variable Name
		 * @return		the value
		 * @throws		ELException is thrown when the variable
		 * 				can not be resolved
		 */
		public Object resolveVariable(String name) throws ELException {

			// Check for implicit objects
			if ((beanName != null) && beanName.equals(name)) {
				return bean;
			} else if ((beanName == null) && "bean".equals(name)) {
				return bean;
			} else {
				return super.resolveVariable(name);
			}
		}
	}

	/**
	 * Function Mapper
	 */
	private static class ELFunctionMapper implements FunctionMapper {
		
		/**
		 * @see javax.servlet.jsp.el.FunctionMapper#resolveFunction(java.lang.String, java.lang.String)
		 */
		public Method resolveFunction(String arg0, String arg1) {
			
			// We do not have any functions at this time!
			return null;
		}
	}

	/**
	 * Logger
	 */
	private static class ELLogger extends Logger {

		/**
		 * Logging instance
		 */
		private Log log = null;

		/**
		 * Constructor
		 * 
		 * @param		log The Log
		 */
		public ELLogger(Log log) {
			super(null);

			this.log = log;
		}

		//-------------------------------------

		/**
		 * @return		returns <code>true</code> if the application should
		 * 				even bother to try logging a warning.
		 */
		public boolean isLoggingWarning() {
			return log.isWarnEnabled();
		}

		/**
		 * Logs a warning
		 * 
		 * @param		pMessage Message
		 * @param		pRootCause Root cause
		 * @throws		ELException Error
		 **/
		public void logWarning(String pMessage, Throwable pRootCause)
			throws ELException {

			if (isLoggingWarning()) {
				if (pMessage == null) {
					log.warn(pRootCause);
				} else if (pRootCause == null) {
					log.warn(pMessage);
				} else {
					log.warn(pMessage + ": " + pRootCause);
				}
			}
		}

		/**
		 * Logs a warning
		 * 
		 * @param		pMessage Message
		 * @param		pRootCause Root cause
		 * @throws		ELException Error
		 **/
		public void logError(String pMessage, Throwable pRootCause)
			throws ELException {

			if (isLoggingError()) {
				log.error(pMessage, pRootCause);
			}

			super.logError(pMessage, pRootCause);
		}

		/**
		 * @return		returns <code>true</code> if the application should
		 * 				even bother to try logging an error.
		 **/
		public boolean isLoggingError() {
			return log.isErrorEnabled();
		}
	}

	/**
	 * Constructor
	 */
	private ELSupport() {
		super();
	}
	
	/**
	 * Evaluates the given <b>expression</b> or <b>expression string</b>.
	 * The bean is made accessible under the identifier <b>beanName</b>
	 * 
	 * @param		ctx Jsp Page Context
	 * @param		expression The Expression
	 * @return		Returns the result
	 * @throws		ELException is thrown in case of an expression
	 * 				error 
	 * @throws		ParseException is thrown in case of a syntax
	 * 				error
	 */
	public static String evaluateToString(
		PageContext ctx,
		String expression)
		throws ELException, ParseException {

		Object obj = evaluate(ctx, expression, null, null);

		return (obj == null) ? null : obj.toString();
	}

	/**
	 * Evaluates the given <b>expression</b> or <b>expression string</b>.
	 * The bean is made accessible under the identifier <b>beanName</b>
	 * 
	 * @param		ctx Jsp Page Context
	 * @param		expression The Expression
	 * @return		Returns the result
	 * @throws		ELException is thrown in case of an expression
	 * 				error 
	 * @throws		ParseException is thrown in case of a syntax
	 * 				error
	 */
	public static Object evaluate(
		PageContext ctx,
		String expression)
		throws ELException, ParseException {

		return evaluate(ctx, expression, null, null);
	}

	/**
	 * Evaluates the given <b>expression</b> or <b>expression string</b>.
	 * The bean is made accessible under the identifier <b>beanName</b>
	 * 
	 * @param		ctx Jsp Page Context
	 * @param		expression The Expression
	 * @param		bean The Bean
	 * @param		beanName The identifier under which the bean
	 * 				is made visible (default is "bean")
	 * @return		Returns the result
	 * @throws		ELException is thrown in case of an expression
	 * 				error 
	 * @throws		ParseException is thrown in case of a syntax
	 * 				error
	 */
	public static Object evaluate(
		PageContext ctx,
		String expression,
		Object bean,
		String beanName)
		throws ELException, ParseException {

		try {
			Object result = null;

			ELParser parser = new ELParser(new StringInputStream(expression));
			Object exp = parser.ExpressionString();

			if (exp instanceof Expression) {
				result =
					((Expression) exp).evaluate(
						new BeanVariableResolver(ctx, bean, beanName),
						new ELFunctionMapper(),
						new ELLogger(log));
			} else if (exp instanceof ExpressionString) {
				result =
					((ExpressionString) exp).evaluate(
						new BeanVariableResolver(ctx, bean, beanName),
						new ELFunctionMapper(),
						new ELLogger(log));
			} else {
				result = exp;
			}

			return result;
		} catch (ParseException pe) {
			LogFactory.getLog(ClientHandlerImp.class).error(pe);

			throw pe;
		} catch (ELException ele) {
			LogFactory.getLog(ClientHandlerImp.class).error(ele);

			throw ele;
		}
	}
}
