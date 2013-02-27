/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/control/TextareaControl.java,v 1.19 2005/09/27 14:06:22 P001002 Exp $
 * $Revision: 1.19 $
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
import com.cc.framework.ui.model.StateModel;
import com.cc.framework.ui.model.TextareaDataModel;
import com.cc.framework.ui.model.TextareaDesignModel;
import com.cc.framework.ui.model.TextareaStateModel;

/**
 * TextareaControl
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision: 1.19 $
 * @since 1.0
 */
public class TextareaControl extends Control implements TextareaStateModel,
		TextareaDataModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -4752742394881898902L;

	/**
	 * Designmodel
	 */
	private TextareaDesignModel designModel = null;

	/**
	 * Value
	 */
	private Object value = null;

	/**
	 * Constructor
	 * 
	 * @param designModel
	 *            TextareaDesignModel
	 */
	public TextareaControl(TextareaDesignModel designModel) {
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
	 * Returns the Number of Rows
	 * 
	 * @return int
	 */
	public int getColumns() {
		return designModel.getColumns();
	}

	/**
	 * Returns the Number of Columns
	 * 
	 * @return int
	 */
	public int getRows() {
		return designModel.getRows();
	}

	/**
	 * The WRAP attribute can be used to specify how to handle word-wrapping
	 * display in text input areas in forms.
	 * 
	 * @return Word Wrapping
	 */
	public String getWrap() {
		return designModel.getWrap();
	}

	/**
	 * Returns maximal length for the input value
	 * 
	 * @return int
	 */
	public int getMaxLength() {
		return designModel.getMaxLength();
	}

	/**
	 * Returns the readonly flag
	 * 
	 * @return True if the textarea is set to readonly
	 */
	public boolean isReadonly() {
		return designModel.isReadonly();
	}

	/**
	 * Returns the value
	 * 
	 * @return Value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * @see com.cc.framework.ui.model.TextareaDataModel#setValue(java.lang.Object)
	 */
	public void setValue(Object value) {
		this.value = value;
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