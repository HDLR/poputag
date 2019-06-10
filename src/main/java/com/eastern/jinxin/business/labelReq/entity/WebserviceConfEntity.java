package com.eastern.jinxin.business.labelReq.entity;

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
 * webservice的API
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-05-19 10:52:54
 */
 @TableName("webservice_conf")
public class WebserviceConfEntity extends Model<WebserviceConfEntity> {
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
	
	//
	@TableId(value="id",type = IdType.AUTO)
	private Long id;
	
	//API名称
		@TableField("api_name")
		private String apiName;
	
	//请求路径
		private String url;
	
	//请求方法
		private String method;
	
	//请求参数描述
		@TableField("req_params")
		private String reqParams;
	
	//返回结果描述
		@TableField("return_res")
		private String returnRes;
	
	//创建时间
		@TableField("create_date")
		private Date createDate;
	
	//更新时间
		@TableField("update_date")
		private Date updateDate;
	
	//
		@TableField("create_user")
		private Integer createUser;
		
		@TableField(exist = false)
		private String username;
	

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：API名称
	 */
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}
	/**
	 * 获取：API名称
	 */
	public String getApiName() {
		return apiName;
	}
	/**
	 * 设置：请求路径
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取：请求路径
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置：请求方法
	 */
	public void setMethod(String method) {
		this.method = method;
	}
	/**
	 * 获取：请求方法
	 */
	public String getMethod() {
		return method;
	}
	/**
	 * 设置：请求参数描述
	 */
	public void setReqParams(String reqParams) {
		this.reqParams = reqParams;
	}
	/**
	 * 获取：请求参数描述
	 */
	public String getReqParams() {
		return reqParams;
	}
	/**
	 * 设置：返回结果描述
	 */
	public void setReturnRes(String returnRes) {
		this.returnRes = returnRes;
	}
	/**
	 * 获取：返回结果描述
	 */
	public String getReturnRes() {
		return returnRes;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdateDate() {
		return updateDate;
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
	@Override
	protected Serializable pkVal() {
		return this.id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}
