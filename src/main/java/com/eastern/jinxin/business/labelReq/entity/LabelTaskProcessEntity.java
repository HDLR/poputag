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
 * 
 * 
 * @author service
 * @email service@gmail.com
 * @date 2019-03-01 17:29:58
 */
 @TableName("label_task_process")
public class LabelTaskProcessEntity extends Model<LabelTaskProcessEntity> {
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
	
	//
	@TableId(value="id",type = IdType.AUTO)
	private Integer id;
	
	//
		private String time;
	
	//
		private String process;
	
	//
		@TableField("create_time")
		private Date createTime;
	
	//
		@TableField("task_mana_id")
		private Integer taskManaId;
	

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
	public void setTime(String time) {
		this.time = time;
	}
	/**
	 * 获取：
	 */
	public String getTime() {
		return time;
	}
	/**
	 * 设置：
	 */
	public void setProcess(String process) {
		this.process = process;
	}
	/**
	 * 获取：
	 */
	public String getProcess() {
		return process;
	}
	/**
	 * 设置：
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：
	 */
	public Date getCreateTime() {
		return createTime;
	}
	@Override
	protected Serializable pkVal() {
		return this.id;
	}
	public Integer getTaskManaId() {
		return taskManaId;
	}
	public void setTaskManaId(Integer taskManaId) {
		this.taskManaId = taskManaId;
	}
	
}
