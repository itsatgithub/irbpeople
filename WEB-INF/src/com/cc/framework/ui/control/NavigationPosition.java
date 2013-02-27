/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/control/NavigationPosition.java,v 1.2 2005/02/16 18:03:02 P001001 Exp $
 * $Revision: 1.2 $
 * $Date: 2005/02/16 18:03:02 $
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

package com.cc.framework.ui.control;

/**
 * Position of page navigation elements
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.2 $
 */
public interface NavigationPosition {

	/** Top left */
	public int TOP_LEFT			= 0x01;

	/** Top center */
	public int TOP_CENTER		= 0x02;

	/** Top right */
	public int TOP_RIGHT		= 0x04;

	/** Bottom left */
	public int BOTTOM_LEFT		= 0x10;

	/** Bottom center */
	public int BOTTOM_CENTER	= 0x20;

	/** Bottom right */
	public int BOTTOM_RIGHT		= 0x40;
}
