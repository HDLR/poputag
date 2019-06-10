
package com.eastern.jinxin.sys.common.excel;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.eastern.jinxin.sys.common.excel.analysisExcel.ExcelExp;
import com.eastern.jinxin.sys.common.excel.analysisExcel.ExcelExpXss2007;
import com.eastern.jinxin.sys.common.excel.enums.FileType;


/**
 * @vertion: 1.0.0
 * @Description: 导入导出action
 * @author haiping.tang
 * @createUate 2016年3月8日 下午5:00:50
 * @updateUser
 * @updateContent
 * @updateUate
 */
public class ImportAndUpload {
	private final String RN = "<br>";

	/**
	 * 模板导出
	 * 
	 * @param request
	 * @param response
	 * @param fileType
	 * @throws IOException
	 * @throws Exception
	 */
	public static void downloadFile(HttpServletRequest request, HttpServletResponse response, String tempCode)
			throws IOException, Exception {
		String fileName = FileType.getCodeName(tempCode);
		URL url = ImportAndUpload.class.getClassLoader()
				.getResource(File.separator + "excel" + File.separator + fileName);
		System.out.println("url====="+url);
		String srcDir = url.getPath();
		if (StringUtils.isBlank(srcDir)) {
			throw new Exception("下载文件不存在。");
		}

		if (StringUtils.isBlank(fileName)) {
			throw new Exception("下载文件不存在。");
		}
		// 解决中文文件名乱码问题
		if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
			fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");// firefox浏览器
		} else {
			fileName = URLEncoder.encode(fileName, "UTF-8");
		}
		// 读取文件
		File file = new File(srcDir);
		if (!file.exists()) {
			throw new Exception("下载文件: " + fileName + " 不存在。");
		}

		response.reset();
		byte[] src = FileUtils.readFileToByteArray(file);
		response.setContentLength(src.length);
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
		response.getOutputStream().write(src);
		response.flushBuffer();
	}

	/**
	 * excel导出下载
	 * 
	 * @param request
	 * @param response
	 * @param fileType
	 * @throws IOException
	 * @throws Exception
	 */
	public static void importExcel(HttpServletRequest request, HttpServletResponse response, String tempCode,
			String importName, List<Map<String, Object>> list, List<String> columns) throws IOException, Exception {
		String fileName = FileType.getCodeName(tempCode);
		// 传递模板地址和要操作的页签
		ExcelExp excel = new ExcelExpXss2007("excel" + File.separator + fileName, 0);
		// 创建页脚，打印excel时显示页数
		excel.createFooter();
		// 为模板中变量赋值
		excel.insertDataToExcel(list, columns);
		excel.downloadExcel(response, importName + ".xlsx");
	}
	
	public static void doDownLoaderExcel(HttpServletRequest request, HttpServletResponse response, String tempCode,
			String importName, Map<String, Object> map) throws IOException, Exception {
		String fileName = FileType.getCodeName(tempCode);
		// 传递模板地址和要操作的页签
		ExcelExp excel = new ExcelExpXss2007("excel" + File.separator + fileName, 0);
		// 创建页脚，打印excel时显示页数
		excel.createFooter();
		// 为模板中变量赋值
		excel.saveDataToExcel(map);
		excel.downloadExcel(response, importName + ".xlsx");
	}

	/*
	public static void main(String[] args) {
		String f = ImportAndUpload.class.getResource("temp.xlsx").getPath();
		System.out.println(f);
	}
	*/
}
