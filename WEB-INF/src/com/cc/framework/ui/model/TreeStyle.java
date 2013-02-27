/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/TreeStyle.java,v 1.16 2005/02/16 18:13:29 P001001 Exp $
 * $Revision: 1.16 $
 * $Date: 2005/02/16 18:13:29 $
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

package com.cc.framework.ui.model;

import com.cc.framework.ui.ExpansionMode;
import com.cc.framework.ui.SelectMode;

/**
 * The interface defines the styles of a tree element
 *
 * @author   <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version  $Revision: 1.16 $
 * @since    1.0
 */
public interface TreeStyle {

	/**
	 * Returns the expansion state.
	 *  <ul>
	 *   <li>FULL = All nodes are shown exploded at all times. The user cannot close any node.</li>
	 *   <li>SINGLE = Only one branch can be exploded.</li>
	 *   <li>MULTIPLE = As many branches as desired can be exploded. </li>
	 * </ul>
	 *
	 * @return	The expansion mode
	 */
	public ExpansionMode getExpansionMode();

	/**
	 * Sets the expansion state
	 *
	 * @param	mode	The expansion mode
	 */
	public void setExpansionMode(ExpansionMode mode);

	/**
	 * Returns if the icons for expanding a node are displayed.
	 * <b>Note:</b> The class which implements this interface must
	 * be aware of the settings of the expansion mode. If the nodes
	 * are always expanded this method should return false.
	 *
	 * @return		boolean
	 */
	public boolean showButtons();

	/**
	 * Defines if the icons for expanding a node schould be displayed.
	 *
	 * @param	show	<code>true</code> if the icons schould be displayed;<br>
	 * 					<code>false</code> otherwise.
	 */
	public void showButtons(boolean show);

	/**
	 * Returns if a line between the nodes is displayed.
	 *
	 * @return	<code>true</code> if a line between the nodes is displayed;<br>
	 * 			<code>false</code> otherwise.
	 */
	public boolean showLines();

	/**
	 * Defines if a line between the nodes schould be displayed.
	 *
	 * @param	show 	<code>true</code> if a line between the nodes schould be displayed;<br>
	 *					<code>false</code> otherwise.
	 */
	public void showLines(boolean show);

	/**
	 * Returns whether lines should be drawn to the
	 * construction elements of the uppermost (displayed) level.
	 *
	 * @return	boolean
	 */
	public boolean showLinesAtRoot();

	/**
	 * Specifies whether lines should be drawn to the
	 * construction elements of the uppermost (displayed) level.
	 *
	 * @param	show	<code>true</code> if the line should be visible on the uppermost level;
	 * 					<code>false</code> otherwiese
	 */
	public void showLinesAtRoot(boolean show);

	/**
	 * Returns if the root element should be displayed
	 *
	 * @return	boolean		<code>true</code> if the root element should be displayed;
	 * 						<code>false</code> otherwiese
	 */
	public boolean showRoot();

	/**
	 * Specifies whether the root of the tree element schould be displayed.
	 *
	 * @param	show <code>true</code> if the root element should be displayed
	 */
	public void showRoot(boolean show);

	/**
	 * Returns if a checkbox is displayed before the tree entries.
	 *
	 * @return	boolean	<code>true</code> if a checkbox is displayed;<br>
	 * 					<code>false</code> otherwise
	 */
	public boolean showCheckBoxes();

	/**
	 * Indicates whether a checkbox should be displayed before the tree entries<br>
	 * The tree entries must implement the <code>Checkable</code> Interface, so that the control
	 * element can draw the checkboxes. Group nodes must not implement this interface,
	 * since the Check-State of groups can be automatically calculated with the help
	 * of the state of the pages.
	 *
	 * @param	show	<code>true</code> if checkbox schould be displayed;<br>
	 * 					<code>false</code> otherwise
	 */
	public void showCheckBoxes(boolean show);

	/**
	 * Defines if the group nodes should be selectabel.
	 *
	 * @param	select	<code>true</code> if the group nodes should be selectabel;<br>
	 * 					<code>false</code> otherwise
	 */
	public void setGroupSelect(boolean select);

	/**
	 * Returns if the group nodes are selectabel.
	 *
	 * @return	boolean	<code>true</code> if the group nodes are selectabel;<br>
	 * 					<code>false</code> otherwise
	 */
	public boolean getGroupSelect();

	/**
	 * Defines a target attribute which should be inserted
	 * in all generated hyperlinks of the coulmns.<br>
	 * <b>Note:</b> The same attribute will be used in all rows!
	 *
	 * @param	target Das Target Attribut
	 */
	public void setTarget(String target);

	/**
	 * Returns the target attribute.
	 *
	 * @return	The target attribute.
	 */
	public String getTarget();

	/**
	 * Specifies the name of a property with the help of which
	 * the relevant line can generate an HTML-target attribute.<br>
	 * <b>Note:</b> Valid Java designator/label. The Row-Bean must
	 * implement a suitable property-getter method. The attribut
	 * can be defined individual within each row (In difference
	 *  to the method setTarget)
	 *
	 * @param	targetProperty	The name of the property to use
	 */
	public void setTargetProperty(String targetProperty);

	/**
	 * Returns the name of the property.
	 *
	 * @return	The name of the property.
	 */
	public String getTargetProperty();

	/**
	 * Specifies the name of a property with the help of which
	 * the relevant line can generate an HTML-text attribute.
	 * <b>Note:</b> Valid Java designator/label. The Row-Bean
	 * must implement a suitable property-getter method.
	 * The attribut can be defined individual within each row
	 *
	 * @param	tooltipProperty	Das Target Attribut
	 */
	public void setTooltipProperty(String tooltipProperty);

	/**
	 * Returns the name of the property.
	 *
	 * @return	The name of the property.
	 */
	public String getTooltipProperty();

	/**
	 * Returns the the selection mode of the list.
	 * <ul>
	 *   <li>NONE = not selectable.</li>
	 *   <li>SINGLE = single selection.</li>
	 *   <li>MULTIPLE = multiole selection.</li>
	 * </ul>
	 * @return	SelectMode
	 */
	public SelectMode getSelectMode();

	/**
	 * Specifies the selection mode of the list.<br>
	 * This is only used if a Check column is to be displayed here as well.
	 *
	 * @param	mode	The select mode
	 */
	public void setSelectMode(SelectMode mode);

	/**
	 * The HTML-id attribute of the element can
	 * be specified with this attribute
	 *
	 * @param	id	The HTML-id attribute
	 */
	public void setStyleId(String id);

	/**
	 * Returns the HTML-id attribute
	 *
	 * @return	The HTML-id attribute
	 */
	public String getStyleId();

	/**
	 * The HTML-class attribute of the element can
	 * be specified with this attribute.
	 *
	 * @param	styleClass	The HTML-class attribute
	 */
	public void setStyleClass(String styleClass);

	/**
	 * Returns the HTML-class attribute
	 *
	 * @return	The HTML-class attribute
	 */
	public String getStyleClass();

	/**
	 * An HTML-style can be directly specified
	 * with this attribute.
	 *
	 * @param	style	The HTML-Style
	 */
	public void setStyle(String style);

	/**
	 * Returns the HTML-style
	 * @return	The HTML-style
	 */
	public String getStyle();

	/**
	 * Liefert den Namen der Eigenschaft, welche die Spaltendaten
	 * ermittelt, zurück
	 *
	 * @return Property
	 */
	public String getEnableProperty();

	/**
	 * Setzt den Namen der Eigenschaft mit deren Hilfe der Spaltenwert
	 * bestimmt werden kann.
	 *
	 * @param property Der Name der Eigenschaft
	 */
	public void setEnableProperty(String property);
}