/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/GaugeDesignModel.java,v 1.5 2005/06/20 08:57:38 P001002 Exp $
 * $Revision: 1.5 $
 * $Date: 2005/06/20 08:57:38 $
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

import com.cc.framework.ui.AlignmentType;

/**
 * Design model for a gauge
 * 
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.5 $
 * @since      1.4
 */
public interface GaugeDesignModel extends ControlDesignModel {

	/**
	 * A text that is output in the body of the
	 * gauge element if there are no items available
	 *
	 * @return		String
	 */
	public String getEmptyText();

	/**
	 * Sets the text that is output in the body of the
	 * gauge element if there are no items available
	 *
	 * @param		emptyText	Text
	 */
	public void setEmptyText(String emptyText);
	
	/**
	 * Returns the alignment for the column
	 *
	 * @return the alignment for the column
	 */
	public AlignmentType getAlignment();

	/**
	 * Sets the alignment for the column
	 *
	 * @param alignment The alignment
	 */
	public void setAlignment(AlignmentType alignment);

	/**
	 * Returns the Filter setting
	 * @return	boolean 
	 */
	public boolean getFilter();

	/**
	 * Sets the Filter. If set to true, all String will be
	 * converted into valid HTML-strings
	 * 
	 * @param	filter <code>true</code> if Strings should be html encoded
	 */
	public void setFilter(boolean filter);
}
