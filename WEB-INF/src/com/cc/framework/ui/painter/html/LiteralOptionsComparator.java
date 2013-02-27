/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/html/LiteralOptionsComparator.java,v 1.7 2005/02/25 12:12:51 P001002 Exp $
 * $Revision: 1.7 $
 * $Date: 2005/02/25 12:12:51 $
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

package com.cc.framework.ui.painter.html;


/**
 * Options Comparator for String literals
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.7 $
 */
public class LiteralOptionsComparator implements OptionsComparator {

	/**
	 * String Literals for comparison
	 */
	private Object[] match = null;

	/**
	 * Constructor
	 * 
	 * @param		value The Literal
	 */
	public LiteralOptionsComparator(Object value) {
		super();

		this.match = OptionsHelp.toArray(value);
	}

	/**
	 * Constructor
	 * 
	 * @param		values The Literal array
	 */
	public LiteralOptionsComparator(Object[] values) {
		super();

		this.match = values;
	}

	/**
	 * Metode match
	 * @param	value	String to macth
	 * @return	boolean
	 */
	public boolean match(Object value) {
		boolean matching = false;

		if ((value == null) || (match == null)) {
			matching = false;
		} else {
			for (int i = 0; !matching && (i < match.length); i++) {
				if (match[i] != null) {
					if (match[i].getClass().equals(value.getClass())) {
						// Compare compatible types
						matching = value.equals(match[i]);	
					} else {
						// Fall back to String compare
						// So the framework is more robust
						matching = value.toString().equals(match[i].toString());
					}
				}
			}
		}

		return matching;
	}
}
