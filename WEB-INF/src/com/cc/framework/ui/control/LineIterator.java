/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/control/LineIterator.java,v 1.12 2005/07/08 14:18:14 P001002 Exp $
 * $Revision: 1.12 $
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


/**
 * Interface for line iterators
 *
 * @author	  <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version	  $Revision: 1.12 $
 * @since     1.0
 */
public interface LineIterator {

	/**
	 * Checks if all items are proccessed
	 * @return	boolean
	 */
	public boolean done();

	/**
	 * Returns the Object at
	 * the current position
	 *
	 * @return	Object
	 */
	public Object current();

	/**
	 * Method current
	 *
	 * @param	property Bean Property Name
	 * @return	Property Value
	 */
	public Object current(String property);

	/**
	 * Method curentKey
	 * @return	String
	 */
	public String currentKey();

	/**
	 * Checks if the current iteration element has the
	 * given property
	 * 
	 * @param		property the property name
	 * @return		<code>true</code> if the current bean
	 * 				posesses a matching property
	 */
	public boolean isValidProperty(String property);

	/**
	 * Checks if the current Item is highlighted
	 *
	 * @return	boolean	True if the current item is selected
	 */
	public boolean isMarked();

	/**
	 * Returns the Check state
	 * <ul>
	 *   <li>-1 = The Item can not be cheked. It has no Property </li>
	 *   <li>0 = The Item is not checked</li>
	 *   <li>1 = The Item ist checked</li>
	 *   <li>2 = the state is undefined</li>
	 * </ul>
	 * @return int
	 */
	public int currentCheckState();

	/**
	 * Moves to the next element
	 */
	public void next();

	/**
	 * Moves to the previous element
	 */
	public void prev();

	/**
	 * resets the iterator to the first element of the
	 * collection
	 */
	public void reset();
}