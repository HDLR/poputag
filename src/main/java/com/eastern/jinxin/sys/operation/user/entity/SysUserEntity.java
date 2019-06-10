package com.eastern.jinxin.sys.operation.user.entity;

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
 * <p>
 * 系统用户
 * </p>
 *
 * @author haiping.tang
 * @since 2018-03-26
 */
@TableName("sys_user")
public class SysUserEntity extends Model<SysUserEntity> {
	@TableField(exist = false)
    private static final long serialVersionUID = 1L;

	@TableId(value="user_id",type = IdType.AUTO)
	private Long userId;
    /**
     * 用户名
     */
	private String username;
    /**
     * 密码
     */
	private String password;
    /**
     * 邮箱
     */
	private String email;
    /**
     * 手机号
     */
	private String mobile;
    /**
     * 状态  0：禁用   1：正常
     */
	private Integer status;
	/**
     * 密码错误次数
     */
	@TableField("miss_number")
	private Integer missNumber;
	
    /**
     * 创建者ID
     */
	@TableField("create_user_id")
	private Long createUserId;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
	
	 /**
     * 锁定时间
     */
	@TableField("lock_time")
	private Date lockTime;
	
	@TableField(exist = false)
	private List<Long>  roleIdList;
	@TableField(exist = false)
	private Set<String> permsSet;
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	
	/**
	 * @return the missNumber
	 */
	public Integer getMissNumber() {
		return missNumber;
	}

	/**
	 * @param missNumber the missNumber to set
	 */
	public void setMissNumber(Integer missNumber) {
		this.missNumber = missNumber;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.userId;
	}

	public List<Long> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(List<Long> roleIdList) {
		this.roleIdList = roleIdList;
	}

	public Set<String> getPermsSet() {
		return permsSet;
	}

	public void setPermsSet(Set<String> permsSet) {
		this.permsSet = permsSet;
	}

	/**
	 * @return the lockTime
	 */
	public Date getLockTime() {
		return lockTime;
	}

	/**
	 * @param lockTime the lockTime to set
	 */
	public void setLockTime(Date lockTime) {
		this.lockTime = lockTime;
	}
	
}
