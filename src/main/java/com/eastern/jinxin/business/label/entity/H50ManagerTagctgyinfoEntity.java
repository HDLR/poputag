package com.eastern.jinxin.business.label.entity;


import java.io.Serializable;
import java.util.ArrayList;
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
 * @date 2018-05-28 17:59:34
 */
 @TableName("h50_manager_tagctgyinfo")
public class H50ManagerTagctgyinfoEntity extends Model<H50ManagerTagctgyinfoEntity> {
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
	
	@TableField(exist = false)
    private List<Long> roleIdList= new ArrayList<Long>();;
	
	//
	@TableId(value="id",type = IdType.AUTO)
	private Integer id;
	
	//
		@TableField("tag_ctgy_id")
		private Long tagCtgyId;
	
	//
		@TableField("tag_ctgy_name")
		private String tagCtgyName;
	
	//
		private String desc;
	
	//
		@TableField("enabled_dt")
		private Date enabledDt;
	
	//
		@TableField("disabled_dt")
		private Date disabledDt;
	
	//
		private String status;
	
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
	
	//
		@TableField("role_id")
		private Integer roleId;
	
	//是否可见
		@TableField("show_flag")
		private String showFlag;
	

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
	 * @return the roleIdList
	 */
	public List<Long> getRoleIdList() {
		return roleIdList;
	}
	/**
	 * @param roleIdList the roleIdList to set
	 */
	public void setRoleIdList(List<Long> roleIdList) {
		this.roleIdList = roleIdList;
	}
	/**
	 * 设置：
	 */
	public void setTagCtgyId(Long tagCtgyId) {
		this.tagCtgyId = tagCtgyId;
	}
	/**
	 * 获取：
	 */
	public Long getTagCtgyId() {
		return tagCtgyId;
	}
	/**
	 * 设置：
	 */
	public void setTagCtgyName(String tagCtgyName) {
		this.tagCtgyName = tagCtgyName;
	}
	/**
	 * 获取：
	 */
	public String getTagCtgyName() {
		return tagCtgyName;
	}
	/**
	 * 设置：
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**
	 * 获取：
	 */
	public String getDesc() {
		return desc;
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
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：
	 */
	public String getStatus() {
		return status;
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
	 * 设置：
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	/**
	 * 获取：
	 */
	public Integer getRoleId() {
		return roleId;
	}
	/**
	 * 设置：是否可见
	 */
	public void setShowFlag(String showFlag) {
		this.showFlag = showFlag;
	}
	/**
	 * 获取：是否可见
	 */
	public String getShowFlag() {
		return showFlag;
	}
	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}
