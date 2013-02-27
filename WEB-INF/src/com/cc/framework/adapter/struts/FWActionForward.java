/*
 * $Header: d:/repository/cvs/cc-adapter-struts-1.2/adapter/com/cc/framework/adapter/struts/FWActionForward.java,v 1.5 2005/07/08 14:11:04 P001002 Exp $
 * $Revision: 1.5 $
 * $Date: 2005/07/08 14:11:04 $
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

package com.cc.framework.adapter.struts;

import java.text.MessageFormat;

import org.apache.struts.action.ActionForward;

import com.cc.framework.http.HttpUtil;

/**
 * Encapsulation of the struts ActionForward class
 * for the common controls framework.
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.5 $
 * @since      1.0
 * @see org.apache.struts.action.ActionForward
 */
public class FWActionForward extends ActionForward {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 7194956731351417071L;

	/**
	 * Constructor for FWActionForward
	 *
	 * @param	forward	ActionForward
	 */
	public FWActionForward(ActionForward forward) {

		super();

		init(forward, null);
	}

	/**
	 * Constructor for FWActionForward
	 *
	 * @param	forward		A ActionForward instance
	 * @param	p1			An optional parameter which should be forwarded with the url
	 */
	public FWActionForward(ActionForward forward, Object p1) {
		super();

		init(forward, new Object[]{p1});
	}

	/**
	 * Constructor for FWActionForward
	 *
	 * @param	forward		A ActionForward instance
	 * @param	p1			An optional parameter which should be forwarded with the url
	 * @param	p2			An optional parameter which should be forwarded with the url
	 */
	public FWActionForward(ActionForward forward, Object p1, Object p2) {

		super();

		init(forward, new Object[]{p1, p2});
	}

	/**
	 * Constructor for FWActionForward
	 *
	 * @param	forward		A ActionForward instance
	 * @param	p1			An optional parameter which should be forwarded with the url
	 * @param	p2			An optional parameter which should be forwarded with the url
	 * @param	p3			An optional parameter which should be forwarded with the url
	 */
	public FWActionForward(ActionForward forward, Object p1, Object p2, Object p3) {

		super();

		init(forward, new Object[]{p1, p2, p3});
	}

	/**
	 * Constructor for FWActionForward
	 *
	 * @param	forward		A ActionForward instance
	 * @param	params		Array with additional parameter which should be forwarded with the url
	 */
	public FWActionForward(ActionForward forward, Object[] params) {

		super();

		init(forward, params);
	}

	/**
	 * Initializes this instance
	 *
	 * @param	forward		A ActionForward instance
	 * @param	params		An array with optional parameter which should be forwarded with the url
	 */
	private void init(ActionForward forward, Object params[]) {
		init(
			forward.getModule(),
			forward.getName(),
			forward.getPath(),
			forward.getRedirect(),
			params);
	}

	/**
	 * Initializes this instance
	 *
	 * @param	module			<p>The prefix of the module to which this <code>ForwardConfig</code>
	 * 							entry points, which must start with a slash ("/") character.</p>
     * @param	name			Name of this instance
     * @param	path			Path for this instance
     * @param	redirect 		Redirect flag for this instance
	 * @param	params			An array with optional parameter which should be forwarded with the url
	 */
	private void init(
		String module,
		String name,
		String path,
		boolean redirect,
		Object params[]) {

		String pathExt;

		if ((params == null) || (params.length == 0)) {
			pathExt = path;
		} else {
			// URL Encode all Parameters
			Object[] encoded = new String[params.length];
			for (int i = 0; i < params.length; i++) {
				encoded[i] = HttpUtil.urlEncode(params[i]);
			}

			pathExt = MessageFormat.format(path, encoded);
		}

		setModule(module);
		setName(name);
		setPath(pathExt);
		setRedirect(redirect);
	}
}