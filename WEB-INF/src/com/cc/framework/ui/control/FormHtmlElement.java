/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/control/FormHtmlElement.java,v 1.9 2005/02/16 18:03:01 P001001 Exp $
 * $Revision: 1.9 $
 * $Date: 2005/02/16 18:03:01 $
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

package com.cc.framework.ui.control;

import com.cc.framework.security.Permission;
import com.cc.framework.security.Principal;
import com.cc.framework.security.StaticPermission;
import com.cc.framework.ui.AlignmentType;
import com.cc.framework.ui.model.FormLabelDesignModel;
import com.cc.framework.ui.model.imp.FormLabelDesignModelImp;

/**
 * A description in a form
 *
 * @author   <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version  $Revision: 1.9 $
 * @since    1.0
 */
public class FormHtmlElement implements LabeledFormElement {

	/**
	 * Label of the form element
	 */
	private FormLabelDesignModel label = new FormLabelDesignModelImp();

	/**
	 * Indicates if the text should be
	 * converted into HTML
	 */
	private boolean filter = true;

	/**
	 * Indicates wether the description
	 * is set within the body or as an attribute
	 */
	private boolean bodyInclude = false;

	/**
	 * The description
	 */
	private String html;

	/**
	 * The horizontal alignemnt of the form element
	 */
	private AlignmentType alignment = null;

	/**
	 * The vertical alignemnt of the form element
	 */
	private AlignmentType valignment = null;

	/**
	 * The height of the form element
	 */
	private String height = null;

	/**
	 * The width of the form element
	 */
	private String width = null;

	/**
	 * Style ID
	 */
	private String styleId = null;

	/**
	 * Style Class -> HTML class Attribut
	 */
	private String styleClass = null;

	/**
	 * Html-Style
	 */
	private String style = null;

	/**
	 * The Help Identifier for this element
	 */
	private String help = null;

	/**
	 * permissions for this form element
	 */
	private Permission permission = StaticPermission.GRANTED;

	/**
	 * The ColumnSpan of this element
	 */
	private int colspan = 1;

	/**
	 * This flag indicates that this element should be
	 * joined with the preceding element without a separator
	 */
	private boolean join = false;

	/**
	 * Constructor
	 */
	public FormHtmlElement() {
		super();
	}

	/**
	 * Returns the description
	 * @return	String
	 */
	public String getDescription() {
		return null;
	}

	/**
	 * Returns the raw HTML Text
	 *
	 * @return	String
	 */
	public String getHtml() {
		return html;
	}

	/**
	 * Sets the HTML text
	 *
	 * @param	html The HTML Text
	 */
	public void setHtml(String html) {
		this.html = html;
	}

	/**
	 * Returns the filter.
	 * @return boolean
	 */
	public boolean filter() {
		return filter;
	}

	/**
	 * Sets the filter. If true the description
	 * will be HTML encoded (default)
	 * @param filter The filter to set
	 */
	public void setFilter(boolean filter) {
		this.filter = filter;
	}

	/**
	 * Return the Label which should be display
	 * in front of the Form element
	 * @return	String
	 */
	public FormLabelDesignModel getLabel() {
		return label;
	}

	/**
	 * Sets the Label
	 * @param	label	Label
	 */
	public void setLabel(String label) {
		this.label.setText(label);
	}

	/**
	 * Sets the elements Label
	 *
	 * @param	label	Label
	 */
	public void setLabel(FormLabelDesignModel label) {
		if (label.getText() == null) {
			label.setText(this.label.getText());
		}

		this.label = label;
	}

	/**
	 * @see com.cc.framework.ui.control.LabeledFormElement#setAccessKey(java.lang.String)
	 */
	public void setAccessKey(String accessKey) {
		this.label.setAccessKey(accessKey);
	}

	/**
	 * @see com.cc.framework.ui.control.LabeledFormElement#setLabelTooltip(java.lang.String)
	 */
	public void setLabelTooltip(String tooltip) {
		this.label.setTooltip(tooltip);
	}

	/**
	 * Returns if the description was set
	 * within the body (true) or as an attribute (false)
	 * @return	true if the body was specified; false otherwise
	 */
	public boolean isBodyInclude() {
		return bodyInclude;
	}

	/**
	 * Sets if the description is set
	 * within the body (=true)
	 * @param include	Flag which indicates if the body was set instead of the attribute
	 */
	public void setBodyInclude(boolean include) {
		bodyInclude = include;
	}

	/**
	 * @see com.cc.framework.ui.model.AccessControlled#getPermission()
	 */
	public Permission getPermission() {
		return permission;
	}

	/**
	 * Sets the elements permission
	 *
	 * @param		permission required permission
	 */
	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	/**
	 * @see com.cc.framework.ui.model.AccessControlled#show(Principal)
	 */
	public boolean show(Principal principal) {
		return permission.isGranted(principal);
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#getColSpan()
	 */
	public int getColSpan() {
		return colspan;
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#setColSpan(int)
	 */
	public void setColSpan(int colspan) {
		this.colspan = colspan;
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#joinElements()
	 */
	public boolean joinElements() {
		return join;
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#setJoin(boolean)
	 */
	public void setJoin(boolean join) {
		this.join = join;
	}

	/**
	 * Assigns the Crumb an Image from an ImageMap
	 * @param	ref	Rule of the ImageMap to match
	 */
	public void setImageRef(String ref) {
		this.label.setImageRef(ref);
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#getAlignment()
	 */
	public AlignmentType getAlignment() {
		return alignment;
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#getVAlignment()
	 */
	public AlignmentType getVAlignment() {
		return valignment;
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#setAlignment(com.cc.framework.ui.AlignmentType)
	 */
	public void setAlignment(AlignmentType alignment) {
		this.alignment = alignment;
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#setVAlignment(com.cc.framework.ui.AlignmentType)
	 */
	public void setVAlignment(AlignmentType valignment) {
		this.valignment = valignment;
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#getHeight()
	 */
	public String getHeight() {
		return height;
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#getWidth()
	 */
	public String getWidth() {
		return width;
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#setHeight(java.lang.String)
	 */
	public void setHeight(String height) {
		this.height = height;
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#setWidth(java.lang.String)
	 */
	public void setWidth(String width) {
		this.width = width;
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#getNoWrap()
	 */
	public boolean getNoWrap() {
		return false;
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#getStyle()
	 */
	public String getStyle() {
		return style;
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#getStyleId()
	 */
	public String getStyleId() {
		return styleId;
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#getStyleClass()
	 */
	public String getStyleClass() {
		return styleClass;
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#setStyle(java.lang.String)
	 */
	public void setStyle(String style) {
		this.style = style;
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#setStyleId(java.lang.String)
	 */
	public void setStyleId(String styleId) {
		this.styleId = styleId;
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#setStyleClass(java.lang.String)
	 */
	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#getHelp()
	 */
	public String getHelp() {
		return help;
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#setHelp(java.lang.String)
	 */
	public void setHelp(String helpId) {
		this.help = helpId;
	}
}