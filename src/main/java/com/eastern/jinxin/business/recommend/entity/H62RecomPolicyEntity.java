package com.eastern.jinxin.business.recommend.entity;

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
 * @date 2018-05-14 15:00:30
 */
 @TableName("h62_recom_policy")
public class H62RecomPolicyEntity extends Model<H62RecomPolicyEntity> {
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
	
	//
	@TableId(value="id",type = IdType.AUTO)
	private Integer id;
	
	//
		@TableField("policy_id")
		private String policyId;
	
	//
		@TableField("policy_nm")
		private String policyNm;
	
	//
		@TableField("policy_cont")
		private String policyCont;
	
	//
		@TableField("policy_href")
		private String policyHref;
	
	//
		@TableField("create_dt")
		private Date createDt;
	
	//
		@TableField("create_user")
		private Integer createUser;
		
		@TableField(exist = false)
		private List<?> tags;
		
		@TableField(exist = false)
		private Map<String, String> tagMap;
		
		@TableField(exist = false)
		private String userName;
	

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
	 * 设置：
	 */
	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}
	/**
	 * 获取：
	 */
	public String getPolicyId() {
		return policyId;
	}
	/**
	 * 设置：
	 */
	public void setPolicyNm(String policyNm) {
		this.policyNm = policyNm;
	}
	/**
	 * 获取：
	 */
	public String getPolicyNm() {
		return policyNm;
	}
	/**
	 * 设置：
	 */
	public void setPolicyCont(String policyCont) {
		this.policyCont = policyCont;
	}
	/**
	 * 获取：
	 */
	public String getPolicyCont() {
		return policyCont;
	}
	/**
	 * 设置：
	 */
	public void setPolicyHref(String policyHref) {
		this.policyHref = policyHref;
	}
	/**
	 * 获取：
	 */
	public String getPolicyHref() {
		return policyHref;
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
	@Override
	protected Serializable pkVal() {
		return this.id;
	}
	public List<?> getTags() {
		return tags;
	}
	public void setTags(List<?> tags) {
		this.tags = tags;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Map<String, String> getTagMap() {
		return tagMap;
	}
	public void setTagMap(Map<String, String> tagMap) {
		this.tagMap = tagMap;
	}
	
}
