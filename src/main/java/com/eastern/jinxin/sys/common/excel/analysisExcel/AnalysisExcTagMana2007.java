package com.eastern.jinxin.sys.common.excel.analysisExcel;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.eastern.jinxin.sys.common.excel.model.AnalysisModel;


public class AnalysisExcTagMana2007 extends AnalysisAbs {
	XSSFCell hssfCell = null;
	XSSFRow xssfRow = null;

	public AnalysisExcTagMana2007() {
		super();
	}

	@Override
	public List<Map<String, String>> analysisExcel(InputStream is, AnalysisModel anal) {
		xssWb = null;
		xssSheet = null;
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			xssWb = new XSSFWorkbook(is);
			for (int numSheet = anal.getStartSheet(); numSheet < xssWb.getNumberOfSheets()
					&& numSheet <= anal.getEndSheet(); numSheet++) {
				xssSheet = xssWb.getSheetAt(numSheet);
				if (xssSheet == null) {
					continue;
				}
				for (int rowNum = anal.getStartRow(); rowNum <= xssSheet.getLastRowNum()
						&& rowNum <= anal.getEndRow(); rowNum++) {
					xssfRow = xssSheet.getRow(rowNum);
					if (xssfRow != null) {
						getAllCell(xssfRow, anal.getColums(), list);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		/* 对上传文件的输入流进行处理，跟本地的文件流处理方式相同 */
		return list;
	}

	SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");

	@SuppressWarnings("static-access")
	private void getAllCell(XSSFRow xssfRow, List<String> Colums, List<Map<String, String>> list) {
		Map<String, String> map = new HashMap<String, String>();
		String columnNm = "";
		String numCellVal = "";
		for (int i = 0; i < Colums.size(); i++) {
			columnNm = Colums.get(i);
			hssfCell = xssfRow.getCell(i);
			if (hssfCell == null) {
				continue;
			}
			if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
				map.put(columnNm, (String.valueOf(hssfCell.getBooleanCellValue())).trim());
			} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
				//System.out.println(hssfCell.getNumericCellValue());
				numCellVal = (String.valueOf(hssfCell.getNumericCellValue())).trim();
				if (numCellVal.indexOf(".") > -1) {
					map.put(columnNm, (numCellVal.substring(0, numCellVal.indexOf("."))).trim());
				}

				// numCellVal = String.valueOf(new Double(numCellVal)).trim();
				// if (HSSFDateUtil.isCellDateFormatted(hssfCell)) {
				// map.put(columnNm,
				// formater.format(hssfCell.getDateCellValue()));
				// } else {
				// map.put(columnNm,
				// (String.valueOf(hssfCell.getNumericCellValue())).trim());
				// }
			} else {
				map.put(columnNm, (String.valueOf(hssfCell.getStringCellValue())).trim());
			}
		}
		list.add(map);
	}

}