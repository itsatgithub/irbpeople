/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/CrumbsDesignModelImp.java,v 1.8 2005/07/08 15:15:32 P001002 Exp $
 * $Revision: 1.8 $
 * $Date: 2005/07/08 15:15:32 $
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

import com.cc.framework.ui.ImageMap;
import com.cc.framework.ui.model.CrumbModel;
import com.cc.framework.ui.model.CrumbsDesignModel;

/**
 * Designmodel for the CrumbsControl
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.8 $
 * @since      1.3
 */
public class CrumbsDesignModelImp extends ControlDesignModelImp	implements CrumbsDesignModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 576014732990171547L;

	/**
	 * Buffer for the crumbs of the CrumbControl
	 */
	private ArrayList crumbs = new ArrayList();

	/**
	 * The length of the labels. If a label exceed
	 * this length it will be cut.
	 */
	private int labelLength = -1;

	/**
	 * The ImageMap which can be used to paint
	 * icons in front of the labels.
	 */
	private ImageMap imagemap = null;

	/**
	 * Specifies if all String should be converted
	 * into there HTML representation
	 */
	private boolean filter = true;

	// --------------------------------
	//          methods
	// --------------------------------

	/**
	 * Constructor for CrumbsDesignModelImp
	 */
	public CrumbsDesignModelImp() {
		super();
	}

	/**
	 * @see com.cc.framework.ui.model.CrumbsDesignModel#addCrumb(com.cc.framework.ui.model.CrumbModel)
	 */
	public CrumbsDesignModel addCrumb(CrumbModel newCrumb) {

		synchronized (crumbs) {
			crumbs.add(newCrumb);
		}

		return this;
	}

	/**
	 * @see com.cc.framework.ui.model.CrumbsDesignModel#removeCrumb(com.cc.framework.ui.model.CrumbModel)
	 */
	public void removeCrumb(CrumbModel crumb) {

		synchronized (crumbs) {
			crumbs.remove(crumb);
		}
	}

	/**
	 * @see com.cc.framework.ui.model.CrumbsDesignModel#getCrumbs()
	 */
	public CrumbModel[] getCrumbs() {

		synchronized (crumbs) {
			CrumbModel[] list = new CrumbModel[crumbs.size()];

			return ((CrumbModel[]) crumbs.toArray(list));
		}
	}

	/**
	 * @see com.cc.framework.ui.model.CrumbsDesignModel#size()
	 */
	public int size() {
		return crumbs.size();
	}

	/**
	 * @see com.cc.framework.ui.model.CrumbsDesignModel#getImageMap()
	 */
	public ImageMap getImageMap() {
		return imagemap;
	}

	/**
	 * @see com.cc.framework.ui.model.CrumbsDesignModel#setImageMap(com.cc.framework.ui.ImageMap)
	 */
	public void setImageMap(ImageMap imagemap) {
		this.imagemap = imagemap;
	}

	/**
	 * @see com.cc.framework.ui.model.CrumbsDesignModel#getLabelLength()
	 */
	public int getLabelLength() {
		return labelLength;
	}

	/**
	 * @see com.cc.framework.ui.model.CrumbsDesignModel#setLabelLength(int)
	 */
	public void setLabelLength(int length) {
		this.labelLength = length;
	}

	/**
	 * @see com.cc.framework.ui.model.CrumbsDesignModel#filter()
	 */
	public boolean filter() {
		return filter;
	}

	/**
	 * @see com.cc.framework.ui.model.CrumbsDesignModel#setFilter(boolean)
	 */
	public void setFilter(boolean filter) {
		this.filter = filter;
	}
}