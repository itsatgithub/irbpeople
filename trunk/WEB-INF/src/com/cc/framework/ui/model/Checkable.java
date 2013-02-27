/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/Checkable.java,v 1.9 2005/02/16 18:03:22 P001001 Exp $
 * $Revision: 1.9 $
 * $Date: 2005/02/16 18:03:22 $
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
 * This interface can be implemented by (display) objects wich
 * want to use checkboxes. The implementaion is optional.
 *
 * @author    <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version   $Revision: 1.9 $
 * @since     1.0
 */
public interface Checkable {

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
	 * @return int
	 */
	public int getCheckState();

	/**
	 * Sets the check state of the object
	 * @param	newState	New state
	 */
	public void setCheckState(int newState);
}

