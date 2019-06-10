package com.eastern.jinxin.business.label.entity;


import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;



/**
 * 法人库部门部门归集统计信息表
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-06-27 16:28:34
 */
 @TableName("corp_depart_stat_info")
public class CorpDepartStatInfoEntity extends Model<CorpDepartStatInfoEntity> {
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
	
	//部门名称
	@TableId(value="depart_name",type = IdType.AUTO)
	private String departName;
	
	//部门标识
		@TableField("depart_flag")
		private String departFlag;
	
	//表名
		@TableField("table_name")
		private String tableName;
		
	//表名的数量
		@TableField(exist = false)
		private String tableCount;
	
	//表中文名
		@TableField("table_name_zh")
		private String tableNameZh;
	
		
	/**
		 * @return the tableCount
		 */
		public String getTableCount() {
			return tableCount;
		}
		/**
		 * @param tableCount the tableCount to set
		 */
		public void setTableCount(String tableCount) {
			this.tableCount = tableCount;
		}
	/**
	 * 设置：部门名称
	 */
	public void setDepartName(String departName) {
		this.departName = departName;
	}
	/**
	 * 获取：部门名称
	 */
	public String getDepartName() {
		return departName;
	}
	/**
	 * 设置：部门标识
	 */
	public void setDepartFlag(String departFlag) {
		this.departFlag = departFlag;
	}
	/**
	 * 获取：部门标识
	 */
	public String getDepartFlag() {
		return departFlag;
	}
	/**
	 * 设置：表名
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	/**
	 * 获取：表名
	 */
	public String getTableName() {
		return tableName;
	}
	/**
	 * 设置：表中文名
	 */
	public void setTableNameZh(String tableNameZh) {
		this.tableNameZh = tableNameZh;
	}
	/**
	 * 获取：表中文名
	 */
	public String getTableNameZh() {
		return tableNameZh;
	}
	@Override
	protected Serializable pkVal() {
		return this.departName;
	}
}
