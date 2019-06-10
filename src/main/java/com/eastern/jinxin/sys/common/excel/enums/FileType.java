package com.eastern.jinxin.sys.common.excel.enums;

import java.io.Serializable;

/**
 * @Description: 文件类型
 * @author haiping.tang
 * @createUate 2015年11月30日 下午5:58:23
 */
public enum FileType implements Serializable {
	bqTemp("bqTemp", "01标签导入导出模板", "01标签导入导出模板.xlsx"), 
	flatModalTemp("flatModalTemp", "02配置扁平化方式代码表","02配置扁平化方式代码表.xlsx"),
	hiveDataSourceTemp("hiveDataSourceTemp", "03配置Hive读取数据源表","03配置Hive读取数据源表信息.xlsx"),
	hbaseConfTemp("hbaseConfTemp", "04配置Hbase写入目的表信息","04配置Hbase写入目的表信息.xlsx"), 
	calcModelTemp("calcModelTemp", "05指标计算方式表","05指标计算方式表.xlsx"),
	flatRuleConf("flatRuleConf", "06扁平化规则配置表","06扁平化规则配置表.xlsx"),
	confClmnInfoTemp("confClmnInfoTemp", "07指标表字段信息表","07指标表字段信息表.xlsx"),
	confDspsAlgRule("confDspsAlgRule", "08离散化算法规则代码表","08离散化算法规则代码表.xlsx"),
	confDspsAlgType("confDspsAlgType", "09离散化算法类型代码表","09离散化算法类型代码表.xlsx"),
	confDspsAlgInfo("confDspsAlgInfo", "10离散化算法信息表","10离散化算法信息表.xlsx"),
	confDspsAlgRulePara("confDspsAlgRulePara", "11离散化算法规则参数表","11离散化算法规则参数表.xlsx"),
	confDspsMappingPara("confDspsMappingPara", "12离散化标签映射表","12离散化标签映射表.xlsx"),
	confDspsTagMapping("confDspsTagMapping", "13离散化标签映射表","13离散化标签映射表.xlsx"),
	
	campTemp("campTemp", "14用户群组包含的所有人员明细信息","14用户群组包含的所有人员明细信息.xlsx");
	
	private final String code;
	private final String shortTitle;
	private final String desc;

	private FileType(String code, String shortTitle, String desc) {
		this.code = code;
		this.shortTitle = shortTitle;
		this.desc = desc;

	}

	public String getShortTitle() {
		return shortTitle;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	@Override
	public String toString() {
		return this.desc;
	}

	public int getId() {
		return this.ordinal();
	}

	public static FileType getStatus(String ordinal) {

		for (FileType fy : FileType.values()) {
			if (fy.getCode().equals(ordinal)) {
				return fy;
			}
		}
		return null;
	}

	public static String getShortName(String ordinal) {
		for (FileType fy : FileType.values()) {
			if (fy.getCode().equals(ordinal)) {
				return fy.getShortTitle();
			}
		}
		return "";
	}

	public static String getCodeName(String ordinal) {
		for (FileType fy : FileType.values()) {
			if (fy.getCode().equals(ordinal)) {
				return fy.getDesc();
			}
		}
		return "";
	}
}
