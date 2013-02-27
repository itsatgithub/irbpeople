/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/ColumnGroupDesignModelImp.java,v 1.6 2005/07/08 14:19:42 P001002 Exp $
 * $Revision: 1.6 $
 * $Date: 2005/07/08 14:19:42 $
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

package com.cc.framework.ui.model.imp;

import java.util.ArrayList;

import com.cc.framework.security.Principal;
import com.cc.framework.ui.AlignmentType;
import com.cc.framework.ui.model.ColumnDesignModel;
import com.cc.framework.ui.model.ColumnGroupDesignModel;

/**
 * Implementation of the Column group
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.6 $
 */
public class ColumnGroupDesignModelImp
	extends ColumnDesignModelImp
	implements ColumnGroupDesignModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -6527731494271202510L;

	/**
	 * The buffer with the columns of the list control
	 */
	private ArrayList columns = new ArrayList();

	/**
	 * Constructor
	 */
	public ColumnGroupDesignModelImp() {
		super();

		setAlignment(AlignmentType.CENTER);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnContainer#addColumn(com.cc.framework.ui.model.ColumnDesignModel)
	 */
	public void addColumn(ColumnDesignModel column) {
		synchronized (columns) {
			columns.add(column);
		}
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnContainer#getColumns()
	 */
	public ColumnDesignModel[] getColumns() {
		synchronized (columns) {
			ColumnDesignModel[] list = new ColumnDesignModel[columns.size()];

			return ((ColumnDesignModel[]) columns.toArray(list));
		}
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnContainer#removeColumn(com.cc.framework.ui.model.ColumnDesignModel)
	 */
	public void removeColumn(ColumnDesignModel column) {
		synchronized (columns) {
			columns.remove(column);
		}
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnContainer#size()
	 */
	public int size() {
		return columns.size();
	}

	/**
	 * A collumn group is visible when the user has the permission to see
	 * the group and and at least one child column is visible
	 *
	 * @see com.cc.framework.ui.model.AccessControlled#show(Principal)
	 */
	public boolean show(Principal principal) {

		if (super.show(principal)) {
			// A collumn group is visible when at
			// least one child column is visible
			for (int i = 0; i < columns.size(); i++) {
				ColumnDesignModel childColumn = (ColumnDesignModel) columns.get(i);
				if (childColumn.show(principal)) {
					return true;
				}
			}
		}

		// Hide the column group
		return false;
	}
}
