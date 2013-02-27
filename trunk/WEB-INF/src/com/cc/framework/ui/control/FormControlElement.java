/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/control/FormControlElement.java,v 1.18 2005/02/16 18:03:02 P001001 Exp $
 * $Revision: 1.18 $
 * $Date: 2005/02/16 18:03:02 $
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
import com.cc.framework.ui.AlignmentType;
import com.cc.framework.ui.model.FormLabelDesignModel;
import com.cc.framework.ui.model.imp.FormLabelDesignModelImp;

/**
 * A form control element
 *
 * @author   <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version  $Revision: 1.18 $
 * @since    1.0
 */
public class FormControlElement implements LabeledFormElement {

	/**
	 * Label of the form element
	 */
	private FormLabelDesignModel label = new FormLabelDesignModelImp();

	/**
	 * Reqired Flag
	 */
	private boolean required = false;

	/**
	 * Description
	 */
	private String description = null;

	/**
	 * The aggregated Control
	 */
	private Control control = null;

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
	public FormControlElement() {
		super();
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
	 * True if the Formelement is required.
	 * @return	boolean
	 */
	public boolean isRequired() {
		return required;
	}

	/**
	 * Sets the Required Attribute
	 * @param	required	true if the orm element is required
	 */
	public void setRequired(boolean required) {
		this.required = required;
	}

	/**
	 * true if the Formelement can be edited
	 * @return	boolean
	 */
	public boolean isDisabled() {
		return control.getDesignModel().isDisabled();
	}

	/**
	 * Returns the Disabled Flag
	 * @param disabled	true if the Formelement can be edited
	 */
	public void setDisabled(boolean disabled) {
		control.getDesignModel().setDisabled(disabled);

	}

	/**
	 * Returns the Description text which should be displayed
	 * under the Formelement
	 * @return	String
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the Discription
	 * @param description	Discription
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns the Help
	 * @return	String
	 */
	public String getHelp() {
		return control.getHelp();
	}

	/**
	 * Sets the help
	 * @param help	Help
	 */
	public void setHelp(String help) {
		control.getDesignModel().setHelp(help);
	}

	/**
	 * Returns the control.
	 * @return Control
	 */
	public Control getControl() {
		return control;
	}

	/**
	 * Sets the control.
	 * @param control The control to set
	 */
	public void setControl(Control control) {
		this.control = control;
	}

	/**
	 * Returns the Property Namen of the Control
	 * @return	String
	 */
	public String getProperty() {
		if (control == null) {
			return null;
		}

		return control.getProperty();
	}

	/**
	 * @see com.cc.framework.ui.model.AccessControlled#getPermission()
	 */
	public Permission getPermission() {
		// Delegate to the embeded control
		return control.getPermission();
	}

	/**
	 * @see com.cc.framework.ui.model.AccessControlled#show(Principal)
	 */
	public boolean show(Principal principal) {
		// Delegate to the embeded control
		return control.show(principal);
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
		return true;
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#getStyle()
	 */
	public String getStyle() {
		return control.getStyle();
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#getStyleId()
	 */
	public String getStyleId() {
		return control.getStyleId();
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#getStyleClass()
	 */
	public String getStyleClass() {
		return control.getStyleClass();
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#setStyle(java.lang.String)
	 */
	public void setStyle(String style) {
		control.getDesignModel().setStyle(style);
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#setStyleId(java.lang.String)
	 */
	public void setStyleId(String styleId) {
		control.getDesignModel().setStyleId(styleId);
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#setStyleClass(java.lang.String)
	 */
	public void setStyleClass(String styleClass) {
		control.getDesignModel().setStyleClass(styleClass);
	}
}