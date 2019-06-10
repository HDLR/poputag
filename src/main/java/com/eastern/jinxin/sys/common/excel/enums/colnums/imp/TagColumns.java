
package com.eastern.jinxin.sys.common.excel.enums.colnums.imp;

/**
 * @author zhaoyong.zhang create time 2013-11-27
 */
public enum TagColumns {
	tag_id("tag_id", "标签id"), tag_nm("tag_nm", "标签名称"), tag_desc("tag_desc", "标签描述"), tag_type_cd("tag_type_cd",
			"标签类型代码"), tag_ctgy_id("tag_ctgy_id", "上级标签"), have_tag_ind("have_tag_ind", "是否有叶子标签"), unknown_ind(
					"unknown_ind", "是否为未知标签"), tag_status("tag_status_str", "标签状态"), enabled_dt("enabled_dt_str",
							"启用时间"), disabled_dt("disabled_dt_str", "禁用时间"), tag_type("tag_type", "数据类型");

	private String code;
	private String codeName;

	TagColumns(String code, String codeName) {
		this.code = code;
		this.codeName = codeName;
	}

	public String getCode() {
		return code;
	}

	public String getCodeName() {
		return codeName;
	}



}