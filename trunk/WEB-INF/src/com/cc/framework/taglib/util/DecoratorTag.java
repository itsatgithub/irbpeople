/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/util/DecoratorTag.java,v 1.10 2005/07/08 14:15:19 P001002 Exp $
 * $Revision: 1.10 $
 * $Date: 2005/07/08 14:15:19 $
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

package com.cc.framework.taglib.util;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ecs.ConcreteElement;
import org.apache.ecs.ElementContainer;
import org.apache.ecs.html.Input;

import com.cc.framework.http.RequestDecorationType;
import com.cc.framework.http.RequestDecorator;


/**
 * DecoratorTag
 * Note: for future use
 *
 * @author     <a href="mailto:gschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version    $Revision: 1.10 $
 * @since      1.0
 */
public class DecoratorTag extends TagSupport {

    /**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 3693459842749017014L;

	/**
     * Commons Logging instance.
     */
    private transient Log log = LogFactory.getLog(this.getClass());

    /**
     * Objekt aus der Session, welches die HiddenFields
     * enthaelt
     */
    private Hashtable hData = null;


    // --------------------------
    //        methods
    // --------------------------

    /**
     * Constructor
     */
    public DecoratorTag() {
        super();
    }

    /**
	 * @see	javax.servlet.jsp.tagext.Tag#doStartTag()
     */
    public int doStartTag() throws JspException {
        return SKIP_BODY;
    }

    /**
	 * @see	javax.servlet.jsp.tagext.Tag#doEndTag()
     */
    public int doEndTag() throws JspException {
        // get the objekt from the session
        hData = RequestDecorator.getValueList(RequestDecorationType.TYPE_HD_FORM, pageContext);

        if (null == hData || hData.isEmpty()) {
            // no fields found
            return EVAL_PAGE;
        }

        try {
            JspWriter out = pageContext.getOut();
            paint(out);
        } catch (Throwable t) {
            log.error("Error DecoratorTag-Painter.", t);
        }

        return EVAL_PAGE;
    }

    /**
     * Method paint
     *
     * @param    out JsPWriter
     * @throws	 Exception Is thrown when an error occurs
     */
    protected void paint(JspWriter out) throws Exception {
        ElementContainer container = new ElementContainer();

        Enumeration e = hData.keys();

        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            String value = (String) hData.get(key);
            container.addElement(doCreateHiddenField(key, value));
        }

        out.print(container.toString());
    }

    /**
     * Creates the HTML hidden field
     *
     * @param	key Key
     * @param	value Value
     * @return	ConcreteElement
     * @throws	 Exception Is thrown when an error occurs
     */
	protected ConcreteElement doCreateHiddenField(String key, String value) throws Exception {
        Input input = new Input();

        input.setType(Input.hidden);
        input.setID(key);
        input.setName(key);
        input.setValue(value);

        return input;
    }
}