/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/template/PresentTag.java,v 1.11 2005/07/08 14:15:12 P001002 Exp $
 * $Revision: 1.11 $
 * $Date: 2005/07/08 14:15:12 $
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

package com.cc.framework.taglib.template;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Tag handler for the <code>present</code> tag.
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version	   $Revision: 1.11 $
 * @since      1.0
 */
public class PresentTag extends TagSupport {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -8461630659880302119L;

	/**
	 * Field name
	 */
	private String name;

	/**
	 * Constructor
	 */
	public PresentTag() {
		super();
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	  */
	public int doStartTag() throws JspException {
		if (condition()) {
			return EVAL_BODY_INCLUDE;
		} else {
			return SKIP_BODY;
		}
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	/**
	 * methode condition
	 *
	 * @return	boolean boolean result of the evaluation
	 * @throws JspException If an error occurs
	 */
	protected boolean condition() throws JspException {
		return TemplateHelp.isContentPresent(pageContext, name);
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#release()
	 */
	public void release() {
		name = null;
	}

	/**
	 * Sets the Name
	 *
	 * @param	name	Name of the template
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the Name
	 *
	 * @return String
	 */
	public String getName() {
		return name;
	}
}