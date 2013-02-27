/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/forms/InfoTag.java,v 1.20 2005/08/02 19:13:05 P001002 Exp $
 * $Revision: 1.20 $
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
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTag;

import com.cc.framework.taglib.TagHelp;
import com.cc.framework.taglib.controls.BaseControlTag;
import com.cc.framework.ui.control.Control;
import com.cc.framework.ui.control.InfoControl;
import com.cc.framework.ui.model.ControlDesignModel;
import com.cc.framework.ui.model.InfoDesignModel;
import com.cc.framework.ui.model.imp.InfoDesignModelImp;

/**
 * Tag for the Info Tag
 * <p>
 * The tag generates an information field The actual information
 * text is specified in the tag-body or as a pointer to other HTTP-resources.
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.20 $
 * @since      1.0
 */
public class InfoTag extends BaseControlTag implements BodyTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 4881726152874611965L;

	/**
	 * The Body Content of the Tag
	 */
	private BodyContent bodyContent = null;

	/**
	 * Constructor
	 */
	public InfoTag() {
		super();
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateDesignModel()
	 */
	protected ControlDesignModel doCreateDesignModel() {
		return new InfoDesignModelImp();
	}

	/**
	 * Returns a casted version of the ControlDesignModel
	 *
	 * @return		InfoDesignModel
	 */
	protected InfoDesignModel getInfoDesignModel() {
		return (InfoDesignModel) getDesignModel();
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#setPageContext(PageContext pc)
	 */
	public void setPageContext(PageContext pc) {
		super.setPageContext(pc);

		// create a new model
		bodyContent = null;
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#release()
	 */
	public void release() {
		super.release();

		bodyContent = null;
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateControl()
	 */
	protected Control doCreateControl() throws JspException {
		InfoDesignModel designModel = getInfoDesignModel();

		// Das Kontrollelement für das Formular erzeugen
		InfoControl ctrl = new InfoControl();
		ctrl.setDesignModel(designModel);

		return ctrl;
	}

	/**
	 * @see javax.servlet.jsp.tagext.BodyTag#doInitBody()
	 */
	public void doInitBody() throws JspException {
	}

	/**
	 * @see javax.servlet.jsp.tagext.BodyTag#setBodyContent(javax.servlet.jsp.tagext.BodyContent)
	 */
	public void setBodyContent(BodyContent b) {
		bodyContent = b;
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {
		super.doStartTag();

		return EVAL_BODY_BUFFERED;
	}

	/**
	 * @see javax.servlet.jsp.tagext.IterationTag#doAfterBody()
	 */
	public int doAfterBody() throws JspException {
		// Render the output from this iteration to the output stream
		if ((bodyContent != null) && (bodyContent.getString() != null)) {
			String bodyText = bodyContent.getString().trim();

			if (bodyText.length() > 0) {
				// We don't paint the body at this time, so
				// store it for later use
				getInfoDesignModel().setContent(bodyText);
			}

			bodyContent.clearBody();
		}

		return SKIP_BODY;
	}

	/**
	 * Pointer to an HTTP-resource that specifies
	 * the contents of the information field.
	 *
	 * @param resource	Resource
	 */
	public void setResource(String resource) {
		getInfoDesignModel().setResource(resource);
	}

	/**
	 * Sets the base directory
	 *
	 * @param		string pathname or a resource key
	 */
	public void setBase(String string) {
		getInfoDesignModel().setBase(string);
	}

	/**
	 * Enables or Disables the frames border
	 *
	 * @param	border	<code>true</code> if the frames
	 * 			should be visible
	 * @throws	JspException If the Argument can't be converted to boolean
	 */
	public void setNoframe(String border) throws JspException {
		getInfoDesignModel().setShowFrame(!TagHelp.toBoolean(border));
	}
}