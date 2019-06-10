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
 * @date 2018-05-17 15:45:44
 */
 @TableName("h62_push_his")
public class H62PushHisEntity extends Model<H62PushHisEntity> {
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
	
	//主键
	@TableId(value="his_id",type = IdType.AUTO)
	private Integer hisId;
	
	//推送成功条数
		@TableField("his_success")
		private Integer hisSuccess;
	
	//推送模板id
		@TableField("temp_id")
		private Integer tempId;
	
	//推送失败条数
		@TableField("his_fail")
		private Integer hisFail;
	
	//创建日期
		@TableField("create_dt")
		private Date createDt;
	
	//创建者
		@TableField("create_user")
		private Integer createUser;
	
	//推送状态:0进行中,1已结束
		@TableField("his_statu")
		private Integer hisStatu;
	
	//政策推送主题id
		@TableField("push_id")
		private Integer pushId;
	

	/**
	 * 设置：主键
	 */
	public void setHisId(Integer hisId) {
		this.hisId = hisId;
	}
	/**
	 * 获取：主键
	 */
	public Integer getHisId() {
		return hisId;
	}
	/**
	 * 设置：推送成功条数
	 */
	public void setHisSuccess(Integer hisSuccess) {
		this.hisSuccess = hisSuccess;
	}
	/**
	 * 获取：推送成功条数
	 */
	public Integer getHisSuccess() {
		return hisSuccess;
	}
	/**
	 * 设置：推送模板id
	 */
	public void setTempId(Integer tempId) {
		this.tempId = tempId;
	}
	/**
	 * 获取：推送模板id
	 */
	public Integer getTempId() {
		return tempId;
	}
	/**
	 * 设置：推送失败条数
	 */
	public void setHisFail(Integer hisFail) {
		this.hisFail = hisFail;
	}
	/**
	 * 获取：推送失败条数
	 */
	public Integer getHisFail() {
		return hisFail;
	}
	/**
	 * 设置：创建日期
	 */
	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}
	/**
	 * 获取：创建日期
	 */
	public Date getCreateDt() {
		return createDt;
	}
	/**
	 * 设置：创建者
	 */
	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}
	/**
	 * 获取：创建者
	 */
	public Integer getCreateUser() {
		return createUser;
	}
	/**
	 * 设置：推送状态:0进行中,1已结束
	 */
	public void setHisStatu(Integer hisStatu) {
		this.hisStatu = hisStatu;
	}
	/**
	 * 获取：推送状态:0进行中,1已结束
	 */
	public Integer getHisStatu() {
		return hisStatu;
	}
	/**
	 * 设置：政策推送主题id
	 */
	public void setPushId(Integer pushId) {
		this.pushId = pushId;
	}
	/**
	 * 获取：政策推送主题id
	 */
	public Integer getPushId() {
		return pushId;
	}
	@Override
	protected Serializable pkVal() {
		return this.hisId;
	}
}
