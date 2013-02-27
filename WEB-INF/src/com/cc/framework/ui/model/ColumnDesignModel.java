/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/ColumnDesignModel.java,v 1.31 2005/07/28 19:41:39 P001002 Exp $
 * $Revision: 1.31 $
 * $Date: 2005/07/28 19:41:39 $
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

import com.cc.framework.convert.Converter;
import com.cc.framework.security.Permission;
import com.cc.framework.ui.AlignmentType;
import com.cc.framework.ui.ImageMap;
import com.cc.framework.ui.RunAt;
import com.cc.framework.ui.model.value.DeferredEnvironment;

/**
 * Defines the visual properties for a column of a list
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision: 1.31 $
 * @since 1.0
 */
public interface ColumnDesignModel extends DesignModel, ClientHandler,
		AccessControlled {

	/**
	 * Sets the EL Environment to evaluate EL Expressions in the context of the
	 * current row bean
	 * 
	 * @param env
	 *            Environment
	 */
	public void setEnvironment(DeferredEnvironment env);

	/**
	 * Retrieves the EL Environment to evaluate EL Expressions in the context of
	 * the current row bean
	 * 
	 * @return Environment or <code>null</code>
	 */
	public DeferredEnvironment getEnvironment();

	/**
	 * Defines if the Control works with or without server roundtrips.
	 * 
	 * @param location
	 *            RunAt
	 */
	public void setRunAt(RunAt location);

	/**
	 * Returns if the Control works with or without server roundtrips.
	 * 
	 * @return RunAt
	 */
	public RunAt getRunAt();

	/**
	 * Returns the row bean type
	 * 
	 * @return string
	 */
	public String getType();

	/**
	 * Sets the type of the row bean
	 * 
	 * @param type
	 *            The type of the row bean
	 */
	public void setType(String type);

	/**
	 * Returns the title of the column
	 * 
	 * @return string
	 */
	public String getTitle();

	/**
	 * Sets the titel of the column
	 * 
	 * @param title
	 *            The titel of the column
	 */
	public void setTitle(String title);

	/**
	 * Returns the columns header design model
	 * 
	 * @return Design model
	 */
	public ColumnHeaderDesignModel getHeader();

	/**
	 * Sets the columns header design model
	 * 
	 * @param header
	 *            Design model
	 */
	public void setHeader(ColumnHeaderDesignModel header);

	/**
	 * Returns the width of the control
	 * 
	 * @return String The width.
	 */
	public String getWidth();

	/**
	 * Sets the width of the column
	 * 
	 * @param width
	 *            The width of the control
	 */
	public void setWidth(int width);

	/**
	 * Sets the width of the column
	 * 
	 * @param width
	 *            The width of the control
	 */
	public void setWidth(String width);

	/**
	 * Returns the maximal number of input characters which can be inserted into
	 * the text field.
	 * 
	 * @return Maximum number of input characters to accept
	 */
	public int getMaxLength();

	/**
	 * Sets the maximal number of input characters which can be inserted into
	 * the text field.
	 * 
	 * @param max
	 *            Maximum number of input characters to accept
	 */
	public void setMaxLength(int max);

	/**
	 * Sets the maximal number of input characters which can be inserted into
	 * the text field.
	 * 
	 * @param max
	 *            Maximum number of input characters to accept
	 */
	public void setMaxLength(String max);

	/**
	 * Returns the name of the property which holds the data for the column
	 * 
	 * @return String The name of the property
	 */
	public String getProperty();

	/**
	 * Sets the name of the property which holds the data for the column
	 * 
	 * @param property
	 *            The name of the property.
	 */
	public void setProperty(String property);

	/**
	 * Liefert den Namen der Eigenschaft, welche die Spaltendaten ermittelt,
	 * zurück
	 * 
	 * @return Property
	 */
	public String getEnableProperty();

	/**
	 * Setzt den Namen der Eigenschaft mit deren Hilfe der Spaltenwert bestimmt
	 * werden kann.
	 * 
	 * @param property
	 *            Der Name der Eigenschaft
	 */
	public void setEnableProperty(String property);

	/**
	 * Returns the Property which should be used to identify an Image in an
	 * ImageMap
	 * 
	 * @return String
	 */
	public String getImageProperty();

	/**
	 * Sets the Property which should be used to identify an Image in an
	 * ImageMap
	 * 
	 * @param property
	 *            Property
	 */
	public void setImageProperty(String property);

	/**
	 * Assigns an ImageMap to the Column
	 * 
	 * @param map
	 *            ImageMap
	 */
	public void setImageMap(ImageMap map);

	/**
	 * Returns the ImageMap
	 * 
	 * @return ImageMap
	 */
	public ImageMap getImageMap();

	/**
	 * Returns the alignment for the column
	 * 
	 * @return the alignment for the column
	 */
	public AlignmentType getAlignment();

	/**
	 * Sets the alignment for the column
	 * 
	 * @param alignment
	 *            The alignment
	 */
	public void setAlignment(AlignmentType alignment);

	/**
	 * Sets the alignment for the column
	 * 
	 * @param alignment
	 *            The alignment
	 */
	public void setAlignment(String alignment);

	/**
	 * Returns true if the column can be sorted. The sort order must be
	 * specified in the state model.
	 * 
	 * @return <code>true</code> if the column is sortable, <code>false</code>
	 *         otherwise.
	 */
	public boolean isSortable();

	/**
	 * Set to <code>true</code> if the column should be sortable.
	 * 
	 * @param sortable
	 *            <code>true</code> if the column should be sortable.
	 */
	public void setSortable(boolean sortable);

	/**
	 * Identifier to be assigned to this column.
	 * 
	 * @param id
	 *            The identifier to be assigned to this column.
	 */
	public void setStyleId(String id);

	/**
	 * Returns the Identifier assigned to this column.
	 * 
	 * @return String
	 */
	public String getStyleId();

	/**
	 * Sets the CSS stylesheet class
	 * 
	 * @param styleClass
	 *            The CSS stylesheet class
	 */
	public void setStyleClass(String styleClass);

	/**
	 * Returns the CSS stylesheet class
	 * 
	 * @return String the CSS stylesheet class
	 */
	public String getStyleClass();

	/**
	 * Sets the CSS styles to be applied to this column.
	 * 
	 * @param style
	 *            The CSS styles for this column.
	 */
	public void setStyle(String style);

	/**
	 * Returns the CSS styles for this column.
	 * 
	 * @return String the CSS styles for this column.
	 */
	public String getStyle();

	/**
	 * Returns if the filter is activated (default=true). This means that all
	 * Strings which should be displayed in the HTML page are html encoded
	 * 
	 * @return <code>true</code> if string will be html encoded;
	 *         <code>false</code> otherwise
	 */
	public boolean filter();

	/**
	 * Activates the html encoding (filter). Default is true. This means that
	 * all Strings which should be displayed in the HTML page will be html
	 * encoded.
	 * 
	 * @param filter
	 *            <code>true</code> if strings should be html encoded; false
	 *            otherwise
	 */
	public void setFilter(boolean filter);

	/**
	 * Activates the html encoding (filter). Default is true. This means that
	 * all Strings which should be displayed in the HTML page will be html
	 * encoded.
	 * 
	 * @param filter
	 *            <code>true</code> if strings should be html encoded; false
	 *            otherwise
	 */
	public void setFilter(String filter);

	/**
	 * Sets the target where the document referenced will appear. (like _blank,
	 * _parent, ...). If you use this method each hyperlinks in each row will
	 * use the same target value. If you want to use different targets you have
	 * to use a property which gets the target from your datamodel. Therefore
	 * you can specify the property with the setTargetProperty() method.
	 * 
	 * @param target
	 *            The target where the document referenced will appear
	 */
	public void setTarget(String target);

	/**
	 * Returns where the document referenced will appear.
	 * 
	 * @return String
	 */
	public String getTarget();

	/**
	 * Sets the name of the propert where the value for the target attribute can
	 * be found in the datamodel. So each row can have a different target value.
	 * 
	 * @param targetProperty
	 *            The target attribut to set
	 */
	public void setTargetProperty(String targetProperty);

	/**
	 * Returns the target property for the column.
	 * 
	 * @return String
	 */
	public String getTargetProperty();

	/**
	 * Sets the static tooltip text.
	 * 
	 * @param tooltip
	 *            Tooltip text
	 */
	public void setTooltip(String tooltip);

	/**
	 * Returns the static tooltip text
	 * 
	 * @return String
	 */
	public String getTooltip();

	/**
	 * Sets the property which should be used to read the text for a tooltip.
	 * This means that the tooltip is accessed using the getter Method specified
	 * by the argument.
	 * 
	 * @param tooltipProperty
	 *            Das Target Attribut
	 */
	public void setTooltipProperty(String tooltipProperty);

	/**
	 * Returns the property used to access the tooltip.
	 * 
	 * @return String
	 */
	public String getTooltipProperty();

	/**
	 * Sets a list with permissions needed to access this object. The list is
	 * seperated by ';'
	 * 
	 * @param permission
	 *            Permission
	 */
	public void setPermission(Permission permission);

	/**
	 * Sets a list with permissions needed to access this object. The list is
	 * seperated by ';'
	 * 
	 * @param permission
	 *            Permission
	 */
	public void setPermission(String permission);

	/**
	 * Returns the edit state of the column
	 * 
	 * @return Returns <code>true</code> if this column is editable
	 */
	public boolean isEditable();

	/**
	 * Retrieves the property which should be used to make the column editable
	 * or not
	 * 
	 * @return the property name or <code>null</code>
	 */
	public String getEditableProperty();

	/**
	 * Sets the edit state of the column
	 * 
	 * @param editable
	 *            <code>true</code> to set the column editable
	 */
	public void setEditable(boolean editable);

	/**
	 * Sets the edit state of the column
	 * 
	 * @param editable
	 *            <code>true</code> to set the column editable
	 */
	public void setEditable(String editable);

	/**
	 * Sets the property which should be used to make the column editable or not
	 * 
	 * @param property
	 *            the property name
	 */
	public void setEditableProperty(String property);

	/**
	 * Adds a new design rule to the column
	 * 
	 * @param rule
	 *            Design rule
	 */
	public void addDesignRule(DesignRule rule);

	/**
	 * Returns the design rules for this column
	 * 
	 * @return Array of design rules
	 */
	public DesignRule[] getDesignRules();

	/**
	 * Directs the framework to include a transaction token (if any) in all
	 * generated hyperlinks for this column. The Transaction token is used to
	 * track form re-submissions.
	 * 
	 * @param transaction
	 *            include transaction token
	 */
	public void setTransaction(boolean transaction);

	/**
	 * Checks if the framework should include a transaction token (if any) in
	 * all generated hyperlinks for this column. The Transaction token is used
	 * to track form re-submissions.
	 * 
	 * @return <code>true</code> if the transaction token should be generated
	 */
	public boolean getTransaction();

	/**
	 * Returns the the Converter that should be used to convert Java Objects
	 * into their localized String representation. If no converter is specified
	 * the framework will use a default Converter that matches the beans data
	 * type of the row beans column property.
	 * <p>
	 * The Converter is set for all rows of the columns. It is not possible to
	 * set individual converters for each row in a column!
	 * 
	 * @return Converter or <code>null</code for a default
	 * 				converter
	 */
	public Converter getConverter();

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
	 *            Converters instance
	 */
	public void setConverter(Converter converter);
}