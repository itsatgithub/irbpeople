/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/OptionListDesignModelImp.java,v 1.14 2005/11/13 12:45:37 P001002 Exp $
 * $Revision: 1.14 $
 * $Date: 2005/11/13 12:45:37 $
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

import com.cc.framework.ui.model.OptionListDesignModel;

/**
 * Designmodel for Optionslists
 *
 * @author	   <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version	   $Revision: 1.14 $
 * @since      1.0
 */
public class OptionListDesignModelImp implements OptionListDesignModel, Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -1968209474461694102L;

	/**
	 * Name of the property for the options list
	 */
	private String property			= null;

	/**
	 * The property for the key
	 */
	private String keyProperty		= null;

	/**
	 * The property for the label
	 */
	private String labelProperty	= null;

	/**
	 * The maximum number of characters
	 * that are displayed for each option element.
	 */
	private int maxLength			= -1;

	/**
	 * An optional text to prompt the
	 * user for selecting an option of the
	 * list. Something like "<please select>"
	 */
	private String empty			= null;

	/**
	 * CSS styles to be applied to this HTML element
	 */
	private String style			= null;

	/**
	 * CSS stylesheet class to be applied to this HTML element
	 */
	private String styleClass		= null;

	/**
	 * Specifies if all String should be converted
	 * into there HTML representation
	 */
	private boolean filter			= true;

	/**
	 * Specifies if all String should be treated
	 * as Resource Identifiers and must be
	 * localized
	 */
	private boolean localize		= false;

	// --------------------------------
	//             methods
	// --------------------------------

	/**
	 * Constructor for OptionListDesignModelImp
	 */
	public OptionListDesignModelImp() {
		super();
	}

	/**
	 * @see com.cc.framework.ui.model.OptionListDesignModel#getEmpty()
	 */
	public String getEmpty() {
		return empty;
	}

	/**
	 * @see com.cc.framework.ui.model.OptionListDesignModel#getKeyProperty()
	 */
	public String getKeyProperty() {
		return keyProperty;
	}

	/**
	 * @see com.cc.framework.ui.model.OptionListDesignModel#getLabelProperty()
	 */
	public String getLabelProperty() {
		return labelProperty;
	}

	/**
	 * @see com.cc.framework.ui.model.OptionListDesignModel#getProperty()
	 */
	public String getProperty() {
		return property;
	}

	/**
	 * @see com.cc.framework.ui.model.OptionListDesignModel#getStyle()
	 */
	public String getStyle() {
		return style;
	}

	/**
	 * @see com.cc.framework.ui.model.OptionListDesignModel#getStyleClass()
	 */
	public String getStyleClass() {
		return styleClass;
	}

	/**
	 * @see com.cc.framework.ui.model.OptionListDesignModel#setEmpty(java.lang.String)
	 */
	public void setEmpty(String empty) {
		this.empty = empty;
	}

	/**
	 * @see com.cc.framework.ui.model.OptionListDesignModel#setKeyProperty(java.lang.String)
	 */
	public void setKeyProperty(String keyProperty) {
		this.keyProperty = keyProperty;
	}

	/**
	 * @see com.cc.framework.ui.model.OptionListDesignModel#setLabelProperty(java.lang.String)
	 */
	public void setLabelProperty(String labelProperty) {
		this.labelProperty = labelProperty;
	}

	/**
	 * @see com.cc.framework.ui.model.OptionListDesignModel#setProperty(java.lang.String)
	 */
	public void setProperty(String property) {
		this.property = property;
	}

	/**
	 * @see com.cc.framework.ui.model.OptionListDesignModel#setStyle(java.lang.String)
	 */
	public void setStyle(String style) {
		this.style = style;
	}

	/**
	 * @see com.cc.framework.ui.model.OptionListDesignModel#setStyleClass(java.lang.String)
	 */
	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	/**
	 * @see com.cc.framework.ui.model.OptionListDesignModel#getMaxLength()
	 */
	public int getMaxLength() {
		return maxLength;
	}

	/**
	 * @see com.cc.framework.ui.model.OptionListDesignModel#setMaxLength(int)
	 */
	public void setMaxLength(int max) {
		this.maxLength = max;
	}

	/**
	 * @see com.cc.framework.ui.model.OptionListDesignModel#filter()
	 */
	public boolean filter() {
		return filter;
	}

	/**
	 * @see com.cc.framework.ui.model.OptionListDesignModel#setFilter(boolean)
	 */
	public void setFilter(boolean filter) {
		this.filter = filter;
	}

	/**
	 * @see com.cc.framework.ui.model.OptionListDesignModel#localize()
	 */
	public boolean localize() {
		return localize;
	}

	/**
	 * @see com.cc.framework.ui.model.OptionListDesignModel#setLocalize(boolean)
	 */
	public void setLocalize(boolean localize) {
		this.localize = localize;
	}
}