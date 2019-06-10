package com.eastern.jinxin.business.userGroup.entity;


import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;



/**
 * 扁平化规则配置表
 * 
 * @author service
 * @email service@gmail.com
 * @date 2019-04-25 10:05:15
 */
 @TableName("h50_flat_rule_config")
public class H50FlatRuleConfigEntity extends Model<H50FlatRuleConfigEntity> {
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
	
	//指标体系代码
	@TableId(value="indx_cat_cd",type = IdType.AUTO)
	private String indxCatCd;
	
	//指标表名称
		@TableField("indx_tbl_nm")
		private String indxTblNm;
	
	//产业分类代码
		@TableField("inds_cls_cd")
		private String indsClsCd;
	
	//指标表字段名称
		@TableField("indx_clmn_nm")
		private String indxClmnNm;
	
	//统计指标编号
		@TableField("statt_indx_id")
		private String stattIndxId;
	
	//维度ID
		@TableField("dim_id")
		private String dimId;
	
	//指标计算方式代码
		@TableField("indx_calc_mode_cd")
		private String indxCalcModeCd;
	
	//扁平表名称 自动生成，根据扁平化方式不同 直接：指标表表名+指标表字段名 行转列：维度ID+指标ID+度量字段名
		@TableField("flat_tbl_nm")
		private String flatTblNm;
	
	//扁平表字段名称
		@TableField("flat_clmn_nm")
		private String flatClmnNm;
	
	//有效标志 1：有效；0：无效
		@TableField("active_ind")
		private String activeInd;
	
	//创建时间
		@TableField("created_ts")
		private Date createdTs;
	
	//最近更新时间
		@TableField("updated_ts")
		private Date updatedTs;
	
	//
		@TableField("tag_ctgy_nm")
		private String tagCtgyNm;
	

	/**
	 * 设置：指标体系代码
	 */
	public void setIndxCatCd(String indxCatCd) {
		this.indxCatCd = indxCatCd;
	}
	/**
	 * 获取：指标体系代码
	 */
	public String getIndxCatCd() {
		return indxCatCd;
	}
	/**
	 * 设置：指标表名称
	 */
	public void setIndxTblNm(String indxTblNm) {
		this.indxTblNm = indxTblNm;
	}
	/**
	 * 获取：指标表名称
	 */
	public String getIndxTblNm() {
		return indxTblNm;
	}
	/**
	 * 设置：产业分类代码
	 */
	public void setIndsClsCd(String indsClsCd) {
		this.indsClsCd = indsClsCd;
	}
	/**
	 * 获取：产业分类代码
	 */
	public String getIndsClsCd() {
		return indsClsCd;
	}
	/**
	 * 设置：指标表字段名称
	 */
	public void setIndxClmnNm(String indxClmnNm) {
		this.indxClmnNm = indxClmnNm;
	}
	/**
	 * 获取：指标表字段名称
	 */
	public String getIndxClmnNm() {
		return indxClmnNm;
	}
	/**
	 * 设置：统计指标编号
	 */
	public void setStattIndxId(String stattIndxId) {
		this.stattIndxId = stattIndxId;
	}
	/**
	 * 获取：统计指标编号
	 */
	public String getStattIndxId() {
		return stattIndxId;
	}
	/**
	 * 设置：维度ID
	 */
	public void setDimId(String dimId) {
		this.dimId = dimId;
	}
	/**
	 * 获取：维度ID
	 */
	public String getDimId() {
		return dimId;
	}
	/**
	 * 设置：指标计算方式代码
	 */
	public void setIndxCalcModeCd(String indxCalcModeCd) {
		this.indxCalcModeCd = indxCalcModeCd;
	}
	/**
	 * 获取：指标计算方式代码
	 */
	public String getIndxCalcModeCd() {
		return indxCalcModeCd;
	}
	/**
	 * 设置：扁平表名称 自动生成，根据扁平化方式不同 直接：指标表表名+指标表字段名 行转列：维度ID+指标ID+度量字段名
	 */
	public void setFlatTblNm(String flatTblNm) {
		this.flatTblNm = flatTblNm;
	}
	/**
	 * 获取：扁平表名称 自动生成，根据扁平化方式不同 直接：指标表表名+指标表字段名 行转列：维度ID+指标ID+度量字段名
	 */
	public String getFlatTblNm() {
		return flatTblNm;
	}
	/**
	 * 设置：扁平表字段名称
	 */
	public void setFlatClmnNm(String flatClmnNm) {
		this.flatClmnNm = flatClmnNm;
	}
	/**
	 * 获取：扁平表字段名称
	 */
	public String getFlatClmnNm() {
		return flatClmnNm;
	}
	/**
	 * 设置：有效标志 1：有效；0：无效
	 */
	public void setActiveInd(String activeInd) {
		this.activeInd = activeInd;
	}
	/**
	 * 获取：有效标志 1：有效；0：无效
	 */
	public String getActiveInd() {
		return activeInd;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreatedTs(Date createdTs) {
		this.createdTs = createdTs;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreatedTs() {
		return createdTs;
	}
	/**
	 * 设置：最近更新时间
	 */
	public void setUpdatedTs(Date updatedTs) {
		this.updatedTs = updatedTs;
	}
	/**
	 * 获取：最近更新时间
	 */
	public Date getUpdatedTs() {
		return updatedTs;
	}
	/**
	 * 设置：
	 */
	public void setTagCtgyNm(String tagCtgyNm) {
		this.tagCtgyNm = tagCtgyNm;
	}
	/**
	 * 获取：
	 */
	public String getTagCtgyNm() {
		return tagCtgyNm;
	}
	@Override
	protected Serializable pkVal() {
		return this.indxCatCd;
	}
}
