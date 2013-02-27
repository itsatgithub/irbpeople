/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/CrumbsDataModel.java,v 1.3 2005/02/16 18:13:31 P001001 Exp $
 * $Revision: 1.3 $
 * $Date: 2005/02/16 18:13:31 $
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
 * Defines the methods which are necessary for the DataModel of a
 * CrumbsControl.
 *
 * @author    <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version   $Revision: 1.3 $
 * @since     1.3
 */
public interface CrumbsDataModel extends DataModel {

	/**
	 * Returns an Array with all Crumbs
	 * 
	 * @return	CrumbDesignModel
	 */
	public CrumbModel[] getCrumbs();

	/**
	 * Returns the total Number of Crumbs
	 * @return	int
	 */
	public int size();
}

