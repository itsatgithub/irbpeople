/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/FrameDesignModelImp.java,v 1.6 2005/09/12 10:33:37 P001002 Exp $
 * $Revision: 1.6 $
 * $Date: 2005/09/12 10:33:37 $
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

import java.util.ArrayList;

import com.cc.framework.security.Principal;
import com.cc.framework.ui.model.FrameDesignModel;
import com.cc.framework.ui.model.FrameUtil;
import com.cc.framework.ui.model.ImageModel;
import com.cc.framework.ui.model.InnerFrame;

/**
 * Designmodel for frames
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.6 $
 * @since      1.0
 */
public class FrameDesignModelImp extends ControlDesignModelImp implements FrameDesignModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -128783290828808664L;

	/**
	 * The titel of the frame
	 */
	private String caption = null;

	/**
	 * The frames subtitle
	 */
	private String detail = null;

	/**
	 * Enables or disables the frames frame
	 */
	private boolean showFrame = true;

	/**
	 * The optional title image
	 */
	private ImageModel image = null;

	/**
	 * Inner Frames
	 */
	private ArrayList frames = new ArrayList();

	// ------------------------------------------------
	//                methods
	// ------------------------------------------------

	/**
	 * Constructor for FrameDesignModelImp
	 */
	public FrameDesignModelImp() {
		super();
	}

	/**
	 * @see com.cc.framework.ui.model.FrameDesignModel#getCaption()
	 */
	public String getCaption() {
		return caption;
	}

	/**
	 * @see com.cc.framework.ui.model.FrameDesignModel#setCaption(java.lang.String)
	 */
	public void setCaption(String caption) {
		this.caption = caption;
	}

	/**
	 * @see com.cc.framework.ui.model.FrameDesignModel#getDetail()
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * @see com.cc.framework.ui.model.FrameDesignModel#setDetail(java.lang.String)
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}

	/**
	 * @see com.cc.framework.ui.model.FrameDesignModel#setShowFrame(boolean)
	 */
	public void setShowFrame(boolean show) {
		this.showFrame = show;
	}

	/**
	 * @see com.cc.framework.ui.model.FrameDesignModel#showFrame()
	 */
	public boolean showFrame() {
		return showFrame;
	}

	/**
	 * @see com.cc.framework.ui.model.FrameContainer#addInnerFrame(com.cc.framework.ui.model.InnerFrame)
	 */
	public void addInnerFrame(InnerFrame frame) {
		frames.add(frame);
	}

	/**
	 * @see com.cc.framework.ui.model.FrameContainer#getInnerFrames(com.cc.framework.security.Principal, java.lang.Object)
	 */
	public InnerFrame[] getInnerFrames(Principal principal, Object layoutHint) {
		return FrameUtil.filter(frames, principal, layoutHint);
	}
	
	/**
	 * @see com.cc.framework.ui.model.FrameContainer#getImage()
	 */
	public ImageModel getImage() {
		return image;
	}

	/**
	 * @see com.cc.framework.ui.model.FrameContainer#setImage(com.cc.framework.ui.model.ImageModel)
	 */
	public void setImage(ImageModel img) {
		this.image = img;
	}
}