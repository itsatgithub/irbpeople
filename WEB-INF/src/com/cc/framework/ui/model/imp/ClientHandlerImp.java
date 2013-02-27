/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/ClientHandlerImp.java,v 1.16 2005/07/08 14:19:43 P001002 Exp $
 * $Revision: 1.16 $
 * $Date: 2005/07/08 14:19:43 $
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

package com.cc.framework.ui.model.imp;

import java.io.Serializable;
import java.util.Hashtable;

import javax.servlet.jsp.PageContext;

import com.cc.framework.ui.model.ClientEvent;
import com.cc.framework.ui.model.ClientHandler;
import com.cc.framework.ui.model.value.DeferredEnvironment;
import com.cc.framework.ui.model.value.DeferredValue;

/**
 * Verwaltungsklasse für Client Handler
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.16 $
 * @since      1.0
 */
public class ClientHandlerImp implements ClientHandler, Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -3891612510233650055L;

	// ==========================
	// Members
	// ==========================

	/**
	 * Collection with JavaScript event handlers
	 */
	private Hashtable handlerList	= null;

	// ==========================
	// Methods
	// ==========================

	/**
	 * Constructor
	 */
	public ClientHandlerImp() {
		super();
	}

	/**
	 * Copy Constructor
	 *
	 * @param		other The Handler to copy from
	 */
	public ClientHandlerImp(ClientHandler other) {
		super();

		if (other != null) {
			ClientEvent[] events = other.getHandlers();

			for (int i = 0; i < events.length; i++) {
				setHandler(events[i], other.getHandler(events[i]));
			}
		}
	}

	/**
	 * @see com.cc.framework.ui.model.ClientHandler#getHandler(ClientEvent)
	 */
	public String getHandler(ClientEvent event) {

		if (handlerList == null) {
			return null;
		}

		return (String) handlerList.get(event);
	}

	/**
	 * @see com.cc.framework.ui.model.ClientHandler#setHandler(ClientEvent, String)
	 */
	public void setHandler(ClientEvent event, String handler) {
		if (handlerList == null) {
			handlerList = new Hashtable();
		}

		handlerList.remove(event);
		handlerList.put(event, handler);
	}

	/**
	 * @see com.cc.framework.ui.model.ClientHandler#getHandlers()
	 */
	public ClientEvent[] getHandlers() {
		if (handlerList == null) {
			return  new ClientEvent[0];
		} else {
			ClientEvent[] list = new ClientEvent[handlerList.size()];
			return (ClientEvent[]) handlerList.keySet().toArray(list);
		}
	}

	/**
	 * Uses the Expression Language to create Client Script handlers
	 * for a certain column and row bean
	 *
	 * @param		ctx JSP Page context
	 * @param		source the client handlers to process
	 * @param		databean the bean which can be accessed
	 * 				in expressions
	 * @return		ClientHandler collection
	 */
	public static ClientHandler evaluate(
		PageContext ctx,
		ClientHandler source,
		Object databean) {

		DeferredEnvironment env = new DeferredEnvironment(ctx, databean);

		ClientHandler ch = new ClientHandlerImp();

		ClientEvent[] events = source.getHandlers();
		for (int i = 0; i < events.length; i++) {
			// Use the Expression Language to expand the Client
			// Script Handler
			try {
				Object handler = DeferredValue.evaluate(source.getHandler(events[i]), env);

				if (handler != null) {
					ch.setHandler(events[i], String.valueOf(handler));
				}
			} catch (Exception e) {
				// Ignore this exception
			}
		}

		return ch;
	}
}