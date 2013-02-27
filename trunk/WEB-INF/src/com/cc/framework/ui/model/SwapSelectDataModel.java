/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/SwapSelectDataModel.java,v 1.3 2005/03/03 10:02:02 P001002 Exp $
 * $Revision: 1.3 $
 * $Date: 2005/03/03 10:02:02 $
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
 * Datamodel for the Swap Select Control
 * 
 * @author   <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version  $Revision: 1.3 $
 * @since    1.4.082
 */
public interface SwapSelectDataModel extends DataModel {

	/**
	 * Returns the keys for the selected Item
	 * @return	String
	 */
	public Object[] getValues();
	
	/**
	 * Sets the key for the selected Item
	 * @param values Values (item to be selected)
	 */
	public void setValues(Object[] values);
}
