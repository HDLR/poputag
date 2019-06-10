package com.eastern.jinxin.sys.operation.schedule.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;


/**
 * <p>
 * 定时任务
 * </p>
 *
 * @author haiping.tang
 * @since 2018-03-26
 */
@TableName("schedule_job")
public class ScheduleJobEntity extends Model<ScheduleJobEntity> {
	@TableField(exist = false)
    private static final long serialVersionUID = 1L;

	/**
	 * 任务调度参数key
	 */
	@TableField(exist = false)
    public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY";
    /**
     * 任务id
     */
	@TableId("job_id")
	private Long jobId;
    /**
     * spring bean名称
     */
	@NotBlank(message="bean名称不能为空")
	@TableField("bean_name")
	private String beanName;
    /**
     * 方法名
     */
	@NotBlank(message="方法名称不能为空")
	@TableField("method_name")
	private String methodName;
    /**
     * 参数
     */
	private String params;
    /**
     * cron表达式
     */

	@NotBlank(message="cron表达式不能为空")
	@TableField("cron_expression")
	private String cronExpression;
    /**
     * 任务状态
     */
	private Integer status;
    /**
     * 备注
     */
	private String remark;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;


	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.jobId;
	}

}
