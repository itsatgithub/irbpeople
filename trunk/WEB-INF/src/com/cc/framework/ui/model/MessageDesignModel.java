/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/MessageDesignModel.java,v 1.5 2005/05/29 09:50:57 P001002 Exp $
 * $Revision: 1.5 $
 * $Date: 2005/05/29 09:50:57 $
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

import com.cc.framework.common.Severity;

/**
 * Designnmodel for the MessageControl
 * 
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version    $Revision: 1.5 $
 */
public interface MessageDesignModel extends ControlDesignModel, FrameContainer {

	/**
	 * Returns the Form Id
	 * @return	String
	 */
	public String getFormId();

	/**
	 * Sets the Form Id
	 * @param	formid	Form Id
	 */
	public void setFormId(String formid);

	/**
	 * Returns the Caption
	 * @return	String
	 */
	public String getCaption();

	/**
	 * Sets the Caption
	 * @param	caption	Caption
	 */
	public void setCaption(String caption);

	/**
	 * Returns the Detail String
	 * @return	String
	 */
	public String getDetail();

	/**
	 * Sets the Details
	 * @param	detail	Details
	 */
	public void setDetail(String detail);

	/**
	 * Returns the Severity of the Message
	 * @return	Severity
	 */
	public Severity getSeverity();
	
	/**
	 * sets the Severity
	 * @param	severity	Severity
	 */
	public void setSeverity(Severity severity);

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