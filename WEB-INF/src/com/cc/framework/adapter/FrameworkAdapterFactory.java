/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/adapter/FrameworkAdapterFactory.java,v 1.8 2005/02/25 12:12:44 P001002 Exp $
 * $Revision: 1.8 $
 * $Date: 2005/02/25 12:12:44 $
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

package com.cc.framework.adapter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cc.framework.common.Factory;
import com.cc.framework.common.Singleton;

/**
 * Factory class for Framework Adapters.
 * An application can register a framework adapter direct by calling
 * the registerAdapter()method or let the factory
 * search for a matching framework adapter.
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.8 $
 */
public abstract class FrameworkAdapterFactory implements Singleton, Factory {

	/**
	 * Commons Logging instance.
	 */
	private static Log log = LogFactory.getLog(FrameworkAdapterFactory.class);

	/**
	 * The cached framework adapter instance
	 */
	private static FrameworkAdapter adapter = null;

	/**
	 * Constructor
	 */
	private FrameworkAdapterFactory() {
		super();
	}
	
	/**
	 * Retrieves an adapter for the current host framework.
	 * The adapter is used to access information from the underlying
	 * host framework -> Common Controls is only a presentation
	 * framework!
	 * 
	 * @return		Framework adapter
	 */
	public static synchronized FrameworkAdapter getAdapter() {

		if (adapter == null) {
			registerAdapter(createAdapter());
		}

		return adapter;
	}

	/**
	 * Registers the Framework Adapter that should be used for this
	 * Application
	 *  
	 * @param		newAdapter Framework Adapter
	 */
	public static void registerAdapter(FrameworkAdapter newAdapter) {
		log.debug("registering framework adapter: " + newAdapter);

		adapter = newAdapter;
	}

	/**
	 * Creates a new framework adapter for the current application.
	 * The decision is made on the current classpath.
	 * It is possible to set a FrameworkAdapter by calling the
	 * registerAdapter() method in a servets init() method()
	 * 
	 * @return		Framework Adapter
	 */
	private static FrameworkAdapter createAdapter() {

		FrameworkAdapter adapter	= null;
		String adapterClassName		= "com.cc.framework.adapter.struts.StrutsFrameworkAdapter";

		// Attempt to load the FrameworkAdapter implementation class
		Class adapterClass = null;
		try {
			log.debug("creating adapter instance: " + adapterClassName);

			adapterClass = loadClass(adapterClassName);

			/*
	 			if (adapterClass == null) {
					throw new FrameworgException
						("No suitable Log implementation for " + logClassName);
				}
				if (!Log.class.isAssignableFrom(logClass)) {
					throw new LogConfigurationException
						("Class " + logClassName + " does not implement Log");
				}
			*/

			adapter = (FrameworkAdapter) adapterClass.newInstance();

		} catch (Throwable t) {
			// throw new LogConfigurationException(t);
		}

		return adapter;
	}

	/**
	 * Return the thread context class loader if available.
	 * Otherwise return null.
	 * 
	 * The thread context class loader is available for JDK 1.2
	 * or later, if certain security conditions are met.
	 *
	 * @return		ClassLoader
	 * @throws		IllegalAccessException error
	 * @throws		InvocationTargetException error
	 */
	protected static ClassLoader getContextClassLoader()
		throws IllegalAccessException, InvocationTargetException {

		ClassLoader classLoader = null;

		try {
			// Are we running on a JDK 1.2 or later system?
			Method method =
				Thread.class.getMethod("getContextClassLoader", null);

			// Get the thread context class loader (if there is one)
			try {
				classLoader =
					(ClassLoader) method.invoke(Thread.currentThread(), null);
			} catch (IllegalAccessException e) {
				throw e;
			} catch (InvocationTargetException e) {
				// InvocationTargetException is thrown by 'invoke' when
				// the method being invoked (getContextClassLoader) throws
				// an exception.
				// 
				// getContextClassLoader() throws SecurityException when
				// the context class loader isn't an ancestor of the
				// calling class's class loader, or if security
				// permissions are restricted.
				// 
				// In the first case (not related), we want to ignore and
				// keep going.  We cannot help but also ignore the second
				// with the logic below, but other calls elsewhere (to
				// obtain a class loader) will trigger this exception where
				// we can make a distinction.
				if (e.getTargetException() instanceof SecurityException) {
					; // ignore
				} else {
					// Capture 'e.getTargetException()' exception for details
					// alternate: log 'e.getTargetException()', and pass back 'e'.
					throw e;
				}
			}
		} catch (NoSuchMethodException e) {
			// Assume we are running on JDK 1.1
			classLoader = LogFactory.class.getClassLoader();
		}

		// Return the selected class loader
		return classLoader;
	}

	/**
	 * MUST KEEP THIS METHOD PRIVATE
	 * 
	 * <p>Exposing this method establishes a security violation.
	 * This method uses <code>AccessController.doPrivileged()</code>.
	 * </p>
	 * 
	 * Load a class, try first the thread class loader, and
	 * if it fails use the loader that loaded this class.
	 * 
	 * @param		name the class name
	 * @return		Class
	 * @throws		ClassNotFoundException error
	 * @throws		IllegalAccessException error
	 * @throws		InvocationTargetException error
	 */
	private static Class loadClass(final String name)
		throws
			ClassNotFoundException,
			IllegalAccessException,
			InvocationTargetException {

		Object result = AccessController.doPrivileged(new PrivilegedAction() {
			public Object run() {

				ClassLoader threadCL = null;

				try {
					threadCL = getContextClassLoader();
				} catch (Exception e) {
					// ignore
				}

				if (threadCL != null) {
					try {
						return threadCL.loadClass(name);
					} catch (ClassNotFoundException ex) {
						// ignore
					}
				}
				try {
					return Class.forName(name);
				} catch (ClassNotFoundException e) {
					return e;
				}
			}
		});

		if (result instanceof Class) {
			return (Class) result;
		}

		throw (ClassNotFoundException) result;
	}
}
