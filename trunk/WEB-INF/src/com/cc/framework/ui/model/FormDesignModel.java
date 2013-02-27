/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/FormDesignModel.java,v 1.10 2005/05/01 08:56:29 P001002 Exp $
 * $Revision: 1.10 $
 * $Date: 2005/05/01 08:56:29 $
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

import com.cc.framework.ui.FormMethod;
import com.cc.framework.ui.FormType;
import com.cc.framework.ui.ImageMap;
import com.cc.framework.ui.control.FormElementContainer;

/**
 * Interface for the FormDesignModel
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.10 $
 */
public interface FormDesignModel extends ControlDesignModel, FormElementContainer, FrameContainer {

	/**
	 * Returns the Formular Id
	 * @return	String
	 */
	public String getFormId();

	/**
	 * Sets the Formular Id
	 * @param	formid	Id
	 */
	public void setFormId(String formid);

	/**
	 * Returns the Action
	 * @return	String
	 */
	public String getAction();

	/**
	 * Sets the Action
	 * @param		action Action
	 */
	public void setAction(String action);

	/**
	 * Returns in which way Data should by sumbmited
	 * to the Server.
	 * @return	FormMethod
	 */
	public FormMethod getMethod();

	/**
	 * Sets the FormMethod
	 * @param	method	FormMethod
	 */
	public void setMethod(FormMethod method);

	/**
	 * Returns the FormType
	 * @return	FormType
	 */
	public FormType getFormType();

	/**
	 * Sets the FormType
	 * @param	type	FormType
	 */
	public void setFormType(FormType type);

	/**
	 * Returns the Caption of the Form
	 * @return	String
	 */
	public String getCaption();

	/**
	 * Sets the Caption
	 * @param	caption	Caption
	 */
	public void setCaption(String caption);

	/**
	 * Returns the Detail, which is
	 * displayed right of the Caption
	 * @return	String
	 */
	public String getDetail();

	/**
	 * Sets the Detail
	 * @param	detail	Detail String
	 */
	public void setDetail(String detail);

	/**
	 * Sets the ImageMap
	 * @param	map	ImageMap
	 */
	public void setImageMap(ImageMap map);

	/**
	 * Returns the ImageMap
	 * @return	ImageMap
	 */
	public ImageMap getImageMap();
}
