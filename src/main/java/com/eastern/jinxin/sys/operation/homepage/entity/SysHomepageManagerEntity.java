package com.eastern.jinxin.sys.operation.homepage.entity;

import java.io.Serializable;
import java.util.Date;

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
 * @date 2018-08-13 17:14:45
 */
 @TableName("sys_homepage_manager")
public class SysHomepageManagerEntity extends Model<SysHomepageManagerEntity> {
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
	
	//
	@TableId(value="id",type = IdType.AUTO)
	private Integer id;
	
	//创建日期
		@TableField("create_date")
		private Date createDate;
	
	//修改日期
		@TableField("update_date")
		private Date updateDate;
	
	//首页的菜单ID
		@TableField("menu_id")
		private Integer menuId;
	
	//角色ID
		@TableField("role_id")
		private Integer roleId;
	
	//用户ID（当有用户id时，不再使用角色id的首页）
		@TableField("user_id")
		private Integer userId;
	
	//状态 0 关闭、1启用
		private String status;
		//菜单名称
		@TableField("menu_name")
		private String menuName;
		
		@TableField(exist = false)
		private String roleName;
	
	//启用时间
		@TableField("enable_date")
		private Date enableDate;
	
	//关闭时间
		@TableField("disable_date")
		private Date disableDate;
	
		
		/**
		 * @return the roleName
		 */
		public String getRoleName() {
			return roleName;
		}
		/**
		 * @param roleName the roleName to set
		 */
		public void setRoleName(String roleName) {
			this.roleName = roleName;
		}
		/**
		 * @return the menu_name
		 */
		public String getMenuName() {
			return menuName;
		}
		/**
		 * @param menu_name the menu_name to set
		 */
		public void setMenuName(String menuName) {
			this.menuName = menuName;
		}
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
	 * 设置：创建日期
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：创建日期
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * 设置：修改日期
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * 获取：修改日期
	 */
	public Date getUpdateDate() {
		return updateDate;
	}
	/**
	 * 设置：首页的菜单ID
	 */
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	/**
	 * 获取：首页的菜单ID
	 */
	public Integer getMenuId() {
		return menuId;
	}
	/**
	 * 设置：角色ID
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	/**
	 * 获取：角色ID
	 */
	public Integer getRoleId() {
		return roleId;
	}
	/**
	 * 设置：用户ID（当有用户id时，不再使用角色id的首页）
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户ID（当有用户id时，不再使用角色id的首页）
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * 设置：状态 0 关闭、1启用
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：状态 0 关闭、1启用
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：启用时间
	 */
	public void setEnableDate(Date enableDate) {
		this.enableDate = enableDate;
	}
	/**
	 * 获取：启用时间
	 */
	public Date getEnableDate() {
		return enableDate;
	}
	/**
	 * 设置：关闭时间
	 */
	public void setDisableDate(Date disableDate) {
		this.disableDate = disableDate;
	}
	/**
	 * 获取：关闭时间
	 */
	public Date getDisableDate() {
		return disableDate;
	}
	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}
