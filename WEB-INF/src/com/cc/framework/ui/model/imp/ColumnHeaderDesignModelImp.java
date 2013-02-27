/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/ColumnHeaderDesignModelImp.java,v 1.9 2005/07/08 14:19:42 P001002 Exp $
 * $Revision: 1.9 $
 * $Date: 2005/07/08 14:19:42 $
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

import com.cc.framework.ui.AlignmentType;
import com.cc.framework.ui.ImageMap;
import com.cc.framework.ui.model.ColumnHeaderDesignModel;

/**
 * Implementation of a header design model
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.9 $
 */
public class ColumnHeaderDesignModelImp extends ClientHandlerImp implements ColumnHeaderDesignModel,  Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1414460600170443752L;

	/**
	 * The column title
	 */
	private String title				= null;

	/**
	 * Indicates wether the title is set
	 * within the body or as an attribute
	 */
	private boolean bodyInclude			= false;

	/**
	 * The alignment of the columnheader
	 */
	private AlignmentType alignment		= null;

	/**
	 * Maximum number of visible characters
	 */
	private int maxLength				= -1;

	/**
	 * Identifier to be assigned to this HTML element
	 */
	private String styleId				= null;

	/**
	 * CSS stylesheet class to be applied to this HTML element
	 */
	private String styleClass			= null;

	/**
	 * CSS styles to be applied to this HTML element
	 */
	private String style				= null;

	/**
	 * Specifies if all String should be converted
	 * into there HTML representation
	 */
	private boolean filter				= true;

	/**
	 * Specifies if localization is enabled
	 */
	private boolean localize			= true;

	/**
	 * Static tooltip Attribut
	 */
	private String tooltip				= null;

	/**
	 * Image reference
	 */
	private String imageref				= null;

	/**
	 * An ImageMap for label images
	 */
	private ImageMap imagemap			= null;

	/**
	 * Construktor
	 */
	public ColumnHeaderDesignModelImp() {
		super();
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnHeaderDesignModel#showHeader()
	 */
	public boolean showHeader() {
		return (title != null) || (tooltip != null) || (imageref != null);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnHeaderDesignModel#getTitle()
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnHeaderDesignModel#setTitle(java.lang.String)
	 */
	public void setTitle(String title) {
		this.title	= title;
	}

	/**
	 * @return		returns <true> if the column header
	 * 				should be localized
	 */
	public boolean localize() {
		return localize;
	}

	/**
	 * @param		localize Enables or disables the localization
	 * 				of the column header
	 */
	public void setLocalize(boolean localize) {
		this.localize = localize;
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnHeaderDesignModel#filter()
	 */
	public boolean filter() {
		return filter;
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnHeaderDesignModel#setFilter(boolean)
	 */
	public void setFilter(boolean filter) {
		this.filter = filter;
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnHeaderDesignModel#setStyleClass(java.lang.String)
	 */
	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnHeaderDesignModel#getStyleClass()
	 */
	public String getStyleClass() {
		return styleClass;
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnHeaderDesignModel#setStyle(String)
	 */
	public void setStyle(String style) {
		this.style = style;
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnHeaderDesignModel#getStyle()
	 */
	public String getStyle() {
		return style;
	}

	/**
	 * @see  com.cc.framework.ui.model.ColumnHeaderDesignModel#setStyleId(String id)
	 */
	public void setStyleId(String id) {
		this.styleId	= id;
	}

	/**
	 * @see  com.cc.framework.ui.model.ColumnHeaderDesignModel#getStyleId()
	 */
	public String getStyleId() {
		return styleId;
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnHeaderDesignModel#getTooltip()
	 */
	public String getTooltip() {
		return tooltip;
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnHeaderDesignModel#setTooltip(java.lang.String)
	 */
	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnHeaderDesignModel#getAlignment()
	 */
	public AlignmentType getAlignment() {
		return alignment;
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnHeaderDesignModel#getImageMap()
	 */
	public ImageMap getImageMap() {
		return imagemap;
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnHeaderDesignModel#getImageRef()
	 */
	public String getImageRef() {
		return imageref;
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnHeaderDesignModel#getMaxLength()
	 */
	public int getMaxLength() {
		return maxLength;
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnHeaderDesignModel#setAlignment(com.cc.framework.ui.AlignmentType)
	 */
	public void setAlignment(AlignmentType alignment) {
		this.alignment = alignment;
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnHeaderDesignModel#setImageMap(com.cc.framework.ui.ImageMap)
	 */
	public void setImageMap(ImageMap map) {
		this.imagemap = map;
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnHeaderDesignModel#setImageRef(java.lang.String)
	 */
	public void setImageRef(String ref) {
		this.imageref = ref;
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnHeaderDesignModel#setMaxLength(int)
	 */
	public void setMaxLength(int max) {
		this.maxLength = max;
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnHeaderDesignModel#isBodyInclude()
	 */
	public boolean isBodyInclude() {
		return bodyInclude;
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnHeaderDesignModel#setBodyInclude(boolean)
	 */
	public void setBodyInclude(boolean include) {
		this.bodyInclude = include;
	}
}