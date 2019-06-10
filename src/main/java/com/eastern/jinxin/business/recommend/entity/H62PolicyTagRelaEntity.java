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
 * @date 2018-05-15 09:18:51
 */
 @TableName("h62_policy_tag_rela")
public class H62PolicyTagRelaEntity extends Model<H62PolicyTagRelaEntity> {
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
	
	//
	@TableId(value="id",type = IdType.AUTO)
	private Integer id;
	
	//
		@TableField("policy_id")
		private Integer policyId;
	
	//
		@TableField("policy_tag_id")
		private Integer policyTagId;
	

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
	public void setPolicyTagId(Integer policyTagId) {
		this.policyTagId = policyTagId;
	}
	/**
	 * 获取：
	 */
	public Integer getPolicyTagId() {
		return policyTagId;
	}
	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}
