/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/controls/ColumnHeaderTag.java,v 1.9 2005/08/23 12:22:26 P001002 Exp $
 * $Revision: 1.9 $
 * $Date: 2005/08/23 12:22:26 $
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

package com.cc.framework.taglib.controls;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;

import com.cc.framework.taglib.InnerTag;
import com.cc.framework.taglib.ScriptBodyTagSupport;
import com.cc.framework.taglib.TagHelp;
import com.cc.framework.ui.model.ClientHandler;
import com.cc.framework.ui.model.ColumnDesignModel;
import com.cc.framework.ui.model.ColumnHeaderDesignModel;
import com.cc.framework.ui.model.imp.ColumnHeaderDesignModelImp;

/**
 * Base class for column header tag handler
 *
 * @author      <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version     $Revision: 1.9 $
 * @since       1.0
 */
public class ColumnHeaderTag extends ScriptBodyTagSupport implements InnerTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 6048614178319980397L;

	/**
	 * The DesignModel of the column
	 */
	private ColumnHeaderDesignModel designModel	= null;

	/**
	 * Constructor
	 */
	public ColumnHeaderTag() {
		super();
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#setPageContext(PageContext pc)
	 */
	public void setPageContext(PageContext pc) {
		super.setPageContext(pc);

		// Create a new DesignModel
		designModel	= new ColumnHeaderDesignModelImp();
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#release()
	 */
	public void release() {
		designModel = null;
	}

	/**
	 * @see com.cc.framework.taglib.ScriptSupport#getClientHandler()
	 */
	public ClientHandler getClientHandler() {
		return designModel;
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {

		Object parent = findAncestorWithClass(this, ColumnBaseTag.class);

		// a column tag must be nested within a ColumnBaseTag
		if (parent == null) {
			throw new JspException("ColumnHeaderTag must be nested inside a ColumnBaseTag");
		}

		ColumnDesignModel column = ((ColumnBaseTag) parent).getDesignModel();
		column.setHeader(designModel);

		return EVAL_BODY_BUFFERED;
	}

	/**
	 * @see javax.servlet.jsp.tagext.IterationTag#doAfterBody()
	 */
	public int doAfterBody() throws JspException {

		BodyContent body = getBodyContent();
		if (body != null) {
			String contentBody = body.getString().trim();

			if (!"".equals(contentBody)) {
				designModel.setTitle(contentBody);
				designModel.setFilter(false);
				designModel.setBodyInclude(true);
			}
		}

		return SKIP_BODY;
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	/**
	 * Specifies the column title
	 * @param	title	The column title
	 */
	public void setTitle(String title) {
		designModel.setTitle(title);
	}

	/**
	 * Specifies the alignment of the column:
	 * <ul>
	 * 		<li>left = left alignment.</li>
	 * 		<li>right =	right alignment.</li>
	 * 		<li>center = the column contents are centered.</li>
	 * </ul>
	 *
	 * @param		alignment	The alignment of the column
	 * @throws		JspException If the argument can't be converted	to an AlignmentType
	 */
	public void setAlign(String alignment) throws JspException {
		designModel.setAlignment(TagHelp.toAlignment(alignment));
	}

	/**
	 * The automatic HTML coding of the column contents can be activated
	 * or disabled with the filter-attribute. The default is <code>true</code>
	 *
	 * @param	filter	<code>true</code> if the column content should be HTML encoded.
	 * @throws	JspException If the Argument can't be converted to boolean
	 */
	public void setFilter(String filter) throws JspException {
		designModel.setFilter(TagHelp.toBoolean(filter));
	}

	/**
	 * The HTML-id attribute of the element can be specified
	 * with this attribute. Note: See HTML documentation for
	 * the attribute id.
	 *
	 * @param	id	The HTML-id attribute
	 */
	public void setStyleId(String id) {
		designModel.setStyleId(id);
	}

	/**
	 * An HTML-style can be directly specified with this attribute.
	 * Note: See HTML documentation for the attribute style.
	 *
	 * @param	style	An HTML-style
	 */
	public void setStyle(String style) {
		designModel.setStyle(style);
	}

	/**
	 * The HTML-class attribute of the element can be specified
	 * with this attribute. Note: See HTML documentation for
	 * the attribute class.
	 *
	 * @param	styleClass	The HTML-class attribute
	 */
	public void setStyleClass(String styleClass) {
		designModel.setStyleClass(styleClass);
	}

	/**
	 * Specifies a static tooltip text.
	 *
	 * @param	tooltip Tooltip Text
	 */
	public void setTooltip(String tooltip) {
		designModel.setTooltip(tooltip);
	}

	/**
	 * Sets the Reference to an Image in an ImageMap,
	 * which should be display in front of the Label
	 * @param imageRef	Reference to an Image in an ImageMap
	 */
	public void setImageref(String imageRef) {
		designModel.setImageRef(imageRef);
	}

	/**
	 * Specifies the name of an Imagemap which must be saved
	 * in the request. The values that the property-attribute
	 * returns are mapped to the entries of this Imagemap.
	 * The mapping is done with the help of the regular expression,
	 * which is assigned to every entry of the Imagemap.
	 * Note: Under the name, there must be an Imagemap saved in the request.
	 *
	 * @param	mapName		Name of the Imagemap
	 * @throws	JspException If the Image Map could not be found
	 */
	public void setImagemap(String mapName) throws JspException {
		designModel.setImageMap(TagHelp.lookupImageMap(pageContext, mapName));
	}

	/**
	 * Specifies the maximum number of characters for the
	 * data input or display.
	 *
	 * @param	maxlength	The maximum number of characters
	 * @throws JspException
	 *             If the Argument can't be converted to int
	 */
	public void setMaxlength(String maxlength) throws JspException {
		designModel.setMaxLength(TagHelp.toInt(maxlength));
	}
}