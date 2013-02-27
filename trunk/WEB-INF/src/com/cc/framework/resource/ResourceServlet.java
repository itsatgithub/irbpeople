/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/resource/ResourceServlet.java,v 1.20 2005/07/08 14:13:56 P001002 Exp $
 * $Revision: 1.20 $
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

import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The resource servlet processes an URL an returns an
 * registered resource in the ServletOutputStream.
 * The ResourceServlet must be registerd for the URL *.res
 * in the web deployment descriptor of the application
 * (web.xml).
 * <b>Note:</b> Only binary resources are supported.
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.20 $
 * @since      1.0
 */
public class ResourceServlet extends javax.servlet.http.HttpServlet {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 8277984344384480555L;

	/**
	 * Commons Logging instance.
	 */
	private static Log log = LogFactory.getLog(ResourceServlet.class);

	/**
 	 * @see javax.servlet.http.HttpServlet#service(HttpServletRequest, HttpServletResponse)
 	 */
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String path			= request.getServletPath();

		// the the clients encoding
		String encodings	= request.getHeader("Accept-Encoding");

		try {
			log.info("lookup for binary resource '" + path + "'");

			Resource resource = ResourceManager.getResourceFromURI(request, path);

			if (resource instanceof BinaryResource) {
				BinaryResource res	= (BinaryResource) resource;

				// Check if the resource can be delivered in a zip format
				// and the client accept packed resources
				boolean compress	= res.allowCompression() && ((encodings != null) && (encodings.indexOf("gzip") != -1));
				byte[] body			= res.getBody();

				log.debug("resource mime type is " + res.getType().getContentType());
				log.debug("resource size (raw):" + body.length + " bytes");
				log.debug("resource gzip      :" + compress);

				// Create the HTTP Response
				response.setContentType(res.getType().getContentType());

				if (compress) {
					GZIPOutputStream os = new GZIPOutputStream(response.getOutputStream());
					response.setHeader("Content-Encoding", "gzip");

					os.write(body, 0, body.length);
					os.finish();
					os.flush();

				} else {
					response.setContentLength(body.length);
					ServletOutputStream os = response.getOutputStream();

					os.write(body);
					os.flush();
				}

				log.debug("resource written to outputstream!");

			} else {
				log.debug("resource has no mime type and can't be served!");
			}

		} catch (Throwable t) {
			log.error("Can't serve resource " + path, t);

			throw new ServletException(t.getMessage());
		}
	}
}