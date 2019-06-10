package com.eastern.jinxin.business.recommend.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;



/**
 * 
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-05-15 09:21:02
 */
 @TableName("h62_policy_tag")
public class H62PolicyTagEntity extends Model<H62PolicyTagEntity> {
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
	
	//
	@TableId(value="tag_id",type = IdType.AUTO)
	private Integer tagId;
	
	//
		@TableField("tag_nm")
		private String tagNm;
	
	//
		@TableField("tag_type_id")
		private Integer tagTypeId;
	
	//
		@TableField("tag_desc")
		private String tagDesc;
	

	/**
	 * 设置：
	 */
	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}
	/**
	 * 获取：
	 */
	public Integer getTagId() {
		return tagId;
	}
	/**
	 * 设置：
	 */
	public void setTagNm(String tagNm) {
		this.tagNm = tagNm;
	}
	/**
	 * 获取：
	 */
	public String getTagNm() {
		return tagNm;
	}
	/**
	 * 设置：
	 */
	public void setTagTypeId(Integer tagTypeId) {
		this.tagTypeId = tagTypeId;
	}
	/**
	 * 获取：
	 */
	public Integer getTagTypeId() {
		return tagTypeId;
	}
	/**
	 * 设置：
	 */
	public void setTagDesc(String tagDesc) {
		this.tagDesc = tagDesc;
	}
	/**
	 * 获取：
	 */
	public String getTagDesc() {
		return tagDesc;
	}
	@Override
	protected Serializable pkVal() {
		return this.tagId;
	}
}
