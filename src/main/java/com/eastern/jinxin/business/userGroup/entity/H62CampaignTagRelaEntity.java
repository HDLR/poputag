package com.eastern.jinxin.business.userGroup.entity;

import java.io.Serializable;
import java.math.BigInteger;
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
 * @date 2018-05-09 17:36:46
 */
 @TableName("h62_campaign_tag_rela")
public class H62CampaignTagRelaEntity extends Model<H62CampaignTagRelaEntity> {
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
	
	//活动ID
	@TableId(value="camp_id",type = IdType.AUTO)
	private Integer campId;
	
	//标签组序号
		@TableField("tag_group_seq")
		private Integer tagGroupSeq;
	
	//标签ID
		@TableField("tag_id")
		private BigInteger tagId;
	
	//标签类别ID
		@TableField("tag_ctgy_id")
		private String tagCtgyId;
	
	//创建时间
		@TableField("created_ts")
		private Date createdTs;
	
	//最近更新时间
		@TableField("updated_ts")
		private Date updatedTs;
	

	/**
	 * 设置：活动ID
	 */
	public void setCampId(Integer campId) {
		this.campId = campId;
	}
	/**
	 * 获取：活动ID
	 */
	public Integer getCampId() {
		return campId;
	}
	/**
	 * 设置：标签组序号
	 */
	public void setTagGroupSeq(Integer tagGroupSeq) {
		this.tagGroupSeq = tagGroupSeq;
	}
	/**
	 * 获取：标签组序号
	 */
	public Integer getTagGroupSeq() {
		return tagGroupSeq;
	}
	/**
	 * 设置：标签ID
	 */
	public void setTagId(BigInteger tagId) {
		this.tagId = tagId;
	}
	/**
	 * 获取：标签ID
	 */
	public BigInteger getTagId() {
		return tagId;
	}
	/**
	 * 设置：标签类别ID
	 */
	public void setTagCtgyId(String tagCtgyId) {
		this.tagCtgyId = tagCtgyId;
	}
	/**
	 * 获取：标签类别ID
	 */
	public String getTagCtgyId() {
		return tagCtgyId;
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
	@Override
	protected Serializable pkVal() {
		return this.campId;
	}
}
