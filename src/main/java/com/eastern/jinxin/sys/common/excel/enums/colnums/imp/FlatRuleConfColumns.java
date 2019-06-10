
package com.eastern.jinxin.sys.common.excel.enums.colnums.imp;

import com.eastern.jinxin.sys.common.excel.enums.colnums.BaseExcelEnum;

/**
 * @author zhaoyong.zhang create time 2013-11-27
 */
public enum FlatRuleConfColumns  implements BaseExcelEnum{
	indx_cat_cd("indx_cat_cd", "指标体系代码",true,true),
	indx_tbl_nm("indx_tbl_nm", "指标表名称",true,true),
	inds_cls_cd("inds_cls_cd", "产业分类代码",true,false),
	indx_clmn_nm("indx_clmn_nm", "指标表字段名称",true,true),
	statt_indx_id("statt_indx_id", "统计指标编号",true,true),
	dim_id("dim_id", "维度ID",true,true),
	indx_calc_mode_cd("indx_calc_mode_cd", "指标计算方式代码",true,true),
	indx_calc_mode_nm("indx_calc_mode_nm", "指标计算方式名称",true,false),
	
	flat_tbl_nm("flat_tbl_nm", "扁平表名称 ",true,false),
	flat_clmn_nm("flat_clmn_nm", "扁平表字段名称",true,false),
	active_ind("active_ind", "有效标志",true,false),
	created_ts_str("created_ts_str", "创建时间",false,false),
	updated_ts_str("updated_ts_str", "修改时间",false,false);
	
	private String code;
	private String codeName;

	private boolean isUpload;//是否为导入字段
	private boolean isPrimaryKey;//是否主键
	FlatRuleConfColumns(String code,String codeName, boolean isUpload, boolean isPrimaryKey) {
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
//		for (FlatRuleConfColumns u : FlatRuleConfColumns.values()) {
//			list.add(u.getCode());
//		}
//		return list;
//	}
//
//	public  List<String>  getUploads() {
//		List<String> list = new ArrayList<String>();
//		for (FlatRuleConfColumns u : FlatRuleConfColumns.values()) {
//			list.add(u.getCodeName());
//		}
//		return list;
//	}
//
//	public  List<String>  getPrimaryKeys() {
//		List<String> list = new ArrayList<String>();
//		for (FlatRuleConfColumns u : FlatRuleConfColumns.values()) {
//			if(u.isPrimaryKey()){
//				list.add(u.getCode());
//			}
//		}
//		return list;
//	}
//
//	public  Map<String, Object> getInsertParams(HttpServletRequest request) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		for (FlatRuleConfColumns u : FlatRuleConfColumns.values()) {
//			if(u.isUpload()){
//				map.put(u.getCode(), request.getParameter(u.getCode()));
//			}
//		}
//		return map;
//	}

}