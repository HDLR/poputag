package com.eastern.jinxin.sys.operation.role.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

import com.eastern.jinxin.sys.operation.role.entity.SysRoleMenuEntity;



/**
 * 角色与菜单对应关系
 * 
 * @author zdl_gis
 * @email service@gmail.com
 * @date 2016年9月18日 上午9:42:30
 */
public interface SysRoleMenuService extends IService<SysRoleMenuEntity>{
	
	void saveOrUpdate(Long roleId, List<Long> menuIdList);
	
	/**
	 * 根据角色ID，获取菜单ID列表
	 */
	List<Long> queryMenuIdList(Long roleId);
	
}
