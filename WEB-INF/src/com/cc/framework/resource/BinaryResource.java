/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/resource/BinaryResource.java,v 1.13 2005/07/08 14:13:56 P001002 Exp $
 * $Revision: 1.13 $
 * $Date: 2005/07/08 14:13:56 $
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

package com.cc.framework.resource;

import com.cc.framework.ui.model.Cachable;

/**
 * This class represent a binary Web-Resource
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.13 $
 * @since      1.0
 */
public class BinaryResource extends Resource {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -1645075965262251623L;

	/**
	 * MIME Typ of the Resource
	 */
	private MimeType type = MimeType.NONE;

	/**
	 * Resourcedata
	 */
	private byte[] body = null;

	/**
	 * Constructor for BinaryResource
	 *
	 * @param type	MimeType
	 * @param body	Resource Data
	 * @param cacheInfo	Cachable
	 */
	public BinaryResource(MimeType type, byte[] body, Cachable cacheInfo) {
		super(cacheInfo);

		this.type = type;
		this.body = body;
	}

	/**
	 * Constructor for BinaryResource
	 *
	 * @param type	MimeType
	 * @param bodyStream	Ressoure Data
	 * @param cacheInfo	Cachable
	 */
	public BinaryResource(MimeType type, String bodyStream, Cachable cacheInfo) {
		super(cacheInfo);

		this.type = type;
		this.body = bodyStream.getBytes();
	}

	/**
	 * Returns the resource data
	 *
	 * @return	byte
	 */
	public byte[] getBody() {
		return body;
	}

	/**
	 * Gets the Type
	 *
	 * @return Returns a MimeType
	 */
	public MimeType getType() {
		return type;
	}

	/**
	 * Checks if the Resource can be packed.
	 * At this Time that depends on the MimeType
	 *
	 * @return boolean
	 */
	public boolean allowCompression() {
		return MimeType.SVG.equals(type);
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return type.getContentType();
	}
}