/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/resource/ResourceManager.java,v 1.18 2005/02/16 18:03:13 P001001 Exp $
 * $Revision: 1.18 $
 * $Date: 2005/02/16 18:03:13 $
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

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Hashtable;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cc.framework.Globals;
import com.cc.framework.http.HttpScope;
import com.cc.framework.ui.model.Cachable;
import com.cc.framework.util.StringHelp;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * The resource manager manages resources in the
 * application, session or request scope.
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.18 $
 * @since      1.0
 */
public final class ResourceManager {

	/**
	 * Logging instance
	 */
	private static Log log = LogFactory.getLog(ResourceManager.class);

	/**
	 * Directory for the Resource Cache
	 */
	private static String cacheDir = "c:/temp";

	/**
	 * Constructor for ResourceManager
	 */
	private ResourceManager() {
		super();
	}

	/**
	 * Registers a JPEG resource
	 *
	 * @param		request HttpServletRequest
	 * @param		scope The scope where to register the Resource
	 * @param		key The resource key
	 * @param		image the image to register
	 * @param		cacheInfo optional caching information
	 */
	public static void registerResource(
		HttpServletRequest request,
		HttpScope scope,
		String key,
		BufferedImage image,
		Cachable cacheInfo) {

		if (image == null) {
			return;
		}

		try {
			log.debug("Encoding Image to JPEG: " + key);

			float quality = 1f;

			ByteArrayOutputStream baos = new ByteArrayOutputStream(0xfff);

			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(baos);
			JPEGEncodeParam param;

			param = encoder.getDefaultJPEGEncodeParam(image);
			param.setQuality(quality, true);

			encoder.encode(image, param);

			// Store the resource in JPEG format
			registerResource(
				request,
				scope,
				key,
				MimeType.JPEG,
				baos.toByteArray(),
				cacheInfo);

			log.debug("Encoding successful: " + key);
		} catch (java.io.IOException ioe) {
			log.error("Error redering JPEG to ByteStream", ioe);
		}
	}

	/**
	 * Registers a binary resource
	 *
	 * @param request	HttpServletRequest
	 * @param scope	HttpScope
	 * @param key	Key
	 * @param type	MimeType
	 * @param body	Resource to register
	 * @param cacheInfo	Cachable
	 */
	public static void registerResource(
		HttpServletRequest request,
		HttpScope scope,
		String key,
		MimeType type,
		byte[] body,
		Cachable cacheInfo) {
		registerResource(
			request,
			scope,
			key,
			new BinaryResource(type, body, cacheInfo));
	}

	/**
	 * Registers a binary resource
	 *
	 * @param request	HttpServletRequest
	 * @param scope	HttpScope
	 * @param key	Key
	 * @param type	MimeType
	 * @param bodyStream Object to register
	 * @param cacheInfo	Cachable
	 */
	public static void registerResource(
		HttpServletRequest request,
		HttpScope scope,
		String key,
		MimeType type,
		String bodyStream,
		Cachable cacheInfo) {
		registerResource(
			request,
			scope,
			key,
			new BinaryResource(type, bodyStream, cacheInfo));
	}

	/**
	 * Registers a object resource
	 *
	 * @param request	HttpServletRequest
	 * @param scope	HttpScope
	 * @param key	Key
	 * @param object	Object to register
	 * @param cacheInfo	Cachable
	 */
	public static void registerResource(
		HttpServletRequest request,
		HttpScope scope,
		String key,
		Object object,
		Cachable cacheInfo) {
		registerResource(
			request,
			scope,
			key,
			new ObjectResource(object, cacheInfo));
	}

	/**
	 * Register a resource in the specified scope
	 *
	 * @param request	HttpServletRequest
	 * @param scope	HttpScope
	 * @param key Key
	 * @param resource	Resource
	 */
	public static void registerResource(
		HttpServletRequest request,
		HttpScope scope,
		String key,
		Resource resource) {
		// Get the Resource Cache.
		// If not available create a new Cache
		Hashtable resources = getResourceCache(request, scope, true);

		if (resources != null) {
			resources.put(key, resource);
		}
	}

	/**
	 * Register a resource in the application scope
	 *
	 * @param context	ServletContext
	 * @param key Key
	 * @param resource	Resource
	 */
	public static void registerResource(
		ServletContext context,
		String key,
		Resource resource) {
		// Get the Resource Cache.
		// If not available create a new Cache
		Hashtable resources = getResourceCache(context, true);

		if (resources != null) {
			resources.put(key, resource);
		}
	}

	/**
	 * Returns a URI for the specified resource
	 * This can be the filename, which is used to store the file on the disk
	 *
	 * @param	ctx			PageContext
	 * @param	scope		HttpScope
	 * @param	key			Key
	 * @param	cacheInfo	Caching Information abaut the Resource or <code>null</code>
	 * @return	String
	 */
	public static String getResourceURI(
		PageContext ctx,
		HttpScope scope,
		String key,
		Cachable cacheInfo) {

		HttpServletRequest request = (HttpServletRequest) ctx.getRequest();

		Resource resource = getResource(request, scope, key);

		if (resource == null) {
			throw new IllegalArgumentException(
				"Accessing unregistered Resource :" + key);
		}

		StringBuffer url =
			new StringBuffer()
				.append(request.getContextPath())
				.append("/")
				.append(scope)
				.append("/");

		// Add some information to prevent the
		// browser from caching the resource on the client side
		if (!HttpScope.APPLICATION.equals(scope) && (request.getSession(false) != null)) {
			url.append(request.getSession(false).getId());
		}

		url.append("-");

		if ((cacheInfo != null) && (cacheInfo.getCacheHint() != null)) {
			url.append(cacheInfo.getCacheHint());
		}

		url.append("/").append(key).append(".res");

		if (HttpScope.SESSION.equals(scope) ||  HttpScope.DIALOG.equals(scope)) {
			HttpServletResponse response = (HttpServletResponse) ctx.getResponse();

			// Allow URL Rewriting
			return response.encodeURL(url.toString());
		} else {
			return url.toString();
		}
	}

	/**
	 * Checks if a registered resource is up to date
	 *
	 * @param	request		The HttpServletRequest
	 * @param	scope		HttpScope
	 * @param	key			Key
	 * @param	cacheInfo	Cachable
	 * @return	boolean
	 */
	public static boolean isUpToDate(
		HttpServletRequest request,
		HttpScope scope,
		String key,
		Cachable cacheInfo) {
		Resource resources = getResource(request, scope, key);

		if (resources != null) {
			return resources.isUpToDate(cacheInfo);
		}

		return false;
	}

	/**
	 * Returns a registered resource from the request cache.
	 * The URI follows the syntax: .../scope/xxxx.res
	 *
	 * @param	request		The HttpServletRequest
	 * @param	uri			The URI for the registered resource
	 * @return	Resource	the registered resource for the requested URI
	 */
	public static Resource getResourceFromURI(
		HttpServletRequest request,
		String uri) {

		String[] tokens = StringHelp.split(uri, "/");

		// Der Scope unter welchem die Ressource abgelegt ist
		if (tokens.length > 1) {
			HttpScope scope = HttpScope.parse(tokens[0]);
			String key = tokens[tokens.length - 1];

			// Der Schlüssel unter welchem die ressource abgelegt ist
			if ((key != null) && key.endsWith(".res")) {
				key = key.substring(0, key.length() - 4);

				Hashtable resources = getResourceCache(request, scope, false);

				if (resources == null) {
					return null;
				} else {
					return (Resource) resources.get(key);
				}
			}
		}

		return null;
	}

	/**
	 * Returns a registered resource out of the cache
	 *
	 * @param	request		The HttpServletRequest
	 * @param	scope		HttpScope
	 * @param	key			Key
	 * @return	Resource
	 */
	public static Resource getResource(
		HttpServletRequest request,
		HttpScope scope,
		String key) {

		Hashtable resources = getResourceCache(request, scope, false);

		if (resources == null) {
			return null;
		}

		return (Resource) resources.get(key);
	}

	/**
	 * Returns the hashtable with resources in the specified scope
	 *
	 * @param	request		HttpServletRequest
	 * @param	scope		HttpScope
	 * @param	create		true, if a new Resourcetable should be created
	 * @return	Hashtable
	 */
	private static Hashtable getResourceCache(
		HttpServletRequest request,
		HttpScope scope,
		boolean create) {
		Hashtable resources = null;

		if (HttpScope.APPLICATION.equals(scope)) {
			HttpSession session = request.getSession();

			if (session != null) {
				resources =
					getResourceCache(session.getServletContext(), create);
			}

		} else if (HttpScope.SESSION.equals(scope)) {
			HttpSession session = request.getSession();

			if (session != null) {
				resources =
					(Hashtable) session.getAttribute(Globals.RESOURCE_KEY);

				// Bei Bedarf wird die Ressourcen Tabelle neu erzeugt
				if (create && (resources == null)) {
					resources = new Hashtable();

					session.setAttribute(Globals.RESOURCE_KEY, resources);
				}
			}

		} else if (HttpScope.REQUEST.equals(scope)) {
			resources = (Hashtable) request.getAttribute(Globals.RESOURCE_KEY);

			// Bei Bedarf wird die Ressourcen Tabelle neu erzeugt
			if (create && (resources == null)) {
				resources = new Hashtable();

				request.setAttribute(Globals.RESOURCE_KEY, resources);
			}
		}

		return resources;
	}

	/**
	 * Returns the hashtable with resources in the application scope
	 *
	 * @param	context		ServletContext
	 * @param	create		true, if a new Resourcetable should be created
	 * @return	Hashtable
	 */
	private static Hashtable getResourceCache(
		ServletContext context,
		boolean create) {
		Hashtable resources =
			(Hashtable) context.getAttribute(Globals.RESOURCE_KEY);

		// Bei Bedarf wird die Ressourcen Tabelle neu erzeugt
		if (create && (resources == null)) {
			resources = new Hashtable();

			context.setAttribute(Globals.RESOURCE_KEY, resources);
		}

		return resources;
	}

	/**
	 * Returns the directory used to cache resources.
	 *
	 * @return String	the cache directory
	 */
	public static String getCacheDir() {
		return cacheDir;
	}

	/**
	 * Sets the directory which should be used to cache resources.
	 *
	 * @param	newCacheDir		The cache directory
	 */
	public static void setCacheDir(String newCacheDir) {
		cacheDir = newCacheDir;
	}
}