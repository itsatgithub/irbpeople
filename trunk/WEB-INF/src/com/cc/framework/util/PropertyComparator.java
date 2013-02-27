/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/util/PropertyComparator.java,v 1.5 2005/06/21 07:09:57 P001002 Exp $
 * $Revision: 1.5 $
 * $Date: 2005/06/21 07:09:57 $
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

import java.util.Comparator;

import com.cc.framework.common.SortOrder;

/**
 * Compares a property
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.5 $
 */
public class PropertyComparator implements Comparator {

	/**
	 * Property to compare
	 */
	private String property;

	/**
	 * Sortorder
	 */
	private SortOrder direction;

	/**
	 * Constructor
	 * @param	property	Property to Sort
	 * @param	direction	SortDirection
	 */
	public PropertyComparator(String property, SortOrder direction) {
		super();

		this.property	= property;
		this.direction	= direction;
	}

	/**
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Object o1, Object o2) {

		int result = 0;

		try {
			Object p1 = Util.getSafeProperty(o1, property);
			Object p2 = Util.getSafeProperty(o2, property);

			if ((p1 == null) && (p2 == null)) {
				result = 0;
			} else if (p1 == null) {
				result = -1;
			} else if (p2 == null) {
				result = 1;
			} else if (!p1.getClass().equals(p2.getClass())) {
				throw new IllegalArgumentException("Types are not comparable");
			} else if (p1 instanceof Comparable) {
				result = ((Comparable) p1).compareTo(p2);
			} else {
				throw new IllegalArgumentException("Types do not implement Comparable: " + p1.getClass().getName());
			}

			// Sort order
			if (SortOrder.DESCENDING.equals(direction)) {
				result *= -1;
			}
		} catch (Throwable t) {
			// No action
			throw new IllegalArgumentException(t.getMessage());
		}

		return result;
	}
}
