/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/forms/FormElementRowTag.java,v 1.4 2005/08/02 19:13:05 P001002 Exp $
 * $Revision: 1.4 $
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
import com.cc.framework.ui.OrientationType;
import com.cc.framework.ui.control.FormGroupElement;


/**
 * Tag-Handler for the <code>row</code> Tag.
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.4 $
 * @since      1.0
 */
public class FormElementRowTag extends FormElementGroupTag implements InnerTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -8462833534093371291L;

	/**
	 * Constructor
	 */
	public FormElementRowTag() {
		super();
	}

	/**
	 * Creates the Group element
	 *
	 * @return		Group Element
	 */
	protected FormGroupElement doCreateGroup() {
		FormGroupElement row =  new FormGroupElement();
		row.setOrientation(OrientationType.HORIZONTAL);

		return row;
	}
}
