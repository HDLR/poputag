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
 * 标签需求
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-05-18 17:01:40
 */
 @TableName("tag_req_record")
public class TagReqRecordEntity extends Model<TagReqRecordEntity> {
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
	
	//
	@TableId(value="id",type = IdType.AUTO)
	private Long id;
	
	//标签需求名称
		@TableField("req_name")
		private String reqName;
	
	//标签需求描述
		@TableField("req_describe")
		private String reqDescribe;
	
	//配置webservice的API接口id
		@TableField("webservice_id")
		private Long webserviceId;
	
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
		
		@TableField(exist = false)
		private WebserviceConfEntity webserviceConfEntity;
	
		//
		@TableField("approval_status")
		private String approvalStatus;
		//
		@TableField("approval_date")
		private Date approvalDate;
		//
		@TableField("approval_user")
		private String approvalUser;
		
		@TableField("url")
		private String url;
		
		@TableField("method")
		private String method;
		
	/**
		 * @return the approvalStatus
		 */
		public String getApprovalStatus() {
			return approvalStatus;
		}
		/**
		 * @return the approvalDate
		 */
		public Date getApprovalDate() {
			return approvalDate;
		}
		/**
		 * @return the approvalUser
		 */
		public String getApprovalUser() {
			return approvalUser;
		}
		/**
		 * @param approvalStatus the approvalStatus to set
		 */
		public void setApprovalStatus(String approvalStatus) {
			this.approvalStatus = approvalStatus;
		}
		/**
		 * @param approvalDate the approvalDate to set
		 */
		public void setApprovalDate(Date approvalDate) {
			this.approvalDate = approvalDate;
		}
		/**
		 * @param approvalUser the approvalUser to set
		 */
		public void setApprovalUser(String approvalUser) {
			this.approvalUser = approvalUser;
		}
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
	 * 设置：标签需求名称
	 */
	public void setReqName(String reqName) {
		this.reqName = reqName;
	}
	/**
	 * 获取：标签需求名称
	 */
	public String getReqName() {
		return reqName;
	}
	/**
	 * 设置：标签需求描述
	 */
	public void setReqDescribe(String reqDescribe) {
		this.reqDescribe = reqDescribe;
	}
	/**
	 * 获取：标签需求描述
	 */
	public String getReqDescribe() {
		return reqDescribe;
	}
	/**
	 * 设置：配置webservice的API接口id
	 */
	public void setWebserviceId(Long webserviceId) {
		this.webserviceId = webserviceId;
	}
	/**
	 * 获取：配置webservice的API接口id
	 */
	public Long getWebserviceId() {
		return webserviceId;
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
	public WebserviceConfEntity getWebserviceConfEntity() {
		return webserviceConfEntity;
	}
	public void setWebserviceConfEntity(WebserviceConfEntity webserviceConfEntity) {
		this.webserviceConfEntity = webserviceConfEntity;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	
}
