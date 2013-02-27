/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/base/InnerFrameTag.java,v 1.2 2005/07/08 14:14:37 P001002 Exp $
 * $Revision: 1.2 $
 * $Date: 2005/07/08 14:14:37 $
 *
 * ====================================================================
 *
 * Copyright (c) 2002 SCC Informationssysteme GmbH. All rights
 * reserved.
 *
 * ====================================================================
 */

package com.cc.framework.taglib.base;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.cc.framework.taglib.FrameContainerTag;
import com.cc.framework.taglib.TagHelp;
import com.cc.framework.ui.model.InnerFrame;

/**
 * Inner frame definition
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.2 $
 */
public class InnerFrameTag extends BodyTagSupport {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 4200927605192890974L;

	/**
	 * Designmodel
	 */
	private InnerFrame frame = null;

	/**
	 * Constructor
	 */
	public InnerFrameTag() {
		super();
	}

	/**
	 * Returns the DesignModel for the inner frame
	 *
	 * @return	ColumnDesignModel
	 */
	protected InnerFrame getInnerFrame() {
		if (frame == null) {
			frame = doCreateInnerFrame();
		}
		
		return frame;
	}

	/**
	 * Creates the inner frame element
	 *
	 * @return		Group Element
	 */
	protected InnerFrame doCreateInnerFrame() {
		return new InnerFrame();
	}

	/**
	 * This method gets called when the design model
	 * is not longer needed
	 */
	protected void releaseInnerFrame() {
		frame = null;
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#setPageContext(PageContext pc)
	 */
	public void setPageContext(PageContext pc) {
		super.setPageContext(pc);

		// create a new model
		releaseInnerFrame();
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#release()
	 */
	public void release() {
		super.release();
		
		releaseInnerFrame();
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {
		FrameContainerTag container = (FrameContainerTag) TagHelp.getParentTag(this, FrameContainerTag.class);

		// Register the control in the container
		container.addInnerFrame(getInnerFrame());

		return EVAL_BODY_BUFFERED;
	}

	/**
	 * @see javax.servlet.jsp.tagext.IterationTag#doAfterBody()
	 */
	public int doAfterBody() throws JspException {
		BodyContent body = getBodyContent();

		if ((body != null)) {
			getInnerFrame().setContent(body.getString());
		}

		return SKIP_BODY;
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {
		// release the design model
		releaseInnerFrame();

		return super.doEndTag();
	}

	/**
	 * Sets the property that can be used for rendering
	 * to non-visual media such as speech or Braille
	 *
	 * @param	summary	specifies a description and/or structure of the object.
	 */
	public void setSummary(String summary) {
		getInnerFrame().setSummary(summary);
	}

	/**
	 * Sets the permission for the element
	 *
	 * @param		permission	Permisson
	 * @throws		JspException If the Argument can't be converted
	 * 				to Permission
	 */
	public void setPermission(String permission) throws JspException {
		getInnerFrame().setPermission(TagHelp.toPermission(permission));
	}

	/**
	 * Sets the height of the frame element (pixel or %)
	 *
	 * @param	height The Height
	 */
	public void setHeight(String height) {
		getInnerFrame().setHeight(height);
	}

	/**
	 * Sets the width of the frame element (pixel or %)
	 *
	 * @param	width The Width
	 */
	public void setWidth(String width) {
		getInnerFrame().setWidth(width);
	}

	/**
	 * Specifies the alignment of the inner fram in the
	 * surrounding container:
	 * <ul>
	 * 		<li>top = top alignment.</li>
	 * 		<li>bottom = bottom alignment.</li>
	 * </ul>
	 *
	 * @param		alignment The alignment of the column
	 * @throws		JspException If the argument can not be parsed
	 */
	public void setValign(String alignment) throws JspException {
		getInnerFrame().setLayoutHint(TagHelp.toAlignment(alignment));
	}

	/**
	 * An HTML-style. See HTML documentation for the attribute style.
	 *
	 * @param	style	An HTML-style
	 */
	public void setStyle(String style) {
		getInnerFrame().setStyle(style);
	}

	/**
	 * The HTML-class attribute. See HTML documentation for the attribute class.
	 *
	 * @param	styleClass	The HTML-class attribute
	 */
	public void setStyleClass(String styleClass) {
		getInnerFrame().setStyleClass(styleClass);
	}

	/**
	 * The HTML-id attribute. See HTML documentation for the Attribute id.
	 *
	 * @param styleId	The HTML-id attribute
	 */
	public void setStyleId(String styleId) {
		getInnerFrame().setStyleId(styleId);
	}

	/**
	 * Enables or Disables the frames border
	 *
	 * @param	border	<code>true</code> if the frames
	 * 			should be visible
	 * @throws	JspException If the Argument can't be converted to boolean
	 */
	public void setBorder(String border) throws JspException {
		getInnerFrame().setBorder(TagHelp.toBoolean(border));
	}
}
