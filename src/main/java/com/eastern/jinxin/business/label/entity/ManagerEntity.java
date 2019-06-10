package com.eastern.jinxin.business.label.entity;

import java.io.Serializable;
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
 * @date 2018-05-18 09:00:47
 */
 @TableName("manager")
public class ManagerEntity extends Model<ManagerEntity> {
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
	
	//
	@TableId(value="tag_ctgy_id",type = IdType.AUTO)
	private String tagCtgyId;
	
	//
		@TableField("tag_ctgy_nm")
		private String tagCtgyNm;
	
	//
		@TableField("tag_desc")
		private String tagDesc;
	
	//
		@TableField("tag_type_cd")
		private String tagTypeCd;
	
	//
		@TableField("up_tag_ctgy_id")
		private String upTagCtgyId;
	
	//
		@TableField("have_tag_ind")
		private String haveTagInd;
	
	//
		@TableField("tag_ctgy_status")
		private Integer tagCtgyStatus;
	
	//
		@TableField("enabled_dt")
		private Date enabledDt;
	
	//
		@TableField("disabled_dt")
		private Date disabledDt;
	
	//
		@TableField("created_ts")
		private Date createdTs;
	
	//
		@TableField("updated_ts")
		private Date updatedTs;
	
	//标签级别
		@TableField("tag_ctgy_level")
		private String tagCtgyLevel;
	
	//
		@TableField("update_user")
		private Integer updateUser;
	
	//
		@TableField("create_user")
		private Integer createUser;
	
	//取值范围为1
		@TableField("tag_type")
		private String tagType;
	

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
	public void setTagCtgyNm(String tagCtgyNm) {
		this.tagCtgyNm = tagCtgyNm;
	}
	/**
	 * 获取：
	 */
	public String getTagCtgyNm() {
		return tagCtgyNm;
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
	public void setUpTagCtgyId(String upTagCtgyId) {
		this.upTagCtgyId = upTagCtgyId;
	}
	/**
	 * 获取：
	 */
	public String getUpTagCtgyId() {
		return upTagCtgyId;
	}
	/**
	 * 设置：
	 */
	public void setHaveTagInd(String haveTagInd) {
		this.haveTagInd = haveTagInd;
	}
	/**
	 * 获取：
	 */
	public String getHaveTagInd() {
		return haveTagInd;
	}
	/**
	 * 设置：
	 */
	public void setTagCtgyStatus(Integer tagCtgyStatus) {
		this.tagCtgyStatus = tagCtgyStatus;
	}
	/**
	 * 获取：
	 */
	public Integer getTagCtgyStatus() {
		return tagCtgyStatus;
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
	 * 设置：标签级别
	 */
	public void setTagCtgyLevel(String tagCtgyLevel) {
		this.tagCtgyLevel = tagCtgyLevel;
	}
	/**
	 * 获取：标签级别
	 */
	public String getTagCtgyLevel() {
		return tagCtgyLevel;
	}
	/**
	 * 设置：
	 */
	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}
	/**
	 * 获取：
	 */
	public Integer getUpdateUser() {
		return updateUser;
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
	 * 设置：取值范围为1
	 */
	public void setTagType(String tagType) {
		this.tagType = tagType;
	}
	/**
	 * 获取：取值范围为1
	 */
	public String getTagType() {
		return tagType;
	}
	@Override
	protected Serializable pkVal() {
		return this.tagCtgyId;
	}
}
