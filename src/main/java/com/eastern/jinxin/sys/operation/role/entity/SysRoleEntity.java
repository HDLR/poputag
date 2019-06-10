package com.eastern.jinxin.sys.operation.role.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;


/**
 * <p>
 * 角色
 * </p>
 *
 * @author haiping.tang
 * @since 2018-03-26
 */
@TableName("sys_role")
public class SysRoleEntity extends Model<SysRoleEntity> {
	@TableField(exist = false)
    private static final long serialVersionUID = 1L;
	@TableId(value="role_id",type = IdType.AUTO)
	private Long roleId;
    /**
     * 角色名称
     */
	@TableField("role_name")
	private String roleName;
    /**
     * 备注
     */
	private String remark;
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
	@TableField(exist = false)
	private List<Long> menuIdList;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
		return this.roleId;
	}

	public List<Long> getMenuIdList() {
		return menuIdList;
	}

	public void setMenuIdList(List<Long> menuIdList) {
		this.menuIdList = menuIdList;
	}

}
