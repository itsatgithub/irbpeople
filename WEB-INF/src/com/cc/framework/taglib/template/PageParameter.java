/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/template/PageParameter.java,v 1.10 2005/02/16 18:03:26 P001001 Exp $
 * $Revision: 1.10 $
 * $Date: 2005/02/16 18:03:26 $
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

package com.cc.framework.taglib.template;

/**
 * Class PageParameter
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.10 $
 * @since      1.0
 */
public class PageParameter {

	/**
	 * Field content
	 */
	private String content;

	/**
	 * Field Base
	 */
	private String base;

	/**
	 * Field Direct
	 */
	private boolean direct;

	/**
	 * Constructor
	 * @param	base	Base
	 * @param	content	Content
	 * @param	direct	Direct Flag
	 */
	public PageParameter(String base, String content, boolean direct) {
		this.base		= base;
		this.content	= content;
		this.direct		= direct;
	}

	/**
	 * Returns the Content
	 * @return	String
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Method isDirect
	 * @return boolean
	 */
	public boolean isDirect() {
		return direct;
	}

	/**
	 * Returns the base.
	 * @return String
	 */
	public String getBase() {
		return base;
	}
}