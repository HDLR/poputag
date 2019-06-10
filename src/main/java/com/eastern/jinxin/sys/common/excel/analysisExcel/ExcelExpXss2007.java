package com.eastern.jinxin.sys.common.excel.analysisExcel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFFooter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Footer;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 * excel导出类
 * <p>
 * 处理.xlsx格式
 * <p>
 * 
 * @since jdk1.6
 * @date 2016-6-2
 * 
 */

public class ExcelExpXss2007 extends ExcelExp {

	public ExcelExpXss2007() {
		super();
	}

	/**
	 * 构造函数 ExcelExp
	 * 
	 * @param filePath
	 *            文件路径，如com/test/template/test.xlsx
	 * @param sheetNum
	 *            要操作的页签，0为第一个页签
	 * @throws IOException
	 */
	public ExcelExpXss2007(String filePath, int sheetNum) throws IOException {
		URL resource = this.getClass().getClassLoader().getResource(filePath);
		InputStream is = new FileInputStream(resource.getFile());
		xssWb = new XSSFWorkbook(is);
		xssSheet = xssWb.getSheetAt(sheetNum);
	}

	/**
	 * 设置页脚
	 */
	public void createFooter() {
		Footer footer = xssSheet.getFooter();
		footer.setRight("第" + HSSFFooter.page() + "页，共" + HSSFFooter.numPages() + "页");
	}

	/**
	 * 
	 * 插入行
	 * 
	 * @param startRow
	 * @param rows
	 */
	public void insertRows(int startRow, int rows) {
		int bottomRow = xssSheet.getLastRowNum();
		if (startRow > bottomRow) {
			int n = startRow - bottomRow;
			for (int i = 1; i <= n; i++) {
				xssSheet.createRow(bottomRow + i);
			}
		}
		xssSheet.shiftRows(startRow, xssSheet.getLastRowNum(), rows, true, false);
	}

	/**
	 * 
	 * 替换模板中变量
	 * 
	 * @param map
	 */
	public void replaceExcelData(Map<String, String> map) {
		int rowNum = xssSheet.getLastRowNum();
		for (int i = 0; i <= rowNum; i++) {
			XSSFRow row = xssSheet.getRow(i);
			if (row == null)
				continue;
			for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
				XSSFCell cell = row.getCell(j);
				if (cell == null)
					continue;
				String key = cell.getStringCellValue();
				if (map.containsKey(key)) {
					cell.setCellValue(map.get(key));
				}
			}
		}
	}

	@Override
	public void insertDataToExcel(List<Map<String, Object>> list, List<String> columns) throws IOException {
		int bottomRow = xssSheet.getLastRowNum();
		int colCounts = 0;
		XSSFRow row = null;
		XSSFCell cell = null;
		bodyFont();
		for (Map<String, Object> map : list) {
			row = xssSheet.createRow(++bottomRow);
			colCounts = 0;
			for (String col : columns) {
				cell = row.createCell(colCounts++);
				cell.setCellStyle(bodyStyle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue((map.get(col) == null ? "" : map.get(col) + ""));
			}
		}

	}

	/**
	 * 设置body样式
	 */
	private XSSFCellStyle bodyStyle = null;

	private void bodyFont() {
		XSSFFont bodyFont = xssWb.createFont();
		bodyFont.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
		bodyFont.setFontName("宋体");
		bodyFont.setFontHeightInPoints((short) 11);
		bodyStyle = xssWb.createCellStyle();
		bodyStyle.setFont(bodyFont);
		bodyStyle.setBorderTop((short) 1);
		bodyStyle.setBorderRight((short) 1);
		bodyStyle.setBorderBottom((short) 1);
		bodyStyle.setBorderLeft((short) 1);
		// bodyStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		bodyStyle.setAlignment(XSSFCellStyle.ALIGN_LEFT);
		bodyStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
	}

	/**
	 * 下载excel
	 * 
	 * @param response
	 * @param filaName
	 * @throws IOException
	 */
	public void downloadExcel(HttpServletResponse response, String filaName) throws IOException {
		String encodeFileName = URLEncoder.encode(filaName, "UTF-8");
		response.addHeader("Content-Disposition", "attachment;filename=" + encodeFileName);
		ServletOutputStream out = response.getOutputStream();
		xssWb.write(out);
		out.flush();
		out.close();
	}
	
	
	@Override
	public void saveDataToExcel(Map<String, Object> rootMap) throws IOException {
		int bottomRow = xssSheet.getLastRowNum();
		XSSFRow row = null;
		bodyFont();
		
		List<List<Map<String, Object>>> uList = (List<List<Map<String, Object>>>)rootMap.get("uList");
		for(List<Map<String, Object>> u : uList) {
			row = xssSheet.createRow(++bottomRow);
			int colCounts = 1;
			for(Map<String, Object> m : u) {
				this.creatCellN(row, colCounts++, m.get("tagCtgyNm") + ":" + m.get("tagNm"));
			}
		}
	}
	
	private XSSFCell creatCellN(XSSFRow rowN, int cellNum, String value) {
		XSSFCell cellN = rowN.createCell(cellNum);
		cellN.setCellStyle(bodyStyle);
		cellN.setCellType(Cell.CELL_TYPE_STRING);
		cellN.setCellValue(value);
		return cellN;
	}

}