package com.eastern.jinxin.sys.operation.role.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import com.eastern.jinxin.sys.operation.role.entity.SysRoleMenuEntity;

/**
 * 角色与菜单对应关系
 * 
 * @author zdl_gis
 * @email service@gmail.com
 * @date 2016年9月18日 上午9:33:46
 */
public interface SysRoleMenuDao extends BaseMapper<SysRoleMenuEntity> {
	
	/**
	 * 根据角色ID，获取菜单ID列表
	 */
	List<Long> queryMenuIdList(Long roleId);
}
