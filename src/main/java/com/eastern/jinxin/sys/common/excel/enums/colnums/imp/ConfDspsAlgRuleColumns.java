
package com.eastern.jinxin.sys.common.excel.enums.colnums.imp;

import com.eastern.jinxin.sys.common.excel.enums.colnums.BaseExcelEnum;

/**
 * @author zhaoyong.zhang create time 2013-11-27
 */
public enum ConfDspsAlgRuleColumns implements BaseExcelEnum{
	dsps_alg_rule_cd("dsps_alg_rule_cd", "离散化算法规则代码",true,true), 
	dsps_alg_rule_desc("dsps_alg_rule_desc", "离散化算法规则描述",true,false), 
	created_ts_str("created_ts_str", "创建时间",false,false), 
	updated_ts_str("updated_ts_str", "修改时间",false,false),;
	private String code;
	private String codeName;
	private boolean isUpload;//是否为导入字段
	private boolean isPrimaryKey;//是否主键
	ConfDspsAlgRuleColumns(String code,String codeName, boolean isUpload, boolean isPrimaryKey) {
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
}