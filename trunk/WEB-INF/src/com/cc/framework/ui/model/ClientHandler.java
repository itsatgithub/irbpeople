/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/ClientHandler.java,v 1.5 2005/09/27 14:06:22 P001002 Exp $
 * $Revision: 1.5 $
 * $Date: 2005/09/27 14:06:22 $
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
 * This interface is implemented by all design models, which support client
 * handler.
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision: 1.5 $
 * @since 1.0
 */
public interface ClientHandler {

	/**
	 * Registers a client handler for an client event
	 * 
	 * @param event
	 *            The client event (like onClick, onMouseOver, ...)
	 * @param handler
	 *            The script handler associated with the event
	 */
	public void setHandler(ClientEvent event, String handler);

	/**
	 * Returns the script handler which was associated for a client event
	 * 
	 * @param event
	 *            The event (like onClick, onMouseOver, ...)
	 * @return String The script handler oder <code>null</code> if no handler
	 *         was associated.
	 */
	public String getHandler(ClientEvent event);

	/**
	 * Returns the list of events for which client handler have been registered.
	 * 
	 * @return the liste of events
	 */
	public ClientEvent[] getHandlers();
}