/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/ScriptSupport.java,v 1.4 2005/02/16 18:03:12 P001001 Exp $
 * $Revision: 1.4 $
 * $Date: 2005/02/16 18:03:12 $
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


package com.cc.framework.taglib;

import com.cc.framework.ui.model.ClientHandler;

/**
 * Interface for all Tags that support JavaScript Client handlers
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.4 $
 */
public interface ScriptSupport {

	/**
	 * Returns the ClientHandler interface of the
	 * controls design model.
	 * 
	 * @return		ClientHandler
	 */
	public ClientHandler getClientHandler();

	// ======================================
	// Standard Client side event handler
	// ======================================

	/**
	 * Sets the onabort event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnabort(String handler);

	/**
	 * Sets the onactivate event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnactivate(String handler);

	/**
	 * Sets the onafterprint event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnafterprint(String handler);

	/**
	 * Sets the onafterupdate event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnafterupdate(String handler);

	/**
	 * Sets the onbeforeactivate event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnbeforeactivate(String handler);

	/**
	 * Sets the onbeforecopy event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnbeforecopy(String handler);

	/**
	 * Sets the onbeforecut event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnbeforecut(String handler);

	/**
	 * Sets the onbeforedeactivate event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnbeforedeactivate(String handler);

	/**
	 * Sets the onbeforeeditfocus event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnbeforeeditfocus(String handler);

	/**
	 * Sets the onbeforepaste event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnbeforepaste(String handler);

	/**
	 * Sets the onbeforeprint event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnbeforeprint(String handler);

	/**
	 * Sets the onbeforeunload event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnbeforeunload(String handler);

	/**
	 * Sets the onbeforeupdate event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnbeforeupdate(String handler);

	/**
	 * Sets the onblur event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnblur(String handler);

	/**
	 * Sets the onbounce event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnbounce(String handler);

	/**
	 * Sets the oncellchange event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOncellchange(String handler);

	/**
	 * Sets the onchange event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnchange(String handler);

	/**
	 * Sets the onclick event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnclick(String handler);

	/**
	 * Sets the oncontextmenu event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOncontextmenu(String handler);

	/**
	 * Sets the oncontrolselect event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOncontrolselect(String handler);

	/**
	 * Sets the oncopy event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOncopy(String handler);

	/**
	 * Sets the oncut event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOncut(String handler);

	/**
	 * Sets the ondataavailable event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOndataavailable(String handler);

	/**
	 * Sets the ondatasetchanged event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOndatasetchanged(String handler);

	/**
	 * Sets the ondatasetcomplete event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOndatasetcomplete(String handler);

	/**
	 * Sets the ondblclick event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOndblclick(String handler);

	/**
	 * Sets the ondeactivate event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOndeactivate(String handler);

	/**
	 * Sets the ondrag event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOndrag(String handler);

	/**
	 * Sets the ondragend event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOndragend(String handler);

	/**
	 * Sets the ondragenter event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOndragenter(String handler);

	/**
	 * Sets the ondragleave event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOndragleave(String handler);

	/**
	 * Sets the ondragover event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOndragover(String handler);

	/**
	 * Sets the ondragstart event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOndragstart(String handler);

	/**
	 * Sets the ondrop event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOndrop(String handler);

	/**
	 * Sets the onerror event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnerror(String handler);

	/**
	 * Sets the onerrorupdate event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnerrorupdate(String handler);

	/**
	 * Sets the onfilterchange event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnfilterchange(String handler);

	/**
	 * Sets the onfinish event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnfinish(String handler);

	/**
	 * Sets the onfocus event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnfocus(String handler);

	/**
	 * Sets the onfocusin event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnfocusin(String handler);

	/**
	 * Sets the onfocusout event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnfocusout(String handler);

	/**
	 * Sets the onhelp event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnhelp(String handler);

	/**
	 * Sets the onkeydown event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnkeydown(String handler);

	/**
	 * Sets the onkeypress event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnkeypress(String handler);

	/**
	 * Sets the onkeyup event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnkeyup(String handler);

	/**
	 * Sets the onlayoutcomplete event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnlayoutcomplete(String handler);

	/**
	 * Sets the onload event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnload(String handler);

	/**
	 * Sets the onlosecapture event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnlosecapture(String handler);

	/**
	 * Sets the onmousedown event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnmousedown(String handler);

	/**
	 * Sets the onmouseenter event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnmouseenter(String handler);

	/**
	 * Sets the onmouseleave event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnmouseleave(String handler);

	/**
	 * Sets the onmousemove event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnmousemove(String handler);

	/**
	 * Sets the onmouseout event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnmouseout(String handler);

	/**
	 * Sets the onmouseover event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnmouseover(String handler);

	/**
	 * Sets the onmouseup Eventhandler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnmouseup(String handler);

	/**
	 * Sets the onmousewheel event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnmousewheel(String handler);

	/**
	 * Sets the onmove event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnmove(String handler);

	/**
	 * Sets the onmoveend event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnmoveend(String handler);

	/**
	 * Sets the onmovestart event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnmovestart(String handler);

	/**
	 * Sets the onpaste event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnpaste(String handler);

	/**
	 * Sets the onpropertychange event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnpropertychange(String handler);

	/**
	 * Sets the onreadystatechange event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnreadystatechange(String handler);

	/**
	 * Sets the onreset event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnreset(String handler);

	/**
	 * Sets the onresize event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnresize(String handler);

	/**
	 * Sets the onresizeend event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnresizeend(String handler);

	/**
	 * Sets the onresizestart event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnresizestart(String handler);

	/**
	 * Sets the onrowenter event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnrowenter(String handler);

	/**
	 * Sets the onrowexit event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnrowexit(String handler);

	/**
	 * Sets the onrowsdelete event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnrowsdelete(String handler);

	/**
	 * Sets the onrowsinserted event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnrowsinserted(String handler);

	/**
	 * Sets the onscroll event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnscroll(String handler);

	/**
	 * Sets the onselect event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnselect(String handler);

	/**
	 * Sets the onselectionchange event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnselectionchange(String handler);

	/**
	 * Sets the onselectstart event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnselectstart(String handler);

	/**
	 * Sets the onstart event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnstart(String handler);

	/**
	 * Sets the onstop event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnstop(String handler);

	/**
	 * Sets the onsubmit event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnsubmit(String handler);

	/**
	 * Sets the onunload event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnunload(String handler);

	// ======================================
	// Extended Client side event handler
	// ======================================

	/**
	 * Sets the <b>extended</b> onexpand event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnexpand(String handler);

	/**
	 * Sets the <b>extended</b> onexpandex event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnexpandex(String handler);

	/**
	 * Sets the <b>extended</b> collapse event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOncollapse(String handler);

	/**
	 * Sets the <b>extended</b> onspinup event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnspinup(String handler);

	/**
	 * Sets the <b>extended</b> onspindown event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnspindown(String handler);

	/**
	 * Sets the <b>extended</b> oncheck event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOncheck(String handler);

	/**
	 * Sets the <b>extended</b> onuncheck event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnuncheck(String handler);

	/**
	 * Sets the <b>extended</b> oncheckall event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOncheckall(String handler);

	/**
	 * Sets the <b>extended</b> onuncheckall event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnuncheckall(String handler);
}
