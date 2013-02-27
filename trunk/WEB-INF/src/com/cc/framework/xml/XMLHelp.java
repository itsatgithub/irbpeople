/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/xml/XMLHelp.java,v 1.15 2005/08/23 12:22:27 P001002 Exp $
 * $Revision: 1.15 $
 * $Date: 2005/08/23 12:22:27 $
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

package com.cc.framework.xml;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Hashtable;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Helper for managing XML-Structurs
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.15 $
 * @since      1.0
 */
public abstract class XMLHelp {

	/**
	 * XML Entities
	 */
	private static final Hashtable ENTITIES;

	static {
		ENTITIES = new Hashtable();
		ENTITIES.put("\"", "&quot;");
		ENTITIES.put("&", "&amp;");
		ENTITIES.put("'", "&apos");
		ENTITIES.put("<", "&lt;");
		ENTITIES.put(">", "&gt;");
	}

	/**
	 * Constructor
	 */
	private XMLHelp() {
		super();
	}

	/**
	 * Method encodeXml
	 * @param	str	String to encode
	 * @return	String
	 */
	public static String encodeXml(String str) {

		StringBuffer buf = new StringBuffer();

		if (str != null) {
			int len	= str.length();

			for (int i = 0; i < len; i++) {
				String c = "" + str.charAt(i);

				if (ENTITIES.containsKey(c)) {
					buf.append(ENTITIES.get(c));
				} else {
					buf.append(c);
				}
			}
		}

		return buf.toString();
	}

	/**
	 * Converts the XML Document into a String
	 *
	 * @param	node	Node
	 * @return	XML Structure as a String
	 * @throws	IOException	If an input or output exception occurred
	 */
	public static String asString(Node node) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream(4096);
		writeDomTree(out, node);

		return out.toString();
	}
	
	/**
	 * Prints the specified node, recursively.
	 * 
	 * @param out
	 *            OutputStream
	 * @param node
	 *            Node
	 * @throws IOException
	 *             If an input or output exception occurred
	 */
	public static void writeDomTree(OutputStream out, Node node)
			throws IOException {

		PrintStream ps = new PrintStream(out);

		writeDomTree(ps, node);
	}

	/**
	 * Prints the specified node, recursively.
	 * 
	 * @param out
	 *            OutputStream
	 * @param node
	 *            Node
	 * @throws IOException
	 *             If an input or output exception occurred
	 */
	public static void writeDomTree(PrintStream out, Node node) throws IOException {

		if (null == node) {
			return;
		}

		int type = node.getNodeType();

		switch (type) {

			// print comment node
			case Node.COMMENT_NODE :
				out.print("<!-- ");
				out.print(node.getNodeValue());
				out.print(" -->");
				break;

			// print text node
			case Node.TEXT_NODE :
				out.print(encodeXml(node.getNodeValue()));
				break;

			// print the document element
			case Node.DOCUMENT_NODE :
				Document doc = (Document) node;

				out.print("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" ?>");

				writeDomTree(out, doc.getDocumentElement());
				break;

			// print element with attributes
			case Node.ELEMENT_NODE :

				out.print("<");
				out.print(node.getNodeName());

				NamedNodeMap attrs = node.getAttributes();

				for (int i = 0; i < attrs.getLength(); i++) {
					Node attr = attrs.item(i);

					out.print(" ");
					out.print(attr.getNodeName());
					out.print("=\"");
					out.print(encodeXml(attr.getNodeValue()));
					out.print("\"");
				}

				NodeList children = node.getChildNodes();

				if ((null == children) || (0 == children.getLength())) {
					out.print("/>");
				} else {
					out.print(">");

					int len	= children.getLength();

					for (int i = 0; i < len; i++) {
						writeDomTree(out, children.item(i));
					}

					out.print("</");
					out.print(node.getNodeName());
					out.print(">");
				}

				break;

			// handle entity reference nodes
			case Node.ENTITY_REFERENCE_NODE :
				out.print("&");
				out.print(node.getNodeName());
				out.print(";");

				break;

			// print cdata sections
			case Node.CDATA_SECTION_NODE :
				out.print("<![CDATA[");
				out.print(node.getNodeValue());
				out.print("]]>");
				break;

			// print processing instruction
			case Node.PROCESSING_INSTRUCTION_NODE :
				out.print("<?");
				out.print(node.getNodeName());
				out.print(" ");
				out.print(node.getNodeValue());
				out.print("?>");
				break;

			default :
				out.print("invalid node type");
		}
	}

	/**
	 * Sets a node prefix
	 *
	 * @param	node	The node
	 * @param	prefix	The prefix
	 * @throws	IOException	If an input or output exception occurred
	 */
	public static void setNodePrefix(Node node, String prefix) throws IOException {

		if (null == node) {
			return;
		}

		if (node.getNodeType() == Node.ELEMENT_NODE) {
			node.setPrefix(prefix);
		}

		NodeList children = node.getChildNodes();

		int len	= children.getLength();
		for (int i = 0; i < len; i++) {
			setNodePrefix(children.item(i), prefix);
		}
	}

	/**
	 * Converts a Node to a byte vector
	 *
	 * @param	node	Node
	 * @return	byte array
	 * @throws	IOException	If an input or output exception occurred
	 */
	public static byte[] toByteVector(Node node) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		writeDomTree(baos, node);

		return baos.toByteArray();
	}

	/**
	 * Returns the value for an attribute from a node
	 *
	 * @param	node		The node
	 * @param	attribute	The wanted attribute
	 * @return	The attribute value
	 */
	public static String getAttribute(Node node, String attribute) {

		if (null == node) {
			return null;
		}

		Node attr	= node.getAttributes().getNamedItem(attribute);

		if (null == attr) {
			return null;
		} else {
			return attr.getNodeValue();
		}
	}

	/**
	 * Method getSafeAttribute
	 *
	 * @param	element		Element
	 * @param	node		Node
	 * @param	attribute	Attribute
	 * @return	String
	 */
	public static String getSafeAttribute(Element element, String node, String attribute) {

		Node theNode	= null;

		if (null != element) {
			NodeList nl	= element.getElementsByTagName(node);

			if (nl.getLength() > 0) {
				theNode	= nl.item(0);
			}
		}

		if (null != theNode) {
			return getSafeAttribute(theNode, attribute);
		} else {
			return "";
		}
	}

	/**
	 * Method getSafeAttribute
	 *
	 * @param	node		Node
	 * @param	attribute	Attribute
	 * @return	String
	 */
	public static String getSafeAttribute(Node node, String attribute) {

		if (null == node) {
			return "";
		}

		Node attr	= node.getAttributes().getNamedItem(attribute);

		if (null == attr) {
			return "";
		} else {
			return attr.getNodeValue();
		}
	}

	/**
	 * Method getSafeAttributeBoolean
	 *
	 * @param	node		Node
	 * @param	attribute	Attribute
	 * @param	defValue	Default value
	 * @return	boolean
	 */
	public static boolean getSafeAttributeBoolean(Node node, String attribute, boolean defValue) {

		String value	= getSafeAttribute(node, attribute);

		if ("".equals(value)) {
			return defValue;
		} else {
			return "true".equalsIgnoreCase(value);
		}
	}

	/**
	 * Method getSafeAttributeInt
	 *
	 * @param	node		Node
	 * @param	attribute	Attribute
	 * @param	defValue	The defualt value
	 * @return	int
	 */
	public static int getSafeAttributeInt(Node node, String attribute, int defValue) {

		String value	= getSafeAttribute(node, attribute);

		if (value.equals("")) {
			return defValue;
		} else {
			return Integer.parseInt(value);
		}
	}

	/**
	 * Method getSafeAttributeDouble
	 *
	 * @param	node		Node
	 * @param	attribute	Attribute
	 * @param	defValue	default value
	 * @return	double
	 */
	public static double getSafeAttributeDouble(Node node, String attribute, double defValue) {

		String value	= getSafeAttribute(node, attribute);

		if (value.equals("")) {
			return defValue;
		} else {
			return Double.parseDouble(value);
		}
	}

	/**
	 * Method getSafeBoolean
	 *
	 * @param	element	Element
	 * @param	node	Node
	 * @return	boolean
	 */
	public static boolean getSafeBoolean(Node element, String node) {

		String value = getSafeString(element, node);

		return (value.equalsIgnoreCase("true")
			|| value.equalsIgnoreCase("ja")
			|| value.equalsIgnoreCase("yes")
			|| value.equals("1"));
	}

	/**
	 * Method getSafeInt
	 *
	 * @param	element		Element
	 * @param	node		Node
	 * @param	defValue	The default value
	 * @return	int
	 */
	public static int getSafeInt(Element element, String node, int defValue) {

		String value	= getSafeString(element, node);

		if (value.equals("")) {
			return defValue;
		} else {
			return Integer.parseInt(value);
		}
	}

	/**
	 * Method getSafeString
	 *
	 * @param	element	Element
	 * @return	String
	 */
	public static String getSafeString(Node element) {

		String value	= "";

		if (null != element) {
			Node node	= element.getFirstChild();

			if (null != node) {
				value	= node.getNodeValue();
			}
		}

		return value;
	}

	/**
	 * Method getSafeString
	 *
	 * @param	element		Element
	 * @param	node		Node
	 * @param	subNode		SubNode
	 * @return	String
	 */
	public static String getSafeString(Element element, String node, String subNode) {

		Node theNode	= null;

		if (null != element) {
			NodeList nl	= element.getElementsByTagName(node);

			if (nl.getLength() > 0) {
				nl	= ((Element) nl.item(0)).getElementsByTagName(subNode);

				if (nl.getLength() > 0) {
					theNode	= nl.item(0).getFirstChild();
				}
			}
		}

		if (null != theNode) {
			return theNode.getNodeValue();
		} else {
			return "";
		}
	}

	/**
	 * Method getSafeString
	 *
	 * @param	element		Element
	 * @param	node		Node
	 * @return	string
	 */
	public static String getSafeString(Node element, String node) {

		Node theNode	= null;

		if (null != element) {
			NodeList nl	= ((Element) element).getElementsByTagName(node);

			if (nl.getLength() > 0) {
				theNode	= nl.item(0).getFirstChild();
			}
		}

		if (null != theNode) {
			return theNode.getNodeValue();
		} else {
			return "";
		}
	}

	/**
	 * Converts a string to a boolean
	 *
	 * @param	str	String to converte
     * @return  <code>true</code> if the argument stands for true;
     *          <code>false</code> otherwise.
	 */
	public static boolean toBool(String str) {

		if (null == str) {
			return false;
		}

		return str.equalsIgnoreCase("ja") || str.equalsIgnoreCase("true") || str.equalsIgnoreCase("1");
	}
}
