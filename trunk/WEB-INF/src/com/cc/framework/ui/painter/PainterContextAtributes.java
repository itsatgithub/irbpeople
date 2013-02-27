/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/PainterContextAtributes.java,v 1.14 2005/08/02 19:15:01 P001002 Exp $
 * $Revision: 1.14 $
 * $Date: 2005/08/02 19:15:01 $
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

package com.cc.framework.ui.painter;

/**
 * PainterContext Atribute Names
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision: 1.14 $
 */
public interface PainterContextAtributes {

	/**
	 * Decoration elements that should be displayed next to a control
	 * 
	 * <p>Type: Collection&lt;ConcreteElement&gt;
	 */
	public String DECORATIONS = "decorations";

	/**
	 * Indicates that the Decoration collection has been processed
	 * 
	 * <p>Type: Boolean
	 */
	public String DECORATIONS_DONE = "decorations.done";

	/**
	 * The <b>id</b> of a formelement will be saves as an attribute in the painter
	 * context. So embedded controls can use this <b>id</b>. This is nesseccary if a
	 * JavaScript handler should be generated.
	 * 
	 * <p>Type: String
	 */
	public String FORMID = "formid";

	/**
	 * Form type
	 * 
	 * <p>type: Boolean (default is <code>false</code>)
	 */
	public String DISPLAY = "display";

	/**
	 * This Attribute will force the painter context to generate a unique html style
	 * id for a control element
	 * 
	 * <p>Type: String
	 */
	public String FORCE_STYLEID = "styleid";

	/**
	 * If this attribute is set to <b>"off"</b> the painters will not generate
	 * any HTML comments
	 * 
	 * <p>Type: Boolean (default is <code>true</code>)
	 */
	public String COMMENTS = "comments";

	/**
	 * The Name of the nesting collumn
	 * 
	 * <p>Type: String
	 */
	public String COLUMN = "column";

	/**
	 * The Key of the nesting row
	 * 
	 * <p>Type: String
	 */
	public String ROWKEY = "rowkey";
}