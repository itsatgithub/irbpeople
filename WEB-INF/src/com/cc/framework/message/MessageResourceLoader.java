/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/message/MessageResourceLoader.java,v 1.2 2005/02/16 18:03:21 P001001 Exp $
 * $Revision: 1.2 $
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

/**
 * Interface for accessing messages from a configuration file.
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.2 $
 * @since      1.0
 */
public interface MessageResourceLoader {

	/**
	 * Checks if the list has more elements
	 *
	 * @return	boolean	true if no more elements exists, false otherwise.
	 */
	public boolean done();

	/**
	 * Returns the message code at the specified position in this list.
	 *
	 * @return int	the message code
	 */
	public int getCode();

	/**
	 * Returns the language of the message
	 *
	 * @param	index	the message code
	 * @return	String	the language
	 */
	public String getLanguage(int index);

	/**
	 * Returns the numer of avaliable language resources for a message
	 *
	 * @return int
	 */
	public int getLanguageCount();

	/**
	 * Returns the symbolic name.
	 *
	 * @return String
	 */
	public String getName();

	/**
	 * Returns the severityof the message
	 *
	 * @return String
	 */
	public String getSeverity();

	/**
	 * Returns the message text at the specified position in this list.
	 *
	 * @param	index	Index of element to return (message number).
	 * @return	String	the message text.
	 */
	public String getText(int index);

	/**
     * Goes to the next element of the list.
	 */
	public void next();

	/**
	 * Sets the iterator to the top of the list.
	 */
	public void restart();
}
