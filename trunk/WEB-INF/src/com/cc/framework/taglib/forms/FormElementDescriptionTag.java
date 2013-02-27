/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/forms/FormElementDescriptionTag.java,v 1.21 2005/09/27 14:06:22 P001002 Exp $
 * $Revision: 1.21 $
 * $Date: 2005/09/27 14:06:22 $
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
import com.cc.framework.ui.control.FormHtmlElement;

/**
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision: 1.21 $
 */
public class FormElementDescriptionTag extends FormElementHtmlTag implements
		InnerTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -8298622013223721440L;

	/**
	 * Creates the html form element
	 * 
	 * @return html form element
	 */
	protected FormHtmlElement doCreateFormElement() {
		FormHtmlElement formElement = new FormHtmlElement();
		formElement.setJoin(true);
		formElement.setLabel("");

		return formElement;
	}

	/**
	 * Sets the optional description text
	 * 
	 * @param description
	 *            Discription
	 */
	public void setDescription(String description) {
		getFormElement().setHtml(description);
		getFormElement().setBodyInclude(false);
	}
}