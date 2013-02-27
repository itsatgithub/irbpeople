/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/util/DesignRuleTag.java,v 1.7 2005/07/31 08:59:08 P001002 Exp $
 * $Revision: 1.7 $
 * $Date: 2005/07/31 08:59:08 $
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

package com.cc.framework.taglib.util;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import com.cc.framework.taglib.DesignRuleContainerTag;
import com.cc.framework.taglib.InnerTag;
import com.cc.framework.taglib.ScriptTagSupport;
import com.cc.framework.ui.model.ClientHandler;
import com.cc.framework.ui.model.DesignRule;
import com.cc.framework.ui.model.imp.DesignRuleImp;

/**
 * Tag for declaring design rules
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.7 $
 */
public class DesignRuleTag extends ScriptTagSupport implements InnerTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 4743612102684910347L;

	/**
	 * The design rule object
	 */
	private DesignRule designRule = null;

	/**
	 * Constructor
	 */
	public DesignRuleTag() {
		super();
	}

	/**
	 * Returns the design rule
	 *
	 * @return	DesignRule
	 */
	protected DesignRule getDesignRule() {
		if (designRule == null) {
			designRule = doCreateDesignRule();
		}
		
		return designRule;
	}

	/**
	 * Creates the design rule object
	 * 
	 * @return		new DesignRule Object
	 */
	protected DesignRule doCreateDesignRule() {
		return new DesignRuleImp();
	}

	/**
	 * Releases the designrule
	 */
	protected void releaseDesignRule() {
		designRule = null;
	}

	/**
	 * @see	javax.servlet.jsp.tagext.Tag#setPageContext(PageContext pc)
	 */
	public void setPageContext(PageContext pc) {
		super.setPageContext(pc);

		// initialize variables
		releaseDesignRule();
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#release()
	 */
	public void release() {
		super.release();
		
		releaseDesignRule();
	}

	/**
	 * @see com.cc.framework.taglib.ScriptSupport#getClientHandler()
	 */
	public ClientHandler getClientHandler() {
		return getDesignRule();
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {

		Object parent = findAncestorWithClass(this, DesignRuleContainerTag.class);

		// a design rule tag must be nested within a DesignRuleContainerTag
		if (parent == null) {
			throw new JspException("DesignRuleTag must be nested inside a DesignRuleContainerTag");
		}

		DesignRuleContainerTag container = (DesignRuleContainerTag) parent;
		container.addDesignRule(getDesignRule());

		return EVAL_BODY_INCLUDE;
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {
		releaseDesignRule();

		return EVAL_PAGE;
	}

	/**
	 * Sets the rule
	 * 
	 * @param	rule the rule
	 */
	public void setRule(String rule) {
		getDesignRule().setRule(rule);
	}

	/**
	 * An HTML-style. See HTML documentation for the attribute style.
	 * 
	 * @param	style	An HTML-style
	 */
	public void setStyle(String style) {
		getDesignRule().setStyle(style);
	}

	/**
	 * The HTML-id attribute. See HTML documentation for the Attribute id.
	 *  
	 * @param styleId	The HTML-id attribute
	 */
	public void setStyleId(String styleId) {
		getDesignRule().setStyleId(styleId);
	}

	/**
	 * The HTML-class attribute. See HTML documentation for the attribute class.
	 *  
	 * @param	styleClass	The HTML-class attribute
	 */
	public void setStyleClass(String styleClass) {
		getDesignRule().setStyleClass(styleClass);
	}
}
