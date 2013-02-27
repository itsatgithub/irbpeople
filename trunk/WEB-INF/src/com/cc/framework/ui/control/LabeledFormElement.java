/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/control/LabeledFormElement.java,v 1.5 2005/02/16 18:03:02 P001001 Exp $
 * $Revision: 1.5 $
 * $Date: 2005/02/16 18:03:02 $
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

package com.cc.framework.ui.control;

import com.cc.framework.ui.model.FormLabelDesignModel;

/**
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.5 $
 */
public interface LabeledFormElement extends FormElement {

	/**
	 * Return the Label which should be display
	 * in front of the Form element
	 *
	 * @return	String
	 */
	public FormLabelDesignModel getLabel();

	/**
	 * Returns the Description text which should be displayed
	 * under the Formelement
	 *
	 * @return	String
	 */
	public String getDescription();

	/**
	 * The ACCESSKEY attribute can be used to specify a shortcut
	 * key for the &lt;LABEL&gt; (activated by pressing 'Alt' and
	 * the ACCESSKEY togther).
	 *
	 * @param		accessKey The Access Key
	 */
	public void setAccessKey(String accessKey);

	/**
	 * Sets the tooltip text for the label
	 *
	 * @param		tooltip Tooltip text
	 */
	public void setLabelTooltip(String tooltip);
}