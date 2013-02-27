/*
 * $Header$
 * $Revision$
 * $Date$
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

package com.cc.framework.convert;

import com.cc.framework.adapter.RequestContext;

/**
 * Base Class for Converters that need to save their state between requests.
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision$
 */
public interface StatefullConverter extends Converter {

	/**
	 * Gets the state of the instance as a Serializable Object.
	 * 
	 * This method must not alter the state of the implementing object. In other
	 * words, after executing this code:
	 * 
	 * <pre>
	 * Object state = component.saveState(ctx);
	 * </pre>
	 * 
	 * Component should be the same as before executing it.
	 * 
	 * @param ctx
	 *            The RequestContext object
	 * @return The return from this method must be Serializable
	 */
	public abstract Object saveState(RequestContext ctx);

	/**
	 * Perform any processing required to restore the state from the entries in
	 * the state Object.
	 * 
	 * @param ctx
	 *            The RequestContext object
	 * @param obj
	 *            the serialized state
	 */
	public abstract void restoreState(RequestContext ctx, Object obj);
}
