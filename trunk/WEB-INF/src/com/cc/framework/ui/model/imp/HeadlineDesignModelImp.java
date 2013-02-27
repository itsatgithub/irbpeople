/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/HeadlineDesignModelImp.java,v 1.9 2005/07/08 15:15:31 P001002 Exp $
 * $Revision: 1.9 $
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

import com.cc.framework.ui.model.HeadlineDesignModel;

/**
  * Designmodel for the HeadlineControl
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.9 $
 * @since      1.0
 */
public class HeadlineDesignModelImp extends ControlDesignModelImp implements HeadlineDesignModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 675771051927567250L;

	/**
	 * The detail text
	 */
	private String detail				= null;

	/**
	 * The caption
	 */
	private String caption				= null;

	/**
	 * Indicates if strings should be converted into
	 * there HTML-String representation. Default = true.
	 */
	private boolean filter			= true;


	/**
	 * Constructor for HeadlineDesignModelImp
	 */
	public HeadlineDesignModelImp() {
		super();
	}

	/**
	 * @see com.cc.framework.ui.model.HeadlineDesignModel#getDetail()
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * @see com.cc.framework.ui.model.HeadlineDesignModel#setDetail(String)
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}

	/**
	 * @see com.cc.framework.ui.model.HeadlineDesignModel#getCaption()
	 */
	public String getCaption() {
		return caption;
	}

	/**
	 * @see com.cc.framework.ui.model.HeadlineDesignModel#setCaption(String)
	 */
	public void setCaption(String caption) {
		this.caption = caption;
	}

	/**
	 * @see com.cc.framework.ui.model.HeadlineDesignModel#getFilter()
	 */
	public boolean getFilter() {
		return filter;
	}

	/**
	 * @see com.cc.framework.ui.model.HeadlineDesignModel#setFilter(boolean)
	 */
	public void setFilter(boolean filter) {
		this.filter = filter;
	}
}
