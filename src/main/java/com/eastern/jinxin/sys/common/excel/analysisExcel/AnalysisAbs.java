package com.eastern.jinxin.sys.common.excel.analysisExcel;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.eastern.jinxin.sys.common.excel.model.AnalysisModel;


public abstract class AnalysisAbs {
	protected XSSFWorkbook xssWb;

	protected XSSFSheet xssSheet;

	protected HSSFWorkbook hssWb;

	protected HSSFSheet hssSheet;

	
	public abstract List<Map<String, String>>  analysisExcel(InputStream is ,AnalysisModel anal);

	public List<Map<String, String>> initExce(HttpServletRequest request,AnalysisModel anal) {
		InputStream is = null;
		List<Map<String, String>> list =null;
		if (ServletFileUpload.isMultipartContent(request)) {
			// 创建文件上传处理器
			ServletFileUpload upload = new ServletFileUpload();
			// 限制单个上传文件的大小
			upload.setFileSizeMax(1L << 24);
			try {
				// 解析请求
				FileItemIterator iter = upload.getItemIterator(request);
				while (iter.hasNext()) {
					FileItemStream item = iter.next();
					if (!item.isFormField()) {
						// 是文件上传对象，获取上传文件的输入流
						 is = item.openStream();
						 list=analysisExcel(is,anal);
						/* 对上传文件的输入流进行处理，跟本地的文件流处理方式相同 */
					}
				}
			} catch (FileUploadException e) {
				System.out.println("上传文件过大");
			} catch (IOException e) {
				System.out.println("文件读取出现问题");
			}
		}
		return list;
	}

}
