/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/forms/FormButtonSectionTag.java,v 1.13 2005/08/02 19:13:05 P001002 Exp $
 * $Revision: 1.13 $
 * $Date: 2005/08/02 19:13:05 $
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

package com.cc.framework.taglib.forms;

import com.cc.framework.taglib.InnerTag;
import com.cc.framework.ui.control.FormButtonContainer;
import com.cc.framework.ui.control.FormGroupElement;

/**
 * Tag-Handler for the <code>buttonsection</code> Tag.
 * <p>
 * The tag generates a section for buttons in the enclosing form.
 * The tag may only be used within a <forms:form>-tag.
 *
 * @author      <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version     $Revision: 1.13 $
 * @since      1.0
 */
public class FormButtonSectionTag extends FormElementGroupTag implements InnerTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 8009562133067071975L;

	/**
	 * Constructor
	 */
	public FormButtonSectionTag() {
		super();
	}

	/**
	 * Creates the Group element
	 *
	 * @return		Group Element
	 */
	protected FormGroupElement doCreateGroup() {
		return new FormButtonContainer();
	}

	/**
	 * Sets the Id of the default button, which should be pressesd
	 * if the user hits the enter-key.
	 *
	 * @param	defaultButton The id of the default button
	 */
	public void setDefault(String defaultButton) {
		((FormButtonContainer) getGroup()).setDefaultButtonId(defaultButton);
	}
}