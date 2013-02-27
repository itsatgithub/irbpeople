/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/common/InvalidEnumType.java,v 1.12 2005/07/08 14:13:43 P001002 Exp $
 * $Revision: 1.12 $
 * $Date: 2005/07/08 14:13:43 $
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
 * This exception is thrown by enumeration classes which try to parse
 * and convert arguments to there internal type
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.12 $
 * @since      1.0
 */
public class InvalidEnumType extends RuntimeException {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 8537715755202875183L;

	/**
	 * Constructor for InvalidEnumType
	 */
	public InvalidEnumType() {
		super();
	}

	/**
	 * Constructor for InvalidEnumType
	 *
	 * @param	message	the detail message for this exception.
	 */
	public InvalidEnumType(String message) {
		super(message);
	}
}
