/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/TabsetStateModel.java,v 1.6 2005/07/04 14:22:43 P001002 Exp $
 * $Revision: 1.6 $
 * $Date: 2005/07/04 14:22:43 $
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

/**
 * Defines the Methods wich manages the state of the Tabset
 *
 * @author   <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version  $Revision: 1.6 $
 * @since    1.0
 */
public interface TabsetStateModel extends StateModel {

	/**
	 * State property for the selected page
	 */
	public String PROP_SELECTED		= "selected";

	/**
	 * State property for the first visible page
	 */
	public String PROP_FIRST		= "first";

	/**
	 * Returns the first visible TabPage of the tabSet
	 * 
	 * @return	String or <code>null</code>
	 */
	public String getFirstVisibleTab();

	/**
	 * Selects the first visible TabPage
	 * 
	 * @param	first The first visible TabPage
	 */
	public void setFirstVisibleTab(String first);

	/**
	 * Returns the current selected TabPage of the tabSet
	 * @return	String		
	 */
	public String getSelectedTab();

	/**
	 * Selects the specified TabPage
	 * @param	selected	TabPage to be selected
	 */
	public void setSelectedTab(String selected);

	/**
	 * Returns <code>true</code> if the TabPage is selected
	 * 
	 * @param	tab	TabDesignModel
	 * @return	boolean
	 */
	public boolean isSelected(TabDesignModel tab);
}