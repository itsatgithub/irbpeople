/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/global/IncludeElement.java,v 1.2 2005/07/08 14:16:31 P001002 Exp $
 * $Revision: 1.2 $
 * $Date: 2005/07/08 14:16:31 $
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

package com.cc.framework.ui.painter.global;

import java.io.PrintWriter;

import javax.servlet.jsp.PageContext;

import org.apache.ecs.ConcreteElement;
import org.apache.ecs.Element;
import org.apache.ecs.StringElement;

/**
 * ECS element for page includes
 *
 * @author      <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version     $Revision: 1.2 $
 * @since       1.0
 */
public class IncludeElement extends ConcreteElement {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -4973953443355185774L;

	/**
	 * The PageContext
	 */
	private PageContext pageContext = null;


	/**
	 * Constructor for IncludeElement
	 */
	public IncludeElement() {
		super();
	}

	/**
	 * Constructor for IncludeElement
	 *
	 * @param	pageContext	The PageContext
	 * @param	string		Include
	 */
	public IncludeElement(PageContext pageContext, String string) {
		super();

		if (string != null) {
			setTagText(string);
		} else {
			setTagText("");
		}

		this.pageContext = pageContext;
	}

	/**
	 * Resets the interal string to be empty.
	 * @return	IncludeElement
	 */
	public IncludeElement reset() {
		setTagText("");
		return this;
	}

	/**
	 * Adds an Element to the element.
	 * @param  hashcode name of element for hash table
	 * @param  element Adds an Element to the element.
	 * @return	IncludeElement
	 */
	public IncludeElement addElement(String hashcode, Element element) {
		addElementToRegistry(hashcode, element);
		return (this);
	}

	/**
	 * Adds an Element to the element.
	 * @param  hashcode name of element for hash table
	 * @param  element Adds an Element to the element.
	 * @return	IncludeElement
	 */
	public IncludeElement addElement(String hashcode, String element) {
		StringElement se = new StringElement(element);
		se.setFilterState(getFilterState());
		se.setFilter(getFilter());
		addElementToRegistry(hashcode, se);
		return (this);
	}

	/**
	 * Adds an Element to the element.
	 * @param  element Adds an Element to the element.
	 * @return	IncludeElement
	 */
	public IncludeElement addElement(String element) {
		addElement(Integer.toString(element.hashCode()), element);
		return (this);
	}

	/**
	 * Adds an Element to the element.
	 * @param  element Adds an Element to the element.
	 * @return	IncludeElement
	 */
	public IncludeElement addElement(Element element) {
		addElementToRegistry(element);
		return (this);
	}

	/**
	 * Removes an Element from the element.
	 * @param hashcode the name of the element to be removed.
	 * @return	IncludeElement
	 */
	public IncludeElement removeElement(String hashcode) {
		removeElementFromRegistry(hashcode);
		return (this);
	}

	/**
	 * @see org.apache.ecs.Element#output(java.io.PrintWriter)
	 */
	public void output(PrintWriter out) {

		out.flush();

		try {
			pageContext.include(getTagText());
		} catch (Exception e) {
			out.println(e.getMessage());
		}
	}
}