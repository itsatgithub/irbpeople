/*
 * $Header$
 * $Revision$
 * $Date$
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

import com.cc.framework.ui.model.HiddenDesignModel;
import com.cc.framework.ui.model.InputFieldType;

/**
 * DesignModel for Hidden element
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision$
 */
public class HiddenDesignModelImp extends TextDesignModelImp implements HiddenDesignModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 5709340433952301049L;

	/**
	 * directs the control to create an additional visible output of the
	 * controls value
	 */
	private boolean write = false;
	
	/**
	 * Constructor
	 */
	public HiddenDesignModelImp() {
		super();

		setInputType(InputFieldType.HIDDEN);
	}

	/**
	 * @see com.cc.framework.ui.model.HiddenDesignModel#isWrite()
	 */
	public boolean isWrite() {
		return write;
	}

	/**
	 * @see com.cc.framework.ui.model.HiddenDesignModel#setWrite(boolean)
	 */
	public void setWrite(boolean write) {
		this.write = write;
	}
}
