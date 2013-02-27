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

import com.cc.framework.common.FrameworkException;

/**
 * Base exception class for all converter exceptions
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision$
 * @since 1.6
 */
public class ConverterException extends FrameworkException {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -1443437655633011220L;

	/**
	 * Constructor for ConverterException
	 */
	public ConverterException() {
		super();
	}

	/**
	 * Constructor for ConverterException
	 * 
	 * @param message
	 *            the detail message for this exception.
	 */
	public ConverterException(String message) {
		super(message);
	}

	/**
	 * Constructor for ConverterException
	 * 
	 * @param cause
	 *            the root cause of the exception
	 */
	public ConverterException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor for ConverterException
	 * 
	 * @param message
	 *            the detail message for this exception.
	 * @param cause
	 *            the root cause of the exception
	 */
	public ConverterException(String message, Throwable cause) {
		super(message, cause);
	}
}