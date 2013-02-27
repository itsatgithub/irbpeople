/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/util/StringInputStream.java,v 1.5 2005/09/27 14:06:24 P001002 Exp $
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

package com.cc.framework.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

/**
 * Wraps a String as an InputStream. Note that data will be lost for
 * characters not in ISO Latin 1, as a simple char->byte mapping is assumed.
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.5 $
 */
public class StringInputStream extends InputStream {

	/**
	 * Source string, stored as a StringReader
	 */
	private StringReader in;

	/**
	 * Composes a stream from a String
	 * 
	 * @param source The string to read from. Must not be <code>null</code>.
	 */
	public StringInputStream(String source) {
		in = new StringReader(source);
	}

	/**
	 * Reads from the Stringreader, returning the same value. Note that
	 * data will be lost for characters not in ISO Latin 1. Clients
	 * assuming a return value in the range -1 to 255 may even fail on
	 * such input.
	 * 
	 * @return the value of the next character in the StringReader
	 * 
	 * @exception IOException if the original StringReader fails to be read
	 */
	public int read() throws IOException {
		return in.read();
	}

	/**
	 * Closes the Stringreader.
	 * 
	 * @exception IOException if the original StringReader fails to be closed
	 */
	public void close() throws IOException {
		in.close();
	}

	/**
	 * Marks the read limit of the StringReader.
	 * 
	 * @param limit the maximum limit of bytes that can be read before the 
	 *              mark position becomes invalid
	 */
	public synchronized void mark(final int limit) {
		try {
			in.mark(limit);
		} catch (IOException ioe) {
			throw new RuntimeException(ioe.getMessage());
		}
	}

	/**
	 * Resets the StringReader.
	 * 
	 * @exception IOException if the StringReader fails to be reset
	 */
	public synchronized void reset() throws IOException {
		in.reset();
	}

	/**
	 * @see java.io.InputStream#markSupported()
	 */
	public boolean markSupported() {
		return in.markSupported();
	}
}
