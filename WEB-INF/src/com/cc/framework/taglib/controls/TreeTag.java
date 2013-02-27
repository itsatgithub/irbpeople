/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/controls/TreeTag.java,v 1.26 2005/08/23 12:22:26 P001002 Exp $
 * $Revision: 1.26 $
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

import com.cc.framework.taglib.TagHelp;
import com.cc.framework.ui.ExpansionMode;
import com.cc.framework.ui.control.Control;
import com.cc.framework.ui.control.TreeControl;
import com.cc.framework.ui.model.ControlDesignModel;
import com.cc.framework.ui.model.TreeDesignModel;

/**
 * Tag-Handler for the <code>tree</code> Tag.
 * <p>
 * Generates a construction element based on a TreeGroupDataModel.
 * The layout of the tree can be defined in the JSP-Page.
 * The <tree>-tag can only be used in conjunction with a bean that
 * implements the TreeGroupDataModel Interface or is derived from TreeListControl
 *
 * @author      <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version     $Revision: 1.26 $
 * @since       1.0
 */
public class TreeTag extends BaseControlTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 7152784916450360138L;

	/**
	 * Constructor
	 */
	public TreeTag() {
		super();
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateDesignModel()
	 */
	public ControlDesignModel doCreateDesignModel() {
		return new com.cc.framework.ui.model.imp.TreeDesignModelImp();
	}

	/**
	 * Returns a casted version of the ControlDesignModel
	 *
	 * @return		TreeDesignModel
	 */
	protected TreeDesignModel getTreeDesignModel() {
		return (TreeDesignModel) getDesignModel();
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateControl()
	 */
	protected Control doCreateControl() throws JspException {
		// get the model
		Object value = lookupBean();

		if (!(value instanceof TreeControl)) {
			throw new JspException("TreeTag: invalid property class. expected class=TreeControl!");
		}

		TreeDesignModel designModel = getTreeDesignModel();

		// assign the control
		TreeControl ctrl = (TreeControl) value;

		ctrl.setDesignModel(designModel);

		// assign an action if this was not done by the programmer
		if (designModel.getAction() == null) {
			designModel.setAction(getDefaultAction());
		}

		return ctrl;
	}

	/**
	 * Sets the Numer of Rows
	 * Note: Actual not supported
	 *
	 * @param	rowCount	Numer of Rows
	 * @throws JspException
	 *             If the Argument can't be converted to int
	 */
	public void setRows(String rowCount) throws JspException {
		getTreeDesignModel().setRowCount(TagHelp.toInt(rowCount));
	}

	/**
	 * This attribute specifies how many nodes of the tree
	 * structure may be displayed exploded at the same time.
	 * <ul>
	 * 		<li>full =	All nodes are shown exploded at all times. The user cannot close any node.</li>
	 * 		<li>single = Only one branch can be exploded.</li>
	 * 		<li>multiple = As many branches as desired can be exploded.</li>
	 * </ul>
	 *
 	 * @param	expand			Specifies how many nodes of the tree structure may be displayed exploded
	 * @throws	JspException	If the argument can not be converted to an object of type ExpansionMode
	 */
	public void setExpandMode(String expand) throws JspException {

		try {
			getTreeDesignModel().setExpansionMode(ExpansionMode.parse(expand));
		} catch (com.cc.framework.common.InvalidEnumType iet) {
			throw new JspException("Invalid attribute value " + iet.getMessage());
		}
	}

	/**
	 * Indicates whether a checkbox should be displayed before
	 * the tree entries. The tree entries must implement the
	 * Checkable Interface, so that the control element can draw
	 * the checkboxes. Group nodes must not implement this interface,
	 * since the Check-State of groups can be automatically calculated
	 * with the help of the state of the pages.
	 *
	 * @param	value			<code>true</code> if checkboxes should be displayed in the tree
	 * @throws	JspException	if the argument can not be converted to a boolean value
	 */
	public void setCheckboxes(String value) throws JspException {
		getTreeDesignModel().showCheckBoxes(TagHelp.toBoolean(value));
	}

	/**
	 * Indicates whether, in case of group nodes, buttons for
	 * exploding or closing the branch should be displayed.
	 * This option has no effect if the expandMode of the tree
	 * structure is set to full. In that case, buttons are never shown.
	 *
	 * @param		value			<code>true</code> if buttons for exploding or closing the branch should be displayed.
	 * @throws		JspException 	If the Argument can't be converted	to boolean
	 */
	public void setButtons(String value) throws JspException {
		getTreeDesignModel().showButtons(TagHelp.toBoolean(value));
	}

	/**
	 * Specifies whether connecting lines should be drawn
	 * between the construction elements.
	 *
	 * @param	value			<code>true</code> if connecting lines should be drawn
	 * @throws	JspException	If the Argument can't be converted	to boolean
	 */
	public void setLines(String value) throws JspException {
		getTreeDesignModel().showLines(TagHelp.toBoolean(value));
	}

	/**
	 * Specifies whether lines should be drawn to the construction
	 * elements of the uppermost (displayed) level.
	 *
	 * @param	value			<code>false</code> if the lines on the top node should not be displayed.
	 * @throws	JspException	if the argument can not be converted to a boolean value
	 */
	public void setLinesAtRoot(String value) throws JspException {
		getTreeDesignModel().showLinesAtRoot(TagHelp.toBoolean(value));
	}

	/**
	 * Specifies whether the root node of the tree structure (Level 0)
	 * should be displayed or whether the display should first start
	 * at the next tree level(Level 1).
	 *
	 * @param	value			<code>false</code> if the root element should not be displayed
	 * @throws	JspException	if the argument can not be converted to a boolean value
	 */
	public void setRoot(String value) throws JspException {
		getTreeDesignModel().showRoot(TagHelp.toBoolean(value));
	}

	/**
	 * Specifies the property using which an image can
	 * be assigned to every Row-Bean. The image names do
	 * not designate any direct HTML-resources; rather,
	 * they are translated into the actual resource names
	 * with the help of the Imagemap.
	 * Note: Valid Java designator/label for a property
	 * which has to be implemented by every Row-Bean!
	 *
	 * @param	property	Name of the property to assign an image
	 */
	public void setImageProperty(String property) {
		getTreeDesignModel().setImageProperty(property);
	}

	/**
	 * Specifies the property using which a label can be
	 * assigned to every Row-Bean. Note: Valid Java designator/label
	 * for a property which has to be implemented by every Row-Bean!
	 *
	 * @param	property	Name of the property
	 */
	public void setLabelProperty(String property) {
		getTreeDesignModel().setLabelProperty(property);
	}

	/**
	 * Specifies whether, for the group nodes of the tree
	 * structure, a Click Eventhandler should be called.
	 *
	 * @param	value			<code>true</code> if a Click Eventhandler should be called.
	 * @throws	JspException	If the Argument can't be converted	to boolean
	 */
	public void setGroupselect(String value) throws JspException {
		getTreeDesignModel().setGroupSelect(TagHelp.toBoolean(value));
	}

	/**
	 * Specifies the name of an Imagemap which must be saved
	 * in the request. The values that the ImageProperty returns
	 * are mapped to the entries of this Imagemap. The mapping is
	 * done with the help of the regular expression, which is
	 * assigned to every entry of the Imagemap.
	 * The Framework appends, in the case of group nodes, to the
	 * value of the ImageProperty, the character strings
	 * .open or .closed, in order to be able to distinguish
	 * between the open and closed states.
	 * By default, the framework uses a folder icon as the image for group nodes.
	 * Note: Under the name, there must be an Imagemap saved in the request.
	 *
	 * @param	mapName			The ImageMap to assign
	 * @throws	JspException	If the ImageMap can not be found
	 */
	public void setImagemap(String mapName) throws JspException {
		getTreeDesignModel().setImageMap(TagHelp.lookupImageMap(pageContext, mapName));
	}

	/**
	 * The HTML-target attribute of the element can be specified with
	 * this attribute. Note: See HTML documentation for the attribute target.
	 *
	 * @param	target	 The HTML-target attribute
	 */
	public void setTarget(String target) {
		getTreeDesignModel().setTarget(target);
	}

	/**
	 * Specifies the name of a property with the help of which the
	 * relevant line can generate an HTML-target attribute.
	 * Note: Valid Java designator/label. The Row-Bean must
	 * implement a suitable property-getter method.
	 *
	 * @param	targetProperty	The name of a property
	 */
	public void setTargetProperty(String targetProperty) {
		getTreeDesignModel().setTargetProperty(targetProperty);
	}

	/**
	 * Specifies the name of a property with the help of which the
	 * relevant line can generate an HTML-text attribute.
	 * Note: Valid Java designator/label. The Row-Bean must
	 * implement a suitable property-getter method
	 *
	 * @param	tooltipProperty		The name of a property
	 */
	public void setTooltipProperty(String tooltipProperty) {
		getTreeDesignModel().setTooltipProperty(tooltipProperty);
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
		getTreeDesignModel().setMaxLength(TagHelp.toInt(maxlength));
	}

	/**
	 * The automatic HTML coding of the column contents can be activated
	 * or disabled with the filter-attribute. The default is <code>true</code>
	 *
	 * @param	filter	<code>true</code> if the column content should be HTML encoded.
	 * @throws	JspException If the Argument can't be converted to boolean
	 */
	public void setFilter(String filter) throws JspException {
		getTreeDesignModel().setFilter(TagHelp.toBoolean(filter));
	}

	/**
	 * Indicates the name of a property, with the help of which the
	 *  column function can be disabled in the relevant line.
	 * For this, the Row-Bean must provide a corresponding
	 * Property Getter, which returns a Boolean data type.
	 *
	 * @param	value	enable
	 */
	public void setEnableProperty(String value) {
		getTreeDesignModel().setEnableProperty(value);
	}
}