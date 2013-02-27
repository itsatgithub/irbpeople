/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/CrumbsStateModel.java,v 1.4 2005/07/04 14:22:48 P001002 Exp $
 * $Revision: 1.4 $
 * $Date: 2005/07/04 14:22:48 $
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
 * Defines the Methods wich manages the state of the Crumcontrol
 *
 * @author   <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version  $Revision: 1.4 $
 * @since    1.3
 */
public interface CrumbsStateModel extends StateModel {

	/**
	 * State property for selected element
	 */
	public String PROP_SELECTED = "selected";

	/**
	 * Returns the current selected crumb
	 * @return	String		
	 */
	public String getSelectedCrumb();

	/**
	 * Selects the specified crumb
	 * @param	selected crumb to be selected
	 */
	public void setSelectedCrumb(String selected);

	/**
	 * Returns <code>true</code> if the Crumb is selected
	 * 
	 * @param	crumb	CrumbModel
	 * @return	boolean
	 */
	public boolean isSelected(CrumbModel crumb);
}

