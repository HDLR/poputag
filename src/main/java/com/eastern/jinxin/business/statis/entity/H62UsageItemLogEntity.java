package com.eastern.jinxin.business.statis.entity;

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
 * @date 2018-05-14 09:43:56
 */
 @TableName("h62_usage_item_log")
public class H62UsageItemLogEntity extends Model<H62UsageItemLogEntity> {
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
	
	//
	@TableId(value="id",type = IdType.AUTO)
	private Integer id;
	
	//操作的camp_id，tag_id等等
		@TableField("item_id")
		private Integer itemId;
	
	//用于区分item_id是camp_id，tag_id或别的id，01：camp_id，02：tag_id
		@TableField("item_type")
		private String itemType;
	
	//用于区分是哪一个界面操作的，01：候鸟人群筛选，02：群内微观画像，03：群内宏观画像
		@TableField("operate_type")
		private String operateType;
	
	//操作user_id
		@TableField("operate_user")
		private Integer operateUser;
	
	//操作时间
		@TableField("created_ts")
		private Date createdTs;
	
	//更新时间
		@TableField("updated_ts")
		private Date updatedTs;
	

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：操作的camp_id，tag_id等等
	 */
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	/**
	 * 获取：操作的camp_id，tag_id等等
	 */
	public Integer getItemId() {
		return itemId;
	}
	/**
	 * 设置：用于区分item_id是camp_id，tag_id或别的id，01：camp_id，02：tag_id
	 */
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	/**
	 * 获取：用于区分item_id是camp_id，tag_id或别的id，01：camp_id，02：tag_id
	 */
	public String getItemType() {
		return itemType;
	}
	/**
	 * 设置：用于区分是哪一个界面操作的，01：候鸟人群筛选，02：群内微观画像，03：群内宏观画像
	 */
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	/**
	 * 获取：用于区分是哪一个界面操作的，01：候鸟人群筛选，02：群内微观画像，03：群内宏观画像
	 */
	public String getOperateType() {
		return operateType;
	}
	/**
	 * 设置：操作user_id
	 */
	public void setOperateUser(Integer operateUser) {
		this.operateUser = operateUser;
	}
	/**
	 * 获取：操作user_id
	 */
	public Integer getOperateUser() {
		return operateUser;
	}
	/**
	 * 设置：操作时间
	 */
	public void setCreatedTs(Date createdTs) {
		this.createdTs = createdTs;
	}
	/**
	 * 获取：操作时间
	 */
	public Date getCreatedTs() {
		return createdTs;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdatedTs(Date updatedTs) {
		this.updatedTs = updatedTs;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdatedTs() {
		return updatedTs;
	}
	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}
