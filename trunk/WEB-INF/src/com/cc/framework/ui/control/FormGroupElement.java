/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/control/FormGroupElement.java,v 1.11 2005/04/18 10:27:15 P001002 Exp $
 * $Revision: 1.11 $
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

import java.util.ArrayList;
import java.util.Iterator;

import com.cc.framework.security.Permission;
import com.cc.framework.security.Principal;
import com.cc.framework.security.StaticPermission;
import com.cc.framework.ui.AlignmentType;
import com.cc.framework.ui.OrientationType;

/**
 * Group of form elements
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.11 $
 */
public class FormGroupElement implements FormElementContainer {

	/**
	 * caption
	 */
	private String title = null;

	/**
	 * The orientation of the elements in this group
	 */
	private OrientationType orientation = OrientationType.VERTICAL;

	/**
	 * Child form elements
	 */
	private ArrayList elements = new ArrayList();

	/**
	 * The ColumnSpan of this group
	 */
	private int colspan = 0;

	/**
	 * This flag indicates that this element should be
	 * joined with the preceding element without a separator
	 */
	private boolean join = false;

	/**
	 * Reference to an image in the ImageMap
	 * of the form which should be displayed in front
	 * of the label.
	 */
	private String imageRef = null;

	/**
	 * The horizontal alignemnt of the form element
	 */
	private AlignmentType alignment = null;

	/**
	 * The vertical alignemnt of the form element
	 */
	private AlignmentType valignment = null;

	/**
	 * The height of the form element
	 */
	private String height = null;

	/**
	 * The width of the form element
	 */
	private String width = null;

	/**
	 * Style ID
	 */
	private String styleId = null;

	/**
	 * Style Class -> HTML class Attribut
	 */
	private String styleClass = null;

	/**
	 * Html-Style
	 */
	private String style = null;

	/**
	 * The Help Identifier for this element
	 */
	private String help = null;

	/**
	 * Enables or disables the forms frame
	 */
	private boolean showFrame = false;

	/**
	 * permissions for the element
	 */
	private Permission permission = StaticPermission.GRANTED;

	/**
	 * @see com.cc.framework.ui.control.FormElementContainer#addFormElement(com.cc.framework.ui.control.FormElement)
	 */
	public void addFormElement(FormElement element) {
		elements.add(element);
	}

	/**
	 * @see com.cc.framework.ui.control.FormElementContainer#getFormElement(int)
	 */
	public FormElement getFormElement(int index) {
		return (FormElement) elements.get(index);
	}

	/**
	 * @see com.cc.framework.ui.control.FormElementContainer#getFormElements()
	 */
	public FormElement[] getFormElements() {
		FormElement[] list = new FormElement[elements.size()];

		return ((FormElement[]) elements.toArray(list));
	}

	/**
	 * @see com.cc.framework.ui.control.FormElementContainer#getFormElements(com.cc.framework.security.Principal)
	 */
	public FormElement[] getFormElements(Principal principal) {
		ArrayList granted = new ArrayList();

		for (int i = 0; i < elements.size(); i++) {
			FormElement element = (FormElement) elements.get(i);

			if (element.show(principal)) {
				granted.add(element);
			}
		}

		FormElement[] list = new FormElement[granted.size()];
		return ((FormElement[]) granted.toArray(list));
	}

	/**
	 * @see com.cc.framework.ui.control.FormElementContainer#removeFormElement(FormElement)
	 */
	public void removeFormElement(FormElement element) {
		elements.remove(element);
	}

	/**
	 * @see com.cc.framework.ui.control.FormElementContainer#size()
	 */
	public int size() {
		return elements.size();
	}

	/**
	 * @see com.cc.framework.ui.model.AccessControlled#getPermission()
	 */
	public Permission getPermission() {
		return permission;
	}

	/**
	 * Sets the elements permission
	 * 
	 * @param		permission required permission
	 */
	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	/**
	 * @see com.cc.framework.ui.model.AccessControlled#show(Principal)
	 */
	public boolean show(Principal principal) {
		if (permission.isGranted(principal)) {
			// check if this group contains any visible
			// children
			for (int i = 0; i < elements.size(); i++) {
				FormElement element = (FormElement) elements.get(i);

				if (element.show(principal)) {
					// at least one element is visible
					// so show the container
					return true;
				}
			}
		}

		// Container is not visible or ha no visible elements
		return false;
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#getColSpan()
	 */
	public int getColSpan() {
		if (colspan != 0) {
			// The colspan for this group is fixed   
			return colspan;
		} else {
			// calculate the actual needed column span
			// of this group
			int cols = 0;
	
			Iterator iter = elements.iterator();
			while (iter.hasNext()) {
				FormElement element = (FormElement) iter.next();
	
				if (orientation.equals(OrientationType.VERTICAL)) {
					// vertical orientation (from top to bottom)
					cols = Math.max(cols, element.getColSpan());
				} else {
					// horizontal orientation (from left to right)
					cols += element.getColSpan();
				}
			}
	
			return Math.max(cols, 1);
		}
	}

	/**
	 * @return		the orientation of the child elements
	 */
	public OrientationType getOrientation() {
		return orientation;
	}

	/**
	 * @param		type the orientation of the child elements
	 */
	public void setOrientation(OrientationType type) {
		orientation = type;
	}

	/**
	 * Returns the title.
	 * @return String
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 * @param title The title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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
	 * Assigns the Crumb an Image from an ImageMap
	 * @param	ref	Rule of the ImageMap to match
	 */
	public void setImageRef(String ref) {
		this.imageRef = ref;
	}

	/**
	 * Returns the assignment Rule
	 * @return	String
	 */
	public String getImageRef() {
		return imageRef;
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
	 * @see com.cc.framework.ui.control.FormElement#getHeight()
	 */
	public String getHeight() {
		return height;
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#getWidth()
	 */
	public String getWidth() {
		return width;
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#setHeight(java.lang.String)
	 */
	public void setHeight(String height) {
		this.height = height;
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#setWidth(java.lang.String)
	 */
	public void setWidth(String width) {
		this.width = width;
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#getNoWrap()
	 */
	public boolean getNoWrap() {
		return false;
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#getStyle()
	 */
	public String getStyle() {
		return style;
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#getStyleId()
	 */
	public String getStyleId() {
		return styleId;
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#getStyleClass()
	 */
	public String getStyleClass() {
		return styleClass;
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#setStyle(java.lang.String)
	 */
	public void setStyle(String style) {
		this.style = style;
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#setStyleId(java.lang.String)
	 */
	public void setStyleId(String styleId) {
		this.styleId = styleId;
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#setStyleClass(java.lang.String)
	 */
	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}


	/**
	 * @see com.cc.framework.ui.control.FormElement#getHelp()
	 */
	public String getHelp() {
		return help;
	}

	/**
	 * @see com.cc.framework.ui.control.FormElement#setHelp(java.lang.String)
	 */
	public void setHelp(String helpId) {
		this.help = helpId;
	}

	/**
	 * @see com.cc.framework.ui.control.FormElementContainer#setShowFrame(boolean)
	 */
	public void setShowFrame(boolean show) {
		this.showFrame = show;
	}

	/**
	 * @see com.cc.framework.ui.control.FormElementContainer#showFrame()
	 */
	public boolean showFrame() {
		return showFrame;
	}
}