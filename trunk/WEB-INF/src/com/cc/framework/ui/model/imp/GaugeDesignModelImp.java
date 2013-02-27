/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/GaugeDesignModelImp.java,v 1.8 2005/07/08 15:15:32 P001002 Exp $
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

import com.cc.framework.ui.AlignmentType;
import com.cc.framework.ui.model.GaugeDesignModel;

/**
 * The design model for the gauge control
 *
 * @author      <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version     $Revision: 1.8 $
 * @since       1.4
 */
public class GaugeDesignModelImp extends ControlDesignModelImp implements GaugeDesignModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -3304039845931674012L;

	/**
	 * The alignment of the text
	 */
	private AlignmentType alignment = AlignmentType.LEFT;

	/**
	 * Specifies if all String should be converted
	 * into valid HTML Strings
	 */
	private boolean filter = true;

	/**
	 * Text to display if a empty list is shown.
	 * The default text is "no items".
	 */
	private String emptyText = null;

	// ----------------------------------
	//           methods
	// ----------------------------------

	/**
	 * Constructor
	 */
	public GaugeDesignModelImp() {
		super();
	}

	/**
	 * @see com.cc.framework.ui.model.GaugeDesignModel#getEmptyText()
	 */
	public String getEmptyText() {
		return emptyText;
	}

	/**
	 * @see com.cc.framework.ui.model.GaugeDesignModel#setEmptyText(java.lang.String)
	 */
	public void setEmptyText(String emptyText) {
		this.emptyText	= emptyText;
	}

	/**
	 * @see com.cc.framework.ui.model.GaugeDesignModel#getAlignment()
	 */
	public AlignmentType getAlignment() {
		return alignment;
	}

	/**
	 * @see com.cc.framework.ui.model.GaugeDesignModel#getFilter()
	 */
	public boolean getFilter() {
		return filter;
	}

	/**
	 * @see com.cc.framework.ui.model.GaugeDesignModel#setAlignment(com.cc.framework.ui.AlignmentType)
	 */
	public void setAlignment(AlignmentType alignment) {
		this.alignment = alignment;
	}

	/**
	 * @see com.cc.framework.ui.model.GaugeDesignModel#setFilter(boolean)
	 */
	public void setFilter(boolean filter) {
		this.filter = filter;
	}

}