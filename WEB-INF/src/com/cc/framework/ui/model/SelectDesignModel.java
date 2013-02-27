/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/SelectDesignModel.java,v 1.6 2005/02/16 18:13:31 P001001 Exp $
 * $Revision: 1.6 $
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
 * Designmodel for the Select Control
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision: 1.6 $
 */
public interface SelectDesignModel extends ControlDesignModel {

	/**
	 * Returns the number of visibel characters
	 * for an input/text field.
	 * 
	 * @return	Number of character positions to allocate
	 */
	public int getSize();

	/**
	 * Sets the size of the input field.
	 *
	 * @param	size	The size of the input field.
	 */
	public void setSize(int size);

	/**
	 * Indicates that multiple selections is allowed.
	 * 
	 * @return		returns <code>true</code> for a multiple
	 * 				selection control
	 */
	public boolean isMultiple();

	/**
	 * Indicates that multiple selections is allowed.
	 * 
	 * @param		multiple <code<true</code> for multiple
	 * 				selection		
	 */
	public void setMultiple(boolean multiple);
}