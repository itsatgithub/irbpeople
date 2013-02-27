/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/DesignRule.java,v 1.4 2005/02/16 18:13:30 P001001 Exp $
 * $Revision: 1.4 $
 * $Date: 2005/02/16 18:13:30 $
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

package com.cc.framework.ui.model;

import javax.servlet.jsp.PageContext;

import org.apache.ecs.ConcreteElement;

import com.cc.framework.ui.model.value.DeferredEnvironment;

/**
 * A design rule is used to modify the properties
 * of a design model at runtime based on a rule
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.4 $
 */
public interface DesignRule extends ClientHandler {

	/**
	 * Matches the rule against the beans properties.
	 * 
	 * @param		env The Environment to execute the
	 * 				rules expression.
	 * @return		returns <code>true</code> if the bean
	 * 				matches the rule
	 * @throws		Exception is thrown in case of an error
	 */
	public boolean match(DeferredEnvironment env) throws Exception;

	/**
	 * This method searches for all matching rules and
	 * applies the design to the given HTML element
	 * 
	 * @param		ctx Jsp Page context
	 * @param		element the HTML element to modify
	 * @param		bean the bean that holds the values to
	 * 				check
	 * @throws		Exception is thrown in case of an error
	 */
	public void applyDesign(
		PageContext ctx,
		ConcreteElement element,
		Object bean)
		throws Exception;

	/**
	 * Sets the rule. The design rule will be selected
	 * based on this expression language (EL) expression.
	 *
	 * @param		rule The rule to set
	 * @return		the rule itself
	 */
	public DesignRule setRule(String rule);

	/**
	 * Returns the rule
	 * 
	 * @return		rule
	 */
	public String getRule();

	/**
	 * Sets the Style
	 * @param		style Stil Konstante
	 * @return		the rule itself
	 */
	public DesignRule setStyle(String style);

	/**
	 * Returns the Style
	 * 
	 * @return	String
	 */
	public String getStyle();

	/**
	 * Sets the StyleId
	 * @param		styleId HTML-Id des Elementes
	 * @return		the rule itself
	 */
	public DesignRule setStyleId(String styleId);

	/**
	 * returns the StyleId
	 * @return	string
	 */
	public String getStyleId();

	/**
	 * Sets the StyleClass
	 * @param		styleClass	StyleClass
	 * @return		the rule itself
	 */
	public DesignRule setStyleClass(String styleClass);

	/**
	 * Returns the StyleClass
	 * @return	String
	 */
	public String getStyleClass();
}
