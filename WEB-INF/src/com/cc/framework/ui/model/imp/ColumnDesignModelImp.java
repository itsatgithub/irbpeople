/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/ColumnDesignModelImp.java,v 1.34 2005/09/27 14:06:22 P001002 Exp $
 * $Revision: 1.34 $
 * $Date: 2005/09/27 14:06:22 $
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

package com.cc.framework.ui.model.imp;

import java.io.Serializable;
import java.util.ArrayList;

import com.cc.framework.convert.Converter;
import com.cc.framework.security.Permission;
import com.cc.framework.security.Principal;
import com.cc.framework.security.StaticPermission;
import com.cc.framework.ui.AlignmentType;
import com.cc.framework.ui.ImageMap;
import com.cc.framework.ui.RunAt;
import com.cc.framework.ui.model.ClientEvent;
import com.cc.framework.ui.model.ColumnDesignModel;
import com.cc.framework.ui.model.ColumnHeaderDesignModel;
import com.cc.framework.ui.model.DesignRule;
import com.cc.framework.ui.model.value.DeferredEnvironment;
import com.cc.framework.ui.model.value.DeferredValue;

/**
 * Designmodel for columns
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision: 1.34 $
 * @since 1.0
 */
public abstract class ColumnDesignModelImp extends ClientHandlerImp implements
		ColumnDesignModel, Serializable {

	/**
	 * Expression Language Environment. This object is needed when the property
	 * values of the column are retrieved in the context of a row bean.
	 * 
	 * Properties might change from row to row because they can contain dynamic
	 * expressions
	 */
	private transient DeferredEnvironment env = null;

	/**
	 * The columns property class name
	 */
	private String type = null;

	/**
	 * The columns header
	 */
	private ColumnHeaderDesignModel header = new ColumnHeaderDesignModelImp();

	/**
	 * Indicates if the control works with or without server roundtrips
	 */
	private RunAt runat = RunAt.DEFAULT;

	/**
	 * Design rules
	 */
	private ArrayList designRules = null;

	/**
	 * An ImageMap for label images
	 */
	private ImageMap imagemap = null;

	/**
	 * Indicates if this column can be sorted
	 */
	private boolean sortable = false;

	/**
	 * Directs the framework to include a transaction token (if any) in all
	 * generated hyperlinks for this column. The Transaction token is used to
	 * track form re-submissions.
	 */
	private boolean transaction = false;

	// ------------------------------------
	// Dynamic Attributes
	// ------------------------------------

	/**
	 * The name of the property for the column which indicates which data to
	 * show.
	 */
	private DeferredValue property = null;

	/**
	 * Field enableProperty
	 */
	private DeferredValue enableProperty = null;

	/**
	 * Indicates if this column is editable
	 */
	private DeferredValue editable = DeferredValue.FALSE;

	/**
	 * The property which should be used to make the column editable or not
	 */
	private DeferredValue editableProperty = null;

	/**
	 * Name of the property which returns a expression/string used to identify
	 * an image in the ImageMap.
	 */
	private DeferredValue imageProperty = null;

	/**
	 * Permissons to access this Column
	 */
	private DeferredValue permission = new DeferredValue(
			StaticPermission.GRANTED);

	/**
	 * The converter that should be used to convert Java Objects into their
	 * localized String representation. If no converter is specified the
	 * framework will use a default Converter that matches the row beans column
	 * property.
	 */
	private Converter converter = null;

	/**
	 * The width of the column
	 */
	private DeferredValue width = null;

	/**
	 * Maximum number of visible characters
	 */
	private DeferredValue maxLength = DeferredValue.NEG;

	/**
	 * The alignment of the column
	 */
	private DeferredValue alignment = new DeferredValue(AlignmentType.LEFT);

	/**
	 * Identifier to be assigned to this HTML element
	 */
	private DeferredValue styleId = null;

	/**
	 * CSS stylesheet class to be applied to this HTML element
	 */
	private DeferredValue styleClass = null;

	/**
	 * CSS styles to be applied to this HTML element
	 */
	private DeferredValue style = null;

	/**
	 * Specifies if all String should be converted into there HTML
	 * representation
	 */
	private DeferredValue filter = DeferredValue.TRUE;

	/**
	 * Target Attribut
	 */
	private DeferredValue target = null;

	/**
	 * Name of the Property to acquire the Target-Atribute
	 */
	private DeferredValue targetProperty = null;

	/**
	 * Static tooltip Attribut
	 */
	private DeferredValue tooltip = null;

	/**
	 * Name of the Property to acquire the Tooltip-Atribute
	 */
	private DeferredValue tooltipProperty = null;

	// ------------------------------------
	// methods
	// ------------------------------------

	/**
	 * Constructor for ColumnDesignModelImp
	 */
	public ColumnDesignModelImp() {
		super();
	}

	/**
	 * Sets the EL Environment to evaluate EL Expressions in the context of the
	 * current row bean
	 * 
	 * @param env
	 *            Environment
	 */
	public void setEnvironment(DeferredEnvironment env) {
		this.env = env;
	}

	/**
	 * retrieves the current EL Environment for the current row
	 * 
	 * @return Environment oder <code>null</code>
	 */
	public DeferredEnvironment getEnvironment() {
		return env;
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#isEditable()
	 */
	public boolean isEditable() {
		return editable.toBoolean(env);
	}

	/**
	 * Sets the column editable
	 * 
	 * @see com.cc.framework.ui.model.ColumnDesignModel#setEditable(boolean)
	 */
	public void setEditable(boolean editable) {
		this.editable = new DeferredValue(editable);
	}

	/**
	 * Sets the column editable
	 * 
	 * @see com.cc.framework.ui.model.ColumnDesignModel#setEditable(java.lang.String)
	 */
	public void setEditable(String editable) {
		this.editable = DeferredValue.parseBoolean(editable);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#getEditableProperty()
	 */
	public String getEditableProperty() {
		return DeferredValue.toString(editableProperty, env);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#setEditableProperty(java.lang.String)
	 */
	public void setEditableProperty(String property) {
		this.editableProperty = new DeferredValue(property);
	}

	/**
	 * Returns the columns property class name
	 * 
	 * @return string
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type of the row bean
	 * 
	 * @param type
	 *            The type of the row bean
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#getAlignment()
	 */
	public AlignmentType getAlignment() {
		return DeferredValue.toAlignment(alignment, env);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#setAlignment(AlignmentType)
	 */
	public void setAlignment(AlignmentType alignment) {
		this.alignment = new DeferredValue(alignment);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#setAlignment(java.lang.String)
	 */
	public void setAlignment(String alignment) {
		this.alignment = DeferredValue.parseAlignment(alignment);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#getWidth()
	 */
	public String getWidth() {
		return DeferredValue.toString(width, env);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#setWidth(int)
	 */
	public void setWidth(int width) {
		this.width = new DeferredValue(width);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#setWidth(String)
	 */
	public void setWidth(String width) {
		this.width = new DeferredValue(width);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#getMaxLength()
	 */
	public int getMaxLength() {
		return maxLength.toInt(env);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#setMaxLength(int)
	 */
	public void setMaxLength(int maxLength) {
		this.maxLength = new DeferredValue(maxLength);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#setMaxLength(java.lang.String)
	 */
	public void setMaxLength(String maxLength) {
		this.maxLength = DeferredValue.parseInt(maxLength);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#getProperty()
	 */
	public String getProperty() {
		return DeferredValue.toString(property, env);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#setProperty(String)
	 */
	public void setProperty(String property) {
		this.property = new DeferredValue(property);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#isSortable()
	 */
	public boolean isSortable() {
		return sortable;
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#setSortable(boolean)
	 */
	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#getEnableProperty()
	 */
	public String getEnableProperty() {
		return DeferredValue.toString(enableProperty, env);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#setEnableProperty(String)
	 */
	public void setEnableProperty(String property) {
		this.enableProperty = new DeferredValue(property);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#setStyleId(String id)
	 */
	public void setStyleId(String id) {
		this.styleId = new DeferredValue(id);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#getStyleId()
	 */
	public String getStyleId() {
		return DeferredValue.toString(styleId, env);
	}

	/**
	 * @see com.cc.framework.ui.model.AccessControlled#getPermission()
	 */
	public Permission getPermission() {
		return DeferredValue.toPermission(permission, env);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#setPermission(com.cc.framework.security.Permission)
	 */
	public void setPermission(Permission permission) {
		this.permission = new DeferredValue(permission);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#setPermission(java.lang.String)
	 */
	public void setPermission(String permission) {
		this.permission = DeferredValue.parsePermission(permission);
	}

	/**
	 * @see com.cc.framework.ui.model.AccessControlled#show(Principal)
	 */
	public boolean show(Principal principal) {
		Permission p = getPermission();

		return (p == null) || p.isGranted(principal);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#filter()
	 */
	public boolean filter() {
		return filter.toBoolean(env);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#setFilter(boolean)
	 */
	public void setFilter(boolean filter) {
		this.filter = new DeferredValue(filter);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#setFilter(java.lang.String)
	 */
	public void setFilter(String filter) {
		this.filter = DeferredValue.parseBoolean(filter);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#setStyleClass(java.lang.String)
	 */
	public void setStyleClass(String styleClass) {
		this.styleClass = new DeferredValue(styleClass);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#getStyleClass()
	 */
	public String getStyleClass() {
		return DeferredValue.toString(styleClass, env);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#setStyle(java.lang.String)
	 */
	public void setStyle(String style) {
		this.style = new DeferredValue(style);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#getStyle()
	 */
	public String getStyle() {
		return DeferredValue.toString(style, env);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#setRunAt(RunAt)
	 */
	public void setRunAt(RunAt location) {
		this.runat = location;
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#getRunAt()
	 */
	public RunAt getRunAt() {
		return runat;
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#setTarget(String)
	 */
	public void setTarget(String target) {
		this.target = new DeferredValue(target);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#getTarget()
	 */
	public String getTarget() {
		return DeferredValue.toString(target, env);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#setTargetProperty(String)
	 */
	public void setTargetProperty(String targetProperty) {
		this.targetProperty = new DeferredValue(targetProperty);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#getTargetProperty()
	 */
	public String getTargetProperty() {
		return DeferredValue.toString(targetProperty, env);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#getImageProperty()
	 */
	public String getImageProperty() {
		return DeferredValue.toString(imageProperty, env);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#setImageProperty(java.lang.String)
	 */
	public void setImageProperty(String property) {
		this.imageProperty = new DeferredValue(property);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#setImageMap(com.cc.framework.ui.ImageMap)
	 */
	public void setImageMap(ImageMap map) {
		this.imagemap = map;
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#getImageMap()
	 */
	public ImageMap getImageMap() {
		return imagemap;
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#getTooltip()
	 */
	public String getTooltip() {
		return DeferredValue.toString(tooltip, env);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#setTooltip(java.lang.String)
	 */
	public void setTooltip(String tooltip) {
		this.tooltip = new DeferredValue(tooltip);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#getTooltipProperty()
	 */
	public String getTooltipProperty() {
		return DeferredValue.toString(tooltipProperty, env);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#setTooltipProperty(java.lang.String)
	 */
	public void setTooltipProperty(String tooltipProperty) {
		this.tooltipProperty = new DeferredValue(tooltipProperty);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#getHeader()
	 */
	public ColumnHeaderDesignModel getHeader() {
		return header;
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#setHeader(com.cc.framework.ui.model.ColumnHeaderDesignModel)
	 */
	public void setHeader(ColumnHeaderDesignModel header) {
		if (header.getTitle() == null) {
			header.setTitle(getTitle());
		}

		this.header = header;
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#getTitle()
	 */
	public String getTitle() {
		if (header == null) {
			return null;
		} else {
			return header.getTitle();
		}
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#setTitle(String)
	 */
	public void setTitle(String title) {
		if (header != null) {
			header.setTitle(title);
		}
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#addDesignRule(com.cc.framework.ui.model.DesignRule)
	 */
	public void addDesignRule(DesignRule rule) {
		if (designRules == null) {
			designRules = new ArrayList();
		}

		designRules.add(rule);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#getDesignRules()
	 */
	public DesignRule[] getDesignRules() {
		if (designRules == null) {
			return new DesignRule[0];
		} else {
			return (DesignRule[]) designRules
					.toArray(new DesignRule[designRules.size()]);
		}
	}

	/**
	 * @see com.cc.framework.ui.model.ClientHandler#getHandler(com.cc.framework.ui.model.ClientEvent)
	 */
	public String getHandler(ClientEvent event) {
		// Translate any deferred expressions in the client handler
		return DeferredValue.evaluateToString(super.getHandler(event), env);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#getTransaction()
	 */
	public boolean getTransaction() {
		return transaction;
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#setTransaction(boolean)
	 */
	public void setTransaction(boolean transaction) {
		this.transaction = transaction;
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#getConverter()
	 */
	public Converter getConverter() {
		return converter;
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnDesignModel#setConverter(com.cc.framework.convert.Converter)
	 */
	public void setConverter(Converter converter) {
		this.converter = converter;
	}
}