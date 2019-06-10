package com.eastern.jinxin.sys.operation.user.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;


/**
 * <p>
 * 用户与角色对应关系
 * </p>
 *
 * @author haiping.tang
 * @since 2018-03-26
 */
@TableName("sys_user_role")
public class SysUserRoleEntity extends Model<SysUserRoleEntity> {
	@TableField(exist = false)
    private static final long serialVersionUID = 1L;
	@TableId(type = IdType.AUTO)
	private Long id;
    /**
     * 用户ID
     */
	@TableField("user_id")
	private Long userId;
    /**
     * 角色ID
     */
	@TableField("role_id")
	private Long roleId;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
