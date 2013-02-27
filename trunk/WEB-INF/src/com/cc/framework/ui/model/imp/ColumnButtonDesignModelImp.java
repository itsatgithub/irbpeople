/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/ColumnButtonDesignModelImp.java,v 1.12 2005/07/08 14:19:43 P001002 Exp $
 * $Revision: 1.12 $
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

import com.cc.framework.ui.model.ColumnButtonDesignModel;
import com.cc.framework.ui.model.ImageModel;
import com.cc.framework.ui.model.value.DeferredValue;

/**
 * Designmodel for button columns
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.12 $
 * @since       1.0
 */
public class ColumnButtonDesignModelImp extends ColumnDesignModelImp implements ColumnButtonDesignModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 2956361401409132920L;

	/**
	 * ImageModel
	 */
	private DeferredValue image = null;

	/**
	 * Text
	 */
	private DeferredValue text = null;

	/**
	 * Optional command name
	 */
	private DeferredValue command = null;

	/**
	 * @see com.cc.framework.ui.model.ColumnButtonDesignModel#getImage()
	 */
	public ImageModel getImage() {
		return DeferredValue.toImage(image, getEnvironment());
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnButtonDesignModel#setImage(ImageModel)
	 */
	public void setImage(ImageModel image) {
		this.image = new DeferredValue(image);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnButtonDesignModel#setImage(java.lang.String)
	 */
	public void setImage(String image) {
		this.image = DeferredValue.parseImage(image);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnButtonDesignModel#getCommand()
	 */
	public String getCommand() {
		return DeferredValue.toString(command, getEnvironment());
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnButtonDesignModel#setCommand(java.lang.String)
	 */
	public void setCommand(String command) {
		this.command = new DeferredValue(command);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnButtonDesignModel#getText()
	 */
	public String getText() {
		return DeferredValue.toString(text, getEnvironment());
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnButtonDesignModel#setText(java.lang.String)
	 */
	public void setText(String text) {
		this.text = new DeferredValue(text);
	}
}

