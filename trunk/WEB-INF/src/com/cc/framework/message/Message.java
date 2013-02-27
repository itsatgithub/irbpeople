/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/message/Message.java,v 1.10 2005/02/16 18:03:21 P001001 Exp $
 * $Revision: 1.10 $
 * $Date: 2005/02/16 18:03:21 $
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

package com.cc.framework.message;

import com.cc.framework.common.Severity;

/**
 * Interface for messages
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.10 $
 * @since      1.0
 */
public interface Message {

	/**
	 * Returns the unique message key
	 *
	 * @return	String
	 */
	public String getKey();

	/**
	 * Returns the severity for the message
	 *
	 * @return	Severity
	 */
	public Severity getSeverity();

	/**
	 * Returns a language dependent message
	 *
	 * @param	locale	Local  <code>null</code> == default language
	 * @return	String
	 */
	public String getMessage(String locale);

	/**
	 * Returns a language independent message
	 *
	 * @return	String
	 */
	public String getMessage();
}