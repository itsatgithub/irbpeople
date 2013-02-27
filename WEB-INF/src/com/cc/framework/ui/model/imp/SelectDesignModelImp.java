/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/SelectDesignModelImp.java,v 1.11 2005/07/08 15:15:31 P001002 Exp $
 * $Revision: 1.11 $
 * $Date: 2005/07/08 15:15:31 $
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

import com.cc.framework.ui.RunAt;
import com.cc.framework.ui.model.SelectDesignModel;

/**
 * Designmodel for the SelectControl
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.11 $
 * @since      1.0
 */
public class SelectDesignModelImp extends ControlDesignModelImp implements SelectDesignModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 7726775512259710859L;

	/**
	 * Number of character positions to allocate.
	 */
	private int size = -1;

	/**
	 * Indicates that multiple selections is allowed.
	 */
	private boolean multiple = false;

	/**
	 * Constructor for SelectDesignModelImp
	 */
	public SelectDesignModelImp() {
		super();

		// This control is by default a from element
		setFormElement(true);

		// This controls runs by default on the client
		setRunAt(RunAt.CLIENT);
	}

	/**
	 * @see com.cc.framework.ui.model.SelectDesignModel#getSize()
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @see com.cc.framework.ui.model.SelectDesignModel#isMultiple()
	 */
	public boolean isMultiple() {
		return multiple;
	}

	/**
	 * @see com.cc.framework.ui.model.SelectDesignModel#setMultiple(boolean)
	 */
	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}

	/**
	 * @see com.cc.framework.ui.model.SelectDesignModel#setSize(int)
	 */
	public void setSize(int size) {
		this.size = size;
	}
}