/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/RadioDesignModel.java,v 1.6 2005/02/16 18:13:31 P001001 Exp $
 * $Revision: 1.6 $
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
 * Designmodel for radio buttons
 *
 * @author   <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version  $Revision: 1.6 $
 * @since    1.0
 */
public interface RadioDesignModel extends ControlDesignModel {

	/**
	 * Returns the Value for the RadioButton
	 * @return	String
	 */
	public String getValue();

	/**
	 * Sets the Value for the RadioButton
	 * @param value	Value
	 */
	public void setValue(String value);


	/**
	 * Sets the optional tooltip text
	 * 
	 * @param	tooltip	Tooltip
	 */
	public void setTooltip(String tooltip);

	/**
	 * Retrieves the optional tooltip text
	 * 
	 * @return	tooltip text or <code>null</code>
	 */
	public String getTooltip();
}