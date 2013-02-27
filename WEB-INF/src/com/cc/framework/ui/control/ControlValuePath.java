/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/control/ControlValuePath.java,v 1.15 2005/02/16 18:03:02 P001001 Exp $
 * $Revision: 1.15 $
 * $Date: 2005/02/16 18:03:02 $
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

package com.cc.framework.ui.control;

import java.util.Arrays;
import java.util.Properties;

import com.cc.framework.http.HttpUtil;
import com.cc.framework.util.StringHelp;

/**
 * This class encapsulates all information necessary to
 * locate a control after a server roundtrip and synchronise its
 * data model with the request values.
 *
 * @author    <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version   $Revision: 1.15 $
 * @since     1.3
 */
public class ControlValuePath {

	/** Prefix */
	private static final String PREFIX = "ctrlv";


	/** Key for the controls name */
	public static final String KEY_CONTROL = "ctrl";

	/** Key for the ControlValuePath type attribute */
	public static final String KEY_TYPE = "type";

	/** Key for the ControlValuePath row attribute */
	public static final String KEY_ROW = "row";

	/** Key for the ControlValuePath column attribute */
	public static final String KEY_COLUMN = "col";

	/** Key for the ControlValuePath selectmode attribute */
	public static final String KEY_SELECTMODE = "sel";


	/** Indicator for text values */
	public static final String TYPE_TEXT = "txt";

	/** Indicator for radio button (horicontal alignment) */
	public static final String TYPE_RADIO = "rd";

	/** Indicator for a nested control */
	public static final String TYPE_NESTEDCONTROL = "ctrl";

	/**
	 * Old value indicator for checkbox values.
	 * Only checkboxes with value "on" are included in the HTTP Request.
	 * To recognize the unchecked checkboxes we need a additional
	 * hidden field that holds the value before the server roundtrip
	 */
	public static final String TYPE_CHECKBOX_ORIGINAL = "cbo";

	/** Indicator for a checkbox values */
	public static final String TYPE_CHECKBOX = "cb";

	/**
	 * Properties
	 */
	private Properties props = new Properties();

	/**
	 * Constructor
	 */
	public ControlValuePath() {
		super();
	}

	/**
	 * Constructor
	 *
	 * @param		ctrlname the name of the control for witch
	 * 				to create a ValuePath
	 */
	public ControlValuePath(String ctrlname) {
		super();

		setProperty(KEY_CONTROL, ctrlname);
	}

	/**
	 * Constructor
	 *
	 * @param		ctrl the control for witch to create
	 * 				a ValuePath
	 */
	public ControlValuePath(Control ctrl) {
		super();

		setControl(ctrl);
	}

	/**
	 * Parses a ControlValuePath from the given Request
	 * Parameter key
	 *
	 * @param		path The request key
	 * @return		returns a ValuePath or <code>null</code>
	 * 				if the given request key is not a valid
	 * 				path expression
	 */
	public static ControlValuePath parse(String path) {
		if (!path.startsWith(PREFIX)) {
			// Not a valid Path
			return null;
		} else {
			ControlValuePath p = new ControlValuePath();
			p.decode(path);

			return p;
		}
	}

	/**
	 * Decodes a String encoded Path
	 *
	 * @param		path String encoded path
	 */
	private void decode(String path) {
		props.clear();

		path = HttpUtil.urlDecode(path.substring(PREFIX.length()));

		String[] property = StringHelp.split(path, ";");

		for (int i = 0; i < property.length; i++) {
			String[] tokens = StringHelp.split(property[i], "=");

			if (tokens.length == 2) {
				props.setProperty(tokens[0], tokens[1]);
			} else {
				throw new IllegalArgumentException("Missing value in conrool path for parameter [" + tokens[0] + "]");
			}
		}
	}

	/**
	 * Creates a String for the path instance which can be
	 * used in HTML-Pages
	 *
	 * @param		props Property table
	 * @return		String encoded Path
	 */
	private static String encode(Properties props) {
		StringBuffer buf = new StringBuffer();

		String[] keys = new String[props.size()];
		props.keySet().toArray(keys);

		Arrays.sort(keys);

		for (int i = 0; i < keys.length; i++) {
			if (i != 0) {
				buf.append(";");
			}

			buf
				.append(keys[i])
				.append("=")
				.append(props.getProperty(keys[i]));
		}

		return HttpUtil.urlEncode(buf.toString());
	}

	/**
	 * Creates a String for the path instance which can be
	 * used in HTML-Pages
	 *
	 * @param		propsList Property table
	 * 				"key=value;key=value;key=value;...."
	 * @return		String encoded Path
	 */
	private static String encode(String propsList) {
		Properties props = new Properties();

		String[] tokens = StringHelp.split(propsList, ";");
		if (tokens != null) {
			for (int i = 0; i < tokens.length; i++) {
				String[] d = StringHelp.split(tokens[i], "=");

				if ((d != null) && (d.length == 2)) {
					props.setProperty(d[0], d[1]);
				}
			}
		}

		return encode(props);
	}

	/**
	 * Reset the parameter list
	 */
	public void reset() {
		props.clear();
	}

	/**
	 * Set the Control
	 *
	 * @param		ctrl Control instance
	 */
	public void setControl(Control ctrl) {
		setProperty(KEY_CONTROL, ctrl.getControlName());
	}

	/**
	 * @return		The control name
	 */
	public String getControl() {
		return getProperty(KEY_CONTROL);
	}

	/**
	 * Sets a property
	 *
	 * @param		key property key
	 * @param		value property value
	 */
	public void setProperty(String key, Object value) {
		props.setProperty(key, String.valueOf(value));
	}

	/**
	 * Gets a property value
	 *
	 * @param		key property key
	 * @return		Property value
	 */
	public String getProperty(String key) {
		return props.getProperty(key);
	}

	/**
	 * Creates a String for the path instance which can be
	 * used in HTML-Pages
	 *
	 * @param		props Property table
	 * @return		String encoded Path
	 */
	public static String toString(Properties props) {
		return PREFIX + encode(props);
	}

	/**
	 * Creates a String for the path instance which can be
	 * used in HTML-Pages
	 *
	 * @param		propsList Property table
	 * 				"key=value;key=value;key=value;...."
	 * @return		String encoded Path
	 */
	public static String toString(String propsList) {
		return PREFIX + encode(propsList);
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return PREFIX + encode(props);
	}
}