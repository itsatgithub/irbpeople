/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/SwapSelectDesignModelImp.java,v 1.8 2005/07/08 15:15:32 P001002 Exp $
 * $Revision: 1.8 $
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

import com.cc.framework.ui.OrientationType;
import com.cc.framework.ui.RunAt;
import com.cc.framework.ui.model.SwapSelectDesignModel;

/**
 * Designmodel for the SwapSelectControl
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.8 $
 * @since      1.4.082
 */
public class SwapSelectDesignModelImp
	extends ControlDesignModelImp
	implements SwapSelectDesignModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -2832289817196356775L;

	/**
	 * Number of character positions to allocate.
	 */
	private int size = 10;

	/**
	 * Label for the left selection window
	 */
	private String labelLeft = null;

	/**
	 * Label for the right selection window
	 */
	private String labelRight = null;

	/**
	 * Specifies if all String should be converted
	 * into there HTML representation
	 */
	private boolean filter = true;

	/**
	 * Specifies the orientation of the selection windows:
	 * <ul>
	 * 		<li>horicontal = from top to bottom</li>
	 * 		<li>vertical = from left to right</li>
	 * </ul>
	 */
	private OrientationType orientation = OrientationType.HORIZONTAL;

	/**
	 * Constructor
	 */
	public SwapSelectDesignModelImp() {
		super();

		// This controls runs by default on the client
		setRunAt(RunAt.CLIENT);
	}

	/**
	 * @see com.cc.framework.ui.model.SwapSelectDesignModel#getSize()
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @see com.cc.framework.ui.model.SwapSelectDesignModel#setSize(int)
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * @see com.cc.framework.ui.model.SwapSelectDesignModel#getLabelLeft()
	 */
	public String getLabelLeft() {
		return labelLeft;
	}

	/**
	 * @see com.cc.framework.ui.model.SwapSelectDesignModel#getLabelRight()
	 */
	public String getLabelRight() {
		return labelRight;
	}

	/**
	 * @see com.cc.framework.ui.model.SwapSelectDesignModel#getOrientation()
	 */
	public OrientationType getOrientation() {
		return orientation;
	}

	/**
	 * @see com.cc.framework.ui.model.SwapSelectDesignModel#setLabelLeft(java.lang.String)
	 */
	public void setLabelLeft(String label) {
		this.labelLeft = label;
	}

	/**
	 * @see com.cc.framework.ui.model.SwapSelectDesignModel#setLabelRight(java.lang.String)
	 */
	public void setLabelRight(String label) {
		this.labelRight = label;
	}

	/**
	 * @see com.cc.framework.ui.model.SwapSelectDesignModel#setOrientation(com.cc.framework.ui.OrientationType)
	 */
	public void setOrientation(OrientationType orientation) {
		this.orientation = orientation;
	}

	/**
	 * @see com.cc.framework.ui.model.SwapSelectDesignModel#filter()
	 */
	public boolean filter() {
		return filter;
	}

	/**
	 * @see com.cc.framework.ui.model.SwapSelectDesignModel#setFilter(boolean)
	 */
	public void setFilter(boolean filter) {
		this.filter = filter;
	}
}