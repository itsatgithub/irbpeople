/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/InfoDesignModelImp.java,v 1.12 2005/07/08 15:15:31 P001002 Exp $
 * $Revision: 1.12 $
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

import com.cc.framework.ui.model.InfoDesignModel;

/**
 * Designmodel for the InfoControl
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.12 $
 * @since      1.0
 */
public class InfoDesignModelImp extends ControlDesignModelImp implements InfoDesignModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 26842395959982404L;

	/**
	 * Image Base directory
	 */
	private String base		= null;

	/**
	 * The resource whisch should be displayed in the
	 * infocontrol
	 */
	private String resource = null;

	/**
	 * Enables or disables the frame
	 */
	private boolean frame = true;

	/**
	 * The Frames content
	 */
	private String content = null;

	/**
	 * Constructor
	 */
	public InfoDesignModelImp() {
		super();
	}

	/**
	 * @see com.cc.framework.ui.WebResourceAccess#getBase()
	 */
	public String getBase() {
		return base;
	}

	/**
	 * @see com.cc.framework.ui.WebResourceAccess#setBase(java.lang.String)
	 */
	public void setBase(String base) {
		this.base = base;
	}

	/**
	 * @see com.cc.framework.ui.model.InfoDesignModel#getResource()
	 */
	public String getResource() {
		return resource;
	}

	/**
	 * @see com.cc.framework.ui.model.InfoDesignModel#setResource(java.lang.String)
	 */
	public void setResource(String resource) {
		this.resource = resource;
	}

	/**
	 * @see com.cc.framework.ui.model.InfoDesignModel#getContent()
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @see com.cc.framework.ui.model.InfoDesignModel#setContent(java.lang.String)
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @see com.cc.framework.ui.model.InfoDesignModel#setShowFrame(boolean)
	 */
	public void setShowFrame(boolean show) {
		this.frame = show;
	}

	/**
	 * @see com.cc.framework.ui.model.InfoDesignModel#showFrame()
	 */
	public boolean showFrame() {
		return frame;
	}
}