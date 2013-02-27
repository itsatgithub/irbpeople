/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/control/GaugeControl.java,v 1.7 2005/09/27 14:06:22 P001002 Exp $
 * $Revision: 1.7 $
 * $Date: 2005/09/27 14:06:22 $
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

import com.cc.framework.ui.AlignmentType;
import com.cc.framework.ui.Color;
import com.cc.framework.ui.model.ClientHandler;
import com.cc.framework.ui.model.ControlDesignModel;
import com.cc.framework.ui.model.DataModel;
import com.cc.framework.ui.model.GaugeDataModel;
import com.cc.framework.ui.model.GaugeDesignModel;
import com.cc.framework.ui.model.GaugeStateModel;
import com.cc.framework.ui.model.StateModel;

/**
 * Gauge Control
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision: 1.7 $
 * @since 1.4
 */
public class GaugeControl extends Control implements GaugeStateModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 4280200352993575663L;

	/**
	 * The Data Model
	 */
	private GaugeDataModel dataModel = null;

	/**
	 * The Design Model
	 */
	private GaugeDesignModel designModel = null;

	/**
	 * Constructor
	 */
	public GaugeControl() {
		super();
	}

	/**
	 * @see com.cc.framework.ui.control.Control#getDataModel()
	 */
	public DataModel getDataModel() {
		return dataModel;
	}

	/**
	 * Sets the Datamodel
	 * 
	 * @param dataModel
	 *            DataModel
	 */
	public void setDataModel(GaugeDataModel dataModel) {
		this.dataModel = dataModel;
	}

	/**
	 * @see com.cc.framework.ui.control.Control#getDesignModel()
	 */
	public ControlDesignModel getDesignModel() {
		return designModel;
	}

	/**
	 * Sets the DesignModel
	 * 
	 * @param designModel
	 *            ChartDesignModel
	 */
	public void setDesignModel(GaugeDesignModel designModel) {
		this.designModel = designModel;
	}

	/**
	 * @see com.cc.framework.ui.control.Control#getStateModel()
	 */
	public StateModel getStateModel() {
		return this;
	}

	/**
	 * @see com.cc.framework.ui.model.StateModel#reset()
	 */
	public void reset() {
	}

	/**
	 * Returns the absolute amount of the given segment. The Gaug Control will
	 * calculate the segments width from this value.
	 * 
	 * @param index
	 *            segments index
	 * @return Amount
	 */
	public double getAmount(int index) {
		return dataModel.getAmount(index);
	}

	/**
	 * calculate the total amount (= 100%)
	 * 
	 * @return total amount
	 */
	public double getTotalAmount() {
		double total = 0;

		for (int i = 0; i < size(); i++) {
			total += getAmount(i);
		}

		return total;
	}

	/**
	 * Returns the text that should be desplayed next to the gauge
	 * 
	 * @return text or <code>null</code>
	 */
	public String getText() {
		return dataModel.getText();
	}

	/**
	 * Returns the tooltip text for the given segment
	 * 
	 * @param index
	 *            segments index
	 * @return Tooltip Text or <code>null</code>
	 */
	public String getTooltip(int index) {
		return dataModel.getTooltip(index);
	}

	/**
	 * Returns a hyperlink for the given segment
	 * 
	 * @param index
	 *            segment index
	 * @return Hyperlink or <code>null</code>
	 */
	public String getHyperlink(int index) {
		return dataModel.getHyperlink(index);
	}

	/**
	 * Returns the traget where to open the hyperlink
	 * 
	 * @param index
	 *            segment index
	 * @return Target or <code>null</code>
	 */
	public String getTarget(int index) {
		return dataModel.getTarget(index);
	}

	/**
	 * Returns a set of client Handlers for this segment
	 * 
	 * @param index
	 *            segment index
	 * @return Client Handler or <code>null</code>
	 */
	public ClientHandler getClientHandler(int index) {
		return dataModel.getClientHandler(index);
	}

	/**
	 * Returns the color of the given segment
	 * 
	 * @param index
	 *            segments index
	 * @return Color
	 */
	public Color getColor(int index) {
		return dataModel.getColor(index);
	}

	/**
	 * A text that is output in the body of the gauge element if there are no
	 * elements available (size() == 0)
	 * 
	 * @return String
	 */
	public String getEmptyText() {
		return designModel.getEmptyText();
	}

	/**
	 * Returns the number of segments of the gauge
	 * 
	 * @return size
	 */
	public int size() {
		return dataModel.size();
	}

	/**
	 * Returns the alignment for the column
	 * 
	 * @return the alignment for the column
	 */
	public AlignmentType getAlignment() {
		return designModel.getAlignment();
	}

	/**
	 * Returns the alignment for the column
	 * 
	 * @return the alignment for the column
	 */
	public boolean getFilter() {
		return designModel.getFilter();
	}
}