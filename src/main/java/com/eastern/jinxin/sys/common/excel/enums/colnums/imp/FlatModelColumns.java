
package com.eastern.jinxin.sys.common.excel.enums.colnums.imp;

import com.eastern.jinxin.sys.common.excel.enums.colnums.BaseExcelEnum;

/**
 * @author zhaoyong.zhang create time 2013-11-27
 */
public enum FlatModelColumns implements BaseExcelEnum{
	flat_mode_cd("flat_mode_cd", "扁平化方式代码",true,true),
	flat_mode_nm("flat_mode_nm", "扁平化方式名称",true,false),
	created_ts_str("created_ts_str", "创建时间",false,false),
	updated_ts_str("updated_ts_str", "修改时间",false,false);
	private String code;
	private String codeName;
	private boolean isUpload;//是否为导入字段
	private boolean isPrimaryKey;//是否主键
	FlatModelColumns(String code,String codeName, boolean isUpload, boolean isPrimaryKey) {
		this.code = code;
		this.codeName = codeName;
		this.isUpload=isUpload;
		this.isPrimaryKey=isPrimaryKey;
	}
	
	public boolean isPrimaryKey() {
		return isPrimaryKey;
	}

	public void setPrimaryKey(boolean isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}

	public boolean isUpload() {
		return isUpload;
	}

	public void setUpload(boolean isUpload) {
		this.isUpload = isUpload;
	}

	public String getCode() {
		return code;
	}

	public String getCodeName() {
		return codeName;
	}
//	public  List<String> getImports() {
//		List<String> list = new ArrayList<String>();
//		for (FlatModelColumns u : FlatModelColumns.values()) {
//			list.add(u.getCode());
//		}
//		return list;
//	}
//
//	public  List<String>  getUploads() {
//		List<String> list = new ArrayList<String>();
//		for (FlatModelColumns u : FlatModelColumns.values()) {
//			list.add(u.getCodeName());
//		}
//		return list;
//	}
//
//	public  List<String>  getPrimaryKeys() {
//		List<String> list = new ArrayList<String>();
//		for (FlatModelColumns u : FlatModelColumns.values()) {
//			if(u.isPrimaryKey()){
//				list.add(u.getCode());
//			}
//		}
//		return list;
//	}
//	
//	public  Map<String, Object> getInsertParams(HttpServletRequest request) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		for (FlatModelColumns u : FlatModelColumns.values()) {
//			if(u.isUpload()){
//				map.put(u.getCode(), request.getParameter(u.getCode()));
//			}
//		}
//		return map;
//	}

}