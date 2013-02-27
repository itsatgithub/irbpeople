/*
 * $Header$
 * $Revision$
 * $Date$
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

package com.cc.framework.ui.model;

/**
 * Interface for checkable appointments
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision$
 */
public interface CheckableAppointment {

	/**
	 * Returns the check state of an object.
	 *
	 * <ul>
	 *   <li>-2 = Don't show any checkboxes</li>
	 *   <li>-1 = The object/node is not selectable</li>
	 *   <li> 0 = The object/node is not checked</li>
	 *   <li> 1 = The object/node is checked</li>
	 *   <li> 2 = The check sate is undefined</li>
	 * </ul>
	 * 
	 * @param	timeInMillis The Timestamp (for recurring appointments)
	 * @return int
	 */
	public int getCheckState(long timeInMillis);

	/**
	 * Sets the check state of the object
	 * 
	 * @param	timeInMillis The Timestamp (for recurring appointments)
	 * @param	newState	New state
	 */
	public void setCheckState(long timeInMillis, int newState);
}