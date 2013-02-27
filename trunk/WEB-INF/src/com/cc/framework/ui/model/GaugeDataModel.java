/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/GaugeDataModel.java,v 1.2 2005/02/16 18:13:30 P001001 Exp $
 * $Revision: 1.2 $
 * $Date: 2005/02/16 18:13:30 $
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
 
package com.cc.framework.ui.model;

import com.cc.framework.ui.Color;

/**
 * Datamodel for the Gauge Control
 * 
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.2 $
 * @since      1.4
 */
public interface GaugeDataModel extends DataModel {

	/**
	 * Returns the text that should be desplayed next to
	 * the gauge
	 * 
	 * @return		text or <code>null</code>
	 */
	public String getText();

	/**
	 * Returns the absolute amount of the given
	 * segment. The Gaug Control will calculate
	 * the segments width from this value.
	 *  
	 * @param		index segments index
	 * @return		Amount
	 */
	public double getAmount(int index);

	/**
	 * Returns the tooltip text for the given segment
	 * 
	 * @param		index segments index
	 * @return		Tooltip Text or <code>null</code>
	 */
	public String getTooltip(int index);

	/**
	 * Returns a hyperlink for the given segment
	 * 
	 * @param		index segment index
	 * @return		Hyperlink or <code>null</code>
	 */
	public String getHyperlink(int index);

	/**
	 * Returns the traget where to open the hyperlink
	 * 
	 * @param		index segment index
	 * @return		Target or <code>null</code>
	 */
	public String getTarget(int index);

	/**
	 * Returns a set of client Handlers for this segment
	 * 
	 * @param		index segment index
	 * @return		Client Handler or <code>null</code>
	 */
	public ClientHandler getClientHandler(int index);

	/**
	 * Returns the color of the given segment
	 * 
	 * @param		index segments index
	 * @return		Color
	 */
	public Color getColor(int index);

	/**
	 * Returns the number of segments of the gauge
	 * 
	 * @return		size
	 */
	public int size();
}