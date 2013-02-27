/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/common/Tuple.java,v 1.12 2005/07/08 14:13:42 P001002 Exp $
 * $Revision: 1.12 $
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

/**
 * Defines a Tupel with two Objects	(A, B)
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.12 $
 * @since      1.0
 */
public class Tuple implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 8530741878473610415L;

	/**
	 * A-Value
	 */
	private Object a	= null;

	/**
	 * B-Value
	 */
	private Object b	= null;

	/**
	 * Constructor for Tuple
	 */
	public Tuple() {
		super();
	}

	/**
	 * Constructor for Tuple
	 *
	 * @param	a	A-Value
	 * @param	b	B-Value
	 */
	public Tuple(Object a, Object b) {
		super();

		this.a	= a;
		this.b	= b;
	}

	/**
	 * Returns the A-Value
	 *
	 * @return	Object
	 */
	public Object a() {
		return this.a;
	}

	/**
	 * Returns the B-Value
	 *
	 * @return	Object
	 */
	public Object b() {
		return this.b;
	}

	/**
	 * @param		object A-Value
	 */
	public void a(Object object) {
		a = object;
	}

	/**
	 * @param		object B-Value
	 */
	public void b(Object object) {
		b = object;
	}

	/**
	 * Returns the Tuple (A, B)
	 *
	 * @return	String
	 */
	public String toString() {
		return "(" + String.valueOf(a) + "," + String.valueOf(b) + ")";
	}
}
