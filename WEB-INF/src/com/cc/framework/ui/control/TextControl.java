/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/control/TextControl.java,v 1.14 2005/09/27 14:06:22 P001002 Exp $
 * $Revision: 1.14 $
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

package com.cc.framework.ui.control;

import com.cc.framework.convert.Converter;
import com.cc.framework.ui.model.ControlDesignModel;
import com.cc.framework.ui.model.DataModel;
import com.cc.framework.ui.model.InputFieldType;
import com.cc.framework.ui.model.StateModel;
import com.cc.framework.ui.model.TextDataModel;
import com.cc.framework.ui.model.TextDesignModel;
import com.cc.framework.ui.model.TextStateModel;

/**
 * TextControl
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision: 1.14 $
 * @since 1.0
 */
public class TextControl extends Control implements TextStateModel,
		TextDataModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 2575829253933331102L;

	/**
	 * Designmodel
	 */
	private TextDesignModel designModel = null;

	/**
	 * (Display-) Value
	 */
	private Object value = null;

	/**
	 * Constructor
	 * 
	 * @param designModel
	 *            TextDesignModel
	 */
	public TextControl(TextDesignModel designModel) {
		super();

		this.designModel = designModel;
	}

	/**
	 * @see com.cc.framework.ui.control.Control#getDataModel()
	 */
	public DataModel getDataModel() {
		return null;
	}

	/**
	 * @see com.cc.framework.ui.control.Control#getDesignModel()
	 */
	public ControlDesignModel getDesignModel() {
		return designModel;
	}

	/**
	 * @see com.cc.framework.ui.control.Control#getStateModel()
	 */
	public StateModel getStateModel() {
		return this;
	}

	/**
	 * @see com.cc.framework.ui.model.StateModel#reset()
	 */
	public void reset() {
	}

	/**
	 * Returns the Size (Numer of visible Characters)
	 * 
	 * @return int
	 */
	public int getSize() {
		return designModel.getSize();
	}

	/**
	 * returns the maximal Nuber of Characters, which can be inserted into the
	 * Control
	 * 
	 * @return int
	 */
	public int getMaxLength() {
		return designModel.getMaxLength();
	}

	/**
	 * @see com.cc.framework.ui.model.TextDataModel#getValue()
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * @see com.cc.framework.ui.model.TextDataModel#setValue(java.lang.Object)
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	/**
	 * Returns the Type
	 * 
	 * @return InputFieldType
	 */
	public InputFieldType getInputType() {
		return designModel.getInputType();
	}

	/**
	 * Returns if the filter is activated (default=true). This means that all
	 * Strings which should be displayed in the HTML page are html encoded
	 * 
	 * @return <code>true</code> if string will be html encoded;
	 *         <code>false</code> otherwise
	 */
	public boolean filter() {
		return designModel.filter();
	}

	/**
	 * Returns the the Converter that should be used to convert Java Objects
	 * into their localized String representation. If no converter is specified
	 * the framework will use a default Converter that matches the beans data
	 * type
	 * 
	 * @return Converter or <code>null</code for a default
	 * 				converter
	 */
	public Converter getConverter() {
		return designModel.getConverter();
	}
}