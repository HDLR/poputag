
package com.eastern.jinxin.sys.common.excel.enums.colnums.imp;

import com.eastern.jinxin.sys.common.excel.enums.colnums.BaseExcelEnum;

/**
 * @author zhaoyong.zhang create time 2013-11-27
 */
public enum ConfDspsAlgRuleParaColumns implements BaseExcelEnum{
	dsps_alg_id("dsps_alg_id", "离散化算法ID",true,true), 
	dsps_alg_desc("dsps_alg_desc", "离散化算法描述",true,false), 
	rule_para_seq("rule_para_seq", "规则参数序号",true,true), 
	rule_para_value("rule_para_value", "规则参数值",true,false), 
	created_ts_str("created_ts_str", "创建时间",false,false), 
	updated_ts_str("updated_ts_str", "修改时间",false,false);
	
	
	private String code;
	private String codeName;
	private boolean isUpload;//是否为导入字段
	private boolean isPrimaryKey;//是否主键
	ConfDspsAlgRuleParaColumns(String code,String codeName, boolean isUpload, boolean isPrimaryKey) {
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
//		for (HbaseConfColumns u : HbaseConfColumns.values()) {
//			list.add(u.getCode());
//		}
//		return list;
//	}
//
//	public  List<String>  getUploads() {
//		List<String> list = new ArrayList<String>();
//		for (HbaseConfColumns u : HbaseConfColumns.values()) {
//			list.add(u.getCodeName());
//		}
//		return list;
//	}
//
//	public  List<String>  getPrimaryKeys() {
//		List<String> list = new ArrayList<String>();
//		for (HbaseConfColumns u : HbaseConfColumns.values()) {
//			if(u.isPrimaryKey()){
//				list.add(u.getCode());
//			}
//		}
//		return list;
//	}
//
//	public  Map<String, Object> getInsertParams(HttpServletRequest request) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		for (HbaseConfColumns u : HbaseConfColumns.values()) {
//			if(u.isUpload()){
//				map.put(u.getCode(), request.getParameter(u.getCode()));
//			}
//		}
//		return map;
//	}
}