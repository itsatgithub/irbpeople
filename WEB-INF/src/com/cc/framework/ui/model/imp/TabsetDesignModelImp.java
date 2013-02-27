/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/TabsetDesignModelImp.java,v 1.14 2005/07/08 15:15:32 P001002 Exp $
 * $Revision: 1.14 $
 * $Date: 2005/07/08 15:15:32 $
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

import com.cc.framework.ui.Color;
import com.cc.framework.ui.ImageMap;
import com.cc.framework.ui.model.TabDesignModel;
import com.cc.framework.ui.model.TabsetDesignModel;

/**
 * Designmodel for the TabSetControl
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.14 $
 * @since      1.0
 */
public class TabsetDesignModelImp extends ControlDesignModelImp implements TabsetDesignModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 6735488277678307553L;

	/**
	 * Buffer for the Tabs of the Tabset
	 */
	private ArrayList tabs = new ArrayList();

	/**
	 * The number of visible Tabs. If more Tabs
	 * exists you can scroll through the Tabs with
	 * the previous or next button.
	 */
	private int maxVisible = -1;

	/**
	 * The length of the labels. If a label exceed
	 * this length it will be cut.
	 */
	private int labelLength = -1;

	/**
	 * The ImageMap which can be used to paint
	 * icons in front of the labels.
	 */
	private ImageMap imagemap = null;

	/**
	 * The background color of the TabSet
	 */
	private Color bgcolor = null;

	/**
	 * Specifies if all String should be converted
	 * into there HTML representation
	 */
	private boolean filter = true;
 
	// --------------------------------
	//          methods
	// --------------------------------

	/**
	 * Constructor for TabsetDesignModelImp
	 */
	public TabsetDesignModelImp() {
		super();
	}

	/**
	 * @see com.cc.framework.ui.model.TabsetDesignModel#addTab(TabDesignModel)
	 */
	public TabsetDesignModel addTab(TabDesignModel newTab) {

		synchronized (tabs) {
			tabs.add(newTab);
		}

		return this;
	}

	/**
	 * @see com.cc.framework.ui.model.TabsetDesignModel#removeTab(TabDesignModel)
	 */
	public void removeTab(TabDesignModel tab) {

		synchronized (tabs) {
			tabs.remove(tab);
		}
	}

	/**
	 * @see com.cc.framework.ui.model.TabsetDesignModel#getTabs()
	 */
	public TabDesignModel[] getTabs() {

		synchronized (tabs) {
			TabDesignModel[] list = new TabDesignModel[tabs.size()];

			return ((TabDesignModel[]) tabs.toArray(list));
		}
	}

	/**
	 * @see com.cc.framework.ui.model.TabsetDesignModel#size()
	 */
	public int size() {
		return tabs.size();
	}

	/**
	 * @see com.cc.framework.ui.model.TabsetDesignModel#getMaxVisible()
	 */
	public int getMaxVisible() {
		return maxVisible;
	}

	/**
	 * @see com.cc.framework.ui.model.TabsetDesignModel#setMaxVisible(int)
	 */
	public void setMaxVisible(int max) {
		this.maxVisible = max;
	}

	/**
	 * @see com.cc.framework.ui.model.TabsetDesignModel#getBgColor()
	 */
	public Color getBgColor() {
		return bgcolor;
	}

	/**
	 * @see com.cc.framework.ui.model.TabsetDesignModel#setBgColor(com.cc.framework.ui.Color)
	 */
	public void setBgColor(Color bgcolor) {
		this.bgcolor = bgcolor;
	}

	/**
	 * @see com.cc.framework.ui.model.TabsetDesignModel#getImageMap()
	 */
	public ImageMap getImageMap() {
		return imagemap;
	}

	/**
	 * @see com.cc.framework.ui.model.TabsetDesignModel#setImageMap(com.cc.framework.ui.ImageMap)
	 */
	public void setImageMap(ImageMap imagemap) {
		this.imagemap = imagemap;
	}

	/**
	 * @see com.cc.framework.ui.model.TabsetDesignModel#getLabelLength()
	 */
	public int getLabelLength() {
		return labelLength;
	}

	/**
	 * @see com.cc.framework.ui.model.TabsetDesignModel#setLabelLength(int)
	 */
	public void setLabelLength(int length) {
		this.labelLength = length;
	}

	/**
	 * @see com.cc.framework.ui.model.TabsetDesignModel#filter()
	 */
	public boolean filter() {
		return filter;
	}

	/**
	 * @see com.cc.framework.ui.model.TabsetDesignModel#setFilter(boolean)
	 */
	public void setFilter(boolean filter) {
		this.filter = filter;
	}
}