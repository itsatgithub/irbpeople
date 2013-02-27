/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/common/TupleArray.java,v 1.11 2005/07/08 14:13:42 P001002 Exp $
 * $Revision: 1.11 $
 * $Date: 2005/07/08 14:13:42 $
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

package com.cc.framework.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Array for managing Tuples
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.11 $
 * @since      1.0
 * @see        com.cc.framework.common.Tuple
 */
public class TupleArray implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 5639964999059032041L;

	/**
	 * The buffer into which the elements are stored.
	 */
	private ArrayList tuples = null;

	/**
	 * Constructs an empty list
	 */
	public TupleArray() {
		super();

		this.tuples	= new ArrayList();
	}

	/**
     * Appends the specified tuple to this list.
     *
     * @param	tuple	Tuple to be appended to this list.
	 */
	public void add(Tuple tuple) {
		this.tuples.add(tuple);
	}

	/**
	 * Creates a new Tuple and adds it to the list
	 *
	 * @param	a	Object1
	 * @param	b	Object2
	 */
	public void add(Object a, Object b) {
		this.tuples.add(new Tuple(a, b));
	}

	/**
	 * Returns the element at the specified position in this list.
	 *
	 * @param	index	Index of element to return.
	 * @return	Tuple	The element at the specified position in this list.
	 */
	public Tuple get(int index) {
		return (Tuple) tuples.get(index);
	}

	/**
	 * Returns the A-Value form the tuple at the specified position in this list.
	 *
	 * @param	index	Index of element to return.
	 * @return	Object
	 */
	public Object a(int index) {

		Tuple t	= (Tuple) tuples.get(index);

		return t.a();
	}

	/**
	 * Returns all A-Values form the tuple list.
	 *
	 * @return		Collection of A-Values
	 */
	public Collection a() {
		ArrayList result = new ArrayList();

		Iterator i = tuples.iterator();
		while (i.hasNext()) {
			Tuple t	= (Tuple) i.next();
			
			result.add(t.a());
		}

		return result;
	}

	/**
	 * Returns the B-Value form the tuple at the specified position in this list.
	 *
	 * @param	index	Index of element to return.
	 * @return	Object
	 */
	public Object b(int index) {

		Tuple t	= (Tuple) tuples.get(index);

		return t.b();
	}

	/**
	 * Returns all B-Values form the tuple list.
	 *
	 * @return		Collection of B-Values
	 */
	public Collection b() {
		ArrayList result = new ArrayList();

		Iterator i = tuples.iterator();
		while (i.hasNext()) {
			Tuple t	= (Tuple) i.next();
			
			result.add(t.b());
		}

		return result;
	}

	/**
	 * Tests if this list has no elements.
	 *
     * @return  <tt>true</tt> if this list has no elements;
     *          <tt>false</tt> otherwise.
	 */
	public boolean isEmpty() {
		return tuples.isEmpty();
	}

	/**
     * Returns the number of elements in this list.
     *
     * @return	The number of elements in this list.
	 */
	public int size() {
		return tuples.size();
	}

	/**
     * Removes all of the elements from this list.
     * The list will be empty after this call returns.
	 */
	public void clear() {
		tuples.clear();
	}
}
