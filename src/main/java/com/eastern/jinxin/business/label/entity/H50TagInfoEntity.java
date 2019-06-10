package com.eastern.jinxin.business.label.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

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
 * @date 2018-05-10 11:30:11
 */
 @TableName("h50_tag_info")
public class H50TagInfoEntity extends Model<H50TagInfoEntity> {
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
	
	//
	@TableId(value="tag_id",type = IdType.INPUT)
	private BigInteger tagId;
	
	//
		@TableField("tag_nm")
		private String tagNm;
	
	//
		@TableField("tag_desc")
		private String tagDesc;
	
	//
		@TableField("tag_type_cd")
		private String tagTypeCd;
	
	//
		@TableField("tag_ctgy_id")
		private String tagCtgyId;
	
	//
		@TableField("enabled_dt")
		private Date enabledDt;
	
	//
		@TableField("disabled_dt")
		private Date disabledDt;
	
	//
		@TableField("active_ind")
		private String activeInd;
	
	//
		@TableField("unknown_ind")
		private String unknownInd;
	
	//
		@TableField("created_ts")
		private Date createdTs;
	
	//
		@TableField("updated_ts")
		private Date updatedTs;
	
	//
		@TableField("create_user")
		private Integer createUser;
	
	//
		@TableField("update_user")
		private Long updateUser;
	
	//取值范围为2
		@TableField("tag_type")
		private String tagType;
	

	/**
	 * 设置：
	 */
	public void setTagId(BigInteger tagId) {
		this.tagId = tagId;
	}
	/**
	 * 获取：
	 */
	public BigInteger getTagId() {
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
	public void setTagDesc(String tagDesc) {
		this.tagDesc = tagDesc;
	}
	/**
	 * 获取：
	 */
	public String getTagDesc() {
		return tagDesc;
	}
	/**
	 * 设置：
	 */
	public void setTagTypeCd(String tagTypeCd) {
		this.tagTypeCd = tagTypeCd;
	}
	/**
	 * 获取：
	 */
	public String getTagTypeCd() {
		return tagTypeCd;
	}
	/**
	 * 设置：
	 */
	public void setTagCtgyId(String tagCtgyId) {
		this.tagCtgyId = tagCtgyId;
	}
	/**
	 * 获取：
	 */
	public String getTagCtgyId() {
		return tagCtgyId;
	}
	/**
	 * 设置：
	 */
	public void setEnabledDt(Date enabledDt) {
		this.enabledDt = enabledDt;
	}
	/**
	 * 获取：
	 */
	public Date getEnabledDt() {
		return enabledDt;
	}
	/**
	 * 设置：
	 */
	public void setDisabledDt(Date disabledDt) {
		this.disabledDt = disabledDt;
	}
	/**
	 * 获取：
	 */
	public Date getDisabledDt() {
		return disabledDt;
	}
	/**
	 * 设置：
	 */
	public void setActiveInd(String activeInd) {
		this.activeInd = activeInd;
	}
	/**
	 * 获取：
	 */
	public String getActiveInd() {
		return activeInd;
	}
	/**
	 * 设置：
	 */
	public void setUnknownInd(String unknownInd) {
		this.unknownInd = unknownInd;
	}
	/**
	 * 获取：
	 */
	public String getUnknownInd() {
		return unknownInd;
	}
	/**
	 * 设置：
	 */
	public void setCreatedTs(Date createdTs) {
		this.createdTs = createdTs;
	}
	/**
	 * 获取：
	 */
	public Date getCreatedTs() {
		return createdTs;
	}
	/**
	 * 设置：
	 */
	public void setUpdatedTs(Date updatedTs) {
		this.updatedTs = updatedTs;
	}
	/**
	 * 获取：
	 */
	public Date getUpdatedTs() {
		return updatedTs;
	}
	/**
	 * 设置：
	 */
	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}
	/**
	 * 获取：
	 */
	public Integer getCreateUser() {
		return createUser;
	}
	/**
	 * 设置：
	 */
	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}
	/**
	 * 获取：
	 */
	public Long getUpdateUser() {
		return updateUser;
	}
	/**
	 * 设置：取值范围为2
	 */
	public void setTagType(String tagType) {
		this.tagType = tagType;
	}
	/**
	 * 获取：取值范围为2
	 */
	public String getTagType() {
		return tagType;
	}
	@Override
	protected Serializable pkVal() {
		return this.tagId;
	}
}
