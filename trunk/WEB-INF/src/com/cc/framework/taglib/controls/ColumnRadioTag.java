/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/controls/ColumnRadioTag.java,v 1.5 2005/07/08 14:17:11 P001002 Exp $
 * $Revision: 1.5 $
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

import com.cc.framework.ui.model.ColumnDesignModel;
import com.cc.framework.ui.model.ColumnRadioDesignModel;
import com.cc.framework.ui.model.imp.ColumnRadioDesignModelImp;

/**
 * Tag-Handler for the <code>columnradio</code> Tag.
 * <p>
 * Generates an radio button column
 *
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz </a>
 * @version $Revision: 1.5 $
 * @since 1.3
 */
public class ColumnRadioTag extends ColumnTextTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 8837486074166051468L;

	/**
	 * Constructor
	 */
	public ColumnRadioTag() {
		super();
	}

	/**
	 * @see com.cc.framework.taglib.controls.ColumnBaseTag#doCreateDesignModel()
	 */
	public ColumnDesignModel doCreateDesignModel() {
		return new ColumnRadioDesignModelImp();
	}

	/**
	 * Specifies the value that must match to show the radio button as selected
	 *
	 * @param value
	 *            the value
	 */
	public void setValue(String value) {
		((ColumnRadioDesignModel) getDesignModel()).setValue(value);
	}
}