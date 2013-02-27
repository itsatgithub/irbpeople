/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/controls/ColumnBaseTag.java,v 1.40 2005/08/02 19:13:05 P001002 Exp $
 * $Revision: 1.40 $
 * $Date: 2005/08/02 19:13:05 $
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

import com.cc.framework.common.InvalidEnumType;
import com.cc.framework.convert.Converter;
import com.cc.framework.convert.ConverterHelp;
import com.cc.framework.taglib.ConversionSupportTag;
import com.cc.framework.taglib.DesignRuleContainerTag;
import com.cc.framework.taglib.InnerTag;
import com.cc.framework.taglib.ScriptBodyTagSupport;
import com.cc.framework.taglib.TagHelp;
import com.cc.framework.ui.RunAt;
import com.cc.framework.ui.model.ClientHandler;
import com.cc.framework.ui.model.ColumnDesignModel;
import com.cc.framework.ui.model.DesignRule;

/**
 * Base class for column tag handler
 *
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision: 1.40 $
 * @since 1.0
 */
public abstract class ColumnBaseTag extends ScriptBodyTagSupport implements
		InnerTag, DesignRuleContainerTag, ConversionSupportTag {

	/**
	 * The DesignModel of the column
	 */
	private ColumnDesignModel designModel = null;

	/**
	 * The Parent Container Tag
	 */
	private ColumnContainerTag container = null;

	/**
	 * Constructor
	 */
	public ColumnBaseTag() {
		super();
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#release()
	 */
	public void release() {
		super.release();

		releaseDesignModel();
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#setPageContext(PageContext pc)
	 */
	public void setPageContext(PageContext pc) {
		super.setPageContext(pc);

		// initialize variables
		releaseDesignModel();
	}

	/**
	 * Returns the DesignModel for the column
	 *
	 * @return ColumnDesignModel
	 */
	protected ColumnDesignModel getDesignModel() {
		if (designModel == null) {
			designModel = doCreateDesignModel();
		}

		return designModel;
	}

	/**
	 * This method is called to create the DesignModel for the column
	 *
	 * @return A concrete DesignModel
	 */
	protected abstract ColumnDesignModel doCreateDesignModel();

	/**
	 * This method gets called when the design model is not longer needed
	 */
	protected void releaseDesignModel() {
		designModel = null;
	}

	/**
	 * @see com.cc.framework.taglib.ScriptSupport#getClientHandler()
	 */
	public ClientHandler getClientHandler() {
		return getDesignModel();
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {
		Object parent = findAncestorWithClass(this, ColumnContainerTag.class);

		// a column tag must be nested within a ColumnContainerTag
		if (parent == null) {
			throw new JspException(
					"ColumnTag must be nested inside a ColumnContainerTag");
		}

		container = (ColumnContainerTag) parent;
		container.addColumn(getDesignModel());

		return EVAL_BODY_BUFFERED;
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {
		releaseDesignModel();

		return EVAL_PAGE;
	}

	/**
	 * Returns te container in which this column is nested
	 *
	 * @return Container
	 */
	public ColumnContainerTag getContainer() {
		return container;
	}

	/**
	 * Specifies the rowbean java type
	 *
	 * @param type
	 *            The row bean type
	 */
	public void setType(String type) {
		getDesignModel().setType(type);
	}

	/**
	 * Specifies the column title
	 *
	 * @param title
	 *            The column title
	 */
	public void setTitle(String title) {
		getDesignModel().setTitle(title);
	}

	/**
	 * The name of the property that contains the data of the column. There must
	 * be a corresponding property-getter implemented in the Row Bean. With some
	 * column types, by returning a Boolean value, the relevant line can be
	 * activated or deactivated
	 *
	 * @param property
	 *            Name of the property that contains the data of the column
	 */
	public void setProperty(String property) {
		getDesignModel().setProperty(property);
	}

	/**
	 * Sets the width of the column (pixel or %)
	 *
	 * @param newWidth
	 *            The Width
	 */
	public void setWidth(String newWidth) {
		getDesignModel().setWidth(newWidth);
	}

	/**
	 * Specifies the alignment of the column:
	 * <ul>
	 * <li>left = left alignment.</li>
	 * <li>right = right alignment.</li>
	 * <li>center = the column contents are centered.</li>
	 * </ul>
	 *
	 * @param alignment
	 *            The alignment of the column
	 */
	public void setAlign(String alignment) {
		getDesignModel().setAlignment(alignment);
	}

	/**
	 * The attribute indicates whether sorting can be carried out according to
	 * this column. Here, if the value true is specified, then the user can
	 * toggle the sorting order with a click on the column header.
	 *
	 * @param value
	 *            <code>true</code> if the column can be sorted
	 * @throws JspException
	 *             If the argument can't be parsed
	 */
	public void setSortable(String value) throws JspException {
		getDesignModel().setSortable(TagHelp.toBoolean(value));
	}

	/**
	 * The automatic HTML coding of the column contents can be activated or
	 * disabled with the filter-attribute. The default is <code>true</code>
	 *
	 * @param filter
	 *            <code>true</code> if the column content should be HTML
	 *            encoded.
	 */
	public void setFilter(String filter) {
		getDesignModel().setFilter(filter);
	}

	/**
	 * Sets the columns edit mode
	 *
	 * @param editable
	 *            <code>true</code> if the column is editable
	 */
	public void setEditable(String editable) {
		getDesignModel().setEditable(editable);
	}

	/**
	 * Sets the property which should be used to make the column editable or not
	 *
	 * @param property
	 *            the name of the property
	 */
	public void setEditableProperty(String property) {
		getDesignModel().setEditableProperty(property);
	}

	/**
	 * Indicates the name of a property, with the help of which the column
	 * function can be disabled in the relevant line. For this, the Row-Bean
	 * must provide a corresponding Property Getter, which returns a Boolean
	 * data type.
	 *
	 * @param value
	 *            enable
	 */
	public void setEnableProperty(String value) {
		getDesignModel().setEnableProperty(value);
	}

	/**
	 * Specifies the maximum number of characters for the data input or display.
	 *
	 * @param maxlength
	 *            The maximum number of characters
	 */
	public void setMaxlength(String maxlength) {
		getDesignModel().setMaxLength(maxlength);
	}

	/**
	 * The HTML-id attribute of the element can be specified with this
	 * attribute. Note: See HTML documentation for the attribute id.
	 *
	 * @param id
	 *            The HTML-id attribute
	 */
	public void setStyleId(String id) {
		getDesignModel().setStyleId(id);
	}

	/**
	 * With this attribute, access to the element can be restricted.
	 * Authorizations are checked using the com.cc.framework.security.Principal
	 * object in the user session. Authorizations are always specified in the
	 * form of an Access Control List (ACL). What is involved here is a
	 * semicolon-delimited list with individual authorizations.
	 *
	 * @param permission
	 *            String witch stands for the required authorization
	 * @see com.cc.framework.security.SecurityUtil
	 */
	public void setPermission(String permission) {
		getDesignModel().setPermission(permission);
	}

	/**
	 * This attribute specifies whether, for the control element, Clientside
	 * JavaScript should be used, or whether the control element should work
	 * purely with Server Roundtrips.
	 *
	 * @param runat
	 *            "client" or "server"
	 * @throws JspException
	 *             If the Argument can't be converted to an object of type RunAt
	 */
	public void setRunat(String runat) throws JspException {
		try {
			getDesignModel().setRunAt(RunAt.parse(runat));
		} catch (InvalidEnumType iet) {
			throw new JspException(iet.getMessage());
		}
	}

	/**
	 * An HTML-style can be directly specified with this attribute. Note: See
	 * HTML documentation for the attribute style.
	 *
	 * @param style
	 *            An HTML-style
	 */
	public void setStyle(String style) {
		getDesignModel().setStyle(style);
	}

	/**
	 * The HTML-class attribute of the element can be specified with this
	 * attribute. Note: See HTML documentation for the attribute class.
	 *
	 * @param styleClass
	 *            The HTML-class attribute
	 */
	public void setStyleClass(String styleClass) {
		getDesignModel().setStyleClass(styleClass);
	}

	/**
	 * The HTML-target attribute of the element can be specified with this
	 * attribute. Note: See HTML documentation for the attribute target.
	 *
	 * @param target
	 *            The HTML-target attribute
	 */
	public void setTarget(String target) {
		getDesignModel().setTarget(target);
	}

	/**
	 * Specifies the name of a property with the help of which the relevant line
	 * can generate an HTML-target attribute. Note: The Row-Bean must implement
	 * a suitable property-getter method.
	 *
	 * @param targetProperty
	 *            Name of a property
	 */
	public void setTargetProperty(String targetProperty) {
		getDesignModel().setTargetProperty(targetProperty);
	}

	/**
	 * Specifies a static tooltip text.
	 *
	 * @param tooltip
	 *            Tooltip Text
	 */
	public void setTooltip(String tooltip) {
		getDesignModel().setTooltip(tooltip);
	}

	/**
	 * Specifies the name of a property with the help of which the relevant line
	 * can generate an HTML-text attribute for the tooltip. Note: The Row-Bean
	 * must implement a suitable property-getter method.
	 *
	 * @param tooltipProperty
	 *            TooltipProperty
	 */
	public void setTooltipProperty(String tooltipProperty) {
		getDesignModel().setTooltipProperty(tooltipProperty);
	}

	/**
	 * Sets the ImageProperty. The ImageProperty identifies the Prperty which
	 * should be evaluated to referenz an Image in an ImageMap
	 *
	 * @param property
	 *            Property
	 */
	public void setImageProperty(String property) {
		getDesignModel().setImageProperty(property);
	}

	/**
	 * Specifies the name of an Imagemap which must be saved in the request. The
	 * values that the property-attribute returns are mapped to the entries of
	 * this Imagemap. The mapping is done with the help of the regular
	 * expression, which is assigned to every entry of the Imagemap. Note: Under
	 * the name, there must be an Imagemap saved in the request.
	 *
	 * @param mapName
	 *            Name of the Imagemap
	 * @throws JspException
	 *             If the Image Map could not be found
	 */
	public void setImagemap(String mapName) throws JspException {
		getDesignModel().setImageMap(
				TagHelp.lookupImageMap(pageContext, mapName));
	}

	/**
	 * Directs the framework to include a transaction token (if any) in all
	 * generated hyperlinks for this column. The Transaction token is used to
	 * track form re-submissions.
	 *
	 * @param transaction
	 *            include transaction token
	 * @throws JspException
	 *             If the Argument can't be converted to boolean
	 */
	public void setTransaction(String transaction) throws JspException {
		getDesignModel().setTransaction(TagHelp.toBoolean(transaction));
	}

	/**
	 * Sets the Converter that should be used to convert Java Objects into their
	 * localized String representation. If no converter is specified the
	 * framework will use a default Converter that matches the row beans column
	 * property.
	 * <p>
	 * The Converter is set for all rows of the columns. It is not possible to
	 * set individual converters for each row in a column!
	 *
	 * @param converter
	 *            Converters class Name
	 * @throws JspException
	 *             If the Converter is invalid
	 */
	public void setConverter(String converter) throws JspException {
		try {
			assignConverter(ConverterHelp.getInstance(converter));
		} catch (Exception e) {
			throw new JspException(e);
		}
	}

	/**
	 * @see com.cc.framework.taglib.ConversionSupportTag#assignConverter(com.cc.framework.convert.Converter)
	 */
	public void assignConverter(Converter converter) {
		getDesignModel().setConverter(converter);
	}

	/**
	 * @see com.cc.framework.taglib.DesignRuleContainerTag#addDesignRule(com.cc.framework.ui.model.DesignRule)
	 */
	public void addDesignRule(DesignRule rule) {
		getDesignModel().addDesignRule(rule);
	}
}