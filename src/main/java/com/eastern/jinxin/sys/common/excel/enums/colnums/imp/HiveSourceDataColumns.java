
package com.eastern.jinxin.sys.common.excel.enums.colnums.imp;

import com.eastern.jinxin.sys.common.excel.enums.colnums.BaseExcelEnum;

/**
 * @author zhaoyong.zhang create time 2013-11-27
 */
public enum HiveSourceDataColumns  implements BaseExcelEnum{
	indx_cat_cd("indx_cat_cd", "指标体系代码",true,true), 
	indx_cat_nm("indx_cat_nm", "指标体系名称",true,false), 
	indx_tbl_nm("indx_tbl_nm", "指标表名称",true,true),
	indx_tbl_desc("indx_tbl_desc", "指标表描",true,false), 
	inds_cls_cd("inds_cls_cd", "产业分类代码",true,false),
	flat_mode_cd("flat_mode_cd", "扁平化方式代码",true,false),
	flat_mode_nm("flat_mode_nm", "扁平化方式名称",true,false), 
	dim_clmn_nm("dim_clmn_nm", "维度字段名称",true,false),
	key_clmn_nm("key_clmn_nm", "指标键字段名称",true,false),
	ext_condition("ext_condition", "指标表查询条件",true,false),
	created_ts("created_ts_str", "创建时间",false,false),
	updated_ts("updated_ts_str", "修改时间",false,false);
	
	private String code;
	private String codeName;

	private boolean isUpload;//是否为导入字段,是否为查询字段
	private boolean isPrimaryKey;//是否主键
	HiveSourceDataColumns(String code,String codeName, boolean isUpload, boolean isPrimaryKey) {
		this.code = code;
		this.codeName = codeName;
		this.isUpload=isUpload;
		this.isPrimaryKey=isPrimaryKey;
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
	public boolean isPrimaryKey() {
		return isPrimaryKey;
	}
	public void setPrimaryKey(boolean isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}
//	public  List<String> getImports() {
//		List<String> list = new ArrayList<String>();
//		for (HiveSourceDataColumns u : HiveSourceDataColumns.values()) {
//			list.add(u.getCode());
//		}
//		return list;
//	}
//
//	public  List<String>  getUploads() {
//		List<String> list = new ArrayList<String>();
//		for (HiveSourceDataColumns u : HiveSourceDataColumns.values()) {
//			list.add(u.getCodeName());
//		}
//		return list;
//	}
//
//	public  List<String>  getPrimaryKeys() {
//		List<String> list = new ArrayList<String>();
//		for (HiveSourceDataColumns u : HiveSourceDataColumns.values()) {
//			if(u.isPrimaryKey()){
//				list.add(u.getCode());
//			}
//		}
//		return list;
//	}
//
//	public  Map<String, Object> getInsertParams(HttpServletRequest request) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		for (HiveSourceDataColumns u : HiveSourceDataColumns.values()) {
//			if(u.isUpload()){
//				map.put(u.getCode(), request.getParameter(u.getCode()));
//			}
//		}
//		return map;
//	}
}