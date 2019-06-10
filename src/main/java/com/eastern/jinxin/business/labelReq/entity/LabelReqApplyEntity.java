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
 * @date 2019-02-28 10:58:16
 */
 @TableName("label_req_apply")
public class LabelReqApplyEntity extends Model<LabelReqApplyEntity> {
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
	
	//审核结果(1通过，2不通过), 3生产结束
	@TableField(exist = false)
	public static final String CHECK_PASS = "1";
	@TableField(exist = false)
	public static final String CHECK_NO_PASS = "2";
	@TableField(exist = false)
	public static final String CHECK_PROCESS_SECCESS = "3";
	
	public static boolean checkProcess(String checkValue) {
		if(CHECK_PASS.equals(checkValue) || CHECK_NO_PASS.equals(checkValue) || CHECK_PROCESS_SECCESS.equals(checkValue)) {
			return true;
		}
		return false;
	}
	
	//
	@TableId(value="apply_id",type = IdType.AUTO)
	private Integer applyId;
	
	//标签名称
		private String name;
	
	//标签描述
		private String describe;
	
	//标签类别
		private String type;
	
	//标签生产申请人
		@TableField("apply_userid")
		private Integer applyUserid;
	
	//申请部门
		private String dep;
	
	//审核结果(1通过，2不通过)
		private String check;
	
	//需求提出时间
		@TableField("apply_time")
		private Date applyTime;
	
	//备注信息，审核人标注审核是否通过原因
		private String remark;
	

	/**
	 * 设置：
	 */
	public void setApplyId(Integer applyId) {
		this.applyId = applyId;
	}
	/**
	 * 获取：
	 */
	public Integer getApplyId() {
		return applyId;
	}
	/**
	 * 设置：标签名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：标签名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：标签描述
	 */
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	/**
	 * 获取：标签描述
	 */
	public String getDescribe() {
		return describe;
	}
	/**
	 * 设置：标签类别
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：标签类别
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：标签生产申请人
	 */
	public void setApplyUserid(Integer applyUserid) {
		this.applyUserid = applyUserid;
	}
	/**
	 * 获取：标签生产申请人
	 */
	public Integer getApplyUserid() {
		return applyUserid;
	}
	/**
	 * 设置：申请部门
	 */
	public void setDep(String dep) {
		this.dep = dep;
	}
	/**
	 * 获取：申请部门
	 */
	public String getDep() {
		return dep;
	}
	/**
	 * 设置：审核结果(1通过，2不通过)
	 */
	public void setCheck(String check) {
		this.check = check;
	}
	/**
	 * 获取：审核结果(1通过，2不通过)
	 */
	public String getCheck() {
		return check;
	}
	/**
	 * 设置：需求提出时间
	 */
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	/**
	 * 获取：需求提出时间
	 */
	public Date getApplyTime() {
		return applyTime;
	}
	/**
	 * 设置：备注信息，审核人标注审核是否通过原因
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注信息，审核人标注审核是否通过原因
	 */
	public String getRemark() {
		return remark;
	}
	@Override
	protected Serializable pkVal() {
		return this.applyId;
	}
}
