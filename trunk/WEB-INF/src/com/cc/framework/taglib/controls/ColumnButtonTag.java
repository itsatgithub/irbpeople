/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/controls/ColumnButtonTag.java,v 1.12 2005/07/08 14:17:10 P001002 Exp $
 * $Revision: 1.12 $
 * $Date: 2005/07/08 14:17:10 $
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

import com.cc.framework.ui.model.ColumnButtonDesignModel;
import com.cc.framework.ui.model.ColumnDesignModel;
import com.cc.framework.ui.model.imp.ColumnButtonDesignModelImp;

/**
 * Tag-Handler for the <code>column-button</code> Tag.
 * <p>
 * Generates a column with a button.
 * The onCellClick Eventhandler of the assigned action is fired.
 * With the property attribute, it is possible to control whether
 * the button should be offered in a particular line.
 * The <columnbutton>-tag may only be used within a Taghandler which
 * implements the com.cc.framework.taglib.controls.ColumnContainerTag
 * Interface. These are e.g. the <list>- or the <treelist>-tag.
 *
 * @author      <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version     $Revision: 1.12 $
 * @since       1.0
 */
public class ColumnButtonTag extends ColumnBaseTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 5513113618190755072L;

	/**
	 * Constructor
	 */
	public ColumnButtonTag() {
		super();
	}

	/**
	 * @see ColumnBaseTag#doCreateDesignModel()
	 */
	public ColumnDesignModel doCreateDesignModel() {
		return new ColumnButtonDesignModelImp();
	}

	/**
	 * Sets the Image
	 *
	 * @param 	image	Image
	 */
	public void setImage(String image) {
		((ColumnButtonDesignModel) getDesignModel()).setImage(image);
	}

	/**
	 * Sets the text.
	 *
	 * @param		text Text
	 */
	public void setText(String text) {
		((ColumnButtonDesignModel) getDesignModel()).setText(text);
	}

	/**
	 * Sets the name of the command that
	 * should bet triggered
	 *
	 * @param		command The Command name
	 */
	public void setCommand(String command) {
		((ColumnButtonDesignModel) getDesignModel()).setCommand(command);
	}
}