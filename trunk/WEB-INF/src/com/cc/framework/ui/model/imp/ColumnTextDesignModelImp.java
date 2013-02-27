/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/ColumnTextDesignModelImp.java,v 1.13 2005/07/08 14:19:42 P001002 Exp $
 * $Revision: 1.13 $
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

import com.cc.framework.ui.model.ColumnTextDesignModel;
import com.cc.framework.ui.model.InputFieldType;
import com.cc.framework.ui.model.value.DeferredValue;

/**
 * Designmodel for text columns
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.13 $
 * @since       1.0
 */
public class ColumnTextDesignModelImp extends ColumnDesignModelImp implements ColumnTextDesignModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -6804956039481982627L;

	/**
	 * Number of character positions to allocate.
	 */
	private DeferredValue size			= DeferredValue.NEG;

	/**
	 * Type of the input field.
	 * @see com.cc.framework.ui.model.InputFieldType
	 */
	private InputFieldType inputType	= InputFieldType.TEXT;

	/**
	 * Constructor for ColumnTextDesignModelImp
	 */
	public ColumnTextDesignModelImp() {
		super();
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnTextDesignModel#getSize()
	 */
	public int getSize() {
		return size.toInt(getEnvironment());
	}

	/**
	 * Sets the size of the input field.
	 *
	 * @param	size	The size of the input field.
	 */
	public void setSize(int size) {
		this.size = new DeferredValue(size);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnTextDesignModel#setSize(java.lang.String)
	 */
	public void setSize(String size) {
		this.size = DeferredValue.parseInt(size);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnTextDesignModel#getInputType()
	 */
	public InputFieldType getInputType() {
		return inputType;
	}

	/**
	 * Sets the type of the input field
	 *
	 * @param	inputType	The input field type
	 * @see com.cc.framework.ui.model.InputFieldType
	 */
	public void setInputType(InputFieldType inputType) {
		this.inputType = inputType;
	}
}