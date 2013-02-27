/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/message/Messages.java,v 1.11 2005/02/16 18:03:21 P001001 Exp $
 * $Revision: 1.11 $
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

import java.util.Enumeration;
import java.util.Vector;

import com.cc.framework.common.Severity;

/**
 * Collection for Messages
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.11 $
 * @since      1.0
 */
public class Messages {

	/**
	 * The buffer into which the messages are stored.
	 */
	private Vector messages = new Vector();

	/**
	 * Constructor for Messages
	 */
	public Messages() {
		super();
	}

	/**
	 * Adds a new message to the collection
	 *
	 * @param	message	the message resource.
	 */
	public void addMessage(MessageResource message) {
		messages.add(message);
	}

	/**
	 * Returns the highest severity for the messages in this list.
	 *
	 * @return Severity
	 */
	public Severity getSeverity() {

		Severity max = Severity.NONE;

		Enumeration e = messages.elements();
		while (e.hasMoreElements()) {
			MessageResource message = (MessageResource) e.nextElement();

			max = Severity.max(max, message.getSeverity());
		}

		return max;
	}

	/**
	 * Checks if the list containes messages of severity "error"
	 *
	 * @return boolean	true if it is an Error
	 */
	public boolean isError() {
		return getSeverity().isError();
	}
}
