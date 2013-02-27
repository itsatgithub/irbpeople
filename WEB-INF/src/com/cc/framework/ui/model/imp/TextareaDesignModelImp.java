/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/TextareaDesignModelImp.java,v 1.13 2005/07/28 19:41:39 P001002 Exp $
 * $Revision: 1.13 $
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

import javax.servlet.jsp.JspException;

import com.cc.framework.convert.Converter;
import com.cc.framework.ui.model.TextareaDesignModel;

/**
 * Designmodel for TextAreas
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision: 1.13 $
 * @since 1.0
 */
public class TextareaDesignModelImp extends ControlDesignModelImp implements
		TextareaDesignModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -7855229063825763149L;

	/**
	 * The converter that should be used to convert Java Objects into their
	 * localized String representation. If no converter is specified the
	 * framework will use a default Converter that matches the beans data type.
	 */
	private Converter converter = null;

	/**
	 * The number of rows
	 */
	private int rows = -1;

	/**
	 * The number of columns
	 */
	private int columns = -1;

	/**
	 * readonly attribute
	 */
	private boolean readonly = false;

	/**
	 * The maximal length for the value within this textare
	 */
	private int maxlength = 0;

	/**
	 * The WRAP attribute can be used to specify how to handle word-wrapping
	 * display in text input areas in forms.
	 */
	private String wrap = null;

	// --------------------------------
	// methods
	// --------------------------------

	/**
	 * Constructor for TextareaDesignModelImp
	 */
	public TextareaDesignModelImp() {
		super();
	}

	/**
	 * @see com.cc.framework.ui.model.TextareaDesignModel#getColumns()
	 */
	public int getColumns() {
		return columns;
	}

	/**
	 * @see com.cc.framework.ui.model.TextareaDesignModel#getRows()
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * @see com.cc.framework.ui.model.TextareaDesignModel#setColumns(int)
	 */
	public void setColumns(int columns) {
		this.columns = columns;
	}

	/**
	 * @see com.cc.framework.ui.model.TextareaDesignModel#setRows(int)
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}

	/**
	 * @see com.cc.framework.ui.model.TextareaDesignModel#isReadonly()
	 */
	public boolean isReadonly() {
		return readonly;
	}

	/**
	 * @see com.cc.framework.ui.model.TextareaDesignModel#setReadonly(boolean)
	 */
	public void setReadonly(boolean readonly) throws JspException {
		this.readonly = readonly;
	}

	/**
	 * @see com.cc.framework.ui.model.TextareaDesignModel#getMaxLength()
	 */
	public int getMaxLength() {
		return maxlength;
	}

	/**
	 * @see com.cc.framework.ui.model.TextareaDesignModel#setMaxLength(int)
	 */
	public void setMaxLength(int maxlength) {
		this.maxlength = maxlength;
	}

	/**
	 * @see com.cc.framework.ui.model.TextareaDesignModel#getWrap()
	 */
	public String getWrap() {
		return wrap;
	}

	/**
	 * @see com.cc.framework.ui.model.TextareaDesignModel#setWrap(java.lang.String)
	 */
	public void setWrap(String wrap) {
		this.wrap = wrap;
	}

	/**
	 * @see com.cc.framework.ui.model.TextareaDesignModel#getConverter()
	 */
	public Converter getConverter() {
		return converter;
	}

	/**
	 * @see com.cc.framework.ui.model.TextareaDesignModel#setConverter(com.cc.framework.convert.Converter)
	 */
	public void setConverter(Converter converter) {
		this.converter = converter;
	}
}