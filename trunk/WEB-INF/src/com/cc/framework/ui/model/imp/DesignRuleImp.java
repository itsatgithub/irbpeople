/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/DesignRuleImp.java,v 1.8 2005/07/08 15:15:32 P001002 Exp $
 * $Revision: 1.8 $
 * $Date: 2005/07/08 15:15:32 $
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

package com.cc.framework.ui.model.imp;

import javax.servlet.jsp.PageContext;

import org.apache.ecs.ConcreteElement;

import com.cc.framework.ui.model.DesignRule;
import com.cc.framework.ui.model.value.DeferredEnvironment;
import com.cc.framework.ui.model.value.DeferredValue;
import com.cc.framework.ui.painter.PainterHelp;

/**
 * A design rule is used to modify the properties
 * of a design model at runtime based on a rule
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.8 $
 */
public class DesignRuleImp extends ClientHandlerImp implements DesignRule {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -9140838097611002671L;

	/**
	 * The rule. The design rule will be selected
	 * based on this expression language (EL) expression.
	 */
	private String rule					= null;

	/**
	 * Style ID
	 */
	private String styleId				= null;

	/**
	 * Style Class -> HTML class Attribut
	 */
	private String styleClass			= null;

	/**
	 * Html-Style
	 */
	private String style				= null;

	/**
	 * Constructor
	 */
	public DesignRuleImp() {
		super();
	}

	/**
	 * @see com.cc.framework.ui.model.DesignRule#getRule()
	 */
	public String getRule() {
		return rule;
	}

	/**
	 * @see com.cc.framework.ui.model.DesignRule#setRule(java.lang.String)
	 */
	public DesignRule setRule(String rule) {
		this.rule = rule;
		return this;
	}

	/**
	 * @see com.cc.framework.ui.model.DesignRule#match(com.cc.framework.ui.model.value.DeferredEnvironment)
	 */
	public boolean match(DeferredEnvironment env) throws Exception {
		if (rule == null) {
			return false;
		} else {
			Object obj = DeferredValue.evaluate(rule, env);

			if (obj instanceof Boolean) {
				return ((Boolean) obj).booleanValue();
			} else if (obj instanceof Number) {
				return ((Number) obj).doubleValue() > 0.0d;
			} else if (obj instanceof String) {
				return "true".equalsIgnoreCase((String) obj);
			} else {
				throw new Exception("could not evaluate expression to boolean: " + obj);
			}
		}
	}

	/**
	 * @see com.cc.framework.ui.model.DesignRule#applyDesign(javax.servlet.jsp.PageContext, org.apache.ecs.ConcreteElement, java.lang.Object)
	 */
	public void applyDesign(PageContext ctx, ConcreteElement element, Object bean)
		throws Exception {

		if (getStyle() != null) {
			element.setStyle(getStyle());
		}

		if (getStyleClass() != null) {
			element.setClass(getStyleClass());
		}

		if (getStyleId() != null) {
			element.setID(getStyleId());
		}

		PainterHelp.setScriptHandler(
			element,
			ClientHandlerImp.evaluate(ctx, this, bean));
	}

	/**
	 * @see com.cc.framework.ui.model.DesignRule#getStyle()
	 */
	public String getStyle() {
		return style;
	}

	/**
	 * @see com.cc.framework.ui.model.DesignRule#getStyleClass()
	 */
	public String getStyleClass() {
		return styleClass;
	}

	/**
	 * @see com.cc.framework.ui.model.DesignRule#getStyleId()
	 */
	public String getStyleId() {
		return styleId;
	}

	/**
	 * @see com.cc.framework.ui.model.DesignRule#setStyle(java.lang.String)
	 */
	public DesignRule setStyle(String style) {
		this.style = style;
		return this;
	}

	/**
	 * @see com.cc.framework.ui.model.DesignRule#setStyleClass(java.lang.String)
	 */
	public DesignRule setStyleClass(String styleClass) {
		this.styleClass = styleClass;
		return this;
	}

	/**
	 * @see com.cc.framework.ui.model.DesignRule#setStyleId(java.lang.String)
	 */
	public DesignRule setStyleId(String styleId) {
		this.styleId = styleId;
		return this;
	}
}
