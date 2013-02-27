/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/TextPopupDesignModelImp.java,v 1.5 2005/07/08 15:15:32 P001002 Exp $
 * $Revision: 1.5 $
 * $Date: 2005/07/08 15:15:32 $
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

import com.cc.framework.ui.model.TextPopupDesignModel;

/**
 * Designmodel for the textarea popup control
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.5 $
 */
public class TextPopupDesignModelImp
	extends TextareaDesignModelImp
	implements TextPopupDesignModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -2814966049937185726L;

	/**
	 * The width of the popup window
	 */
	private String popupWidth = null;

	/**
	 * The height of the popup window
	 */
	private String popupHeight = null;

	/**
	 * The number of rows in the popup window
	 */
	private int popupRows = -1;

	/**
	 * @see com.cc.framework.ui.model.TextPopupDesignModel#getPopupRows()
	 */
	public int getPopupRows() {
		return popupRows;
	}

	/**
	 * @see com.cc.framework.ui.model.TextPopupDesignModel#setPopupRows(int)
	 */
	public void setPopupRows(int rows) {
		this.popupRows = rows;
	}

	/**
	 * @see com.cc.framework.ui.model.TextPopupDesignModel#getPopupHeight()
	 */
	public String getPopupHeight() {
		return popupHeight;
	}

	/**
	 * @see com.cc.framework.ui.model.TextPopupDesignModel#getPopupWidth()
	 */
	public String getPopupWidth() {
		return popupWidth;
	}

	/**
	 * @see com.cc.framework.ui.model.TextPopupDesignModel#setPopupHeight(java.lang.String)
	 */
	public void setPopupHeight(String height) {
		this.popupHeight = height;
	}

	/**
	 * @see com.cc.framework.ui.model.TextPopupDesignModel#setPopupWidth(java.lang.String)
	 */
	public void setPopupWidth(String width) {
		this.popupWidth = width;
	}
}
