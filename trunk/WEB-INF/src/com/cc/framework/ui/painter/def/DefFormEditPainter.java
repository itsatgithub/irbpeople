/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/DefFormEditPainter.java,v 1.22 2005/02/16 18:03:20 P001001 Exp $
 * $Revision: 1.22 $
 * $Date: 2005/02/16 18:03:20 $
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

package com.cc.framework.ui.painter.def;

import com.cc.framework.ui.control.FormControl;
import com.cc.framework.ui.painter.PainterContext;

/**
 * Painter for forms that could be edited / have input fields
 *
 * @author	    <a href="mailto:hschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version	    $Revision: 1.22 $
 * @since       1.0
 */
public class DefFormEditPainter extends DefFormPainter {

	/**
	 * Constructor for DefFormEditPainter
	 *
	 * @param	painterContext	The PainterContext
	 * @param	ctrl			The Control to render
	 */
	public DefFormEditPainter(PainterContext painterContext, FormControl ctrl) {
		super(painterContext, ctrl);
	}
}