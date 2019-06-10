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
 * @date 2018-05-16 16:10:34
 */
 @TableName("h62_policy_push_temp")
public class H62PolicyPushTempEntity extends Model<H62PolicyPushTempEntity> {
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
	
	//模板id
	@TableId(value="temp_id",type = IdType.AUTO)
	private Integer tempId;
	
	//模板名称
		@TableField("temp_nm")
		private String tempNm;
	
	//推送内容
		@TableField("temp_content")
		private String tempContent;
	
	//创建日期
		@TableField("create_dt")
		private Date createDt;
	
	//创建者
		@TableField("create_user")
		private Integer createUser;
	
	//描述
		@TableField("temp_desc")
		private String tempDesc;
		
		@TableField(exist = false)
		private String userName;
	

	/**
	 * 设置：模板id
	 */
	public void setTempId(Integer tempId) {
		this.tempId = tempId;
	}
	/**
	 * 获取：模板id
	 */
	public Integer getTempId() {
		return tempId;
	}
	/**
	 * 设置：模板名称
	 */
	public void setTempNm(String tempNm) {
		this.tempNm = tempNm;
	}
	/**
	 * 获取：模板名称
	 */
	public String getTempNm() {
		return tempNm;
	}
	/**
	 * 设置：推送内容
	 */
	public void setTempContent(String tempContent) {
		this.tempContent = tempContent;
	}
	/**
	 * 获取：推送内容
	 */
	public String getTempContent() {
		return tempContent;
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
	 * 设置：描述
	 */
	public void setTempDesc(String tempDesc) {
		this.tempDesc = tempDesc;
	}
	/**
	 * 获取：描述
	 */
	public String getTempDesc() {
		return tempDesc;
	}
	@Override
	protected Serializable pkVal() {
		return this.tempId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
