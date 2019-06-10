package com.eastern.jinxin.sys.common.excel.enums.colnums;

import java.util.ArrayList;
import java.util.List;

import com.eastern.jinxin.sys.common.excel.enums.colnums.imp.TagColumns;


public class CodeUtils {
	public static List<String> getTagColumnsImport() {
		List<String> list = new ArrayList<String>();
		for (TagColumns u : TagColumns.values()) {
			list.add(u.getCode());
		}
		return list;
	}

	public static List<String> getTagColumnsUpload() {
		List<String> list = new ArrayList<String>();
		for (TagColumns u : TagColumns.values()) {
			list.add(u.getCodeName());
		}
		return list;
	}
	
}
