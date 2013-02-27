/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/control/TextPopupControl.java,v 1.4 2005/07/08 14:19:11 P001002 Exp $
 * $Revision: 1.4 $
 * $Date: 2005/07/08 14:19:11 $
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

import com.cc.framework.ui.model.TextPopupDesignModel;
import com.cc.framework.ui.model.TextareaDataModel;
import com.cc.framework.ui.model.TextareaStateModel;

/**
 * TextareaControl
 *
 * @author   <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version  $Revision: 1.4 $
 * @since    1.0
 */
public class TextPopupControl extends TextareaControl implements TextareaStateModel, TextareaDataModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -5710308828520034065L;

	/**
	 * Constructor
	 * @param	designModel	TextareaDesignModel
	 */
	public TextPopupControl(TextPopupDesignModel designModel) {
		super(designModel);
	}

	/**
	 * Returns the width of the popup window
	 * @return	int
	 */
	public String getPopupWidth() {
		return ((TextPopupDesignModel) getDesignModel()).getPopupWidth();
	}

	/**
	 * Returns the height of the popup window
	 * @return	int
	 */
	public String getPopupHeight() {
		return ((TextPopupDesignModel) getDesignModel()).getPopupHeight();
	}

	/**
	 * Returns the Number of Columns in the popup window
	 * @return	int
	 */
	public int getPopupRows() {
		return ((TextPopupDesignModel) getDesignModel()).getPopupRows();
	}
}