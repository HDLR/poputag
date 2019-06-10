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
 * @date 2018-05-15 09:20:31
 */
 @TableName("h62_policy_tag_type")
public class H62PolicyTagTypeEntity extends Model<H62PolicyTagTypeEntity> {
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
	
	//
	@TableId(value="tag_type_id",type = IdType.AUTO)
	private Integer tagTypeId;
	
	//
		@TableField("tag_type_nm")
		private String tagTypeNm;
	
	//
		@TableField("tag_type_desc")
		private String tagTypeDesc;
	

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
	public void setTagTypeNm(String tagTypeNm) {
		this.tagTypeNm = tagTypeNm;
	}
	/**
	 * 获取：
	 */
	public String getTagTypeNm() {
		return tagTypeNm;
	}
	/**
	 * 设置：
	 */
	public void setTagTypeDesc(String tagTypeDesc) {
		this.tagTypeDesc = tagTypeDesc;
	}
	/**
	 * 获取：
	 */
	public String getTagTypeDesc() {
		return tagTypeDesc;
	}
	@Override
	protected Serializable pkVal() {
		return this.tagTypeId;
	}
}
