/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/ColumnSelectDesignModelImp.java,v 1.16 2005/09/12 10:33:37 P001002 Exp $
 * $Revision: 1.16 $
 * $Date: 2005/09/12 10:33:37 $
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

import com.cc.framework.ui.model.ColumnSelectDesignModel;
import com.cc.framework.ui.model.value.DeferredValue;

/**
 * Design Model implementation for the select column
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.16 $
 */
public class ColumnSelectDesignModelImp extends ColumnDesignModelImp implements ColumnSelectDesignModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 4756480158906814942L;

	/**
	 * Indicates that multiple selections is allowed.
	 */
	private boolean multiple				= false;

	/**
	 * The property for the key
	 */
	private DeferredValue keyProperty		= null;

	/**
	 * The property for the label
	 */
	private DeferredValue labelProperty		= null;

	/**
	 * Property for retrieving a individual option list
	 * for each row bean
	 */
	private DeferredValue optionsProperty	= null;

	/**
	 * A option list that is shared among all row beans
	 */
	private Object sharedOptions			= null;

	/**
	 * An optional text to prompt the
	 * user for selecting an option of the
	 * list. Something like "<please select>"
	 */
	private DeferredValue empty				= null;

	/**
	 * Number of character positions to allocate.
	 */
	private DeferredValue size				= DeferredValue.NEG;

	/**
	 * Specifies if all String should be treated
	 * as Resource Identifiers and must be
	 * localized
	 */
	private boolean localize				= false;

	/**
	 * @see com.cc.framework.ui.model.ColumnSelectDesignModel#getEmpty()
	 */
	public String getEmpty() {
		return DeferredValue.toString(empty, getEnvironment());
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnSelectDesignModel#getOptionsProperty()
	 */
	public String getOptionsProperty() {
		return DeferredValue.toString(optionsProperty, getEnvironment());
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnSelectDesignModel#setEmpty(java.lang.String)
	 */
	public void setEmpty(String empty) {
		this.empty = new DeferredValue(empty);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnSelectDesignModel#setOptionsProperty(java.lang.String)
	 */
	public void setOptionsProperty(String property) {
		this.optionsProperty = new DeferredValue(property);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnSelectDesignModel#setSharedOptions(java.lang.Object)
	 */
	public void setSharedOptions(Object elements) {
		this.sharedOptions = elements;
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnSelectDesignModel#getSharedOptions()
	 */
	public Object getSharedOptions() {
		return sharedOptions;
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnSelectDesignModel#getKeyProperty()
	 */
	public String getKeyProperty() {
		return DeferredValue.toString(keyProperty, getEnvironment());
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnSelectDesignModel#getLabelProperty()
	 */
	public String getLabelProperty() {
		return DeferredValue.toString(labelProperty, getEnvironment());
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnSelectDesignModel#setKeyProperty(java.lang.String)
	 */
	public void setKeyProperty(String keyProperty) {
		this.keyProperty = new DeferredValue(keyProperty);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnSelectDesignModel#setLabelProperty(java.lang.String)
	 */
	public void setLabelProperty(String labelProperty) {
		this.labelProperty = new DeferredValue(labelProperty);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnSelectDesignModel#getSize()
	 */
	public int getSize() {
		return size.toInt(getEnvironment());
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnSelectDesignModel#isMultiple()
	 */
	public boolean isMultiple() {
		return multiple;
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnSelectDesignModel#setMultiple(boolean)
	 */
	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnSelectDesignModel#setSize(int)
	 */
	public void setSize(int size) {
		this.size = new DeferredValue(size);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnSelectDesignModel#setSize(java.lang.String)
	 */
	public void setSize(String size) {
		this.size = DeferredValue.parseInt(size);
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