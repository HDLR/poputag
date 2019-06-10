package com.eastern.jinxin.business.userGroup.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
 * @date 2018-05-09 11:22:25
 */
 @TableName("h62_campaign_info")
public class H62CampaignInfoEntity extends Model<H62CampaignInfoEntity> {
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
	
	//活动ID
	@TableId(value="camp_id",type = IdType.AUTO)
	private Integer campId;
	
	//活动名称
		@TableField("camp_nm")
		private String campNm;
	
	//活动描述
		@TableField("camp_desc")
		private String campDesc;
	
	//活动描述
		@TableField("camp_inds_cd")
		private String campIndsCd;
	
	//
		@TableField("start_dt")
		private Date startDt;
	
	//
		@TableField("end_dt")
		private Date endDt;
	
	//目标客户数量
		@TableField("indv_num")
		private Integer indvNum;
	
	//活动渠道代码
		@TableField("camp_chnl_cd")
		private String campChnlCd;
	
	//活动模板ID
		@TableField("templt_id")
		private String templtId;
	
	//活动状态代码
		@TableField("camp_status_cd")
		private String campStatusCd;
	
	//创建用户ID
		@TableField("user_id")
		private Integer userId;
	
	//创建时间
		@TableField("created_ts")
		private Date createdTs;
	
	//最近更新时间
		@TableField("updated_ts")
		private Date updatedTs;
		
	//包含的标签属性
		@TableField(exist = false)
		private List<?> tags;
		
	//符合人数数量
		@TableField(exist = false)
		private long sumPersionCount;
		
		@TableField(exist = false)
		private Map<String, String> tagMap;
		
		@TableField(exist = false)
		private Map<String, Map<String, List<String>>> categoryMap;
		
		//使用次数
		@TableField(exist = false)
		private String usageCount;

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
	 * 设置：活动名称
	 */
	public void setCampNm(String campNm) {
		this.campNm = campNm;
	}
	/**
	 * 获取：活动名称
	 */
	public String getCampNm() {
		return campNm;
	}
	/**
	 * 设置：活动描述
	 */
	public void setCampDesc(String campDesc) {
		this.campDesc = campDesc;
	}
	/**
	 * 获取：活动描述
	 */
	public String getCampDesc() {
		return campDesc;
	}
	/**
	 * 设置：活动描述
	 */
	public void setCampIndsCd(String campIndsCd) {
		this.campIndsCd = campIndsCd;
	}
	/**
	 * 获取：活动描述
	 */
	public String getCampIndsCd() {
		return campIndsCd;
	}
	/**
	 * 设置：
	 */
	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}
	/**
	 * 获取：
	 */
	public Date getStartDt() {
		return startDt;
	}
	/**
	 * 设置：
	 */
	public void setEndDt(Date endDt) {
		this.endDt = endDt;
	}
	/**
	 * 获取：
	 */
	public Date getEndDt() {
		return endDt;
	}
	/**
	 * 设置：目标客户数量
	 */
	public void setIndvNum(Integer indvNum) {
		this.indvNum = indvNum;
	}
	/**
	 * 获取：目标客户数量
	 */
	public Integer getIndvNum() {
		return indvNum;
	}
	/**
	 * 设置：活动渠道代码
	 */
	public void setCampChnlCd(String campChnlCd) {
		this.campChnlCd = campChnlCd;
	}
	/**
	 * 获取：活动渠道代码
	 */
	public String getCampChnlCd() {
		return campChnlCd;
	}
	/**
	 * 设置：活动模板ID
	 */
	public void setTempltId(String templtId) {
		this.templtId = templtId;
	}
	/**
	 * 获取：活动模板ID
	 */
	public String getTempltId() {
		return templtId;
	}
	/**
	 * 设置：活动状态代码
	 */
	public void setCampStatusCd(String campStatusCd) {
		this.campStatusCd = campStatusCd;
	}
	/**
	 * 获取：活动状态代码
	 */
	public String getCampStatusCd() {
		return campStatusCd;
	}
	/**
	 * 设置：创建用户ID
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * 获取：创建用户ID
	 */
	public Integer getUserId() {
		return userId;
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
	public List<?> getTags() {
		return tags;
	}
	public void setTags(List<?> tags) {
		this.tags = tags;
	}
	public long getSumPersionCount() {
		return sumPersionCount;
	}
	public void setSumPersionCount(long sumPersionCount) {
		this.sumPersionCount = sumPersionCount;
	}
	public Map<String, String> getTagMap() {
		return tagMap;
	}
	public void setTagMap(Map<String, String> tagMap) {
		this.tagMap = tagMap;
	}
	public Map<String, Map<String, List<String>>> getCategoryMap() {
		return categoryMap;
	}
	public void setCategoryMap(Map<String, Map<String, List<String>>> categoryMap) {
		this.categoryMap = categoryMap;
	}
	public String getUsageCount() {
		return usageCount;
	}
	public void setUsageCount(String usageCount) {
		this.usageCount = usageCount;
	}
	
}
