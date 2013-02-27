/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/FormDesignModelImp.java,v 1.23 2005/07/08 15:15:31 P001002 Exp $
 * $Revision: 1.23 $
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

import java.util.ArrayList;

import com.cc.framework.security.Principal;
import com.cc.framework.ui.AlignmentType;
import com.cc.framework.ui.FormMethod;
import com.cc.framework.ui.FormType;
import com.cc.framework.ui.ImageMap;
import com.cc.framework.ui.OrientationType;
import com.cc.framework.ui.control.FormElement;
import com.cc.framework.ui.control.FormGroupElement;
import com.cc.framework.ui.model.FormDesignModel;
import com.cc.framework.ui.model.FrameUtil;
import com.cc.framework.ui.model.ImageModel;
import com.cc.framework.ui.model.InnerFrame;

/**
 * Designmodel for forms
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.23 $
 * @since      1.0
 */
public class FormDesignModelImp extends ControlDesignModelImp implements FormDesignModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -8513180170862209060L;

	/**
	 * Type of the form
	 */
	private FormType type = FormType.NONE;

	/**
	 * Method to submit the form
	 */
	private FormMethod method = FormMethod.POST;

	/**
	 * The titel of the form
	 */
	private String caption = null;

	/**
	 * The forms subtitle
	 */
	private String detail = null;

	/**
	 * The unique Id of the form
	 */
	private String formid = null;

	/**
	 * The ImageMap which can be used to paint
	 * icons in front of the labels.
	 */
	private ImageMap imagemap = null;

	/**
	 * Enables or disables the forms frame
	 */
	private boolean showFrame = true;

	/**
	 * The optional title image
	 */
	private ImageModel image = null;

	/**
	 * Container with the form elements
	 */
	private FormGroupElement container = new FormGroupElement();

	/**
	 * Inner Frames
	 */
	private ArrayList frames = new ArrayList();

	// ------------------------------------------------
	//                methods
	// ------------------------------------------------

	/**
	 * Constructor for FormDesignModelImp
	 */
	public FormDesignModelImp() {
		super();
	}

	/**
	 * @see com.cc.framework.ui.model.FormDesignModel#getMethod()
	 */
	public FormMethod getMethod() {
		return method;
	}

	/**
	 * @see com.cc.framework.ui.model.FormDesignModel#setMethod(FormMethod method)
	 */
	public void setMethod(FormMethod method) {
		this.method = method;
	}

	/**
	 * @see com.cc.framework.ui.model.FormDesignModel#getCaption()
	 */
	public String getCaption() {
		return caption;
	}

	/**
	 * @see com.cc.framework.ui.model.FormDesignModel#setCaption(java.lang.String)
	 */
	public void setCaption(String caption) {
		this.caption = caption;
	}

	/**
	 * @see com.cc.framework.ui.model.FormDesignModel#getDetail()
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * @see com.cc.framework.ui.model.FormDesignModel#setDetail(String)
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}

	/**
	 * @see com.cc.framework.ui.model.FormDesignModel#getFormId()
	 */
	public String getFormId() {
		return formid;
	}

	/**
	 * @see com.cc.framework.ui.model.FormDesignModel#setFormId(String formid)
	 */
	public void setFormId(String formid) {
		this.formid = formid;
	}

	/**
	 * @see com.cc.framework.ui.model.FormDesignModel#getFormType()
	 */
	public FormType getFormType() {
		return type;
	}

	/**
	 * @see com.cc.framework.ui.model.FormDesignModel#setFormType(com.cc.framework.ui.FormType)
	 */
	public void setFormType(FormType type) {
		this.type = type;
	}

	/**
	 * @see com.cc.framework.ui.control.FormElementContainer#addFormElement(FormElement element)
	 */
	public void addFormElement(FormElement element) {
		container.addFormElement(element);
	}

	/**
	 * @see com.cc.framework.ui.control.FormElementContainer#getFormElement(int)
	 */
	public FormElement getFormElement(int index) {
		return container.getFormElement(index);
	}

	/**
	 * @see com.cc.framework.ui.control.FormElementContainer#getFormElements()
	 */
	public FormElement[] getFormElements() {
		return container.getFormElements();
	}

	/**
	 * @see com.cc.framework.ui.control.FormElementContainer#getFormElements(com.cc.framework.security.Principal)
	 */
	public FormElement[] getFormElements(Principal principal) {
		return container.getFormElements(principal);
	}

	/**
	 * @see com.cc.framework.ui.control.FormElementContainer#removeFormElement(FormElement)
	 */
	public void removeFormElement(FormElement element) {
		container.removeFormElement(element);
	}

	/**
	 * @see com.cc.framework.ui.control.FormElementContainer#size()
	 */
	public int size() {
		return container.size();
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#getColSpan()
	 */
	public int getColSpan() {
		return container.getColSpan();
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#setColSpan(int)
	 */
	public void setColSpan(int colspan) {
		container.setColSpan(colspan);
	}

	/**
	 * @see com.cc.framework.ui.control.FormElementContainer#getOrientation()
	 */
	public OrientationType getOrientation() {
		return container.getOrientation();
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#joinElements()
	 */
	public boolean joinElements() {
		return container.joinElements();
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#setJoin(boolean)
	 */
	public void setJoin(boolean join) {
		container.setJoin(join);
	}

	/**
	 * @see com.cc.framework.ui.model.FormDesignModel#getImageMap()
	 */
	public ImageMap getImageMap() {
		return imagemap;
	}

	/**
	 * @see com.cc.framework.ui.model.FormDesignModel#setImageMap(com.cc.framework.ui.ImageMap)
	 */
	public void setImageMap(ImageMap imagemap) {
		this.imagemap = imagemap;
	}

	/**
	 * @see com.cc.framework.ui.control.FormElementContainer#setShowFrame(boolean)
	 */
	public void setShowFrame(boolean show) {
		this.showFrame = show;
	}

	/**
	 * @see com.cc.framework.ui.control.FormElementContainer#showFrame()
	 */
	public boolean showFrame() {
		return showFrame;
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#getAlignment()
	 */
	public AlignmentType getAlignment() {
		return container.getAlignment();
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#getVAlignment()
	 */
	public AlignmentType getVAlignment() {
		return container.getVAlignment();
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#setAlignment(com.cc.framework.ui.AlignmentType)
	 */
	public void setAlignment(AlignmentType alignment) {
		container.setAlignment(alignment);
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#setVAlignment(com.cc.framework.ui.AlignmentType)
	 */
	public void setVAlignment(AlignmentType valignment) {
		container.setVAlignment(valignment);
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#getNoWrap()
	 */
	public boolean getNoWrap() {
		return false;
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
}