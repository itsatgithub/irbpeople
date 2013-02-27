/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/message/MessageResource.java,v 1.10 2005/02/25 12:12:50 P001002 Exp $
 * $Revision: 1.10 $
 * $Date: 2005/02/25 12:12:50 $
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

import java.util.Hashtable;

import com.cc.framework.common.Severity;

/**
 * Class MessageResource
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.10 $
 * @since      1.0
 */
public class MessageResource {

	/**
	 * Field DEF_LANGUAGE
	 */
	public static final String DEF_LANGUAGE	= "def";

	/**
	 * Unique message code
	 */
	private int code	= 0;

	/**
	 * Symbolic name for the message
	 */
	private String name	= "";

	/**
	 * Message
	 */
	private Hashtable text	= new Hashtable();

	/**
	 * Severity assigned to the message
	 */
	private Severity severity	= Severity.NONE;


	/**
	 * Constructor for MessageResource
	 */
	public MessageResource() {
		super();
	}

	/**
	 * Constructor for MessageResource
	 *
	 * @param	code		the message code
	 * @param	name		the symbolic name of the message
	 * @param	severity	the severity assigned for the message
	 */
	public MessageResource(int code, String name, Severity severity) {

		super();

		this.code		= code;
		this.name		= name;
		this.severity	= severity;
	}

	/**
	 * Adds a message text for the specified language
	 *
	 * @param	language	the language of the message text
	 * @param	text		the message text
	 */
	public void addText(String language, String text) {

		if ((null == language) || language.equals("")) {
			this.text.put(DEF_LANGUAGE, text);
		} else {
			this.text.put(language, text);
		}
	}

	/**
	 * Replaces the placeholders in the Message with the specified Parameters
	 *
	 * @param	language	the language
	 * @param	params		the parameters to replace in the message text
	 * @return	String
	 */
	public String expand(String language, Object[] params) {

		String text	= getText(language);

		return java.text.MessageFormat.format(text, params);
	}

	/**
	 * Replaces the placeholders in the Message with the specified Parameter
	 *
	 * @param	language	the language of the message
	 * @param	param1		the parameter to replace in the message text
	 * @return	String
	 */
	public String expand(String language, Object param1) {

		Object[] params	= new Object[1];

		params[0]	= param1;

		return expand(language, params);
	}

	/**
	 * Replaces the placeholders in the Message with the specified Parameters
	 *
	 * @param	language	the language of the message
	 * @param	param1		the parameter to replace in the message text
	 * @param	param2		the parameter to replace in the message text
	 * @return String
	 */
	public String expand(String language, Object param1, Object param2) {

		Object[] params	= new Object[2];

		params[0]	= param1;
		params[1]	= param2;

		return expand(language, params);
	}

	/**
	 * Replaces the placeholders in the Message with the specified Parameters
	 *
	 * @param	language	the language of the message
	 * @param	param1		the parameter to replace in the message text
	 * @param	param2		the parameter to replace in the message text
	 * @param	param3		the parameter to replace in the message text
	 * @return String
	 */
	public String expand(String language, Object param1, Object param2, Object param3) {

		Object[] params	= new Object[3];

		params[0]	= param1;
		params[1]	= param2;
		params[2]	= param3;

		return expand(language, params);
	}

	/**
	 * Returns the Code
	 * @return String
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Creates a String representation for the message
	 *
	 * @return String
	 */
	public String toString() {

		StringBuffer buffer	= new StringBuffer();

		buffer.append(code).append(": ").append(name).append(" (").append(severity).append(")");

		return buffer.toString();
	}

	/**
	 * Returns the Name
	 *
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the severity
	 *
	 * @return Severity
	 */
	public Severity getSeverity() {
		return severity;
	}

	/**
	 * Returns the message text for the specified language
	 *
	 * @param	language	the language
	 * @return	String
	 */
	public String getText(String language) {

		String theText	= (String) text.get(language);

		if (null == theText) {
			theText	= (String) text.get(DEF_LANGUAGE);
		}

		return theText;
	}

	/**
	 * Returns the message text for the default language
	 *
	 * @return String
	 */
	public String getText() {
		return (String) text.get(DEF_LANGUAGE);
	}
}