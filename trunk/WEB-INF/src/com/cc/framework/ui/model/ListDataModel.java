/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/ListDataModel.java,v 1.4 2005/02/16 18:13:29 P001001 Exp $
 * $Revision: 1.4 $
 * $Date: 2005/02/16 18:13:29 $
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
 * Datamodel for the ListControl
 *
 * @author    <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version   $Revision: 1.4 $
 * @version   1.0
 */
public interface ListDataModel extends DataModel {

	/**
	 * Returns the (row) object for the specified index.
	 * This can be a display object (view helper) whose
	 * properties will be accessed within the columns of
	 * a List- or TreeControl 
	 * 
	 * @param index	A zero based index
	 *
	 * @return A row object. 
	 */
	public Object getElementAt(int index);

	/**
	 * Returns the number of rows within the list.
	 *
	 * @return Number of rows
	 */
	public int size();

	/**
	 * Returns a unique key for the row which is specified by
	 * the index. This id will be attached in hyperlinks to
	 * identify the row/object. This can be a simple row index
	 * or a key which correspond to the primary key from the
	 * database.
	 *
	 * @param index A zero based index
	 *
	 * @return The unique key
	 */
	public String getUniqueKey(int index);
}

