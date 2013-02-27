/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/control/FormControl.java,v 1.15 2005/07/08 15:15:30 P001002 Exp $
 * $Revision: 1.15 $
 * $Date: 2005/07/08 15:15:30 $
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

package com.cc.framework.ui.control;

import com.cc.framework.ui.FormType;
import com.cc.framework.ui.ImageMap;
import com.cc.framework.ui.model.ControlDesignModel;
import com.cc.framework.ui.model.DataModel;
import com.cc.framework.ui.model.FormDataModel;
import com.cc.framework.ui.model.FormDesignModel;
import com.cc.framework.ui.model.FormStateModel;
import com.cc.framework.ui.model.ImageModel;
import com.cc.framework.ui.model.InnerFrame;
import com.cc.framework.ui.model.StateModel;

/**
 * A form control
 *
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision: 1.15 $
 */
public class FormControl extends Control implements FormStateModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 5174489687836862322L;

	/**
	 * Datamodel
	 */
	private FormDataModel dataModel		= null;

	/**
	 * Designmodel
	 */
	private FormDesignModel designModel	= null;


	/**
	 * Constructor
	 */
	public FormControl() {
		super();
	}

	/**
	 * @see com.cc.framework.ui.control.Control#getDataModel()
	 */
	public DataModel getDataModel() {
		return dataModel;
	}

	/**
	 * Sets the data model
	 *
	 * @param	dataModel	The data model of the form
	 */
	public void setDataModel(FormDataModel dataModel) {
		this.dataModel	= dataModel;
	}

	/**
	 * @see com.cc.framework.ui.control.Control#getDesignModel()
	 */
	public ControlDesignModel getDesignModel() {
		return designModel;
	}

	/**
	 * @see com.cc.framework.ui.control.Control#getStateModel()
	 */
	public StateModel getStateModel() {
		return this;
	}

	/**
	 * Sets the design model
	 *
	 * @param	designModel		The design model of the form
	 */
	public void setDesignModel(FormDesignModel designModel) {
		this.designModel = designModel;
	}

	/**
	 * Returns a container with all form elements within the form
	 *
	 * @return	FormElementContainer
	 */
	public FormElementContainer getFormElements() {
		return designModel;
	}

	/**
	 * Returns the column span for this element
	 *
	 * @return		column span
	 */
	public int getColSpan() {
		return designModel.getColSpan();
	}

	/**
	 * Returns the id of the form
	 *
	 * @return	The id of the form
	 */
	public String getFormId() {
		return designModel.getFormId();
	}

	/**
	 * Returns the caption of the form
	 *
	 * @return	String
	 */
	public String getCaption() {
		return designModel.getCaption();
	}

	/**
	 * Returns the detail text
	 *
	 * @return	String
	 */
	public String getDetail() {
		return designModel.getDetail();
	}

	/**
	 * Returns the type of the form
	 *
	 * @return	FormType
	 */
	public FormType getFormType() {
		return designModel.getFormType();
	}

	/**
	 * Returns the ImageMap
	 * @return	ImageMap
	 */
	public ImageMap getImageMap() {
		return designModel.getImageMap();
	}

	/**
	 * Returns the image for the frames title
	 * 
	 * @return		ImageModel
	 */
	public ImageModel getImage() {
		return designModel.getImage();
	}

	/**
	 * @return	<code>true</code> if the fram should be shown
	 */
	public boolean showFrame() {
		return designModel.showFrame();
	}

	/**
	 * Retrieves a list of inner frames
	 * that are matching the given layout hint
	 * 
	 * @param		layoutHint The layout hint that specifies
	 * 				what frames should be selected:
	 * 				<code>AlignmentType.TOP</code> - header frames
	 * 				<code>AlignmentType.BOTTOM</code> - footer frames
	 * @return		Frame list
	 */
	public InnerFrame[] getInnerFrames(Object layoutHint) {
		return designModel.getInnerFrames(getPrincipal(), layoutHint);
	}

	/**
	 * Resets the state model
	 */
	public void reset() {
		// no action
	}
}
