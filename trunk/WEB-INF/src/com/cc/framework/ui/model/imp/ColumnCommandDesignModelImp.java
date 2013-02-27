/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/ColumnCommandDesignModelImp.java,v 1.5 2005/07/08 14:19:43 P001002 Exp $
 * $Revision: 1.5 $
 * $Date: 2005/07/08 14:19:43 $
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

import com.cc.framework.ui.AlignmentType;
import com.cc.framework.ui.control.ControlActionDef;
import com.cc.framework.ui.model.ColumnCommandDesignModel;
import com.cc.framework.ui.model.value.DeferredValue;

/**
 * Class ColumnAddDesignModelImp
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.5 $
 * @since       1.0
 */
public class ColumnCommandDesignModelImp extends ColumnDesignModelImp implements ColumnCommandDesignModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -8515642588555348174L;

	/**
	 * the column command
	 */
	private ControlActionDef command = null;

	/**
	 * The icon resource id
	 */
	private DeferredValue icon = null;

	/**
	 * Constructor
	 *
	 * @param		command the commande for this column
	 * @param		icon the icon resource id
	 * @param		width the column width
	 */
	public ColumnCommandDesignModelImp(ControlActionDef command, String icon, String width) {
		super();

		setWidth(width);
		setAlignment(AlignmentType.CENTER);

		this.command	= command;
		this.icon		= new DeferredValue(icon);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnCommandDesignModel#getCommand()
	 */
	public ControlActionDef getCommand() {
		return command;
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnCommandDesignModel#getIconResource()
	 */
	public String getIconResource() {
		return DeferredValue.toString(icon, getEnvironment());
	}
}
