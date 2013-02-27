/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/value/DeferredException.java,v 1.5 2005/09/27 14:06:24 P001002 Exp $
 * $Revision: 1.5 $
 * $Date: 2005/09/27 14:06:24 $
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

package com.cc.framework.ui.model.value;

/**
 * Exception for Deferred Values
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.5 $
 */
public class DeferredException extends RuntimeException {
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 8997845101583040356L;

	/**
	 * Constructs a new runtime exception with <code>null</code> as its
	 * detail message.  The cause is not initialized, and may subsequently be
	 * initialized by a call to {@link java.lang.Throwable#initCause}.
	 */
	public DeferredException() {
		super();
	}

	/**
	 * Constructs a new runtime exception with the specified detail message.
	 * The cause is not initialized, and may subsequently be initialized by a
	 * call to {@link java.lang.Throwable#initCause}.
	 *
	 * @param   message   the detail message. The detail message is saved for 
	 *          later retrieval by the {@link #getMessage()} method.
	 */
	public DeferredException(String message) {
		super(message);
	}
}
