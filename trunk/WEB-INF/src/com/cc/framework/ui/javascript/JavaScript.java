/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/javascript/JavaScript.java,v 1.2 2005/07/08 14:16:01 P001002 Exp $
 * $Revision: 1.2 $
 * $Date: 2005/07/08 14:16:01 $
 *
 * ====================================================================
 *
 * Copyright (c) 2002 SCC Informationssysteme GmbH. All rights
 * reserved.
 *
 * ====================================================================
 */

package com.cc.framework.ui.javascript;

/**
 * Specialized JavaScript HTML Script Element 
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.2 $
 */
public class JavaScript extends org.apache.ecs.html.Script {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 3534001950407810551L;

	/**
	 * Constructor
	 */
	public JavaScript() {
		super();

		setType("text/javascript");
		setLanguage("JavaScript");
	}
	
	/**
	 * Constructor
	 * 
	 * @param		element Nested Element
	 */
	public JavaScript(String element) {
		super(element);

		setType("text/javascript");
		setLanguage("JavaScript");
	}
}
