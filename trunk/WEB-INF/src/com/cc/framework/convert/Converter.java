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

import com.cc.framework.adapter.RequestContext;

/**
 * The Common-Controls framework makes use of Converter classes to
 * convert Datatypes to and from String.
 * A Converter is assigned to one specific Java Datatype (Class).
 * Use ConverterRegisty.register() to register a custom converter. 
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision$
 */
public interface Converter {

	/**
	 * Converts the given String value into the Converters
	 * Datatype
	 * 
	 * @param		ctx Request Context
	 * @param		newValue The String Value to convert
	 * @return		Object of the Converters Datatype
	 * @throws		ConverterException is thrown when a conversion
	 * 				error occurs
	 */
	public Object getAsObject(RequestContext ctx, String newValue)
		throws ConverterException;

	/**
	 * Converts the Object of the converters Datatype into
	 * it's String representation
	 * 
	 * @param		ctx Request Context
	 * @param		value The Object to convert
	 * @return		String
	 * @throws		ConverterException is thrown when a conversion
	 * 				error occurs
	 */
	public String getAsString(RequestContext ctx, Object value)
		throws ConverterException;
}
