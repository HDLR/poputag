package com.eastern.jinxin.sys.operation.role.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;


/**
 * <p>
 * 角色与菜单对应关系
 * </p>
 *
 * @author haiping.tang
 * @since 2018-03-26
 */
@TableName("sys_role_menu")
public class SysRoleMenuEntity extends Model<SysRoleMenuEntity> {
	@TableField(exist = false)
    private static final long serialVersionUID = 1L;
	@TableId(type = IdType.AUTO)
	private Long id;
    /**
     * 角色ID
     */
	@TableField("role_id")
	private Long roleId;
    /**
     * 菜单ID
     */
	@TableField("menu_id")
	private Long menuId;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
