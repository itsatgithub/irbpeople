/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/forms/FrameTag.java,v 1.7 2005/09/12 10:33:37 P001002 Exp $
 * $Revision: 1.7 $
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

package com.cc.framework.taglib.forms;

import javax.servlet.jsp.JspException;

import com.cc.framework.taglib.FrameContainerTag;
import com.cc.framework.taglib.TagHelp;
import com.cc.framework.taglib.controls.BaseControlTag;
import com.cc.framework.ui.control.Control;
import com.cc.framework.ui.control.FrameControl;
import com.cc.framework.ui.model.ControlDesignModel;
import com.cc.framework.ui.model.FrameDesignModel;
import com.cc.framework.ui.model.ImageModel;
import com.cc.framework.ui.model.InnerFrame;
import com.cc.framework.ui.model.imp.FrameDesignModelImp;

/**
 * Tag-Handler for the <code>frame</code> Tag.
 * <p>
 * The tag generates a frame. The frames content is specified in
 * the tag-body.
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.7 $
 * @since      1.0
 */
public class FrameTag extends BaseControlTag implements FrameContainerTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 6815328769843586066L;

	/**
	 * Constructor
	 */
	public FrameTag() {
		super();
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateDesignModel()
	 */
	protected ControlDesignModel doCreateDesignModel() {
		return new FrameDesignModelImp();
	}

	/**
	 * Returns a casted version of the ControlDesignModel
	 * 
	 * @return FrameDesignModel
	 */
	protected FrameDesignModel getFrameDesignModel() {
		return (FrameDesignModel) getDesignModel();
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateControl()
	 */
	protected Control doCreateControl() throws JspException {
		FrameDesignModel designModel = getFrameDesignModel();
		FrameControl ctrl = null;

		// get the control
		Object value = lookupBean();

		if (value == null) {
			ctrl = new FrameControl();
		} else if (!(value instanceof FrameControl)) {
			throw new JspException("FrameTag: Invalid property class. expected class=FrameControl!");
		} else {
			ctrl = (FrameControl) value;
		}

		// Assign an action if this doesn't happend by the programmer
		if (designModel.getAction() == null) {
			designModel.setAction(getDefaultAction());
		}

		// Assign the DesignModel if this did not happened
		// in the Application
		if ((ctrl.getDesignModel() == null) || ctrl.getDesignModel().isDynamicDesignModel()) {
			ctrl.setDesignModel(designModel);
		} else {
			FrameDesignModel dm = (FrameDesignModel) ctrl.getDesignModel();

			dm.setAction(designModel.getAction());
			dm.setProperty(designModel.getProperty());
		}

		return ctrl;
	}

	/**
	 * The main title of the form. The title and detail can also be specified
	 * together in the form "title - detail.
	 * 
	 * @param caption
	 *            The caption
	 */
	public void setCaption(String caption) {
		getFrameDesignModel().setCaption(caption);
	}

	/**
	 * The detail title of the form
	 * 
	 * @param detail
	 *            The detail title of the form
	 */
	public void setDetail(String detail) {
		getFrameDesignModel().setDetail(detail);
	}

	/**
	 * Disables the controls Frame
	 * 
	 * @param noframe
	 *            Boolean Value to enable or disable the frame
	 * @throws JspException
	 *             If the Argument can't be converted to boolean
	 */
	public void setNoframe(String noframe) throws JspException {
		getFrameDesignModel().setShowFrame(!TagHelp.toBoolean(noframe));
	}

	/**
	 * @see com.cc.framework.taglib.FrameContainerTag#addInnerFrame(com.cc.framework.ui.model.InnerFrame)
	 */
	public void addInnerFrame(InnerFrame frame) {
		getFrameDesignModel().addInnerFrame(frame);
	}

	/**
	 * @see com.cc.framework.taglib.ImageContainerTag#setImage(com.cc.framework.ui.model.ImageModel)
	 */
	public void setImage(ImageModel image) {
		getFrameDesignModel().setImage(image);
	}
}