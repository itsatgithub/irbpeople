/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/controls/ColumnBaseBodyTag.java,v 1.15 2005/08/24 17:51:18 P001002 Exp $
 * $Revision: 1.15 $
 * $Date: 2005/08/24 17:51:18 $
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

package com.cc.framework.taglib.controls;

import java.util.Hashtable;

import javax.servlet.jsp.JspException;

import com.cc.framework.Globals;
import com.cc.framework.ui.control.LineIterator;
import com.cc.framework.ui.model.ColumnDesignModel;
import com.cc.framework.ui.painter.PainterContextAtributes;
import com.cc.framework.ui.painter.PainterHelp;
import com.cc.framework.util.StringHelp;

/**
 * Base Tag-Handler for columns with Body Tagsupport
 *
 * @author      <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version     $Revision: 1.15 $
 * @since       1.0
 */
public abstract class ColumnBaseBodyTag extends ColumnBaseTag {

	/**
	 * Object to iterate over the Elements of the
	 * current page
	 */
	private LineIterator iterator = null;

	/**
	 * Number of elements on the current page
	 */
	private int rowcount = 0;

	/**
	 * The index of the current row
	 */
	private int rowindex = 0;

	/**
	 * Key of the current row bean
	 */
	private String rowkey;

	/**
	 * The current row bean
	 */
	private Object rowbean = null;

	/**
	 * Indicates if the current row is editable
	 */
	private boolean roweditable = false;

	/**
	 * The column property of the current row
	 */
	private Object rowproperty = null;

	/**
	 * Constructor
	 */
	public ColumnBaseBodyTag() {
		super();
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#release()
	 */
	public void release() {
		super.release();

		iterator	= null;
		rowcount	= 0;
		rowindex	= 0;
		rowkey		= null;
		rowbean		= null;
		roweditable	= false;

		super.release();
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {

		super.doStartTag();

		iterator	= getContainer().getLineIterator();
		rowcount	= getContainer().getRowsPerPage();
		rowindex	= 0;

		// Save the PainterContext attributes because
		// they will be modified while we iterate the list rows
		getContainer().getPainterContext().pushAttributes();
		
		// select the first element
		return selectNext();
	}

	/**
	 * @see javax.servlet.jsp.tagext.IterationTag#doAfterBody()
	 */
	public int doAfterBody() throws JspException {

		// Render the output from this iteration to the output stream
		if (bodyContent != null) {
			String bodyText = bodyContent.getString();

			if (!StringHelp.isWhitespaceOnlyStr(bodyText)) {
				// We don't paint the body at this time, so
				// store it for later use

				Hashtable ht = (Hashtable) pageContext.getAttribute(Globals.JSPBODYBUFFER_KEY);
				if (ht == null) {
					ht = new Hashtable();
					pageContext.setAttribute(Globals.JSPBODYBUFFER_KEY, ht);
				}

				String hashkey = Integer.toHexString(getDesignModel().hashCode()) + "@" + rowkey;
				ht.put(hashkey, bodyText);
			}

			bodyContent.clearBody();
		}

		return selectNext();
	}
	
	/**
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {
		getContainer().getPainterContext().popAttributes();

		return super.doEndTag();
	}

	/**
	 * Returns the key of the current iteration element
	 *
	 * @return		current key
	 */
	public String getCurrentKey() {
		return rowkey;
	}

	/**
	 * Returns the column property of the current iteration element
	 *
	 * @return		current property
	 */
	public Object getCurrentProperty() {
		return rowproperty;
	}

	/**
	 * Returns the current iteration element
	 *
	 * @return		iteration element
	 */
	public Object getCurrent() {
		return rowbean;
	}

	/**
	 * Checks if the current row is editable
	 *
	 * @return		returns <code>true</code> if the row is
	 * 				editable
	 */
	public boolean isCurrentEditable() {
		return roweditable;
	}

	/**
	 * Checks if the column or cell is editable or not
	 *
	 * @param		iter the iterator with the current row bean
	 * @return		returns <code>true</code> if the cell is
	 * 				editable
	 */
	protected boolean isEditable(LineIterator iter) {
		ColumnDesignModel column = getDesignModel();

		if (column.isEditable()) {
			return true;
		} else if (column.getEditableProperty() != null) {
			return PainterHelp.toBoolean(iter.current(column.getEditableProperty()), false);
		}

		// THe column is not editable
		return false;
	}

	/**
	 * This method selects th next element of the iteration
	 *
	 * @return		returns <code>EVAL_BODY_BUFFERED</code>
	 * 				when ther was a valid iteration element
	 * 				and <code>SKIP_BODY</code> otherwise
	 */
	protected int selectNext() {

		if (!iterator.done() && ((rowcount == -1) || (rowindex < rowcount))) {
			rowkey		= iterator.currentKey();
			rowbean		= iterator.current();
			roweditable	= isEditable(iterator);
			rowproperty	= null;

			if (getDesignModel().getProperty() != null) {
				rowproperty = iterator.current(getDesignModel().getProperty());
			}

			// Expose the elements as scripting variables
			if (getId() != null) {
				if (rowkey != null) {
					pageContext.setAttribute(getId() + ColumnBaseTei.ROWKEY_SUFFIX, rowkey);
				}

				if (rowbean != null) {
					pageContext.setAttribute(getId(), rowbean);
				}
			}

			// Set some painter context attributes for the current row
			getContainer().getPainterContext().setAttribute(
				PainterContextAtributes.DISPLAY,
				roweditable ? null : Boolean.TRUE);

			getContainer().getPainterContext().setAttribute(
				PainterContextAtributes.COLUMN,
				getDesignModel().getProperty());

			getContainer().getPainterContext().setAttribute(
				PainterContextAtributes.ROWKEY,
				rowkey);

			// go to the next row
			iterator.next();

			++rowindex;

			return EVAL_BODY_BUFFERED;
		} else {
			if ((getId() != null) && (rowindex > 0)) {
				// Remove the elements from the last iteration
				pageContext.removeAttribute(getId() + ColumnBaseTei.ROWKEY_SUFFIX);
				pageContext.removeAttribute(getId());
			}

			return SKIP_BODY;
		}
	}
}