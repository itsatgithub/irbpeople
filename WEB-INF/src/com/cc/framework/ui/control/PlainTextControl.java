/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/control/PlainTextControl.java,v 1.3 2005/07/08 14:18:42 P001002 Exp $
 * $Revision: 1.3 $
 * $Date: 2005/07/08 14:18:42 $
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

import com.cc.framework.ui.model.PlainTextDesignModel;

/**
 * TextControl
 *
 * @author   <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version  $Revision: 1.3 $
 * @since    1.0
 */
public class PlainTextControl extends TextControl {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1727452892103114995L;

	/**
	 * Constructor
	 *
	 * @param	designModel	TextDesignModel
	 */
	public PlainTextControl(PlainTextDesignModel designModel) {
		super(designModel);
	}

	/**
	 * Returns the hidden.
	 * @return boolean
	 */
	public boolean isHidden() {
		return ((PlainTextDesignModel) getDesignModel()).isHidden();
	}
}