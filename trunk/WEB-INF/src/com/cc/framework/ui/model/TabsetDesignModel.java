/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/TabsetDesignModel.java,v 1.8 2005/05/27 16:05:01 P001002 Exp $
 * $Revision: 1.8 $
 * $Date: 2005/05/27 16:05:01 $
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
import com.cc.framework.ui.ImageMap;

/**
 * DesignModel for a Tabset
 *
 * @author   <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version  $Revision: 1.8 $
 * @since    1.0
 */
public interface TabsetDesignModel extends ControlDesignModel {

	/**
	 * Adds a TabPag to a TabSet
	 * 
	 * @param	tab	TabsetDesignModel
	 * @return	TabsetDesignModel
	 */
	public TabsetDesignModel addTab(TabDesignModel tab);

	/**
	 * Removes a TabPage from a TabSet
	 * 
	 * @param tab Tab to be removed
	 */
	public void removeTab(TabDesignModel tab);

	/**
	 * Returns an Array with all TabPages
	 * 
	 * @return	TabDesignModel
	 */
	public TabDesignModel[] getTabs();

	/**
	 * Returns the total Number of TabPages
	 * @return	int
	 */
	public int size();

	/**
	 * Returns the number of visible TabPages.
	 * -1 means that all Pages are visible
	 * @return	int
	 */
	public int getMaxVisible();

	/**
	 * Sets the number of visible TabPages.
	 * -1 means that all Pages are visible.
	 * @param	max	Number of visible TabPages.
	 */
	public void setMaxVisible(int max);

	/**
	 * Sets the Background Color
	 * @param	bgcolor	Background Color	
	 */
	public void setBgColor(Color bgcolor);

	/**
	 * Returns the Background Color
	 * @return	Color
	 */
	public Color getBgColor();

	/**
	 * Sets the ImageMap for the TabSet
	 * @param	map	ImageMap
	 */
	public void setImageMap(ImageMap map);

	/**
	 * Returns the ImageMap
	 * @return	ImageMap
	 */
	public ImageMap getImageMap();

	/**
	 * Sets the maximal length for labels shown on a TabPage
	 * If a label is longer as the size defined here,
	 * it will be cut.
	 * 
	 * @param	length	LabelLength	
	 */
	public void setLabelLength(int length);

	/**
	 * Returns the maximal length for labels
	 * @return	int
	 */
	public int getLabelLength();

	/**
	 * Returns if the filter is activated (default=true). This means
	 * that all Strings which should be displayed in the HTML page
	 * are html encoded
	 *
	 * @return	<code>true</code> if string will be html encoded;
	 * 			<code>false</code> otherwise
	 */
	public boolean filter();

	/**
	 * Activates the html encoding (filter). Default is true. This
	 * means that all Strings which should be displayed in the HTML page
	 * will be html encoded.
	 *
	 * @param		filter <code>true</code> if strings should be html encoded; false otherwise
	 */
	public void setFilter(boolean filter);
}
