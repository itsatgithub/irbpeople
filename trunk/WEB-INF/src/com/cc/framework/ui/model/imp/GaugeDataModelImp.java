/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/GaugeDataModelImp.java,v 1.6 2005/07/08 15:15:31 P001002 Exp $
 * $Revision: 1.6 $
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

import java.io.Serializable;
import java.util.Vector;

import com.cc.framework.ui.Color;
import com.cc.framework.ui.model.ClientHandler;
import com.cc.framework.ui.model.GaugeDataModel;

/**
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.6 $
 */
public class GaugeDataModelImp implements GaugeDataModel, Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -6433030313582133135L;

	/**
	 * Item of the Gauge control
	 */
	public static class GaugeItem extends ClientHandlerImp implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = -6951486493172779771L;

		/**
		 * The absolute amount
		 */
		private double amount;

		/**
		 * optional tooltip text
		 */
		private String tooltip = null;

		/**
		 * optional hyperlink target attribute
		 */
		private String target = null;

		/**
		 * optional hyperlink
		 */
		private String hyperlink = null;

		/**
		 * Segments color
		 */
		private Color color = Color.GREEN;

		/**
		 * Constructor
		 */
		public GaugeItem() {
			super();
		}

		/**
		 * Constructor
		 *
		 * @param		amount The absolute amount
		 * @param		tooltip Tooltip text
		 * @param		color Color
		 */
		public GaugeItem(double amount, String tooltip, Color color) {
			super();

			this.amount		= amount;
			this.tooltip	= tooltip;
			this.color		= color;
		}

		/**
		 * Returns the absolute amount of the given
		 * segment. The Gaug Control will calculate
		 * the segments width from this value.
		 *
		 * @return		Amount
		 */
		public double getAmount() {
			return amount;
		}

		/**
		 * Returns the tooltip text for the given segment
		 *
		 * @return		Tooltip Text or <code>null</code>
		 */
		public String getTooltip() {
			return tooltip;
		}

		/**
		 * Returns the color of the given segment
		 *
		 * @return		Color
		 */
		public Color getColor() {
			return color;
		}

		/**
		 * Returns a hyperlink for the given segment
		 *
		 * @return		Hyperlink or <code>null</code>
		 */
		public String getHyperlink() {
			return hyperlink;
		}

		/**
		 * Returns the traget where to open the hyperlink
		 *
		 * @return		Target or <code>null</code>
		 */
		public String getTarget() {
			return target;
		}

		/**
		 * @param		d absolute amount
		 */
		public void setAmount(double d) {
			this.amount = d;
		}

		/**
		 * @param		color Color
		 */
		public void setColor(Color color) {
			this.color = color;
		}

		/**
		 * @param		string Hyperlink
		 */
		public void setHyperlink(String string) {
			hyperlink = string;
		}

		/**
		 * @param		string Target attribute
		 */
		public void setTarget(String string) {
			target = string;
		}

		/**
		 * @param		string tooltip
		 */
		public void setTooltip(String string) {
			tooltip = string;
		}
	}

	/**
	 * The optional text which is shown next to the gauge
	 */
	private String text = null;

	/**
	 * The segments which compose the gauge
	 */
	private Vector items = new Vector();

	/**
	 * Constructor
	 */
	public GaugeDataModelImp() {
		super();
	}

	/**
	 * Adds a ne Item to the gauge data model
	 *
	 * @param		item the gauge item to add
	 */
	public void addItem(GaugeItem item) {
		items.add(item);
	}

	/**
	 * Adds a ne Item to the gauge data model
	 *
	 * @param		amount The absolute amount
	 * @param		tooltip Tooltip text
	 * @param		color Color
	 */
	public void addItem(double amount, String tooltip, Color color) {
		items.add(new GaugeItem(amount, tooltip, color));
	}

	/**
	 * Retrieves the segment with the given index
	 *
	 * @param		index segments index
	 * @return		Segment Data
	 */
	public GaugeItem getItem(int index) {
		return (GaugeItem) items.get(index);
	}

	/**
	 * @see com.cc.framework.ui.model.GaugeDataModel#getAmount(int)
	 */
	public double getAmount(int index) {
		return getItem(index).getAmount();
	}

	/**
	 * @see com.cc.framework.ui.model.GaugeDataModel#getColor(int)
	 */
	public Color getColor(int index) {
		return getItem(index).getColor();
	}

	/**
	 * @see com.cc.framework.ui.model.GaugeDataModel#getText()
	 */
	public String getText() {
		return text;
	}

	/**
	 * @see com.cc.framework.ui.model.GaugeDataModel#getClientHandler(int)
	 */
	public ClientHandler getClientHandler(int index) {
		return getItem(index);
	}

	/**
	 * @see com.cc.framework.ui.model.GaugeDataModel#getHyperlink(int)
	 */
	public String getHyperlink(int index) {
		return getItem(index).getHyperlink();
	}

	/**
	 * @see com.cc.framework.ui.model.GaugeDataModel#getTarget(int)
	 */
	public String getTarget(int index) {
		return getItem(index).getTarget();
	}

	/**
	 * @see com.cc.framework.ui.model.GaugeDataModel#getTooltip(int)
	 */
	public String getTooltip(int index) {
		return getItem(index).getTooltip();
	}

	/**
	 * @see com.cc.framework.ui.model.GaugeDataModel#size()
	 */
	public int size() {
		return items.size();
	}

	/**
	 * Sets t which is displayed next to the gauge
	 *
	 * @param		string Text
	 */
	public void setText(String string) {
		this.text = string;
	}
}
