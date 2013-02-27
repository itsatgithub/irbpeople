/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/controls/ColumnDeleteTag.java,v 1.11 2005/07/08 14:17:11 P001002 Exp $
 * $Revision: 1.11 $
 * $Date: 2005/07/08 14:17:11 $
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

package com.cc.framework.taglib.controls;

import com.cc.framework.ui.control.ControlActionDef;
import com.cc.framework.ui.model.ColumnDesignModel;
import com.cc.framework.ui.model.imp.ColumnCommandDesignModelImp;
import com.cc.framework.ui.painter.def.DefResources;

/**
 * Tag-Handler for the <code>columndelete</code> Tag.
 * <p>
 * Generates a Delete column. The Delete column offers
 * the user a button to delete the relevant line.
 * The onDelete-Eventhandler of the assigned action is fired.
 * With the property attribute, it is possible to control
 * whether the button should be offered in a particular line.
 * The <columndelete>-tag may only be used within a Taghandler
 * which implements the com.cc.framework.taglib.controls.ColumnContainerTag
 * Interface. These are e.g. the <list>- or the <treelist>-tag.
 *
 * @author      <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version     $Revision: 1.11 $
 * @since       1.0
 */
public class ColumnDeleteTag extends ColumnBaseTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 4372643439396385795L;

	/**
	 * Constructor
	 */
	public ColumnDeleteTag() {
		super();
	}

	/**
	 * @see com.cc.framework.taglib.controls.ColumnBaseTag#doCreateDesignModel()
	 */
	public ColumnDesignModel doCreateDesignModel() {
		return new ColumnCommandDesignModelImp(
			ControlActionDef.ACTION_DELETE,
			DefResources.ICON_DELETE,
			"50");
	}
}
