/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/Globals.java,v 1.30 2005/09/27 14:06:22 P001002 Exp $
 * $Revision: 1.30 $
 * $Date: 2005/09/27 14:06:22 $
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

package com.cc.framework;

/**
 * This interface defineds global variables used by the
 * common controls framework.
 * <p>
 * Naming conventions see javax.servlet.ServletRequest
 *
 * @author      <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version     $Revision: 1.30 $
  * @since      1.0
 */
public interface Globals {

	/**
	 * Naming Root key
	 */
	String ROOT_KEY						= Globals.class.getPackage().getName();
	
	/**
	 * Key for the Principal Objekt in the Session
	 */
	String PRINCIPAL_KEY				= ROOT_KEY + ".principal";

	/**
	 * Key to register the Painter Factory in the user  session
	 */
	String PAINTER_KEY					= ROOT_KEY + ".painter";

	/**
	 * Key to register the Painter Context stack in the request.
	 * The Context Stack is used to nest controls
	 */
	String PAINTERCONEXT_STACK_KEY		= ROOT_KEY + ".painter.stack";

	/**
	 * Key to register the resource cache in the
	 * Applikation/Session/Request or Page
	 */
	String RESOURCE_KEY					= ROOT_KEY + ".resource";

    /**
     * Key for the Menucontext in the jsp
     */
    String MENUCONTEXT_KEY				= ROOT_KEY + ".menuctx";

	/**
	 * RequestDecorator Objekt
	 */
	String DECORATOR_KEY 				= ROOT_KEY + ".decorator";

	/**
	 * RequestDecorator Objekt
	 */
	String DECORATOR_ACT_KEY			= ROOT_KEY + ".decorator.act";

	/**
	 * Key for the Dialog Manager
	 */
	String DIALOGMANAGER_KEY			= ROOT_KEY + ".dialog.manager";

	/**
	 * Key for the dialog factory
	 */
	String DIALOGFACTORY_KEY			= ROOT_KEY + ".dialog.factory";

	/**
	 * Key for the runnung dialog instance
	 */
	String DIALOG_KEY					= ROOT_KEY + ".dialog.active";

	/**
	 * Key for the Error Collection in the session
	 * Error Collection is used to achieve the Struts
	 * Error Objects over multiple Server Roundtrips
	 * (if they are stored in the Request ERROR_KEY)
	 */
	String ERRORS_SAVE_KEY				= ROOT_KEY + ".errors";

	/**
	 * Key for the message collection in the session
	 * Message Collection is used to achieve the Struts
	 * Message Objects over multiple Server Roundtrips
	 * (if they are stored in the Request MESSAGE_KEY)
	 */
	String MESSAGES_SAVE_KEY			= ROOT_KEY + ".messages";

	/**
	 * Key for JSP Templates
	 */
	String TEMPLATE_KEY					= ROOT_KEY + ".jsp.tempates";

	/**
	 * Key for Locale Setting
	 */
	String LOCALENAME_KEY				= ROOT_KEY + ".locale";

	/**
	 * Key for Framework Message Resources Configuration
	 */
	String MESSAGE						= ROOT_KEY + ".message";

	/**
	 * Key for buffered JSP Bodies
	 */
	String JSPBODYBUFFER_KEY			= ROOT_KEY + ".jsp.bodybuffer";

	/**
	 * This key indicates that an action has seen the
	 * HTTP-Request before
	 */
	String REQUEST_SEEN					= ROOT_KEY + ".seen.request";

	/**
	 * Ste mame of the Request parameter that is used to synchronize
	 * control states
	 */
	String STATE_PARAM					= ROOT_KEY + ".state";

	/**
	 * Ste mame of the Request parameter that is used to store information
	 * about Converters
	 */
	String CONVERTER_PARAM				= ROOT_KEY + ".cvt";

	// -------------------------------------------------
	//      Constants used by the Struts Framework
	// -------------------------------------------------

	/**
	 * value of org.apache.struts.taglib.html.Constants#BEAN_KEY
	 */
	String STRUTS_BEAN_KEY				= "org.apache.struts.taglib.html.BEAN";

	/**
	 * value of org.apache.struts.taglib.html.Constants#SELECT_KEY
	 */
	String STRUTS_SELECT_KEY			= "org.apache.struts.taglib.html.SELECT";
	
	/**
	 * value of org.apache.struts.Globals#CANCEL_KEY
	 */
	String STRUTS_CANCEL_KEY			= "org.apache.struts.taglib.html.CANCEL";
	
	
}