package com.eastern.jinxin.business.label.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
 * @date 2018-05-22 10:12:49
 */
 @TableName("h50_taggroup_info")
public class H50TaggroupInfoEntity extends Model<H50TaggroupInfoEntity> {
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
	
	//
	@TableId(value="tag_id",type = IdType.INPUT)
	private Integer tagId;
	//
		@TableField("tag_nm")
		private String tagNm;
	
	//
		@TableField("tag_desc")
		private String tagDesc;
		//标签包含的人数
		@TableField(exist = false)
		private Long tagHasPersonCount;
	
	//组合标签的具体内容
		@TableField("tag_group_content")
		private String tagGroupContent;
	
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
		private Integer updateUser;
	
	//取值范围为2
		@TableField("tag_type")
		private String tagType;
		
		@TableField(exist = false)
		private List<?> tags;
	

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
	 * 设置：组合标签的具体内容
	 */
	public void setTagDesc(String tagDesc) {
		this.tagDesc = tagDesc;
	}
	/**
	 * 获取：组合标签的具体内容
	 */
	public String getTagDesc() {
		return tagDesc;
	}
	/**
	 * 设置：
	 */
	public void setTagGroupContent(String tagGroupContent) {
		this.tagGroupContent = tagGroupContent;
	}
	/**
	 * 获取：
	 */
	public String getTagGroupContent() {
		return tagGroupContent;
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
	/**
	 * @return the tagHasPersonCount
	 */
	public Long getTagHasPersonCount() {
		return tagHasPersonCount;
	}
	/**
	 * @param tagHasPersonCount the tagHasPersonCount to set
	 */
	public void setTagHasPersonCount(Long tagHasPersonCount) {
		this.tagHasPersonCount = tagHasPersonCount;
	}
	/**
	 * @return the tags
	 */
	public List<?> getTags() {
		return tags;
	}
	/**
	 * @param tags the tags to set
	 */
	public void setTags(List<?> tags) {
		this.tags = tags;
	}
	
}
