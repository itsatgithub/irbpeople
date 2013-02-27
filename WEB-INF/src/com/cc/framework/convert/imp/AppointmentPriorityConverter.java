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

package com.cc.framework.convert.imp;

import com.cc.framework.adapter.RequestContext;
import com.cc.framework.common.InvalidEnumType;
import com.cc.framework.convert.Converter;
import com.cc.framework.convert.ConverterException;
import com.cc.framework.ui.model.AppointmentPriority;

/**
 * Converter for the AppointmentPriority enumeration type
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision$
 */
public class AppointmentPriorityConverter implements Converter {

	/**
	 * @see com.cc.framework.convert.Converter#getAsObject(com.cc.framework.adapter.RequestContext, java.lang.String)
	 */
	public Object getAsObject(RequestContext ctx, String newValue) throws ConverterException {
		if (newValue == null) {
			return null;
		} else {
			try {
				return AppointmentPriority.parse(newValue);
			} catch (InvalidEnumType iet) {
				throw new ConverterException(iet);
			}
		}
	}

	/**
	 * @see com.cc.framework.convert.Converter#getAsString(com.cc.framework.adapter.RequestContext, java.lang.Object)
	 */
	public String getAsString(RequestContext ctx, Object value) throws ConverterException {
		return ((AppointmentPriority) value).toString();
	}
}