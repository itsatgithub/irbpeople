/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/forms/FormLabelTag.java,v 1.13 2005/08/23 12:22:27 P001002 Exp $
 * $Revision: 1.13 $
 * $Date: 2005/08/23 12:22:27 $
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

package com.cc.framework.taglib.forms;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;

import com.cc.framework.taglib.InnerTag;
import com.cc.framework.taglib.ScriptBodyTagSupport;
import com.cc.framework.taglib.TagHelp;
import com.cc.framework.ui.model.ClientHandler;
import com.cc.framework.ui.model.FormLabelDesignModel;
import com.cc.framework.ui.model.imp.FormLabelDesignModelImp;

/**
 * Base class for form label tag handler
 *
 * @author      <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version     $Revision: 1.13 $
 * @since       1.0
 */
public class FormLabelTag extends ScriptBodyTagSupport implements InnerTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -1302164985867105327L;

	/**
	 * The DesignModel of the label
	 */
	private FormLabelDesignModel designModel	= null;

	/**
	 * Constructor
	 */
	public FormLabelTag() {
		super();
	}

	/**
	 * @return		Retrieves or creates the Labels Design model
	 */
	public FormLabelDesignModel getLabelModel() {
		if (designModel == null) {
			designModel = new FormLabelDesignModelImp();
		}

		return designModel;
	}

	/**
	 * Release the current form element
	 */
	protected void releaseLabel() {
		designModel = null;;
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#setPageContext(PageContext pc)
	 */
	public void setPageContext(PageContext pc) {
		super.setPageContext(pc);

		// initialize variables
		releaseLabel();
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#release()
	 */
	public void release() {
		super.release();

		releaseLabel();
	}

	/**
	 * @see com.cc.framework.taglib.ScriptSupport#getClientHandler()
	 */
	public ClientHandler getClientHandler() {
		return getLabelModel();
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {

		Object parent = findAncestorWithClass(this, LabeledFormElementTag.class);

		// a label tag must be nested within a FormLabelTag
		if (parent == null) {
			throw new JspException("FormLabelTag must be nested inside a LabeledFormElementTag");
		}

		((LabeledFormElementTag) parent).setLabelDesignModel(getLabelModel());

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
				getLabelModel().setText(contentBody);
				getLabelModel().setFilter(false);
				getLabelModel().setBodyInclude(true);
			}
		}

		return SKIP_BODY;
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {
		// release the current lable
		releaseLabel();

		return EVAL_PAGE;
	}

	/**
	 * Specifies the label text
	 * @param	label	The label text
	 */
	public void setLabel(String label) {
		getLabelModel().setText(label);
	}

	/**
	 * Specifies the alignment of the label:
	 * <ul>
	 * 		<li>left = left alignment.</li>
	 * 		<li>right =	right alignment.</li>
	 * 		<li>center = the label contents are centered.</li>
	 * </ul>
	 *
	 * @param		alignment	The alignment of the label
	 * @throws		JspException If the argument can't be converted	to an AlignmentType
	 */
	public void setAlign(String alignment) throws JspException {
		getLabelModel().setAlignment(TagHelp.toAlignment(alignment));
	}

	/**
	 * The automatic HTML coding of the label contents can be activated
	 * or disabled with the filter-attribute. The default is <code>true</code>
	 *
	 * @param	filter	<code>true</code> if the label content should be HTML encoded.
	 * @throws	JspException If the Argument can't be converted to boolean
	 */
	public void setFilter(String filter) throws JspException {
		getLabelModel().setFilter(TagHelp.toBoolean(filter));
	}

	/**
	 * The HTML-id attribute of the element can be specified
	 * with this attribute. Note: See HTML documentation for
	 * the attribute id.
	 *
	 * @param	id	The HTML-id attribute
	 */
	public void setStyleId(String id) {
		getLabelModel().setStyleId(id);
	}

	/**
	 * An HTML-style can be directly specified with this attribute.
	 * Note: See HTML documentation for the attribute style.
	 *
	 * @param	style	An HTML-style
	 */
	public void setStyle(String style) {
		getLabelModel().setStyle(style);
	}

	/**
	 * The HTML-class attribute of the element can be specified
	 * with this attribute. Note: See HTML documentation for
	 * the attribute class.
	 *
	 * @param	styleClass	The HTML-class attribute
	 */
	public void setStyleClass(String styleClass) {
		getLabelModel().setStyleClass(styleClass);
	}

	/**
	 * Specifies a static tooltip text.
	 *
	 * @param	tooltip Tooltip Text
	 */
	public void setTooltip(String tooltip) {
		getLabelModel().setTooltip(tooltip);
	}

	/**
	 * Sets the Reference to an Image in an ImageMap,
	 * which should be display in front of the Label
	 * @param imageRef	Reference to an Image in an ImageMap
	 */
	public void setImageref(String imageRef) {
		getLabelModel().setImageRef(imageRef);
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
		getLabelModel().setMaxLength(TagHelp.toInt(maxlength));
	}

	/**
	 * Sets the labels width
	 *
	 * @param	width		The width
	 */
	public void setWidth(String width) {
		getLabelModel().setWidth(width);
	}

	/**
	 * Sets the nowrap attribute of the label
	 *
	 * @param	nowrap		Boolean Value
	 * @throws	JspException If the Argument can't be converted to boolean
	 */
	public void setNowrap(String nowrap) throws JspException {
		getLabelModel().setNowrap(new Boolean((TagHelp.toBoolean(nowrap))));
	}

	/**
	 * Sets the Loacel configuration for this element
	 *
	 * @param	locale Locale Identifier or <code>true|false</code>
	 */
	public void setLocale(String locale) {
		getLabelModel().setLocaleName(locale);
	}

	/**
	 * The ACCESSKEY attribute can be used to specify a shortcut
	 * key for the &lt;LABEL&gt; (activated by pressing 'Alt' and
	 * the ACCESSKEY togther).
	 *
	 * @param		accessKey The Access Key
	 */
	public void setAccesskey(String accessKey) {
		getLabelModel().setAccessKey(accessKey);
	}
}