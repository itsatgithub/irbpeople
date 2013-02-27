/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/forms/FormButtonTag.java,v 1.9 2005/08/02 19:13:05 P001002 Exp $
 * $Revision: 1.9 $
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

import javax.servlet.jsp.JspException;

import com.cc.framework.taglib.InnerTag;
import com.cc.framework.taglib.TagHelp;
import com.cc.framework.taglib.WebResourceTag;
import com.cc.framework.taglib.controls.ButtonTag;
import com.cc.framework.ui.control.FormButtonElement;
import com.cc.framework.ui.model.ControlDesignModel;

/**
 * Tag-Handler for the ButtonTag.
 * <p>
 * The tag generates a Form button.
 * The tag may only be used within a <forms:buttonsection>-tag.
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.9 $
 * @since      1.0
 */
public class FormButtonTag extends ButtonTag  implements InnerTag, WebResourceTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -8503606275517321710L;

	/**
	 * Constructor
	 */
	public FormButtonTag() {
		super();
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateDesignModel()
	 */
	public ControlDesignModel doCreateDesignModel() {
		return new FormButtonElement();
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {
		FormElementContainerTag container = TagHelp.getContainer(this);

		// Um dem Container eventuelle Layout Aktionen zu ermöglichen
		// wird lediglich das Modell des Elementes im Container
		// registriert
		container.addFormElement((FormButtonElement) getDesignModel());

		return SKIP_BODY;
	}

	/**
	 * Sets the default Button.
	 * @param def true if it's the default button
	 * @throws JspException if the argument can not be convert to a boolean value
	 */
	public void setDefault(String def) throws JspException {
		((FormButtonElement) getDesignModel()).setDefault(TagHelp.toBoolean(def));
	}
}