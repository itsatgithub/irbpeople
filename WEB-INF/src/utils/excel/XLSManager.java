package utils.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;

import utils.Pair;
import bussineslogic.controlers.UseCaseUtils;
import bussineslogic.objects.Irbholiday;
import bussineslogic.objects.Personal;

/**
 * This class is used to manage MS Excel Documents
 * @author JustInMind - Guillem Corominas
 *
 */
public class XLSManager {

	private static org.apache.log4j.Category log = org.apache.log4j.Logger.getLogger(XLSManager.class);

	/**
	 * Creates a new instance of an XLSManager()
	 *
	 */
	public XLSManager() {}

	/**
	 * Creates a new Excel Workbook from an Object[]. The Object type of this
	 * array must be a bean which has at least its getter methods for the entire
	 * set of attributes wanted to be exported to the Workbook. The excel file
	 * will be saved in the path specified by outputPath (including the filename)
	 * and the sheet name will be specified by sheetName 
	 * 
	 * @param obs the Object[] to be exported to excel format (.xls)
	 * @param outputPath the full path to the new excel file (including the filename) 
	 * @param sheetName the name of the sheet
	 */
	public void createXLS(Object[] obs, String outputPath, String sheetName) {

		Class aux;
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(sheetName);
		int i; // row identifier
		short j; // method identifier
		short k; //column identifier
		short maxColWidth[], width;
		String columnName;
		try {
			aux = obs[0].getClass();
			maxColWidth = new short[aux.getMethods().length];
			for (i = 0; i < obs.length + 1; i++) { //run over rows
				HSSFRow row = sheet.createRow(i);
				Method[] m = aux.getMethods();
				k = 0;
				for (j = 0; j < m.length; j++) { //run over columns (Object's methods)
					if (m[j].getName().startsWith("get")) { // it's a getter
						//first row will contain the labels
						if (i == 0) {
							columnName = m[j].getName().substring(3);
							row.createCell(k).setCellValue(columnName);
							//initialize maxColWidth array with label sizes
							maxColWidth[k] = (short) columnName.length();
						}
						//the rest of the rows
						else {
							//invoke the method and save its return object
							Object obaux = m[j].invoke(obs[i - 1], new Object[0]);
							if (obaux != null) {
								HSSFCell cell = row.createCell(k);
								//fills the cell with the object
								width = insertValue(wb, cell, obaux);
								//update maximum column width
								maxColWidth[k] = maxim(maxColWidth[k], width);
							}
						}
						k++;
					}
				}
				//adjust column width with the maxColWidth array
				for (short cont = 0; cont < k; cont++) {
					short mida = maxim((short) (sheet.getDefaultColumnWidth() * 256), (short) (maxColWidth[cont] * 256));
					sheet.setColumnWidth(cont, (short) (mida + 256));
				}
			}

			FileOutputStream fileOut = new FileOutputStream(outputPath);
			wb.write(fileOut);
			fileOut.close();

		}
		catch (Exception e) {
			log.warn(e);
		}

	}

	/**
	 * Creates a new Excel Workbook from an Object[]. The Object type of this
	 * array must be a bean which has at least its getter methods for the entire
	 * set of attributes wanted to be exported to the Workbook. The excel file
	 * will be saved in an OutputStream and the sheet name will be specified by sheetName 
	 * 
	 * @param obs the Object[] to be exported to excel format (.xls)
	 * @param sheetName the name of the sheet
	 * @param columnCodes the codes which correspond to each attribute to show from the bean
	 * @param columnName the user-friendly name which corresponds to the attribute in the same position of the columnCode
	 * @param out the OutputStream where the workbook will be saved
	 */
	public void createXLS(Object[] obs, String sheetName, String[] columnCodes, String[] columnNames, OutputStream out) {

		Class aux;
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(sheetName);
		int i; // row identifier
		short j; // method identifier
		short k;
		short maxColWidth[], width;
		String columnName;
		try {

			aux = obs[0].getClass();
			Method[] meths = new Method[columnCodes.length];
			String methname, auxname, auxname2;
			maxColWidth = new short[columnCodes.length];
			// Find all necessary get Methods and Print the table Header
			HSSFRow row = sheet.createRow(0);
			for (k = 0; k < columnCodes.length; k++) {
				auxname = columnCodes[k].substring(0, 1).toUpperCase();
				auxname2 = columnCodes[k].substring(1);
				methname = "get" + auxname + auxname2;
				meths[k] = aux.getMethod(methname, new Class[0]);

				row.createCell(k).setCellValue(columnNames[k]);
				maxColWidth[k] = (short) columnNames[k].length();
			}

			for (i = 0; i < obs.length; i++) { //run over rows
				row = sheet.createRow(i + 1);
				for (j = 0; j < meths.length; j++) { // run over columns (Object's  methods)

					// invoke the method and save its return object
					Object obaux = meths[j].invoke(obs[i], new Object[0]);
					if (obaux != null) {
						HSSFCell cell = row.createCell(j);
						// format the object if it is a date String
						if (meths[j].getName().endsWith("date") || columnCodes[j].contains("_date_")) {
							obaux = composeDate(obaux.toString());
						}
						// fills the cell with the object
						width = insertValue(wb, cell, obaux);
						// update maximum column width
						maxColWidth[j] = maxim(maxColWidth[j], width);
					}
				}
				//adjust column width with the maxColWidth array
				for (short cont = 0; cont < j; cont++) {
					short mida = maxim((short) (sheet.getDefaultColumnWidth() * 256), (short) (maxColWidth[cont] * 256));
					sheet.setColumnWidth(cont, (short) (mida + 256));
				}
			}

			wb.write(out);

		}
		catch (Exception e) {
			log.warn(e);
		}

	}

	public HSSFWorkbook createWorkbook() {

		HSSFWorkbook wb = new HSSFWorkbook();
		return wb;
	}

	public void writeWorkbook(HSSFWorkbook wb, OutputStream out) {

		try {
			wb.write(out);
		}
		catch (Exception e) {
			log.warn(e);
		}
	}

	public HSSFWorkbook createSheet(Object[] obs, HSSFWorkbook wb, String sheetName, String[] columnCodes, String[] columnNames) {

		HSSFSheet sheet = wb.createSheet(sheetName);
		int i; // row identifier
		short j; // method identifier
		short k;
		short maxColWidth[], width;
		String columnName;
		Class aux;
		try {

			aux = obs[0].getClass();
			Method[] meths = new Method[columnCodes.length];
			String methname, auxname, auxname2;
			maxColWidth = new short[columnCodes.length];
			// Find all necessary get Methods and Print the table Header
			HSSFRow row = sheet.createRow(0);
			HSSFCellStyle headerStyle = wb.createCellStyle();
			for (k = 0; k < columnCodes.length; k++) {
				auxname = columnCodes[k].substring(0, 1).toUpperCase();
				auxname2 = columnCodes[k].substring(1);
				methname = "get" + auxname + auxname2;
				meths[k] = aux.getMethod(methname, new Class[0]);

				HSSFCell cell = row.createCell(k);
				cell.setCellValue(columnNames[k]);

				HSSFFont font = wb.createFont();
				font.setFontName(HSSFFont.FONT_ARIAL);
				font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

				headerStyle.setFont(font);

				headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				headerStyle.setFillForegroundColor(new HSSFColor.YELLOW().getIndex());
				headerStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
				headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
				headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);

				cell.setCellStyle(headerStyle);
				maxColWidth[k] = (short) columnNames[k].length();
			}

			HSSFCellStyle cellstyle = wb.createCellStyle();

			for (i = 0; i < obs.length; i++) { //run over rows
				row = sheet.createRow(i + 1);
				for (j = 0; j < meths.length; j++) { // run over columns (Object's  methods)

					// invoke the method and save its return object
					Object obaux = meths[j].invoke(obs[i], new Object[0]);
					if (obaux != null) {
						HSSFCell cell = row.createCell(j);
						// format the object if it is a date String
						if (meths[j].getName().endsWith("date") || columnCodes[j].contains("_date_")) {
							obaux = composeDate(obaux.toString());
						}
						// fills the cell with the object
						width = insertValue(wb, cell, obaux, cellstyle);
						// update maximum column width
						maxColWidth[j] = maxim(maxColWidth[j], width);
					}
				}
				//adjust column width with the maxColWidth array
				for (short cont = 0; cont < j; cont++) {
					short mida = maxim((short) (sheet.getDefaultColumnWidth() * 256), (short) (maxColWidth[cont] * 256));
					sheet.setColumnWidth(cont, (short) (mida + 256));
				}
			}

		}
		catch (Exception e) {
			log.warn(e);
		}

		return wb;
	}

	/**
	 * Creates a new Excel Workbook from an Object[]. The Object type of this
	 * array must be a bean which has at least its getter methods for the entire
	 * set of attributes wanted to be exported to the Workbook. The excel file
	 * will be saved in the path specified by outputPath (including the filename)
	 * and the sheet name will be specified by sheetName 
	 * 
	 * @param obs the Object[] to be exported to excel format (.xls)
	 * @param outputPath the full path to the new excel file (including the filename) 
	 * @param sheetName the name of the sheet
	 * @param columnCodes the codes which correspond to each attribute to show from the bean
	 * @param columnName the user-friendly name which corresponds to the attribute in the same position of the columnCode
	 */
	public void createXLS(Object[] obs, String outputPath, String sheetName, String[] columnCodes, String[] columnNames) {
		try {
			FileOutputStream fileOut = new FileOutputStream(outputPath);
			createXLS(obs, sheetName, columnCodes, columnNames, fileOut);
			fileOut.close();
		}
		catch (FileNotFoundException e) {
			log.warn(e);
		}
		catch (IOException e) {
			log.warn(e);
		}

	}

	/**
	 * This method inserts the Object obaux using POI's insertValue method
	 * that fits most closely to the Object's real subclass.
	 * 
	 * @param wb - the workbook that containins the cell
	 * @param cell - the cell where the value has to be inserted
	 * @param obaux - the Object which has to inserted as it's subclass type
	 * @return a short value containing the number of characters written to the cell
	 */

	protected short insertValue(HSSFWorkbook wb, HSSFCell cell, Object obaux) {
		return insertValue(wb, cell, obaux, null);

	}

	protected short insertValue(HSSFWorkbook wb, HSSFCell cell, Object obaux, HSSFCellStyle cellStyle) {

		if (cellStyle == null) {
			cellStyle = wb.createCellStyle();
		}

		String obtype = obaux.getClass().getName();
		short mida = 0;
		if (obtype.equals("java.lang.Integer") || obtype.equals("java.lang.Byte") || obtype.equals("java.lang.Short") || obtype.equals("java.lang.Long") || obtype
				.equals("java.lang.Float") || obtype.equals("java.lang.Double") || obtype.equals("java.lang.Number")) {
			cell.setCellValue(((Number) obaux).doubleValue());
			mida = (short) ((Number) obaux).toString().length();
		} else if (obtype.equals("java.math.BigDecimal")) {
			cell.setCellValue(((java.math.BigDecimal) obaux).doubleValue());
			mida = (short) ((java.math.BigDecimal) obaux).toString().length();
		} else if (obtype.equals("java.math.BigInteger")) {
			cell.setCellValue(((java.math.BigInteger) obaux).doubleValue());
			mida = (short) ((java.math.BigInteger) obaux).toString().length();
		} else if (obtype.equals("java.lang.String")) {
			if (obtype.startsWith("=")) {
				cell.setCellStyle(cellStyle);
				cell.setCellFormula((String) obaux);
			} else {
				cell.setCellStyle(cellStyle);
				cell.setCellValue((String) obaux);
			}
			mida = (short) ((String) obaux).length();
		} else if (obtype.equals("java.lang.Boolean")) {
			cell.setCellValue(((Boolean) obaux).booleanValue());
			mida = (short) ((Boolean) obaux).toString().length();
		} else if (obtype.equals("java.sql.Date")) {
			cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
			cell.setCellStyle(cellStyle);
			cell.setCellValue((java.sql.Date) obaux);
			mida = (short) ((java.sql.Date) obaux).toString().length();
		} else if (obtype.equals("java.sql.Time")) {
			cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("h:mm"));
			cell.setCellStyle(cellStyle);
			cell.setCellValue((java.sql.Time) obaux);
			mida = (short) ((java.sql.Time) obaux).toString().length();
		} else if (obtype.equals("java.sql.Timestamp")) {
			cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
			cell.setCellStyle(cellStyle);
			cell.setCellValue((java.sql.Timestamp) obaux);
			mida = (short) ((java.sql.Timestamp) obaux).toString().length();
		} else if (obtype.equals("java.util.Date")) {
			cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
			cell.setCellStyle(cellStyle);
			cell.setCellValue((java.util.Date) obaux);
			mida = (short) ((java.util.Date) obaux).toString().length();

		}
		return mida;
	}

	/**
	 * Simple method to obtain the maximum value of "a" and "b"
	 * @param a - first short value
	 * @param b - second short value
	 * @return - if (a>b)-> a; else b; 
	 */
	protected short maxim(short a, short b) {
		if (a >= b)
			return a;
		else
			return b;
	}

	public HSSFWorkbook getWorkbook(InputStream is) throws IOException {
		//Create a new Workbook from the stream
		return new HSSFWorkbook(is);
	}

	public HSSFSheet[] getSheets(HSSFWorkbook wb) {
		int numSheets = wb.getNumberOfSheets();
		HSSFSheet sheets[] = new HSSFSheet[numSheets];
		for (int i = 0; i < numSheets; i++) {
			sheets[i] = wb.getSheetAt(i);
		}
		return sheets;
	}

	public LinkedList createObjectList(File file, String objectname, String[] columns) throws Exception {
		FileInputStream fis = new FileInputStream(file.getAbsoluteFile());
		HSSFWorkbook wb = getWorkbook(fis);
		HSSFSheet sheets[] = getSheets(wb);
		return createObjectList(sheets[0], objectname, columns);
	}

	public LinkedList createObjectList(InputStream in, String objectname, String[] columns) throws Exception {
		HSSFWorkbook wb = getWorkbook(in);
		HSSFSheet sheets[] = getSheets(wb);
		return createObjectList(sheets[0], objectname, columns);
	}

	public LinkedList createObjectList(HSSFSheet sheet, String objectname, String[] columns) throws Exception {
		Logger log = Logger.getLogger("XLSManager");
		log.info("importing worksheet");
		Class aux = Class.forName(objectname);
		//Get all the methods of the objectname
		Method allMeths[] = aux.getMethods();
		Method setMeths[] = new Method[allMeths.length];
		short numSetMeths = 0;

		//Store only the "setter" methods in a Method[]
		for (short i = 0; i < allMeths.length; i++) {
			if (allMeths[i].getName().startsWith("set")) {
				setMeths[numSetMeths] = allMeths[i];
				numSetMeths++;
			}
		}
		//Create an Object[] which length equals to the number of rows
		LinkedList obs = new LinkedList();
		LinkedList methods = new LinkedList();

		for (int col = 0; col < columns.length; col++)
			methods.add("set" + firstUpper(columns[col]));

		boolean found;
		//Run over the rows
		int firstRN = sheet.getFirstRowNum();
		int lastRN = sheet.getLastRowNum();

		short firstCN = 0;
		short lastCN = 0;
		if (lastRN != 0) {
			HSSFRow firstRow = sheet.getRow(firstRN);
			firstCN = firstRow.getFirstCellNum();
			lastCN = firstRow.getLastCellNum();
		}
		String errors = "";
		int i;

		int visibleColumnsIndex = 0;

		//The first row contains the labels, so we ignore it
		firstRN++;

		for (i = firstRN; i <= lastRN && lastRN != 0; i++) {
			HSSFRow row = sheet.getRow(i);
			Constructor c = aux.getConstructor(new Class[0]);
			Object ob = c.newInstance(new Object[0]);

			//Run over the columns
			for (short j = firstCN; j < lastCN; j++) {
				HSSFCell cell = row.getCell(j);
				//The first row contains the titles (the method names)
				found = false;
				//look for the method which matches column name
				for (int k = 0; k < numSetMeths && !found; k++) {
					if (setMeths[k].getName().equals(methods.get(j))) {
						//call the private method to do the tuff work
						try {
							setCellValue(ob, setMeths[k], cell);
						}
						catch (java.lang.IllegalArgumentException e) {
							//errors+="cell["+(i+1)+"]["+(j+1)+"] <br> ";
							String xx = new Character((char) (65 + j)).toString();
							errors += xx + ":" + (i + 1) + " <br>";
						}
						found = true;
					}
				}
			}
			obs.add(ob);
		}
		if (errors.length() != 0)
			throw new CellFormatException(errors);
		log.info((i - 1) + " rows found");
		return obs;

	}

	private void setCellValue(Object ob, Method meth, HSSFCell cell) throws Exception {
		//System.out.println(meth.getName());
		Logger log = Logger.getLogger("XLSManager");
		int type;
		try {
			type = cell.getCellType();
		}
		catch (Exception e) {
			type = HSSFCell.CELL_TYPE_BLANK;
		}
		Class[] cls;
		Object params[] = new Object[1];

		if (type == HSSFCell.CELL_TYPE_STRING) {
			String str = cell.getStringCellValue();
			params[0] = str;
			meth.invoke(ob, params);
		} else if (type == HSSFCell.CELL_TYPE_BOOLEAN) {
			Boolean bool = new Boolean(cell.getBooleanCellValue());
			params[0] = bool;
			meth.invoke(ob, params);
		} else if (type == HSSFCell.CELL_TYPE_NUMERIC) {
			cls = meth.getParameterTypes();
			if ((cls[0].getName()).equals("java.lang.Integer")) {
				Double d = new Double(cell.getNumericCellValue());
				params[0] = new Integer(d.intValue());
			} else if ((cls[0].getName()).equals("java.lang.Short")) {
				Double d = new Double(cell.getNumericCellValue());
				params[0] = new Short(d.shortValue());
			} else if ((cls[0].getName()).equals("java.lang.Long")) {
				Double d = new Double(cell.getNumericCellValue());
				params[0] = new Long(d.longValue());
			} else if ((cls[0].getName()).equals("java.lang.Byte")) {
				Double d = new Double(cell.getNumericCellValue());
				params[0] = new Byte(d.byteValue());
			} else if ((cls[0].getName()).equals("java.lang.Float")) {
				Double d = new Double(cell.getNumericCellValue());
				params[0] = new Float(d.floatValue());
			} else if ((cls[0].getName()).equals("java.lang.Double")) {
				Double d = new Double(cell.getNumericCellValue());
				params[0] = d;
			} else if ((cls[0].getName()).equals("java.math.BigDecimal")) {
				String str = new Double(cell.getNumericCellValue()).toString();
				params[0] = new BigDecimal(str);
			} else if ((cls[0].getName()).equals("java.math.BigInteger")) {
				String str = new Double(cell.getNumericCellValue()).toString();
				params[0] = new BigInteger(str);
			} else if ((cls[0].getName()).equals("java.util.Date")) {
				Date da = cell.getDateCellValue();
				params[0] = da;
			} else if ((cls[0].getName()).equals("java.lang.String")) {
				BigInteger bi = BigInteger.valueOf(new Double(cell.getNumericCellValue()).longValue());
				String str = bi.toString();
				params[0] = str;
			} else {
				log.warn("ATENCIOOOOO: tipus num�ric " + cls[0].getName() + " no contemplat a XLSManager");
			}
			meth.invoke(ob, params);
		} else if (type == HSSFCell.CELL_TYPE_ERROR) {
			Byte by = new Byte(cell.getErrorCellValue());
			params[0] = by;
			meth.invoke(ob, params);
		} else if (type == HSSFCell.CELL_TYPE_FORMULA) {
			String str = cell.getCellFormula();
			params[0] = str;
			meth.invoke(ob, params);
		} else if (type == HSSFCell.CELL_TYPE_BLANK) {
			//			params[0]= null;
			//			meth.invoke(ob, params);
			throw new IllegalArgumentException();
		} else {
			log.warn("ATENCIOOOOO: tipus " + type + " no contemplat a XLSManager");
		}

	}

	/**
	 * Efficient convert the first char of a string to UpperCase find with
	 * replace in the string subject. karl@xk72.com
	 * 
	 * @param subject
	 *            Convert first char to Uppercase.
	 */
	public static String firstUpper(String subject) {
		StringBuffer buf = new StringBuffer();
		buf.append(subject.substring(0, 1).toUpperCase());
		buf.append(subject.substring(1));
		return buf.toString();
	}

	/**
	 * Inserte aqu� la descripci�n del m�todo. Fecha de creaci�n: (04/09/2004
	 * 19:39:15)
	 * 
	 * @return java.lang.String
	 * @param stringdate
	 *            java.lang.String
	 */
	public static String composeDate(String stringdate) {
		// Este m�todo siempre recibe un Timestamp.toString que, por deficinici�n
		// tiene el formato: yyyy-mm-dd hh:mm:ss[.f...]. The fractional seconds may be omitted. 
		String finaldate = stringdate.substring(8, 10) + "/" + stringdate.substring(5, 7) + "/" + stringdate.substring(0, 4);
		return finaldate;
	}

	/**
	 * Creates a new Excel Workbook from an Object[]. The Object type of this
	 * array must be another object array (Object[]). This second dimension of
	 * the array must have a length equal to columnCodes. obs has the format returned
	 * by SQLQuery.list().
	 * The excel file will be saved in an OutputStream and the sheet name will be specified by sheetName 
	 * 
	 * @param obs the Object[] to be exported to excel format (.xls)
	 * @param sheetName the name of the sheet
	 * @param columnCodes the names of the fields of obs.
	 * @param columnName the user-friendly name which corresponds to the attribute in the same position of the columnCode
	 * @param out the OutputStream where the workbook will be saved
	 */
	public void createXLSNoBeans(Object[] obs, String sheetName, String[] columnCodes, String[] columnNames, OutputStream out) {
		HSSFWorkbook wb = new HSSFWorkbook();

		if (sheetName.length() > 31) {
			sheetName = sheetName.substring(0, 31);
		}

		HSSFSheet sheet = wb.createSheet(sheetName);
		int i; // row identifier
		short j; // method identifier
		short k;
		short maxColWidth[], width;

		try {

			maxColWidth = new short[columnCodes.length];
			// Print the table Header
			HSSFRow row = sheet.createRow(0);

			HSSFCellStyle style = wb.createCellStyle();

			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			style.setFillForegroundColor(new HSSFColor.YELLOW().getIndex());

			for (k = 0; k < columnCodes.length; k++) {
				HSSFCell cell = row.createCell(k);
				cell.setCellValue(columnNames[k]);
				cell.setCellStyle(style);

				maxColWidth[k] = (short) columnNames[k].length();
			}

			for (i = 0; i < obs.length; i++) { //run over rows
				row = sheet.createRow(i + 1);
				for (j = 0; j < columnCodes.length; j++) { // run over columns   
					Object obaux = ((Object[]) obs[i])[j];
					if (obaux != null) {
						HSSFCell cell = row.createCell(j);
						// format the object if it is a date String
						if (columnCodes[j].endsWith("date") || columnCodes[j].contains("_date_")) {
							obaux = composeDate(obaux.toString());
						}
						// fills the cell with the object
						width = insertValue(wb, cell, obaux);
						// update maximum column width
						maxColWidth[j] = maxim(maxColWidth[j], width);
					}
				}
				//adjust column width with the maxColWidth array
				for (short cont = 0; cont < j; cont++) {
					short mida = maxim((short) (sheet.getDefaultColumnWidth() * 256), (short) (maxColWidth[cont] * 256));
					sheet.setColumnWidth(cont, (short) (mida + 256));
				}
			}

			wb.write(out);

		}
		catch (Exception e) {
			log.warn(e);
		}

	}

	public void createHolidaysXLS(Date start, Date end, Pair<Integer, List<Personal>> _list_personal, List<Irbholiday> holidays, OutputStream out) {

		try {

			HSSFWorkbook wb = new HSSFWorkbook();

			HSSFSheet sheet = wb.createSheet("Holidays");

			HSSFCellStyle weekendStyle = wb.createCellStyle();

			weekendStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			weekendStyle.setFillForegroundColor(new HSSFColor.BROWN().getIndex());

			HSSFCellStyle monthStyle = wb.createCellStyle();
			monthStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

			HSSFRow monthsRow = sheet.createRow(0);

			Map<String, Integer> indexes = new HashMap<String, Integer>();

			HSSFRow numbersRow = sheet.createRow(1);

			int rangeDays = UseCaseUtils.getDiffDays(start, end);

			HSSFFont font8 = wb.createFont();
			font8.setFontName(HSSFFont.FONT_ARIAL);
			font8.setFontHeightInPoints((short) 8);

			weekendStyle.setFont(font8);

			HSSFCellStyle numberStyle = wb.createCellStyle();

			numberStyle.setFont(font8);

			{

				short counter = 0;

				GregorianCalendar dateToUse = new GregorianCalendar();
				dateToUse.setTime(start);

				int currentMonth = -1;
				int firstMonthColumn = 1;

				while (counter < rangeDays) {
					short cellIndex = (short) (counter + 1);

					if (dateToUse.get(Calendar.MONTH) != currentMonth) {
						HSSFCell titlecell = monthsRow.createCell(cellIndex);

						titlecell.setCellStyle(monthStyle);

						titlecell.setCellValue(getDisplayName(dateToUse));

						if (currentMonth != -1) {

							sheet.addMergedRegion(new Region((short) 0, (short) firstMonthColumn, (short) 0, counter));

							firstMonthColumn = cellIndex;
						}

						currentMonth = dateToUse.get(Calendar.MONTH);

					}

					HSSFCell cell = numbersRow.createCell(cellIndex);

					cell.setCellValue(dateToUse.get(Calendar.DAY_OF_MONTH));
					cell.setCellStyle(numberStyle);

					sheet.setColumnWidth(cellIndex, (short) 640);

					dateToUse.add(GregorianCalendar.DAY_OF_YEAR, 1);
					counter++;

				}

				sheet.addMergedRegion(new Region((short) 0, (short) firstMonthColumn, (short) 0, counter));
			}

			int r = 2;

			for (Personal p : _list_personal.getSecond()) {
				HSSFRow row = sheet.createRow(r);

				HSSFCell name = row.createCell((short) 0);
				name.setCellValue(p.getName() + " " + p.getSurname1() + (p.getSurname2() != null && !p.getSurname2().equals("") ? " " + p.getSurname2() : ""));

				indexes.put(p.getCode(), r);
				r++;
			}

			HSSFCellStyle holidayStyle = wb.createCellStyle();

			holidayStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			holidayStyle.setFillForegroundColor(new HSSFColor.GREEN().getIndex());

			HSSFCellStyle apStyle = wb.createCellStyle();

			apStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			apStyle.setFillForegroundColor(new HSSFColor.BLUE().getIndex());

			HSSFCellStyle festivoStyle = wb.createCellStyle();

			festivoStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			festivoStyle.setFillForegroundColor(new HSSFColor.ORANGE().getIndex());
			festivoStyle.setFont(font8);

			Set<Date> festivos = new HashSet<Date>();

			for (Irbholiday h : holidays) {

				if (h.getPersonal() == null) {
					festivos.add(h.getInitialdate());
					continue;
				}

				Integer rowIndex = indexes.get(h.getPersonal().getPersonalcode());

				if (rowIndex == null)
					continue;

				HSSFRow row = sheet.getRow(rowIndex);

				int difDaysInicial = UseCaseUtils.getDiffDays(start, h.getInitialdate());
				int difDaysFinal = UseCaseUtils.getDiffDays(end, h.getEnddate()) - 1;

				if (difDaysFinal < 0)
					difDaysFinal = 0;

				for (int i = 0; i < UseCaseUtils.getDiffDays(h.getInitialdate(), h.getEnddate()) - difDaysFinal; i++) {

					short column = (short) (difDaysInicial + i);

					if (column >= 1) {
						HSSFCell cell = row.createCell(column);

						if (h.getType() == Irbholiday.TYPE_ASUMPTES_PROPIS) {
							cell.setCellStyle(apStyle);
						} else if (h.getType() == Irbholiday.TYPE_VACANCES) {
							cell.setCellStyle(holidayStyle);
						}
					}
				}
			}

			for (int we = 1; we < r; we++) {
				HSSFRow row = sheet.getRow(we);

				//				int rangeDays = UseCaseUtils.getDiffDays(start, end);
				short counter = 0;

				GregorianCalendar dateToUse = new GregorianCalendar();
				dateToUse.setTime(start);

				while (counter < rangeDays) {

					short cellIndex = (short) (counter + 1);

					if (dateToUse.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || dateToUse.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {

						if (row.getCell(cellIndex) == null) {
							row.createCell(cellIndex);
						}

						row.getCell(cellIndex).setCellStyle(weekendStyle);

					}

					if (festivos.contains(dateToUse.getTime())) {
						if (row.getCell(cellIndex) == null) {
							row.createCell(cellIndex);
						}

						row.getCell(cellIndex).setCellStyle(festivoStyle);

					}

					dateToUse.add(GregorianCalendar.DAY_OF_YEAR, 1);
					counter++;
				}

			}

			sheet.setColumnWidth((short) 0, (short) 10000);

			wb.write(out);

		}
		catch (Exception e) {
			log.warn("Error generando excel de vacaciones", e);
		}

	}

	protected static final String[] meses =
			{ "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" };

	private String getDisplayName(GregorianCalendar dateToUse) {

		int mes = dateToUse.get(Calendar.MONTH);

		return meses[mes];
	}

}
