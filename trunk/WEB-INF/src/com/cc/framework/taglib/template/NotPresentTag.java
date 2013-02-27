/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/template/NotPresentTag.java,v 1.11 2005/07/08 14:15:12 P001002 Exp $
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

/**
 * Tag handler for the <code>notpresent</code> tag.
 * The tag checks the non-existence of a JSP-fragment
 * and in case of success, executes the Body-Content.
 * The tag may only be used within a <template:insert>-tag. It can thus,
 * in particular, be used in a JSP template, since these are located
 * in the Scope of a <template:insert>-tag!
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version	   $Revision: 1.11 $
 * @since      1.0
 */
public class NotPresentTag extends PresentTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -9218988338271731882L;

	/**
	 * Constructor
	 */
	public NotPresentTag() {
		super();
	}

	/**
	 * @see com.cc.framework.taglib.template.PresentTag#condition()
	 */
	protected boolean condition() throws JspException {
		return !TemplateHelp.isContentPresent(pageContext, getName());
	}

}