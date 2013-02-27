/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/TreeGroupDataModel.java,v 1.4 2005/02/16 18:13:29 P001001 Exp $
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
 * Datamodell for the nodes of a tree
 *
 * @author    <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version   $Revision: 1.4 $
 * @since     1.0
 */
public interface TreeGroupDataModel extends TreeNodeDataModel {

	/**
	 * Returns the node for the specified index
	 *
	 * @param	index	The zero based index
	 * @return	A tree node.
	 */
	public TreeNodeDataModel getChild(int index);

	/**
	 * Adds a new node<br>
	 * <b>Note:</b> This method is also used to set the
	 * parent link of the new child node to <code>this</code>
	 * 
	 * @param		child Child-Knoten
	 */
	public void addChild(TreeNodeDataModel child);

	/**
	 * Returns the number of childs for this node or -1
	 * if the number of children is unknown.
	 *
	 * @return The number of child nodes.
	 */
	public int size();
}
