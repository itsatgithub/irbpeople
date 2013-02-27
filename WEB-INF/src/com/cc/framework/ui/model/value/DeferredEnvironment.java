/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/value/DeferredEnvironment.java,v 1.5 2005/05/27 15:50:48 P001002 Exp $
 * $Revision: 1.5 $
 * $Date: 2005/05/27 15:50:48 $
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

import java.lang.reflect.Method;

import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.el.ELException;
import javax.servlet.jsp.el.FunctionMapper;
import javax.servlet.jsp.el.VariableResolver;

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
 * Environemnt for executing expressions
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.5 $
 */
public class DeferredEnvironment {

	/**
	 * The name that can be used in deferred expressions to
	 * access the (row-) bean 
	 */
	public static final String BEAN_NAME = "bean";

	/**
	 * Logging instance
	 */
	private static Log log = LogFactory.getLog(DeferredEnvironment.class);

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
		 * Constructor
		 * 
		 * @param	ctx JSP Page Context
		 * @param	bean The Bean
		 */
		public BeanVariableResolver(PageContext ctx, Object bean) {
			super(ctx);

			this.bean = bean;
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
			if (BEAN_NAME.equals(name)) {
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
	 * The Page Context of the current JSP page
	 */
	private PageContext pageContext = null;

	/**
	 * The variable resolver that gives access to the (row-)bean
	 */
	private VariableResolver variableResolver = null;

	/**
	 * Function mapper for custom functions
	 */
	private FunctionMapper functionMapper = new ELFunctionMapper();

	/**
	 * Logger
	 */
	private Logger logger = new ELLogger(log);

	/**
	 * Construktor
	 * 
	 * @param		pageContext The Page Context of the current JSP page
	 */
	public DeferredEnvironment(PageContext pageContext) {
		super();

		this.pageContext	= pageContext;
	}

	/**
	 * Construktor
	 * 
	 * @param		pageContext The Page Context of the current JSP page
	 * @param		bean The (row-) bean
	 */
	public DeferredEnvironment(PageContext pageContext, Object bean) {
		this(pageContext);

		setBean(bean);
	}

	/**
	 * Sets the (row-) bean
	 * 
	 * @param		bean the (row-) bean
	 */
	public void setBean(Object bean) {
		variableResolver = new BeanVariableResolver(pageContext, bean);
	}	

	/**
	 * Evaluates the given <b>expression</b> or <b>expression string</b>
	 * (multiple expressions in one string). The bean is made accessible
	 * under the identifier <b>BEAN_NAME</b>
	 * 
	 * @param		expression The Expression
	 * @return		Returns the result
	 */
	protected Object evaluate(String expression) {

		try {
			Object result = null;

			ELParser parser	= new ELParser(new StringInputStream(expression));
			Object exp		= parser.ExpressionString();

			if (exp instanceof Expression) {
				result = ((Expression) exp).evaluate(variableResolver, functionMapper, logger);
			} else if (exp instanceof ExpressionString) {
				result = ((ExpressionString) exp).evaluate(variableResolver, functionMapper, logger);
			} else {
				result = exp;
			}

			return result;
		} catch (ParseException pe) {
			LogFactory.getLog(ClientHandlerImp.class).error(pe);

			throw new DeferredException(pe.getMessage());
		} catch (ELException ele) {
			LogFactory.getLog(ClientHandlerImp.class).error(ele);

			throw new DeferredException(ele.getMessage());
		}
	}
}