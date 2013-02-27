/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/ColumnImageDesignModelImp.java,v 1.12 2005/07/08 14:19:42 P001002 Exp $
 * $Revision: 1.12 $
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

import com.cc.framework.ui.model.ColumnImageDesignModel;
import com.cc.framework.ui.model.value.DeferredValue;

/**
 * Designmodel for image columns
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.12 $
 * @since       1.0
 */
public class ColumnImageDesignModelImp extends ColumnDesignModelImp implements ColumnImageDesignModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 9035670453663430857L;

	/**
	 * With this attribute, it is possible to specify whether
	 * the column should generate a hyperlink for the image.
	 * Only in such a case can the column fire an onCellClick-event.
	 */
	private DeferredValue clickable = DeferredValue.FALSE;

	/**
	 * Constructor for ColumnImageDesignModelImp
	 */
	public ColumnImageDesignModelImp() {
		super();
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnImageDesignModel#isClickable()
	 */
	public boolean isClickable() {
		return clickable.toBoolean(getEnvironment());
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnImageDesignModel#setClickable(boolean)
	 */
	public void setClickable(boolean clickable) {
		this.clickable = new DeferredValue(clickable);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnImageDesignModel#setClickable(java.lang.String)
	 */
	public void setClickable(String clickable) {
		this.clickable = DeferredValue.parseBoolean(clickable);
	}
}
