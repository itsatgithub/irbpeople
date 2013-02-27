/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/MessageDesignModelImp.java,v 1.14 2005/07/08 15:15:33 P001002 Exp $
 * $Revision: 1.14 $
 * $Date: 2005/07/08 15:15:33 $
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

import com.cc.framework.common.Severity;
import com.cc.framework.security.Principal;
import com.cc.framework.ui.model.FrameUtil;
import com.cc.framework.ui.model.ImageModel;
import com.cc.framework.ui.model.InnerFrame;
import com.cc.framework.ui.model.MessageDesignModel;

/**
 * Designmodel for messages
 *
 * @author	   <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version	   $Revision: 1.14 $
 * @since      1.0
 */
public class MessageDesignModelImp extends ControlDesignModelImp implements MessageDesignModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -3820508935407773429L;

	/**
	 * The severity of the message
	 */
	private Severity severity		= Severity.NONE;

	/**
	 * The caption
	 */
	private String caption			= null;

	/**
	 * The message text
	 */
	private String detail			= null;

	/**
	 * Form iD
	 */
	private String formId			= null;

	/**
	 * Specifies if all String should be converted
	 * into there HTML representation
	 */
	private boolean filter			= true;

	/**
	 * The optional title image
	 */
	private ImageModel image		= null;

	/**
	 * Container with the form elements
	 */
	private ArrayList frames		= new ArrayList();

	// --------------------------------
    //             methods
    // --------------------------------

	/**
	 * Constructor for MessageDesignModelImp
	 */
	public MessageDesignModelImp() {
		super();
	}

	/**
	 * @see com.cc.framework.ui.model.MessageDesignModel#getSeverity()
	 */
	public Severity getSeverity() {
		return severity;
	}

	/**
	 * @see com.cc.framework.ui.model.MessageDesignModel#setSeverity(com.cc.framework.common.Severity)
	 */
	public void setSeverity(Severity severity) {
		this.severity = severity;
	}

	/**
	 * @see com.cc.framework.ui.model.MessageDesignModel#getCaption()
	 */
	public String getCaption() {
		return caption;
	}

	/**
	 * @see com.cc.framework.ui.model.MessageDesignModel#getDetail()
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * @see com.cc.framework.ui.model.MessageDesignModel#setCaption(java.lang.String)
	 */
	public void setCaption(String caption) {
		this.caption = caption;
	}

	/**
	 * @see com.cc.framework.ui.model.MessageDesignModel#setDetail(java.lang.String)
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}

	/**
	 * @see com.cc.framework.ui.model.MessageDesignModel#getFormId()
	 */
	public String getFormId() {
		return formId;
	}

	/**
	 * @see com.cc.framework.ui.model.MessageDesignModel#setFormId(java.lang.String)
	 */
	public void setFormId(String formId) {
		this.formId = formId;
	}

	/**
	 * @see com.cc.framework.ui.model.MessageDesignModel#filter()
	 */
	public boolean filter() {
		return filter;
	}

	/**
	 * @see com.cc.framework.ui.model.MessageDesignModel#setFilter(boolean)
	 */
	public void setFilter(boolean filter) {
		this.filter = filter;
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