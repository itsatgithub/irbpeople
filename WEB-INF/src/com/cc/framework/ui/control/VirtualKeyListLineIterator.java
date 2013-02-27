/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/control/VirtualKeyListLineIterator.java,v 1.6 2005/07/08 14:19:11 P001002 Exp $
 * $Revision: 1.6 $
 * $Date: 2005/07/08 14:19:11 $
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
import com.cc.framework.ui.model.Checkable;
import com.cc.framework.ui.model.ListStateModel;
import com.cc.framework.ui.model.imp.VirtualKeyListDataModel;
import com.cc.framework.util.Util;

/**
 * Class VirtualKeyListLineIterator iterates only the in memory
 * Rows of a virtual key List
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.6 $
 */
public class VirtualKeyListLineIterator implements LineIterator {

	/**
	 * The current position
	 */
	private int current							= -1;

	/**
	 * The state model
	 */
	private ListStateModel state				= null;

	/**
	 * The data model
	 */
	private VirtualKeyListDataModel dataModel	= null;

	/**
	 * The principal object with the users permission
	 */
	private Principal principal					= null;


	/**
	 * Constructor
	 *
	 * @param	state		The Statemodel
	 * @param	dataModel	The Datamodell for the list to be proccessed
	 * @param	principal	The principal object
	 */
	public VirtualKeyListLineIterator(ListStateModel state, VirtualKeyListDataModel dataModel, Principal principal) {
		super();

		this.state		= state;
		this.dataModel	= dataModel;
		this.current	= 0;
		this.principal	= principal;
	}

	/**
	 * @see com.cc.framework.ui.control.LineIterator#done()
	 */
	public boolean done() {
		if ((dataModel == null) || (current < 0)) {
			return true;
		} else {
			return current >= dataModel.capacity();
		}
	}

	/**
	 * @see com.cc.framework.ui.control.LineIterator#current()
	 */
	public Object current() {
		return dataModel.getElementAt(dataModel.getKeysetIndex() + current);
	}

	/**
	 * @see com.cc.framework.ui.control.LineIterator#current(String property)
	 */
	public Object current(String property) {
		if (property == null) {
			return null;
		}

		Object bean = current();

		if (bean == null) {
			return null;
		}

		return Util.getSafeProperty(bean, property);
	}

	/**
	 * @see com.cc.framework.ui.control.LineIterator#isValidProperty(java.lang.String)
	 */
	public boolean isValidProperty(String property) {
		if (property == null) {
			return false;
		}

		Object bean = current();

		if (bean == null) {
			return false;
		}

		return Util.isValidProperty(bean, property);
	}

	/**
	 * @see com.cc.framework.ui.control.LineIterator#currentKey()
	 */
	public String currentKey() {
		return dataModel.getUniqueKey(dataModel.getKeysetIndex() + current);
	}

	/**
	 * @see com.cc.framework.ui.control.LineIterator#currentCheckState()
	 */
	public int currentCheckState() {

		int checkState = -1;

		Object curLine = current();

		// Wenn der Knoten die Markierungseigenschaft besitz, dann
		// kann der Markierungszustand bestimmt werden
		if (curLine instanceof Checkable) {
			Checkable checkLine = (Checkable) curLine;

			checkState = checkLine.getCheckState();
		}

		return checkState;
	}

	/**
	 * Returns the current row index
	 *
	 * @return		row index
	 */
	public int currentIndex() {
		return current;
	}

	/**
	 * @see com.cc.framework.ui.control.LineIterator#next()
	 */
	public void next() {
		++current;
	}

	/**
	 * @see com.cc.framework.ui.control.LineIterator#prev()
	 */
	public void prev() {
		--current;
	}

	/**
	 * @see com.cc.framework.ui.control.LineIterator#isMarked()
	 */
	public boolean isMarked() {
		if (state == null) {
			return false;
		} else {
			return state.isMarked(currentKey());
		}
	}

	/**
	 * @see com.cc.framework.ui.control.LineIterator#reset()
	 */
	public void reset() {
		if (dataModel == null) {
			current = -1;
		} else {
			current = 0;
		}
	}

	/**
	 * @return Returns the principal.
	 */
	public Principal getPrincipal() {
		return principal;
	}
}