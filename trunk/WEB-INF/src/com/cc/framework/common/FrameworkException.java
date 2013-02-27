/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/common/FrameworkException.java,v 1.13 2005/07/28 19:41:06 P001002 Exp $
 * $Revision: 1.13 $
 * $Date: 2005/07/28 19:41:06 $
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

package com.cc.framework.common;

/**
 * Base exception class for all exceptions of the common controls framework.
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision: 1.13 $
 * @since 1.0
 */
public abstract class FrameworkException extends Exception {

	/**
	 * Constructor for FrameworkException
	 */
	public FrameworkException() {
		super();
	}

	/**
	 * Constructor for FrameworkException
	 * 
	 * @param message
	 *            the detail message for this exception.
	 */
	public FrameworkException(String message) {
		super(message);
	}

	/**
	 * Constructor for FrameworkException
	 * 
	 * @param cause
	 *            the root cause of the exception
	 */
	public FrameworkException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor for FrameworkException
	 * 
	 * @param message
	 *            the detail message for this exception.
	 * @param cause
	 *            the root cause of the exception
	 */
	public FrameworkException(String message, Throwable cause) {
		super(message, cause);
	}
}