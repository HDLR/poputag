package com.eastern.jinxin.business.labelReq.entity;

import java.io.Serializable;
import java.util.ArrayList;
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
 * @date 2019-03-01 17:29:58
 */
 @TableName("label_task_mana")
public class LabelTaskManaEntity extends Model<LabelTaskManaEntity> {
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
	
	//
	@TableId(value="id",type = IdType.AUTO)
	private Integer id;
	
	//对应标签需求申请id
		@TableField("apply_id")
		private Integer applyId;
		
		@TableField(exist = false)
		private String applyName;
		
	//创建者
		@TableField("user_id")
		private Long userId;
		
		@TableField(exist = false)
		private String username;
	
	//审核结果
		private String check;
	
	//什么结果原因
		@TableField("check_reason")
		private String checkReason;
		
	//操作时间
		@TableField("cztime")
		private Date cztime;
		
		@TableField(exist = false)
		private List<LabelTaskProcessEntity> processs = new ArrayList<LabelTaskProcessEntity>();

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
	 * 设置：对应标签需求申请id
	 */
	public void setApplyId(Integer applyId) {
		this.applyId = applyId;
	}
	/**
	 * 获取：对应标签需求申请id
	 */
	public Integer getApplyId() {
		return applyId;
	}
	/**
	 * 设置：创建者
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 获取：创建者
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * 设置：审核结果
	 */
	public void setCheck(String check) {
		this.check = check;
	}
	/**
	 * 获取：审核结果
	 */
	public String getCheck() {
		return check;
	}
	/**
	 * 设置：什么结果原因
	 */
	public void setCheckReason(String checkReason) {
		this.checkReason = checkReason;
	}
	/**
	 * 获取：什么结果原因
	 */
	public String getCheckReason() {
		return checkReason;
	}
	@Override
	protected Serializable pkVal() {
		return this.id;
	}
	public String getApplyName() {
		return applyName;
	}
	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<LabelTaskProcessEntity> getProcesss() {
		return processs;
	}
	public void setProcesss(List<LabelTaskProcessEntity> processs) {
		this.processs = processs;
	}
	public Date getCztime() {
		return cztime;
	}
	public void setCztime(Date cztime) {
		this.cztime = cztime;
	}
	
}
