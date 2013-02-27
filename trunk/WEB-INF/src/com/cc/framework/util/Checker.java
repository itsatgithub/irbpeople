/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/util/Checker.java,v 1.15 2005/02/16 18:03:25 P001001 Exp $
 * $Revision: 1.15 $
 * $Date: 2005/02/16 18:03:25 $
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

package com.cc.framework.util;

import com.cc.framework.common.Algorithm;
import com.cc.framework.ui.SelectMode;
import com.cc.framework.ui.model.Checkable;

/**
 * Algorithm for changing the check state in a collection of
 * objects which implements the <code>checkable</code> interfaces
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version	   $Revision: 1.15 $
 * @see        com.cc.framework.ui.model.Checkable
 * @since      1.0
 */
public class Checker implements Algorithm {

	/**
	 *  The select mode
	 */
	private SelectMode mode	= SelectMode.NONE;

	/**
	 * Object whose state should be changed
	 */
	private String key		= null;

	/**
	 * Type of selection (true if the items should be checked)
	 */
	private boolean check	= false;

	/**
	 * Constructor for Checker
	 *
	 * @param	mode	The select mode
	 * @param	check	<code>true</code> if the Items should be checked, <code>false</code> otherwise
	 * @see		com.cc.framework.ui.SelectMode
	 */
	public Checker(SelectMode mode, boolean check) {
		super();

		this.mode	= mode;
		this.key	= null;
		this.check	= check;
	}

	/**
	 * Constructor for Checker
	 *
	 * @param	mode	The select mode
	 * @param	key		Key of the object whose state should be changed
	 * @param	check	<code>true</code> if the Items should be checked, <code>false</code> otherwise
	 * @see		com.cc.framework.ui.SelectMode
	 */
	public Checker(SelectMode mode, String key, boolean check) {
		super();

		this.mode	= mode;
		this.key	= key;
		this.check	= check;
	}

	/**
	 * @see com.cc.framework.common.Algorithm#execute(String uniqueId, Object obj)
	 */
	public boolean execute(String uniqueId, Object obj) {

		if (obj instanceof Checkable) {
			Checkable cb = (Checkable) obj;

			if ((key == null) || key.equals(uniqueId)) {

				int newState = check ? 1 : 0;

				// only change state if necessary
				if (cb.getCheckState() != newState) {
					cb.setCheckState(newState);
				}
			} else {
				if (SelectMode.SINGLE.equals(mode)) {
					// uncheck all other selected items.
					// only one item can be checked
					if (check && (cb.getCheckState() != 0)) {
						cb.setCheckState(0);
					}
				}
			}
		}

		// process the next element
		return true;
	}
}