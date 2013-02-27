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

package com.cc.framework.convert.imp;

import com.cc.framework.adapter.RequestContext;
import com.cc.framework.convert.Converter;
import com.cc.framework.convert.ConverterException;

/**
 * Converter for Byte sizes
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision$
 */
public class ByteSizeConverter implements Converter {

	/**
	 * Converter Id
	 */
	public static final String CONVERTER_ID = "javax.faces.ByteSize";

	/**
	 * The Size Units
	 */
	private static final String[] SUFFIX = new String[] {"Bytes", "KB", "MB", "GB", "TB"};

	/**
	 * @see com.cc.framework.convert.Converter#getAsObject(com.cc.framework.adapter.RequestContext, java.lang.String)
	 */
	public Object getAsObject(RequestContext ctx, String newValue) throws ConverterException {
		int factor = 1;

		int pos	= 0;
		long bytes = 0;

		while (pos < newValue.length()) {
			if ((newValue.charAt(pos) >= '0') && (newValue.charAt(pos) <= '9')) {
				bytes = bytes * 10 + (newValue.charAt(pos) - '0');
				++pos;
			} else {
				break;
			}
		}

		String v = newValue.substring(pos).trim().toUpperCase();
		for (int i = 0; i < SUFFIX.length; i++) {
			if (!v.equalsIgnoreCase(SUFFIX[i].toUpperCase())) {
				factor *= 1024;
			} else {
				return new Long(bytes * factor);
			}
		}

		throw new ConverterException("no valid unit specified!");
	}

	/**
	 * @see com.cc.framework.convert.Converter#getAsString(com.cc.framework.adapter.RequestContext, java.lang.Object)
	 */
	public String getAsString(RequestContext ctx, Object value) throws ConverterException {

		if (ctx == null) {
			throw new NullPointerException();
		}

		if (value == null) {
			return "";
		} else if (value instanceof Number) {
			return toByteSize(((Number) value).longValue());
		} else {
			throw new ConverterException("Number object exprected: " + value);
		}
	}

	/**
	 * Converts a long value into a byte size string
	 * 
	 * @param size
	 *            the size to convert
	 * @return byte size string
	 */
	public static String toByteSize(long size) {
		int i = 0;
		while ((i < SUFFIX.length) && (size >= 1024)) {
			++i;
			size /= 1024;
		}

		return size + " " + SUFFIX[i];
	}
}