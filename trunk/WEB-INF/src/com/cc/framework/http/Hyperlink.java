/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/http/Hyperlink.java,v 1.11 2005/07/13 07:45:40 P001002 Exp $
 * $Revision: 1.11 $
 * $Date: 2005/07/13 07:45:40 $
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

import javax.servlet.http.HttpServletRequest;

import com.cc.framework.common.TupleArray;
import com.cc.framework.util.StringHelp;

/**
 * Creates a Hyperlink
 *
 * @author   <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version  $Revision: 1.11 $
 * @since    1.0
 */
public class Hyperlink {

	/**
	 * The uri
	 */
	private String uri			= null;

	/**
	 * The query parameters
	 */
	private TupleArray params	= null;

	/**
	 * Constructor for Hyperlink
	 */
	public Hyperlink() {
		super();
	}

	/**
	 * Constructor for Hyperlink
	 *
	 * @param	uri			the URI
	 */
	public Hyperlink(String uri) {
		super();

		this.uri = uri;
	}

	/**
	 * Constructor for Hyperlink
	 *
	 * @param	uri			the URI
	 * @param	queryString	the query string
	 */
	public Hyperlink(String uri, String queryString) {
		this(uri);

		addQueryParameters(queryString);
	}

	/**
	 * Creates a Hyperlink Object from the HttpServletRequest
	 *
	 * @param	link Hyperlink as a String
	 * @return	Hyperlink
	 */
	public static Hyperlink parse(String link) {
		if (link == null) {
			return null;
		}

		int sep = link.indexOf('?');

		if (sep == -1) {
			return new Hyperlink(link);
		} else {
			return new Hyperlink(link.substring(0, sep), link.substring(sep + 1));
		}
	}

	/**
	 * Creates a Hyperlink Object from the HttpServletRequest
	 *
	 * @param	request	HttpServletRequest
	 * @return	Hyperlink
	 */
	public static Hyperlink createFromRequest(HttpServletRequest request) {
		return new Hyperlink(request.getRequestURI(), request.getQueryString());
	}

	/**
	 * @return Returns the number of Query parameters
	 */
	public int getParamCount() {
		if (params == null) {
			return 0;
		} else {
			return params.size();
		}
	}
	
	/**
	 * Extracts all query parameters from the given query string
	 * 
	 * @param		queryString The query string
	 */
	public void addQueryParameters(String queryString) {
		String[] tokens = StringHelp.split(queryString, "&");

		for (int i = 0; i < tokens.length; i++) {
			int eq = tokens[i].indexOf('=');

			if (eq == -1) {
				addQueryParameter(tokens[i], null);
			} else {
				addQueryParameter(tokens[i].substring(0, eq), tokens[i].substring(eq + 1));
			}
		}
	}

	/**
	 * Adds a new Query parameter to the query parameter list
	 * 
	 * @param		key Parameter key
	 * @param		value Parameter value
	 */
	public void addQueryParameter(String key, Object value) {
		if (params == null) {
			params = new TupleArray();
		}

		if (value == null) {
			params.add(key, null);
		} else {
			params.add(key, value.toString());
		}
	}
	
	/**
	 * Returns the URI
	 *
	 * @return String
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * Sets the URI
	 *
	 * @param	newUri	the URI
	 */
	public void setUri(String newUri) {
		uri	= newUri;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer buf = new StringBuffer();

		if (uri != null) {
			buf.append(uri);
		}

		if (params != null) {
			buf.append("?");

			for (int i = 0; i < params.size(); i++) {
				if (i > 0) {
					buf.append("&");
				}
				
				buf.append(params.a(i));
				
				if (params.b(i) != null) {
					buf.append("=").append(HttpUtil.urlEncode(params.b(i)));
				}
			}
		}

		return buf.toString();
	}
}
