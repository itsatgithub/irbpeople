/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/col/ColumnPainterFactory.java,v 1.8 2005/02/25 12:12:50 P001002 Exp $
 * $Revision: 1.8 $
 * $Date: 2005/02/25 12:12:50 $
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

package com.cc.framework.ui.painter.def.col;

import java.util.Hashtable;

import com.cc.framework.ui.model.ColumnButtonDesignModel;
import com.cc.framework.ui.model.ColumnCheckDesignModel;
import com.cc.framework.ui.model.ColumnCheckboxDesignModel;
import com.cc.framework.ui.model.ColumnCommandDesignModel;
import com.cc.framework.ui.model.ColumnDesignModel;
import com.cc.framework.ui.model.ColumnDrilldownDesignModel;
import com.cc.framework.ui.model.ColumnHtmlDesignModel;
import com.cc.framework.ui.model.ColumnImageDesignModel;
import com.cc.framework.ui.model.ColumnLinkDesignModel;
import com.cc.framework.ui.model.ColumnRadioDesignModel;
import com.cc.framework.ui.model.ColumnSelectDesignModel;
import com.cc.framework.ui.model.ColumnTextDesignModel;
import com.cc.framework.ui.model.ColumnTextareaDesignModel;
import com.cc.framework.ui.model.ColumnTreeDesignModel;
import com.cc.framework.ui.painter.def.ColumnPainter;

/**
 * Factory for ColumnPainters. The painter instances are shared.
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz </a>
 * @version $Revision: 1.8 $
 */
public abstract class ColumnPainterFactory {

	/**
	 * The default column painter instance
	 */
	private static ColumnPainter defpainter = new ColumnPainterBase();

	/**
	 * Column painters instances
	 */
	private static Hashtable painters;

	static {
		painters = new Hashtable();
		registerColumnPainter(ColumnButtonDesignModel.class, new ColumnButtonPainter());
		registerColumnPainter(ColumnCheckDesignModel.class, new ColumnCheckPainter());
		registerColumnPainter(ColumnDrilldownDesignModel.class, new ColumnDrilldownPainter());
		registerColumnPainter(ColumnCommandDesignModel.class, new ColumnCommandPainter());
		registerColumnPainter(ColumnHtmlDesignModel.class, new ColumnHtmlPainter());
		registerColumnPainter(ColumnImageDesignModel.class, new ColumnImagePainter());
		registerColumnPainter(ColumnLinkDesignModel.class, new ColumnLinkPainter());
		registerColumnPainter(ColumnSelectDesignModel.class, new ColumnSelectPainter());
		registerColumnPainter(ColumnTextDesignModel.class, new ColumnTextPainter());
		registerColumnPainter(ColumnTextareaDesignModel.class, new ColumnTextareaPainter());
		registerColumnPainter(ColumnCheckboxDesignModel.class, new ColumnCheckboxPainter());
		registerColumnPainter(ColumnRadioDesignModel.class, new ColumnRadioPainter());
		registerColumnPainter(ColumnTreeDesignModel.class, new ColumnTreePainter());
	}

	/**
	 * Constructor
	 */
	private ColumnPainterFactory() {
		super();
	}

	/**
	 * Registers a column painter for the given column class
	 * 
	 * @param		columnClass the columns class
	 * @param		painter Column painter instance
	 */
	public static void registerColumnPainter(Class columnClass, ColumnPainter painter) {
		if (painters.containsKey(columnClass)) {
			painters.remove(columnClass);
		}

		painters.put(columnClass, painter);
	}

	/**
	 * This method creates a concrete column painter for a given column object.
	 * 
	 * @param column
	 *            the concrete column object
	 * @return a painter instance for the concrete column
	 */
	public static ColumnPainter createPainter(ColumnDesignModel column) {

		ColumnPainter painter = (ColumnPainter) painters.get(column.getClass());

		// search for a painter
		if (painter == null) {
			Class columnClass = column.getClass();

			do {
				Class[] interfaces = columnClass.getInterfaces();

				for (int i = 0; (painter == null) && (i < interfaces.length); i++) {
					painter = (ColumnPainter) painters.get(interfaces[i]);
				}
			
				if (painter == null) {
					columnClass = columnClass.getSuperclass();
				}
			} while ((painter == null) && (columnClass != Object.class));
		}

		if (painter == null) {
			return defpainter;
		} else {
			return painter;
		}
	}
}