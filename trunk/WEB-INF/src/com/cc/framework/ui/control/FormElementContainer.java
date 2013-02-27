/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/control/FormElementContainer.java,v 1.12 2005/04/18 10:27:15 P001002 Exp $
 * $Revision: 1.12 $
 * $Date: 2005/04/18 10:27:15 $
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

import com.cc.framework.security.Principal;
import com.cc.framework.ui.OrientationType;
import com.cc.framework.ui.model.AccessControlled;

/**
 * Container for form elements
 *
 * @author    <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version   Revision: 1.5 $
 * @since     1.0
 */
public interface FormElementContainer extends FormElement, AccessControlled {

	/**
	 * Adds a new child element to the container
	 *
	 * @param	element		The new form element
	 */
	public void addFormElement(FormElement element);

	/**
	 * Removes the specified form element from the container
	 *
	 * @param	element		The form element to remove
	 */
	public void removeFormElement(FormElement element);

	/**
	 * Returns a single elements in the container
	 *
	 * @param	index Elements index
	 * @return	FormElement
	 */
	public FormElement getFormElement(int index);

	/**
	 * Returns a list with all elements in the container
	 *
	 * @return	FormElement
	 */
	public FormElement[] getFormElements();

	/**
	 * Returns a list with all visible elements in the
	 * container fitered on the principals permissions
	 *
	 * @param	principal the principal object with the
	 * 			permissions
	 * @return	FormElement
	 */
	public FormElement[] getFormElements(Principal principal);

	/**
	 * Returns the number of elements in the container
	 *
	 * @return	int
	 */
	public int size();

	/**
	 * @return the orientation of the child elements
	 */
	public OrientationType getOrientation();

	/**
	 * Disables the forms border
	 *
	 * @param	show <code>true</code> if the forms frame should be shown
	 */
	public void setShowFrame(boolean show);

	/**
	 * @return	<code>true</code> if the frame should be shown
	 */
	public boolean showFrame();
}