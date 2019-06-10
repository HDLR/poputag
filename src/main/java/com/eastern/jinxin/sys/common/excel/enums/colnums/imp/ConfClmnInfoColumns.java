
package com.eastern.jinxin.sys.common.excel.enums.colnums.imp;

import com.eastern.jinxin.sys.common.excel.enums.colnums.BaseExcelEnum;

/**
 * @author zhaoyong.zhang create time 2013-11-27
 */
public enum ConfClmnInfoColumns  implements BaseExcelEnum{
	indx_cat_cd("indx_cat_cd", "指标体系代码",true,true), 
	indx_tbl_nm("indx_tbl_nm", "指标表名称",true,true), 
	indx_clmn_nm("indx_clmn_nm", "指标表字段名称",true,true),
	indx_clmn_desc("indx_clmn_desc", "指标表字段描述",true,false), 
	//度量字段标识  1:度量 0:非度量
	msmt_clmn_ind("msmt_clmn_ind", "度量",true,false),
	created_ts("created_ts_str", "创建时间",false,false),
	updated_ts("updated_ts_str", "修改时间",false,false);
	private String code;
	private String codeName;

	private boolean isUpload;//是否为导入字段,是否为查询字段
	private boolean isPrimaryKey;//是否主键
	ConfClmnInfoColumns(String code,String codeName, boolean isUpload, boolean isPrimaryKey) {
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