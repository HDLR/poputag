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
 * @date 2018-05-17 09:34:12
 */
 @TableName("h62_policy_push")
public class H62PolicyPushEntity extends Model<H62PolicyPushEntity> {
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
	
	//
	@TableId(value="push_id",type = IdType.AUTO)
	private Integer pushId;
	
	//
		@TableField("policy_id")
		private Integer policyId;
	
	//
		@TableField("camp_id")
		private Integer campId;
	
	//
		@TableField("push_nm")
		private String pushNm;
	
	//
		@TableField("push_desc")
		private String pushDesc;
	
	//
		@TableField("create_dt")
		private Date createDt;
	
	//
		@TableField("create_user")
		private Integer createUser;
	
	//推送模板id
		@TableField("temp_id")
		private Integer tempId;
		
		@TableField(exist = false)
		private String policyNm;
		
		@TableField(exist = false)
		private String tempNm;
		
		@TableField(exist = false)
		private String campNm;
		
		@TableField(exist = false)
		private String username;
		
		@TableField(exist = false)
		private List<?> policys;
		
		@TableField(exist = false)
		private List<?> camps;
		
		@TableField(exist = false)
		private List<?> temps;
	

	/**
	 * 设置：
	 */
	public void setPushId(Integer pushId) {
		this.pushId = pushId;
	}
	/**
	 * 获取：
	 */
	public Integer getPushId() {
		return pushId;
	}
	/**
	 * 设置：
	 */
	public void setPolicyId(Integer policyId) {
		this.policyId = policyId;
	}
	/**
	 * 获取：
	 */
	public Integer getPolicyId() {
		return policyId;
	}
	/**
	 * 设置：
	 */
	public void setCampId(Integer campId) {
		this.campId = campId;
	}
	/**
	 * 获取：
	 */
	public Integer getCampId() {
		return campId;
	}
	/**
	 * 设置：
	 */
	public void setPushNm(String pushNm) {
		this.pushNm = pushNm;
	}
	/**
	 * 获取：
	 */
	public String getPushNm() {
		return pushNm;
	}
	/**
	 * 设置：
	 */
	public void setPushDesc(String pushDesc) {
		this.pushDesc = pushDesc;
	}
	/**
	 * 获取：
	 */
	public String getPushDesc() {
		return pushDesc;
	}
	/**
	 * 设置：
	 */
	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}
	/**
	 * 获取：
	 */
	public Date getCreateDt() {
		return createDt;
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
	@Override
	protected Serializable pkVal() {
		return this.pushId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPolicyNm() {
		return policyNm;
	}
	public void setPolicyNm(String policyNm) {
		this.policyNm = policyNm;
	}
	public String getTempNm() {
		return tempNm;
	}
	public void setTempNm(String tempNm) {
		this.tempNm = tempNm;
	}
	public String getCampNm() {
		return campNm;
	}
	public void setCampNm(String campNm) {
		this.campNm = campNm;
	}
	public List<?> getPolicys() {
		return policys;
	}
	public void setPolicys(List<?> policys) {
		this.policys = policys;
	}
	public List<?> getCamps() {
		return camps;
	}
	public void setCamps(List<?> camps) {
		this.camps = camps;
	}
	public List<?> getTemps() {
		return temps;
	}
	public void setTemps(List<?> temps) {
		this.temps = temps;
	}
	
}
