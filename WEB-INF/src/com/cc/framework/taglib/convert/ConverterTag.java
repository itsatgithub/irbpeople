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

package com.cc.framework.taglib.convert;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.cc.framework.convert.Converter;
import com.cc.framework.convert.ConverterException;
import com.cc.framework.convert.ConverterHelp;
import com.cc.framework.taglib.ConversionSupportTag;
import com.cc.framework.taglib.InnerTag;

/**
 * Tag for declaring a simple converter
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision$
 */
public class ConverterTag extends TagSupport implements InnerTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 5879591905260620363L;

	/**
	 * Converter id or class name
	 */
	private String converterId = null;

	/**
	 * Constructor
	 */
	public ConverterTag() {
		super();
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#release()
	 */
	public void release() {
		super.release();

		converterId = null;
	}

	/**
	 * Template method is called to create the concrete Converter instance
	 * 
	 * @return Converter instance
	 * @throws ConverterException
	 *             If an error occurred while creating the Converter
	 */
	public Converter doCreateConverter() throws ConverterException {
		return ConverterHelp.getInstance(converterId);
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {

		Object parent = findAncestorWithClass(this, ConversionSupportTag.class);

		// a converter tag must be nested within a ConversionSupportTag
		if (parent == null) {
			throw new JspException(
					"ConverterTag must be nested inside a ConversionSupportTag");
		}

		ConversionSupportTag container = (ConversionSupportTag) parent;

		try {
			container.assignConverter(doCreateConverter());
		} catch (ConverterException ce) {
			throw new JspException("Could not create Converter with id="
					+ converterId, ce);
		}

		return EVAL_PAGE;
	}

	/**
	 * Converter identifier of the Converter instance to be created and
	 * registered.
	 * 
	 * @param converterId
	 *            The converters identifier or class name
	 */
	public void setConverterId(String converterId) {
		this.converterId = converterId;
	}
}