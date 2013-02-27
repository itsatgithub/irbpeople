/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/control/FrameControl.java,v 1.6 2005/08/24 19:16:38 P001002 Exp $
 * $Revision: 1.6 $
 * $Date: 2005/08/24 19:16:38 $
 *
 * ====================================================================
 *
 * Copyright (c) 2000 - 2005 SCC Inframeationssysteme GmbH. All rights
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

import com.cc.framework.ui.model.ControlDesignModel;
import com.cc.framework.ui.model.DataModel;
import com.cc.framework.ui.model.FrameDataModel;
import com.cc.framework.ui.model.FrameDesignModel;
import com.cc.framework.ui.model.FrameStateModel;
import com.cc.framework.ui.model.ImageModel;
import com.cc.framework.ui.model.InnerFrame;
import com.cc.framework.ui.model.StateModel;
import com.cc.framework.ui.model.imp.FrameStateModelImp;

/**
 * A frame control
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision: 1.6 $
 */
public class FrameControl extends Control implements FrameStateModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 5987361632207556487L;

	/**
	 * Datamodel
	 */
	private FrameDataModel dataModel = null;

	/**
	 * Designmodel
	 */
	private FrameDesignModel designModel = null;

	/**
	 * State Model
	 */
	private FrameStateModel stateModel = null;

	/**
	 * Constructor
	 */
	public FrameControl() {
		super();

		stateModel = doCreateStatemodel();
	}

	/**
	 * Creates the state model for this control instance
	 *
	 * @return		State model instance
	 */
	protected FrameStateModel doCreateStatemodel() {
		return new FrameStateModelImp();
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
	 * @param dataModel
	 *            The data model of the frame
	 */
	public void setDataModel(FrameDataModel dataModel) {
		this.dataModel = dataModel;
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
	 * Sets the StateModel
	 * 
	 * @param stateModel
	 *            FrameStateModel
	 */
	public void setStateModel(FrameStateModel stateModel) {
		this.stateModel = stateModel;
	}

	/**
	 * @see com.cc.framework.ui.model.FrameStateModel#close()
	 */
	public void close() {
		stateModel.close();
	}

	/**
	 * @see com.cc.framework.ui.model.FrameStateModel#isClosed()
	 */
	public boolean isClosed() {
		return stateModel.isClosed();
	}

	/**
	 * @see com.cc.framework.ui.model.FrameStateModel#isMaximized()
	 */
	public boolean isMaximized() {
		return stateModel.isMaximized();
	}

	/**
	 * @see com.cc.framework.ui.model.FrameStateModel#isMinimized()
	 */
	public boolean isMinimized() {
		return stateModel.isMinimized();
	}

	/**
	 * @see com.cc.framework.ui.model.FrameStateModel#isOpen()
	 */
	public boolean isOpen() {
		return stateModel.isOpen();
	}

	/**
	 * @see com.cc.framework.ui.model.FrameStateModel#maximize()
	 */
	public void maximize() {
		stateModel.maximize();
	}

	/**
	 * @see com.cc.framework.ui.model.FrameStateModel#minimize()
	 */
	public void minimize() {
		stateModel.minimize();
	}

	/**
	 * @see com.cc.framework.ui.model.FrameStateModel#open()
	 */
	public void open() {
		stateModel.open();
	}

	/**
	 * @see com.cc.framework.ui.model.FrameStateModel#restore()
	 */
	public void restore() {
		stateModel.restore();
	}

	/**
	 * Sets the design model
	 * 
	 * @param designModel
	 *            The design model of the frame
	 */
	public void setDesignModel(FrameDesignModel designModel) {
		this.designModel = designModel;
	}

	/**
	 * Returns the caption of the frame
	 * 
	 * @return String
	 */
	public String getCaption() {
		return designModel.getCaption();
	}

	/**
	 * Returns the detail text
	 * 
	 * @return String
	 */
	public String getDetail() {
		return designModel.getDetail();
	}

	/**
	 * Returns the image for the frames title
	 * 
	 * @return ImageModel
	 */
	public ImageModel getImage() {
		return designModel.getImage();
	}

	/**
	 * @return <code>true</code> if the fram should be shown
	 */
	public boolean showFrame() {
		return designModel.showFrame();
	}

	/**
	 * Resets the state model
	 */
	public void reset() {
		stateModel.reset();
	}

	/**
	 * Retrieves a list of inner frames that are matching the given layout hint
	 * 
	 * @param layoutHint
	 *            The layout hint that specifies what frames should be selected:
	 *            <code>AlignmentType.TOP</code> - header frames
	 *            <code>AlignmentType.BOTTOM</code> - footer frames
	 * @return Frame list
	 */
	public InnerFrame[] getInnerFrames(Object layoutHint) {
		return designModel.getInnerFrames(getPrincipal(), layoutHint);
	}

	// --------------------------------
	// event handler
	// --------------------------------

	/**
	 * Default Handler for the <b>Close</b> Event
	 * 
	 * @param ctx
	 *            ControlRequestContext
	 * @throws Exception
	 *             is thrown when an error occurs
	 */
	public void onClose(ControlRequestContext ctx)
			throws Exception {

		log.debug("onClose()");
		
		close();
	}

	/**
	 * Default Handler for the <b>Minimize</b> Event
	 * 
	 * @param ctx
	 *            ControlRequestContext
	 * @throws Exception
	 *             is thrown when an error occurs
	 */
	public void onMinimize(ControlRequestContext ctx)
			throws Exception {

		log.debug("onMinimize()");
		
		minimize();
	}

	/**
	 * Default Handler for the <b>Maximize</b> Event
	 * 
	 * @param ctx
	 *            ControlRequestContext
	 * @throws Exception
	 *             is thrown when an error occurs
	 */
	public void onMaximize(ControlRequestContext ctx)
			throws Exception {

		log.debug("onMaximize()");
		
		maximize();
	}

	/**
	 * Default Handler for the <b>Restore</b> Event
	 * 
	 * @param ctx
	 *            ControlRequestContext
	 * @throws Exception
	 *             is thrown when an error occurs
	 */
	public void onRestore(ControlRequestContext ctx)
			throws Exception {

		log.debug("onRestore()");
		
		restore();
	}
}