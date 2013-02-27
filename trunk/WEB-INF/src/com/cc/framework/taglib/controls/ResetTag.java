/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/controls/ResetTag.java,v 1.4 2005/07/08 14:17:22 P001002 Exp $
 * $Revision: 1.4 $
 * $Date: 2005/07/08 14:17:22 $
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

package com.cc.framework.taglib.controls;

import com.cc.framework.ui.model.ButtonDesignModel;
import com.cc.framework.ui.model.ButtonType;
import com.cc.framework.ui.model.ControlDesignModel;

/**
 * Creates a reset Button
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.4 $
 */
public class ResetTag extends ButtonTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 8251651009706699625L;

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateDesignModel()
	 */
	public ControlDesignModel doCreateDesignModel() {
		ButtonDesignModel button = (ButtonDesignModel) super.doCreateDesignModel();
		button.setButtonType(ButtonType.RESET);

		return button;
	}
}
