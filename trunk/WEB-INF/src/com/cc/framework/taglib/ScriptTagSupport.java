/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/ScriptTagSupport.java,v 1.2 2005/02/16 18:03:12 P001001 Exp $
 * $Revision: 1.2 $
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

import javax.servlet.jsp.tagext.TagSupport;

import com.cc.framework.ui.model.ClientEvent;

/**
 * Baseclass for all simple Tags that support JavaScript Client handlers
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.2 $
 */
public abstract class ScriptTagSupport extends TagSupport implements ScriptSupport {

	// ======================================
	// Standard Client side event handler
	// ======================================

	/**
	 * Sets the onabort event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnabort(String handler) {
		getClientHandler().setHandler(ClientEvent.ONABORT, handler);
	}

	/**
	 * Sets the onactivate event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnactivate(String handler) {
		getClientHandler().setHandler(ClientEvent.ONACTIVATE, handler);
	}

	/**
	 * Sets the onafterprint event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnafterprint(String handler) {
		getClientHandler().setHandler(ClientEvent.ONAFTERPRINT, handler);
	}

	/**
	 * Sets the onafterupdate event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnafterupdate(String handler) {
		getClientHandler().setHandler(ClientEvent.ONAFTERUPDATE, handler);
	}

	/**
	 * Sets the onbeforeactivate event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnbeforeactivate(String handler) {
		getClientHandler().setHandler(ClientEvent.ONBEFOREACTIVATE, handler);
	}

	/**
	 * Sets the onbeforecopy event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnbeforecopy(String handler) {
		getClientHandler().setHandler(ClientEvent.ONBEFORECOPY, handler);
	}

	/**
	 * Sets the onbeforecut event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnbeforecut(String handler) {
		getClientHandler().setHandler(ClientEvent.ONBEFORECUT, handler);
	}

	/**
	 * Sets the onbeforedeactivate event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnbeforedeactivate(String handler) {
		getClientHandler().setHandler(ClientEvent.ONBEFOREDEACTIVATE, handler);
	}

	/**
	 * Sets the onbeforeeditfocus event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnbeforeeditfocus(String handler) {
		getClientHandler().setHandler(ClientEvent.ONBEFOREEDITFOCUS, handler);
	}

	/**
	 * Sets the onbeforepaste event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnbeforepaste(String handler) {
		getClientHandler().setHandler(ClientEvent.ONBEFOREPASTE, handler);
	}

	/**
	 * Sets the onbeforeprint event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnbeforeprint(String handler) {
		getClientHandler().setHandler(ClientEvent.ONBEFOREPRINT, handler);
	}

	/**
	 * Sets the onbeforeunload event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnbeforeunload(String handler) {
		getClientHandler().setHandler(ClientEvent.ONBEFOREUNLOAD, handler);
	}

	/**
	 * Sets the onbeforeupdate event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnbeforeupdate(String handler) {
		getClientHandler().setHandler(ClientEvent.ONBEFOREUPDATE, handler);
	}

	/**
	 * Sets the onblur event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnblur(String handler) {
		getClientHandler().setHandler(ClientEvent.ONBLUR, handler);
	}

	/**
	 * Sets the onbounce event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnbounce(String handler) {
		getClientHandler().setHandler(ClientEvent.ONBOUNCE, handler);
	}

	/**
	 * Sets the oncellchange event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOncellchange(String handler) {
		getClientHandler().setHandler(ClientEvent.ONCELLCHANGE, handler);
	}

	/**
	 * Sets the onchange event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnchange(String handler) {
		getClientHandler().setHandler(ClientEvent.ONCHANGE, handler);
	}

	/**
	 * Sets the onclick event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnclick(String handler) {
		getClientHandler().setHandler(ClientEvent.ONCLICK, handler);
	}

	/**
	 * Sets the oncontextmenu event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOncontextmenu(String handler) {
		getClientHandler().setHandler(ClientEvent.ONCONTEXTMENU, handler);
	}

	/**
	 * Sets the oncontrolselect event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOncontrolselect(String handler) {
		getClientHandler().setHandler(ClientEvent.ONCONTROLSELECT, handler);
	}

	/**
	 * Sets the oncopy event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOncopy(String handler) {
		getClientHandler().setHandler(ClientEvent.ONCOPY, handler);
	}

	/**
	 * Sets the oncut event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOncut(String handler) {
		getClientHandler().setHandler(ClientEvent.ONCUT, handler);
	}

	/**
	 * Sets the ondataavailable event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOndataavailable(String handler) {
		getClientHandler().setHandler(ClientEvent.ONDATAAVAILABLE, handler);
	}

	/**
	 * Sets the ondatasetchanged event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOndatasetchanged(String handler) {
		getClientHandler().setHandler(ClientEvent.ONDATASETCHANGED, handler);
	}

	/**
	 * Sets the ondatasetcomplete event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOndatasetcomplete(String handler) {
		getClientHandler().setHandler(ClientEvent.ONDATASETCOMPLETE, handler);
	}

	/**
	 * Sets the ondblclick event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOndblclick(String handler) {
		getClientHandler().setHandler(ClientEvent.ONDBLCLICK, handler);
	}

	/**
	 * Sets the ondeactivate event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOndeactivate(String handler) {
		getClientHandler().setHandler(ClientEvent.ONDEACTIVATE, handler);
	}

	/**
	 * Sets the ondrag event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOndrag(String handler) {
		getClientHandler().setHandler(ClientEvent.ONDRAG, handler);
	}

	/**
	 * Sets the ondragend event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOndragend(String handler) {
		getClientHandler().setHandler(ClientEvent.ONDRAGEND, handler);
	}

	/**
	 * Sets the ondragenter event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOndragenter(String handler) {
		getClientHandler().setHandler(ClientEvent.ONDRAGENTER, handler);
	}

	/**
	 * Sets the ondragleave event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOndragleave(String handler) {
		getClientHandler().setHandler(ClientEvent.ONDRAGLEAVE, handler);
	}

	/**
	 * Sets the ondragover event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOndragover(String handler) {
		getClientHandler().setHandler(ClientEvent.ONDRAGOVER, handler);
	}

	/**
	 * Sets the ondragstart event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOndragstart(String handler) {
		getClientHandler().setHandler(ClientEvent.ONDRAGSTART, handler);
	}

	/**
	 * Sets the ondrop event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOndrop(String handler) {
		getClientHandler().setHandler(ClientEvent.ONDROP, handler);
	}

	/**
	 * Sets the onerror event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnerror(String handler) {
		getClientHandler().setHandler(ClientEvent.ONERROR, handler);
	}

	/**
	 * Sets the onerrorupdate event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnerrorupdate(String handler) {
		getClientHandler().setHandler(ClientEvent.ONERRORUPDATE, handler);
	}

	/**
	 * Sets the onfilterchange event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnfilterchange(String handler) {
		getClientHandler().setHandler(ClientEvent.ONFILTERCHANGE, handler);
	}

	/**
	 * Sets the onfinish event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnfinish(String handler) {
		getClientHandler().setHandler(ClientEvent.ONFINISH, handler);
	}

	/**
	 * Sets the onfocus event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnfocus(String handler) {
		getClientHandler().setHandler(ClientEvent.ONFOCUS, handler);
	}

	/**
	 * Sets the onfocusin event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnfocusin(String handler) {
		getClientHandler().setHandler(ClientEvent.ONFOCUSIN, handler);
	}

	/**
	 * Sets the onfocusout event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnfocusout(String handler) {
		getClientHandler().setHandler(ClientEvent.ONFOCUSOUT, handler);
	}

	/**
	 * Sets the onhelp event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnhelp(String handler) {
		getClientHandler().setHandler(ClientEvent.ONHELP, handler);
	}

	/**
	 * Sets the onkeydown event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnkeydown(String handler) {
		getClientHandler().setHandler(ClientEvent.ONKEYDOWN, handler);
	}

	/**
	 * Sets the onkeypress event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnkeypress(String handler) {
		getClientHandler().setHandler(ClientEvent.ONKEYPRESS, handler);
	}

	/**
	 * Sets the onkeyup event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnkeyup(String handler) {
		getClientHandler().setHandler(ClientEvent.ONKEYUP, handler);
	}

	/**
	 * Sets the onlayoutcomplete event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnlayoutcomplete(String handler) {
		getClientHandler().setHandler(ClientEvent.ONLAYOUTCOMPLETE, handler);
	}

	/**
	 * Sets the onload event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnload(String handler) {
		getClientHandler().setHandler(ClientEvent.ONLOAD, handler);
	}

	/**
	 * Sets the onlosecapture event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnlosecapture(String handler) {
		getClientHandler().setHandler(ClientEvent.ONLOSECAPTURE, handler);
	}

	/**
	 * Sets the onmousedown event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnmousedown(String handler) {
		getClientHandler().setHandler(ClientEvent.ONMOUSEDOWN, handler);
	}

	/**
	 * Sets the onmouseenter event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnmouseenter(String handler) {
		getClientHandler().setHandler(ClientEvent.ONMOUSEENTER, handler);
	}

	/**
	 * Sets the onmouseleave event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnmouseleave(String handler) {
		getClientHandler().setHandler(ClientEvent.ONMOUSELEAVE, handler);
	}

	/**
	 * Sets the onmousemove event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnmousemove(String handler) {
		getClientHandler().setHandler(ClientEvent.ONMOUSEMOVE, handler);
	}

	/**
	 * Sets the onmouseout event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnmouseout(String handler) {
		getClientHandler().setHandler(ClientEvent.ONMOUSEOUT, handler);
	}

	/**
	 * Sets the onmouseover event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnmouseover(String handler) {
		getClientHandler().setHandler(ClientEvent.ONMOUSEOVER, handler);
	}

	/**
	 * Sets the onmouseup Eventhandler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnmouseup(String handler) {
		getClientHandler().setHandler(ClientEvent.ONMOUSEUP, handler);
	}

	/**
	 * Sets the onmousewheel event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnmousewheel(String handler) {
		getClientHandler().setHandler(ClientEvent.ONMOUSEWHEEL, handler);
	}

	/**
	 * Sets the onmove event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnmove(String handler) {
		getClientHandler().setHandler(ClientEvent.ONMOVE, handler);
	}

	/**
	 * Sets the onmoveend event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnmoveend(String handler) {
		getClientHandler().setHandler(ClientEvent.ONMOVEEND, handler);
	}

	/**
	 * Sets the onmovestart event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnmovestart(String handler) {
		getClientHandler().setHandler(ClientEvent.ONMOVESTART, handler);
	}

	/**
	 * Sets the onpaste event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnpaste(String handler) {
		getClientHandler().setHandler(ClientEvent.ONPASTE, handler);
	}

	/**
	 * Sets the onpropertychange event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnpropertychange(String handler) {
		getClientHandler().setHandler(ClientEvent.ONPROPERTYCHANGE, handler);
	}

	/**
	 * Sets the onreadystatechange event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnreadystatechange(String handler) {
		getClientHandler().setHandler(ClientEvent.ONREADYSTATECHANGE, handler);
	}

	/**
	 * Sets the onreset event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnreset(String handler) {
		getClientHandler().setHandler(ClientEvent.ONRESET, handler);
	}

	/**
	 * Sets the onresize event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnresize(String handler) {
		getClientHandler().setHandler(ClientEvent.ONRESIZE, handler);
	}

	/**
	 * Sets the onresizeend event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnresizeend(String handler) {
		getClientHandler().setHandler(ClientEvent.ONRESIZEEND, handler);
	}

	/**
	 * Sets the onresizestart event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnresizestart(String handler) {
		getClientHandler().setHandler(ClientEvent.ONRESIZESTART, handler);
	}

	/**
	 * Sets the onrowenter event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnrowenter(String handler) {
		getClientHandler().setHandler(ClientEvent.ONROWENTER, handler);
	}

	/**
	 * Sets the onrowexit event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnrowexit(String handler) {
		getClientHandler().setHandler(ClientEvent.ONROWEXIT, handler);
	}

	/**
	 * Sets the onrowsdelete event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnrowsdelete(String handler) {
		getClientHandler().setHandler(ClientEvent.ONROWSDELETE, handler);
	}

	/**
	 * Sets the onrowsinserted event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnrowsinserted(String handler) {
		getClientHandler().setHandler(ClientEvent.ONROWSINSERTED, handler);
	}

	/**
	 * Sets the onscroll event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnscroll(String handler) {
		getClientHandler().setHandler(ClientEvent.ONSCROLL, handler);
	}

	/**
	 * Sets the onselect event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnselect(String handler) {
		getClientHandler().setHandler(ClientEvent.ONSELECT, handler);
	}

	/**
	 * Sets the onselectionchange event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnselectionchange(String handler) {
		getClientHandler().setHandler(ClientEvent.ONSELECTIONCHANGE, handler);
	}

	/**
	 * Sets the onselectstart event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnselectstart(String handler) {
		getClientHandler().setHandler(ClientEvent.ONSELECTSTART, handler);
	}

	/**
	 * Sets the onstart event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnstart(String handler) {
		getClientHandler().setHandler(ClientEvent.ONSTART, handler);
	}

	/**
	 * Sets the onstop event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnstop(String handler) {
		getClientHandler().setHandler(ClientEvent.ONSTOP, handler);
	}

	/**
	 * Sets the onsubmit event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnsubmit(String handler) {
		getClientHandler().setHandler(ClientEvent.ONSUBMIT, handler);
	}

	/**
	 * Sets the onunload event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnunload(String handler) {
		getClientHandler().setHandler(ClientEvent.ONUNLOAD, handler);
	}

	// ======================================
	// Extended Client side eventhandler
	// ======================================

	/**
	 * Sets the <b>extended</b> onexpand event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnexpand(String handler) {
		getClientHandler().setHandler(ClientEvent.EXT_ONEXPAND, handler);
	}

	/**
	 * Sets the <b>extended</b> onexpandex event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnexpandex(String handler) {
		getClientHandler().setHandler(ClientEvent.EXT_ONEXPANDEX, handler);
	}

	/**
	 * Sets the <b>extended</b> collapse event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOncollapse(String handler) {
		getClientHandler().setHandler(ClientEvent.EXT_ONCOLLAPSE, handler);
	}

	/**
	 * Sets the <b>extended</b> onspinup event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnspinup(String handler) {
		getClientHandler().setHandler(ClientEvent.EXT_ONSPINUP, handler);
	}

	/**
	 * Sets the <b>extended</b> onspindown event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnspindown(String handler) {
		getClientHandler().setHandler(ClientEvent.EXT_ONSPINDOWN, handler);
	}

	/**
	 * Sets the <b>extended</b> oncheck event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOncheck(String handler) {
		getClientHandler().setHandler(ClientEvent.EXT_ONCHECK, handler);
	}

	/**
	 * Sets the <b>extended</b> onuncheck event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnuncheck(String handler) {
		getClientHandler().setHandler(ClientEvent.EXT_ONUNCHECK, handler);
	}

	/**
	 * Sets the <b>extended</b> oncheckall event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOncheckall(String handler) {
		getClientHandler().setHandler(ClientEvent.EXT_ONCHECKALL, handler);
	}

	/**
	 * Sets the <b>extended</b> onuncheckall event handler
	 *
	 * @param	handler		The JavaScript event handler
	 */
	public void setOnuncheckall(String handler) {
		getClientHandler().setHandler(ClientEvent.EXT_ONUNCHECKALL, handler);
	}
}
