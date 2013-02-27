/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/control/MessageControl.java,v 1.14 2005/07/08 15:15:30 P001002 Exp $
 * $Revision: 1.14 $
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

import java.util.Vector;

import com.cc.framework.common.Severity;
import com.cc.framework.message.Message;
import com.cc.framework.ui.model.ControlDesignModel;
import com.cc.framework.ui.model.DataModel;
import com.cc.framework.ui.model.ImageModel;
import com.cc.framework.ui.model.InnerFrame;
import com.cc.framework.ui.model.MessageDataModel;
import com.cc.framework.ui.model.MessageDesignModel;
import com.cc.framework.ui.model.MessageStateModel;
import com.cc.framework.ui.model.StateModel;

/**
 * The Message Control
 *
 * @author   <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version  $Revision: 1.14 $
 * @since    1.0
 */
public class MessageControl extends Control implements MessageStateModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -7463408443503900388L;

	/**
	 * The datamodel
	 */
	private MessageDataModel dataModel	= null;

	/**
	 * The designmodel
	 */
	private MessageDesignModel designModel	= null;


	// ------------------------------------------------
    //                Methoden
    // ------------------------------------------------

   	/**
	 * Constructor for MessageTag
	 */
	public MessageControl() {
		super();
	}

	/**
	 * @see com.cc.framework.ui.control.Control#getStateModel()
	 */
	public StateModel getStateModel() {
		return this;
	}

	/**
	 * @see com.cc.framework.ui.control.Control#getDataModel()
	 */
	public DataModel getDataModel() {
		return dataModel;
	}

	/**
	 * @see com.cc.framework.ui.control.Control#getDesignModel()
	 */
	public ControlDesignModel getDesignModel() {
		return designModel;
	}

	/**
	 * Sets teh data model
	 * @param	dataModel	The data model
	 */
	public void setDataModel(MessageDataModel dataModel) {
		this.dataModel = dataModel;
	}

	/**
	 * Sets the design model
	 * @param	designModel	The design model
	 */
	public void setDesignModel(MessageDesignModel designModel) {
		this.designModel = designModel;
	}

	/**
	 * Returns the form id
	 * @return	The form id
	 */
	public String getFormId() {
		return designModel.getFormId();
	}

	/**
	 * Returns the caption
	 * @return	The caption
	 */
	public String getCaption() {
		return designModel.getCaption();
	}

	/**
	 * Returns the detail
	 * @return	The detail
	 */
	public String getDetail() {
		return designModel.getDetail();
	}

	/**
	 * Returns the messages
	 * @return	Collection of messages
	 */
	public Message[] getMessages() {
		return dataModel.getMessages();
	}

	/**
	 * Returns a list of messages that is filtered to the
	 * controls severity
	 *
	 * @return		Filtered Message List
	 */
	public Message[] getFilteredMessages() {
		Vector filtered = new Vector();

		Message[] msg = getMessages();

		Severity sev = getSeverity();
		for (int i = 0; i < msg.length; i++) {
			if ((sev == null)
				|| Severity.NONE.equals(sev)
				|| sev.equals(msg[i].getSeverity())) {
				filtered.add(msg[i]);
			}
		}

		return (Message[]) filtered.toArray(new Message[filtered.size()]);
	}

	/**
	 * Returns the number of messages
	 * @return	The number of messages
	 */
	public int size() {
		return dataModel.size();
	}

	/**
	 * Returns the severity
	 * @return	The severity
	 */
	public Severity getSeverity() {
		return designModel.getSeverity();
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
	 * Returns if the filter is activated (default=true). This means
	 * that all Strings which should be displayed in the HTML page
	 * are html encoded
	 *
	 * @return	<code>true</code> if string will be html encoded;
	 * 			<code>false</code> otherwise
	 */
	public boolean filter() {
		return designModel.filter();
	}

	/**
	 * @see com.cc.framework.ui.model.StateModel#reset()
	 */
	public void reset() {
		// no action
	}
}
