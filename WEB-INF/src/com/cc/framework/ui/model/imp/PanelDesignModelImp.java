/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/PanelDesignModelImp.java,v 1.10 2005/07/08 15:15:31 P001002 Exp $
 * $Revision: 1.10 $
 * $Date: 2005/07/08 15:15:31 $
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

import com.cc.framework.ui.model.PanelContentDesignModel;
import com.cc.framework.ui.model.PanelDesignModel;

/**
 * Designmodel for the PanelControl
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.10 $
 * @since      1.0
 */
public class PanelDesignModelImp extends ControlDesignModelImp implements PanelDesignModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -1641586396903934899L;

	/**
	 * Buffer with the content of the panel
	 */
	private ArrayList contents	= new ArrayList();


	/**
	 * Constructor
	 */
	public PanelDesignModelImp() {
		super();
	}

	/**
	 * @see com.cc.framework.ui.model.PanelDesignModel#addContent(PanelContentDesignModel)
	 */
	public PanelDesignModel addContent(PanelContentDesignModel newContent) {

		synchronized (contents) {
			contents.add(newContent);
		}

		return this;
	}

	/**
	 * @see com.cc.framework.ui.model.PanelDesignModel#removeContent(PanelContentDesignModel)
	 */
	public void removeContent(PanelContentDesignModel content) {

		synchronized (contents) {
			contents.remove(content);
		}
	}

	/**
	 * @see com.cc.framework.ui.model.PanelDesignModel#getContent()
	 */
	public PanelContentDesignModel[] getContent() {

		synchronized (contents) {
			PanelContentDesignModel[] list = new PanelContentDesignModel[contents.size()];

			return ((PanelContentDesignModel[]) contents.toArray(list));
		}
	}

	/**
	 * @see com.cc.framework.ui.model.PanelDesignModel#size()
	 */
	public int size() {
		return contents.size();
	}
}
