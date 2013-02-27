/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/forms/FormElementSectionTag.java,v 1.6 2005/09/27 14:06:22 P001002 Exp $
 * $Revision: 1.6 $
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
import com.cc.framework.ui.control.FormGroupElement;


/**
 * Tag-Handler for the <code>section</code> Tag.
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.6 $
 * @since      1.0
 */
public class FormElementSectionTag extends FormElementGroupTag implements InnerTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -5784293003823782499L;

	/**
	 * Constructor
	 */
	public FormElementSectionTag() {
		super();
	}

	/**
	 * Creates the Group element
	 *
	 * @return		Group Element
	 */
	protected FormGroupElement doCreateGroup() {
		FormGroupElement group = new FormGroupElement();
		group.setJoin(true);

		return group;
	}

	/**
	 * The title of the group
	 *
	 * @param title	Title
	 */
	public void setTitle(String title) {
		getGroup().setTitle(title);
	}

	/**
	 * Sets the Reference to an Image in an ImageMap, which should be display in
	 * front of the Label
	 * 
	 * @param imageRef
	 *            Reference to an Image in an ImageMap
	 */
	public void setImageref(String imageRef) {
		getGroup().setImageRef(imageRef);
	}
}