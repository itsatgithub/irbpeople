/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/TextPopupDesignModel.java,v 1.4 2005/02/16 18:13:31 P001001 Exp $
 * $Revision: 1.4 $
 * $Date: 2005/02/16 18:13:31 $
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
 * Designmodel for the textarea popup control
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.4 $
 */
public interface TextPopupDesignModel extends TextareaDesignModel {

	/**
	 * Returns the width of the popup window
	 * @return	int
	 */
	public String getPopupHeight();

	/**
	 * Sets the width of the popup window
	 * @param	height	window height
	 */
	public void setPopupHeight(String height);

	/**
	 * Returns the width of the popup window
	 * @return	int
	 */
	public String getPopupWidth();

	/**
	 * Sets the width of the popup window
	 * @param	width	window width
	 */
	public void setPopupWidth(String width);

	/**
	 * Returns the number of Rows in the popup window
	 * @return	int
	 */
	public int getPopupRows();

	/**
	 * Sets the number of Rows in the popup window
	 * @param	rows	Number of Rows
	 */		
	public void setPopupRows(int rows);
}
