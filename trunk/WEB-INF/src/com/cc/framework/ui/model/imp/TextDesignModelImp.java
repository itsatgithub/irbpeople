/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/TextDesignModelImp.java,v 1.15 2005/07/28 19:41:39 P001002 Exp $
 * $Revision: 1.15 $
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

package com.cc.framework.ui.model.imp;

import com.cc.framework.convert.Converter;
import com.cc.framework.ui.model.InputFieldType;
import com.cc.framework.ui.model.TextDesignModel;

/**
 * Designmodel for text/input fields
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision: 1.15 $
 * @since 1.0
 */
public class TextDesignModelImp extends ControlDesignModelImp implements
		TextDesignModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1497191027828488643L;

	/**
	 * The converter that should be used to convert Java Objects into their
	 * localized String representation. If no converter is specified the
	 * framework will use a default Converter that matches the beans data type.
	 */
	private Converter converter = null;

	/**
	 * Maximum number of input characters to accept.
	 */
	private int maxLength = -1;

	/**
	 * Number of character positions to allocate.
	 */
	private int size = -1;

	/**
	 * Type of the input field.
	 * 
	 * @see com.cc.framework.ui.model.InputFieldType
	 */
	private InputFieldType inputType = InputFieldType.TEXT;

	/**
	 * Specifies if all String should be converted into there HTML
	 * representation
	 */
	private boolean filter = true;

	// -------------------------------
	// methods
	// -------------------------------

	/**
	 * Constructor
	 */
	public TextDesignModelImp() {
		super();
	}

	/**
	 * @see com.cc.framework.ui.model.TextDesignModel#getMaxLength()
	 */
	public int getMaxLength() {
		return maxLength;
	}

	/**
	 * @see com.cc.framework.ui.model.TextDesignModel#getSize()
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Sets the maximum number of input characters to accept.
	 * 
	 * @param maxLength
	 *            The maximum number of input characters to accept.
	 */
	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	/**
	 * Sets the size of the input field.
	 * 
	 * @param size
	 *            The size of the input field.
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * @see com.cc.framework.ui.model.TextDesignModel#getInputType()
	 */
	public InputFieldType getInputType() {
		return inputType;
	}

	/**
	 * Sets the type of the input field
	 * 
	 * @param inputType
	 *            The input field type
	 * @see com.cc.framework.ui.model.InputFieldType
	 */
	public void setInputType(InputFieldType inputType) {
		this.inputType = inputType;
	}

	/**
	 * @see com.cc.framework.ui.model.TextDesignModel#filter()
	 */
	public boolean filter() {
		return filter;
	}

	/**
	 * @see com.cc.framework.ui.model.TextDesignModel#setFilter(boolean)
	 */
	public void setFilter(boolean filter) {
		this.filter = filter;
	}

	/**
	 * @see com.cc.framework.ui.model.TextDesignModel#getConverter()
	 */
	public Converter getConverter() {
		return converter;
	}

	/**
	 * @see com.cc.framework.ui.model.TextDesignModel#setConverter(com.cc.framework.convert.Converter)
	 */
	public void setConverter(Converter converter) {
		this.converter = converter;
	}
}