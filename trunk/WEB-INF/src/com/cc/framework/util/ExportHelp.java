/*
 * $Header: d:/repository/cvs/cc-samples-ext-2/tools/com/cc/framework/util/ExportHelp.java,v 1.1 2005/03/16 08:28:16 P001002 Exp $
 * $Revision: 1.1 $
 * $Date: 2005/03/16 08:28:16 $
 *
 * ====================================================================
 *
 * Copyright (c) 2000 - 2004 SCC Informationssysteme GmbH. All rights
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
 
package com.cc.framework.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.util.MessageResources;

import com.cc.framework.adapter.struts.ActionContext;
import com.cc.framework.ui.model.ColumnDesignModel;
import com.cc.framework.ui.model.ColumnDrilldownDesignModel;
import com.cc.framework.ui.model.ColumnTextDesignModel;
import com.cc.framework.ui.model.DataModel;
import com.cc.framework.ui.model.DesignModel;
import com.cc.framework.ui.model.ListDataModel;
import com.cc.framework.ui.model.ListDesignModel;

/**
 * This utility class provides some helpers to export
 * the data from a list or tree into an other format
 * like an excel workbook, a xml or csv file.
 * 
 * @author	<a href="mailto:gschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version	$Revision: 1.1 $
 */
public final class ExportHelp {


	/**
	 * Creates an excel workbook from a ListDataModel
	 * 
	 * @param	ctx			The action context
	 * @param	dataModel	The data model
	 * @param	designModel	The design model
	 * @exception	IOException Signals that an I/O exception of some sort has occurred.
	 */
	public static final synchronized void sendExcel(ActionContext ctx, DataModel dataModel, DesignModel designModel) throws IOException {
		
		// currently only the ListControl is supported!
		
		ByteArrayOutputStream baos = doCreateWorkbook(ctx, dataModel, designModel);
				
		HttpServletResponse response = ctx.response();
			 		
		response.setHeader("Cache-Control", "max-age=0");
		response.setContentType("application/vnd.ms-excel");
		response.setContentLength(baos.size());

		ServletOutputStream os = response.getOutputStream();
		os.write(baos.toByteArray());
		os.flush();
	}

	/**
	 * Creates an excel workbook from a ListDataModel
	 * This method needs also the design model, because
	 * only the data, which is displayed, should be written out.
	 * 
	 * @param	ctx			The action context
	 * @param	dataModel	The data model
	 * @param	designModel	The design model
	 * @return ByteArrayOutputStream
	 * @exception	IOException	Signals that an I/O exception of some sort has occurred.
	 */
	public static final synchronized ByteArrayOutputStream doCreateWorkbook(ActionContext ctx, DataModel dataModel, DesignModel designModel) throws IOException {
		
		ServletContext servletContext = ctx.session().getServletContext();
		
		Locale locale = (Locale) ctx.session().getAttribute(org.apache.struts.Globals.LOCALE_KEY);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		HSSFSheet sheet = workbook.createSheet();

		HSSFRow row = null;
		
		ListDesignModel design = (ListDesignModel) designModel;
		ListDataModel 	data   = (ListDataModel) dataModel;
		
		
		// write rows		
		for (int i = 0; i < data.size() + 1; i++) {

			row = sheet.createRow((short) i);
			
			for (int j = 0; j < design.getColumns().length; j++) {
			
				String value = ""; 
				ColumnDesignModel cdm = design.getColumns()[j];
			
				// has the user the permission to view this column?
				if (!cdm.getPermission().isGranted(ctx.getPrincipal())) {
					continue;
				}
			
				// in this example we only write drilldown and textcolumns!
				if (cdm instanceof ColumnDrilldownDesignModel || cdm instanceof ColumnTextDesignModel)  {
				
					if (i == 0) {
						// create titel
						
						// Search struts resources 						
						MessageResources res = (MessageResources) servletContext.getAttribute(org.apache.struts.Globals.MESSAGES_KEY);
						if (res != null) {
							value = res.getMessage(locale,  cdm.getTitle());
						}
						
						if (value == null) {
							value = "?" + cdm.getTitle() + "?";
						}
						
					} else {
						// insert values
						Object bean = data.getElementAt(i - 1);
						
						try {
							value = BeanUtils.getProperty(bean, cdm.getProperty());
						} catch (Exception e) {
							// no action
						}
					}

					row.createCell((short) j).setCellValue(value);	
				}
			}
		}
		
		workbook.write(baos);
	
		return baos;
	}

	
}
