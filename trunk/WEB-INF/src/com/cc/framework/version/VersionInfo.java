/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/version/VersionInfo.java,v 1.11 2005/02/16 18:03:28 P001001 Exp $
 * $Revision: 1.11 $
 * $Date: 2005/02/16 18:03:28 $
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

package com.cc.framework.version;

import java.util.Date;

/**
 * Interface for an application version class.<br>
 * To provide an own version class for your application you
 * can implement a class which implements this interface.
 * <p>
 * This interface is also used for configuration files needed
 * by the framework. At the beginning an object is stored in
 * the session under the key "CONFIG_SESSION_KEY". The object
 * must implement this interface.
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.11 $
 * @since      1.0
 */
public interface VersionInfo {

	/**
	 * Returns the build date
	 *
	 * @return	Date
	 */
	public Date getProductBuildDate();

	/**
	 * Returns the product build number
	 *
	 * @return	String
	 */
	public String getProductBuildNumber();

	/**
	 * Returns the product major version
	 *
	 * @return int
	 */
	public int getProductMajorVersion();

	/**
	 * Returns the product minor version
	 *
	 * @return	int
	 */
	public int getProductMinorVersion();

	/**
	 * Returns the product name
	 *
	 * @return	String
	 */
	public String getProductName();

	/**
	 * Returns the product name extension
	 *
	 * @return	String
	 */
	public String getProductNameExtension();

	/**
	 * Returns the product vendor
	 *
	 * @return	String
	 */
	public String getProductVendor();

	/**
	 * Returns the URL of the homepage of the product vendor
	 *
	 * @return	String
	 */
	public String getProductVendorSite();

	/**
	 * Returns the product version
	 *
	 * @return	String
	 */
	public String getProductVersion();

	/**
	 * Returns the system type
	 *
	 * @return	SystemType
	 */
	public SystemType getSystemType();
}
