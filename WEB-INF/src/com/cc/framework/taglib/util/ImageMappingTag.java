/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/util/ImageMappingTag.java,v 1.15 2005/08/02 19:13:06 P001002 Exp $
 * $Revision: 1.15 $
 * $Date: 2005/08/02 19:13:06 $
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

import com.cc.framework.taglib.InnerTag;
import com.cc.framework.taglib.base.ImageTag;
import com.cc.framework.ui.model.imp.ImageModelImp;

/**
 * Tag-Handler for the <code>ImageMapping</code> Tag.
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.15 $
 * @since      1.0
 */
public class ImageMappingTag extends ImageTag implements InnerTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 2233884637915942950L;
	/**
	 * Rule
	 */
	private String rule	= null;

	/**
	 * Constructor
	 */
	public ImageMappingTag() {
		super();
	}

	/**
	 * @see	javax.servlet.jsp.tagext.Tag#setPageContext(PageContext pc)
	 */
	public void setPageContext(PageContext pc) {
		super.setPageContext(pc);

		rule = null;
	}

	/**
	 * @see	javax.servlet.jsp.tagext.Tag#release()
	 */
	public void release() {
		rule = null;
	}

	/**
	 * @see	javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {

		Object parent = findAncestorWithClass(this, ImageMapTag.class);

		// a column tag must be nested within a PanelTag
		if (parent == null) {
			throw new JspException("ImageMappingTag must be nested inside a ImageMapTag");
		}

		// The Model is now assigned to the Parent Tag
		ImageMapTag map		= (ImageMapTag) parent;
		ImageModelImp img	= getImage();

		// Use the base Attribute from the imagemap
		img.setBase(map.getBase());

		map.addImage(rule, img);

		// The Tag has no Body
		return SKIP_BODY;
	}

	/**
	 * @see	javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {
		// Overwrite the default processing
		// -> this tag generates no visible HTML output!

		// release data model
		releaseImage();

		return EVAL_PAGE;
	}

	/**
	 * Sets the rule
	 *
	 * @param	rule	The rule to set
	 */
	public void setRule(String rule) {
		this.rule	= rule;
	}
}