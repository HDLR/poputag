
package com.eastern.jinxin.sys.common.excel.enums.colnums.imp;

import com.eastern.jinxin.sys.common.excel.enums.colnums.BaseExcelEnum;

/**
 * @author zhaoyong.zhang create time 2013-11-27
 */
public enum CalcModelColumns implements BaseExcelEnum{
	indx_calc_mode_cd("indx_calc_mode_cd", "指标计算方式代码",true,true),
	indx_calc_mode_nm("indx_calc_mode_nm", "指标计算方式名称",true,false),
	created_ts_str("created_ts_str", "创建时间",false,false),
	updated_ts_str("updated_ts_str", "修改时间",false,false);
	private String code;
	private String codeName;
	private boolean isUpload;//是否为导入字段
	private boolean isPrimaryKey;//是否主键
	CalcModelColumns(String code,String codeName, boolean isUpload, boolean isPrimaryKey) {
		this.code = code;
		this.codeName = codeName;
		this.isUpload=isUpload;
		this.isPrimaryKey=isPrimaryKey;
	}

	public String getCode() {
		return code;
	}

	public String getCodeName() {
		return codeName;
	}

	public boolean isUpload() {
		return isUpload;
	}

	public void setUpload(boolean isUpload) {
		this.isUpload = isUpload;
	}

	public boolean isPrimaryKey() {
		return isPrimaryKey;
	}

	public void setPrimaryKey(boolean isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}


//	public  List<String> getImports() {
//		List<String> list = new ArrayList<String>();
//		for (CalcModelColumns u : CalcModelColumns.values()) {
//			list.add(u.getCode());
//		}
//		return list;
//	}
//
//	public  List<String>  getUploads() {
//		List<String> list = new ArrayList<String>();
//		for (CalcModelColumns u : CalcModelColumns.values()) {
//			list.add(u.getCodeName());
//		}
//		return list;
//	}
//
//	public  List<String>  getPrimaryKeys() {
//		List<String> list = new ArrayList<String>();
//		for (CalcModelColumns u : CalcModelColumns.values()) {
//			if(u.isPrimaryKey()){
//				list.add(u.getCode());
//			}
//		}
//		return list;
//	}
//	
//	public  Map<String, Object> getInsertParams(HttpServletRequest request) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		for (CalcModelColumns u : CalcModelColumns.values()) {
//			if(u.isUpload()){
//				map.put(u.getCode(), request.getParameter(u.getCode()));
//			}
//		}
//		return map;
//	}

}