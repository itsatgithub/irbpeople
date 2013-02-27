/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/http/CacheControl.java,v 1.11 2005/02/25 12:12:45 P001002 Exp $
 * $Revision: 1.11 $
 * $Date: 2005/02/25 12:12:45 $
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

package com.cc.framework.http;

import javax.servlet.http.HttpServletResponse;

/**
 * Helper class for setting the cache options of the HTTP header.
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.11 $
 * @since      1.0
 */
public abstract class CacheControl {

	/**
	 * Constructor
	 */
	private CacheControl() {
		super();
	}

	/**
	 * Sets the cache options
	 *
	 * @param	response	the HttpServletResponse
	 * @param	noCache		<code>true</code> if the page should not be cached by the browser
	 */
	public static final void setCache(HttpServletResponse response, boolean noCache) {
		setCache(response, noCache, 0);
	}

	/**
	 * Sets the cache options
	 *
	 * @param	response	the HttpServletResponse
	 * @param	noCache		<code>true</code> if the page should not be cached by the browser
	 * @param	expiration	the the expiration date of the page
	 */
	public static final void setCache(HttpServletResponse response, boolean noCache, int expiration) {

		if (noCache) {
			response.setHeader("pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Expires", "0");
		} else {
			if (expiration > 0) {
				response.setHeader("Expires", Integer.toString(expiration));
			}
		}
	}

	/**
	 * Clears the cache options
	 *
	 * @param	response	the HttpServletResponse
	 */
	public static final void clearCache(HttpServletResponse response) {

		response.setHeader("pragma", null);
		response.setHeader("Cache-Control", null);
		response.setHeader("Expires", null);
	}

	/**
	 * Sets the expiration date
	 *
	 * @param	response	the HttpServletResponse
	 * @param	expiration	the expiration date of the page
	 */
	public static final void setExpiration(HttpServletResponse response, int expiration) {
		response.setHeader("Expires", Integer.toString(expiration));
	}
}