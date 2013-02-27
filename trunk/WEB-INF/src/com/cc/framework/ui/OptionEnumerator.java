/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/OptionEnumerator.java,v 1.7 2005/02/16 18:03:23 P001001 Exp $
 * $Revision: 1.7 $
 * $Date: 2005/02/16 18:03:23 $
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

package com.cc.framework.ui;

/**
 * Iterator for creating <code>OPTION</code> Tags in a DropDownList Box
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.7 $
 * @since      1.0
 */
public interface OptionEnumerator {

	/**
	 * Checks if the End of the Collection is reached
     * @return  <code>true</code> if the end is reached;
     *          <code>false</code> otherwise.
	 */
	public boolean done();

	/**
	 * Returns the key, which is inserted in the
	 * value attribute of the option tag.
	 * @return	The Key
	 */
	public String getKey();

	/**
	 * Returns the text, wich should display
	 * @return	The display text
	 */
	public String getValue();

	/**
	 * Indicates if the actual item is selected.
     * @return  <code>true</code> if the actual item is selected;
     *          <code>false</code> otherwise.
	 */
	public boolean isSelected();

	/**
	 * Moves the iterator to the next elememt in the collection
	 */
	public void next();

	/**
	 * Starts with the collection at the beginning
	 */
	public void restart();
}
