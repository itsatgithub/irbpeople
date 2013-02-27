/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/control/FormButtonElement.java,v 1.20 2005/07/08 14:18:14 P001002 Exp $
 * $Revision: 1.20 $
 * $Date: 2005/07/08 14:18:14 $
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

import com.cc.framework.ui.AlignmentType;
import com.cc.framework.ui.model.imp.ButtonDesignModelImp;


/**
 * Used for a button within a form
 *
 * @author    <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version   $Revision: 1.20 $
 * @since     1.0
 */
public class FormButtonElement extends ButtonDesignModelImp implements FormElement {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 6304577154949655590L;

	/**
	 * Indicates if this is the default button
	 * on the form.
	 */
	private boolean def = false;

	/**
	 * The ColumnSpan of this element
	 */
	private int colspan = 1;

	/**
	 * This flag indicates that this element should be
	 * joined with the preceding element without a separator
	 */
	private boolean join = false;

	/**
	 * The horizontal alignemnt of the form element
	 */
	private AlignmentType alignment = null;

	/**
	 * The vertical alignemnt of the form element
	 */
	private AlignmentType valignment = null;

	// ----------------------------------
	//             methods
	// ----------------------------------

	/**
	 * Constructor
	 */
	public FormButtonElement() {
		super();
	}

	/**
	 * Returns the asDefault.
	 * @return boolean
	 */
	public boolean isDefault() {
		return def;
	}

	/**
	 * Sets the asDefault.
	 * @param def The asDefault to set
	 */
	public void setDefault(boolean def) {
		this.def = def;
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#getColSpan()
	 */
	public int getColSpan() {
		return colspan;
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#setColSpan(int)
	 */
	public void setColSpan(int colspan) {
		this.colspan = colspan;
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#joinElements()
	 */
	public boolean joinElements() {
		return join;
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#setJoin(boolean)
	 */
	public void setJoin(boolean join) {
		this.join = join;
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#getAlignment()
	 */
	public AlignmentType getAlignment() {
		return alignment;
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#getVAlignment()
	 */
	public AlignmentType getVAlignment() {
		return valignment;
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#setAlignment(com.cc.framework.ui.AlignmentType)
	 */
	public void setAlignment(AlignmentType alignment) {
		this.alignment = alignment;
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#setVAlignment(com.cc.framework.ui.AlignmentType)
	 */
	public void setVAlignment(AlignmentType valignment) {
		this.valignment = valignment;
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#getNoWrap()
	 */
	public boolean getNoWrap() {
		return false;
	}
}