/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/ColumnRadioDesignModelImp.java,v 1.8 2005/07/08 14:19:42 P001002 Exp $
 * $Revision: 1.8 $
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

import com.cc.framework.ui.AlignmentType;
import com.cc.framework.ui.model.ColumnRadioDesignModel;
import com.cc.framework.ui.model.InputFieldType;

/**
 * Designmodel for the ColumnCheckboxColumn
 *
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz </a>
 * @version $Revision: 1.8 $
 * @since 1.3
 */
public class ColumnRadioDesignModelImp
	extends ColumnTextDesignModelImp
	implements ColumnRadioDesignModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 2764640378954696771L;

	/**
	 * The value that must be match to mark the radio button as selected
	 * When <code>value</code> is <code>null</code> the checkboxes are
	 * grouped by rows
	 */
	private String value = null;

	/**
	 * Constructor for ColumnEditDesignModelImp
	 */
	public ColumnRadioDesignModelImp() {
		super();

		setWidth(40);
		setInputType(InputFieldType.RADIO);
		setAlignment(AlignmentType.CENTER);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnRadioDesignModel#getValue()
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnRadioDesignModel#setValue(java.lang.String)
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnRadioDesignModel#isRowSpan()
	 */
	public boolean isRowSpan() {
		return (value == null);
	}
}